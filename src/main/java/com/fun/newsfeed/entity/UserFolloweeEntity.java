package com.fun.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = {
    @Index(columnList = "userId,followeeId", unique = true),
    @Index(columnList = "followeeId")})
public class UserFolloweeEntity extends BaseWithIdColumnEntity {
  @ManyToOne
  @JoinColumn(name = "userId")
  private UserEntity userEntity;
  @ManyToOne
  @JoinColumn(name = "followeeId")
  private UserEntity followeeEntity;
}
