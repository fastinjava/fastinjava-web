package com.fastinjava.application.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserCreateDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.UserFeginClient;
import com.fastinjava.application.convert.UserConvert;
import com.fastinjava.application.service.UserService;
import com.fastinjava.framework.baseapplication.vo.UserInsertVO;
import com.fastinjava.framework.baseapplication.vo.UserListDetailVO;
import com.fastinjava.framework.baseapplication.vo.UserReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeginClient userFeginClient;


    @Resource
    private UserConvert userConvert;

    @Override
    public Boolean insert(UserInsertVO userInsertVO) {
        UserCreateDTO userCreateDTO = userConvert.userInsertVO2UserCreateDTO(userInsertVO);
        ResultDTO<Boolean> resultDTO = userFeginClient.insert(userCreateDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
    }

    @Override
    public PageResult<UserListDetailVO> list(UserReqVO userReqVO) {
        UserReqDTO userReqDTO = userConvert.userReqVO2UserReqDTO(userReqVO);
        ResultDTO<PageDTO<UserDTO>> resultDTO = userFeginClient.selectList(userReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }

        PageDTO<UserDTO> pageDTO = resultDTO.getData();
        List<UserDTO> userDTOList = pageDTO.getList();
        List<UserListDetailVO> userListDetailVOList = Lists.newArrayList();
        if (CollectionUtil.isNotEmpty(userDTOList)) {
            userListDetailVOList = userDTOList.stream().map(new Function<UserDTO, UserListDetailVO>() {
                @Override
                public UserListDetailVO apply(UserDTO userDTO) {
                    UserListDetailVO userListDetailVO = userConvert.userDTO2UserListDetailVO(userDTO);
                    return userListDetailVO;
                }
            }).collect(Collectors.toList());
        }
        return new PageResult<>(
                userReqVO.getPageNum(),
                userReqVO.getPageSize(),
                pageDTO.getTotal(),
                userListDetailVOList
        );
    }

}
