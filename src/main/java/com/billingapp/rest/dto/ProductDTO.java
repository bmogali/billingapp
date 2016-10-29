package com.billingapp.rest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductDTO {

	@Id
	@GeneratedValue
	@Column(name = "ProductID")
	private int productID;

	@Column(name = "Product")
	private String product;

	@Column(name = "ProductDefaultCharge")
	private Double productDefaultCharge;

	@Column(name = "Display")
	private boolean display;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getProductDefaultCharge() {
		return productDefaultCharge;
	}

	public void setProductDefaultCharge(Double productDefaultCharge) {
		this.productDefaultCharge = productDefaultCharge;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

}
