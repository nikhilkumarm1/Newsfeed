package com.fun.newsfeed.post;

import com.fun.newsfeed.common.BaseTranslator;
import com.fun.newsfeed.common.ContentType;
import com.fun.newsfeed.entity.ContentEntity;
import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.entity.UserPostEntity;
import com.fun.newsfeed.post.dto.BinaryDto;
import com.fun.newsfeed.post.dto.ContentDto;
import com.fun.newsfeed.post.dto.TextDto;
import com.fun.newsfeed.post.dto.UserPostDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostTranslator extends BaseTranslator<UserPostDto, UserPostEntity> {
  @Override
  public UserPostEntity translateTo(UserPostDto from) {
    UserPostEntity userPostEntity = new UserPostEntity();
    UserEntity user = new UserEntity();
    user.setUserName(from.getUsername());
    userPostEntity.setUserEntity(user);
    userPostEntity.setContentList(new ArrayList<>());
    for (ContentDto contentDto: from.getContentList()) {
      if (contentDto.getContentType() == ContentType.TEXT) {
        userPostEntity.setText(((TextDto)contentDto).getText());
      } else {
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setCaption(((BinaryDto)contentDto).getCaption());
        contentEntity.setLink(((BinaryDto)contentDto).getLink());
        userPostEntity.getContentList().add(contentEntity);
      }
    }
    if (from.getParentId() != null) {
      UserPostEntity parentPost = new UserPostEntity();
      parentPost.setId(from.getParentId());
      userPostEntity.setParentPost(parentPost);
    }
    return userPostEntity;
  }
}
