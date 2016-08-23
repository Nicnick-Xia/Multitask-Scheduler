/**
 * 
 */
package com.csair.mts.service.impl;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.csair.commons.lang.utils.ObjectUtils;
import com.csair.mts.domain.EmailJob;
import com.csair.mts.domain.JobInfo;
import com.csair.mts.domain.PlayJob;
import com.csair.mts.domain.PrintJob;
import com.csair.mts.domain.exeJob;
import com.csair.mts.listener.MyJobListener;
import com.csair.mts.service.TaskScheduleService;
import com.csair.mts.utils.SchedulerFactoryUtil;

/**
 * 任务调度服务实现
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月4日 $
 *
 */
@Service
public class TaskScheduleServiceImpl implements TaskScheduleService {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String JOB_GROUP = "myGroup";
	private static final String TRIGGER = "_trigger";
	private static final String LISTENER = "_listener";
	
	private Scheduler sched = SchedulerFactoryUtil.getInstance().getScheduler();
	
	@PostConstruct
	protected void initScheduler() {
		try {
			sched.start();
			logger.info("Scheduler {} started...", sched.getSchedulerName());
		} catch (SchedulerException e) {
			logger.error("Scheduler start failed! ");
		}
	}
	
	@PreDestroy
	protected void shutdownScheduler() {
		try {
			sched.shutdown(false);
			logger.info("Scheduler {} closed...", sched.getSchedulerName());
		} catch (SchedulerException e) {
			logger.error("Scheduler shutdown failed! ");
		}
	}

	/* (non-Javadoc)
	 * @see com.csair.mts.service.TaskScheduleService#addJob(com.csair.mts.domain.JobInfo)
	 */
	@Override
	public void addJob(JobInfo job) {
		try {
			// 在控制台打印“Job: {} is executing. Status: {}. Content: {}”的相关信息。
			if (StringUtils.equalsIgnoreCase(JobInfo.JobType.PRINT.name(), job.getJobType())) {
				JobDetail jobDetail = newJob(PrintJob.class)
						.withIdentity(job.getJobID(), JOB_GROUP)
						.usingJobData(JobInfo.JobPara.JOB_ID.name(), job.getJobID())
						.usingJobData(JobInfo.JobPara.JOB_TYPE.name(), job.getJobType())
						.usingJobData(JobInfo.JobPara.START_TIME.name(), job.getJobStartTime())
						.usingJobData(JobInfo.JobPara.JOB_CONTENT.name(), job.getJobContent())
						.usingJobData(JobInfo.JobPara.JOB_STATUS.name(), job.getJobStatus())
						.build();
			    CronTrigger trigger = newTrigger()
			    		.withIdentity(job.getJobID() + TRIGGER, JOB_GROUP)
			    		.withSchedule(cronSchedule(job.getJobStartTime()))
			            .build();
			    MyJobListener listener = new MyJobListener(job.getJobID() + LISTENER);
			    Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			    if (ObjectUtils.isNotNullAndEmpty(sched)) {
				    sched.getListenerManager().addJobListener(listener, matcher);
				    sched.scheduleJob(jobDetail, trigger);
				    sched.getContext().put(jobDetail.getKey().toString(), job);
			    }
			} 
			// 播放D盘目录下的te.wav音乐文件
			else if(StringUtils.equalsIgnoreCase(JobInfo.JobType.PLAY.name(), job.getJobType())){
				JobDetail jobDetail = newJob(PlayJob.class)
						.withIdentity(job.getJobID(), JOB_GROUP)
						.usingJobData(JobInfo.JobPara.JOB_ID.name(), job.getJobID())
						.usingJobData(JobInfo.JobPara.JOB_TYPE.name(), job.getJobType())
						.usingJobData(JobInfo.JobPara.START_TIME.name(), job.getJobStartTime())
						.usingJobData(JobInfo.JobPara.JOB_CONTENT.name(), job.getJobContent())
						.usingJobData(JobInfo.JobPara.JOB_STATUS.name(), job.getJobStatus())
						.build();
			    CronTrigger trigger = newTrigger()
			    		.withIdentity(job.getJobID() + TRIGGER, JOB_GROUP)
			    		.withSchedule(cronSchedule(job.getJobStartTime()))
			            .build();
			    MyJobListener listener = new MyJobListener(job.getJobID() + LISTENER);
			    Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			    if (ObjectUtils.isNotNullAndEmpty(sched)) {
				    sched.getListenerManager().addJobListener(listener, matcher);
				    sched.scheduleJob(jobDetail, trigger);
				    sched.getContext().put(jobDetail.getKey().toString(), job);
			    }
			}
			// cmd命令，打开D盘目录下的data.txt文档
			else if(StringUtils.equalsIgnoreCase(JobInfo.JobType.CMD.name(), job.getJobType())){
				JobDetail jobDetail = newJob(exeJob.class)
						.withIdentity(job.getJobID(), JOB_GROUP)
						.usingJobData(JobInfo.JobPara.JOB_ID.name(), job.getJobID())
						.usingJobData(JobInfo.JobPara.JOB_TYPE.name(), job.getJobType())
						.usingJobData(JobInfo.JobPara.START_TIME.name(), job.getJobStartTime())
						.usingJobData(JobInfo.JobPara.JOB_CONTENT.name(), job.getJobContent())
						.usingJobData(JobInfo.JobPara.JOB_STATUS.name(), job.getJobStatus())
						.build();
			    CronTrigger trigger = newTrigger()
			    		.withIdentity(job.getJobID() + TRIGGER, JOB_GROUP)
			    		.withSchedule(cronSchedule(job.getJobStartTime()))
			            .build();
			    MyJobListener listener = new MyJobListener(job.getJobID() + LISTENER);
			    Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			    if (ObjectUtils.isNotNullAndEmpty(sched)) {
				    sched.getListenerManager().addJobListener(listener, matcher);
				    sched.scheduleJob(jobDetail, trigger);
				    sched.getContext().put(jobDetail.getKey().toString(), job);
			    }
			}
			// 发送邮件的job
			else if (StringUtils.equalsIgnoreCase(JobInfo.JobType.EMAIL.name(), job.getJobType())) {
				JobDetail jobDetail = newJob(EmailJob.class)
						.withIdentity(job.getJobID(), JOB_GROUP)
						.usingJobData(JobInfo.JobPara.JOB_ID.name(), job.getJobID())
						.usingJobData(JobInfo.JobPara.JOB_TYPE.name(), job.getJobType())
						.usingJobData(JobInfo.JobPara.START_TIME.name(), job.getJobStartTime())
						.usingJobData(JobInfo.JobPara.JOB_CONTENT.name(), job.getJobContent())
						.usingJobData(JobInfo.JobPara.JOB_STATUS.name(), job.getJobStatus())
						.build();
			    CronTrigger trigger = newTrigger()
			    		.withIdentity(job.getJobID() + TRIGGER, JOB_GROUP)
			    		.withSchedule(cronSchedule(job.getJobStartTime()))
			            .build();
			    MyJobListener listener = new MyJobListener(job.getJobID() + LISTENER);
			    Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			    if (ObjectUtils.isNotNullAndEmpty(sched)) {
				    sched.getListenerManager().addJobListener(listener, matcher);
				    sched.scheduleJob(jobDetail, trigger);
				    sched.getContext().put(jobDetail.getKey().toString(), job);
			    }
				
			}
		} catch (SchedulerException e) {
			logger.error("Add job: {} failed! ", job.getJobID());
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.csair.mts.service.TaskScheduleService#removeJob(java.lang.String)
	 */
	@Override
	public void removeJob(String jobID) {
		JobKey jobKey = new JobKey(jobID, JOB_GROUP);
		try {
			if (ObjectUtils.isNotNullAndEmpty(sched) && sched.checkExists(jobKey)) {
				sched.deleteJob(jobKey);
				sched.getListenerManager().removeJobListener(jobID + LISTENER);
				sched.getContext().remove(jobKey.toString());
			}
		} catch (SchedulerException e) {
			logger.error("Remove job: {} failed! ", jobID);
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.csair.mts.service.TaskScheduleService#retriveAllJobs()
	 */
	@Override
	public List<JobInfo> retriveAllJobs() {
		List<JobInfo> jobList = new ArrayList<>();
		GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(JOB_GROUP);
		try {
			if (ObjectUtils.isNotNullAndEmpty(sched)) {
				JobInfo jobInfo = null;
				for (JobKey key: sched.getJobKeys(matcher)) {
					if (!sched.getContext().containsKey(key.toString())) 
						continue;
					jobInfo = (JobInfo) sched.getContext().get(key.toString());
					//logger.debug("job: {} status: {}. ", jobInfo.getJobID(), jobInfo.getJobStatus());
					jobList.add(jobInfo);
				}
			}
		} catch (SchedulerException e) {
			logger.error("Retrive all jobs info failed! ");
			logger.error(e.getMessage(), e);
		}
		return jobList;
	}
	
	/* 
	 * @author cxm
	 * @date 2016.8.8
	 * @Description 根据传入的status，返回相应的joblist
	 */
	@Override
	public List<JobInfo> retriveTypeJobs(String status) {
		List<JobInfo> jobList = new ArrayList<>();
		GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(JOB_GROUP);
		try {
			if (ObjectUtils.isNotNullAndEmpty(sched)) {
				JobInfo jobInfo = null;
				for (JobKey key: sched.getJobKeys(matcher)) {
					if (!sched.getContext().containsKey(key.toString())) 
						continue;
					jobInfo = (JobInfo) sched.getContext().get(key.toString());
					//logger.debug("job: {} status: {}. ", jobInfo.getJobID(), jobInfo.getJobStatus());
					if(jobInfo.getJobStatus().equals(status)){
						jobList.add(jobInfo);
					}
				}
			}
		} catch (SchedulerException e) {
			logger.error("Retrive" + status + " jobs info failed! ");
			logger.error(e.getMessage(), e);
		}
		return jobList;
	}
}
