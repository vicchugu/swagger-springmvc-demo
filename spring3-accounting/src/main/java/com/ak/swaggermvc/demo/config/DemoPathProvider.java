package com.ak.swaggermvc.demo.config;

import com.mangofactory.swagger.core.SwaggerPathProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;

public class DemoPathProvider implements SwaggerPathProvider {
    private SwaggerPathProvider defaultSwaggerPathProvider;
    @Autowired
    private ServletContext servletContext;


    @Override
    public String getApiResourcePrefix() {
        return defaultSwaggerPathProvider.getApiResourcePrefix();
    }

    public String getAppBasePath() {
        return UriComponentsBuilder
                .fromHttpUrl("http://localhost:9080/")
                .path(servletContext.getContextPath())
                .build()
                .toString();
    }

    @Override
    public String sanitizeRequestMappingPattern(String requestMappingPattern) {
        return defaultSwaggerPathProvider.sanitizeRequestMappingPattern(requestMappingPattern);
    }

    @Override
    public String getSwaggerDocumentationBasePath() {
        return UriComponentsBuilder
                .fromHttpUrl(getAppBasePath())
                .pathSegment("api-docs/")
                .build()
                .toString();
    }

    public void setDefaultSwaggerPathProvider(SwaggerPathProvider defaultSwaggerPathProvider) {
        this.defaultSwaggerPathProvider = defaultSwaggerPathProvider;
    }
}
