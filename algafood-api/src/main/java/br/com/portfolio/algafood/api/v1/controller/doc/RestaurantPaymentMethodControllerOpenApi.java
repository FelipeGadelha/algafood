package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Meio de pagamento de Restaurante")
public interface RestaurantPaymentMethodControllerOpenApi {

    @Operation(summary = "Lista meio de pagamento de restaurante", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de meio de pagamento de restaurante"),
        @ApiResponse(responseCode = "404", description = "Meio de pagamento não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<PaymentMethodRs>> findAll(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Remove meio de pagamento de um restaurante")
    void deletePaymentMethod(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um meio de pagamento", example = "1", required = true) Long id
    );
    @Operation(summary = "Adiciona meio de pagamento a um restaurante")
    void addPaymentMethod(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long restaurantId,
        @Parameter(description = "ID de um meio de pagamento", example = "1", required = true) Long id
    );
}
