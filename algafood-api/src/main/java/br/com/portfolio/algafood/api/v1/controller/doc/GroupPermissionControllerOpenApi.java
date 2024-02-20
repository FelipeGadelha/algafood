package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.response.PermissionRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Set;
import org.springframework.http.ResponseEntity;

@Tag(name = "Grupo")
public interface GroupPermissionControllerOpenApi {

    @Operation(summary = "Lista as permissões de um grupo", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de permissões de um grupo"),
        @ApiResponse(responseCode = "404", description = "Permissões não encontrada",
            content = @Content(schema = @Schema(hidden = true))),
//        @ApiResponse(responseCode = "401", description = "permissões de autenticação",
//            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<Set<PermissionRs>> findAll(@Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId);

    @Operation(summary = "Conectar uma permissão a um grupo")
    void connect(
        @Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId,
        @Parameter(description = "ID de uma permissão", example = "1", required = true) Long id
    );

    @Operation(summary = "Desconectar uma permissão a um grupo")
    void disconnect(
        @Parameter(description = "ID de um grupo", example = "1", required = true) Long groupId,
        @Parameter(description = "ID de um permissão", example = "1", required = true) Long id
    );
}
