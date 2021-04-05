package com.fastinjava.application.base.convert;

import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoReqDTO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;

public interface ApiInfoConvert {
    ApiInfoReqDTO apiInfoReqVO2ApiInfoReqDTO(ApiInfoReqVO apiInfoReqVO);

    ApiInfoListDetailVO apiInfoDTO2ApiInfoListDetailVO(ApiInfoDTO apiInfoDTO);

    ApiInfoInsertDTO apiInfoInsertVO2ApiInfoInsertDTO(ApiInfoInsertVO apiInfoInsertVO);
}
