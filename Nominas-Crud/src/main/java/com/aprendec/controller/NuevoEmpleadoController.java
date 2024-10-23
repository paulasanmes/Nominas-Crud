package com.aprendec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;

/**
 * Servlet controlador para la creación de nuevos empleados. 
 * Este servlet gestiona la inserción de un nuevo empleado en la base de datos 
 * y la visualización del formulario de registro.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/insertarEmpleado")
public class NuevoEmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * DAO para gestionar las operaciones CRUD de empleados.
     */
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    
    /**
     * Procesa las solicitudes GET para mostrar el formulario de registro 
     * de nuevos empleados.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/nuevoEmpleado.jsp").forward(request, response);
    }

    /**
     * Procesa las solicitudes POST para registrar un nuevo empleado. 
     * Recupera los parámetros del formulario, crea un nuevo objeto 
     * Empleado y lo inserta en la base de datos. Maneja mensajes de éxito 
     * y error durante el proceso de inserción.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anyos = Integer.parseInt(request.getParameter("anyos"));
        String mensaje;

        try {
            // Crear nuevo empleado
            Empleado nuevoEmpleado = new Empleado(nombre, dni, sexo, categoria, anyos);
            
            // Insertar el empleado en la base de datos
            empleadoDAO.insertarEmpleado(nuevoEmpleado);
            
            // Mensaje de éxito
            mensaje = "Empleado registrado con éxito.";
            request.setAttribute("mensaje", mensaje);
        } catch (Exception e) {
            // Mensaje de error
            mensaje = "Error al registrar el empleado: " + e.getMessage();
            request.setAttribute("mensaje", mensaje);
        }

        // Redirigir a la página de nuevo empleado
        request.getRequestDispatcher("/views/nuevoEmpleado.jsp").forward(request, response);
    }
}