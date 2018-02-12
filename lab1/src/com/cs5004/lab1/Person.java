package com.cs5004.lab1;

/**
 * This class represents a person. The person has a first name, a last name, year of birth, phone
 * number and email address.
 */
public class Person {
  private String firstName;
  private String lastName;
  private int yearOfBirth;
  private String phone;
  private Email email;


  /**
   * Constructs a com.cs5004.lab1.Person object and initializes it to the given first name, last
   * name, year of birth, phone number and email address.
   *
   * @param firstName   the first name of this person
   * @param lastName    the last name of this person
   * @param yearOfBirth the year of birth of this person
   * @param phone       the phone number of this person
   * @param email       the email of this person
   */
  public Person(String firstName, String lastName, int yearOfBirth, String phone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
    this.phone = phone;
    this.email = new Email(email);
  }

  /**
   * Get the first name of this person.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Return the last name of this person.
   *
   * @return the last name of this person
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Return the year of birth of this person.
   *
   * @return the year of birth of this person
   */
  public int getYearOfBirth() {
    return this.yearOfBirth;
  }

  /**
   * Return the phone number of this person.
   *
   * @return the phone number of this person
   */
  public String getPhone() {
    return this.phone;
  }

  /**
   * Return the email of this person.
   *
   * @return the email of this person
   */
  public String getEmail() {
    return email.toString();
  }

}
