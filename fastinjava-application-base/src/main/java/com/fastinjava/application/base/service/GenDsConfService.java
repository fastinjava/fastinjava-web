package com.fastinjava.application.base.service;

import com.fastdevelopinjava.framework.codegen.api.dto.GenCodePreviewReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.TableDTO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;
import java.util.Map;

public interface GenDsConfService {
    PageResult<GenDatasourceConfListDetailVO> list(GenDatasourceConfReqVO genDatasourceConfReqVO);

    List<TableDTO> getTablesByDsName(String dsName);

    Map<String, Object> preview(GenCodePreviewReqDTO genCodePreviewReqDTO);
}
