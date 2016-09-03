# Multitask-Scheduler
A practise for quartz application
## Introduction
This is a group project. In this project, we use Quartz as our core scheduler to manage multitask. The application is a web-app using Spring MVC as its service framework.

##  Example Jobs
* play music (The music file should be placed at D:\\)
* send E-mail (Using a local mail server to realize)
* exe job (open any exe file)
* print job

If you want to add some other kinds of job, you can take them as an example. Actually, you just need to focus how to realize your job and there is no need to care about how it is scheduled. The Quartz framework can automatically run it when it is triggled.
