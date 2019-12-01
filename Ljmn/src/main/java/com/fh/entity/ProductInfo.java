package com.fh.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductInfo {
	private String id;
	private String productName;
	private String mainImagePath;
	private Double price;
	private String address;
	
	private String brandName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;
	/**
	 * 登录时间区间查询
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMainImagePath() {
		return mainImagePath;
	}
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
