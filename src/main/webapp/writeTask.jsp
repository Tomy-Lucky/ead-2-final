<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Task</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<%@ include file="header.jsp" %>
<form action="/writeTask" method="POST">
    <p><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%>
    </p>
    <h2>Write Task</h2>
    <label>Title:</label><br>
    <input type="text" name="title"><br><br>
    <label>Content: </label><br>
    <textarea name="content"></textarea><br><br>
    <button>Publish</button>
</form>

</body>
</html>
