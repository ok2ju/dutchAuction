package by.grsu.av.web;

import by.grsu.av.engine.UserFacade;
import by.grsu.av.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/admin/content")
public class AdminDashBordServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserFacade userFacade = UserFacade.getInstance();
        Collection<User> users = userFacade.getUsers();
        req.setAttribute("users", users);
        render("/admin/content.jsp", req, resp);
    }
}
