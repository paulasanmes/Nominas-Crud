package com.aprendec.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * La clase {@code Conexion} maneja la conexión a una base de datos MySQL
 * utilizando un pool de conexiones proporcionado por {@code BasicDataSource}.
 * 
 * <p> Esta clase es un singleton que garantiza que solo haya una instancia de
 * {@code BasicDataSource} en la aplicación, optimizando así el uso de
 * conexiones a la base de datos.</p>
 * 
 * <p> Proporciona un método estático {@link #getConnection()} que permite obtener
 * una conexión a la base de datos configurada.</p>
 */
public class Conexion {
    
    private static BasicDataSource dataSource = null;

    /**
     * Obtiene la instancia del {@code DataSource}. Si no existe, la crea
     * y la configura con los parámetros de conexión a la base de datos.
     * 
     * @return el {@code DataSource} configurado.
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("1234");
            dataSource.setUrl("jdbc:mysql://localhost:3306/nominas?useTimezone=true&serverTimezone=UTC");
            dataSource.setInitialSize(20);
            dataSource.setMaxIdle(15);
            dataSource.setMaxTotal(20);
            dataSource.setMaxWaitMillis(5000);
        }
        return dataSource;
    }

    /**
     * Obtiene una conexión a la base de datos.
     * 
     * @return una conexión a la base de datos configurada.
     * @throws SQLException si se produce un error al obtener la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}