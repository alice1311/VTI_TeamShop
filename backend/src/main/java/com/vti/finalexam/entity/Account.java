package com.vti.finalexam.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.TemporalType;


@Table(name = "`Account`")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "`role`", discriminatorType = DiscriminatorType.STRING)
public class Account implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    public Account(String username, String phone, String password, String firstName, String lastName, String address, Date birthday, String email, Role role, Gender gender, Date createdDate) {

        this.username = username;
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.createdDate = createdDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "`password`", length = 255, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "birthday", updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(name = "email", length = 50, nullable = false, updatable = false)
    private String email;



    public enum Gender {
        MALE, FEMALE, UNKNOWN;
    }
    @Column(name = "`role`", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;



    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;



    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.CUSTOMER;
        }

    }

    public String getAddress() {
        return address;
    }

//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public Account(int id, String username, String password, String firstName, String lastName, String address, Date birthday, String email, Role role, Gender gender, Date createdDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.createdDate = createdDate;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public enum Role {
        ADMIN, EMPLOYEE, CUSTOMER;
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
