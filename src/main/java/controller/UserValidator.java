package controller;

import connection.DataBaseConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidator {
    Connection connection;

    public boolean isValidUser(String login, String pass) {
        connection = DataBaseConnection.getInstance().getConnection();
        boolean valid = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userPass = rs.getString("password");
                if (userPass.equals(pass)) {
                    valid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

    public boolean isValidLogin(String login) {
        connection = DataBaseConnection.getInstance().getConnection();
        boolean find = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                find = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }
}
