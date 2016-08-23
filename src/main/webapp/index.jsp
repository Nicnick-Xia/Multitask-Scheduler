<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
 <script type="text/javascript" 
 src="js/jquery.min.js"></script>
 <script type="text/javascript" 
 src="js/main.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>南航信息中心研究院</title>
</head>
<body>
	<header>
		<div id="header">
		</div>
	</header>
	
	<section>
		<div id="content">
			<button id="add" >新增任务</button>
			<button id="ready">已准备任务</button>
			<button id="running">运行中任务</button>
			<button id="success">成功任务</button>
			<button id="fail">失败任务</button>
		</div>
	</section>	
	
	<footer>
		<div id="footer">
		</div>
	</footer>
	<table id="taskList" border=1>
	<thead><th>任务编号</th><th>任务名</th><th>任务开始时间</th><th>任务内容</th></thead>
	<tr><td></td></tr>
	</table>
</body>
</html>