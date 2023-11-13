package com.acme.learning.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI Configuration.
 * <p>
 * This class is responsible for configuring the OpenAPI.
 * It creates the OpenAPI object and adds the info and security scheme.
 * </p>
 */
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        final String securitySchemeName = "bearerAuth";

        // Create OpenAPI object
        var openApi = new OpenAPI();

        // Add info
        openApi.info(new Info().title("ACME Learning Platform API")
                        .description(
                        "ACME Learning Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ACME Learning Platform Wiki Documentation")
                        .url("https://acme-learning-platform.wiki.github.org/docs"));

        // Add security scheme
        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

        // Return OpenAPI object with info and security scheme
        return openApi;
    }
}
