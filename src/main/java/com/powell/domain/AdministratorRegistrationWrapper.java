package com.powell.domain;

/**
 * Created by tpowell on 12/17/16.
 * -_-
 */
public class AdministratorRegistrationWrapper {
    private final Administrator administrator;

    public AdministratorRegistrationWrapper(Administrator administrator) {
        this.administrator = administrator;
    }

    public Administrator getAdministrator() {

        return new Administrator(administrator.getFirstName(), administrator.getLastName(), administrator.getUserName(),
                administrator.getPassword(), administrator.getCompany());
    }
}
