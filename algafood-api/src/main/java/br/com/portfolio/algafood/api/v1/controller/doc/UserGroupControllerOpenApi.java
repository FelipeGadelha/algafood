package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.response.GroupRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Grupo de usuários")
public interface UserGroupControllerOpenApi {
    @Operation(summary = "Lista grupos de usuários", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de grupos de usuários"),
        @ApiResponse(responseCode = "404", description = "grupos de usuários não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<List<GroupRs>> findAll(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long userId
    );
    @Operation(summary = "Atribui um usuário a um grupo")
    void connect(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long userId,
        @Parameter(description = "ID de um grupo", example = "1", required = true) Long id
    );

    @Operation(summary = "Desatribui um usuário a um grupo")
    void disconnect(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long userId,
        @Parameter(description = "ID de um grupo", example = "1", required = true) Long id
    );
}
