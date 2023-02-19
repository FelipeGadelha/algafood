package br.com.portfolio.algafood.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DailySaleProjection {
    LocalDate getDate();
    Long getTotalSales();
    BigDecimal getTotalIncome();
}
