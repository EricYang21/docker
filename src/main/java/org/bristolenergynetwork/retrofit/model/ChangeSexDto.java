package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class ChangeSexDto {

  private String sex;
  private Integer userId;

  public ChangeSexDto(String sex, Integer userId) {
    this.sex = sex;
    this.userId = userId;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
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
    ChangeSexDto that = (ChangeSexDto) o;
    return Objects.equals(sex, that.sex) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sex, userId);
  }

  @Override
  public String toString() {
    return "changeSexDto{" + "sex='" + sex + '\'' + ", userId=" + userId + '}';
  }
}
