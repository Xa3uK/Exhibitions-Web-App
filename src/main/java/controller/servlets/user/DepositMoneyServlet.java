package controller.servlets.user;

import controller.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDao;

import java.io.IOException;

@WebServlet("/deposit-money")
public class DepositMoneyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher("deposit.jsp");
        disp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDao user = (UserDao) session.getAttribute("user");
        int userId = user.getId();
        int money = Integer.parseInt(req.getParameter("money"));

        if (money > 0) {
            UserService userService = new UserService();
            userService.depositMoney(money, userId);
            user.setMoney(user.getMoney() + money);
            session.setAttribute("user", user);
            session.removeAttribute("error");
            resp.sendRedirect("/displayDeposit");
        } else {
            session.setAttribute("error", "Error: you put negative amount");
            resp.sendRedirect("/displayDeposit");
        }
    }
}
