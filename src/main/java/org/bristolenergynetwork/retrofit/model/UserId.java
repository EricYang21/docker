package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class UserId {
  private Integer userId;

  public UserId(Integer userId) {
    this.userId = userId;
  }

  public UserId() {}

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
    UserId userId1 = (UserId) o;
    return Objects.equals(userId, userId1.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    return "userId{" + "userId=" + userId + '}';
  }
}
