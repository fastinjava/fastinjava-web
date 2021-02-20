package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleInsertVO implements Serializable {
    private Integer id;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String deleteFlag;
    private String orgId;
    private Date creatTime;
    private Date updateTime;
}
