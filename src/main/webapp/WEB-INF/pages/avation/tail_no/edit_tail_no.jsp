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
<form:form name="add_tail_no" id="add_tail_no"
	action="#" method="post" class="form-horizontal"
	commandName="edit_tail_noCMD">
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
										class="form-control autocomplete" value="${edit_tail_noCMD.std_nomclature}" autocomplete="off" readonly>
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
										class="form-control autocomplete"  value="${edit_tail_noCMD.tail_no}"  oninput="handleInput(this)" autocomplete="off" readonly>
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
									<input type="text" id="lh_eng_hrs" name="lh_eng_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="lh_eng_minutes" name="lh_eng_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="rh_eng_hrs" name="rh_eng_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="rh_eng_minutes" name="rh_eng_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="af_hrs" name="af_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="af_minutes" name="af_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="hrs_left" name="hrs_left"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="minutes_left" name="minutes_left"
									placeholder="Minutes"oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="mon_hrs" name="mon_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="mon_minutes" name="mon_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="qtrly_hrs" name="qtrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="qtrly_minutes" name="qtrly_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="hyrly_hrs" name="hyrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="hyrly_minutes" name="hyrly_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
									<input type="text" id="yrly_hrs" name="yrly_hrs"
									placeholder="Hours" oninput="handleInput1(this)" class="form-control">
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
									<input type="text" id="yrly_minutes" name="yrly_minutes"
									placeholder="Minutes" oninput="handleInput1(this); limitInput(this);" class="form-control">
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
					<a class="btn btn-success" onclick='submitData()'>Update</a>
					<a href="Search_tail_no" type="reset"
						class="btn btn-success">  Back  </a>
					</div>
				</div>
			</div>
		</div>
	
</form:form>
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

		   
		   $("#tail_no").val('${edit_tail_noCMD.tail_no}');
		   var tail_no = $("#tail_no").val();

		   if (tail_no != "") {
		       $.post("gettailNODetails?" + key + "=" + value, {
		           tail_no: tail_no
		       }).done(function(j) {
		           var length = j.length - 1;
		           var encKey = j[length][0].substring(0, 16);

		           // Decrypt values and handle null/empty after decryption
		           var lhEngSerNo = getEmptyIfNull(dec(encKey, j[0][0]));
		           var rhEngSerNo = getEmptyIfNull(dec(encKey, j[0][1]));
		           var lhEngHrs = getEmptyIfNull(dec(encKey, j[0][2]));
		           var rhEngHrs = getEmptyIfNull(dec(encKey, j[0][3]));
		           var engName = getEmptyIfNull(dec(encKey, j[0][5]));
		           var dateOfAcceptanceOsft = getEmptyIfNull(dec(encKey, j[0][6]));
		           var lhEngInstalledDate = getEmptyIfNull(dec(encKey, j[0][7]));
		           var rhEngInstalledDate = getEmptyIfNull(dec(encKey, j[0][8]));
		           var afHrs = getEmptyIfNull(dec(encKey, j[0][9]));
		           var hrsLeft = getEmptyIfNull(dec(encKey, j[0][10]));
		           var hrsMonthly = getEmptyIfNull(dec(encKey, j[0][11]));
		           var hrsQtrly = getEmptyIfNull(dec(encKey, j[0][12]));
		           var hrsHalfYear = getEmptyIfNull(dec(encKey, j[0][13]));
		           var hrsQtrlyFlow = getEmptyIfNull(dec(encKey, j[0][14]));
		           var nextInsp = getEmptyIfNull(dec(encKey, j[0][15]));
		           var remarks = getEmptyIfNull(dec(encKey, j[0][16]));
		           var susNo = getEmptyIfNull(dec(encKey, j[0][17]));
		          

		           function splitTime(time) {
		               var timeParts = time.split(":");
		               var leftValue = timeParts[0];
		               var rightValue = timeParts[1];
		               return { hours: leftValue, minutes: rightValue };
		           }
		           
		           // Split time values as usual
		           var lhEngHrsParts = splitTime(lhEngHrs);
		           var rhEngHrsParts = splitTime(rhEngHrs);
		           var afHrsParts = splitTime(afHrs);
		           var hrsLeftParts = splitTime(hrsLeft);
		           var hrsMonthlyParts = splitTime(hrsMonthly);
		           var hrsQtrlyParts = splitTime(hrsQtrly);
		           var hrsHalfYearParts = splitTime(hrsHalfYear);
		           var hrsQtrlyFlowParts = splitTime(hrsQtrlyFlow);

		           // Populate input fields
		           $("#lh_eng_hrs").val(getEmptyIfNull(lhEngHrsParts.hours));
		           $("#lh_eng_minutes").val(getEmptyIfNull(lhEngHrsParts.minutes));

		           $("#rh_eng_hrs").val(getEmptyIfNull(rhEngHrsParts.hours));
		           $("#rh_eng_minutes").val(getEmptyIfNull(rhEngHrsParts.minutes));

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

		           $("#lh_eng_ser_no").val(getEmptyIfNull(lhEngSerNo));
		           $("#rh_eng_ser_no").val(getEmptyIfNull(rhEngSerNo));
		           $("#eng_type").val(getEmptyIfNull(engName));
		           $("#date_of_aos").val(getEmptyIfNull(dateOfAcceptanceOsft));
		           $("#lh_eng_date").val(getEmptyIfNull(lhEngInstalledDate));
		            $("#rh_eng_date").val(getEmptyIfNull(rhEngInstalledDate));
		           $("#next_insp").val(getEmptyIfNull(nextInsp));
		           $("#remarks").val(getEmptyIfNull(remarks));
		           $("#sus_no").val(getEmptyIfNull(susNo));
		           
		           if(susNo !="") {
		    		   $.post("getActiveUnitNameFromSusNo?" + key + "=" + value, {
		    				sus_no : susNo
		    			}).done(function(j) {
		    				var length = j.length - 1;
		    				var enc = j[length].substring(0, 16);
		    				jQuery("#unit_name").val(dec(enc, j[0]));
		    			}).fail(function(xhr, textStatus, errorThrown) {
		    			});
		    		   }
		           else{
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
    input.value = input.value.toUpperCase();

    const regex = /[^A-Z0-9\s-]/g; // Allow only uppercase letters, numbers, and spaces

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
    // Validation checks only for specific fields
    if ($("#std_nomen").val() == "") {
        alert("Please select the Type Of Aircraft.");
        return false;
    } else {
        var std_nomen = $("#std_nomen").val();  
    }

    if ($("#tail_no").val() == "") {
        alert("Please enter the Tail No.");
        return false;
    } else {
        var tail_no = $("#tail_no").val();
    }

    if ($("#sus_no").val() == "") {
        alert("Please enter the Sus No.");
        return false;
    } else {
        var unit_sus_no = $("#sus_no").val();
    }

    if ($("#unit_name").val() == "") {
        alert("Please enter the Unit Name.");
        return false;
    } else {
        var unit_name = $("#unit_name").val();
    }

    if ($("#aircraft_type").val() == "") {
        alert("Please select Aircraft Utilization.");
        return false;
    } else {
        var aircraft_type = $("#aircraft_type").val();
    }

    if ($("#eng_type").val() == "") {
        alert("Please select the Engine Type.");
        return false;
    } else {
        var eng_type = $("#eng_type").val();
    }

    if ($("#date_of_aos").val() == "") {
        alert("Please enter the Date of Acceptance/OSFT.");
        return false;
    } else {
        var date_of_aos = $("#date_of_aos").val();
    }

    if ($("#lh_eng_ser_no").val() == "") {
        alert("Please enter the LH-ENG Ser No.");
        return false;
    } else {
        var lh_eng_ser_no = $("#lh_eng_ser_no").val();
    }

    if ($("#rh_eng_ser_no").val() == "") {
        alert("Please enter the RH-ENG Ser No.");
        return false;
    } else {
        var rh_eng_ser_no = $("#rh_eng_ser_no").val();
    }

    if ($("#lh_eng_hrs").val() == "") {
        alert("Please enter the LH-ENG Hrs.");
        return false;
    } else {
        var lh_eng_hrs = $("#lh_eng_hrs").val();
    }

    if ($("#lh_eng_minutes").val() == "") {
        alert("Please enter the LH-ENG Minutes.");
        return false;
    } else {
        var lh_eng_minutes = $("#lh_eng_minutes").val();
    }

    if ($("#rh_eng_hrs").val() == "") {
        alert("Please enter the RH-ENG Hrs.");
        return false;
    } else {
        var rh_eng_hrs = $("#rh_eng_hrs").val();
    }

    if ($("#rh_eng_minutes").val() == "") {
        alert("Please enter the RH-ENG Minutes.");
        return false;
    } else {
        var rh_eng_minutes = $("#rh_eng_minutes").val();
    }

    if ($("#lh_eng_date").val() == "") {
        alert("Please enter the LH-ENG Installed Date.");
        return false;
    } else {
        var lh_eng_date = $("#lh_eng_date").val();
    }

    if ($("#rh_eng_date").val() == "") {
        alert("Please enter the RH-ENG Installed Date.");
        return false;
    } else {
        var rh_eng_date = $("#rh_eng_date").val();
    }

    if ($("#af_hrs").val() == "") {
        alert("Please enter AF Hrs.");
        return false;
    } else {
        var af_hrs = $("#af_hrs").val();
    }

    if ($("#af_minutes").val() == "") {
        alert("Please enter AF Minutes.");
        return false;
    } else {
        var af_minutes = $("#af_minutes").val();
    }

    if ($("#hrs_left").val() == "") {
        alert("Please enter HrsLeft for next inspection.");
        return false;
    } else {
        var hrs_left = $("#hrs_left").val();
    }

    if ($("#minutes_left").val() == "") {
        alert("Please enter MinutesLeft for next inspection.");
        return false;
    } else {
        var minutes_left = $("#minutes_left").val();
    }

    if ($("#mon_hrs").val() == "") {
        alert("Please enter Monthly flg Hrs.");
        return false;
    } else {
        var mon_hrs = $("#mon_hrs").val();
    }

    if ($("#mon_minutes").val() == "") {
        alert("Please enter Monthly flg Minutes.");
        return false;
    } else {
        var mon_minutes = $("#mon_minutes").val();
    }

    if ($("#qtrly_hrs").val() == "") {
        alert("Please enter Qtrly flg Hrs.");
        return false;
    } else {
        var qtrly_hrs = $("#qtrly_hrs").val();
    }

    if ($("#qtrly_minutes").val() == "") {
        alert("Please enter Qtrly flg Minutes.");
        return false;
    } else {
        var qtrly_minutes = $("#qtrly_minutes").val();
    }

    if ($("#hyrly_hrs").val() == "") {
        alert("Please enter H/Yrly flg Hrs.");
        return false;
    } else {
        var hyrly_hrs = $("#hyrly_hrs").val();
    }

    if ($("#hyrly_minutes").val() == "") {
        alert("Please enter H/Yrly flg Minutes.");
        return false;
    } else {
        var hyrly_minutes = $("#hyrly_minutes").val();
    }

    if ($("#yrly_hrs").val() == "") {
        alert("Please enter Yrly flg Hrs.");
        return false;
    } else {
        var yrly_hrs = $("#yrly_hrs").val();
    }

    if ($("#yrly_minutes").val() == "") {
        alert("Please enter Yrly flg Minutes.");
        return false;
    } else {
        var yrly_minutes = $("#yrly_minutes").val();
    }

    if ($("#next_insp").val() == "") {
        alert("Please enter the Next INSP.");
        return false;
    } else {
        var next_insp = $("#next_insp").val();
    }

    if ($("#remarks").val() == "") {
        alert("Please enter the Remarks.");
        return false;
    } else {
        var remarks = $("#remarks").val();
    }

jQuery.ajax({
    type: 'POST',
    url: "updatetailNoData?" + key + "=" + value, 
    data: {
    	std_nomen: std_nomen,
        tail_no: tail_no,
        unit_sus_no: unit_sus_no,
        unit_name: unit_name,
        aircraft_type: aircraft_type,
        eng_type: eng_type,
        date_of_aos: date_of_aos,
        lh_eng_ser_no: lh_eng_ser_no,
        rh_eng_ser_no: rh_eng_ser_no,
        lh_eng_hrs: lh_eng_hrs,
        lh_eng_minutes: lh_eng_minutes,
        rh_eng_hrs: rh_eng_hrs,
        rh_eng_minutes: rh_eng_minutes,
        lh_eng_date: lh_eng_date,
        rh_eng_date: rh_eng_date,
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

return true;
}

</script>

