package top.yaohc.lease.web.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yaohc.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【payment_type(支付方式表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity top.yaohc.lease.model.PaymentType
 */
@Mapper
public interface PaymentTypeMapper extends BaseMapper<PaymentType> {

}




