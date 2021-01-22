package com.baozi.admin.manager;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baoz.admin.annation.Manager;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baoz.admin.enums.common.GlobalEnums;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.convert.SystemConvert;
import com.baozi.admin.dto.SystemUpdateDTO;
import com.baozi.admin.entity.SysSystemDO;
import com.baozi.admin.query.SystemListQuery;
import com.baozi.admin.service.SysSystemService;
import com.baozi.admin.vo.SysSystemVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysSystemManager
 * @Description 系统管理 Manager
 * @Author LiZuoYang
 * @Date 2020/12/17 15:31
 **/
@Manager
public class SysSystemManager {

    @Autowired
    private SysSystemService systemService;

    /**
     * @author lizuoyang
     * @description 系统管理 - 修改
     * @date 16:15 2020/12/17
     * @param dto
     * @return boolean
     **/
    public boolean saveOrupdate(SystemUpdateDTO dto) {
        SysSystemDO sysSystem = SystemConvert.INSTANCE.dto2do(dto);

        boolean updResult = Boolean.FALSE;
        if (StringUtil.isNotEmpty(dto.getId())) {
            sysSystem.setUpdatedTime(new Date());
            updResult = systemService.updateById(sysSystem);
        } else {
            sysSystem.setId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_TEM));
            sysSystem.setCreatedTime(new Date());
            updResult = systemService.save(sysSystem);
        }

        return updResult;
    }

    /**
     * @author lizuoyang
     * @description 系统管理 -> 分页查询
     * @date 15:38 2020/12/17
     * @param query
     * @return java.util.List<com.purcotton.omni.vo.SysSystemVo>
     **/
    public PageInfo<SysSystemVo> listPageSystems(SystemListQuery query) {
        LambdaQueryWrapper<SysSystemDO> queryQW = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(query.getSystemName())) {
            queryQW.like(SysSystemDO::getSystemName, query.getSystemName());
        }
        if (StringUtil.isNotEmpty(query.getSystemCode())) {
            queryQW.like(SysSystemDO::getSystemCode, query.getSystemCode());
        }

        if (ObjectUtil.isNotEmpty(query.getIsDelete())) {
            queryQW.eq(SysSystemDO::getIsDelete, query.getIsDelete());
        }

        queryQW.orderByAsc(SysSystemDO::getIsDelete);
        queryQW.orderByAsc(SysSystemDO::getSystemSort);
        queryQW.orderByDesc(SysSystemDO::getCreatedTime);

        List<SysSystemDO> sysSystems = systemService.list(queryQW);
        List<SysSystemVo> sysSystemVos = SystemConvert.INSTANCE.dos2vos(sysSystems);
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<SysSystemVo> pageInfo = new PageInfo<>(sysSystemVos);
        pageInfo.setList(sysSystemVos);
        return pageInfo;

    }

    /**
     * @author lizuoyang
     * @description 获取所有系统
     * @date 15:33 2020/12/17
     * @param
     * @return java.util.List<com.purcotton.omni.vo.SysSystemVo>
     **/
    public List<SysSystemVo> listSystemsAll() {
        LambdaQueryWrapper<SysSystemDO> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.eq(SysSystemDO::getIsDelete, GlobalEnums.NORMAL);
        lambdaQuery.orderByAsc(SysSystemDO::getSystemSort);
        List<SysSystemDO> list = systemService.list(lambdaQuery);
        List<SysSystemVo> sysSystemVos = SystemConvert.INSTANCE.dos2vos(list);
        return sysSystemVos;
    }
}
