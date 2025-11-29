package com.example.ventasbolivar.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

/**
 * Personalizacion del Swagger.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
    title = "Electrónica Bolívar - Springboot",
    version = "1.0.0",
    description = "Documentación oficial del sistema.",
    license = @License(
    name = "Apache 2.0",
    url = "https://www.apache.org/licenses/LICENSE-2.0"
    )
    )
    )
public class SwaggerConfig {
}
