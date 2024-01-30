package com.lucascesca.workshopmongodb.services;

import com.lucascesca.workshopmongodb.domain.User;
import com.lucascesca.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
       return repository.findAll();
    }
}
