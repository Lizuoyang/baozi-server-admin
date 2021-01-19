package com.baozi.admin.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baoz.admin.annation.Manager;
import com.baoz.admin.constants.IdPrefixConstant;
import com.baoz.admin.constants.RedisKeyConstant;
import com.baoz.admin.enums.common.GlobalEnums;
import com.baoz.admin.enums.exception.UserExceptionEnums;
import com.baoz.admin.exception.CommonException;
import com.baozi.admin.RedisUtil;
import com.baozi.admin.SnowflakeIdUtil;
import com.baozi.admin.convert.SysUserConvert;
import com.baozi.admin.dto.SysUserLoginDTO;
import com.baozi.admin.dto.SysUserUpdateDTO;
import com.baozi.admin.entity.SysUserDO;
import com.baozi.admin.entity.SysUserRoleDO;
import com.baozi.admin.query.SysUserListQuery;
import com.baozi.admin.service.SysUserRoleService;
import com.baozi.admin.service.SysUserService;
import com.baozi.admin.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName SysRoleManager
 * @Description 用户管理 Manager层
 * @Author LiZuoYang
 * @Date 2020/12/7 17:01
 **/
@Manager
public class SysUserManager {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @author lizuoyang
     * @description 新增用户
     * @date 14:26 2020/12/9
     * @param dto
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(SysUserUpdateDTO dto) {
        SysUserDO sysUser = SysUserConvert.INSTANCE.dto2do(dto);
        sysUser.setId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_USER));
        sysUser.setCreatedTime(LocalDateTime.now());
        sysUser.setRegisterDate(sysUser.getCreatedTime());
        sysUser.setImageUrl("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        sysUser.setPassword(dto.getPassword());
        // 新增用户
        boolean saveRes = userService.save(sysUser);

        if (saveRes) {
            SysUserRoleDO userRole = SysUserRoleDO.builder()
                    .userId(sysUser.getId())
                    .roleId(SnowflakeIdUtil.buildIdWithPrefix(IdPrefixConstant.SYS_ROLE))
                    .id(SnowflakeIdUtil.buildGenerateId())
                    .createdTime(LocalDateTime.now())
                    .build();

            // 新增角色关联信息
            userRoleService.save(userRole);
        }


        return saveRes;
    }

    /**
     * @author lizuoyang
     * @description 修改用户
     * @date 14:26 2020/12/9
     * @param dto
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(SysUserUpdateDTO dto) {
        SysUserDO sysUser = SysUserConvert.INSTANCE.dto2do(dto);
        sysUser.setUpdatedTime(LocalDateTime.now());

        // 修改用户
        boolean updRes = userService.updateById(sysUser);

        if (ObjectUtils.isNotEmpty(dto.getRoleId())) {
            LambdaUpdateWrapper<SysUserRoleDO> updQw = new LambdaUpdateWrapper<>();
            updQw.eq(SysUserRoleDO::getUserId, sysUser.getId());
            boolean delRes = userRoleService.remove(updQw);
            if (delRes) {
                SysUserRoleDO userRole = SysUserRoleDO.builder()
                        .userId(sysUser.getId())
                        .roleId(dto.getRoleId())
                        .id(SnowflakeIdUtil.buildGenerateId())
                        .createdTime(LocalDateTime.now())
                        .build();

                // 新增角色关联信息
                userRoleService.save(userRole);
            }
        }
        return updRes;
    }

    /**
     * @author lizuoyang
     * @description 判断user的某些字段值是否已存在
     * @date 11:31 2020/12/9
     * @param columnName
     * @param columnValue
     * @return boolean
     **/
    public SysUserDO checkUserAccount(String columnName, String columnValue) {
        QueryWrapper<SysUserDO> qw = new QueryWrapper<>();
        qw.eq(columnName, columnValue);
        qw.eq("is_delete", GlobalEnums.NORMAL);
        SysUserDO sysUser = userService.getOne(qw);
        return sysUser;
    }

    /**
     * @author lizuoyang
     * @description 分页查询用户列表
     * @date 14:26 2020/12/9
     * @param query
     * @return PageInfo<SysUserVo>
     **/
    public PageInfo<SysUserVo> listUsers(SysUserListQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<SysUserVo> sysUserVos = userService.listUsersWithRoleInfo(query);
        PageInfo<SysUserVo> pageInfo = new PageInfo(sysUserVos);
        pageInfo.setList(sysUserVos);
        return pageInfo;
    }

    /**
     * @author lizuoyang
     * @description 获取用户详情
     * @date 14:25 2020/12/9
     * @param token
     * @return com.purcotton.omni.vo.SysUserVo
     **/
    public SysUserVo getUserInfo(String token) {
        String userInfoKey = RedisKeyConstant.SYS_USER_TOKEN + token;
        if (!redisUtil.hasKey(userInfoKey)) {
            throw new CommonException(UserExceptionEnums.USER_NOT_EXIST);
        }

        String userInfoJsonStr = redisUtil.get(userInfoKey);
        SysUserDO sysUser = JSONObject.parseObject(userInfoJsonStr, SysUserDO.class);

        SysUserVo userVo = userService.getUserWithRoleInfo(sysUser.getId());
        return userVo;
    }


    /**
     * @author lizuoyang
     * @description 退出登录，删除token
     * @date 14:25 2020/12/9
     * @param token
     * @return void
     **/
    public void userLoginOut(String token) {
        String loginOutKey = RedisKeyConstant.SYS_USER_TOKEN + token;
        if (redisUtil.hasKey(loginOutKey)) {
            redisUtil.delete(loginOutKey);
        }
    }

    /**
     * @author lizuoyang
     * @description 用户登录，生成token
     * @date 14:25 2020/12/9
     * @param dto
     * @return java.lang.String
     **/
    public String userLogin(SysUserLoginDTO dto) {
        // 校验参数
        boolean checkLoginParam = checkLoginParam(dto);
        if (!checkLoginParam) {
            throw new CommonException(UserExceptionEnums.USER_LOGIN_PARAM_ERROR);
        }

        // 登录判断
        LambdaQueryWrapper<SysUserDO> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUserDO::getLoginName, dto.getUsername());
        qw.eq(SysUserDO::getPassword, dto.getPassword());
        qw.eq(SysUserDO::getIsDelete, GlobalEnums.NORMAL);
        SysUserDO queryUser = userService.getOne(qw);
        if (ObjectUtils.isEmpty(queryUser)) {
            throw new CommonException(UserExceptionEnums.USER_LOGIN_FAIL);
        }

        // 获取token 存放至redis
        String token =IdWorker.getIdStr();
        redisUtil.setEx(RedisKeyConstant.SYS_USER_TOKEN + token, JSONObject.toJSONString(queryUser), 12, TimeUnit.HOURS);


        return token;
    }

    /**
     * @author lizuoyang
     * @description 登录参数检查
     * @date 9:15 2020/12/8
     * @param dto
     * @return boolean
     **/
    private boolean checkLoginParam(SysUserLoginDTO dto) {
        boolean validate = Boolean.TRUE;
        if (StringUtil.isEmpty(dto.getUsername()) || StringUtil.isEmpty(dto.getPassword())) {
            validate =  Boolean.FALSE;
        }
        return validate;
    }

}
