package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class Contractor {
  public final Integer id;
  public final String name;
  public final String phone;
  public final String address;
  public final String certs;

  public Contractor(Integer id, String name, String phone, String certs, String address) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.certs = certs;
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contractor that = (Contractor) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(phone, that.phone)
        && Objects.equals(address, that.address)
        && Objects.equals(certs, that.certs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phone, address, certs);
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public String getCerts() {
    return certs;
  }

  @Override
  public String toString() {
    return "Contractor{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", address='"
        + address
        + '\''
        + ", certs='"
        + certs
        + '\''
        + '}';
  }
}
