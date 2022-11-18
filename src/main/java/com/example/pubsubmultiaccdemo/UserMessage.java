package com.example.pubsubmultiaccdemo;

public class UserMessage {

  private final String body;

  private final String username;

  public UserMessage(String body, String username) {
    this.body = body;
    this.username = username;
  }

  public String getBody() {
    return body;
  }

  public String getUsername() {
    return username;
  }
}
