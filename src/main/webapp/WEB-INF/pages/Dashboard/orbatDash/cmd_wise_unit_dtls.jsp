<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 
%>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

  <head>
<!-- 	<title>Inter Command Move Details</title> -->
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
				<div class="card-header" align="center"> <strong style="text-decoration: underline;">All Action: Orbat</strong> 
	    			</div>		
		  				
		  		</div>
		  	
			</div>
		</div>
	</div>
	<input type="hidden" name="cmd" id="cmd"  value="0">
	<input type="hidden" name="Ydata" id="Ydata"  value="0">
						<div class="row form-group" style="display:none">
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
						</div>
				
	    			<div class="mt-1">
	    				<div class="row form-group" >
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> SUS No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" >
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_name" name="unit_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50"
										onkeyup="return specialcharecter(this)">

								</div>
							</div>
						</div>
					</div>
	    			<div class="row form-group" >
	    			
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Command</label>
								</div>
								<div  class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control" disabled >
										${selectcomd}
										<c:forEach var="item" items="${getCommandList}"
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
									<label class="form-control-label"> Corps</label>
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
						</div>
					
				<div class="row form-group" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
									<label class=" form-control-label"> Division</label>
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
									<label class=" form-control-label"> Brigade</label>
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
						</div>

	    			<div class="card-body">
	  					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-3">
					                  <label class=" form-control-label">Arm/Service</label>
					                </div>
					                <div class="col-12 col-md-8">				                
					                <select  class="form-control" id="arm" name="arm">
					                <option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getArmNameList}" varStatus="num">
           									<option value="${item[0]}" > ${item[1]}</option>
           								</c:forEach>					                					                                                    
		                            </select>
					                </div>
					            </div>	  								
	  						</div> 
             				</div>
             				
		  						</div>
		  							<div class="card-footer">
			<div align="center" class="search_btn">
<!-- 			<a href="cmdWiseUnitDtl" class="btn btn-success btn-sm" >Clear</a>   -->
			<input type="button" class="btn btn-success btn-sm" id="btn1" value="Clear" onclick="return clearAll();">					
			<i class="fa fa-search"></i>
			<input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search"/>  
		</div>
		</div>
						</div>
					
				<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearch" 
								class="table no-margin table-striped  table-hover  table-bordered" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>Unit</th>
											<th>SUS No.</th>
											<th>Cmd Name</th>
											<th>scenario</th>
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

	$("#fromDate").val('${fromDate}');
	$("#toDate").val('${toDate}');
	
	
	$("#cmd").val('${cmd}');
	var a_cmd= $("#cmd").val();  
	if(a_cmd=="SC"){
		$("#cont_comd").val('1');
	}
	if(a_cmd=="EC"){
		$("#cont_comd").val('2');
	}
	if(a_cmd=="WC"){
		$("#cont_comd").val('3');
	}
	if(a_cmd=="CC"){
		$("#cont_comd").val('4');
	}
	if(a_cmd=="NC"){
		$("#cont_comd").val('5');
	}
	if(a_cmd=="ARTRAC"){
		$("#cont_comd").val('6');
	}
	if(a_cmd=="ANC"){
		$("#cont_comd").val('7');
	}
	if(a_cmd=="SWC"){
		$("#cont_comd").val('8');
	}
	
	if(a_cmd=="UN"){
		$("#cont_comd").val('9');
	}
	if(a_cmd=="MOD"){
		$("#cont_comd").val('0');
	}
	if(a_cmd=="SFC"){
		$("#cont_comd").val('A');
	}
	
		
// 	var fcode = this.value;


getCONTCorps($("#cont_comd").val());
	$("#Ydata").val('${Ydata}');
	
	mockjax1('getSearch');
	table = dataTable11('getSearch');
	
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



$('select#cont_bde').change(function() {
var fcode = this.value;
if (fcode == "0") {
	$("select#cont_bde").html(select);
} else {
	$("#hd_bde_sus").val(fcode);
}
});


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
$('#btn-reload').on('click', function(){
	
	table.ajax.reload();
});
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


$("#unit_sus_no")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#unit_sus_no");

			susNoAuto
					.autocomplete({
						source : function(
								request,
								response) {
							$
									.ajax({
										type : 'POST',
										url : "getTargetSUSNoList?"
												+ key
												+ "="
												+ value,
										data : {
											sus_no : sus_no
										},
										success : function(
												data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(
															0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval
														.push(dec(
																enc,
																data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(
								event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No.");
								document
										.getElementById("unit_name").value = "";
								susNoAuto
										.val("");
								susNoAuto
										.focus();
								return false;
							}
						},
						select : function(
								event, ui) {
							var sus_no = ui.item.value;
							$
									.post(
											"getTargetUnitNameList?"
													+ key
													+ "="
													+ value,
											{
												sus_no : sus_no
											},
											function(
													j) {

											})
									.done(
											function(
													j) {
												var length = j.length - 1;
												var enc = j[length]
														.substring(
																0,
																16);
												$("#unit_name").val(dec(enc,j[0]));
// 												getCommand(sus_no);

											})
									.fail(
											function(
													xhr,
													textStatus,
													errorThrown) {
											});
						}
					});
		});

// unit name
$("input#unit_name")
.keypress(
		function() {
			var unit_name = this.value;
			var susNoAuto = $("#unit_name");
			susNoAuto
					.autocomplete({
						source : function(
								request,
								response) {
							$
									.ajax({
										type : 'POST',
										url : "getTargetUnitsNameActiveList?"
												+ key
												+ "="
												+ value,
										data : {
											unit_name : unit_name
										},
										success : function(
												data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(
															0,
															16);
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
						change : function(
								event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit Name.");
								document
										.getElementById("unit_name").value = "";
								susNoAuto
										.val("");
								susNoAuto
										.focus();
								return false;
							}
						},
						select : function(
								event, ui) {
							var unit_name = ui.item.value;

							$
									.post(
											"getTargetSUSFromUNITNAME?"
													+ key
													+ "="
													+ value,
											{
												target_unit_name : unit_name
											},
											function(
													data) {
											})
									.done(
											function(
													data) {
												var length = data.length - 1;
												var enc = data[length].substring(0,16);
												$("#unit_sus_no").val(dec(enc,data[0]));
												var a = dec(enc,data[0]);
// 											getCommand(a);		
											})
									.fail(
											function(
													xhr,
													textStatus,
													errorThrown) {
											});
						}
					});
		});
		
		function clearAll(){
			document.getElementById('cont_corps').value = "0";
			document.getElementById('cont_div').value = "0";
			document.getElementById('cont_bde').value = "0";
			document.getElementById('arm').value = "0";
			document.getElementById('unit_sus_no').value = "";
			document.getElementById('unit_name').value = "";
		
			table.ajax.reload();
		}
		function formatDate(date) {
		    var parts = date.split("/");
		    var day = parts[0];
		    var month = parts[1];
		    var year = parts[2];

		    // Construct a new date string in the format yyyy-mm-dd
		    var formattedDate = year + '-' + month + '-' + day;

		    // Return the formatted date
		    return formattedDate;
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

	var fromDate_d = $("#fromDate").val();
	var toDate_d = $("#toDate").val();  
	var cmd = $("#cmd").val();  
	var Ydata = $("#Ydata").val(); 
	var cmd = $("#cmd").val();  
	
	var cont_comd = $("#cont_comd").val();  
	var cont_corps = $("#cont_corps").val();  
	var cont_div = $("#cont_div").val();  
	var cont_bde = $("#cont_bde").val();  
	var arm = $("#arm").val();  
	var unit_sus_no = $("#unit_sus_no").val();  
	var unit_name = $("#unit_name").val();  
	
	var fromDate = formatDate(fromDate_d);
	var toDate = formatDate(toDate_d);
	
	
	
	if (tableName == "getSearch") {
	
		$.post("unit_wise_dtls_tablecount?" + key + "=" + value,{
			Search:Search,
			fromDate: fromDate, 
			toDate: toDate,
			cmd : cmd,
			Ydata : Ydata,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			arm : arm,
			unit_sus_no : unit_sus_no,
			unit_name : unit_name
			
			
		 },  function(j) {
			FilteredRecords = j;
		});
		
		
		$.post("unit_wise_dtls_table?" + key + "=" + value, {	
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
		
			fromDate: fromDate, 
			toDate: toDate,
			cmd : cmd,
			Ydata : Ydata,
			cont_comd : cont_comd,
			cont_corps : cont_corps,
			cont_div : cont_div,
			cont_bde : cont_bde,
			arm : arm,
			unit_sus_no : unit_sus_no,
			unit_name : unit_name
		}).done(function(j) {

			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;
					
				jsondata.push([sr, j[i].unit_name,j[i].sus_no, j[i].cmd_name,j[i].scenario]);
				
				
			}
		});
	}
}

</script>
