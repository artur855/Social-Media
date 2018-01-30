/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arthurzera.website.forms;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public class RegisterForm {

    @Size(min = 6, max = 15, message = "Username size should have more them 6 and less than 15 characters")
    private String username;

    @Size(min = 0, max = 40, message = "Fullname size should have more them 2 and less than 40 characters")
    private String fullName;

    @Email
    private String email;

    @Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
    private String password;

    @Size(min = 6, max = 12, message = "Password size should have more them 6 and less than 12 charcters")
    private String confirmPassword;

    @Override
    public String toString() {
        return "RegisterForm{" + "username=" + username + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
