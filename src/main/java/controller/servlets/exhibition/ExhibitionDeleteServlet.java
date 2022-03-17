package controller.servlets.exhibition;

import connection.ConnectionPool;
import controller.ExhibitionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/exhibition-del")
public class ExhibitionDeleteServlet extends HttpServlet {
    Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitionService exService = new ExhibitionService();
        connection = ConnectionPool.getInstance().getConnection();
        int id = Integer.parseInt(req.getParameter("id"));
        exService.deleteExhibition(id);
        resp.sendRedirect("/exhibitions");
    }
}
