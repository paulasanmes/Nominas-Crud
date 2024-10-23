package com.aprendec.controller;

import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.DatosNoCorrectosException;
import com.aprendec.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para la actualización de empleados. Este servlet gestiona las
 * solicitudes para editar y actualizar empleados en la base de datos.
 * 
 * Utiliza GET para mostrar la página de edición y POST para procesar los datos 
 * enviados del formulario de actualización.
 * 
 * @author Paula
 * @version 1.0
 */
@WebServlet("/actualizarEmpleado")
public class ActualizarEmpleadoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * DAO para gestionar las operaciones CRUD de empleados.
     */
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Procesa las solicitudes GET. Busca un empleado por su DNI y lo pasa como atributo
     * al JSP para ser mostrado en el formulario de edición.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el DNI del empleado a editar
        String dni = request.getParameter("dni");

        // Buscar el empleado en la base de datos
        Empleado empleado = null;
        try {
            empleado = empleadoDAO.getEmpleadoPorDNI(dni);
        } catch (DatosNoCorrectosException e) {
            e.printStackTrace();
            request.setAttribute("error", "Empleado no encontrado.");
        }

        System.out.println(empleado);

        // Pasar el empleado como atributo al JSP
        request.setAttribute("empleado", empleado);

        // Redirigir al formulario de edición
        request.getRequestDispatcher("/views/detalleEmpleado.jsp").forward(request, response);
    }

    /**
     * Procesa las solicitudes POST. Actualiza la información de un empleado en la
     * base de datos y redirige a la lista de empleados. Si ocurre un error, muestra
     * un mensaje descriptivo en la interfaz.
     * 
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en el servlet
     * @throws IOException si ocurre un error de E/S
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del formulario de actualización
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anyos = Integer.parseInt(request.getParameter("anyos"));

        // Crear un objeto empleado con los datos actualizados
        Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);

        // Intentar actualizar el empleado en la base de datos
        try {
            empleadoDAO.actualizarEmpleado(empleado);
            request.setAttribute("success", "Empleado actualizado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar el empleado: " + e.getMessage());
        }

        // Obtener la lista de empleados actualizada y pasársela al JSP
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        request.setAttribute("empleados", empleados);

        // Redirigir a la lista de empleados
        request.getRequestDispatcher("/views/empleado.jsp").forward(request, response);
    }
}