package com.websiteapps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Digvijay
 *
 */
@Controller
public class Index {
	
	@RequestMapping("/")
	public String hello() {
		return "index";
	}
}
