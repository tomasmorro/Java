package com.pgrsoft.springbatchlab.ejemplo12;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job12Config extends AbstractJobConfig{

	@Autowired
	@Qualifier("job12Writer")
	private ItemWriter<Pedido> writer;
	
	@Bean
	public Job job12() {
		return jobBuilderFactory.get("job12")
				.flow(step12())
				.end()
				.build();
	}
	
	public Step step12() {
		return stepBuilderFactory.get("step12")
				.<Pedido,Pedido> chunk(10)
				.reader(pedidoItemReader())
				.writer(writer)
				.build();
	}
	
	@Bean
	public ItemReader<Pedido> pedidoItemReader(){
		
		FlatFileItemReader<Pedido> reader = new FlatFileItemReader<>();
		
		reader.setResource(new FileSystemResource("materiales/entradas/ejemplo12_pedidos.csv"));
		reader.setLineMapper(pedidoLineMapper());
		
		return reader;
	}
	
	public LineMapper<Pedido> pedidoLineMapper(){
		DefaultLineMapper<Pedido> mapper = new DefaultLineMapper<>();
		mapper.setLineTokenizer(pedidoLineTokenizer());
		mapper.setFieldSetMapper(new Mapper12());
		return mapper;
	}
	
	public LineTokenizer pedidoLineTokenizer() {
		
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		
		tokenizer.setColumns(new Range[] {new Range(1,4), new Range(5,6)});
		
		// No es necesario aportar estos nombres
		// si existe una correspondencia 1 a 1 con los nombres de la clase Pedido
		tokenizer.setNames(new String[] {"elA","elB"});
		
		return tokenizer;
	}
	
}
