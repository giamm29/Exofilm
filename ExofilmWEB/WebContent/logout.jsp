<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- Questa pagina non è collegata alla servlet, è solo un esempio di login -->
	<title>Logout</title>
</head>
<body>
	<% session.invalidate(); %>
	<jsp:forward page="index.jsp"></jsp:forward>
</body>
</html>