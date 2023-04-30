package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;
import lombok.Data;

@Data
public class ChangeAvatarDto {
  private String avatar;
  private Integer userId;

  public ChangeAvatarDto(String avatar, Integer userId) {
    this.avatar = avatar;
    this.userId = userId;
  }

  public ChangeAvatarDto() {}

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
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
    ChangeAvatarDto that = (ChangeAvatarDto) o;
    return Objects.equals(avatar, that.avatar) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(avatar, userId);
  }

  @Override
  public String toString() {
    return "ChangeAvatarDto{" + "avatar='" + avatar + '\'' + ", userId=" + userId + '}';
  }
}
