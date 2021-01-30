package com.fastinjava.application.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.fastdevelopinjava.framework.ucenter.api.client.OrgClient;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.OrgFeginClient;
import com.fastinjava.application.convert.OrgConvert;
import com.fastinjava.application.service.OrgService;
import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.common.res.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrgFeginClient orgFeginClient;

    @Resource
    private OrgConvert orgConvert;

    @Override
    public OrgListResVO detail(OrgListReqVO orgListReqVO) {
        OrganizationReqDTO organizationReqDTO = orgConvert.orgListReqVO2OrganizationReqDTO(orgListReqVO);
        ResultDTO<OrganizationDTO> resultDTO = orgFeginClient.getOne(organizationReqDTO);
        Assert.isTrue(BooleanUtil.isTrue(resultDTO.getSuccess()) && ObjectUtil.isNotEmpty(resultDTO.getData()));
        OrganizationDTO organizationDTO = resultDTO.getData();
        OrgListResVO orgListResVO = orgConvert.organizationDTO2OrgListDetailVO(organizationDTO);
        return orgListResVO;
    }

    @Override
    public PageResult<OrgListResVO> list(OrgListReqVO orgListReqVO) {
        OrganizationReqDTO organizationReqDTO = orgConvert.orgListReqVO2OrganizationReqDTO(orgListReqVO);
        ResultDTO<PageDTO<OrganizationDTO>> resultDTO = orgFeginClient.getList(organizationReqDTO);
        Assert.isTrue(BooleanUtil.isTrue(resultDTO.getSuccess()) && ObjectUtil.isNotEmpty(resultDTO.getData()));
        PageDTO<OrganizationDTO> organizationDTOPageDTO = resultDTO.getData();
        List<OrganizationDTO> organizationDTOList = organizationDTOPageDTO.getList();
        List<OrgListResVO> orgListDetailVOList = organizationDTOList.stream()
                .map(organizationDTO -> orgConvert.organizationDTO2OrgListDetailVO(organizationDTO))
                .collect(Collectors.toList());
        return new PageResult<>(orgListReqVO.getPageNum(), orgListReqVO.getPageSize(), organizationDTOPageDTO.getTotal(), orgListDetailVOList);
    }
}
