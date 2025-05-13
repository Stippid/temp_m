<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<!--  Created By Mitesh  (20-11-2024) -->
<form:form name="add_tail_noCHTL" id="add_tail_noCHTL"
	action="add_tail_noCHTLAction" method="post" class="form-horizontal"
	commandName="add_tail_noCHTLCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>TAIL NO HISTORY FOR CHT/CHK/CTL</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Type Of Aircraft</label>
								</div>
								<div class="col-md-8">
									<select id="std_nomen" name="std_nomen" class="form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${ml_1}">
											<option value="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>


						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Tail No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="tail_no" name="tail_no" placeholder=""
										class="form-control autocomplete" oninput="handleInput(this)" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="sus_no" value='${sus_no}' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name"
										class="form-control autocomplete" style="font-size: 11px;"
										autocomplete="off" maxlength="100">${unit_name}</textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Country of Origin</label>
									</div>
									<div class="col-md-8">
										<select id="country_isocode" name="country_isocode" class="form-control">
										<option value="">--Select--</option>
										<c:forEach var="item" items="${GetCountry}">
											<option value="${item.isonumeric}">${item.country}</option>
										</c:forEach>
									</select>
										
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
										style="color: red;">* </strong> Purchase Cost in <i class="fa fa-inr"></i><br><span style="font-size: 10px;font-size:12px;color:red">(Rounded to Nearest Rs)</span></label>
									</div>
									<div class="col-md-8">
										<input type="number" id="purchase_cost" name="purchase_cost"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
					</div>     
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>ENG Type</label>
								</div>
								<div class="col-md-8">
									<select id="eng_type" name="eng_type" class="form-control">
										<option value="">--Select--</option>
										<option value="ARTOUSTE IIIB/B1">ARTOUSTE IIIB/B1</option>
										<option value="TM333 2M2">TM333 2M2</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>ENG Ser No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="eng_ser_no" name="eng_ser_no"
										placeholder="" class="form-control autocomplete" oninput="handleInput(this)"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
									style="color: red;">*</strong> ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="eng_hrs" name="eng_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
									style="color: red;">*</strong> ENG Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="eng_minutes" name="eng_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>ENG Installation
										Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="eng_date" name="eng_date" placeholder=""
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Date of
										Acceptance/OSFT</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="date_of_aos" name="date_of_aos"
										placeholder="" class="form-control autocomplete"
										autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>AF Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="af_hrs" name="af_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>AF Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="af_minutes" name="af_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div> 
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>HrsLeft<br><span style="font-size: 10px;font-size:12px;color:red">(For Next INSP)</span></label>
								</div>
								<div class="col-md-8">
									<input type="number" id="hrs_left" name="hrs_left"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>MinutesLeft<br><span style="font-size: 10px;font-size:12px;color:red">(For Next INSP)</span></label>
								</div>
								<div class="col-md-8">
									<input type="number" id="minutes_left" name="minutes_left"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Monthly flg Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="mon_hrs" name="mon_hrs"
									placeholder="Hours"  class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Monthly flg Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="mon_minutes" name="mon_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Qtrly flg Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="qtrly_hrs" name="qtrly_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Qtrly flg Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="qtrly_minutes" name="qtrly_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>H/Yrly flg Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="hyrly_hrs" name="hyrly_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>H/Yrly flg Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="hyrly_minutes" name="hyrly_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Yrly flg Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="yrly_hrs" name="yrly_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong>Yrly flg Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="yrly_minutes" name="yrly_minutes"
									placeholder="Minutes" oninput="limitInput(this)" class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> Next INSP </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="next_insp" name="next_insp"
											style="font-family: 'FontAwesome', Arial;"
											class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()"  autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Remarks </label>
									</div>
									<div class="col-md-8">
										<textarea class="form-control char-counter1" maxlength="255"
											id="remarks" name="remarks"></textarea>
									</div>
								</div>
							</div>
					</div>     
				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<input type="submit" class="btn btn-primary btn-sm" value="Save"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>

</form:form>

<script>
	function validate() {
		// 1. Type of Aircraft Validation
		if ($("#std_nomen").val() == "") {
			alert("Please select the Type of Aircraft.");
			return false;
		}

		// 2. Tail No Validation
		if ($("#tail_no").val() == "") {
			alert("Please enter the Tail No.");
			return false;
		}

		// 3. Sus No Validation
		if ($("#sus_no").val() == "") {
			alert("Please enter the Sus No.");
			return false;
		}

		// 4. Unit Name Validation
		if ($("#unit_name").val() == "") {
			alert("Please enter the Unit Name.");
			return false;
		}
		 if ($("#country_isocode").val() == "") {
		        alert("Please select Country.");
		        return false;
		    }
		    if ($("#purchase_cost").val() == "") {
		        alert("Please enter the purchase cost.");
		        return false;
		    }
		// 5. Engine Type Validation
		if ($("#eng_type").val() == "") {
			alert("Please select the Engine Type.");
			return false;
		}

		// 6. Engine Serial No Validation
		if ($("#eng_ser_no").val() == "") {
			alert("Please enter the Engine Serial No.");
			return false;
		}

		// 7. Engine Hours and Minutes Validation
		const engHrs = $("#eng_hrs").val();
		const engMinutes = $("#eng_minutes").val();

		// Validate if both hours and minutes are entered
		if (engHrs === "" || engMinutes === "") {
			alert("Please enter both Engine Hours and Engine Minutes.");
			return false;
		}

		// 8. Engine Installation Date Validation
		if ($("#eng_date").val() == "") {
			alert("Please select the Engine Installation Date.");
			return false;
		}

		// 9. Date of Acceptance/OSFT Validation
		if ($("#date_of_aos").val() == "") {
			alert("Please select the Date of Acceptance/OSFT.");
			return false;
		}
		// 10. AF Hours and Minutes Validation
		const afHrs = $("#af_hrs").val();
		const afMinutes = $("#af_minutes").val();

		// Validate if both hours and minutes are entered
		if (afHrs === "" || afMinutes === "") {
			alert("Please enter both AF Hours and AF Minutes.");
			return false;
		}
		
		// 11. HoursLeft and MinutesLeft Validation
		const HrsLeft = $("#hrs_left").val();
		const MinutesLeft = $("#minutes_left").val();

		// Validate if both hours and minutes are entered
		if (HrsLeft === "" || MinutesLeft === "") {
			alert("Please enter both HoursLeft and MinutesLeft.");
			return false;
		}
		
		// 12. Mon Hours and Minutes Validation
		const monHrs = $("#mon_hrs").val();
		const monMinutes = $("#mon_minutes").val();

		// Validate if both hours and minutes are entered
		if (monHrs === "" || monMinutes === "") {
			alert("Please enter both Monthly Hours and Monthly Minutes.");
			return false;
		}
		
		// 13. Qtrly Hours and Minutes Validation
		const qtrHrs = $("#qtrly_hrs").val();
		const qtrMinutes = $("#qtrly_minutes").val();

		// Validate if both hours and minutes are entered
		if (qtrHrs === "" || qtrMinutes === "") {
			alert("Please enter both Qtrly Hours and Qtrly Minutes.");
			return false;
		}
		
		// 14. HY Hours and Minutes Validation
		const hyHrs = $("#hyrly_hrs").val();
		const hyMinutes = $("#hyrly_minutes").val();

		// Validate if both hours and minutes are entered
		if (hyHrs === "" || hyMinutes === "") {
			alert("Please enter both H/Yrly Hours and H/Yrly Minutes.");
			return false;
		}
		
		// 15. Yearly Hours and Minutes Validation
		const yHrs = $("#yrly_hrs").val();
		const yMinutes = $("#yrly_minutes").val();

		// Validate if both hours and minutes are entered
		if (yHrs === "" || yMinutes === "") {
			alert("Please enter both Yrly Hours and Yrly Minutes.");
			return false;
		}
		
		// 16. Engine Serial No Validation
		if ($("#next_insp").val() == "") {
			alert("Please enter the next insp.");
			return false;
		}

		// If all validations pass, return true to submit the form
		return true;
	}
</script>

<script>
	$(document).ready(function() {
		document.addEventListener('dragenter', function(event) {
		       event.preventDefault();
		   });
	
		   document.addEventListener('dragover', function(event) {
		       event.preventDefault();
		   });

		   document.addEventListener('drop', function(event) {
		       event.preventDefault();
		   });
	});

	$(function() {
		$("#sponsoring_dte").keypress(function() {
			var sponsoring_dte = this.value;
			var sponsoring_dteAuto = $("#sponsoring_dte");
			sponsoring_dteAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getNodalDteList?" + key + "=" + value,
						data : {
							sponsoring_dte : sponsoring_dte
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
						alert("Please Enter valid Nodal Dte.");
						document.getElementById("sponsoring_dte").value = "";
						sponsoring_dteAuto.val("");
						sponsoring_dteAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var ReName = ui.item.value;

				}
			});
		});

	});

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}
</script>
<script>
	jQuery(function() {
		// Source SUS No
		jQuery("#sus_no").keypress(function() {
			var sus_no = this.value;
			var susNoAuto = jQuery("#sus_no");
			susNoAuto.autocomplete({
				source : function(request, response) {
					jQuery.ajax({
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
					}).done(function(j) {
						var length = j.length - 1;
						var enc = j[length].substring(0, 16);
						jQuery("#unit_name").val(dec(enc, j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		// End

		// Source Unit Name
		jQuery("#unit_name")
				.keyup(
						function() {
							var unit_name = this.value;
							var susNoAuto = jQuery("#unit_name");
							susNoAuto
									.autocomplete({
										source : function(request, response) {
											jQuery
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
															})
													.done(
															function(j) {
																var length = j.length - 1;
																var enc = j[length]
																		.substring(
																				0,
																				16);
																jQuery(
																		"#sus_no")
																		.val(
																				dec(
																						enc,
																						j[0]));
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
  function limitInput(input) {
    // Limit input to 2 digits (if more than 2 digits, trim to first 2 digits)
    if (input.value.length > 2) {
      input.value = input.value.slice(0, 2); // Keep only the first 2 digits
    }
    
    let numValue = parseInt(input.value, 10);
    if (numValue > 59) {
      input.value = 59; // If greater than 99, set to 99
    }
  }
</script>
<script>
// Function to block special characters and convert text to uppercase
function handleInput(input) {
    // Convert the value to uppercase
    input.value = input.value.toUpperCase();

    // Regular expression to match any special characters
    const regex = /[^A-Z0-9\s-]/g; // Allow only uppercase letters, numbers, and spaces

    // Replace special characters with an empty string
    input.value = input.value.replace(regex, '');
}
</script>
