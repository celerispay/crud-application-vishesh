package com.example.demo.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	private String id;
	private String transactionReference;
	private BigDecimal amount;
	private String userId;
}
