package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.validator.annotation.Offset;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.model.DailySale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Estatisticas")
public interface StatisticsControllerOpenApi {
    @Operation(
        summary = "Busca vendas diarias",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de Venda diária"),
//                content = {
//                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)),
//                @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)
//            }),
            @ApiResponse(responseCode = "404", description = "Venda diária não encontrada",
                content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha de autenticação",
                content = @Content(schema = @Schema(hidden = true)))
        }
    )
    List<DailySale> findDailySale(
        @ParameterObject DailySaleFilter filter,
        @Parameter(description = "Diferença de tempo", example = "+00:00")
        @RequestParam(required = false, defaultValue = "+00:00") @Offset String offset
    );
    @Operation(
        summary = "Busca vendas diarias",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de Venda diária"),
//                content = {
//                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)),
//                @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)
//            }),
            @ApiResponse(responseCode = "404", description = "Venda diária não encontrada",
                content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Falha de autenticação",
                content = @Content(schema = @Schema(hidden = true)))
        }
    )
    ResponseEntity<byte[]> findDailySalePdf(
        @ParameterObject DailySaleFilter filter,
        @Parameter(description = "Diferença de tempo", example = "+00:00")
        @RequestParam(required = false, defaultValue = "+00:00") @Offset String offset

    );
}
