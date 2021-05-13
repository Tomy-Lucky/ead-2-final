<%@ page import="com.example.assignment_rk_2_ead_2.beans.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Task task = (Task) request.getAttribute("task");
%>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <title><%=task.getTitle()%>
        </title>
    </head>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="task">
    <h4><%=task.getUserId()%>
    </h4>
    <h3><%=task.getTitle()%>
    </h3>
    <p><%=task.getContent()%>
    </p>
    <%
        if (session.getAttribute("userName") != null) {
    %>
    <%
        if (session.getAttribute("userId").equals(task.getUserId())) {
    %>
    <button><a href="editTask.jsp?taskId=<%=task.getId()%>">Edit</a></button>
    <%
            }
        }
    %>
</div>
</body>
</html>
