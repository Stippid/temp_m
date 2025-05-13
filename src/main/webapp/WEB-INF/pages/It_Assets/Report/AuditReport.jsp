<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<form:form name="Actual_str_of_user" id="Actual_str_of_user"
	action="assests_Serviceablity_detailsAction" method="post"
	class="form-horizontal" commandName="">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>AUDIT REPORT</h5>
					<h6 class="enter_by">Reported On: ${date}</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6" id="un_show" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">UN-Serviceable State</label>
								</div>
								<div class="col-md-8">
									<select name="unserviceable_state" id="unserviceable_state"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${UNServiceableList}"
											varStatus="num"> 
 											<option value="${item[1]}" name="${item[1]}">${item[1]}</option> 
 										</c:forEach> 
									</select>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Computing Assets Type </label>
								</div>
								<div class="col-md-8">
									<select name="asset_type" id="asset_type"
										class="form-control" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="AuditReportUrl"
						class="btn btn-success btn-sm">Clear</a> 
						<i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-search" value="Search">
						<i class="fa fa-download"></i><input type="button" class="btn btn-primary btn-sm" onclick="downloaddata();" value="Print">
					 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
			</div>
		</div>
	</div>

	<div class="datatablediv" id="reportDiv">
		<div class="">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span> 
				
				<table id="getSearch_Assets_Serviceablity_details" class="display table no-margin table-striped  table-hover table-bordered " >
					<thead style="font-size: 15px; text-align: center;">
						<tr>
							<th>Ser No</th>
							<th>Assets Type</th>
							<th>Machine Serial No.</th>
							<th>Make</th>
							<th>Model</th>
							<th>IP Address</th>
							<th>MAC Address</th>
							<th>Domain Username</th>
							<th>OS Installed</th>
							<th>Year Of Proc</th>
							<th>CD Avl</th>
							<th>Last Cyber Audit Date</th>
							<th>Upgradation/Repair Done</th>
							<th>Component Upgraded</th>
							<th>Last Formatting Done</th>
							<th>Authority</th>
							<th>Reason For Formatting</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
	
</form:form>

<c:url value="Download_AuditReport" var="dwonloadUrl" />
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="computing_assets_dn">
	<input type="hidden" name="asset_type2" id="asset_type2" value="0">
</form:form>


<c:url value="Excel_AuditReport" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="computing_assets_dn1">
	<input type="hidden" name="asset_type3" id="asset_type3" value="0">
</form:form>
<Script>
	$(document).ready(function() {
		if ('${asset_type1}' != "") {
			$("select#asset_type").val('${asset_type1}');
		}
	});
	
	mockjax1('getSearch_Assets_Serviceablity_details');
	table = dataTable('getSearch_Assets_Serviceablity_details');
	
	
	function data(tableName) {
		jsondata = [];

		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		var asset_type = $("#asset_type").val();

		if (tableName == "getSearch_Assets_Serviceablity_details") {
		    $.post("AuditReportCount?" + key + "=" + value,{Search:Search,
		    	asset_type : asset_type
			 },  function(j) {
				FilteredRecords = j;
			});
			$.post("AuditReportData?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search:Search,
				orderColunm : orderColunm,
				orderType : orderType,
				asset_type : asset_type
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					  jsondata.push([sr, j[i].assets_name, j[i].machine_number,j[i].make_name, j[i].model_name, j[i].ip_address
						  , j[i].mac_address, j[i].domain_username, j[i].os, j[i].proc_date, j[i].cd_drive
						  , j[i].last_cyber_audit_date, j[i].upgrade_repair_date, j[i].comp_upgrade_repair
						  , j[i].last_format_done, j[i].details_of_auth, j[i].reason_for_formatting]);
				}
			});
		}
		
	}
	
	$('#btn-search').on('click', function(){

	    table.ajax.reload();
		
	});
	
	function downloaddata() {
		$("#asset_type2").val($("#asset_type").val());
		document.getElementById('downloadForm').submit();
	}
	
	function getExcel() {	
		$("#asset_type3").val($("#asset_type").val());
		document.getElementById('ExcelForm').submit();
	} 
</script>
