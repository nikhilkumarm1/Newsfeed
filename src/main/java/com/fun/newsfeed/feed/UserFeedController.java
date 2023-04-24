package com.fun.newsfeed.feed;

import com.fun.newsfeed.session.SessionHolder;
import com.fun.newsfeed.feed.dto.UserFeedDto;
import com.fun.newsfeed.session.SessionRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeedController {

  private final UserFeedService cacheableUserFeedService;

  @Autowired
  public UserFeedController(UserFeedService cacheableUserFeedService) {
    this.cacheableUserFeedService = cacheableUserFeedService;
  }

  @SessionRequired
  public List<UserFeedDto> getUserFeeds(int page) {
    return cacheableUserFeedService.getUserFeeds(SessionHolder.getSession().getUsername(), page);
  }
}
