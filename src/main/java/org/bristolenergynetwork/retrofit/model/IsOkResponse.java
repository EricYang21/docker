package org.bristolenergynetwork.retrofit.model;

public class IsOkResponse {
  private Integer userId;
  private Boolean isOk;

  public IsOkResponse(Integer userId, Boolean isOk) {
    this.userId = userId;
    this.isOk = isOk;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Boolean getIsOk() {
    return isOk;
  }

  public void setIsOk(Boolean isOk) {
    this.isOk = isOk;
  }

  @Override
  public String toString() {
    return "LoginOrSignUpResponse{" + "userId=" + userId + ", IsOk=" + isOk + '}';
  }
}
