package com.fastinjava.application.base.service;

import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface UserService {
    Boolean insert(UserInsertVO userInsertVO);

    PageResult<UserListDetailVO> list(UserReqVO userReqVO);
}
