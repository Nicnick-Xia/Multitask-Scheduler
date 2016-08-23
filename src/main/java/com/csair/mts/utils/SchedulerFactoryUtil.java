/**
 * 
 */
package com.csair.mts.utils;

import java.io.IOException;
import java.util.Collection;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 调度器工厂单例类
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月2日 $
 *
 */
public class SchedulerFactoryUtil {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static final SchedulerFactoryUtil _instance = new SchedulerFactoryUtil();
	private SchedulerFactory schedulerFactory = null;
	
	private SchedulerFactoryUtil() {
		initSchedulerFactory();
	}
	
	private void initSchedulerFactory() {
		StdSchedulerFactory sf = new StdSchedulerFactory();
		Resource res = new ClassPathResource("/props/quartz.properties");
		try {
			sf.initialize(res.getInputStream());
			this.schedulerFactory = sf;
		} catch (SchedulerException | IOException e) {
			logger.error("Init scheduler factory failed! ");
		}
	}
	
	public static SchedulerFactoryUtil getInstance() {
		return _instance;
	}
	
	public void setSchedulerFactory(SchedulerFactory factory) {
		this.schedulerFactory = factory;
	}
	
	public Scheduler getScheduler() {
		try {
			return this.schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public Scheduler getScheduler(String schedName) {
		try {
			return this.schedulerFactory.getScheduler(schedName);
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public Collection<Scheduler> getAllSchedulers() {
		try {
			return this.schedulerFactory.getAllSchedulers();
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	
}
