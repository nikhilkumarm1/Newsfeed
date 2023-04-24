package com.fun.newsfeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ContentEntity extends BaseWithIdColumnEntity {
  private String caption;
  private String link;
  @ManyToOne
  @JoinColumn(name = "postId")
  private UserPostEntity userPostEntity;

}
