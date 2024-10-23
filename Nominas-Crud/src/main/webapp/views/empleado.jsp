<%@ page contentType="text/html ; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Empleados</title>
<link rel="stylesheet" type="text/css" href="styles/style.css">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
    <!-- Mostrar mensajes de error o éxito -->
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>

    <h1>Lista de Empleados</h1>

    <!-- Mostrar mensaje si hay alguno -->
    <c:if test="${not empty mensaje}">
        <div>${mensaje}</div>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>         
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años Trabajados</th>
                <th>Modificar</th>
                <th>Eliminar</th>
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
                    <td>
                        <!-- Botón de "Editar" -->
                        <form action="actualizarEmpleado" method="get" class="act"> 
                            <input type="hidden" name="dni" value="${empleado.dni}">
                            <input type="submit" value="Editar">
                        </form>
                    </td>
                    <td>
                        <!-- Botón de "Eliminar" -->
                        <form action="eliminarEmpleado" method="post" class="eliminar">
                            <input type="hidden" name="dni" value="${empleado.dni}">
                            <input type="submit" value="Eliminar">
                        </form>
                    </td>
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