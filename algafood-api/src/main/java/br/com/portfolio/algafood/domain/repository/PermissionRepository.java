package br.com.portfolio.algafood.domain.repository;

import br.com.portfolio.algafood.domain.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long>{

}
