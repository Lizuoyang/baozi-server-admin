package com.baozi.admin.service.impl;

import com.baozi.admin.entity.SysUserMenuDO;
import com.baozi.admin.mapper.SysUserMenuMapper;
import com.baozi.admin.service.SysUserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户单独菜单中间表 用户单独菜单中间表，里面用字段存操作功能集合,例子：create,import。预留单独给用户分配菜单、操作的功能 服务实现类
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuMapper, SysUserMenuDO> implements SysUserMenuService {

}
