package com.artificer.algafood.core.springdoc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artificer.algafood.api.exceptionhandler.Problem;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(authorizationCode = @OAuthFlow(authorizationUrl = "${springdoc.oauthflow.authorizationurl}", tokenUrl = "${springdoc.oauthflow.tokenurl}", scopes = {
        @OAuthScope(name = "READ", description = "read scope"),
        @OAuthScope(name = "WRITE", description = "write scope")})))
public class SpringDocConfig {

    private static final String BAD_REQUEST = "BadRquest";
    private static final String NOT_FOUND = "NotFound";
    private static final String NOT_ACCEPTABLE = "NotAcceptableResouce";
    private static final String INTERNAL_SERVER_ERROR = "InternalServerError";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("AlgaFood API").version("v1").description("Rest API do AlgaFood")
                        .license(new License().name("Apache 2.0").url("http://springdoc.com ")))
                .tags(Arrays.asList(new Tag().name("Cidades").description("Gerencia as cidades"),
                        new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
                        new Tag().name("Grupos").description("Gerencia os grupos"),
                        new Tag().name("Formas de Pagamento").description("Gerencia as Formas de Pagamentos"),
                        new Tag().name("Pedidos").description("Gerencia os pedidos"),
                        new Tag().name("Restaurantes").description("Gerencia os restaurantes"),
                        new Tag().name("Estados").description("Gerencia os Estados"),
                        new Tag().name("Produto Imagem").description("Imagem do Produto")))
                .components(new Components().schemas(generateSchemas()).responses(gerarResponses()));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values()
                    .forEach(pathItem -> pathItem.readOperationsMap().forEach((httpMethod, operation) -> {
                        ApiResponses responses = operation.getResponses();

                        switch (httpMethod) {
                            case GET:
                                responses.addApiResponse("406", new ApiResponse().$ref(NOT_ACCEPTABLE));
                                responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR));
                                break;
                            case POST:
                            case PUT:
                            case PATCH:
                                responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST));
                                responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR));
                                break;
                            case DELETE:
                            default:
                                responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR));
                                break;
                        }
                    }));
        };
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Schema> generateSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();
        Map<String, Schema> schemaProblem = ModelConverters.getInstance().read(Problem.class);
        Map<String, Schema> schemaObjectProblem = ModelConverters.getInstance().read(Problem.Object.class);
        schemaMap.putAll(schemaProblem);
        schemaMap.putAll(schemaObjectProblem);
        return schemaMap;
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> responseMap = new HashMap<>();
        Content content = new Content().addMediaType(APPLICATION_JSON_VALUE,
                new MediaType().schema(new Schema<Problem>().$ref("Problem")));
        responseMap.put(BAD_REQUEST, new ApiResponse().description("Requisição Inválida").content(content));
        responseMap.put(INTERNAL_SERVER_ERROR,
                new ApiResponse().description("Erro Interno no Servidor").content(content));
        responseMap.put(NOT_FOUND, new ApiResponse().description("Recurso Não Encontrado").content(content));
        responseMap.put(NOT_ACCEPTABLE,
                new ApiResponse().description("Recurso não possui respresentação que possar ser aceita pelo consumidor")
                        .content(content));

        return responseMap;
    }

}
