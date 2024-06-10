package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Account;

import java.util.Date;

public class AdminDTO {
    private int id;

    private String username;
    private String address;
    private Date birthday;
    private String email;
    private Date createdDate;
    private Account.Gender gender;

    public AdminDTO( int id, String username, String address, Date birthday, String email,  Date createdDate, Account.Gender gender) {
        this.id=id;
        this.username = username;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.createdDate = createdDate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Account.Gender getGender() {
        return gender;
    }

    public void setGender(Account.Gender gender) {
        this.gender = gender;
    }
}