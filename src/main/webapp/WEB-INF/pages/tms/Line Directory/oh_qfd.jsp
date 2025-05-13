<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<style>
.overSelect {
	position: absolute;
	margin-top: 10px;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
	margin-top: 10px;
	width: 250px;
	display: none;
	border: 0.5px #000000 solid;
	position: absolute;
	background-color: #FFFFFF;
	width: 100%;
	z-index: 1;
	padding: 5px;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}
</style>
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<div class="animated fadeIn">
	<div class="" style="width: 100%;">
		<div class="" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Submit QR (OH / MR)</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12" id="tpt">
						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> VEH CAT</label>
								</div>
								<div class="col-md-8">
									<select id="type_veh" class="form-control-sm form-control"
										style="width: 100%" onchange="changehrs();getMCTMainList();">
										<option value="">--Select--</option>
										<option value="0">A Vehicles</option>
										<option value="1">B Vehicles</option>
										<option value="2">C Vehicles</option>
									</select>
								</div>
							</div>
						</div>
						<!-- <div class="col-md-6">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> VEH TYPE</label>
								</div>
								<div class="col-md-8">
									<select id="prf_code" class="form-control-sm form-control" style="width: 100%"></select>
								</div>
							</div>
						</div> -->

						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">PY</label>
								</div>
								<div class="col-md-8">
									<select id="ddlYears" name="ddlYears"
										class="form-control-sm form-control">
										<option value='0'>--Select Year--</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Type Of Intervention</label>
								</div>
								<div class="col-md-8">
									<select id="type_of_intervention"
										class="form-control-sm form-control" style="width: 100%">
										<!-- onchange="getPRFList(this.value)" -->
										<option value="0">--Select--</option>
										<option value="1">MR1</option>
										<option value="2">OH1</option>
										<option value="3">MR2</option>
										<option value="4">OH2</option>
										<option value="5">MR3</option>
										<option value="6">OH3</option>
									</select>
								</div>
							</div>
						</div>



					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Nodal Dte</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus" name="line_dte_sus"
										class="form-control-sm form-control"
										onchange="getMCTMainList();"> ${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">SUB CAT (MCT 4)</label> <input
										type="hidden" id="CheckVal" name="CheckVal">
								</div>
								<div class="col-md-8">

									<div class="selectBox" onclick="showCheckboxes1()">
										<select id="mct_main" name="mct_main"
											class="form-control-sm form-control">
											<option value="" hidden>--Select--</option>


										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes1" class="checkboxes"
										style="max-height: 200px; overflow: auto;">

										<div id="chk" class="chk"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">

							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Line Dte</label>
								</div>
								<div class="col-md-8">
									<select id="line_dte_sus_main" name="line_dte_sus_main"
										class="form-control-sm form-control"
										onchange="getMCTMainList();"> ${selectLine_dte}
										<c:forEach var="item" items="${getLine_DteList2}"
											varStatus="num">
											<option value="${item.line_dte_sus}"
												name="${item.line_dte_name}">${item.line_dte_name}</option>
										</c:forEach>
									</select>
								</div>



							</div>

						</div>




					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4" style="display: none" id="kms_hide">
									<label class=" form-control-label"> Kms </label>
								</div>
								<div class="col-md-4" style="display: none" id="hrs">
									<label class=" form-control-label"> HRs </label>
								</div>

								<div class="col-md-8">
									<input type="text" id="kms" name="kms"
										onkeypress="return isNumber0_9Only(event);"
										class="form-control autocomplete" autocomplete="off"
										maxlength="6" value="0">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Vintage</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="vintag" name="vintag"
										onkeypress="return isNumber0_9Only(event);"
										class="form-control autocomplete" autocomplete="off"
										maxlength="2" value="0">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class=" col-md-4">
									<label class=" form-control-label">Forecast</label>
								</div>
								<div class="col-md-8">
									<select id="py" class="form-control-sm form-control"
										style="width: 100%">
										<option value="0">--Current Yr--</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Type Of Force</label>
								</div>
								<div class="col-md-8">
									<select name="type_force" id="type_force"
										class="form-control-sm form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${getTypeOfUnitList}">
											<option value="${item[0]}">${item[0]}-${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Command</label>
								</div>
								<div class="col-12 col-md-8">


									<div class="selectBox" onclick="showCheckboxes5()">
										<input type="hidden" id="CheckVal2" name="CheckVal2">
										<select name="cont_comd" id="cont_comd"
											class="form-control ${not empty selectedGetCommandList ? 'green-style' : ''}">
											<option>--Select--</option>

										</select>

										<div class="overSelect"></div>
									</div>

									<div id="checkboxes5"
										class="checkboxes ${not empty selectedGetCommandList}"
										style="max-height: 200px; overflow: auto;">
										<div id="chk_box" class="chk_box">
											<c:forEach var="item" items="${getCommandList}"
												varStatus="num">
												<label for="one" class="quali_subjectlist_cmd"> <input
													type="checkbox" name="multisub_cmd" class="cmdCheckBox"
													value="${item[0]}"
													<c:forEach var="selectedGetCommandList" items="${selectedGetCommandList}">
   											<c:if test="${item[0] eq selectedGetCommandList}">
        										checked
    										</c:if>
											</c:forEach> />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>





								</div>
							</div>
						</div>




					</div>
				</div>
				<input type="hidden" id="count" name="count">
				<div class="form-control card-footer" align="center">
					<a href="oh_qfd_url" type="reset" class="btn btn-success btn-sm">Clear
					</a> <i class="action_icons searchButton"></i><input type="button"
						class="btn btn-info btn-sm" onclick="return Search();"
						value="Search"> <i class="fa fa-file-excel-o"
						id="btnExport"
						style="font-size: x-large; color: green; text-align: right;"
						aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
				</div>
				<div class="card-body">
					<div class="" id="reportDiv">
						<div class="col-md-12">
							<div align="right">
								<h6>Total Count : ${list.size()}</h6>
							</div>
							<!-- 							<div class="watermarked" data-watermark="" id="divwatermark"> -->
							<span id="ip"></span>
							<form id="bDetailsForm" class="form-horizontal">
								<table id="getVehicleStatusTbl"
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th style="width: 5%;" rowspan=2>S No</th>
											<th rowspan=2>Comd</th>
											<th rowspan=2>Corps</th>
											<th rowspan=2>Div</th>
											<th rowspan=2>Bde</th>
											<th rowspan=2>Unit</th>
											<th rowspan=2>SUS No</th>
											<th rowspan=2>Line Dte</th>
											<th rowspan=2>Nomen</th>
											<th rowspan=2>BA No</th>
											<!-- 											<th rowspan=2>Type Of Intervention </th> -->
											<c:if test="${type_veh1 == 0 || type_veh1 == 1 }">
												<th rowspan=2>Kms Run</th>
											</c:if>
											<c:if test="${type_veh1 == 2 }">
												<th rowspan=2>HRs</th>
											</c:if>

											<th rowspan=2>Vintage</th>
											<th rowspan=2>Cl</th>
											<!-- 											<th  rowspan=2>Priv OH</th> -->
											<th colspan=2>OH 1</th>
											<th colspan=2>OH 2</th>
											<th colspan=2>OH 3</th>
											<th colspan=2>MR 1</th>
											<th colspan=2>MR 2</th>
											<th colspan=2>MR 3</th>



											<%-- 											<c:if test="${type_veh1 == 0 || type_veh1 == 2  }"> --%>
											<th style="width: 10%;" rowspan=2>Action</th>
											<%-- 											</c:if> --%>
										</tr>
										<tr>
											<th>Due</th>
											<th>Done</th>
											<th>Due</th>
											<th>Done</th>
											<th>Due</th>
											<th>Done</th>
											<th>Due</th>
											<th>Done</th>
											<th>Due</th>
											<th>Done</th>
											<th>Due</th>
											<th>Done</th>

										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="11" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
										<c:forEach var="item" items="${list}" varStatus="num">
											<tr>
												<td style="width: 5%;">${num.index+1}</td>
												<td>${item[0]}</td>

												<td>${item[1]}</td>
												<td>${item[2]}</td>
												<td>${item[3]}</td>
												<td>${item[4]}</td>
												<td>${item[5]}</td>
												<td>${item[12]}</td>
												<td>${item[6]}</td>

												<td>${item[7]}<input type="hidden"
													id="ba_no_t${num.index+1}" name="ba_no_t${num.index+1}"
													value="${item[7]}" /></td>
												<%-- 											<td >${item[17]}</td> --%>
												<td>${item[8]}<input type="hidden"
													id="km_t${num.index+1}" name="km_t${num.index+1}"
													value="${item[8]}" /></td>

												<td>${item[9]}<input type="hidden"
													id="vintage_t${num.index+1}" name="vintage_t${num.index+1}"
													value="${item[9]}" />
												</td>
												<td>${item[10]}<input type="hidden"
													id="classification_t${num.index+1}"
													name="classification${num.index+1}" value="${item[10]}" />
												</td>
												<%-- 												<td >${item[11]}</td> --%>
												<td>${item[13]}</td>
												<td>${item[16]}</td>
												<td>${item[14]}</td>
												<td>${item[17]}</td>
												<td>${item[15]}</td>
												<td>${item[18]}</td>
												<td>${item[19]}</td>
												<td>${item[22]}</td>
												<td>${item[20]}</td>
												<td>${item[23]}</td>
												<td>${item[21]}</td>
												<td>${item[24]}</td>



												<%-- 												<c:if test="${type_veh1 == 0 || type_veh1 == 2}"> --%>
												<td style="width: 10%;"><select
													name="mode${num.index+1}" id="mode${num.index+1}"
													class="form-control">
														<option value="0">--Select--</option>
														<option value="1">Industry Mode</option>
														<option value="2">Service Mode</option>
												</select> <input type="button" class="btn btn-primary btn-sm"
													id="btn_s${num.index+1}" value="Submit"
													onclick="return save_base_wshop(${num.index+1});">
													<label class="form-control-label" id="lab_l${num.index+1}"
													style="display: none"><strong style="color: Green;"><u>
																Submitted Successfully</u></strong></label></td>
												<%-- </c:if> --%>


											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<!-- 							</div> -->
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="button" id="save_p" class="btn btn-primary btn-sm"
						value="Submit" onclick="submit_b_veh();" style="display: none">
				</div>
			</div>
		</div>
	</div>
</div>
<c:url value="getSearch_oh_qfd_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="type_veh1">
	<input type="hidden" name="type_veh1" id="type_veh1" />
	<input type="hidden" name="mct_main1" id="mct_main1" />
	<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" />
	<input type="hidden" name="kms1" id="kms1" />
	<input type="hidden" name="vintag1" id="vintag1" />
	<input type="hidden" name="py1" id="py1" />
	<input type="hidden" name="type_force1" id="type_force1" />
	<input type="hidden" id="CheckVal1" name="CheckVal1">    <!-- subcat -->
	<input type="hidden" id="CheckVal3" name="CheckVal3">
	<input type="hidden" id="type_of_intervention1" name="type_of_intervention1"> 
	<input type="hidden" id="line_dte_sus_main1" name="line_dte_sus_main1"> 
	<input type="hidden" id="ddlYears1" name="ddlYears1"> 
</form:form>

<c:url value="Excel_oh_qfd_report" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="typeReport1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="type_vehex" id="type_vehex" />
	<input type="hidden" name="mct_mainex" id="mct_mainex" />
	<input type="hidden" name="line_dte_susex" id="line_dte_susex" />
	<input type="hidden" name="kmsex" id="kmsex" />
	<input type="hidden" name="vintagex" id="vintagex" />
	<input type="hidden" name="pyex" id="pyex" />
	<input type="hidden" name="type_forceex" id="type_forceex" />
</form:form>

<c:url value="save_workshop_submit_qr" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="saveForm"
	name="saveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0" />

</form:form>




<script>
	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
/* 	function getPRFList(val){
		var options = '<option value="0">' + "--Select--" + '</option>';
		if (val != "") {
			$.post("getTptSummaryInPRFList?" + key + "=" + value, {type : val}).done(function(j) {
				for (var i = 0; i < j.length; i++) {
					if (j[i].prf_code == '${prf_code1}') {
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" selected=selected>'+ j[i].group_name + '</option>';
					} else {
						options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name + '</option>';
					}
				}
				$("select#prf_code").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		} else {
			$("select#prf_code").html(options);
		}
		
	} */
 	/* function getMCTMainList(){
		var type_veh = $('#type_veh').val();
		var line_dte_sus = $('#line_dte_sus').val();
		var options = '<option value="0">' + "--Select--" + '</option>';
		if (line_dte_sus != "") {
			$.post("getMctNo4DigitDetailListforqfd?" + key + "=" + value, {line_dte_sus : line_dte_sus,type_veh:type_veh}).done(function(j) {
				for (var i = 0; i < j.length; i++) {
					if (j[i].mct_main_id == '${mct_main1}') {
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+j[i].mct_main_id+"-"+j[i].mct_nomen + '</option>';
					} else {
						options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+j[i].mct_main_id+"-"+j[i].mct_nomen + '</option>';
					}
				}
				$("select#mct_main").html(options);
			}).fail(function(xhr, textStatus, errorThrown) {});
		} else {
			$("select#mct_main").html(options);
		}
	} */
	function Search() {
		if ($('#type_veh').val() == "") {
			alert("Please Select Veh Cat");
			$('#type_veh').focus();
			return false;
		/* } else if ($('#prf_code').val() == "0") {
			alert("please select Veh Type");
			$('#prf_code').focus();
			return false; */
		} else if ($('#line_dte_sus').val() == "0") {
			alert("Please Select Nodal Dte");
			$('#line_dte_sus').focus();
			return false;
		} 
		else {
			findselected();
			findselected2();
			$("#type_veh1").val($('#type_veh').val());
			$("#ddlYears1").val($('#ddlYears').val());
			$("#type_of_intervention1").val($('#type_of_intervention').val());
			$("#line_dte_sus1").val($('#line_dte_sus').val());
			$("#mct_main1").val($('#mct_main').val());
			$("#kms1").val($('#kms').val());
			$("#vintag1").val($('#vintag').val());
			$("#py1").val($('#py').val());
			$("#type_force1").val($('#type_force').val());
			$("#CheckVal1").val($("#CheckVal").val());
			$("#CheckVal3").val($("#CheckVal2").val());
			$("#line_dte_sus_main1").val($('#line_dte_sus_main').val());
// 			$("#type_of_intervention1").val($("#type_of_intervention").val());
			document.getElementById('searchForm').submit();
		}
	}
	function getExcel() {	
		
	 	
		var type_veh=$("#type_veh").val();
	 	var mct_main=$("#mct_main").val();
	 	var line_dte_sus=$("#line_dte_sus").val();
	 	var kms=$("#kms").val();
	 	var vintag=$("#vintag").val();
	 	var py=$("#py").val();
	 	var type_force=$("#type_force").val();
	 	
		$("#type_vehex").val(type_veh);
		$("#mct_mainex").val(mct_main);
		$("#line_dte_susex").val(line_dte_sus);
		$("#kmsex").val(kms);
		$("#vintagex").val(vintag);
		$("#pyex").val(py);
		$("#type_forceex").val(type_force);
		
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('ExcelForm').submit();
	}
	$(document).ready(function() {
// 		mockjax1('getVehicleStatusTbl');
//  		colAdj('getVehicleStatusTbl');
		getMCTMainList();
		getyear(); 
		
		var q5 = '${type_veh1}';
		if (q5 != "") {
			$("#type_veh").val(q5);
		}
// 		if (q5== "1") {
// 			$('#save_p').show();
// 		}
// 		else{
			
// 			$('#save_p').hide();
//             }
		//getPRFList($('#type_veh').val());
		var q2 = '${line_dte_sus1}';
		if (q2 != "") {
			$("#line_dte_sus").val(q2);
			getMCTMainList(q2)
		}
		
		var q2 = '${line_dte_sus_main1}';
		if (q2 != "") {
			$("#line_dte_sus_main").val(q2);
			getMCTMainList(q2)
		}
		
		
		
		var q3 = '${CheckVal1}';
		if (q3 != "") {
			$("#CheckVal").val(q3);
		}
		
		
		
		
		if ('${kms1}' == '') {
			$("#kms").val('0');
		} else {
			$("#kms").val('${kms1}');
		}
		if ('${vintag1}' == '') {
			$("#vintag").val('0');
		} else {
			$("#vintag").val('${vintag1}');
		}
		if ('${py1}' == '') {
			$("#py").val('0');
		} else {
			$("#py").val('${py1}');
		}
		if ('${type_force1}' == '') {
			$("#type_force").val('');
		} else {
			$("#type_force").val('${type_force1}');
		}
		if (q5 == "2") {
			$("#hrs").show();
			$("#kms_hide").hide();
		}
		else  {
			$("#kms_hide").show();
			$("#hrs").hide();
		}
		
	});

	function changehrs(){
		if ($("#type_veh").val() == "2") {
			$("#hrs").show();
			$("#kms_hide").hide();
		}
		else  {
			$("#kms_hide").show();
			$("#hrs").hide();
		}
	}
	
	function save_base_wshop(ps){
		$.ajaxSetup({
	        async : false
		});
		if ($('#ddlYears').val() == "0") {
			alert("Please Select PY");
			$('#ddlYears').focus();
			return false;
		} 
		if ($('#type_of_intervention').val() == "0") {
			alert("Please Select Type Of Intervention");
			$('#type_of_intervention').focus();
			return false;
		} 
		if ($('#mode'+ps).val() == "0") {
			alert("Please Select Mode");
			$('#mode'+ps).focus();
			return false;
		} 
		
		
		var base_wp = $('#base_wp'+ps).val();
		var vintage=$('#vintage_t'+ps).val();
		var ba_no_t = $('#ba_no_t'+ps).val();
		var mode = $('#mode'+ps).val();
		var py = $('#ddlYears').val();
		var type_of_intervention = $('#type_of_intervention').val();
		
	var line_dte_sus=$('#line_dte_sus').val();
		var km_t=$('#km_t'+ps).val();
		var type_veh = '${type_veh1}';
		$.post('save_base_action?' + key + "=" + value, {
			type_veh: type_veh,
			base_wp: base_wp,
			vintage:vintage,
			ba_no_t:ba_no_t,
			km_t:km_t,line_dte_sus:line_dte_sus,mode:mode,py:py,type_of_intervention:type_of_intervention
		}, function(data) {
			if(data.error == null) {
					alert("Data Saved Successfully");
					$('#btn_s'+ps).hide();
					$('#base_wp'+ps).hide();
					$('#lab_l'+ps).show();
					
			} else alert("Data Not Saved");
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	function submit_b_veh(){
		if ($('#type_veh').val() == "") {
			alert("please select Veh Cat");
			$('#type_veh').focus();
			return false;
		} else if ($('#line_dte_sus').val() == "0") {
			alert("please select Line Dte");
			$('#line_dte_sus').focus();
			return false;
		}else if ($('#type_veh').val() != "1") {
			alert("please select Veh Type B");
			$('#type_veh').focus();
			return false;
		}else{
			var type_veh1 = $('#type_veh').val();
			var mct_main1 = $('#mct_main').val();
			var line_dte_sus1 = $('#line_dte_sus').val();
			var kms1 = $('#kms').val();
			var vintag1 = $('#vintag').val();
			var py1 = $('#py').val();
			var type_force1 = $('#type_force').val();
			
			$.post('Insert_b_veh_cin?' + key + "=" + value, {
				type_veh: type_veh1,
				mct_main: mct_main1,
				line_dte_sus: line_dte_sus1,
				kms:kms1,
				vintag:vintag1,
				py:py1,
				type_force:type_force1
			}, function(data){
				alert(data)
				location.href = 'oh_qfd_url';
			}).fail(function(xhr, textStatus, errorThrown){});
		}
	}

	function showCheckboxes1() {
	 		getMCTMainList();
		var checkboxes = document.getElementById("checkboxes1");
		$("#checkboxes1").toggle();
			$('.mct_main').each(function() {
				$(this).show();
			})
	}
	function showCheckboxes5() {
 	
	var checkboxes = document.getElementById("checkboxes5");
	$("#checkboxes5").toggle();
		$('.cont_comd').each(function() {
			$(this).show();
		})
}
	function setchecked(val)
	{
		
		var myArray = val.split(",");

		 $('.chk input[type="checkbox"]').each(function() {   	
			 if(myArray.includes(this.value)){
					$( this ).attr( 'checked', true );
			 }
	      });
		  
	}
	function getMCTMainList(){
		
 		var w=$("#CheckVal").val();

		var type_veh = $('#type_veh').val();
		var line_dte_sus = $('#line_dte_sus').val();

		
		var option='';
		if (line_dte_sus != "" &&type_veh!="") {
			$.post("getMctNoDigitDetailListforqfd4?" + key + "=" + value, {line_dte_sus : line_dte_sus,type_veh:type_veh}).done(function(j) {
		
				for (var i = 0; i < j.length; i++) {
			option +='<label for="one" class="quali_subjectlist" ><input  class="nrCheckBox" type="checkbox" onchange="findselected()"  name="multisub_cmd" id=" '+j[i].mct+ ' "value="'+j[i].mct+'"/>&nbsp&nbsp&nbsp'+j[i].mct_main_id+'-'+j[i].mct_nomen+'</label>';
	
				}
				$("#chk").html(option);
				setchecked(w);
			}).fail(function(xhr, textStatus, errorThrown) {});
	} 
		
	}
// 	function getMCTMainList(){
//  		var w='${CheckVal1}';

// 		var type_veh = $('#type_veh').val();
// 		var line_dte_sus = $('#line_dte_sus').val();

		
// 		var option='';
// 		if (line_dte_sus != "" &&type_veh!="") {
// 			$.post("getMctNoDigitDetailListforqfd4?" + key + "=" + value, {line_dte_sus : line_dte_sus,type_veh:type_veh}).done(function(j) {
		
// 				for (var i = 0; i < j.length; i++) {
// 			option +='<label for="one" class="quali_subjectlist" ><input  class="nrCheckBox" type="checkbox"  name="multisub_cmd" id=" '+j[i].mct+ ' "value="'+j[i].mct+'"/>&nbsp&nbsp&nbsp'+j[i].mct_main_id+'-'+j[i].mct_nomen+'</label>';
	
// 				}
// 				$("#chk").html(option);
// 				setchecked(w);
// 			}).fail(function(xhr, textStatus, errorThrown) {});
// 	} 
		
// 	}
	
	
	function findselected2() {
	 	
		var nrSel = $('.chk_box input[type=checkbox]:checked' ).map(function() {
				return $(this).attr('value');
			}).get();
			var b=nrSel.join(',');
			$("#CheckVal2").val(b);
		}
	
	
	function findselected() {
	 	
		var nrSel = $('.chk input[type=checkbox]:checked' ).map(function() {
				return $(this).attr('value');
			}).get();
			var b=nrSel.join(',');
			$("#CheckVal").val(b);

		}
	
	
	function getyear() 
	{
	        var ddlYears = document.getElementById("ddlYears");
	        var currentYear = (new Date()).getFullYear();
	        for (var i = 0; i <= 20; i++) {
	            var option = document.createElement("OPTION");
	            option.innerHTML = i+2024;
	            option.value = i+2024;
	            ddlYears.appendChild(option);
	        }
	}
	
</script>