package top.yaohc.lease.web.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.yaohc.lease.model.entity.AttrKey;
import top.yaohc.lease.web.admin.mapper.AttrKeyMapper;
import top.yaohc.lease.web.admin.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yaohc.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
        implements AttrKeyService {

    @Autowired(required = false)
    private AttrKeyMapper mapper;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return mapper.listAttrInfo();
    }

}




