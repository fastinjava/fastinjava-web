package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppInsertVO implements Serializable {
    /**
     * app id
     */
    private Integer appId;

    /**
     * app名称
     */
    private String appName;

    /**
     * app编码
     */
    private String appCode;

    /**
     * app描述
     */
    private String appDesc;

    /**
     * app类型,1=application 2=service
     */
    private String appType;

    /**
     * 是否删除
     */
    private String deleteFlag;

    /**
     * 是否支持https，0不支持1支持，默认为0不支持
     */
    private Integer appHttps;

    /**
     * ip或者主机名
     */
    private String appHost;

    /**
     * 资源前缀
     */
    private String appContext;

    /**
     * 端口号
     */
    private Integer appPort;

    private Date creatTime;

    private Date updateTime;
}
