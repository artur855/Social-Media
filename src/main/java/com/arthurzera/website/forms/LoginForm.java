package com.arthurzera.website.forms;

import javax.validation.constraints.Size;

import com.arthurzera.website.validators.ValidPassword;

public class LoginForm {

    @Size(min = 6, max = 15, message = "Username size should have more them 6 and less than 15 characters")
    private String username;

    @ValidPassword
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
