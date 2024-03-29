package com.fastinjava.application.base.service;

import com.fastinjava.framework.baseapplication.vo.GrantRoleMenuVO;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface RoleService {
    PageResult<RoleListDetailVO> list(RoleListReqVO roleListReqVO);

    Boolean insert(RoleInsertVO roleInsertVO);

    Boolean grantRoleMenus(GrantRoleMenuVO grantRoleMenuVO);

    RoleListDetailVO detail(Integer id);
}
