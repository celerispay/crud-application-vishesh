package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FirstJobScheduler {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job firstJob;	
	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void firstJobStarter() throws Exception {
		Map<String, JobParameter> params = new HashMap<>();
		
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(params);
		
		jobLauncher.run(firstJob, jobParameters);
	}
}
