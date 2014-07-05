package com.websiteapps.scrapper;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.websiteapps.domain.Product;

/**
 * @author Digvijay
 *
 */
public abstract class BaseScrapper implements Runnable {
	protected CrawlEngine engine;
	protected String product;
	protected String baseUrl;
	
	public BaseScrapper(String product, CrawlEngine engine, String baseUrl) {
		this.product = product;
		this.engine = engine;
		this.baseUrl = baseUrl;
	}
	
	public Document doGet(String url) throws Exception {
		return Jsoup.connect(url).timeout(30 * 1000).get();
	}
	
	public abstract List<Product> scrap();
	
	@Override
	public void run() {
		List<Product> products = scrap();
		engine.addProducts(products);
	}

	public Float cleanPriceValue(String value) {
		value = value.replace("\u00a0", "");
		value = value.replace(",", "");
		return new Float(value);
	}

	public CrawlEngine getEngine() {
		return engine;
	}

	public void setEngine(CrawlEngine engine) {
		this.engine = engine;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}	
}
