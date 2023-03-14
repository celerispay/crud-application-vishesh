package com.example.demo.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class TransactionTest {

	@Test
	void getterAndSetterTest() {
		Transaction transaction = new Transaction();
		transaction.setId("foo");
		transaction.setAmount(BigDecimal.valueOf(99.99));
		transaction.setTransactionReference("bar");
		transaction.setUserId("xyz");
	
		Transaction temp = new Transaction();
		temp.setId("foo");
		temp.setAmount(BigDecimal.valueOf(99.99));
		temp.setTransactionReference("bar");
		temp.setUserId("xyz");
		
		assertThat(transaction).usingRecursiveComparison().isEqualTo(temp);
	}

}
