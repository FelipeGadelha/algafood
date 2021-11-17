package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.entity.Group;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    private static final String MSG_GROUP_NOT_FOUND = "Não existe Grupo com o ID %d";
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) { this.groupRepository = groupRepository; }

    @Transactional
    public List<Group> findAll() { return groupRepository.findAll(); }

    @Transactional
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_GROUP_NOT_FOUND, id)));
    }

    @Transactional
    public Group save(Group group) { return groupRepository.save(group); }

    @Transactional
    public Group update(Long id, Group update) {
        var group = this.findById(id);
        group = new Group(
                group.getId(),
                update.getName(),
                null);
        return groupRepository.save(group);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            groupRepository.deleteById(id);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Grupo com o ID %d não existe", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Grupo com o ID %d não pode ser removido, pois esta em uso", id));
        }
    }
}
