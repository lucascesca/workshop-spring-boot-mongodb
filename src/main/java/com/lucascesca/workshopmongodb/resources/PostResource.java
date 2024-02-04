package com.lucascesca.workshopmongodb.resources;

import com.lucascesca.workshopmongodb.domain.Post;
import com.lucascesca.workshopmongodb.resources.util.URL;
import com.lucascesca.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostResource {
    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Post> getPost(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/titlesearch")
    private ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);

        return ResponseEntity.ok().body(service.findByTitle(text));
    }
}
