package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDictReqVO implements Serializable {


    //默认分页，第一页
    private Integer pageNum = 0;
    //默认分页大小 10
    private Integer pageSize = 10;
    //默认分页
    private Boolean pageable = true;

    /**
     * 编号
     */
    private Integer id;

    private String type;

    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String remarks;

    private String system;

    private String delFlag;

    private static final long serialVersionUID = 1L;
}
