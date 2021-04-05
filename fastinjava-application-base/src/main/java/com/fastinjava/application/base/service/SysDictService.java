package com.fastinjava.application.base.service;

import com.fastinjava.framework.baseapplication.vo.SysDictInsertVO;
import com.fastinjava.framework.baseapplication.vo.SysDictListDetailVO;
import com.fastinjava.framework.baseapplication.vo.SysDictReqVO;
import com.fastinjava.framework.common.res.PageResult;

public interface SysDictService {
    PageResult<SysDictListDetailVO> list(SysDictReqVO sysDictReqVO);

    Boolean insert(SysDictInsertVO sysDictInsertVO);

}
