package com.pgrsoft.springbatchlab.ejemplo12;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class Mapper12 implements FieldSetMapper<Pedido>{

	@Override
	public Pedido mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Pedido pedido = new Pedido();
		
		pedido.setCodigo(fieldSet.readInt("elB"));
		pedido.setCantidad(fieldSet.readInt("elA"));
		
		//Si no queremos utilizar los nombres...
		
		//pedido.setCodigo(fieldSet.readInt(0));
		//pedido.setCantidad(fieldSet.readInt(1));
		
		return pedido;
	}

}
