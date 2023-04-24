package com.fun.newsfeed.post.dto;

import com.fun.newsfeed.common.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextDto implements ContentDto {
  private String text;
  @Override
  public ContentType getContentType() {
    return ContentType.TEXT;
  }
}
