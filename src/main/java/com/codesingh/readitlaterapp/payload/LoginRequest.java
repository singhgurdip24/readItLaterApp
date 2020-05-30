package com.codesingh.readitlaterapp.payload;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginRequest implements Serializable {

  @NotBlank
  private String userOrEmail;

  @NotBlank
  private String password;

  public String getUserOrEmail() {
    return userOrEmail;
  }

  public void setUserOrEmail(String userOrEmail) {
    this.userOrEmail = userOrEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
