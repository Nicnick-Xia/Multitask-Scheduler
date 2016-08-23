package com.csair.mts.controller;

import java.awt.print.Printable;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TaskManger {
	
	
	//private Map<String, Task> readyTask=new ConcurrentHashMap<String, Task>();
	private Map<String, Task> runningTask=new ConcurrentHashMap<String, Task>();
	private Map<String, Task> failTask=new ConcurrentHashMap<String, Task>();
	private Map<String, Task> succussTask=new ConcurrentHashMap<String, Task>();
	//private Map<String, Task> readyTask=new ConcurrentHashMap<String, Task>();
	private Queue<Task> readyTask=new ConcurrentLinkedQueue<Task>();
	
	public static void main(String[] args) throws InterruptedException{
		start();
	}
	public static void start() throws InterruptedException {
		TaskManger taskManger = new TaskManger();
		taskManger.initQueue();
		final Timer readyTimer =new Timer();
		readyTimer.schedule(taskManger.new ReadyToRuning(), 0,100);
		final Timer runningTimer =new Timer();
		runningTimer.schedule(taskManger.new RunningToFinish(),0, 200);
		while(true){
			System.out.println("----------start-----------");
			printQueue(taskManger.getReadyTask());
			System.out.println("----------ready end-----------");
			print(taskManger.getRunningTask());
			System.out.println("----------running end-----------");
			print(taskManger.getSuccussTask());
			System.out.println("----------success end-----------");
			print(taskManger.getFailTask());
			System.out.println("----------fail end-----------");
			System.out.println("-----------end------------");
			System.out.println();
			TimeUnit.SECONDS.sleep(1);
		}
	}
	static void printQueue(Queue<Task> queue){
		for(Task task:queue){
			System.out.println(task);
		}
	}
	static void print(Map<String , Task> map){
		for (String key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}
	public  void initQueue(){
		for(int i=0;i<100;i++){
			Task task=new Task("task_"+i,"taskName"+i,""+i,"taskContent"+i);
			readyTask.offer(task);
		}
	}
	
	class ReadyToRuning extends TimerTask{
		private int count=readyTask.size();
		@Override
		public void run() {
			if(count>0){
				Task task=readyTask.poll();
				runningTask.put(task.getTaskId(), task);
				count--;
				//System.out.println("count="+count);
			}
			
		}
	}
	class RunningToFinish extends TimerTask{
		public void run() {
			for (String key : runningTask.keySet()) {
				if(new Random().nextBoolean()){
					succussTask.put(key, runningTask.get(key));
					runningTask.remove(key);
				}else{
					failTask.put(key, runningTask.get(key));
					runningTask.remove(key);
				}
				break;
			}
			
		
		}
	}
	
	
	public Map<String, Task> getRunningTask() {
		return runningTask;
	}
	public void setRunningTask(Map<String, Task> runningTask) {
		this.runningTask = runningTask;
	}
	public Map<String, Task> getFailTask() {
		return failTask;
	}
	public void setFailTask(Map<String, Task> failTask) {
		this.failTask = failTask;
	}
	public Map<String, Task> getSuccussTask() {
		return succussTask;
	}
	public void setSuccussTask(Map<String, Task> succussTask) {
		this.succussTask = succussTask;
	}
	public Queue<Task> getReadyTask() {
		return readyTask;
	}
	public void setReadyTask(Queue<Task> readyTask) {
		this.readyTask = readyTask;
	}

}
