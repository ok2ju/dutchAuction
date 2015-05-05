package by.grsu.av.web;

import by.grsu.av.engine.GameFacade;
import by.grsu.av.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/5/15.
 */
@WebServlet("/product/buy")
public class BuyServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        GameFacade gameFacade = GameFacade.getInstance();
        gameFacade.buy(user);
        redirect("/game", resp);
    }
}
