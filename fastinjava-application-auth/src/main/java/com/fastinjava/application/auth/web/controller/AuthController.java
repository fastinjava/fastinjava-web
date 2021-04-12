package com.fastinjava.application.auth.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.fastdevelopinjava.framework.system.api.dto.MenuNodeDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.auth.client.OauthInfoFeginClient;
import com.fastinjava.application.auth.client.UserFeginClient;
import com.fastinjava.framework.common.res.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
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

    @Resource
    private OauthInfoFeginClient oauthInfoFeginClient;

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
            OauthDetailReqDTO oauthDetailReqDTO = new OauthDetailReqDTO();
            oauthDetailReqDTO.setClientId(clientId);
            ResultDTO<OauthDetailsDTO> clientDetailResultDTO = oauthInfoFeginClient.getOne(oauthDetailReqDTO);
            ResultDTO<JSONObject> clientDetailExtResultDTO = oauthInfoFeginClient.oauthClientExtinfo(new JSONObject().fluentPut("clientId", clientId));
            UserReqDTO userReqDTO = new UserReqDTO();
            userReqDTO.setUserName(username);
            ResultDTO<UserDTO> userDTOResultDTO = userFeginClient.selectOne(userReqDTO);


            Assert.isTrue((
                            clientDetailResultDTO.getSuccess() &&
                                    ObjectUtil.isNotEmpty(clientDetailResultDTO.getData()) &&
                                    clientDetailExtResultDTO.getSuccess() &&
                                    ObjectUtil.isNotEmpty(clientDetailExtResultDTO.getData()) &&
                                    userDTOResultDTO.getSuccess() &&
                                    ObjectUtil.isNotEmpty(userDTOResultDTO.getData())
                    ), "客户端调用接口失败"
            );

            OauthDetailsDTO oauthDetailsDTO = clientDetailResultDTO.getData();
            JSONObject oauthDetailsExtDTO = clientDetailExtResultDTO.getData();
            UserDTO userDTO = userDTOResultDTO.getData();

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Assert.isTrue(bCryptPasswordEncoder.matches(clientSecret,oauthDetailsDTO.getClientSecret()),"客户端密码错误");
            Assert.isTrue(bCryptPasswordEncoder.matches(password,userDTO.getPassword()),"用户密码错误");

            String accessKey = oauthDetailsExtDTO.getString("accessKey");
            String accessSecret = oauthDetailsExtDTO.getString("accessSecret");
            String privateKeyBase64 = oauthDetailsExtDTO.getString("privateKeyBase64");
            String publicKeyBase64 = oauthDetailsExtDTO.getString("publicKeyBase64");

            RSA rsa = new RSA(privateKeyBase64, publicKeyBase64);
            PrivateKey privateKey = rsa.getPrivateKey();
            PublicKey publicKey = rsa.getPublicKey();

            long expireAt = DateUtil.currentSeconds() + (5 * 60);

            log.info("expireAt timestamp/s = {},datetime = {}",expireAt,DateUtil.dateNew(
                    new Date(expireAt * 1000)
            ));

            Jwt jwt = JwtHelper.encode(
                    JSONUtil.toJsonStr(
                           new JSONObject()
                                   .fluentPut("email",userDTO.getUserEmail())
                                   .fluentPut("userId",userDTO.getUserId())
                                   .fluentPut("userNickName",userDTO.getUserNickName())
                                    .fluentPut("username",username)
                                    .fluentPut("loginClient",clientId)
                                    .fluentPut("exp",expireAt)
                                    .fluentPut("expDatetime",
                                            DateUtil.formatDateTime(DateUtil.dateNew(
                                                    new Date(expireAt * 1000)
                                            ))
                                    )
                    ) ,
                    new RsaSigner((RSAPrivateKey) privateKey));


            JSONObject accessObj = new JSONObject();

            String access_token = jwt.getEncoded();
            accessObj.fluentPut("access_token",access_token);
            accessObj.fluentPut("jti", IdUtil.fastSimpleUUID());
            log.info(JSONUtil.toJsonPrettyStr(accessObj));

            ResultDTO<List<MenuNodeDTO>> listResultDTO = userFeginClient.getUserMenus(new JSONObject().fluentPut("username", username).fluentPut("clientId", clientId));
            return JsonResult.<JSONObject>builder().success(
                    new JSONObject().fluentPut("accessObj", accessObj).fluentPut("userMenus", listResultDTO.getData())
            ).build();
        } catch (Exception e) {
            log.error("授权失败 error = {} " ,e.getMessage());
            return JsonResult.<JSONObject>builder().failure(e.getMessage()).build();
        }


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
