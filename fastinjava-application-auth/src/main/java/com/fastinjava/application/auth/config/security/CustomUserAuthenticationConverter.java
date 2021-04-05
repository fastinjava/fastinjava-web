package com.fastinjava.application.auth.config.security;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    Logger logger = LoggerFactory.getLogger(CustomUserAuthenticationConverter.class);

    @Resource
    private ClientDetailsService clientDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap jwtBody = new LinkedHashMap();
        jwtBody.put("username", authentication.getName());
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            jwtBody.put("username", userDetails.getUsername());
            jwtBody.put("authorities", userDetails.getAuthorities());
        } else {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authentication.getName());
            jwtBody.put("authorities", clientDetails.getAuthorities());
        }
        Map<String, ?> stringMap = super.convertUserAuthentication(authentication);
        logger.info("stringMap = {}", JSONUtil.toJsonPrettyStr(stringMap));
        return stringMap;
    }


}
