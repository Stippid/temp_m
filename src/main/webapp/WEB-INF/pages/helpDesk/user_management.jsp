<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<style>
textarea {
    width:690px;
    height:200px;
    text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<c:url value="deleteUsermngURL" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form>
<form:form name="user_management" id="user_management" action="user_managementAction" method='POST' commandName="user_managementCMD">
<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>User Management</h5></div>
		<div class="card-body card-block cue_text">
            <div class="col-md-12">	
             <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Module  </label>
		                </div>
		                <div class="col-12 col-md-8">
		             <input type="hidden" id="module_hid" name=module_hid  class="form-control" value="${module_name1}" >
						    <select  id="moduleid" name="moduleid" class="form-control" > 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
       								<option style="text-transform: uppercase;" value="${item.id}" name="${item.module_name}">${item.module_name}</option>
       							</c:forEach>
       							
						    </select>
						</div>
	             	</div>
                </div>
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Agent Name</label>
		                </div>
		                <div class="col-12 col-md-8">
               				<input type="text" id="agent_name" name="agent_name" class="form-control" maxlength="30" autocomplete="off">
               				<input type="hidden" id="id_hid" name="id_hid" class="form-control">
               			</div>
	             	</div>
               </div>
           </div>
	        </div>
            <div class="card-footer" align="center">
				<a href="user_management" class="btn btn-success btn-sm">Clear</a>   	
            	<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData();">
            	<input type="submit" id="update_btn" class="btn btn-primary btn-sm" value="Update" style="display: none" onclick="return isvalidData();">
            	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
            </div>
            </div>
		</div>
		<div class="container"  id="divPrint" style="display: none;"> 
			<!-- <div class="card-body card-block" id="divPrint" style="display: none;"> -->
				<div id="divShow" style="display: block;"></div>
				<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
					<table id="TicketReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead style="background-color: #9c27b0; color: white;">
							<tr>
								<th style="font-size: 15px;text-align: center;width: 10%;">Ser No</th>
								<th style="font-size: 15px;text-align: center;">Module</th>
								<th style="font-size: 15px;text-align: center;">Agent Name</th>
								<th style="font-size: 15px;text-align: center;width: 10%;">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num">
								<tr>
									<th style="font-size: 15px;text-align: center;width: 10%;">${num.index+1}</th>
									<th style="font-size: 15px;text-align: center;">${item.module_name}</th>
									<th style="font-size: 15px;text-align: center;">${item.agent_name}</th>
									<th id="thAction1" style="font-size: 15px;text-align: center;width: 10%;">${item.id}</th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			<!-- </div> -->
		</div>	
<!-- </div> -->
</form:form> 
<c:url value="getHelpMngtList1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="module1">
		<input type="hidden" name="module_name1" id="module_name1" value="0"/>
		<input type="hidden" name="agent_name1" id="agent_name1" value="0"/>
		<input type="hidden" name="userid1" id="userid1" value="0"/>
	</form:form> 
<Script>

function isvalidData() {
	if($("select#moduleid").val() == "0")
	{
		alert("Please Select module");
		$("select#moduleid").focus();
		return false;
	} 
	if($("input#agent_name").val() == "")
	{
		alert("Please Enter Agent name");
		$("input#agent_name").focus();
		return false;
	} 
	return true; 
}
var newWin;
function Search(){
	watermarkreport();
	 if($("select#moduleid").val() == "0")
	 {		 
		 alert("Please Select module");
		 $("select#moduleid").focus();
		return false;
	 }else{
		 $("#module_name1").val($("#moduleid").val());
	     $("#agent_name1").val($("#agent_name").val());
	     document.getElementById('searchForm').submit();
		 $("#divPrint").show();
	 }
	return true;	
}
 $(document).ready(function () {
	 watermarkreport();
	 if('${module_name1}' != ""){
		 $("select#moduleid").val('${module_name1}');
		$("input#agent_name").val('${agent_name1}');
	 } 

	 if('${list.size()}' == '0' ){
	     $("table#TicketReport").append("<tr><td colspan='4'>Data Not Available</td></tr>");
	 }	
	 if('${module_name1}' != "" || '${agent_name1}' != ""){
		 $("#divPrint").show();
	 } 
	
	 try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}catch(e){} 
	
	$('input#agent_name').keyup(function(){
		var moduleName = $("#moduleid").find('option:selected').attr("name");
		if(this.value == ""){
			clearItem();	
		}else if(moduleName == undefined){
			alert("Please Select module");
			$("#moduleid").focus();
			clearItem();
		}else{
			agent_nameAutoComplate(moduleName);
		}
	});
 }); 
 
 function clearItem(){
	$("#agent_name").val("");
}
 </script>
<script>
var newWin;
function editData(id,module,agent_name){
	document.getElementById('moduleid').value=module;
	document.getElementById('id_hid').value=id;
	document.getElementById('agent_name').value=agent_name;
	document.getElementById('update_btn').style.display='inline-block'; 
	document.getElementById('save_btn').style.display='none';  
}
function closeWindow() {
	newWin.close();   
}
var id=$("#id_hid").val();
function deleteData(id) {
	$.post("deleteUsermngURL?"+key+"="+value,{deleteid : id}, function(j) {
		alert(j);
		Search();
	}); 
}

//Start Agent Name Autocomplate
function agent_nameAutoComplate(moduleName){
	var agent_name = $("#agent_name");
	agent_name.autocomplete({
		source : function(request, response) {
			$.ajax({type : 'POST',url : "getUserNameAutoComplateList?" + key + "=" + value,data : {agent_name : agent_name.val(),moduleName:moduleName},
				success : function(data) {
					var myResponse = [];
					for (var i = 0; i < data.length; i++) {
						myResponse.push(data[i]);
					}
					response(myResponse);
				}
			});
		},
		minLength : 1,
		autoFill : true,
		change : function(event, ui) {
			if (ui.item) {
				return true;
			} else {
				alert("Please Enter valid input.");
				agent_name.val("");
				agent_name.focus();
				return false;
			}
		},
		select : function(event, ui) {
			$(this).val(ui.item.value);
		}
	});
}
//end Agent Name Autocomplate
</script>