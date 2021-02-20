package com.fastinjava.application.convert;

import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationReqDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.OrganizationUpdateDTO;
import com.fastinjava.framework.baseapplication.vo.OrgInsertReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListReqVO;
import com.fastinjava.framework.baseapplication.vo.OrgListResVO;
import com.fastinjava.framework.baseapplication.vo.OrgUpdateReqVO;

public interface OrgConvert {
    OrgListResVO organizationDTO2OrgListDetailVO(OrganizationDTO organizationDTO);
    OrganizationReqDTO orgListReqVO2OrganizationReqDTO(OrgListReqVO orgListReqVO);
    OrganizationUpdateDTO orgUpdateReqVO2OrganizationUpdateDTO(OrgUpdateReqVO orgUpdateReqVO);

    OrganizationCreateDTO orgInsertReqVO2OrganizationCreateDTO(OrgInsertReqVO orgInsertReqVO);
}
