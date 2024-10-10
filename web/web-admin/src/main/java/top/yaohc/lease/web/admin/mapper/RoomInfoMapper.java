package top.yaohc.lease.web.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.yaohc.lease.model.entity.RoomInfo;
import top.yaohc.lease.web.admin.vo.room.RoomItemVo;
import top.yaohc.lease.web.admin.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity top.yaohc.lease.model.RoomInfo
*/
@Mapper
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

}




