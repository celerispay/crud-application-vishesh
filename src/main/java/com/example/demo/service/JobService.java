package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.JobParamsRequest;

@Async
@Service
public class JobService {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job firstJob;
	
	public void startJob(String jobName, List<JobParamsRequest> jobParamsRequestList) throws Exception {
		Map<String, JobParameter> params = new HashMap<>();
		
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
	
		jobParamsRequestList.forEach(jobParams -> {
			params.put(jobParams.getParamKey(), new JobParameter(jobParams.getParamValue()));
		});
		
		JobParameters jobParameters = new JobParameters(params);
		
		if (jobName.equals("First Job")) {
			jobLauncher.run(firstJob, jobParameters);
		} else {
			System.out.println("Invalid job name");
		}
	}
	
}
