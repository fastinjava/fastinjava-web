package com.fastinjava.application.base.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.codegen.api.dto.GenCodePreviewReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.TableDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.PageDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.GenDsConfFeginClient;
import com.fastinjava.application.base.client.GeneratorFeginClient;
import com.fastinjava.application.base.convert.GenDsConfConvert;
import com.fastinjava.application.base.service.GenDsConfService;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;
import com.fastinjava.framework.common.res.PageResult;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenDsConfServiceImpl implements GenDsConfService {

    @Resource
    private GenDsConfFeginClient genDsConfFeginClient;

    @Resource
    private GeneratorFeginClient generatorFeginClient;

    @Resource
    private GenDsConfConvert genDsConfConvert;


    @Override
    public PageResult<GenDatasourceConfListDetailVO> list(GenDatasourceConfReqVO genDatasourceConfReqVO) {
        GenDatasourceConfReqDTO genDatasourceConfReqDTO = genDsConfConvert.genDatasourceConfReqVO2GenDatasourceConfReqDTO(genDatasourceConfReqVO);
        ResultDTO<PageDTO<GenDatasourceConfDTO>> resultDTO = genDsConfFeginClient.getList(genDatasourceConfReqDTO);
        Assert.isTrue(ObjectUtil.isNotEmpty(resultDTO) && resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()));
        PageDTO<GenDatasourceConfDTO> pageDTO = resultDTO.getData();
        List<GenDatasourceConfDTO> genDatasourceConfDTOList = pageDTO.getList();

        List<GenDatasourceConfListDetailVO> genDatasourceConfListDetailVOList = Lists.newArrayList();
        genDatasourceConfListDetailVOList = genDatasourceConfDTOList.stream().map(
                genDatasourceConfDTO -> genDsConfConvert.genDatasourceConfDTO2GenDatasourceConfListDetailVO(genDatasourceConfDTO)
        ).collect(Collectors.toList());
        PageResult<GenDatasourceConfListDetailVO> pageResult = new PageResult<>(genDatasourceConfReqVO.getPageNum(), genDatasourceConfReqDTO.getPageSize(), pageDTO.getTotal(), genDatasourceConfListDetailVOList);
        return pageResult;
    }


    @Override
    public Map<String, Object> preview(GenCodePreviewReqDTO genCodePreviewReqDTO) {
        ResultDTO<Map<String, Object>> resultDTO = generatorFeginClient.preview(genCodePreviewReqDTO);
        return resultDTO.getData();
    }

    @Override
    public List<TableDTO> getTablesByDsName(String dsName) {
        ResultDTO<List<TableDTO>> resultDTO = genDsConfFeginClient.getTablesByDsName(dsName);
        Assert.isTrue(ObjectUtil.isNotEmpty(resultDTO) && resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()));
        List<TableDTO> tableDTOList = resultDTO.getData();
        return tableDTOList;
    }
}
