package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Account;

public class LoginInfoDTO {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private Account.Role role;
    public int getId() {
        return id;
    }

    public Account.Role getRole() {
        return role;
    }

    public void setRole(Account.Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public LoginInfoDTO(int id, String fullName, Account.Role role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }

    public LoginInfoDTO(int id, String fullName, String email, String phoneNumber, Account.Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public LoginInfoDTO(int id, String fullName, String email, Account.Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
