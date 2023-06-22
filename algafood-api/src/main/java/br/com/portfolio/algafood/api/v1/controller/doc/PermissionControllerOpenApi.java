package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.PermissionRq;
import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Permissão")
public interface PermissionControllerOpenApi {

    @Operation(summary = "Lista permissões", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de permissões"),
        @ApiResponse(responseCode = "404", description = "Permissão não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<PermissionRs>> findAll();

    @Operation(summary = "Busca uma permissão por ID")
    ResponseEntity<PermissionRs> findById(
        @Parameter(description = "ID de uma permissão", example = "1", required = true) Long id
    );

    @Operation(summary = "Cadastra uma permissão")
    ResponseEntity<PermissionRs> save(
        @RequestBody(description = "Representação de uma permissão") PermissionRq permissionRq
    );

    @Operation(summary = "Atualiza uma permissão por ID")
    ResponseEntity<PermissionRs> update(
        @Parameter(description = "ID de uma permissão", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de uma permissão com novos dados") PermissionRq permissionRq
    );

    @Operation(summary = "Exclui uma permissão por ID")
    void deleteById(@Parameter(description = "ID de uma permissão", example = "1", required = true) Long id);
}
