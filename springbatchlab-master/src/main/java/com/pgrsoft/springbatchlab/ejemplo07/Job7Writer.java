package com.pgrsoft.springbatchlab.ejemplo07;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class Job7Writer implements ItemWriter<Product> {

	@Override
	public void write(List<? extends Product> products) throws Exception {
		products.forEach(System.out::println);
	}
}
