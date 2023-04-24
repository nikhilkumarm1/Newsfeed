package com.fun.newsfeed.user;


import com.fun.newsfeed.session.Session;
import com.fun.newsfeed.session.SessionHolder;
import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.session.SessionRequired;
import com.fun.newsfeed.user.exception.UserAlreadyExists;
import com.fun.newsfeed.user.dto.UserSignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserController {
  private final UserTranslator userTranslator;
  private final UserService userService;
  private final UserFolloweeService userFolloweeService;

  @Autowired
  public UserController(
      UserTranslator userTranslator, UserService userService, UserFolloweeService userFolloweeService) {
    this.userTranslator = userTranslator;
    this.userService = userService;
    this.userFolloweeService = userFolloweeService;
  }

  public void signup(UserSignupRequestDto requestDto) throws UserAlreadyExists {
    UserEntity user = userTranslator.translateTo(requestDto);
    userService.registerUser(user);
  }

  public boolean login(String username, String password) {
    boolean valid = userService.validateUser(username, password);
    if (valid) {
      SessionHolder.setSession(new Session(username));
    }
    return valid;
  }

  public void logout() {
    SessionHolder.invalidate();
  }

  @SessionRequired
  public void follow(String followeeId) {
    userFolloweeService.addFollowee(SessionHolder.getSession().getUsername(), followeeId);
  }

}
