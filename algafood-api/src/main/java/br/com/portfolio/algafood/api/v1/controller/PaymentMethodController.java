package br.com.portfolio.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import br.com.portfolio.algafood.infra.repository.PaymentMethodRepositoryImpl;

@RestController
@RequestMapping("/v1/payment-methods")
public class PaymentMethodController {
	
	@Autowired
	private PaymentMethodRepositoryImpl repository;
	
	@GetMapping()
	public List<PaymentMethod> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public PaymentMethod findById(@PathVariable Long id) {
		return repository.find(id);
	}
	
	@PostMapping
	public PaymentMethod save(@RequestBody PaymentMethod paymentMethod) {
		System.err.println(paymentMethod);
		return repository.save(paymentMethod);
	}
	
	
	
	
	

}
