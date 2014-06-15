package com.websiteapps.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websiteapps.scrapper.Flipkart;
import com.websiteapps.scrapper.domain.Product;

@Controller
public class Search {

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
	public @ResponseBody
	List<Product> searchProduct(@PathVariable String name) throws Exception {
		Flipkart flipkart = new Flipkart(name);
		return flipkart.scrap();
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public @ResponseBody
	String test(){
		return "Digvijay Mohte";
	}
}
