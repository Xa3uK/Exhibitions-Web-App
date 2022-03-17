package controller.servlets.exhibition;

import controller.ExhibitionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDao;

import java.io.IOException;

@WebServlet("/exhibition-buy")
public class ExhibitionBuyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            session.removeAttribute("errorBuy");
            resp.sendRedirect("/exhibitions");
        }
        else {
            session.setAttribute("errorBuy", "no money no honey");
            resp.sendRedirect("/exhibitions");
        }
    }
}
