<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Empleado</title>
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    
</head>
<body>
    <h2>Detalle del Empleado</h2>

    <!-- Mostrar mensajes de error o éxito -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>

    <form action="actualizarEmpleado" method="post">
        <input type="hidden" name="dni" value="${empleado.dni}">
        <p>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${empleado.nombre}" required>
        </p>
       <p>
        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo" required>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select>
    </p>
        <p>
            <label for="categoria">Categoría:</label>
            <input type="number" id="categoria" name="categoria" value="${empleado.categoria}" required>
        </p>
        <p>
            <label for="anyos">Años Trabajados:</label>
            <input type="number" id="anyos" name="anyos" value="${empleado.anyos}" required>
        </p>

        <p>
            <input type="submit" value="Actualizar Empleado">
        </p>
    </form>
    <br>
    <button onclick="history.back()">Volver a la Página Anterior</button>
    <br>
	<a href="index.jsp" class="button">Volver al Menú Principal</a>
</body>
</html>