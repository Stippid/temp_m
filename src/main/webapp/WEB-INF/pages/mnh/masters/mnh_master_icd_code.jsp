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

<form:form id="ICD_MasterForm" action="ICD_MasterAction" method='POST'
	commandName="ICD_MasterFormCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>
					<span id="lbladd"></span> ICD CODE MASTER
				</h5>
				<h6>
					<span style="font-size: 12px; color: red">(To be entered by
						MISO)</span>
				</h6>
			</div>

			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">* </strong>ICD Code</label>
								</div>
								<div class="col-md-8">
								<!-- 	<input type="text" id="icd_code" name="icd_code"
										class="form-control-sm form-control" autocomplete="off" onkeypress="return onlyAlphaNumeric(event);"
										maxlength="10" placeholder="Search ICD Code..." oninput="this.value = this.value.toUpperCase()">
								
								 -->
								
								<input title="Enter STANDARD FORMAT : [ALPHABET NUMBER NUMBER. NUMBER NUMBER NUMBER NUMBER] EG: A11.1111" oninput="this.value = this.value.toUpperCase()"
												type="text" id="icd_code" name="icd_code"
												class="form-control autocomplete"
												
													onchange="return validateCodeMac();"
												
												 autocomplete="off"></input>	
								
								
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">* </strong>ICD Description</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="icd_description" name="icd_description" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Search ICD Description...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Disease
										MMR</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_mmr" name="disease_mmr" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter Disease MMR...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Disease
										ASO</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_aso" name="disease_aso" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter Disease ASO...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12 ">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Principal</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_principal" onkeypress="return onlyAlphaNumeric(event);"
										name="disease_principal" class="form-control-sm form-control"
										autocomplete="off" maxlength="255"
										placeholder="Enter Disease Principal...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Type</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_type" name="disease_type" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Disease Type...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Block
										Description</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="block_description" onkeypress="return onlyAlphaNumeric(event);"
										name="block_description" class="form-control-sm form-control"
										autocomplete="off" maxlength="255"
										placeholder="Enter Block Description...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Sub Type</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_subtype" name="disease_subtype" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Disease Sub Type...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Family</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_family" name="disease_family" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="50" placeholder="Enter Disease Family...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Children</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_children"
										name="disease_children" class="form-control-sm form-control" onkeypress="return onlyAlphaNumeric(event);"
										autocomplete="off" maxlength="50"
										placeholder="Enter Disease Children...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										CR Type</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_cr_type" name="disease_cr_type" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Disease CR Type...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										CR Block Desc</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="disease_cr_block_description"
										name="disease_cr_block_description" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="255" placeholder="Enter Disease CR Block Desc...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">ICD
										Short Form</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="short_form" name="short_form" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="100" placeholder="Enter ICD Short Form...">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">ICD
										Short Description</label>
								</div>

								<div class="col-md-8">
									<input type="text" id="short_desc" name="short_desc" onkeypress="return onlyAlphaNumeric(event);"
										class="form-control-sm form-control" autocomplete="off"
										maxlength="250" placeholder="Enter ICD Short Description...">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4" style="text-align: left;">
									<label for="text-input" class=" form-control-label">ICD
										Type</label>
								</div>

								<div class="col-md-8">
									<select name="type" id="type"
										data-placeholder="Select the value..."
										class="form-control-sm form-control">
										<option value="GENERAL">GENERAL ICD</option>
										<option value="NBB">NEW BORN BABY (NBB) ICD</option>
										<option value="MALE">MALE ICD</option>
										<option value="FEMALE">FEMALE ICD</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="0" class="form-control">
			<div class="card-footer" align="center">
			
			    <input type="button"  class="btn btn-primary btn-sm" value="Clear" onclick="cleardata();"> 
				<!-- <a href="mnh_icd_code" type="reset" class="btn btn-primary btn-sm"> Clear </a>  -->
				<input type="submit" id="save_btn" class="btn btn-success btn-sm" value="Save" onclick="return validate()" /> 
				<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return getIcdCode();">
			</div>
		</div>
	</div>
</form:form>


<div class="container" id="getPartNoSearch" style="display: block;">
	<div class="page-wrapper">
		<datatables:table id="applicanttbl" url="icd_codeRpt"
			serverSide="true" pipelining="true" pipeSize="3" row="latlon"
			rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10"
			jqueryUI="true" bDestroy="true" filterable="true" sortable="true"
			processing="true" border="1" autoWidth="true" pageable="true"
			paginationType="full_numbers" stateSave="false" deferRender="true"
			scrollX="100%" scrollY="600px" scrollCollapse="true">

			<datatables:column title="ICD Code" property="icd_code" searchable="true" data-halign="left" data-valign="left" />
			<datatables:column title="ICD Description" property="icd_description" searchable="true" data-halign="left" data-valign="left" />
			<datatables:column title="Action" property="action111" sortable="false" searchable="false" />
		</datatables:table>
	</div>
</div>

<c:url value="deleteicd_code" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Search_mnh_icd_code" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="id1">
	<input type="hidden" name="icd_code1" id="icd_code1" value="0" />
		<input type="hidden" name="icd_description1" id="icd_description1" value="0" />
</form:form>

<script>
$(document).ready(function() {
	
/* 	if($("#disease_mmr").val() == "null" || $("#disease_mmr").val() == null){
		alert("in");
		 document.getElementById("disease_mmr").value="";
	} */
	
	
	$("#icd_code").tooltip();
	$("#icd_code").css('cursor','pointer');
	
	
	if('${flag}' == 0){
		$("#getPartNoSearch").hide();
	}else{
		$("#getPartNoSearch").show();
	}
	
	var q1 = '${icd_code1}';
	var q2 = '${icd_description1}';
	
	 if(q1 != ""){
	    	$("#icd_code").val(q1);
		}  
	  if(q2 != ""){
	    	$("#icd_description").val(q2);
		}  
});
	 


function editData(id,icd_code,icd_description,disease_mmr,disease_aso,disease_principal,disease_type,
		block_description,disease_subtype,disease_family,disease_children,
		disease_cr_type,disease_cr_block_description,short_form,short_desc,type){
	
	document.getElementById('lbladd').innerHTML = "UPDATE" ;
		 $("#id").val(id);
		 $("#icd_code").val(icd_code);
		 $("#icd_description").val(icd_description);
		 $("#disease_mmr").val(disease_mmr);
		 if($("#disease_mmr").val() == "null" || $("#disease_mmr").val() == null){
				$("#disease_mmr").val('');
			} 
		 $("#disease_aso").val(disease_aso);
		 if($("#disease_aso").val() == "null" || $("#disease_aso").val() == null){
				$("#disease_aso").val('');
			} 
		 $("#disease_principal").val(disease_principal);
		 if($("#disease_principal").val() == "null" || $("#disease_principal").val() == null){
				$("#disease_principal").val('');
			} 
		 $("#disease_type").val(disease_type);
		 if($("#disease_type").val() == "null" || $("#disease_type").val() == null){
				$("#disease_type").val('');
			} 
		 $("#block_description").val(block_description);
		 if($("#block_description").val() == "null" || $("#block_description").val() == null){
				$("#block_description").val('');
			} 
		 $("#disease_subtype").val(disease_subtype);
		 if($("#disease_subtype").val() == "null" || $("#disease_subtype").val() == null){
				$("#disease_subtype").val('');
			} 
		 $("#disease_family").val(disease_family);
		 if($("#disease_family").val() == "null" || $("#disease_family").val() == null){
				$("#disease_family").val('');
			} 
		 $("#disease_children").val(disease_children);
		 if($("#disease_children").val() == "null" || $("#disease_children").val() == null){
				$("#disease_children").val('');
			} 
		 $("#disease_cr_type").val(disease_cr_type);
		 if($("#disease_cr_type").val() == "null" || $("#disease_cr_type").val() == null){
				$("#disease_cr_type").val('');
			} 
		 $("#disease_cr_block_description").val(disease_cr_block_description);
		 if($("#disease_cr_block_description").val() == "null" || $("#disease_cr_block_description").val() == null){
				$("#disease_cr_block_description").val('');
			} 
		 $("#short_form").val(short_form);
		 if($("#short_form").val() == "null" || $("#short_form").val() == null){
				$("#short_form").val('');
			} 
		 $("#short_desc").val(short_desc);
		 if($("#short_desc").val() == "null" || $("#short_desc").val() == null){
				$("#short_desc").val('');
			} 
		 $("#type").val(type);
		 if($("#type").val() == "null" || $("#type").val() == null){
				$("#type").val('');
			} 
} 

$("#icd_code").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMNHDistinctICDList',icd_code,{a:d1,b:"1"},'','','1','','','');
});

$("#icd_description").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMNHDistinctICDList',icd_description,{a:d1,b:"2"},'','','1','','','');
});


function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
function validate(){
	
	if($("input#icd_code").val().trim() == ""){
		alert("Please Enter the Icd Code");
		$("#icd_code").focus();
		return false;
	}
	if($("input#icd_description").val().trim() == ""){
		alert("Please Enter the Icd Description");
		$("#icd_description").focus();
		return false;
	}
/* 	if($("input#disease_mmr").val().trim() == ""){
		alert("Please Enter the Disease MMR");
		$("#disease_mmr").focus();
		return false;
	}
	if($("input#disease_aso").val().trim() == ""){
		alert("Please Enter the Disease ASO");
		$("#disease_aso").focus();
		return false;
	}
	
	if($("input#disease_principal").val().trim() == ""){
		alert("Please Enter the Disease Principal");
		$("#disease_principal").focus();
		return false;
	}
	
	if($("input#disease_type").val().trim() == ""){
		alert("Please Enter the Disease Type");
		$("#disease_type").focus();
		return false;
	}
	
	if($("input#block_description").val().trim() == ""){
		alert("Please Enter the Block Description");
		$("#block_description").focus();
		return false;
	}
	if($("input#disease_subtype").val().trim() == ""){
		alert("Please Enter the Disease Sub Type");
		$("#disease_subtype").focus();
		return false;
	}
	if($("input#disease_family").val().trim() == ""){
		alert("Please Enter the Disease Family");
		$("#disease_family").focus();
		return false;
	}
	if($("input#disease_children").val().trim() == ""){
		alert("Please Enter the Disease Children");
		$("#disease_children").focus();
		return false;
	}
	if($("input#disease_cr_type").val().trim() == ""){
		alert("Please Enter the Disease CR Type");
		$("#disease_cr_type").focus();
		return false;
	}
	if($("input#disease_cr_block_description").val().trim() == ""){
		alert("Please Enter the Disease CR Block Desc");
		$("#disease_cr_block_description").focus();
		return false;
	}
	if($("input#short_form").val().trim() == ""){
		alert("Please Enter the Disease ICD Short Form");
		$("#short_form").focus();
		return false;
	}
	if($("input#short_desc").val().trim() == ""){
		alert("Please Enter the Disease ICD Short Description");
		$("#short_desc").focus();
		return false;
	} */
	return true;
	
	
}
function getIcdCode()
{
	$("#icd_code1").val($("#icd_code").val());
	$("#icd_description1").val($("#icd_description").val());
	document.getElementById('searchForm').submit();
}
function cleardata(){
	
	$("#icd_code").val("");
	$("#icd_description").val("");
	
	$("#disease_mmr").val("");
	$("#disease_aso").val("");
	$("#disease_principal").val("");
	$("#disease_type").val("");
	$("#block_description").val("");
	
	$("#disease_subtype").val("");
	$("#disease_family").val("");
	$("#disease_children").val("");
	
	
	$("#disease_cr_type").val("");
	$("#disease_cr_block_description").val("");
	
	$("#short_form").val("");
	$("#short_desc").val("");
}

function validateCodeMac() {
	   
	   var  testString = $("input#icd_code").val();
		 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
	
		 }
		 else{
			 alert("Please enter letters and numbers only.");
		 }
}
// function makeMacAddress(obj){
	
// 	 if(obj.value!=''){
// 	 var val=obj.value.split('.').join('');
// 	 var v = val.match(/.{1,2}/g).join(".");
// 	 obj.value=v;
// 	 }
	 

// }
</script>



