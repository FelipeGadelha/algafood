package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.filter.DailySaleFilter;

public interface SalesReportService {
    byte[] emitDailySales(DailySaleFilter filter, String offset);
}
