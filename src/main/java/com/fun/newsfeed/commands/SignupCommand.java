package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import com.fun.newsfeed.common.Gender;
import com.fun.newsfeed.exception.NotValidException;
import com.fun.newsfeed.user.UserController;
import com.fun.newsfeed.user.exception.UserAlreadyExists;
import com.fun.newsfeed.user.dto.UserSignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SignupCommand implements UserCommand {
  private final UserController userController;

  @Autowired
  public SignupCommand(UserController userController) {
    this.userController = userController;
  }

  @Override
  public boolean canExecute(String input) {
    return supportedCommandType().getCommandType().equals(input);
  }

  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    UserSignupRequestDto.Builder requestDtoBuilder = UserSignupRequestDto.newBuilder();
    System.out.print(ColorConstants.ANSI_BLUE);
    System.out.println("Enter Username*");
    String userName = scanner.nextLine();
    requestDtoBuilder.withUserName(userName);
    System.out.println("Enter first name*");
    requestDtoBuilder.withFirstName(scanner.nextLine());
    System.out.println("Enter last name*");
    requestDtoBuilder.withLastName(scanner.nextLine());
    System.out.println("Enter age*");
    requestDtoBuilder.withAge(scanner.nextInt());
    System.out.println("Enter gender (MALE/FEMALE)*");
    requestDtoBuilder.withGender(Gender.valueOf(scanner.next()));
    System.out.println("Enter password*");
    requestDtoBuilder.withPassword(scanner.next());
    System.out.print(ColorConstants.ANSI_RESET);
    try {
      userController.signup(requestDtoBuilder.build());
    } catch (UserAlreadyExists e) {
      System.out.println(ColorConstants.ANSI_RED + "Username already exists" + ColorConstants.ANSI_RESET);
    }  catch (NotValidException e) {
      e.printStackTrace();
      System.out.println(ColorConstants.ANSI_RED + "Validation Error" + ColorConstants.ANSI_RESET);
    }
    System.out.println(ColorConstants.ANSI_GREEN
        + "Successfully registered user with username: "+ userName + ColorConstants.ANSI_RESET);
  }

  @Override
  public CommandType supportedCommandType() {
    return CommandType.SIGNUP;
  }
}
