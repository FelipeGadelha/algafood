package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.api.v1.dto.request.PaymentMethodRq;
import br.com.portfolio.algafood.api.v1.dto.response.PaymentMethodRs;
import br.com.portfolio.algafood.domain.service.PaymentMethodService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@RestController
@RequestMapping("/v1/payment-methods")
public class PaymentMethodController {
	
	private final PaymentMethodService paymentMethodService;

	@Autowired
	public PaymentMethodController(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@GetMapping()
	public ResponseEntity<List<PaymentMethodRs>> findAll(ServletWebRequest request) {
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
			.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
//			.header("ETag", eTag)
			.eTag(eTag)
			.body(paymentMethodRs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentMethodRs> findById(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String eTag = "0";
		var dateLastUpdate = paymentMethodService.getDateLastUpdate();
		if (dateLastUpdate != null) eTag = String.valueOf(dateLastUpdate.getEpochSecond());
		if (request.checkNotModified(eTag)) return null;

		var paymentMethod = paymentMethodService.findById(id);
		return ResponseEntity.ok()
			.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
			.eTag(eTag)
			.body(new PaymentMethodRs(paymentMethod));
	}
	
	@PostMapping
	public ResponseEntity<PaymentMethodRs> save(@RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.save(paymentMethodRq.convert());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new PaymentMethodRs(saved));
	}

	@PutMapping
	public ResponseEntity<PaymentMethodRs> update(@PathVariable Long id, @RequestBody @Valid PaymentMethodRq paymentMethodRq) {
		final var saved = paymentMethodService.update(id, paymentMethodRq.convert());
		return ResponseEntity.ok(new PaymentMethodRs(saved));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) { paymentMethodService.deleteById(id); }

}
