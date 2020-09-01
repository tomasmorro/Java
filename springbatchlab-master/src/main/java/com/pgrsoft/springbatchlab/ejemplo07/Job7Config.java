package com.pgrsoft.springbatchlab.ejemplo07;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job7Config extends AbstractJobConfig {
	
	@Autowired
	@Qualifier("job7Writer")
	private ItemWriter<Product> writer;
	
	@Autowired
	@Qualifier("job7ItemProcessor")
	private ItemProcessor<ProductDTO,Product> processor;
	
	@Bean
	public Job job7() {
		return jobBuilderFactory.get("job7")
				.flow(step7())
				.end()
				.build();
	}
	
	@Bean
	public Step step7() {
		return stepBuilderFactory.get("step7")
				.<ProductDTO,Product> chunk(10)
				.reader(productReaderFromList())
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	public ItemReader<ProductDTO> productReaderFromList(){
		
		final List<ProductDTO> products = IntStream.range(0,100)
				.mapToObj(x -> {
					ProductDTO product = new ProductDTO();
					
					product.setCodigo("" + x);
					product.setNombre("nombre_" + x);
					product.setDescatalogado("false");
					product.setFamilia("HARDWARE");
					product.setPrecio("100.0");
					product.setFechaAlta("20/10/2017");
					return product;
					
				}).collect(Collectors.toList());
		
		return new ListItemReader<ProductDTO>(products);

	}
	
}
