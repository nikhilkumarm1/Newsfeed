package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ContentType;
import com.fun.newsfeed.feed.UserFeedController;
import com.fun.newsfeed.feed.dto.UserFeedDto;
import com.fun.newsfeed.post.dto.BinaryDto;
import com.fun.newsfeed.post.dto.ContentDto;
import com.fun.newsfeed.post.dto.TextDto;
import com.ocpsoft.pretty.time.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ShowNewsFeedCommand implements UserCommand {

  private final UserFeedController userFeedController;

  private final PrettyTime prettyTime;

  @Autowired
  public ShowNewsFeedCommand(UserFeedController userFeedController) {
    this.userFeedController = userFeedController;
    this.prettyTime = new PrettyTime();
  }

  @Override
  public boolean canExecute(String input) {
    return supportedCommandType().getCommandType().equals(input);
  }
  @Override
  public void execute() {
    int page = 0;
    while (true) {
      List<UserFeedDto> userFeedDtoList = userFeedController.getUserFeeds(page);
      System.out.println("===============");
      for (UserFeedDto userFeedDto : userFeedDtoList) {
        System.out.println("Post Id: " + userFeedDto.getPostId());
        System.out.println("Posted By: " + userFeedDto.getPostedBy());
        System.out.println("Posted Time: " + prettyTime.format(userFeedDto.getCreatedTime()));
        System.out.println("Up Votes: " + userFeedDto.getUpVotes());
        System.out.println("Down Votes: " + userFeedDto.getDownVotes());
        System.out.println("Total Comments: " + userFeedDto.getTotalComments());
        for (ContentDto contentDto : userFeedDto.getContents()) {
          if (contentDto.getContentType() == ContentType.TEXT) {
            System.out.println("Content: " + ((TextDto) contentDto).getText());
          } else if (contentDto.getContentType() == ContentType.BINARY) {
            System.out.println("Link: " + ((BinaryDto) contentDto).getLink());
            System.out.println("Caption: " + ((BinaryDto) contentDto).getCaption());
          }
        }
        System.out.println("===============");
      }
      System.out.println("Need more? (true/false)");
      Scanner scanner = new Scanner(System.in);
      Boolean needMore = Boolean.valueOf(scanner.nextLine());
      if (!needMore || userFeedDtoList.isEmpty()) {
        break;
      }
      page++;
    }
  }
  @Override
  public CommandType supportedCommandType() {
    return CommandType.SHOW_NEWS_FEED;
  }
}
