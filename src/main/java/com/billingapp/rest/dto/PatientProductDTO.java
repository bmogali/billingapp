package com.billingapp.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity(name = "patientproducts")
@Table(name = "patientproducts")
public class PatientProductDTO {
	
	@Id
	@GeneratedValue
	@Column(name = "PatientProductID")
	private int patientProductID;
	
	@ManyToOne
	@JoinColumn(name="PatientID")
	private PatientDTO patient;
	
	@ManyToOne
	@JoinColumn(name="ProductID")
	private ProductDTO product;
	
	@Column(name="ProductBaseCharge")
	private Double productBaseCharge;
	
	public Double getProductBaseCharge() {
		return productBaseCharge;
	}

	public void setProductBaseCharge(Double productBaseCharge) {
		this.productBaseCharge = productBaseCharge;
	}

	@Column(name="Date")
	private Date date;
	
	@Column(name="Comment")
	private String comment;
	
	@Column(name="ProductTotalCharge")
	private Double productTotalCharge;
	
	@Column(name="ProductTax")
	private Double produdctTax;
	
	@Column(name="Quantity")
	private Integer quantity;
	
	@Column(name="Discount")
	private Double discount;

	public int getPatientProductID() {
		return patientProductID;
	}

	public void setPatientProductID(int patientProductID) {
		this.patientProductID = patientProductID;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getProductTotalCharge() {
		return productTotalCharge;
	}

	public void setProductTotalCharge(Double productTotalCharge) {
		this.productTotalCharge = productTotalCharge;
	}

	public Double getProdudctTax() {
		return produdctTax;
	}

	public void setProdudctTax(Double produdctTax) {
		this.produdctTax = produdctTax;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
}
