package com.fastinjava.application.base.service;

import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;

public interface MenuService {
    PageResult<MenuListDetailVO> list(MenuReqVO menuReqVO);

    Boolean insert(MenuInsertVO menuInsertVO);

    MenuListDetailVO detail(MenuReqVO menuReqVO);

    List<MenuNodeDTO> listTree(MenuReqDTO menuReqDTO);
}
