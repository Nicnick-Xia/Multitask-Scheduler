$(document).ready(function() {
	$("#sureAddJob").click(function(){
		var id = $("input[name='job_id']").val();
		var type = $('#job_type option:selected').val();
		var startTime = $("input[name='job_start_time']").val();
		var jobContent = $("input[name='job_content']").val();
		console.log(id + ' ' + type + ' ' + startTime + ' ' + jobContent);
		if(id!="" && type!="" && startTime!="" && jobContent!=""){
			$.ajax({
				url:"addJob",
				data: {
					jobID: id,
					jobType: type,
					jobStartTime: startTime,
					jobContent: jobContent
				},
				type: "GET",						
				success: function(data){
					if(data == "SUCCESS"){
						location.href = "checkJob";
					}else if(data == "FAIL"){
						$('#msgType').text("操作失败");
						$('#msgContent').text("增加任务失败，任务编号已存在 ");
						$('#tips').modal('show');
					}else {
						$('#msgType').text("操作失败");
						$('#msgContent').text("增加任务失败，触发器格式有误 ");
						$('#tips').modal('show');
					}
				}
			})
		}
	});
	
	$("#sureRemoveJob").click(function(){
		var id = $("#remove_jobID").text();
		console.log(id);
		if(id!=""){
			$.ajax({
				url:"removeJob",
				data: {
					jobID: id
				},
				type: "GET",						
				success: function(data){
					if(data == "SUCCESS"){
						$('#remove_job').modal('hide');
						$('#msgType').text("操作成功");
						$('#msgContent').text("删除任务成功");
						$('#tips').modal('show');
						location.reload();
					}else{
						$('#msgType').text("操作失败");
						$('#msgContent').text("删除任务失败");
						$('#tips').modal('show');
					}
				}
			})
		}
	});
	
	$("#refresh").click(function(){
		location.reload();
	});
	
	setInterval(function() {
		$("#job_list").load(location.href+" #job_list>","");
	}, 1000);
	
	$("#waiting").click(function(){
		window.location.href = "waitJob";
	});
	
	$("#running").click(function(){
		window.location.href = "runJob";
	});
	
	$("#success").click(function(){
		window.location.href = "successJob";
	});
	
	$("#fail").click(function(){
		window.location.href = "failJob";
	});
	
	$("#all").click(function(){
		window.location.href = "checkJob";
	});

});

function removeJob(btn) {
	$('#remove_job').modal('show');
	$('#remove_jobID').text($(btn).attr('id'));
}