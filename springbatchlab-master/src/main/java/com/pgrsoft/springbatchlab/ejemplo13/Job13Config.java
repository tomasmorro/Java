package com.pgrsoft.springbatchlab.ejemplo13;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job13Config extends AbstractJobConfig{

	@Autowired
	private Step step1;
	
	@Autowired
	private Step step2;
	
	@Autowired
	private Step step8;
	
	@Bean
	public Job job13() {
		return jobBuilderFactory.get("job13")
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.next(step2)
				.next(step8)
				.next(step2)
				.next(step2)
				.build();
	}

}
