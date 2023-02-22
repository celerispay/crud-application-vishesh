package com.example.demo.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealth implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().withDetail("success code", "The server is up !!").build();
	}

}
