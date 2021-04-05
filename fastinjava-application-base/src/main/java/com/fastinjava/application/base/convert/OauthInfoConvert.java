package com.fastinjava.application.base.convert;

import com.fastdevelopinjava.framework.system.api.dto.*;
import com.fastinjava.framework.baseapplication.vo.*;

public interface OauthInfoConvert {
    OauthListDetailsVO oauthDetailsDTO2OauthListDetailsVO(OauthDetailsDTO oauthDetailsDTO);

    OauthDetailReqDTO oauthDetailReqVO2OauthDetailReqDTO(OauthDetailReqVO oauthDetailReqVO);

    OauthDetailsInsertDTO oauthDetailsInsertVO2OauthDetailsInsertDTO(OauthDetailsInsertVO oauthDetailsInsertVO);

    OauthDetailsUpdateDTO oauthDetailsUpdateVO2OauthDetailsUpdateDTO(OauthDetailsUpdateVO oauthDetailsUpdateVO);

    GrantClientApiDTO grantClientApiVO2GrantClientApiDTO(GrantClientApiVO grantClientApiVO);

}
