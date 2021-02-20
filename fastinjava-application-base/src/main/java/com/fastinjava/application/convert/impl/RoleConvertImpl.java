package com.fastinjava.application.convert.impl;

import com.fastdevelopinjava.framework.ucenter.api.dto.RoleCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastinjava.application.client.OrgFeginClient;
import com.fastinjava.application.convert.RoleConvert;
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
        return roleCreateDTO;

    }

    @Override
    public RoleListDetailVO roleDTO2RoleListDetailVO(RoleDTO roleDTO) {
        return RoleListDetailVO.builder()
                .id(roleDTO.getId())
                .orgId(roleDTO.getOrgId())
                .orgName(roleDTO.getOrgName())
                .roleCode(roleDTO.getRoleCode())
                .roleName(roleDTO.getRoleName())
                .roleDesc(roleDTO.getRoleDesc())
                .deleteFlag(roleDTO.getDeleteFlag())
                .creatTime(roleDTO.getCreatTime())
                .updateTime(roleDTO.getUpdateTime())
                .build();
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
