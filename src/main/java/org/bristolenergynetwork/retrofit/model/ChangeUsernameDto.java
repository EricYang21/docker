package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class ChangeUsernameDto {
  private String username;
  private Integer userId;

  public ChangeUsernameDto(String username, Integer userId) {
    this.username = username;
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChangeUsernameDto that = (ChangeUsernameDto) o;
    return Objects.equals(username, that.username) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, userId);
  }

  @Override
  public String toString() {
    return "ChangeUsernameResponse{" + "username='" + username + '\'' + ", userId=" + userId + '}';
  }
}
