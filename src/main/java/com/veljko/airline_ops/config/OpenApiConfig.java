package com.veljko.airline_ops.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI airlineOpsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Airline Operations API")
                        .description("REST API for airline dispatch and operations (flights, aircraft, airports, gates, weight & balance).")
                        .version("v1"));
    }
}
