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

/* .print_btn { */
/* 	margin: 0 auto; */
/* } */

/* .print_btn input { */
/* 	background-color: #9c27b0; */
/* 	border-color: #9c27b0; */
/* } */

/* .print_btn input:hover { */
/* 	background-color: #cb5adf; */
/* 	border-color: #cb5adf; */
/* } */
</style>
</head>

<div class="animated fadeIn">
	<div class="mt-1">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header" align="center">
					<strong style="text-decoration: underline;">Pers(OR)</strong>
				</div>
			</div>
		</div>
	</div>
	<br>
<div class="card-body card-block">  
							 <div class="row form-group" >
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">	
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="army_no" name="army_no" class="form-control autocomplete" maxlength="11" autocomplete="off"  onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	              				 <input type="hidden" id="type1" name="type1" value="OR"class="form-control autocomplete" > 
									<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                   <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList_jCO}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
							</div>
								
							<div class="row form-group" >
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Name</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" maxlength="11" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Trade</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="trade" name="trade" class="form-control autocomplete" maxlength="11" autocomplete="off"  onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		</div>
	              		
	        <div class="card">
				<div class="card-header" align="center">
						<input type="button" class="btn btn-info btn-sm" onclick="Search1();" value="Search"> 
						<input type="button" class=" btn btn-success btn-sm" onclick="Clear();" value="Clear">			
				</div>
			</div> 
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
						<table id="persOR"
							class="table no-margin table-striped  table-hover  table-bordered">
							<thead style="font-size: 15px; text-align: center;">
								<tr>
									<th>Ser No</th>
									<th>Unit Name</th>
									<th>Army No</th>
									<th>RK</th>
									<th>Name</th>
									<th>Trade</th>
									<th>DoB</th>
									<th>DoS</th>
									<th>DoE</th>
									<th>ToS</th>
									<th>Med Cat</th>
									<th>Army Courses</th>
									<th>Spouse Name</th>
									<th>DoM</th>
									
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
				onclick="exportToExcel('persOR', 'Pers(OR)')">
		</div>
	</div>
	

<input type="hidden" name="sus_no" id="sus_no" value="${sus}">
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

			// 	clearAll();

			colAdj("persOR");

		});

		mockjax1('persOR');
		table = dataTable('persOR');

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
			var orderColunm = $(table.column(order[0][0]).header()).attr('id');
			var orderType = order[0][1];
			
			var rank =$("#rank").val();
			var name =$("#name").val();
			var trade =$("#trade").val();
			var armyNo =$("#army_no").val();
			var sus_no = $('#sus_no').val();

			if (tableName == "persOR") {

				$.post("getUnitReportPersORcount?" + key + "=" + value, {
					Search : Search,
					rank:rank,
					name:name,
					trade:trade,
					armyNo:armyNo,
					sus_no : sus_no

				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getUnitReportPersOR?" + key + "=" + value, {

					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm,
					rank:rank,
					name:name,
					trade:trade,
					armyNo:armyNo,
					sus_no : sus_no
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;

						jsondata.push([ sr,j[i].unit_name, j[i].army_no, j[i].rk, j[i].name,
								j[i].trade, j[i].dob, j[i].dos, j[i].doe,
								j[i].tos, j[i].med_cat, j[i].army_courses, j[i].spousename,j[i].dom ]);

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
			row += '<div class="col-6 col-sm-6 col-md-6"><h3>Pers Held(OR)</h3> </div> ';
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
			XLSX.utils.book_append_sheet(wb, ws, "Pers(OR)");
			XLSX.writeFile(wb, fileName + ".xlsx");
		}
		
		function Search1(){	

			table.ajax.reload();
			table1.ajax.reload();
	    	
	    	
		}
		
		function Clear(){	
			$('#name').val('');
			$('#rank').val('');
			$('#trade').val('');
			$('#army_no').val('');
			table.ajax.reload();
			table1.ajax.reload();    	
		}
		function downloaddata(){
            document.getElementById('type_of_report').value="or_held";
            document.getElementById('downloadForm').submit();                 
    }
$("input#army_no").keypress(function(){
			
			var army_no = this.value;
			 var susNoAuto=$("#army_no");
			 var type1=$("#type1").val();
// 			 alert(type1);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getArmy_noListApproved_forunit?"+key+"="+value,
				        data: {army_no:army_no, type1:type1},
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
				        	  alert("Please Enter Approved Army No");
				        	  document.getElementById("army_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	
				      } 	     
				}); 			
		});
		
$("input#trade").keypress(function(){
	
	var trade = this.value;
	 var susNoAuto=$("#trade");
	 var type1=$("#type1").val();
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getJcoTradelist?"+key+"="+value,
		        data: {trade:trade, type1:type1},
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
		        	  alert("Please Enter Trade");
		        	  document.getElementById("trade").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});
	</script>