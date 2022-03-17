package controller.servlets.exhibition;

import controller.ExhibitionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ExhibitionDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/exhibition-stat")
public class ExhibitionStatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitionService exService = new ExhibitionService();
        List<ExhibitionDao> exhibitions = exService.getExhibitionsWithStat();
        req.setAttribute("exhibitions", exhibitions);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listExhibitionsStat.jsp");
        requestDispatcher.forward(req, resp);
    }
}
