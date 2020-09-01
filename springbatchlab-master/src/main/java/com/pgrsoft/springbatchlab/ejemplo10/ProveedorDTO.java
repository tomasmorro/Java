package com.pgrsoft.springbatchlab.ejemplo10;

import java.io.Serializable;

public class ProveedorDTO implements Serializable {
	private static final long serialVersionUID = -4645886571L;

	private String nombre;
	private String pais;
	
	public ProveedorDTO() {
		
	}
	
	public ProveedorDTO(String nombre, String pais) {
		this.nombre = nombre;
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "ProveedorDTO [nombre=" + nombre + ", pais=" + pais + "]";
	}

}
