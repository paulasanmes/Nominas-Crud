package com.aprendec.controller;

import com.aprendec.model.Empleado;
import com.aprendec.dao.EmpleadoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para gestionar la visualización de empleados. 
 * Este servlet se encarga de recuperar la lista de empleados de la base de datos
 * y enviarla a la vista correspondiente.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/empleados")
public class EmpleadoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * DAO para gestionar las operaciones CRUD de empleados.
     */
    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el controlador y crea una instancia del DAO de empleados.
     * 
     * @throws ServletException si ocurre un error al inicializar el servlet
     */
    @Override
    public void init() throws ServletException {
        empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Procesa las solicitudes GET para mostrar la lista de empleados.
     * Recupera todos los empleados de la base de datos y los pasa como
     * atributo a la JSP. También maneja mensajes de éxito opcionales.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar la lista de empleados de la base de datos
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();

        // Añadir la lista de empleados al request
        request.setAttribute("empleados", empleados);

        // Mensaje opcional (si existe)
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            request.setAttribute("success", mensaje);
        }

        // Redirigir a la JSP para mostrar los empleados
        request.getRequestDispatcher("views/empleado.jsp").forward(request, response);
    }
}