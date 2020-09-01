package com.pgrsoft.springbatchlab.ejemplo10;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job10Config extends AbstractJobConfig {

	@Autowired
	private ItemWriter<Proveedor> writer;
	
	@Bean
	public Job job10() {
		return jobBuilderFactory.get("job10")
				.flow(step10())
				.end()
				.build();
	}
	
	@Bean
	public Step step10() {
		return stepBuilderFactory.get("step10")
				.<Proveedor,Proveedor> chunk(10)
				.reader(reader10())
				.writer(writer)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<Proveedor> reader10() {
		return new FlatFileItemReaderBuilder<Proveedor>()
				.name("reader10")
				.resource(new FileSystemResource("materiales/entradas/ejemplo10_proveedores.csv"))
				.delimited()
				.names(new String[] {"codigo","nombre","pais"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Proveedor>() {{
					setTargetType(Proveedor.class);
				}}).build();
	}
	
}
