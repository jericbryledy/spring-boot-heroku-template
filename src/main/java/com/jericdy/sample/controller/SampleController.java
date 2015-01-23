package com.jericdy.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Controller
public class SampleController {

	@Value("${application.welcomemessage}")
	private String message;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("index");
		mv.addObject("message", message);

		return mv;
	}

	@RequestMapping("/healthcheck")
	@ResponseBody
	public String healthCheck() {
		return "OK";
	}

}
