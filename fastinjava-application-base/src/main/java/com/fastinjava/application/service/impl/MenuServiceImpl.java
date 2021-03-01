package com.fastinjava.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.MenuFeginClient;
import com.fastinjava.application.convert.MenuConvert;
import com.fastinjava.application.service.MenuService;
import com.fastinjava.framework.baseapplication.api.MenuController;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuFeginClient menuFeginClient;
    @Resource
    private MenuConvert menuConvert;

    @Override
    public Boolean insert(MenuInsertVO menuInsertVO) {
        MenuInsertDTO menuInsertDTO = menuConvert.menuInsertVO2MenuInsertDTO(menuInsertVO);
        ResultDTO<Boolean> resultDTO = menuFeginClient.insert(menuInsertDTO);
        if (!resultDTO.getSuccess())
        {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
    }

    @Override
    public PageResult<MenuListDetailVO> list(MenuReqVO menuReqVO) {
        //menuConvert.menuReqVO2MenuReqDTO(menuReqVO)
        MenuReqDTO menuReqDTO = menuConvert.menuReqVO2MenuReqDTO(menuReqVO);
        ResultDTO<PageDTO<MenuDTO>> resultDTO = menuFeginClient.getList(
               menuReqDTO
        );
        if (!resultDTO.getSuccess())
        {
            throw new RuntimeException(resultDTO.getMsg());
        }
        PageDTO<MenuDTO> pageDTO = resultDTO.getData();
        PageResult<MenuListDetailVO> pageResult = new PageResult<>();
        pageResult.setPageNum(menuReqVO.getPageNum());
        pageResult.setPageSize(menuReqVO.getPageSize());
        pageResult.setTotal(pageDTO.getTotal());
        List<MenuDTO> menuDTOList = pageDTO.getList();
        if (CollectionUtil.isNotEmpty(menuDTOList))
        {
            List<MenuListDetailVO> menuListDetailVOList = menuDTOList.stream().map(menuDTO -> menuConvert.menuDTO2MenuListDetailVO(menuDTO)).collect(Collectors.toList());
            pageResult.setList(menuListDetailVOList);
        }
        else {
            pageResult.setList(Lists.newArrayList());
        }
        return pageResult;
    }
}
