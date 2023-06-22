package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.StateRq;
import br.com.portfolio.algafood.api.v1.dto.response.StateRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Estado")
public interface StateControllerOpenApi {

    @Operation(summary = "Lista Estado", responses = {
        @ApiResponse(responseCode = "200", description = "Lista Estado"),
        @ApiResponse(responseCode = "404", description = "Meio de pagamento não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<StateRs>> findAll();

    @Operation(summary = "Lista Estado por id")
    ResponseEntity<StateRs> findById(
        @Parameter(description = "ID de um estado", example = "1", required = true) Long id
    );

    @Operation(summary = "Cadastra um Estado")
    ResponseEntity<StateRs> save(@RequestBody(description = "Representação de um estado") StateRq stateRq);

    @Operation(summary = "Atualiza um Estado")
    ResponseEntity<StateRs> update(
        @Parameter(description = "ID de um estado", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um estado") StateRq stateRq
    );

    @Operation(summary = "Remove um Estado")
    void deleteById(@Parameter(description = "ID de um estado", example = "1", required = true) Long id);
}
