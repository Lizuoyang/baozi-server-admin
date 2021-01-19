package com.baozi.admin.convert;

import com.baozi.admin.dto.SysUserUpdateDTO;
import com.baozi.admin.entity.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;


/**
 * @ClassName SysUserCovert
 * @Description sysuser 转换器
 * @Author LiZuoYang
 * @Date 2020/12/8 10:48
 **/
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    @Mapping(target = "createdBy", source = "actionBy")
    @Mapping(target = "updatedBy", source = "actionBy")
    SysUserDO dto2do(SysUserUpdateDTO dto);
}
