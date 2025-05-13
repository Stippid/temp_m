<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>

<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script>
var username="${username}";
$(document).ready(function() {
	 if('${msg1}' != ""){
		 alert('${msg1}');	 
	 }
});
</script>
<form:form name="viewticket" id="viewticket" action="viewticketAction" method='POST' commandName="viewticketCMD">
<div class="container" align="center">
<div class="animated fadeIn">
	<div class="card">
		<div class="card-header"> <h5>View Ticket</h5></div>
		<div class="card-body card-block">
		<div  class="watermarked report_print" data-watermark="" id="divwatermark" style="display: block;">
		  <br>
		   <div class="col-md-12">	
			       <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Ticket Id
		                </div>
		                <div class="col-12 col-md-8">
		                 	<input type="hidden" id="id" name="id" value="${id}"/>
		                	<b>:  ${ticket}</b>
						</div>
	             	</div>
	             	</div>
	             	<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Date
		                </div>
		                <div class="col-12 col-md-8">
		                	<b>:  ${created_on}</b>
		            	</div>
	            	</div>
               	</div>
               </div>
               <div class="col-md-12">			  
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	User
		                </div>
		                <div class="col-12 col-md-8">
		                <input type="hidden" name="username" id="username" value="${username}">
		                 <input type="hidden" name="userid" id="userid" value="${userid}">
		                	<b>:  ${username}</b>
						</div>
	             	</div>
               </div>
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	SUS No 
		            	</div>
		           		<div class="col-12 col-md-8">
		           		 <input type="hidden" name="ticket" id="ticket" value="${ticket}">
               				<b>:  ${sus_no}</b>
						</div>
	             	</div>
               </div>
             </div>
            <div class="col-md-12">	
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Unit Name
		                </div>
		                <div class="col-12 col-md-8">
               				<b>:  ${unit_name}</b>
						</div>
	             	</div>
               </div>
             <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Module  
		                </div>
		                <div class="col-12 col-md-8">
               				<b>:  ${moduleName}</b>
               			</div>
	             	</div>
                </div>
           </div>
		   <div class="col-md-12">				  
               <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Sub Module 
		                </div>
		                <div class="col-12 col-md-8">
		                    <b>:  ${sub_module}</b>
						</div>
	             	</div>
               </div> 
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Screen Name 
		                </div>
		                <div class="col-12 col-md-8">
		                    <b>:  ${screen_name}</b>
						</div>
	             	</div>
               </div>
           </div>
			<div class="col-md-12">	           
               <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Help Topic  
		                </div>
		                <div class="col-12 col-md-8">
               				<b>:  ${help_topic}</b> 
						</div>
	             	</div>
                </div> 
                <div class="col-md-6">
	              <div class="row form-group"> 
		              <div class="col col-md-4">	             		
    	                   File  
    	               </div>
					   <div class="col-12 col-md-8" id="d1">
							<a href='#' onclick="getmodule_help('${id}');"class="btn btn-primary btn-sm" >Download</a>
						</div>
						<div class="col-12 col-md-8" id="d2">
							<h4>No Screenshot Available</h4> 
						</div> 
	            	</div> 
                </div>       
                </div>
 				<div class="col-md-12 form-group">				  
            		<div class="col-2 col-md-2">	             		
	                	Issue summary 
	                </div>
	                <div class="col-10 col-md-10">
	             		<b>:  ${issue_summary}</b>
					</div>
               </div>
              <div class="col-md-12 form-group">	
            	<div class="col-2 col-md-2">	             		
                	Description 
				</div>
           		<div class="col-10 col-md-10">
					<b>:  ${description}</b>
				</div>
            </div>
            <div class="col-md-12">
				<c:if test="${assigned_dt != null}">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							Assign on 
						</div>
						<div class="col-12 col-md-8">
							<b>: ${assigned_dt}</b>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${completed_dt != null}">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							completed on
						</div>
						<div class="col-12 col-md-8">
							<b>: ${completed_dt}</b>
						</div>
					</div>
				</div>
				</c:if>
			</div>
            <div class="col-md-12">	           
				<div class="col-md-6" id="assignto" style="display: none;">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	Assign To User <strong style="color: red;">*</strong>
		                </div>
		                <div class="col-12 col-md-8">
		                	<select id="agent_name" name="agent_name" class="form-control"></select>
               				<input type="hidden" id="agent_name_hid" value="${assigned_to}">
						</div>
	             	</div>
                </div> 
               	<div class="col-md-6" id="statusdiv">
					<div class="row form-group">
						<div class="col col-md-4">
							Status <strong style="color: red;">*</strong>
						</div>
						<div class="col-12 col-md-8">
							<input type="hidden" id="ticket_status_hid" value="${ticket_status}">
							<select name="ticket_status" id="ticket_status" class="form-control">
									<option value="0">--Select--</option>
									<option value="1">In Progress</option>
									<option value="2">Completed</option>
								</select>
							</div>
						</div>
					</div>
              	</div>
              	<div class="col-md-12" id="replayDiv" style="display: none;" >	           
					<div class="col-md-10">
		             	<div class="row form-group"> 
		             		<div class="col col-md-3">	             		
			                	<label for="text-input" class="form-control-label">Reply from Miso <strong style="color: red;">*</strong></label>
			                </div>
			                <div class="col-12 col-md-9">
			                	<textarea rows="2" id="miso_reply" name="miso_reply" class="form-control" maxlength="250">${viewticketCMD.miso_reply}</textarea>
	               			</div>
		             	</div>
	                </div>
				</div>
				<div class="col-md-12" id="sdReplayDiv" style="display: none;">	           
					<div class="col-md-10">
		             	<div class="row form-group"> 
		             		<div class="col col-md-3">	             		
			                	<label for="text-input" class="form-control-label">Reply from sd <strong style="color: red;">*</strong></label>
			                </div>
			                <div class="col-12 col-md-9">
			                	<textarea rows="2" id="sd_reply" name="sd_reply" class="form-control" maxlength="250">${viewticketCMD.sd_reply}</textarea>
	               			</div>
		             	</div>
	                </div>
				</div>
			</div>
		</div>
	</div>
</div>
      <div class="animated fadeIn" >
             <div class="row">
		<div class="col-md-12" align="center">
			<div class="col-md-12" align="center">
				<input id="update1" type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();"> 
				<input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv('printableArea');">
			</div>
		</div>
	</div>
</div>
</div>
       		
	
<div id="printableArea" style="display: none;">
<div  class="watermarked" id="divShow" style="display: block;">
	 	</div>
	 	
</div>                     
</form:form>
<c:url value="getmodule_help" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/>
	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
	<input type="hidden" name="contraint" id="contraint" value=""/>
</form:form> 
	<script>
    	function getmodule_help(id)
       	{  
       		document.getElementById("id1").value=id;
       	   	document.getElementById("contraint").value="Edit";
       	   	document.getElementById("pageUrl").value="viewticketTiles";
           	document.getElementById("getDownloadImageForm").submit();
       	}  
</script>
<script>
function isvalidData(){
	var status = document.getElementById('ticket_status').value;
	if('${typeWhr}' == "SHOW"){
 	if($("#agent_name").val() == "0"){
		alert("Please Select Agent Name");
		$("#agent_name").focus();
		return false;
	}

 	if(status == "0" ){
		alert("Please Select Status");
		$("#ticket_status").focus();
		return false;
	}
 	if(status == "2" && $("#miso_reply").val() == ""){
 		alert("Please enter Reply");
		$("#miso_reply").focus();
		return false;
 	}
	}
 	return true;
}
</script> 
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled" />
</c:if>
<script>
$(document).ready(function() {debugger;
	$('select#ticket_status').change(function() {
		var ticket_status = $(this).val();
		if(ticket_status == '2'){
			$("div#replayDiv").show();
		}else{
			$("div#replayDiv").hide();
		}
	});
	
	if('${typeWhr}' == "SHOW"){
		$("div#assignto").show();
	}if('${typeWhr}' == "sd9"){
		$("div#replayDiv").hide();
		$("div#statusdiv").hide();
		$("div#sdReplayDiv").show();
	
	}
	
	if('${help_topic}' == 'Line Dte Obsr' && '${screen_name}' == 'PERS UE VETTING')
	{
		$("div#sdReplayDiv").show();
		 if ($("#sd_reply").val().trim() == "") {
			 $("#sd_reply").val("Pending Reply from SD");
		 }
	     $("#sd_reply").prop("readonly", true);
	}
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
 	 if('${screen_shot}'!= ""){
 		 $("div#d1").show();
 		 $("div#d2").hide();
	  }else{
 		 $("div#d1").hide();
		 $("div#d2").show(); 
 	 }
		
	
	if('${ticket_status}' == '1')
	{
		$("#ticket_status").val('${ticket_status}');
		$("#agent_name").val('${assigned_to}');
	}
	if('${ticket_status}' == '2')
	{
		
		$("#ticket_status").val('${ticket_status}');
		$("#agent_name").val('${assigned_to}');
		
		document.getElementById("ticket_status").disabled = true;
	 	document.getElementById("agent_name").disabled = true;
	 	document.getElementById("miso_reply").disabled = true;
	 	$("div#replayDiv").show();
	 	$("input#update1").hide();
	}
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	var module_hid = '0';
	if('${moduleId}' != 'others'){
		module_hid = '${moduleId}'; 
	}
	var key = "${_csrf.parameterName}";
 	var value = "${_csrf.token}";
   	$.post("getUserNameList?"+key+"="+value,{id3 : module_hid}, function(j) {
	   var agent_name_hid = $("#agent_name_hid").val();
		var options = '<option   value="0">'+ "--Select--" + '</option>';
		for ( var i = 0; i < j.length; i++) {
			if(agent_name_hid == j[i][1]){
				options += '<option value="'+j[i][1]+'" name="'+j[i][0]+'" selected=selected>'+j[i][1]+'</option>';
			}	
			else{
				options += '<option value="'+j[i][1]+'" name="'+j[i][0]+'" >'+ j[i][1]+'</option>';
			}
		}
		$("select#agent_name").html(options);
	});
});
function printDiv() 
{
	$("div#divwatermark").val('').addClass('watermarked'); 
  	watermarkreport();
	var printLbl = [];
	var printVal = [];
	 if('${ticket_status}' == "0"){
		status = "New";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Issue summary :","Status:","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',document.getElementById('agent_name').value,'${issue_summary}',status,'${description}'];
	}else if('${ticket_status}' == "1"){
		status = "In Progress";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Assign on :","Issue summary :","Status:","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',document.getElementById('agent_name').value,'${assigned_dt}','${issue_summary}',status,'${description}'];
	}else if('${ticket_status}' == "2"){
		status = "Completed";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign to :","Assign on :","completed on:","Issue summary :","Status:","Description :","","Reply From MISO :"];
		 printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',document.getElementById('agent_name').value,'${assigned_dt}','${completed_dt}','${issue_summary}',status,'${description}','','${viewticketCMD.miso_reply}'];
	}else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"select#status");
 }
</script>