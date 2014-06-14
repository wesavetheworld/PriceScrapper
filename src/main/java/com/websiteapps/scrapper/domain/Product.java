package com.websiteapps.scrapper.domain;

public class Product implements Comparable {
	String name;
	String oldPrice;
	Integer newprice;
	String desciption;
	String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Integer getNewprice() {
		return newprice;
	}

	public void setNewprice(Integer newprice) {
		this.newprice = newprice;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Product product = (Product) o;
		if (this.newprice > product.newprice) {
			return 1;
		}
		return 0;

	}
}
