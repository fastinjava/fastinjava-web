package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrgUpdateReqVO implements Serializable {
    @NotNull(message = "更新组织orgId缺失")
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
