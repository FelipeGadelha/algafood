package br.com.portfolio.algafood.api.v1.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.portfolio.algafood.domain.model.Kitchen;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class KitchenControllerTest {

	@LocalServerPort
	private int port;
	
	final String BASE_PATH = "/v1/kitchens/";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
//	@Autowired
//	private KitchenRepository kitchenRepository;
	
//	@BeforeEach
//	public void init() {
//		this.datas();
//	}
	
//	@AfterEach
//	public void finish() {
//		kitchenRepository.deleteAll();
//	}
	
//	private void datas() {
//		var kitchen = new Kitchen(null, "Italiano", null);
//		var kitchen2 = new Kitchen(null, "Brasileiro", null);
//		var kitchen3 = new Kitchen(null, "Australiano", null);
//		
//		kitchenRepository.save(kitchen);
//		kitchenRepository.save(kitchen2);
//		kitchenRepository.save(kitchen3);
//	}
	
	@DisplayName("should return a list of kitchens successfully")
	@Test
	public void test() {
		ResponseEntity<String> entity = restTemplate.getForEntity(BASE_PATH, String.class);
		System.err.println("--------------TESTE ---------------- " + entity);
		assertThat(entity.getStatusCode()).isEqualTo(200);
	}
	
	@DisplayName("should return a kitchen successfully")
	@Test
	public void test1() {
		ResponseEntity<Kitchen> entity = restTemplate.getForEntity(BASE_PATH + "{id}", Kitchen.class, 1L);
		System.err.println("--------------TESTE 1 ---------------- " + entity);
		assertThat(entity.getStatusCode()).isEqualTo(200);
		assertThat(entity.getBody().getName()).isEqualTo("Tailandesa");
	}
	
	@DisplayName("should successfully save a kitchen")
	@Test
	public void test2() {
		var kitchen = new Kitchen(null, "Italiano", null);
		ResponseEntity<Kitchen> entity = restTemplate.postForEntity(BASE_PATH, kitchen, Kitchen.class);
		System.err.println("--------------TESTE 2 ---------------- " + entity.getBody());
		assertThat(entity.getStatusCode()).isEqualTo(201);
		assertThat(entity.getBody().getName()).isEqualTo("Italiano");
		assertThat(entity.getBody().getId()).isNotNull();
	}
	
	@DisplayName("should successfully update a kitchen")
	@Test
	public void test3() {
		HttpEntity<Kitchen> request = new HttpEntity<>(new Kitchen(null, "Italian", null));
		ResponseEntity<Kitchen> entity = restTemplate.exchange(BASE_PATH + "{id}", HttpMethod.PUT, request, Kitchen.class, 1L);
		System.err.println("--------------TESTE 3 ---------------- " + entity.getBody());
		assertThat(entity.getStatusCode()).isEqualTo(200);
		assertThat(entity.getBody().getId()).isEqualTo(1L);
		assertThat(entity.getBody().getName()).isEqualTo("Italian");
	}
	
	@DisplayName("should successfully delete a kitchen")
	@Test
	public void test4() {
		ResponseEntity<Kitchen> entity = restTemplate.exchange(BASE_PATH + "{id}", HttpMethod.DELETE, null, Kitchen.class, 1L);
		System.err.println("--------------TESTE 4 ---------------- " + entity.getBody());
		assertThat(entity.getStatusCode()).isEqualTo(204);
		assertThat(entity.getBody()).isNull();
	}
}
