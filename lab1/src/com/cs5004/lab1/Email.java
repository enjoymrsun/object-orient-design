package com.cs5004.lab1;

/**
 * This class represents an email. An email has a username and a domain name.
 */
public class Email {
  private String username;
  private String domain;

  /**
   * Constructs a com.cs5004.lab1.Email object and initializes it to the given email address.
   *
   * @param address  the email address
   */
  public Email(String address) {
    String[] es = address.split("@");
    this.username = es[0];
    this.domain = es[1];
  }

  /**
   * Get the username of this email.
   *
   * @return the username of this email
   */
  public String getUsername() {
    return username;
  }

  /**
   * Get the domain name of this email.
   *
   * @return the domain name of this email
   */
  public String getDomain() {
    return domain;
  }

  /**
   * Get the string representation of this email.
   *
   * @return the string representation of this email
   */
  @Override
  public String toString() {
    return "Email{" + "username='" + username + '\'' + ", domain='" + domain + '\'' + '}';
  }
}
