package com.fun.newsfeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(columnList = "userId"), @Index(columnList = "parentId")})
public class UserPostEntity extends BaseWithIdColumnEntity {
  @ManyToOne
  @JoinColumn(name = "userId")
  private UserEntity userEntity;
  @ManyToOne
  @JoinColumn(name = "parentId")
  private UserPostEntity parentPost;
  @ManyToOne
  @JoinColumn(name = "rootId")
  private UserPostEntity rootPost;

  private String text;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userPostEntity")
  private List<ContentEntity> contentList;

  private int upVotes;

  private int downVotes;

  private int totalComments;
}
