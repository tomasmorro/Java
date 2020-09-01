package com.pgrsoft.springbatchlab.ejemplo12;

import java.io.Serializable;

public class Pedido implements Serializable {
	private static final long serialVersionUID = -3534441L;
	
	public int codigo;
	public int cantidad;
	
	public Pedido() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cantidad=" + cantidad + "]";
	}
	
}
