package com.example.demo.repository;

import com.example.demo.model.SelectedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SelectedPostRepository extends JpaRepository<SelectedPost, Long> {
    Optional<SelectedPost> findByIdAndPackageNameAndPosition(Long id, String packageName, Integer position);
}
