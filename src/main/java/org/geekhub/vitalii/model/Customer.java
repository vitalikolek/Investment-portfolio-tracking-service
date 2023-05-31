package org.geekhub.vitalii.model;

import java.time.LocalDate;

public class Customer {

    private String username;
    private String password;
    private String email;
    private CustomerRole role;
    private LocalDate creationTime;

    public Customer() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRole() {
        return role.getRole();
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(CustomerRole role) {
        this.role = role;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    public void generateCreationTime() {
        this.creationTime = LocalDate.now();
    }
}