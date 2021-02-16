package br.com.portfolio.algafood.domain.jpa.restaurant;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.portfolio.algafood.AlgafoodApiApplication;

public class RestaurantJpaTest {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

//		RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepositoryImpl.class);
		
//		System.out.println("Restaurant: " + restaurantRepository.find(1L));
		
		System.out.println();
		
//		restaurantRepository.findAll().forEach(System.out::println);
		
		System.out.println();
		
		
	}

}
