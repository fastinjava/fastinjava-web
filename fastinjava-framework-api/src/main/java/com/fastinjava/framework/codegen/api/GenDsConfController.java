package com.fastinjava.framework.codegen.api;

import com.fastdevelopinjava.framework.codegen.api.dto.GenCodePreviewReqDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.TableDTO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;
import com.fastinjava.framework.common.res.JsonResult;
import com.fastinjava.framework.common.res.PageResult;

import java.util.List;
import java.util.Map;

public interface GenDsConfController {

    JsonResult<PageResult<GenDatasourceConfListDetailVO>> list(GenDatasourceConfReqVO genDatasourceConfReqVO);
    JsonResult<List<TableDTO>> getTablesByDsName(String dsName);
    JsonResult<Map<String,Object>> preview(GenCodePreviewReqDTO genCodePreviewReqDTO);
}
