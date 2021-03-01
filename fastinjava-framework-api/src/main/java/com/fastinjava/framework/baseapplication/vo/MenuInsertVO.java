package com.fastinjava.framework.baseapplication.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
public class MenuInsertVO implements Serializable {
    private Integer menuId;
    private String menuName;
    private String menuCode;
    private String menuUrl;
    private String menuIcon;
    private Integer menuPid;
    /**
     * 菜单类型,0->目录 1->页面路由 2->按钮
     */
    private String menuType;

    /**
     * 如果是按钮类型,按钮是否可见
     */
    private String menuVisible;

    /**
     * 如果是按钮类型,按钮关联的接口
     */
    private String menuApiIds;

    /**
     * 是否删除
     */
    private String deleteFlag;

    private Date createTime;

    private Date updateTime;
}
