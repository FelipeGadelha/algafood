package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.exception.ReportException;
import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.search.SalesSearch;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfSalesReportService implements SalesReportService {

    private final SalesSearch salesSearch;

    @Autowired
    public PdfSalesReportService(SalesSearch salesSearch) { this.salesSearch = salesSearch; }

    @Override
    public byte[] emitDailySales(DailySaleFilter filter, String offset) {
        try {
            var locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
            var inputStream = this.getClass()
                .getResourceAsStream("/reports/daily-sales.jasper");

            var params = new HashMap<String, Object>();
            params.put("REPORT_LOCALE", locale);

            var dailySales = salesSearch.findDailySale(filter, offset);
            var dataSource = new JRBeanCollectionDataSource(dailySales);
            var jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias " + e.getMessage());
        }
    }
}
