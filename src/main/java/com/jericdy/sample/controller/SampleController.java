package com.jericdy.sample.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SampleController {

	@RequestMapping("/")
	public String index() {
		return "Hello World!";
	}

	@RequestMapping("/healthcheck")
	public String healthCheck() {
		return "OK";
	}

}
