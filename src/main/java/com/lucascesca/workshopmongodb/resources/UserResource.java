package com.lucascesca.workshopmongodb.resources;

import com.lucascesca.workshopmongodb.domain.Post;
import com.lucascesca.workshopmongodb.domain.User;
import com.lucascesca.workshopmongodb.dto.UserDTO;
import com.lucascesca.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = service.findAll();

        List<UserDTO> dtoList = users.stream().map(x -> new UserDTO(x)).toList();

        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(new UserDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO dto) {
        User user = service.fromDTO(dto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO dto) {
        User user = service.fromDTO(dto);
        user.setId(id);
        user =  service.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id).getPosts());
    }

    // My solution
    @GetMapping(value = "/{id}/posts/{postId}")
    public ResponseEntity<Post> getUserPost(@PathVariable String id, @PathVariable String postId) {
        User user = service.findById(id);
        Post post = null;
        for (Post p : user.getPosts()) {
            if (p.getId().toLowerCase().equals(postId)) {
                post = p;
            }
        }
        return ResponseEntity.ok().body(post);
    }
}
