package com.dch.core.security.jwt.config;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.jwt.RestAccessDeniedHandler;
import com.dch.core.security.jwt.RestAuthenticationEntryPoint;
import com.dch.core.security.jwt.auth.ajax.AjaxAuthenticationProvider;
import com.dch.core.security.jwt.auth.ajax.AjaxAwareAuthenticationFailureHandler;
import com.dch.core.security.jwt.auth.ajax.AjaxAwareAuthenticationSuccessHandler;
import com.dch.core.security.jwt.auth.ajax.AjaxLoginProcessingFilter;
import com.dch.core.security.jwt.auth.token.JwtAuthenticationProvider;
import com.dch.core.security.jwt.auth.token.JwtTokenAuthenticationProcessingFilter;
import com.dch.core.security.jwt.auth.token.SkipPathRequestMatcher;
import com.dch.core.security.jwt.auth.token.extractor.TokenExtractor;
import com.dch.core.security.jwt.model.token.JwtTokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Web security configurer that extends {@link WebSecurityConfigurerAdapter} to
 * provide security configuration support.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
@ComponentScan("com.dch.core.security.jwt")
public class WebSecurityConfigurerSupport extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtSetting jwtSetting;

    @Autowired
    private JwtTokenFactory tokenFactory;

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
			.cors().and()
			.csrf().disable()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
				.accessDeniedHandler(accessDeniedHandler())
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers(WebSecuritySupport.JWT_BASED_LOGIN_ENTRY_POINT.getValue()).permitAll()
					.antMatchers(WebSecuritySupport.JWT_BASED_REFRESH_ENTRY_POINT.getValue()).permitAll();

		authorizeRequests = configureAuthorization(authorizeRequests);

		authorizeRequests.anyRequest().authenticated()
        	.and()
				.addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
    }

    /**
     * Method used to build login processing filter.
     *
     * @return {@link AjaxLoginProcessingFilter}
     * @throws Exception
     */
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(
                WebSecuritySupport.JWT_BASED_LOGIN_ENTRY_POINT.getValue(), authenticationSuccessHandler(),
                authenticationFailureHandler());
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Method used to build JWT Token authentication processing filter.
     *
     * @return {@link JwtTokenAuthenticationProcessingFilter}
     * @throws Exception
     */
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = requestAntPathsToPermitAll();
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip,
                WebSecuritySupport.BASED_AUTH_ENTRY_POINT.getValue());
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
                authenticationFailureHandler(), tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Method used to get default authentication entry point.
     *
     * @return {@link RestAuthenticationEntryPoint}
     */
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint(securityDetailsService, objectMapper, jwtSetting);
    }

    /**
     * Method used to get default access denied handler.
     *
     * @return {@link RestAccessDeniedHandler}
     */
    protected AccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler(securityDetailsService, objectMapper, jwtSetting);
    }

    /**
     * Method used to get default authentication success handler.
     *
     * @return {@link AjaxAwareAuthenticationSuccessHandler}
     */
    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AjaxAwareAuthenticationSuccessHandler(securityDetailsService, objectMapper, tokenFactory);
    }

    /**
     * Method used to get default authentication failure handler.
     *
     * @return {@link AjaxAwareAuthenticationFailureHandler}
     */
    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return new AjaxAwareAuthenticationFailureHandler(securityDetailsService, objectMapper, jwtSetting);
    }

    /**
     * Request paths to be given permitAll() access.
     *
     * @return {@link List}&lt;{@link String}&gt; Array of ant paths.
     */
    protected List<String> requestAntPathsToPermitAll() {
        return Arrays.asList(WebSecuritySupport.JWT_BASED_LOGIN_ENTRY_POINT.getValue(),
                WebSecuritySupport.JWT_BASED_REFRESH_ENTRY_POINT.getValue());
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
