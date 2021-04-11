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
    public JsonResult<JSONArray> javaLearnMap() {
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
                        .fluentPut("label", "Java基础").fluentPut("value", "1").fluentPut("desc","JavaSE基础是Java中级程序员的起点，是帮助你从小白到懂得编程的必经之路。在Java基础板块中有6个子模块的学习：基础语法，可帮助你建立基本的编程逻辑思维；面向对象，以对象方式去编写优美的Java程序；集合，后期开发中存储数据必备技术；IO，对磁盘文件进行读取和写入基础操作；多线程与并发，提高程序效率；异常，编写代码逻辑更加健全；网络编程，应用服务器学习基础，完成数据的远程传输。学习该阶段，可以完成一些简单的管理系统、坦克大战游戏、QQ通信等。")
                        .fluentPut("children", new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Java基础语法").fluentPut("value", "1-1").fluentPut("desc","Java基础语法是更好学习Java高级特性和后阶技术的前提，基础语法包括变量、表达式、语句、方法、常用API等。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "面向对象").fluentPut("value", "1-2").fluentPut("desc","Java是面向对象的语言，将一切可以描述的事物都抽象成对象，将属性、方法封装到对象中，从而易于操作，是一种思想的体现。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "集合").fluentPut("value", "1-3").fluentPut("desc","Java中的集合是用于存储数据的容器，是在编程时使用频率极高的知识点，根据底层结构的不同可以分为List、Set、Map等。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "IO").fluentPut("value", "1-4").fluentPut("desc","Java的IO是对磁盘和内存文件的操作，主要包括：文件读写、标准设备输出等。Java中IO是以流为基础进行输入输出的。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "线程与并发").fluentPut("value", "1-5").fluentPut("desc","线程编程是Java中高级技术点，Java给多线程编程提供了内置的支持，一个进程中可以并发多个线程，每条线程并行执行不同的任务。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "异常").fluentPut("value", "1-6").fluentPut("desc","Java中的异常（Exception）是一个在程序执行期间发生的事件，它中断正在执行的程序的正常指令流。可以使程序更加规范与健全。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "网络编程").fluentPut("value", "1-7").fluentPut("desc","Java内置API可以完成网络之间的通信，而网络编程也是学习各种服务器的网络基础，开发中手写网编代码机会比较少，但是众多服务底层都是基于网编的。"))
                        )
        );

        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "数据库").fluentPut("value", "2").fluentPut("desc","数据库不仅仅是Java开发工程师的必学课程，也是其他语言都需要掌握的技能。用于对交互过程中客户的数据进行存储。该板块包括关系型数据库和非关系型数据库。例如：MySQL、oracle、redis、MongoDB等。数据库学习完毕后，可以将数据存储到数据库中，也可以通过SQL语句从数据库中查询数据，结合Java项目可以实现动态站点的数据的保存。")
                        .fluentPut("children", new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "MySQL").fluentPut("value", "2-1").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Oracle").fluentPut("value", "2-2").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "JDBC").fluentPut("value", "2-3").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "C3P0").fluentPut("value", "2-4").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Druid").fluentPut("value", "2-5").fluentPut("desc",""))
                        )
        );

        result.fluentAdd(
                new JSONObject() .fluentPut("label", "前端技术").fluentPut("value", "3").fluentPut("desc","Javaweb阶段包括前端、数据库和动态网页。Javaweb是互联网项目的入门课程，是学习后面高进阶课程的基础。首先，我们先看一下前端板块。该板块主要包括如下几个模块：HTML5，网页制作标记语言；CSS，对HTML制作网页进行美化；JavaScript，嵌入在页面中的脚本语言，具备逻辑性；Vue，前端框架，简化了与服务器端交互的操作，用户良好的交互体验是必不可少的。学习前端技术后，可以完成类似京东、淘宝的前端工程的编写。")
                        .fluentPut("children",new JSONArray()
                .fluentAdd(new JSONObject())
                )
        );

        /*5*/
        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "编程强化").fluentPut("value", "5").fluentPut("desc","编程强化是对解决实际问题方面做一个深入的了解和应用，是对JavaSE基础的加强，对后期自动以框架和对一些服务框架的底层理解做支撑。编程强化板块主要包括如下几个模块：多线程高级、涉及线程内存、线程通信等；JVM优化，对JVM底层进行调优来提高项目执行效率；NIO，同步非阻塞IO来提高效率。学习该阶段，可以对原有项目进行优化从而使程序更快更稳定。")
                        .fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "设计模式").fluentPut("value", "5-1").fluentPut("desc", "设计模式是开发人员在开发过程中面临的一般问题的解决方案。众多框架和服务的源码都大量使用了设计模式，在自定义框架时也会使用设计模式。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "JVM优化").fluentPut("value", "5-2").fluentPut("desc", "大型项目在业务健全后，会对性能有较高的要求，除了使用性能更好的中间件和逻辑优化之外，更高级的需要对JVM进行优化。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "数据结构算法").fluentPut("value", "5-3").fluentPut("desc", "算法是对某类问题解决的方式，它代表着用系统的方法描述解决问题的策略机制。在大数据，人工智能时代，算法显得更加重要。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "多线程高级").fluentPut("value", "5-4").fluentPut("desc", "多线程高级是在JavaSE线程基础上，对线程的高级特性进行讲解，例如：线程内存、线程通信等，服务框架中几乎都使用到了多线程技术。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "MINA").fluentPut("value", "5-5").fluentPut("desc", "Apache Mina是一个能够帮助用户开发高性能和高伸缩性网络应用程序的框架。它通过Java nio技术提供了抽象的、事件驱动的、异步的API。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Netty").fluentPut("value", "5-6").fluentPut("desc", "Netty是java开源框架，提供异步的、事件驱动的网络应用程序框架和工具，用以快速开发高性能、高可靠性的网络服务器和客户端程序。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "NIO").fluentPut("value", "5-7").fluentPut("desc", "NIO相对于传统IO，我们称之为同步非阻塞IO，通过多路复用技术，可减少资源创建与销毁的消耗，提高性能，Netty服务器使用的就是NIO技术。"))
                )
        );

        /*6*/
        result.fluentAdd(
                new JSONObject()
                        .fluentPut("label", "软件项目管理").fluentPut("value", "6").fluentPut("children", null)
                        .fluentPut("children", new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Maven").fluentPut("value", "6-1").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "SVN").fluentPut("value", "6-2").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Git").fluentPut("value", "6-3").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "码云").fluentPut("value", "6-4").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Jenkins").fluentPut("value", "6-5").fluentPut("desc",""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Sonar").fluentPut("value", "6-6").fluentPut("desc",""))
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
        /*8*/
        result.fluentAdd(new JSONObject()
                .fluentPut("label", "分布式架构").fluentPut("value", "8").fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Dubbo").fluentPut("value", "8-1").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Zookeeper").fluentPut("value", "8-2").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "SpringBoot").fluentPut("value", "8-3").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "SpringCloud").fluentPut("value", "8-4").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Eureka").fluentPut("value", "8-5").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Consul").fluentPut("value", "8-6").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Hystrix").fluentPut("value", "8-7").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Zuul").fluentPut("value", "8-8").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Gateway").fluentPut("value", "8-9").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Skywalking").fluentPut("value", "8-10").fluentPut("desc", ""))
                                .fluentAdd(new JSONObject().fluentPut("label", "Pinpoint").fluentPut("value", "8-11").fluentPut("desc", ""))
                )

        );

        result.fluentAdd(
                new JSONObject().fluentPut("label", "服务器中间件").fluentPut("value", "9").fluentPut("children",
                        new JSONArray()
                                .fluentAdd(new JSONObject().fluentPut("label", "Tomcat").fluentPut("value", "9-1").fluentPut("desc", "Tomcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用服务器，Tomcat结合Nginx进行集群搭建，是大型互联网项目常用选择。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Jetty").fluentPut("value", "9-2").fluentPut("desc", "Jetty 是一个开源的servlet容器，它为基于Java的web容器，Jetty是使用Java语言编写的，它的API以一组JAR包的形式发布。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Nginx").fluentPut("value", "9-3").fluentPut("desc", "Nginx (engine x) 是一个高性能的http和反向代理web服务器，同时也可以充当邮件服务器，我们常常使用Nginx进行反向代理进行集群搭建。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Linux系统").fluentPut("value", "9-4").fluentPut("desc", "Linux是一套免费使用和自由传播的且性能稳定的多用户网络操作系统，正式基于这个优点，项目在部署时首选该系统。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "CentOS").fluentPut("value", "9-5").fluentPut("desc", "CentOS是Linux发行版之一，它是来自于Red Hat依照开放源代码规定释出的源代码所编译而成。也是我们常常使用的版本。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Ubuntu").fluentPut("value", "9-6").fluentPut("desc", "Ubuntu（又称乌班图）是一个以桌面应用为主的开源GNU/Linux操作系统，因为桌面应用丰富，也会作为部分人的办公系统使用。"))
                                .fluentAdd(new JSONObject().fluentPut("label", "Virtualbox").fluentPut("value", "9-8").fluentPut("desc", "VirtualBox 是一款开源虚拟机软件，号称是最强的免费虚拟机软件，可虚拟的系统包括Windows、Mac OS X、Linux、甚至Android等。"))
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
    public JsonResult<List<ApiInfoDTO>> listallApi(Integer appId) {
        try {

            List<ApiInfoDTO> apiInfoDTOS = Lists.newArrayList();

            ApplicationReqDTO applicationReqDTO = new ApplicationReqDTO();
            applicationReqDTO.setAppId(appId);
            ResultDTO<ApplicationDTO> resultDTO = appFeginClient.getOne(applicationReqDTO);

            if (resultDTO.getSuccess()) {
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

                JsonResult<List<RequestToMethodItemDTO>> jsonResult = JSONObject.parseObject(body, new TypeReference<JsonResult<List<RequestToMethodItemDTO>>>() {
                });

                if (jsonResult.getSuccess()) {
                    List<RequestToMethodItemDTO> methodItemDTOList = jsonResult.getData();

                    apiInfoDTOS = methodItemDTOList.stream().map(new Function<RequestToMethodItemDTO, ApiInfoDTO>() {
                        @Override
                        public ApiInfoDTO apply(RequestToMethodItemDTO requestToMethodItemDTO) {
                            String controllerName = requestToMethodItemDTO.getControllerName();
                            ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
                            apiInfoDTO.setApiUrl(requestToMethodItemDTO.getRequestUrl());
                            apiInfoDTO.setApiCode(StrUtil.sub(controllerName, controllerName.lastIndexOf(".") + 1, controllerName.length() - 1) + "#" + requestToMethodItemDTO.getRequestMethodName());
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
