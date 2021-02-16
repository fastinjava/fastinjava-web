package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrgTreeReqVO implements Serializable {
    private Integer orgId;
    private Integer orgPid;
    private String orgName;
    private String orgCode;
    private String orgDesc;
    private String deleteFlag;
    private Date creatTime;
    private String creatTimeStr;
    private Date updateTime;
    private String updateTimeStr;
}
