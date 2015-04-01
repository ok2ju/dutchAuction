package by.grsu.av.web;

import by.grsu.av.db.UserRepository;
import by.grsu.av.engine.LoginFacade;
import by.grsu.av.engine.UserFacade;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 4/1/15.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "username";
    private static final String CURRENT_USER = "currentUser";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginFacade loginFacade = LoginFacade.getInstance();
        String username = req.getParameter(USERNAME);
        User currentUser = loginFacade.login(username);
        req.getSession().setAttribute(CURRENT_USER, currentUser);
        UserRole role = currentUser.getRole();
        if(role == UserRole.Admin) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/dashbord.jsp");
            req.setAttribute("users", UserFacade.getInstance().getUsers());
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/waiting.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
