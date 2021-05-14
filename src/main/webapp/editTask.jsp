<%@ page import="com.example.ead_2_final_Tamir.beans.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Task task = (Task) request.getAttribute("task");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Edit Task</title>
</head>

<%@ include file="header.jsp" %>
<body>
<div class="task">
    <h3>
        Title: <%=task.getTitle()%>
    </h3>
    <p>
        Content: <%=task.getContent()%>
    </p>
    <form action="editTask" method="POST">
        <h2>Edit task</h2>
        <label>Title:</label><br>
        <input type="text" name="title"><br><br>
        <label>Content: </label><br>
        <textarea name="content"></textarea><br><br>
        <input type="hidden" name="taskId" value="<%=task.getId()%>"><br><br>
        <button>Edit</button>
    </form>

</div>
</body>
</html>
