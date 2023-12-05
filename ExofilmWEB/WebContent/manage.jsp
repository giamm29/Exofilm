<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Management</title>
</head>
<body>
	<form action="update.jsp">
		<input type="submit" value="update" />
		<br/>
	</form>
	<form action="delete.jsp">
		<input type="submit" value="delete" />
		<br/>
	</form>
	<form action="register.jsp">
		<input type="submit" value="register" />
		<br/>
	</form>
	<form action="ExofilmServlet" method="get">
		<input type="hidden" value="toGetAll" name="toGetAll" />
		<input type="submit" value="FindAll" />
		<br/>
	</form>
</body>
</html>