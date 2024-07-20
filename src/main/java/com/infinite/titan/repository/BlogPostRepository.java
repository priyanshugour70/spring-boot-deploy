package com.infinite.titan.repository;

import com.infinite.titan.entity.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {

    Optional<BlogPostEntity> findByTitle(String title);

    // Additional query methods can be defined here if needed
}