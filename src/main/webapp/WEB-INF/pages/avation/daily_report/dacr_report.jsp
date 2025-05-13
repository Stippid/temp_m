<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

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
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<style>
.table {
	display: grid;
	width: 100%;
	table-layout: none;
	overflow-x: scroll;
	margin: auto;
}

.table thead {
	display: table;
	width: calc(100% - 16px);
}

.table tbody {
	display: block;
}

.table td {
	padding: .2rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
	width: 90px;
	min-width: 52px;
	text-align: center;
}

.table th {
	padding: .2rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
	width: 90px;
	min-width: 52px;
}

.table tr {
	disaplay: table;
	width: 100%;
}

.table td, .table th {
	min-width: 52px;
	word-break: break-all words;
	min-width: 135px;
}
</style>
<style>
table tbody {
	display: grid;
	max-height: 500px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
	font-size: 10px;
}

table thead, table tbody tr {
	display: table;
	width: 100%;
	table-layout: auto;
}
</style>
<style>
/* Styling for the header of the table */
.table thead {
	background-color: #4CAF50; /* Green background color for the header */
	color: white; /* White text color */
}

/* Styling for each table header cell */
.table th {
	padding: 8px 12px; /* Add padding for spacing */
	text-align: center; /* Center align the header text */
	font-weight: bold; /* Make the text bold */
	background-color: #9c27b0;
	/* #4CAF50;  Green background for header cells */
	color: white; /* White text color */
}

/* Additional hover effect for header */
.table th:hover {
	background-color: #9c27b0; /* #45a049;  Darker green when hovering */
}
</style>

<div class="container" align="center">
	<div class="card">
		<div class="card-header">
			<h5>
				DAILY AVIATION CASUALTY RETURN FOR ALH/LCH/APACHE<br>
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
	</div>
</div>

<div id="dacr">
<div>
<table  class="table no-margin table-striped  table-bordered report_print">
			<tbody>
			<tr >
			<td>
									<br>
					<h6 style="font-weight:bold">Raise Ticket</h6>
				<div>
		
							<div >
							
								<div>
								<label for="text-input" class=" form-control-label">Report Obsn </label>
								</div>
								<div>
									
									<select  id="report_obsn" name="report_obsn" class="form-control"> 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${GetHelpTopic}" varStatus="num" >
       								<option value="${item.codevalue}" name="${item.label}">${item.label}<option>
       							</c:forEach>
						    </select>
								</div>
							</div>
						</div>
						<div>
			<td>	
			<br>
			<h6 style="font-weight:bold">Upload Document</h6><span style="color: red;">(*.zip or *.rar Only)</span>
										<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
											<input type="hidden" id="sus" name="sus" value="${sus_no}"/> 
											<input type="file" id="uploadDacr" name="uploadDacr" title="DACR Upload .ZIP only" /> <br>
											<br> 
											<button class="btn btn-primary btn-sm" value="Upload" id="btnSubmit">Upload</button>
											
											<a hreF="#" data-target='#rejectModal' data-toggle='modal' onclick="getDownloadImageDACR()" class="btn btn-primary btn-sm">Download</a>
										</form>
										</div>
									</td>
							
		
			</tr>
			</tbody>
			</table>
		</div>	
	<!-- First Table: First 9 Columns 05-12-24 -->
	<div class="tbl_scroll" id="dacr_header">
		<table id="SearchReportPart1"
			class="table no-margin table-striped table-hover table-bordered report_print">
			<thead>
	
				<tr>
					<th rowspan="2" style="width: 5%">Sr No</th>
					<th rowspan="2" style="width: 9%">AC No</th>
					<th rowspan="2" style="width: 8%">Status</th>
					<th rowspan="2" style="width: 13%">Location</th>
					<th colspan="2" style="width: 18%">Daily Flg Hrs as on <input
						type="date" id="date_of_aos" name="date_of_aos" style="width: 55%"
						autocomplete="off" onchange="dateChanged()"></th>
					<th rowspan="2" style="width: 9%">G/Run</th>
					<th rowspan="2" style="width: 9%">cal</th>
					<th rowspan="2" style="width: 9%">AF Hrs</th>
					<th colspan="2" style="width: 18%">ENG Ser No</th>

				</tr>
				<tr>
					<th style="width: 9%">Day</th>
					<th style="width: 9%">Night</th>
					<th style="width: 9%">LH Hrs</th>
					<th style="width: 9%">RH Hrs</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size() == 0}">
					<tr>
						<td colspan="10" align="center" style="color: red;">Data Not
							Available</td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="item" varStatus="num">
					<tr>
						<td style="font-size: 15px; width: 5%;">${num.index + 1}</td>
						<td style="font-size: 15px; width: 9%;">${item[1]}</td>
						<td style="width: 8%">${item[2]}</td>
						<td style="width: 13%">${item[24]}</td>
						<td style="width: 9%">${item[3]}</td>
						<td style="width: 9%">${item[4]}</td>
						<td style="width: 9%">${item[5]}</td>
						<td style="width: 9%">${item[17]}</td>
						<td style="width: 9%">${item[6]}</td>
						<td style="font-size: 15px; width: 9%;">${item[19]}<br>${item[7]}</td>
						<td style="font-size: 15px; width: 9%;">${item[20]}<br>${item[8]}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="tbl_scroll" id="dacr_footer">
		<table id="SearchReportPart2"
			class="table no-margin table-striped table-hover table-bordered report_print">
			<thead>
				<tr>
					<th rowspan="2" style="width: 6.5%">Sr No</th>
					<th rowspan="2" style="width: 10%">HRS Left<br><span style="font-size: 10px;">(Next INSP)</span></th>
					<th rowspan="2" style="width: 10%">Next INSP</th>
					<th colspan="4" style="width: 40%">Prog Hrs Flown</th>
					<th rowspan="2" style="width: 15%">DT Last Flown</th>
					<th rowspan="2" style="width: 15%">PDC</th>
					<th rowspan="2" style="width: 20%">Remarks</th>
					<th rowspan="2" style="width: 10%">Action</th>
				</tr>
				<tr>
					<th style="width: 10%">Monthly</th>
					<th style="width: 10%">Qtrly</th>
					<th style="width: 10%">H/Yrly</th>
					<th style="width: 10%">Yrly</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size() == 0}">
					<tr>
						<td colspan="8" align="center" style="color: red;">Data Not
							Available</td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="item" varStatus="num">
					<tr>
						<td style="font-size: 15px; width: 6.5%;">${num.index + 1}</td>
						<td style="width: 10%">${item[9]}</td>
						<td style="width: 10%">${item[10]}</td>
						<td style="width: 10%">${item[11]}</td>
						<td style="width: 10%">${item[12]}</td>
						<td style="width: 10%">${item[13]}</td>
						<td style="width: 10%">${item[14]}</td>
						<td style="width: 15%">${item[16]}</td>
						<td style="width: 15%">${item[23]}</td>
						<td style="width: 20%">${item[15]}</td>
						<td style="width: 10%">${item[18]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<div class="tbl_scroll" id="dacr_header">
		<c:if test="${list1.size() != 0}">
			<div>
				
				<h6>MR & SOW</h6>

			</div>
			<table id="SearchReportPart1"
				class="table no-margin table-striped table-hover table-bordered report_print">
				<thead>
					<tr>
						<th rowspan="2" style="width: 5%">Sr No</th>
						<th rowspan="2" style="width: 9%">AC No</th>
						<th rowspan="2" style="width: 8%">Status</th>
						<th rowspan="2" style="width: 13%">Location</th>
						<th colspan="2" style="width: 18%">Daily Flg Hrs</th>
						<th rowspan="2" style="width: 9%">G/Run</th>
						<th rowspan="2" style="width: 9%">cal</th>
						<th rowspan="2" style="width: 9%">AF Hrs</th>
						<th colspan="2" style="width: 18%">ENG Ser No</th>

					</tr>
					<tr>
						<th style="width: 9%">Day</th>
						<th style="width: 9%">Night</th>
						<th style="width: 9%">LH Hrs</th>
						<th style="width: 9%">RH Hrs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list1}" var="item" varStatus="num">
						<tr>
							<td style="font-size: 15px; width: 5%;">${num.index + 1}</td>
							<td style="font-size: 15px; width: 9%;">${item[1]}</td>
							<td style="width: 8%">${item[2]}</td>
							<td style="width: 13%">${item[24]}</td>
							<td style="width: 9%">${item[3]}</td>
							<td style="width: 9%">${item[4]}</td>
							<td style="width: 9%">${item[5]}</td>
							<td style="width: 9%">${item[17]}</td>
							<td style="width: 9%">${item[6]}</td>
							<td style="font-size: 15px; width: 9%;">${item[19]}<br>${item[7]}</td>
							<td style="font-size: 15px; width: 9%;">${item[20]}<br>${item[8]}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<!-- Second Table: Remaining Columns -->
	<div class="tbl_scroll" id="dacr_footer">
		<c:if test="${list1.size() != 0}">
			<table id="SearchReportPart2"
				class="table no-margin table-striped table-hover table-bordered report_print">
				<thead>
					<tr>
						<th rowspan="2" style="width: 6.5%">Sr No</th>
						<th rowspan="2" style="width: 10%">HRS Left<br><span style="font-size: 10px;">(Next INSP)</span></th>
						<th rowspan="2" style="width: 10%">Next INSP</th>
						<th colspan="4" style="width: 40%">Prog Hrs Flown</th>
						<th rowspan="2" style="width: 15%">DT Last Flown</th>
						<th rowspan="2" style="width: 15%">PDC</th>
						<th rowspan="2" style="width: 20%">Remarks</th>
						<th rowspan="2" style="width: 10%">Action</th>
					</tr>
					<tr>
						<th style="width: 10%">Monthly</th>
						<th style="width: 10%">Qtrly</th>
						<th style="width: 10%">H/Yrly</th>
						<th style="width: 10%">Yrly</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list1}" var="item" varStatus="num">
						<tr>
							<td style="font-size: 15px; width: 6.5%;">${num.index + 1}</td>
							<td style="width: 10%">${item[9]}</td>
							<td style="width: 10%">${item[10]}</td>
							<td style="width: 10%">${item[11]}</td>
							<td style="width: 10%">${item[12]}</td>
							<td style="width: 10%">${item[13]}</td>
							<td style="width: 10%">${item[14]}</td>
							<td style="width: 15%">${item[16]}</td>
							<td style="width: 15%">${item[23]}</td>
							<td style="width: 20%">${item[15]}</td>
							<td style="width: 10%">${item[18]}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>
<!-- model open -->
	<div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
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
					<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
						<table class="table no-margin table-striped  table-hover  table-bordered report_print" id="getImgDocDACR" >
			                <thead>
			                  <tr>
			                  	<th>Document Name</th>
			                   	<th>Action</th>
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
				<button type="button" id="close_div" class="btn btn-danger" data-dismiss="modal"
					onclick="$('#rrr').attr('data-target','#')">Close</button>
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
		if (sus_no == "") {
			jQuery("div#dacr").hide();
		} else {
			jQuery("div#dacr").show();
		}
		
		document.addEventListener('dragenter', function(event) {
		       event.preventDefault();
		   });
	
		   document.addEventListener('dragover', function(event) {
		       event.preventDefault();
		   });

		   document.addEventListener('drop', function(event) {
		       event.preventDefault();
		   });

	});
</script>
<script>
	//05-12-24
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
		var goiDate = jQuery("#date_goi_letter_" + j).val();
		var pdcDate = jQuery("#date_of_pdc_" + j).val();
		var loc = jQuery("#loc_code_" + j).val();

		// Convert goiDate (dd-MM-yyyy) to yyyy-MM-dd format
		if (goiDate) {
			var dateParts = goiDate.split('-');
			// Format date as yyyy-MM-dd
			goiDate = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
		}

		if (pdcDate) {
			var dateParts = pdcDate.split('-');
			// Format date as yyyy-MM-dd
			pdcDate = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
		}

		var sus_no = "";
		if ('${sus_no}' == "") {
			sus_no = jQuery("#sus_no").val();
		} else {
			sus_no = '${sus_no}';
		}

		var asonDate = jQuery("#date_of_aos").val();

		if (asonDate) {
			var dateParts = asonDate.split('-');
			// Format date as yyyy-MM-dd
			asonDate = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
		}

		jQuery.ajax({
			type : 'POST',
			url : "updateDacr?" + key + "=" + value,
			data : {
				sus_no : sus_no,
				acc_no : acc_no,
				status : status,
				falfHrsDay : falfHrsDay,
				falfHrsNight : falfHrsNight,
				gRun : gRun,
				afHrs : afHrs,
				engHrsLeft : engHrsLeft,
				engHrsRigth : engHrsRigth,
				hrsLeft : hrsLeft,
				nextInsp : nextInsp,
				hrsMonthly : hrsMonthly,
				hrsQtrly : hrsQtrly,
				hrsHalfYear : hrsHalfYear,
				hrsQtrlyFlow : hrsQtrlyFlow,
				remarks : remarks,
				balHrs : balHrs,
				goiDate : goiDate,
				pdcDate : pdcDate,
				asonDate : asonDate,
				loc : loc
			// Send formatted date
			},
			success : function(response) {
				if (response == "Data Updated Successfully.") {
					alert("Data Updated Successfully.");
					
					  // Change the color of the 'Update' button to red upon success
	                jQuery("#updateId" + j).css('background-color', 'red');

	                // Disable the 'Update' button after it has been clicked
	                jQuery("#updateId" + j).prop('disabled', true);
				} else if (response == "Data already exists") {
					alert("Data already exists.");
				} else {
					alert(response + " \n Data Not Updated.");
				}
			}
		});
		return true;
	}
	


	function formatDate(dateString) {
		// Assuming the input is in dd-MM-yyyy format
		var parts = dateString.split('-');
		var day = parts[0];
		var month = parts[1];
		var year = parts[2];

		// Return the date in yyyy-MM-dd format
		return year + '-' + month + '-' + day;
	}
</script>
<script>
	jQuery(function() {
		// Source SUS No
		jQuery("#sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = jQuery("#sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					jQuery.ajax({
						type : 'POST',
						url : "getTargetSUSNoList?" + key + "=" + value,
						data : {
							sus_no : sus_no
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							if (data.length != 0) {
								var enc = data[length].substring(0, 16);
							}
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							response(susval);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {
					if (ui.item) {
						return true;
					} else {
						alert("Please Enter Approved SUS No.");
						document.getElementById("sus_no").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var sus_no = ui.item.value;
					$.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
						sus_no : sus_no
					}).done(function(j) {
						var length = j.length - 1;
						var enc = j[length].substring(0, 16);
						jQuery("#unit_name").val(dec(enc, j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		// End

		// Source Unit Name
		jQuery("#unit_name")
				.keyup(
						function() {
							var unit_name = this.value;
							var susNoAuto = jQuery("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											jQuery
													.ajax({
														type : 'POST',
														url : "getTargetUnitsNameActiveList?"
																+ key
																+ "="
																+ value,
														data : {
															unit_name : unit_name
														},
														success : function(data) {
															var susval = [];
															var length = data.length - 1;
															if (data.length != 0) {
																var enc = data[length]
																		.substring(
																				0,
																				16);
															}
															for (var i = 0; i < data.length; i++) {
																susval
																		.push(dec(
																				enc,
																				data[i]));

															}
															response(susval);
														}
													});
										},
										minLength : 1,
										autoFill : true,
										change : function(event, ui) {
											if (ui.item) {
												return true;
											} else {
												alert("Please Enter Approved Unit Name.");
												document
														.getElementById("unit_name").value = "";
												susNoAuto.val("");
												susNoAuto.focus();
												return false;
											}
										},
										select : function(event, ui) {
											var target_unit_name = ui.item.value;
											$
													.post(
															"getTargetSUSFromUNITNAME?"
																	+ key + "="
																	+ value,
															{
																target_unit_name : target_unit_name
															})
													.done(
															function(j) {
																var length = j.length - 1;
																var enc = j[length]
																		.substring(
																				0,
																				16);
																jQuery(
																		"#sus_no")
																		.val(
																				dec(
																						enc,
																						j[0]));
															})
													.fail(
															function(xhr,
																	textStatus,
																	errorThrown) {
															});
										}
									});
						});
	});
</script>
<script type="text/javascript">
	function calculateHrsForRecord(recordId) {
		
		var today = new Date();
		var isFirstDayOfMonth = today.getDate() === 1; 

		
		var isFirstDayOfQuarter = isFirstDayOfMonth
				&& [ 0, 3, 6, 9 ].includes(today.getMonth());

	
		var isFirstDayOfHalfYear = isFirstDayOfMonth
				&& [ 3, 9 ].includes(today.getMonth()); // January and July

		
		var isFirstDayOfApril = isFirstDayOfMonth && today.getMonth() === 3; // April is month 3 (zero-based)

		
		var falfHrsDay = document.getElementById("falf_hrs_day_" + recordId).value;
		var falfHrsNight = document
				.getElementById("falf_hrs_night_" + recordId).value;
		var gRun = document.getElementById("g_run_" + recordId).value;

		
		if (!isValidTimeFormat(falfHrsDay) || !isValidTimeFormat(falfHrsNight)
				|| !isValidTimeFormat(gRun)) {
			alert("Invalid time format! Please ensure all times are in HH:MM format.");
			return; 
		}

		var afhrs = document.getElementById("af_hrs_" + recordId).value;
		var lh = document.getElementById("eng_hrs_left_" + recordId).value;
		var rh = document.getElementById("eng_hrs_rigth_" + recordId).value;
		var hleft = document.getElementById("hrs_left_" + recordId).value;
		var m = document.getElementById("hrs_monthly_" + recordId).value;
		var qr = document.getElementById("hrs_qtrly_" + recordId).value;
		var hy = document.getElementById("hrs_half_year_" + recordId).value;
		var y = document.getElementById("hrs_qtrly_flow_" + recordId).value;

		
		if (isFirstDayOfMonth) {
			document.getElementById("hrs_monthly_" + recordId).value = "00:00";
			m = "00:00"; // Reset monthly hours
		}

		
		if (isFirstDayOfQuarter) {
			document.getElementById("hrs_qtrly_" + recordId).value = "00:00";
			qr = "00:00"; // Reset quarterly hours
		}

		
		if (isFirstDayOfHalfYear) {
			document.getElementById("hrs_half_year_" + recordId).value = "00:00";
			hy = "00:00"; // Reset half-year hours
		}

		
		if (isFirstDayOfApril) {
			document.getElementById("hrs_qtrly_flow_" + recordId).value = "00:00";
			y = "00:00"; // Reset quarterly flow hours
		}

		
		var falfHrsDayMinutes = timeToMinutes(falfHrsDay);
		var falfHrsNightMinutes = timeToMinutes(falfHrsNight);
		var gRunMinutes = timeToMinutes(gRun);
		var afhrsMinutes = timeToMinutes(afhrs);
		var lhMinutes = timeToMinutes(lh);
		var rhMinutes = timeToMinutes(rh);
		var hleftMinutes = timeToMinutes(hleft);
		var mMinutes = timeToMinutes(m);
		var qrMinutes = timeToMinutes(qr);
		var hyMinutes = timeToMinutes(hy);
		var yrMinutes = timeToMinutes(y);

	
		var gRun20Percent = Math.round(gRunMinutes * 0.20);

		
		var afHrs = minutesToTime(afhrsMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes + gRun20Percent);
		var lhHrs = minutesToTime(lhMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes + gRun20Percent);
		var rhHrs = minutesToTime(rhMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes + gRun20Percent);
		var hrs = minutesToTime(hleftMinutes - falfHrsDayMinutes
				- falfHrsNightMinutes - gRun20Percent);
		var mHrs = minutesToTime(mMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes);
		var qrHrs = minutesToTime(qrMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes);
		var hyHrs = minutesToTime(hyMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes);
		var yHrs = minutesToTime(yrMinutes + falfHrsDayMinutes
				+ falfHrsNightMinutes);

		
		document.getElementById("af_hrs_" + recordId).value = afHrs;
		document.getElementById("eng_hrs_left_" + recordId).value = lhHrs;
		document.getElementById("eng_hrs_rigth_" + recordId).value = rhHrs;
		document.getElementById("hrs_left_" + recordId).value = hrs;
		document.getElementById("hrs_monthly_" + recordId).value = mHrs;
		document.getElementById("hrs_qtrly_" + recordId).value = qrHrs;
		document.getElementById("hrs_half_year_" + recordId).value = hyHrs;
		document.getElementById("hrs_qtrly_flow_" + recordId).value = yHrs;
		
		 var calculateButton = document.getElementById("calId" + recordId);
		    calculateButton.disabled = true;
		    calculateButton.style.backgroundColor = 'red';
		    calculateButton.style.color = 'white';

	}

	// Validate if the time is in HH:MM format
	function isValidTimeFormat(time) {
		var regex = /^\d{2}:\d{2}$/;
		return regex.test(time) && !isNaN(time.split(":")[0])
				&& !isNaN(time.split(":")[1]);
	}

	// Convert time in HH:MM format to total minutes
	function timeToMinutes(time) {
		var parts = time.split(":");
		var hours = parseInt(parts[0]);
		var minutes = parseInt(parts[1]);
		return (hours * 60) + minutes;
	}

	// Convert total minutes to HH:MM format
	function minutesToTime(totalMinutes) {
		var hours = Math.floor(totalMinutes / 60);
		var remainingMinutes = totalMinutes % 60;
		return String(hours).padStart(2, '0') + ":"
				+ String(remainingMinutes).padStart(2, '0');
	}
</script>
<script>

	function blockColon(event) {
		
		if (event.key === ":" || event.keyCode === 58) {
			event.preventDefault(); 
			return false;
		}
		
		if (event.keyCode === 8) {
			return true;
		}
		return true;
	}

	
	function maintainColonFormat(event) {
		let inputElement = event.target;
		let value = inputElement.value;

		
		if (value.length === 2 && value.indexOf(":") === -1) {
			inputElement.value = value.slice(0, 2) + ":" + "00";
		}

		
		if (value.length > 5) {
			let parts = value.split(":");
			if (parts.length === 2) {
				let hour = parts[0];
				let minute = parts[1];
				if (hour.length < 2) {
					parts[0] = "0" + hour; 
				}
				if (minute.length < 2) {
					parts[1] = "0" + minute; 
				}
				inputElement.value = parts[0] + ":" + parts[1]; 
			}
		}

		return true;
	}

	
	function ensureLeadingZero(event) {
		let inputElement = event.target;
		let value = inputElement.value;

		if (value.length === 1) {
			inputElement.value = "0" + value; 
		}
	}
	
	document.getElementById('falf_hrs_day_123').addEventListener('mousedown',
			function(e) {
				e.preventDefault(); 
			});


	document.getElementById('falf_hrs_day_123').addEventListener('keydown',
			function(e) {
				if (e.ctrlKey && e.key === 'a') {
					e.preventDefault(); 
				}
			});

	
	document.getElementById('falf_hrs_day_123').addEventListener('selectstart',
			function(e) {
				e.preventDefault(); 
			});
</script>

<script type="text/javascript">
function dateChanged() {
    const date = document.getElementById('date_of_aos').value;
    
   
    jQuery(".updateButton").each(function() {
        let buttonId = jQuery(this).attr('id'); 
        let id = buttonId.replace("updateId", ""); 
        
       
        jQuery("#" + buttonId).css('background-color', 'blue'); 
        jQuery("#" + buttonId).prop('disabled', false); 

        console.log("Updated button with ID: " + buttonId);
    });
    
    jQuery(".calButton").each(function() {
        let buttonIdd = jQuery(this).attr('id'); 
        let id = buttonIdd.replace("calId", ""); 
        
       
        jQuery("#" + buttonIdd).css('background-color', 'blue'); 
        jQuery("#" + buttonIdd).prop('disabled', false); 

        console.log("Updated button with ID: " + buttonIdd);
    });
    
  
}
</script>

<script>
var count = 0;
jQuery(document).ready(function() {
	var file = document.getElementById('uploadDacr');
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
			  	alert('Allowed.');
				break;
			  	default:
			    	alert('Please Only Allowed *.zip or *.rar File Extensions.');
			    this.value = '';
			}
        }
	};
	$("#btnSubmit").click(function(event) {
		event.preventDefault();
		
		var sus_no = $("#sus_no").val();
		if(sus_no == ""){
			alert("Please Select SUS No.");
			$("#sus_no").focus()
			return false;
		}
		var report_obsn = jQuery("#report_obsn").val();
		if(report_obsn == "0"){
			alert("Please Select Report Obsn.");
			jQuery("#report_obsn").focus();
			return false;
		}
		if($("#uploadDacr").get(0).files.length === 0){
			alert("Please Select Document.");
			$("#uploadDacr").focus()
			return false;
		}
		openModal();
	});
	var sus_no = '${sus_no}';
   	var unit_name = '${unit_name}'; 
   
   	if(unit_name == ""){
   		
 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}, function(j) {

		}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
   				var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,j[0]));
   		 	}
		}).fail(function(xhr, textStatus, errorThrown) {
		});	
   	}
    $("#sus_no").val(sus_no);
    $("#unit_name").val(unit_name);
   
     
});
var u = 0;
function open1(id)
{  
	document.getElementById("editId").value=id;	
	document.getElementById("editForm").submit();
}   

 function getDownloadImageDACR(){
	
	
	jQuery("table#getImgDocDACR tbody").empty();
	var sus_no = jQuery("#sus_no").val();

			 	$.post("getDocumentDACR?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			 		for (var x = 0; x < j.length; x++) {
						if(j[x].document != null ){
							jQuery("table#getImgDocDACR").append('<tr id="'+x+'"><td>'+j[x].document+'</td><td><a hreF="#" onclick="getDownloadImageDACR1('+ j[x].id +')" class="btn btn-primary btn-sm">Download</a></td>' +'</tr>');
						}
					}	
				}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function getDownloadImageDACR1(id){  
	document.getElementById("id1").value = id;
	document.getElementById("getDownloadImageForm1").submit();
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
var sus_no='${sus_no}';
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
			
		var form = $('#fileUploadForm')[0];
		var data = new FormData(form);
		data.append("issue_summary",issue_summary);
		data.append("description",description);
		data.append("report_obsn",report_obsn);
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "uploadDocDACR?${_csrf.parameterName}=${_csrf.token}",
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {
				alert(data);
				$("#uploadDacr").val(null);
				
				if(data == "Document Uploaded Successfully")
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
</script>

<c:url value="getDownloadImageDACR_Report" var="imageDownloadUrl1" />
	<form:form action="${imageDownloadUrl1}" method="post" id="getDownloadImageForm1" name="getDownloadImageForm1" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/>
	</form:form> 


