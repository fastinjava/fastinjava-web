package com.fastinjava.application.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastinjava.application.convert.MenuConvert;
import com.fastinjava.framework.baseapplication.vo.MenuInsertVO;
import com.fastinjava.framework.baseapplication.vo.MenuListDetailVO;
import com.fastinjava.framework.baseapplication.vo.MenuReqVO;
import org.springframework.stereotype.Component;

@Component
public class MenuConvertIMpl implements MenuConvert {
    @Override
    public MenuListDetailVO menuDTO2MenuListDetailVO(MenuDTO menuDTO) {
        if (ObjectUtil.isEmpty(menuDTO)) {
            return null;
        }
        MenuListDetailVO menuListDetailVO = new MenuListDetailVO();
        BeanUtil.copyProperties(menuDTO,menuListDetailVO);
        return menuListDetailVO;
    }

    @Override
    public MenuInsertDTO menuInsertVO2MenuInsertDTO(MenuInsertVO menuInsertVO) {
        if (ObjectUtil.isEmpty(menuInsertVO)) {
            return null;
        }
        MenuInsertDTO menuInsertDTO = new MenuInsertDTO();
        BeanUtil.copyProperties(menuInsertVO,menuInsertDTO);
        return menuInsertDTO;
    }

    @Override
    public MenuReqDTO menuReqVO2MenuListDetailVO(MenuReqVO menuReqVO) {
        if (ObjectUtil.isEmpty(menuReqVO)) return null;
        MenuReqDTO menuReqDTO = new MenuReqDTO();
        BeanUtil.copyProperties(menuReqVO,menuReqDTO);
        return menuReqDTO;
    }
}
