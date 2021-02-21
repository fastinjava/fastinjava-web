package com.fastinjava.application.convert.impl;


import cn.hutool.core.bean.BeanUtil;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoReqDTO;
import com.fastinjava.application.convert.ApiInfoConvert;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import org.springframework.stereotype.Component;

@Component
public class ApiInfoConvertImpl implements ApiInfoConvert {
    @Override
    public ApiInfoReqDTO apiInfoReqVO2ApiInfoReqDTO(ApiInfoReqVO apiInfoReqVO) {
        if (null == apiInfoReqVO) return null;
        ApiInfoReqDTO apiInfoReqDTO = new ApiInfoReqDTO();
        //apiInfoReqDTO.setApiCode(apiInfoReqVO.getApiCode());
        BeanUtil.copyProperties(apiInfoReqVO,apiInfoReqDTO);
        return apiInfoReqDTO;
    }

    @Override
    public ApiInfoInsertDTO apiInfoInsertVO2ApiInfoInsertDTO(ApiInfoInsertVO apiInfoInsertVO) {
        if (null == apiInfoInsertVO) return null;

        ApiInfoInsertDTO apiInfoInsertDTO = new ApiInfoInsertDTO();

        BeanUtil.copyProperties(apiInfoInsertVO,apiInfoInsertDTO);

        return apiInfoInsertDTO;
    }

    @Override
    public ApiInfoListDetailVO apiInfoDTO2ApiInfoListDetailVO(ApiInfoDTO apiInfoDTO) {
        if (null == apiInfoDTO) return null;
        ApiInfoListDetailVO apiInfoListDetailVO = new ApiInfoListDetailVO();
        BeanUtil.copyProperties(apiInfoDTO,apiInfoListDetailVO);
        return apiInfoListDetailVO;
    }
}
