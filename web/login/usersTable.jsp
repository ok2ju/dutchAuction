<%--
  Created by IntelliJ IDEA.
  User: ok2ju
  Date: 12.03.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.grsu.av.model.User" %>
<%@ page import="java.util.Collection" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
  <thead>
  <td>Username</td>
  <td>Role</td>
  </thead>
  <tbody>
  <%
    for (User u: (Collection<User>) request.getAttribute("users")){
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
  setTimeout('location.href = "/user/online"', 3000);
</script>