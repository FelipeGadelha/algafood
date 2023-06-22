package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.GroupRq;
import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Grupo")
public interface GroupControllerOpenApi {

    @Operation(summary = "Lista os grupos", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de grupos"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Grupo de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<GroupRs>> findAll();

    @Operation(summary = "Busca um grupo por ID")
    ResponseEntity<GroupRs> findById(@Parameter(description = "ID de um grupo", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra um grupo")
    ResponseEntity<GroupRs> save(
        @RequestBody(description = "Representação de um grupo") GroupRq groupRq
    );

    @Operation(summary = "Atualiza um grupo por ID")
    ResponseEntity<GroupRs> update(
        @Parameter(description = "ID de um grupo", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um grupo com novos dados") GroupRq groupRq
    );

    @Operation(summary = "Exclui um grupo por ID")
    void deleteById(@Parameter(description = "ID de um grupo", example = "1", required = true) Long id);
}
//https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
