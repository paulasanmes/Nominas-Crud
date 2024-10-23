package com.aprendec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;

/**
 * Servlet controlador para la eliminación de empleados. Procesa la solicitud 
 * POST para eliminar un empleado de la base de datos utilizando su DNI.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/eliminarEmpleado")
public class EliminarEmpleadoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * DAO para gestionar las operaciones CRUD de empleados.
     */
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    
    /**
     * Procesa las solicitudes GET. En este caso, redirige a la lista de empleados.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige a la lista de empleados cuando se accede con GET
        response.sendRedirect("empleados");
    }

    /**
     * Procesa las solicitudes POST para eliminar un empleado. Si el empleado es
     * eliminado correctamente, muestra un mensaje de éxito. Si ocurre un error,
     * muestra un mensaje descriptivo del error.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el DNI del empleado a eliminar
        String dni = request.getParameter("dni");
        System.out.println(dni);
        String mensaje;

        // Intentar eliminar el empleado de la base de datos
        try {
            empleadoDAO.eliminarEmpleado(dni);
            mensaje = "Empleado eliminado con éxito.";
            request.setAttribute("mensaje", mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error al eliminar el empleado: " + e.getMessage();
            request.setAttribute("mensaje", mensaje);
        }

        // Obtener la lista de empleados actualizada y pasarla al JSP
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        request.setAttribute("empleados", empleados);
        
        // Redirigir a la lista de empleados
        request.getRequestDispatcher("/views/empleado.jsp").forward(request, response);
    }
}