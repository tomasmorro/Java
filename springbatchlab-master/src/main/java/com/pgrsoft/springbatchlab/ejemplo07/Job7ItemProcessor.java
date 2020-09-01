package com.pgrsoft.springbatchlab.ejemplo07;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Job7ItemProcessor implements ItemProcessor<ProductDTO,Product>{

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Product process(ProductDTO productDTO) throws Exception {
		
		Product product = new Product();
		
		product.setCodigo(Integer.parseInt(productDTO.getCodigo()));
		product.setNombre(productDTO.getNombre());
		product.setPrecio(Double.parseDouble(productDTO.getPrecio()));
		product.setFamilia(productDTO.getFamilia());
		product.setDescatalogado(Boolean.parseBoolean(productDTO.getDescatalogado()));
		product.setFechaAlta(sdf.parse(productDTO.getFechaAlta()));
		
		return product;
	}
}
