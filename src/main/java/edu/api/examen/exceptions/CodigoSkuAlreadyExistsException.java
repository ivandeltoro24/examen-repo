/**
 * 
 */
package edu.api.examen.exceptions;

/**
 * Excepcion que salta si se intenta crear una entidad con un codigoSku ya existente
 */
public class CodigoSkuAlreadyExistsException extends RuntimeException {
	public CodigoSkuAlreadyExistsException(String mensaje) {
		super(mensaje);
	}
}
