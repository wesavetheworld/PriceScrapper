package com.websiteapps.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.websiteapps.domain.Product;

/**
 * @author Digvijay
 */
public class Filter {
	public static void filter(List<Product> products, String searchProduct) {

		List<Product> filteredProducts = new ArrayList<Product>();
		Collections.sort(products);
		for (Product product : products) {
			if (isNameBiggerThanSearchName(product.getName(), searchProduct)) {
				if (!isSearchNameMatcing(product.getName(), searchProduct)) {
					continue;
				}
			}
			filteredProducts.add(product);
		}
		products.clear();
		products.addAll(filteredProducts);
	}

	public static boolean isNameBiggerThanSearchName(String name1, String name2) {
		int productNameCount = name1.length();
		int searchNameCount = name2.length();
		if (productNameCount - searchNameCount > 15) {
			return true;
		}
		return false;
	}

	public static boolean isSearchNameMatcing(String productName, String searchName) {
		boolean result = true;
		for (String name : searchName.split(" ")) {
			if (!containsWord(name, productName)) {
				result = false;
			}
		}
		return result;
	}

	public static boolean containsWord(String word, String searchString) {
		for (String nm : searchString.split(" ")) {
			if (nm.equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}
}
