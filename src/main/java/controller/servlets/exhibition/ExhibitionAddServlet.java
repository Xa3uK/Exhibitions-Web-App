package controller.servlets.exhibition;

import connection.DataBaseConnection;
import controller.ExhibitionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ExhibitionDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/exhibition-add")
public class ExhibitionAddServlet extends HttpServlet {
    Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addExhibition.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connection = DataBaseConnection.getInstance().getConnection();

        String theme = req.getParameter("theme");
        String hall = req.getParameter("hall");
        LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
        LocalTime startTime = LocalTime.parse(req.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(req.getParameter("endTime"));
       Time start = Time.valueOf(startTime);
       Time end = Time.valueOf(endTime);
        int price = Integer.parseInt(req.getParameter("price"));

        ExhibitionService exService = new ExhibitionService();
        ExhibitionDao exhibition = new ExhibitionDao();
        exhibition.setTheme(theme);
        exhibition.setHall(hall);
        exhibition.setStartDate(startDate);
        exhibition.setEndDate(endDate);
        exhibition.setStartTime(start);
        exhibition.setEndTime(end);
        exhibition.setPrice(price);

        exService.addExhibition(exhibition);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addExhibition.jsp");
        requestDispatcher.forward(req, resp);
    }
}
