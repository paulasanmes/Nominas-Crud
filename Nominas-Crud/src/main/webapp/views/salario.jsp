<%@ page contentType="text/html ; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Salario de Empleado</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>

	<c:if test="${not empty mensaje}">
        <div style="color: green;">${mensaje}</div>
    </c:if>

   
    <h1>Consultar Salario de Empleado</h1>

    <form action="salario" method="post">
        <label for="dni">DNI del Empleado:</label>
        <input type="text" id="dni" name="dni" required>
        <button type="submit">Consultar</button>
    </form>

	<c:choose>
		<c:when test="${sueldo > 0}"><h2>Salario del empleado con DNI ${dni}: ${sueldo}</h2></c:when>
	</c:choose>
	
    <br>
    <button onclick="history.back()">Volver a la Página Anterior</button>
    <br>
	<a href="index.jsp" class="button">Volver al Menú Principal</a>
</html>