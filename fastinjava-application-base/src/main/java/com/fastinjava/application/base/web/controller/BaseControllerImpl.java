package com.fastinjava.application.base.web.controller;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fastdevelopinjava.framework.system.api.dto.ApiInfoDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationDTO;
import com.fastdevelopinjava.framework.system.api.dto.ApplicationReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.base.client.AppFeginClient;
import com.fastinjava.framework.common.dto.RequestToMethodItemDTO;
import com.fastinjava.framework.common.res.JsonResult;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Api("BaseControllerImpl")
@Slf4j
@RequestMapping("/v/vv")
@RestController
public class BaseControllerImpl {


    @Resource
    private AppFeginClient appFeginClient;


    @GetMapping("/java_learn_map")
    public JsonResult<JSONArray> javaLearnMap(){
        /*http://yun.itheima.com/map/javaeetree?pc&stt#map1*/
        JSONObject data = new JSONObject()
                .fluentPut("label", "Java基础").fluentPut("value", "1")
                .fluentPut("children", new JSONArray()
                        .fluentAdd(new JSONObject().fluentPut("label", "Java基础语法").fluentPut("value", "1-1"))
                        .fluentAdd(new JSONObject().fluentPut("label", "面向对象").fluentPut("value", "1-2"))
                        .fluentAdd(new JSONObject().fluentPut("label", "集合").fluentPut("value", "1-3"))
                        .fluentAdd(new JSONObject().fluentPut("label", "IO").fluentPut("value", "1-4"))
                        .fluentAdd(new JSONObject().fluentPut("label", "线程与并发").fluentPut("value", "1-5"))
                        .fluentAdd(new JSONObject().fluentPut("label", "异常").fluentPut("value", "1-6"))
                        .fluentAdd(new JSONObject().fluentPut("label", "网络编程").fluentPut("value", "1-7"))
                )
                .fluentPut("label", "数据库").fluentPut("value", "2")
                .fluentPut("children", new JSONArray()
                        .fluentAdd(new JSONObject().fluentPut("label", "MySQL").fluentPut("value", "2-1"))
                        .fluentAdd(new JSONObject().fluentPut("label", "Oracle").fluentPut("value", "2-2"))
                        .fluentAdd(new JSONObject().fluentPut("label", "JDBC").fluentPut("value", "2-3"))
                        .fluentAdd(new JSONObject().fluentPut("label", "C3P0").fluentPut("value", "2-4"))
                        .fluentAdd(new JSONObject().fluentPut("label", "Druid").fluentPut("value", "2-5"))
                )
                .fluentPut("label", "前端技术").fluentPut("value", "3")
                .fluentPut("children", new JSONArray()
                        .fluentAdd(new JSONObject().fluentPut("label", "MySQL").fluentPut("value", "2-1"))
                        .fluentAdd(new JSONObject().fluentPut("label", "Oracle").fluentPut("value", "2-2"))
                        .fluentAdd(new JSONObject().fluentPut("label", "JDBC").fluentPut("value", "2-3"))
                        .fluentAdd(new JSONObject().fluentPut("label", "C3P0").fluentPut("value", "2-4"))
                        .fluentAdd(new JSONObject().fluentPut("label", "Druid").fluentPut("value", "2-5"))
                )
//                        /*4*/
                .fluentPut("label", "动态网页").fluentPut("value", "1").fluentPut("children", null)
                /*5*/
                .fluentPut("label", "编程强化").fluentPut("value", "1").fluentPut("children", null)
                /*6*/
                .fluentPut("label", "软件项目管理").fluentPut("value", "1").fluentPut("children", null)
                /*7*/
                .fluentPut("label", "热门技术框架").fluentPut("value", "1").fluentPut("children", null)
                /*8*/
                .fluentPut("label", "分布式架构").fluentPut("value", "1").fluentPut("children", null)
                /*9*/
                .fluentPut("label", "服务器中间件").fluentPut("value", "1").fluentPut("children", null)
                /*10*/
                .fluentPut("label", "服务器技术").fluentPut("value", "1").fluentPut("children", null)
                /*11*/
                .fluentPut("label", "容器技术").fluentPut("value", "1").fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Docker").fluentPut("value", "11-1").fluentPut("desc", "Docker 是一个开源的应用容器引擎，可以打包应用以及依赖包到一个可移植的镜像中，然后发布到任何流行的Linux或Windows 机器上，也可以实现虚拟化。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Kubernetes").fluentPut("value", "11-2").fluentPut("desc", "Kubernetes是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes的目标是让部署容器化的应用简单并且高效。"))
                )
                .fluentPut("label", "业务解决方案").fluentPut("value", "1").fluentPut("children", null)
                .fluentPut("label", "业务解决方案").fluentPut("value", "1").fluentPut("children", null);

        JSONArray result = new JSONArray();

        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "Java基础").fluentPut("value", "1")
                        .fluentPut("children", new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Java基础语法").fluentPut("value", "1-1"))
                                .fluentAdd(new JSONObject().fluentPut("label", "面向对象").fluentPut("value", "1-2"))
                                .fluentAdd(new JSONObject().fluentPut("label", "集合").fluentPut("value", "1-3"))
                                .fluentAdd(new JSONObject().fluentPut("label", "IO").fluentPut("value", "1-4"))
                                .fluentAdd(new JSONObject().fluentPut("label", "线程与并发").fluentPut("value", "1-5"))
                                .fluentAdd(new JSONObject().fluentPut("label", "异常").fluentPut("value", "1-6"))
                                .fluentAdd(new JSONObject().fluentPut("label", "网络编程").fluentPut("value", "1-7"))
                        )
        );

        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "数据库").fluentPut("value", "2")
                        .fluentPut("children", new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "MySQL").fluentPut("value", "2-1"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Oracle").fluentPut("value", "2-2"))
                                .fluentAdd(new JSONObject().fluentPut("label", "JDBC").fluentPut("value", "2-3"))
                                .fluentAdd(new JSONObject().fluentPut("label", "C3P0").fluentPut("value", "2-4"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Druid").fluentPut("value", "2-5"))
                        )
        );
        /*7*/
        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "热门技术框架").fluentPut("value", "7").fluentPut("children",
                            new JSONArray()
                                    .fluentAdd(new JSONObject().fluentPut("label", "SpringMVC").fluentPut("value", "7-1").fluentPut("desc", "Spring生态中用于web层的MVC框架，是SpringFrameWork的后续产品，当前互联网项目中web层首选框架，与Spring框架无缝衔接，必会技术！"))
                                    .fluentAdd(new JSONObject().fluentPut("label", "MyBatis").fluentPut("value", "7-2").fluentPut("desc", "MyBatis是持久层的用于数据访问的框架，其插件丰富，应用灵活，性能良好，作为互联网项目开发中首选框架。实用性极强，必会技术！"))
                                    .fluentAdd(new JSONObject().fluentPut("label", "Mybatis Plus").fluentPut("value", "7-3").fluentPut("desc", "Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis的基础上只做增强不做改变，为简化开发、提高效率而生。"))
                                    .fluentAdd(new JSONObject().fluentPut("label", "JPA").fluentPut("value", "7-4").fluentPut("desc", "JPA是Java Persistence API的简称，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。"))
                                    .fluentAdd(new JSONObject().fluentPut("label", "Spring Data").fluentPut("value", "7-5").fluentPut("desc", "Spring Data是Spring 的一个子项目。用于简化数据库访问，支持NoSQL和关系数据库存储。其主要目标是使数据库的访问变得方便快捷。"))
                                    .fluentAdd(new JSONObject().fluentPut("label", "Spring").fluentPut("value", "7-6").fluentPut("desc", "Spring是一个轻量级IoC和AOP容器框架。是JavaEE工程师必须掌握的内容，企业开发趋向于Spring\"全家桶\"架构，而这些都基于Spring框架。"))
                        )
        );

        result.fluentAdd(
                new JSONObject().fluentPut("label", "服务器中间件").fluentPut("value", "9").fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label","Tomcat").fluentPut("value","9-1").fluentPut("desc","Tomcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用服务器，Tomcat结合Nginx进行集群搭建，是大型互联网项目常用选择。"))
                                .fluentAdd(new JSONObject().fluentPut("label","Jetty").fluentPut("value","9-2").fluentPut("desc","Jetty 是一个开源的servlet容器，它为基于Java的web容器，Jetty是使用Java语言编写的，它的API以一组JAR包的形式发布。"))
                                .fluentAdd(new JSONObject().fluentPut("label","Nginx").fluentPut("value","9-3").fluentPut("desc","Nginx (engine x) 是一个高性能的http和反向代理web服务器，同时也可以充当邮件服务器，我们常常使用Nginx进行反向代理进行集群搭建。"))
                                .fluentAdd(new JSONObject().fluentPut("label","Linux系统").fluentPut("value","9-4").fluentPut("desc","Linux是一套免费使用和自由传播的且性能稳定的多用户网络操作系统，正式基于这个优点，项目在部署时首选该系统。"))
                                .fluentAdd(new JSONObject().fluentPut("label","CentOS").fluentPut("value","9-5").fluentPut("desc","CentOS是Linux发行版之一，它是来自于Red Hat依照开放源代码规定释出的源代码所编译而成。也是我们常常使用的版本。"))
                                .fluentAdd(new JSONObject().fluentPut("label","Ubuntu").fluentPut("value","9-6").fluentPut("desc","Ubuntu（又称乌班图）是一个以桌面应用为主的开源GNU/Linux操作系统，因为桌面应用丰富，也会作为部分人的办公系统使用。"))
                                .fluentAdd(new JSONObject().fluentPut("label","Virtualbox").fluentPut("value","9-8").fluentPut("desc","VirtualBox 是一款开源虚拟机软件，号称是最强的免费虚拟机软件，可虚拟的系统包括Windows、Mac OS X、Linux、甚至Android等。"))
                        )
        );

        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "容器技术").fluentPut("value", "11").fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Docker").fluentPut("value", "11-1").fluentPut("desc", "Docker 是一个开源的应用容器引擎，可以打包应用以及依赖包到一个可移植的镜像中，然后发布到任何流行的Linux或Windows 机器上，也可以实现虚拟化。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Kubernetes").fluentPut("value", "11-2").fluentPut("desc", "Kubernetes是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes的目标是让部署容器化的应用简单并且高效。"))
                )
        );



        return JsonResult.<JSONArray>builder().success(
                result
        ).build();
    }

    @RequestMapping(value = "/listallhandlers", method = RequestMethod.GET)
    public JsonResult<List<RequestToMethodItemDTO>> index(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        if (servletContext == null) {
            return null;
        }
        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //请求url和处理方法的映射
        List<RequestToMethodItemDTO> requestToMethodItemList = new ArrayList<>();
        //获取所有的RequestMapping
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,
                HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
                    RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
                    String requestType = methodCondition.getMethods().stream().map(vo -> String.valueOf(vo)).collect(Collectors.joining(","));

                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    //  String requestUrl = SetUtils.first(patternsCondition.getPatterns());
                    String requestUrl = patternsCondition.getPatterns().stream().map(vo -> String.valueOf(vo)).collect(Collectors.joining(","));
                    ;
                    String controllerName = mappingInfoValue.getBeanType().toString();
                    String requestMethodName = mappingInfoValue.getMethod().getName();
                    Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
                    RequestToMethodItemDTO item = new RequestToMethodItemDTO(requestUrl, requestType, controllerName, requestMethodName,
                            methodParamTypes);
                    requestToMethodItemList.add(item);
                }
            }
        }

        return JsonResult.<List<RequestToMethodItemDTO>>builder().success(requestToMethodItemList).build();

    }


    @GetMapping("/listallApi")
    public JsonResult<List<ApiInfoDTO>> listallApi(Integer appId){
        try {

            List<ApiInfoDTO> apiInfoDTOS = Lists.newArrayList();

            ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
            applicationReqDTO.setAppId(appId);
            ResultDTO<ApplicationDTO> resultDTO = appFeginClient.getOne(applicationReqDTO);

            if (resultDTO.getSuccess()){
                ApplicationDTO applicationDTO = resultDTO.getData();

                StrBuilder sb = StrBuilder.create();
                Integer https = applicationDTO.getAppHttps();
                //是否https
                boolean httpEnable = ObjectUtil.equal("1", https);
                if (httpEnable) {
                    sb.append("https://");
                } else {
                    sb.append("http://");
                }
                sb.append(applicationDTO.getAppHost()).append(":").append(applicationDTO.getAppPort()).append("/").append(applicationDTO.getAppContext()).append("/v/vv/listallhandlers");

                String body = HttpUtil.createGet(sb.toString()).timeout(5000 * 2).execute().body();

                JsonResult<List<RequestToMethodItemDTO>> jsonResult = JSONObject.parseObject(body,new TypeReference<JsonResult<List<RequestToMethodItemDTO>>>(){});

                if (jsonResult.getSuccess())
                {
                    List<RequestToMethodItemDTO> methodItemDTOList = jsonResult.getData();

                    apiInfoDTOS  =  methodItemDTOList.stream().map(new Function<RequestToMethodItemDTO, ApiInfoDTO>() {
                        @Override
                        public ApiInfoDTO apply(RequestToMethodItemDTO requestToMethodItemDTO) {
                            String controllerName = requestToMethodItemDTO.getControllerName();
                            ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
                            apiInfoDTO.setApiUrl(requestToMethodItemDTO.getRequestUrl());
                            apiInfoDTO.setApiCode(StrUtil.sub(controllerName,controllerName.lastIndexOf(".") + 1,controllerName.length() - 1) + "#" + requestToMethodItemDTO.getRequestMethodName());
                            return apiInfoDTO;
                        }
                    }).collect(Collectors.toList());

                }

            }

            return JsonResult.<List<ApiInfoDTO>>builder().success(apiInfoDTOS).build();

        } catch (Exception e) {
            log.error(e.getMessage());
        }


        return JsonResult.<List<ApiInfoDTO>>builder().failure().build();
    }

    @PostMapping("/listApi")
    @ApiOperation("listApi")
    public JsonResult<List<ApiInfoDTO>> listApi(@RequestBody JSONObject jsonObject) {

        try {
            Integer appId = jsonObject.getInteger("appId");
            String apiCode = jsonObject.getString("apiCode");
            ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
            applicationReqDTO.setAppId(appId);
            ResultDTO<ApplicationDTO> resultDTO = appFeginClient.getOne(applicationReqDTO);

            StringBuilder sb = new StringBuilder();

            if (resultDTO.getSuccess()) {
                ApplicationDTO applicationDTO = resultDTO.getData();
                Integer https = applicationDTO.getAppHttps();
                //是否https
                boolean httpEnable = ObjectUtil.equal("1", https);
                if (httpEnable) {
                    sb.append("https://");
                } else {
                    sb.append("http://");
                }
                sb.append(applicationDTO.getAppHost()).append(":").append(applicationDTO.getAppPort()).append("/").append(applicationDTO.getAppContext()).append("/v/vv/apilist");
                String body = HttpUtil.createGet(sb.toString()).timeout(5000 * 2).execute().body();
                if (StrUtil.isNotBlank(body)) {
                    ResultDTO<List<ApiInfoDTO>> listResultDTO = JSON.parseObject(body, new TypeReference<ResultDTO<List<ApiInfoDTO>>>() {
                    });
                    if (listResultDTO.getSuccess()) {
                        List<ApiInfoDTO> apiInfoDTOList = listResultDTO.getData().stream()
                                .filter(apiInfoDTO -> !StrUtil.isBlank(apiInfoDTO.getApiUrl()))
                                .filter(apiInfoDTO -> StrUtil.isNotBlank(apiCode) ? (apiCode.equalsIgnoreCase(apiInfoDTO.getApiCode()) ? true : false) : true)
                                .collect(Collectors.toList());
                        return JsonResult.<List<ApiInfoDTO>>builder().success(apiInfoDTOList).build();
                    }
                }
            }
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage());
        }

        return JsonResult.<List<ApiInfoDTO>>builder().failure("获取接口基础信息失败").build();
    }
}
