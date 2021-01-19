package com.baozi.admin.convert;

import com.baozi.admin.dto.SystemUpdateDTO;
import com.baozi.admin.entity.SysSystemDO;
import com.baozi.admin.vo.SysSystemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName SysSystemConvert
 * @Description SysSystem 转换器
 * @Author LiZuoYang
 * @Date 2020/12/14 10:53
 **/
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SystemConvert {
    SystemConvert INSTANCE = Mappers.getMapper(SystemConvert.class);

    @Mapping(target = "id", expression = "java(system.getId().toString())")
    SysSystemVo do2vo(SysSystemDO system);

    List<SysSystemVo> dos2vos(List<SysSystemDO> system);

    @Mapping(target = "createdBy", source = "actionBy")
    @Mapping(target = "updatedBy", source = "actionBy")
    SysSystemDO dto2do(SystemUpdateDTO dto);
}
