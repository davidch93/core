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
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
 * @version 2.0.0
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.security.jwt")
@EnableConfigurationProperties(JwtSetting.class)
public class WebSecurityConfigurerSupport extends WebSecurityConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private JwtSetting jwtSetting;
    private SecurityDetailsService securityDetailsService;
    private TokenExtractor jwtTokenExtractor;
    private TokenFactory jwtTokenFactory;
    private ObjectMapper objectMapper;

    public WebSecurityConfigurerSupport(AuthenticationManager authenticationManager,
                                        AjaxAuthenticationProvider ajaxAuthenticationProvider,
                                        JwtAuthenticationProvider jwtAuthenticationProvider, JwtSetting jwtSetting,
                                        SecurityDetailsService securityDetailsService, TokenExtractor jwtTokenExtractor,
                                        TokenFactory jwtTokenFactory, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.ajaxAuthenticationProvider = ajaxAuthenticationProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtSetting = jwtSetting;
        this.securityDetailsService = securityDetailsService;
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtTokenFactory = jwtTokenFactory;
        this.objectMapper = objectMapper;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(ajaxAuthenticationProvider)
                .authenticationProvider(jwtAuthenticationProvider);
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

		configureAuthorization(authorizeRequests).anyRequest().authenticated()
        	.and()
				.addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
    }

    /**
     * Method to build login processing filter.
     *
     * @return {@link AjaxLoginProcessingFilter}
     */
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(
                WebSecuritySupport.JWT_BASED_LOGIN_ENTRY_POINT.getValue(), authenticationSuccessHandler(),
                authenticationFailureHandler());
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Method to build JWT Token authentication processing filter.
     *
     * @return {@link JwtTokenAuthenticationProcessingFilter}
     */
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() {
        List<String> pathsToSkip = requestAntPathsToPermitAll();
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip,
                WebSecuritySupport.BASED_AUTH_ENTRY_POINT.getValue());
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
                authenticationFailureHandler(), jwtTokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Method to get default authentication entry point.
     *
     * @return {@link RestAuthenticationEntryPoint}
     */
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint(securityDetailsService, objectMapper, jwtSetting);
    }

    /**
     * Method to get default access denied handler.
     *
     * @return {@link RestAccessDeniedHandler}
     */
    protected AccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler(securityDetailsService, objectMapper, jwtSetting);
    }

    /**
     * Method to get default authentication success handler.
     *
     * @return {@link AjaxAwareAuthenticationSuccessHandler}
     */
    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AjaxAwareAuthenticationSuccessHandler(securityDetailsService, objectMapper, jwtTokenFactory);
    }

    /**
     * Method to get default authentication failure handler.
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
     * Method to add custom authorization configurations. By default do nothing.
     *
     * @param authorizeRequests {@link ExpressionInterceptUrlRegistry}
     * @return Custom {@link ExpressionInterceptUrlRegistry}
     */
    protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(
            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        return authorizeRequests;
    }
}
