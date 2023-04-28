package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class CommentAndUser {
  private Comment comment;
  private UserInComment userInComment;

  public CommentAndUser(Comment comment, UserInComment userInComment) {
    this.comment = comment;
    this.userInComment = userInComment;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }

  public UserInComment getUserInComment() {
    return userInComment;
  }

  public void setUserInComment(UserInComment userInComment) {
    this.userInComment = userInComment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CommentAndUser that = (CommentAndUser) o;
    return Objects.equals(comment, that.comment)
        && Objects.equals(userInComment, that.userInComment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment, userInComment);
  }

  @Override
  public String toString() {
    return "CommentAndUser{" + "comment=" + comment + ", userInComment=" + userInComment + '}';
  }
}
