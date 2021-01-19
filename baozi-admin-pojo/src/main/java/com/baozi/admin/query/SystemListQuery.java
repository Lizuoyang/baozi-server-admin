package com.baozi.admin.query;

import lombok.Data;

/**
 * @ClassName SystemQuery
 * @Description 查询系统列表参数
 * @Author LiZuoYang
 * @Date 2020/12/17 15:35
 **/
@Data
public class SystemListQuery extends BaseQuery{
    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 是否删除： 0-未删除 1-删除
     */
    private Boolean isDelete;
}
