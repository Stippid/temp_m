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
<!--  Created By Mitesh  -->
<form:form name="add_tail_noRPAS" id="add_tail_noRPAS"
	action="#" method="post" class="form-horizontal"
	commandName="edit_RPAStail_noCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT TAIL NO HISTORY</h5>
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
									<input type="text" id="std_nomen" name="std_nomen" placeholder=""
										class="form-control autocomplete" value="${edit_RPAStail_noCMD.std_nomclature}" autocomplete="off" readonly>
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
										class="form-control autocomplete"  value="${edit_RPAStail_noCMD.tail_no}"  oninput="handleInput(this)" autocomplete="off" readonly>
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
									<input type="text" id="sus_no" value='' name="sus_no"
										class="form-control autocomplete" maxlength="8"
										autocomplete="off" readonly>
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
										autocomplete="off" maxlength="100" readonly></textarea>
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
										<option value="WANKEL AR68-1000">WANKEL AR68-1000</option>
										<option value="ROTAX 914">ROTAX 914</option>
										<option value="ROTAX 916">ROTAX 916</option>
										<option value="TAE 125-02-114P">TAE 125-02-114P</option>
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
									<input type="text" id="eng_hrs" name="eng_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="eng_minutes" name="eng_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="af_hrs" name="af_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="af_minutes" name="af_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="hrs_left" name="hrs_left"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="minutes_left" name="minutes_left"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="mon_hrs" name="mon_hrs"
									placeholder="Hours" oninput="handleInput1(this)"  class="form-control">
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
									<input type="text" id="mon_minutes" name="mon_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="qtrly_hrs" name="qtrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="qtrly_minutes" name="qtrly_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="hyrly_hrs" name="hyrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="hyrly_minutes" name="hyrly_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="yrly_hrs" name="yrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="yrly_minutes" name="yrly_minutes"
									placeholder="Minutes"  oninput="handleInput1(this); limitInput(this);" class="form-control">
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
					<a class="btn btn-success" onclick='submitData()'>Update</a>
					<a href="Search_tail_no" type="reset"
						class="btn btn-success">  Back  </a>
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
		   function getEmptyIfNull(value) {
			    return value == null || value === "" ? "" : value;
			}
		   
		   $("#tail_no").val('${edit_RPAStail_noCMD.tail_no}');
		   var tail_no = $("#tail_no").val();

		   if (tail_no != "") {
			   $.post("getRPAStailNODetails?" + key + "=" + value, {
				    tail_no: tail_no
				}).done(function(j) {
				    var length = j.length - 1;
				    var encKey = j[length][0].substring(0, 16);

				    // Decrypt values and handle null/empty after decryption
				    var EngSerNo = getEmptyIfNull(dec(encKey, j[0][0]));
				    var EngHrs = getEmptyIfNull(dec(encKey, j[0][1]));
				    var engName = getEmptyIfNull(dec(encKey, j[0][3]));
				    var dateOfAcceptanceOsft = getEmptyIfNull(dec(encKey, j[0][4]));
				    var EngInstalledDate = getEmptyIfNull(dec(encKey, j[0][5]));
				    var afHrs = getEmptyIfNull(dec(encKey, j[0][6]));
				    var hrsLeft = getEmptyIfNull(dec(encKey, j[0][7]));
				    var hrsMonthly = getEmptyIfNull(dec(encKey, j[0][8]));
				    var hrsQtrly = getEmptyIfNull(dec(encKey, j[0][9]));
				    var hrsHalfYear = getEmptyIfNull(dec(encKey, j[0][10]));
				    var hrsQtrlyFlow = getEmptyIfNull(dec(encKey, j[0][11]));
				    var nextInsp = getEmptyIfNull(dec(encKey, j[0][12]));
				    var remarks = getEmptyIfNull(dec(encKey, j[0][13]));
				    var susNo = getEmptyIfNull(dec(encKey, j[0][14]));

				    function splitTime(time) {
				        var timeParts = time.split(":");
				        var leftValue = timeParts[0];
				        var rightValue = timeParts[1];
				        return { hours: leftValue, minutes: rightValue };
				    }
				    
				    // Split time values as usual
				    var EngHrsParts = splitTime(EngHrs);
				    var afHrsParts = splitTime(afHrs);
				    var hrsLeftParts = splitTime(hrsLeft);
				    var hrsMonthlyParts = splitTime(hrsMonthly);
				    var hrsQtrlyParts = splitTime(hrsQtrly);
				    var hrsHalfYearParts = splitTime(hrsHalfYear);
				    var hrsQtrlyFlowParts = splitTime(hrsQtrlyFlow);

				    // Populate input fields
				    $("#eng_hrs").val(getEmptyIfNull(EngHrsParts.hours));
				    $("#eng_minutes").val(getEmptyIfNull(EngHrsParts.minutes));

				    $("#af_hrs").val(getEmptyIfNull(afHrsParts.hours));
				    $("#af_minutes").val(getEmptyIfNull(afHrsParts.minutes));

				    $("#hrs_left").val(getEmptyIfNull(hrsLeftParts.hours));
				    $("#minutes_left").val(getEmptyIfNull(hrsLeftParts.minutes));

				    $("#mon_hrs").val(getEmptyIfNull(hrsMonthlyParts.hours));
				    $("#mon_minutes").val(getEmptyIfNull(hrsMonthlyParts.minutes));

				    $("#qtrly_hrs").val(getEmptyIfNull(hrsQtrlyParts.hours));
				    $("#qtrly_minutes").val(getEmptyIfNull(hrsQtrlyParts.minutes));

				    $("#hyrly_hrs").val(getEmptyIfNull(hrsHalfYearParts.hours));
				    $("#hyrly_minutes").val(getEmptyIfNull(hrsHalfYearParts.minutes));

				    $("#yrly_hrs").val(getEmptyIfNull(hrsQtrlyFlowParts.hours));
				    $("#yrly_minutes").val(getEmptyIfNull(hrsQtrlyFlowParts.minutes));

				    $("#eng_ser_no").val(getEmptyIfNull(EngSerNo));
				    $("#eng_type").val(getEmptyIfNull(engName));
				    $("#date_of_aos").val(getEmptyIfNull(dateOfAcceptanceOsft));
				    $("#eng_date").val(getEmptyIfNull(EngInstalledDate));
				    $("#next_insp").val(getEmptyIfNull(nextInsp));
				    $("#remarks").val(getEmptyIfNull(remarks));
				    $("#sus_no").val(getEmptyIfNull(susNo));

				    if (susNo !== "") {
				        $.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
				            sus_no: susNo
				        }).done(function(j) {
				            var length = j.length - 1;
				            var enc = j[length].substring(0, 16);
				            jQuery("#unit_name").val(dec(enc, j[0]));
				        }).fail(function(xhr, textStatus, errorThrown) {
				            console.error("Error fetching unit name:", errorThrown);
				        });
				    } else {
				        document.getElementById('sus_no').removeAttribute('readonly');
				        document.getElementById('unit_name').removeAttribute('readonly');
				    }

				}).fail(function(xhr, textStatus, errorThrown) {
				    console.error("Error fetching aircraft details:", errorThrown);
				});
		   }
		   
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
<script>
function handleInput1(input) {
    input.value = input.value.toUpperCase();

    const regex = /[^0-9]/g; // Allow only digits (0-9)

    input.value = input.value.replace(regex, '');
}
</script>
<script type="text/javascript">
function submitData() {
    // Call the new validate function
    if (!validate()) {
        return false;  // Prevent form submission if validation fails
    }

    // Proceed with AJAX request if validation passes
    var std_nomen = $("#std_nomen").val();
    var tail_no = $("#tail_no").val();
    var unit_sus_no = $("#sus_no").val();
    var unit_name = $("#unit_name").val();
    var eng_type = $("#eng_type").val();
    var eng_ser_no = $("#eng_ser_no").val();
    var eng_hrs = $("#eng_hrs").val();
    var eng_minutes = $("#eng_minutes").val();
    var eng_date = $("#eng_date").val();
    var date_of_aos = $("#date_of_aos").val();
    var af_hrs = $("#af_hrs").val();
    var af_minutes = $("#af_minutes").val();
    var hrs_left = $("#hrs_left").val();
    var minutes_left = $("#minutes_left").val();
    var mon_hrs = $("#mon_hrs").val();
    var mon_minutes = $("#mon_minutes").val();
    var qtrly_hrs = $("#qtrly_hrs").val();
    var qtrly_minutes = $("#qtrly_minutes").val();
    var hyrly_hrs = $("#hyrly_hrs").val();
    var hyrly_minutes = $("#hyrly_minutes").val();
    var yrly_hrs = $("#yrly_hrs").val();
    var yrly_minutes = $("#yrly_minutes").val();
    var next_insp = $("#next_insp").val();
    var remarks = $("#remarks").val();

    jQuery.ajax({
        type: 'POST',
        url: "updateRPAStailNoData?" + key + "=" + value, 
        data: {
            std_nomen: std_nomen,
            tail_no: tail_no,
            unit_sus_no: unit_sus_no,
            unit_name: unit_name,
            eng_type: eng_type,
            eng_ser_no: eng_ser_no,
            eng_hrs: eng_hrs,
            eng_minutes: eng_minutes,
            eng_date: eng_date,
            date_of_aos: date_of_aos,
            af_hrs: af_hrs,
            af_minutes: af_minutes,
            hrs_left: hrs_left,
            minutes_left: minutes_left,
            mon_hrs: mon_hrs,
            mon_minutes: mon_minutes,
            qtrly_hrs: qtrly_hrs,
            qtrly_minutes: qtrly_minutes,
            hyrly_hrs: hyrly_hrs,
            hyrly_minutes: hyrly_minutes,
            yrly_hrs: yrly_hrs,
            yrly_minutes: yrly_minutes,
            next_insp: next_insp,
            remarks: remarks
        },
        success: function(response) {
            if (response === "Data Updated Successfully.") {
                alert("Data Updated successfully!");
            } else {
                alert(response);  
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error("AJAX request failed:", textStatus, errorThrown);  
            alert("An error occurred while updating data.");
        }
    });
}
</script>
