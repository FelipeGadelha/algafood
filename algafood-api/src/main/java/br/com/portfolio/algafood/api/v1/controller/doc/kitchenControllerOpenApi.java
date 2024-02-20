package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.controller.doc.model.KitchenOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.KitchenRq;
import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cozinha")
public interface kitchenControllerOpenApi {

    @Operation(summary = "Lista as cozinhas", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de cozinhas",
            content = @Content(schema = @Schema(implementation =  KitchenOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    CollectionModel<KitchenRs> findAll(
        @ParameterObject Pageable pageable
    );

    @Operation(summary = "Busca uma cozinha por ID")
    ResponseEntity<KitchenRs> findById(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra uma cozinha")
    ResponseEntity<KitchenRs> save(@RequestBody(description = "Representação de uma cozinha") KitchenRq kitchenRq);

    @Operation(summary = "Atualiza uma cozinha por ID")
    ResponseEntity<KitchenRs> update(
        @Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de uma cozinha com novos dados") KitchenRq kitchenRq
    );

    @Operation(summary = "Exclui uma cidade por ID")
    void deleteById(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long id);
}
