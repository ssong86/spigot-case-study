package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PostControllerTest {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/posts")
    public Post createPost(@Validated @RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "id") Long postId,
                                           @Validated @RequestBody Post postDetails) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

        post.setSlug(postDetails.getSlug());
        post.setUrl(postDetails.getUrl());
        post.setImage(postDetails.getImage());
        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    @PatchMapping("/posts/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable(value = "id") Long postId,
                                          @Validated @RequestBody Map<String, Object> postDetails)
            throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

        if (postDetails.containsKey("slug")) {
            post.setSlug((String) postDetails.get("slug"));
        }

        if (postDetails.containsKey("url")) {
            post.setUrl((String) postDetails.get("url"));
        }

        if (postDetails.containsKey("image")) {
            post.setImage((String) postDetails.get("image"));
        }

        final Post updatedPost = postRepository.save(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
