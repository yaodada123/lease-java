package top.yaohc.lease.web.admin.custom.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import top.yaohc.lease.model.enums.BaseEnum;

/**
 * ClassName: StringToBaseEnumConverterFactory
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/8 下午8:46
 * @Version 1.0
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                System.out.println("-----------------9090--------------------");
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    if (enumConstant.getCode().equals(Integer.valueOf(source))) {
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("非法的枚举值:" + source);
            }
        };
    }

}
