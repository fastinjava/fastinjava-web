package com.fastinjava.application.base.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fastdevelopinjava.framework.system.api.dto.*;
import com.fastinjava.application.base.convert.OauthInfoConvert;
import com.fastinjava.framework.baseapplication.vo.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 转换类
 * 对于通用属性我们直接使用BeanUtil#copyProperties方法
 */
@Component
public class OauthInfoConvertImpl implements OauthInfoConvert {

    @Override
    public OauthListDetailsVO oauthDetailsDTO2OauthListDetailsVO(OauthDetailsDTO oauthDetailsDTO) {

        if (null == oauthDetailsDTO) {
            return null;
        }

        OauthListDetailsVO oauthListDetailsVO = new OauthListDetailsVO();

        BeanUtil.copyProperties(oauthDetailsDTO, oauthListDetailsVO);

        String resourceNameList = oauthDetailsDTO.getResourceNameList();
        if (StrUtil.isNotBlank(resourceNameList)) {
            oauthListDetailsVO.setResourceNameList(Arrays.asList(resourceNameList.split(",")));
        }
        return oauthListDetailsVO;
    }

    @Override
    public OauthDetailsInsertDTO oauthDetailsInsertVO2OauthDetailsInsertDTO(OauthDetailsInsertVO oauthDetailsInsertVO) {
        if (null == oauthDetailsInsertVO) {
            return null;
        }
        OauthDetailsInsertDTO oauthDetailsInsertDTO = new OauthDetailsInsertDTO();
        BeanUtil.copyProperties(oauthDetailsInsertVO, oauthDetailsInsertDTO);

        List<Integer> resourceIdList = oauthDetailsInsertVO.getResourceIdList();
        if (CollectionUtil.isNotEmpty(resourceIdList)) {
            String resourceIds = CollectionUtil.join(resourceIdList, ",");
            oauthDetailsInsertDTO.setResourceIds(resourceIds);
        }
        return oauthDetailsInsertDTO;
    }

    @Override
    public OauthDetailsUpdateDTO oauthDetailsUpdateVO2OauthDetailsUpdateDTO(OauthDetailsUpdateVO oauthDetailsUpdateVO) {
        if (null == oauthDetailsUpdateVO) {
            return null;
        }
        OauthDetailsUpdateDTO oauthDetailsUpdateDTO = new OauthDetailsUpdateDTO();
        BeanUtil.copyProperties(oauthDetailsUpdateVO, oauthDetailsUpdateDTO);
        return oauthDetailsUpdateDTO;
    }

    @Override
    public GrantClientApiDTO grantClientApiVO2GrantClientApiDTO(GrantClientApiVO grantClientApiVO) {
        if (ObjectUtil.isEmpty(grantClientApiVO))
        {
            return null;
        }
        else
        {
            GrantClientApiDTO grantClientApiDTO = new GrantClientApiDTO();
            grantClientApiDTO.setClientId(grantClientApiVO.getClientId());
            grantClientApiDTO.setApiCodeList(grantClientApiVO.getApiCodeList());
            return grantClientApiDTO;
        }

    }

    @Override
    public OauthDetailReqDTO oauthDetailReqVO2OauthDetailReqDTO(OauthDetailReqVO oauthDetailReqVO) {

        if (ObjectUtil.isEmpty(oauthDetailReqVO)) {
            return null;
        }

        OauthDetailReqDTO oauthDetailReqDTO = new OauthDetailReqDTO();
        BeanUtil.copyProperties(oauthDetailReqVO, oauthDetailReqDTO);


        return oauthDetailReqDTO;
    }
}
