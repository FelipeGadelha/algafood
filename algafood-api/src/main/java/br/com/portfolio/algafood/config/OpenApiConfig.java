package br.com.portfolio.algafood.config;

import br.com.portfolio.algafood.api.handle.ExceptionDetails;
import br.com.portfolio.algafood.api.v1.controller.doc.model.PageableOpenApi;
import br.com.portfolio.algafood.api.v1.controller.doc.model.PagedOpenApi;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.function.Supplier;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Configuration
public class OpenApiConfig {
//	https://springdoc.org/v1/
//	https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-spring-boot-2-webmvc/src/main/java/org/springdoc/demo/app2/api/UserApi.java
//	https://stackoverflow.com/questions/57736856/how-to-configure-springfox-to-unwrap-reactive-types-such-as-mono-and-flux-withou

	static {

//		var schema = new Schema<LocalDateTime>();
//		schema.example(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
//		ConverterUtils.addResponseTypeToIgnore(PagedOpenApi.class);

//		var schema = new Schema<KitchenOpenApi>();
//		var k = new KitchenOpenApi();
//		k.setContent(List.of(new KitchenRs(new Kitchen(1L, "Italiana"))));
//		schema.example(k);
		SpringDocUtils.getConfig()
			.replaceWithClass(Page.class, PagedOpenApi.class)
//			.replaceWithSchema(Page.class, schema)
//			.replaceWithClass(Page.class, KitchenOpenApi.class)
			.replaceParameterObjectWithClass(Pageable.class, PageableOpenApi.class)
			.setResponseEntityExceptionHandlerClass(ResponseEntityExceptionHandler.class);
	}

	@Bean
	public OpenAPI springOpenAPI() {

		return new OpenAPI()
//			.tags(
//				List.of(
//					new Tag().name("cidade").description("gerencia as cidades")
//				)
//			)
			.info(new Info()
				.title("Algafood API")
				.description("Spring Algafood API sample application")
				.version("v1.0")
				.contact(new Contact()
					.name("Felipe Gadelha Diniz Da Silva")
					.url("https://www.linkedin.com/in/felipe-gadelha-diniz-da-silva-aaaa4a158/")
					.email("felipegadelha90@gmail.com"))
				.license(new License()
					.name("Apache 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0")))
			.externalDocs(new ExternalDocumentation()
				.description("Spring Algafood Github Documentation")
				.url("https://github.com/FelipeGadelha/algafood"));

	}

	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
//		HandlerMethod handlerMethod;
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				// Change only paths with GET Méthods
				if (pathItem.getPost() != null || pathItem.getPut() != null) globalPostPutResponseMessages().forEach(operation.getResponses()::addApiResponse);
				if (pathItem.getGet() != null) globalGetResponseMessages().forEach(operation.getResponses()::addApiResponse);
				if (pathItem.getDelete() != null) globalDeleteResponseMessages().forEach(operation.getResponses()::addApiResponse);


//				pathItem.setParameters(List.of(new Parameter()
//					.name("campos")
//					.description("Nomes das propriedades para filtrar na resposta, ")
//					.in(ParameterIn.QUERY.toString())
//					.schema(new Schema<String>().example("tet"))
//					.content(new Content()
//							.addMediaType("", new MediaType().example("name"))
//						)
//					)
//				);
			}));
		};
	}
	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApi() {
//		HandlerMethod handlerMethod;
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				// Change only paths with GET Méthods
				if (pathItem.getPost() != null || pathItem.getPut() != null) globalPostPutResponseMessages()
					.forEach(operation.getResponses()::addApiResponse);
				if (pathItem.getGet() != null) globalGetResponseMessages()
					.forEach(operation.getResponses()::addApiResponse);
				if (pathItem.getDelete() != null) globalDeleteResponseMessages()
					.forEach(operation.getResponses()::addApiResponse);
			}));
		};
	}
	private Map<String, ApiResponse> globalGetResponseMessages() {
		return Map.of(
			String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), internalServerErrorResponse.get(),
			String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), notAcceptableResponse.get()
		);
	}
	private Map<String, ApiResponse> globalDeleteResponseMessages() {
		return Map.of(
			String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), internalServerErrorResponse.get(),
			String.valueOf(HttpStatus.BAD_REQUEST.value()), badRequestResponse.get()
		);
	}
	private Map<String, ApiResponse> globalPostPutResponseMessages() {
		return Map.of(
			String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), internalServerErrorResponse.get(),
			String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), notAcceptableResponse.get(),
			String.valueOf(HttpStatus.BAD_REQUEST.value()), badRequestResponse.get(),
			String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()), unsupportedMediaTypeResponse.get()
		);
	}

	private final Supplier<ApiResponse> internalServerErrorResponse = () -> new ApiResponse()
		.description("Erro interno do Servidor")
		.content(new Content()
			.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType())
		);
	private final Supplier<ApiResponse> badRequestResponse = () -> new ApiResponse()
		.description("Requisição inválida (erro do cliente)")
		.content(new Content()
			.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType())
		);
	private final Supplier<ApiResponse> notAcceptableResponse = () -> new ApiResponse()
		.description("Recurso não possui representação que pode ser aceita pelo consumidor")
//		.$ref("#/components/schemas/State")
		.content(new Content()
			.addMediaType(
				org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
				new MediaType()
					.example(
						ExceptionDetails.builder()
							.title("test")
							.timestamp(OffsetDateTime.now())
							.build()
					)
			)
		);
	private final Supplier<ApiResponse> unsupportedMediaTypeResponse = () -> new ApiResponse()
		.description("Requisição recusada porque o corpo está em um formato não suportado")
		.content(new Content()
			.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType())
		);
}

//	@Bean
//	public OpenApiCustomiser openApiCustomiser() {
//		return openApi -> {
//			openApi.getComponents()
//				.addParameters("pageable", new Parameter().$ref("#/components/parameters/PageableOpenApi"));
//			openApi.getComponents()
//				.addParameters("paged", new Parameter().$ref("#/components/parameters/PagedOpenApi"));
//
//			openApi.getComponents()
//				.addParameters("PageableOpenApi", new Parameter().name("CustomPageableParameter"));
//			openApi.getComponents()
//				.addParameters("PagedOpenApi", new Parameter().name("CustomPagedParameter"));
//		};
//	}
