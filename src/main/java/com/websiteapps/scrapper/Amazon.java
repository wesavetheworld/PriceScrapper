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
public class Amazon extends BaseScrapper {

	public Amazon(String product, CrawlEngine engine) {
		super(product, engine, "http://www.amazon.in");
	}

	public String init() throws Exception {
		System.out.println("Initializing Amazon Scrapper..");
		Document doc = doGet(baseUrl);
		String subUrl = doc.select("form[id$=nav-searchbar]").attr("action");
		String searchIn = "";
		Elements option = doc.select("#searchDropdownBox > option");
		for (Element ele : option) {
			if (ele.attr("selected").equals("selected")) {
				searchIn = option.attr("value");
				break;
			}
		}
		if (searchIn.equals("")) {
			throw new RuntimeException("Fail to Init Amazon");
		}
		return baseUrl + subUrl + "?url=" + searchIn + "&field-keywords=" + encode(product);
	}

	@Override
	public List<Product> scrap() {
		System.out.println("Amazon Searcing for Product : " + product);
		List<Product> products = new ArrayList<Product>();
		try {
			String searchUrl = init();
			System.out.println("Amazon Searcing...");
			Document document = doGet(searchUrl);
			Elements productsContainer1 = document.getElementsByAttributeValueContaining("class", "prod celwidget");
			int srno = 0;
			for (Element element : productsContainer1) {
				try {
					Element nameAndUrl = element.getElementsByClass("newaps").first();
					String url = nameAndUrl.child(0).attr("href");
					String name = nameAndUrl.text();
					String img = element.getElementsByClass("productImage").attr("src");
					Element li = element.getElementsByClass("newp").first();
					String oldPrice = "";
					try {
						oldPrice = li.getElementsByClass("grey").text();
					} catch (Exception e) {

					}
					String newPrice = li.getElementsByAttributeValueContaining("class", "red").text();
					String description = element.getElementsByAttributeValueContaining("class", "rsltR dkGrey").first().html();
					products.add(new Product(++srno, name, oldPrice.trim(), cleanPriceValue(newPrice.trim()), description, url, ProductSource.AMAZON, img));
				} catch (Exception e) {
					System.out.println("Error Amazon : " + e.getMessage());
				}
			}
			System.out.println("Amazon no. of Products Found : " + products.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}
}
