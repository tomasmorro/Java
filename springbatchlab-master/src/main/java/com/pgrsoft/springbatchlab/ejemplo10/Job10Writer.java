package com.pgrsoft.springbatchlab.ejemplo10;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Job10Writer implements ItemWriter<Proveedor> {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Transactional
	@Override
	public void write(List<? extends Proveedor> items) throws Exception {
		proveedorRepository.saveAll(items);
	
		//prueba 1...
		List<Object[]> resultado = proveedorRepository.metodoCualquiera();
		
		resultado.forEach(x -> {
			System.out.println(x[0] + " " + x[1]);
		});
		
		//prueba 2...
		List<ProveedorDTO> proveedoresDTO = proveedorRepository.findDTO();
		
		System.out.println(proveedoresDTO);

	}
}
