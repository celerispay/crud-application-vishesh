package com.example.demo.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;

@Component
public class FirstItemProcessor implements ItemProcessor<Student, Student> {
	@Override
	public Student process(Student item) throws Exception {
		if (Integer.valueOf(item.getId())%2 != 0) throw new NullPointerException();
		item.setId(UUID.randomUUID().toString());
		return item;
	}
}
