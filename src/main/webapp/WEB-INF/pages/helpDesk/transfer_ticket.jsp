<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/printWatermark/common.js"></script>

<form:form name="transferticket" id="transferticket">
	<div class="container" align="center">
		<div class="animated fadeIn">
			<div class="card">
				<div class="card-header">
					<h5>Transfer Ticket</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Ticket Id</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="id" name="id" value="${id}" /> <b>:
										${ticket}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Date</div>
								<div class="col-12 col-md-8">
									<b>: ${created_on}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">User</div>
								<div class="col-12 col-md-8">
									<b>: ${username}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">SUS No</div>
								<div class="col-12 col-md-8">
									<b>: ${sus_no}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Unit Name</div>
								<div class="col-12 col-md-8">
									<b>: ${unit_name}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Module</div>
								<div class="col-12 col-md-8">
									<b>: ${moduleName}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Sub Module</div>
								<div class="col-12 col-md-8">
									<b>: ${sub_module}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Screen Name</div>
								<div class="col-12 col-md-8">
									<b>: ${screen_name}</b>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">Help Topic</div>
								<div class="col-12 col-md-8">
									<b>: ${help_topic}</b>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">File</div>
								<div class="col-12 col-md-8" id="d1">
									<a href='#' onclick="getmodule_help('${id}');"
										class="btn btn-primary btn-sm">Download</a>
								</div>
								<div class="col-12 col-md-8" id="d2">
									<h4>No Screenshot Available</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-2 col-md-2">Issue summary</div>
						<div class="col-10 col-md-10">
							<b>: ${issue_summary}</b>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-2 col-md-2">Description</div>
						<div class="col-10 col-md-10">
							<b>: ${description}</b>
						</div>
					</div>
					<div class="col-md-12"  style="border-top: solid black 1px;">
						<c:if test="${assigned_dt != null}">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">Assign on</div>
									<div class="col-12 col-md-8">
										<b>: ${assigned_dt}</b>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${completed_dt != null}">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">completed on</div>
									<div class="col-12 col-md-8">
										<b>: ${completed_dt}</b>
									</div>
								</div>
							</div>
						</c:if>
						<hr>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group"> 
			             		<div class="col col-md-4">	             		
				                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Module</label>
				                </div>
				                <div class="col-12 col-md-8">
		               				<select  id="module" name="module" class="form-control" > 
		               				<option value="0">--Select--</option>
		       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
		       								<option style="text-transform: uppercase;"  value="${item.id}">${item.module_name}</option>
		       							</c:forEach>
								    </select>
								</div>
			             	</div>
		               </div>
		                <div class="col-md-6">
			             	<div class="row form-group"> 
			             		<div class="col col-md-4">	             		
				                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Sub Module</label>
				                </div>
				                <div class="col-12 col-md-8">
		               				<select  id="sub_module" name="sub_module" class="form-control">
		               					<option value="0">--Select--</option> 
								   </select>
								</div>
			             	</div>
		               </div> 
					</div>
					<div class="col-md-12">				  
		                <div class="col-md-6">
			             	<div class="row form-group"> 
			             		<div class="col col-md-4">	             		
				                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Screen Name </label>
				                </div>
				                <div class="col-12 col-md-8">
		               				<select  id="screen_name" name="screen_name" class="form-control">
			               				<option value="0">--Select--</option>  
								   </select>
								</div>
			             	</div>
		               </div>
		           </div>
				</div>
			</div>
		</div>
		<div class="animated fadeIn">
			<div class="row">
				<div class="col-md-12" align="center">
					<div class="col-md-12" align="center">
						<input id="update1" type="submit" class="btn btn-primary btn-sm" value="Transfer" onclick="return isvalidData();">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
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
       	document.getElementById("contraint").value="Edit";
       	document.getElementById("pageUrl").value="viewticketTiles";
       	document.getElementById("getDownloadImageForm").submit();
    }
</script>
<script>
function isvalidData(){
	if($("select#module").val() == "0"){
		alert("Please Select Module");
		$("select#module").focus();
		return false;
	}
	else if($("select#sub_module").val() == "0"){
		alert("Please Select Sub Module");
		$("select#sub_module").focus();
		return false;
	}
	else if($("select#screen_name").val() == "0"){
		alert("Please Select Screen");
		$("select#screen_name").focus();
		return false;
	}else{
		var form = jQuery('#transferticket')[0];
		var data = new FormData(form);
		jQuery.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "transferTicketAction?"+key+"="+value,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				alert(data);
				if(data == "Data Saved Successfully."){
					window.location.href='manageticket';
				}
			}
		});
		return false;
	}
}
</script>
<script>
$(document).ready(function() {
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
	}
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	var module_hid = '0';
	if('${moduleId}' != 'others'){
		module_hid = '${moduleId}'; 
	}
	
 	$('select#module').change(function(){
 		var mid = this.value;
 		var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
 		$("select#screen_name").html(options);  
 		if(mid == "0"){
 			$("select#sub_module").html(options);
 			
 		}else{
		   <c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
				if('${item.module.id}' == mid){
					options += '<option style="text-transform: uppercase;" value="${item.id}">${item.submodule_name}</option>';
				}
			</c:forEach>
			$("select#sub_module").html(options);
 		}
	});
	$('select#sub_module').change(function(){
	    var mid = this.value; 
	    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
		<c:forEach var="item" items="${getScreenList}" varStatus="num" >
			if('${item.sub_module.id}' == mid){
				options += '<option style="text-transform: uppercase;" value="${item.id}">${item.screen_name}</option>';
			}
		</c:forEach>
		$("select#screen_name").html(options); 
	});
});
</script>