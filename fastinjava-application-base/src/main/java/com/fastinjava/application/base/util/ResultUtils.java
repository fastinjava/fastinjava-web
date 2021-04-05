package com.fastinjava.application.base.util;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;

/**
 * ResultUtils 工具类
 */
public class ResultUtils {

    public static boolean checkSuccess(ResultDTO resultDTO){
        return BooleanUtil.isTrue(resultDTO.getSuccess());
    }

    public static boolean checkSuccessOrElseThrow(ResultDTO resultDTO,String errorTemplate,Object... objects){
        boolean success = BooleanUtil.isTrue(resultDTO.getSuccess());
        if (success)
        {
            return true;
        }
        else {
            throw new RuntimeException(StrUtil.format(errorTemplate,objects));
        }
    }

    public static boolean checkSuccessAndDataNotNull(ResultDTO resultDTO){
        return checkSuccess(resultDTO) && ObjectUtil.isNotEmpty(resultDTO.getData());
    }

    private static boolean checkSuccessAndDataNotNullAndDataIsTrue(ResultDTO resultDTO){
        if (!checkSuccessAndDataNotNull(resultDTO))
        {
            return false;
        }
        else
        {
            Object data = resultDTO.getData();
            if (BooleanUtil.isBoolean(data.getClass()))
            {
                return BooleanUtil.isTrue((Boolean) data);
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean check(ResultDTO resultDTO){
        return checkSuccessAndDataNotNullAndDataIsTrue(resultDTO);
    }

    public static boolean checkOrElseThrow(ResultDTO resultDTO,String errorTemplate,Object... objects){
        if (checkSuccessAndDataNotNullAndDataIsTrue(resultDTO))
            return true;
        throw new RuntimeException(StrUtil.format(errorTemplate,objects));
    }

}
