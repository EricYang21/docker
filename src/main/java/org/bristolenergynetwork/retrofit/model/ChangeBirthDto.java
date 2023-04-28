package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class ChangeBirthDto {
  private String birth;
  private Integer userId;

  public ChangeBirthDto(String birth, Integer userId) {
    this.birth = birth;
    this.userId = userId;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
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
    ChangeBirthDto that = (ChangeBirthDto) o;
    return Objects.equals(birth, that.birth) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(birth, userId);
  }

  @Override
  public String toString() {
    return "ChangeBirthDto{" + "birth='" + birth + '\'' + ", userId=" + userId + '}';
  }
}
