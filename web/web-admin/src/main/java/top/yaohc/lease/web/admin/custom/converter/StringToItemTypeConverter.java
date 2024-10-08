package top.yaohc.lease.web.admin.custom.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import top.yaohc.lease.model.enums.ItemType;

/**
 * ClassName: StringToItemTypeConverter
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/8 下午9:19
 * @Version 1.0
 */
@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
    @Override
    public ItemType convert(String code) {
//        System.out.println(111);

        for (ItemType value : ItemType.values()) {
            if (value.getCode().equals(Integer.valueOf(code))) {
                return value;
            }
        }
        throw new IllegalArgumentException("code非法");
    }
}
