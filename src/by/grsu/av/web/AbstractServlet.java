package by.grsu.av.web;

import by.grsu.av.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/5/15.
 */
public class AbstractServlet extends HttpServlet {
    protected void render(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }
    protected User getCurrentUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute(LoginServlet.CURRENT_USER);
    }
    protected void redirect(String path, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.sendRedirect(path);
    }
}
