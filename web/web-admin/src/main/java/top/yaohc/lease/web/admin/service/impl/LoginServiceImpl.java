package top.yaohc.lease.web.admin.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.util.StringUtils;
import top.yaohc.lease.common.constant.RedisConstant;
import top.yaohc.lease.common.exception.LeaseException;
import top.yaohc.lease.common.result.ResultCodeEnum;
import top.yaohc.lease.common.utils.JwtUtil;
import top.yaohc.lease.model.entity.SystemUser;
import top.yaohc.lease.model.enums.BaseStatus;
import top.yaohc.lease.web.admin.mapper.SystemUserMapper;
import top.yaohc.lease.web.admin.service.LoginService;
import org.springframework.stereotype.Service;
import top.yaohc.lease.web.admin.vo.login.CaptchaVo;
import top.yaohc.lease.web.admin.vo.login.LoginVo;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        String image = specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        return new CaptchaVo(image, key);
    }

    @Override
    public String login(LoginVo loginVo) {
        //1.判断是否输入了验证码
        if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        //2.校验验证码
        String code = redisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //3.校验用户是否存在
        SystemUser systemUser = systemUserMapper.selectOneByUsername(loginVo.getUsername());

        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        //4.校验用户是否被禁
        if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        //5.校验用户密码
        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            System.out.println("密码不正确");
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        String temp = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVU0VSX0lORk8iLCJleHAiOjE3Mjg3MDM1NTMsInVzZXJJZCI6MSwidXNlcm" +
                "5hbWUiOiJ6aGFuZ3NhbiJ9.BXi_anNvyhnmecl7hz7I1ehK7qHABUGsBaW0RCm6cok";
        //6.创建并返回TOKEN
        return temp;
//        return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
    }
}
