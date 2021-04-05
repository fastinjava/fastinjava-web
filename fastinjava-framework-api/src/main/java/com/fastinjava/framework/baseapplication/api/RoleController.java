package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.GrantRoleMenuVO;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface RoleController {


    JsonResult<PageResult<RoleListDetailVO>> list(RoleListReqVO roleListReqVO);

    JsonResult<Boolean> insert(RoleInsertVO roleInsertVO);

    JsonResult<Boolean> grantRoleMenus(GrantRoleMenuVO grantRoleMenuVO);

    JsonResult<RoleListDetailVO> detail(Integer id);

}
