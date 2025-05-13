<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">



<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form name="search_capture_rel_order" id="search_capture_rel_order">
	<div class="animated fadeIn" style="display: block;">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>SD9 DISTRIBUTE COMD AND CONT INST</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" name="sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 11px;"
										autocomplete="off" maxlength="100"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
				
								<div class="col-md-6">
											<div class="row form-group">
		                						<div class="col col-md-4">
		                  							<label class=" form-control-label"><strong style="color: red;"></strong>Comd Name</label>
		                						</div>
										        <div class="col-12 col-md-8">
										        	<select id="command_name" name="command_name" class="form-control">
										        		<option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										        	</select>
									            </div>
										    </div>
										</div>
					
						
						
								<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label"><strong style="color: red;"></strong> Parent Arm</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<select id="parent_arm" name="parent_arm" class="form-control">
					                                    <option value="0">--Select--</option>
					                                    <c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
			                  								<option value="${item.code}">${item.code} - ${item.code_value}</option>
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
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;"></strong> Status</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="status" id="status"
										class="form-control-sm form-control">
										<option value="0">Pending</option>
										<option value="1">Approved</option>
										<option value="3">Rejected</option>										
									</select>
								</div>
							</div>
						</div>
			
					
				
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>From </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="issue_date" name="issue_date"
										placeholder="" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">To </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="date" id="to_date" name="to_date" placeholder=""
										class="form-control autocomplete" autocomplete="off"
										onchange="return checkdate(this)">
								</div>
							</div>
						</div>
					</div>
			
				
				</div>
				<div class="form-control card-footer" align="center">
					<a href="searchDistributeComdAndCont" class="btn btn-success btn-sm">Clear</a>
					<button type="button" class="btn btn-primary btn-sm"
						onclick="return Search();">Search</button>				
		
				</div>
			</div>
		</div>
	</div>

	<div class="animated fadeIn" id="AllBTN">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-body card-block">
						<div class="col-md-12" align="right">
							<div class="col-md-6"></div>
							<div class="col-md-2">${sendButton}</div>
							<div class="col-md-2">${rejectButton}</div>							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="animated fadeIn" id="printableArea">
		<div class="">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header" id="printableArea3" style="display: none;">
						<table class="col-md-12">
							<tr>
								<td align="left"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center">
									<h5>Comd and Cont Inst</h5>
								</td>
								<td align="right"><img src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="col s12" id="tableshow">
						<div class="animated fadeIn">
							<div align="right">
								<h6>Total Count : ${list.size()}</h6>
							</div>
							<div id="divShow" style="display: block;"></div>
							<div class="watermarked" data-watermark="" id="divwatermark"
								style="display: block;">
								<span id="ip"></span>
								<table id="getSearchReport"
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead>
										<tr>
											<th id="thnSelAll" style="text-align: center; width: 2%;"><input
												type=checkbox id='nSelAll' name='tregn'
												onclick='setselectall();'></th>
												<th style="text-align: center; width: 10%;">TO BE SEND</th>
												<th style="text-align: center; width: 10%;">Action</th>
											<th style="text-align: center; width: 9%;">Auth Letter No</th>											
											<th style="text-align: center; width: 15%;">Unit Name</th>
											<th style="text-align: center; width: 5%;">Comd Name</th>										
											
										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="11" align="center" style="color: red;">
													Data Not Available</td>
											</tr>
										</c:if>
										<c:forEach items="${list}" var="list" varStatus="num">
											<tr>
												<td id="tdnSelAll" style="text-align: center; width: 2%;">${list[4]}</td>
												<td style="text-align: center; width: 10%;"><select>
												<option value="SD9">SD9</option>
														<option value="MO">MO</option>
														<option value="SD4">SD4</option>
														<option value="SD5">SD5</option>
														
												</select></td>
												<td style="text-align: center; width: 10%;">${list[3]}
													</td>
												
												<td style="text-align: center; width: 9%;">${list[0]}</td>
												<td style="text-align: center; width: 15%;">${list[1]}</td>
												<td style="text-align: center; width: 5%;">${list[2]}</td>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal-open modal" id="getComdAndContInstDtl"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="getRODetailsDiv">
					<div class="modal-header">
						<table style="width: 100%;">
							<tr style="height: 30px;">
								<td align="left" width="33.33%"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center" width="33.33%">
									<h5>Comd And Cont Inst Dtl</h5>
								</td>
								<td align="right" width="33.33%"><img
									src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="modal-body">
						<div>
							<table style="width: 100%;">
								<tr style="height: 30px;">
									<td width="20%"><b>Auth Letter No </b></td>
									<td width="30%">: <span id="vletter_no"></span></td>
									<td width="20%"><b>Auth Letter Date</b></td>
									<td width="30%">: <span id="vletter_date"></span></td>
								</tr>
								<tr style="height: 30px;">
								<td width="20%"><b>Unit Name</b></td>
									<td width="30%">: <span id="vunit_name"></span></td>
									<td width="20%"><b>Unit Sus No</b></td>
									<td width="30%">: <span id="vunit_susNo"></span></td>
									
								</tr>
									<tr style="height: 30px;">
									<td width="20%"><b>Loc</b></td>
									<td width="30%">: <span id="vloc"></span></td>
									
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>OPS name</b></td>
									<td width="30%">: <span id="vopsName"></span></td>
									<td width="20%"><b>OPS Sus No</b></td>
									<td width="30%">: <span id="vopsSusNo"></span></td>
								</tr>
							
									<tr style="height: 30px;">
									<td width="20%"><b>IS name</b></td>
									<td width="30%">: <span id="visName"></span></td>
									<td width="20%"><b>Is Sus No</b></td>
									<td width="30%">: <span id="visSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Mil name</b></td>
									<td width="30%">: <span id="vmilName"></span></td>
									<td width="20%"><b>Mil Sus No</b></td>
									<td width="30%">: <span id="vmilSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Tech Cont name</b></td>
									<td width="30%">: <span id="vtechName"></span></td>
									<td width="20%"><b>Tech Cont Sus No</b></td>
									<td width="30%">: <span id="vtechSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Discp name</b></td>
									<td width="30%">: <span id="vdiscpName"></span></td>
									<td width="20%"><b>Discp Sus No</b></td>
									<td width="30%">: <span id="vdiscpSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Local Adm name</b></td>
									<td width="30%">: <span id="vlocalAdmName"></span></td>
									<td width="20%"><b>Local Adm Sus No</b></td>
									<td width="30%">: <span id="vlocalAdmSusNo"></span></td>
								</tr>
							<tr style="height: 30px;">
									<td width="20%"><b>From Date</b></td>
									<td width="30%">: <span id="vfdate"></span></td>
									<td width="20%"><b>To Date</b></td>
									<td width="30%">: <span id="vtodate"></span></td>
								</tr>
						
							</table>
						</div>
						<div style="border: 1px solid black;">
							<table class="col-md-12">
								<tbody style="overflow: hidden;">
									<tr>
										<td style="width: 100%;" align="center"><label
											style="text-decoration: underline;">Remarks</label></td>
									</tr>
									<tr>
										<td style="width: 100%;">&emsp; </td>
									</tr>
								
								</tbody>
							</table>
						</div>



					</div>
				</div>
				<div class="modal-footer" align="center">
					<!-- <input type="button" class="btn btn-primary btn-sm" value="Print"
						onclick="printDiv1('getRODetailsDiv')"> -->
					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModal1();">Close</button>
				</div>
			</div>
		</div>
	</div>

	


	<div class="modal-open modal" id="cancleRODetails"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="cancleRODetailsDiv">
					<div class="modal-body">
						<input type="hidden" id="cancleRoNo" name="cancleRoNo"> <input
							type="hidden" id="cancleid" name="cancleid"> <input
							type="hidden" id="cancletype_of_veh" name="cancletype_of_veh">
						<table style="width: 100%;">
							<tr>
								<th>RO No :</th>
								<th><span id="cancleRO"></span></th>
							</tr>
							<tr>
								<th>Remarks <strong style="color: red;">* </strong></th>
								<td><textarea id="cancleRemarks" name="cancleRemarks"
										class="form-control"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="modal-footer" align="center">
					<input type="button" class="btn btn-primary btn-sm"
						value="RO Cancel" onclick="return finalCancelRoDetails()">

					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModalCancleRo();">Close</button>
				</div>
			</div>
		</div>
	</div>

</form>
<c:url value="getDistributeComdAndContDtl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0" />
	<input type="hidden" name="unit_name2" id="unit_name2" value="0" />
	<input type="hidden" name="command_name2" id="command_name2" value="0" />
	<input type="hidden" name="to_date2" id="to_date2" value="0" />	
	<input type="hidden" name="issue_date2" id="issue_date2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="parent_arm2" id="parent_arm2"
		value="0" />
</form:form>

<c:url value="Update" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="code_value1">
	<input type="hidden" name="vro_no1" id="vro_no1" value="0" />
	<input type="hidden" name="extended_date1" id="extended_date1"
		value="0" />
</form:form>
<script>
	function cancelRoDetails(id, ro_no, type_of_vehicle) {
		$("#cancleRO").text(ro_no);
		$("#cancleid").val(id);
		$("#cancleRoNo").val(ro_no);
		$("#cancletype_of_veh").val(type_of_vehicle);
		var modal = document.getElementById('cancleRODetails');
		modal.style.display = "block";
	}
	function closeModalCancleRo() {
		var modal = document.getElementById('cancleRODetails');
		modal.style.display = "none";
	}
	function finalCancelRoDetails() {
		var id = $("#cancleid").val();
		var ro_no = $("#cancleRoNo").val();
		var type_veh = $("#cancletype_of_veh").val();

		var remarks = $("#cancleRemarks").val();
		if (remarks == "") {
			alert("please enter Remarks");
			$("#cancleRemarks").fucos();
			return false;
		} else {
			jQuery.ajax({
				type : 'POST',
				url : "getCancelRO?" + key + "=" + value,
				data : {
					id : id,
					ro_no : ro_no,
					type_veh : type_veh,
					remarks : remarks
				},
				success : function(data) {
					alert(data[0].msg);
					if (data[0].msg == "RO Cancelled") {
						location.reload();
					}
				}
			});
		}
	}

	function printDiv1(divName) {
		let popupWinindow
		let innerContents = document.getElementById(divName).innerHTML;
		popupWinindow = window
				.open(
						'',
						'_blank',
						'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.oncontextmenu = function() {
			return false;
		}
		popupWinindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');
		//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:8px;} table th{font-size:9px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		watermarkreport();
		popupWinindow.document.close();
	}
	$(document).ready(function() {

		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();
		if ('${list.size()}' != "") {
			$("#tableshow").show();
		} else {
			$("#tableshow").hide();
		}
		if ('${status}' == '1') {
			$("#validUptoHeader").show();
			$("#validUptoHeader1").show();
		} else {
			$("#validUptoHeader").hide();
			$("#validUptoHeader1").hide();
		}

		if ('${status}' != "") {
			$("#sus_no").val('${sus_no}');
			$("#unit_name").val('${unit_name}');
			$("#command_name").val('${command_name}');			
			$("#to_date").val('${to_date}');		
			$("#issue_date").val('${issue_date}');
			$("#status").val('${status}');
			$("select#parent_arm").val('${parent_arm}');
		}
	});

	function closeModal1() {
		var modal = document.getElementById('getComdAndContInstDtl');
		modal.style.display = "none";
	}

	function viewComdAndContDetails(id) {
		var modal = document.getElementById('getComdAndContInstDtl');
		modal.style.display = "block";

		jQuery.ajax({
			type : 'POST',
			url : "getComdAndContInstDetails?" + key + "=" + value,
			data : {
				id : id
			},
			success : function(data) {
				if (data.length != 0) {
					$("#vletter_no").text(data[0].auth_letter_no);
					$("#vletter_date").text(data[0].auth_letter_date);
					$("#vloc").text(data[0].loc);
					$("#vunit_susNo").text(data[0].unit_sus_no);
					$("#vunit_name").text(data[0].unit_name);
					$("#vopsSusNo").text(data[0].ops_sus_no);
					$("#vopsName").text(data[0].ops_name);
					$("#visSusNo").text(data[0].is_sus_no);
					$("#visName").text(data[0].is_name);
					$("#vmilSusNo").text(data[0].mil_sus_no);
					$("#vmilName").text(data[0].mil_name);
					$("#vtechSusNo").text(data[0].techcont_sus_no);
					$("#vtechName").text(data[0].techcont_name);
					$("#vdiscpSusNo").text(data[0].discp_sus_no);
					$("#vdiscpName").text(data[0].discp_name);			
					$("#vlocalAdmSusNo").text(data[0].local_adm_sus_no);
					$("#vlocalAdmName").text(data[0].local_adm_name);
					$("#vfdate").text(data[0].from_date);
					$("#vtodate").text(data[0].to_date);					

				}
			}
		});
	}

	$(function() {
		$("#sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = $("#sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
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
					}, function(data) {
					}).done(function(data) {
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						$("#unit_name").val(dec(enc, data[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		// Source Unit Name

		$("#unit_name")
				.keypress(
						function() {
							var unit_name = this.value;
							var susNoAuto = $("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
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
															}, function(data) {
															})
													.done(
															function(data) {
																var length = data.length - 1;
																var enc = data[length]
																		.substring(
																				0,
																				16);
																$("#sus_no")
																		.val(
																				dec(
																						enc,
																						data[0]));
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
<script>

	function clearAll() {
		var tab = $("#getSearchReport > tbody");
		tab.empty();
		if (document.getElementById('tableshow').style.display == 'block') {
			document.getElementById('tableshow').style.display = 'none';
		}
		localStorage.clear();
		localStorage.Abandon();
	}	
	function Search() {
		$("#sus_no2").val($("#sus_no").val());
		$("#unit_name2").val($("#unit_name").val());
		$("#command_name2").val($("#command_name").val());		
		$("#to_date2").val($("#to_date").val());	
		$("#issue_date2").val($("#issue_date").val());
		$("#status2").val($("#status").val());
		$("#parent_arm2").val($("#parent_arm").val());
		$("#WaitLoader").show();
		document.getElementById('searchForm').submit();
	}

	function send_action() {
		if (confirm('I certify that the above data are true to the best of my knowledge.\nAre You Sure you want to Distribute Comd and Cont Inst ?')) {
			$("#WaitLoader").show();
			//debugger; // Remove or uncomment for debugging as needed

			var selectedData = window.selectedCompleteData || findselected();

			if (selectedData && selectedData.length > 0) {
				$
						.ajax({
							type : 'POST',
							url : "distributeComdAndContAction?" + key + "="
									+ value, // Ensure key and value are defined
							data : {
								selectedData : JSON.stringify(selectedData),
								checkboxIds : $("#nrSrcSel").val(),
								statusValues : $("#statushid").val()
							},
							success : function(response) {
								Search(); // Assuming Search is defined elsewhere
								alert(response);
								$('#btn_app').prop('disabled', false);
								$("#WaitLoader").hide(); // Hide loader on success
							},
							error : function(xhr, status, error) {
								console.error("Error in AJAX call:", error);
								alert("An error occurred while processing your request.");
								$("#WaitLoader").hide();
								$('#btn_app').prop('disabled', false);
							}
						});
			} else {
				alert("Please Select at least one Release Order.");
				$("#WaitLoader").hide();
				$('#btn_app').prop('disabled', false);
			}
		} else {
			return false;
		}
	}

	function setselectall() {
		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		findselected();
	}

	function findselected() {
		// Get checked checkbox IDs (unchanged)
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(',');
		$("#nrSrcSel").val(b);
		$('#tregn').text(nrSel.length);

		// Get checkbox names (unchanged)
		var nrSel1 = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('name');
		}).get();

		var b1 = nrSel1.join(',');
		$("#statushid").val(b1);

		// Collect only value and dropdownValue
		var selectedData = $('.nrCheckBox:checkbox:checked').map(function() {
			var row = $(this).closest('tr');
			var dropdownValue = row.find('select').val();
			return {
				value : $(this).val(),
				dropdownValue : dropdownValue
			};
		}).get();

		window.selectedCompleteData = selectedData;
		//$("#selectedDataInput").val(JSON.stringify(selectedData)); //Likely unnecessary now

		return selectedData;
	}
	function reject_action() {
		  if (confirm('Are You Sure you want to Reject Distribute Comd and Cont Inst ?')) {
		        $("#WaitLoader").show();	    

		        var selectedData = window.selectedCompleteData || findselected();

		        if (selectedData && selectedData.length > 0) {
		            $.ajax({
		                type: 'POST',
		                url: "rejectDisributeComdAndContInstAction?" + key + "=" + value, 
		                data: {
		                    selectedData: JSON.stringify(selectedData),
		                    checkboxIds: $("#nrSrcSel").val(),
		                    statusValues: $("#statushid").val()
		                },
		                success: function(response) {
		                    Search(); 
		                    alert(response);
		                    $('#btn_app').prop('disabled', false);
		                    $("#WaitLoader").hide();
		                },
		                error: function(xhr, status, error) {
		                    console.error("Error in AJAX call:", error);
		                    alert("An error occurred while processing your request.");
		                    $("#WaitLoader").hide();
		                    $('#btn_app').prop('disabled', false);
		                }
		            });
		        } else {
		            alert("Please Select at least one Record.");
		            $("#WaitLoader").hide();
		            $('#btn_app').prop('disabled', false);
		        }
		    } else {
		        return false;
		    }

	}

	Date.prototype.addMonths = function(value) {
		var n = this.getDate();
		this.setDate(1);
		this.setMonth(this.getMonth() + value);
		this.setDate(Math.min(n, this.getDaysInMonth()));

		var datestring = this.getFullYear() + "-"
				+ ("0" + (this.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + this.getDate()).slice(-2);
		return datestring;
	};
	Date.isLeapYear = function(year) {
		return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
	};

	Date.getDaysInMonth = function(year, month) {
		return [ 31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31,
				30, 31, 30, 31 ][month];
	};

	Date.prototype.isLeapYear = function() {
		return Date.isLeapYear(this.getFullYear());
	};

	Date.prototype.getDaysInMonth = function() {
		return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
	};
</script>
<script>
/* 	function setselectall() {

		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		findselected();
	} */
	
	// Function to handle select all checkbox



/* 	function findselected() {

		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(',');
		$("#nrSrcSel").val(b);
		$('#tregn').text(nrSel.length);
		var nrSel1 = $('.nrCheckBox:checkbox:checked').map(function() {
			$(this).attr('background-color', 'yellow');
			return $(this).attr('name');
		}).get();
		var b1 = nrSel1.join(',');
		$("#statushid").val(b1);

	} */
	

	function validate() {
		if ($("#issue_date").val() == "") {
			alert("Please Enter the From date.");
			$("#issue_date").focus();
			return false;
		}
		if ($("#status").val() == "") {
			alert("Please Select Status.");
			$("#status").focus();
			return false;
		}
	}
</script>
<script>



	//Function to convert dd-mm-yyyy to a Date object 
	  function createValidDate(dateStr) {
	      const parts = dateStr.split('-');
	      if (parts.length !== 3) return null; // Check if the format is correct
	      const day = parseInt(parts[0], 10);
	      const month = parseInt(parts[1], 10) - 1; // Month is 0-based in JavaScript
	      const year = parseInt(parts[2], 10);

	      // Create a new Date object
	      const date = new Date(year, month, day);
	      return isNaN(date.getTime()) ? null : date; // Return null if the date is invalid
	  }
</script>


