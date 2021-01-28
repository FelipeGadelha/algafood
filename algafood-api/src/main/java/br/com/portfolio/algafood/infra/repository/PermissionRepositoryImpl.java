package br.com.portfolio.algafood.infra.repository;

import org.springframework.stereotype.Component;

import br.com.portfolio.algafood.domain.entity.Permission;
import br.com.portfolio.algafood.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImpl extends RepositoryImpl<Permission> implements PermissionRepository{
	
	public PermissionRepositoryImpl() {
		super(Permission.class);
	}
	
}
