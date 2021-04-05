package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDictInsertVO implements Serializable {
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
