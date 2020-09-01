package com.pgrsoft.springbatchlab.ejemplo11;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job11Config extends AbstractJobConfig {
	
	@Autowired
	@Qualifier("job11Writer")
	private ItemWriter<Acreedor> writer;
	
	@Bean
	public Job job11() {
		return jobBuilderFactory.get("job11")
				.flow(step11())
				.end()
				.build();
	}
	
	@Bean
	public Step step11() {
		return stepBuilderFactory.get("step11")
				.<Acreedor,Acreedor> chunk(10)
				.reader(jsonItemReader())
				.writer(writer)
				.build();			
	}
	
	@Bean
	public JsonItemReader<Acreedor> jsonItemReader(){
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JacksonJsonObjectReader<Acreedor> jsonObjectReader = new JacksonJsonObjectReader<>(Acreedor.class);
		jsonObjectReader.setMapper(objectMapper);
		
		return new JsonItemReaderBuilder<Acreedor>()
				.name("acreedorJsonItemReader")
				.jsonObjectReader(jsonObjectReader)
				.resource(new FileSystemResource("materiales/entradas/ejemplo11_acreedores.json"))
				.build();
	}
	

}
