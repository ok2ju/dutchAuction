<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>Goodname: <%=request.getAttribute("goodName")%></div>
Price: <%=request.getAttribute("goodPrice")%>
<a href="/product/buy">Buy</a>