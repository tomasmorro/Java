package com.pgrsoft.springbatchlab.ejemplo10;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * JpaRepository necesita conocer:
 * 
 * 1.- El tipo de entidad que se va a gestionar (Proveedor)
 * 2.- El tipo de la @Id (Long)
 *
 * Ojo! Este interface pertenece a org.springframework.data.jpa.repository.JpaRepository;
 *
 */

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long>{

	List<Proveedor> findByNombre(String nombre);
	
	@Query("select p.nombre, p.pais from Proveedor p")
	List<Object[]> metodoCualquiera();
	
	@Query("select new com.pgrsoft.springbatchlab.ejemplo10.ProveedorDTO(p.nombre, p.pais) from Proveedor p")
	List<ProveedorDTO> findDTO();
		
}
