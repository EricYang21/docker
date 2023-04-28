package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class CommentDeleteDto {
  private Integer commentId;
  private Integer userId;

  public CommentDeleteDto(Integer commentId, Integer userId) {
    this.commentId = commentId;
    this.userId = userId;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
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
    CommentDeleteDto that = (CommentDeleteDto) o;
    return Objects.equals(commentId, that.commentId) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, userId);
  }

  @Override
  public String toString() {
    return "CommentDeleteDto{" + "commentId=" + commentId + ", userId=" + userId + '}';
  }
}
