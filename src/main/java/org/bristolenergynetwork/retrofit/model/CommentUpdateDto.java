package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class CommentUpdateDto {
  private Integer commentId;
  private String content;

  private Integer userId;

  public CommentUpdateDto(Integer commentId, String content, Integer userId) {
    this.commentId = commentId;
    this.content = content;
    this.userId = userId;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
    CommentUpdateDto that = (CommentUpdateDto) o;
    return Objects.equals(commentId, that.commentId)
        && Objects.equals(content, that.content)
        && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId, content, userId);
  }

  @Override
  public String toString() {
    return "CommentUpdateDto{"
        + "commentId="
        + commentId
        + ", content='"
        + content
        + '\''
        + ", userId="
        + userId
        + '}';
  }
}
