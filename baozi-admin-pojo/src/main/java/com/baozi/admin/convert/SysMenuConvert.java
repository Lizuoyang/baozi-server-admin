package com.baozi.admin.convert;

import com.baozi.admin.dto.SysMenuUpdateDTO;
import com.baozi.admin.entity.SysMenuDO;
import com.baozi.admin.vo.SysMenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @ClassName SysMenuConvert
 * @Description 菜单转换器
 * @Author LiZuoYang
 * @Date 2020/12/10 16:10
 **/
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenuVo do2vo (SysMenuDO menu);

    List<SysMenuVo> dos2vos (List<SysMenuDO> menus);

    @Mapping(target = "createdBy", source = "actionBy")
    @Mapping(target = "updatedBy", source = "actionBy")
    SysMenuDO dto2do(SysMenuUpdateDTO dto);
}
