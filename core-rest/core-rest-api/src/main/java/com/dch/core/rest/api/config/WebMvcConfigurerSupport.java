package com.dch.core.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Defines callback methods to customize the Java-based configuration for
 * Spring MVC enabled via {@code @EnableWebMvc}. <br/>
 * This implementation used for create custom {@link WebMvcConfigurer#addResourceHandlers}
 * for adding swagger resources.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 */
@ComponentScan("com.dch.core.rest.api")
public class WebMvcConfigurerSupport implements WebMvcConfigurer {

    /**
     * Bean of {@link LocaleResolver}.
     *
     * @return {@link SessionLocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
