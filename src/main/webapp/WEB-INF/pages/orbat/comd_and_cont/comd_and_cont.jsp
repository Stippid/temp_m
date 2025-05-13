<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="comd_cont" id="comd_cont"
	action="comd_cont_action?${_csrf.parameterName}=${_csrf.token}"
	method='POST' commandName="ComdAndContInstCMD"
	enctype="multipart/form-data">

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">


					<div class="card-header">
						<h5>
							<b>CAPTURE COMD AND CONT INST</b><br>
						</h5>
						<h6>
							<span style="font-size: 12px; color: red;">(To be entered
								by Comd HQ)</span>
						</h6>
						<strong></strong>
					</div>


					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Auth Letter No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="letter_no" name="letter_no"
											maxlength="250" autocomplete="off"
											placeholder="Auth Letter No" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;"></strong> Auth Letter Date </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="auth_letterdate" name="auth_letterdate"
											class="form-control" max="${date}">
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong> DETAILS OF CAPTURE COMD AND CONT INST </strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="editId" name="editId"
											class="form-control" value="0"> <input type="text"
											id="unit_posted_to" name="unit_posted_to" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong>Unit SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_sus_no" name="unit_sus_no"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"> <strong
											style="color: red;">*</strong> Loc
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="code" name="code" maxlength="5"
											class="form-control" style="width: 100%;"> <input
											type="text" id="loc" name="loc" maxlength="400"
											class="form-control"
											style="width: 86%; display: inline-block;"
											readonly="readonly"> <i class="fa fa-search"
											onclick="openLocLOV();"></i>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">NRS</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="nrs_code" name="nrs_code"
											maxlength="5" class="form-control"> <input
											type="text" id="nrs_name" name="nrs_name" maxlength="400"
											class="form-control" readonly="readonly">
									</div>
								</div>
							</div>
						</div>





						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> OPS Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name2" name="unit_name2"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> OPS SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no2" name="sus_no2" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>


						<!-- Level 3 -->


						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> IS Name</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name3" name="unit_name3"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> IS SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no3" name="sus_no3" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> MIL</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name6" name="unit_name6"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> MIL SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no6" name="sus_no6" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<!-- Tech Cont -->

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"></strong> Tech Cont</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name7" name="unit_name7"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Tech Cont SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no7" name="sus_no7" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Discp</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name4" name="unit_name4"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Discp SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no4" name="sus_no4" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>


						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Local Adm</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_name5" name="unit_name5"
											maxlength="100" style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search Unit Name"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Local Adm SUS No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no5" name="sus_no5" maxlength="100"
											style="font-family: 'FontAwesome', Arial;"
											placeholder="&#xF002; Search SUS No"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>



						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong>From Date </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="from_date" name="from_date"
											maxlength="250" placeholder="" class="form-control">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong
											style="color: red;"></strong> To Date </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="to_date" name="to_date"
											class="form-control" max="${date}">
									</div>
								</div>
							</div>
						</div>

						<div id="sdmoveid" style="display: none;">
							<div class="col-md-12">

								<div class="col col-md-1">
									<label class="form-control-label"><strong>SD
											Move</strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label"><strong>Command
											Name</strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label"><strong>Corps
											Name</strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label"><strong>Div
											Name</strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label"><strong>Bde
											Name</strong></label>
								</div>

							</div>
							<div class="col-md-12">

								<div class="col col-md-1">
									<label class="form-control-label"><strong></strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label" id="cmdName"><strong></strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label" id="corpsName"><strong></strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label" id="divName"><strong></strong></label>
								</div>

								<div class="col col-md-3">
									<label class="form-control-label" id="bdeName"><strong></strong></label>
								</div>

							</div>
						</div>


					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear"
							id="clearbtn"> <input type="button"
							class="btn btn-primary btn-sm" value="Save" id="savebtn"
							onclick="comd_Save_fn();">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
	$(function() {
		$("#editId").val('${editId}')
		if ('${editId}' > 0) {
			viewComdAndContDetails('${editId}');
		}
	});

	//Unit SUS No

	$("#unit_sus_no")
			.keyup(
					function() {
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(request, response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
															+ key + "=" + value,
													data : {
														sus_no : sus_no
													},
													success : function(data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval.push(dec(
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
									change : function(event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit SUS No.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto.val("");
											susNoAuto.focus();
											return false;
										}
									},
									select : function(event, ui) {
										var sus_no = ui.item.value;
										viewSdMoveDetails(sus_no);
										$.post(
												"getTargetUnitNameList?" + key
														+ "=" + value, {
													sus_no : sus_no
												}, function(j) {

												}).done(
												function(j) {
													var length = j.length - 1;
													var enc = j[length]
															.substring(0, 16);
													$("#unit_posted_to").val(
															dec(enc, j[0]));

												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});

	// unit name
	$("input#unit_posted_to").keypress(function() {
		var unit_name = this.value;
		var susNoAuto = $("#unit_posted_to");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList?" + key + "=" + value,
					data : {
						unit_name : unit_name
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
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
					alert("Please Enter Approved Unit Name.");
					document.getElementById("unit_posted_to").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var unit_name = ui.item.value;

				$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
					target_unit_name : unit_name
				}, function(data) {
				}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#unit_sus_no").val(dec(enc, data[0]));
					viewSdMoveDetails(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	});

	$('input[id^="sus_no"]').each(
			function() {
				var susNoField = $(this);
				var currentIndex = susNoField.attr('id').replace('sus_no', '');
				var unitnameField = document.getElementById('unit_name'
						+ currentIndex);

				susNoField.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getSusNoActiveListForComdAndCont?" + key
									+ "=" + value,
							data : {
								sus_no : susNoField.val()
							},
							success : function(data) {
								var susval = [];
								var length = data.length - 1;
								var enc = data[length].substring(0, 16);
								for (var i = 0; i < data.length; i++) {
									susval.push(dec(enc, data[i]));
								}
								var dataCountry1 = susval.join("|");
								var myResponse = [];
								$.each(dataCountry1.toString().split("|"),
										function(i, e) {
											var newE = e.substring(0,
													susNoField.val().length);
											if (e.toLowerCase().includes(
													susNoField.val()
															.toLowerCase())) {
												myResponse.push(e);
											}
										});
								response(myResponse);
							}
						});
					},
					minLength : 1,
					autoFill : true,
					select : function(event, ui) {
						var sus_no = ui.item.value;
						$.post("getTargetUnitNameList?" + key + "=" + value, {
							sus_no : sus_no
						}, function(j) {
							var length = j.length - 1;
							var enc = j[length].substring(0, 16);
							unitnameField.value = dec(enc, j[0]);
						}).fail(function(xhr, textStatus, errorThrown) {
						});
					}
				});
			});

	// unit name
	$('input[id^="unit_name"]').keypress(
			function() {
				var unit_name = this.value;
				var susNoAuto = $('input[id^="unit_name"]');
				var currentIndex = this.id.replace('unit_name', '');
				var susNoField = document.getElementById('sus_no'
						+ currentIndex);
				susNoAuto.autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'POST',
							url : "getUnitsNameActiveListForComdAndCont?" + key
									+ "=" + value,
							data : {
								unit_name : unit_name
							},
							success : function(data) {
								var susval = [];
								var length = data.length - 1;
								var enc = data[length].substring(0, 16);
								for (var i = 0; i < data.length; i++) {
									susval.push(dec(enc, data[i]));
								}

								response(susval);
							}
						});
					},
					minLength : 1,
					autoFill : true,

					select : function(event, ui) {
						var unit_name = ui.item.value;
						$.post("getTargetSUSFromUNITNAME?" + key + "=" + value,
								{
									target_unit_name : unit_name
								}, function(data) {
								}).done(function(data) {
							var length = data.length - 1;
							var enc = data[length].substring(0, 16);
							susNoField.value = dec(enc, data[0]);
						}).fail(function(xhr, textStatus, errorThrown) {
						});
					}
				});
			});

	/* function getSdMove(){
	 $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
	 }).done(function(data) {
	 var length = data.length-1;
	 var enc = data[length].substring(0,16);
	 susNoField.value = dec(enc,data[0]);
	 }).fail(function(xhr, textStatus, errorThrown) {
	 });
	 } */

	function comd_Save_fn() {
		if ($("#letter_no").val() == "") {
			alert("Please Select Auth Letter No");
			$("#letter_no").focus();
			return false;
		}

		if ($("#auth_letterdate").val() == "") {
			alert("Please Select Auth Letter Date");
			$("#auth_letterdate").focus();
			return false;
		}

		if ($("#unit_posted_to").val() == "") {
			alert("Please Select Unit Name");
			$("#unit_posted_to").focus();
			return false;
		}

		if ($("#code").val() == "") {
			alert("Please Select Loc Code");
			$("#code").focus();
			return false;
		}

		if ($("#unit_name2").val() == "") {
			alert("Please Select OPS Name");
			$("#unit_name2").focus();
			return false;
		}

		if ($("#unit_name3").val() == "") {
			alert("Please Select IS Name");
			$("#unit_name3").focus();
			return false;
		}
		if ($("#unit_name6").val() == "") {
			alert("Please Select MIL Name");
			$("#unit_name6").focus();
			return false;
		}

		if ($("#unit_name4").val() == "") {
			alert("Please Select Discp Name.");
			$("#unit_name4").focus();
			return false;
		}

		if ($("#unit_name5").val() == "") {
			alert("Please Select Local Adm Name");
			$("#unit_name5").focus();
			return false;
		}

		/* 	
		 if($("#to_date").val() == ""){
		 alert("Please Select To Date");
		 $("#to_date").focus();
		 return false;
		 } */

		if ($("#from_date").val() == "") {
			alert("Please Select From Date");
			$("#from_date").focus();
			return false;
		}

		var letter_no = $('#letter_no').val();
		var letter_date = $('#auth_letterdate').val();
		var unit_sus_no = $('#unit_sus_no').val();
		var loc_code = $('#code').val();
		var nrs_code = $('#nrs_code').val();
		var ops_sus_no = $('#sus_no2').val();
		var is_sus_no = $('#sus_no3').val();
		var mil_sus_no = $('#sus_no6').val();
		var tech_cont_sus_no = $('#sus_no7').val();
		var discp_sus_no = $('#sus_no4').val();
		var local_adm_sus_no = $('#sus_no5').val();
		var from_date = $('#from_date').val();
		var to_date = $('#to_date').val();
		var editId = $('#editId').val();
		$('#savebtn').prop('disabled', true);
		$.post('comd_cont_action?' + key + "=" + value, {
			letter_no : letter_no,
			letter_date : letter_date,
			unit_sus_no : unit_sus_no,
			loc_code : loc_code,
			nrs_code : nrs_code,
			ops_sus_no : ops_sus_no,
			is_sus_no : is_sus_no,
			discp_sus_no : discp_sus_no,
			mil_sus_no : mil_sus_no,
			tech_cont_sus_no : tech_cont_sus_no,
			local_adm_sus_no : local_adm_sus_no,
			from_date : from_date,
			to_date : to_date,
			editId : editId
		}, function(data) {
			if (data == "Data Updated Successfully.") {
				alert(data);
				window.location.href = 'search_comd_and_contUrl';
			} else if (data == "Data Saved Successfully.") {
				$('#savebtn').prop('disabled', false);
				alert(data);
				clearField();
			} else {
				alert(data);
				$('#savebtn').prop('disabled', false);
			}

		}).fail(function(xhr, textStatus, errorThrown) {
			alert("An error occurred while saving the data.");
		});

	}

	var popupWindow = null
	function openLocLOV(url) {
		popupWindow = window
				.open(
						"locationLOV",
						"_blank",
						"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
	}

	function HandlePopupResult(loc, nrs_name, loc_code, trn_type, typeofloc,
			nrscode) {
		$("#code").val(loc_code);
		$("#nrs_name").val(nrs_name);
		$("#modification").val(trn_type);
		$("#type_of_location").val(typeofloc);
		$("#loc").val(loc);
		$("#nrs_code").val(nrscode);
	}

	function clearField() {
		$("#sdmoveid").hide();	
		$('#unit_sus_no').val("");
		$('#code').val("");
		$('#sus_no2').val("");
		$('#sus_no3').val("");
		$('#sus_no5').val("");
		$('#sus_no4').val("");
		$('#sus_no6').val("");
		$('#sus_no7').val("");
		$('#from_date').val("");
		$('#to_date').val("");
		$('#unit_posted_to').val("");
		$('#loc').val("");
		$('#nrs_name').val("");
		$('#unit_name2').val("");
		$('#unit_name3').val("");
		$('#unit_name6').val("");
		$('#unit_name7').val("");
		$('#unit_name4').val("");
		$('#unit_name5').val("");
	}

	function viewComdAndContDetails(id) {
		jQuery.ajax({
			type : 'POST',
			url : "getComdAndContInstDetails?" + key + "=" + value,
			data : {
				id : id
			},
			success : function(data) {
				if (data.length != 0) {
					$("#letter_no").val(data[0].auth_letter_no);
					$("#auth_letterdate").val(data[0].auth_letter_date);
					$("#loc").val(data[0].loc);
					$("#code").val(data[0].loc_code);
					$("#nrs_name").val(data[0].nrs);
					$("#nrs_code").val(data[0].nrs_code);
					$("#unit_sus_no").val(data[0].unit_sus_no);
					$("#unit_posted_to").val(data[0].unit_name);
					$("#unit_sus_no").prop('disabled', true);
					$("#unit_posted_to").prop('disabled', true);
					$("#sus_no2").val(data[0].ops_sus_no);
					$("#unit_name2").val(data[0].ops_name);
					$("#sus_no3").val(data[0].is_sus_no);
					$("#unit_name3").val(data[0].is_name);
					$("#sus_no6").val(data[0].mil_sus_no);
					$("#unit_name6").val(data[0].mil_name);
					$("#sus_no7").val(data[0].techcont_sus_no);
					$("#unit_name7").val(data[0].techcont_name);
					$("#sus_no4").val(data[0].discp_sus_no);
					$("#unit_name4").val(data[0].discp_name);
					$("#sus_no5").val(data[0].local_adm_sus_no);
					$("#unit_name5").val(data[0].local_adm_name);
					$("#from_date").val(data[0].from_date);
					$("#to_date").val(data[0].to_date);
					$("#clearbtn").hide();

					document.getElementById('savebtn').value = 'Update'

				}
			}
		});
	}

	function viewSdMoveDetails(sus_no) {
		$("#sdmoveid").show();
		jQuery.ajax({
			type : 'POST',
			url : "getSdMoveDetails?" + key + "=" + value,
			data : {
				sus_no : sus_no
			},
			success : function(data) {
				if (data.length != 0) {
					$("#cmdName").text(data[0].cmd_name);
					$("#corpsName").text(data[0].coprs_name);
					$("#divName").text(data[0].div_name);
					$("#bdeName").text(data[0].bde_name);

				}
			}
		});
	}
	
	document.getElementById("clearbtn").addEventListener("click", function() {
		$("#sdmoveid").hide();
		});
</script>






