package br.com.portfolio.algafood.api.v1.controller.doc.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "Pageable")
public record PageableOpenApi(

    @Parameter(description = "Número da página (começa em 0)", example = "0")
    int page,

    @Parameter(description = "Quantidade de elementos por página", example = "10")
    int size,

    @Parameter(description = "Nome da propriedade para ordenação (name,asc)", example = "name,asc")
    List<String> sort
) { }
