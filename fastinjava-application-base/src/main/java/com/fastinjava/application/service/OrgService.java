package com.fastinjava.application.service;

import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.common.res.PageResult;

public interface OrgService {
    PageResult<OrgListResVO> list(OrgListReqVO orgListReqVO);

    OrgListResVO detail(OrgListReqVO orgListReqVO);
}
