/**
 * 
 */
package edu.api.examen.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.api.examen.entities.Producto;

/**
 * Repositorio de la entidad Prodcuto
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

	/**
	 * Metodo que comprueba que no exista la clave unica antes de crear
	 * @param codigoSku
	 * @return
	 */
	// --- CREATE / VALIDACIÓN ---
	// Comprobar si existe por la clave única antes de crear
	boolean existsByCodigoSku(String codigoSku);

	/**
	 * Metodo que busca por la clave única (retorna Optional en vez de List al ser única)
	 * @param codigoSku
	 * @return
	 */
	// --- READ ---
	// Buscar por la clave única (retorna Optional en vez de List al ser única)
	Optional<Producto> findByCodigoSku(String codigoSku);

	/**
	 * Metodo que elimina por la clave única (retorna la cantidad de registros eliminados o void)
	 * @param codigoSku
	 */
	// --- DELETE ---
	// Eliminar por la clave única (retorna la cantidad de registros eliminados o void)
	void deleteByCodigoSku(String codigoSku);
}
