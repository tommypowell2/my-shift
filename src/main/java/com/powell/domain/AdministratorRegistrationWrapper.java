package com.powell.domain;

import com.powell.security.domain.*;
import com.powell.security.domain.User;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class AdministratorRegistrationWrapper {
    private final com.powell.security.domain.User administrator;

    public AdministratorRegistrationWrapper(User administrator) {
        this.administrator = administrator;
    }

    public com.powell.security.domain.User getAdministrator() {

        return new com.powell.security.domain.User(administrator.getFirstName(), administrator.getLastName(), administrator.getUserName(),
                administrator.getPassword(), administrator.getCompany(), administrator.getPosition());
    }
}
