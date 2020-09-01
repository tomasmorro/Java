package com.pgrsoft.springbatchlab.ejemplo01;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job1Config extends AbstractJobConfig {
	

	@Bean
	public Job job1() {
		return jobBuilderFactory.get("job1")
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Person,Person> chunk(10)
				.reader(reader1())
				.writer(writer1())
				.build();
	}
	
	public FlatFileItemReader<Person> reader1(){
		return new FlatFileItemReaderBuilder<Person>()
			.name("reader1")
			.resource(new FileSystemResource("materiales/entradas/ejemplo01_personas.csv"))
			.delimited()
			.names(new String[] {"firstName","lastName"})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
				setTargetType(Person.class);
			}}).build();
	}
	
	@Bean // Necesario que esté registrado como Bean!!
	public JdbcBatchItemWriter<Person> writer1(){
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO PEOPLE (first_name, last_name) VALUES (:firstName, :lastName)")
				.dataSource(dataSource)
				.build();
	}
}
