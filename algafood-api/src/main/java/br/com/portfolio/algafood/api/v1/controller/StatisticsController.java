package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.domain.entity.DailySale;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.search.SalesSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {

    private final SalesSearch salesSearch;

    @Autowired
    public StatisticsController(SalesSearch salesSearch) { this.salesSearch = salesSearch; }

    @GetMapping("/daily-sale")
    public List<DailySale> findDailySale(DailySaleFilter filter) { return salesSearch.findDailySale(filter); }

}
