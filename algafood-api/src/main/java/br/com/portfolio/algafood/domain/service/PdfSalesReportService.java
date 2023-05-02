package br.com.portfolio.algafood.domain.service;

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
            var inputStream = this.getClass()
                .getResourceAsStream("/reports/daily-sales.jasper");

            var params = new HashMap<String, Object>();
            params.put(
                "REPORT_LOCALE",
                new Locale.Builder().setLanguage("pt").setRegion("BR").build()
            );

            var dailySales = salesSearch.findDailySale(filter, offset);

            var dataSource = new JRBeanCollectionDataSource(dailySales);

//            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/thermochart.pdf");

            var jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);
            var result = JasperExportManager.exportReportToPdf(jasperPrint);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Teste: " + e.getMessage());
//            throw new RuntimeException(e);
        }
    }
}
