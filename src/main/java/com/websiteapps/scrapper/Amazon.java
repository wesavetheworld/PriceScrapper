package com.websiteapps.scrapper;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websiteapps.scrapper.domain.Product;
import com.websiteapps.scrapper.domain.ProductSource;

public class Amazon extends BaseScrapper {
	private String baseUrl = "http://www.amazon.in";
	private String product;

	public Amazon(String product) {
		this.product = product;
	}

	public String init() throws Exception {
		System.out.println("Initializing Amazon Scrapper..");
		Document doc = doGet(baseUrl);
		String subUrl = doc.select("form[id$=nav-searchbar]").attr("action");
		String searchIn = "";
		Elements option = doc.select("#searchDropdownBox > option");
		for (Element ele : option) {
			if (option.attr("selected").equals("selected")) {
				searchIn = option.attr("value");
				break;
			}
		}
		if (searchIn.equals("")) {
			throw new RuntimeException("Fail to Init Amazon");
		}
		return baseUrl + subUrl + "?url=" + searchIn + "&field-keywords=" + product + "";
	}

	public List<Product> scrap() throws Exception {
		System.out.println("Amazon Searcing for Product : " + product);
		List<Product> products = new ArrayList<Product>();
		String searchUrl = init();
		System.out.println("Amazon Searcing...");
		Document document = doGet(searchUrl);
		Elements productsContainer1 = document.getElementsByAttributeValueContaining("class", "prod celwidget");
		int count = 0;
		for (Element element : productsContainer1) {
			try {
				Element nameAndUrl = element.getElementsByClass("newaps").first();
				String url = nameAndUrl.child(0).attr("href");
				String name = nameAndUrl.text();
				String img = element.getElementsByClass("productImage").attr("src");
				Element li = element.getElementsByClass("newp").first();
				String oldPrice = ""; 
				try{
					oldPrice = li.getElementsByClass("grey").text();
				}catch(Exception e){
					
				}			
				String newPrice = li.getElementsByAttributeValueContaining("class", "red").text();
				String description = element.getElementsByAttributeValueContaining("class", "rsltR dkGrey").first().html();				
				Product product = buildProduct(name, ProductSource.AMAZON, oldPrice.trim(), newPrice.trim(), description, url, img);
				products.add(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return products;
	}
}
