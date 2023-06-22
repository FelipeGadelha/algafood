package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.RestaurantRq;
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantDetailRs;
import br.com.portfolio.algafood.api.v1.dto.response.RestaurantRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Restaurante")
public interface RestaurantControllerOpenApi {

    @Operation(summary = "Lista restaurantes", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de restaurantes"),
        @ApiResponse(responseCode = "404", description = "restaurante não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<RestaurantRs>> findAll();

    @Operation(summary = "Busca um restaurante por ID")
    ResponseEntity<RestaurantDetailRs> findById(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra um restaurante")
    ResponseEntity<RestaurantDetailRs> save(@RequestBody(description = "Representação de um restaurante") RestaurantRq restaurantRq);

    @Operation(summary = "Atualiza um restaurante por ID")
    ResponseEntity<RestaurantDetailRs> update(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um restaurante") RestaurantRq restaurantRq
    );

    @Operation(summary = "Atualiza parcialmente um restaurante por ID")
    ResponseEntity<RestaurantDetailRs> patch(
        @Parameter(description = "ID de um restaurante", example = "1", required = true) Long id,
        Map<String, Object> fields, HttpServletRequest request
    );

    @Operation(summary = "Ativa um restaurante por ID")
    void activate(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Ativação de restaurante em massa por ID")
    void massActivations(@RequestBody List<Long> ids);

    @Operation(summary = "Desativa um restaurante por ID")
    void inactivate(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Desativação de restaurante em massa por ID")
    void massInactivations(@RequestBody List<Long> ids);

    @Operation(summary = "Abre um restaurante por ID")
    void open(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Fecha um restaurante por ID")
    void close(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);

    @Operation(summary = "Deleta um restaurante por ID")
    void deleteById(@Parameter(description = "ID de um restaurante", example = "1", required = true) Long id);
}
