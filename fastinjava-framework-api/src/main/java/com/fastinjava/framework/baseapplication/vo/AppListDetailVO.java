package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppListDetailVO implements Serializable{
        private Integer appId;
        private String appName;
        private String appCode;
        private String appDesc;
        private String appType;
        private String deleteFlag;
        private Integer appHttps;
        private String appHost;
        private String appContext;
        private Integer appPort;
        private Date creatTime;
        private Date updateTime;
}
