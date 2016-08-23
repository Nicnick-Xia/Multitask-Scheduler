/**
 * 
 */
package com.csair.mts.listener;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csair.commons.lang.utils.DateUtils;
import com.csair.mts.domain.JobInfo;
import com.csair.mts.utils.SchedulerFactoryUtil;

/**
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月3日 $
 *
 */
public class MyJobListener extends JobListenerSupport {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final Scheduler sched = SchedulerFactoryUtil.getInstance().getScheduler();
	
	private String name;
	
	/**
	 * 
	 */
	public MyJobListener(String listenerName) {
		this.name = listenerName;
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/* (non-Javadoc)
	 * @see org.quartz.listeners.JobListenerSupport#jobToBeExecuted(org.quartz.JobExecutionContext)
	 * Job准备被调度执行时, 将这个Job的状态改为执行中
	 * JobDataMap之所以从Scheduler中获得而不是从context中获得, 
	 * 是因为从Scheduler中获得的JobDataMap是存储在JobStore中的
	 * 其他的线程在查询和Job相关的JobDataMap时也是从JobStore中取出的
	 */
	@Override
    public void jobToBeExecuted(JobExecutionContext context) {
		try {
			JobKey jobKey = context.getJobDetail().getKey();
			JobInfo job = (JobInfo) sched.getContext().get(jobKey.toString());
			job.setJobStatus(JobInfo.JobStatus.EXECUTING.name());

			logger.debug("job: {} status has been changed to {}. ", 
					jobKey.toString(), job.getJobStatus());			
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
    }

	/* (non-Javadoc)
	 * @see org.quartz.listeners.JobListenerSupport#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	@Override
    public void jobExecutionVetoed(JobExecutionContext context) {
		
    }

	/* (non-Javadoc)
	 * @see org.quartz.listeners.JobListenerSupport#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 * Job被调度执行完时, 将这个Job的状态改为执行完, 并保存执行完成时间
	 * JobDataMap之所以从Scheduler中获得而不是从context中获得, 
	 * 是因为从Scheduler中获得的JobDataMap是存储在JobStore中的
	 */
	@Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		try {
			JobKey jobKey = context.getJobDetail().getKey();
			JobInfo job = (JobInfo) sched.getContext().get(jobKey.toString());
			String jobStatus = ( jobException == null) ? 
					JobInfo.JobStatus.EXECUTED.name() : JobInfo.JobStatus.EXCEPTION.name();
			String endTime = DateUtils.format(new Date(), DateUtils.FORMAT_PATTERN_DATETIME);
			job.setJobStatus(jobStatus);
			job.setJobEndTime(endTime);
			logger.debug("job: {} status has been changed to {}. ", 
					jobKey.toString(), job.getJobStatus());
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
    }	

}
