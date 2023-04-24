package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import com.fun.newsfeed.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginCommand implements UserCommand {

  private final UserController userController;

  @Autowired
  public LoginCommand(UserController userController) {
    this.userController = userController;
  }

  @Override
  public boolean canExecute(String input) {
    return supportedCommandType().getCommandType().equals(input);
  }
  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(ColorConstants.ANSI_BLUE);
    System.out.println("Enter Username*");
    String username = scanner.nextLine();
    System.out.println("Enter Password*");
    String password = scanner.nextLine();
    boolean valid = userController.login(username, password);
    if (valid) {
      System.out.print(ColorConstants.GREEN_BOLD);
      System.out.println("Successfully Logged in, hello "+ username);
    } else {
      System.out.print(ColorConstants.ANSI_RED);
      System.out.println("Invalid credentials");
      System.out.print(ColorConstants.ANSI_RESET);
    }
  }

  @Override
  public CommandType supportedCommandType() {
    return CommandType.LOGIN;
  }
}
