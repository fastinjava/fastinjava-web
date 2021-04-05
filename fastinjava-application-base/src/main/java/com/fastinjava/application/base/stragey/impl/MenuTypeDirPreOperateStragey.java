package com.fastinjava.application.base.stragey.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastinjava.application.base.stragey.MenuType;
import com.fastinjava.application.base.stragey.MenuTypePreOperateStragey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@MenuType(value = "1")
public class MenuTypeDirPreOperateStragey extends MenuTypePreOperateStragey {
    @Override
    public String operate(String input) {
        log.info(this.getClass().getAnnotation(MenuType.class).value());

        MenuInsertDTO inputParam = JSONUtil.toBean(input, MenuInsertDTO.class);
        MenuInsertDTO outputParam = new MenuInsertDTO();
        if ("1".equalsIgnoreCase(inputParam.getMenuType()))
        {
            outputParam.setMenuId(null);
            outputParam.setMenuName(inputParam.getMenuName());
            outputParam.setMenuCode(inputParam.getMenuCode());
            outputParam.setMenuUrl(StrUtil.EMPTY);
            outputParam.setMenuIcon(inputParam.getMenuIcon());
            outputParam.setMenuPid("0");
            outputParam.setMenuType("1");
            outputParam.setMenuVisible(StrUtil.EMPTY);
            outputParam.setMenuApiIds("[]");
            outputParam.setDeleteFlag("0");
            outputParam.setCreateTime(DateUtil.date());
            outputParam.setUpdateTime(DateUtil.date());
            outputParam.setClientId(inputParam.getClientId());
        }

        else {
            Assert.isTrue("1".equalsIgnoreCase(inputParam.getMenuType()),"菜单类型预处理不匹配,menutype = {}",inputParam.getMenuType());
        }


        return JSONUtil.toJsonStr(outputParam);
    }
}
