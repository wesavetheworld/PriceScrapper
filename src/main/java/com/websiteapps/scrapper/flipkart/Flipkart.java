package com.websiteapps.scrapper.flipkart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websiteapps.scrapper.domain.Product;

public class Flipkart {
	private String baseUrl = "http://www.flipkart.com";
	private String product;

	public Flipkart(String product) {
		this.product = product;
	}

	public String init() throws Exception {
		System.out.println("Initializing Flipkart Scrapper..");
		Document doc = Jsoup.connect("http://www.flipkart.com").timeout(30 * 1000).get();
		String as = doc.select("input[id$=as]").val();
		String asshow = doc.select("input[id$=as-show]").val();
		String oTracker = doc.select("input[id$=searchTracker]").val();
		return baseUrl + "/search?q=" + product + "&as=" + as + "&as-show=" + asshow + "&otracker=" + oTracker;
	}

	public List<Product> scrap() throws Exception {
		System.out.println("Searcing for Product : " + product);
		List<Product> products = new ArrayList<Product>();
		String searchUrl = init();
		System.out.println("Searcing...");
		Document document = Jsoup.connect(searchUrl).timeout(30 * 1000).get();
		Elements productsContainer = document.select("div.product-unit");
		for (Element element : productsContainer) {
			try{				
				String productName = element.getElementsByAttributeValue("data-tracking-id", "prd_title").attr("title");
				String oldPriceWithDiscount = element.getElementsByAttributeValue("class", "pu-discount fk-font-11").text();
				String newPrice = element.getElementsByAttributeValue("class", "pu-final").text();
				if(newPrice.contains("Rs.")){
					newPrice = newPrice.trim();
					newPrice = newPrice.substring(3, newPrice.length()).trim();
				}
				String description = element.getElementsByAttributeValue("class", "pu-usp").html();
				String url = element.getElementsByAttributeValue("data-tracking-id","prd_title").attr("href");
				Product product = new Product();
				product.setName(productName.trim());
				product.setOldPrice(oldPriceWithDiscount);
				product.setNewprice(Integer.parseInt(newPrice));
				product.setDesciption(description);
				product.setUrl(baseUrl + url);
				products.add(product);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("No. of Products Found : " + products.size());
		Collections.sort(products);
		return products;
	}

}
