package com.fastinjava.application.base.stragey;


import cn.hutool.core.lang.ClassScanner;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class MenuTypePreOperateStrageyProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "com.fastinjava.application.base.stragey";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String,Class> menuTypeHandlerMap = Maps.newHashMap();

        Set<Class<?>> handlerClasses = ClassScanner.scanPackageByAnnotation(HANDLER_PACKAGE, MenuType.class);

        for (Class<?> handlerClass : handlerClasses) {
            MenuType menuType = handlerClass.getAnnotation(MenuType.class);
            menuTypeHandlerMap.put(menuType.value(),handlerClass);
        }

        configurableListableBeanFactory.registerSingleton(MenuTypePreOperateStrageyContext.class.getName(),new MenuTypePreOperateStrageyContext(menuTypeHandlerMap));

    }
}
