package top.yaohc.lease.web.admin.service.impl;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import top.yaohc.lease.common.minio.MinioProperties;
import top.yaohc.lease.web.admin.service.FileService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioProperties properties;

    @Autowired
    private MinioClient client;

    @Override
    public String upload(MultipartFile file) {
//        System.out.println(properties.getBucketName());
        try {
            boolean bucketExists = client.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
            if (!bucketExists) {
                client.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
                client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(properties.getBucketName()).config(createBucketPolicyConfig(properties.getBucketName())).build());
            }

            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            client.putObject(PutObjectArgs.builder().
                    bucket(properties.getBucketName()).
                    object(filename).
                    stream(file.getInputStream(), file.getSize(), -1).
                    contentType(file.getContentType()).build());

            return String.join("/", properties.getEndpoint(), properties.getBucketName(), filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }
}
