package com.artificer.algafood.core.springdoc;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(authorizationCode = @OAuthFlow(authorizationUrl = "${springdoc.oauthflow.authorizationurl}", tokenUrl = "${springdoc.oauthflow.tokenurl}", scopes = {
		@OAuthScope(name = "READ", description = "read scope"),
		@OAuthScope(name = "WRITE", description = "write scope") })))
public class SpringDocConfig {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title("AlgaFood API").version("v1").description("Rest API do AlgaFood")
				.license(new License().name("Apache 2.0").url("http://springdoc.com ")));
	}

	@Bean
	public OpenApiCustomiser openApiCustomiser() {
		return openApi -> {
			openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
					.forEach(operation -> {
						var responses = operation.getResponses();
						var apiResponsesNaoEncontrado = new ApiResponse().description("Recurso Não Encontrado");
						var apiResponsesErrorInterno = new ApiResponse().description("Erro Interno Servidor");
						var apiResponsesSemRepresentacao = new ApiResponse()
								.description("Recurso sem representação que poderia ser aceita pelo consumidor");

						responses
								.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
										apiResponsesErrorInterno)
								.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), apiResponsesNaoEncontrado)
								.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
										apiResponsesSemRepresentacao);
					});
		};
	}

}
