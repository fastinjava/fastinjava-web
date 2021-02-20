package com.fastinjava.application.service.impl;

import cn.hutool.core.lang.Assert;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.client.AppFeginClient;
import com.fastinjava.application.convert.AppConvert;
import com.fastinjava.application.service.AppService;
import com.fastinjava.framework.baseapplication.vo.AppInsertVO;
import com.fastinjava.framework.baseapplication.vo.AppListDetailVO;
import com.fastinjava.framework.baseapplication.vo.AppListReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {

    @Resource
    private AppFeginClient appFeginClient;


    @Resource
    private AppConvert appConvert;

    @Override
    public PageResult<AppListDetailVO> list(AppListReqVO appListReqVO) {
        ApplicationReqDTO applicationReqDTO = appConvert.appListReqVO2ApplicationReqDTO(appListReqVO);
        ResultDTO<PageDTO<ApplicationDTO>> resultDTO = appFeginClient.getList(applicationReqDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        PageDTO<ApplicationDTO> pageDTO = resultDTO.getData();
        Assert.notNull(pageDTO);

        List<ApplicationDTO> applicationDTOList = pageDTO.getList();
        List<AppListDetailVO> appListDetailVOList = applicationDTOList.stream().map(applicationDTO -> appConvert.applicationDTO2AppListDetailVO(applicationDTO)).collect(Collectors.toList());


        PageResult<AppListDetailVO> pageResult = new PageResult<>();
        pageResult.setTotal(pageDTO.getTotal());
        pageResult.setPageNum(appListReqVO.getPageNum());
        pageResult.setPageSize(appListReqVO.getPageSize());
        pageResult.setList(appListDetailVOList);

        return pageResult;
    }


    @Override
    public boolean insertSelective(AppInsertVO appInsertVO) {
        ApplicationInsertDTO applicationInsertDTO = appConvert.appInsertVO2ApplicationInsertDTO(appInsertVO);
        ResultDTO<Boolean> resultDTO = appFeginClient.insert(applicationInsertDTO);
        if (!resultDTO.getSuccess()) {
            throw new RuntimeException(resultDTO.getMsg());
        }
        return true;
    }
}
