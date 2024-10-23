package com.aprendec.model;

/**
 * Clase que representa la nómina de un empleado.
 * Contiene información sobre el DNI del empleado y su salario.
 * 
 * @author Paula
 * @version 1.0
 */
public class Nomina {
    private String dni;
    private double salario;

    // Sueldo base por categoría
    private static final double SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
            230000 };

    /**
     * Constructor vacío.
     */
    public Nomina() {

    }

    /**
     * Constructor que inicializa el DNI y el salario de la nómina.
     * 
     * @param dni el DNI del empleado
     * @param salario el salario del empleado
     */
    public Nomina(String dni, double salario) {
        this.dni = dni;
        this.salario = salario;
    }

    // Getters y setters

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
     * Obtiene el salario del empleado.
     * 
     * @return el salario del empleado
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Establece el salario del empleado.
     * 
     * @param salario el nuevo salario del empleado
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Calcula el sueldo de un empleado basado en su categoría y años trabajados.
     * 
     * @param categoria la categoría del empleado (1-10)
     * @param anosTrabajados los años trabajados por el empleado
     * @return el sueldo calculado del empleado
     */
    public double calculaSueldo(int categoria, int anosTrabajados) {
        return SUELDO_BASE[categoria - 1] + anosTrabajados * 5000;
    }

}