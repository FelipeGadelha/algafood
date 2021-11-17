package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
}
