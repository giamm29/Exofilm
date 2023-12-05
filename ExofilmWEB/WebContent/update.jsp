<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Modifica</title>
</head>
<body>
	<form action="ExofilmServlet" method="post">
		<label>Id Film:</label>
		<input type="number" value="${film.idFilm}"    name="idFilm" />
		<br>
		<label>Titolo:</label>
		<input type="text"   value="${film.titolo}"    name="titolo" />
		<br>
		<label>Cognome:</label>
		<input type="number" value="${film.idRegista}" name="idRegista" />
		<br>
		<input type="hidden" value="toUpdate"		   name="toUpdate" />
		<input type="submit" value="Update" />
	</form>
</body>
</html>