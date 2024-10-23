<%@ page contentType="text/html ; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Registrar Nuevo Empleado</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>
	<h2>Registrar Nuevo Empleado</h2>

	<!-- Mostrar mensaje si hay alguno -->
	<c:if test="${not empty mensaje}">
		<div>${mensaje}</div>
	</c:if>

	<form action="insertarEmpleado" method="post">
		<p>
			<label for="dni">DNI:</label> <input type="text" id="dni" name="dni"
				pattern="^[0-9]{8}[A-Za-z]$"
				title="Debe ser un DNI válido (8 números seguidos de una letra)"
				required>
		</p>
		<p>
			<label for="nombre">Nombre:</label> <input type="text" id="nombre"
				name="nombre" required>
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
				id="categoria" name="categoria" required>
		</p>
		<p>
			<label for="anyos">Años Trabajados:</label> <input type="number"
				id="anyos" name="anyos" required>
		</p>

		<p>
			<button type="submit">Registrar Empleado</button>  
		</p>
	</form>
	<br>
	<button onclick="history.back()">Volver a la Página Anterior</button>
	<br>
	<a href="index.jsp" class="button">Volver al Menú Principal</a>

</body>
</html>