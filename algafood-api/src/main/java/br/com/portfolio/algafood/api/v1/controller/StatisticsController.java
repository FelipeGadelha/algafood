package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.validator.annotation.Offset;
import br.com.portfolio.algafood.domain.entity.DailySale;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.search.SalesSearch;
import br.com.portfolio.algafood.domain.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@Validated
public class StatisticsController {

    private final SalesSearch salesSearch;
    private final SalesReportService salesReportService;

    @Autowired
    public StatisticsController(SalesSearch salesSearch, SalesReportService salesReportService) {
        this.salesSearch = salesSearch;
        this.salesReportService = salesReportService;
    }

    @GetMapping("/daily-sale")
    public List<DailySale> findDailySale(DailySaleFilter filter,
                                         @RequestParam(required = false, defaultValue = "+00:00") @Offset String offset) {
        return salesSearch.findDailySale(filter, offset);
    }

    @GetMapping(path = "/daily-sale", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findDailySalePdf(DailySaleFilter filter,
                                                   @RequestParam(required = false, defaultValue = "+00:00") @Offset String offset) {
        byte[] bytesPdf = salesReportService.emitDailySales(filter, offset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sale.pdf");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytesPdf.length));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }
}
