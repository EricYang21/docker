package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class Request {
  public String postcode;
  public String task;
  public String source;

  public Request(String postcode, String task, String source) {
    this.postcode = postcode;
    this.task = task;
    this.source = source;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Request request = (Request) o;
    return Objects.equals(postcode, request.postcode)
        && Objects.equals(task, request.task)
        && Objects.equals(source, request.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postcode, task, source);
  }

  @Override
  public String toString() {
    return "Request{"
        + "postcode='"
        + postcode
        + '\''
        + ", task='"
        + task
        + '\''
        + ", source='"
        + source
        + '\''
        + '}';
  }
}
