package com.fastinjava.framework.baseapplication.api;

import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;

public interface MenuController {
    JsonResult<PageResult<MenuListDetailVO>> list(MenuReqVO menuReqVO);
    JsonResult<Boolean> insert(MenuInsertVO menuInsertVO);
    JsonResult<MenuListDetailVO> detail(MenuReqVO menuReqVO);
    JsonResult<List<MenuNodeDTO>> listTree(MenuReqDTO menuReqDTO);
}
