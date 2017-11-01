package com.progresssoft.deals.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

@Entity
@Table(name = "Valid_Deals")
public class DealDetailsBean {
	@Id
	@Column(name = "Deal_Unique_Id")
	@CsvBindByName(column = "Deal Unique Id", required = false)
	private String dealId;
	
	@Column(name = "To_Currency_Code")
	@CsvBindByName(column = "To Currency ISO Code", required = false)
	private String toCurrencyCode;
	
	@Column(name = "Deal_Time_Stamp")
	@CsvBindByName(column = "Deal Timestamp", required = false)
	private String dealTimeStamp;
	
	@Column(name = "Deal_Amount")
	@CsvBindByName(column = "Deal Amount", required = false)
	private String dealAmount;
	
	@Column(name = "From_Currency_Code")
	@CsvBindByName(column = "From Currency ISO Code", required = false)
	private String fromCurrencyCode;
	
	@Column(name = "File_Name")
	private String fileName;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the dealId
	 */
	public String getDealId() {
		return dealId;
	}

	/**
	 * @param dealId
	 *            the dealId to set
	 */
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	/**
	 * @return the fromCurrencyCode
	 */
	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}

	/**
	 * @param fromCurrencyCode
	 *            the fromCurrencyCode to set
	 */
	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}

	/**
	 * @return the toCurrencyCode
	 */
	public String getToCurrencyCode() {
		return toCurrencyCode;
	}

	/**
	 * @param toCurrencyCode
	 *            the toCurrencyCode to set
	 */
	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

	/**
	 * @return the dealTimeStamp
	 */
	public String getDealTimeStamp() {
		return dealTimeStamp;
	}

	/**
	 * @param dealTimeStamp
	 *            the dealTimeStamp to set
	 */
	public void setDealTimeStamp(String dealTimeStamp) {
		this.dealTimeStamp = dealTimeStamp;
	}

	/**
	 * @return the dealAmount
	 */
	public String getDealAmount() {
		return dealAmount;
	}

	/**
	 * @param dealAmount
	 *            the dealAmount to set
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}



	public String toString() {// overriding the toString() method
		return dealId + " " + fromCurrencyCode + " " + toCurrencyCode + " " + dealTimeStamp + " " + dealAmount + " "
				+ fileName;
	}

	public DealDetailsBean(String dealId, String toCurrencyCode, String dealTimeStamp, String dealAmount,
			String fromCurrencyCode, String fileName) {
		super();
		this.dealId = dealId;
		this.toCurrencyCode = toCurrencyCode;
		this.dealTimeStamp = dealTimeStamp;
		this.dealAmount = dealAmount;
		this.fromCurrencyCode = fromCurrencyCode;
		this.fileName = fileName;
	}
	public DealDetailsBean() {
		
	}
	

}
