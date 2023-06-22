package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import org.springframework.http.ResponseEntity;

@Tag(name = "Proprietario de Restaurante")
public interface RestaurantOwnerControllerOpenApi {

    @Operation(summary = "Lista proprietarios de restaurante", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de proprietarios de restaurante"),
        @ApiResponse(responseCode = "404", description = "proprietarios não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<Set<UserRs>> findAllOwner(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Atribui um usuário(proprietário) a um restaurante")
    void connectOwner(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long id
    );

    @Operation(summary = "Destitui um usuário(proprietário) a um restaurante")
    void disconnectOwner(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long id
    );
}
