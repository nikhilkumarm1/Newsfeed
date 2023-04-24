package com.fun.newsfeed.feed.dto;

import com.fun.newsfeed.post.dto.ContentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserFeedDto {
  private String postedBy;
  private Long postId;
  private List<ContentDto> contents;
  private int upVotes;
  private int downVotes;
  private int totalComments;
  private Date createdTime;
}
