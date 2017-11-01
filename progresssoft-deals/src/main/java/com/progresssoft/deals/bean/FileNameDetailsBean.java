package com.progresssoft.deals.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "File_Name_Details")
public class FileNameDetailsBean {
	@Id
	@Column(name = "File_Name")
	private String fileName;
	
	@Column(name = "Success_Count")
	private long success_Count;
	
	@Column(name = "Failure_Count")
	private long failure_Count;
	
	@Column(name = "Time_Diff")
	private long processingTime;
	
	public String toString() {
		return fileName+ " " + success_Count + " " + failure_Count + " " + processingTime;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @return the success_Count
	 */
	public long getSuccess_Count() {
		return success_Count;
	}
	/**
	 * @param success_Count the success_Count to set
	 */
	public void setSuccess_Count(long success_Count) {
		this.success_Count = success_Count;
	}
	/**
	 * @return the failure_Count
	 */
	public long getFailure_Count() {
		return failure_Count;
	}
	/**
	 * @param failure_Count the failure_Count to set
	 */
	public void setFailure_Count(long failure_Count) {
		this.failure_Count = failure_Count;
	}
	/**
	 * @return the processingTime
	 */
	public long getProcessingTime() {
		return processingTime;
	}
	/**
	 * @param processingTime the processingTime to set
	 */
	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public FileNameDetailsBean(String fileName, long success_Count, long failure_Count, long processingTime) {
		super();
		this.fileName = fileName;
		this.success_Count = success_Count;
		this.failure_Count = failure_Count;
		this.processingTime = processingTime;
	}
	public FileNameDetailsBean() {
	}


}
