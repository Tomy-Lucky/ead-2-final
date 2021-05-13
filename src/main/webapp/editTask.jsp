<%@ page import="com.example.ead_2_final_Tamir.db.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="task" class="com.example.ead_2_final_Tamir.beans.Task"/>
<jsp:setProperty property="*" name="task"/>

<%
    Database db = new Database();
    String message = null;
    long taskId = task.getId();
    if (request.getParameter("title") != null && request.getParameter("taskContent") != null) {
        try {
            db.updateTask(task);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        message = "You are successfully edit task";
    } else {
        try {
            task = db.getTask(taskId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
%>
<% assert task != null; %>
<jsp:setProperty property="title" name="task" value="<%=task.getTitle()%>"/>
<jsp:setProperty property="taskContent" name="task" value="<%=task.getContent()%>"/>
<%}%>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <title>Edit Task</title>
    </head>
</head>
<%@ include file="header.jsp" %>
<body>
<div class="task">
    <h3>Title:
        <jsp:getProperty property="title" name="task"/>
    </h3>
    <p>Content:
        <jsp:getProperty property="taskContent" name="task"/>
    </p>
    <form action="/editTask.jsp" method="POST">
        <p><%=(message == null) ? "" : message%>
        </p>
        <h2>Edit task</h2>
        <label>Title:</label><br>
        <input type="text" name="title" value="<jsp:getProperty property="title" name="task"/>"><br><br>
        <label>Content: </label><br>
        <textarea name="taskContent"><jsp:getProperty property="taskContent" name="task"/></textarea><br><br>
        <input type="hidden" name="taskId" value="<jsp:getProperty property="taskId" name="task"/>"><br><br>
        <button>Edit</button>
    </form>

</div>
</body>
</html>
