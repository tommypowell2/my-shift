package com.powell.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tpowell on 10/9/16.
 * -_-
 */
@Repository
public class AdminDAOJDBC implements AdminDAO {
    private DataSource dataSource;

    @Autowired
    public AdminDAOJDBC(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullPointerException("Datasource was not initialized");
        }
        this.dataSource = dataSource;

    }

    public boolean validateUser(String userName, String password) throws SQLException {
        boolean validated = false;
        String sql = "select Count(*) as ROWS from SHIFT_USER where username = ? and password = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("ROWS") == 1)
                    validated = true;
            }
        }
        return validated;
    }

    @Override
    public int getCompanyID(String username) throws SQLException {
        String sql = "select companyid from shift_admin_users where username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("companyid");
            }
        }
        return 0;
    }
}
