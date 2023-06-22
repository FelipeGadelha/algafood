package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.PaymentMethodRq;
import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Tag(name = "Método de pagamento")
public interface PaymentMethodControllerOpenApi {

    @Operation(summary = "Lista métodos de pagamento", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de métodos de pagamento"),
        @ApiResponse(responseCode = "404", description = "Métodos de pagamento não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<PaymentMethodRs>> findAll(ServletWebRequest request);

    @Operation(summary = "Busca um método de pagamento por ID")
    ResponseEntity<PaymentMethodRs> findById(
        @Parameter(description = "ID de um método de pagamento", example = "1", required = true) Long id,
        ServletWebRequest request
    );

    @Operation(summary = "Cadastra um método de pagamento")
    ResponseEntity<PaymentMethodRs> save(
        @RequestBody(description = "Representação de um método de pagamento") PaymentMethodRq paymentMethodRq
    );

    @Operation(summary = "Atualiza um método de pagamento por ID")
    ResponseEntity<PaymentMethodRs> update(
        @Parameter(description = "ID de um método de pagamento", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um método de pagamento com novos dados") PaymentMethodRq paymentMethodRq
    );

    @Operation(summary = "Exclui um método de pagamento por ID")
    void deleteById(@Parameter(description = "ID de um método de pagamento", example = "1", required = true) Long id);
}
