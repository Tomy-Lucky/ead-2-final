<%@ page import="com.example.assignment_rk_2_ead_2.db.Database" %>
<%@ page import="com.example.assignment_rk_2_ead_2.beans.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<p class="message"><%=(request.getAttribute("message") == null) ? "" : request.getAttribute("message")%>
</p>

<h2>All Tasks</h2>
<%
    if (session.getAttribute("userName") != null) {
%>
<button><a href="writeTask.jsp">Write your own task</a></button>
<%
    }
    Database db = new Database();
    List<Task> tasks = null;
    try {
        tasks = db.getTasks();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    if (tasks != null) {
        for (Task task : tasks) {
%>
<div class="task">
    <h4><%=task.getUserId()%>
    </h4>
    <h3><a href="task?taskId=<%=task.getId()%>"><%=task.getTitle()%>
    </a></h3>
    <p><%=task.getContent()%>
    </p>

    <%
        if (session.getAttribute("userId") != null) {
            if (session.getAttribute("userId").equals(task.getUserId())) {
    %>
    <button><a href="editTask.jsp?taskId=<%=task.getId()%>">Edit</a></button>
    <%
            }
        }
    %>
</div>

<%
        }
    }
%>