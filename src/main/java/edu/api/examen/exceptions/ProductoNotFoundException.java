/**
 * 
 */
package edu.api.examen.exceptions;

/**
 * Excepcion que salta si no se encuentra un Producto especificado
 */
public class ProductoNotFoundException extends RuntimeException {
	public ProductoNotFoundException(String mensaje) {
		super(mensaje);
	}
}
