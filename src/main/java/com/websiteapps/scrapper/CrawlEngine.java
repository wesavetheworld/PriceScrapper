package com.websiteapps.scrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.websiteapps.domain.Product;
import com.websiteapps.filter.Filter;

/**
 * @author Digvijay
 * 
 */
public class CrawlEngine {
	List<Product> products = new ArrayList<Product>();

	synchronized void addProducts(List<Product> products) {
		this.products.addAll(products);
	}

	public List<BaseScrapper> initCrawler(String product) {
		List<BaseScrapper> crawlJobs = new ArrayList<BaseScrapper>();
		crawlJobs.add(new Flipkart(product, this));
		crawlJobs.add(new Amazon(product, this));
		return crawlJobs;
	}

	public List<Product> crawl(String product) throws Exception {
		List<BaseScrapper> crawlJobs = initCrawler(product);
		ExecutorService executor = Executors.newFixedThreadPool(crawlJobs.size());
		for (BaseScrapper scrapper : crawlJobs) {
			executor.execute((Runnable) scrapper);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		Filter.filter(products, product);
		return products;
	}

}
