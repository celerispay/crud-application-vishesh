package com.example.demo.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;

class BatchConfigurationTest {
	
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	@Disabled
	void test() throws Exception {

	    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
	    JobInstance actualJobInstance = jobExecution.getJobInstance();
	    ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
	  
	    assertThat(actualJobInstance.getJobName()).isEqualTo("populateDatabase");
	    assertThat(actualJobExitStatus.getExitCode()).isEqualTo("COMPLETED");
	}
}