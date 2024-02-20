package br.com.portfolio.algafood.domain.filter;

import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class DailySaleFilter {

    private Long restaurantId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) private OffsetDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) private OffsetDateTime finishDate;

    public DailySaleFilter(Long restaurantId, OffsetDateTime creationDate, OffsetDateTime finishDate) {
        this.restaurantId = restaurantId;
        this.creationDate = creationDate;
        this.finishDate = finishDate;
    }
    public Long getRestaurantId() { return restaurantId; }
    public OffsetDateTime getCreationDate() { return creationDate; }
    public OffsetDateTime getFinishDate() { return finishDate; }

    @Override
    public String toString() {
        return "DailySaleFilter{" +
                "restaurantId=" + restaurantId +
                ", creationDate=" + creationDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
