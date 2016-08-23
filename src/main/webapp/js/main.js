$(document).ready(  function() { 
     //alert("kk");
     $("#add").click(function(){
    	 //console.log("ok");
    	 getData("start");
    	});
     $("#ready").click(function(){
    	 //console.log("ok");
    	 getData("readyTask");
     });
     $("#fail").click(function(){
    	 //console.log("ok");
    	 getData("failTask");
     });
     $("#success").click(function(){
    	 //console.log("ok");
    	 getData("successTask");
     });
     $("#running").click(function(){
    	 //console.log("ok");
    	 getData("failTask");
     });
      
     
 });
function getData(taskType){
	jQuery.ajax( {  
        type : 'GET',  
        contentType : 'application/json',  
        url : taskType,  
        dataType : 'json',  
        success : function(data) {  
         //alert(data);
        	$("#taskList tbody").empty();
        	var tr="";
        	$.each(data,function(i,val){
        		//alert(val.taskId);
        		tr+="<tr><td>"+val.taskId+"</td>";
        		tr+="<td>"+val.taskName+"</td>";
        		tr+="<td>"+val.taskStart+"</td>";
        		tr+="<td>"+val.taskContent+"</td></tr>";
        	});
        	$("#taskList").append("<tbody>"+tr+"</tbody>");
        	console.log(data);
        },  
        error : function(data) {  
         // alert(data)  
        	console.log(data);
        }  
      });  
}
