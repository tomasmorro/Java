package com.pgrsoft.springbatchlab.ejemplo08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job8Config extends AbstractJobConfig {
	
	@Bean
	public Job job8() {
		return jobBuilderFactory.get("job8")
				.flow(step8())
				.end()
				.build();
	}
	
	@Bean
	public Step step8() {
		return stepBuilderFactory.get("step8")
				.<Person,Person> chunk(10)
				.reader(xmlReader())
				.writer(writer8())
				.build();
	}
	
	@Bean
	public StaxEventItemReader<Person> xmlReader(){
		
		return new StaxEventItemReaderBuilder<Person>()
			.name("reader")
			.resource(new FileSystemResource("materiales/entradas/ejemplo08_personas.xml"))
			.addFragmentRootElements("person")
			.unmarshaller(personMarshaller())
			.build();
	}
	
	@Bean
	public XStreamMarshaller personMarshaller() {
	
		Map<String,Class<?>> aliases = new HashMap<>();
		aliases.put("person",Person.class);
		aliases.put("firstName",String.class);
		aliases.put("lastName",String.class);
		
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliases);
		return marshaller;
	}
	
	public ItemWriter<Person> writer8(){
		return new ItemWriter<Person>() {

			@Override
			public void write(List<? extends Person> items) throws Exception {
				items.forEach(System.out::println);
			}
		};
	}
	
}
