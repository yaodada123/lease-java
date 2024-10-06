package top.yaohc.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisPlusConfiguration
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/6 下午8:38
 * @Version 1.0
 */
@Configuration
@MapperScan("top.yaohc.lease.web.*.mapper")
public class MybatisPlusConfiguration {

}
