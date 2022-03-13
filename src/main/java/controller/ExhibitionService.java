package controller;

import connection.DataBaseConnection;
import model.Exhibition;

import java.sql.*;

public class ExhibitionService {
    Connection connection;

    public void add(Exhibition ex) {
        connection = DataBaseConnection.getInstance().getConnection();
        String sql = "insert into exhibitions (theme, hall, start_date, end_date, " +
                "start_time, end_time, price) values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ex.getTheme());
            stmt.setString(2, ex.getHall());
            stmt.setDate(3, Date.valueOf(ex.getStartDate()));
            stmt.setDate(4, Date.valueOf(ex.getEndDate()));
            stmt.setTime(5, ex.getStartTime());
            stmt.setTime(6, ex.getEndTime());
            stmt.setInt(7, ex.getPrice());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
