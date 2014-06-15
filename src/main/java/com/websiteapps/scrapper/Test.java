package com.websiteapps.scrapper;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websiteapps.scrapper.domain.Product;

public class Test {
	public void testScrapper() {
		try {
			String baseUrl = "http://www.flipkart.com";
			System.out.println("Connecting..");
			Document doc = Jsoup.connect("http://www.flipkart.com").timeout(30 * 1000).get();
			System.out.println("Extracting Data..");
			String as = doc.select("input[id$=as]").val();
			String asshow = doc.select("input[id$=as-show]").val();
			String oTracker = doc.select("input[id$=searchTracker]").val();

			String productName = "Dell Vostro 2520 Laptop";
			String searchUrl = baseUrl + "/search?q=" + productName + "&as=" + as + "&as-show=" + asshow + "&otracker=" + oTracker;
			System.out.println("Searching for Product..");
			doc = Jsoup.connect(searchUrl).timeout(30 * 1000).get();

			Elements productsContainer = doc.select("div.product-unit");
			for (Element product : productsContainer) {
				System.out.println("-------------------------------------------------");
				System.out.println("Old price               : " + product.getElementsByAttributeValue("class", "pu-discount fk-font-11").text());
				System.out.println("New price with discount : " + product.getElementsByAttributeValue("class", "pu-final").text());
				System.out.println("Product                 : " + product.getElementsByAttributeValue("data-tracking-id", "prd_title").attr("title"));
				System.out.println("-------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		try {
			Flipkart obj = new Flipkart("Dell Vostro 2520 Laptop");
			List<Product> products = obj.scrap();
			System.out.println(products.size());
			System.out.println(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
