package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.filter.DailySaleFilter;
import br.com.portfolio.algafood.domain.search.SalesSearch;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
            params.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailySales = salesSearch.findDailySale(filter, offset);

            var dataSource = new JRBeanCollectionDataSource(dailySales, false);

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/thermochart.pdf");

            var jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Teste: " + e.getMessage());
        }
    }
}
