package br.com.portfolio.algafood.domain.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.portfolio.algafood.AlgafoodApiApplication;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;

public class AddKitchen {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		Kitchen kitchen = new Kitchen(null, "Koreana");
		Kitchen kitchen1 = new Kitchen(null, "Norte-Americana");
		
		Kitchen save = kitchenRepository.save(kitchen);
		Kitchen save1 = kitchenRepository.save(kitchen1);
		
		System.out.println("Kitchen: " + save);
		System.out.println("Kitchen: " + save1);
		
	}

}
