package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.Permission;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private static final String MSG_PERMISSION_NOT_FOUND = "Não existe um cadastro de permissão com código %d";

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public List<Permission> findAll() { return permissionRepository.findAll(); }

    @Transactional
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PERMISSION_NOT_FOUND, id)));
    }

    @Transactional
    public Permission save(Permission permission) { return permissionRepository.save(permission); }

    @Transactional
    public Permission update(Long id, Permission updated) {
        var permission = this.findById(id);
        permission = Permission.builder()
                .clone(permission)
                .copy(updated)
                .build();
        return permissionRepository.save(permission);
    }
    @Transactional
    public void deleteById(Long id) {
        try {
            permissionRepository.deleteById(id);
            permissionRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Permissão com o ID %d não existe", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Permissão com o ID %d não pode ser removido, pois esta em uso", id));
        }
    }
}
