package com.pgrsoft.springbatchlab.ejemplo14;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job14Config extends AbstractJobConfig{

	Logger logger = LoggerFactory.getLogger(Job14Config.class);
	
	@Bean
	public Job job14() {
		
		Flow masterFlow = (Flow) new FlowBuilder<Object>("masterFlow").start(taskletStep("stepMaster")).build();
		
		Flow flow1 = (Flow) new FlowBuilder<Object>("flow1").start(taskletStep("step1")).build();
		Flow flow2 = (Flow) new FlowBuilder<Object>("flow2").start(taskletStep("step2")).build();
		Flow flow3 = (Flow) new FlowBuilder<Object>("flow3").start(taskletStep("step3")).build();
		
		// Posibilita la ejecución en paralelo...
		Flow slaveFlow = (Flow) new FlowBuilder<Object>("slaveFlow").split(new SimpleAsyncTaskExecutor())
				         .add(flow1, flow2, flow3).build();
	
		return (jobBuilderFactory.get("job14")
				.incrementer(new RunIdIncrementer())
				.start(masterFlow)
				.next(slaveFlow)
				.build()).build();
	}
	
	private TaskletStep taskletStep(String step) {
		return stepBuilderFactory.get(step).tasklet((a,b) -> {
				IntStream.range(1,100)
					     .forEach(token -> logger.info("step: " + step + " token: " + token));
				return RepeatStatus.FINISHED;
		}).build();
	}
	
}
