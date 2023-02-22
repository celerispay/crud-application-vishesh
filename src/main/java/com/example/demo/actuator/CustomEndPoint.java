package com.example.demo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="demo", enableByDefault = true)
public class CustomEndPoint {
	
	@ReadOperation
	public Integer demoEndPoint() {
		return 1234;
	}
}