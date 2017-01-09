package com.powell.dao;

import com.powell.domain.Administrator;
import com.powell.domain.Company;
import com.powell.domain.Employee;
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

    @Autowired
    public RegistrationDAOJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void registerEmployee(Employee employee) throws SQLException {
        String insertEmployeeSQL = "INSERT INTO Employee (FirstName, LastName, UserName, POSITION, CompanyID)" +
                "VALUES (? , ?, ?, ?, ?)";
        String insertAuthSQL = "INSERT INTO authorities (username, authority) values (?, ?)";
        String selectIDSQL = "select employeeID from employee where username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertEmployeeStatement = connection.prepareStatement(insertEmployeeSQL);
             PreparedStatement insertAuthStatement = connection.prepareStatement(insertAuthSQL);
             PreparedStatement selectIDStatement1 = connection.prepareStatement(selectIDSQL)) {
            insertEmployee(employee, insertEmployeeStatement);
            insertEmployeeAuthorization(employee, insertAuthStatement);
            setEmployeeIdFromDatabase(employee, selectIDStatement1);
        }
    }

    private void setEmployeeIdFromDatabase(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            employee.setEmployeeID(resultSet.getLong("EmployeeID"));
        }
    }

    private void insertEmployeeAuthorization(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getUserName());
        preparedStatement.setString(2, "employee");
        preparedStatement.execute();
    }

    private void insertEmployee(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getUserName());
        preparedStatement.setString(4, employee.getPosition());
        preparedStatement.setInt(5, employee.getCompanyID());
        preparedStatement.execute();
    }

    @Override
    public void registerAdmin(Administrator administrator) throws SQLException {
        String authoritySQL = "insert into authorities(username,authority) values(?,'admin')";
        String companyInsertSQL = "insert into company (name) values(?)";
        String companySelectSQL = "select id from company where name = ?";
        String userSQL = "insert into shift_user (username,password,companyid,enabled) values(?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement authorityStatement = connection.prepareStatement(authoritySQL);
             PreparedStatement companyInsertStatement = connection.prepareStatement(companyInsertSQL);
             PreparedStatement companySelectStatement = connection.prepareStatement(companySelectSQL);
             PreparedStatement userStatement = connection.prepareStatement(userSQL)) {

            insertCompany(administrator, companyInsertStatement);
            setAdminCompanyID(administrator, companySelectStatement);
            setAdminAuth(administrator, authorityStatement);
            insertAdminUser(administrator, userStatement);

        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }

    }

    private void insertAdminUser(Administrator administrator, PreparedStatement userStatement) throws SQLException {
        userStatement.setString(1, administrator.getUserName());
        userStatement.setString(2, administrator.getPassword());
        userStatement.setInt(3, administrator.getCompany().getCompanyID());
        userStatement.setBoolean(4, true);
        userStatement.execute();
    }

    private void setAdminAuth(Administrator administrator, PreparedStatement authorityStatement) throws SQLException {
        authorityStatement.setString(1, administrator.getUserName());
        authorityStatement.execute();
    }

    private void setAdminCompanyID(Administrator administrator, PreparedStatement companySelectStatement) throws SQLException {
        companySelectStatement.setString(1, administrator.getCompany().getCompanyName());
        ResultSet companyResultSet = companySelectStatement.executeQuery();
        while (companyResultSet.next()) {
            administrator.setCompany(new Company(administrator.getCompany().getCompanyName(), companyResultSet.getInt("id")));
        }
    }

    private void insertCompany(Administrator administrator, PreparedStatement companyInsertStatement) throws SQLException {
        companyInsertStatement.setString(1, administrator.getCompany().getCompanyName());
        companyInsertStatement.execute();
    }
}
