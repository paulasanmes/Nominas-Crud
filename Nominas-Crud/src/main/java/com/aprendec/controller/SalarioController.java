package com.aprendec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.EmpleadoDAO;

/**
 * Servlet controlador para la gestión de salarios de empleados. 
 * Este servlet permite visualizar el formulario para consultar salarios 
 * y procesar la solicitud para obtener el salario de un empleado 
 * basado en su DNI.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/salario")
public class SalarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * DAO para gestionar las operaciones relacionadas con empleados.
     */
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Procesa las solicitudes GET para mostrar el formulario de consulta 
     * de salarios.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
    }

    /**
     * Procesa las solicitudes POST para obtener el salario de un empleado 
     * basado en su DNI. Si el empleado es encontrado, se muestra su salario; 
     * de lo contrario, se muestra un mensaje de error.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el DNI del empleado
        String dni = request.getParameter("dni");
        String mensaje = "";
        double sueldo = 0;

        // Obtener el salario del empleado a partir del DNI
        sueldo = empleadoDAO.obtenerSalarioPorDNI(dni);
        
        // Pasar el DNI al JSP
        request.setAttribute("dni", dni);

        // Verificar si se encontró el salario
        if (sueldo > 0) {
            request.setAttribute("sueldo", sueldo);
        } else {
            request.setAttribute("sueldo", -1);
            mensaje = "Empleado no encontrado.";
            request.setAttribute("mensaje", mensaje);
        }

        // Redirigir a la vista de salarios
        request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
    }
}