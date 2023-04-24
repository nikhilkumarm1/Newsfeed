package com.fun.newsfeed.post.dto;

import com.fun.newsfeed.common.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BinaryDto implements ContentDto {
  private String link;
  private String caption;

  @Override
  public ContentType getContentType() {
    return ContentType.BINARY;
  }
}
