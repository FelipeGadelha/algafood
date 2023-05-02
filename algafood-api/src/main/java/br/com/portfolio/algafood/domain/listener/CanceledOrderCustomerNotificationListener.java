package br.com.portfolio.algafood.domain.listener;

import br.com.portfolio.algafood.domain.event.CanceledOrderEvent;
import br.com.portfolio.algafood.domain.event.ConfirmedOrderEvent;
import br.com.portfolio.algafood.domain.model.Email;
import br.com.portfolio.algafood.domain.service.SendEmailService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CanceledOrderCustomerNotificationListener {

    private final SendEmailService emailService;

    public CanceledOrderCustomerNotificationListener(SendEmailService sendEmailService) {
        this.emailService = sendEmailService;
    }

    @TransactionalEventListener
    public void whenCancelingOrder(CanceledOrderEvent event) {
        var order = event.getOrder();
        var email = Email.builder()
            .subject(order.getRestaurant().getName() + " - Pedido Cancelado")
            .addTo(order.getClient().getEmail())
            .addProperty("order", order)
            .body("order-cancel.html")
            .build();
        emailService.send(email);
    }
}
