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
@Table(name="SHIFT_ADMIN_USERS")
public class User implements Serializable {
    @Id @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column
    private int companyID;

    @Column
    private boolean enabled;

    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles;

    @OneToMany
    @JoinColumn(name="USERNAME", referencedColumnName="USERNAME")
    private List<Authority> userAuthorities;

    public User() { }

    public User(Long id, String username, String password, List<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userAuthorities = authorities;
    }

    public Long getId() {
        return id;
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

//    public List<UserRole> getRoles() {
//        return roles;
//    }

    public List<Authority> getUserAuthorities() {
        return userAuthorities;
    }
}
