package com.fastinjava.framework.baseapplication.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleListDetailVO implements Serializable {
    private Integer id;
    private Integer orgId;
    private String orgName;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String deleteFlag;
    private Date creatTime;
    private Date updateTime;
}
