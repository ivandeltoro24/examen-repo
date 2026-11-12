/**
 * 
 */
package edu.api.examen.dtos;

import java.time.LocalDate;

/**
 * Record de tipo response de la entidad Producto
 */
public record ProductoResponseDto(
		Long idProduct,
		String codigoSku,
		String nombre,
		String descripcion,
		double precio,
		int stock,
		LocalDate fechaAlta) {

}
