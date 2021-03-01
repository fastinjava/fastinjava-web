package com.fastinjava.application.convert;

import com.fastdevelopinjava.framework.ucenter.api.dto.UserCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;

public interface UserConvert {
    UserCreateDTO userInsertVO2UserCreateDTO(UserInsertVO userInsertVO);

    UserReqDTO userReqVO2UserReqDTO(UserReqVO userReqVO);

    UserListDetailVO userDTO2UserListDetailVO(UserDTO userDTO);
}
