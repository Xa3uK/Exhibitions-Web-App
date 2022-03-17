package controller;


import connection.ConnectionPool;
import model.TicketDao;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<TicketDao> getTicketList(int userId) {
        List<TicketDao> ticketDaoList = new ArrayList<>();
        Connection con = connectionPool.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(Queries.GET_MY_TICKETS);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TicketDao ticket = new TicketDao();
                ticket.setTheme(rs.getString("theme"));
                ticket.setHall(rs.getString("hall"));
                ticket.setPrice(rs.getInt("price"));
                ticket.setTicketsCount(rs.getInt("ticket_count"));
                ticket.setStartDate(rs.getDate("start_date").toLocalDate());
                ticket.setEndDate(rs.getDate("end_date").toLocalDate());
                ticket.setStartTime(rs.getTime("start_time"));
                ticket.setEndTime(rs.getTime("end_time"));
                ticketDaoList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketDaoList;
    }
}
