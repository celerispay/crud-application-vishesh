package com.example.demo.listener;

import java.io.File;
import java.io.FileWriter;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Transaction;

@Component
public class SkipListener {
	@OnSkipInRead
	public void skipInRead(Throwable th) {
		if (th instanceof FlatFileParseException) {
			createFile("/home/vishesh/Documents/bad-record/read.csv", 
					((FlatFileParseException) th).getInput());
		}
	}

	@OnSkipInProcess
	public void skipInProcess(Transaction transaction, Throwable th) {
		if (th instanceof NullPointerException) {
			createFile("/home/vishesh/Documents/bad-record/process.csv", 
					transaction.toString());
		}
	}
	
	public void createFile(String filePath, String data) {
		try {
			File file = new File(filePath);
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(data + "\n");
			fileWriter.close();
		} catch(Exception e) {
			
		}
	}
}