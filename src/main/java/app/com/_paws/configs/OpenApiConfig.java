package app.com._paws.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configureOpenApi() {

        String securitySchemeName = "bearerAuth";

        return new OpenAPI().info(
                new Info()
                        .title("4Paws API \uD83D\uDC3E\uD83D\uDC15\u200D\uD83E\uDDBA")
                        .description(
                                """
                                <h3>Documentação da API do 4Paws - Sistema de Gerenciamento para Clínicas Veterinárias</h3>
                                <p>O 4Paws é uma API projetada para otimizar e simplificar a gestão de clínicas veterinárias. Ela oferece um conjunto de ferramentas para gerenciar consultas,</p>
                                <p>exames, prescrições, registros dos veterinários da clínica, registros de animais de estimação e informações dos tutores.</p>
                                """
                        )
                        .version("v1.0.0")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
        )
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
