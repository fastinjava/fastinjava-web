package com.fastinjava.application.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.GrantRoleMenuDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.RoleFeginClient;
import com.fastinjava.application.base.convert.RoleConvert;
import com.fastinjava.application.base.service.RoleService;
import com.fastinjava.application.base.util.ResultUtils;
import com.fastinjava.framework.baseapplication.vo.GrantRoleMenuVO;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleFeginClient roleFeginClient;

    @Resource
    private RoleConvert roleConvert;

    @Override
    public Boolean insert(RoleInsertVO roleInsertVO) {
        ResultDTO<Boolean> resultDTO = roleFeginClient.insert(roleConvert.roleInsertVO2RoleCreateDTO(roleInsertVO));
        return ResultUtils.checkSuccessOrElseThrow(resultDTO,resultDTO.getMsg());
    }

    @Override
    public Boolean grantRoleMenus(GrantRoleMenuVO grantRoleMenuVO) {
        GrantRoleMenuDTO grantRoleMenuDTO = new GrantRoleMenuDTO();
        grantRoleMenuDTO.setRoleIdList(grantRoleMenuVO.getRoleIdList());
        grantRoleMenuDTO.setMenuIdList(grantRoleMenuVO.getMenuIdList());
        ResultDTO<Boolean> resultDTO = roleFeginClient.grantRoleMenus(grantRoleMenuDTO);
        ResultUtils.checkOrElseThrow(resultDTO,"RoleServiceImpl#grantRoleMenus error, error = {}",resultDTO.getMsg());
        return Boolean.TRUE;
    }

    @Override
    public RoleListDetailVO detail(Integer id) {
        RoleReqDTO roleReqDTO = new RoleReqDTO();
        roleReqDTO.setId(id);
        ResultDTO<RoleDTO> resultDTO = roleFeginClient.getOne(roleReqDTO);
        boolean checkResult = ResultUtils.checkSuccessAndDataNotNull(resultDTO);
        if (checkResult)
        {
            RoleDTO roleDTO = resultDTO.getData();
            RoleListDetailVO roleListDetailVO = roleConvert.roleDTO2RoleListDetailVO(roleDTO);
            return roleListDetailVO;
        }
        else
        {
            throw new RuntimeException(resultDTO.getMsg());
        }

    }

    @Override
    public PageResult<RoleListDetailVO> list(RoleListReqVO roleListReqVO) {
        RoleReqDTO roleReqDTO = roleConvert.roleListReqVO2RoleReqDTO(roleListReqVO);
        ResultDTO<PageDTO<RoleDTO>> resultDTO = roleFeginClient.getList(roleReqDTO);
        if (!resultDTO.getSuccess())
        {
            throw new RuntimeException(resultDTO.getMsg());
        }

        PageDTO<RoleDTO> pageDTO = resultDTO.getData();

        List<RoleDTO> roleDTOList = pageDTO.getList();

        List<RoleListDetailVO> roleListDetailVOList = Lists.newArrayList();

        if (CollectionUtil.isNotEmpty(roleDTOList))
        {
            roleListDetailVOList = roleDTOList.stream().map(roleDTO -> roleConvert.roleDTO2RoleListDetailVO(roleDTO)).collect(Collectors.toList());
        }

        PageResult<RoleListDetailVO> pageResult = new PageResult<>(
                roleListReqVO.getPageNum(), roleListReqVO.getPageSize(), pageDTO.getTotal(), roleListDetailVOList
        );
        return pageResult;
    }
}
