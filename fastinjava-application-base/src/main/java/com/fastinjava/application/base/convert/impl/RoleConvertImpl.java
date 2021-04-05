package com.fastinjava.application.base.convert.impl;

import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.OauthInfoFeginClient;
import com.fastinjava.application.base.client.OrgFeginClient;
import com.fastinjava.application.base.convert.RoleConvert;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RoleConvertImpl implements RoleConvert {


    @Resource
    private OrgFeginClient orgFeginClient;

    @Resource
    private OauthInfoFeginClient oauthInfoFeginClient;

    @Override
    public RoleCreateDTO roleInsertVO2RoleCreateDTO(RoleInsertVO roleInsertVO) {
        if (null == roleInsertVO) return null;
        RoleCreateDTO roleCreateDTO = new RoleCreateDTO();
        roleCreateDTO.setId(roleInsertVO.getId());
        roleCreateDTO.setRoleName(roleInsertVO.getRoleName());
        roleCreateDTO.setRoleCode(roleInsertVO.getRoleCode());
        roleCreateDTO.setRoleDesc(roleInsertVO.getRoleDesc());
        roleCreateDTO.setDeleteFlag(roleInsertVO.getDeleteFlag());
        String orgId = roleInsertVO.getOrgId();
        if (StringUtils.isNotEmpty(orgId))
        {
            roleCreateDTO.setOrgId(Integer.parseInt(orgId));
        }
        if (StringUtils.isNotEmpty(roleInsertVO.getClientId()))
        {
            roleCreateDTO.setClientId(roleInsertVO.getClientId());
        }
        return roleCreateDTO;

    }

    @Override
    public RoleListDetailVO roleDTO2RoleListDetailVO(RoleDTO roleDTO) {
        RoleListDetailVO roleListDetailVO = RoleListDetailVO.builder()
                .id(roleDTO.getId())
                .orgId(roleDTO.getOrgId())
                .orgName(roleDTO.getOrgName())
                .roleCode(roleDTO.getRoleCode())
                .roleName(roleDTO.getRoleName())
                .roleDesc(roleDTO.getRoleDesc())
                .deleteFlag(roleDTO.getDeleteFlag())
                .creatTime(roleDTO.getCreatTime())
                .updateTime(roleDTO.getUpdateTime())
                .menuIdList(roleDTO.getMenuIdList())
                .build();
        String clientId = roleDTO.getClientId();
        if (StringUtils.isNotEmpty(clientId))
        {
            roleListDetailVO.setClientId(clientId);
            OauthDetailReqDTO oauthDetailReqDTO = new OauthDetailReqDTO();
            oauthDetailReqDTO.setClientId(clientId);
            ResultDTO<OauthDetailsDTO> resultDTO = oauthInfoFeginClient.getOne(oauthDetailReqDTO);
            if (resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()))
            {
                OauthDetailsDTO oauthDetailsDTO = resultDTO.getData();
                roleListDetailVO.setClientName(oauthDetailsDTO.getClientname());
            }
        }
        return roleListDetailVO;
    }

    @Override
    public RoleReqDTO roleListReqVO2RoleReqDTO(RoleListReqVO roleListReqVO) {

        if (null == roleListReqVO) return null;

        RoleReqDTO roleReqDTO = new RoleReqDTO();

        roleReqDTO.setPageNum(roleListReqVO.getPageNum());
        roleReqDTO.setPageSize(roleListReqVO.getPageSize());
        roleReqDTO.setPageable(roleListReqVO.getPageable());

        //...
        roleReqDTO.setOrgId(roleListReqVO.getOrgId());

        return roleReqDTO;
    }

}
