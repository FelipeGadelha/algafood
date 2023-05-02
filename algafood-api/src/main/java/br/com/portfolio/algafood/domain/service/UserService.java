package br.com.portfolio.algafood.domain.service;

import br.com.portfolio.algafood.domain.model.User;
import br.com.portfolio.algafood.domain.exception.BusinessException;
import br.com.portfolio.algafood.domain.exception.EntityInUseException;
import br.com.portfolio.algafood.domain.exception.EntityNotFoundException;
import br.com.portfolio.algafood.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String MSG_USER_NOT_FOUND = "Não existe Usuário com o ID %d";;
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Transactional
    public List<User> findAll() { return userRepository.findAll(); }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_USER_NOT_FOUND, id)));
    }

    @Transactional
    public User save(User user) {
        userRepository.detach(user);
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent() && !optional.get().equals(user)) {
            throw new BusinessException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User updated) {
        var user = this.findById(id);
        user = User.builder()
                .clone(user)
                .copy(updated)
                .build();
        return this.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            userRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Usuário com o ID %d não existe", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Usuário com o ID %d não pode ser removido, pois esta em uso", id));
        }
    }
}
