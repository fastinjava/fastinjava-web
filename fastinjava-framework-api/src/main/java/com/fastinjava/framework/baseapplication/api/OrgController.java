package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface OrgController {
    JsonResult<PageResult<OrgListResVO>> list(OrgListReqVO orgListReqVO);
    JsonResult<OrgListResVO> detail(OrgListReqVO orgListReqVO);
}
