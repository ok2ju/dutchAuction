<%--
  Created by IntelliJ IDEA.
  User: ok2ju
  Date: 12.03.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.grsu.av.engine.UserFacade" %>
<%@ page import="by.grsu.av.model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
  <thead>
  <td>Username</td>
  <td>Role</td>
  </thead>
  <tbody>
  <%
    for (User u: UserFacade.getInstance().getUsers()){
  %>
  <tr>
    <td><%= u.getUsername() %></td>
    <td><%= u.getRole().toString() %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
<script>
  setTimeout('location.href = "/index.jsp?mode=2"', 3000);
</script>