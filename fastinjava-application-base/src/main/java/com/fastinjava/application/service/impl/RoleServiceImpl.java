package com.fastinjava.application.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.RoleReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.RoleFeginClient;
import com.fastinjava.application.convert.RoleConvert;
import com.fastinjava.application.service.RoleService;
import com.fastinjava.framework.baseapplication.vo.RoleInsertVO;
import com.fastinjava.framework.baseapplication.vo.RoleListDetailVO;
import com.fastinjava.framework.baseapplication.vo.RoleListReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
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
        RoleCreateDTO roleCreateDTO = roleConvert.roleInsertVO2RoleCreateDTO(roleInsertVO);
        ResultDTO<Boolean> resultDTO = roleFeginClient.insert(roleCreateDTO);
        if (!resultDTO.getSuccess())
        {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
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
