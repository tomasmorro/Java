package com.pgrsoft.springbatchlab.ejemplo06;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job6Config extends AbstractJobConfig{

	@Autowired
	@Qualifier("tasklet6")
	private Tasklet tasklet;
	
	@Bean
	public Job job6() {
		return jobBuilderFactory.get("job6")
			.flow(step6())
			.end()
			.build();
	}
	
	@Bean
	public TaskletStep step6() {
		return stepBuilderFactory.get("step6")
			.tasklet(tasklet)
			.build();
	}
}
