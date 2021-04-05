package com.fastinjava.application.auth.config.security;

import com.fastinjava.application.auth.service.impl.ClientDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    //授权管理器
    @Autowired
    AuthenticationManager authenticationManager;

    //token存储方式
    @Autowired
    TokenStore tokenStore;

    //jwt令牌转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    //用户,UserDetailsService 其具体实现子类我们需要注入到spring容器
    @Qualifier("UserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    //客户端实现类
    @Autowired
    private ClientDetailServiceImpl clientDetailServiceImpl;

    //自定义用户信息转换类
    @Autowired
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    //读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties() {
        return new KeyProperties();
    }

    //客户端配置
    @Bean
    public ClientDetailsService clientDetails() {
        return clientDetailServiceImpl;
    }

    //客户端
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }


    /**
     * 读取密钥
     *
     * @param customUserAuthenticationConverter
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory
                (keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getSecret().toCharArray())
                .getKeyPair(keyProperties.getKeyStore().getAlias(), keyProperties.getKeyStore().getPassword().toCharArray());
        converter.setKeyPair(keyPair);
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }


    //授权服务器端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)//认证管理器
                .tokenStore(tokenStore)//令牌存储
                .userDetailsService(userDetailsService);//用户信息service
    }

    //授权服务器的安全配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

}
