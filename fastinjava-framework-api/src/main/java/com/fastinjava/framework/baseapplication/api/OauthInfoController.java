package com.fastinjava.framework.baseapplication.api;

import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

public interface OauthInfoController {

    JsonResult<PageResult<OauthListDetailsVO>> list(OauthDetailReqVO oauthDetailReqVO);

    JsonResult<OauthListDetailsVO> detail(OauthDetailReqVO oauthDetailReqVO);


    JsonResult<Boolean> insert(OauthDetailsInsertVO oauthDetailsInsertVO);

    JsonResult<Boolean> update(OauthDetailsUpdateVO oauthDetailsUpdateVO);


    JsonResult<Boolean> grantClientApi(GrantClientApiVO grantClientApiVO);

}
