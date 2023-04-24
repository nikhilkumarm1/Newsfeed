package com.fun.newsfeed;

import com.fun.newsfeed.commands.CommandRegistry;
import com.fun.newsfeed.commands.CommandType;
import com.fun.newsfeed.common.ColorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NewsFeedCommandLineRunner implements CommandLineRunner {

  private final CommandRegistry commandRegistry;

  @Autowired
  public NewsFeedCommandLineRunner(CommandRegistry commandRegistry) {
    this.commandRegistry = commandRegistry;
  }

  @Override
  public void run(String... args) {
    Scanner scn = new Scanner(System.in);
    chooseCommand();
    while (true) {
      String input = scn.nextLine();
      if (commandRegistry.supportedCommandTypes().contains(input)) {
        try {
          commandRegistry.execute(input);
        } catch (Exception ex) {
          System.out.println("Error processing: " + ex.getMessage());
        }
      }
      chooseCommand();
    }
  }

  private void chooseCommand() {
    System.out.print(ColorConstants.GREEN_BOLD);
    System.out.println("Choose one from below: ");
    commandRegistry.supportedCommandTypes().stream().forEach(System.out::println);
    System.out.print(ColorConstants.ANSI_RESET);
  }
}
