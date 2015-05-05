package by.grsu.av.web.game;

import by.grsu.av.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alehatsman on 5/5/15.
 */
@WebServlet("/game")
public class GameServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        render("/game/game.jsp", req, resp);
    }
}
