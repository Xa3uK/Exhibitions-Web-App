package controller;

import connection.DataBaseConnection;
import model.UserDao;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    DataBaseConnection dbcon = DataBaseConnection.getInstance();

    public boolean registration(String login, String pass, String email) {
       Connection connection = dbcon.getConnection();
        boolean isRegister = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.REGISTER_USER);
            stmt.setString(1, login);
            stmt.setString(2, pass);
            stmt.setString(3, email);
            stmt.setString(4, "user");
            isRegister = stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return isRegister;
    }

    public boolean isValidUser(String login, String pass) {
        Connection connection = dbcon.getConnection();
        boolean isValid = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userPass = rs.getString("password");
                if (userPass.equals(pass)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return isValid;
    }

    public boolean isValidLogin(String login) {
        Connection connection = dbcon.getConnection();
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
        } finally {
            dbcon.releaseConnection(connection);
        }
        return find;
    }

    public void depositMoney(int money, int userId) {
        Connection connection = dbcon.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.ADD_MONEY);
            stmt.setInt(1, money);
            stmt.setInt(2, userId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
    }

    public UserDao getUser(String login) {
        Connection connection = dbcon.getConnection();
        UserDao user = new UserDao();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(login);
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setMoney(rs.getInt("money"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return user;
    }
}
