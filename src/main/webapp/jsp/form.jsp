<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<html lang="en">
    <body>
        <p> ${2 + 2} equal to 4 </p>
        <p> Current server date time is <%= java.time.LocalDateTime.now()%></p>

        <%
            String name = request.getParameter("name");
            if (name != null) {
                out.println("<p>Привет, " + name + "!<p>");
            }
        %>

        <%!
            private int visitCount = 0;
            public String getVisitCountMessage() {
                return "There were " + visitCount++ + "visits";
            }
        %>
    </body>
</html>