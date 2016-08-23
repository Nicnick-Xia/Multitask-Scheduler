package com.csair.mts.domain;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月3日 $
 *
 */
public class PlayJob extends AbstractJob {

	/* (non-Javadoc)
	 * @see com.csair.mts.domain.AbstractJob#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		List<String> path=new ArrayList<String>();  
		path.add("D://te.mp3");  
		PlayMedia play=new PlayMedia(path);  
		play.start();
	}

}