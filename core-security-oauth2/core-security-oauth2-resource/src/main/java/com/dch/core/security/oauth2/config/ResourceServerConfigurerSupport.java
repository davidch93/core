package com.dch.core.security.oauth2.config;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.oauth2.RestAccessDeniedHandler;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * {@link TokenStore}. This class extends
 * {@link ResourceServerConfigurerAdapter} to provide resource server
 * configuration support.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 13, 2017
 * @since 1.0.0-SNAPSHOT
 */
@ComponentScan("com.cnx.core.security.oauth2")
public class ResourceServerConfigurerSupport extends ResourceServerConfigurerAdapter {

    @Autowired
    protected SecurityDetailsService securityDetailsService;

    @Autowired
    protected TokenExtractor tokenExtractor;

    @Autowired
    protected ResourceSetting resourceSettings;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // @formatter:off
		resources
			.authenticationEntryPoint(authenticationEntryPoint())
			.accessDeniedHandler(accessDeniedHandler())
			.expressionHandler(expressionHandler())
			.resourceId(resourceSettings.getResourceId())
			.tokenExtractor(tokenExtractor);
		// @formatter:on
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
			.cors().and()
			.authorizeRequests()
				.antMatchers(WebSecuritySupport.OAUTH2_BASED_CHECK_ENTRY_POINT.getValue()).permitAll()
				.antMatchers(WebSecuritySupport.OAUTH2_BASED_REVOKE_ENTRY_POINT.getValue()).permitAll();

		authorizeRequests = configureAuthorization(authorizeRequests)
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
        return new RestAccessDeniedHandler();
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
     * @param authorizeRequests {@link ExpressionUrlAuthorizationConfigurer}&lt;{@link HttpSecurity}&gt;
     *                                                                      .{@link ExpressionInterceptUrlRegistry}
     * @return {@link ExpressionUrlAuthorizationConfigurer}&lt;{@link HttpSecurity}&gt;
     * .{@link ExpressionInterceptUrlRegistry}
     * @throws Exception if there are errors during configure authorization.
     */
    protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests)
            throws Exception {
        return authorizeRequests;
    }
}
