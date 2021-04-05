package com.fastinjava.framework.baseapplication.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MenuReqVO implements Serializable {
    private Integer pageNum = 0;
    private Integer pageSize = 10;
    private Boolean pageable = true;
    private Integer menuId;
    private String menuName;
    private String menuCode;
    private String menuUrl;
    private String menuIcon;
    private String menuPid;
    private String menuType;
    private String menuVisible;
    private String menuApiIds;
    private String deleteFlag;
    private Date createTime;
    private Date updateTime;
    private String clientId;
    private static final long serialVersionUID = 1L;
}
