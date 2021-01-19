package com.baozi.admin.convert;

import com.baozi.admin.dto.SysRoleDTO;
import com.baozi.admin.entity.SysRoleDO;
import com.baozi.admin.vo.SysRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SysRoleCovert {
    SysRoleCovert INSTANCE = Mappers.getMapper(SysRoleCovert.class);

    @Mapping(target = "createdBy", source = "actionBy")
    @Mapping(target = "updatedBy", source = "actionBy")
    SysRoleDO dto2do(SysRoleDTO sysRoleDTO);

    @Mapping(target = "id", expression="java(String.valueOf(sysRole.getId()))")
    SysRoleVo do2vo(SysRoleDO sysRole);

    List<SysRoleVo> dos2vos(List<SysRoleDO> sysRole);

}
