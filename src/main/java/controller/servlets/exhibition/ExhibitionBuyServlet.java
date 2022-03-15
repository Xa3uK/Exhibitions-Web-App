package controller.servlets.exhibition;

import connection.DataBaseConnection;
import controller.ExhibitionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDao;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/exhibition-buy")
public class ExhibitionBuyServlet extends HttpServlet {
    Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connection = DataBaseConnection.getInstance().getConnection();
        ExhibitionService exService = new ExhibitionService();
        HttpSession session = req.getSession();
        UserDao user = (UserDao) session.getAttribute("user");
        int userId = user.getId();
        int exhibitionId = Integer.parseInt(req.getParameter("id"));
        int exhibitionPrice = Integer.parseInt(req.getParameter("price"));
        int userMoney = exService.checkAmount(userId);
        if (userMoney > exhibitionPrice) {
            exService.buyTicket(exhibitionId, userId, exhibitionPrice);
            user.setMoney(user.getMoney() - exhibitionPrice);
            session.setAttribute("user", user);
            session.removeAttribute("error");
            resp.sendRedirect("/exhibitions");

        }
        else {
            session.setAttribute("error", "no money no honey");
            resp.sendRedirect("/exhibitions");
        }
    }
}
