<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript"
	src="js/amin_module/helpdesk/jquery-1.12.3.js"></script>
<script>
//var username="${username}";
</script>
<div class="container" align="center">
	<div class="animated fadeIn">
		<div class="card">
			<div class="card-header">
				<h5>View Ticket Details</h5>
			</div>
			<div class="card-body card-block">
				<div class="watermarked report_print" data-watermark=""
					id="divwatermark" style="display: block;">
					<br>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Ticket
										Id</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${ticket}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Date
									</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${created_on}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">User</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${username}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">SUS
										No </label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${sus_no}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Unit
										Name</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${unit_name}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Module</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${moduleName}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Sub Module </label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${sub_module}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Screen Name </label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${screen_name}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Help Topic </label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${help_topic}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">File
									</label>
								</div>
								<div class="col-12 col-md-8" id="d1">
									<a href='#' onclick="getmodule_help('${id}');"
										class="btn btn-primary btn-sm">Download</a>
								</div>
								<div class="col-12 col-md-8" id="d2">
									<h4>No File Available</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-2 col-md-2">
							<label for="text-input" class=" form-control-label">Issue
								summary </label>
						</div>
						<div class="col-10 col-md-10">
							<b>: ${issue_summary}</b>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-2 col-md-2">
							<label for="text-input" class=" form-control-label">Description</label>
						</div>
						<div class="col-10 col-md-10">
							<b>: ${description}</b>
						</div>
					</div>
					<div class="col-md-12">
						<c:if test="${assigned_dt != null}">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class=" form-control-label">Assign on </label>
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
									<label for="text-input" class=" form-control-label">completed on</label>
								</div>
								<div class="col-12 col-md-8">
									<b>: ${completed_dt}</b>
								</div>
							</div>
						</div>
						</c:if>
					</div>
					
					<c:if test="${userticketCMD.miso_reply != null}">
						<div class="col-md-12 form-group">
							<div class="col-2 col-md-4">
								<label for="text-input" class=" form-control-label">Reply From MISO</label>
							</div>
							<div class="col-10 col-md-10">
								<b>: ${userticketCMD.miso_reply}</b>
							</div>
						</div>
					</c:if>
					
					<c:if test="${userticketCMD.sd_reply != null}">
						<div class="col-md-12 form-group">
							<div class="col-2 col-md-4">
								<label for="text-input" class=" form-control-label">Reply From sd</label>
							</div>
							<div class="col-10 col-md-10">
								<b>: ${userticketCMD.sd_reply}</b>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="col-md-12" align="center">
					<button id="printId" class="btn btn-primary btn-sm btn_report" onclick="printDiv('printableArea');">
						<i class="fa fa-print"></i> Print
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="printableArea" style="display: none;">
	<div class="watermarked" data-watermark="" id="divShow"
		style="display: block;"></div>
</div>
<%-- </form:form> --%>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}"
		disabled="disabled" />
</c:if>
<c:url value="getmodule_help" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post"
	id="getDownloadImageForm" name="getDownloadImageForm"
	modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="pageUrl" id="pageUrl" value="" />
	<input type="hidden" name="contraint" id="contraint" value="" />
</form:form>
<script>
      
        function getmodule_help(id)
        {  
    	   document.getElementById("id1").value=id;
    	   document.getElementById("contraint").value="";
    	   document.getElementById("pageUrl").value="user_ticketTiles";
           document.getElementById("getDownloadImageForm").submit();
        }  
</script>
<script type="text/javascript">
$(document).ready(function() {
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	 if('${screen_shot}'!=""){
		 $("div#d1").show();
		 $("div#d2").hide();
	  } else{
		 $("div#d1").hide();
		 $("div#d2").show(); 
	 }
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
});
</script>
<script>
function printDiv(){debugger;
	$("div#divwatermark").val('').addClass('watermarked'); 
  	watermarkreport();
	var printLbl = [];
	var printVal = [];
	 if('${ticket_status}' == "0"){
		status = "New";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Status:","Issue summary :","Description :"];
		printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}',status,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "1"){
		status = "In Progress";
		printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:","Issue summary :","Description :"];
		 printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}', '${assigned_dt}',status,'${issue_summary}','${description}'];
	}else if('${ticket_status}' == "2"){
		if('${userticketCMD.sd_reply}' != ""){
			status = "Completed";
			printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:", "completed on:","Issue summary :","Description :","Reply From MISO :","Reply From SD :"];
			printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}', '${assigned_dt}',status,'${completed_dt}','${issue_summary}','${description}','${userticketCMD.miso_reply}','${userticketCMD.sd_reply}'];

			}else{
			status = "Completed";
			printLbl = ["Ticket Id :","Created on :","Created by :", "SUS No :", "Unit  :", "Module :", "Sub Module :", "Screen Name :", "Help Topic :","Assign on :","Status:", "completed on:","Issue summary :","Description :","Reply From MISO :"];
			printVal = ['${ticket}','${created_on}','${username}','${sus_no}','${unit_name}','${moduleName}','${sub_module}','${screen_name}','${help_topic}', '${assigned_dt}',status,'${completed_dt}','${issue_summary}','${description}','${userticketCMD.miso_reply}'];
		}
	}
	
	else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"");
}
</script>