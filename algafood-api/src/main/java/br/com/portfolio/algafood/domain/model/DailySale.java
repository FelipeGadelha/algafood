package br.com.portfolio.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DailySale {

    private String date;
    private Long totalSales;
    private BigDecimal totalIncome;

    @Deprecated public DailySale() {  }

    public DailySale(String date, Long totalSales, BigDecimal totalIncome) {
        this.date = date;
        this.totalSales = totalSales;
        this.totalIncome = totalIncome;
    }

    public String getDate() { return date; }
    public Long getTotalSales() {return totalSales; }
    public BigDecimal getTotalIncome() {return totalIncome; }

    @Override
    public String toString() {
        return "DailySale{" +
                "date=" + date +
                ", totalSales=" + totalSales +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
