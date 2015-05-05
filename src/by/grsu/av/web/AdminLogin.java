package by.grsu.av.web;

import by.grsu.av.db.UserRepository;
import by.grsu.av.engine.GameFacade;
import by.grsu.av.engine.HistoryFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/5/15.
 */
@WebServlet("/admin")
public class AdminLogin extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserRepository.getUsers());
        if(GameFacade.getInstance().isFinished()) {
            req.setAttribute("history", HistoryFacade.getInstance().getPurchases());
        }
        render("/admin/dashbord.jsp", req, resp);
    }
}
