<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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
</style>

<div class="animated fadeIn">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						Monthly Vehicle Casualty Return<br>
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
					<input type="submit" class="btn btn-primary btn-sm" value="Search"
						onclick="return Search();">
				</div>

				<div class="card-body card-block" id="mvcr" style="">
					<div class="col-md-12" style="background-color: #f0f3f5;">

						<div class="col-md-6">

							<form method="POST" enctype="multipart/form-data"
								id="exportExcelData" name="exportExcelData"
								action="exportTMSExcelActionMVCR?${_csrf.parameterName}=${_csrf.token}">
								<label style="color: #000000;">Download data in Excel:</label> <a
									href="#" title="Download Uploaded Documents"
									onclick="getExcel();"> <i
									class="action_icons action_download"></i></a> <br> <label
									style="color: #000000;">Upload Excel:</label>
								<div style="display: flex; align-items: center;">
									<input type="file" style="width: 50%;" id="file_browser"
										name="file_browser"
										accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
										class="form-control-sm form-control" /> <input type="submit"
										class="btn btn-success btn-sm" value="Submit"
										onclick="return validate()">
								</div>
								<input type="hidden" id="count" name="count"> <input
									type="hidden" id="countrow" name="countrow"> 
									<input type="hidden"  id="numberOfColumns" name="numberOfColumns">
									<input type="hidden"  id="numberOfRows" name="numberOfRows">
									<input type="hidden"  id="ColumnNameExists" name="ColumnNameExists">
									<input type="hidden"  id="sus_no3" name="sus_no3" value="${sus_no}">
									
									
									<input
									type="hidden" id="table_name" name="table_name"
									value="tb_tms_mvcr_parta_dtl">

								<div class="card-body card-block">
									<div id="result"></div>
									<div>
										<table id="Report"
											style="display: none; width: 100%; font-size: 12px;"></table>
									</div>
								</div>
							</form>


						</div>
						<div class="col-md-4">

							<c:if test="${roleAccess == 'Unit'}">
								<div>
									<h5 style="text-align: center;">Raise Ticket</h5>
									<h6 style="color: red" align="center">(In Case of
										Observation)</h6>
								</div>
								<div class="row form-group">

									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Report
											Obsn </label>
									</div>
									<div class="col-12 col-md-8">
										<!-- <select id="report_obsn" name="report_obsn"
										class="form-control">
										               				  <option value="">--Select--</option>
																	  <option value="1">Feedback</option>
																	  <option value="2">General Inquiry</option>
																	  <option value="3">Report a problem</option>
																	  <option value="4">Report a problem/Access Issues</option>
																	  <option value="5">Feature Request</option>
										<option value="">--Select--</option>
										<option value="6">Km Amdt</option>
										<option value="7">Cl Amdt</option>
										<option value="8">Nomenclature Amdt</option>
										<option value="9">Addn of Veh</option>
										<option value="10">Engine & Chassis Updation</option>
										<option value="11">Veh Deposition</option>
										<option value="12">Handing Taking Over</option>
										<option value="13">IUT</option>
										<option value="14">Report Issues</option>
									</select> -->
										<select id="report_obsn" name="report_obsn"
											class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${GetHelpTopic}" varStatus="num">
												<option value="${item.codevalue}" name="${item.label}">${item.label}
												<option>
											</c:forEach>
										</select>

									</div>
								</div>
							</c:if>
							<c:if test="${roleAccess == 'Unit'}">
								Document Upload : <span style="color: red;">(*.zip, *.rar
									only)</span>
							</c:if>
							<div>
								<form method="POST" enctype="multipart/form-data"
									id="fileUploadForm">
									<input type="hidden" id="sus" name="sus" value="${sus_no}" />
									<input type="hidden" value="0" id="hdcount" name="hdcount" />
									<c:if test="${roleAccess == 'Unit'}">
										<input type="file" id="uploadMvcr" name="uploadMvcr"
											title="MVCR Upload .ZIP ,.RAR only" />
										<br>
										<br>
										<input type="submit" class="btn btn-primary btn-sm"
											value="Upload" id="btnSubmit" data-toggle='modal'>
										&emsp;
									</c:if>
									<a hreF="#" data-target='#rejectModal' data-toggle='modal'
										onclick="return getDownloadImageMVCR()"
										class="btn btn-primary btn-sm">View Uploaded Doc</a>
								</form>
							</div>
						</div>




						<div class="col-md-6">
							<div align="left">
								Total Vehicles : <label id="totalVeh">${TOTAL}</label>
							</div>
							<div align="left">
								Updated : <label id="totalUp">${UPDATE}</label>
							</div>
							<div align="left">
								Not Updated : <label id="totalNUp">${NotUpdated}</label>
							</div>
						</div>
					</div>
				</div>




				<div class="animated fadeIn">
					<div class="col-md-12" align="center" id="mvcr">
						<div id="divShow" style="display: block;"></div>
						<div class="watermarked" data-watermark="" id="divwatermark"
							style="display: block;">
							<span id="ip"></span>
							<table id="SearchReport"
								class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead>
									<tr>
										<th style="text-align: center; width: 5%;">Ser No</th>
										<th style="text-align: center; width: 10%;">BA No</th>
										<th style="text-align: center; width: 7%;">Previous KM</th>
										<th style="text-align: center; width: 7%;">Present KM</th>
										<th style="text-align: center; width: 10%;">Cl</th>
										<th style="text-align: center; width: 14%;">Servicebality
											State</th>
										<th style="text-align: center; width: 40%;">Remarks</th>
										<th style="text-align: center; width: 7%;">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${list.size() == 0}">
										<tr>
											<td colspan="7" align="center" style="color: red;">Data
												Not Available</td>
										</tr>
									</c:if>
									<c:forEach items="${list}" var="list" varStatus="num">
										<tr>
											<td style="text-align: center; width: 5%;">${num.index+1}</td>
											<td style="text-align: center; width: 10%;">${list[1]}</td>
											<td style="text-align: center; width: 7%;">${list[6]}</td>
											<td style="text-align: center; width: 7%;">${list[7]}</td>
											<td style="text-align: center; width: 10%;">${list[8]}</td>
											<td style="text-align: center; width: 14%;">${list[16]}</td>
											<td style="text-align: center; width: 40%;">${list[9]}</td>
											<td style="text-align: center; width: 7%;">${list[11]}</td>
											<td style='display: none;'>${list[13]}</td>
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

<!-- model open -->
<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog"
	aria-labelledby="rejectModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">SUS NO wise Documents</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<!-- Modal body -->
			<div class="modal-body">
				<div class="form-group">
					<div class="row"
						style="color: maroon; font-size: 16px; font-weight: bold;">
						<table
							class="table no-margin table-striped  table-hover  table-bordered report_print"
							id="getImgDoc">
							<thead>
								<tr>
									<th width="80%">Document Name</th>
									<th width="20%" id="mvcr_doc_download">Action</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="td-myModal" tabindex="-1" role="dialog"
	aria-labelledby="rejectModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">New Ticket Remark</h4>
				<!-- 				<button type="button" id ="close_div" class="close" data-dismiss="modal">&times;</button> -->
			</div>

			<div class="modal-body">
				<div class="form-group">
					<div class="col-md-12">
						<div class="row"
							style="color: maroon; font-size: 16px; font-weight: bold;">
							<div class="col-md-12 form-group">
								<div class="col-2 col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Issue summary </label>
								</div>
								<div class="col-10 col-md-10">
									<input type="text" style="width: 680;" id="issue_summary"
										name="issue_summary" placeholder="Maximum 100 characters"
										class="form-control char-counter" autocomplete="off"
										maxlength="100"></input>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-2 col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">*</strong> Description<br> <span
										style="font-size: 10px; font-size: 12px; color: red">(Max
											1000 words)</span></label>
								</div>
								<div class="col-10 col-md-10">
									<textarea rows="2" cols="250" id="description"
										style="height: 150px;" name="description"
										class="form-control char-counter1" autocomplete="off"
										maxlength="1000"></textarea>
								</div>
							</div>

						</div>
					</div>

				</div>

				<div align="center">
					<button type="button" name="submit"
						class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
					<button type="reset" name="reset" class="btn btn-primary login-btn"
						onclick="cleardata();">Reset</button>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="close_div" class="btn btn-danger"
					data-dismiss="modal" onclick="$('#rrr').attr('data-target','#')">Close</button>
			</div>
		</div>
	</div>
</div>
<c:url value="UpdategetMVCRReportList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0" />
	<input type="hidden" name="unit_name2" id="unit_name2" value="0" />
</form:form>

<c:url value="mvcrSetSession" var="reloadUrl" />
<form:form action="${reloadUrl}" method="post" id="reloadReport"
	name="reloadReport">
	<input type="hidden" name="sus_no1" id="sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
</form:form>
<script>

function validate(){
	if($("#file_browser").val() == ""){
		alert("Please Upload the File.");
		$("#file_browser").focus();
		return false;
	}
	if($("#table_name").val() == "0"){
		alert("Please Select the table.");
		$("#table_name").focus();
		return false;
	}
	var filename = $("#file_browser").val();
	var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);           	        
        fl = filename.split('.');
        var str1 = fl[0];
        var str =  str1.substring(0, 4);
        var str2 = $("#table_name").val();
        if (str === new Date().getFullYear().toString() && str2 == "tb_tms_mvcr_parta_dtl"){
        	return true;
        }else{
        	alert("Please Enter Valid File and Valid Table Name.");
            return false;
        }
    }
}



$(function() {$("input#file_browser").on("change",function() {
	$("#Report").find("tbody").empty();

	var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
	var regex_low = $("#file_browser").val().toLowerCase();
	if (regex.test(regex_low)) {
		var xlsxflag = false;
		if ($("#file_browser").val().toLowerCase().indexOf(".xlsx|.xls") > 0) {
			xlsxflag = true;
		}
		if (typeof (FileReader) != "undefined") {
			var reader = new FileReader();
			reader.onload = function(e) {
				var data = e.target.result;
				if (xlsxflag) {
					var workbook = XLSX.read(data, 
					{
						type : 'binary'
					});
				}
				else {
					var workbook = XLS.read(data, {
						type : 'binary'
					});
				}
				var sheet_name_list = workbook.SheetNames;
				var cnt = 0;
				const sheetName = workbook.SheetNames[0];
				const worksheet = workbook.Sheets[sheetName];
				const range = XLSX.utils.decode_range(worksheet['!ref']);
				const numberOfColumns = range.e.c + 1;
				$("#numberOfColumns").val(numberOfColumns);
				const numberOfRows = range.e.r;
				alert("Total BA No.: " + numberOfRows);

				validateColumnName(workbook,'BA No');

				function validateColumnName(workbook, targetColumnName) {
					const sheetName = workbook.SheetNames[0];
					const worksheet = workbook.Sheets[sheetName];
					const headerRow = XLSX.utils.sheet_to_json(worksheet, { header: 1 })[0];
					$("#ColumnNameExists").val(headerRow.includes(targetColumnName));
				}
				
				sheet_name_list
						.forEach(function(y) {
							if (xlsxflag) {
								var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);
							} 
							else {
								var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);
							}
							if (exceljson.length > 0 && cnt == 0) {
								BindTable(exceljson,'#Report');
								$("#countrow").val(exceljson.length);
								cnt++;
							}
						});
				$("div#importshow").hide();
			}
			if (xlsxflag) {
				reader.readAsArrayBuffer($("#file_browser")[0].files[0]);

			} else {
				reader.readAsBinaryString($("#file_browser")[0].files[0]);
			}
		} else {
			alert("Sorry! Your browser does not support HTML5!");
		}
	} else {
		alert("Please upload a valid Excel file!");
	}
	})
});



function BindTable(jsondata, tableid) {/*Function used to convert the JSON array to Html Table*/
	var columns = BindTableHeader(jsondata, tableid); /*Gets all the column headings of Excel*/
	for (var i = 0; i < jsondata.length; i++) {
		var td = "<tbody><tr>";
		for (var colIndex = 0; colIndex < columns.length; colIndex++) {
			var cellValue = jsondata[i][columns[colIndex]];
			var TH1 = columns[colIndex];
			td += "<td style='text-align:center;width: 10%'><input name='"+TH1+"_"+i+"' id='"+TH1+"_"+i+"' value='"+cellValue+"' style='text-align:center;' readonly='readonly' /></td>";
			if (cellValue == null)
				cellValue = "";
		}
		td += "</tbody>";
		$(tableid).append(td);
	}
}

function BindTableHeader(jsondata, tableid) {/*Function used to get all column names from JSON and bind the html table header*/
	var columnSet = [];
	var headerTr$ = $('<tr/>');
	for (var i = 0; i < jsondata.length; i++) {
		var rowHash = jsondata[i];
		for ( var key in rowHash) {
			if (rowHash.hasOwnProperty(key)) {
				if ($.inArray(key, columnSet) == -1) {/*Adding each unique column names to a variable array*/
					columnSet.push(key);
					//  alert(key + "key");
					headerTr$.append($('<th/>').html(key));
				}
			}
		}
	}
	$(tableid).append(headerTr$);
	return columnSet;
}


function getExcel() {	
	var sus_no=$("#sus_no").val();
 	var unit_name=$("#unit_name").val();
	$("#sus_noex").val(sus_no);
	$("#unit_nameex").val(unit_name);
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
}



jQuery(document).ready(function() {
	//jQuery("div#divwatermark").val('').addClass('watermarked'); 
	//watermarkreport();
	if('${status}' == "1")
	{
		jQuery("button#printId").attr('disabled',false);
	}
	
	if('${list.size()}' != "0" ){
		jQuery("#divPrint").show();
	}else{
		jQuery("#divPrint").hide();
	}
	
	if('${roleAccess}' == "Unit"){
		jQuery("#sus_no").attr('disabled','disabled');
		jQuery("#unit_name").attr('disabled','disabled');
		jQuery("div#searchBTN").hide();
		jQuery("td#mvcr_doc_download").hide();
	}
});	

function ChangeClassification(ba_no,id)
{
	var classification = jQuery('select#classification'+id).val();
	if(classification > 4)
	{
		jQuery("div#" + ba_no).show();
	}
	else
	{
		jQuery("div#" + ba_no).hide();
	}
}
function ChangeSer_Status(ba_no,id,value)
{
	if(value == '1'){
		jQuery("div#ser_reason"+ba_no).show();
	}else{
		jQuery("div#ser_reason"+ba_no).hide();
		jQuery("select#ser_reason"+id).val("0");
	}
}
function Search(){
    jQuery("#sus_no2").val(jQuery("#sus_no").val()) ;
    jQuery("#unit_name2").val(jQuery("#unit_name").val()) ;
    if(jQuery("#sus_no").val() == "")
    {
    	alert("Please Enter SUS No.");
    }
    else
    {
    	if('${roleAccess}' != "Unit"){
    		document.getElementById('searchForm').submit();
    	}else{
    		alert("You are not Authorised");
    	}
    }
}
jQuery(document).ready(function() {
	var unit_name = jQuery("#unit_name").val();
	${con}
	if('${roleAccess}' == 'Unit'){
		var file = document.getElementById('uploadMvcr');
		file.onchange = function(e) {
		  	var ext = this.value.match(/\.([^\.]+)$/)[1];
		  	var FileSize = file.files[0].size / 1024 / 1024; // in MB
		  	if (FileSize > 2) {
	           alert('File size exceeds 2 MB');
	           this.value = '';
	        } else {
	        	switch (ext) {
				  	case 'rar':
				  	case 'zip':
				 	alert('Allowed');
				    break;
				  	default:
				    	alert('Please Only Allowed *.zip , *.rar File Extensions.');
				   	this.value = '';
				}
	        }
		};
	}
	jQuery("#btnSubmit").click(function(event) {debugger;
		event.preventDefault();
		var sus_no = jQuery("#sus_no").val();
		if(sus_no == ""){
			alert("Please Enter SUS No.");
			jQuery("#sus_no").focus();
			return false;
		}
		var report_obsn = jQuery("#report_obsn").val();
		if(report_obsn == "0"){
			alert("Please Select Report Obsn.");
			jQuery("#report_obsn").focus();
			return false;
		}
		if(jQuery("#uploadMvcr").get(0).files.length === 0){
			alert("Please Select Document.");
			jQuery("#uploadMvcr").focus()
			return false;
		}
		
		openModal();
		
// 		var form = jQuery('#fileUploadForm')[0];
// 		var data = new FormData(form);
// 		jQuery.ajax({
// 			type : "POST",
// 			enctype : 'multipart/form-data',
// 			url : "uploadDocMVCR?"+key+"="+value,
// 			data : data,
// 			processData : false,
// 			contentType : false,
// 			cache : false,
// 			timeout : 600000,
// 			success : function(data) {
// 				jQuery("#uploadMvcr").val(null);
// 				jQuery("#report_obsn").val("");
// 				getDownloadImageMVCR();
				
// 				alert(data);
				
// 			}
// 		});
	}); 
		
	var sus_no = '${sus_no}';
	if(sus_no == "")
	{
		jQuery("div#mvcr").hide();
// 		jQuery("#h_report").hide();
		
	}
	else
	{
		jQuery("div#mvcr").show();
	}
		
	jQuery("#sus_no").val(sus_no);
	jQuery("#unit_name").val('${unit_name}');
});
	
var i = 0;
var u = 0;
function submitData(ba_no, j) {
	var last_kms_run = jQuery("label#" + j).text();
	var kms_run = jQuery("#kms_run" + j).val();
	var authrty_letter_no = jQuery("#authrty_letter_no" + j).val();
	var unitRmk = jQuery("#unitRmk" + j).val();
	var misoRmk = jQuery("#misoRmk" + j).val();
	var lastclass = jQuery("#lastclass" + j).text();
	var classification = jQuery("#classification" + j).val();
	var ser_status = jQuery("input[name='ser_status"+j+"']:checked").val();//jQuery("#ser_status" + j).val();;
	var ser_reason = jQuery("#ser_reason" + j).val();
	
	var sus_no = "";
	if('${sus_no}' == ""){
		sus_no = jQuery("#sus_no").val();
	}else{
		sus_no = '${sus_no}';
	}	
	var lastclass = jQuery("#lastclass" + j).text();
 	
/* 	if (parseInt(lastclass) > parseInt(classification)) {
		alert("Classification can`t be Decrease from Last Classification.");
		return false;
	} */
	if (classification >= 5 && (authrty_letter_no == "" || authrty_letter_no == "null")) {
		alert("Please Enter Authority Details.");
		//jQuery("select#classification" + j).val(lastclass);
		jQuery("#authrty_letter_no"+j).focus();
		return false;
	}
/* 	if (parseFloat(last_kms_run) > parseFloat(kms_run)) {
		alert("KM can't be Decrease from Previous KM.");
		jQuery("#kms_run" + j).val(last_kms_run);
		return false;
	} */
	if(ser_status != '0' && ser_status != '1'){
		alert("Please update Servicebality State.");
		return false;
	}
	if(ser_status == "1" && ser_reason == "0")
	{
		alert("Please select Reason of UNSV.");
		jQuery("select#ser_reason"+j).focus();
		return false;
	}
	var update =jQuery("#hdcount").val();
	if(update == 0)
	{
		jQuery("#hdcount").val('${UPDATE}');
	}
	
	jQuery.ajax({
		type : 'POST',
		url : "updateMvcr?"+key+"="+value,
		data : {
			sus_no : sus_no,
			ba_no : ba_no,
			kms_run : kms_run,
			authrty_letter_no : authrty_letter_no,
			classification : classification,
			unitRmk : unitRmk,
			misoRmk : misoRmk,
			ser_status : ser_status, 
			ser_reason : ser_reason
		},
		success : function(response) {
			if(response == "Data Updated Successfully.")
			{
			//	jQuery("#kms_run" + j).prop("disabled", true);
				jQuery("#authrty_letter_no" + j).prop("disabled", true);
				//jQuery("#classification" + j).prop("disabled", true);
				jQuery("#unitRmk" + j).prop("disabled", true);
				jQuery("#misoRmk" + j).prop("disabled", true);
				//jQuery("#updateId" + j).prop("disabled", true);
				jQuery("#ser_status" + j).prop("disabled", true);
				jQuery("#ser_reason" + j).prop("disabled", true);
				
				var count = jQuery('#hdcount').val();
				count = parseInt(count) + 1;
				var np = ${TOTAL} - parseInt(count);
				jQuery('#hdcount').val(count);
				jQuery('#totalUp').text(count);
				jQuery('#totalNUp').text(np); 
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

function action(data, type, full) {
	var f = "";
	var view = "onclick=\"  viewDetails('" + full.id + "')\"";
	f += '<input type="radio" '+view+' title="Delete Data"> &nbsp; &nbsp;';
	return f;
}
function viewDetails(id) {
	document.getElementById("id").value = id;
	document.getElementById("viewDetailsForm").submit();
}
</script>
<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
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

function getDownloadImageMVCR(){
	jQuery("table#getImgDoc tbody").empty();
	var sus_no = "";
	if('${roleAccess}' == "Unit"){
		jQuery("th#mvcr_doc_download").hide();
		sus_no = '${sus_no}';
	}else{
		sus_no = jQuery("#sus_no").val();
	}
	
	if(sus_no == ""){
		alert("Please Select SUS No");
		jQuery("#sus_no").focus();
		return false;
	}else{
		jQuery.post("getDocumentMVCR?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
	 		for (var x = 0; x < j.length; x++) {
				if(j[x].document != null ){
					var filename = j[x].document.split('\\').pop();
					if('${roleAccess}' != "Unit"){
						jQuery("table#getImgDoc").append('<tr id="'+x+'"><td width="80%">'+filename+'</td><td width="20%"><a hreF="#" onclick="getDownloadImageMVCR1('+ j[x].id +')" class="btn btn-primary btn-sm">Download</a></td></tr>');
					}
					if('${roleAccess}' == "Unit"){
						jQuery("table#getImgDoc").append('<tr id="'+x+'"><td width="80%">'+filename+'</td></tr>');
					}
				}
			}	
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
}
function getDownloadImageMVCR1(id){
	document.getElementById("id").value = id;
	document.getElementById("getDownloadImageForm").submit();
} 

function openModal(element) {
    var modal = document.getElementById("td-myModal");

    modal.style.display = "block";


    var closeDiv = document.getElementById("close_div");
    closeDiv.onclick = function () {
        modal.style.display = "none";
    };
}


function cleardata()
{
	  	document.getElementById("issue_summary").value ="";
	  	document.getElementById("description").value ="";
}

function updatedata()
{
	var issue_summary = $("input#issue_summary").val(), description = $("textarea#description").val();
var report_obsn =$("select#report_obsn").val();
	if(issue_summary == "")
	{
		alert("Please Enter issue summary");
		$("input#issue_summary").focus();
		return false;
	}
	if(description == "")
	{
		alert("Please Enter description");
		$("textarea#description").focus();
		return false;
	}
	else if((issue_summary != "" && issue_summary != null) || (description != "" && description != null))
	{
// 		var id =document.getElementById('rejectid_model').value; 
		
		var form = jQuery('#fileUploadForm')[0];
		var data = new FormData(form);
		data.append("issue_summary",issue_summary);
		data.append("description",description);
		data.append("report_obsn",report_obsn);
		jQuery.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "uploadDocMVCR?"+key+"="+value,
			issue_summary:issue_summary,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				jQuery("#uploadMvcr").val(null);
				jQuery("#report_obsn").val("");
				getDownloadImageMVCR();
				
				alert(data);
				
				if(data == "Document Uploaded Successfully.")
				{
					 document.getElementById('issue_summary').value ="";
					 document.getElementById('description').value ="";
					
					 
					 //////////////////// close pop up
					 $('.modal').removeClass('in');
					 $('.modal').attr("aria-hidden","true");
					 $('.modal').css("display", "none");
					 $('.modal-backdrop').remove();
					 $('body').removeClass('modal-open');
					 //////////////////// end close pop up
					
					 
				}
				
				
			}
		});
		
		

		return true;
	}
	
	return true;
}
function kmcheck(a,b,c){
	return true;
}
</script>

<c:url value="getDownloadImageMVCR" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post"
	id="getDownloadImageForm" name="getDownloadImageForm"
	modelAttribute="id">
	<input type="hidden" name="id" id="id" value="0" />
</form:form>

<c:url value="getExcelMVCR" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="typeReport1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="sus_noex" id="sus_noex" />
	<input type="hidden" name="unit_nameex" id="unit_nameex" />
</form:form> 