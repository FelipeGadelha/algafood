package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CustomJpaRepository<Group, Long> {

}
