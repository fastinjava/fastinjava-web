package com.fastinjava.application.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationUpdateDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.NodeDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.OrgFeginClient;
import com.fastinjava.application.base.convert.OrgConvert;
import com.fastinjava.application.base.service.OrgService;
import com.fastinjava.framework.baseapplication.vo.*;
import com.fastinjava.framework.common.dto.Node;
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
        return orgConvert.organizationDTO2OrgListDetailVO(organizationDTO);
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


    @Override
    public List<Node> tree(OrgTreeReqVO orgTreeReqVO) {
        OrganizationReqDTO organizationReqDTO = new OrganizationReqDTO();
        if (ObjectUtil.isNotEmpty(orgTreeReqVO.getOrgPid())) {
            organizationReqDTO.setOrgPid(orgTreeReqVO.getOrgPid());
        }
        ResultDTO<List<NodeDTO>> resultDTO = orgFeginClient.listTree(organizationReqDTO);
        Assert.isTrue(BooleanUtil.isTrue(resultDTO.getSuccess()) && ObjectUtil.isNotEmpty(resultDTO.getData()));
        List<NodeDTO> nodeDTOList = resultDTO.getData();
        return nodeDTOList.stream().map(nodeDTO -> {
            Node node = new Node();
            BeanUtil.copyProperties(nodeDTO, node);
            return node;
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean update(OrgUpdateReqVO orgUpdateReqVO) {
        OrganizationUpdateDTO organizationUpdateDTO = orgConvert.orgUpdateReqVO2OrganizationUpdateDTO(orgUpdateReqVO);
        ResultDTO<Boolean> resultDTO = orgFeginClient.update(organizationUpdateDTO);
        if (resultDTO.getSuccess()) {
            return true;
        } else {
            throw new RuntimeException(StrUtil.format("{}", resultDTO.getMsg()));
        }
    }

    @Override
    public Boolean insert(OrgInsertReqVO orgInsertReqVO) {
        OrganizationCreateDTO organizationCreateDTO = orgConvert.orgInsertReqVO2OrganizationCreateDTO(orgInsertReqVO);
        ResultDTO<Boolean> resultDTO = orgFeginClient.insert(organizationCreateDTO);
        return resultDTO.getSuccess();
    }
}
