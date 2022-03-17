package controller;

import connection.ConnectionPool;
import model.ExhibitionDao;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionService {
    ConnectionPool dbcon = ConnectionPool.getInstance();
    private int noOfRecords;

    public int addExhibition(ExhibitionDao ex) {
        Connection connection = dbcon.getConnection();
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
        } finally {
            dbcon.releaseConnection(connection);
        }
        return id;
    }

    public void deleteExhibition(int id) {
        Connection connection = dbcon.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.DEL_EXHIBITION);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
    }

    public List<ExhibitionDao> listExhibitions(int offset, int noOfRecords) {
        Connection connection = dbcon.getConnection();
        List<ExhibitionDao> exhibitions = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.SELECT_EXHIBITIONS_LIMIT);
            stmt.setInt(1, noOfRecords);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ExhibitionDao exhibition = new ExhibitionDao();
                createExhibitionDao(rs, exhibition);
                exhibitions.add(exhibition);
            }
            rs.close();
            stmt = connection.prepareStatement(Queries.SELECT_EXHIBITIONS_COUNT);
            rs = stmt.executeQuery();
            if (rs.next())
                System.out.println(rs.getInt(1));
            this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return exhibitions;
    }

    private void createExhibitionDao(ResultSet rs, ExhibitionDao exhibition) throws SQLException {
        exhibition.setId(rs.getInt("id"));
        exhibition.setTheme(rs.getString("theme"));
        exhibition.setHall(rs.getString("hall"));
        exhibition.setStartDate(rs.getDate("start_date").toLocalDate());
        exhibition.setEndDate(rs.getDate("end_date").toLocalDate());
        exhibition.setStartTime(rs.getTime("start_time"));
        exhibition.setEndTime(rs.getTime("end_time"));
        exhibition.setPrice(rs.getInt("price"));
    }

    public List<ExhibitionDao> getExhibitionsStat() {
        Connection connection = dbcon.getConnection();
        List<ExhibitionDao> exhibitions = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.CHECK_EXHIBITION_STATISTICS);
            while (rs.next()) {
                ExhibitionDao exhibition = new ExhibitionDao();
                createExhibitionDao(rs, exhibition);
                exhibition.setSoldTickets(rs.getInt("sold_tickets"));
                exhibitions.add(exhibition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return exhibitions;
    }

    public boolean buyTicket(int exhibitionId, int userId, int exhibitionPrice) {
        Connection connection = dbcon.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.BUY_TICKET);
            stmt.setInt(1, userId);
            stmt.setInt(2, exhibitionId);
            if (stmt.executeUpdate() > 0) {
                return debitMoney(exhibitionPrice, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return false;
    }

    public int checkAmount(int userId) {
        Connection connection = dbcon.getConnection();
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
        } finally {
            dbcon.releaseConnection(connection);
        }
        return money;
    }

    public boolean debitMoney(int money, int userId) {
        Connection connection = dbcon.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(Queries.DEBIT_MONEY);
            stmt.setInt(1, money);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbcon.releaseConnection(connection);
        }
        return false;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
