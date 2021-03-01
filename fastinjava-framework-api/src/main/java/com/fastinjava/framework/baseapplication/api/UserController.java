package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface UserController {
    JsonResult<PageResult<UserListDetailVO>> list(UserReqVO userReqVO);
    JsonResult<Boolean> insert(UserInsertVO userInsertVO);
}
