package com.fastinjava.application.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsUpdateDTO;
import com.fastinjava.application.convert.OauthInfoConvert;
import com.fastinjava.framework.baseapplication.vo.OauthDetailReqVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsInsertVO;
import com.fastinjava.framework.baseapplication.vo.OauthDetailsUpdateVO;
import com.fastinjava.framework.baseapplication.vo.OauthListDetailsVO;
import org.springframework.stereotype.Component;

/**
 * 转换类
 * 对于通用属性我们直接使用BeanUtil#copyProperties方法
 */
@Component
public class OauthInfoConvertImpl implements OauthInfoConvert {

    @Override
    public OauthListDetailsVO oauthDetailsDTO2OauthListDetailsVO(OauthDetailsDTO oauthDetailsDTO) {

        if (null == oauthDetailsDTO)
        {
            return null;
        }

        OauthListDetailsVO oauthListDetailsVO = new OauthListDetailsVO();

        BeanUtil.copyProperties(oauthDetailsDTO,oauthListDetailsVO);

        return oauthListDetailsVO;
    }

    @Override
    public OauthDetailsInsertDTO oauthDetailsInsertVO2OauthDetailsInsertDTO(OauthDetailsInsertVO oauthDetailsInsertVO) {
        if (null == oauthDetailsInsertVO)
        {
            return null;
        }
        OauthDetailsInsertDTO oauthDetailsInsertDTO = new OauthDetailsInsertDTO();
        BeanUtil.copyProperties(oauthDetailsInsertVO,oauthDetailsInsertDTO);
        return oauthDetailsInsertDTO;
    }

    @Override
    public OauthDetailsUpdateDTO oauthDetailsUpdateVO2OauthDetailsUpdateDTO(OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        if (null == oauthDetailsUpdateVO)
        {
            return null;
        }
        OauthDetailsUpdateDTO oauthDetailsUpdateDTO = new OauthDetailsUpdateDTO();
        BeanUtil.copyProperties(oauthDetailsUpdateVO,oauthDetailsUpdateDTO);
        return oauthDetailsUpdateDTO;
    }

    @Override
    public OauthDetailReqDTO oauthDetailReqVO2OauthDetailReqDTO(OauthDetailReqVO oauthDetailReqVO) {

        if (ObjectUtil.isEmpty(oauthDetailReqVO))
        {
            return null;
        }

        OauthDetailReqDTO oauthDetailReqDTO = new OauthDetailReqDTO();
        BeanUtil.copyProperties(oauthDetailReqVO,oauthDetailReqDTO);


        return oauthDetailReqDTO;
    }
}
