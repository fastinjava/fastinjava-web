package com.fastinjava.application.auth.web.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.auth.client.UserFeginClient;
import com.fastinjava.application.auth.constans.AuthConstant;
import com.fastinjava.framework.common.res.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v/auth")
public class AuthController {

    @Value("${server.port}")
    private String port;

    private String host = "localhost";

    @Value("${server.servlet.context-path}")
    private String path;

    @Resource
    private UserFeginClient userFeginClient;


    @GetMapping("/test")
    public JsonResult<String> test() {
        return JsonResult.<String>builder().success("测试接口").build();
    }


    @PostMapping("/login")
    public JsonResult<JSONObject> login(@RequestBody JSONObject jsonObject) {

        String clientId = jsonObject.getString("clientId");
        String clientSecret = jsonObject.getString("clientSecret");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        Assert.notEmpty(clientId);
        Assert.notEmpty(clientSecret);
        Assert.notEmpty(username);
        Assert.notEmpty(password);

        try {
            HttpResponse response = HttpUtil.createPost(accessUrl())
                    .header(AuthConstant.authorization, httpbasic(clientId, clientSecret))
                    .form(
                            new JSONObject()
                                    .fluentPut(AuthConstant.GRANT_TYPE, AuthConstant.GRANT_TYPE_PASSWORD)
                                    .fluentPut("username", username)
                                    .fluentPut("password", password)
                    )
                    .timeout(5000).execute();
            if (response.isOk()) {
                String body = response.body();
                JSONObject accessObj = JSONObject.parseObject(body);
                log.info(JSONUtil.toJsonPrettyStr(accessObj));

                ResultDTO<List<MenuNodeDTO>> listResultDTO = userFeginClient.getUserMenus(new JSONObject().fluentPut("username", username).fluentPut("clientId", clientId));
                if (listResultDTO.getSuccess()) {
                    List<MenuNodeDTO> menuNodeDTOList = listResultDTO.getData();
                    return JsonResult.<JSONObject>builder().success(
                            new JSONObject().fluentPut("accessObj", accessObj).fluentPut("userMenus", menuNodeDTOList)
                    ).build();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return JsonResult.<JSONObject>builder().failure("登陆失败").build();

    }

    private static String httpbasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);

    }

    private String accessUrl() {
        StrBuilder urlSb = StrBuilder.create("http://");
        urlSb.append(host);
        urlSb.append(":");
        urlSb.append(port);
        urlSb.append("/");
        urlSb.append(path);
        urlSb.append("/oauth/token");
        return urlSb.toString();
    }


}
