package com.fastinjava.application.base.stragey;

import cn.hutool.core.util.ObjectUtil;
import com.fastinjava.application.base.util.SpringContextHolder;

import java.util.Map;

public class MenuTypePreOperateStrageyContext {

    public MenuTypePreOperateStrageyContext(Map<String, Class> menuTypeHandlerMap) {
        this.menuTypeHandlerMap = menuTypeHandlerMap;
    }

    private Map<String,Class> menuTypeHandlerMap;


    public MenuTypePreOperateStragey getMenuTypePreOperateStragey(String menuType){
        Class aClass = menuTypeHandlerMap.get(menuType);
        if (ObjectUtil.isEmpty(aClass))
            throw new RuntimeException(String.format("找不到菜单类型menutype = %s 的处理器",menuType));
        return (MenuTypePreOperateStragey)SpringContextHolder.getBean(aClass);
    }

}
