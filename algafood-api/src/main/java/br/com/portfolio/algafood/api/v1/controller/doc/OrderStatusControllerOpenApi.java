package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.domain.model.OrderStatus;
import br.com.portfolio.algafood.domain.model.OrderStatusType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.SortedSet;
import org.springframework.http.ResponseEntity;

@Tag(name = "Status do pedido")
public interface OrderStatusControllerOpenApi {

    @Operation(summary = "Listar todos os pedido por código")
    ResponseEntity<SortedSet<OrderStatus>> findAll(
        @Parameter(description = "Código de um pedido", example = "1", required = true) String code
    );

    @Operation(summary = "Ultimo status por código")
    ResponseEntity<OrderStatusType> lastStatus(
        @Parameter(description = "Código de um pedido", example = "1", required = true) String code
    );

    @Operation(summary = "Busca um pedido por ID")
    void confimation(@Parameter(description = "Código de um pedido", example = "1", required = true) String code);

    @Operation(summary = "Busca um pedido por ID")
    void delivered(@Parameter(description = "Código de um pedido", example = "1", required = true) String code);
    @Operation(summary = "Busca um pedido por ID")
    void cancellation(@Parameter(description = "Código de um pedido", example = "1", required = true) String code);
}
