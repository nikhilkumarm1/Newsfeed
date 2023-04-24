package com.fun.newsfeed.user;

import com.fun.newsfeed.common.BaseTranslator;
import com.fun.newsfeed.entity.UserEntity;
import com.fun.newsfeed.user.dto.UserSignupRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserTranslator extends BaseTranslator<UserSignupRequestDto, UserEntity> {

  @Override
  public UserEntity translateTo(UserSignupRequestDto from) {
    UserEntity user = new UserEntity();
    user.setUserName(from.getUserName());
    user.setFirstName(from.getFirstName());
    user.setLastName(from.getLastName());
    user.setAge(from.getAge());
    user.setGender(from.getGender());
    user.setPasswordEncoded(from.getPassword());
    return user;
  }
}
