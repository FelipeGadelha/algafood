package br.com.portfolio.algafood.domain.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.portfolio.algafood.AlgafoodApiApplication;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;

public class QueryKitchen {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		kitchenRepository.findAll().forEach(System.out::println);
		
//		KitchenRegister register = new KitchenRegister();
//		register.list().forEach(System.out::println);
		
	}

}
