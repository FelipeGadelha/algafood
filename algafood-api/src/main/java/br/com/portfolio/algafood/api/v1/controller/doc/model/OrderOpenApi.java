package br.com.portfolio.algafood.api.v1.controller.doc.model;

import br.com.portfolio.algafood.api.v1.dto.response.OrderRs;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "OrderOpenApi")
public class OrderOpenApi extends PagedOpenApi<OrderRs>{
}
