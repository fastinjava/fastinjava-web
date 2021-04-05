package com.fastinjava.application.base.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfDTO;
import com.fastdevelopinjava.framework.codegen.api.dto.GenDatasourceConfReqDTO;
import com.fastinjava.application.base.convert.GenDsConfConvert;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfListDetailVO;
import com.fastinjava.framework.codegen.vo.GenDatasourceConfReqVO;
import org.springframework.stereotype.Component;

@Component
public class GenDsConfConvertImpl implements GenDsConfConvert {
    @Override
    public GenDatasourceConfReqDTO genDatasourceConfReqVO2GenDatasourceConfReqDTO(GenDatasourceConfReqVO genDatasourceConfReqVO) {

        if (ObjectUtil.isEmpty(genDatasourceConfReqVO))
        {
            return null;
        }
        else
        {
            GenDatasourceConfReqDTO genDatasourceConfReqDTO = new GenDatasourceConfReqDTO();
            BeanUtil.copyProperties(genDatasourceConfReqVO,genDatasourceConfReqDTO);
            return genDatasourceConfReqDTO;
        }


    }

    @Override
    public GenDatasourceConfListDetailVO genDatasourceConfDTO2GenDatasourceConfListDetailVO(GenDatasourceConfDTO genDatasourceConfDTO) {
        if (ObjectUtil.isEmpty(genDatasourceConfDTO)) return null;
        GenDatasourceConfListDetailVO genDatasourceConfListDetailVO = new GenDatasourceConfListDetailVO();

        BeanUtil.copyProperties(genDatasourceConfDTO,genDatasourceConfListDetailVO);

        return genDatasourceConfListDetailVO;
    }
}
