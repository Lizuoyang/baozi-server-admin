package com.baozi.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单操作中间表
 * </p>
 *
 * @author baozi-mybatis-plus-general
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_menu_operation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuOperationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 菜单主键
     */
    private String menuId;

    /**
     * 操作功能主键
     */
    private String operationId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


}
