package br.com.portfolio.algafood.domain.jpa.restaurant;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.portfolio.algafood.AlgafoodApiApplication;

public class PaymentMethodJpaTest {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

//		PaymentMethodRepositoryImpl paymentMethodRepository = applicationContext.getBean(PaymentMethodRepositoryImpl.class);
		
//		System.out.println("Payment Method: " + paymentMethodRepository.find(1L));
//		
//		System.out.println();
//		
//		paymentMethodRepository.findAll().forEach(System.out::println);
//		
//		System.out.println();
		
		
	}

}
