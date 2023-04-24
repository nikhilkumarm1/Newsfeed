package com.fun.newsfeed.post.repo;

import com.fun.newsfeed.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostContentRepository extends JpaRepository<ContentEntity, Long> {
}
