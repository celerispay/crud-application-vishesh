package com.example.demo.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class DynamicInfo implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("User data", getUserCount());
	}
	
	public Map<String, Long> getUserCount() {
		Map<String, Long> userCount = new HashMap<>();
		userCount.put("Total", 120L);
		userCount.put("Active", 100L);
		userCount.put("Inactive", 20L);
		return userCount;
	}
}
