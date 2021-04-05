package com.fastinjava.application.base.service.impl;

import com.fastdevelopinjava.framework.system.api.dto.*;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.OauthInfoFeginClient;
import com.fastinjava.application.base.convert.OauthInfoConvert;
import com.fastinjava.application.base.service.OauthInfoService;
import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.res.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OauthInfoServiceImpl implements OauthInfoService {

    @Resource
    private OauthInfoFeginClient oauthInfoFeginClient;

    @Resource
    private OauthInfoConvert oauthInfoConvert;

    @Override
    public PageResult<OauthListDetailsVO> list(OauthDetailReqVO oauthDetailReqVO) {

        OauthDetailReqDTO oauthDetailReqDTO = oauthInfoConvert.oauthDetailReqVO2OauthDetailReqDTO(oauthDetailReqVO);
        ResultDTO<PageDTO<OauthDetailsDTO>> resultDTO = oauthInfoFeginClient.getList(oauthDetailReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        PageDTO<OauthDetailsDTO> pageDTO = resultDTO.getData();
        List<OauthDetailsDTO> oauthDetailsDTOList = pageDTO.getList();

        List<OauthListDetailsVO> oauthListDetailsVOList = oauthDetailsDTOList.stream().map(oauthDetailsDTO -> oauthInfoConvert.oauthDetailsDTO2OauthListDetailsVO(oauthDetailsDTO)).collect(Collectors.toList());
        PageResult<OauthListDetailsVO> pageResult = new PageResult<>(oauthDetailReqVO.getPageNum(), oauthDetailReqVO.getPageSize(), pageDTO.getTotal(), oauthListDetailsVOList);
        return pageResult;
    }


    @Override
    public OauthListDetailsVO detail(OauthDetailReqVO oauthDetailReqVO) {
        OauthDetailReqDTO oauthDetailReqDTO = oauthInfoConvert.oauthDetailReqVO2OauthDetailReqDTO(oauthDetailReqVO);
        ResultDTO<OauthDetailsDTO> resultDTO = oauthInfoFeginClient.getOne(oauthDetailReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        OauthDetailsDTO detailsDTO = resultDTO.getData();
        OauthListDetailsVO oauthListDetailsVO = oauthInfoConvert.oauthDetailsDTO2OauthListDetailsVO(detailsDTO);
        return oauthListDetailsVO;
    }

    @Override
    public Boolean insert(OauthDetailsInsertVO oauthDetailsInsertVO) {
        OauthDetailsInsertDTO oauthDetailsInsertDTO = oauthInfoConvert.oauthDetailsInsertVO2OauthDetailsInsertDTO(oauthDetailsInsertVO);
        ResultDTO<Boolean> resultDTO = oauthInfoFeginClient.insert(oauthDetailsInsertDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
    }

    @Override
    public Boolean grantClientApi(GrantClientApiVO grantClientApiVO) {

        GrantClientApiDTO grantClientApiDTO = new GrantClientApiDTO();
        grantClientApiDTO.setClientId(grantClientApiVO.getClientId());
        grantClientApiDTO.setApiCodeList(grantClientApiVO.getApiCodeList());

        ResultDTO<Boolean> resultDTO = oauthInfoFeginClient.grantClientApi(
                grantClientApiDTO
        );

        if (!resultDTO.getSuccess()) {
            log.error("oauthInfoFeginClient#grantClientApi fail, fail message = {} ", resultDTO.getMsg());
            throw new RuntimeException(resultDTO.getMsg());
        } else {
            return true;
        }
    }

    @Override
    public Boolean update(OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        OauthDetailsUpdateDTO oauthDetailsUpdateDTO = oauthInfoConvert.oauthDetailsUpdateVO2OauthDetailsUpdateDTO(oauthDetailsUpdateVO);
        ResultDTO<Boolean> resultDTO = oauthInfoFeginClient.update(oauthDetailsUpdateDTO);
        if (!resultDTO.getSuccess())
            throw new RuntimeException(resultDTO.getMsg());
        return true;
    }
}
