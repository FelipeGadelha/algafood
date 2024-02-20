package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.controller.doc.model.OrderOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.OrderRq;
import br.com.portfolio.algafood.api.v1.dto.response.OrderDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import br.com.portfolio.algafood.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@Tag(name="Pedido")
public interface OrderControllerOpenApi {

    @Operation(summary = "Lista os pedidos",
//        parameters = {
//            @Parameter(name = "campos", description = "Nomes das propriedades para filtrar na resposta, separados por virgula",
//                in = ParameterIn.QUERY, example = "code,subtotal", content = @Content(schema = @Schema(implementation = String.class))
//            )
//        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos",
                content = @Content(schema = @Schema(implementation = OrderOpenApi.class))),
            @ApiResponse(responseCode = "404", description = "pedido não encontrado",
                content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha de autenticação",
                content = @Content(schema = @Schema(hidden = true)))
        }
    )
    PagedModel<OrderRs> search(
        @RequestBody(description = "Representação de um filtro de cozinha uma cozinha")
        @ParameterObject OrderFilter filter,
        @ParameterObject Pageable pageable
    );

    @Operation(
        summary = "Busca um pedido por ID"
//        parameters = {
//            @Parameter(name = "campos", description = "Nomes das propriedades para filtrar na resposta, separados por virgula",
//                in = ParameterIn.QUERY, example = "code,subtotal", content = @Content(schema = @Schema(implementation = String.class))
//            )
//        }
    )
    ResponseEntity<OrderDetailRs> findById(
        @Parameter(description = "ID de um pedido", example = "1", required = true) String code
    );

    @Operation(summary = "Cadastra um pedido")
    ResponseEntity<OrderDetailRs> save(
        @RequestBody(description = "Representação de um pedido com novos dados") OrderRq orderRq
    );
}
