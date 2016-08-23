<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<!--导入样式文件-->
	<link rel="stylesheet" type="text/css" href="css/index.css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="css/font-awesome/css/font-awesome.min.css" />
<title>南航信息中心研究院</title>
</head>
<body>
<div class="header">
	<div class="main_header">
		<div class="main_header_left">
			<span>欢迎使用任务计划调度器</span>
		</div>
		<div class="main_header_right">
			<div class="logout"><a href="#" id="logout">退出</a></div>
			<div class="username" id="username">admin</div>
		</div>
	</div>
</div>
<div class="logo">
	<img src="images/logo.png">
</div>
<div class="container">
	<button class="btn-my" id="refresh" type="submit"><i class="fa fa-refresh fa-spin"></i> 刷新列表</button>
	<button class="btn-my" id="add" type="submit"><i class="fa fa-plus"></i> 新增任务</button>
	<button class="btn-my" id="all" type="submit"><i class="fa fa-list-ul"></i> 所有任务</button>
	<button class="btn-my" id="waiting" type="submit"><i class="fa fa-ellipsis-h"></i> 待调度任务</button>
	<button class="btn-my" id="running" type="submit"><i class="fa fa-spinner fa-pulse"></i> 运行中任务</button>
	<button class="btn-my" id="success" type="submit"><i class="fa fa-check"></i> 成功任务</button>
	<button class="btn-my" id="fail" type="submit"><i class="fa fa-close"></i> 失败任务</button>
	<!--任务列表-->
	<div class="container_body" id="job_list">
	<div id="count"></div>
		<table class="table table-bordered table-striped table-hover" id="taskList" >
			<thead>
				<th>任务编号</th>
				<th>任务类型</th>
				<th>任务开始时间</th>
				<th>任务结束时间</th>
				<th>任务状态</th>
				<th>任务内容</th>
				<th>操作</th>
			</thead>
			<c:forEach var="job" items="${jobs }">
				<tr>
					<td>${job.jobID }</td>
					<td>${job.jobType }</td>
					<td>${job.jobStartTime }</td>
					<td>${job.jobEndTime }</td>
					<td id=${job.jobStatus }>${job.jobStatus }</td>
					<td>${job.jobContent }</td>
					<td>
						<a class="btn btn-danger" href="#" onclick="javascript:removeJob(this)" id=${job.jobID }>
  						<i class="fa fa-trash-o fa-lg"></i> 删除任务</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div id="add_job" class="modal fade"  tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">新增任务</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group ">
            <label for="job_id" class="col-sm-2 control-label">任务编号:</label>
            <div class="col-sm-10">
            <input name="job_id" type="text" class="form-control" placeholder="任务编号" required="true">
            </div>
          </div>
          <div class="form-group">
            <label for="job_type" class="col-sm-2 control-label">任务类型:</label>
            <div class="col-sm-10">
            <select id="job_type" class="form-control">
  				<option value="print">打印日志</option>
			    <option value="CMD">打开网页</option>
				<option value="EMAIL">发送邮件</option>
				<option value="PLAY">播放音乐</option>
			</select>
            </div>
           </div>
           <div class="form-group">
            <label for="job_start_time" class="col-sm-2 control-label">触发器:</label>
            <div class="col-sm-10">
            <input name="job_start_time" type="text" class="form-control" placeholder="Cron表达式" required="true">
            </div>
           </div>
           <div class="form-group">
            <label for="job_content" class="col-sm-2 control-label">任务内容:</label>
            <div class="col-sm-10">
            <input name="job_content" type="text" class="form-control" placeholder="任务内容" required="true">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" type="submit" id="sureAddJob">增加</button>
      </div>
    </div>
  </div>
</div>
<div id="remove_job" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
     <div class="modal-content">
       <div class="modal-header">
       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">提示：</h4>
      </div>
      <div class="modal-body">
      	<h4>确定删除该任务？</h4>
      	<div style="display: none;" id="remove_jobID"></div>
      </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" type="submit" id="sureRemoveJob">确认</button>
      </div>
     </div>
  </div>
</div>
<div id="tips" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
     <div class="modal-content">
       <div class="modal-header">
       <button id="close" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="msgType"></h4>
      </div>
      <div class="modal-body">
      	<h4 id="msgContent"></h4>
      </div>
     </div>
  </div>
</div>
<div class="footer"></div>
</body>
<script type="text/javascript">
	$('#add').click(function(){
		$('#add_job').modal('show');
	});
</script>
</html>