package com.powell.dao;

import com.powell.domain.Company;
import com.powell.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by tpowell on 11/23/16.
 * -_-
 */
@Repository
public class RegistrationDAOJDBC implements RegistrationDAO {

    private DataSource dataSource;
    private String INSERT_AUTHORITY = "insert into authorities(username,authority) values(?, ?)";
    private String INSERT_USER = "insert into shift_user (username,password,companyid,enabled, firstname, lastname, position) values(?,?,?,?,?,?,?)";

    @Autowired
    public RegistrationDAOJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void registerEmployee(User employee) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement authorityStatement = connection.prepareStatement(INSERT_AUTHORITY);
             PreparedStatement userStatement = connection.prepareStatement(INSERT_USER);
             PreparedStatement getUserIDStatement = connection.prepareStatement("select userid from shift_user where username = ?")) {
            insertUser(employee,userStatement);
            setAuthority(employee.getUserName(), authorityStatement, "employee");
            setUserIdFromDatabase(employee, getUserIDStatement);
        }
    }

    private void setUserIdFromDatabase(User employee, PreparedStatement getUserIDStatement) throws SQLException {
        getUserIDStatement.setString(1, employee.getUserName());
        ResultSet resultSet = getUserIDStatement.executeQuery();
        if(resultSet.next()){
            employee.setUserID(resultSet.getLong("userid"));
        }
    }

    @Override
    public void registerAdmin(User administrator) throws SQLException {
        String companyInsertSQL = "insert into company (name) values(?)";
        String companySelectSQL = "select id from company where name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement authorityStatement = connection.prepareStatement(INSERT_AUTHORITY);
             PreparedStatement companyInsertStatement = connection.prepareStatement(companyInsertSQL);
             PreparedStatement companySelectStatement = connection.prepareStatement(companySelectSQL);
             PreparedStatement userStatement = connection.prepareStatement(INSERT_USER)) {

            insertCompany(administrator, companyInsertStatement);
            setAdminCompanyID(administrator, companySelectStatement);
            setAuthority(administrator.getUserName(), authorityStatement, "admin");
            insertUser(administrator, userStatement);

        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }

    }

    private void insertUser(User user, PreparedStatement userStatement) throws SQLException {
        userStatement.setString(1, user.getUserName());
        userStatement.setString(2, user.getPassword());
        if(user.getCompany()!= null){
            userStatement.setInt(3, user.getCompany().getCompanyID());
        } else {
            userStatement.setInt(3, user.getCompanyID());
        }
        userStatement.setBoolean(4, true);
        userStatement.setString(5, user.getFirstName());
        userStatement.setString(6, user.getLastName());
        userStatement.setString(7, user.getPosition());
        userStatement.execute();
    }

    private void setAuthority(String userName, PreparedStatement authorityStatement, String authority) throws SQLException {
        authorityStatement.setString(1, userName);
        authorityStatement.setString(2, authority);
        authorityStatement.execute();
    }

    private void setAdminCompanyID(User administrator, PreparedStatement companySelectStatement) throws SQLException {
        companySelectStatement.setString(1, administrator.getCompany().getCompanyName());
        ResultSet companyResultSet = companySelectStatement.executeQuery();
        while (companyResultSet.next()) {
            administrator.setCompany(new Company(administrator.getCompany().getCompanyName(), companyResultSet.getInt("id")));
        }
    }

    private void insertCompany(User administrator, PreparedStatement companyInsertStatement) throws SQLException {
        companyInsertStatement.setString(1, administrator.getCompany().getCompanyName());
        companyInsertStatement.execute();
    }
}
