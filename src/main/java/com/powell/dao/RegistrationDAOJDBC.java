package com.powell.dao;

import com.powell.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO Employee (FirstName, LastName, UserName, POSITION)" +
                "VALUES (? , ?, ?, ?)";
        String sql1 = "select employeeID from employee where username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUserName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.execute();

            preparedStatement1.setString(1, employee.getUserName());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if(resultSet.next()){
                employee.setEmployeeID(resultSet.getLong("EmployeeID"));
            }
        }
    }
}
