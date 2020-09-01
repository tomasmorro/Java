package com.pgrsoft.springbatchlab.ejemplo02;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job2Config extends AbstractJobConfig {

	@Autowired
	private ItemProcessor<Person,Person> processor;
	
	@Autowired
	private JobExecutionListener listener;
		
	@Bean
	public Job job2() {
		return jobBuilderFactory.get("job2")
				.listener(listener)
				.flow(step2())
				.end()
				.build();
	}
		
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Person,Person> chunk(5)
				.reader(reader2())
				.processor(processor)
				.writer(writer2())
				.build();
	}
	
	public FlatFileItemReader<Person> reader2(){
		return new FlatFileItemReaderBuilder<Person>()
			.name("reader2")
			.resource(new FileSystemResource("materiales/entradas/ejemplo02_personas.csv"))
			.linesToSkip(3)
			.delimited()
			.names(new String[] {"firstName","lastName"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
				setTargetType(Person.class);
			}}).build();
	}
	
	@Bean
	public JdbcBatchItemWriter<Person> writer2(){
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO PEOPLE (first_name, last_name) VALUES (:firstName, :lastName)")
				.dataSource(dataSource)
				.build();
	}
}
