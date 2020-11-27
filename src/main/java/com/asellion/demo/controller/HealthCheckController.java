package com.asellion.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling healthcheck request
 * 
 * @author sandeep
 *
 */
@RestController
public class HealthCheckController {

	/**
	 * Operation handling health check of API
	 * 
	 * @return Boolean
	 * test
	 */
	@GetMapping(path = "/api/products/healthcheck.html")
	public Boolean isHealthy() {
		return Boolean.TRUE;
	}

}
