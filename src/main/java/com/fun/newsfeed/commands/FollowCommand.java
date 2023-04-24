package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import com.fun.newsfeed.user.UserController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FollowCommand implements UserCommand {

  private final UserController userController;

  public FollowCommand(UserController userController) {
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
    System.out.println("Enter Followee username*");
    String username = scanner.nextLine();
    userController.follow(username);
    System.out.print(ColorConstants.GREEN_BOLD);
    System.out.println("You started following "+ username);
  }
  @Override
  public CommandType supportedCommandType() {
    return CommandType.FOLLOW;
  }
}
