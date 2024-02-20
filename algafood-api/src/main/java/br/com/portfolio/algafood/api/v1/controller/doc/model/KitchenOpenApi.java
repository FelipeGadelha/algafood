package br.com.portfolio.algafood.api.v1.controller.doc.model;

import br.com.portfolio.algafood.api.v1.dto.response.KitchenRs;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "KitchenOpenApi")
public class KitchenOpenApi extends PagedOpenApi<KitchenRs>{
}
