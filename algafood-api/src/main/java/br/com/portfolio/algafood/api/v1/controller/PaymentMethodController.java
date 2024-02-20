package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.controller.doc.PaymentMethodControllerOpenApi;
import br.com.portfolio.algafood.api.v1.dto.request.PaymentMethodRq;
import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import br.com.portfolio.algafood.domain.service.PaymentMethodService;
import jakarta.validation.Valid;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@RestController
@RequestMapping("/v1/payment-methods")
public class PaymentMethodController implements PaymentMethodControllerOpenApi {
	
	private final PaymentMethodService paymentMethodService;

	@Autowired
	public PaymentMethodController(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@GetMapping()
	@Override public ResponseEntity<List<PaymentMethodRs>> findAll(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String eTag = "0";
		var dateLastUpdate = paymentMethodService.getDateLastUpdate();
		if (dateLastUpdate != null) eTag = String.valueOf(dateLastUpdate.getEpochSecond());
		if (request.checkNotModified(eTag)) return null;

		var paymentMethodRs = paymentMethodService.findAll()
			.stream()
			.map(PaymentMethodRs::new)
			.toList();
		return ResponseEntity.ok()
			.cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)).cachePublic())
//			.header("ETag", eTag)
			.eTag(eTag)
			.body(paymentMethodRs);
	}
	
	@GetMapping("/{id}")
	@Override public ResponseEntity<PaymentMethodRs> findById(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String eTag = "0";
		var dateLastUpdate = paymentMethodService.getDateLastUpdate();
		if (dateLastUpdate != null) eTag = String.valueOf(dateLastUpdate.getEpochSecond());
		if (request.checkNotModified(eTag)) return null;

		var paymentMethod = paymentMethodService.findById(id);
		return ResponseEntity.ok()
			.cacheControl(CacheControl.maxAge(Duration.ofSeconds(10)))
			.eTag(eTag)
			.body(new PaymentMethodRs(paymentMethod));
	}
	
	@PostMapping
	@Override public ResponseEntity<PaymentMethodRs> save(@RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.save(paymentMethodRq.convert());
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new PaymentMethodRs(saved));
	}

	@PutMapping
	@Override public ResponseEntity<PaymentMethodRs> update(@PathVariable Long id, @RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.update(id, paymentMethodRq.convert());
		return ResponseEntity.ok(new PaymentMethodRs(saved));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override public void deleteById(@PathVariable Long id) { paymentMethodService.deleteById(id); }
}
