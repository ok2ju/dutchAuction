<%@ page import="by.grsu.av.model.User" %>
<%@ page import="by.grsu.av.engine.UserFacade" %>
<%@ page import="by.grsu.av.engine.HistoryFacade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%for(User user : UserFacade.getInstance().getUsers()) {
  int score = HistoryFacade.getInstance().calcScore(user);
  %>User : <%=user.getUsername()%> score: <%=score%><br/>
<%}%>