<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Log In</title>
</head>
<body>
<nav>
    <a href="index.jsp">Main page</a>
    <a href="registration.jsp">Register</a>
    <a href="logout">Log out</a>
</nav>
<div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
    <form action="login" method="POST">
        <p сlass="message"><%=(request.getAttribute("message") == null) ? ""
                : request.getAttribute("message")%>
        </p>
        <h2>Log In</h2>
        <label>Username: </label><br>
        <input type="text" name="userName"><br><br>
        <label>Password: </label><br>
        <input type="password" name="password"><br><br>
        <button>Log In</button>
    </form>
</div>
</body>
</html>
