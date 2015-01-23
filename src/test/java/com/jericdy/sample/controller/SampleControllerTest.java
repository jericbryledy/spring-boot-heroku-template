package com.jericdy.sample.controller;

import com.jericdy.sample.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@SpringApplicationConfiguration(classes = Application.class)
public class SampleControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SampleController controller;

	@Test
	public void a() {
		ModelAndView mv = controller.index();

		Assert.assertEquals(mv.getModel().get("message"), "Hello World!");
	}

}
