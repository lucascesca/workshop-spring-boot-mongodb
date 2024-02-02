package com.lucascesca.workshopmongodb.services;

import com.lucascesca.workshopmongodb.domain.User;
import com.lucascesca.workshopmongodb.dto.UserDTO;
import com.lucascesca.workshopmongodb.repositories.UserRepository;
import com.lucascesca.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
       return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User user) { return repository.save(user); }

    public User fromDTO(UserDTO dto) {
        return new User(dto.getId(), dto.getName(),dto.getEmail());
    }

    public void delete(String id) {
        repository.deleteById(findById(id).getId());
    }

    public User update(User user) {
        User entity = findById(user.getId());
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
    }
}
