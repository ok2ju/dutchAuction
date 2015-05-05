package by.grsu.av.web;

import by.grsu.av.engine.LoginFacade;
import by.grsu.av.engine.UserFacade;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

    private static final String USERNAME = "username";
    public static final String CURRENT_USER = "currentUser";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("login/login.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginFacade loginFacade = LoginFacade.getInstance();
        String username = req.getParameter(USERNAME);
        User currentUser = loginFacade.login(username, UserRole.Player);
        req.getSession().setAttribute(CURRENT_USER, currentUser);
        redirect("/game", resp);
    }
}
