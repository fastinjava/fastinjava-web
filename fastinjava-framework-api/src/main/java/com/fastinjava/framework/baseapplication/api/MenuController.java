package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface MenuController {
    JsonResult<PageResult<MenuListDetailVO>> list(MenuReqVO menuReqVO);
    JsonResult<MenuListDetailVO> detail(MenuReqVO menuReqVO);
    JsonResult<Boolean> insert(MenuInsertVO menuInsertVO);
}
