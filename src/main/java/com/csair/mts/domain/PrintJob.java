/**
 * 
 */
package com.csair.mts.domain;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;

/**
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月2日 $
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PrintJob extends AbstractJob {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		JobDataMap data = context.getJobDetail().getJobDataMap();	
		
		String printContent = data.getString(JobInfo.JobPara.JOB_CONTENT.name());
		data.put(JobInfo.JobPara.JOB_STATUS.name(), JobInfo.JobStatus.EXECUTING.name());
		logger.info("Job: {} is executing. Status: {}. Content: {} ", 
				jobKey.toString(), 
				data.get(JobInfo.JobPara.JOB_STATUS.name()),
				printContent);
		try {
			Thread.sleep(40 * 1000L);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
