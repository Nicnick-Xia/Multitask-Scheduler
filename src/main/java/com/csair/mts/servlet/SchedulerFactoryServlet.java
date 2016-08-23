package com.csair.mts.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csair.mts.utils.SchedulerFactoryUtil;

/**
 * 通过QuartzInitializerServlet获得SchedulerFactory
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月2日 $
 */
public class SchedulerFactoryServlet extends QuartzInitializerServlet {
	private static final long serialVersionUID = 1L;
	
    /** 子类也可以使用的通用Logger */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
       
    /**
     * @see QuartzInitializerServlet#QuartzInitializerServlet()
     */
    public SchedulerFactoryServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SchedulerFactory factory = (SchedulerFactory) config.getServletContext()
				.getAttribute(QUARTZ_FACTORY_KEY);
		SchedulerFactoryUtil.getInstance().setSchedulerFactory(factory);
		logger.info("Scheduler factory {} set successfully. ", 
				SchedulerFactoryUtil.getInstance().toString());
		try {
			logger.debug("Scheduler instance name: {} ", 
					SchedulerFactoryUtil.getInstance().getScheduler().getSchedulerName());
			logger.debug("Scheduler instance id: {} ", 
					SchedulerFactoryUtil.getInstance().getScheduler().getSchedulerInstanceId());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		return super.getServletConfig();
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		return super.getServletInfo(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

}
