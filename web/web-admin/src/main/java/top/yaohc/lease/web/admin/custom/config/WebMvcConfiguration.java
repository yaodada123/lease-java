package top.yaohc.lease.web.admin.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.yaohc.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
//import top.yaohc.lease.web.admin.custom.converter.StringToItemTypeConverter;

/**
 * ClassName: WebMvcConfiguration
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/8 下午8:49
 * @Version 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        System.out.println("之心了");
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }
}
