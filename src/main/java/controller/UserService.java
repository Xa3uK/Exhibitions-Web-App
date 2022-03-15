package controller;

import connection.DataBaseConnection;
import model.UserDao;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
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

    public String userRole(String login){
        connection = DataBaseConnection.getInstance().getConnection();
       String role = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public void insertMoney(int money, int userId){
        connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.ADD_MONEY);
            stmt.setInt(1, money);
            stmt.setInt(2, userId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDao getUser(String login){
        connection = DataBaseConnection.getInstance().getConnection();
        UserDao user = new UserDao();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt("id"));
                user.setLogin(login);
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setMoney(rs.getInt("money"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}