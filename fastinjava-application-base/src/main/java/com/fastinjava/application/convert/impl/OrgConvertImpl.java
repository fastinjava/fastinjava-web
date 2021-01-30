package com.fastinjava.application.convert.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastinjava.application.convert.OrgConvert;
import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import org.springframework.stereotype.Component;

@Component
public class OrgConvertImpl implements OrgConvert {

    @Override
    public OrganizationReqDTO orgListReqVO2OrganizationReqDTO(OrgListReqVO orgListReqVO) {
        if (ObjectUtil.isEmpty(orgListReqVO)) return null;

        OrganizationReqDTO organizationReqDTO = new OrganizationReqDTO();
        if (ObjectUtil.isNotEmpty(orgListReqVO.getPageable())) {
            organizationReqDTO.setPageable(orgListReqVO.getPageable());
        } else {
            organizationReqDTO.setPageable(true);
        }
        organizationReqDTO.setPageNum(orgListReqVO.getPageNum());
        organizationReqDTO.setPageSize(orgListReqVO.getPageSize());
        if (ObjectUtil.isNotEmpty(orgListReqVO.getOrgId())) {
            organizationReqDTO.setOrgId(orgListReqVO.getOrgId());
        }
        return organizationReqDTO;
    }

    @Override
    public OrgListResVO organizationDTO2OrgListDetailVO(OrganizationDTO organizationDTO) {
        if (ObjectUtil.isEmpty(organizationDTO)) return null;
        OrgListResVO orgListResVO =  new OrgListResVO();

        if (ObjectUtil.isNotEmpty(organizationDTO.getOrgId())) {
            orgListResVO.setOrgId(organizationDTO.getOrgId());
        }
        if (StrUtil.isNotEmpty(organizationDTO.getOrgName())) {
            orgListResVO.setOrgName(organizationDTO.getOrgName());
        }
        if (StrUtil.isNotEmpty(organizationDTO.getOrgCode())) {
            orgListResVO.setOrgCode(organizationDTO.getOrgCode());
        }
        if (StrUtil.isNotEmpty(organizationDTO.getOrgDesc())) {
            orgListResVO.setOrgDesc(organizationDTO.getOrgDesc());
        }

        return orgListResVO;
    }
}
