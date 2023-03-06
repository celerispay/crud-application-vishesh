package com.example.demo.writter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;

@Component
public class FirstItemWriter implements ItemWriter<Student>{
	@Override
	public void write(List<? extends Student> items) throws Exception {
		System.out.println("Inside item writer");
		items.forEach(System.out::println);
	}
}