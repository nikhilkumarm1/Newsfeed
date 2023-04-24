package com.fun.newsfeed.user;

import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.user.exception.UserAlreadyExists;
import com.fun.newsfeed.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public void registerUser(UserEntity user) throws UserAlreadyExists {
    if (userRepository.findById(user.getUserName()).isPresent()) {
      throw new UserAlreadyExists("username already exists");
    }
    userRepository.save(user);
  }

  public boolean validateUser(String username, String password) {
    return userRepository.findByUserNameAndPasswordEncoded(username, password).isPresent();
  }
}
