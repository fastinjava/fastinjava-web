package com.fastinjava.application.base.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastinjava.application.base.convert.UserConvert;
import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;
import org.springframework.stereotype.Component;

/**
 * 转换类，简单属性使用BeanUti.copyProperties方法
 */
@Component
public class UserConvertImpl implements UserConvert {
    @Override
    public UserCreateDTO userInsertVO2UserCreateDTO(UserInsertVO userInsertVO) {
        if (ObjectUtil.isEmpty(userInsertVO)) return null;
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        BeanUtil.copyProperties(userInsertVO,userCreateDTO);
        return userCreateDTO;
    }

    @Override
    public UserReqDTO userReqVO2UserReqDTO(UserReqVO userReqVO) {
        if (ObjectUtil.isEmpty(userReqVO)) return null;
        UserReqDTO userReqDTO = new UserReqDTO();
        BeanUtil.copyProperties(userReqVO,userReqDTO);
        return userReqDTO;
    }

    @Override
    public UserListDetailVO userDTO2UserListDetailVO(UserDTO userDTO) {
        if (ObjectUtil.isEmpty(userDTO)) return null;
        UserListDetailVO userListDetailVO = new UserListDetailVO();
        BeanUtil.copyProperties(userDTO,userListDetailVO);
        return userListDetailVO;
    }
}
