package com.fastinjava.application.convert;

import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;

public interface OrgConvert {
    OrgListResVO organizationDTO2OrgListDetailVO(OrganizationDTO organizationDTO);
    OrganizationReqDTO orgListReqVO2OrganizationReqDTO(OrgListReqVO orgListReqVO);
}
