package com.powell.dao;

import com.powell.domain.Administrator;
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
        String selectIDSQL = "select employeeID from employee where username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeSQL);
             PreparedStatement preparedStatement1 = connection.prepareStatement(selectIDSQL)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUserName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getCompanyID());
            preparedStatement.execute();

            preparedStatement1.setString(1, employee.getUserName());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                employee.setEmployeeID(resultSet.getLong("EmployeeID"));
            }
        }
    }

    @Override
    public void registerAdmin(Administrator administrator) throws SQLException {
        String authoritySQL = "insert into authorities(username,authority) values(?,'admin')";
        String companyInsertSQL = "insert into company (name) values(?)";
        String companySelectSQL = "select id from company where name = ?";
        String userSQL = "insert into shift_admin_users(username,password,companyid,enabled) values(?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement authorityStatement = connection.prepareStatement(authoritySQL);
             PreparedStatement companyInsertStatement = connection.prepareStatement(companyInsertSQL);
             PreparedStatement companySelectStatement = connection.prepareStatement(companySelectSQL);
             PreparedStatement userStatement = connection.prepareStatement(userSQL)) {

            companyInsertStatement.setString(1, administrator.getCompany().getCompanyName());
            companyInsertStatement.execute();

            companySelectStatement.setString(1, administrator.getCompany().getCompanyName());
            ResultSet companyResultSet = companySelectStatement.executeQuery();
            int userCompanyID = 0;
            while (companyResultSet.next()) {
                userCompanyID = companyResultSet.getInt("id");
            }

            authorityStatement.setString(1, administrator.getUserName());
            authorityStatement.execute();

            userStatement.setString(1, administrator.getUserName());
            userStatement.setString(2, administrator.getPassword());
            userStatement.setInt(3, userCompanyID);
            userStatement.setBoolean(4, true);
            userStatement.execute();

        } catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e.getMessage());
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        }

    }
}
