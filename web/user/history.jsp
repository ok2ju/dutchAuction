<%@ page import="by.grsu.av.engine.HistoryFacade" %>
<%@ page import="by.grsu.av.model.Purchase" %>
<%@ page import="by.grsu.av.engine.GameFacade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(GameFacade.getInstance().isFinished()) {%>
<%for(Purchase p : HistoryFacade.getInstance().getPurchases()) {
%>User : <%=p.getUser().getUsername()%> <br/>
GameId: <%=p.getState().getGameId()%><br/>
SetId: <%=p.getState().getSetId()%><br/>
MatchId: <%=p.getState().getMatchId()%><br/>
Product: <%=p.getProduct().getTitle()%><br/>
Price: <%=p.getProduct().getPrice()%><br/>
<%}%>
<%}%>