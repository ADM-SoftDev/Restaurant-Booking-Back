package com.adm.restaurante.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
/*Links : http://localhost:7082/api-Booking-Restaurants.yamls ; http://localhost:7082/swagger-ui/index.html*/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API-Booking-Restaurant")
                .version("1.0.0")
                .description("ADM-DigitalCloud")
                .contact(new Contact()
                        .name("Alexander Delgado Mancilla")
                        .email("backend-support@adm-digitalcloud.com")
                        .url("https://BookingRestaurante.adm.com"))

                );
    }

}
