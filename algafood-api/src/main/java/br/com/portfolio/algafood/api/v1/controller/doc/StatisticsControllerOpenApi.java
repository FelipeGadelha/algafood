package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.model.DailySale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Estatisticas")
public interface StatisticsControllerOpenApi {
    @Operation(summary = "Busca vendas diarias em JSON")
    List<DailySale> findDailySale(
        @RequestBody(description = "Representação de um filtro para venda diaria") DailySaleFilter filter,
        @Parameter(description = "Diferença de tempo", example = "1", required = true) String offset
    );
    @Operation(summary = "Busca vendas diarias em PDF")
    ResponseEntity<byte[]> findDailySalePdf(
        @RequestBody(description = "Representação de um filtro para venda diaria") DailySaleFilter filter,
        @Parameter(description = "Diferença de tempo", example = "1", required = true) String offset
    );
}
