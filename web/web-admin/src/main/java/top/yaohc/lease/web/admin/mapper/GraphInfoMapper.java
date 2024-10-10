package top.yaohc.lease.web.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yaohc.lease.model.entity.GraphInfo;
import top.yaohc.lease.model.enums.ItemType;
import top.yaohc.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity top.yaohc.lease.model.GraphInfo
*/
@Mapper
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {
    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long itemId);
}




