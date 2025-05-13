<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<div class="animated fadeIn">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
			    <div class="card-header" align="center"><h5>HIGHEST EDN QUALIFICATION OF SPOUSES OF ALL PERS </h5></div>
				<div class="card-body">
    					<div class="col-md-12">
    						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Select Category: </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="typeID" name="typeID" class="form-control">
<!-- 										<option value = "0">--Select--</option> -->
										<option value = "1">Offrs</option>
										<option value = "2">JCO/OR</option>
									</select>
								</div>
							</div>
						</div>
				</div>
				
						<div class="col-md-12">
						
							<div class="col-md-6">
							
							
							<div class="row form-group">
								<div class="col-md-4">
    						
				
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
								<div class="col-md-4">
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
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Comd </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" name="cont_comd" class="form-control">
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
					                 <div class="col-md-6" id="examination_Spouses">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label">Examination Passed</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali" id="examination"
															class=" form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getExaminatonPassed}" varStatus="num" >
                  							            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							               </c:forEach>

														</select>
													</div>
												</div>

											</div>
											</div>
					
					
						<div class="col-md-6">
	              				<div class="row form-group">
						        	<div class="col-md-4">
						            	<label class=" form-control-label">Posn As On: </label>
						            </div>
						            <div class="col-md-8">
						            	<input type="text" name="posn_date" value = "${posn_date}" id="posn_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 89%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						            </div>
						        </div>
	              			</div>
	              			
	     
					</div>
				</div>
				
				<div class="card-footer" align="center">
		<a href="edn_qual_spouses" class="btn btn-success btn-sm" >Clear</a>  
	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" /> 
<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: red; text-align: right;"aria-hidden="true" title="EXPORT TO EXCEL"onclick="getExcelmain();"></i> -->
			</div>	
				
			</div>
			</div>	

</div>
				<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="ednSpouses"
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>Unit</th>
											<th>SUS No.</th>
											 <th id= "a_id">Personnel No</th>
											 <th>Rank</th>
											<th>Name</th>
											<th>Name Of Spouse</th>
											<th>Highest Edn Qualification</th>
											


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
				
				
				
				
				<div class="card-body">
					<div class="datatablediv" id="reportDiv">
						
						<div class="">
						<h4 align="center"><u>SUMMARY</u></h4>
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="ednSpousesSummary"
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th rowSpan="2" >Ser No</th>
											<th rowSpan="2" >Unit</th>
											<th rowSpan="2" >SUS No</th>
											<th rowSpan="2" >Highest Edn Qualification</th> 
     			                            <th id ='cat_id'>Category</th>
											<th rowSpan="2" >Total</th>
										</tr>
										
										     <tr>
										     <th id ="th_officer">Offrs</th>
										     <th id ="or">OR</th>
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
		

<script>
$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	
	
	jQuery(function($){ 
		 datepicketDate('posn_date');
		
	});
	
	
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

		

	if ('${roleAccess}' == 'MISO') {
		
		if ('${month1}' != 0) {
			$("#month").val('${month1}');
			$("#report_all").show();
		}
		if ('${year1}' != '') {
			$("#year").val('${year1}');
			$("#report_all").show();
		}
	
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
	if ('${roleSubAccess}' == 'Brigade') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
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
	if ('${roleSubAccess}' == 'Division') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {	/* table2.column(9).visible(true); */
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Corps') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
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
	
	
	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
	{
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

	if ('${sus_no}' != "") {
		$("#unit_sus_no").val('${sus_no}');

		$.post("getActiveUnitNameFromSusNo?" + key + "="
				+ value, {
			sus_no : '${sus_no}'
		}, function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			$("#unit_name").val(dec(enc, j[0]));
			//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			//var a = dec(enc,j[0]);
			//getCommand(a);	
		});

		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);
	}


	if ('${cmd_sus}' != "" && '${cmd_sus}' != "0") {
		$("#cont_comd").val('${cmd_sus}');
		$("#hd_cmd_sus").val('${cmd_sus}');
		$("#cont_comd").attr("disabled", true);
		getCONTCorps('${cmd_sus}');
	}
	if ('${corp_sus}' != "" && '${corp_sus}' != "0") {
		$("#cont_corps").val('${corp_sus}');
		$("#hd_corp_sus").val('${corp_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		getCONTDiv('${corp_sus}');
	}
	if ('${div_sus}' != "" && '${div_sus}' != "0") {
		$("#cont_div").val('${div_sus}');
		$("#hd_div_sus").val('${div_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		getCONTBde('${div_sus}');
	}
	if ('${bde_sus}' != "" && '${bde_sus}' != "0") {
		$("#cont_bde").val('${bde_sus}');
		$("#hd_bde_sus").val('${bde_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
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
	
	mockjax1('ednSpouses');
	table = dataTable11('ednSpouses');
	mockjax1('ednSpousesSummary');
	table2 = dataTable11('ednSpousesSummary');
	
	var typeID = $("#typeID").val();
	if(typeID == 1){
	$("#or").hide();
	}
	
 	$('#btn-reload').on('click', function(){
 	   table.ajax.reload();
   	table2.ajax.reload();
		   
    	var typeID = $("#typeID").val();
        
        if(typeID == "1"){	
            document.getElementById('a_id').innerHTML='Personnel No';
            document.getElementById('th_officer').innerHTML='Offrs';
           	document.getElementById("cat_id").colSpan = "1";
         	table2.column(6).visible(false);
           
        }
        else{
        	document.getElementById('a_id').innerHTML='Army No';
            document.getElementById('th_officer').innerHTML='JCO';
          //  $('#or').css('display','block');
            table2.column(6).visible(true);
            document.getElementById("cat_id").colSpan = "2";
            $("#or").show();
        }
     
        
    });

</script>

<script>


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



function data(tableName) {
jsondata = [];
var table = $('#' + tableName).DataTable();
var info = table.page.info();

var currentPage = info.page;

var pageLength = -1;
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
var unit_name = $("#unit_name").val();
var unit_sus_no = $("#unit_sus_no").val();
var typeID = $("#typeID").val();
var  examination = $("#examination").val();
var s_offrs = 0;	var s_jco = 0;	var s_ors = 0; var s_total = 0;



if (tableName == "ednSpouses") {
    $.post("search_qualification_spouses_tablecount?" + key + "=" + value,{
    	Search:Search,
		cont_comd : cont_comd,
		cont_corps : cont_corps,	
		cont_div : cont_div,
		cont_bde : cont_bde,
		unit_name : unit_name,
		unit_sus_no : unit_sus_no,
		typeID : typeID,
		examination : examination
		
		
		
	 },  function(j) {
		FilteredRecords = j;
	});
	$.post("search_qualification_spouses_table?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search:Search,
		orderColunm : orderColunm,
		orderType : orderType,
		cont_comd : cont_comd,
		cont_corps : cont_corps,
		cont_div : cont_div,
		cont_bde : cont_bde,
		unit_name : unit_name,
		unit_sus_no : unit_sus_no,
		typeID : typeID,
		examination : examination  
		
		
	}, function(j) {

		var offf_ttl=0;
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;
			
			if(typeID=="1")
			{
				  document.getElementById('a_id').innerHTML='Personnel No';
				
		    	jsondata.push([ sr, j[i].unit_name, j[i].unit_sus_no, j[i].personnel_no,
					j[i].description, j[i].name ,j[i].maiden_name,j[i].examination_passed]);

			}
			
			else {
				
				 document.getElementById('a_id').innerHTML='Army No';
				 
					jsondata.push([ sr, j[i].unit_name, j[i].unit_sus_no, j[i].army_no,
						j[i].rank, j[i].full_name ,j[i].maiden_name,j[i].examination_passed]);

			}
		}
		
	});

}


if (tableName == "ednSpousesSummary") {
    $.post("search_qualification_spouses_summary_tablecount?" + key + "=" + value,{Search:Search,
		cont_comd : cont_comd,
		cont_corps : cont_corps,	
		cont_div : cont_div,
		cont_bde : cont_bde,
		unit_name : unit_name,
		unit_sus_no : unit_sus_no,
		typeID : typeID,
		examination : examination
		
		
	 },  function(j) {
		FilteredRecords = j;
	});
	$.post("search_qualification_spouses_summary_table?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search:Search,
		orderColunm : orderColunm,
		orderType : orderType,
		cont_comd : cont_comd,
		cont_corps : cont_corps,
		cont_div : cont_div,
		cont_bde : cont_bde,
		unit_name : unit_name,
		unit_sus_no : unit_sus_no,
		typeID : typeID,
		examination : examination
		
	}, function(j) {

		var offf_ttl=0;
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;
			
			if(typeID=="1")
				{
				
				document.getElementById('th_officer').innerHTML='Offrs';
				document.getElementById("cat_id").colSpan = "1";
				table2.column(6).visible(false);
				  
					jsondata.push([ sr, j[i].unit_name, j[i].unit_sus_no, j[i].examination_passed,
					j[i].officer, j[i].total,""]);
					s_offrs +=  j[i].officer; s_total +=  j[i].total; 

				}
			else {
				
				document.getElementById('th_officer').innerHTML='JCO';
				document.getElementById("cat_id").colSpan = "2";
				//$('#or').css('display','block');
				table2.column(6).visible(true);
				$("#or").show();
				
				jsondata.push([ sr, j[i].unit_name, j[i].unit_sus_no, j[i].examination_passed,
					j[i].jco, j[i].ors, j[i].total]);
				s_jco +=  j[i].jco; s_ors +=  j[i].ors; s_total +=  j[i].total;

			}
		}
		if(typeID=='1'){
			table2.column(6).visible(false);
			jsondata.push(["","","","TOTAL",s_offrs, s_total, ""]);
		}else {
			jsondata.push(["","","","TOTAL",s_jco, s_ors, s_total]);
		}
	});

}

}

</script>


