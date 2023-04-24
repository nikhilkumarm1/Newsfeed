package com.fun.newsfeed.session;

public class SessionHolder {
  private static Session session;

  public static void setSession(Session session) {
    SessionHolder.session = session;
  }

  public static Session getSession() {
    return session;
  }

  public static void invalidate() {
    session = null;
  }

}
