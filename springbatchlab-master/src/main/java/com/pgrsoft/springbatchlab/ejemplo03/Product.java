package com.pgrsoft.springbatchlab.ejemplo03;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	private static final long serialVersionUID = 156465456L;
	
	private int codigo;
	private String nombre;
	private double precio;
	private Date fechaAlta;
	private boolean descatalogado;
	private String familia;
	
	public Product() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isDescatalogado() {
		return descatalogado;
	}

	public void setDescatalogado(boolean descatalogado) {
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
		return "Product [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", fechaAlta=" + fechaAlta
				+ ", descatalogado=" + descatalogado + ", familia=" + familia + "]";
	}

}
