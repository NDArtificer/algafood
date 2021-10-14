package com.artificer.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.artificer.algafood.api.exceptionhandler.Problem;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		var typeresolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.artificer.algafood.api"))
					.build()
					.apiInfo(apiInfo())
					.useDefaultResponseMessages(false)
					.globalResponses(HttpMethod.GET, globalGetResponseMessages())
					.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
		            .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
		            .globalResponses(HttpMethod.PATCH, globalPostPutResponseMessages())
		            .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
		            .globalRequestParameters(Arrays.asList(new RequestParameterBuilder()
		            		.name("fields")
		            		.description("Nomes das propriedades para filtrar a resposta, separados por virgula")
		            		.in(ParameterType.QUERY)
		            		.required(false)
		            		.query(query -> query.model(
		            				model -> model.scalarModel(
		            						ScalarType.STRING)))
		            		.build()))
		            .additionalModels(typeresolver.resolve(Problem.class))
		            .directModelSubstitute(Pageable.class, PagebleModel.class)
		            .directModelSubstitute(Links.class, LinksModelOpenApi.class)
					.tags(new Tag("Cidades", "Gerencia as Cidades"),
						  new Tag("Grupos", "Gerencia os grupos de usuários"),
						  new Tag("Cozinhas", "Gerencia as cozinhas"),
						  new Tag("Formas de pagamento", "Gerencia as formas de pagamento"));
	}
	
	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.description("Erro interno do Servidor")
				.build(),
				
				new ResponseBuilder()
				.code(String.valueOf( HttpStatus.NOT_ACCEPTABLE.value()))
				.description("Tipo de Reposta não possui representação que possa ser aceita")
				.build()
				);
	}
	
	
	private List<Response> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.description("Erro interno do Servidor")
				.build(),
				
				new ResponseBuilder()
				.code(String.valueOf( HttpStatus.NOT_ACCEPTABLE.value()))
				.description("Tipo de Reposta não possui representação que possa ser aceita")
				.build(),
				
				new ResponseBuilder()
				.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
				.description("Requisição inválida (erro do cliente)")
				.representation( MediaType.APPLICATION_JSON)
					.apply(builderModelProblema())
				.build(),
				
				new ResponseBuilder()
				.code(String.valueOf( HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
				.description("Requisição recusada porque o corpo está em um formato não suportado")
				.build()
				);
	}
	
	
	private Consumer<RepresentationBuilder> builderModelProblema() {
		return r->r.model(m->m.name("Problem")
				.referenceModel(
						ref->ref.key(
								k->k.qualifiedModelName(
										q->q.name("Problem")
										.namespace("com.artificer.algafood.api.exceptionhandler")
										))));
	}

	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.description("Erro interno do Servidor")
				.build(),
				
				new ResponseBuilder()
				.code(String.valueOf( HttpStatus.BAD_REQUEST.value()))
				.description("Requisição inválida (erro do cliente)")
				.build()
				);
	}
	

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API para clientes e restaurantes")
				.version("1.0.0")
				.contact(new Contact("Artificer", "www.artificer.com.br", "the.artificer@hotmail.com"))
				.build();
	}
	
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("index.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

}
