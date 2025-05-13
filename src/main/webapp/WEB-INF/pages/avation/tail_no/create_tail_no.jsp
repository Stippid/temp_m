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
<form:form name="add_tail_no" id="add_tail_no"
	action="add_tail_noAction" method="post" class="form-horizontal"
	commandName="add_tail_noCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>TAIL NO HISTORY FOR ALH/LCH/APACHE</h5>
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
										class="form-control autocomplete"   oninput="handleInput(this)" autocomplete="off">
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
										style="color: red;">* </strong>Aircraft Utilization</label>
								</div>
								<div class="col-md-8">
									<select id="aircraft_type" name="aircraft_type" class="form-control">
										<option value="">--Select--</option>
										<option value="0">MR & SOW</option>
										<option value="1">OTHER</option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
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
										style="color: red;">* </strong>ENG Type</label>
								</div>
								<div class="col-md-8">
									<select id="eng_type" name="eng_type" class="form-control">
										<option value="">--Select--</option>
										<option value="Artouste IIIB/B1">Artouste IIIB/B1</option>
										<option value="TM333 2M2">TM333 2M2</option>
										<option value="TM333 2B2 (Turbomeca)">TM333 2B2 (Turbomeca)</option>
										<option value="Shakti 1H1 Ardiden (Turbomeca)">Shakti 1H1 Ardiden (Turbomeca)</option>
										<option value="T700-GE-701D">T700-GE-701D</option>
									</select>
								</div>
							</div>
						</div>
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Date of
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
										style="color: red;">* </strong>LH-ENG Ser No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="lh_eng_ser_no" name="lh_eng_ser_no"
										placeholder="" class="form-control autocomplete" oninput="handleInput(this)"
										autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RH-ENG Ser No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="rh_eng_ser_no" name="rh_eng_ser_no" 
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>LH-ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="lh_eng_hrs" name="lh_eng_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>LH-ENG Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="lh_eng_minutes" name="lh_eng_minutes"
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
										style="color: red;">* </strong>RH-ENG Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="rh_eng_hrs" name="rh_eng_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RH-ENG Minutes</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="rh_eng_minutes" name="rh_eng_minutes"
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
										style="color: red;">* </strong>LH-ENG Installed Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="lh_eng_date" name="lh_eng_date" placeholder=""
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RH-ENG Installed Date</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="rh_eng_date" name="rh_eng_date" placeholder=""
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
										style="color: red;">* </strong>AF Hrs</label>
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
										style="color: red;">* </strong>AF Minutes</label>
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
										style="color: red;">* </strong>HrsLeft<br><span style="font-size: 10px;font-size:12px;color:red">(For Next INSP)</span></label>
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
										style="color: red;">* </strong>MinutesLeft<br><span style="font-size: 10px;font-size:12px;color:red">(For Next INSP)</span></label>
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
										style="color: red;">* </strong>Monthly flg Hrs</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="mon_hrs" name="mon_hrs"
									placeholder="Hours" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Monthly flg Minutes</label>
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
										style="color: red;">* </strong>Qtrly flg Hrs</label>
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
										style="color: red;">* </strong>Qtrly flg Minutes</label>
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
										style="color: red;">* </strong>H/Yrly flg Hrs</label>
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
										style="color: red;">* </strong>H/Yrly flg Minutes</label>
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
										style="color: red;">* </strong>Yrly flg Hrs</label>
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
										style="color: red;">* </strong>Yrly flg Minutes</label>
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
											style="color: red;">*</strong> Next INSP</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="next_insp" name="next_insp"
											style="font-family: 'FontAwesome', Arial;"
											class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
										style="color: red;">* </strong>Remarks </label>
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
    if ($("#std_nomen").val() == "") {
        alert("Please select the Type Of Aircraft.");
        return false;
    }
    if ($("#tail_no").val() == "") {
        alert("Please enter the Tail No.");
        return false;
    }
    if ($("#sus_no").val() == "") {
        alert("Please enter the Sus No.");
        return false;
    }
    if ($("#unit_name").val() == "") {
        alert("Please enter the Unit Name.");
        return false;
    }
    if ($("#aircraft_type").val() == "") {
        alert("Please select Aircraft Utilization.");
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
    if ($("#eng_type").val() == "") {
        alert("Please select the Engine Type.");
        return false;
    }
    if ($("#date_of_aos").val() == "") {
        alert("Please enter the Date of Acceptance/OSFT.");
        return false;
    }
    if ($("#lh_eng_ser_no").val() == "") {
        alert("Please enter the LH-ENG Ser No.");
        return false;
    }
    if ($("#rh_eng_ser_no").val() == "") {
        alert("Please enter the RH-ENG Ser No.");
        return false;
    }
    if ($("#lh_eng_hrs").val() == "") {
        alert("Please enter the LH-ENG Hrs.");
        return false;
    }
    if ($("#lh_eng_minutes").val() == "") {
        alert("Please enter the LH-ENG Minutes.");
        return false;
    }
    if ($("#rh_eng_hrs").val() == "") {
        alert("Please enter the RH-ENG Hrs.");
        return false;
    }
    if ($("#rh_eng_minutes").val() == "") {
        alert("Please enter the RH-ENG Minutes.");
        return false;
    }
    if ($("#lh_eng_date").val() == "") {
        alert("Please enter the LH-ENG Installed Date.");
        return false;
    }
    if ($("#rh_eng_date").val() == "") {
        alert("Please enter the RH-ENG Installed Date.");
        return false;
    }
    if ($("#af_hrs").val() == "") {
        alert("Please enter AF Hrs.");
        return false;
    }
    if ($("#af_minutes").val() == "") {
        alert("Please enter AF Minutes.");
        return false;
    }
    if ($("#hrs_left").val() == "") {
        alert("Please enter HrsLeft for next inspection.");
        return false;
    }
    if ($("#minutes_left").val() == "") {
        alert("Please enter MinutesLeft for next inspection.");
        return false;
    }
    if ($("#mon_hrs").val() == "") {
        alert("Please enter Monthly flg Hrs.");
        return false;
    }
    if ($("#mon_minutes").val() == "") {
        alert("Please enter Monthly flg Minutes.");
        return false;
    }
    if ($("#qtrly_hrs").val() == "") {
        alert("Please enter Qtrly flg Hrs.");
        return false;
    }
    if ($("#qtrly_minutes").val() == "") {
        alert("Please enter Qtrly flg Minutes.");
        return false;
    }
    if ($("#hyrly_hrs").val() == "") {
        alert("Please enter H/Yrly flg Hrs.");
        return false;
    }
    if ($("#hyrly_minutes").val() == "") {
        alert("Please enter H/Yrly flg Minutes.");
        return false;
    }
    if ($("#yrly_hrs").val() == "") {
        alert("Please enter Yrly flg Hrs.");
        return false;
    }
    if ($("#yrly_minutes").val() == "") {
        alert("Please enter Yrly flg Minutes.");
        return false;
    }
    if ($("#next_insp").val() == "") {
        alert("Please enter the Next INSP.");
        return false;
    }
    if ($("#remarks").val() == "") {
        alert("Please enter the Remarks.");
        return false;
    }

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
<script type="text/javascript">
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
