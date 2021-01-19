package com.baozi.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baozi.admin.entity.SysUserDO;
import com.baozi.admin.query.SysUserListQuery;
import com.baozi.admin.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户表  Mapper 接口
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
public interface SysUserMapper extends BaseMapper<SysUserDO> {
    List<SysUserVo> selectListWithRoleInfo(SysUserListQuery query);

    SysUserVo selectOneWithRoleInfo(@Param("uid") String userId);
}
