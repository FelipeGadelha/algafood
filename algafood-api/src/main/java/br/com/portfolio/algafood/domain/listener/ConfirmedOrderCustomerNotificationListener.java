package br.com.portfolio.algafood.domain.listener;

import br.com.portfolio.algafood.domain.event.ConfirmedOrderEvent;
import br.com.portfolio.algafood.domain.model.Email;
import br.com.portfolio.algafood.domain.service.SendEmailService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ConfirmedOrderCustomerNotificationListener {

    private final SendEmailService emailService;

    public ConfirmedOrderCustomerNotificationListener(SendEmailService sendEmailService) {
        this.emailService = sendEmailService;
    }
    // @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @TransactionalEventListener
    public void whenConfirmingOrder(ConfirmedOrderEvent event) {
        var order = event.getOrder();
        var email = Email.builder()
            .subject(order.getRestaurant().getName() + " - Pedido Confirmado")
            .addTo(order.getClient().getEmail())
            .addProperty("order", order)
            .body("order-confirm.html")
            .build();
        emailService.send(email);
    }
}
