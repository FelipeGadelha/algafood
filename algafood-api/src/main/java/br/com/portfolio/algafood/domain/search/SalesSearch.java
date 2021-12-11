package br.com.portfolio.algafood.domain.search;

import br.com.portfolio.algafood.domain.entity.DailySale;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;

import java.util.List;

public interface SalesSearch {

    List<DailySale> findDailySale(DailySaleFilter filter);
}
