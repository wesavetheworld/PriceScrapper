package com.websiteapps.scrapper;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.websiteapps.domain.Product;

/**
 * @author Digvijay
 */
public class Snapdeal extends BaseScrapper{

	public Snapdeal(String product, CrawlEngine engine) {
		super(product, engine,"http://www.snapdeal.com");
	}

	public String init() throws Exception {
		System.out.println("Initializing Snapdeal Scrapper..");
		Document doc = doGet(baseUrl);
		String subUrl = doc.select("form[id$=formSearch]").attr("action");
		String searchIn = "";
		Elements option = doc.select("#searchDropdownBox > option");
		for (Element ele : option) {
			if (ele.attr("selected").equals("selected")) {
				searchIn = option.attr("value");
				break;
			}
		}
		if (searchIn.equals("")) {
			throw new RuntimeException("Fail to Init Snapdeal");
		}
		return subUrl + "?url=" + searchIn + "&field-keywords=" + product + "";
	}

	@Override
	public List<Product> scrap() {
		return null;
	}
}
