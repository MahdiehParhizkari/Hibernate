package com.helman.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.*;

@Entity
@Table(name = "users")
@JsonFilter("Userfilter")
public class User {
    public User() {}

    private Integer id;
    private String username;
    private String password;
    private Integer employeefk;

    private Employee useremployee;

    @Id
    @Column(name ="Id")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "employeefk")
    public Integer getEmployeefk() {
        return employeefk;
    }
    public void setEmployeefk(Integer employeefk) {
        this.employeefk = employeefk;
    }

    @ManyToOne
    @JoinColumn(name = "employeefk", referencedColumnName ="employeeNumber", insertable = false, updatable = false )
    public Employee getEmployee() {
        return useremployee;
    }
    public void setEmployee(Employee useremployee) {
        this.useremployee = useremployee;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", employeefk=" + employeefk +
                ", useremployee=" + useremployee +
                '}';
    }
}