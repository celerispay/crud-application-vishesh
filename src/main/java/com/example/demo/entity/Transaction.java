package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {
	@Id
	private String id;
	private String transactionReference;
	private BigDecimal amount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
}