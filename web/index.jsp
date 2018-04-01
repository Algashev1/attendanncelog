<%@ page import="connection.*" %>
<%@ page import="javax.jws.soap.SOAPBinding" %><%--
  Created by IntelliJ IDEA.
  User: Gennadiy
  Date: 04.03.2018
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
      UserDAO.getUser("algashev@live.com", "r1oot");
  %>
  $END$
  </body>
</html>
