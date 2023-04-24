package com.fun.newsfeed.entity;

import com.fun.newsfeed.common.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class UserEntity extends BaseEntity {
  @Id
  private String userName;
  private String firstName;
  private String lastName;
  private int age;
  private Gender gender;
  private String passwordEncoded;
  private Date creationTime;
  private Date updationTime;

}
