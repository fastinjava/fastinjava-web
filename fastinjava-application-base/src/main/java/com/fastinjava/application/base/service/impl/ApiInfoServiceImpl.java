package com.fastinjava.application.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.ApiInfoFeginClient;
import com.fastinjava.application.base.convert.ApiInfoConvert;
import com.fastinjava.application.base.service.ApiInfoService;
import com.fastinjava.framework.baseapplication.vo.ApiInfoInsertVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoListDetailVO;
import com.fastinjava.framework.baseapplication.vo.ApiInfoReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiInfoServiceImpl implements ApiInfoService {
    @Resource
    private ApiInfoFeginClient apiInfoFeginClient;
    @Resource
    private ApiInfoConvert apiInfoConvert;

    @Override
    public Boolean insertSelective(ApiInfoInsertVO apiInfoInsertVO) {
        ApiInfoInsertDTO apiInfoInsertDTO = apiInfoConvert.apiInfoInsertVO2ApiInfoInsertDTO(apiInfoInsertVO);
        ResultDTO<Boolean> resultDTO = apiInfoFeginClient.insert(apiInfoInsertDTO);
        if (!resultDTO.getSuccess()) throw new RuntimeException(resultDTO.getMsg());
        return Boolean.TRUE;
    }

    @Override
    public PageResult<ApiInfoListDetailVO> list(ApiInfoReqVO apiInfoReqVO) {
        ApiInfoReqDTO apiInfoReqDTO = apiInfoConvert.apiInfoReqVO2ApiInfoReqDTO(apiInfoReqVO);
        ResultDTO<PageDTO<ApiInfoDTO>> resultDTO = apiInfoFeginClient.getList(apiInfoReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        List<ApiInfoDTO> apiInfoDTOList = resultDTO.getData().getList();
        List<ApiInfoListDetailVO> apiInfoListDetailVOList = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(apiInfoDTOList)) {
            apiInfoListDetailVOList = apiInfoDTOList.stream().map(apiInfoDTO -> apiInfoConvert.apiInfoDTO2ApiInfoListDetailVO(apiInfoDTO)).collect(Collectors.toList());
        }
        PageResult<ApiInfoListDetailVO> pageResult = new PageResult<>(apiInfoReqVO.getPageNum(), apiInfoReqVO.getPageSize(), resultDTO.getData().getTotal(),
                apiInfoListDetailVOList);
        return pageResult;
    }
}
