package com.example.demo.listener;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.entity.Transaction;

class SkipListenerTest {

	@Test
	public void skipInReadTest() throws IOException {
		String filePath = "/home/vishesh/Desktop/read.csv";
		SkipListener skipListener = new SkipListener();
		ReflectionTestUtils.setField(skipListener, "skipInReadFilePath", filePath);
		FlatFileParseException ex = new FlatFileParseException("foo", "bar");
		skipListener.skipInRead(ex);
		File file = new File(filePath);
		assertThat(file.exists()).isTrue();
		file.delete();
	}
}