<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 
%>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

  <head>
	<title>Inter Command Move Details</title>
		<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
		<script src="js/JS_CSS/jquery-3.3.1.js"></script>
		<script src="js/JS_CSS/jquery.dataTables.js"></script>
		<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
		<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="js/cue/cueWatermark.css">
        <script src="js/cue/cueWatermark.js"></script>
		<script src="js/cue/printAllPages.js" type="text/javascript"></script>
		<script>
			var username="${username}";
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

<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

 				
	<style type="text/css">
	.dataTable{
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
			overflow-x:hidden !important;
		}
		.DataTables_sort_wrapper{
			 width : auto;  
		}

		table.dataTable tr.odd {
	  			background-color: #f0e2f3;
		}
		table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
  			font-weight: bold;
		}
		
		.dataTables_paginate.paging_full_numbers{
			margin-top: 0.755em;
		}
		.dataTables_paginate.paging_full_numbers a{
			background-color: #9c27b0;
			border: 1px solid #9c27b0;
			color: #fff;
			border-radius: 5px;
			padding: 3px 10px;
			margin-right: 5px;
		}
		.dataTables_paginate.paging_full_numbers a:hover{
			background-color: #cb5adf;
			border-color: #cb5adf;
		}
		.dataTables_info{
			color:#9c27b0 !important;
			font-weight: bold;
		}
		
		.search_btn{
		  margin:0 auto;
		}
		
	#btn-reload input{
		  background-color: #007bff;
          border-color: #007bff;
		}

		#btn-reload input:hover{
		background-color: #0069d9;
			border-color: #0069d9;
		}
		
		.watermarked::before{
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
		<div class="col-md-12" >
			<div class="card">		
				<div class="card-header" id ="heading" align="center"> <strong style="text-decoration: underline;"> Inter Command Move Details</strong> 
	    			</div>	
	    			
	    			<div class="card-body" id="search_fil" style="display:none">
	  				<div class="row form-group" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label">From Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control">
										${selectcomd1}
										<c:forEach var="item" items="${getCommandList1}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label">To Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="to_cont_comd" name="to_cont_comd" class="form-control" >
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
											varStatus="num">
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
								<div class="col col-md-3">
									<label class="form-control-label">From  Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class="form-control-label">To Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="to_cont_corps" name="to_cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
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
								<div class="col col-md-3">
									<label class=" form-control-label">From  Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label">To  Div</label>
								</div>
								<input type="hidden" id="from_date" name="from_date"
								class="required form-control" autocomplete="off" value="1" />
								<input type="hidden" id="test" name="test"
								class="required form-control" autocomplete="off" value="2" />
								
								<div class="col-12 col-md-8">
									<select id="to_cont_div" name="to_cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
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
								<div class="col col-md-3">
									<label class=" form-control-label">From Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_bde" name="cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label">To Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="to_cont_bde" name="to_cont_bde" class="form-control">
										${selectBde}
										<c:forEach var="item" items="${getBdeList}" varStatus="num">
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
						               <div class="col col-md-3">
						                    <label class=" form-control-label">Date From: </label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="fromDate" id="fromDate" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col col-md-3 ">
						                    <label class=" form-control-label"> Date To: </label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="toDate" id="toDate" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						                </div>
						            </div>
	              				</div>
	              				<input type="hidden" id="reportId" name="reportId"
								class="required form-control" autocomplete="off" value="" />
						</div>
		  				
		  		</div>
		  	<div id="third_div" class="card-footer" style="display:none">
			<div align="center" class="search_btn">
						<input type="button" class="btn btn-success btn-sm" id="btn1" value="Clear" onclick="return clearAll();">					
			
<!-- 			<a href="unitMoveReport" class="btn btn-success btn-sm" >Clear</a>  -->
			<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" style="color:white; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;" /> 						</div>
		</div>
			</div>
		</div>
	</div>
					</div><br>
					
					
				<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearch" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th rowspan="2">Ser No</th>
											<th rowspan="2">SUS No</th>
											<th rowspan="2">Unit Name</th>
											<th rowspan="2">NMB Date</th>
											<th colspan="4">From</th>
											<th colspan="4">To</th>
											<th rowspan="2">Arm Name</th>
											<th rowspan="2">CT Part</th>
											<th rowspan="2">Type Of Force</th>
										</tr>
										<tr>
											<th>Command</th>
											<th>Corps</th>
											<th>Division</th>
											<th>Brigade</th>
											<th>Command</th>
											<th>Corps</th>
											<th>Division</th>
											<th>Brigade</th>
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
			
				

<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	jQuery(function($){ 
        
        datepicketDate('fromDate');
        datepicketDate('toDate');
   
	});	


	$("#reportId").val('${reportId}');
debugger;
	var id = $("#reportId").val();
	if (id == "moveReport"){
	
	table.column(5).visible(false);
	table.ajax.reload();
	document.getElementById("heading").innerHTML = '<span style="font-weight: bold; text-decoration: underline;">UNIT DETAILS</span>';

	}
	
	if (id == "inter_cmd_move_dtl"){
		table.column(12).visible(false);
		table.column(13).visible(false);
		table.column(14).visible(false);
		document.getElementById("search_fil").style.display = "block"; 
		document.getElementById("third_div").style.display = "block"; 
		$("#fromDate").val('${fromDate}');
		$("#toDate").val('${toDate}');
		}
	
	var select = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	$('select#cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorps(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#to_cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);
		} else {
			$("select#to_cont_corps").html(select);
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorpsFrom(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDiv(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	
	$('select#to_cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);
		} else {
			$("select#to_cont_div").html(select);
			$("select#to_cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDivFrom(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});
	
	$('select#to_cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_bde").html(select);
		} else {

			$("select#to_cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBdeFrom(fcode);
		}
	});

	$('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});
	
	$('select#to_cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#to_cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});
});

mockjax1('getSearch');
table = dataTable('getSearch');

$('#btn-reload').on('click', function(){
    table.ajax.reload();
});


function getCONTCorps(fcode) {
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';

			for (var i = 0; i < length; i++) {
				if ('${corp_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_corps").html(options);
		}
	});
}


function getCONTCorpsFrom(fcode) {
	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';

			for (var i = 0; i < length; i++) {
				if ('${corp_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#to_cont_corps").html(options);
		}
	});
}
function getCONTDiv(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < length; i++) {
				if ('${div_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_div").html(options);
		}
	});
}

function getCONTDivFrom(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	$.post("getDivDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < length; i++) {
				if ('${div_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected >' + dec(enc, j[i][1])
							+ '</option>';
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" >' + dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#to_cont_div").html(options);
		}
	});
}
function getCONTBde(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
			+ fcode[5];
	$.post("getBdeDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			jQuery("select#cont_bde").html(options);
			for (var i = 0; i < length; i++) {
				if ('${bde_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected>' + dec(enc, j[i][1])
							+ '</option>';
					$("#cont_bname").val(dec(enc, j[i][1]));
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1]) + '">'
							+ dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#cont_bde").html(options);
		}
	});
}

function getCONTBdeFrom(fcode) {
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
			+ fcode[5];
	$.post("getBdeDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		if (j != "") {
			var length = j.length - 1;
			var enc = j[length][0].substring(0, 16);
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			jQuery("select#to_cont_bde").html(options);
			for (var i = 0; i < length; i++) {
				if ('${bde_sus}' == dec(enc, j[i][0])) {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1])
							+ '" selected=selected>' + dec(enc, j[i][1])
							+ '</option>';
					$("#to_cont_bname").val(dec(enc, j[i][1]));
				} else {
					options += '<option value="' + dec(enc, j[i][0])
							+ '" name="' + dec(enc, j[i][1]) + '">'
							+ dec(enc, j[i][1]) + '</option>';
				}
			}
			$("select#to_cont_bde").html(options);
		}
	});
}

function clearAll() {

	document.getElementById("cont_comd").value=""; 
	document.getElementById("cont_corps").value="0"; 
	document.getElementById("cont_div").value="0"; 
	document.getElementById("cont_bde").value="0"; 
	document.getElementById("to_cont_comd").value=""; 
	document.getElementById("to_cont_corps").value="0"; 
	document.getElementById("to_cont_div").value="0"; 
	document.getElementById("to_cont_bde").value="0"; 
	document.getElementById("fromDate").value=""; 
	document.getElementById("toDate").value=""; 
	

}


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
	var cont_comd = $("#cont_comd").val();
	var cont_corps = $("#cont_corps").val();
	var cont_div = $("#cont_div").val();
	var cont_bde = $("#cont_bde").val();
	var to_cont_comd = $("#to_cont_comd").val();
	var to_cont_corps = $("#to_cont_corps").val();
	var to_cont_div = $("#to_cont_div").val();
	var to_cont_bde = $("#to_cont_bde").val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();  
	var id = $("#reportId").val();
	
	if (tableName == "getSearch") {
	
		$.post("getUnitMovReportcount?" + key + "=" + value,{
			Search:Search,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			to_cont_comd : to_cont_comd,
			to_cont_corps : to_cont_corps,
			to_cont_div : to_cont_div,
			to_cont_bde : to_cont_bde,
			fromDate: fromDate, 
			toDate: toDate
		 },  function(j) {
			FilteredRecords = j[0].count;
		});
		
		
		$.post("getUnitMovReport?" + key + "=" + value, {	
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			to_cont_comd : to_cont_comd,
			to_cont_corps : to_cont_corps,
			to_cont_div : to_cont_div,
			to_cont_bde : to_cont_bde,
			fromDate: fromDate, 
			toDate: toDate
		}).done(function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
				
				if (id == "inter_cmd_move_dtl"){
					
				jsondata.push([sr, j[i].sus_no, j[i].unit_name,j[i].nmb_date, j[i].frm_cmd_name,j[i].frm_coprs_name, j[i].frm_div_name, j[i].frm_bde_name
					, j[i].to_cmd_name,j[i].to_coprs_name, j[i].to_div_name, j[i].to_bde_name,"","",""]);
				
				}
				
				if (id == "moveReport"){
					
					jsondata.push([sr, j[i].sus_no, j[i].unit_name,j[i].nmb_date, j[i].frm_cmd_name,j[i].frm_coprs_name, j[i].frm_div_name, j[i].frm_bde_name
						, j[i].to_cmd_name,j[i].to_coprs_name, j[i].to_div_name, j[i].to_bde_name,j[i].arm_desc,j[i].ct_part_i_ii,j[i].type_of_force]);
					
					}
			}
		});
	}
}

</script>

