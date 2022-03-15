package controller;

import connection.DataBaseConnection;
import model.ExhibitionDao;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionService {
    Connection connection;

    public int add(ExhibitionDao ex) {
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

    public void delete(int id) {
        connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.DEL_EXHIBITION);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ExhibitionDao> getExhibitions() {
        connection = DataBaseConnection.getInstance().getConnection();
        List<ExhibitionDao> exhibitions = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.FIND_ALL_EXHIBITIONS);
            while (rs.next()) {
                ExhibitionDao exhibition = new ExhibitionDao();
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

    public List<ExhibitionDao> getExhibitionsStat() {
        connection = DataBaseConnection.getInstance().getConnection();
        List<ExhibitionDao> exhibitions = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.CHECK_EXHIBITION_STATISTICS);
            while (rs.next()) {
                ExhibitionDao exhibition = new ExhibitionDao();
                exhibition.setTheme(rs.getString("theme"));
                exhibition.setHall(rs.getString("hall"));
                exhibition.setStartDate(rs.getDate("start_date").toLocalDate());
                exhibition.setEndDate(rs.getDate("end_date").toLocalDate());
                exhibition.setStartTime(rs.getTime("start_time"));
                exhibition.setEndTime(rs.getTime("end_time"));
                exhibition.setPrice(rs.getInt("price"));
                exhibition.setSoldTickets(rs.getInt("sold_tickets"));
                exhibitions.add(exhibition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exhibitions;
    }

    public void buyTicket(int exId, int userId, int exPrice) {
        connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.BUY_TICKET);
            stmt.setInt(1, userId);
            stmt.setInt(2, exId);
            stmt.execute();
            debitMoney(exPrice, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkAmount(int userId) {
        connection = DataBaseConnection.getInstance().getConnection();
        int money = -1;
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.CHECK_AMOUNT_MONEY);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                money = rs.getInt("Money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    public void debitMoney(int money, int userId){
        connection = DataBaseConnection.getInstance().getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.DEBIT_MONEY);
            stmt.setInt(1, money);
            stmt.setInt(2, userId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
