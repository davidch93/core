package com.dch.core.security.oauth2.config;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Web security configurer that extends {@link WebSecurityConfigurerAdapter} to
 * provide security configuration support.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.security.oauth2")
public class WebSecurityConfigurerSupport extends WebSecurityConfigurerAdapter {

    protected final SecurityDetailsService securityDetailsService;

    public WebSecurityConfigurerSupport(SecurityDetailsService securityDetailsService) {
        this.securityDetailsService = securityDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
			.csrf().disable()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
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
