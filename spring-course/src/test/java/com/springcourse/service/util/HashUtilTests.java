package com.springcourse.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.springcourse.services.util.HashUtil;

public class HashUtilTests {
	
	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("12345"); 
		
		assertThat(hash.length()).isEqualTo(64);
	}
	
}
