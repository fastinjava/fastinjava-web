package com.fastinjava.application.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailReqDTO;
import com.fastdevelopinjava.framework.system.api.dto.OauthDetailsDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.auth.client.OauthInfoFeginClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientDetailServiceImpl implements ClientDetailsService {

    @Resource
    private OauthInfoFeginClient oauthInfoFeginClient;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {


        try {
            OauthDetailReqDTO oauthDetailReqDTO = new OauthDetailReqDTO();
            oauthDetailReqDTO.setClientId(clientId);
            ResultDTO<OauthDetailsDTO> resultDTO = oauthInfoFeginClient.getOne(oauthDetailReqDTO);

            if (resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData())) {
                return oauthDetailsDTO2ClientDetails(resultDTO.getData());
            }
        } catch (Exception e) {
            log.error("ClientDetailServiceImpl.loadClientByClientId error , errorMessage = {}", e.getMessage());
        }

        return null;
    }

    private  ClientDetails oauthDetailsDTO2ClientDetails (OauthDetailsDTO oauthDetailsDTO){
        BaseClientDetails baseClientDetails = new BaseClientDetails();

        String scope = oauthDetailsDTO.getScope();
        String id = oauthDetailsDTO.getClientId();
        String clientSecret = oauthDetailsDTO.getClientSecret();
        String authorizedGrantTypes = oauthDetailsDTO.getAuthorizedGrantTypes();
        String authorities = oauthDetailsDTO.getAuthorities();
        Integer accessTokenValidity = oauthDetailsDTO.getAccessTokenValidity();
        Integer refreshTokenValidity = oauthDetailsDTO.getRefreshTokenValidity();


        baseClientDetails.setClientId(id);
        if (StrUtil.isNotBlank(scope)) {
            baseClientDetails.setScope(Arrays.asList(scope.split(",")));
        }
        baseClientDetails.setClientSecret(clientSecret);

        if (StrUtil.isNotBlank(authorizedGrantTypes)) {
            baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(authorizedGrantTypes.split(",")));
        }

        if (StrUtil.isNotBlank(authorities)) {
            List<SimpleGrantedAuthority> grantedAuthorityList = Arrays.stream(authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            baseClientDetails.setAuthorities(grantedAuthorityList);
        }
        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);

        return baseClientDetails;
    }

}
