/**
 * 
 */
package edu.api.examen.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.api.examen.dtos.ProductoRequestDto;
import edu.api.examen.dtos.ProductoResponseDto;
import edu.api.examen.entities.Producto;
import edu.api.examen.exceptions.CodigoSkuAlreadyExistsException;
import edu.api.examen.exceptions.ProductoNotFoundException;
import edu.api.examen.repositories.ProductoRepository;

/**
 * Clase que contiene los servicios del a entidad Producto
 */
@Service
public class ProductoService {

	private final ProductoRepository productoRepository;

	// Inyección de dependencias por constructor
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	/**
	 * Metodo que lista a todas las entidades de tipo Producto
	 * @return devuelve todas las entidades Producto existentes
	 */
	@Transactional(readOnly = true)
	public List<ProductoResponseDto> obtenerTodos() {
		return productoRepository.findAll().stream().map(this::mapToResponseDto).toList();
	}
	/**
	 * Metodo que crea las entidades Producto dto con la excepcion de si ya existe una entidad Producto con el mismo codigoSku
	 * @param dto
	 * @return devuelve la entidad creada Producto
	 */
	@Transactional
	public ProductoResponseDto crear(ProductoRequestDto dto) {
		// Validación de regla de negocio: codigo único
		if (productoRepository.existsByCodigoSku(dto.codigoSku())) {
			throw new CodigoSkuAlreadyExistsException("Ya existe un producto registrado con el codigo: " + dto.codigoSku());
		}

		Producto nuevoProducto = new Producto(
				dto.idProduct(),
				dto.codigoSku(),
				dto.nombre(),
				dto.descripcion(),
				dto.precio(),
				dto.stock(),
				dto.fechaAlta()
		);

		Producto guardado = productoRepository.save(nuevoProducto);
		return mapToResponseDto(guardado);
	}
	/**
	 * Metodo que actualiza las entidades Producto dto por el codigoSku y si no lo encuentra devuelve una excepcion
	 * @param codigoSku
	 * @param dto
	 * @return devuelve la entidad actualizada
	 */
	@Transactional
	public ProductoResponseDto actualizar(String codigoSku, ProductoRequestDto dto) {
		Producto productoExistente = productoRepository.findByCodigoSku(codigoSku)
				.orElseThrow(() -> new ProductoNotFoundException("Prodcuto no encontrado con el codigo: " + codigoSku));

		// Si se modifica el codigo, comprobar que el nuevo codigo no pertenezca a otro producto
		if (!productoExistente.getCodigoSku().equalsIgnoreCase(dto.codigoSku()) && productoRepository.existsByCodigoSku(dto.codigoSku())) {
			throw new CodigoSkuAlreadyExistsException("El codigo " + dto.codigoSku() + " ya está en uso por otro producto.");
		}

		productoExistente.setIdProduct(dto.idProduct());
		productoExistente.setCodigoSku(dto.codigoSku());
		productoExistente.setNombre(dto.nombre());
		productoExistente.setDescripcion(dto.descripcion());
		productoExistente.setPrecio(dto.precio());
		productoExistente.setStock(dto.stock());
		productoExistente.setFechaAlta(dto.fechaAlta());

		Producto actualizado = productoRepository.save(productoExistente);
		return mapToResponseDto(actualizado);
	}
	/**
	 * Metodo que elimina las entidades Producto por el codigoSku
	 * @param codigoSku
	 */
	@Transactional
	public void eliminar(String codigoSku) {
		if (!productoRepository.existsByCodigoSku(codigoSku)) {
			throw new ProductoNotFoundException("Imposible eliminar. Producto no encontrado con el codigo: " + codigoSku);
		}
		productoRepository.deleteByCodigoSku(codigoSku);
	}
	
	/**
	 *  Método auxiliar de mapeo de Entidad a DTO
	 * @param producto
	 * @return
	 */
	private ProductoResponseDto mapToResponseDto(Producto producto) {
		return new ProductoResponseDto(
				producto.getIdProduct(),
				producto.getCodigoSku(),
				producto.getNombre(),
				producto.getDescripcion(),
				producto.getPrecio(),
				producto.getStock(),
				producto.getFechaAlta()
		);
	}
}
