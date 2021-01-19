package com.baozi.admin.convert;

import com.baozi.admin.dto.UpdateOperationDTO;
import com.baozi.admin.entity.SysOperationDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName SysOperationConvert
 * @Description 菜单按钮转换器
 * @Author LiZuoYang
 * @Date 2021/1/19 19:42
 **/
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface SysOperationConvert {
    SysOperationConvert INSTANCE = Mappers.getMapper(SysOperationConvert.class);

    @Mapping(target = "createdBy", source = "actionBy")
    @Mapping(target = "updatedBy", source = "actionBy")
    SysOperationDO dto2do(UpdateOperationDTO dto);
}
