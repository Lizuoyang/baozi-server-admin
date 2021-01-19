package com.baozi.admin.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseQuery
 * @Description 查询参数通用字段
 * @Author LiZuoYang
 * @Date 2020/12/7 17:04
 **/
@Data
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = -2715632122735317650L;

    /**
     * 当前页
     */
    private Integer page = 1;

    /**
     * 分页条数
     */
    private Integer limit = 10;
}
