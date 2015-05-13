package by.grsu.av.web;

import by.grsu.av.engine.ConfigFacade;
import by.grsu.av.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/13/15.
 */
@WebServlet("/jocker")
public class JockerServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        ConfigFacade.setJokerNumber(Integer.parseInt(req.getParameter("jockerNumber")));
        user.setMoney(user.getMoney() - 20);
        redirect("/game", resp);
    }
}
