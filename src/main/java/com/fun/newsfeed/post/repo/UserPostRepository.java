package com.fun.newsfeed.post.repo;

import com.fun.newsfeed.entity.UserPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPostEntity, Long> {

  @Modifying
  @Query("update UserPostEntity u set u.upVotes = u.upVotes + 1 where u.id = :id")
  void incrementUpVotes(@Param("id") Long id);

  @Modifying
  @Query("update UserPostEntity u set u.downVotes = u.downVotes + 1 where u.id = :id")
  void incrementDownVotes(@Param("id") Long id);

  @Modifying
  @Query("update UserPostEntity u set u.totalComments = u.totalComments + 1 where u.id = :id")
  void incrementCommentCount(@Param("id") Long id);
}
