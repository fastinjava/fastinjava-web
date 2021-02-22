package com.fastinjava.application.service.impl;


import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.MenuFeginClient;
import com.fastinjava.application.convert.MenuConvert;
import com.fastinjava.application.service.MenuService;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuConvert menuConvert;
    @Resource
    private MenuFeginClient menuFeginClient;

    @Override
    public Boolean insert(MenuInsertVO menuInsertVO) {
        MenuInsertDTO menuInsertDTO = menuConvert.menuInsertVO2MenuInsertDTO(menuInsertVO);
        ResultDTO<Boolean> resultDTO = menuFeginClient.insert(menuInsertDTO);
        if (!resultDTO.getSuccess())
            throw new RuntimeException(resultDTO.getMsg());
        resultDTO.getSuccess();
        return true;
    }

    @Override
    public PageResult<MenuListDetailVO> list(MenuReqVO menuReqVO) {
        MenuReqDTO menuReqDTO = menuConvert.menuReqVO2MenuListDetailVO(menuReqVO);
        ResultDTO<PageDTO<MenuDTO>> resultDTO = menuFeginClient.getList(menuReqDTO);
        if (!resultDTO.getSuccess())
            throw new RuntimeException(resultDTO.getMsg());
        PageDTO<MenuDTO> pageDTO = resultDTO.getData();
        List<MenuDTO> menuDTOList = pageDTO.getList();
        List<MenuListDetailVO> menuListDetailVOList = menuDTOList.stream().map(menuDTO -> menuConvert.menuDTO2MenuListDetailVO(menuDTO)).collect(Collectors.toList());
        return new PageResult<>(menuReqVO.getPageNum(),menuReqVO.getPageSize(),pageDTO.getTotal(),menuListDetailVOList);
    }
}
