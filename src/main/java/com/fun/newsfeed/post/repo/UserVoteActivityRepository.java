package com.fun.newsfeed.post.repo;

import com.fun.newsfeed.entity.UserVoteActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoteActivityRepository extends JpaRepository<UserVoteActivityEntity, Long> {
}
