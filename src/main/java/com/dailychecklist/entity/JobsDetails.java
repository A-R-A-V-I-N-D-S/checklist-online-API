package com.dailychecklist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "jobs_details")
@AllArgsConstructor
public class JobsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "job_name")
	private String jobName;
	@Column(name = "folder_name")
	private String folderName;
	@Column(name = "start_time")
	private String startTime;
	@Column(name = "end_time")
	private String endTime;
	@Column(name = "job_status")
	private String jobStatus;
	@Column(name = "order_date")
	private String orderDate;
	@Column(name = "held_status")
	private String isHeld;
	@Column(name = "deletion_status")
	private String isDeleted;

	public JobsDetails() {

	}

	public JobsDetails(String jobName, String folderName, String startTime, String endTime, String jobStatus,
			String orderDate, String isHeld, String isDeleted) {
		super();
		this.jobName = jobName;
		this.folderName = folderName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.jobStatus = jobStatus;
		this.orderDate = orderDate;
		this.isHeld = isHeld;
		this.isDeleted = isDeleted;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getIsHeld() {
		return isHeld;
	}

	public void setIsHeld(String isHeld) {
		this.isHeld = isHeld;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
