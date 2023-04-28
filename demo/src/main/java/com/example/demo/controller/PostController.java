package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.exception.ResourceNotFoundException;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        BeanUtils.copyProperties(post, existingPost, "id");
        return postRepository.save(existingPost);
    }

    @PatchMapping("/{id}")
    public Post patchPost(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        applyPatch(existingPost, updates);
        return postRepository.save(existingPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.delete(existingPost);
    }

    private void applyPatch(Post post, Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            switch (field) {
                case "slug":
                    post.setSlug((String) value);
                    break;
                case "url":
                    post.setUrl((String) value);
                    break;
                case "image":
                    post.setImage((String) value);
                    break;
                default:
                    // Ignore unrecognized fields
                    break;
            }
        }
    }
}
