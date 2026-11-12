/**
 * 
 */
package edu.api.examen.dtos;

import java.time.LocalDate;

/**
 * Record tipo request de la entidad Producto
 */
public record ProductoRequestDto(
		Long idProduct,
		String codigoSku,
		String nombre,
		String descripcion,
		double precio,
		int stock,
		LocalDate fechaAlta) {

}
