<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Riassunto</title>
	<style>
		table, th, tr, td {
			border: 1px solid gray;
		}
		td {
			text-align: center;
		}
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${listaFilm != null}">
			<table>
				<tr>
					<th colspan="10" style="font-size: 24px;">
						List Film
					</th>
				<tr>
				<tr style="font-size: 20px;">
					<th colspan="3">Film</th>
					<th colspan="5">Regista</th>
					<th colspan="2">Categorie</th>
				</tr>
				<tr>
					<!-- Film -->
					<th>idFilm</th>
					<th>Titolo</th>
					<!-- Regista -->
					<th colspan="2">idRegista</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>AnnoDiNascita</th>
					<th>AnnoDiMorte</th>
					<!-- Categoria -->
					<th>Numero</th>
					<th>Categorie</th>
				</tr>
				<c:forEach items="${listaFilm}" var="film">
					<tr>
						<!-- Film -->
						<td>${film.idFilm}</td>
						<td>${film.titolo}</td>
						<!-- Regista -->
						<td colspan="2">${film.regista 			ne null ? film.regista.idRegista	 : '-'}</td>
						<td>${film.regista 			ne null ? film.regista.nome			 : '-'}</td>
						<td>${film.regista 			ne null ? film.regista.cognome		 : '-'}</td>
						<td>${film.regista 			ne null ? film.regista.annoDiNascita : '-'}</td>
						<td>${film.regista 			ne null ? film.regista.annoDiMorte	 : '-'}</td>
						<!-- Categoria -->
						<td>${film.listaCategorie 	ne null ? film.listaCategorie.size() : '-'}</td>
						<c:choose>
							<c:when test="${not empty film.listaCategorie}">
								<td>
									<c:forEach var="categoria" items="${film.listaCategorie}" varStatus="loop">
					                    ${categoria.nome}${!loop.last ? ', ' : ''}
					                </c:forEach>
				                </td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	
	<form action="manage.jsp">
		<input type="submit" value="home" style="margin-top: 8px;"><br/>
	</form>
</body>
</html>