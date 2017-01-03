package com.powell.dao;

import java.sql.SQLException;

/**
 * Created by tpowell on 12/18/16.
 * -_-
 */
public interface AdminDAO {
    int getCompanyID(String username) throws SQLException;
}
