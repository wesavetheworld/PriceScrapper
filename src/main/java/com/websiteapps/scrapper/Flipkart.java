package com.websiteapps.scrapper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websiteapps.domain.Product;
import com.websiteapps.domain.ProductSource;

/**
 * @author Digvijay
 * 
 */
public class Flipkart extends BaseScrapper {

	public Flipkart(String product, CrawlEngine engine) {
		super(product, engine, "http://www.flipkart.com");
	}

	public String init() throws Exception {
		System.out.println("Initializing Flipkart Scrapper..");
		Document doc = doGet(baseUrl);
		String as = doc.select("input[id$=as]").val();
		String asshow = doc.select("input[id$=as-show]").val();
		String oTracker = doc.select("input[id$=searchTracker]").val();
		return baseUrl + "/search?q=" + encode(product + "&as=" + as + "&as-show=" + asshow + "&otracker=" + oTracker);
	}

	@Override
	public List<Product> scrap() {
		List<Product> products = new ArrayList<Product>();
		try {
			System.out.println("Flipkart Searcing for Product : " + product);
			String searchUrl = init();
			System.out.println("Flipkart Searcing...");
			Document document = doGet(searchUrl);
			Elements productsContainer = document.select("div.product-unit");
			int srno = 0;
			for (Element element : productsContainer) {
				try {
					String productName = element.getElementsByAttributeValue("data-tracking-id", "prd_title").attr("title");
					String oldPriceWithDiscount = element.getElementsByAttributeValue("class", "pu-discount fk-font-11").text();
					String newPrice = element.getElementsByAttributeValue("class", "pu-final").text();
					if (newPrice.contains("Rs.")) {
						newPrice = newPrice.trim();
						newPrice = newPrice.substring(3, newPrice.length()).trim();
					}
					String description = element.getElementsByAttributeValue("class", "pu-usp").html();
					String url = element.getElementsByAttributeValue("data-tracking-id", "prd_title").attr("href");
					String img = element.getElementsByAttributeValueContaining("class", "pu-image").first().child(0).attr("data-src");
					if(img.equals("") || img == null){
						img = element.getElementsByAttributeValueContaining("class", "pu-image").first().child(0).attr("src");
					}
					products.add(new Product(++srno, productName, oldPriceWithDiscount, cleanPriceValue(newPrice), description, baseUrl + url, ProductSource.FLIPKART, img));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Flipkar no. of Products Found : " + products.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public static void main(String... args){
		BaseScrapper obj = new Flipkart("Motorola Flip Cover for Moto G",null);
		System.out.println(obj.scrap().size());
	}
}
