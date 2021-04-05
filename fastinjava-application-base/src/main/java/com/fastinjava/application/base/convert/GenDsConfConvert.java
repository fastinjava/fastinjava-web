package com.fastinjava.application.base.convert;

import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfReqDTO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;

public interface GenDsConfConvert {
    GenDatasourceConfReqDTO genDatasourceConfReqVO2GenDatasourceConfReqDTO(GenDatasourceConfReqVO genDatasourceConfReqVO);
    GenDatasourceConfListDetailVO genDatasourceConfDTO2GenDatasourceConfListDetailVO(GenDatasourceConfDTO genDatasourceConfDTO);
}
