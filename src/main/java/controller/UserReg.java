package controller;

import connection.DataBaseConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserReg {
    Connection connection;

    public boolean Registration(String login, String pass, String email){
        connection = DataBaseConnection.getInstance().getConnection();
        boolean register = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.REGISTER_USER);
            stmt.setString(1, login);
            stmt.setString(2, pass);
            stmt.setString(3, email);
            stmt.setString(4, "user");
            register = stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return register;
    }
}
