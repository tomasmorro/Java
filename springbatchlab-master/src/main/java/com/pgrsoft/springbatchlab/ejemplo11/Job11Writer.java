package com.pgrsoft.springbatchlab.ejemplo11;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("job11Writer")
public class Job11Writer implements ItemWriter<Acreedor>{

	@Autowired
	private AcreedorRepository acreedorRepository;
	
	@Transactional
	@Override
	public void write(List<? extends Acreedor> items) throws Exception {
		acreedorRepository.saveAll(items);
	}

}
