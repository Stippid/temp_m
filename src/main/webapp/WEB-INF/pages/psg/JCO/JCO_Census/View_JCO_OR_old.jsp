
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
.popup {
	position: relative;
	display: inline-block;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
.popup .popuptext {
	visibility: hidden;
	width: 160px;
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 8px 0;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -80px;
}
.popup .popuptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
}
.popup .show {
	visibility: visible;
	-webkit-animation: fadeIn 1s;
	animation: fadeIn 1s;
}
@
-webkit-keyframes fadeIn {
	from {opacity: 0;
}
to {
	opacity: 1;
}
}
@
keyframes fadeIn {
	from {opacity: 0;
}
to {
	opacity: 1;
}
}
</style>

<style>
.tab {
}
.tablinks {
	outline: 0 !important;
	background-color: #cccccc;
	color: black;
	font-size: 14px;
	padding: 10px 5px;
	font-family: system-ui;
	border: none;
	margin: 5px 5px;
	border-right: 2px solid black;
	border-left: 2px solid black;
}
button.tablinks.active {
	background-color: #343a40 !important;
	color: white;
	box-shadow: 5px 10px 8px #888888 !important;
	border-right: 2px solid white;
	border-bottom: 2px solid white;
}
.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 8px;
}
.card-body.card-block {
	text-align: left;
	background-color: #cccccc21;
}
.card .card-footer {
	padding: .65rem 1.25rem;
	background-color: #cccccc;
	border-top: 1px solid #c2cfd6;
	position: relative;
}
</style>
<div class="container-fluid" align="center">

	
		<div class="animated fadeIn">
			<div class="container-fluid" align="center">
				<div class="card">
					<div class="card-header">
						<h5> VIEW JCO/OR DATA</h5>
						<h6 class="enter_by">
							<span style="font-size: 12px; color: red;">(To be entered
								by Unit)</span>
						</h6>
					</div>
					<div class="card-body card-block">
						<div class="card-header-inner" id="aa"
							style="margin-left: 20px; margin-top: 10px;">
							<strong><h4>Personal Details</h4></strong>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Category </label>
									</div>
									<div class="col-md-8">
										<label id="category"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 form-group">
							<div class="col-md-2">
								<label for="text-input" class=" form-control-label"><strong
										style="margin-left:10px;"></strong>Army No</label>
							</div>
							<div class="col-md-10">
								<div class="row form-group">
									<input type="hidden" id="jco_id" name="jco_id"
										class="form-control autocomplete" autocomplete="off">
									<div class="col-md-4">
										<label id="army_no1"></label>
									</div>
									<div class="col-md-4">
										<label id="army_no2"></label>
									</div>
									<div class="col-md-4">
										<label id="army_no3"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> First Name</label>
									</div>
									<div class="col-md-8">
										<label id="first_name"></label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Middle Name</label>
									</div>
									<div class="col-md-8">
										<label id="middle_name"></label>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Last Name</label>
									</div>
									<div class="col-md-8">
										<label id="last_name"></label> <input type="hidden" id="id"
											name="id" value="0">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Gender</label>
									</div>
									<div class="col-md-8">
										<label id="gender"></label>
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Rank</label>
									</div>
									<div class="col-md-8">
										<label id="rank"></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12" id="intake" style="display: none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Rank Intake</label>
									</div>
									<div class="col-md-8">
										<label id="rank_intake"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_birth"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Place of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="place_of_birth"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">COUNTRY of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="country_of_birth"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">STATE of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="state_of_birth"></label>
									</div>
								</div>
							</div>
						</div>

						<div id = "country_other_div" class="col-md-12" style="display:none;">
							<div class="col-md-6" id="country_birth_other_hid"
								style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">COUNTRY of Birth Other</label>
									</div>
									<div class="col-md-8">
										<label id="country_other"></label>
									</div>
								</div>
							</div>
							<div  id = "state_other_div" class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">STATE of Birth Other</label>
									</div>
									<div class="col-md-8">
										<label id="state_other"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">DISTRICT of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="district_of_birth" ></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Nationality</label>
									</div>
									<div class="col-md-8">
										<label id="nationality"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" >
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Religion</label>
									</div>
									<div class="col-md-8">
										<label id="religion"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother Tongue</label>
									</div>
									<div class="col-md-8">
										<label id="mother_tongue"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Marital Status</label>
									</div>
									<div class="col-md-8">
										<label id="marital_status"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6" >
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Blood Group</label>
									</div>
									<div class="col-md-8">
										<label id="blood_group"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Height (cm)</label>
									</div>
									<div class="col-md-8">
										<label id="height"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Aadhar No</label>
									</div>
									<div class="col-md-2">
										<label id="adhar_number1"></label>
									</div>
									<div class="col-md-2">
										<label id="adhar_number2"></label>
									</div>
									<div class="col-md-2">
										<label id="adhar_number3"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Pan No</label>
									</div>
									<div class="col-md-8">
										<label id="pan_no"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Class For Enrolment</label>
									</div>
									<div class="col-md-8">
										<label id="class_enroll"></label>
									</div>
								</div>
							</div>
								<div class="col-md-6" id="domicile_div" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Domicile</label>
									</div>
									<div class="col-md-8">
										<label id="domicile"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="card-header-inner" id="aa"
							style="margin-left: 20px; margin-bottom: 20px;" >
							<strong><h4>Service Details</h4></strong>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Class(Pay)</label>
									</div>
									<div class="col-md-8">
										<label id="class_pay"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Pay Group</label>
									</div>
									<div class="col-md-8">
										<label id="pay_group"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Record Office (SUS
											No)</label>
									</div>
									<div class="col-md-8">
										<label id="record_office_sus"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Record Office</label>
									</div>
									<div class="col-md-8">
										<label id="record_office"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of Attestation</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_attestation"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of Enrolment</label>
									</div>
									<div class="col-md-8">
										<label id="enroll_dt"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of Seniority</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_seniority"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Trade</label>
									</div>
									<div class="col-md-8">
										<label id="trade"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of TOS</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_tos"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Arm/Service</label>
									</div>
									<div class="col-md-8">
										<label id="arm_service"></label>
									</div>
								</div>
							</div>

							<div class="col-md-6" id="regiment_div" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Regiment(INF)</label>
									</div>
									<div class="col-md-8">
										<label id="regiment"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-md-8">
										<label id="unit_name"></label> 
<!--  										<label id="unit_name_l"></label>  -->
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Unit SUS No </label>
									</div>
									<div class="col-md-8">
										<label id="unit_sus_no_hid" style="display: none"></label> 
										<label id="unit_sus_no"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="card-header-inner" id="aa"
							style="margin-left: 20px; margin-bottom: 20px;">
							<strong><h4>Family Details</h4></strong>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Father's Name</label>
									</div>
									<div class="col-md-8">
										<label id="father_name"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Father's Date of
											Birth</label>
									</div>
									<div class="col-md-8">
										<label id="father_dob"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Father's Place of
											Birth</label>
									</div>
									<div class="col-md-8">
										<label id="father_place_birth"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Father In
											Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<label id="father_in_service_id234"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div id="father_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Father's Services</label>
										</div>
										<div class="col-md-8">
											<label id="father_service"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="father_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Other</label>
										</div>
										<div class="col-md-8">
											<label id="father_other"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="father_personalDiv"  style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Father's Personal
												No.</label>
										</div>
										<div class="col-md-8">
											<label id="father_personal_no"></label>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-6" id="father_proffDiv" style="display: none">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Father's Profession</label>
									</div>
									<div class="col-md-8">
										<label id="father_profession"></label>
									</div>
								</div>
							</div>
						</div>
						
							<div class="col-md-12" style="display:none;">
							<div class="col-md-6" id="father_proffotherDiv"
								style="display: none">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Other</label>
									</div>
									<div class="col-md-8">
										<label id="father_proffother"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother's Name</label>
									</div>
									<div class="col-md-8">
										<label id="mother_name"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother's Date of
											Birth</label>
									</div>
									<div class="col-md-8">
										<label id="mother_dob"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother's Place of
											Birth</label>
									</div>
									<div class="col-md-8">
										<label id="mother_place_birth"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display:none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother In
											Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<label id="mother_in_service_id234"></label>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display:none;">
							<div id="mother_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Mother's Services</label>
										</div>
										<div class="col-md-8">
											<label id="mother_service"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Other</label>
										</div>
										<div class="col-md-8">
											<label id="mother_other"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_personalDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Mother's Personal
												No.</label>
										</div>
										<div class="col-md-8">
											<label id="mother_personal_no"></label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="mother_proffDiv" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Mother's Profession</label>
									</div>
									<div class="col-md-8">
										<label id="mother_profession"></label>
									</div>
								</div>
							</div>
						</div>
							<div class="col-md-12" style="display:none;">
							<div class="col-md-6" id="mother_otherproffDiv"
								style="display: none">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Other</label>
									</div>
									<div class="col-md-8">

										<label id="mother_proffother"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4" style="display:none;">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
									of Siblings</h4></label>
						</div>
						<br>
						<div class="col-md-12" style="display:none;">
							<table id="family_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;" style="display:none;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 20%;">Name</th>
										<th style="width: 20%;">Date of Birth</th>
										<th style="width: 20%;">Relationship</th>
										<th style="width: 15%;">Aadhar No</th>
										<th style="width: 15%;">PAN No</th>
										<th style="width: 15%;">Services</th>
										<th style="width: 15%;">Personal No</th>
										<th style="width: 15%;">Other Service</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_sibtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno" style="width: 2%;"></td>
										<td style="width: 20%;"><label id="sib_name1"></label></td>
										<td style="width: 20%;"><label id="sib_date_of_birth1"></label></td>
										<td style="width: 20%;"><label id="sib_relationship1"></label></td>
										<td style="width: 15%;"><label id="sib_adhar_number1"></label></td>
										<td style="width: 15%;"><label id="sib_pan_no1"></label></td>
										<td style="width: 15%;"><label id="sibling_service1"></label></td>
										<td style="width: 15%;"><label id="sibling_personal_no1"></label></td>
										<td style="width: 15%;"><label id="other_sibling_ser1"></label></td>
										
										
										
										
									</tr>
								</tbody>
							</table>
							<input type="hidden" id="siblings_count" name="siblings_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="siblingsOld_count" name="siblingsOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">
						</div>

						<div id="fill_marraige_div" style="display: block">
							<div class="col-md-6" id=""
								style="margin-top: 10px; width: 100%;">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
										of Spouse</h4></label>
							</div>
							<br>
							<div class="col-md-12 watermarked" style="display: block;">

								<table id="married_table" class="table-bordered"
									style="margin-top: 10px; width: 100%;">

									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th>Name</th>
											<th>Date of Birth</th>
											<th>Place of Birth</th>
											<th>Nationality</th>
											<th>Other Nationality</th>
											<th>Date of Marriage</th>
											<th>Aadhar No</th>
											<th>PAN No</th>
											<th>Services</th>
											<th>Personal No</th>
											<th>Other Service</th>
											<th class="sepratedSpouseClass">From Date</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="family_marrtbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">

									</tbody>
								</table>
							</div>

							<div class="col-md-12" id="spouse_quali_radioDiv123"
								style="margin-top: 10px; width: 100%;" style="display:none;">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Any
										Qualification Details Of Spouse</h4></label> &nbsp &nbsp <label
									id="spouse_education_id234"></label>


							</div>

							<div class="col-md-12 watermarked" id="spouse_quali_radioDiv"
								style="margin-top: 20px; display: none;" >


								<table id="spouse_quali_table" class="table-bordered"
									style="margin-top: 10px; width: 100%;">
									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">Sr No</th>
											<th style="width: 7%;">Qualification Type</th>
											<th style="width: 7%;">Examination Pass</th>
											<th style="width: 7%;">Degree Acquired</th>
											<th style="width: 7%;">Specialisation</th>
											<th style="width: 7%;">Year of Passing</th>
											<th style="width: 7%;">Div/Grade</th>
											<th style="width: 7%;">Institute & Place</th>
											<th style="width: 7%;">Subject</th>

										</tr>
									</thead>
									<tbody data-spy="scroll" id="spouse_quali_tbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">

									</tbody>
								</table>
							</div>
						</div>
						<div id="fill_divorce_div" style="display: none">
							<div class="col-md-6">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
										of Divorce/Widow/Widower</h4></label> <br>

							</div>
							<div class="col-md-12" style="display: none;">
								<table id="divorce_table" class="table-bordered "
									style="margin-top: 10px; width: 100%;">

									<thead style="color: white; text-align: center;">
										<tr>
											<th style="width: 2%;">Sr No</th>
											<th>Marital Event</th>
											<th>Name</th>
											<th>Date of Birth</th>
											<th>Place of Birth</th>
											<th>Nationality</th>
											<th>Other Nationality</th>
											<th>Date of Marriage</th>
											<th>Aadhar No</th>
											<th>PAN No</th>
											<th>Date of Divorce/Widow/Widower</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="family_divotbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_divorce1">
											<td class="divosr_no" style="width: 2%;"></td>
											<td><label id="spouse_name1"></label></td>
											<td><label id="divorce_date_of_birth1"></label></td>
											<td><label id="divorce_place_of_birth1"></label></td>
											<td><label id="divorce_nationality1"></label></td>
											<td><label id="divorce_othernationality1"></label></td>
											<td><label id="divorce_marriage_date1"></label></td>
											<td><label id="divorce_spouse_adhar_number1"></label></td>
											<td><label id="divorce_spouse_pan_number1"></label></td>
											<td><label id="divorce_date1"></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="search_jco_url" class="btn btn-info btn-sm">Back</a> 
				</div>
			</div>
		</div>
</div>

<script>
var marital_status="";
$(document).ready(function() {
	
 	$("#category").text('${list[0][0]}');
	$("#army_no1").text('${list[0][1]}');
	$("#first_name").text('${list[0][2]}');
	$("#middle_name").text('${list[0][3]}');
	$("#last_name").text('${list[0][4]}');
	$("#gender").text('${list[0][5]}');
	$("#rank").text('${list[0][6]}');
	$("#rank_intake").text('${list[0][7]}');
	$("#date_of_birth").text('${list[0][8]}');
	$("#place_of_birth").text('${list[0][9]}');
	$("#country_of_birth").text('${list[0][10]}');
	$("#state_of_birth").text('${list[0][11]}');
	$("#district_of_birth").text('${list[0][12]}');
	$("#nationality").text('${list[0][13]}');
	$("#religion").text('${list[0][14]}');
	$("#mother_tongue").text('${list[0][15]}');
	$("#marital_status").text('${list[0][16]}');
	$("#blood_group").text('${list[0][17]}');
	$("#height").text('${list[0][18]}');
	$("#adhar_number1").text('${list[0][19]}');
	$("#pan_no").text('${list[0][20]}');
	$("#class_enroll").text('${list[0][21]}');
	$("#domicile").text('${list[0][22]}');
	$("#class_pay").text('${list[0][23]}');
	$("#pay_group").text('${list[0][24]}');
	$("#record_office_sus").text('${list[0][25]}');
	$("#record_office").text('${list[0][26]}');
	$("#date_of_attestation").text('${list[0][27]}');
	$("#enroll_dt").text('${list[0][28]}');
	$("#date_of_seniority").text('${list[0][29]}');
	$("#trade").text('${list[0][30]}');
	$("#date_of_tos").text('${list[0][31]}');
	$("#arm_service").text('${list[0][32]}');
	$("#regiment").text('${list[0][33]}');
	$("#father_name").text('${list[0][34]}');
	$("#father_dob").text('${list[0][35]}');
	$("#father_place_birth").text('${list[0][36]}');
	$("#father_service").text('${list[0][37]}');
	$("#father_other").text('${list[0][38]}');
	$("#father_personal_no").text('${list[0][39]}');
	$("#father_profession").text('${list[0][40]}');
	$("#mother_name").text('${list[0][41]}');
	$("#mother_dob").text('${list[0][42]}');
	$("#mother_place_birth").text('${list[0][43]}');
	$("#mother_service").text('${list[0][44]}');
	$("#mother_other").text('${list[0][45]}');
	$("#mother_personal_no").text('${list[0][46]}');
	$("#mother_profession").text('${list[0][47]}');
	$("#id").val('${list[0][48]}');
	$("#unit_name").text('${list[0][49]}');
	$("#unit_sus_no").text('${list[0][50]}');
	$("#father_proffother").text('${list[0][51]}');
	$("#mother_proffother").text('${list[0][52]}');
		
	 var rank_intake = '${list[0][7]}';
	 if(rank_intake > "0"){
			$("#intake").show(); 		 
	 }else {
		 $("#intake").hide(); 
	 }
	 
	 var arm_service = '${list[0][32]}';
	 if(arm_service == "INFANTRY" || arm_service == "GORKHA"){
			$("#regiment_div").show(); 	
	 }else {
		 $("#regiment_div").hide();
		 
	 }
	 
	 if('${list[0][22]}' != null && '${list[0][22]}' != ""){
		
			$("#domicile_div").show(); 	
		}
		else
			{
		
			$("#domicile_div").hide(); 
			}
	 var father_proff_other = '${list[0][40]}';
	 if(father_proff_other == "OTHERS"){
		 $("#father_proffotherDiv").show();
	 }else{
		 $("#father_proffotherDiv").hide(); 
	 }
	 
	 var mother_proff_other = '${list[0][47]}';
	 if(mother_proff_other == "OTHERS"){
		 $("#mother_otherproffDiv").show();
	 }else{
		 $("#mother_otherproffDiv").hide(); 
	 }
	 
	 var father_service = '${list[0][37]}';
	 if(father_service == "OTHER"){
		 $("#father_otherDiv").show(); 
		 $("#father_personalDiv").hide();
	 }else{
		 $("#father_otherDiv").hide(); 
		 $("#father_personalDiv").show();
	 }
	 
	 var mother_service = '${list[0][44]}';
	 if(mother_service == "OTHER"){
		 $("#mother_otherDiv").show(); 
		 $("#mother_personalDiv").hide();
	 }else{
		 $("#mother_otherDiv").hide(); 
		 $("#mother_personalDiv").show();
	 }
	
	 var father_in_service = '${list[0][37]}';
	 if(father_in_service > "0"){
		 father_in_service = "Yes"
 		$("#father_inserviceDiv").show(); 		 
		 $("#father_proffDiv").hide();
	 }else if(father_in_service !=  "3"){
		 father_in_service = "No"
 		$("#father_inserviceDiv").hide();
 		 $("#father_proffDiv").show();
	 }
	 $("#father_in_service_id234").text(father_in_service);
	
	 var mother_in_service = '${list[0][44]}';
	 if(mother_in_service > "0"){
		 mother_in_service = "Yes"
		$("#mother_inserviceDiv").show();
		 $("#mother_proffDiv").hide();
	 }else if(mother_in_service != "3"){
		 mother_in_service = "No"
		 $("#mother_inserviceDiv").hide();
		 $("#mother_proffDiv").show();
	 }
	 $("#mother_in_service_id234").text(mother_in_service);
	 
	 var jco_id = $('#id').val();
	 
	  marital_status = '${list[0][16]}';
	 
	 if(marital_status == "Divorced" || marital_status == "Widower" || marital_status == "Widow") {
		 	$("#fill_marraige_div").hide();
			$("#fill_divorce_div").show();
		}
	 if (marital_status == "Married" || marital_status == "Separated") {
			$("#fill_marraige_div").show();
			$("#fill_divorce_div").show();
			if(marital_status == "Married"){
				$(".sepratedSpouseClass").hide();								
			}
		}
		if(marital_status == "Unmarried") {
			$("#fill_marraige_div").hide();
			$("#fill_divorce_div").hide();
		}

	/*$.post("getSiblingsValue?"+key+"="+value,{jco_id:jco_id}, function(j) {		
 		if(j != ""){
 		
 			var x1=0;
 			for(var i=0;i<j.length;i++)
 				{
 				var sibling = j[i][5];
 				
 				if(sibling.toUpperCase() == "OTHER"){
 					$('#other_sibling_ser' + i).attr('readonly', false);	
 					$('#sibling_personal_no' + i).attr('readonly', true);
 					$('#sibling_personal_no' + i).val('');
 				}
 				if(sibling.toUpperCase() != "OTHER" && sibling != "--Select--"){
 					$('#other_sibling_ser' + i).attr('readonly', true);
 					$('#sibling_personal_no' + i).attr('readonly', false);
 					$('#other_sibling_ser' + i).val('');
 				}
 				x1+=1;
 				if(x1==1){
					$('#family_sibtbody').empty();
				}
 				 $("#family_sibtbody").append(' <tr id="tr_sibling1'+x1+'">'
 						
 						+'<td>'+x1+'</td>'
 						+'<td>'+j[i][0]+'</td>' 
 						+'<td>'+j[i][1]+'</td>'
	    				+'<td>'+j[i][2]+'</td>'
	    				+'<td>'+j[i][3]+'</td>'
	    				+'<td>'+j[i][4]+'</td>'	
	    				+'<td>'+j[i][5]+'</td>'	
	    				+'<td>'+j[i][6]+'</td>'	
	    				+'<td>'+j[i][7]+'</td>'	
	    				
	    						    +'</tr>');
 				}
		}    
   });
	
	$.post("getSpouseValue?"+key+"="+value,{jco_id:jco_id}, function(j) {	
 		if(j != ""){
 			var x1=0;
 			for(var i=0;i<j.length;i++)
 				{
 				
 				var spouse = j[i][9];
 				
 				if(spouse.toUpperCase() == "OTHER"){
 					$('#other_sibling_ser' + i).attr('readonly', false);	
 					$('#sibling_personal_no' + i).attr('readonly', true);
 					$('#sibling_personal_no' + i).val('');
 				}
 				if(spouse.toUpperCase() != "OTHER" && spouse != "--Select--"){
 					$('#other_sibling_ser' + i).attr('readonly', true);
 					$('#sibling_personal_no' + i).attr('readonly', false);
 					$('#other_sibling_ser' + i).val('');
 				}
 				
 				x1+=1;
 				if(x1==1){
					$('#family_marrtbody').empty();
				}
 				if(j[i][8] == 0){
					+'<td>'+""+'</td>'
				}else{
					+'<td>'+j[i][8]+'</td>'
				}
 				 $("#family_marrtbody").append(' <tr id="tr_marriage1'+x1+'">'
 						
 						+'<td>'+x1+'</td>'
 						+'<td>'+j[i][0]+'</td>' 
 						+'<td>'+j[i][1]+'</td>'
	    				+'<td>'+j[i][2]+'</td>'
	    				+'<td>'+j[i][3]+'</td>'
	    				+'<td>'+j[i][4]+'</td>'
	    				+'<td>'+j[i][5]+'</td>'
	    				+'<td>'+j[i][6]+'</td>'
	    				+'<td>'+j[i][7]+'</td>'
	    				+'<td>'+j[i][8]+'</td>'
	    				+'<td>'+j[i][9]+'</td>'
	    				+'<td>'+j[i][10]+'</td>'
	    				+ '<td class="sepratedSpouseClass">'
						+ j[i][11] + '</td>'
	    				   					
	    						    +'</tr>');
 				}
 			if(marital_status == "Married"){
				$(".sepratedSpouseClass").hide();								
			}
		}    
   });

	
	$.post("getDIV_WIDValue?"+key+"="+value,{jco_id:jco_id}, function(j) {	
		if(j != ""){
		var x1=0;
		for(var i=0;i<j.length;i++)
			{
			x1+=1;
			if(x1==1){
			$('#family_divotbody').empty();
		}
			 $("#family_divotbody").append(' <tr id="tr_divorce1'+x1+'">'
					
					+'<td>'+x1+'</td>'
					+ '<td>'+ j[i][9] + '</td>'
					+'<td>'+j[i][0]+'</td>' 
					+'<td>'+j[i][1]+'</td>'
					+'<td>'+j[i][2]+'</td>'
					+'<td>'+j[i][3]+'</td>'
					+'<td>'+j[i][4]+'</td>'
					+'<td>'+j[i][5]+'</td>'
					+'<td>'+j[i][6]+'</td>'
					+'<td>'+j[i][7]+'</td>'
					+'<td>'+j[i][8]+'</td>'
				   			 +'</tr>');
			 
			}
}    
});
	 
	 $.post('getSpouse_EducationData?' + key + "=" + value, {jco_id:jco_id,status:1}, function(j){
		 var v=j.length;
		 var i=0;
		 $('#spouse_quali_tbody').empty();
	     if(v!=0){
	    
	    	 $("#spouse_quali_radioDiv").show();
	    	 $("#spouse_education_id234").text("Yes");
			for(i;i<v;i++){
	     		cor=i+1;
	     		var dmod=convertDateFormate(j[i].modified_date);
	     		var exp_name =j[i].exp_name;
	     		var d_name =j[i].d_name;
	     		var spce_name =j[i].spce_name;
	     		var div =j[i].div;
	     		
	     		if(j[i].exp_name.toUpperCase()=="OTHERS" || j[i].exp_name.toUpperCase()=="OTHER"){
	     			exp_name=j[i].exam_other;
	     		}
	     		if(j[i].d_name.toUpperCase()=="OTHERS" || j[i].d_name.toUpperCase()=="OTHER"){
	     			d_name=j[i].degree_other;
	     		}
	     		if(j[i].spce_name.toUpperCase()=="OTHERS" || j[i].spce_name.toUpperCase()=="OTHER"){
	     			spce_name=j[i].specialization_other;
	     		}
	     		if(j[i].div.toUpperCase()=="OTHERS" || j[i].div.toUpperCase()=="OTHER"){
	     			div=j[i].class_other;
	     		}
				$("table#spouse_quali_table").append('<tr id="tr_spouse_quali'+cor+'">'
	                     +'<td class="anm_srno">'+cor+'</td>'
	                     +'<td style="width: 10%;">'+j[i].type+'</td>'
	                     +'<td style="width: 10%;">'+exp_name+'</td>'
	                     +'<td style="width: 10%;">'+d_name+'</td>'
	                     +'<td style="width: 10%;">'+spce_name+'</td>'
	                     +'<td style="width: 10%;">'+j[i].passing_year+'</td>'
	                     +'<td style="width: 10%;">'+div+'</td>'
	                     +'<td style="width: 10%;">'+j[i].institute+'</td>'
	                     +'<td style="width: 10%;">'+j[i].subject+'</td>'      
	                     +'</tr>');
			}	 
		}
	     else{
	    	 $("#spouse_quali_radioDiv").hide();
	    	 $("#spouse_education_id234").text("No");
	     }
	    
	 });
	*/ 
	 var id = $('#id').val();
	 
	 var r =('${roleAccess}');
		if( r == "Unit"){
			
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
			 
		}
		else  if(r == "MISO"){
			
			 $("input#unit_sus_no").show();		 
			 $("input#unit_name").show();
			
		}
		
	   var sus_no = '${roleSusNo}';
	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
	  		var l=data.length-1;
	  		var enc = data[l].substring(0,16);    	   	 
	  	 	$("#unit_name").text(dec(enc,data[0]));
	  		}).fail(function(xhr, textStatus, errorThrown) {
		  }); 
		  
	});
</script>

