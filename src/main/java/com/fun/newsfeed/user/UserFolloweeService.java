package com.fun.newsfeed.user;

import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.entity.UserFolloweeEntity;
import com.fun.newsfeed.user.observer.UserFolloweeObserver;
import com.fun.newsfeed.user.repo.UserFolloweeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFolloweeService {

  private final UserFolloweeRepository userFolloweeRepository;

  private final List<UserFolloweeObserver> observerList;

  @Autowired
  public UserFolloweeService(
      UserFolloweeRepository userFolloweeRepository, List<UserFolloweeObserver> observerList) {
    this.userFolloweeRepository = userFolloweeRepository;
    this.observerList = observerList;
  }

  public void addFollowee(String username, String followeeId) {
    UserFolloweeEntity userFolloweeEntity = new UserFolloweeEntity();
    UserEntity user = new UserEntity();
    user.setUserName(username);
    userFolloweeEntity.setUserEntity(user);
    UserEntity followee = new UserEntity();
    followee.setUserName(followeeId);
    userFolloweeEntity.setFolloweeEntity(followee);
    userFolloweeRepository.save(userFolloweeEntity);
    notifyAdd(username);
  }

  public void notifyAdd(String username) {
    for (UserFolloweeObserver observer: observerList) {
      observer.followeesChanged(username);
    }
  }

  public void notifyDelete(String username) {
    for (UserFolloweeObserver observer: observerList) {
      observer.followeesChanged(username);
    }
  }
}
