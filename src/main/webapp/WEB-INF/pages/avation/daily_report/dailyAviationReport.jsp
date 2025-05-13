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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="dailyAviationReport" id="dailyAviationReport"
	action="daily_aviation_report_action?${_csrf.parameterName}=${_csrf.token}"
	class="form-horizontal" commandName="daily_aviation_report_cmd"
	method='POST'>
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					<div class="card-header">
						<h5>
							<b>DAILY AVIATION REPORT</b><br>
						</h5>
						<h6>
							<span style="font-size: 12px; color: red;">(To be entered
								by Unit)</span>
						</h6>
						<!-- 						<strong>Schedule Details</strong> -->
					</div>
					<div class="card-body card-block">

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"> <strong
											style="color: red;">*</strong> AC No
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="acc_no" name="acc_no" maxlength="15"
											class="form-control autocomplete" autocomplete="off"
											style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
							<input type="hidden" id="id" name="id"
								class="form-control autocomplete" value="0" autocomplete="off">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> STATUS </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="status" name="status"
											style="font-family: 'FontAwesome', Arial;"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Daily Flag Hrs As On Day 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="Falf_hrs_day" name="Falf_hrs_day"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Daily Flag Hrs As On Night 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="Falf_hrs_night" name="Falf_hrs_night"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
						</div>



						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> G Run </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="g_run" name="g_run"
											style="font-family: 'FontAwesome', Arial;"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"> <strong
											style="color: red;">*</strong> AF HRS</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="af_hrs" name="af_hrs" maxlength="250"
											class="form-control" autocomplete="off">
									</div>
								</div>
							</div>

						</div>


						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> ENG LEFT LH </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="eng_hrs_left" name="eng_hrs_left"
											maxlength="250" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> ENG LEFT RH </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="eng_hrs_rigth" name="eng_hrs_rigth" autocomplete="off"
											maxlength="250" class="form-control">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> HRS LEFT </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="hrs_left" name="hrs_left"
											maxlength="15" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong> NEXT INSP </label>
									</div>
									<div class="col-12 col-md-6">
										<input type="text" id="next_insp" name="next_insp"
											style="font-family: 'FontAwesome', Arial;"
											class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> PROG HRS FLOWN MONTHLY 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="hrs_monthly" name="hrs_monthly"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> PROG HRS FLOWN QTRLY 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="hrs_qtrly" name="hrs_qtrly"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"> <strong style="color: red;">*</strong> PROG HRS FLOWN H/YRLY
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="hrs_half_year" name="hrs_half_year"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> PROG HRS FLOWN YRLY 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="hrs_qtrly_flow" name="hrs_qtrly_flow"
											maxlength="4" class="form-control autocomplete"
											autocomplete="off" style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label">Remarks </label>
									</div>
									<div class="col-6">
										<textarea class="form-control char-counter1" maxlength="255"
											id="remarks" name="remarks"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><strong style="color: red;">*</strong> BALANCE AH ENG HRS
											
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="time" id="bal_hrs" name="bal_hrs" maxlength="4"
											class="form-control autocomplete" autocomplete="off"
											style="font-family: 'FontAwesome', Arial;">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label for="text-input" class=" form-control-label">
										<strong style="color: red;">*</strong> DT LAST FLOW 
										</label>
									</div>
									<div class="col-12 col-md-6">
										<input type="date" id="date_goi_letter" name="date_goi_letter"
											class="form-control" max='${date}'>
									</div>
								</div>
							</div>
						</div>


					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">
						<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick=" return validate();">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
	function validate() {
		$.ajaxSetup({
			async : false
		});
		var acc_no = $("#acc_no").val();
		var status = $("#status").val();
		var Falf_hrs_day = $("#Falf_hrs_day").val();
		var Falf_hrs_night = $("#Falf_hrs_night").val();

		var g_run = $("#g_run").val();
		var af_hrs = $("#af_hrs").val();
		var eng_hrs_left = $("#eng_hrs_left").val();
		var Falf_hrs_night = $("#Falf_hrs_night").val();

		var eng_hrs_rigth = $("#eng_hrs_rigth").val();
		var hrs_left = $("#hrs_left").val();
		var next_insp = $("#next_insp").val();
		var hrs_monthly = $("#hrs_monthly").val();

		var hrs_qtrly = $("#hrs_qtrly").val();
		var hrs_half_year = $("#hrs_half_year").val();
		var hrs_qtrly_flow = $("#hrs_qtrly_flow").val();
		var bal_hrs = $("#bal_hrs").val();
		var date_goi_letter = $("#date_goi_letter").val();

		if (acc_no.trim() == "") {
			alert("Please Enter AC No");
			$("input#acc_no").focus();
			return false;
		}
		if (status.trim() == "") {
			alert("Please Enter STATUS");
			$("input#status").focus();
			return false;
		}
		if (Falf_hrs_day.trim() == "") {
			alert("Please Enter Daily Flag Hrs As On Day");
			$("input#Falf_hrs_day").focus();
			return false;
		}
		if (Falf_hrs_night.trim() == "") {
			alert("Please Enter Daily Flag Hrs As On Night");
			$("input#Falf_hrs_night").focus();
			return false;
		}

		if (g_run.trim() == "") {
			alert("Please Enter  G Run ");
			$("input#g_run").focus();
			return false;
		}
		if (af_hrs.trim() == "") {
			alert("Please Enter  AF HRS");
			$("input#af_hrs").focus();
			return false;
		}
		if (eng_hrs_left.trim() == "") {
			alert("Please Enter ENG LEFT LH");
			$("input#eng_hrs_left").focus();
			return false;
		}
		if (eng_hrs_rigth.trim() == "") {
			alert("Please Enter ENG LEFT RH");
			$("input#eng_hrs_rigth").focus();
			return false;
		}

		if (hrs_left.trim() == "") {
			alert("Please Enter  HRS LEFT ");
			$("input#hrs_left").focus();
			return false;
		}
		if (next_insp.trim() == "") {
			alert("Please Enter NEXT INSP");
			$("input#next_insp").focus();
			return false;
		}
		if (hrs_monthly.trim() == "") {
			alert("Please Enter  PROG HRS FLOWN MONTHLY");
			$("input#hrs_monthly").focus();
			return false;
		}
		if (hrs_qtrly.trim() == "") {
			alert("Please Enter PROG HRS FLOWN QTRLY");
			$("input#hrs_qtrly").focus();
			return false;
		}

		if (hrs_half_year.trim() == "") {
			alert("Please Enter PROG HRS FLOWN H/YRLY");
			$("input#hrs_half_year").focus();
			return false;
		}
		if (hrs_qtrly_flow.trim() == "") {
			alert("Please Enter PROG HRS FLOWN YRLY");
			$("input#hrs_qtrly_flow").focus();
			return false;
		}
		if (bal_hrs.trim() == "") {
			alert("Please Enter BALANCE AH ENG HRS");
			$("input#bal_hrs").focus();
			return false;
		}

		if ($("#date_goi_letter").val() == ""
				|| $("#date_goi_letter").val() == "DD/MM/YYYY") {
			alert("Please Enter DT LAST FLOW");
			$("#date_goi_letter").focus();
			return false;
		}

		return true;
	}
</script>
