package controller.servlets.exhibition;

import controller.ExhibitionService;
import controller.UserService;
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
    ExhibitionService exService = new ExhibitionService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDao user = (UserDao) session.getAttribute("user");
        int userId = user.getId();
        int exhibitionId = Integer.parseInt(req.getParameter("id"));
        if (exService.buyTicket(exhibitionId, userId)) {
            user = userService.getUser(user.getLogin());
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
