package top.yaohc.lease.web.admin.service.impl;

import top.yaohc.lease.model.entity.SystemUser;
import top.yaohc.lease.web.admin.mapper.SystemUserMapper;
import top.yaohc.lease.web.admin.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

}




