<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<dandelion:asset cssExcludes="datatables" />
<dandelion:asset jsExcludes="datatables" />
<dandelion:asset jsExcludes="jquery" />

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/amin_module/rbac/report/criteriareportdesign.css">

<style>
.dataTables_scrollBody {
	overflow-x: hidden !important;
	overflow-y: scroll !important;
	scrollbar-width: thin;
}
.dataTables_scrollHead {
	overflow-y: hidden !important;
}
.ui-toolbar.ui-widget-header, .dataTables_scrollHead.ui-state-default {
	width: calc(100% - 8px) !important;
}
.dataTables_scrollHeadInner {
	padding-right: 0 !important;
	width: 100% !important;
}
.dataTable {
	width: 100% !important;
}
.watermarked::before {
	color: #3c3838;
	opacity: 1;
	width: calc(100% - 8px) !important;
}
.dataTables_wrapper {
	opacity: 0.9;
}
</style>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
		$('html').bind('cut copy paste', function (e) {
			e.preventDefault();
	   	});
	   	$("html").on("contextmenu",function(e){
			return false;
		}); 
		try{
			if(window.location.href.includes("updateid="))
			{
				var url = window.location.href.split("?updateid=")[0];
				var m = window.location.href.split("&msg")[1];
				window.location = url;
				if(m.includes("Updated+Successfully") || m.includes("Password+pattern+doesn't+match.") ){
					window.opener.getRefreshReport('user_mst1',m);
					window.close('','_parent','');
				} 
			} 
			else if(window.location.href.includes("msg="))
			{
				var m=  window.location.href.split("?msg=")[0];
				window.location = m;
				if( m.includes("Password+pattern+doesn't+match.") ){
					window.opener.getRefreshReport('user_mst1',m);
					window.close('','_parent','');
				}
			}
		}catch (e) { } 
	});
	function getRefreshReport(page,msg)
	{
		if(msg.includes("Updated+Successfully") || msg.includes("Password+pattern+doesn't+match.")  )
		{
			if(page == "user_mst1")
			{
				closeWindow();
				document.getElementById('mainForm').submit();
			}
		}
	} 
	function closeWindow()
	{
		newWin.close();
	}
</script>
<form:form name="search_user_mst_formation_wise" id="search_user_mst_formation_wise" action="#" method='POST'>
	<div class="col-md-12" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Formation Wise Search User</h5>
			</div>
			<div class="card-body">
	            			<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>
		          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Command </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
								            	${selectcomd}
									            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control" >
			                 					${selectcorps}
			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
			                 				</select>
			                			</div>
					              	</div>
								</div>
							</div>
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
						                <div class="col col-md-4">
						                  <label class=" form-control-label">Division</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" >
						                 		${selectDiv}
						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
						                 	</select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Brigade</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
								
							</div>
						</div>
			<div class="card-footer" align="center">
				<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();"> 
				<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
				<i class="fa fa-download"></i><input type="button" id="exportId" class="btn btn-sm btn_report" style="background-color: #e37c22;color: white;" value="Export" onclick="exportFn();">
			</div>
		</div>
	</div>
</form:form>

<div class="col-md-12" id="divPrint" style="display: none">
	<div id="divShow"></div>
	<div class="watermarked" data-watermark="" id="divwatermark"
		style="display: block;">
		<span id="ip"></span>
		<datatables:table id="applicanttbl1" url="getUserReportListFormationWise" serverSide="true" pipelining="true" pipeSize="3" row="latlon"
			rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" bDestroy="true"
			filterable="false" sortable="false" processing="true" border="1" autoWidth="true" pageable="true" paginationType="full_numbers"
			stateSave="false" deferRender="true" scrollX="100%" scrollCollapse="true">
			<datatables:column title="SUS NO" property="sus_no" searchable="false" />
			<datatables:column title="UNIT NAME" property="unit_name" searchable="false" />
			<datatables:column title="USER ID" property="username" searchable="false" />
			<datatables:column title="ARMY NO" property="army_no" searchable="false"  />
			<%-- <datatables:column title="USER ROLE" property="role" searchable="false" /> --%>
			<%-- <datatables:column title="Action" property="Action" searchable="false" sortable="false" /> --%>
		</datatables:table>
	</div>
</div>

	<c:url value="search_user_by_formation" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
		<input type="hidden" name="cont_comd1" id="cont_comd1"/>
		<input type="hidden" name="cont_corps1" id="cont_corps1" value="0"/>
		<input type="hidden" name="cont_div1" id="cont_div1" value="0"/>
		<input type="hidden" name="cont_bde1" id="cont_bde1" value="0"/>
	</form:form>

	<c:url value="update_user_mstUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0" />
	</form:form>

	<c:url value="search_user_mstUrl" var="mainFormUrl" />
	<form:form action="${mainFormUrl}" method="GET" id="mainForm" name="mainForm"></form:form> 



	<c:url value="exportUserListForm" var="exportUrl" />
	<form:form action="${exportUrl}" method="post" id="exportUserListForm" name="exportUserListForm"></form:form>

<script>
function clearAll(){
	$("#divPrint").hide();
}
$(document).ready(function () {
	document.getElementById("exportId").disabled = true;
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	if('${cont_comd1}' != ""){
		$("#divPrint").show();
		document.getElementById("exportId").disabled = false;
    	$("#cont_comd").val('${cont_comd1}');
    	getCONTCorps('${cont_comd1}');
	}
	if('${cont_corps1}' != ""){
		 getCONTDiv('${cont_corps1}');
	}
    if('${cont_div1}' != ""){
    	getCONTBde('${cont_div1}');
    }
	
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   		var fcode = this.value;
	   		if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{	
   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
	   			
	   			getCONTCorps(fcode);
	    	
	       		fcode += "00";	
	   			getCONTDiv(fcode);
	       	
	       		fcode += "000";	
	   			getCONTBde(fcode);
   		}
	});
	$('select#cont_corps').change(function() {
	   	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	});
	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
	});
});

function Search(){
	if($("#cont_comd").val() == ""){
		alert("Please Select Command");
		return false;
	}else{
		jQuery("#WaitLoader").show();
		$("#cont_comd1").val($("#cont_comd").val());
		$("#cont_corps1").val($("#cont_corps").val());
		$("#cont_div1").val($("#cont_div").val());
		$("#cont_bde1").val($("#cont_bde").val());
		document.getElementById('searchForm').submit();
	}
}
function exportFn(){
	if($("#cont_comd").val() == ""){
		alert("Please Select Command");
		return false;
	}else{
		document.getElementById('exportUserListForm').submit();
	}
}

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				
				for ( var i = 0; i < length; i++) {
					if('${cont_corps1}' ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				$("select#cont_corps").html(options);
			}
		});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	   		if(j != ""){
 		   	var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < length; i++) {
				if('${cont_div1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}
		   		$("select#cont_div").html(options);
	   		}
		});
 } 
function getCONTBde(fcode){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		jQuery("select#cont_bde").html(options);
		for ( var i = 0; i < length; i++) {
			if('${cont_bde1}' ==  dec(enc,j[i][0])){
				options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
				$("#cont_bname").val(dec(enc,j[i][1]));
			}else{
				options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
			}
		}	
		$("select#cont_bde").html(options);
		}
	});
}
</script>
<script>
	function editData(userid){	
		document.getElementById('updateid').value=userid;
		document.getElementById('updateForm').submit();
	}
</script>