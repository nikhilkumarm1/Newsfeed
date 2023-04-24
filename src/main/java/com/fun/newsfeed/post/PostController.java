package com.fun.newsfeed.post;

import com.fun.newsfeed.session.SessionHolder;
import com.fun.newsfeed.entity.UserPostEntity;
import com.fun.newsfeed.post.dto.UserPostDto;
import com.fun.newsfeed.post.exception.PostFailureException;
import com.fun.newsfeed.session.SessionRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostController {

  private final UserPostService userPostService;

  private final PostTranslator postTranslator;

  @Autowired
  public PostController(UserPostService userPostService, PostTranslator postTranslator) {
    this.userPostService = userPostService;
    this.postTranslator = postTranslator;
  }

  @SessionRequired
  public long addUserPost(UserPostDto userPostDto) throws PostFailureException {
    UserPostEntity userPostEntity = postTranslator.translateTo(userPostDto);
    userPostEntity.getUserEntity().setUserName(SessionHolder.getSession().getUsername());
    return userPostService.addUserPost(userPostEntity);
  }

  @SessionRequired
  public void vote(long postId, boolean like) {
    userPostService.vote(SessionHolder.getSession().getUsername(), postId, like);
  }
}
