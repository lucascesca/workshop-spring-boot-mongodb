package com.lucascesca.workshopmongodb.resources;

import com.lucascesca.workshopmongodb.domain.Post;
import com.lucascesca.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class PostResource {
    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Post> getPost(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
