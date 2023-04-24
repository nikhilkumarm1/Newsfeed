package com.fun.newsfeed.post;

import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.entity.UserPostEntity;
import com.fun.newsfeed.entity.UserVoteActivityEntity;
import com.fun.newsfeed.post.exception.PostFailureException;
import com.fun.newsfeed.post.repo.UserPostContentRepository;
import com.fun.newsfeed.post.repo.UserPostRepository;
import com.fun.newsfeed.post.repo.UserVoteActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPostService {
  private final UserPostRepository userPostRepository;
  private final UserPostContentRepository contentRepository;
  private final UserVoteActivityRepository userVoteActivityRepository;

  public UserPostService(
      UserPostRepository userPostRepository,
      UserPostContentRepository contentRepository,
      UserVoteActivityRepository userVoteActivityRepository) {
    this.userPostRepository = userPostRepository;
    this.contentRepository = contentRepository;
    this.userVoteActivityRepository = userVoteActivityRepository;
  }

  @Transactional
  public long addUserPost(UserPostEntity userPostEntity) throws PostFailureException {
    try {
      UserPostEntity savedEntity = userPostRepository.save(userPostEntity);

      // update the contents with post id
      userPostEntity.getContentList().stream().forEach(e -> e.setUserPostEntity(savedEntity));
      contentRepository.saveAll(userPostEntity.getContentList());

      //optimization for root post id, need for computing total comments
      if (userPostEntity.getParentPost() == null) {
        //copying the same entity as a root entity if it's a post
        savedEntity.setRootPost(savedEntity);
      } else {
        UserPostEntity rootEntity = userPostRepository.findById(savedEntity.getParentPost().getId()).get().getRootPost();
        //copy the root entity from the parent if it's a comment
        savedEntity.setRootPost(rootEntity);
        userPostRepository.incrementCommentCount(rootEntity.getId());
      }
      userPostRepository.save(savedEntity);

      return savedEntity.getId();

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new PostFailureException("Error while saving user post", ex);
    }
  }

  @Transactional
  public void vote(String username, long postId, boolean like) {
    if (like) {
      userPostRepository.incrementUpVotes(postId);
    } else {
      userPostRepository.incrementDownVotes(postId);
    }
    UserVoteActivityEntity entity = new UserVoteActivityEntity();
    UserEntity user = new UserEntity();
    user.setUserName(username);
    entity.setUserEntity(user);
    UserPostEntity userPostEntity = new UserPostEntity();
    userPostEntity.setId(postId);
    entity.setUserPostEntity(userPostEntity);
    entity.setUpVote(like);
    userVoteActivityRepository.save(entity);
  }
}
