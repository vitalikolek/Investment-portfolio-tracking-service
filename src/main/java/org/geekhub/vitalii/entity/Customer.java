package org.geekhub.vitalii.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private Role role;
    @Column(name = "creation_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationTime;

    public Customer() {
    }

    public Customer(Long id, String username, String email, String password, Role role, Date creationTime) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
