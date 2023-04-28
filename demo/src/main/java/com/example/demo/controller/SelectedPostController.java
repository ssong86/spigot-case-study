package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SelectedPost;
import com.example.demo.repository.SelectedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selected-posts")
public class SelectedPostController {

    private final SelectedPostRepository selectedPostRepository;

    @Autowired
    public SelectedPostController(SelectedPostRepository selectedPostRepository) {
        this.selectedPostRepository = selectedPostRepository;
    }

    // GET endpoint to retrieve a selected post by its composite key
    @GetMapping("/{id}/{packageName}/{position}")
    public ResponseEntity<SelectedPost> getSelectedPost(
            @PathVariable("id") Long id,
            @PathVariable("packageName") String packageName,
            @PathVariable("position") Integer position
    ) {
        SelectedPost selectedPost = selectedPostRepository
                .findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("Selected post not found"));
        return ResponseEntity.ok(selectedPost);
    }

    // POST endpoint to create a new selected post
    @PostMapping
    public ResponseEntity<SelectedPost> createSelectedPost(
            @RequestBody SelectedPost selectedPost
    ) {
        SelectedPost createdPost = selectedPostRepository.save(selectedPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // PATCH endpoint to update an existing selected post
    @PatchMapping("/{id}/{packageName}/{position}")
    public ResponseEntity<SelectedPost> updateSelectedPost(
            @PathVariable("id") Long id,
            @PathVariable("packageName") String packageName,
            @PathVariable("position") Integer position,
            @RequestBody SelectedPost selectedPostUpdates
    ) {
        SelectedPost selectedPost = selectedPostRepository
                .findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("Selected post not found"));

        // Update the fields of the existing selected post
        selectedPost.setActive(selectedPostUpdates.getActive());
        selectedPost.setUpdatedAt(selectedPostUpdates.getUpdatedAt());

        SelectedPost updatedPost = selectedPostRepository.save(selectedPost);
        return ResponseEntity.ok(updatedPost);
    }

    // DELETE endpoint to delete a selected post by its composite key
    @DeleteMapping("/{id}/{packageName}/{position}")
    public ResponseEntity<Void> deleteSelectedPost(
            @PathVariable("id") Long id,
            @PathVariable("packageName") String packageName,
            @PathVariable("position") Integer position
    ) {
        SelectedPost selectedPost = selectedPostRepository
                .findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("Selected post not found"));

        selectedPostRepository.delete(selectedPost);
        return ResponseEntity.noContent().build();
    }
}
