package controller.servlets.autorization;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        boolean valid = userService.isValidUser(login, pass);
        if (valid) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(60*60);
            UserDao user = userService.getUser(login);
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("userPanel.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            req.setAttribute("error", "Wrong login or password");
            requestDispatcher.forward(req, resp);
        }
    }
}
