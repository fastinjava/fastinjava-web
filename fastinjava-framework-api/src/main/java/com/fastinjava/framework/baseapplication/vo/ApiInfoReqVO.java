package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiInfoReqVO implements Serializable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;
    private Boolean pageable = true;
    private Integer apiId;
    private String apiCode;
    private String apiName;
    private String apiDesc;
    private Integer appId;
    private String needAuth;
    private String deleteFlag;
    private Date creatTime;
    private Date updateTime;
}
