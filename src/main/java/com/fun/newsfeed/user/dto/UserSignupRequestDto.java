package com.fun.newsfeed.user.dto;

import com.fun.newsfeed.common.Gender;
import com.fun.newsfeed.exception.NotValidException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserSignupRequestDto {
  private String userName;
  private String firstName;
  private String lastName;
  private int age;
  private Gender gender;
  private String password;

  private UserSignupRequestDto(Builder builder) {
    userName = builder.userName;
    firstName = builder.firstName;
    lastName = builder.lastName;
    age = builder.age;
    gender = builder.gender;
    password = builder.password;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String userName;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String password;

    private Builder() {
    }

    public Builder withUserName(String userName) {
      this.userName = userName;
      return this;
    }

    public Builder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder withAge(int age) {
      this.age = age;
      return this;
    }

    public Builder withGender(Gender gender) {
      this.gender = gender;
      return this;
    }

    public Builder withPassword(String password) {
      this.password = password;
      return this;
    }
    private void validate() throws NotValidException {
      isNull(userName, firstName, lastName, age, gender, password);
    }
    private void isNull(Object... objs) throws NotValidException {
      for (Object object: objs){
        if (object == null){
          throw new NotValidException("Required field is null");
        }
      }
    }
    public UserSignupRequestDto build() throws NotValidException {
      validate();
      return new UserSignupRequestDto(this);
    }
  }
}
