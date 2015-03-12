<%@ page import="by.grsu.av.engine.LoginFacade" %>
<%@ page import="by.grsu.av.engine.UserFacade" %>
<%@ page import="by.grsu.av.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ok2ju
  Date: 12.03.2015
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <%
    String modeValue = request.getParameter("mode");
    int mode;

    try {
      mode = Integer.parseInt(modeValue);
    } catch (NumberFormatException e) {
      mode = 0;
    }

    switch (mode) {
      case 0: // username + submit form
        %>

        <%@ include file="login/login.jsp" %>

        <%
          break;
          case 1: // username already taken message
            String username = request.getParameter("username");
            User user = LoginFacade.getInstance().login(username);

            if(user == null) {
              %>

              <h1>User has already logged in!!!</h1>
              <a href="index.jsp">Try again..</a>

              <%
              break;

            }
                  case 2: // TODO
                    %>
              <%@ include file="login/usersTable.jsp" %>
  <%
                break;
          }

        %>

  </body>
</html>
