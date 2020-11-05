package ru.stqa.pft.addressbook;

public class ProfileData {
  private final String firstname;
  private final String lastname;
  private final String homephone;
  private final String email;

  public ProfileData(String firstname, String lastname, String homephone, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.homephone = homephone;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getEmail() {
    return email;
  }
}
