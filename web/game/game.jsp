<%@ page import="by.grsu.av.web.LoginServlet" %>
<%@ page import="by.grsu.av.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: alehatsman
  Date: 5/5/15
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Game page</h1>
<h2>Username: <small><%=((User)request.getSession().getAttribute(LoginServlet.CURRENT_USER)).getUsername()%></small></h2>
<h3>Money: <%=((User)request.getSession().getAttribute(LoginServlet.CURRENT_USER)).getMoney()%></h3>
<div id="container">
  Waiting
</div>
<script src="/js/jquery.min.js"></script>
<script>
  (function() {
    setInterval(function() {
      $.get('/game/content', function(html) {
        if(html) {
          $('#container').html(html);
        }
      });
    }, 3000);
  })();
</script>
</body>
</html>
