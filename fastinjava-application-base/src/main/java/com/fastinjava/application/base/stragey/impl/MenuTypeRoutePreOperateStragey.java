package com.fastinjava.application.base.stragey.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fastdevelopinjava.framework.system.api.dto.MenuDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuInsertDTO;
import com.fastdevelopinjava.framework.system.api.dto.MenuReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.MenuFeginClient;
import com.fastinjava.application.base.stragey.MenuType;
import com.fastinjava.application.base.stragey.MenuTypePreOperateStragey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@MenuType(value = "2")
public class MenuTypeRoutePreOperateStragey extends MenuTypePreOperateStragey {

    @Resource
    private MenuFeginClient menuFeginClient;

    @Override
    public String operate(String input) {
        log.info(this.getClass().getAnnotation(MenuType.class).value());

        MenuInsertDTO inputParam = JSONUtil.toBean(input, MenuInsertDTO.class);
        MenuInsertDTO outputParam = new MenuInsertDTO();
        if ("2".equalsIgnoreCase(inputParam.getMenuType()))
        {
            outputParam.setMenuId(null);
            outputParam.setMenuName(inputParam.getMenuName());
            outputParam.setMenuCode(inputParam.getMenuCode());
            outputParam.setMenuUrl(inputParam.getMenuUrl());
            outputParam.setMenuIcon(inputParam.getMenuIcon());

            String menuPid = inputParam.getMenuPid();
            if (StrUtil.isBlank(menuPid))
            {
                throw new RuntimeException("所选菜单类型为页面路由时，父菜单不允许为空");
            }
            else {
                MenuReqDTO menuReqDTO = new MenuReqDTO();
                menuReqDTO.setMenuId(Integer.valueOf(menuPid));
                ResultDTO<MenuDTO> resultDTO = menuFeginClient.getOne(menuReqDTO);
                MenuDTO menuDTO = resultDTO.getData();
                //当所选菜单类型为页面路由时，父菜单，不允许为空，且父菜单类型必须是目录类型；
                Assert.isTrue("1".equalsIgnoreCase(menuDTO.getMenuType()));
                outputParam.setMenuPid(menuPid);
            }
            outputParam.setMenuType("2");
            outputParam.setMenuVisible(StrUtil.EMPTY);
            outputParam.setMenuApiIds("[]");
            outputParam.setDeleteFlag("0");
            outputParam.setCreateTime(DateUtil.date());
            outputParam.setUpdateTime(DateUtil.date());
            outputParam.setClientId(inputParam.getClientId());
        }

        else {
            Assert.isTrue("2".equalsIgnoreCase(inputParam.getMenuType()),"菜单类型预处理不匹配,menutype = {}",inputParam.getMenuType());
        }


        return JSONUtil.toJsonStr(outputParam);
    }
}
