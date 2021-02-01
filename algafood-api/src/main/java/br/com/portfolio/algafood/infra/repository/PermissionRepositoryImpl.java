package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Repository;

import br.com.portfolio.algafood.domain.entity.Permission;
import br.com.portfolio.algafood.domain.repository.PermissionRepository;

@Repository
public class PermissionRepositoryImpl extends RepositoryImpl<Permission> implements PermissionRepository{
	
	public PermissionRepositoryImpl() {
		super(Permission.class);
	}
	
}
