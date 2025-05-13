<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>



<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
  
  <!-- new datatables -->
<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>

   <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<style>
.select-position{
    position: absolute;
    top: 57px;
    z-index: 9;
    left: 7px;
    cursor: pointer;
}
</style>


</head>
<body>

	<form:form action="search_xml?${_csrf.parameterName}=${_csrf.token}"
		method="POST" class="form-horizontal" enctype="multipart/form-data">
<div class="animated fadeIn">
	    
	    		<div class="card">
	    		<div class="card-header" align="center"><h5> XML Upload Logs </h5> </div>
	          			<div class="card-body card-block">
	          					<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> SUS No </label>
						                </div>
						                <div class="col-md-8">
						                
						                	 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)" >	
						                  
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)" >
						                
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div> 
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Personal No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="9" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="name" name="name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()">
										   
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Date
										From </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="from_date" id="from_date"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 90%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Date
										To </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="to_date" id="to_date" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 90%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="status" class="form-control-sm form-control"   >
													<option value="0">Success</option>
												    <option value="-1">Failure</option>	
<!-- 													<option value="1">Approved</option> -->
													
											</select>
										</div>
						            </div>
	              				</div>	       
	              				
	              				
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Authority </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<input type="text" id="auth" name="auth"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" 
										oninput="this.value = this.value.toUpperCase()">
										</div>
						            </div>
	              				</div>	
	              				      					              				
	              			</div>		
				</div>						
					
						<div class="card-footer" align="center" class="form-control">
							<a href="xml_upload_logs" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Serarch"> 

			            </div> 		
	        	</div>
			
	</div>

	</form:form>


	<div class="datatablediv">
		<div class="col-md-12" id="getSearch_Letter_a" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				
				
				
				<table id="getSearch_uploadxmltbl"
					class="table no-margin table-striped  table-hover  table-bordered ">
<!-- <div class="select-position"><input type="checkbox" onclick="setAllChecked()"  id="checked_box_adfcvgbh"></div> -->
					<thead>
						<tr>
<!-- 							<th style="text-align: center;" id="select_all"> Select All</th> -->
							<th style="text-align: center;" id="cadet_no">Ser No</th>
							<th style="text-align: center;" id="file_name">File Name</th>
							<th style="text-align: center;" id="part_2no">Part II Order No</th>
							<th style="text-align: center;" id="personnel_no">Army No </th>
							<th style="text-align: center;" id="unitsus_no">Sus No </th>
							<th style="text-align: center;" id="name">Name</th>
							<th style="text-align: center;" id="uploaded_on">Uploaded On</th>
							<th style="text-align: center;" id="upload_status">Upload Status</th>
							<th style="text-align: center;" id="error_msg">Error Message</th>
							<th style="text-align: center;" id="action"> Action</th>
						</tr>
					</thead>
				</table>

			</div>
<!-- 			<div class="card-footer" align="center" class="form-control"> -->

<!-- 		              		<input type="button" class="btn btn-success btn-sm"  id="approve" onclick="Approve();" value="Submit For Approval">  -->

<!-- 			            </div> 		 -->
		</div>

	</div>
	<input type="hidden" name="selected_comm_id" id="selected_comm_id" value="0">
	
	<c:url value="Approve_UpadteComm_DataUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm1" name="ViewForm1" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="personnel_no2" id="personnel_no2" value="0">
</form:form>
	<c:url value="Approve_UpadteOfficerDataUrl_xml" var="ViewUrl"/>
		<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id3">
	  <input type="hidden" name="personnel_no1" id="personnel_no1" value="0">
	   <input type="hidden" name="comm_id1" id="comm_id1" value="0">
	   <input type="hidden" name="event1" id="event1" value="0"/>
</form:form>
<c:url value="Edit_UpadteComm_DataUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="eid2">
  	<input type="hidden" name="eid2" id="eid2" value="0">
 	<input type="hidden" name="epersonnel_no2" id="epersonnel_no2" value="0">
</form:form>
<%-- <c:url value="Update_Off_DataUrla" var="editUrl"/> --%>
<%-- 	<form:form action="${editUrl}" method="post" id="updateForm1" name="updateForm1" modelAttribute="id2"> --%>
<!-- 	  	<input type="hidden" name="id_edit" id="id_edit" value="0"> -->
<!-- 	  	<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0"> -->
<!-- 	  	<input type="hidden" name="status" id="status" value="0"> -->
<!-- 	  <input type="hidden" name="personnel_no6" id="personnel_no6" value="0"> -->
<!-- 	  <input type="hidden" name="status6" id="status6" value="0"> -->
<!-- 	  <input type="hidden" name="rank6" id="rank6" value="0"> -->
<!-- 	  <input type="hidden" name="comm_id6" id="comm_id6" value="0"> -->
<!-- 	  <input type="hidden" name="unit_name6" id="unit_name6" value="0"> -->
<!-- 	  <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0"> -->
<%-- 	</form:form> --%>
	<c:url value="XML_Update_Data" var="editUrl"/>
	<form:form action="${editUrl}" method="post" id="updateFormXML" name="updateFormXML" modelAttribute="id2">
	  	<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0">
	  	 	<input type="hidden" name="census_id_edit" id="census_id_edit" value="0">
	  <input type="hidden" name="comm_id_edit" id="comm_id_edit" value="0">
	</form:form>
	
	<c:url value="Approved_xml_data_view" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="viewapproedForm"
	name="viewapproedForm" modelAttribute="cont_comd4" > 
	<input type="hidden" name="comm_id_view" id="comm_id_view" />	
	<input type="hidden" name="date_modified" id="date_modified" />	
	<input type="hidden" name="personnel_no5" id="personnel_no5" />	
</form:form> 
		 	<c:url value="casuality_xml_data_view_pop_up" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="casualtyForm"
	name="casualtyForm" modelAttribute="cont_comd4" target="result"> 
	<input type="hidden" name="comm_id_casualty" id="comm_id_casualty" />	
	<input type="hidden" name="upload_id_casualty" id="upload_id_casualty" />		
</form:form> 
	

	<script>
		$(document).ready(function() {
			$.ajaxSetup({
				async : false
			});
	
		jQuery(function($){ 
			datepicketDate('from_date');
			datepicketDate('to_date');
		});
		
		mockjax1('getSearch_uploadxmltbl');
		table = dataTable('getSearch_uploadxmltbl');
		colAdj("getSearch_uploadxmltbl");
		var selectAllTh = $("#select_all");
	    if (selectAllTh.length > 0) {	    
	        selectAllTh.removeClass('sorting');
	    }
		
});
		
		
 	function	Search(){
 		
 		 var army=$("#personnel_no").val();
		   var name=$("#name").val();
		   var f_date  = $("#from_date").val();
			 f_date1 =f_date.substring(6,10);
			 var t_date  = $("#to_date").val();
			  t_date1 =t_date.substring(6,10);
				var susno=$("#unit_sus_no").val();
				var unitname=$("#unit_name").val();
				
			  if (susno!= "" && unitname=="") {
					alert("Please Enter Unit Name");
					$("input#unit_name").focus();
					return false;
				}
			  if ( unitname!= "" &&susno =="") {
					alert("Please Enter Sus No");
					$("input#unit_sus_no").focus();
					return false;
				}
			  
if ($("input#from_date").val() != "" && $("#from_date").val() == "DD/MM/YYYY") {
		alert("Please Select  From Date ");
		$("input#from_date").focus();
		return false;
	} 
if ($("input#to_date").val() != "" && $("#to_date").val() == "DD/MM/YYYY") {
	alert("Please Select  To Date ");
	$("input#to_date").focus();
	return false;
}
  if ( f_date != ""&&t_date1=="") {
				alert("To Date can not Empty ");
				$("#to_date").focus();
		 		return false;
			}
  if ( t_date1 != ""&&f_date=="") {
		alert("From Date can not Empty ");
		$("#from_date").focus();
		return false;
	}
  if(getformatedDate(f_date) > getformatedDate(t_date)) {
		alert("To Date can not be less than From Date");
		$("#to_date").focus();
		return false;
	}
		
		else {
	table.ajax.reload();
		}
 		
		}
	
 	$("#status").change(function() {
 	
 		var a=$("#status").val();
 	    if (a!="0") {
 	        $("#approve").hide();
 	       $(".select-position").hide();
 	    }
 	    else{
 	    	  $("#approve").show();
 	    	 $(".select-position").show();
 	    }
 	});

 	
 	
 	
 	function ViewData(comm_id,personnel_no){	

 		$.post("getcensus_id?" + key + "=" + value, {
 			comm_id:comm_id
		}, function(j) {
			
// 			if(j=="0"){
// 	 			$("#id2").val(comm_id);
// 	 	 		$("#personnel_no2").val(personnel_no);
// 	 		document.getElementById('ViewForm1').submit();
// 	 		}else{
	 			$("#comm_id1").val(comm_id);
	 	 		$("#personnel_no1").val(personnel_no);
	 			document.getElementById('ViewForm').submit();
	 			colAdj("getSearch_uploadxmltbl");
	 		//}
			
		});
 	}  
 	
 	function editData(comm_id,personnel_no){		
 		$.post("getcensus_id?" + key + "=" + value, {
 			comm_id:comm_id
		}, function(j) {
			
// 			if(j=="0"){
// 				$("#eid2").val(comm_id);
// 		 		$("#epersonnel_no2").val(personnel_no);
// 		 		 document.getElementById('updateForm').submit();
// 	 		}else{
	 			$("#comm_id_edit").val(comm_id);
	 			$("#census_id_edit").val(j);
	 	 		$("#personnel_no_edit").val(personnel_no);
	 			document.getElementById('updateFormXML').submit();
	 			colAdj("getSearch_uploadxmltbl");
// 	 		}
			
		});
 		

 	
 	}
 	
 	
	function data(tableName) {
		jsondata = [];

		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];
		   var army=$("#personnel_no").val();
		   var name=$("#name").val();
		   var frm_dt1 =	$("#from_date").val();
			var to_dt1 =	$("#to_date").val();
			var status=$("#status").val(); 
			var present_p2_no=$("#auth").val(); 
			var susno=$("#unit_sus_no").val();
			var unitname=$("#unit_name").val();
			var selected_id="";
		if (tableName == "getSearch_uploadxmltbl" ) {

			$.post("getUploadedFiles_count?" + key + "=" + value, {
				Search : Search,
				army : army,
				name : name,
				frm_dt1:frm_dt1,
				to_dt1:to_dt1,
				status:status,
				present_p2_no:present_p2_no,selected_id:selected_id,susno:susno,unitname:unitname
				
			}, function(j) {
				FilteredRecords = j;
				
			});
			$.post("getUploadedFiles?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				army : army,
				name : name,
				frm_dt1:frm_dt1,to_dt1:to_dt1,status:status,present_p2_no:present_p2_no,selected_id:selected_id,susno:susno,unitname:unitname
			}, function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					if(status==0)
						{
// 						jsondata.push([ '<input type="checkbox" class="setcheckedasas" onclick="checked_uchecked()"  id ="checkbox_approve_'+i+' " value="'+j[i].comm_id+'" >',sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
// 							 j[i].error_msg,j[i].action]);
// 						table.column(0).visible(true);
// 							table.column(9).visible(true);
							jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].sus_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
								 j[i].error_msg,j[i].action]);
							table.column(0).visible(true);
								table.column(8).visible(true);
						}
					
					else if(status==1)
					{
					jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].sus_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
						 j[i].error_msg,j[i].action]);
						table.column(8).visible(true);
						table.column(0).visible(false);
					}
					
					
					
					
					else{
						table.column(0).visible(false);
					table.column(8).visible(false);
						jsondata.push([sr, j[i].file_name,j[i].present_p2_no, j[i].army_no,j[i].name, j[i].uploaded_on, j[i].uploaded_status,
							 j[i].error_msg,'']);
						
					}
				
							
					

				}
				
			});
		}
	
	}

	function Approve()
	{
		if (confirm('Are You Sure You Want to Approve Officer Data?')){
			findselectedcomm_id();
			var comm_id=$("#selected_comm_id").val();
			if(comm_id=="")
				{
				alert("Please Select Data To Approve.");
				return false;
				}
			else{
				$("#WaitLoader").show();
				  
				$.post("Approve_officer_DataAction_xml_multiple?" + key + "=" + value, {
					comm_id:comm_id
				}, function(j) {				
					$("#WaitLoader").show();
					alert(j);
				});
				
				table.ajax.reload();
				
			}
			
			}else{
				return false;
				}
		
	}
	
	var popupWindow="";
function 	AppViewData(comm_id,date,army_no)
{
	
	$("#comm_id_view").val(comm_id);
	$("#date_modified").val(date);
	$("#personnel_no5").val(army_no);
document.getElementById('viewapproedForm').submit();
		}	
		
var popupWindow=null;
function edit_new_Data(comm_id,upload_id)
{

	$("#comm_id_casualty").val(comm_id);
	$("#upload_id_casualty").val(upload_id);

	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");		
		window.onfocus = function () { 
		}
 		document.getElementById('casualtyForm').submit();
 	
}
	
	
		else
			{
			popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		
			window.onfocus = function () { 
			}
			document.getElementById('casualtyForm').submit();
			
			}
	
}
	
	function findselectedcomm_id() {
	
	    var nrSel = $("#getSearch_uploadxmltbl tbody input:checkbox:checked").map(function() {
	        return $(this).val();
	    }).get();
	    var b = nrSel.join('|');
	    $("#selected_comm_id").val(b);
	}

	
	
	
	
$("input#personnel_no").keyup(function(){

		var personel_no = this.value;
			 var susNoAuto=$("#personnel_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "get_comm_personnelnoList?"+key+"="+value,
			        data: {personel_no:personel_no},
			          success: function( data ) {
			   
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        	   	          
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Personal No");
			        	  document.getElementById("personnel_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	
			      } 	     
			}); 			
	});
	
	
	
function onlyAlphabets(e, t) {
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
}
function setAllChecked() {    
	
    if ($("#checked_box_adfcvgbh").prop("checked")) {
		$(".setcheckedasas").prop('checked', true);
	} else {
		$(".setcheckedasas").prop('checked', false);
	}
    var selectAllTh = $("#select_all");
//     if (selectAllTh.length > 0) {	    
//         selectAllTh.removeClass('sorting');
//     }
    
}
		
		function checked_uchecked() {		
		    var selectAllCheckbox = document.getElementById('checked_box');
		    selectAllCheckbox.checked=false;
		}
		
		
		$("#unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var susNoAuto=$("#unit_sus_no");

			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
		                  var length = data.length-1;
		                  var enc = data[length].substring(0,16);
		                        for(var i = 0;i<data.length;i++){
		                                susval.push(dec(enc,data[i]));
		                        }
		                        var dataCountry1 = susval.join("|");
		                        var myResponse = []; 
						        var autoTextVal=susNoAuto.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
				  minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit SUS No.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#unit_name").val(dec(enc,j[0]));   
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
				var unit_name = this.value;
					 var susNoAuto=$("#unit_name");
					  susNoAuto.autocomplete({
					      source: function( request, response ) {
					        $.ajax({
					        	type: 'POST',
						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
					        data: {unit_name:unit_name},
					          success: function( data ) {
					        	 var susval = [];
					        	  var length = data.length-1;
					        	  var enc = data[length].substring(0,16);
						        	for(var i = 0;i<data.length;i++){
						        		susval.push(dec(enc,data[i]));
						        	}
						        	   	          
								response( susval ); 
					          }
					        });
					      },
					      minLength: 1,
					      autoFill: true,
					      change: function(event, ui) {
					    	 if (ui.item) {   	        	  
					        	  return true;    	            
					          } else {
					        	  alert("Please Enter Approved Unit Name.");
					        	  document.getElementById("unit_name").value="";
					        	  susNoAuto.val("");	        	  
					        	  susNoAuto.focus();
					        	  return false;	             
					          }   	         
					      }, 
					      select: function( event, ui ) {
					    	 var unit_name = ui.item.value;
					    
						            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#unit_sus_no").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
					}); 			
			});
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>