package com.fastinjava.application.convert;

import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsUpdateDTO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailReqVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsInsertVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsUpdateVO;
import com.fastinjava.framework.baseapplication.vo.OauthListDetailsVO;

public interface OauthInfoConvert {
    OauthListDetailsVO oauthDetailsDTO2OauthListDetailsVO(OauthDetailsDTO oauthDetailsDTO);

    OauthDetailReqDTO oauthDetailReqVO2OauthDetailReqDTO(OauthDetailReqVO oauthDetailReqVO);

    OauthDetailsInsertDTO oauthDetailsInsertVO2OauthDetailsInsertDTO(OauthDetailsInsertVO oauthDetailsInsertVO);

    OauthDetailsUpdateDTO oauthDetailsUpdateVO2OauthDetailsUpdateDTO(OauthDetailsUpdateVO oauthDetailsUpdateVO);
}
