package controller.servlets.autorization;

import controller.UserValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserValidator validator = new UserValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login = req.getParameter("login");
       String pass = req.getParameter("password");
       boolean valid = validator.isValidUser(login, pass);
       if(valid){
           RequestDispatcher requestDispatcher = req.getRequestDispatcher("autorized.html");
           requestDispatcher.forward(req, resp);
       } else {
           RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
           req.setAttribute("error", "Wrong login or password");
           requestDispatcher.forward(req, resp);
       }
    }
}
