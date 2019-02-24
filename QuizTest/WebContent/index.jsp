<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz</title>
</head>
<body>
<center>
<h1>Quiz</h1>
 <form action = "login" method = "post">

<h3>Email</h3>
<input type="text" name = "login_email">
<h3>Password</h3>
<input type="password" name = "login_password"><br>
<input type="submit" value="Login" /><br>
If you don't have account
<a href="Register.jsp">Create now</a>
</form>
</center>
</body>
</html>