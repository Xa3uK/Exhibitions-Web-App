package controller.servlets.autorization;

import controller.UserReg;
import controller.UserValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserValidator validator = new UserValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        if (validator.isValidLogin(login)) {
            System.out.println("palivo");
            req.setAttribute("error", "data allready used");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            UserReg reg = new UserReg();
            reg.Registration(login, pass, email);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("autorized.html");
            requestDispatcher.forward(req, resp);
        }
    }
}

