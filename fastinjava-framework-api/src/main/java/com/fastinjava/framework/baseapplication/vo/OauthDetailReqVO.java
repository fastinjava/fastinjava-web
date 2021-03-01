package com.fastinjava.framework.baseapplication.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class OauthDetailReqVO implements Serializable {
    //默认分页，第一页
    private Integer pageNum = 0;
    //默认分页大小 10
    private Integer pageSize = 10;
    //默认分页
    private Boolean pageable = true;
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
    private String status;
}
