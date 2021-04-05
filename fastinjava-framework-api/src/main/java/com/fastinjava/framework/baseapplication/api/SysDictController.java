package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.SysDictInsertVO;
import com.fastinjava.framework.baseapplication.vo.SysDictListDetailVO;
import com.fastinjava.framework.baseapplication.vo.SysDictReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface SysDictController {

    JsonResult<PageResult<SysDictListDetailVO>> list(SysDictReqVO sysDictReqVO);

    JsonResult<Boolean> insert(SysDictInsertVO sysDictInsertVO);
}
