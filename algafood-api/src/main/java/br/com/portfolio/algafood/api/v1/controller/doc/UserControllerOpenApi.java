package br.com.portfolio.algafood.api.v1.controller.doc;

import br.com.portfolio.algafood.api.v1.dto.request.PasswordRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserRq;
import br.com.portfolio.algafood.api.v1.dto.request.UserUpdateRq;
import br.com.portfolio.algafood.api.v1.dto.response.UserRs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Usuário")
public interface UserControllerOpenApi {

    @Operation(summary = "Lista usuários", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários"),
        @ApiResponse(responseCode = "404", description = "usuário não encontrado",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "401", description = "Falha de autenticação",
            content = @Content(schema = @Schema(hidden = true)))
    })
    ResponseEntity<CollectionModel<UserRs>> findAll();

    @Operation(summary = "Busca usuário por id")
    ResponseEntity<UserRs> findById(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long id
    );

    @Operation(summary = "Cadastra usuário")
    ResponseEntity<UserRs> save(@RequestBody(description = "Representação de um usuário")  UserRq userRq);

    @Operation(summary = "Atualiza usuário")
    ResponseEntity<UserRs> update(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um usuário") UserUpdateRq userUpdateRq
    );

    void passwordUpdate(
        @Parameter(description = "ID de um usuário", example = "1", required = true) Long id,
        @RequestBody(description = "Representação de um objeto de senha") PasswordRq request
    );

    @Operation(summary = "Remove usuário")
    void deleteById(@Parameter(description = "ID de um usuário", example = "1", required = true) Long id);
}
