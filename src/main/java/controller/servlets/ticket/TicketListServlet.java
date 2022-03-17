package controller.servlets.ticket;

import controller.TicketService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TicketDao;
import model.UserDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-tickets")
public class TicketListServlet extends HttpServlet {
    TicketService ticketService = new TicketService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDao user = (UserDao) session.getAttribute("user");
        List<TicketDao> tickets = ticketService.getTicketList(user.getId());
        req.setAttribute("tickets", tickets);
        RequestDispatcher disp = req.getRequestDispatcher("tickets.jsp");
        disp.forward(req, resp);
    }
}
