package com.pgrsoft.springbatchlab.ejemplo02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class Job2CompletionNotificationListener extends JobExecutionListenerSupport{

	private static final Logger log = LoggerFactory.getLogger(Job2CompletionNotificationListener.class);
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		log.info("job2: " + jobExecution.getStatus().toString());
		
	}

	
	
	
}
