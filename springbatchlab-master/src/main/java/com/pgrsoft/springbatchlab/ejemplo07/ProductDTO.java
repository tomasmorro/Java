package com.pgrsoft.springbatchlab.ejemplo07;

import java.io.Serializable;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 15454L;
	
	private String codigo;
	private String nombre;
	private String precio;
	private String fechaAlta;
	private String descatalogado;
	private String familia;
	
	public ProductDTO() {
		
	}

	public ProductDTO(String codigo, String nombre, String precio, String fechaAlta, String descatalogado, String familia) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaAlta = fechaAlta;
		this.descatalogado = descatalogado;
		this.familia = familia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getDescatalogado() {
		return descatalogado;
	}

	public void setDescatalogado(String descatalogado) {
		this.descatalogado = descatalogado;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	@Override
	public String toString() {
		return "ProductDTO [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fechaAlta=" + fechaAlta
				+ ", descatalogado=" + descatalogado + ", familia=" + familia + "]";
	}

}
