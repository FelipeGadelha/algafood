package br.com.portfolio.algafood.domain.jpa;

import br.com.portfolio.algafood.domain.model.State;
import br.com.portfolio.algafood.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	public static void main(String[] args) {

		var user = User.builder()
			.id(1L)
			.name("Felipe Gadelha")
			.email("felipe@email.com")
			.password("123456")
			.build();
		var result = user.map(u -> {
			log.info("{}", u);
			return u;
		}).let(u -> {
			log.info("{}", u);
			return u;
		});
		log.info("NAME: {}", result.getName());

		var state = new State(1L, "Isabella Monteiro");
		state.let(s -> {
			log.info("{}", s);
			return s;
		}).map(s -> {
			log.info("{}", s);
			return s;
		}).also(s -> log.info("{}", s));
	}

}
