package com.dch.core.security.oauth2.config;

import com.dch.core.security.oauth2.RestAccessDeniedHandler;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * The server configuration that used to provide implementations of the client
 * details service and token services and to enable or disable certain aspects
 * of the mechanism globally. This default configuration using
 * {@link JdbcTokenStore}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
 * @since 1.0.0
 */
@ComponentScan("com.cnx.core.security.oauth2")
@EnableConfigurationProperties(AuthorizationSetting.class)
public class AuthorizationServerConfigurerSupport extends AuthorizationServerConfigurerAdapter {

    protected final AuthenticationManager authenticationManager;
    protected final DataSource dataSource;
    protected final UserDetailsService userDetailsService;
    protected final SecurityDetailsService securityDetailsService;
    protected final AuthorizationSetting authorizationSetting;

    public AuthorizationServerConfigurerSupport(AuthenticationManager authenticationManager, DataSource dataSource,
                                                UserDetailsService userDetailsService,
                                                SecurityDetailsService securityDetailsService,
                                                AuthorizationSetting authorizationSetting) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.securityDetailsService = securityDetailsService;
        this.authorizationSetting = authorizationSetting;
    }

    /**
     * Bean of JDBC Client Details Service.
     *
     * @return {@link JdbcClientDetailsService}
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * Bean of JDBC Token Store.
     *
     * @return {@link TokenStore}
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    /**
     * Method used to get default authentication entry point.
     *
     * @return {@link RestAuthenticationEntryPoint}
     */
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint(securityDetailsService);
    }

    /**
     * Method used to get default access denied handler.
     *
     * @return {@link RestAccessDeniedHandler}
     */
    protected AccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler(securityDetailsService);
    }

    /**
     * Method used to get custom token using token enhancer.
     *
     * @return {@link TokenEnhancerChain}
     */
    protected TokenEnhancerChain tokenEnhancerChain() {
        return new TokenEnhancerChain();
    }
}
