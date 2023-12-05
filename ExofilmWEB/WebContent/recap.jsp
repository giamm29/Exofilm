<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Riassunto</title>
</head>
<body>
	<table>
		<tr>
			<th>Campo</th>
			<th>Valore</th>
		</tr>
		<tr>
			<td>Id Film</td>
			<td>${film.idFilm}</td>
		</tr>
		<tr>
			<td>Titolo</td>
			<td>${film.titolo}</td>
		<tr>
			<td>Id Regista</td>
			<td>${film.idRegista}</td>
		</tr>
	</table>
	
	<form action="manage.jsp">
			<input type="submit" value="home" ><br/>
	</form>
</body>
</html>