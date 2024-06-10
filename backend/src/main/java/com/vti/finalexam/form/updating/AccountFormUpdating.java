package com.vti.finalexam.form.updating;

import com.vti.finalexam.entity.Account;

import java.time.LocalDate;
import java.util.Date;

public class AccountFormUpdating {
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String birthday;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountFormUpdating(String password, String firstName, String lastName, String address, String birthday, String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
    }

    public AccountFormUpdating() {
    }
}
