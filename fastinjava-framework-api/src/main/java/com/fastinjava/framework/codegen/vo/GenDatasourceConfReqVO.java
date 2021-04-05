package com.fastinjava.framework.codegen.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GenDatasourceConfReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    //默认分页，第一页
    private Integer pageNum = 0;
    //默认分页大小 10
    private Integer pageSize = 10;
    //默认分页
    private Boolean pageable = true;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * jdbcurl
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新
     */
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private String delFlag;

}
