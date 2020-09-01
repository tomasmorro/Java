package com.pgrsoft.springbatchlab.jobexplorer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobRepositoryController {
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@RequestMapping("/prueba")
	public String prueba(Model model) {
		
		List<JobExecution> jobExecutions = new ArrayList<>();
		
		for (String jobName: jobExplorer.getJobNames()) {
			
			List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, 10000);
			
			for (JobInstance jobInstance: jobInstances) {
				List<JobExecution> jobExecutionsForJobInstance = jobExplorer.getJobExecutions(jobInstance);
				jobExecutions.addAll(jobExecutionsForJobInstance);
			}
		}
		
		jobExecutions.sort((a,b) -> a.getId().compareTo(b.getId()));
	
		model.addAttribute("jobExecutions", jobExecutions);
		
		return "jobexplorer";
	}
	
}
