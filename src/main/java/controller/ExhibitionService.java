package controller;

import connection.DataBaseConnection;
import model.Exhibition;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionService {
    Connection connection;

    public int add(Exhibition ex) {
        connection = DataBaseConnection.getInstance().getConnection();
        Integer id = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.ADD_EXHIBITION, new String[]{"id"});
            stmt.setString(1, ex.getTheme());
            stmt.setString(2, ex.getHall());
            stmt.setDate(3, Date.valueOf(ex.getStartDate()));
            stmt.setDate(4, Date.valueOf(ex.getEndDate()));
            stmt.setTime(5, ex.getStartTime());
            stmt.setTime(6, ex.getEndTime());
            stmt.setInt(7, ex.getPrice());
            stmt.executeUpdate();
            ResultSet gk = stmt.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void delete(int id){
        connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.DEL_EXHIBITION);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Exhibition> getExhibitions(){
        connection = DataBaseConnection.getInstance().getConnection();
        List<Exhibition> exhibitions = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.FIND_ALL_EXHIBITIONS);
            while (rs.next()){
                Exhibition exhibition = new Exhibition();
                exhibition.setId(rs.getInt("id"));
                exhibition.setTheme(rs.getString("theme"));
                exhibition.setHall(rs.getString("hall"));
                exhibition.setStartDate(rs.getDate("start_date").toLocalDate());
                exhibition.setEndDate(rs.getDate("end_date").toLocalDate());
                exhibition.setStartTime(rs.getTime("start_time"));
                exhibition.setEndTime(rs.getTime("end_time"));
                exhibition.setPrice(rs.getInt("price"));
                exhibitions.add(exhibition);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exhibitions;
    }
}
