package com.sskorupski.learn.springboot.config.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                // Handle incoming request that match the URI
                .addResourceHandler("/static/**")
                // The "folders" where the resources will be looked up
                .addResourceLocations("/resources/static")
                // How long a resource will be kept in the web server cache
                .setCachePeriod(3600)
                // use multiple resolver, cache their results
                .resourceChain(true)
                // Tries to find a resource under the given locations matching to the request path.
                .addResolver(new PathResourceResolver());
    }
}