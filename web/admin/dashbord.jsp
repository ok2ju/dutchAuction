<%--
  Created by IntelliJ IDEA.
  User: alehatsman
  Date: 4/1/15
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div id="content">
        <jsp:include page="/admin/content.jsp"/>
    </div>
    <a href="/startgame">Start game</a>
    <script src="/js/jquery.min.js"></script>
    <script>
        (function() {
            setInterval(function() {
                $.get('/admin/content', function(html) {
                    $('#content').html(html);
                });
            }, 3000);
        })();
    </script>
</body>
</html>
