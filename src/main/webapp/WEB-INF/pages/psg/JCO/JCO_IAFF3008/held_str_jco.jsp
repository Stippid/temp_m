
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<form action="" method="post" enctype="multipart/form-data"
	class="form-horizontal">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 style="text-transform: capitalize">JCOs/OR Str Return</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> SUS No </label>
								</div>
								<div class="col-md-8">

									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" value="${sus_no}">

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
										maxlength="50" value="${unit_name}"
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Month </label>
								</div>
								<div class="col-md-8">
									<select id="month" name="month" class="required form-control">
										<option value="0">--Select--</option>
										<option value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Year </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year"
										class="form-control autocomplete" maxlength="4"
										onkeypress="return isNumber(event)"
										onclick="return AvoidSpace(event)" autocomplete="off"
										onkeyup="return specialcharecter(this)">
								</div>
							</div>
						</div>
					</div>



				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Held_str_jco_JCO_Url" class="btn btn-success btn-sm">Clear</a>
					<i class="fa fa-search"></i><input type="button"
						class="btn btn-primary btn-sm" id="btn-reload" value="Search" />
				</div>
			</div>
		</div>
	</div>
	<!-- /// bisag v2 010922  (converted to Datatable) -->
	<div class="col-md-12" id="getSearch_held" style="display: block;">
		<div class="card-header">
			<h5>HELD STR : JCOs/OR</h5>
			<h6>Section A : Auth Str</h6>
		</div>
	</div>



	<div class="datatablediv">
		<div class="col-md-12" id="getSearch_held_a" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_a"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="text-align: center;">Ser No</th>
							<th style="text-align: center;">JCOs</th>
							<th style="text-align: center;">JCOs/OR</th>
							<th style="text-align: center;">OR</th>
							<th style="text-align: center;">TOTAL</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>




		<div class="col-md-12" id="getSearch_held_b" style="display: block;">
			<div class="card-header">
				<h5>Section B : Held Str</h5>
			</div>
		</div>



		<div class="col-md-12" id="getSearch_held_b" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_b"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="text-align: center;">Ser No</th>
							<th style="text-align: center;">SM</th>
							<th style="text-align: center;">Sub</th>
							<th style="text-align: center;">Nb Sub</th>
							<th style="text-align: center;">Total JCOs</th>
							<th style="text-align: center;">Hav</th>
							<th style="text-align: center;">Nk</th>
							<th style="text-align: center;">Sep</th>
							<th style="text-align: center;">Total OR</th>
							<th style="text-align: center;">Total</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>



		<div class="col-md-12" id="getSearch_held_c" style="display: block;">
			<div class="card-header">
				<h5>Section C : Deserter</h5>
			</div>
		</div>
		<%-- <div class="col-md-12" id="getSearch_Letter" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Letter" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead>
		                       <tr>	
		                       		 <th style="text-align: center;">Ser No</th>		                         
			                         <th style="text-align: center;"> SM </th>	                         
			                         <th style="text-align: center;"> Sub </th>
			                         <th style="text-align: center;"> Nb Sub </th>	
			                         <th style="text-align: center;"> Total JCOs </th>			                       
			                         <th style="text-align: center;"> Hav </th>
			                         <th style="text-align: center;"> Nk </th>
			                         <th style="text-align: center;"> Sep </th>
			                         <th style="text-align: center;"> Total OR </th>
			                         <th style="text-align: center;"> Total </th>
			                         
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list4.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="13">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item4" items="${list4}" varStatus="num" >
									 <tr >
									 	<td style="font-size: 15px;text-align: center ;">${num.index+1}</td>  
											<td style="font-size: 15px;">${item4[0]}</td>	
										<td style="font-size: 15px;">${item4[1]}</td>	
										<td style="font-size: 15px;">${item4[2]}</td>	
										<td style="font-size: 15px;">${item4[3]}</td>	
										<td style="font-size: 15px;">${item4[4]}</td>	
										<td style="font-size: 15px;">${item4[5]}</td>
										<td style="font-size: 15px;">${item4[6]}</td>	
										<td style="font-size: 15px;">${item4[7]}</td>
										<td style="font-size: 15px;">${item4[8]}</td>
										
										
										
									 </tr>
							
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> --%>

		<div class="col-md-12" id="getSearch_heldstr_c"
			style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_c"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="text-align: center;">Ser No</th>
							<th style="text-align: center;">SM</th>
							<th style="text-align: center;">Sub</th>
							<th style="text-align: center;">Nb Sub</th>
							<th style="text-align: center;">Total JCOs</th>
							<th style="text-align: center;">Hav</th>
							<th style="text-align: center;">Nk</th>
							<th style="text-align: center;">Sep</th>
							<th style="text-align: center;">Total OR</th>
							<th style="text-align: center;">Total</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>


		<div class="col-md-12" id="getSearch_held_d" style="display: block;">
			<div class="card-header">
				<h5>Section D : Attach out</h5>
			</div>
		</div>



		<div class="col-md-12" id="getSearch_heldstr_d"
			style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getheldstr_d"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="text-align: center;">Ser No</th>
							<th style="text-align: center;">SM</th>
							<th style="text-align: center;">Sub</th>
							<th style="text-align: center;">Nb Sub</th>
							<th style="text-align: center;">Total JCOs</th>
							<th style="text-align: center;">Hav</th>
							<th style="text-align: center;">Nk</th>
							<th style="text-align: center;">Sep</th>
							<th style="text-align: center;">Total OR</th>
							<th style="text-align: center;">Total</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>



</form>


<script>
	$(document)
			.ready(
					function() {
						/// bisag v2 010922  (converted to Datatable)
						$.ajaxSetup({
							async : false
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
							if ('${div_sus}' != "") {
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
																				$(
																						"#unit_name")
																						.val(
																								dec(
																										enc,
																										j[0]));

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
																				var enc = data[length]
																						.substring(
																								0,
																								16);
																				$(
																						"#unit_sus_no")
																						.val(
																								dec(
																										enc,
																										data[0]));
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
						
						
						mockjax1('getheldstr_a');
						table = dataTable('getheldstr_a');
						mockjax1('getheldstr_b');
						table2 = dataTable('getheldstr_b');
						mockjax1('getheldstr_c');
						table3 = dataTable('getheldstr_c');
						mockjax1('getheldstr_d');
						table4 = dataTable('getheldstr_d');
						
						
						$('#btn-reload').on('click', function(){
					    	table.ajax.reload();
					    	table2.ajax.reload();
					    	table3.ajax.reload();
					    	table4.ajax.reload();
					    	//table5.ajax.reload();
					    });

				
					});

	function Search() {

		$("#unit_sus_no1").val($("#unit_sus_no").val());
		$("#unit_name1").val($("#unit_name").val());
		$("#month1").val($("#month").val());
		$("#year1").val($("#year").val());

		$("#hd_cmd_sus1").val($("#cont_comd").val());
		$("#hd_corp_sus1").val($("#cont_corps").val());
		$("#hd_div_sus1").val($("#cont_div").val());
		$("#hd_bde_sus1").val($("#cont_bde").val());
		//document.getElementById('searchForm').submit();

	}
</script>


<script>
/// bisag v2 050922  (converted to Datatable)
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
		var month = $('#month').val();
		var year = $('#year').val();
		var cont_comd = $("#cont_comd").val();
		var cont_corps = $("#cont_corps").val();
		var cont_div = $("#cont_div").val();
		var cont_bde = $("#cont_bde").val();
		var unit_name = $("#unit_name").val();
		var unit_sus_no = $("#unit_sus_no").val();

		
	
		if (tableName == "getheldstr_a") {
		    $.post("Getsearch_heldthstrCount_a?" + key + "=" + value, {
				Search : Search,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_heldthstrdata_a?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ sr, j[i].jco, j[i].jcos_or, j[i].or,
							j[i].total ]);

				}
			});

		}

	else	if (tableName == "getheldstr_b") {
			
		

			$.post("Getsearch_heldthstrCount_b?" + key + "=" + value, {
				Search : Search,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_heldthstrdata_b?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					// alert(j[i].lf + "------ ")
					jsondata.push([ sr, j[i].sm, j[i].sub, j[i].nb_sub,
							j[i].total_jcos, j[i].hav, j[i].nk, j[i].sep,
							j[i].total_or, j[i].total ]);

				}
			});
		}

		else	if (tableName == "getheldstr_c") {
			
			$.post("Getsearch_heldthstrCount_c?" + key + "=" + value, {
				Search : Search,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_heldthstrdata_c?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					// alert(j[i].lf + "------ ")
					jsondata.push([ sr, j[i].sm, j[i].sub, j[i].nb_sub,
							j[i].total_jcos, j[i].hav, j[i].nk, j[i].sep,
							j[i].total_or, j[i].total ]);

				}
			});
		}

		/* d */
		else	if (tableName == "getheldstr_d") {
			 $.post("Getsearch_heldthstrCount_d?" + key + "=" + value, {
				Search : Search,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("Getsearch_heldthstrdata_d?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				cont_comd : cont_comd,
				cont_corps : cont_corps,
				cont_div : cont_div,
				cont_bde : cont_bde,
				unit_name : unit_name,
				unit_sus_no : unit_sus_no,
				month : month,
				year : year
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					// alert(j[i].lf + "------ ")
					jsondata.push([ sr, j[i].sm, j[i].sub, j[i].nb_sub,
							j[i].total_jcos, j[i].hav, j[i].nk, j[i].sep,
							j[i].total_or, j[i].total ]);

				}
			});
		}

	}
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
</script>