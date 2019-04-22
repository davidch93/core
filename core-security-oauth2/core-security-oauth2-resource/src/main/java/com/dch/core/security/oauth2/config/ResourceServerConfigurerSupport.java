package com.dch.core.security.oauth2.config;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.oauth2.RestAccessDeniedHandler;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * The server configuration that protects resources from authorization server,
 * capable of accepting and responding to protected resource requests using
 * {@link TokenStore}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.security.oauth2")
@EnableConfigurationProperties(ResourceSetting.class)
public class ResourceServerConfigurerSupport extends ResourceServerConfigurerAdapter {

    protected final SecurityDetailsService securityDetailsService;
    protected final TokenExtractor tokenExtractor;
    protected final ResourceSetting resourceSettings;

    public ResourceServerConfigurerSupport(SecurityDetailsService securityDetailsService,
                                           TokenExtractor tokenExtractor, ResourceSetting resourceSettings) {
        this.securityDetailsService = securityDetailsService;
        this.tokenExtractor = tokenExtractor;
        this.resourceSettings = resourceSettings;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .expressionHandler(expressionHandler())
                .resourceId(resourceSettings.getResourceId())
                .tokenExtractor(tokenExtractor);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
			.cors().and()
			.authorizeRequests()
				.antMatchers(WebSecuritySupport.OAUTH2_BASED_CHECK_ENTRY_POINT.getValue()).permitAll()
				.antMatchers(WebSecuritySupport.OAUTH2_BASED_REVOKE_ENTRY_POINT.getValue()).permitAll();

		configureAuthorization(authorizeRequests)
				.anyRequest().authenticated();
		// @formatter:on
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
     * Method used to generate expression handler.
     *
     * @return {@link OAuth2WebSecurityExpressionHandler}
     */
    protected SecurityExpressionHandler<FilterInvocation> expressionHandler() {
        return new OAuth2WebSecurityExpressionHandler();
    }

    /**
     * Method used to add custom authorization configurations. By default do
     * nothing.
     *
     * @param authorizeRequests {@link ExpressionInterceptUrlRegistry}
     * @return Custom {@link ExpressionInterceptUrlRegistry}
     */
    protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        return authorizeRequests;
    }
}
