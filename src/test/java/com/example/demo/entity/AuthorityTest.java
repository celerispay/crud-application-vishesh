package com.example.demo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AuthorityTest {
	@Test
	public void getterAndSetterTest() {
		Authority authority = new Authority();
		authority.setId("foo");
		authority.setName("bar");
		
		Authority temp = new Authority();
		temp.setId("foo");
		temp.setName("bar");
		
		assertThat(authority).usingRecursiveComparison().isEqualTo(temp);
	}
}
