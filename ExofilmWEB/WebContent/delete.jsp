<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Delete</title>
</head>
<body>
	<form action="ExofilmServlet" method="post">
		<label>Id Film:</label>
		<input type="number" value="${film.idFilm}"	name="idFilm" />
		<br>
		<input type="hidden" value="toDelete"		name="toDelete" />
		<input type="submit" value="Elimina" />
	</form>
</body>
</html>