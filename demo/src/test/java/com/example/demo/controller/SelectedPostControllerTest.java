package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SelectedPost;
import com.example.demo.repository.SelectedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class SelectedPostControllerTest {

    @Autowired
    private SelectedPostRepository selectedPostRepository;

    // Get all selected posts
    @GetMapping("/selectedposts")
    public List<SelectedPost> getAllSelectedPosts() {
        return selectedPostRepository.findAll();
    }

    // Get a single selected post
    @GetMapping("/selectedposts/{id}/{packageName}/{position}")
    public SelectedPost getSelectedPostById(@PathVariable(value = "id") Long id,
                                            @PathVariable(value = "packageName") String packageName,
                                            @PathVariable(value = "position") Integer position) {
        return selectedPostRepository.findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("SelectedPost with id/packageName/position " + id + "/" + packageName + "/" + position + " not found"));
    }

    // Create a new selected post
    @PostMapping("/selectedposts")
    public SelectedPost createSelectedPost(@Validated @RequestBody SelectedPost selectedPost) {
        return selectedPostRepository.save(selectedPost);
    }

    // Update a selected post
    @PutMapping("/selectedposts/{id}/{packageName}/{position}")
    public SelectedPost updateSelectedPost(@PathVariable(value = "id") Long id,
                                           @PathVariable(value = "packageName") String packageName,
                                           @PathVariable(value = "position") Integer position,
                                           @Validated @RequestBody SelectedPost selectedPostDetails) {
        SelectedPost selectedPost = selectedPostRepository.findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("SelectedPost with id/packageName/position" + id+"/"+packageName+"/"+position));

        selectedPost.setActive(selectedPostDetails.getActive());
        selectedPost.setCreatedAt(selectedPostDetails.getCreatedAt());
        selectedPost.setUpdatedAt(selectedPostDetails.getUpdatedAt());
        selectedPost.setPackageName(selectedPostDetails.getPackageName());
        selectedPost.setPosition(selectedPostDetails.getPosition());

        SelectedPost updatedSelectedPost = selectedPostRepository.save(selectedPost);
        return updatedSelectedPost;
    }

    // Delete a selected post
    @DeleteMapping("/selectedposts/{id}/{packageName}/{position}")
    public ResponseEntity<?> deleteSelectedPost(@PathVariable(value = "id") Long id,
                                                @PathVariable(value = "packageName") String packageName,
                                                @PathVariable(value = "position") Integer position) {
        SelectedPost selectedPost = selectedPostRepository.findByIdAndPackageNameAndPosition(id, packageName, position)
                .orElseThrow(() -> new ResourceNotFoundException("SelectedPost with id/packageName/position" + id+"/"+packageName+"/"+position));

        selectedPostRepository.delete(selectedPost);

        return ResponseEntity.ok().build();
    }
}
