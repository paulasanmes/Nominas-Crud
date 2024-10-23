package com.aprendec.model;

/**
 * Clase que representa a un empleado.
 * Contiene información básica del empleado, incluyendo nombre, DNI, sexo, categoría y años de experiencia.
 * 
 * @author Paula
 * @version 1.0
 */
public class Empleado {
    private String nombre;
    private String dni;
    private String sexo;
    private int categoria;
    private int anyos;

    /**
     * Constructor que inicializa todos los atributos del empleado.
     * 
     * @param nombre el nombre del empleado
     * @param dni el DNI del empleado
     * @param sexo el sexo del empleado
     * @param categoria la categoría del empleado
     * @param anyos los años de experiencia del empleado
     */
    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    /**
     * Constructor vacío.
     */
    public Empleado() {
    }

    // Getters y setters

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return el nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     * 
     * @param nombre el nuevo nombre del empleado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el DNI del empleado.
     * 
     * @return el DNI del empleado
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del empleado.
     * 
     * @param dni el nuevo DNI del empleado
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el sexo del empleado.
     * 
     * @return el sexo del empleado
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del empleado.
     * 
     * @param sexo el nuevo sexo del empleado
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene la categoría del empleado.
     * 
     * @return la categoría del empleado
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del empleado.
     * 
     * @param categoria la nueva categoría del empleado
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene los años de experiencia del empleado.
     * 
     * @return los años de experiencia del empleado
     */
    public Integer getAnyos() {
        return anyos;
    }

    /**
     * Establece los años de experiencia del empleado.
     * 
     * @param anyos los nuevos años de experiencia del empleado
     */
    public void setAnyos(Integer anyos) {
        this.anyos = anyos;
    }

    /**
     * Retorna una representación en forma de cadena del empleado.
     * 
     * @return una cadena que representa al empleado
     */
    @Override
    public String toString() {
        return "Empleado [nombre=" + nombre + ", dni=" + dni + ", sexo=" + sexo + ", categoria=" + categoria
                + ", anyos=" + anyos + "]";
    }
}