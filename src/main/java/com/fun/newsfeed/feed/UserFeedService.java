package com.fun.newsfeed.feed;

import com.fun.newsfeed.feed.dto.UserFeedDto;

import java.util.List;

public interface UserFeedService {

  List<UserFeedDto> getUserFeeds(String userName, int page);
}
