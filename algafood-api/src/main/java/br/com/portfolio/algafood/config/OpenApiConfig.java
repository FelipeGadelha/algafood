package br.com.portfolio.algafood.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.Arrays;
import java.util.List;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;


@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI springSchoolOpenAPI() {
		return new OpenAPI()
			.tags(
				List.of(
					new Tag().name("cidade").description("gerencia as cidades")
				)
			)
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

//	@Bean
//	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
//		HandlerMethod handlerMethod;
//		return openApi -> {
//			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
//				// Change only paths with GET Méthods
//				if (pathItem.getPost() != null) {
//					ApiResponses apiResponses = operation.getResponses();
//					ApiResponse apiResponse = new ApiResponse().description("Custom Error")
//						.content(new Content()
//							.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
//					apiResponses.addApiResponse("400", apiResponse);
//				}
//			}));
//		};
//	}

//	private List<Response> globalGetResponseMessages() {
//		return Arrays.asList(
//			new ResponseBuilder()
//				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//				.description("Erro interno do Servidor")
//				.build(),
//			new ResponseBuilder()
//				.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
//				.description("Recurso não possui representação que pode ser aceita pelo consumidor")
//				.build()
//		);
//	}
}