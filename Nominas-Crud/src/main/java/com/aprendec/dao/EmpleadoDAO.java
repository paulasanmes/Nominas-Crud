package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.DatosNoCorrectosException;
import com.aprendec.model.Empleado;
import com.aprendec.model.Nomina;

/**
 * Clase de acceso a datos para gestionar empleados en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y consultar salarios.
 * 
 * @author Paula
 * @version 1.0
 */
public class EmpleadoDAO {

    private static final String SELECT_ALL_EMPLEADOS = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";
    private static final String SELECT_EMPLEADO_POR_DNI = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";
    private static final String SELECT_SALARIO_POR_DNI = "SELECT n.sueldo FROM nominas n JOIN empleados e ON n.dni = e.dni WHERE e.dni = ?";
    private static final String INSERT_EMPLEADO = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_NOMINA = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";
    private static final String UPDATE_EMPLEADO = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
    private static final String DELETE_EMPLEADO = "DELETE FROM empleados WHERE dni = ?";

    /**
     * Obtiene una lista de todos los empleados de la base de datos.
     * 
     * @return una lista de objetos Empleado
     */
    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLEADOS)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");

                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
                empleados.add(empleado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return empleados;
    }

    /**
     * Obtiene un empleado específico a partir de su DNI.
     * 
     * @param dni el DNI del empleado
     * @return un objeto Empleado si se encuentra, o null si no existe
     * @throws DatosNoCorrectosException si los datos son incorrectos
     */
    public Empleado getEmpleadoPorDNI(String dni) throws DatosNoCorrectosException {
        Empleado empleado = null;
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLEADO_POR_DNI)) {
            preparedStatement.setString(1, dni);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                empleado = new Empleado(rs.getString("nombre"), rs.getString("dni"), rs.getString("sexo"),
                        rs.getInt("categoria"), rs.getInt("anyos"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    /**
     * Obtiene el salario de un empleado a partir de su DNI.
     * 
     * @param dni el DNI del empleado
     * @return el salario del empleado o 0 si no se encuentra
     */
    public double obtenerSalarioPorDNI(String dni) {
        double sueldo = 0;

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SALARIO_POR_DNI)) {

            preparedStatement.setString(1, dni);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                sueldo = rs.getDouble("sueldo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sueldo;
    }

    /**
     * Inserta un nuevo empleado en la base de datos y calcula su salario.
     * 
     * @param empleado el empleado a insertar
     * @throws SQLException si ocurre un error al insertar el empleado
     */
    public void insertarEmpleado(Empleado empleado) throws SQLException {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLEADO)) {

            preparedStatement.setString(1, empleado.getDni());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setString(3, String.valueOf(empleado.getSexo()));
            preparedStatement.setInt(4, empleado.getCategoria());
            preparedStatement.setInt(5, empleado.getAnyos());

            preparedStatement.executeUpdate();
        }

        Nomina nomina = new Nomina();
        double salario = nomina.calculaSueldo(empleado.getCategoria(), empleado.getAnyos());
        System.out.println(salario);

        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOMINA)) {

            preparedStatement.setString(1, empleado.getDni());
            preparedStatement.setDouble(2, salario);

            int filas = preparedStatement.executeUpdate();
            System.out.println(filas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un empleado existente.
     * 
     * @param empleado el empleado con datos actualizados
     * @throws SQLException si ocurre un error al actualizar el empleado
     */
    public void actualizarEmpleado(Empleado empleado) throws SQLException {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLEADO)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, String.valueOf(empleado.getSexo()));
            preparedStatement.setInt(3, empleado.getCategoria());
            preparedStatement.setInt(4, empleado.getAnyos());
            preparedStatement.setString(5, empleado.getDni());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Elimina un empleado de la base de datos por su DNI.
     * 
     * @param dni el DNI del empleado a eliminar
     * @throws SQLException si ocurre un error al eliminar el empleado
     */
    public void eliminarEmpleado(String dni) throws SQLException {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLEADO)) {

            preparedStatement.setString(1, dni);
            preparedStatement.executeUpdate();
        }
    }
    
    /**
     * Busca empleados en la base de datos con filtros opcionales.
     * 
     * @param dni el DNI del empleado (opcional)
     * @param nombre el nombre del empleado (opcional)
     * @param sexo el sexo del empleado (opcional)
     * @param categoria la categoría del empleado (opcional)
     * @param anyos los años de experiencia del empleado (opcional)
     * @return una lista de empleados que coinciden con los criterios de búsqueda
     */
    public List<Empleado> buscarEmpleado(String dni, String nombre, String sexo, Integer categoria, Integer anyos) {
        List<Empleado> empleados = new ArrayList<>();
        
        String sql = "SELECT * FROM empleados WHERE 1=1"; // Consulta base

        // Condiciones opcionales
        if (dni != null && !dni.isEmpty()) {
            sql += " AND dni = ?";
        }
        if (nombre != null && !nombre.isEmpty()) {
            sql += " AND nombre LIKE ?";
        }
        if (sexo != null && !sexo.isEmpty()) {
            sql += " AND sexo = ?";
        }
        if (categoria != null) {
            sql += " AND categoria = ?";
        }
        if (anyos != null) {
            sql += " AND anyos = ?";
        }
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;

            if (dni != null && !dni.isEmpty()) {
                ps.setString(index++, dni);
            }
            if (nombre != null && !nombre.isEmpty()) {
                ps.setString(index++, "%" + nombre + "%");
            }
            if (sexo != null && !sexo.isEmpty()) {
                ps.setString(index++, sexo);
            }
            if (categoria != null) {
                ps.setInt(index++, categoria);
            }
            if (anyos != null) {
                ps.setInt(index++, anyos);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo"),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }
}