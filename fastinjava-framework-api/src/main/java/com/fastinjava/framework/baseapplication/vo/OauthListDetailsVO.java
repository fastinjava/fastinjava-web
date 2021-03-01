package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OauthListDetailsVO implements Serializable {
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

    /**
     * 状态 0 ---  刚刚创建
     * 1 ---  申请中
     * 2 --- 申请通过
     * 3 --- 申请拒绝
     */
    private String status;
    /**
     * 是否删除
     */
    private String deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}