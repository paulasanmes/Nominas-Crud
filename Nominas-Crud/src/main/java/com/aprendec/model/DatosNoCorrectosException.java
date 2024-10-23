package com.aprendec.model;

/**
 * La clase DatosNoCorrectosException es una excepción personalizada que se lanza cuando
 * los datos introducidos para un empleado no son correctos, como una categoría fuera del
 * rango permitido o un número de años negativos.
 * 
 * @author Paula
 * @version 1.0
 */
public class DatosNoCorrectosException extends Exception {

    /**
     * Constructor que crea una excepción con un mensaje detallado.
     * 
     * @param message Mensaje que describe el motivo por el cual se lanza la excepción.
     */
    public DatosNoCorrectosException(String message) {
        super(message);
    }

}