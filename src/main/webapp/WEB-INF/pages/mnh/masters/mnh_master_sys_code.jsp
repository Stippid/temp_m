<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<dandelion:asset cssExcludes="datatables" />
<dandelion:asset jsExcludes="datatables" />
<dandelion:asset jsExcludes="jquery" />

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>




<style type="text/css">
table.dataTable, table.dataTable th, table.dataTable td {
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	text-align: center;
}

.dataTables_scrollHead {
	overflow-y: scroll !important;
	overflow-x: hidden !important;
}

.DataTables_sort_wrapper { //
	width: 80px;
}

table.dataTable tr.odd {
	background-color: #f0e2f3;
}

table.dataTable thead {
	background-color: #9c27b0;
	color: white;
}

.dataTables_paginate.paging_full_numbers {
	margin-top: 0.755em;
}

.dataTables_paginate.paging_full_numbers a {
	background-color: #9c27b0;
	border: 1px solid #9c27b0;
	color: #fff;
	border-radius: 5px;
	padding: 3px 10px;
	margin-right: 5px;
}

.dataTables_paginate.paging_full_numbers a:hover {
	background-color: #cb5adf;
	border-color: #cb5adf;
}

.dataTables_info {
	color: #9c27b0 !important;
	font-weight: bold;
}

.print_btn input {
	background-color: #9c27b0;
	border-color: #9c27b0;
}
</style>

<form:form action="sys_code_mstrAction" id="sys_code_mstr"  method="post" class="form-horizontal" commandName="sys_code_mstrCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5><span id="lbladd"></span> SYSTEM CODE</h5>
		             <h6>
					    <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
					 </h6> 
		      </div> 
		      
		      <div class="card-body card-block"> 
		      <div class="row">
		         <div class="col-md-12 ">
		         <div class="col-md-6 ">
		         <div class=" row form-group">
		              <div class="col-md-4" > 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Sys Code</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="hidden" id="id" name="id" value="0">		
		               
		                   <input type="text" id="sys_code" name="sys_code" placeholder="Search..." class="form-control-sm form-control" maxlength="30"
		                   autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"/>
		              </div>
		              </div>
		              </div>  
		              
		              <div class="col-md-6 ">
		         <div class=" row form-group">
		              <div class="col-md-4" > 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Sys Code Value</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="text" id="sys_code_value" name="sys_code_value" placeholder="Enter Sys Code Value..." 
		                   class="form-control-sm form-control" maxlength="50" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"/>
		              </div>
		              </div>
		              </div>
		          </div>
		          
		          <div class="col-md-12" >
		          <div class="col-md-6 ">
		         <div class=" row form-group">
		              <div class="col-md-4" style="text-align: left;"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Sys Code Desc</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="text" id="sys_code_desc" name="sys_code_desc" placeholder="Enter Sys Code Desc..." 
		                   class="form-control-sm form-control" maxlength="100" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"/>
		              </div>
		              </div>
		              </div>  
		              <div class="col-md-6 ">
		         <div class=" row form-group">
		              <div class="col-md-4" > 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Sys Code Value Desc</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="text" id="sys_code_value_desc" name="sys_code_value_desc" onkeypress="return onlyAlphaNumeric(event);"  placeholder="Enter Sys Code Value Desc..." 
		                   class="form-control-sm form-control" maxlength="150" autocomplete="off"/>
		              </div>
		              </div>
		              </div>
		          </div>
		          
		          <div class="col-md-12 " >
		          <div class="col-md-6 ">
		         <div class=" row form-group">
		              <div class="col-md-4" style="text-align: left;"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
		              </div>
		              
		              <div class="col-md-8">
                  			<select name="status" id="status" class="form-control-sm form-control">
								<option value="ACTIVE">ACTIVE</option>
								<option value="DEACTIVE">DEACTIVE</option>
							</select>
					  </div>
					  </div>
					  </div>
		          </div>
		          </div>
		      </div>
		      
		      <div class="card-footer" align="center" >
		        <input type="button"  class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();"> 
		   <!--    <a href="mnh_sys_code" type="reset" class="btn btn-primary btn-sm"> Clear </a>  -->
			      <input type="submit" id="save_btn" class="btn btn-success btn-sm" value="Save" onclick="return validate();"/>  
		        	<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return getSysCode();">  
		                  
              </div> 
                </div>
      			</div>
              
           
  
</form:form>


<div class="container" id="getPartNoSearch" style=" display: block; ">
	<div class="page-wrapper">
	
	     <datatables:table id="applicanttbl" url="getsyscodeRpt" serverSide="true" pipelining="true" pipeSize="3" row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="5,10,20,100,500,5000" jqueryUI="true" 
 				bDestroy="true" filterable="true" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" scrollY="100%"  scrollX="true" scrollCollapse="true" >  
			    <datatables:column title="System Code" property="sys_code" searchable="true" data-halign="left" data-valign="left" />
			     <datatables:column title="System Code Value" property="sys_code_value" searchable="true" data-halign="left" data-valign="left" />
			   
			    <datatables:column title="System Code Description" property="sys_code_desc" searchable="true" data-halign="left" data-valign="left" />
			  		    <datatables:column title="Action" property="action111" sortable="false" searchable="false" />
	
			 
		 </datatables:table> 
	</div>
</div>
	

<c:url value="deleteSysCode" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>


<c:url value="Search_mnh_sys_code" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="id1">
	<input type="hidden" name="sys_code1" id="sys_code1" value="0" />
	<input type="hidden" name="sys_code_value1" id="sys_code_value1" value="0" />
	<input type="hidden" name="sys_code_desc1" id="sys_code_desc1" value="0" />

</form:form>

<script>
function btn_clc(){
	
	$("#sys_code").val('');
	$("#sys_code_value").val('');
	$("#sys_code_desc").val('');
	$("#sys_code_value_desc").val('');
	$("#status").val('ACTIVE');
}

function validate(){
	if($("input#sys_code").val().trim() == ""){
		alert("Please Enter the system code.");
		$("#sys_code").focus();
		return false;
	}
	else if($("input#sys_code_value").val().trim() == ""){
		alert("Please Enter the system code value.");
		$("#sys_code_value").focus();
		return false;
	}
	else if($("input#sys_code_desc").val().trim() == ""){
		alert("Please Enter the system code desc.");
		$("#sys_code_desc").focus();
		return false;
	}
	else if($("input#sys_code_value_desc").val().trim() == ""){
		alert("Please Enter the  system code value desc.");
		$("#sys_code_value_desc").focus();
		return false;
	}
	else{
		var q1 = $("#sys_code").val();
		var q2 = $("#sys_code_value").val();
		
		$.post("checkExitstingbedoccex?"+key+"="+value,{d1:q1,d2:q2}, function(j) {
			var enc;
			var a;
			if(j != ""){
				enc = j[j.length-1].substring(0,16);
				a = dec(enc,j[0]);
			}
			
			if(q2 == a){
				alert("System Code and Value already exists");
				$("#sys_code").val('');
				$("#sys_code_value").val('');
			}else{
				$("#sys_code_mstr").submit();
			}
		}); 
	}
}


function editData(id,sys_code,sys_code_value,sys_code_desc,sys_code_value_desc,status){

	document.getElementById('lbladd').innerHTML = "UPDATE";
	
	$("#id").val(id);
	$("#sys_code").val(sys_code);
	$("#sys_code_value").val(sys_code_value);
	$("#sys_code_desc").val(sys_code_desc);
	$("#sys_code_value_desc").val(sys_code_value_desc);
	$("#status").val(status);
	
	
	
}

function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}

function getSysCode()
{
	
	$("#sys_code1").val($("#sys_code").val());
	$("#sys_code_value1").val($("#sys_code_value").val());
	$("#sys_code_desc1").val($("#sys_code_desc").val());
	
	document.getElementById('searchForm').submit();
}
$("#sys_code").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedAllSystemCode',sys_code,{a:d1},'','','1','','','');
});

$("#sys_code_value").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedAllSystemCodevalue',sys_code_value,{a:d1},'','','1','','','');
});
$("#sys_code_desc").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedAllSystemCodedesc',sys_code_desc,{a:d1},'','','1','','','');
});
$(document).ready(function() {
	
	if('${flag}' == 0){
		$("#getPartNoSearch").hide();
	}else{
		$("#getPartNoSearch").show();
	}
	
	
	var q = '${sys_code1}';
	if(q != ""){ 
		$("#sys_code").val(q);
	}
	
	var q1 = '${sys_code_value1}';
	if(q1 != ""){ 
		$("#sys_code_value").val(q1);
	}
	
	var q2 = '${sys_code_desc1}';
	if(q2 != ""){ 
		$("#sys_code_desc").val(q2);
	}
	
	var q3 = '${sys_code_value_desc1}';
	if(q3 != ""){ 
		$("#sys_code_value_desc").val(q3);
	}
	
	var q4 = '${stat1}';
	if(q4 != ""){ 
		$("#status").val(q4);
	}
		
	
});
</script>
