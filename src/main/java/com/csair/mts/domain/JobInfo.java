/**
 * 
 */
package com.csair.mts.domain;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月2日 $
 *
 */
public class JobInfo implements Serializable{

	private static final long serialVersionUID = 803113819292105745L;
	
	private String jobID;
	
	/** 格式: Seconds Minutes Hours Day-of-month Month Day-of-Week */
	private String jobStartTime;
	
	/** 格式: YYYY-MM-DD HH:mm:ss */
	private String jobEndTime;
	
	private String jobStatus;
	
	private String jobContent;
	
	private String jobType;
	
	/**
	 * @return the jobID
	 */
	public String getJobID() {
		return jobID;
	}
	/**
	 * @param jobID the jobID to set
	 */
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	/**
	 * @return the jobStartTime
	 */
	public String getJobStartTime() {
		return jobStartTime;
	}
	/**
	 * @param jobStartTime the jobStartTime to set
	 */
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
	/**
	 * @return the jobEndTime
	 */
	public String getJobEndTime() {
		return jobEndTime;
	}
	/**
	 * @param jobEndTime the jobEndTime to set
	 */
	public void setJobEndTime(String jobEndTime) {
		this.jobEndTime = jobEndTime;
	}
	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}
	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	/**
	 * @return the jobContent
	 */
	public String getJobContent() {
		return jobContent;
	}
	/**
	 * @param jobContent the jobContent to set
	 */
	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public static enum JobType {
		PRINT("打印Job"), 
		CMD("命令Job"),
		PLAY("播放Job"),
		EXE("运行EXE的Job"),
		EMAIL("发送E-mail");
		private final String value;
		
		private JobType(String v) {
			this.value = v;
		}
		
		public String value() {
			return this.value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
	}
	
	public static enum JobPara {
		JOB_ID("JobId"),
		START_TIME("开始时间"),
		END_TIME("结束时间"),
		JOB_STATUS("Job状态"),
		JOB_TYPE("Job类型"),
		JOB_CONTENT("Job打印内容");
		
		private final String value;
		
		private JobPara(String v) {
			this.value = v;
		}
		
		public String value() {
			return this.value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}		
	}
	
	public static enum JobStatus {
		SCHEDULE("计划执行"),
		EXECUTING("执行中"),
		EXECUTED("执行完成"),
		EXCEPTION("执行异常");
		
		private final String value;
		
		private JobStatus(String v) {
			this.value = v;
		}
		
		public String value() {
			return this.value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}		
	}

	
}
