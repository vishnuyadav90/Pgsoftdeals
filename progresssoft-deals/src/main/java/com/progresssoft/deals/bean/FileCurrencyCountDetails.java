package com.progresssoft.deals.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@NamedNativeQuery(name="FileCurrencyCountDetails.fetchFileCurrencyCountDetails", query ="select From_Currency_Code, count(*) From_Currency_Count from valid_deals  group by From_Currency_Code")
@Table(name = "File_Currency_Count_Details")
public class FileCurrencyCountDetails {
	@Id
	@Column(name = "From_Currency_Code")
	private String fromCurrencyCode;
	

	@Column(name = "From_Currency_Count")
	private long currencyCount;

	public FileCurrencyCountDetails() {
	}

	
		public FileCurrencyCountDetails(String fromCurrencyCode, long currencyCount) {
		super();
		this.fromCurrencyCode = fromCurrencyCode;
		this.currencyCount = currencyCount;
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

	public String toString() {
		return fromCurrencyCode + " " + currencyCount;
	}


	/**
	 * @return the currencyCount
	 */
	public long getCurrencyCount() {
		return currencyCount;
	}


	/**
	 * @param currencyCount the currencyCount to set
	 */
	public void setCurrencyCount(long currencyCount) {
		this.currencyCount = currencyCount;
	}

	
}
