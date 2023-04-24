package com.fun.newsfeed.feed;

import com.fun.newsfeed.common.BaseTranslator;
import com.fun.newsfeed.entity.ContentEntity;
import com.fun.newsfeed.entity.UserPostEntity;
import com.fun.newsfeed.feed.dto.UserFeedDto;
import com.fun.newsfeed.post.dto.BinaryDto;
import com.fun.newsfeed.post.dto.TextDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserFeedTranslator extends BaseTranslator<UserPostEntity, UserFeedDto> {
  @Override
  public UserFeedDto translateTo(UserPostEntity from) {
    UserFeedDto userFeedDto = new UserFeedDto();
    userFeedDto.setPostedBy(from.getUserEntity().getUserName());
    userFeedDto.setPostId(from.getId());
    userFeedDto.setContents(new ArrayList<>());
    userFeedDto.getContents().add(new TextDto(from.getText()));
    for (ContentEntity contentEntity: from.getContentList()) {
      userFeedDto.getContents().add(new BinaryDto(contentEntity.getLink(), contentEntity.getCaption()));
    }
    userFeedDto.setUpVotes(from.getUpVotes());
    userFeedDto.setDownVotes(from.getDownVotes());
    userFeedDto.setTotalComments(from.getTotalComments());
    userFeedDto.setCreatedTime(from.getCreationTime());
    return userFeedDto;
  }
}
