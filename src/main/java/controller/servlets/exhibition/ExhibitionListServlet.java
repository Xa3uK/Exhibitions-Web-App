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

@WebServlet("/exhibitions")
public class ExhibitionListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        ExhibitionService exService = new ExhibitionService();
        List<ExhibitionDao> exhibitions = exService.listExhibitions((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = exService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("exhibitions", exhibitions);

        RequestDispatcher dispatcher = req.getRequestDispatcher("listExhibitions.jsp");
        dispatcher.forward(req, resp);
    }
}
