package com.fastinjava.application.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.SysDictDTO;
import com.fastdevelopinjava.framework.system.api.dto.SysDictInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.SysDictReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.SysDictFeginClient;
import com.fastinjava.application.base.service.SysDictService;
import com.fastinjava.framework.baseapplication.vo.SysDictInsertVO;
import com.fastinjava.framework.baseapplication.vo.SysDictListDetailVO;
import com.fastinjava.framework.baseapplication.vo.SysDictReqVO;
import com.fastinjava.framework.common.res.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SysDictServiceImpl  implements SysDictService {

    @Resource
    private SysDictFeginClient sysDictFeginClient;

    @Override
    public PageResult<SysDictListDetailVO> list(SysDictReqVO sysDictReqVO) {
        SysDictReqDTO sysDictReqDTO = new SysDictReqDTO();
        BeanUtil.copyProperties(sysDictReqVO,sysDictReqDTO);
        ResultDTO<PageDTO<SysDictDTO>> resultDTO = sysDictFeginClient.getList(sysDictReqDTO);
        if (resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()))
        {
            PageDTO<SysDictDTO> pageDTO = resultDTO.getData();
            List<SysDictListDetailVO> sysDictListDetailVOList = pageDTO.getList().stream().map(new Function<SysDictDTO, SysDictListDetailVO>() {
                @Override
                public SysDictListDetailVO apply(SysDictDTO sysDictDTO) {
                    SysDictListDetailVO sysDictListDetailVO = new SysDictListDetailVO();
                    BeanUtil.copyProperties(sysDictDTO,sysDictListDetailVO);
                    return sysDictListDetailVO;
                }
            }).collect(Collectors.toList());

            PageResult<SysDictListDetailVO> pageResult = new PageResult<>();
            pageResult.setTotal(pageDTO.getTotal());
            pageResult.setPageSize(sysDictReqVO.getPageSize());
            pageResult.setList(sysDictListDetailVOList);
            return pageResult;
        }
        else {
            throw new RuntimeException(resultDTO.getMsg());
        }
    }

    @Override
    public Boolean insert(SysDictInsertVO sysDictInsertVO) {
        SysDictInsertDTO sysDictInsertDTO = new SysDictInsertDTO();

        BeanUtil.copyProperties(sysDictInsertVO,sysDictInsertDTO);

        sysDictInsertDTO.setId(null);
        sysDictInsertDTO.setCreateTime(null);
        sysDictInsertDTO.setUpdateTime(null);

        ResultDTO<Boolean> resultDTO = sysDictFeginClient.insert(sysDictInsertDTO);
        if (resultDTO.getSuccess())
        {
            return true;
        }
        else {
            throw new RuntimeException(resultDTO.getMsg());
        }
    }
}
