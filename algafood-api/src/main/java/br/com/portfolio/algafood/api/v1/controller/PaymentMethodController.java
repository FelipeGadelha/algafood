package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.api.v1.dto.request.PaymentMethodRq;
import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import br.com.portfolio.algafood.domain.service.PaymentMethodService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/payment-methods")
public class PaymentMethodController {
	
	private final PaymentMethodService paymentMethodService;

	@Autowired
	public PaymentMethodController(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@GetMapping()
	@JsonView(View.Basic.class)
	public ResponseEntity<List<PaymentMethodRs>> findAll() {
		return ResponseEntity.ok(paymentMethodService.findAll()
				.stream()
				.map(PaymentMethodRs::new)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	@JsonView(View.Detail.class)
	public ResponseEntity<PaymentMethodRs> findById(@PathVariable Long id) {
		var paymentMethod = paymentMethodService.findById(id);
		return ResponseEntity.ok(new PaymentMethodRs(paymentMethod));
	}
	
	@PostMapping
	@JsonView(View.Detail.class)
	public ResponseEntity<PaymentMethodRs> save(@RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.save(paymentMethodRq.convert());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new PaymentMethodRs(saved));
	}

	@PutMapping
	@JsonView(View.Detail.class)
	public ResponseEntity<PaymentMethodRs> update(@PathVariable Long id, @RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.update(id, paymentMethodRq.convert());
		return ResponseEntity.ok(new PaymentMethodRs(saved));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) { paymentMethodService.deleteById(id); }

}
