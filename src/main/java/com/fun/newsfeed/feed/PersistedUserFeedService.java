package com.fun.newsfeed.feed;

import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.entity.UserFolloweeEntity;
import com.fun.newsfeed.entity.UserPostEntity;
import com.fun.newsfeed.feed.dto.UserFeedDto;
import com.fun.newsfeed.feed.repo.UserFeedRepository;
import com.fun.newsfeed.user.repo.UserFolloweeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersistedUserFeedService implements UserFeedService {
  public static final int PAGE_SIZE = 5;
  private final UserFeedRepository userFeedRepository;
  private final UserFolloweeRepository userFolloweeRepository;
  private final UserFeedTranslator userFeedTranslator;
  @Autowired
  public PersistedUserFeedService(
      UserFeedRepository userFeedRepository,
      UserFolloweeRepository userFolloweeRepository,
      UserFeedTranslator userFeedTranslator) {
    this.userFeedRepository = userFeedRepository;
    this.userFolloweeRepository = userFolloweeRepository;
    this.userFeedTranslator = userFeedTranslator;
  }

  @Override
  public List<UserFeedDto> getUserFeeds(String userName, int page) {
    System.out.println("Getting user feeds from DB");
    UserEntity user = new UserEntity();
    user.setUserName(userName);
    List<UserFolloweeEntity> followeeEntities = userFolloweeRepository.findByUserEntity(user);
    Collection<String> userFollowees = followeeEntities.stream().map(e -> e.getFolloweeEntity().getUserName()).collect(Collectors.toSet());
    List<UserPostEntity> userPostEntities = userFeedRepository.getUserFeed(userFollowees, PageRequest.of(page,
        PAGE_SIZE));
    List<UserFeedDto> userFeedDtos = userFeedTranslator.translateTo(userPostEntities);
    return userFeedDtos;
  }
}
