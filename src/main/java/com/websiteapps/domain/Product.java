package com.websiteapps.domain;

/**
 * @author Digvijay
 *
 */
public class Product implements Comparable<Product> {
	private int srno;
	private String name;
	private String offer;
	private Float price;
	private String desciption;
	private String url;
	private ProductSource source;
	private String img;

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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

	public ProductSource getSource() {
		return source;
	}

	public void setSource(ProductSource source) {
		this.source = source;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public int compareTo(Product product) {
		if (this.price > product.price) {
			return 1;
		}
		return 0;
	}
}
