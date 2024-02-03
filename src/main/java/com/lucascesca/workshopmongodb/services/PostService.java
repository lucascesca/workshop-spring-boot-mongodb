package com.lucascesca.workshopmongodb.services;

import com.lucascesca.workshopmongodb.domain.Post;
import com.lucascesca.workshopmongodb.repositories.PostRepository;
import com.lucascesca.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Obeject not found"));
    }
}
