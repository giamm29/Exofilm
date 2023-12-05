<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<meta charset="ISO-8859-1">
	<title>Registrazione</title>
</head>
<body>
	<form action="ExofilmServlet" method="post">
		<label>Titolo:</label>
		<input type="text" name="titolo" />
		<br>
		<label>idRegista:</label>
		<input type="number" name="idRegista" />
		<br>
		<input type="hidden" value="toInsert" name="toInsert" />
		<input type="submit" value="Registrazione" />
	</form>
</body>
</html>