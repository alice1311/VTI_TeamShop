package com.vti.finalexam.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.finalexam.entity.Account;

import java.time.LocalDate;
import java.util.Date;

public class AccountDTO {
    private String username;
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;
    private Account.Role role;

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return lastName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Account.Role getRole() {
        return role;
    }

    public AccountDTO(String username, String fullName, Date createdDate, Account.Role role) {
        this.username = username;
        this.lastName = fullName;
        this.createdDate = createdDate;
        this.role = role;
    }

    public AccountDTO() {
    }
}
