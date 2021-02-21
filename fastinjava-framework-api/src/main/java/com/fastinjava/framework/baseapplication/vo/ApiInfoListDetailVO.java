package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiInfoListDetailVO implements Serializable {
    private Integer apiId;
    private String apiCode;
    private String apiName;
    private String apiDesc;
    private Integer appId;
    private String needAuth;
    private String deleteFlag;
    private Date creatTime;
    private Date updateTime;
    private String apiUrl;
    private String appName;
}