package com.sp.oauth2Demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Qualifier("authenticationManagerBean")
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory().withClient("finpal").secret(passwordEncoder.encode("finpal@123"))
//                .scopes("read").authorizedGrantTypes("password").and()
//                .withClient("jobportal").secret(passwordEncoder.encode("job123"))
//                .scopes("read").authorizedGrantTypes("authorization_code").redirectUris("http://localhost:8084");

        clients.inMemory().withClient("jobportal").secret(passwordEncoder.encode("2222"))
                .accessTokenValiditySeconds(8000).authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write", "info");

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }


}
