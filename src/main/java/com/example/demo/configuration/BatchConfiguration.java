package com.example.demo.configuration;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;
import com.example.demo.processor.FirstItemProcessor;
import com.example.demo.reader.FirstItemReader;
import com.example.demo.writter.FirstItemWriter;

@Component
public class BatchConfiguration {
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
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())
				.next(secondStep())
				.build();
	}

	public Step firstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step")
				.<Student, Student>chunk(3)
				.reader(jsonItemReader())
				//.processor(firstItemProcessor)
				.writer(firstItemWriter)
				.build();
	}
	
	public Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet())
				.build();
	}

	/*
	 * file: where out csv file is located
	 *
	 * FlatFileItemReader: 
	 * 		- Responsible for reading the csv file.
	 * 		- Needs a LineMapper
	 * 
	 *	LineMapper:
	 *		- Needs a LineTokenizer & FieldSetMapper 							
	 */
	public FlatFileItemReader<Student> flatFileItemReader() {
		File file = new File("/home/vishesh/Desktop/code/crud-application-vishesh/file.csv");
		
		FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(file));
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("|");
		lineTokenizer.setNames("ID", "First Name", "Last Name", "Email");
		
		BeanWrapperFieldSetMapper<Student> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(Student.class);
		
		DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(mapper);
		
		flatFileItemReader.setLinesToSkip(1);
		
		flatFileItemReader.setLineMapper(lineMapper);
		
		return flatFileItemReader;
	}
	
	public JsonItemReader<Student> jsonItemReader() {
		File file = new File("/home/vishesh/Desktop/code/crud-application-vishesh/file.json");
		
		JsonItemReader<Student> itemReader = new JsonItemReader<>();
		itemReader.setResource(new FileSystemResource(file));
		
		JacksonJsonObjectReader<Student> objectReader = new JacksonJsonObjectReader<>(Student.class);
		
		itemReader.setJsonObjectReader(objectReader);
		
		return itemReader;
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
}
