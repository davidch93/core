package com.dch.core.rest.reactive.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Defines callback methods to customize the Java-based configuration for
 * Spring MVC enabled via {@code @EnableWebFlux}.<br>
 * This implementation used for create custom {@link WebFluxConfigurer#addResourceHandlers}
 * for adding swagger resources.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see org.springframework.web.reactive.config.WebFluxConfigurer
 */
@ComponentScan("com.cdh.core.rest.reactive")
public class WebFluxConfigurerSupport implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
