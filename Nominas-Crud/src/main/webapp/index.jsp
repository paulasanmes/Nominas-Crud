<%@ page contentType="text/html ; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menú de Opciones</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>
	<h1>Menú de Opciones Empleados</h1>
	<table border="1">
		<tr>
			<td><a href="empleados"> Mostrar Información de Empleados</a></td>
		</tr>
		<!-- Aquí puedes agregar más opciones en el futuro -->
		<tr>
			<td><a href="salario"> Mostrar Salario de un Empleado</a></td>
		</tr>
		<tr>
			<td><a href="insertarEmpleado">Registrar Nuevo Empleado</a></td>
		</tr>
		<tr>
			<td><a href="buscarEmpleados">Buscar Empleado</a></td>
		</tr>
		
	</table>
	<br>
    <button onclick="history.back()">Volver a la Página Anterior</button>
</body>
</html>
