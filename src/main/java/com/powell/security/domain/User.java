package com.powell.security.domain;

import com.powell.domain.Company;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="SHIFT_USER")
public class User implements Serializable {
    @Id @Column(name="USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="username")
    private String userName;

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

    @Transient
    private Company company;

    public User() { }

    public User(Long id, String userName, String password, List<Authority> authorities) {
        this.userID = id;
        this.userName = userName;
        this.password = password;
        this.userAuthorities = authorities;
    }

    public User(String firstName, String lastName, String username, String password, Company company, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        if (StringUtils.isBlank(password)) {
            this.password = null;
        } else {
            this.password = new BCryptPasswordEncoder().encode(password);
        }
        this.company = company;
        this.position = position;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public int getCompanyID() {
        return companyID;
    }

    public Company getCompany(){return company; }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public List<Authority> getUserAuthorities() {
        return userAuthorities;
    }
}
