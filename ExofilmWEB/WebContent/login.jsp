<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- Questa pagina non è collegata alla servlet, è solo un esempio di login -->
	<title>Login</title>
</head>
<body>
	<form action="ExofilmServlet" method="post">
		<label>Mail:</label>
		<input type="text" name="mail" />
		<br/>
		<label>Password:</label>
		<input type="text" name="password" />
		<br>
		<input type="hidden" value="toLogin" name="toLogin"  />
		<input type="submit" value="Login" />
	</form>
</body>
</html>