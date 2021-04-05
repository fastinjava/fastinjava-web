package com.fastinjava.framework.baseapplication.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class UserInsertVO implements Serializable {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 用户昵称
     */
    private String userNickName;

    private List<Integer> roleIdList;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 删除标识
     */
    private Integer deleteFlag;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 创建用户主键id
     */
    private Integer createdUserId;

    /**
     * 更新用户主键id
     */
    private Integer updatedUserId;

    /**
     * 创建时间
     */
    private Date createdTime;

    private static final long serialVersionUID = 1L;
}
