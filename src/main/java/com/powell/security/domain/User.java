package com.powell.security.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SHIFT_USER")
public class User implements Serializable {
    @Id @Column(name="USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="position")
    private String position;

    @Column
    private int companyID;

    @Column
    private boolean enabled;

    @OneToMany
    @JoinColumn(name="USERNAME", referencedColumnName="USERNAME")
    private List<Authority> userAuthorities;

    public User() { }

    public User(Long id, String username, String password, List<Authority> authorities) {
        this.userID = id;
        this.username = username;
        this.password = password;
        this.userAuthorities = authorities;
    }

    public Long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCompanyID() {
        return companyID;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPosition() {
        return position;
    }

    public List<Authority> getUserAuthorities() {
        return userAuthorities;
    }
}
