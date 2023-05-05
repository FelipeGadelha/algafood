package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.CityRq;
import br.com.portfolio.algafood.api.v1.dto.response.CityRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "cidade")
public interface CityControllerOpenApi {

    @Operation(summary = "Lista as cidades", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de raças"),
        @ApiResponse(responseCode = "404", description = "Raça não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<CityRs>> findAll();

    @Operation(summary = "Busca uma cidade por ID")
    ResponseEntity<CityRs> findById(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra uma cidade")
    ResponseEntity<CityRs> save(
        @RequestBody(description = "Representação de uma cidade") CityRq cityRq
    );

    @Operation(summary = "Atualiza uma cidade por ID")
    ResponseEntity<CityRs> update(
        @Parameter(description = "ID de uma cidade", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de uma cidade com novos dados") CityRq cityRq
    );

    @Operation(summary = "Exclui uma cidade por ID")
    void deleteById(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
}
//https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
