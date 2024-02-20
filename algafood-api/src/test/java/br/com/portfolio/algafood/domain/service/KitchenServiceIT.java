package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import jakarta.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class KitchenServiceIT {

	private final KitchenService kitchenService;


	@Autowired
	public KitchenServiceIT(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

//	@BeforeEach
//	public void setupUp() {
//		manager.getTransaction().begin();
//		manager.createQuery("delete from Restaurant").executeUpdate();
//		manager.createQuery("delete from Kitchen").executeUpdate();
//		manager.getTransaction().commit();
//	}

	@Test
	@DisplayName("should register kitchen successfully")
	public void test1() {
		var kitchen = new Kitchen(null, "Chinesa", null);
		var saved = kitchenService.save(kitchen);
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
	}

	@Test
	@DisplayName("should not register kitchen when kitchen has no name")
	public void test2() {
		var kitchen = new Kitchen(null, null, null);
		assertThrows(ConstraintViolationException.class, () -> kitchenService.save(kitchen));
	}

	@Test
	@DisplayName("should not delete kitchen when kitchen is in use")
	public void test3() {
		var kitchen = new Kitchen(null, "Chinesa", null);
		var saved = kitchenService.save(kitchen);
//		manager.getTransaction().begin();
//		manager.persist(new Restaurant(null, "teste", BigDecimal.TEN, saved, null, null, null));
//		manager.getTransaction().commit();
		var exception = assertThrows(DataIntegrityViolationException.class, () -> kitchenService.deleteById(saved.getId()));
		assertThat(exception.getMessage()).isEqualTo("teste");
	}


	@Test
	@DisplayName("should not delete kitchen when kitchen does not exists")
	public void test4() {
		var exception = assertThrows(EntityNotFoundException.class, () -> kitchenService.deleteById(1L));
		assertThat(exception.getMessage()).isEqualTo("Não existe Cozinha com o ID 1");
	}
}
