package com.example.demo.configuration;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.listener.SkipListener;
import com.example.demo.model.Transaction;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SkipListener skipListener;
	
	@Value("${batch.readData.filePath}")
	private String readDataFilePath;
	
	@Bean
	public Job transactionJob() {
		return jobBuilderFactory.get("populateDatabase")
				.incrementer(new RunIdIncrementer())
				.start(transactionStep())
				.build();
	}

	public Step transactionStep() {
		return stepBuilderFactory.get("readCsvWriteDatabase")
				.<Transaction, Transaction>chunk(3)
				.faultTolerant()
				.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.listener(skipListener)
				.reader(flatFileItemReader())
				.writer(jdbcBatchItemWriter())
				.build();
	}
	
	public FlatFileItemReader<Transaction> flatFileItemReader() {
		File file = new File(readDataFilePath);
		
		FlatFileItemReader<Transaction> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(file));
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
		lineTokenizer.setNames("ID", "Transaction Referenece", "Amount", "User ID");
		
		BeanWrapperFieldSetMapper<Transaction> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(Transaction.class);
		
		DefaultLineMapper<Transaction> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(mapper);
		
		flatFileItemReader.setLinesToSkip(1);
		
		flatFileItemReader.setLineMapper(lineMapper);
		
		return flatFileItemReader;
	}
	
	public JdbcBatchItemWriter<Transaction> jdbcBatchItemWriter() {
		JdbcBatchItemWriter<Transaction> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setSql("insert into transaction(id, transaction_reference, amount, user_id) values(:id, :transactionReference, :amount, :userId)");
	
		BeanPropertyItemSqlParameterSourceProvider<Transaction> sourceProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(sourceProvider);
		
		jdbcBatchItemWriter.afterPropertiesSet();
		return jdbcBatchItemWriter;
	}
}