<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Empleados</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
	<h1>Buscar Empleado</h1>

	<form action="buscarEmpleados" method="get">
		<p>
			<label for="dni">DNI:</label> <input type="text" id="dni" name="dni">
		</p>
		<p>
			<label for="nombre">Nombre:</label> <input type="text" id="nombre"
				name="nombre">
		</p>
		<p>
			<label for="sexo">Sexo:</label> <select id="sexo" name="sexo"
				required>
				<option value="M">Masculino</option>
				<option value="F">Femenino</option>
			</select>
		</p>
		<p>
			<label for="categoria">Categoría:</label> <input type="number"
				id="categoria" name="categoria">
		</p>
		<p>
			<label for="anyos">Años Trabajados:</label> <input type="number"
				id="anyos" name="anyos">
		</p>
		<p>
			<input type="submit" value="Filtrar" class="button-restablecer" > 
			
			<input type="button" value="Restablecer" class="button-restablecer" onclick="location.href='buscarEmpleados'">

		</p>
	</form>

	<!-- Mostrar mensaje si hay alguno -->
	<c:if test="${not empty mensaje}">
		<div style="color: red;">${mensaje}</div>
	</c:if>

	<table border="1">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Sexo</th>
				<th>Categoría</th>
				<th>Años Trabajados</th>
			</tr>
		</thead>
		<tbody>
			<!-- Iterar sobre la lista de empleados -->
			<c:forEach var="empleado" items="${empleados}">
				<tr>
					<td>${empleado.dni}</td>
					<td>${empleado.nombre}</td>
					<td>${empleado.sexo}</td>
					<td>${empleado.categoria}</td>
					<td>${empleado.anyos}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br>
	<button onclick="history.back()">Volver a la Página Anterior</button>
	<br>
	<a href="index.jsp" class="button">Volver al Menú Principal</a>
</body>
</html>