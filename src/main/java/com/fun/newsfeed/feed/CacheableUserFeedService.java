package com.fun.newsfeed.feed;

import com.fun.newsfeed.feed.dto.UserFeedDto;
import com.fun.newsfeed.user.observer.UserFolloweeObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CacheableUserFeedService implements UserFolloweeObserver, UserFeedService {

  private final PersistedUserFeedService persistedUserFeedService;

  @Autowired
  public CacheableUserFeedService(PersistedUserFeedService persistedUserFeedService) {
    this.persistedUserFeedService = persistedUserFeedService;
  }

  @Override
  @Cacheable(cacheNames = {"userFeeds"} )
  public List<UserFeedDto> getUserFeeds(String userName, int page) {
    return persistedUserFeedService.getUserFeeds(userName, page);
  }

  @CacheEvict(cacheNames = {"userFeeds"})
  public void followeesChanged(String userName) {
    System.out.println("Evicting userFeeds for userName as followees got changed: "+  userName);
  }

  @CacheEvict(allEntries = true, cacheNames = {"userFeeds" })
  @Scheduled(fixedDelay = 150, timeUnit = TimeUnit.SECONDS)
  public void cacheEvict() {
  }
}
