/**
 * 
 */
package com.csair.mts.domain;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月3日 $
 *
 */
public abstract class AbstractJob implements Job {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public abstract void execute(JobExecutionContext context)
			throws JobExecutionException;

}
