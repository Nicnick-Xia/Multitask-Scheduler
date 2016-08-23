package com.csair.mts.controller;

public class Task {
	private String taskId;
	private String taskName;
	private String taskStart;
	private String taskContent;
	public Task(){
		
	}
	
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName
				+ ", taskStart=" + taskStart + ", taskContent=" + taskContent
				+ "]";
	}

	public Task(String taskId, String taskName, String taskStart,
			String taskContent) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStart = taskStart;
		this.taskContent = taskContent;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskStart() {
		return taskStart;
	}
	public void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	

}
