package com.dch.core.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
@ComponentScan("com.dch.core.rest")
public class WebMvcConfigurerSupport implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
