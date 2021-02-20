package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleListReqVO implements Serializable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;
    private Boolean pageable = true;
    private Integer id;
    private Integer orgId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String deleteFlag;
    private Date creatTime;
    private Date updateTime;
}
