package com.fun.newsfeed.post.dto;

import com.fun.newsfeed.exception.NotValidException;
import lombok.Getter;

import java.util.List;

@Getter
public class UserPostDto {
  private String username;
  private Long parentId;
  private List<ContentDto> contentList;

  private UserPostDto(Builder builder) {
    username = builder.username;
    parentId = builder.parentId;
    contentList = builder.contentList;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String username;
    private Long parentId;
    private List<ContentDto> contentList;

    private Builder() {
    }

    public Builder withUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder withParentId(Long parentId) {
      this.parentId = parentId;
      return this;
    }

    public Builder withContentList(List<ContentDto> contentList) {
      this.contentList = contentList;
      return this;
    }

    public UserPostDto build() throws NotValidException {
      validate();
      return new UserPostDto(this);
    }

    private void validate() throws NotValidException {
      if (this.contentList == null || this.contentList.isEmpty()) {
        throw new NotValidException("Content list can be empty");
      }

    }
  }
}
