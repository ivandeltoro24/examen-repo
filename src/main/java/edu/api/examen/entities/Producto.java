/**
 * 
 */
package edu.api.examen.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Clase que contiene la entidad Producto
 */
@Entity
@Table(name = "productos", schema = "examen", uniqueConstraints = {
		@UniqueConstraint(name = "uk_productos_codigo_sku", columnNames = "codigo_sku")
})
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "codigo_sku", nullable = false, length = 10, unique = true)
	private String codigoSku;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	@Column(name = "precio", nullable = false)
	private double precio;
	
	@Column(name = "stock", nullable = false)
	private int stock;
	
	@Column(name = "fecha_alta", nullable = false)
	private LocalDate fechaAlta;
/**
 * Constructor vacio
 */
	public Producto() {
		super();
	}
/**
 * Constructor de la entidad Producto relleno para operar con él
 * @param idProduct
 * @param codigoSku
 * @param nombre
 * @param descripcion
 * @param precio
 * @param stock
 * @param fechaAlta
 */
	public Producto(Long idProduct, String codigoSku, String nombre, String descripcion, double precio, int stock,
			LocalDate fechaAlta) {
		super();
		this.idProduct = idProduct;
		this.codigoSku = codigoSku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Getters y setters
	 * @return Devuelve todos los getters y setter de la entidad Producto
	 */
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getCodigoSku() {
		return codigoSku;
	}

	public void setCodigoSku(String codigoSku) {
		this.codigoSku = codigoSku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
}
