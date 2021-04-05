package com.fastinjava.framework.baseapplication.vo;

import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class UserListDetailVO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;
    private String password;
    /**
     * 用户昵称
     */
    private String userNickName;

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

    private List<RoleDTO> roleDTOList;

    private static final long serialVersionUID = 1L;
}
