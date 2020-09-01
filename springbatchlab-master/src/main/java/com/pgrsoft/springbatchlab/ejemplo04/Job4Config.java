package com.pgrsoft.springbatchlab.ejemplo04;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job4Config extends AbstractJobConfig {
	
	@Autowired
	@Qualifier("job4ItemProcessor")
	private ItemProcessor<Product,Product> processor;
	
	@Bean
	public Job job4() {
		return jobBuilderFactory.get("job4")
				.flow(step4())
				.end()
				.build();
	}
	
	@Bean
	public Step step4() {
		return stepBuilderFactory.get("step4")
				.<Product,Product> chunk(10)
				.reader(reader4(null))
				.processor(processor)
				.writer(writer4())
				.build();
	}
	
	@Bean(destroyMethod="") // Acto de FE
	@StepScope // se instancia un nuevo reader para cada vez que se ejecuta el step...
	public JdbcCursorItemReader<Product> reader4 (@Value ("#{jobParameters['familia']}") String familia){
	
		System.out.println("fase de construcción del reader4");
		
		JdbcCursorItemReader<Product> cursorItemReader = new JdbcCursorItemReader<>();
	
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT CODIGO, NOMBRE, PRECIO, FAMILIA FROM PRODUCTS WHERE FAMILIA = ?");
		cursorItemReader.setPreparedStatementSetter(new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,familia.toUpperCase());
			}
			
		});
		
		cursorItemReader.setRowMapper(new ProductRowMapper());
		
		return cursorItemReader;
	}
	
	public FlatFileItemWriter<Product> writer4(){
		
		FlatFileItemWriter<Product> writer = new FlatFileItemWriter<>();
		
		//write sin crear un fichero nuevo...
		//writer.setAppendAllowed(true);
		
		// dónde
		
		writer.setResource(new FileSystemResource("materiales/salidas/ejemplo04_productos.csv"));
		
		// cómo
		
		BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] {"codigo","nombre","precio","familia"});
		
		DelimitedLineAggregator<Product> lineAggregator = new DelimitedLineAggregator<>();
		
		lineAggregator.setDelimiter(",");
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
 		
		return writer;
	}

}
