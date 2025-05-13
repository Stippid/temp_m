<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script src="js/exportExcelData/modernizr-2.8.3.js"></script>
<script src="js/exportExcelData/jquery-1.11.3.min.js"></script>
<script src="js/exportExcelData/jquery-ui.min.js"></script>
<script src="js/excel/xlsx-0.7.7.core.min.js"></script>
<script src="js/excel/xls-0.7.4.core.min.js"></script>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>  -->
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript"
	src="js/exportExcelDafunction kmcheck(a,b,c){
	return true;
}ta/infragistics.documents.core_core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript"
	src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>
<!-- <link rel="stylesheet" href="js/common/nrcss.css"> -->

<style type="text/css">
.td-img-modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.7);
	z-index: 9999;
	text-align: center;
	background: #0000008;
}

.td-img-modal .modal-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	max-width: 70%;
	max-height: 70%;
}

.zoom-icon {
	position: relative;
}

.td-img-modal button.close {
	position: absolute;
	right: 10px;
	top: 10px;
	background: red;
	color: white;
	height: 40px;
	width: 40px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50%;
	font-size: 30px;
}

.expandable-column {
  white-space: nowrap; /* Prevent wrapping */
  width: auto; /* Auto width based on content */
    flex: 2; /* This column will expand more */
}

.table-container {
  display: flex;
  flex-direction: column;
}

.table-row {
  display: flex;
  width: 100%;
}

.table-cell {
  flex: 1; /* Default column width */
  padding: 8px;
}

</style>

<style type="text/css">
table.dataTable, table.dataTable th, table.dataTable td {
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	/* width: 80px; */
	text-align: center;
}

.dataTables_scrollHead {
	overflow-y: scroll !important;
	overflow-x: hidden !important;
}

.DataTables_sort_wrapper {
	/* width : 80px; */
	
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

.print_btn {
	margin: 0 auto;
}

.print_btn input {
	background-color: #9c27b0;
	border-color: #9c27b0;
}

.print_btn input:hover {
	background-color: #cb5adf;
	border-color: #cb5adf;
}
</style>
<div class="animated fadeIn">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						Daily Aviation Casualty Return<br>
					</h5>
					<h6>
						<span style="font-size: 12px; color: red">(To be entered by
							Unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" value='${sus_no}' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 11px;"
										autocomplete="off" maxlength="100">${unit_name}</textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>


				<div class="form-control card-footer" align="center" id="searchBTN">
					<input type="button" class="btn btn-primary btn-sm" value="Search"
						onclick="submitSearch();">
				</div>

				<div></div>

				<div class="animated fadeIn">
					<div class="col-md-12" align="center" id="dacr">
						<div id="divShow" style="display: block;"></div>
						<div class="watermarked" data-watermark="" id="divwatermark"
							style="display: block;">
							<span id="ip"></span>
							<table id="SearchReport" style="width:100%;" border="1"
								class="table no-margin table-striped table-hover table-bordered table-container table-row table-cell report_print">
								<thead>
									<tr>
										<th  rowspan="2" style="width: 4%;">Sr No</th>
										<th  rowspan="2" style="width: 10%;">AC No</th>
										<th  rowspan="2" style="width: 10%;">Status</th>
										<th  colspan="2" style="width: 25%;">Daily Flg Hrs as on</th>
										<th  rowspan="2" style="width: 10%;">G/Run</th>
										<th  rowspan="2" style="width: 10%;">AF Hrs</th>
										<th  colspan="2" style="width: 25%;">ENG HRS</th>
										<th  rowspan="2" style="width: 6%;">HRS Left</th>
										<th  rowspan="2" style="width: 6%;">Next INSP</th>
										<th  colspan="4" style="width: 25%;">Prog Hrs Flown</th>
										<th  rowspan="2" style="width: 6%;">Remarks</th>
										<th  rowspan="2" style="width: 6%;">Balance Ah Eng Hrs</th>
										<th  rowspan="2" style="width: 6%;">DT Last Flow</th>
										<th  rowspan="2" style="width: 5%;">Action</th>
									</tr>
									<tr>
										<th  style="width: 12%;">Day</th>
										<th  style="width: 12%;">Night</th>
										<th  style="width: 12%;">LH</th>
										<th  style="width: 12%;">RH</th>
										<th  style="width: 5%;">Monthly</th>
										<th  style="width: 5%;">Qtrly</th>
										<th  style="width: 5%;">H/Yrly</th>
										<th  style="width: 5%;">Yrly</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${list.size() == 0}">
										<tr>
											<td colspan="17" align="center" style="color: red;">Data
												Not Available</td>
										</tr>
									</c:if>
									<c:forEach items="${list}" var="item" varStatus="num">
										<tr>
											<td style="width: 3.5%;">${num.index + 1}</td>
											<td>${item[1]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											<td>${item[4]}</td>
											<td>${item[5]}</td>
											<td >${item[6]}</td>
											<td>${item[7]}</td>
											<td >${item[8]}</td>
											<td >${item[9]}</td>
											<td >${item[10]}</td>
											<td >${item[11]}</td>
											<td >${item[12]}</td>
											<td >${item[13]}</td>
											<td >${item[14]}</td>
											<td >${item[15]}</td>
											<td >${item[16]}</td>
											<td >${item[17]}</td>
											<td  style="width: 4%;">${item[18]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:url value="UpdategetDACRReportList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0" />
	<input type="hidden" name="unit_name2" id="unit_name2" value="0" />
</form:form>

<c:url value="dacrSetSession" var="reloadUrl" />
<form:form action="${reloadUrl}" method="post" id="reloadReport"
	name="reloadReport">
	<input type="hidden" name="sus_no1" id="sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
</form:form>
<script>
document.addEventListener('DOMContentLoaded', function() {
    window.submitSearch = function() {
        const susNo = document.getElementById('sus_no').value;
        const unitName = document.getElementById('unit_name').value;

        document.getElementById('sus_no2').value = susNo;
        document.getElementById('unit_name2').value = unitName;
        
        document.getElementById('searchForm').submit();
    };
});

$(document).ready(function() {
	var sus_no = '${sus_no}';
	if(sus_no == "")
	{
		jQuery("div#dacr").hide();
	}
	else
	{
		jQuery("div#dacr").show();
	}

});

</script>

<script>
function submitData(acc_no, j) {
	
	 var status = jQuery("#status_" + j).val();
	 var falfHrsDay = jQuery("#falf_hrs_day_" + j).val();
	 var falfHrsNight = jQuery("#falf_hrs_night_" + j).val();
	 var gRun = jQuery("#g_run_" + j).val();
	 var afHrs = jQuery("#af_hrs_" + j).val();
	 var engHrsLeft = jQuery("#eng_hrs_left_" + j).val();
	 var engHrsRigth = jQuery("#eng_hrs_rigth_" + j).val();
	 var hrsLeft = jQuery("#hrs_left_" + j).val();
	 var nextInsp = jQuery("#next_insp_" + j).val();
	 var hrsMonthly = jQuery("#hrs_monthly_" + j).val();
	 var hrsQtrly = jQuery("#hrs_qtrly_" + j).val();
	 var hrsHalfYear = jQuery("#hrs_half_year_" + j).val();
	 var hrsQtrlyFlow = jQuery("#hrs_qtrly_flow_" + j).val();
	 var remarks = jQuery("#remarks_" + j).val();
	 var balHrs = jQuery("#bal_hrs_" + j).val();

	var sus_no = "";
	if('${sus_no}' == ""){
		sus_no = jQuery("#sus_no").val();
	}else{
		sus_no = '${sus_no}';
	}	
	
	jQuery.ajax({
		type : 'POST',
		url : "updateDacr?"+key+"="+value,
		data : {
			sus_no : sus_no,
			acc_no : acc_no,
			status : status,
			 falfHrsDay: falfHrsDay,  // Added these to the data if necessary
	            falfHrsNight: falfHrsNight,
	            gRun: gRun,
	            afHrs: afHrs,
	            engHrsLeft: engHrsLeft,
	            engHrsRigth: engHrsRigth,
	            hrsLeft: hrsLeft,
	            nextInsp: nextInsp,
	            hrsMonthly: hrsMonthly,
	            hrsQtrly: hrsQtrly,
	            hrsHalfYear: hrsHalfYear,
	            hrsQtrlyFlow: hrsQtrlyFlow,
	            remarks: remarks,
	            balHrs: balHrs
		},
		success : function(response) {
			if(response == "Data Updated Successfully.")
			{
				alert("Data Updated Successfully.");
			}else if (response == "Data already exists"){
				alert("Data already exists.");
			}
			else{
				alert(""+response+" \n Data Not Updated.");
			}
		}
	});
	return true;
}
</script>
<script>
jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	var sus_no = ui.item.value;
			    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			    		var length = j.length-1;
					   	var enc = j[length].substring(0,16);
					   	jQuery("#unit_name").val(dec(enc,j[0]));	
			    	}).fail(function(xhr, textStatus, errorThrown) {
			    });
			} 	     
		});	
	});
	// End
	
	// Source Unit Name
    jQuery("#unit_name").keyup(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
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
						var target_unit_name = ui.item.value;
					 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
					 		 var length = j.length-1;
	 				         var enc = j[length].substring(0,16);
	 				         jQuery("#sus_no").val(dec(enc,j[0]));	
						}).fail(function(xhr, textStatus, errorThrown) {
					});
				} 	     
			}); 			
 		});
	});
</script>