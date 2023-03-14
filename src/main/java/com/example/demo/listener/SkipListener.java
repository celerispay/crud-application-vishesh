package com.example.demo.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SkipListener {

	@Value("${batch.skipInRead.filePath}")
	private String skipInReadFilePath;

	@OnSkipInRead
	public void skipInRead(Throwable th) throws IOException {
		if (th instanceof FlatFileParseException) {
			createFile(skipInReadFilePath, 
					((FlatFileParseException) th).getInput());
		}
	}

	public void createFile(String filePath, String data) throws IOException {
		String lineSeperator = System.lineSeparator();
		File file = new File(filePath);
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file, true);
		try {
			fileWriter.write(data + lineSeperator);
		} finally {
			fileWriter.close();
		}
	}
}