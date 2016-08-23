package com.csair.mts.service;

import java.util.List;

import com.csair.mts.domain.JobInfo;

/**
 * 任务调度服务
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月4日 $
 *
 */
public interface TaskScheduleService {

	/**
	 * 新增一个Job
	 * @param job
	 */
	public void addJob(JobInfo job);
	
	/**
	 * 删除一个Job
	 * @param jobID
	 */
	public void removeJob(String jobID);
	
	/**
	 * 获得所有的Job
	 * @return
	 */
	public List<JobInfo> retriveAllJobs();
	
	/**
	 * @author cxm
	 * @param type
	 * @return
	 * @description 根据传入的type，返回相应的joblist
	 */
	public List<JobInfo> retriveTypeJobs(String type) ;
}