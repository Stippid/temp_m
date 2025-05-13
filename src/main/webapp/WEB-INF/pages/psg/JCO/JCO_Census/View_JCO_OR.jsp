
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
							<div class="col-md-6">
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

						<div class="col-md-12">
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

						<div id = "country_other_div" class="col-md-12">
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

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">DISTRICT of Birth</label>
									</div>
									<div class="col-md-8">
										<label id="district_of_birth"></label>
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

						<div class="col-md-12">
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
							<div class="col-md-6">
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
						<div class="col-md-12">
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
							<div class="col-md-6">
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
						<div class="col-md-12">
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
						<div class="col-md-12">
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
							style="margin-left: 20px; margin-bottom: 20px;">
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
										<label class=" form-control-label">Date of Enrolment</label>
									</div>
									<div class="col-md-8">
										<label id="enroll_dt"></label>
									</div>
								</div>
							</div>
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
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Date of Rank</label>
									</div>
									<div class="col-md-8">
										<label id="date_of_rank"></label>
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
							<div class="col-md-6">
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

						<div class="col-md-12">
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

						<div class="col-md-12">
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

						<div class="col-md-12">
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
						
							<div class="col-md-12">
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
							<div class="col-md-6">
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
						<div class="col-md-12">
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
						<div class="col-md-12">
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

						<div class="col-md-12">
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
							<div class="col-md-6" id="mother_proffDiv">
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
							<div class="col-md-12">
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
						
						
						
						
											<!-- 	//===========================medical dtl =======================================// -->		
					
						
	<div class="card">
    <div class="card-header" style="text-align: center;">
        <h5>Medical Details</h5>
        <h6 class="enter_by">
            <span style="font-size: 12px; color: red;"></span>
        </h6>
    </div>
    <div class="card-body card-block">
        </br>
        <!--        <div id="divhideformed"-->
        <!--             style="width: -webkit-fill-available; text-align: -webkit-center; display:block">-->
        <!--            <strong style="font-size: x-large; text-align: center;">For-->
        <!--                Any Modification pls Click Here </strong> <a-->
        <!--                class="btn btn-danger btn-sm" value="DELETE"-->
        <!--                title="Reset All Medical Details " id="cCope_remove6"-->
        <!--                onclick="AllCoperemove_fn();"><i-->
        <!--                style="font-size: x-large;" class="fa fa-trash"></i></a>-->
        <!--        </div>-->
        </br> </br> </br> <input type="hidden" id="mad_classification_count"
            name="mad_classification_count" value="NIL" disabled>

        <div class="col-md-12" style="display:none">
            <div class="row form-group">
                <div class="col-md-12" style="font-size: 15px; margin: 10px;">
                    <input type="checkbox" id="check_1bx" name="check_1bx"
                        onclick="oncheck_1bx()" value="1BX" disabled> <label
                        for="text-input" class=" form-control-label"
                        style="margin-left: 10px;"><strong> SHAPE
                            1BX </strong></label>
                </div>
            </div>
            <input type="hidden" id="shape_1bx_id" name="shape_1bx_id"
                value="0" class="form-control autocomplete" autocomplete="off" disabled>
        </div>
        <div class="col-md-12" id="shape1bxdiv" style="display: none;">

            <div class="col-md-4">
                <div class="row form-group">
                    <div class="col-md-4">
                        <label class=" form-control-label"><strong
                                style="color: red;">* </strong>From Date</label>
                    </div>
                    <div class="col-md-8">
                        <%--                         <input type="date" id="1bx_from_date" name="1bx_from_date" max="${date_without_time}" class="form-control autocomplete" autocomplete="off"> --%>

                        <input type="text" name="1bx_from_date" id="1bx_from_date"
                            maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
                            class="form-control" style="width: 85%; display: inline;"
                            onfocus="this.style.color='#000000'"
                            onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                            onkeyup="clickclear(this, 'DD/MM/YYYY')"
                            onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                            aria-required="true" autocomplete="off"
                            style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row form-group">
                    <div class="col-md-4">
                        <label class=" form-control-label"><strong
                                style="color: red;">* </strong>To Date</label>
                    </div>
                    <div class="col-md-8">
                        <!--                            <input type="date" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off"> -->
                        <input type="text" name="1bx_to_date" id="1bx_to_date"
                            maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
                            class="form-control" style="width: 85%; display: inline;"
                            onfocus="this.style.color='#000000'"
                            onblur="clickrecall(this,'DD/MM/YYYY');"
                            onkeyup="clickclear(this, 'DD/MM/YYYY')"
                            onchange="clickrecall(this,'DD/MM/YYYY');"
                            aria-required="true" autocomplete="off"
                            style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row form-group">
                    <div class="col-md-4">
                        <label class=" form-control-label"><strong
                                style="color: red;">* </strong>Diagnosis</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" name="1bx_diagnosis_code"
                            id="1bx_diagnosis_code"
                            class="form-control-sm form-control" autocomplete="off"
                            placeholder="Search..."
                            onkeypress="AutoCompleteDiagnosis(this);" disabled>
                    </div>
                </div>
            </div>
        </div>

        <div id="shapediv" style="width: -webkit-fill-available;">

            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>S - Psychological & Congnitive</strong>
            </div>
            <div class="">
                <table id="s_madtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">

                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Status LMC</th>
                            <th style="">Value</th>
                            <th style="">From Date</th>
                            <th style="">To Date</th>
                            <th style="display: none;" class="diagnosisClass1">Diagnosis</th>
                            <!--                            <th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>                                        -->
                            <!-- <th style="display: none;" class="addbtClass1">Action</th> -->
                        </tr>
                    </thead>
                    <tbody data-spy="scroll" id="s_madtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_sShape1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="s_status1" id="s_status1"
                                class="form-control-sm form-control"
                                onchange="statusChange(1,1,this.options[this.selectedIndex].value)" disabled>
                                    <option value="0">--Select--</option>
                                    <c:forEach var="item" items="${getShapeStatusList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[1]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>
                            <td style=""><select name="sShape_value1"
                                id="sShape_value1" class="form-control-sm form-control"
                                onchange="onShapeValueChange(this,'s')" disabled>
                                    <option value="0">--Select--</option>
                            </select></td>

                            <td style=""><input type="text" name="s_from_date1"
                                id="s_from_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>

                            <td style=""><input type="text" name="s_to_date1"
                                id="s_to_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>
                            <td style="display: none;" class="diagnosisClass11">
                                <input type="text" name="_diagnosis_code11"
                                    id="_diagnosis_code11"
                                    class="form-control-sm form-control" autocomplete="off"
                                    placeholder="Search..."
                                    onkeypress="AutoCompleteDiagnosis(this);" disabled>
                            </td>

                            <td style="display: none;"><input type="text"
                                id="sShape_ch_id1" name="sShape_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <!-- <td style="width: 10%; display: none;" class="addbtClass11">
                                <a class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="sShape_add1" onclick="sShape_add_fn(1);"><i
                                    class="fa fa-plus"></i></a>
                            </td> -->
                        </tr>
                    </tbody>
                </table>
            </div>

            <br />

            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>H - Hearing</strong>
            </div>
            <div>
                <table id="h_madtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">

                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Status LMC</th>
                            <th style="">Value</th>
                            <th style="">From Date</th>
                            <th style="">To Date</th>
                            <th style="display: none;" class="diagnosisClass2">Diagnosis</th>
                            <!--                            <th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>                                        -->
                            <!-- <th style="display: none;" class="addbtClass2">Action</th> -->
                        </tr>
                    </thead>
                    <tbody data-spy="scroll" id="h_madtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_hShape1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="h_status1" id="h_status1"
                                class="form-control-sm form-control"
                                onchange="statusChange(2,1,this.options[this.selectedIndex].value)" disabled>
                                    <option value="0">--Select--</option>
                                    <c:forEach var="item" items="${getShapeStatusList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[1]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>
                            <td style=""><select name="hShape_value1"
                                id="hShape_value1" class="form-control-sm form-control"
                                onchange="onShapeValueChange(this,'h')" disabled>
                                    <option value="0">--Select--</option>
                            </select></td>

                            <td style=""><input type="text" name="h_from_date1"
                                id="h_from_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>

                            <td style=""><input type="text" name="h_to_date1"
                                id="h_to_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>
                            <td style="display: none;" class="diagnosisClass21"><input
                                type="text" name="_diagnosis_code21"
                                id="_diagnosis_code21"
                                class="form-control-sm form-control" autocomplete="off"
                                placeholder="Search..."
                                onkeypress="AutoCompleteDiagnosis(this);" disabled></td>

                            <td style="display: none;"><input type="text"
                                id="hShape_ch_id1" name="hShape_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <td style="width: 10%; display: none;" class="addbtClass21">
                                <a class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="hShape_add1" onclick="hShape_add_fn(1);"><i
                                    class="fa fa-plus"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>


            <br />
            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>A - Appendages</strong>
            </div>
            <div>
                <table id="a_madtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">

                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Status LMC</th>
                            <th style="">Value</th>
                            <th style="">From Date</th>
                            <th style="">To Date</th>
                            <th style="display: none;" class="diagnosisClass3">Diagnosis</th>
                            <!--                            <th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>                                        -->
                            <!-- <th style="display: none;" class="addbtClass3">Action</th> -->
                        </tr>
                    </thead>
                    <tbody data-spy="scroll" id="a_madtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_aShape1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="a_status1" id="a_status1"
                                class="form-control-sm form-control"
                                onchange="statusChange(3,1,this.options[this.selectedIndex].value)" disabled>
                                    <option value="0">--Select--</option>
                                    <c:forEach var="item" items="${getShapeStatusList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[1]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>
                            <td style=""><select name="aShape_value1"
                                id="aShape_value1" class="form-control-sm form-control"
                                onchange="onShapeValueChange(this,'a')" disabled>
                                    <option value="0">--Select--</option>
                            </select></td>

                            <td style=""><input type="text" name="a_from_date1"
                                id="a_from_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>

                            <td style=""><input type="text" name="a_to_date1"
                                id="a_to_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>
                            <td style="display: none;" class="diagnosisClass31"><input
                                type="text" name="_diagnosis_code31"
                                id="_diagnosis_code31"
                                class="form-control-sm form-control" autocomplete="off"
                                placeholder="Search..."
                                onkeypress="AutoCompleteDiagnosis(this);" disabled></td>

                            <td style="display: none;"><input type="text"
                                id="aShape_ch_id1" name="aShape_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <td style="width: 10%; display: none;" class="addbtClass31">
                                <a class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="aShape_add1" onclick="aShape_add_fn(1);"><i
                                    class="fa fa-plus"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>


            <br />
            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>P - Physical Capacity</strong>
            </div>
            <div>
                <table id="p_madtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">

                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Status LMC</th>
                            <th style="">Value</th>
                            <th style="">From Date</th>
                            <th style="">To Date</th>
                            <th style="display: none;" class="diagnosisClass4">Diagnosis</th>
                            <!--                            <th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>                                        -->
                            <!-- <th style="display: none;" class="addbtClass4">Action</th> -->
                        </tr>
                    </thead>
                    <tbody data-spy="scroll" id="p_madtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_eShape1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="p_status1" id="p_status1"
                                class="form-control-sm form-control"
                                onchange="statusChange(4,1,this.options[this.selectedIndex].value)" disabled>
                                    <option value="0">--Select--</option>
                                    <c:forEach var="item" items="${getShapeStatusList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[1]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>
                            <td style=""><select name="pShape_value1"
                                id="pShape_value1" class="form-control-sm form-control"
                                onchange="onShapeValueChange(this,'p')" disabled>
                                    <option value="0">--Select--</option>
                            </select></td>

                            <td style=""><input type="text" name="p_from_date1"
                                id="p_from_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>

                            <td style=""><input type="text" name="p_to_date1"
                                id="p_to_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>
                            <td style="display: none;" class="diagnosisClass41"><input
                                type="text" name="_diagnosis_code41"
                                id="_diagnosis_code41"
                                class="form-control-sm form-control" autocomplete="off"
                                placeholder="Search..."
                                onkeypress="AutoCompleteDiagnosis(this);" disabled></td>

                            <td style="display: none;"><input type="text"
                                id="pShape_ch_id1" name="pShape_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <td style="width: 10%; display: none;" class="addbtClass41">
                                <a class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="pShape_add1" onclick="pShape_add_fn(1);"><i
                                    class="fa fa-plus"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <br />

            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>E - Eye Sight</strong>
            </div>
            <div>
                <table id="e_madtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">

                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Status LMC</th>
                            <th style="">Value</th>
                            <th style="">From Date</th>
                            <th style="">To Date</th>
                            <th style="display: none;" class="diagnosisClass5">Diagnosis</th>
                            <!--                            <th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
                            <!--                            <th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>                                        -->
                            <!-- <th style="display: none;" class="addbtClass5">Action</th> -->
                        </tr>
                    </thead>
                    <tbody data-spy="scroll" id="e_madtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_eShape1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="e_status1" id="e_status1"
                                class="form-control-sm form-control"
                                onchange="statusChange(5,1,this.options[this.selectedIndex].value)" disabled>
                                    <option value="0">--Select--</option>
                                    <c:forEach var="item" items="${getShapeStatusList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[1]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>
                            <td style=""><select name="eShape_value1"
                                id="eShape_value1" class="form-control-sm form-control"
                                onchange="onShapeValueChange(this,'e')" disabled>
                                    <option value="0">--Select--</option>
                            </select></td>

                            <td style=""><input type="text" name="e_from_date1"
                                id="e_from_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>

                            <td style=""><input type="text" name="e_to_date1"
                                id="e_to_date1" maxlength="10"
                                onclick="clickclear(this, 'DD/MM/YYYY')"
                                class="form-control" style="width: 85%; display: inline;"
                                onfocus="this.style.color='#000000'"
                                onblur="clickrecall(this,'DD/MM/YYYY');"
                                onkeyup="clickclear(this, 'DD/MM/YYYY')"
                                onchange="clickrecall(this,'DD/MM/YYYY');"
                                aria-required="true" autocomplete="off"
                                style="color: rgb(0, 0, 0);" value="DD/MM/YYYY" disabled>

                            </td>
                            <td style="display: none;" class="diagnosisClass51"><input
                                type="text" name="_diagnosis_code51"
                                id="_diagnosis_code51"
                                class="form-control-sm form-control" autocomplete="off"
                                placeholder="Search..."
                                onkeypress="AutoCompleteDiagnosis(this);" disabled></td>

                            <td style="display: none;"><input type="text"
                                id="eShape_ch_id1" name="eShape_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <td style="width: 10%; display: none;" class="addbtClass51">
                                <a class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="eShape_add1" onclick="eShape_add_fn(1);"><i
                                    class="fa fa-plus"></i></a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>


            <br />

            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>C - Climate and Terrain Restrictions</strong>
            </div>
            <div style="width: -webkit-fill-available;">
                <table id="c_cmadtable" class="table-bordered "
                    style="margin-top: 10px; width: 100%;">
                    <thead style="color: white; text-align: center;">
                        <tr>
                            <th style="width: 2%;">Sr No</th>
                            <th style="">Value & Description</th>
                            <th style="display: none;" class="cCopClass">Other</th>
                            <!--                                <th >Description</th>                                               -->
                            <!-- <th style="width: 10%; display: none;"
                                class="CopaddbtClass1">Action</th>
                        </tr> -->
                    </thead>
                    <tbody data-spy="scroll" id="c_cmadtbody"
                        style="min-height: 46px; max-height: 650px; text-align: center;">
                        <tr id="tr_cCope1">
                            <td style="width: 2%;">1</td>
                            <td style=""><select name="c_cvalue1" id="c_cvalue1"
                                onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
                                class="form-control-sm form-control" disabled>
                                    <!--                                    <option value="0">--Select--</option>                                                                    -->
                                    <c:forEach var="item" items="${getcCopeList}"
                                        varStatus="num">
                                        <option value="${item[1]}" name="${item[0]}">${item[0]}</option>
                                    </c:forEach>
                            </select></td>

                            <td style="display: none;" class="cCopClass1"><input
                                type="text" name="c_cother1" id="c_cother1"
                                class="form-control-sm form-control" autocomplete="off" disabled>
                            </td>



                            <td style="display: none;"><input type="text"
                                id="cCope_ch_id1" name="cCope_ch_id1" value="0"
                                class="form-control autocomplete" autocomplete="off" disabled></td>

                            <!--            <td style="width: 10%; display: none;"
                                class="CopaddbtClass11"><a
                                class="btn btn-success btn-sm" value="ADD" title="ADD"
                                id="cCope_add1" onclick="cCope_add_fn(1);"><i
                                    class="fa fa-plus"></i></a></td> -->
                        </tr>
                    </tbody>
                </table>
            </div>
            <br />

            <div class="card-header-inner"
                style="text-align: center; margin-bottom: 20px;">
                <strong>O - Degree of Medical Observation Required</strong>
            </div>
            <div style="width: -webkit-fill-available;">
                <table id="c_omadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<!-- 													<th >Description</th>																					 -->
											<!-- 		<th style="width: 10%; display: none;"
														class="CopaddbtClass2">Action</th> -->
												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_omadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">
												<tr id="tr_oCope1">
													<td style="width: 2%;">1</td>
													<td style=""><select name="c_ovalue1" id="c_ovalue1"
														onchange="onCopeChangebt(this,2,1);"
														class="form-control-sm form-control">
															<!-- 																<option value="0">--Select--</option>																 -->
															<c:forEach var="item" items="${getoCopeList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select></td>





													<td style="display: none;"><input type="text"
														id="oCope_ch_id1" name="oCope_ch_id1" value="0"
														class="form-control autocomplete" autocomplete="off"></td>

													<td style="width: 10%; display: none;"
														class="CopaddbtClass21"><a
														class="btn btn-success btn-sm" value="ADD" title="ADD"
														id="oCope_add1" onclick="oCope_add_fn(1);"><i
															class="fa fa-plus"></i></a></td>
												</tr>
											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="text-align: center; margin-bottom: 20px;">
										<strong>P - Physical Capability Limitations</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_pmadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<!-- 													<th >Description</th>																					 -->
													<!-- <th style="width: 10%; display: none;"
														class="CopaddbtClass3">Action</th> -->
												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_pmadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">
												<tr id="tr_pCope1">
													<td style="width: 2%;">1</td>
													<td style=""><select name="c_pvalue1" id="c_pvalue1"
														onchange="onCopeChangebt(this,3,1);"
														class="form-control-sm form-control">
															<!-- 																<option value="0">--Select--</option>																 -->
															<c:forEach var="item" items="${getpCopeList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select></td>





													<td style="display: none;"><input type="text"
														id="pCope_ch_id1" name="pCope_ch_id1" value="0"
														class="form-control autocomplete" autocomplete="off"></td>

													<td style="width: 10%; display: none;"
														class="CopaddbtClass31"><a
														class="btn btn-success btn-sm" value="ADD" title="ADD"
														id="pCope_add1" onclick="pCope_add_fn(1);"><i
															class="fa fa-plus"></i></a></td>
												</tr>
											</tbody>
										</table>
									</div>


									<br />
									<div class="card-header-inner"
										style="text-align: center; margin-bottom: 20px;">
										<strong>E - Exclusive Limitations</strong>
									</div>
									<div style="width: -webkit-fill-available;">
										<table id="c_emadtable" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display: none;" class="eCopClass">SubValue</th>
													<th style="display: none;" class="eCop_otherClass">Other</th>
													<!-- 													<th >Description</th>																					 -->
													<!-- <th style="width: 10%; display: none;"
														class="CopaddbtClass4">Action</th> -->
												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_emadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">
												<tr id="tr_eCope1">
													<td style="width: 2%;">1</td>
													<td style=""><select name="c_evalue1" id="c_evalue1"
														onchange="onECopeChange(this,1); onCopeChangebt(this,4,1);"
														class="form-control-sm form-control">
															<!-- 																<option value="0">--Select--</option>																 -->
															<c:forEach var="item" items="${geteCopeList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style="display: none;" class="eCopClass1"><select
														name="c_esubvalue1" id="c_esubvalue1"
														onchange="onECopeSubChange(this,1)"
														class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getesubCopeList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style="display: none;" class="eCop_otherClass1">
														<input type="text" name="c_esubvalueother1"
														id="c_esubvalueother1"
														class="form-control-sm form-control" autocomplete="off">
													</td>



													<td style="display: none;"><input type="text"
														id="eCope_ch_id1" name="eCope_ch_id1" value="0"
														class="form-control autocomplete" autocomplete="off"></td>

													<td style="width: 10%; display: none;"
														class="CopaddbtClass41"><a
														class="btn btn-success btn-sm" value="ADD" title="ADD"
														id="eCope_add1" onclick="eCope_add_fn(1);"><i
															class="fa fa-plus"></i></a></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>	

						
					<!-- //===========================End medical dtl =======================================// -->
						
						
						<div style="display:none">
						
						<div class="col-md-4">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
									of Siblings</h4></label>
						</div>
						<br>
						<div class="col-md-12">
							<table id="family_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

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
								style="margin-top: 10px; width: 100%;">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Any
										Qualification Details Of Spouse</h4></label> &nbsp &nbsp <label
									id="spouse_education_id234"></label>


							</div>

							<div class="col-md-12 watermarked" id="spouse_quali_radioDiv"
								style="margin-top: 20px; display: block">


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
						<div id="fill_divorce_div" style="display: block">
							<div class="col-md-6">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
										of Divorce/Widow/Widower</h4></label> <br>

							</div>
							<div class="col-md-12" style="display: block;">
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
	$("#date_of_rank").text('${list[0][53]}');
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
	$("#jco_id").val('${list[0][48]}');
	
	getsShapeDetails();
	gethShapeDetails();
	getaShapeDetails();
	getpShapeDetails();
	geteShapeDetails();
	getcCopeDetails();
	getoCopeDetails();
	getpCopeDetails();
	geteCopeDetails();
		
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
	 $('#id').val('${list[0][48]}');
	 var jco_id = '${list[0][48]}';
	 
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

	$.post("getSiblingsValue?"+key+"="+value,{jco_id:jco_id}, function(j) {		
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
	  	 	//$("#unit_name").text(dec(enc,data[0])); //@nk
	  		}).fail(function(xhr, textStatus, errorThrown) {
		  }); 
		  
	});
</script>

<script>

function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}

var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function onShapeValueChange(e,label){
// 	if(e.value=="1"){
		
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", true );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", true );
// 	}
// 	else{
// 		$("#"+label+"_from_date1").datepicker( "option", "disabled", false );
// 		$("#"+label+"_to_date1").datepicker( "option", "disabled", false );
// 	}
}

function oncheck_1bx(){
	
	if ($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
		}
	else{
		$("#shape1bxdiv").hide();
		$("#shapediv").show();
	}
}

function onCopeChangebt(e,cope,ind){
	if(ind==1){
		if(e.value == 0 || (cope!= 4 && cope!= 2&& e.value == 1)){
			$('.CopaddbtClass'+cope+ind).hide();
			$('.CopaddbtClass'+cope).hide();
			
		}
		else{
			$('.CopaddbtClass'+cope+ind).show();
			$('.CopaddbtClass'+cope).show();
			
		}
	}
}
function onCCopeChange(e,ind){	
	if(e.value != '2 [c]'){
		$('.cCopClass'+ind).hide();
		$('.cCopClass').hide();
		
	}
	else{
		$('.cCopClass'+ind).show();
		$('.cCopClass').show();
		
	}
	var styleC = $(".cCopClass").css("display");
	for(var i = 1; i<=cCope; i++){
		var	st = $("#c_cvalue"+i).val();
		 
		 if(st == '2 [c]'){
			 $('.cCopClass').show();
				 $('#c_cother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
		 styleC = $(".cCopClass").css("display");
		if(i == cCope && styleC != "none"  ){
			 if(st == '2 [c]'){
				 $('#c_cother'+i).show();
					$('.cCopClass'+i).show();
			 }
			 else {
				 $('#c_cother'+i).hide();
					$('.cCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=cCope; i++){
			 $('#c_cother'+i).show();
			 $('.cCopClass'+i).hide();
		 }
	}
	
}

function onECopeChange(e,ind){
	if(e.value == '1'){
		$('.eCopClass'+ind).show();
		$('.eCopClass').show();
	}
	else{
		$('.eCopClass'+ind).hide();
		$('.eCopClass').hide();
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
		$('#c_esubvalue'+ind).val(0);
	}
	
	var styleC = $(".eCopClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_evalue"+i).val();
		 if(st == '1'){
			 $('.eCopClass').show();
				 $('#c_esubvalue'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
		 styleC = $(".eCopClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == '1'){
				 $('#c_esubvalue'+i).show();
					$('.eCopClass'+i).show();
			 }
			 else {
				 $('#c_esubvalue'+i).hide();
					$('.eCopClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalue'+i).show();
			 $('.eCopClass'+i).hide();
		 }
	}
	
	onECopeSubChange(e,ind);
	
}

function onECopeSubChange(e,ind){
	if(e.value == 'k'){
		$('.eCop_otherClass'+ind).show();
		$('.eCop_otherClass').show();
	}
	else{
		$('.eCop_otherClass'+ind).hide();
		$('.eCop_otherClass').hide();
	}
	
	var styleC = $(".eCop_otherClass").css("display");
	for(var i = 1; i<=eCope; i++){
		var	st = $("#c_esubvalue"+i).val();
		 
		 if(st == 'k'){
			 $('.eCop_otherClass').show();
				 $('#c_esubvalueother'+i).show();
		 }
		 else if(i == cCope && styleC == "none"  ){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
		 styleC = $(".eCop_otherClass").css("display");
		if(i == eCope && styleC != "none"  ){
			 if(st == 'k'){
				 $('#c_esubvalueother'+i).show();
					$('.eCop_otherClass'+i).show();
			 }
			 else {
				 $('#c_esubvalueother'+i).hide();
					$('.eCop_otherClass'+i).show();
			}
			 
		 }
	}
	if(styleC == "none"  ){
		for(var i = 1; i<=eCope; i++){
			 $('#c_esubvalueother'+i).show();
			 $('.eCop_otherClass'+i).hide();
		 }
	}
}

function setDiagnosis(){

}

function statusChange(Shape,position,Shape_status){
	

	//26-01-1994
// 	if( Shape_status==1 || Shape_status==0){
// 		$('.diagnosisClass'+Shape+position).hide();
// 		$('.diagnosisClass'+Shape).hide();
// 		$('.addbtClass'+Shape+position).hide();
// 		$('.addbtClass'+Shape).hide();
		
// 	}else{
// 		$('.diagnosisClass'+Shape+position).show();
// 		$('.diagnosisClass'+Shape).show();
// 		$('.addbtClass'+Shape+position).show();
// 		$('.addbtClass'+Shape).show();
// 	}

	var slable ="";
	ShapeCount = 1;
	if(Shape == 1) {
		slable = "s";
		ShapeCount = sShape;
			}
			 else if(Shape == 2) {
				 slable = "h";
				 ShapeCount = hShape;
			} else if(Shape == 3) {
				slable = "a";
				ShapeCount = aShape;
			} else if(Shape == 4) {
				slable = "p";
				ShapeCount = pShape;
			} else if(Shape == 5) {
				slable = "e";
				ShapeCount = eShape;
			}
	
	if((Shape_status == 1 || Shape_status == 0) ) {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", true);
		$("#" + slable + "_to_date"+ position).val("");
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).hide();
			
			if (ShapeCount ==1) {
				$('.addbtClass' + Shape + position).hide();
		 		$('.addbtClass' + Shape).hide();
		 		$('.diagnosisClass' + Shape).hide();
			}
	 		
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","hidden");
	 		$('.addbtClass' + Shape + position).show();
	 		$('.addbtClass' + Shape).show();
		}
		
	}else {
		$("#" + slable + "_to_date"+ position).datepicker("option", "disabled", false);
		if ( position==1) {
			$('.diagnosisClass' + Shape + position).show();
			$('.diagnosisClass' + Shape).show();
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		else{
			$('.diagnosisClass' + Shape + position).css("visibility","visible");
		}
		$('.addbtClass' + Shape + position).show();
		$('.addbtClass' + Shape).show();
	}
	
	 $.post('getShapevalueUrl?' + key + "=" + value, {Shape_status:Shape_status }, function(j){
		 
		 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
             var length = j.length;
             if(length != 0){           
		             for ( var i = 0; i < length; i++) {                  
		                             options += '<option value="' + j[i][1]+ '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
		             }  
		             
		             if(Shape==1){
		            	   $("select#sShape_value"+position).html(options);
		    			 
		    		 }
		    		 else  if(Shape==2){
		    			   $("select#hShape_value"+position).html(options);
		    		 }
		    		else  if(Shape==3){
		    			   $("select#aShape_value"+position).html(options);
		    				 }
		    		else  if(Shape==4){
		    			   $("select#pShape_value"+position).html(options);
		    		}
		    		else  if(Shape==5){
		    			   $("select#eShape_value"+position).html(options);
		    		}
		             
		            
             }
		 
		 
	 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	 
// 	}
}

function AutoCompleteDiagnosis(ele){
	
	var code = ele.value;
	var susNoAuto =$("#"+ele.id);
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : "getMNHDistinctICDList?" + key + "=" + value,
				data : {
					a : code,b:"ALL"
				},
				success : function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for (var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					susval.push("Others");

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
				alert("Please Enter Approved Diagnosis ");			
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {

		}
	});
	
}





sShape=1;
function sShape_add_fn(q){
	 if( $('#sShape_add'+q).length )        
	 {
			$("#sShape_add"+q).hide();
			 $("#sShape_remove"+q).hide();	

	 }
	 sShape=sShape+1;

	 $("input#sShape_count").val(sShape);
	 
	$("table#s_madtable").append('<tr id="tr_sShape'+sShape+'">'
	+'<td  style="width: 2%;">'+sShape+'</td>'
	+'<td>'
	+'<select name="s_status'+sShape+'" id="s_status'+sShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(1,'+sShape+',this.options[this.selectedIndex].value)">'

	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="sShape_value'+sShape+'" id="sShape_value'+sShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'






	+'<td style="""> '
	+' <input type="text" name="s_from_date'+sShape+'" id="s_from_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="s_to_date'+sShape+'" id="s_to_date'+sShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'

	
	+'  <td style="visibility:hidden; "  class="diagnosisClass1'+sShape+'">'
	+' <input type="text" name="_diagnosis_code1'+sShape+'" id="_diagnosis_code1'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'


	+' <td style="display:none;"><input type="text" id="sShape_ch_id'+sShape+'" name="sShape_ch_id'+sShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	+'</tr>');
	
	 
	 datepicketDate('s_from_date'+sShape);
	 datepicketDate('s_to_date'+sShape);
	 $( "#s_to_date"+sShape ).datepicker( "option", "maxDate", null);
	 statusChange(1,sShape,$("#s_status"+sShape).val());
	 
// 	if(q!=0){
// 		var preShape=sShape-1;
// 			$("#sShape_value"+preShape+" > option").clone().appendTo("#sShape_value"+sShape);
// 			$("#s_status"+sShape).val($("#s_status"+preShape).val());
// 			$("#sShape_value"+sShape).val($("#sShape_value"+preShape).val());
// 			statusChange(1,sShape,$("#s_status"+preShape).val());
// 			$("#s_status"+sShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='1']").remove();
// 			$("#s_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}



hShape=1;
function hShape_add_fn(q){
	 if( $('#hShape_add'+q).length )        
	 {
			$("#hShape_add"+q).hide();
			 $("#hShape_remove"+q).hide();	

	 }
	 hShape=hShape+1;
	
	
	 $("input#hShape_count").val(hShape);
	 
	$("table#h_madtable").append('<tr id="tr_hShape'+hShape+'">'
	+'<td  style="width: 2%;">'+hShape+'</td>'
	+'<td>'
	+'<select name="h_status'+hShape+'" id="h_status'+hShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(2,'+hShape+',this.options[this.selectedIndex].value)">'

	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="hShape_value'+hShape+'" id="hShape_value'+hShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'






	
	+'<td style="""> '
	+' <input type="text" name="h_from_date'+hShape+'" id="h_from_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="h_to_date'+hShape+'" id="h_to_date'+hShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	
	+'  <td style="visibility:hidden; "  class="diagnosisClass2'+hShape+'">'
	+' <input type="text" name="_diagnosis_code2'+hShape+'" id="_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'


	+' <td style="display:none;"><input type="text" id="hShape_ch_id'+hShape+'" name="hShape_ch_id'+hShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+hShape+'" onclick="hShape_add_fn('+hShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "hShape_remove'+hShape+'" onclick="hShaperemove_fn('+hShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	 datepicketDate('h_from_date'+hShape);
	 datepicketDate('h_to_date'+hShape);
	 $( "#h_to_date"+hShape ).datepicker( "option", "maxDate", null);
	 statusChange(2,hShape,$("#h_status"+hShape).val());
// 	if(q!=0){
// 		var preShape=hShape-1;
// 			$("#hShape_value"+preShape+" > option").clone().appendTo("#hShape_value"+hShape);
// 			$("#h_status"+hShape).val($("#h_status"+preShape).val());
// 			$("#hShape_value"+hShape).val($("#hShape_value"+preShape).val());
// 			statusChange(2,hShape,$("#h_status"+preShape).val());
// 			$("#h_status"+hShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='1']").remove();
// 			$("#h_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function hShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_hShape"+R).remove(); 
						 R = R-1;
					 $("#hShape_add"+R).show();
					 $("#hShape_remove"+R).show();
					 $("input#hShape_count").val(R);
					 hShape=hShape-1;
// 					 if(hShape == 1){
// 						var s_val = $("#h_status"+hShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#h_status"+hShape).html();
// 						 $('#h_status'+hShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#h_status"+hShape).val(s_val);
// 						}
	 }

	 }


aShape=1;
function aShape_add_fn(q){
	 if( $('#aShape_add'+q).length )        
	 {
			$("#aShape_add"+q).hide();
			 $("#aShape_remove"+q).hide();	

	 }
	 aShape=aShape+1;
	 $("input#aShape_count").val(aShape);
	 
	$("table#a_madtable").append('<tr id="tr_aShape'+aShape+'">'
	+'<td  style="width: 2%;">'+aShape+'</td>'
	+'<td>'
	+'<select name="a_status'+aShape+'" id="a_status'+aShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(3,'+aShape+',this.options[this.selectedIndex].value)">'

	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="aShape_value'+aShape+'" id="aShape_value'+aShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'


	+'<td style="""> '
	+' <input type="text" name="a_from_date'+aShape+'" id="a_from_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="a_to_date'+aShape+'" id="a_to_date'+aShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass3'+aShape+'">'
	+' <input type="text" name="_diagnosis_code3'+aShape+'" id="_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'

	+' <td style="display:none;"><input type="text" id="aShape_ch_id'+aShape+'" name="aShape_ch_id'+aShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	+'</tr>');
	
	 datepicketDate('a_from_date'+aShape);
	 datepicketDate('a_to_date'+aShape);
	 $( "#a_to_date"+aShape ).datepicker( "option", "maxDate", null);
	 statusChange(3,aShape,$("#a_status"+aShape).val());
	 
// 	if(q!=0){
// 		var preShape=aShape-1;
// 			$("#aShape_value"+preShape+" > option").clone().appendTo("#aShape_value"+aShape);
// 			$("#a_status"+aShape).val($("#a_status"+preShape).val());
// 			$("#aShape_value"+aShape).val($("#aShape_value"+preShape).val());
// 			statusChange(3,aShape,$("#a_status"+preShape).val());
// 			$("#a_status"+aShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='1']").remove();
// 			$("#a_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}


function aShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_aShape"+R).remove(); 
						 R = R-1;
					 $("#aShape_add"+R).show();
					 $("#aShape_remove"+R).show();	
					 $("input#aShape_count").val(R);
					 aShape=aShape-1;
// 					 if(aShape == 1){
// 						var s_val = $("#a_status"+aShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#a_status"+aShape).html();
// 						 $('#a_status'+aShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#a_status"+aShape).val(s_val);
// 						}
	 }

	 }
	 


pShape=1;
function pShape_add_fn(q){
	 if( $('#pShape_add'+q).length )        
	 {
			$("#pShape_add"+q).hide();
			 $("#pShape_remove"+q).hide();	

	 }
	 pShape=pShape+1;
	 $("input#pShape_count").val(pShape);
	
	 
	$("table#p_madtable").append('<tr id="tr_pShape'+pShape+'">'
	+'<td  style="width: 2%;">'+pShape+'</td>'
	+'<td>'
	+'<select name="p_status'+pShape+'" id="p_status'+pShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(4,'+pShape+',this.options[this.selectedIndex].value)">'

	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="pShape_value'+pShape+'" id="pShape_value'+pShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'






	
	+'<td style="""> '
	+' <input type="text" name="p_from_date'+pShape+'" id="p_from_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="p_to_date'+pShape+'" id="p_to_date'+pShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass4'+pShape+'">'
	+' <input type="text" name="_diagnosis_code4'+pShape+'" id="_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'

	+' <td style="display:none;"><input type="text" id="pShape_ch_id'+pShape+'" name="pShape_ch_id'+pShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	+'</tr>');
	
	 datepicketDate('p_from_date'+pShape);
	 datepicketDate('p_to_date'+pShape);
	 $( "#p_to_date"+pShape ).datepicker( "option", "maxDate", null);
	 statusChange(4,pShape,$("#p_status"+pShape).val());
	 
// 	if(q!=0){
// 		var preShape=pShape-1;
// 			$("#pShape_value"+preShape+" > option").clone().appendTo("#pShape_value"+pShape);
// 			$("#p_status"+pShape).val($("#p_status"+preShape).val());
// 			$("#pShape_value"+pShape).val($("#pShape_value"+preShape).val());
// 			statusChange(4,pShape,$("#p_status"+preShape).val());
// 			$("#p_status"+pShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='1']").remove();
// 			$("#p_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function pShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_pShape"+R).remove(); 
						 R = R-1;
					 $("#pShape_add"+R).show();
					 $("#pShape_remove"+R).show();	
					 $("input#pShape_count").val(R);
					 pShape=pShape-1;
// 					 if(pShape == 1){
// 						var s_val = $("#p_status"+pShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#p_status"+pShape).html();
// 						 $('#p_status'+pShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#p_status"+pShape).val(s_val);
// 						}
	 }

	 }

eShape=1;
function eShape_add_fn(q){
	 if( $('#eShape_add'+q).length )        
	 {
			$("#eShape_add"+q).hide();
			 $("#eShape_remove"+q).hide();	
	 }
	 eShape=eShape+1;
	 $("input#eShape_count").val(eShape);
	 
	$("table#e_madtable").append('<tr id="tr_eShape'+eShape+'">'
	+'<td  style="width: 2%;">'+eShape+'</td>'
	+'<td>'
	+'<select name="e_status'+eShape+'" id="e_status'+eShape+'" '
	+'	class="form-control-sm form-control"  onchange="statusChange(5,'+eShape+',this.options[this.selectedIndex].value)">'

	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="eShape_value'+eShape+'" id="eShape_value'+eShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	

	
	+'<td style="""> '
	+' <input type="text" name="e_from_date'+eShape+'" id="e_from_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'<td style="""> '
	+' <input type="text" name="e_to_date'+eShape+'" id="e_to_date'+eShape+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
	+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
	+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">'
	+'</td>'
	+'  <td style="visibility:hidden; "  class="diagnosisClass5'+eShape+'">'
	+' <input type="text" name="_diagnosis_code5'+eShape+'" id="_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
	+'   </td>'

	+' <td style="display:none;"><input type="text" id="eShape_ch_id'+eShape+'" name="eShape_ch_id'+eShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	+'</tr>');
	
	
	 datepicketDate('e_from_date'+eShape);
	 datepicketDate('e_to_date'+eShape);
	 $( "#e_to_date"+eShape ).datepicker( "option", "maxDate", null);
	 statusChange(5,eShape,$("#e_status"+eShape).val());
	 
	 
// 	if(q!=0){
// 		var preShape=eShape-1;
// 			$("#eShape_value"+preShape+" > option").clone().appendTo("#eShape_value"+eShape);
// 			$("#e_status"+eShape).val($("#e_status"+preShape).val());
// 			$("#eShape_value"+eShape).val($("#eShape_value"+preShape).val());
// 			statusChange(5,eShape,$("#e_status"+preShape).val());
// 			$("#e_status"+eShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='1']").remove();
// 			$("#e_status"+preShape+" option[value='0']").remove();
// 		 }
	setDiagnosis();
}

function eShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_eShape"+R).remove(); 
						 R = R-1;
					 $("#eShape_add"+R).show();
					 $("#eShape_remove"+R).show();
					 $("input#eShape_count").val(R);
					 eShape=eShape-1;
// 					 if(eShape == 1){
// 						var s_val = $("#e_status"+eShape).val();
// 						var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 						 var lis2 = $("#e_status"+eShape).html();
// 						 $('#e_status'+eShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 						 $("#e_status"+eShape).val(s_val);
// 						}
	 }
	 
	 
}
 
	 
	 
	 
	 
	 
	 
	 
	 
	 cCope=1;
	 function cCope_add_fn(q){
	 	 if( $('#cCope_add'+q).length )        
	 	 {
	 			$("#cCope_add"+q).hide();
	 			 $("#cCope_remove"+q).hide();	
	 	 }
	 	 cCope=cCope+1;
	 	 $("input#cCope_count").val(cCope);
	 	 
	 	$("table#c_cmadtable").append('<tr id="tr_cCope'+cCope+'">'
	 	+'<td  style="width: 2%;">'+cCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_cvalue'+cCope+'" id="c_cvalue'+cCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+cCope+')">'

	 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="cCopClass'+cCope+'" >'
		+'<input type="text" name="c_cother'+cCope+'" id="c_cother'+cCope+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
		+' </td>'




	 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+cCope+'" name="cCope_ch_id'+cCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	 	+'</tr>');
	 	
	 	var thisT = document.getElementById('c_cvalue'+cCope)
	 	onCCopeChange(thisT,cCope);
	 	$("#c_cvalue"+cCope+" option[value='0']").remove();
	 	$("#c_cvalue"+cCope+" option[value='1']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='0']").remove();
		$("#c_cvalue"+(cCope-1)+" option[value='1']").remove();
	 	
	 }

	 function cCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_cCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#cCope_add"+R).show();
	 					 $("#cCope_remove"+R).show();
	 					 $("input#cCope_count").val(R);
	 					cCope=cCope-1;
	 					if(cCope == 1){
							var s_val = $("#c_cvalue"+cCope).val();
							var lis1 = '<option value="${getcCopeList[0][1]}" name="${getcCopeList[0][0]}">${getcCopeList[0][0]}</option>'
							var lis2 = '<option value="${getcCopeList[1][1]}" name="${getcCopeList[1][0]}">${getcCopeList[1][0]}</option>'
							var lis3 = $("#c_cvalue"+cCope).html();
							 $('#c_cvalue'+cCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_cvalue"+cCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_cvalue'+cCope)
	 				 	onCCopeChange(thisT,cCope);
	 	 }

	 }
	 
	
	 
	 oCope=1;
	 function oCope_add_fn(q){
	 	 if( $('#oCope_add'+q).length )        
	 	 {
	 			$("#oCope_add"+q).hide();
	 			 $("#oCope_remove"+q).hide();	
	 	 }
	 	 oCope=oCope+1;
	 	 $("input#oCope_count").val(oCope);
	 	 
	 	$("table#c_omadtable").append('<tr id="tr_oCope'+oCope+'">'
	 	+'<td  style="width: 2%;">'+oCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_ovalue'+oCope+'" id="c_ovalue'+oCope+'" '
	 	+'	class="form-control-sm form-control"  >'

	 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	




	 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+oCope+'" name="oCope_ch_id'+oCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	 	+'</tr>');
	 	$("#c_ovalue"+oCope+" option[value='0']").remove();
		$("#c_ovalue"+(oCope-1)+" option[value='0']").remove();
	 	
	 }

	 function oCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_oCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#oCope_add"+R).show();
	 					 $("#oCope_remove"+R).show();
	 					 $("input#oCope_count").val(R);
	 					oCope=oCope-1;
	 					if(oCope == 1){
							var s_val = $("#c_ovalue"+oCope).val();
							var lis1 = '<option value="${getoCopeList[0][1]}" name="${getoCopeList[0][0]}">${getoCopeList[0][0]}</option>'
							var lis2 = $("#c_ovalue"+oCope).html();
							 $('#c_ovalue'+oCope).empty().append(""+lis1+lis2);
							 $("#c_ovalue"+oCope).val(s_val);
							}
	 	 }

	 }
	 
	 pCope=1;
	 function pCope_add_fn(q){
	 	 if( $('#pCope_add'+q).length )        
	 	 {
	 			$("#pCope_add"+q).hide();
	 			 $("#pCope_remove"+q).hide();	
	 	 }
	 	 pCope=pCope+1;
	 	 $("input#pCope_count").val(pCope);
	 	 
	 	$("table#c_pmadtable").append('<tr id="tr_pCope'+pCope+'">'
	 	+'<td  style="width: 2%;">'+pCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_pvalue'+pCope+'" id="c_pvalue'+pCope+'" '
	 	+'	class="form-control-sm form-control"  >'

	 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	




	 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+pCope+'" name="pCope_ch_id'+pCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	 	+'</tr>');
	 	$("#c_pvalue"+pCope+" option[value='0']").remove();
	 	$("#c_pvalue"+pCope+" option[value='1']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='0']").remove();
		$("#c_pvalue"+(pCope-1)+" option[value='1']").remove();
	 	
	 }

	 function pCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_pCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#pCope_add"+R).show();
	 					 $("#pCope_remove"+R).show();
	 					 $("input#pCope_count").val(R);
	 					pCope=pCope-1;
	 					if(pCope == 1){
							var s_val = $("#c_pvalue"+pCope).val();
							var lis1 = '<option value="${getpCopeList[0][1]}" name="${getpCopeList[0][0]}">${getpCopeList[0][0]}</option>'
							var lis2 = '<option value="${getpCopeList[1][1]}" name="${getpCopeList[1][0]}">${getpCopeList[1][0]}</option>'
							var lis3 = $("#c_pvalue"+pCope).html();
							 $('#c_pvalue'+pCope).empty().append(""+lis1+lis2+lis3);
							 $("#c_pvalue"+pCope).val(s_val);
							}
	 	 }

	 }
	 
	 
	 eCope=1;
	 function eCope_add_fn(q){
	 	 if( $('#eCope_add'+q).length )        
	 	 {
	 			$("#eCope_add"+q).hide();
	 			 $("#eCope_remove"+q).hide();	
	 	 }
	 	 eCope=eCope+1;
	 	 $("input#eCope_count").val(eCope);
	 	 
	 	$("table#c_emadtable").append('<tr id="tr_eCope'+eCope+'">'
	 	+'<td  style="width: 2%;">'+eCope+'</td>'
	 	+'<td>'
	 	+'<select name="c_evalue'+eCope+'" id="c_evalue'+eCope+'" '
	 	+'	class="form-control-sm form-control" onchange="onECopeChange(this,'+eCope+')" >'

	 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="eCopClass'+eCope+'">'
	 	+'<select name="c_esubvalue'+eCope+'" id="c_esubvalue'+eCope+'" onchange="onECopeSubChange(this,'+eCope+')"'
	 	+'			class="form-control-sm form-control" >'
	 	+'			<option value="0">--Select--</option>'																
	 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
	 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'			</c:forEach></select>   </td>'
	 	+'<td style="display:none;" class="eCop_otherClass'+eCope+'" >'
	 	+'	 <input type="text" name="c_esubvalueother'+eCope+'" id="c_esubvalueother'+eCope+'" class="form-control-sm form-control" autocomplete="off" >'						                              
	 	+'	   </td>'




	 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+eCope+'" name="eCope_ch_id'+eCope+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   

	 	+'</tr>');
	 	
	 	$("#c_evalue"+eCope+" option[value='0']").remove();
		$("#c_evalue"+(eCope-1)+" option[value='0']").remove();
		var thisT = document.getElementById('c_evalue'+eCope)
	 	onECopeChange(thisT,eCope);
	 	
	 }

	 function eCoperemove_fn(R){
	 	var r = confirm("Are You Sure! You Want To Delete This Row");
	 	 if(r){				   
	 					 $("tr#tr_eCope"+R).remove(); 
	 						 R = R-1;
	 					 $("#eCope_add"+R).show();
	 					 $("#eCope_remove"+R).show();
	 					 $("input#eCope_count").val(R);
	 					eCope=eCope-1;
	 					if(eCope == 1){
							var s_val = $("#c_evalue"+eCope).val();
							var lis1 = '<option value="${geteCopeList[0][1]}" name="${geteCopeList[0][0]}">${geteCopeList[0][0]}</option>'
							var lis2 = $("#c_evalue"+eCope).html();
							 $('#c_evalue'+eCope).empty().append(""+lis1+lis2);
							 $("#c_evalue"+eCope).val(s_val);
							}
	 					var thisT = document.getElementById('c_evalue'+eCope)
	 				 	onECopeChange(thisT,eCope);
	 	 }

	 }
	 
	 
	 function medical_cat_validation(){
		 var count_classi = 0;	
		 
		/*  if($("#madical_authority").val().trim()==""){
				alert("Please Enter Authority");
				$("#madical_authority").focus();				
				return false;
			}
		 if(lengthValidation($("input#madical_authority").val().trim(),auth_length)){
				alert("Authority Length Should be Less or Equal To 100");
				$("#madical_authority").focus();
				return false;

			}
		 
		 if($("input#madical_date_of_authority").val().trim()=="" || $("#madical_date_of_authority").val().trim()=="DD/MM/YYYY"){
				alert("Please Enter Date of Authority");
				$("#madical_date_of_authority").focus();
				return false;
			}
		

		 	var enroll_date=$("#enroll_date").val();
			if(getformatedDate($("input#enroll_date").val()) > getformatedDate($("#madical_date_of_authority").val())) {
				alert("Authority Date should be Greater than Enrollment Date");
				$("#madical_date_of_authority").focus();
				return false;
		    } */		 

			if(count_classi >= 3){
				$("#mad_classification_count").val("Z");
			}
			else if(count_classi == 2){
				$("#mad_classification_count").val("Y");
			}
			else if(count_classi == 1){
				$("#mad_classification_count").val("X");
			}
			else{
				$("#mad_classification_count").val("NIL");
			}
		  

	}
	 
	 
	 


	 function getsShapeDetails() {

		    var p_id = $('#jco_id').val();
		    var Shape = 'S';
		    var i = 0;
		    var app_status= '${status6}';
		    $.post('madical_cat_GetData_jco?' + key + "=" + value, {
		        p_id: p_id,
		        Shape: Shape,
		        app_status: app_status
		    }, function (j) {
		        var v = j.length;


		        if (v != 0) {

		            $('#s_madtable').show();
		            $('#s_madtbody').empty();
		            for (i; i < v; i++) {
		                x = i + 1;
		                var fd = "DD/MM/YYYY";
		                var td = "DD/MM/YYYY";

		                if (j[i][2] != null && j[i][2] != "") {
		                    fd = ParseDateColumncommission(j[i][2]);
		                    // 				  	 td=ParseDateColumncommission(j[i][3]);
		                }
		                if (j[i][3] != null && j[i][3] != "") {
		                    td = ParseDateColumncommission(j[i][3]);
		                }
		                if (j[i][0] == 1) {
		                    td = "";
		                }
		                $("table#s_madtable").append('<tr id="tr_sShape' + x + '">'
		                    + '<td class="sShape_srno" style="width: 2%;">' + x + '</td>'
		                    + '<td>'

		                    + '<select name="s_status' + x + '" id="s_status' + x + '" '
		                    + '	class="form-control-sm form-control"  onchange="statusChange(1,' + x + ',this.options[this.selectedIndex].value)">'
		                    + '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
		                    + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
		                    + '	</c:forEach>'
		                    + '		</select>'
		                    + '  </td>'
		                    + '   <td style="">'
		                    + '<select name="sShape_value' + x + '" id="sShape_value' + x + '" '
		                    + '		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
		                    + '		</select>	</td>'


		                    + '<td style=""> '
		                    + ' <input type="text" name="s_from_date' + x + '" id="s_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
		                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '">'
		                    + '</td>'
		                    + '<td style=""> '
		                    + ' <input type="text" name="s_to_date' + x + '" id="s_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
		                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '">'
		                    + '</td>'

		                    + '  <td style="visibility:hidden; "  class="diagnosisClass1' + x + '">'
		                    + ' <input type="text" name="_diagnosis_code1' + x + '" id="_diagnosis_code1' + x + '" value="' + j[i][4] + '" class="form-control-sm form-control" autocomplete="off" '
		                    + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'
		                    + '   </td>'

		                    + ' <td style="display:none;"><input type="text" id="sShape_ch_id' + x + '" name="sShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>'



		                    + '</tr>');

		                datepicketDate('s_from_date' + x);
		                datepicketDate('s_to_date' + x);
		                $("#s_to_date" + x).datepicker("option", "maxDate", null);

		                $("#s_status" + x).val(j[i][0]);
		                $.ajaxSetup({
		                    async: false
		                });

		                statusChange(1, x, j[i][0]);

		                $("#sShape_value" + x).val(j[i][1]);
		                // 						if(x>1){
		                // 							$("#s_status"+x+" option[value='1']").remove();
		                // 							$("#s_status"+(x-1)+" option[value='1']").remove();

		                // 						}
		                // 						else {
		                var thisT = document.getElementById('sShape_value' + x);
		                onShapeValueChange(thisT, 's');
		                // 						}


		            }

		            // Disable fields after table is generated
		            $('tr[id^="tr_sShape"]').each(function() {
		                $(this).find('input, select').prop('disabled', true); // Disable all input and select fields within the row
		            });

		            sShape = v;
		            $("input#sShape_count").val(v);
		            $("input#sShapeOld_count").val(v);

		            $("#madical_authority").val(j[i - 1][6]);
		            $("#madical_date_of_authority").val(ParseDateColumncommission(j[i - 1][7]));
		            $('#mad_classification_count').val(j[i - 1][8]);

		            if (j[i - 1][11] != null && j[i - 1][11] != "" && j[i - 1][12] != null && j[i - 1][12] != "" && j[i - 1][13] != null && j[i - 1][13] != "") {
		                $("#check_1bx").prop("checked", true);
		                oncheck_1bx();
		                fd = ParseDateColumncommission(j[i - 1][11]);
		                td = ParseDateColumncommission(j[i - 1][12]);
		                $("#1bx_from_date").val(fd);
		                $("#1bx_to_date").val(td);
		                $("#1bx_diagnosis_code").val(j[i - 1][13]);
		            }


		            setDiagnosis();
		        }


		    });
		}


function gethShapeDetails() {
    var p_id = $('#jco_id').val();
    var Shape = 'H';
    var i = 0;
    var app_status= '${status6}';
    $.post('madical_cat_GetData_jco?' + key + "=" + value, { p_id: p_id, Shape: Shape,app_status: app_status }, function (j) {
        var v = j.length;

        if (v != 0) {
            $('#h_madtable').show();
            $('#h_madtbody').empty();
            for (i; i < v; i++) {
                x = i + 1;

                var fd = "DD/MM/YYYY";
                var td = "DD/MM/YYYY";

                if (j[i][2] != null && j[i][2] != "") {
                    fd = ParseDateColumncommission(j[i][2]);
                    // td=ParseDateColumncommission(j[i][3]);
                }
                if (j[i][3] != null && j[i][3] != "") {
                    td = ParseDateColumncommission(j[i][3]);
                }
				 if (j[i][0]==1) {
					 td="";
					 }

                $("table#h_madtable").append('<tr id="tr_hShape' + x + '">'
                    + '<td style="width: 2%;">' + x + '</td>'
                    + '<td>'
                     + '<select name="h_status' + x + '" id="h_status' + x + '" '
                    + '	class="form-control-sm form-control"  onchange="statusChange(2,' + x + ',this.options[this.selectedIndex].value)" disabled>'
                    + '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
                    + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
                    + '	</c:forEach>'
                    + '		</select>'
                    + '  </td>'
                    + '   <td style="">'
                    + '<select name="hShape_value' + x + '" id="hShape_value' + x + '" '
                    + '		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')" disabled>'
                    + '		</select>	</td>'
					+ '<td style=""> '
                    + ' <input type="text" name="h_from_date' + x + '" id="h_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '" disabled>'
                    + '</td>'
                    + '<td style=""> '
                    + ' <input type="text" name="h_to_date' + x + '" id="h_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '" disabled>'
                    + '</td>'
                     +  '<td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
                    + ' <input type="text" name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
                    +'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" disabled>	'
					+  '   </td>'

                    + ' <td style="display:none;"><input type="text" id="hShape_ch_id' + x + '" name="hShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" disabled></td>'
                   
					
					+ '</tr>');

                datepicketDate('h_from_date' + x);
                datepicketDate('h_to_date' + x);
                $("#h_to_date" + x).datepicker("option", "maxDate", null);


                $("#h_status" + x).val(j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                 statusChange(2, x, j[i][0]);
				  $("#hShape_value" + x).val(j[i][1]);
							var thisT = document.getElementById('hShape_value'+x);
							onShapeValueChange(thisT,'h');


            }
            hShape = v;
            $("input#hShape_count").val(v);
            $("input#hShapeOld_count").val(v);
            $("#hShape_add" + v).show();
			setDiagnosis();
        }

    });
}



function getaShapeDetails() {
    var p_id = $('#jco_id').val();
    var Shape = 'A';
    var i = 0;
    var app_status= '${status6}';
    $.post('madical_cat_GetData_jco?' + key + "=" + value, { p_id: p_id, Shape: Shape, app_status: app_status }, function (j) {
        var v = j.length;

        if (v != 0) {
			classification=j[0][13];
            $('#a_madtable').show();
            $('#a_madtbody').empty();
            for (i; i < v; i++) {
                x = i + 1;

                var fd = "DD/MM/YYYY";
                var td = "DD/MM/YYYY";

                if (j[i][2] != null && j[i][2] != "") {
                    fd = ParseDateColumncommission(j[i][2]);
                    // td=ParseDateColumncommission(j[i][3]);
                }
                  if (j[i][3] != null && j[i][3] != "") {
                    td = ParseDateColumncommission(j[i][3]);
                }
				 if (j[i][0]==1) {
					 td="";
					 }


                $("table#a_madtable").append('<tr id="tr_aShape' + x + '">'
                    + '<td style="width: 2%;">' + x + '</td>'
                    + '<td>'
                    + '<select name="a_status' + x + '" id="a_status' + x + '" '
                    + '	class="form-control-sm form-control"  onchange="statusChange(3,' + x + ',this.options[this.selectedIndex].value)" disabled>'
                    + '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
                    + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
                    + '	</c:forEach>'
                    + '		</select>'
                    + '  </td>'
                    + '   <td style="">'
                    + '<select name="aShape_value' + x + '" id="aShape_value' + x + '" '
                    + '		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')" disabled>'
                    + '		</select>	</td>'

                    + '<td style=""> '
                    + ' <input type="text" name="a_from_date' + x + '" id="a_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '" disabled>'
                    + '</td>'
                    + '<td style=""> '
                    + ' <input type="text" name="a_to_date' + x + '" id="a_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '" disabled>'
                    + '</td>'
                    +  '<td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
                    + ' <input type="text" name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
                    +'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" disabled>	'
                    +   ' </td>'
                     + ' <td style="display:none;"><input type="text" id="aShape_ch_id' + x + '" name="aShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" disabled></td>'
					
					+ '</tr>');

                datepicketDate('a_from_date' + x);
                datepicketDate('a_to_date' + x);
                $("#a_to_date" + x).datepicker("option", "maxDate", null);


                $("#a_status" + x).val(j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                statusChange(3, x, j[i][0]);
                 $("#aShape_value" + x).val(j[i][1]);
							var thisT = document.getElementById('aShape_value'+x);
							onShapeValueChange(thisT,'a');
				
            }
            aShape = v;
            $("input#aShape_count").val(v);
            $("input#aShapeOld_count").val(v);
            $("#aShape_add" + v).show();
			setDiagnosis();
        }

    });
}


function getpShapeDetails() {
    var p_id = $('#jco_id').val();
    var Shape = 'P';
    var i = 0;
    var app_status= '${status6}';
    $.post('madical_cat_GetData_jco?' + key + "=" + value, { p_id: p_id, Shape: Shape,app_status: app_status }, function (j) {
        var v = j.length;

        if (v != 0) {
            $('#p_madtable').show();
            $('#p_madtbody').empty();
            for (i; i < v; i++) {
                x = i + 1;

                var fd = "DD/MM/YYYY";
                var td = "DD/MM/YYYY";

                if (j[i][2] != null && j[i][2] != "") {
                    fd = ParseDateColumncommission(j[i][2]);
                }
                  if (j[i][3] != null && j[i][3] != "") {
                    td = ParseDateColumncommission(j[i][3]);
                }
				 if (j[i][0]==1) {
					 td="";
					 }

                $("table#p_madtable").append('<tr id="tr_pShape' + x + '">'
                    + '<td style="width: 2%;">' + x + '</td>'
                    + '<td>'
                    + '<select name="p_status' + x + '" id="p_status' + x + '" '
                    + '	class="form-control-sm form-control"  onchange="statusChange(4,' + x + ',this.options[this.selectedIndex].value)" disabled>'
                    + '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
                    + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
                    + '	</c:forEach>'
                    + '		</select>'
                    + '  </td>'
                    + '   <td style="">'
                    + '<select name="pShape_value' + x + '" id="pShape_value' + x + '" '
                    + '		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')" disabled>'
                    + '		</select>	</td>'

                    + '<td style=""> '
                    + ' <input type="text" name="p_from_date' + x + '" id="p_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '" disabled>'
                    + '</td>'
                    + '<td style=""> '
                    + ' <input type="text" name="p_to_date' + x + '" id="p_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '" disabled>'
                    + '</td>'
					+  '<td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
                    + ' <input type="text" name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
                    +'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" disabled>	'
                    +   ' </td>'
                    + ' <td style="display:none;"><input type="text" id="pShape_ch_id' + x + '" name="pShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" disabled></td>'
					
					+ '</tr>');

                datepicketDate('p_from_date' + x);
                datepicketDate('p_to_date' + x);
                $("#p_to_date" + x).datepicker("option", "maxDate", null);


                $("#p_status" + x).val(j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                statusChange(4, x, j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                $("#pShape_value" + x).val(j[i][1]);
			
				var thisT = document.getElementById('pShape_value'+x);
							onShapeValueChange(thisT,'p');
           
            }
            pShape = v;
            $("input#pShape_count").val(v);
            $("input#pShapeOld_count").val(v);
            $("#pShape_add" + v).show();
			 setDiagnosis();
        }

    });
}


function geteShapeDetails() {
    var p_id = $('#jco_id').val();
    var Shape = 'E';
    var i = 0;
    var app_status= '${status6}';
    $.post('madical_cat_GetData_jco?' + key + "=" + value, { p_id: p_id, Shape: Shape,app_status: app_status }, function (j) {
        var v = j.length;

        if (v != 0) {
            $('#e_madtable').show();
            $('#e_madtbody').empty();
            for (i; i < v; i++) {
                x = i + 1;

                var fd = "DD/MM/YYYY";
                var td = "DD/MM/YYYY";

                if (j[i][2] != null && j[i][2] != "") {
                    fd = ParseDateColumncommission(j[i][2]);
                    // td=ParseDateColumncommission(j[i][3]);
                }
                 if (j[i][3] != null && j[i][3] != "") {
                    td = ParseDateColumncommission(j[i][3]);
                }
				  if (j[i][0]==1) {
					 td="";
					 }
					
                $("table#e_madtable").append('<tr id="tr_eShape' + x + '">'
                    + '<td style="width: 2%;">' + x + '</td>'
                    + '<td>'
                    + '<select name="e_status' + x + '" id="e_status' + x + '" '
                    + '	class="form-control-sm form-control"  onchange="statusChange(5,' + x + ',this.options[this.selectedIndex].value)" disabled>'
                    + '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
                    + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
                    + '	</c:forEach>'
                    + '		</select>'
                    + '  </td>'
                    + '   <td style="">'
                    + '<select name="eShape_value' + x + '" id="eShape_value' + x + '" '
                    + '		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')" disabled>'
                    + '		</select>	</td>'

                    + '<td style=""> '
                    + ' <input type="text" name="e_from_date' + x + '" id="e_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '" disabled>'
                    + '</td>'
                    + '<td style=""> '
                    + ' <input type="text" name="e_to_date' + x + '" id="e_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
                    + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
                    + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '" disabled>'
                    + '</td>'
					+  '<td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
                    + ' <input type="text" name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
                    +'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" disabled>	'
					
					+   ' </td>'
                    + ' <td style="display:none;"><input type="text" id="eShape_ch_id' + x + '" name="eShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" disabled></td>'

                    + '</tr>');

                datepicketDate('e_from_date' + x);
                datepicketDate('e_to_date' + x);
                $("#e_to_date" + x).datepicker("option", "maxDate", null);


                $("#e_status" + x).val(j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                statusChange(5, x, j[i][0]);
                $.ajaxSetup({
                    async: false
                });

                $("#eShape_value" + x).val(j[i][1]);
				
							var thisT = document.getElementById('eShape_value' + x);
							onShapeValueChange(thisT, 'e');

            }
            eShape = v;
            $("input#eShape_count").val(v);
            $("input#eShapeOld_count").val(v);
            $("#eShape_add" + v).show();
			setDiagnosis();
        }

    });
}



function getcCopeDetails(){
	
	var p_id=$('#jco_id').val();
	 var Shape='C_C';
	 var i=0;
	 var app_status= '${status6}';
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status: app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_cmadtable').show(); 
				$('#c_cmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					cCope=x;
					$("table#c_cmadtable").append('<tr id="tr_cCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	
						 	+'<select name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');" disabled>'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<input type="text" name="c_cother'+x+'" id="c_cother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" disabled>	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" disabled></td>'   
						
						 	+'</tr>');
					
						$("#c_cvalue"+x).val(j[i][1]); 
						var thisT = document.getElementById('c_cvalue'+x);
					 	onCCopeChange(thisT,x);
						if(x==1){onCopeChangebt(thisT,1,x);}
						
						if(x>1){
						 	$("#c_cvalue"+x+" option[value='0']").remove();
						 	$("#c_cvalue"+x+" option[value='1']").remove();
							$("#c_cvalue"+(x-1)+" option[value='0']").remove();
							$("#c_cvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
			cCope=v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			$("#cCope_add"+v).show(); 														
			}
			
	  });
}


function getoCopeDetails(){
	
	var p_id=$('#jco_id').val();
	 var Shape='C_O';
	 var i=0;
	 var app_status= '${status6}';
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status: app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_omadtable').show(); 
				$('#c_omadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					oCope=x;
					$("table#c_omadtable").append('<tr id="tr_oCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');" disabled>'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" disabled></td>'   
					
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_ovalue"+x).val(j[i][1]); 
						
						var thisT = document.getElementById('c_ovalue'+x)
						if(x==1){onCopeChangebt(thisT,2,x);}
						
						if(x>1){
						$("#c_ovalue"+x+" option[value='0']").remove();
						$("#c_ovalue"+(x-1)+" option[value='0']").remove();
						}
		
		}
			oCope=v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			$("#oCope_add"+v).show(); 														
			}
			
	  });
}

function getpCopeDetails(){
	
	var p_id=$('#jco_id').val();
	 var Shape='C_P';
	 var i=0;
	 var app_status= '${status6}';
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status: app_status }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			
			$('#c_pmadtable').show(); 
				$('#c_pmadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					pCope=x;
					$("table#c_pmadtable").append('<tr id="tr_pCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');" disabled>'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" disabled></td>'   
					
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_pvalue"+x).val(j[i][1]); 
						
						var thisT = document.getElementById('c_pvalue'+x)
						if(x==1){onCopeChangebt(thisT,3,x);}
						
						if(x>1){
						$("#c_pvalue"+x+" option[value='0']").remove();
						$("#c_pvalue"+x+" option[value='1']").remove();
						$("#c_pvalue"+(x-1)+" option[value='0']").remove();
						$("#c_pvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
			pCope=v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			$("#pCope_add"+v).show(); 														
			}
			
	  });
}

function geteCopeDetails(){

	var p_id=$('#jco_id').val();
	 var Shape='C_E';
	 var i=0;
	 var app_status= '${status6}';
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status: app_status }, function(j){
			var v=j.length;
		
		if(v!=0){	
			
			$('#c_emadtable').show(); 
				$('#c_emadtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;												
					eCope=x;
					$("table#c_emadtable").append('<tr id="tr_eCope'+x+'">'
						 	+'<td  style="width: 2%;">'+x+'</td>'
						 	+'<td>'
						 	+'<select name="c_evalue'+x+'" id="c_evalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');" disabled>'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'<td style="display:none;" class="eCopClass'+x+'">'
						 	+'<select name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')" disabled'
						 	+'			class="form-control-sm form-control" >'
						 	+'			<option value="0">--Select--</option>'																
						 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
						 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'			</c:forEach></select>   </td>'
						 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
						 	+'	 <input type="text" name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" disabled >'						                              
						 	+'	   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" disabled ></td>'   
					
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_evalue"+x).val(j[i][1]); 
						$("#c_esubvalue"+x).val(j[i][9]);
						var thisT = document.getElementById('c_evalue'+x)
						onECopeChange(thisT,x);
						if(x==1){onCopeChangebt(thisT,4,x);}
						
						if(x>1){
						$("#c_evalue"+x+" option[value='0']").remove();
						$("#c_evalue"+(x-1)+" option[value='0']").remove();
						}
				
		}
			eCope=v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			$("#eCope_add"+v).show(); 														
			}
			
	  });
}

//************************************* END MEDICAL *****************************//

 </script> 

