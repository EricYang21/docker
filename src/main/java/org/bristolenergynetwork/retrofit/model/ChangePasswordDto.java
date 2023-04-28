package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class ChangePasswordDto {
  private String password;
  private Integer userId;

  public ChangePasswordDto(String password, Integer userId) {
    this.password = password;
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    ChangePasswordDto that = (ChangePasswordDto) o;
    return Objects.equals(password, that.password) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, userId);
  }

  @Override
  public String toString() {
    return "ChangePasswordDto{" + "password='" + password + '\'' + ", userId=" + userId + '}';
  }
}
