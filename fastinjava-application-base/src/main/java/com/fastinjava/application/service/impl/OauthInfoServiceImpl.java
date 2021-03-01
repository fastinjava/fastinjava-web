package com.fastinjava.application.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsUpdateDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.OauthInfoFeginClient;
import com.fastinjava.application.convert.OauthInfoConvert;
import com.fastinjava.application.service.OauthInfoService;
import com.fastinjava.framework.baseapplication.vo.OauthDetailReqVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsInsertVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsUpdateVO;
import com.fastinjava.framework.baseapplication.vo.OauthListDetailsVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

        List<OauthListDetailsVO> oauthListDetailsVOList = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(oauthDetailsDTOList)) {
            oauthListDetailsVOList = oauthDetailsDTOList.stream().map(oauthDetailsDTO ->
                    oauthInfoConvert.oauthDetailsDTO2OauthListDetailsVO(oauthDetailsDTO)
            ).collect(Collectors.toList());
        }

        PageResult<OauthListDetailsVO> pageResult = new PageResult<>();
        pageResult.setList(oauthListDetailsVOList);
        pageResult.setPageSize(oauthDetailReqVO.getPageSize());
        pageResult.setPageNum(oauthDetailReqVO.getPageNum());
        pageResult.setTotal(pageDTO.getTotal());
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
    public Boolean update(OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        OauthDetailsUpdateDTO oauthDetailsUpdateDTO = oauthInfoConvert.oauthDetailsUpdateVO2OauthDetailsUpdateDTO(oauthDetailsUpdateVO);
        ResultDTO<Boolean> resultDTO = oauthInfoFeginClient.update(oauthDetailsUpdateDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
    }
}
