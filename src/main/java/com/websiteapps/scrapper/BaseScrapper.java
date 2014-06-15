package com.websiteapps.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.websiteapps.scrapper.domain.Product;
import com.websiteapps.scrapper.domain.ProductSource;

public abstract class BaseScrapper {
	public Document doGet(String url) throws Exception {
		return Jsoup.connect(url).timeout(30 * 1000).get();
	}

	public Product buildProduct(String name, ProductSource source, String offer, String price, String description, String url, String img) {
		Product product = new Product();
		product.setName(name.trim());
		product.setSource(source);
		product.setOffer(offer);
		product.setPrice(cleanPriceValue(price));
		product.setDesciption(description);
		product.setUrl(url);
		product.setImg(img);
		return product;
	}
	
	public Float cleanPriceValue(String value){
		value = value.replace("\u00a0", "");
		value = value.replace(",","");
		return new Float(value);
	}
}
