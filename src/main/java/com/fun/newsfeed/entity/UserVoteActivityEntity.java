package com.fun.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "userId, postId, upVote", unique = true)})
public class UserVoteActivityEntity extends BaseWithIdColumnEntity {
  @ManyToOne
  @JoinColumn(name = "userId")
  private UserEntity userEntity;
  @ManyToOne
  @JoinColumn(name = "postId")
  private UserPostEntity userPostEntity;

  private boolean upVote;
}
