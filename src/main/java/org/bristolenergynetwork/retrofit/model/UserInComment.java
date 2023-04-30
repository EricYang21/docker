package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class UserInComment {
  private String avatar;
  private String username;

  public UserInComment(String avatar, String username) {
    this.avatar = avatar;
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserInComment that = (UserInComment) o;
    return Objects.equals(avatar, that.avatar) && Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(avatar, username);
  }

  @Override
  public String toString() {
    return "UserInComment{" + "avatar='" + avatar + '\'' + ", username='" + username + '\'' + '}';
  }
}
