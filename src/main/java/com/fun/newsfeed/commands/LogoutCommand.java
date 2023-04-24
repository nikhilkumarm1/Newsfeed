package com.fun.newsfeed.commands;

import com.fun.newsfeed.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutCommand implements UserCommand {

  private final UserController userController;

  @Autowired
  public LogoutCommand(UserController userController) {
    this.userController = userController;
  }
  @Override
  public boolean canExecute(String input) {
    return supportedCommandType().getCommandType().equals(input);
  }
  @Override
  public void execute() {
    userController.logout();
  }

  @Override
  public CommandType supportedCommandType() {
    return CommandType.LOGOUT;
  }
}
