<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%
HttpSession sess = request.getSession(false);
if (sess.getAttribute("userId") == null) {
	response.sendRedirect("/login");
	return;
}
%>
<dandelion:asset cssExcludes="datatables" />
<dandelion:asset jsExcludes="datatables" />
<dandelion:asset jsExcludes="jquery" />

<head>
<title>Document Type Details</title>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script>
	var username = "${username}";
	var curDate = "${curDate}";
</script>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/upload_xls/xlsx.full.min.js"></script>

<style type="text/css">
.dataTable {
	position: relative;
	z-index: -2;
}

table.dataTable, table.dataTable th, table.dataTable td {
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	width: auto;
	text-align: left;
	font-size: 12px;
	padding: 0px;
	font-weight: bold;
}

.dataTables_scrollHead {
	/* overflow-y:scroll !important; */
	overflow-x: hidden !important;
}

.DataTables_sort_wrapper {
	width: auto;
}

table.dataTable tr.odd {
	background-color: #f0e2f3;
}

table.dataTable thead {
	background-color: #9c27b0;
	color: white;
	font-weight: bold;
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

.search_btn {
	margin: 0 auto;
}

#btn-reload input {
	background-color: #007bff;
	border-color: #007bff;
}

#btn-reload input:hover {
	background-color: #0069d9;
	border-color: #0069d9;
}

.watermarked::before {
	opacity: 0.4;
}

.mt-1 {
	margin-top: 1rem;
}

.card-footer, .card-header {
	padding: 0.35rem 1.25rem;
}

</style>
</head>

<div class="animated fadeIn">
	<div class="mt-1">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header" align="center">
					<strong style="text-decoration: underline;">MCR</strong>
				</div>
			</div>
		</div>
	</div>
	<br>    		
	</div>									

	<div class="col-md-12" id="printableArea">
		<div id="divShow" style="display: block;"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>

			<div class="datatablediv" id="reportDiv">
				<div class="">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="McrCurrentMonthReport"
							class="table no-margin table-striped  table-hover  table-bordered">
							<thead style="font-size: 15px; text-align: center;">
								<tr>
									<th>Ser No</th>
									<th>Unit Name</th>
									<th>Status</th>									
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="12" align="center" style="color: red;">Data
											Not Available</td>
									</tr>
								</c:if>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="col-md-12">
		<div align="center" class="print_btn">
	
				<input type="button" value="Print" class="btn btn-primary"
				onclick="downloaddata()"
				style="color: white; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;">
			<input type="button" class="btn btn-purple" style="padding: 5px 10px; font-size: 14px; 
			font-weight: bold; color: white; background-color: purple;" value="Export to Excel"
				onclick="exportToExcel('McrCurrentMonthReport', 'Mcr Current Month Report')">
		</div>
	</div>

<c:url value="Download_unit_dashboard_Report" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="type_of_report">
 <input type="hidden" name="type_of_report" id="type_of_report"  value="0">
</form:form>

	<script>
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$(document).ready(function() {
			$.ajaxSetup({
				async : false
			});
		
			colAdj("McrCurrentMonthReport");

		});

		mockjax1('McrCurrentMonthReport');
		table = dataTable('McrCurrentMonthReport');

		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

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
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];

			if (tableName == "McrCurrentMonthReport") {			

				$.post("getMcrCurrentMonthlistCount?" + key + "=" + value, {
					Search : Search}, function(j) {

					FilteredRecords = j;
				});
				$.post("getMcrCurrentMonthlist?" + key + "=" + value, {

					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].unit_name, j[i].status]);

					}
				});
			}
		}

		function printDiv(divName) {
			$("div#divwatermark").val('').addClass('watermarked');
			watermarkreport();
			$("div#divShow").empty();
			$("div#divShow").show();

			var row = "";
			row += '<div class="print_content"> ';
			row += '<div class="print_logo"> ';
			row += '<div class="row"> ';
			row += '<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
			row += '<div class="col-6 col-sm-6 col-md-6"><h3>Pers Auth(Jco)</h3> </div> ';
			row += '<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
			row += '</div> ';
			row += '</div> ';
			$("div#divShow").append(row);

			let popupWinindow;
			let innerContents = document.getElementById(divName).innerHTML;
			popupWinindow = window
					.open(
							'',
							'_blank',
							'width=1400,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
			popupWinindow.document.open();
			popupWinindow.oncontextmenu = function() {
				return false;
			}

			popupWinindow.document
					.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:12px;font-weight:normal;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); setTimeout(function(){ window.print(); window.close(); }, 100);" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
							+ innerContents + '</html>');

			watermarkreport();
			popupWinindow.document.close();
			document.location.reload();
			$("div#divShow").hide();
		}

		function exportToExcel(tableId, fileName) {
			var table = document.getElementById(tableId);
			var ws = XLSX.utils.table_to_sheet(table);
			var wb = XLSX.utils.book_new();
			XLSX.utils.book_append_sheet(wb, ws, "Mcr Current Month Report");
			XLSX.writeFile(wb, fileName + ".xlsx");
		}

	


	
	function downloaddata(){
		document.getElementById('type_of_report').value="CurrentMonthReportMcr";
		document.getElementById('downloadForm').submit();		 
	}

	</script>