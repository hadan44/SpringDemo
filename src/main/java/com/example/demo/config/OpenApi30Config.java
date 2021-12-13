package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {

  private final String moduleName;
  private final String apiVersion;

  public OpenApi30Config(
      @Value("Spirng Demo") String moduleName,
      @Value("1.0") String apiVersion) {
    this.moduleName = moduleName;
    this.apiVersion = apiVersion;
  }

  @Bean
  public OpenAPI customOpenAPI() {
//    final String securitySchemeName = "bearerAuth";
//    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
//    return new OpenAPI()
//        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//        .components(
//            new Components()
//                .addSecuritySchemes(securitySchemeName,
//                    new SecurityScheme()
//                        .name(securitySchemeName)
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                )
//        )
//        .info(new Info().title(apiTitle).version(apiVersion));
	  final String securitySchemeName = "bearer-key";
		return new OpenAPI()
				.components(new Components().addSecuritySchemes(securitySchemeName,
						new SecurityScheme()
								.name(securitySchemeName)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")))
				.info(new Info().title(moduleName).version(apiVersion));
  }
}