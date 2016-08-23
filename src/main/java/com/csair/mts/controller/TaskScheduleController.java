/**
 * 
 */
package com.csair.mts.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csair.commons.lang.annotation.Singleton;
import com.csair.commons.lang.annotation.ThreadSafe;
import com.csair.mts.controller.TaskManger.ReadyToRuning;
import com.csair.mts.controller.TaskManger.RunningToFinish;
import com.csair.mts.domain.JobInfo;
import com.csair.mts.service.TaskScheduleService;

/**
 * 任务调度前端控制器
 * @author <a href="mailto:wanggangq@csair.com">Andrew Wang</a>
 * @version $Date: 2016年8月1日 $
 *
 */

@Controller
@Singleton
@ThreadSafe
public class TaskScheduleController {
	private TaskManger taskManger;

	@Autowired
	private TaskScheduleService service;
	
	
	@RequestMapping(value = "/checkJob", method = RequestMethod.GET)
	public ModelAndView checkJob() {
		ModelAndView mav = new ModelAndView();
		List<JobInfo> jobList = service.retriveAllJobs();
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}	
	@RequestMapping(value = "/addJob1", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public ModelAndView addJob(JobInfo task) {
		ModelAndView mav = new ModelAndView();
		System.out.println(task);
		service.addJob(task);
		List<JobInfo> jobList = service.retriveAllJobs();
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}	
	
	@RequestMapping(value = "/addJob", method = RequestMethod.GET)
	public @ResponseBody String addJob(String jobID, String jobType, String jobStartTime, String jobContent){
		JobInfo job = new JobInfo();	
		job.setJobID(jobID);
		job.setJobType(jobType);
		job.setJobStartTime(jobStartTime);
		job.setJobStatus(JobInfo.JobStatus.SCHEDULE.name());
		job.setJobContent(jobContent);
		System.out.println(TaskScheduleController.class.getClassLoader().getResource("/").getPath());
		if(canAddJob(job)){
			service.addJob(job);
		}else{
			return "FAIL";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/readData", method = RequestMethod.GET)
	public @ResponseBody String readData(String jobID, String jobType, String jobStartTime, String jobContent)throws Exception{
		String basePathString=TaskScheduleController.class.getClassLoader().getResource("/").getPath();
		System.out.println("kk"+basePathString);
        String fileContent = readFileByLines(basePathString+"/props/printData");
        return fileContent;
	}
	private boolean canAddJob(JobInfo job) {
		List<JobInfo> jobList = service.retriveAllJobs();
		for (JobInfo jobInfo : jobList) {
			if(jobInfo.getJobID().equals(job.getJobID()))
				return false;
		}
		//System.out.println(jobContent);
		//System.out.println(job);
		return true;
	}
	   public static String readFileByLines(String fileName) {  
	        File file = new File(fileName);  
	        BufferedReader reader = null;  
	        String str="";
	        try {  
	            System.out.println("以行为单位读取文件内容，一次读一整行：");  
	            reader = new BufferedReader(new FileReader(file));  
	            String tempString = null;  
	            int line = 1;  
	            // 一次读入一行，直到读入null为文件结束  
	            while ((tempString = reader.readLine()) != null) {  
	                // 显示行号  
	                System.out.println("line " + line + ": " + tempString);  
	                line++;  
	                str+=tempString;
	            }  
	            reader.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (reader != null) {  
	                try {  
	                    reader.close();  
	                } catch (IOException e1) {  
	                }  
	            }  
	        }  
	        return str;
	    }

	
	@RequestMapping(value = "/removeJob", method = RequestMethod.GET)
	public @ResponseBody String removeJob(String jobID){
		service.removeJob(jobID);
		return "SUCCESS";
	}

	
	@RequestMapping(value = "/waitJob", method = RequestMethod.GET)
	public ModelAndView waitJob() {
		ModelAndView mav = new ModelAndView();
		List<JobInfo> jobList = service.retriveTypeJobs("SCHEDULE");
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}
	
	@RequestMapping(value = "/runJob", method = RequestMethod.GET)
	public ModelAndView runJob() {
		ModelAndView mav = new ModelAndView();
		List<JobInfo> jobList = service.retriveTypeJobs("EXECUTING");
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}
	@RequestMapping(value = "/successJob", method = RequestMethod.GET)
	public ModelAndView successJob() {
		ModelAndView mav = new ModelAndView();
		List<JobInfo> jobList = service.retriveTypeJobs("EXECUTED");
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}
	@RequestMapping(value = "/failJob", method = RequestMethod.GET)
	public ModelAndView failJob() {
		ModelAndView mav = new ModelAndView();
		List<JobInfo> jobList = service.retriveTypeJobs("EXCEPTION");
		mav.addObject("jobs", jobList);
		mav.setViewName("test");
		return mav;
	}


}