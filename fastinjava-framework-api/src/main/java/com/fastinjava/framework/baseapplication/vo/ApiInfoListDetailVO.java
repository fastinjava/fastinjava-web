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
    //todo 下个版本改回来
    private Date creatTime;
    private String creatTimeStr;
    private Date updateTime;
    private String updateTimeStr;
    private String apiUrl;
    private String appName;
}