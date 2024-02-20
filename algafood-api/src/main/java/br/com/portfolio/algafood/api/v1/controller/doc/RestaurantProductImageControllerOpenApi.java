package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.ProductImageRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductImageRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

@Tag(name = "Produto")
public interface RestaurantProductImageControllerOpenApi {

    @Operation(summary = "Busca imagem de produto de Restaurante",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de meio de pagamento de restaurante", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)),
                @Content(mediaType = MediaType.IMAGE_PNG_VALUE),
                @Content(mediaType = MediaType.IMAGE_JPEG_VALUE)
            }
        ),
        @ApiResponse(responseCode = "404", description = "Meio de pagamento não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<ProductImageRs> find(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long productId
    );

    @Operation(summary = "Busca imagem de produto de Restaurante", hidden = true)
    ResponseEntity<?> findImage(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long productId,
        String accept
    ) throws HttpMediaTypeNotAcceptableException;

    @Operation(summary = "Atualiza imagem de produto de Restaurante")
    ResponseEntity<ProductImageRs> update(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long productId,
        ProductImageRq imageRq
    ) throws IOException;

    @Operation(summary = "Remove imagem de produto de Restaurante")
    void deleteById(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long productId
    );
}
