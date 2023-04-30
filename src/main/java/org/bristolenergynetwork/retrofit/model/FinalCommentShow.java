package org.bristolenergynetwork.retrofit.model;

import java.util.List;
import java.util.Objects;

public class FinalCommentShow {
  private List<CommentAndUser> commentAndUserList;
  private Graph graph;

  public FinalCommentShow(List<CommentAndUser> commentAndUserList, Graph graph) {
    this.commentAndUserList = commentAndUserList;
    this.graph = graph;
  }

  public List<CommentAndUser> getCommentAndUserList() {
    return commentAndUserList;
  }

  public void setCommentAndUserList(List<CommentAndUser> commentAndUserList) {
    this.commentAndUserList = commentAndUserList;
  }

  public Graph getGraph() {
    return graph;
  }

  public void setGraph(Graph graph) {
    this.graph = graph;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FinalCommentShow that = (FinalCommentShow) o;
    return Objects.equals(commentAndUserList, that.commentAndUserList)
        && Objects.equals(graph, that.graph);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentAndUserList, graph);
  }

  @Override
  public String toString() {
    return "FinalCommentShow{"
        + "commentAndUserList="
        + commentAndUserList
        + ", graph="
        + graph
        + '}';
  }
}
