package by.grsu.av.web.game;

import by.grsu.av.engine.GameFacade;
import by.grsu.av.model.Product;
import by.grsu.av.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/5/15.
 */
@WebServlet("/game/content")
public class GameContentServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameFacade gameFacade = GameFacade.getInstance();
        if(gameFacade.isStarted()) {
            Product product = gameFacade.getCurrentProduct();
            if(product != null) {
                req.setAttribute("goodName", product.getTitle());
                req.setAttribute("goodPrice", product.getPrice());
                render("/game/currentGood.jsp", req, resp);
                return;
            }
        }
        if(gameFacade.isFinished()) {
            render("/game/finishedGame.jsp", req, resp);
            return;
        }
        render("/game/waiting.jsp", req, resp);
    }
}
