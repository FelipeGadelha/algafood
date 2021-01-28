package br.com.portfolio.algafood.domain.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.portfolio.algafood.AlgafoodApiApplication;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;
import br.com.portfolio.algafood.infra.repository.KitchenRepositoryImpl;

public class RemoveKitchen {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepositoryImpl.class);		
		Kitchen kitchen = new Kitchen(1L, null);
		
		kitchenRepository.remove(kitchen.getId());
		
	}

}
