package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentMethodService {

    private static final String MSG_PAYMENT_METHOD_NOT_FOUND = "Não existe Forma de Pagamento com o ID %d";
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Transactional
    public List<PaymentMethod> findAll() { return paymentMethodRepository.findAll(); }

    @Transactional
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PAYMENT_METHOD_NOT_FOUND, id)));

    }
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) { return paymentMethodRepository.save(paymentMethod); }

    @Transactional
    public PaymentMethod update(Long id, PaymentMethod updated) {
        var paymentMethod = this.findById(id);
        paymentMethod = new PaymentMethod(paymentMethod.getId(), updated.getDescription());
        return this.save(paymentMethod);
    }

    @Transactional
    public void remove(Long id) {
        try {
            paymentMethodRepository.deleteById(id);
            paymentMethodRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Forma de Pagamento com o ID %d não existe", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Forma de Pagamento com o ID  %d não pode ser removida, pois esta em uso", id));
        }
    }
}
