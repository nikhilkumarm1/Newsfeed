package com.fun.newsfeed.feed.repo;

import com.fun.newsfeed.entity.UserPostEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserFeedRepository extends PagingAndSortingRepository<UserPostEntity, Long> {

  @Query("select p from UserPostEntity p "
      + "WHERE p.userEntity.userName in :followees and p.parentPost is null "
      + "order by p.upVotes-p.downVotes desc, p.totalComments desc, p.id desc ")
  List<UserPostEntity> getUserFeed(@Param("followees") Collection<String> followees, Pageable pageable);
}
