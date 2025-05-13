	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
		<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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


<div class="animated fadeIn">
	<div class="col-md-12" align="center">
		<div class="card">
			<div class="card-header">
				<h5>
					IUT TRACK STATUS C VEH <br>
				</h5>
			</div>

			<div class="card-body">

				<div class="col-md-12">

					<div class="col-md-6">


						<div class="row form-group">
							<div class="col-md-4">


								<label class=" form-control-label"> SUS No </label>
							</div>
							<div class="col-md-8">

								<input type="text" id="unit_sus_no" name="unit_sus_no"
									class="form-control autocomplete" autocomplete="off"
									maxlength="8">

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" onkeyup="return specialcharecter(this)">

							</div>
						</div>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Cont Comd </label>
							</div>
							<div class="col-12 col-md-8">
								<select id="cont_comd" name="cont_comd" class="form-control">
									${selectcomd}
									<c:forEach var="item" items="${getCommandList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class="form-control-label">Cont Corps</label>
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
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Cont Div</label>
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
							<div class="col col-md-4">
								<label class=" form-control-label">Cont Bde</label>
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
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Line Dte </label>
							</div>
							<div class="col-12 col-md-8">
								<select id="line_dte" name="line_dte"
									class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getLineDteList}" varStatus="num">
										<option value="${item[0]}"
											${item[0] eq line_dte1 ? 'selected="selected"' : ''}>
											${item[1]}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="card-footer" align="center">
				<a href="track_iut_status_c_veh" class="btn btn-success btn-sm">Clear</a>
				<i class="fa fa-search"></i><input type="button"
					onclick="Search1();" class="btn btn-primary btn-sm" id="btn-reload"
					value="Search" />
				<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: red; text-align: right;"aria-hidden="true" title="EXPORT TO EXCEL"onclick="getExcelmain();"></i> -->
			</div>
		</div>
	</div>



	<div class="watermarked" data-watermark="" id="divwatermark"
		style="display: block;">

		<table id="alltable"
			class="table no-margin table-striped  table-hover  table-bordered report_print">
			<thead
				style="background-color: #9c27b0; color: white; text-align: center;">
				<tr>

					<th width="5%">IUT No</th>
					<th width="15%">Authority</th>
					<th width="10%">User Name</th>
					<th width="10%">Vehical Type</th>
					<th width="20%">Main</th>
					<th width="10%">Source Unit Name</th>
					<th width="10%">Target Unit Name</th>
					<th width="10%">List Of Ba No</th>
					<th width="10%">Status</th>
					<th width="10%">Upload/ Download Voucher</th>
					<th width="10%">Action</th>

				</tr>
			</thead>
			<tbody>
				<c:if test="${list.size() == 0}">
					<tr>
						<td align="center" colspan="9" style="color: red;">Data Not
							Available</td>
					</tr>
				</c:if>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>

						<td width="5%" style="text-align: center;">${item[0]}</td>
						<td width="15%" style="text-align: center;">${item[1]}</td>
						<td width="10%" style="text-align: center;">${item[8]}</td>
						<td width="20%" style="text-align: center;">${item[2]}</td>
						<td width="10%" style="text-align: center;">${item[3]}</td>
						<td width="10%" style="text-align: center;">${item[4]}</td>
						<td width="10%" style="text-align: center;">${item[5]}<input
							type="hidden" id="target_sus_no${num.index+1}"
							name="target_sus_no${num.index+1}" value="${item[10]}" /></td>
						<td>${item[6]}<input type="hidden" id="ba_no${num.index+1}"
							name="ba_no${num.index+1}" value="${item[6]}" /></td>

						<td width="10%" style="text-align: center;">${item[7]}</td>
						<td width="10%" style="text-align: center;"><i
							class='fa fa-upload' onclick='openUploadFile(${num.index+1})'></i></td>
						<td width="10%" style="text-align: center;">${item[9]}</td>

						<%-- 									<td width="5%" style="text-align: center;">${item[0]}</td>  --%>
						<%-- 									<td width="15%" style="text-align: center;">${item[1]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[8]}</td> --%>
						<%-- 									<td width="20%" style="text-align: center;">${item[2]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[3]}</td>   --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[4]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[5]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[6]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[7]}</td> --%>
						<%-- 									<td width="10%" style="text-align: center;"><i class='fa fa-upload' onclick='openUploadFile(${num.index+1})'></i></td>  --%>
						<%-- 									<td width="10%" style="text-align: center;">${item[9]}</td> --%>
						<!-- 								</tr> -->
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>


<script>
function reject_iut(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}


function approve_iut(id,type_veh) {
	$("#id2").val(id);
	$("#type_veh2").val(type_veh);
	document.getElementById('appForm').submit();
}
function openUploadFile(ps) {
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
  	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
// 	window.onfocus = function () {
		
// 	}
	var ba_no =$('#ba_no'+ps).val();
	$("#ba_nop").val(ba_no);
	
	var target_sus_no=$('#target_sus_no'+ps).val();
	$("#target_unit_sus").val(target_sus_no);
	 document.getElementById('UploadFile').submit(); 
	
	}
</script>
<script>
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	debugger;
	
	
	
	$("#report_all").hide();

	
	if ('${month1}' != 0) {
		$("#month").val('${month1}');
		// $("#report_all").show() ;	
	} else {
		var d = new Date();
		var month = d.getMonth() + 1;
		$("#month").val(month);
	}

	if ('${year1}' != '') {
		$("#year").val('${year1}');
		//  $("#report_all").show() ;	
	} else {
		var d = new Date();
		var year = d.getFullYear();
		$("#year").val(year);
	}
	if('${unit_sus_no1}' != ""){
		$("#unit_sus_no").val('${unit_sus_no1}');
	}
	
	if('${unit_name1}' != ""){
		$("#unit_name").val('${unit_name1}');
	}
	if('${cont_comd1}' != ""){
    	$("#cont_comd").val('${cont_comd1}');
    	getCONTCorps('${cont_comd1}');
	}
	if('${cont_corps1}' != ""){
		getCONTDiv('${cont_corps1}');
	}
    if('${cont_div1}' != ""){
    	getCONTBde('${cont_div1}');
    }
    if ('${roleAccess}' == 'Unit') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);

		$("#unit_sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');

	
		if ('${cmd_sus}' != "") {
			
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
			$("#report_all").show();
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}
	}
    
	if ('${roleSubAccess}' == 'Command') {
		$("#cont_comd").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
	}
	
    var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   	var fcode = this.value;
	   	if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{
   			$("select#cont_corps").html(select);
   			$("select#cont_div").html(select);
   			$("select#cont_bde").html(select);
   			
   			getCONTCorps(fcode);
    	
       		fcode += "00";	
   			getCONTDiv(fcode);
       	
       		fcode += "000";	
   			getCONTBde(fcode);
   		}
	});
	$('select#cont_corps').change(function() {
	   	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	});
	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
	});

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
															getCommand(sus_no);

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
														getCommand(a);		
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
	
});


function Search1() {

	$("#unit_sus_no1").val($('#unit_sus_no').val());
	$("#unit_name1").val($('#unit_name').val());
	$("#cont_comd1").val($('#cont_comd').val());
	$("#cont_corps1").val($('#cont_corps').val());
	$("#cont_div1").val($("#cont_div").val()) ;
	$("#cont_bde1").val($("#cont_bde").val()) ;
	$("#line_dte1").val($("#line_dte").val()) ;

	document.getElementById('searchForm').submit();
}
</script>

<c:url value="reject_iut_url" var="delete_Form" />
		<form:form action="${delete_Form}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="">
</form:form>

<c:url value="approve_iut_url" var="app_Form" />
		<form:form action="${app_Form}" method="post" id="appForm" name="appForm" modelAttribute="id2">
		<input type="hidden" name="id2" id="id2" value="">
		<input type="hidden" name="type_veh2" id="type_veh2" value="">
</form:form>
<c:url value="UploadFile_Voucher2" var="UploadFile_Voucher" />
	<form:form action="${UploadFile_Voucher}" method="post" id="UploadFile" name="UploadFile" modelAttribute="ba_nop" target="result">
		<input type="hidden" name="ba_nop" id="ba_nop" />
		<input type="hidden" name="target_unit_sus" id="target_unit_sus" />
	</form:form>
	
<c:url value="getSearch_track_status_C" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="cont_comd1" id="cont_comd1" />
	<input type="hidden" name="cont_corps1" id="cont_corps1" />
	<input type="hidden" name="cont_div1" id="cont_div1"  />
	<input type="hidden" name="cont_bde1" id="cont_bde1"  />
	<input type="hidden" name="line_dte1" id="line_dte1"  /> 
</form:form>	
	
	
<script type="text/javascript">

function getCONTCorps(fcode){
	
 	var fcode1 = fcode[0];
	$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			
			for ( var i = 0; i < length; i++) {
				if('${cont_corps1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_corps").html(options);
		}
	});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
   		if(j != ""){
		   	var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		for ( var i = 0; i < length; i++) {
			if('${cont_div1}' ==  dec(enc,j[i][0])){
				options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
			}else{
				options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
			}
		}
	   		$("select#cont_div").html(options);
   		}
	});
 } 
function getCONTBde(fcode){
	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		if(j != ""){
			var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
		jQuery("select#cont_bde").html(options);
		for ( var i = 0; i < length; i++) {
			if('${cont_bde1}' ==  dec(enc,j[i][0])){
				options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
				$("#cont_bname").val(dec(enc,j[i][1]));
			}else{
				options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
			}
		}	
		$("select#cont_bde").html(options);
		}
	});
}

function getCommand(y){
	//$("#cont_comd").attr('disabled',true);
	var FindWhat = "COMMAND";
	
	$.post("getpsg_comndSUS?"+key+"="+value,{FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
			
		}
		var data=a[0].split(",");
		var datap;
		
// 		for(var i = 0; i < data.length-1; i++) {
			datap=data[0].split(":");
			$("#cont_comd").val(datap[0]);  
			getCONTCorps(datap[0]);
			$("#cont_corps").val(datap[1]);  
			getCONTDiv(datap[1]);
			$("#cont_div").val(datap[2]);  
			getCONTBde(datap[2]);
			$("#cont_bde").val(datap[3]);  
			
 		
// 		}	
	}); 
}
</script>
	
