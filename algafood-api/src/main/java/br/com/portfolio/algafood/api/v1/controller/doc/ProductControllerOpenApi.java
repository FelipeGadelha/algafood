package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.ProductRq;
import br.com.portfolio.algafood.api.v1.dto.response.ProductRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Produto")
public interface ProductControllerOpenApi {

    @Operation(summary = "Lista produtos", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos"),
        @ApiResponse(responseCode = "404", description = "produto não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<ProductRs>> findAll(
        @Parameter(description = "ID de um produto", example = "1", required = true) Long restaurantId,
        boolean includeInactive
    );

    @Operation(summary = "Busca um produto por ID")
    ResponseEntity<ProductRs> findById(
        @Parameter(description = "ID de um produto", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long id
    );

    @Operation(summary = "Cadastra um produto")
    ResponseEntity<ProductRs> save(
        @Parameter(description = "ID de um produto", example = "1", required = true) Long restaurantId,
        @RequestBody(description = "Representação de um produto") ProductRq productRq
    );

    @Operation(summary = "Atualiza um produto por ID")
    ResponseEntity<ProductRs> update(
        @Parameter(description = "ID de um produto", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um produto", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um produto com novos dados")ProductRq productRq
    );
}
