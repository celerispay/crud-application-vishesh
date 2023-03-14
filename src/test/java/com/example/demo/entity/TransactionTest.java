package com.example.demo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class TransactionTest {
	@Test
	public void getterAndSetterTest() {
		Transaction transaction = new Transaction();
		transaction.setId("foo");
		transaction.setTransactionReference("bar");
		transaction.setAmount(BigDecimal.valueOf(999.99));
		
		Transaction temp = new Transaction();
		temp.setId("foo");
		temp.setTransactionReference("bar");
		temp.setAmount(BigDecimal.valueOf(999.99));
		
		assertThat(transaction).usingRecursiveComparison().isEqualTo(temp);
	}

}
