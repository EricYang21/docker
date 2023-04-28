package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class Graph {
  private String longitude;
  private String latitude;

  public Graph(String longitude, String latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Graph graph = (Graph) o;
    return Objects.equals(longitude, graph.longitude) && Objects.equals(latitude, graph.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }

  @Override
  public String toString() {
    return "Graph{" + "longitude='" + longitude + '\'' + ", latitude='" + latitude + '\'' + '}';
  }
}
