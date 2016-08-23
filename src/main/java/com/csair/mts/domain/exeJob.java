package com.csair.mts.domain;

import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class exeJob extends AbstractJob{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		//String cmd="cmd.exe /c start D:\\data.txt";
		//String cmd="cmd.exe iexplore http://10.92.8.15/";
		// 原来为打开文档，现在可以设置为打开研究院的网页
		String cmd="C:\\Program Files (x86)\\Internet Explorer\\iexplore http://10.92.8.15/";
		   try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试代码
	public static void main(String[] args){
		String cmd="C:\\Program Files (x86)\\Internet Explorer\\iexplore http://10.92.8.15/";
		   try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
