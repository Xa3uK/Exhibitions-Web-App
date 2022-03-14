package controller.servlets.autorization;

import controller.UserValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserValidator validator = new UserValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        boolean valid = validator.isValidUser(login, pass);
        if (valid) {
            HttpSession session = req.getSession();
            session.setAttribute("name", login);
            if (validator.userRole(login).equals("admin")) {
                session.setAttribute("role", "admin");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminPanel.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                session.setAttribute("role", "user");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("userPanel.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            req.setAttribute("error", "Wrong login or password");
            requestDispatcher.forward(req, resp);
        }
    }
}
