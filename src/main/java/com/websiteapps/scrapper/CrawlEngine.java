package com.websiteapps.scrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.websiteapps.scrapper.domain.Product;

public class CrawlEngine {
	public List<Product> crawl(String product) throws Exception {
		List<Product> products = new ArrayList<Product>();
		Flipkart flipkart = new Flipkart(product);
		products.addAll(flipkart.scrap());
		Amazon amazon = new Amazon(product);
		products.addAll(amazon.scrap());
		Collections.sort(products);
		return products;
	}
}
