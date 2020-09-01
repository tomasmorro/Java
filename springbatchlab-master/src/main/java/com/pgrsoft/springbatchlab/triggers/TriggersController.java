package com.pgrsoft.springbatchlab.triggers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggersController {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	@Qualifier("asyncJobLauncher")
	private JobLauncher asyncJobLauncher;
	
	@Autowired
	@Qualifier("syncJobLauncher")
	private JobLauncher syncJobLauncher;
					
	@RequestMapping("/trigger/{job}")
	public String endPoint(@PathVariable ("job") String strJob,
						   @RequestParam Map<String,String> allParameters) throws Exception {
		
		Job job = context.getBean(strJob, Job.class);
		
		JobParameter jobParameter = new JobParameter("parametro_" + System.currentTimeMillis());
		
		Map<String,JobParameter> jobParametersMap = new HashMap<>();
		
		jobParametersMap.put("parametro1", jobParameter);
		
		switch (strJob) {
		
			case "job4":  jobParametersMap.put("familia", new JobParameter(allParameters.get("familia")));
						  break;
						 
			case "job15": jobParametersMap.put("fail", new JobParameter(allParameters.get("fail")));
			 			  break;
						 
		}
		
		JobParameters jobParameters = new JobParameters(jobParametersMap);
		
		JobExecution jobExecution = asyncJobLauncher.run(job, jobParameters);

		return "Estado " + strJob + ": " + jobExecution.getStatus().toString();
	}
			
}
