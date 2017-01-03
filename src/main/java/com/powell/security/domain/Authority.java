package com.powell.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tpowell on 1/2/17.
 * -_-
 */
@Entity
@Table(name = "AUTHORITIES")
public class Authority implements Serializable {

    @Id
    private String username;

    @Column
    private String authority;

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }
}
