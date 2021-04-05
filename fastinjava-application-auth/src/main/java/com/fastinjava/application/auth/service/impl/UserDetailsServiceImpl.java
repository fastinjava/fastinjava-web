package com.fastinjava.application.auth.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserDTO;
import com.fastdevelopinjava.framework.ucenter.api.dto.UserReqDTO;
import com.fastdevelopinjava.framework.ucenter.common.res.ResultDTO;
import com.fastinjava.application.auth.client.UserFeginClient;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Slf4j
@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private UserFeginClient userFeginClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notEmpty(username);
        log.info("UserDetailsServiceImpl#loadUserByUsername username = {} ", username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtil.isEmpty(authentication))
        {
            ClientDetails clientDetail = clientDetailsService.loadClientByClientId(username);
            if (ObjectUtil.isNotEmpty(clientDetail))
            {
                Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
                grantedAuthorities.add(new SimpleGrantedAuthority("122"));
                return new User(username,
                        clientDetail.getClientSecret(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(grantedAuthorities.toArray(), ",")));
            }
        }

        UserReqDTO userReqDTO = new UserReqDTO();
        userReqDTO.setUserName(username);
        ResultDTO<UserDTO> resultDTO = userFeginClient.selectOne(userReqDTO);

        if (resultDTO.getSuccess() && ObjectUtil.isNotEmpty(resultDTO.getData()))
        {
            UserDTO userDTO = resultDTO.getData();

            Assert.isTrue(username.equalsIgnoreCase(userDTO.getUserName()));

            Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            grantedAuthorities.add(new SimpleGrantedAuthority("122"));
            UserDetails userDetails = new User(username,userDTO.getPassword(), grantedAuthorities);
            return userDetails;

        }

        return null;
    }
}
