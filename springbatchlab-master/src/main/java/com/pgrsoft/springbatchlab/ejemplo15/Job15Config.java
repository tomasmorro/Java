package com.pgrsoft.springbatchlab.ejemplo15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pgrsoft.springbatchlab.batchconfiguration.AbstractJobConfig;

@Configuration
public class Job15Config extends AbstractJobConfig{

	private Logger logger = LoggerFactory.getLogger(Job15Config.class);
	
	@Bean
	public Job job15(Step conditionalStep1,
			         Step conditionalStep2,
			         Step conditionalStep3,
			         Step conditionalStep4,
			         Step conditionalStep5) 
	{
		return jobBuilderFactory.get("job15")
				.incrementer(new RunIdIncrementer())
				.flow(conditionalStep1).on(ExitStatus.FAILED.getExitCode()).to(conditionalStep3)
				.from(conditionalStep1).on("*").to(conditionalStep4)
				.from(conditionalStep1).on("PEPITO").to(conditionalStep2)       // BIEN
				.from(conditionalStep4).next(conditionalStep5)
				.end()
				.build();
	}
	
	@Bean
	public Step conditionalStep1() {
		return stepBuilderFactory
				.get("conditionalStep1")
				.tasklet((contribution, chunkContext) -> {
					
				// Vamos a recoger el parámetro fail
				// Aquí no sabemos si es un String o en que forma llega
				// Haremos un casting seguro...
					
				StepContext stepContext = chunkContext.getStepContext();
					
				Object fail = stepContext.getJobParameters().get("fail");
					
				switch (String.valueOf(fail)) {
					case "1": contribution.setExitStatus(ExitStatus.FAILED);
							  break;
					case "2": contribution.setExitStatus(new ExitStatus("PEPITO"));
							  break;
					}
					
				return RepeatStatus.FINISHED;
						
				}).build();
	}
	
	@Bean
	public Step conditionalStep2() {
		return stepBuilderFactory
				.get("conditionalStep2")
				.tasklet((a,b) -> {
					logger.info("conditionalStep2");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
	@Bean
	public Step conditionalStep3() {
		return stepBuilderFactory
				.get("conditionalStep3")
				.tasklet((a,b) -> {
					logger.info("conditionalStep3");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
	@Bean
	public Step conditionalStep4() {
		return stepBuilderFactory
				.get("conditionalStep4")
				.tasklet((a,b) -> {
					logger.info("conditionalStep4");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
	@Bean
	public Step conditionalStep5() {
		return stepBuilderFactory
				.get("conditionalStep5")
				.tasklet((a,b) -> {
					logger.info("conditionalStep5");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
}