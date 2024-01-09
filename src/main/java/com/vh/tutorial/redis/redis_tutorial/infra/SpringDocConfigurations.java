package com.vh.tutorial.redis.redis_tutorial.infra;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableWebMvc
@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )

                ).info(new Info().title("redis teste")
                        .description("Api de teste para o redis")
                        .contact(new Contact().email("hanelvinicius@gmail.com")
                                .name("Vinicius Hansel")));
    }
}
