package com.aprendec.controller;

import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para la búsqueda de empleados. Permite filtrar empleados
 * por DNI, nombre, sexo, categoría o años trabajados.
 * 
 * Si no se proporcionan filtros, obtiene todos los empleados de la base de datos.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/buscarEmpleados")
public class BuscarEmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * DAO para gestionar las operaciones CRUD de empleados.
     */
    private EmpleadoDAO empleadoDAO;

    /**
     * Constructor que inicializa el DAO de empleados.
     */
    public BuscarEmpleadoController() {
        super();
        empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Procesa las solicitudes GET para buscar empleados en la base de datos.
     * Permite buscar empleados según filtros como DNI, nombre, sexo, categoría 
     * y años trabajados. Si no se proporcionan filtros, obtiene todos los empleados.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros de búsqueda
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String categoriaStr = request.getParameter("categoria");
        String anyosStr = request.getParameter("anyos");

        // Convertir los parámetros numéricos a Integer si son válidos
        Integer categoria = (categoriaStr != null && !categoriaStr.isEmpty()) ? Integer.parseInt(categoriaStr) : null;
        Integer anyos = (anyosStr != null && !anyosStr.isEmpty()) ? Integer.parseInt(anyosStr) : null;

        List<Empleado> empleados;

        // Si se han proporcionado filtros, buscar empleados filtrados
        if (dni != null || nombre != null || sexo != null || categoria != null || anyos != null) {
            empleados = empleadoDAO.buscarEmpleado(dni, nombre, sexo, categoria, anyos);
        } else {
            // Si no hay filtros, obtener todos los empleados
            empleados = empleadoDAO.getAllEmpleados();
        }

        // Pasar la lista de empleados a la JSP
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/views/buscarEmpleado.jsp").forward(request, response);
    }
}