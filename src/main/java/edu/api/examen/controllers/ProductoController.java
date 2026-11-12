package edu.api.examen.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.api.examen.dtos.ProductoRequestDto;
import edu.api.examen.dtos.ProductoResponseDto;
import edu.api.examen.services.ProductoService;
/**
 * Clase controller de la entidad Producto
 */
@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	/**
	 * Innyeccion de dependencias de ProductoService
	 */
	 private final ProductoService productoService;

	    public ProductoController(ProductoService productoService) {
	        this.productoService = productoService;
	    }
	 /**
	  * Metodo GET que devuelve todas las entidades Producto  
	  * @return
	  */
	// READ ALL - GET /api/productos
	@GetMapping
	public ResponseEntity<List<ProductoResponseDto>> obtenerTodos() {
		return ResponseEntity.ok(productoService.obtenerTodos());
	}
	/**
	 * Metodo POST que crea la entidad Producto 
	 * @param dto
	 * @return devuelve un http 200 si se creo correctamente, en caso opuesto devolvera error 
	 */
	// CREATE - POST /api/productos
	@PostMapping
	public ResponseEntity<ProductoResponseDto> crear(@RequestBody ProductoRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(dto));
	}
	
	/**
	 * Metodo DELETE que elimina la entidad Producto
	 * @param codigoSku
	 * @return devuelve un ok si se elimina correctamente
	 */
	// DELETE - DELETE /api/productos/{codigo_sku}
	@DeleteMapping("/{codigo_sku}")
	public ResponseEntity<Void> eliminar(@PathVariable String codigoSku) {
		productoService.eliminar(codigoSku);
		return ResponseEntity.noContent().build();
	}
}
