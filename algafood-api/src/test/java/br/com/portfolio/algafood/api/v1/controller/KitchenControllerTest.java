package br.com.portfolio.algafood.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.repository.KitchenRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class KitchenControllerTest {

	@LocalServerPort
	private int port;
	
	final String BASE_PATH = "/v1/kitchens/";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@BeforeEach
	public void init() {
		this.datas();
	}
	
	@AfterEach
	public void finish() {
		kitchenRepository.deleteAll();
	}
	
	private void datas() {
		var kitchen = new Kitchen(null, "Italiano", null);
		var kitchen2 = new Kitchen(null, "Brasileiro", null);
		var kitchen3 = new Kitchen(null, "Australiano", null);
		
		kitchenRepository.save(kitchen);
		kitchenRepository.save(kitchen2);
		kitchenRepository.save(kitchen3);
	}
	
	@Test
	public void test() {
		var kitchen = new Kitchen(null, "Italiano", null);
		ResponseEntity<Kitchen> entity = restTemplate.postForEntity(BASE_PATH, kitchen, Kitchen.class);
		System.err.println(entity.getBody());
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
		assertThat(entity.getBody().getName()).isEqualTo("Italiano");
		assertThat(entity.getBody().getId()).isNotNull();
	}
	
	@Test
	public void test1() {
		ResponseEntity<String> entity = restTemplate.getForEntity("/v1/kitchens", String.class);
		System.err.println(entity);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
//		assertThat(entity.getBody().getName()).isEqualTo("Brasileiro");
//		assertThat(entity.getBody().getRestaurants()).isNotNull();
		
		
		
	}
	
	
	
	
	
}
