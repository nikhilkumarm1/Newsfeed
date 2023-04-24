package com.fun.newsfeed.user.repo;

import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.entity.UserFolloweeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFolloweeRepository extends JpaRepository<UserFolloweeEntity, Long> {
  List<UserFolloweeEntity> findByUserEntity(UserEntity userEntity);
}
