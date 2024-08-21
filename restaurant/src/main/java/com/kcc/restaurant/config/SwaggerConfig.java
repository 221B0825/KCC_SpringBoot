package com.kcc.restaurant.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(title = "Restaurant Service API", description = "Spring Boot Rest API", version = "v1.0.0")

)

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customOpenAPI() {
        String[] paths = {"/restaurant"};
        return GroupedOpenApi.builder().group("레스토랑").pathsToMatch(paths).build();
    }
}
