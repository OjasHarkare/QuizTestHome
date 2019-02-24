<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin login</title>
</head>
<body>
<h1>Admin</h1>

 <form action = "createtest" method = "post">
 Test ID : <input type="text" name="test_id"> 
<input type="submit" value="Create Test"></form>


 <form action = "deletetest" method = "post">
  Enter test ID to delete : <input type="text" name="delete_test_id">  
<input type="submit" value="Delete Test" >

</form>

 <form action = "admin_modifytest.jsp" method = "post">
<input type="submit" value="Modify Test"></form>

</body>
</html>