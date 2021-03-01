package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface MenuService {
    PageResult<MenuListDetailVO> list(MenuReqVO menuReqVO);

    Boolean insert(MenuInsertVO menuInsertVO);
}
