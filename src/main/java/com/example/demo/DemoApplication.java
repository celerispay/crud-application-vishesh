package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.demo.processor.FirstItemProcessor;
import com.example.demo.reader.FirstItemReader;
import com.example.demo.writter.FirstItemWriter;

@SpringBootApplication
@EnableBatchProcessing
@EnableAsync
public class DemoApplication {	

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FirstItemReader firstItemReader;

	@Autowired
	private FirstItemProcessor firstItemProcessor;

	@Autowired
	private FirstItemWriter firstItemWriter;
	
	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.start(firstChunkStep())
				.next(secondStep())
				.build();
	}

	public Step firstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step")
				.<Integer, Long>chunk(3)
				.reader(firstItemReader)
				.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
	}
	
	public Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet())
				.build();
	}
	
	public Tasklet secondTasklet() {
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("This is second tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
