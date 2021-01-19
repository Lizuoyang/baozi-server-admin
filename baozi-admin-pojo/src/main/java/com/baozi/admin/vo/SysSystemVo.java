package com.baozi.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysSystemVo
 * @Description 系统VO
 * @Author LiZuoYang
 * @Date 2020/12/14 10:51
 **/
@Data
public class SysSystemVo implements Serializable {
    private static final long serialVersionUID = 773981274224053721L;
    /**
     * 主键
     */
    private String id;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer systemSort;

    /**
     * 是否删除
     */
    private Boolean isDelete;

}
