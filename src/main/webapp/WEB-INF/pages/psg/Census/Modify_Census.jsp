<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@page autoFlush="true"%> --%>

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
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<style>
.selectBox {
	position: relative;
}

element.style {
	width: 70%;
}

span.subspan {
	padding: 5px;
	background-color: #79cece54;
	border-radius: 20px;
	margin: 3px;
	display: block;
	width: fit-content;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
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
<style>
/* Popup container - can be anything you want */
.popup {
	position: relative;
	display: inline-block;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* The actual popup */
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

/* Popup arrow */
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

/* Toggle this class - hide and show the popup */
.popup .show {
	visibility: visible;
	-webkit-animation: fadeIn 1s;
	animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
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
label {
	word-break: break-word;
}

.tab {
	display: inline-flex;
}

.tablinks {
	outline: 0 !important;
	background-color: #cccccc;
	color: black;
	font-size: 14px;
	padding: 10px 5px;
	/*     border-radius: 5px;  */
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
	<form id="Personnel_no_form">
		<div class="animated fadeIn">
			<div class="container-fluid" align="center">
				<div class="card">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default" id="insp_div1">
							<div class="panel-heading">

								<div class="card-header">
									<h5> FILL CENSUS DETAILS OF OFFR</h5>
									<h6 class="enter_by">
										<span style="font-size: 12px; color: red;">(To be
											entered by the first Unit of Commissioned Offr)</span>
									</h6>
								</div>
								<div class="col-md-12"
									style="padding-top: 20px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no"
													onkeydown="return AvoidSpace(event)"
													onkeypress="return onlyAlphaNumeric(event);" maxlength="9"
													min="7" class="form-control autocomplete"
													autocomplete="off">

											</div>
										</div>
									</div>

									<div class="col-md-6">
										<input type="button" class="btn btn-success btn-sm" id="btn1"
											value="Proceed" onclick="return personal_number();">
									</div>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" id="insp_count" name="count" value='1'>
					<input type="hidden" id="c_i" name="c_i" value=''> <input
						type="hidden" id="insert_count" name="insert_count" value="0">
					<input type="hidden" id="lastcount" name="lastcount"
						value='${inspDtls.size()}'>
				</div>
			</div>
		</div>
	</form>

	<div id="personal_detail_div" class="tabcontent" style="display: none;">

		<div class="animated fadeIn">
			<div class="container-fluid" align="center">
				<div class="card">
					<div class="card-header">
						<h5>Personal Details</h5>
						<h6 class="enter_by">
							<span style="font-size: 12px; color: red;">(* Fields are
								mandatory)</span>
						</h6>
					</div>
					<div class="card-body card-block">
						<form id="personalDetailsForm" class="form-horizontal">
							<div class="col-md-12">
								<div class="col-md-2">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Name</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="cadet_name"></label>
										</div>
									</div>
								</div>

								<div class="col-md-2">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Cadet No</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="cadet_no"></label>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Course </label>
										</div>
										<div class="col-md-8">

											<label class=" form-control-label" id="course_name"></label>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Course No</label>
										</div>
										<div class="col-md-8">
											<input type="hidden" id="census_id" name="census_id"
												value="0" class="form-control autocomplete"
												autocomplete="off"> <input type="hidden"
												id="comm_id" name="comm_id"
												class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="med_id" name="med_id" value="0"
												class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="i_id" name="i_id" value="0"
												class="form-control autocomplete" autocomplete="off">
											<label class=" form-control-label" id="course"></label>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Rank </label>
										</div>
										<div class="col-md-8">

											<label class=" form-control-label" id="rank"></label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> First Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="first_name" name="first_name"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);"
												style="left: 80px; width: 80%;"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong> Middle Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="middle_name" name="middle_name"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()"
												style="width: 80%;">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong> Last Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="last_name" name="last_name"
												class="form-control autocomplete" autocomplete="off"
												style="width: 80%;" maxlength="50"
												onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Gender</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="gender"></label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Date of Birth</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="dob"></label> <input
												type="hidden" class=" form-control-label" id="dob_date"
												style="display: none">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY of Birth</label>
										</div>
										<div class="col-md-8">
											<select name="country_birth" id="country_birth"
												onchange="fn_country_birth();" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> STATE of Birth</label>
										</div>
										<div class="col-md-8">
											<select name="state_birth" id="state_birth"
												onchange="fn_state_birth();" class="form-control">
												<option value="0">--Select--</option>
												<%-- 	<c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6" id="country_birth_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">COUNTRY Of Birth
												Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="country_birth_other"
												name="country_birth_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" maxlength="50"
												autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="state_birth_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">STATE Of Birth
												Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="state_birth_other"
												name="state_birth_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" maxlength="50"
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
												style="color: red;">* </strong> DISTRICT of Birth</label>
										</div>
										<div class="col-md-8">
											<select name="district_birth" id="district_birth"
												onchange="fn_district_birth();" class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedDistrictName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Place of Birth</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="place_birth" name="place_birth"
												onkeypress="return onlyAlphabets(event);" maxlength="50"
												class="form-control autocomplete" autocomplete="off"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-12">
								<div class="col-md-6" id="district_birth_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">DISTRICT of Birth
												Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="district_birth_other"
												name="district_birth_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" maxlength="50"
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
												style="color: red;">* </strong> Nationality</label>
										</div>
										<div class="col-md-8">
											<select name="nationality" id="nationality"
												class="form-control" onchange="fn_nationality();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNationalityList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Mother Tongue</label>
										</div>
										<div class="col-md-8">
											<select name="mother_tongue" id="mother_tongue"
												class="form-control" onchange="fn_mother_tongue();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMothertoungeList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" id="nationality_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Nationality Other
											</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="nationality_other"
												name="nationality_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_tongue_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Mother Tongue
												Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_tongue_other"
												name="mother_tongue_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Religion</label>
										</div>
										<div class="col-md-8">
											<select name="religion" id="religion" class="form-control"
												onchange="fn_religion();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getReligionList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Type of Entry</label>
										</div>
										<div class="col-md-8">
											<select name="ncc_type" id="ncc_type" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNCCTypeList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-6" id="religion_other_hid"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Religion Other </label>
										</div>
										<div class="col-md-8">
											<input type="text" id="religion_other" name="religion_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">
													<!-- * -->
											</strong> Aadhaar No</label>
										</div>



										<div class="col-md-2">
											<input type="text" id="adhar_number1" name="adhar_number1"
												class="form-control autocomplete" autocomplete="off"
												maxlength="4" onkeydown="return AvoidSpace(event)"
												onkeypress="return isNumber(event)"
												onkeyup="movetoNext(this, 'adhar_number2'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number2" name="adhar_number2"
												class="form-control autocomplete" autocomplete="off"
												maxlength="4" onkeydown="return AvoidSpace(event)"
												onkeypress="return isNumber(event)"
												onkeyup="movetoNext(this, 'adhar_number3'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number3" name="adhar_number3"
												class="form-control autocomplete" autocomplete="off"
												onkeydown="return AvoidSpace(event)"
												onkeypress="return isNumber(event)" maxlength="4"
												onkeyup="lengthadhar()">
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">
													<!-- * -->
											</strong> Pan No</label>
										</div>
										<div class="col-md-8">

											<input type="text" id="pan_no" name="pan_no"
												class="form-control autocomplete" autocomplete="off"
												onchange=" isPAN(this); "
												onkeyup="return specialcharecter(this)"
												onkeydown="return AvoidSpace(event)"
												oninput="this.value = this.value.toUpperCase()"
												maxlength="10">

										</div>
									</div>
								</div>
							</div>


							<div class="col-md-12">


								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Commissioning Institute</label>
										</div>
										<div class="col-md-8">
											<select name="com_institute" id="com_institute"
												class="form-control" onchange="getInst_btn(this);">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCommInstitute}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>

									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Training
												Institute</label>
										</div>
										<div class="col-md-8">
											<select name="training_institute" id="training_institute"
												class="form-control" onchange="getInst_trainngBtn(this);">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTrainingInstitute}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>

									</div>
								</div>

							</div>

							<div class="col-md-12">


								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Comm Bn</label>
										</div>
										<div class="col-md-8">
											<select name="com_bn_id" id="com_bn_id"
												class="form-control autocomplete"
												onchange="getInst_company(this);">
												<option value="0">--Select--</option>
												<%-- 												<c:forEach var="item" items="${getBattalionList}" --%>
												<%-- 													varStatus="num"> --%>
												<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
												<%-- 												</c:forEach> --%>
											</select>

										</div>

									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Comm Coy</label>
										</div>
										<div class="col-md-8">
											<select name="com_company_id" id="com_company_id"
												class="form-control">
												<option value="0">--Select--</option>
												<%-- 												<c:forEach var="item" items="${getCompanyList}" --%>
												<%-- 													varStatus="num"> --%>
												<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
												<%-- 												</c:forEach> --%>
											</select>

										</div>
									</div>
								</div>


								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Trg Bn</label>
										</div>
										<div class="col-md-8">
											<select name="training_bn_id" id="training_bn_id"
												class="form-control autocomplete"
												onchange="getInst_trainngCompany(this);">
												<option value="0">--Select--</option>
												<%-- 												<c:forEach var="item" items="${getBattalionList}" --%>
												<%-- 													varStatus="num"> --%>
												<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
												<%-- 												</c:forEach> --%>
											</select>

										</div>

									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Trg Coy</label>
										</div>
										<div class="col-md-8">
											<select name="training_company_id" id="training_company_id"
												class="form-control">
												<option value="0">--Select--</option>
												<%-- 												<c:forEach var="item" items="${getCompanyList}" --%>
												<%-- 													varStatus="num"> --%>
												<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
												<%-- 												</c:forEach> --%>
											</select>

										</div>
									</div>
								</div>
							</div>


							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Marital Status</label>
										</div>
										<div class="col-md-8">
											<select name="marital_status" id="marital_status"
												class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_statusList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Blood Group</label>
										</div>
										<div class="col-md-8">
											<select name="blood_group" id="blood_group"
												class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getBloodList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Height (cm)</label>
										</div>
										<div class="col-md-8">
											<!-- <input type="text" id="height" name="height" class="form-control autocomplete" autocomplete="off" maxlength="3"> 						                     -->
											<select name="height" id="height" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getHeight}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>
							</div>

							<div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Father's Name</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="father_name" name="father_name"
													class="form-control autocomplete" autocomplete="off"
													maxlength="50" onkeypress="return onlyAlphabets(event);"
													oninput="this.value = this.value.toUpperCase()">

											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Mother's Name</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="mother_name" name="mother_name"
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return onlyAlphabets(event);" maxlength="50"
													oninput="this.value = this.value.toUpperCase()">


											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12" style="margin-top: 10px; width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4>Any
											Known Allergy</h4></label> &nbsp<input type="radio" id="allergic_radio1"
										name="allergic_radio" value="yes"
										onchange="allergic_radioFn();">&nbsp <label for="yes">Yes</label>&nbsp
									<input type="radio" id="allergic_radio2" name="allergic_radio"
										value="no" onchange="allergic_radioFn();" checked="checked">&nbsp
									<label for="no">No</label><br>

								</div>
								<br>

								<div id="allergicToMedicineDiv" style="display: none;">



									<table id="allergic_table" class="table-bordered"
										style="margin-top: 10px; width: 100%;">

										<thead style="color: white; text-align: center;">
											<tr>
												<th style="width: 10%;">Sr No</th>
												<th>Allergy</th>
												<th class="hide-action">Action</th>

											</tr>
										</thead>
										<tbody data-spy="scroll" id="allergictbody"
											style="min-height: 46px; max-height: 650px; text-align: center;">
											<tr id="tr_allergic1">
												<td style="width: 2%;">1</td>
												<td><input type="text" id="medicine1" name="medicine1"
													maxlength="100" class="form-control autocomplete"
													autocomplete="off"></td>

												<td style="display: none;"><input type="text"
													id="allergic_ch_id1" name="allergic_ch_id1" value="0"
													maxlength="100" class="form-control autocomplete"
													autocomplete="off"></td>
												<td class="hide-action" align="center" style="width: 10%;"><a
													class='btn btn-success btn-sm' value='ADD' title='ADD'
													id='allergic_id_add1' onclick='allergicformopen(1);'><i
														class='fa fa-plus'></i></a></td>

											</tr>
										</tbody>
									</table>



								</div>
								<input type="hidden" id="allergic_count" name="allergic_count"
									class="required form-control" autocomplete="off" value="1" />
								<input type="hidden" id="allergicOld_count"
									name="allergicOld_count" class="form-control" maxlength="2"
									autocomplete="off" value="0">

							</div>




							<div class="col-md-12">
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Original Domicile of</h4></label>
							</div>


							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="org_domicile_Country" id="org_domicile_Country"
												onchange="fn_org_domicile_Country();" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> STATE</label>
										</div>
										<div class="col-md-8">
											<input type="hidden" id="addr_ch_id" name="addr_ch_id"
												value="0" class="form-control autocomplete"
												autocomplete="off"> <select
												name="org_domicile_state" id="org_domicile_state"
												onchange="fn_org_domicile_state();" class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</SELECT>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6" id="org_domicile_country_other_id"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> COUNTRY OTHER</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="org_domicile_Country_other"
												name="org_domicile_Country_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="org_domicile_state_other_id"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> STATE OTHER</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="org_domicile_state_other"
												name="org_domicile_state_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-contro
											
											l-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="org_domicile_district"
												id="org_domicile_district"
												onchange="fn_org_domicile_district();" class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedDistrictName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row foInteger.parseInt(rm-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> TEHSIL</label>
										</div>
										<div class="col-md-8">
											<select name="org_domicile_tehsil" id="org_domicile_tehsil"
												onchange="fn_org_domicile_tehsil();" class="form-control">
												<option value="0">--Select--</option>

											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6" id="org_domicile_district_other_id"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> DISTRICT OTHER</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="org_domicile_district_other"
												name="org_domicile_district_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="org_domicile_tehsil_other_id"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> TEHSIL OTHER</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="org_domicile_tehsil_other"
												name="org_domicile_tehsil_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12" style="margin-top: 10px; width: 100%;">
								<label class=" form-control-label" style="margin-right: 100px;"><h4> Pre-Cadet
										Details</h4></label> <br>

							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Pre-Cadet Status</label>
										</div>
										<div class="col-md-8">
											<select name="cadet_status1" id="cadet_status1"
												class=" form-control" onchange="typeSelection()">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getPreCadetStatusList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-12">
								<br>
							</div>

							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Designation</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="designation1" name="designation1"
												maxlength="50" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Name of Employer</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="emp_name1" name="emp_name1"
												maxlength="100" class="form-control autocomplete"
												autocomplete="off" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>




							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Competency</label>
										</div>
										<div class="col-md-8">
											<select name="competency" id="competency"
												class="form-control"
												onchange="fn_otherShowHide(this,'divcompetency_other','competency_other')">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSpecializationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="divcompetency_other"
									style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Competency Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="competency_other"
												name="competency_other"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>If Gazetted/Non Gazetted</label>
										</div>
										<div class="col-md-8">
											<input type="radio" id="isyes1" name="isgazetted1"
												value="gazetted"> <label for="Gazetted">Gazetted</label>
											<input type="radio" id="isno1" name="isgazetted1"
												value="non_gazetted"> <label for="No">Non
												Gazetted</label>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>If Pensionable Civil Service</label>
										</div>
										<div class="col-md-8">
											<input type="radio" id="iscivilyes1" name="isCivil1"
												value="yes"> <label for="Yes">Yes</label> <input
												type="radio" id="iscivilno1" name="isCivil1" value="no">
											<label for="No">No</label>
										</div>
									</div>
								</div>


							</div>
							<div class="col-md-12">
								<br>
							</div>
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Service No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="army_no1" name="army_no1"
												maxlength="10" class="form-control autocomplete"
												autocomplete="off" onkeydown="return AvoidSpace(event)"
												onkeypress="return onlyAlphaNumeric(event);">
										</div>
									</div>
								</div>


								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Unit/Regiment</label>
										</div>
										<div class="col-md-8">
											<input type="hidden" id="sus_no" name="sus_no"
												class="form-control autocomplete" autocomplete="off">
											<input type="text" id="unit_reg1" name="unit_reg1"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);" maxlength="100"
												oninput="this.value = this.value.toUpperCase()">

										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date From</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="date_form1" id="date_form1"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');calcDate();validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">



										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Date To</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="date_to1" id="date_to1"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');calcDate();validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> <!-- <strong
										style="color: red;">* </strong> -->Total Service in Ranks
											</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="total_rank1" name="total_rank1"
												maxlength="50" class="form-control autocomplete"
												autocomplete="off" readonly="readonly"> <input
												type="hidden" id="total_rank" name="total_rank" value="0"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>


							</div>


							<input type="hidden" id="pre_ch_id1" name="pre_ch_id1" value="0"
								class="form-control autocomplete" autocomplete="off">


							<div class="col-md-12"></div>
							<br> <br>

							<div id="NCC_check">
								<div class="col-md-12" style="margin-top: 10px; width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4>NCC
											Experience</h4></label> <br>

								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Whether in OTU Div/Jr Div/Sr
													Div</label>
											</div>
											<div class="col-md-8">
												<input type="radio" id="otu_div1" name="otu1"
													value="otu_div"> <label for="Yes">OTU Div</label> <input
													type="radio" id="jr_div1" name="otu1" value="jr_div">
												<label for="No">Jr Div</label> <input type="radio"
													id="sr_div1" name="otu1" value="sr_div"> <label
													for="No">Sr Div</label> <input type="radio" id="nill"
													name="otu1" value="nil" checked="checked"> <label
													for="nil">NIL</label>
											</div>
										</div>
										<input type="hidden" id="ncc_ch_id1" name="ncc_ch_id1"
											value="0" class="form-control autocomplete"
											autocomplete="off">
									</div>
								</div>

							</div>


							<div class="card">
								<div class="card-header" style="text-align: center;">
									<h5>Medical Details</h5>
									<h6 class="enter_by">
										<span style="font-size: 12px; color: red;"></span>
									</h6>
								</div>
								<div class="card-body card-block">
									<br>
									<div class="row">
										<div id="divhideformed"
											style="width: -webkit-fill-available; text-align: -webkit-center; display:block">
											<strong style="font-size: x-large; text-align: center;">For
												Any Modification pls Click Here </strong> <a
												class="btn btn-danger btn-sm" value="DELETE"
												title="Reset All Medical Details " id="cCope_remove6"
												onclick="AllCoperemove_fn();"><i
												style="font-size: x-large;" class="fa fa-trash"></i></a>
										</div>
										</br> </br> </br> <input type="hidden" id="mad_classification_count"
											name="mad_classification_count" value="NIL">
										<div class="col-md-12" id="divshape1bx" style="display:block">
											<div class="row form-group">
												<div class="col-md-12"
													style="font-size: 15px; margin: 10px;">
													<input type="checkbox" id="check_1bx" name="check_1bx"
														onclick="oncheck_1bx()" value="1BX"> <label
														for="text-input" class=" form-control-label"
														style="margin-left: 10px;"> <strong>
															SHAPE 1BX </strong>
													</label>
												</div>
											</div>
											<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"
												value="0" class="form-control autocomplete"
												autocomplete="off">
												
													<input type="hidden" id="medDtlFillIn3008" name="medDtlFillIn3008"
												value="0" class="form-control autocomplete"
												autocomplete="off">
										</div>
										<div class="col-md-12" id="shape1bxdiv" style="display: none;">

											<div class="col-md-4">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>From Date</label>
													</div>
													<div class="col-md-8">
														<%-- 						<input type="date" id="1bx_from_date" name="1bx_from_date" max="${date_without_time}" class="form-control autocomplete" autocomplete="off"> --%>

														<input type="text" name="1bx_from_date" id="1bx_from_date"
															maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
															class="form-control" style="width: 85%; display: inline;"
															onfocus="this.style.color='#000000'"
															onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
															onkeyup="clickclear(this, 'DD/MM/YYYY')"
															onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
															aria-required="true" autocomplete="off"
															style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														<!-- 							<input type="date" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off"> -->
														<input type="text" name="1bx_to_date" id="1bx_to_date"
															maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
															class="form-control" style="width: 85%; display: inline;"
															onfocus="this.style.color='#000000'"
															onblur="clickrecall(this,'DD/MM/YYYY');"
															onkeyup="clickclear(this, 'DD/MM/YYYY')"
															onchange="clickrecall(this,'DD/MM/YYYY');"
															aria-required="true" autocomplete="off"
															style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
															maxlength="255" id="1bx_diagnosis_code"
															class="form-control" autocomplete="off"
															placeholder="Search..."
															onkeypress="AutoCompleteDiagnosis(this);">
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
															<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
															<th style="display: none;" class="addbtClass1">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="s_madtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_sShape1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="s_status1" id="s_status1"
																class="form-control "
																onchange="statusChange(1,1,this.options[this.selectedIndex].value)">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getShapeStatusList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style=""><select name="sShape_value1"
																id="sShape_value1" class="form-control "
																onchange="onShapeValueChange(this,'s')">
																	<option value="0">--Select--</option>
															</select></td>

															<td style=""><input type="text" name="s_from_date1"
																id="s_from_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

															<td style=""><input type="text" name="s_to_date1"
																id="s_to_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
															<td style="display: none;" class="diagnosisClass11"><input
																type="text" name="_diagnosis_code11"
																id="_diagnosis_code11" class="form-control"
																autocomplete="off" placeholder="Search..."
																maxlength="255"
																onkeypress="AutoCompleteDiagnosis(this);"></td>
															<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
															<!-- 														 <input type="text" name="s_diagnosis_code21" id="s_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
															<!-- 														 <input type="text" name="s_diagnosis_code31" id="s_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
															<!-- 														 <input type="text" name="s_diagnosis_code41" id="s_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


															<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
															<!-- 														 <input type="text" name="s_diagnosis_code51" id="s_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
															<!-- 														 <input type="text" name="s_diagnosis_code61" id="s_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->

															<td style="display: none;"><input type="text"
																id="sShape_ch_id1" name="sShape_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 10%; display: none;"
																class="addbtClass11"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="sShape_add1" onclick="sShape_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
															<th style="display: none;" class="addbtClass2">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="h_madtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_hShape1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="h_status1" id="h_status1"
																class="form-control"
																onchange="statusChange(2,1,this.options[this.selectedIndex].value)">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getShapeStatusList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style=""><select name="hShape_value1"
																id="hShape_value1" class="form-control"
																onchange="onShapeValueChange(this,'h')">
																	<option value="0">--Select--</option>
															</select></td>

															<td style=""><input type="text" name="h_from_date1"
																id="h_from_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

															<td style=""><input type="text" name="h_to_date1"
																id="h_to_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
															<td style="display: none;" class="diagnosisClass21"><input
																type="text" name="_diagnosis_code21"
																id="_diagnosis_code21" class="form-control"
																autocomplete="off" placeholder="Search..."
																maxlength="255"
																onkeypress="AutoCompleteDiagnosis(this);"></td>
															<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
															<!-- 														 <input type="text" name="h_diagnosis_code21" id="h_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
															<!-- 														 <input type="text" name="h_diagnosis_code31" id="h_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
															<!-- 														 <input type="text" name="h_diagnosis_code41" id="h_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


															<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
															<!-- 														 <input type="text" name="h_diagnosis_code51" id="h_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
															<!-- 														 <input type="text" name="h_diagnosis_code61" id="h_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->

															<td style="display: none;"><input type="text"
																id="hShape_ch_id1" name="hShape_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 10%; display: none;"
																class="addbtClass21"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="hShape_add1" onclick="hShape_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
															<th style="display: none;" class="addbtClass3">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="a_madtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_aShape1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="a_status1" id="a_status1"
																class="form-control"
																onchange="statusChange(3,1,this.options[this.selectedIndex].value)">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getShapeStatusList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style=""><select name="aShape_value1"
																id="aShape_value1" class="form-control"
																onchange="onShapeValueChange(this,'a')">
																	<option value="0">--Select--</option>
															</select></td>

															<td style=""><input type="text" name="a_from_date1"
																id="a_from_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

															<td style=""><input type="text" name="a_to_date1"
																id="a_to_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
															<td style="display: none;" class="diagnosisClass31"><input
																type="text" name="_diagnosis_code31"
																id="_diagnosis_code31" class="form-control"
																autocomplete="off" placeholder="Search..."
																maxlength="255"
																onkeypress="AutoCompleteDiagnosis(this);"></td>
															<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
															<!-- 														 <input type="text" name="a_diagnosis_code21" id="a_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
															<!-- 														 <input type="text" name="a_diagnosis_code31" id="a_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
															<!-- 														 <input type="text" name="a_diagnosis_code41" id="a_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


															<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
															<!-- 														 <input type="text" name="a_diagnosis_code51" id="a_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
															<!-- 														 <input type="text" name="a_diagnosis_code61" id="a_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->

															<td style="display: none;"><input type="text"
																id="aShape_ch_id1" name="aShape_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 10%; display: none;"
																class="addbtClass31"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="aShape_add1" onclick="aShape_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
															<th style="display: none;" class="addbtClass4">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="p_madtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_eShape1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="p_status1" id="p_status1"
																class="form-control"
																onchange="statusChange(4,1,this.options[this.selectedIndex].value)">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getShapeStatusList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style=""><select name="pShape_value1"
																id="pShape_value1" class="form-control"
																onchange="onShapeValueChange(this,'p')">
																	<option value="0">--Select--</option>
															</select></td>

															<td style=""><input type="text" name="p_from_date1"
																id="p_from_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

															<td style=""><input type="text" name="p_to_date1"
																id="p_to_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
															<td style="display: none;" class="diagnosisClass41"><input
																type="text" name="_diagnosis_code41"
																id="_diagnosis_code41" class="form-control"
																autocomplete="off" placeholder="Search..."
																maxlength="255"
																onkeypress="AutoCompleteDiagnosis(this);"></td>
															<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
															<!-- 														 <input type="text" name="p_diagnosis_code21" id="p_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
															<!-- 														 <input type="text" name="p_diagnosis_code31" id="p_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
															<!-- 														 <input type="text" name="p_diagnosis_code41" id="p_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


															<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
															<!-- 														 <input type="text" name="p_diagnosis_code51" id="p_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
															<!-- 														 <input type="text" name="p_diagnosis_code61" id="p_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->

															<td style="display: none;"><input type="text"
																id="pShape_ch_id1" name="pShape_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 10%; display: none;"
																class="addbtClass41"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="pShape_add1" onclick="pShape_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<!-- 													<th style=" display: none" class="diagnosisClass2">Diagnosis-2</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass3">Diagnosis-3</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass4">Diagnosis-4</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass5">Diagnosis-5</th> -->
															<!-- 													<th style=" display: none" class="diagnosisClass6">Diagnosis-6</th>										 -->
															<th style="display: none;" class="addbtClass5">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="e_madtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_eShape1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="e_status1" id="e_status1"
																class="form-control"
																onchange="statusChange(5,1,this.options[this.selectedIndex].value)">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getShapeStatusList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style=""><select name="eShape_value1"
																id="eShape_value1" class="form-control"
																onchange="onShapeValueChange(this,'e')">
																	<option value="0">--Select--</option>
															</select></td>

															<td style=""><input type="text" name="e_from_date1"
																id="e_from_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

															<td style=""><input type="text" name="e_to_date1"
																id="e_to_date1" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
															<td style="display: none;" class="diagnosisClass51"><input
																type="text" name="_diagnosis_code51"
																id="_diagnosis_code51" class="form-control"
																autocomplete="off" placeholder="Search..."
																maxlength="255"
																onkeypress="AutoCompleteDiagnosis(this);"></td>
															<!-- 														    <td style=" display: none" class="diagnosisClass2"> -->
															<!-- 														 <input type="text" name="e_diagnosis_code21" id="e_diagnosis_code21" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass3"> -->
															<!-- 														 <input type="text" name="e_diagnosis_code31" id="e_diagnosis_code31" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass4"> -->
															<!-- 														 <input type="text" name="e_diagnosis_code41" id="e_diagnosis_code41" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


															<!-- 														    <td style=" display: none" class="diagnosisClass5"> -->
															<!-- 														 <input type="text" name="e_diagnosis_code51" id="e_diagnosis_code51" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->
															<!-- 														    <td style=" display: none" class="diagnosisClass6"> -->
															<!-- 														 <input type="text" name="e_diagnosis_code61" id="e_diagnosis_code61" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->

															<td style="display: none;"><input type="text"
																id="eShape_ch_id1" name="eShape_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 10%; display: none;"
																class="addbtClass51"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="eShape_add1" onclick="eShape_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<th style="width: 70%;">Value & Description</th>
															<th style="width: 14%; display: none;" class="cCopClass">Other</th>
															<th style="width: 14%; display: none;"
																class="CopaddbtClass1">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="c_cmadtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_cCope1">
															<td style="width: 2%;">1</td>
															<td style="width: 70%;"><select name="c_cvalue1"
																id="c_cvalue1"
																onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
																class="form-control">
																	<c:forEach var="item" items="${getcCopeList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																	</c:forEach>
															</select></td>

															<td style="width: 14%; display: none;" class="cCopClass1"><input
																type="text" name="c_cother1" id="c_cother1"
																class="form-control" autocomplete="off"></td>

															<td style="display: none;"><input type="text"
																id="cCope_ch_id1" name="cCope_ch_id1" value="0"
																class="form-control autocomplete" autocomplete="off"></td>

															<td style="width: 14%; display: none;"
																class="CopaddbtClass11"><a
																class="btn btn-success btn-sm" value="ADD" title="ADD"
																id="cCope_add1" onclick="cCope_add_fn(1);"><i
																	class="fa fa-plus"></i></a></td>
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
															<th style="width: 10%; display: none;"
																class="CopaddbtClass2">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="c_omadtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_oCope1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="c_ovalue1" id="c_ovalue1"
																onchange="onCopeChangebt(this,2,1);"
																class="form-control">
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
															<th style="width: 10%; display: none;"
																class="CopaddbtClass3">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="c_pmadtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_pCope1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="c_pvalue1" id="c_pvalue1"
																onchange="onCopeChangebt(this,3,1);"
																class="form-control">
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
															<th style="width: 10%; display: none;"
																class="eCop_otherClass">Other</th>
															<th style="width: 10%; display: none;"
																class="CopaddbtClass4">Action</th>
														</tr>
													</thead>
													<tbody data-spy="scroll" id="c_emadtbody"
														style="min-height: 46px; max-height: 650px; text-align: center;">
														<tr id="tr_eCope1">
															<td style="width: 2%;">1</td>
															<td style=""><select name="c_evalue1" id="c_evalue1"
																onchange="onECopeChange(this,1); onCopeChangebt(this,4,1);"
																class="form-control">
																	<c:forEach var="item" items="${geteCopeList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style="display: none;" class="eCopClass1"><select
																name="c_esubvalue1" id="c_esubvalue1"
																onchange="onECopeSubChange(this,1)" class="form-control">
																	<option value="0">--Select--</option>
																	<c:forEach var="item" items="${getesubCopeList}"
																		varStatus="num">
																		<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																	</c:forEach>
															</select></td>
															<td style="display: none;" class="eCop_otherClass1"><input
																type="text" name="c_esubvalueother1"
																id="c_esubvalueother1" maxlength="50"
																class="form-control" autocomplete="off"></td>

															<!-- 						                                <td style="" > -->
															<!-- 														 <input type="text" name="c_edescription1" id="c_edescription1" class="form-control-sm form-control" autocomplete="off"  -->
															<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
															<!-- 														   </td> -->


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
							</div>



							<input type="hidden" id="sShape_count" name="sShape_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="sShapeOld_count" name="sShapeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

							<input type="hidden" id="hShape_count" name="hShape_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="hShapeOld_count" name="hShapeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

							<input type="hidden" id="aShape_count" name="aShape_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="aShapeOld_count" name="aShapeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

							<input type="hidden" id="pShape_count" name="pShape_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="pShapeOld_count" name="pShapeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

							<input type="hidden" id="eShape_count" name="eShape_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="eShapeOld_count" name="eShapeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

							<input type="hidden" id="cCope_count" name="cCope_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="cCopeOld_count" name="cCopeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">
							<input type="hidden" id="oCope_count" name="oCope_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="oCopeOld_count" name="oCopeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">
							<input type="hidden" id="pCope_count" name="pCope_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="pCopeOld_count" name="pCopeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">
							<input type="hidden" id="eCope_count" name="eCope_count"
								class="required form-control" autocomplete="off" value="1" /> <input
								type="hidden" id="eCopeOld_count" name="eCopeOld_count"
								class="form-control" maxlength="2" autocomplete="off" value="0">

						</form>
					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="button" id="save_per" class="btn btn-primary btn-sm"
							value="Submit For Approval" onclick="return Validation();">
					</div>
				</div>
			</div>
		</div>

	</div>


	<!--  <div id="submit_data" class="card-footer" align="center"
	class="form-control" style="display: none;">
	<input type="button" class="btn btn-primary btn-sm" id="bt_sub"
		value="Submit For Approval" onclick="Submit_Approval();"><a
		href="search_censusUrl" id="censussearshUrllink" style="display: none;"
		class="btn btn-success btn-sm">Back</a> <button
		 id="censussearshUrl" style="display: none;" onclick="returnSearch()"
		class="btn btn-success btn-sm">Back</button> 
</div> -->
	<c:url value="GetSearch_Census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm"
		name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="" />
		<input type="hidden" name="rank1" id="rank1" />
		<input type="hidden" name="status1" id="status1" value="0" />
		<input type="hidden" name="unit_name1" id="unit_name1" />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />

	</form:form>



	<script>
var other='OTHERS';
function openCity(evt, cityName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for(i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(cityName).style.display = "block";
	try{
	evt.currentTarget.className += " active";
	}catch{
		
	}
	if(cityName == "Pre-Cadet") {
		getPreCreditDetails();
		getNCC();
	} else if(cityName == "addr_details") {
		get_address_details();
	} else if(cityName == "language_details") {
		get_language_details(); 
		 colAdj("language_table");
		colAdj("foregin_language_table");
	} else if(cityName == "Foreign_Countries") {
		getForeign_CountriesDetails();
		 colAdj("country_visit");
	} else if(cityName == "Qualifications") {
		getQualifications();
		 colAdj("qualificationtable");
	} else if(cityName == "family_details") {
		getmarital_status();
		getfamily_siblingDetails();
		getfamily_marriageDetails();
		getSpouseQualificationData();
		getfamily_divoreceDetails();
		 colAdj("family_table");
		 colAdj("married_table");
		 colAdj("divorce_table");
	} else if(cityName == "medical_details") {
		getsShapeDetails();
		gethShapeDetails();
		getaShapeDetails();
		getpShapeDetails();
		geteShapeDetails();
		getcCopeDetails();
		getoCopeDetails();
		getpCopeDetails();
		geteCopeDetails();
		 colAdj("s_madtable");
		 colAdj("h_madtable");
		 colAdj("a_madtable");
		 colAdj("p_madtable");
		 colAdj("e_madtable");
		 colAdj("c_cmadtable");
		 colAdj("c_omadtable");
		 colAdj("c_pmadtable");
		 colAdj("c_emadtable");
	}
	// $("input").prop("disabled",true);
	// $("select").prop("disabled",true);
	// $("input[type=button]").hide();
	// $(".hide-action").hide();
	addMaxLengthToInput(auth_length);
}
//*************************************MAIN Personel Number Onchange*****************************//
var comm_id = "";
var off_dob;
var comm_date;
var curr_marital_status="0";
function personal_number() {
	debugger
	if($("input#personnel_no").val().trim() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	
	var personnel_no = $("input#personnel_no").val();
	$.post('GetPersonnelNoData?' + key + "=" + value, {
		personnel_no: personnel_no
	}, function(j) {
		
		$("#course").text(j[0][2]);
		$("#cadet_no").text(j[0][1]);
		$("#comm_id").val(j[0][0]);		
		$("#cadet_name").text(j[0][5]);
		comm_id = j[0][0];	
	
		
		comm_date=ParseDateColumncommission(j[0][11])
	
		// 	    	 var d = new Date(j[0][4]);
		// 			 var date= d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear();
		off_dob = ParseDateColumncommission(j[0][4]);
		$("#dob").text(convertDateFormate(j[0][4]));
		$("#dob_date").val(off_dob);			
		setMinDate('date_form1', off_dob);
		setMinDate('date_to1', off_dob);
		setMaxDate('date_form1', comm_date);
		setMaxDate('date_to1', comm_date);
		
		setMinDate('from_dt1', off_dob);
		setMinDate('to_dt1', off_dob);
		$("#gender").text(j[0][3]);
		$("#course_name").text(j[0][7]);
		$("#rank").text(j[0][6]);
		var comm_id = j[0][0];
		
		
		$.post('chekmedicaldtlfillin3008?' + key + "=" + value, {				
			comm_id:comm_id
		}, function(count) {					
			var v = count;				
			if(v > 1) {	
				$('#medDtlFillIn3008').val(1);
				getpersonalnodata();
		} else{
			$('#medDtlFillIn3008').val(0);
			getpersonalnodata();
		}
		});
		
		
	});
	 
	$("input#personnel_no").attr('readonly', true);
}

function getpersonalnodata(){
	debugger
	var comm_id =$("#comm_id").val();		
	$.post('GetModifyCensusData?' + key + "=" + value, {
		comm_id: comm_id
	}, function(k) {
		debugger
		if(k.length > 0) {
			
			// 	    			 id,comm_id,first_name,middle_name,last_name,place_birth,district_birth,state_birth,country_birth,"
			// 						+ "nationality,religion,marital_status,blood_group,height,adhar_number,border_area,"
			// 						+ "father_name, father_dob,father_place_birth,father_profession,mother_name,"
			// 						+ "mother_dob,mother_place_birth,mother_profession,mother_tongue,ncc_type,institute 
			$.ajaxSetup({
				async: false
			});
			debugger
			$('#census_id').val(k[0].id);
			$('#first_name').val(k[0].first_name);
			$('#middle_name').val(k[0].middle_name);
			$('#last_name').val(k[0].last_name);
			$('#place_birth').val(k[0].place_birth);
			$('#country_birth').val(k[0].country_birth);
			fn_country_birth();
			$('#country_birth_other').val(k[0].country_birth_other);
			$('#state_birth').val(k[0].state_birth);
			fn_state_birth();
			$('#state_birth_other').val(k[0].state_birth_other);
			$('#district_birth').val(k[0].district_birth);
			fn_district_birth();
			$('#district_birth_other').val(k[0].district_birth_other);
			
			$('#nationality').val(k[0].nationality);
			fn_nationality();
			$('#nationality_other').val(k[0].nationality_other);
			$('#religion').val(k[0].religion);
			fn_religion();
			$('#mother_tongue').val(k[0].mother_tongue);
			fn_mother_tongue();
			$('#religion_other').val(k[0].religion_other);
			$('#marital_status').val(k[0].marital_status);
			curr_marital_status=k[0].marital_status;
			$('#blood_group').val(k[0].blood_group);
			$('#height').val(k[0].height);
			$('#adhar_number1').val(k[0].adhar_number.toString().substring(0, 4));
			$('#adhar_number2').val(k[0].adhar_number.toString().substring(4, 8));
			$('#adhar_number3').val(k[0].adhar_number.toString().substring(8, 12));
			$('#pan_no').val(k[0].pancard_number);
			if(k[0].border_area == "yes") {
				$("input[name='border_area'][value='yes']").prop('checked', true);
			} else {
				$("input[name='border_area'][value='no']").prop('checked', true);
			}		
			$('#ncc_type').val(k[0].ncc_type);
			$('#father_name').val(k[0].father_name);
			$('#mother_name').val(k[0].mother_name);
			
			$('#com_institute').val(k[0].com_institute);
			getInst_btn(document.getElementById('com_institute'));
			$('#com_bn_id').val(k[0].com_bn_id);
			getInst_company(document.getElementById('com_bn_id'));
			$('#com_company_id').val(k[0].com_company_id);
			if(k[0].training_institute != null) {
				$('#training_institute').val(k[0].training_institute);
				getInst_trainngBtn(document.getElementById('training_institute'));
				$('#training_bn_id').val(k[0].training_bn_id);
				getInst_trainngCompany(document.getElementById('training_bn_id'));
				$('#training_company_id').val(k[0].training_company_id);
			}
			$("#org_domicile_Country").val(k[0].org_country);
			fn_org_domicile_Country_ready();
			$("#org_domicile_state").val(k[0].org_state);
			fn_org_domicile_state();
			$("#org_domicile_district").val(k[0].org_district);
			fn_org_domicile_district();
			$("#org_domicile_tehsil").val(k[0].org_tehsil);
			fn_org_domicile_tehsil();
			
			$("#org_domicile_Country_other").val(k[0].org_domicile_country_other);
			$("#org_domicile_state_other").val(k[0].org_domicile_state_other);
			$("#org_domicile_district_other").val(k[0].org_domicile_district_other);
			$("#org_domicile_tehsil_other").val(k[0].org_domicile_tehsil_other);
		
			getNCC();
			getallergicDetails();
			getPreCreditDetails();
			getsShapeDetails();
			gethShapeDetails();
			getaShapeDetails();
			getpShapeDetails();
			geteShapeDetails();
			getcCopeDetails();
			getoCopeDetails();
			getpCopeDetails();
			geteCopeDetails();
			if($('#medDtlFillIn3008').val() == 1){
				document.getElementById('divshape1bx').style.display = 'none';
				document.getElementById('divhideformed').style.display = 'none';
				 $('#1bx_from_date, #1bx_to_date,#1bx_diagnosis_code').prop('disabled', true);
			}
			else{
				document.getElementById('divshape1bx').style.display = 'block';
				document.getElementById('divhideformed').style.display = 'block';
				$('#1bx_from_date, #1bx_to_date,#1bx_diagnosis_code').prop('disabled', false);
			}
			document.getElementById("save_per").value = "Update";		
		
		} else{
			
			getsShapeDetails();
			gethShapeDetails();
			getaShapeDetails();
			getpShapeDetails();
			geteShapeDetails();
			getcCopeDetails();
			getoCopeDetails();
			getpCopeDetails();
			geteCopeDetails();
			if($('#medDtlFillIn3008').val() == 1){
				document.getElementById('divshape1bx').style.display = 'none';
				document.getElementById('divhideformed').style.display = 'none';
				 $('#1bx_from_date, #1bx_to_date,#1bx_diagnosis_code').prop('disabled', true);
			}
			else{
				document.getElementById('divshape1bx').style.display = 'block';
				document.getElementById('divhideformed').style.display = 'block';
				$('#1bx_from_date, #1bx_to_date,#1bx_diagnosis_code').prop('disabled', false);
			}
			 
		
		}
			
			
	
		
		var census_id = $('#census_id').val()
		  if(parseInt(census_id) > 0) {
			  var comm_id=$("#comm_id").val();
		  document.getElementById("personal_detail_div").style.display = "block";		  
		} else {
			  var comm_id=$("#comm_id").val();
			  document.getElementById("personal_detail_div").style.display = "block";
		} 
	});
}
$("input#personnel_no").keyup(function() {
	var personel_no = this.value;
	var susNoAuto = $("#personnel_no");
	susNoAuto.autocomplete({
		source: function(request, response) {
			$.ajax({
				type: 'POST',
				url: "getpersonnel_noList?" + key + "=" + value,
				data: {
					personel_no: personel_no
				},
				success: function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for(var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					response(susval);
				}
			});
		},
		minLength: 1,
		autoFill: true,
		change: function(event, ui) {
			if(ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Personal No");
				document.getElementById("personnel_no").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select: function(event, ui) {}
	});
});
$("input#issue_authority").keypress(function() {
	var unit_name = this.value;
	var susNoAuto = $("#issue_authority");
	susNoAuto.autocomplete({
		source: function(request, response) {
			$.ajax({
				type: 'POST',
				url: "getIssueingAuthList?" + key + "=" + value,
				data: {
					unit_name: unit_name
				},
				success: function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for(var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					response(susval);
				}
			});
		},
		minLength: 1,
		autoFill: true,
		change: function(event, ui) {
			if(ui.item) {
				return true;
			} else {
				alert("Please Enter Approved Issue Authority");
				document.getElementById("issue_authority").value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select: function(event, ui) {
			var unit_name = ui.item.value;
			$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
				target_unit_name: unit_name
			}, function(data) {}).done(function(data) {
				var length = data.length - 1;
				var enc = data[length].substring(0, 16);
				$("#issue_authority_sus").val(dec(enc, data[0]));
			}).fail(function(xhr, textStatus, errorThrown) {});
		}
	});
});
//*************************************END Personel Number Onchange*****************************//
//*************************************START Personel Detail *****************************//
function Validation() {
// 	if($("input#cadet_name").val().trim() == "") {
// 		alert("Please Enter Name ");
// 		$("input#cadet_name").focus();
// 		return false;
// 	}
	if($("input#first_name").val().trim() == "") {
		alert("Please Enter  First Name ");
		$("input#first_name").focus();
		return false;
	}
	
	if($("select#country_birth").val() == "0") {
		alert("Please Select Country of Birth");
		$("select#country_birth").focus();
		return false;
	}
	var text9 = $("#country_birth option:selected").text();
	
	if(text9.toUpperCase() == "OTHERS" && $("input#country_birth_other").val().trim() == ""){
		alert("Please Enter Country Birth Other");
		$("input#country_birth_other").focus();
		return false;
	}
	if($("select#state_birth").val() == "0") {
		alert("Please Select State of Birth ");
		$("select#state_birth").focus();
		return false;
	}
var text10 = $("#state_birth option:selected").text();
	
	if(text10.toUpperCase() == "OTHERS" && $("input#state_birth_other").val().trim() == ""){
		alert("Please Enter State Birth Other");
		$("input#state_birth_other").focus();
		return false;
	}
	if($("select#district_birth").val() == "0") {
		alert("Please Select District of Birth ");
		$("select#district_birth").focus();
		return false;
	}
var text11 = $("#district_birth option:selected").text();
	
	if(text11.toUpperCase() == "OTHERS" && $("input#district_birth_other").val().trim() == ""){
		alert("Please Enter District Birth Other");
		$("input#district_birth_other").focus();
		return false;
	}
	if($("input#place_birth").val().trim() == "") {
		alert("Please Enter Place of Birth ");
		$("input#place_birth").focus();
		return false;
	}
	if($("select#nationality").val() == "0") {
		alert("Please Select Nationality");
		$("select#nationality").focus();
		return false;
	}
var text12 = $("#nationality option:selected").text();
	
	if(text12.toUpperCase() == "OTHERS" && $("input#nationality_other").val().trim() == ""){
		alert("Please Enter Nationality Other");
		$("input#nationality_other").focus();
		return false;
	}
	if($("select#mother_tongue").val() == "0") {
		alert("Please Select Mother Tongue");
		$("select#mother_tongue").focus();
		return false;
	}
var text13 = $("#mother_tongue option:selected").text();
	
	if(text13.toUpperCase() == "OTHERS" && $("input#mother_tongue_other").val().trim() == ""){
		alert("Please Enter Mother Tongue Other");
		$("input#mother_tongue_other").focus();
		return false;
	}
	if($("select#religion").val() == "0") {
		alert("Please Select Religion");
		$("select#religion").focus();
		return false;
	}
var text14 = $("#religion option:selected").text();
	
	if(text14.toUpperCase() == "OTHERS" && $("input#religion_other").val().trim() == ""){
		alert("Please Enter Religion Other");
		$("input#religion_other").focus();
		return false;
	}
	if($("select#ncc_type").val() == "0") {
		alert("Please Select Type of Entry");
		$("select#ncc_type").focus();
		return false;
	}
	var aadhar1 = $("input#adhar_number1").val();
	var aadhar2 = $("input#adhar_number2").val();
	var aadhar3 = $("input#adhar_number3").val();
	if(aadhar1.trim()!="" &&aadhar1.length < 4) {
		alert("Please Enter Aadhaar Number");
		$("input#adhar_number1").focus();
		return false;				
	}
	
	if(aadhar2.trim()!="" && aadhar2.length < 4) {
		alert("Please Enter Aadhaar Number");
		$("input#adhar_number2").focus();
		return false;
	}
	if(aadhar3.trim()!="" && aadhar3.length < 4) {
		alert("Please Enter Aadhaar Number");
		$("input#adhar_number3").focus();
		return false;
	}
	if(aadhar1.trim()!="" &&aadhar1.length ==4) {
		if(aadhar2.trim()=="" && aadhar2.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number2").focus();
			return false;
		}
		if(aadhar3.trim()=="" && aadhar3.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number3").focus();
			return false;
		}			
	}
	if(aadhar2.trim()!="" &&aadhar2.length ==4) {
		if(aadhar1.trim()=="" && aadhar1.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number1").focus();
			return false;
		}
		if(aadhar3.trim()=="" && aadhar3.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number3").focus();
			return false;
		}			
	}
	if(aadhar3.trim()!="" &&aadhar3.length ==4) {
		if(aadhar1.trim()=="" && aadhar1.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number1").focus();
			return false;
		}
		if(aadhar2.trim()=="" && aadhar2.length < 4) {
			alert("Please Enter Aadhaar Number");
			$("input#adhar_number2").focus();
			return false;
		}			
	}
	
	
	
	
// 	var pan_no = $('#pan_no').val();
// 	if(pan_no.trim()=="") {
// 		alert("Please Enter PAN Number");
// 		$('#pan_no').focus();
// 		return false
// 	}
	if($("select#com_institute").val() == "0") {
		alert("Please Select Commissioning Institute");
		$("select#com_institute").focus();
		return false;
	}
	if($("select#marital_status").val() == "0") {
		alert("Please Select Marital Status");
		$("select#marital_status").focus();
		return false;
	}
	if($("select#blood_group").val() == "0") {
		alert("Please Select Blood Group");
		$("select#blood_group").focus();
		return false;
	}
	if($("select#height").val() == "0") {
		alert("Please Select Height");
		$("select#height").focus();
		return false;
	}
	
	//original Domicile validation
	if($("select#org_domicile_Country").val() == "0") {
		alert("Please Select Original Domicile Country");
		$("select#org_domicile_Country").focus();
		return false;
	}
	
	 var text1 = $("#org_domicile_Country option:selected").text();
		if(text1.toUpperCase() == "OTHERS" && $("input#org_domicile_Country_other").val().trim() == ""){
			alert("Please Enter Original Domicile Country Other");
			$("input#org_domicile_Country_other").focus();
			return false;
		}
	
	if($("select#org_domicile_state").val() == "0") {
		alert("Please Select Original Domicile State");
		$("select#org_domicile_state").focus();
		return false;
	}
	
	var text2 = $("#org_domicile_state option:selected").text();
	if(text2.toUpperCase() == "OTHERS" && $("input#org_domicile_state_other").val().trim() == ""){
		alert("Please Enter Original Domicile State Other");
		$("input#org_domicile_state_other").focus();
		return false;
	}
	
	if($("select#org_domicile_district").val() == "0") {
		alert("Please Select Original Domicile District");
		$("select#org_domicile_district").focus();
		return false;
	}
	var text3 = $("#org_domicile_district option:selected").text();
	if(text3.toUpperCase() == "OTHERS" && $("input#org_domicile_district_other").val().trim() == ""){
		alert("Please Enter Original Domicile District Other");
		$("input#org_domicile_district_other").focus();
		return false;
	}
	if($("select#org_domicile_tehsil").val() == "0") {
		alert("Please Select Original Domicile Tehsil");
		$("select#org_domicile_tehsil").focus();
		return false;
	}
	var text4 = $("#org_domicile_tehsil option:selected").text();
	if(text4.toUpperCase() == "OTHERS" && $("input#org_domicile_tehsil_other").val().trim() == ""){
		alert("Please Enter Original Domicile Tehsil Other");
		$("input#org_domicile_tehsil_other").focus();
		return false;
	}
	
	
	//pre-cadet
		if($("select#cadet_status1").val() == "0") {
		alert("Please Select Pre-Cadet Status  ");
		$("select#cadet_status1").focus();
		return false;
	}
	if($("#cadet_status1").val() == "2" || $("#cadet_status1").val() == "3" || $("#cadet_status1").val() == "4") {
		if($("input#designation1").val().trim() == "") {
			alert("Please Enter Designation");
			$("input#designation1").focus();
			return false;
		}
		if($("input#emp_name1").val().trim() == "") {
			alert("Please Enter Name of Employer");
			$("input#emp_name1").focus();
			return false;
		}
		if($("#cadet_status1").val() != "2") {
			var a = $('input:radio[name=isgazetted1]:checked').val();
			if(a == undefined) {
				alert("Please select If Gazetted/Non Gazetted");
				return false;
			}
			var b = $('input:radio[name=isCivil1]:checked').val();
			if(b == undefined) {
				alert("Please select If Pensionable Civil Service");
				return false;
			}
			
		}
		else{
			
			if($('#competency').val()=='0'){
				alert("Please Select The Competency");
				$('#competency').focus();
				return false; 
			}
	        if($("#competency option:selected").text().toUpperCase()=="OTHERS"){
                if($('#competency_other').val().trim()==''){
                alert("Please Enter The Competency Other");
                $('#competency_other').focus();
                return false; 
        }
        }

			
		}
	}
	if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "6" || $("#cadet_status1").val() == "7" || $("#cadet_status1").val() == "8" || $("#cadet_status1").val() == "9") {
		
		if($("input#army_no1").val().trim() == "") {
			alert("Please Enter Service No");
			$("input#army_no1").focus();
			return false;
		}
		if($("input#unit_reg1").val().trim() == "") {
			alert("Please Enter Unit/Regiment ");
			$("input#unit_reg1").focus();
			return false;
		}
		if($("#cadet_status1").val() == "5") {
			if($("input#sus_no").val().trim() == "") {
				alert("Please Enter valid Unit/Regiment ");
				$("input#unit_reg1").focus();
				return false;
			}
		}
		if($("input#date_form1").val().trim() == "" || $("input#date_form1").val() == "DD/MM/YYYY") {
			alert("Please Select From Date ");
			$("input#date_form1").focus();
			return false;
		}
		if(getformatedDate($("input#dob_date").val()) >= getformatedDate($("input#date_form1").val())) {
			alert("From Date should be Greater than Date of Birth");
			$("input#date_form" + ps).focus();
			return false;
		}
		if($("input#date_to1").val().trim() == "" || $("input#date_to1").val() == "DD/MM/YYYY") {
			alert("Please Select To Date ");
			$("input#date_to1").focus();
			return false;
		}
		var currentdate = new Date();
		if(getformatedDate($("input#date_form1").val()) >= getformatedDate(comm_date)) {
			alert("Enter From Date Should Be Before Date of Commision");
			$("input#date_form1").focus();
			return false;
		}
		if(getformatedDate($("input#date_to1").val()) >= getformatedDate(comm_date)) {
			alert("Enter To Date Should Be Before Date of Commision");
			$("input#date_to1").focus();
			return false;
		}
		
		if(getformatedDate($("input#date_form1").val()) > getformatedDate($("input#date_to1").val())) {
	
			alert("Invalid Date Range");
			$("input#date_to1").focus();
			$('#total_rank1').val('');
			return false;
		}
		if($("input#total_rank1").val().trim() == "") {
			alert("Please Enter Total Service In Rank ");
			$("input#total_rank1").focus();
			return false;
		}
		
		
	}
	
	//medical category
var count_classi = 0;
if($('#medDtlFillIn3008').val() == 0){
	
	if ($("#check_1bx").prop('checked')){
		if($("input#1bx_from_date").val().trim()=="" || $("input#1bx_from_date").val().trim()=="DD/MM/YYYY"){
			alert("Please Enter From Date");
			$("#1bx_from_date").focus();
			return false;
		}
		if($("input#1bx_to_date").val().trim()=="" || $("input#1bx_to_date").val().trim()=="DD/MM/YYYY"){
			alert("Please Enter To Date");
			$("#1bx_to_date").focus();
			return false;
		}
		if(getformatedDate($("input#1bx_from_date").val()) > getformatedDate($("#1bx_to_date").val())) {
			alert("To Date should be Greater than Or Equal to From Date");
			$("#1bx_to_date").focus();
			
			return false;
	    }
		if($("#1bx_diagnosis_code").val().trim()==""){
				alert("Please Enter Diagnosis");
				$("#1bx_diagnosis_code").focus();				
				return false;
			}
		
	  }
	else{
		
		$("#1bx_from_date").val("");
		$("#1bx_to_date").val("");
		$("#1bx_diagnosis_code").val("");	
		
		for(var j=1; j<=sShape; j++){
			if($("#s_status"+j).val()=='0'){
				alert("Please Select S-Shape Status In "+j+" Row");
				$("#s_status"+j).focus();
				return false;
			}
			
			if($("#sShape_value"+j).val()=='0'){
				alert("Please Select S-Shape Value In "+j+" Row");
				$("#sShape_value"+j).focus();
				return false;
			}
			if($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B"){
				if($("input#s_to_date"+j).val().trim()!="" && $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter S-Shape From Date In "+j+" Row");
						$("#s_from_date"+j).focus();
						return false;
					}
				}
				if($("input#s_from_date"+j).val().trim()!="" && $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter S-Shape To Date In "+j+" Row");
						$("#s_to_date"+j).focus();
						return false;
					}
				}
			}
			if($("#s_status"+j).val() != "0" && $("#s_status"+j).val() != "1")
				count_classi++;
			
			if($("#s_status"+j).val() != "1"){
				if($("input#s_from_date"+j).val().trim()=="" || $("input#s_from_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter S-Shape From Date In "+j+" Row");
					$("#s_from_date"+j).focus();
					return false;
				}
				if($("input#s_to_date"+j).val().trim()=="" || $("input#s_to_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter S-Shape To Date In "+j+" Row");
					$("#s_to_date"+j).focus();
					return false;
				}
			}	
		
			if(($("#s_status"+j).val() == "1" && $("#sShape_value"+j).val()=="1B" && $("input#s_from_date"+j).val().trim()!="" 
					&& $("input#s_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#s_to_date"+j).val().trim()!="" 
					&& $("input#s_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#s_status"+j).val() != "1"){
				if(getformatedDate($("input#s_from_date"+j).val()) > getformatedDate($("#s_to_date"+j).val())) {
					alert("S-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
					$("#s_to_date"+j).focus();
					
					return false;
			    }
			}
			
	
		}
		for(var j=1; j<=hShape; j++){
			if($("#h_status"+j).val()=='0'){
				alert("Please Select H-Shape Status In "+j+" Row");
				$("#h_status"+j).focus();
				return false;
			}
			
			if($("#hShape_value"+j).val()=='0'){
				alert("Please Select H-Shape Value In "+j+" Row");
				$("#hShape_value"+j).focus();
				return false;
			}
			if($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B"){
				if($("input#h_to_date"+j).val().trim()!="" && $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter H-Shape From Date In "+j+" Row");
						$("#h_from_date"+j).focus();
						return false;
					}
				}
				if($("input#h_from_date"+j).val().trim()!="" && $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter H-Shape To Date In "+j+" Row");
						$("#h_to_date"+j).focus();
						return false;
					}
				}
			}
			if($("#h_status"+j).val() != "0" && $("#h_status"+j).val() != "1")
				count_classi++;
			
			if($("#h_status"+j).val() != "1"){
				if($("input#h_from_date"+j).val().trim()=="" || $("input#h_from_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter H-Shape From Date In "+j+" Row");
					$("#h_from_date"+j).focus();
					return false;
				}
				if($("input#h_to_date"+j).val().trim()=="" || $("input#h_to_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter H-Shape To Date In "+j+" Row");
					$("#h_to_date"+j).focus();
					return false;
				}
			}
			
			if(($("#h_status"+j).val() == "1" && $("#hShape_value"+j).val()=="1B" && $("input#h_from_date"+j).val().trim()!="" 
				&& $("input#h_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#h_to_date"+j).val().trim()!="" 
				&& $("input#h_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#h_status"+j).val() != "1"){
				if(getformatedDate($("input#h_from_date"+j).val()) > getformatedDate($("#h_to_date"+j).val())) {
					alert("H-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
					$("#h_to_date"+j).focus();
					return false;
			    }
			}
			
			
			
		}
		for(var j=1; j<=aShape; j++){
			if($("#a_status"+j).val()=='0'){
				alert("Please Select A-Shape Status In "+j+" Row");
				$("#a_status"+j).focus();
				return false;
			}
			
			if($("#aShape_value"+j).val()=='0'){
				alert("Please Select A-Shape Value In "+j+" Row");
				$("#aShape_value"+j).focus();
				return false;
			}
			if($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B"){
				if($("input#a_to_date"+j).val().trim()!="" && $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter A-Shape From Date In "+j+" Row");
						$("#a_from_date"+j).focus();
						return false;
					}
				}
				if($("input#a_from_date"+j).val().trim()!="" && $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter A-Shape To Date In "+j+" Row");
						$("#a_to_date"+j).focus();
						return false;
					}
				}
			}
			if($("#a_status"+j).val() != "0" && $("#a_status"+j).val() != "1")
				count_classi++;
			
			if($("#a_status"+j).val() != "1"){
				if($("input#a_from_date"+j).val().trim()=="" || $("input#a_from_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter A-Shape From Date In "+j+" Row");
					$("#a_from_date"+j).focus();
					return false;
				}
				if($("input#a_to_date"+j).val().trim()=="" || $("input#a_to_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter A-Shape To Date In "+j+" Row");
					$("#a_to_date"+j).focus();
					return false;
				}
			}
			if(($("#a_status"+j).val() == "1" && $("#aShape_value"+j).val()=="1B" && $("input#a_from_date"+j).val().trim()!="" 
				&& $("input#a_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#a_to_date"+j).val().trim()!="" 
				&& $("input#a_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#a_status"+j).val() != "1"){
				if(getformatedDate($("input#a_from_date"+j).val()) > getformatedDate($("#a_to_date"+j).val())) {
					alert("A-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
					$("#a_to_date"+j).focus();
					return false;
			    }
			}	
		}
		for(var j=1; j<=pShape; j++){
			if($("#p_status"+j).val()=='0'){
				alert("Please Select P-Shape Status In "+j+" Row");
				$("#p_status"+j).focus();
				return false;
			}
			
			if($("#pShape_value"+j).val()=='0'){
				alert("Please Select P-Shape Value In "+j+" Row");
				$("#pShape_value"+j).focus();
				return false;
			}
			if($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B"){
				if($("input#p_to_date"+j).val().trim()!="" && $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter P-Shape From Date In "+j+" Row");
						$("#p_from_date"+j).focus();
						return false;
					}
				}
				if($("input#p_from_date"+j).val().trim()!="" && $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter P-Shape To Date In "+j+" Row");
						$("#p_to_date"+j).focus();
						return false;
					}
				}
			}
			if($("#p_status"+j).val() != "0" && $("#p_status"+j).val() != "1")
				count_classi++;
			if($("#p_status"+j).val() != "1"){
				if($("input#p_from_date"+j).val().trim()=="" || $("input#p_from_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter P-Shape From Date In "+j+" Row");
					$("#p_from_date"+j).focus();
					return false;
				}
				if($("input#p_to_date"+j).val().trim()=="" || $("input#p_to_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter P-Shape To Date In "+j+" Row");
					$("#p_to_date"+j).focus();
					return false;
				}
			}
			
			if(($("#p_status"+j).val() == "1" && $("#pShape_value"+j).val()=="1B" && $("input#p_from_date"+j).val().trim()!="" 
				&& $("input#p_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#p_to_date"+j).val().trim()!="" 
				&& $("input#p_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#p_status"+j).val() != "1"){
				
				if(getformatedDate($("input#p_from_date"+j).val()) > getformatedDate($("#p_to_date"+j).val())) {
					alert("P-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
					$("#p_to_date"+j).focus();
					return false;
			    }
			}
		}
		for(var j=1; j<=eShape; j++){
			
			if($("#e_status"+j).val()=='0'){
				alert("Please Select E-Shape Status In "+j+" Row");
				$("#e_status"+j).focus();
				return false;
			}
			
			if($("#eShape_value"+j).val()=='0'){
				alert("Please Select E-Shape Value In "+j+" Row");
				$("#eShape_value"+j).focus();
				return false;
			}
			if($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B"){
				if($("input#e_to_date"+j).val().trim()!="" && $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter E-Shape From Date In "+j+" Row");
						$("#e_from_date"+j).focus();
						return false;
					}
				}
				
				if($("input#e_from_date"+j).val().trim()!="" && $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY"){
					if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
						alert("Please Enter E-Shape To Date In "+j+" Row");
						$("#e_to_date"+j).focus();
						return false;
					}
				}
			}
			
			if($("#e_status"+j).val() != "0" && $("#e_status"+j).val() != "1")
				count_classi++;
			if($("#e_status"+j).val() != "1"){
				if($("input#e_from_date"+j).val().trim()=="" || $("input#e_from_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter E-Shape From Date In "+j+" Row");
					$("#e_from_date"+j).focus();
					return false;
				}
				if($("input#e_to_date"+j).val().trim()=="" || $("input#e_to_date"+j).val().trim()=="DD/MM/YYYY"){
					alert("Please Enter E-Shape To Date In "+j+" Row");
					$("#e_to_date"+j).focus();
					return false;
				}
			}
			if(($("#e_status"+j).val() == "1" && $("#eShape_value"+j).val()=="1B" && $("input#e_from_date"+j).val().trim()!="" 
				&& $("input#e_from_date"+j).val().trim()!="DD/MM/YYYY" && $("input#e_to_date"+j).val().trim()!="" 
				&& $("input#e_to_date"+j).val().trim()!="DD/MM/YYYY") || $("#e_status"+j).val() != "1"){
				
			
				if(getformatedDate($("input#e_from_date"+j).val()) > getformatedDate($("#e_to_date"+j).val())) {
					alert("E-Shape To Date should be Greater than Or Equal to From Date "+j+" Row");
					$("#e_to_date"+j).focus();
					return false;
			    }
			}	
		}
		
		for(var j=1; j<=cCope; j++){
				if($("#c_cvalue"+j).val()=="2 [c]"){
					if($("#c_cother"+j).val().trim()==""){
						alert("Please Enter C-Cope Other In "+j+" Row");
						$("#c_cother"+j).focus();
						return false;
					}
				}
		}
		
		for(var j=1; j<=eCope; j++){
			if($("#c_evalue"+j).val()=="1"){
				if($("#c_esubvalue"+j).val().trim()=="0"){
					alert("Please Select E-Cope Sub Value In "+j+" Row");
					$("#c_esubvalue"+j).focus();
					return false;
				}
				if($("#c_esubvalueother"+j).val().trim()=="" && $("#c_esubvalue"+j).val().trim()=="k"){
					alert("Please Enter E-Cope Sub Other In "+j+" Row");
					$("#c_esubvalueother"+j).focus();
					return false;
				}
			}
		}
		
		
	}
if(count_classi >= 3){
	$("#mad_classification_count").val("Z");
}
else if(count_classi == 2){
	$("#mad_classification_count").val("Y");
}
else if(count_classi == 1){
	$("#mad_classification_count").val("X");
}
else
	$("#mad_classification_count").val("NIL");	
	
}

	var first_name = $('#first_name').val();
	var middle_name = $('#middle_name').val();
	var last_name = $('#last_name').val();
	var place_birth = $('#place_birth').val();
	
	var district_birth = $('#district_birth').val();
	var district_birth_other = $('#district_birth_other').val();
	
	var state_birth = $('#state_birth').val();
	var state_birth_other = $('#state_birth_other').val();
	
	var country_birth = $('#country_birth').val();
	var country_birth_other = $('#country_birth_other').val();
	
	var nationality = $('#nationality').val();
	var nationality_other = $('#nationality_other').val();
	var religion = $('#religion').val();
	var religion_other = $('#religion_other').val();
	var marital_status = $('#marital_status').val();
	var blood_group = $('#blood_group').val();
	var height = $('#height').val();
	var adhar_number = aadhar1 + "" + aadhar2 + "" + aadhar3;
	// 	var identity = $("input[name='identity_radio']:checked").val();
	var border_area = $("input[name='border_area']:checked").val();
	var comm_id = $('#comm_id').val();
	var census_id = $('#census_id').val();
	var med_id = $('#med_id').val();
	var id_card_no = $('#id_card_no').val();
	var issue_dt = $('#issue_dt').val();
	var id_marks = $('#id_marks').val();
	var hair_colour = $('#hair_colour').val();
	var hair_colour_other = $('#hair_colour_other').val();
	var eye_colour = $('#eye_colour').val();
	var eye_colour_other = $('#eye_colour_other').val();
	var mother_tongue = $('#mother_tongue').val();
	var mother_tongue_other = $('#mother_tongue_other').val();
	var ncc_type = $('#ncc_type').val();
	var com_institute = $('#com_institute').val();
	var com_bn_id = $('#com_bn_id').val();
	var com_company_id = $('#com_company_id').val();
	var training_institute = $('#training_institute').val();
	var training_bn_id = $('#training_bn_id').val();
	var training_company_id = $('#training_company_id').val();
	var platoon_id = $('#platoon_id').val();
	var issue_authority = $('#issue_authority_sus').val();
	var total_rank = $("#total_rank1").val();
	$("#total_rank").val(total_rank);
	var medDtlFillIn3008= $('#medDtlFillIn3008').val();
	// 	var sShape_id=$('#sShape_id').val();
	// 	var hShape_id=$('#hShape_id').val();
	// 	var aShape_id=$('#aShape_id').val();
	// 	var pShape_id=$('#pShape_id').val();
	// 	var eShape_id=$('#eShape_id').val();
	var allergic_radio = $("input[name='allergic_radio']:checked").val();
	if(allergic_radio == "yes") {
		for(al = 1; al <= aller; al++) {
			if($('#medicine' + al).val().trim() == "") {
				alert("Please Enter Allergy");
				$('#medicine' + al).focus();
				return false
			}
		}
	}


	$('#save_per').prop('disabled', true);
	// $("#WaitLoader").show();
	// 	aaaa	 var formvalues=$("#personalDetailsForm").serialize();
	
	var form_data = new FormData(document.getElementById("personalDetailsForm"));
	$.ajax({
		url: 'Personnel_DetailModifyaction?_csrf=' + value, 
		type: "POST",
		data: form_data,
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false
	}).done(function(data) {
		if(data.error == null) {			
			alert("Data Save/Updated Successfully");	
			$('#save_per').prop('disabled', false);	
			var personnelNo = $("#personnel_no").val();
			   if (personnelNo.substring(0, 2).toUpperCase() === 'NR' || 
				        personnelNo.substring(0, 2).toUpperCase() === 'NS') {
				   window.location.href="search_censusMnsUrl";
				    } else {
				    	window.location.href="search_censusUrl";
				    }
			
		} else {
			alert(data.error)
			$('#save_per').prop('disabled', false);
		}
	}).fail(function(jqXHR, textStatus) {
		alert("fail to fetch");
		$('#save_per').prop('disabled', false);
	});
}
function getInst_btn(obj) {
	var inst_id = obj.value;
	$.post("getInsatitute_btn?" + key + "=" + value, {
		inst_id: inst_id
	}).done(function(j) {
		var options = '<option value="0" >--Select--</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#com_bn_id").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
	getInst_company(document.getElementById('com_bn_id'));
}

function getInst_trainngBtn(obj) {
	var inst_id = obj.value;
	$.post("getInsatitute_btn?" + key + "=" + value, {
		inst_id: inst_id
	}).done(function(j) {
		var options = '<option value="0" >--Select--</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#training_bn_id").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
	getInst_trainngCompany(document.getElementById('training_bn_id'));
}

function getInst_company(obj) {
	var btn_id = obj.value;
	var inst_id = $("#com_institute").val();
	$.post("getInsatitute_company?" + key + "=" + value, {
		btn_id: btn_id,
		inst_id: inst_id
	}).done(function(j) {
		var options = '<option value="0" >--Select--</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#com_company_id").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function getInst_trainngCompany(obj) {
	var btn_id = obj.value;
	var inst_id = $("#training_institute").val();
	$.post("getInsatitute_company?" + key + "=" + value, {
		btn_id: btn_id,
		inst_id: inst_id
	}).done(function(j) {
		var options = '<option value="0" >--Select--</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#training_company_id").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}
//}
//*************************************END Personel Detail*****************************//

// ////////////////////////////////start  pre_cadet//////////////////////////////////////////////////


function typeSelection() {
	if($("#cadet_status1").val() == "1") {
		$('#designation1').prop('disabled', true);
		$('#emp_name1').prop('disabled', true);
		$('#isyes1').prop('disabled', true);
		$('#isno1').prop('disabled', true);
		$('#iscivilyes1').prop('disabled', true);
		$('#iscivilno1').prop('disabled', true);
		$('#army_no1').prop('disabled', true);
		$('#date_form1').prop('disabled', true);
		$('#date_to1').prop('disabled', true);
		$('#unit_reg1').prop('disabled', true);
		$('#competency').prop('disabled', true);
	} else if($("#cadet_status1").val() == "2" || $("#cadet_status1").val() == "3" || $("#cadet_status1").val() == "4") {
		$('#designation1').prop('disabled', false);
		$('#emp_name1').prop('disabled', false);
		if($("#cadet_status1").val() != "2") {
			$('#isyes1').prop('disabled', false);
			$('#isno1').prop('disabled', false);
			$('#iscivilyes1').prop('disabled', false);
			$('#iscivilno1').prop('disabled', false);
			$('#competency').prop('disabled', true);
		} else {
			$('#isyes1').prop('disabled', true);
			$('#isno1').prop('disabled', true);
			$('#iscivilyes1').prop('disabled', true);
			$('#iscivilno1').prop('disabled', true);
			$('#competency').prop('disabled', false);
		}
		$('#army_no1').prop('disabled', true);
		$('#date_form1').prop('disabled', true);
		$('#date_to1').prop('disabled', true);
		$('#unit_reg1').prop('disabled', true);
		if($("#cadet_status1").val() == "2") {
			$('#isyes1').prop('checked', false);
			$('#isno1').prop('checked', false);
			$('#iscivilyes1').prop('checked', false);
			$('#iscivilno1').prop('checked', false);
			$('#competency').prop('disabled', false);
		}
		$('#army_no1').val('');
		$('#date_form1').val('');
		$('#date_to1').val('');
		$('#total_rank1').val('');
		$('#unit_reg1').val('');
	} else if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "6" || $("#cadet_status1").val() == "7" || $("#cadet_status1").val() == "8" || $("#cadet_status1").val() == "9") {
		$('#designation1').prop('disabled', true);
		$('#emp_name1').prop('disabled', true);
		$('#isyes1').prop('disabled', true);
		$('#isno1').prop('disabled', true);
		$('#iscivilyes1').prop('disabled', true);
		$('#iscivilno1').prop('disabled', true);
		$('#army_no1').prop('disabled', false);
		$('#date_form1').prop('disabled', false);
		$('#date_to1').prop('disabled', false);
		$('#unit_reg1').prop('disabled', false);
		$('#designation1').val('');
		$('#emp_name1').val('');
		$('#isyes1').prop('checked', false);
		$('#isno1').prop('checked', false);
		$('#iscivilyes1').prop('checked', false);
		$('#iscivilno1').prop('checked', false);
		$('#competency').prop('disabled', true);
		
	} else {}
	$('#designation1').val('');
	$('#emp_name1').val('');
	$('#isyes1').prop('checked', false);
	$('#isno1').prop('checked', false);
	$('#iscivilyes1').prop('checked', false);
	$('#iscivilno1').prop('checked', false);
	$('#army_no1').val('');
	$('#date_form1').val('');
	$('#date_to1').val('');
	$('#total_rank1').val('');
	$('#unit_reg1').val('');
	$('#competency').val('0');
	fn_otherShowHide(document.getElementById('competency'),'divcompetency_other','competency_other');
}

function getPreCreditDetails() {

	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getModifyPreCadetData?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			
			
			$('#cadet_status1').val(j[0].pre_cadet_status);
			if(j[0].pre_cadet_status == "1") {
				$('#designation1').prop('disabled', true);
				$('#emp_name1').prop('disabled', true);
				$('#isyes1').prop('disabled', true);
				$('#isno1').prop('disabled', true);
				$('#iscivilyes1').prop('disabled', true);
				$('#iscivilno1').prop('disabled', true);
				$('#army_no1').prop('disabled', true);
				$('#date_form1').prop('disabled', true);
				$('#date_to1').prop('disabled', true);
				$('#unit_reg1').prop('disabled', true);
				$('#competency').prop('disabled', true);
			}
			if($("#cadet_status1").val() == "2" || $("#cadet_status1").val() == "3" || $("#cadet_status1").val() == "4") {
				$("#designation1").val(j[0].designation);
				$("#emp_name1").val(j[0].employee_name);
				if(j[0].gazetted == "gazetted") {
					$("input[name='isgazetted1'][value='gazetted']").prop('checked', true);
				} else {
					$("input[name='isgazetted1'][value='non_gazetted']").prop('checked', true);
				}
				if(j[0].civil_service == "yes") {
					$("input[name='isCivil1'][value='yes']").prop('checked', true);
				} else {
					$("input[name='isCivil1'][value='no']").prop('checked', true);
				}
				if($("#cadet_status1").val() == "2") {
					$("#competency").val(j[0].competency);
					if(j[0].competency_other!=null)
						$("#competency_other").val(j[0].competency_other);
					
					$('#competency').prop('disabled', false);
					$("input[name='isgazetted1'][value='gazetted']").prop('checked', false);
					$("input[name='isgazetted1'][value='non_gazetted']").prop('checked', false);
					$("input[name='isCivil1'][value='yes']").prop('checked', false);
					$("input[name='isCivil1'][value='no']").prop('checked', false);
					$("input[name='isgazetted1'][value='gazetted']").prop('disabled', true);
					$("input[name='isgazetted1'][value='non_gazetted']").prop('disabled', true);
					$("input[name='isCivil1'][value='yes']").prop('disabled', true);
					$("input[name='isCivil1'][value='no']").prop('disabled', true);
				}
				else{
					$("#competency").val(0);
					$('#competency').prop('disabled', true);
				}
				$('#army_no1').prop('disabled', true);
				$('#date_form1').prop('disabled', true);
				$('#date_to1').prop('disabled', true);
				$('#unit_reg1').prop('disabled', true);
			}
			if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "6" || $("#cadet_status1").val() == "7" || $("#cadet_status1").val() == "8" || $("#cadet_status1").val() == "9") {
				$("#army_no1").val(j[0].army_no);
				$("#date_form1").val(ParseDateColumncommission(j[0].from_date));
				$("#date_to1").val(ParseDateColumncommission(j[0].to_date));
				$("#total_rank1").val(j[0].total_service);
				$('#designation1').prop('disabled', true);
				$('#emp_name1').prop('disabled', true);
				$('#isyes1').prop('disabled', true);
				$('#isno1').prop('disabled', true);
				$('#iscivilyes1').prop('disabled', true);
				$('#iscivilno1').prop('disabled', true);
				$('#competency').prop('disabled', true);
				if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "9") {
					$("#sus_no").val(j[0].unit_reg);
					fn_getUnitnamefromSus(j[0].unit_reg, document.getElementById("unit_reg1"));
				} else {
					$("#unit_reg1").val(j[0].unit_reg);
				}
			}
			$("#pre_ch_id1").val(j[0].id);
			
			fn_otherShowHide(document.getElementById('competency'),'divcompetency_other','competency_other');
		}
	});
}

function calcDate() {
	
	if($('#date_form1').val().trim() != "" && $('#date_to1').val().trim() != "" && $('#date_form1').val() != "DD/MM/YYYY" && $('#date_to1').val() != "DD/MM/YYYY") {
		var startDate = getformatedDate($('#date_form1').val());
		var endDate = getformatedDate($('#date_to1').val());
		if(startDate > endDate) {
			alert("Invalid Date Range");
			$("input#date_to1").focus();
			$('#total_rank1').val('');
			return false;
		}
		


		
		
		 var startYear = startDate.getFullYear();
		    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
		    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		    var yearDiff = endDate.getFullYear() - startYear;
		    var monthDiff = endDate.getMonth() - startDate.getMonth();
		    if (monthDiff < 0) {
		        yearDiff--;
		        monthDiff += 12;
		    }
		    var dayDiff = endDate.getDate() - startDate.getDate();
		    if (dayDiff < 0) {
		        if (monthDiff > 0) {
		            monthDiff--;
		        } else {
		            yearDiff--;
		            monthDiff = 11;
		        }
		        dayDiff += daysInMonth[startDate.getMonth()];
		    }
	    
		var message;
		   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
			message = "0 years 0 months 1 days";
		} else {
			message = yearDiff + " years "
			message += monthDiff + " months "
			message += dayDiff + " days"
		}
		   $('#total_rank1').val(message);
	} else {
		$('#total_rank1').val('');
	}
	
}
$("input#unit_reg1").keypress(function() {
	if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "9") {
		var unit_name = this.value;
		var susNoAuto = $("#unit_reg1");
		susNoAuto.autocomplete({
			source: function(request, response) {
				$.ajax({
					type: 'POST',
					url: "UnitsNameList_active_or_inactive?" + key + "=" + value,
					data: {
						unit_name: unit_name
					},
					success: function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for(var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength: 1,
			autoFill: true,
			change: function(event, ui) {
				if(ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Unit/Regiment");
					document.getElementById("unit_reg1").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select: function(event, ui) {
				var unit_name = ui.item.value;
				$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
					target_unit_name: unit_name
				}, function(data) {}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#sus_no").val(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {});
			}
		});
	} else {
		$("#unit_reg1").autocomplete({
			source: [],
			delay: 200,
			minLength: 3,
			select: function(event, ui) {
				ui.item.value = ""; 
				return false;
			}
		});
	}
});



function ncc_save_fn() {
	alert("ncc");
	var a = $('input:radio[name=otu1]:checked').val();
	if(a == undefined) {
		alert("Please select Whether in OTU Div/Jr Div/Sr Div");
		return false;
	}
	var otu = $("input[name='otu1']:checked").val();
	if(otu == undefined) {
		alert("Please select Whether in OTU Div/Jr Div/Sr Div");
		return false;
	}
	$.post('ncc_action?' + key + "=" + value, {
		otu: otu,
		ncc_ch_id: ncc_ch_id,
		n_id: n_id
	}, function(data) {
		if(data == "update") alert("Data Updated Successfully");
		else if(parseInt(data) > 0) {
			$('#ncc_ch_id1').val(data);
			alert("Data Saved Successfully")
		} else alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getNCC() {
	var n_id = $('#census_id').val();
	var i = 0;
	$.post('getModifyNCCData?' + key + "=" + value, {
		n_id: n_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$("#ncc_ch_id1").val(j[0].id);
			if(j[0].otu == "otu_div") {
				$("input[name='otu1'][value='otu_div']").prop('checked', true);
				$("#check_ncc1").prop("checked", true);
			} else if(j[0].otu == "jr_div") {
				$("input[name='otu1'][value='jr_div']").prop('checked', true);
				$("#check_ncc1").prop("checked", true);
			} else if(j[0].otu == "sr_div") {
				$("input[name='otu1'][value='sr_div']").prop('checked', true);
				$("#check_ncc1").prop("checked", true);
			} else {
				$("input[name='otu1'][value='nill']").prop('checked', true);
				$("#check_ncc1").prop("checked", true);
			}
		}
	});
}



function getmarital_status() {
	var a =curr_marital_status;
	if(a == "9" || a == "12" || a == "11" || a == "13") {
		$("#fill_marraige_div").hide();
		$("#fill_divorce_div").show();
	}
	if(a == "8") {
		$("#fill_marraige_div").show();
		$("#fill_divorce_div").show();
	}
	if(a == "10" || a == "0") {
		$("#fill_marraige_div").hide();
		$("#fill_divorce_div").hide();
	}
	if(a == "13") {
		$("#fill_marraige_div").show();
		$(".sepratedSpouseClass").show();
		$("#spouse_quali_radioDiv").hide();
		$("#spouse_Qualifications").hide();
		
		
	}
	else{
		$(".sepratedSpouseClass").hide();
	}
}
/* $("#marital_status").change(function(){
 var a = this.value;

 if(a == "9" || a == "12" || a == "11" || a == "13"){

 $("#marrried_table11").show(); 
 $("th#th_divorce").show(); 
 $("td#td_divorce").show();
 $("#married_table").show();
 } 
 if(a == "8"){

 $("#marrried_table11").show(); 
 $("th#th_divorce").hide(); 
 $("td#td_divorce").hide(); 
 $("#married_table").show();
 } 
 if(a == "10"){

 $("#marrried_table11").hide(); 
 $("th#th_divorce").hide(); 
 $("td#td_divorce").hide(); 
 $("#married_table").hide();
 } 
 }); */




function fn_org_domicile_state() {
 var text = $("#org_domicile_state option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#org_domicile_state_other_id").show();
	}
	else{
		$("#org_domicile_state_other_id").hide();
		$("#org_domicile_state_other").val("");
	}	
	
	var state_id = $('select#org_domicile_state').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#org_domicile_district").html(options);
		fn_org_domicile_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}



function fn_org_domicile_Country() {
	
	var text = $("#org_domicile_Country option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#org_domicile_country_other_id").show();
	}
	else{
		$("#org_domicile_country_other_id").hide();
		$("#org_domicile_Country_other").val("");
		$("#org_domicile_tehsil_other_id").hide();

	}	
	
	var contry_id = $('select#org_domicile_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#org_domicile_state").html(options);
		fn_org_domicile_state();
		fn_org_domicile_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_org_domicile_Country_ready() {
	
	var text = $("#org_domicile_Country option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#org_domicile_country_other_id").show();
	}
	else{
		$("#org_domicile_country_other_id").hide();
		$("#org_domicile_Country_other").val("");
		$("#org_domicile_tehsil_other_id").hide();

	}	
	
	var contry_id = $('select#org_domicile_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#org_domicile_state").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {});
}


function fn_org_domicile_district() {
	
	 var text = $("#org_domicile_district option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#org_domicile_district_other_id").show();
		}
		else{
			$("#org_domicile_district_other_id").hide();
			$("#org_domicile_district_other").val("");
		}
	
	var Dist_id = $('select#org_domicile_district').val();
	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
		Dist_id: Dist_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#org_domicile_tehsil").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_org_domicile_tehsil(){
	 var text = $("#org_domicile_tehsil option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#org_domicile_tehsil_other_id").show();
		}
		else{
			$("#org_domicile_tehsil_other_id").hide();
			$("#org_domicile_tehsil_other").val("");
		}
}
$("#Stn_HQ_sus_no").keypress(function(){
	var sus_no = this.value;
	var susNoAuto=$("#Stn_HQ_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "HQ_getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Station HQ SUS No.");
	        	  document.getElementById("Stn_HQ_unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#Stn_HQ_unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

$("#Stn_HQ_unit_name").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#Stn_HQ_unit_name");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getallUnitNameListStnHQ?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        	   	          
					response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Station HQ Unit Name.");
		        	  document.getElementById("Stn_HQ_sus_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	 var unit_name = ui.item.value;
		    
			            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#Stn_HQ_sus_no").val(dec(enc,data[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
		      } 	     
		}); 			
});


//PERSONAL PAGE
function fn_country_birth() {
	debugger
var text = $("#country_birth option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#country_birth_other_hid").show();
	}
	else{
		$("#country_birth_other_hid").hide();
		$("#nationality_other").val('');
		$("#district_birth_other_hid").hide();
	}
	var contry_id = $('select#country_birth').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#state_birth").html(options);
		fn_state_birth();
		//fn_district_birth();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_state_birth() {
var text = $("#state_birth option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#state_birth_other_hid").show();
	}
	else{
		$("#state_birth_other_hid").hide();
		$("#country_birth_other").val('');
	}
	var state_id = $('select#state_birth').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#district_birth").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}
 function fn_district_birth() {
	 var text = $("#district_birth option:selected").text();
		if(text.toUpperCase() == "OTHERS"){
			$("#district_birth_other_hid").show();
		}
		else{
			$("#district_birth_other_hid").hide();
			$("#district_birth_other").val('');
		}
 	}
 function fn_nationality() {
	 var text = $("#nationality option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#nationality_other_hid").show();
		}
		else{
			$("#nationality_other_hid").hide();
			$("#nationality_other").val('');
		}
 	}
 function fn_mother_tongue() {
	 var text = $("#mother_tongue option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#mother_tongue_other_hid").show();
		}
		else{
			$("#mother_tongue_other_hid").hide();
			$("#mother_tongue_other").val('');
		}
 	}
 function fn_religion() {
	 var text = $("#religion option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#religion_other_hid").show();
		}
		else{
			$("#religion_other_hid").hide();
			$("#religion_other").val('');
		}
 	}



function calcDate22(f) {
	if($('#from_dt' + f).val().trim() != "" && $('#from_dt' + f).val() != "DD/MM/YYYY" && $('#to_dt' + f).val().trim() != "" && $('#to_dt' + f).val() != "DD/MM/YYYY") {
		if(getformatedDate($('#from_dt' + f).val()) > getformatedDate($('#to_dt' + f).val())) {
			alert("Invalid Date Range");
			$('#period' + f).val('');
			$("input#to_dt" + f).focus();
			return false;
		}
		var startDate = getformatedDate($('#from_dt' + f).val());
		var endDate = getformatedDate($('#to_dt' + f).val());
		
		
		 var startYear = startDate.getFullYear();
		    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
		    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		    var yearDiff = endDate.getFullYear() - startYear;
		    var monthDiff = endDate.getMonth() - startDate.getMonth();
		    if (monthDiff < 0) {
		        yearDiff--;
		        monthDiff += 12;
		    }
		    var dayDiff = endDate.getDate() - startDate.getDate();
		    if (dayDiff < 0) {
		        if (monthDiff > 0) {
		            monthDiff--;
		        } else {
		            yearDiff--;
		            monthDiff = 11;
		        }
		        dayDiff += daysInMonth[startDate.getMonth()];
		    }
	    
		var message;
		   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
			message = "0 years 0 months 1 days";
		} else {
			message = yearDiff + " years "
			message += monthDiff + " months "
			message += dayDiff + " days"
		}
		$('#period' + f).val(message);
	}
}

function myFunction() {
	var popup = document.getElementById("myPopup");
	popup.classList.toggle("show");
}

function onlyAlphabets(e, t) {
	return(e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;
}

function fn_getUnitnamefromSus(sus_no, e) {
	$.post("getTargetUnitNameList?" + key + "=" + value, {
		sus_no: sus_no
	}).done(function(data) {
		var l = data.length - 1;
		var enc = data[l].substring(0, 16);
		e.value = dec(enc, data[0]);
	}).fail(function(xhr, textStatus, errorThrown) {});
}
///////////allergic///////////////
aller = 1;

function allergicformopen(q) {
	if(q != 0) {
		$("#allergic_id_add" + aller).hide();
		$("#allergic_id_remove" + aller).hide();
	}
	aller = q + 1;
	$("input#allergic_count").val(aller);
	$("table#allergic_table").append('<tr align="center" id="tr_allergic' + aller + '">'
	+'<td style="width: 2%;">' + aller + '</td>' 
	+ '<td><input type="text" id="medicine' + aller + '" name="medicine' + aller + '" class="form-control autocomplete" autocomplete="off" maxlength="50" >  </td>' 
	+ '<td style="display: none;"><input type="text" id="allergic_ch_id' + aller + '" name="allergic_ch_id' + aller + '" value="0" maxlength="50"  class="form-control autocomplete" autocomplete="off"></td>'
	+ '<td class="hide-action" style="width: 10%;"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_id_add' + aller + '" onclick="allergicformopen(' + aller + ');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "allergic_id_remove' + aller + '" onclick="allergicformopen_re(' + aller + ');"><i class="fa fa-trash"></i></a></td>' + '</tr>');
	if(aller == 1) {
		$("#allergic_id_remove" + aller).hide();
	}
}

function allergicformopen_re(R) {
	$("tr#tr_allergic" + R).remove();
	aller = aller - 1
	$("input#allergic_count").val(aller);
	$("#allergic_id_add" + aller).show();
	if(aller != 1) {
		$("#allergic_id_remove" + aller).show();
	}
}

function getallergicDetails() {
	var comm_id = $('#comm_id').val();
	var i = 0;
	$.post('getModifyallergicData?' + key + "=" + value, {
		comm_id: comm_id
	}, function(j) {
		var v = j.length;
		$("#allergictbody").empty();
		if(v != 0) {
			for(i; i < v; i++) {
				x = i + 1;
				$("table#allergic_table").append('<tr align="center" id="tr_allergic' + x + '">' 
				+'<td style="width: 2%;">' + x + '</td>' + '<td><input type="text" id="medicine' + x + '" name="medicine' + x + '" value="' + j[i].medicine + '" class="form-control autocomplete" autocomplete="off" maxlength="50" >  </td>'
				+ '<td style="display: none;"><input type="text" id="allergic_ch_id' + x + '" name="allergic_ch_id' + x + '" value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>' 
				+ '<td class="hide-action" style="width: 10%;"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "allergic_id_add' + x + '" style="display:none;" onclick="allergicformopen(' + x + ');" ><i class="fa fa-plus"></i></a> </td>' + '</tr>');
			}
			aller = v;
			$("input#allergic_count").val(v);
			$("input#allergicOld_count").val(v);
			$("#allergic_id_add" + v).show();
			$("#allergic_radio1").prop("checked", true);
			$("#allergicToMedicineDiv").show();
		} else {
			allergicformopen(0);
			$("input#allergic_count").val('1');
			$("input#allergicOld_count").val('0');
		}
	});
}

function spouse_addressFn() {
	if($("#check_spouse_address").is(':checked')) $("#spouse_addressInnerdiv").show(); // checked
	else{
	    $("#spouse_addressInnerdiv").hide();
	    $("#spouse_addr_Country").val("0");
	    $("#spouse_addr_state").val("0");
	    $("#spouse_addr_district").val("0");
	    $("#spouse_addr_tehsil").val("0");
	    $("#spouse_addr_village").val("");
	    $("#spouse_addr_pin").val("");
	    $("#spouse_addr_rail").val("");
	    $("#spouse_addr_rural").prop('checked', false);
	    $("#spouse_addr_urban").prop('checked', false);
	    $("#spouse_addr_semi_urban").prop('checked', false);
	    $("#Stn_HQ_sus_no").val("");
	    $("#Stn_HQ_unit_name").val("");
	 	    
	}
}

function showCheckboxes() {
	var checkboxes = document.getElementById("checkboxes");
	$("#checkboxes").toggle();
	$("#quali_sub_search").val('');
	$('.quali_subjectlist').each(function() {
		$(this).show()
	})
}

function allergic_radioFn() {
	var a = $('input:radio[name=allergic_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#allergicToMedicineDiv").show();
	} else {
		$("#allergicToMedicineDiv").hide();
	}
}


function showCheckboxesSpouse() {
	
	$("#spouse_checkboxes").toggle();
	$("#spouse_searchSubject").val('');
	$('.spouse_subjectlist').each(function() {
		$(this).show()
	})
}




var classification = '1';
$("#mad_classification").change(function() {
	classification = this.value;
	
});

function onShapeValueChange(e, label) {
// 	if(e.value == "1") {
// 		$("#" + label + "_from_date1").val("");
// 		$("#" + label + "_to_date1").val("");
// 		$("#" + label + "_from_date1").datepicker("option", "disabled", true);
// 		$("#" + label + "_to_date1").datepicker("option", "disabled", true);
		
// 	} else {
// 		$("#" + label + "_from_date1").datepicker("option", "disabled", false);
// 		$("#" + label + "_to_date1").datepicker("option", "disabled", false);
// 	}
}

function oncheck_1bx() {
	if($("#check_1bx").is(':checked')) {
		$("#shape1bxdiv").show();
		$("#shapediv").hide();
	} else {
		$("#shape1bxdiv").hide();
		
		$("#shapediv").show();
	}
	 colAdj("s_madtable");
	 colAdj("h_madtable");
	 colAdj("a_madtable");
	 colAdj("p_madtable");
	 colAdj("e_madtable");
	 colAdj("c_cmadtable");
	 colAdj("c_omadtable");
	 colAdj("c_pmadtable");
	 colAdj("c_emadtable");
}

function onCopeChangebt(e, cope, ind) {
	if(ind == 1) {
		if(e.value == 0 || (cope != 4 && cope != 2 && e.value == 1)) {
			$('.CopaddbtClass' + cope + ind).hide();
			$('.CopaddbtClass' + cope).hide();
		} else {
			$('.CopaddbtClass' + cope + ind).show();
			$('.CopaddbtClass' + cope).show();
		}
	}
}

function onCCopeChange(e, ind) {
	if(e.value != '2 [c]') {
		$('.cCopClass' + ind).hide();
		$('.cCopClass').hide();
	} else {
		$('.cCopClass' + ind).show();
		$('.cCopClass').show();
	}
	var styleC = $(".cCopClass").css("display");
	for(var i = 1; i <= cCope; i++) {
		var st = $("#c_cvalue" + i).val();
		if(st == '2 [c]') {
			$('.cCopClass').show();
			$('#c_cother' + i).show();
		} else if(i == cCope && styleC == "none") {
			$('#c_cother' + i).show();
			$('.cCopClass' + i).hide();
		}
		styleC = $(".cCopClass").css("display");
		if(i == cCope && styleC != "none") {
			if(st == '2 [c]') {
				$('#c_cother' + i).show();
				$('.cCopClass' + i).show();
			} else {
				$('#c_cother' + i).hide();
				$('.cCopClass' + i).show();
			}
		}
	}
	if(styleC == "none") {
		for(var i = 1; i <= cCope; i++) {
			$('#c_cother' + i).show();
			$('.cCopClass' + i).hide();
		}
	}
}

function onECopeChange(e, ind) {
	if(e.value == '1') {
		$('.eCopClass' + ind).show();
		$('.eCopClass').show();
	} else {
		$('.eCopClass' + ind).hide();
		$('.eCopClass').hide();
		$('.eCop_otherClass' + ind).hide();
		$('.eCop_otherClass').hide();
		$('#c_esubvalue' + ind).val(0);
	}
	var styleC = $(".eCopClass").css("display");
	for(var i = 1; i <= eCope; i++) {
		var st = $("#c_evalue" + i).val();
		if(st == '1') {
			$('.eCopClass').show();
			$('#c_esubvalue' + i).show();
		} else if(i == cCope && styleC == "none") {
			$('#c_esubvalue' + i).show();
			$('.eCopClass' + i).hide();
		}
		styleC = $(".eCopClass").css("display");
		if(i == eCope && styleC != "none") {
			if(st == '1') {
				$('#c_esubvalue' + i).show();
				$('.eCopClass' + i).show();
			} else {
				$('#c_esubvalue' + i).hide();
				$('.eCopClass' + i).show();
			}
		}
	}
	if(styleC == "none") {
		for(var i = 1; i <= eCope; i++) {
			$('#c_esubvalue' + i).show();
			$('.eCopClass' + i).hide();
		}
	}
	onECopeSubChange(e, ind);
}

function onECopeSubChange(e, ind) {
	if(e.value == 'k') {
		$('.eCop_otherClass' + ind).show();
		$('.eCop_otherClass').show();
	} else {
		$('.eCop_otherClass' + ind).hide();
		$('.eCop_otherClass').hide();
	}
	var styleC = $(".eCop_otherClass").css("display");
	for(var i = 1; i <= eCope; i++) {
		var st = $("#c_esubvalue" + i).val();
		if(st == 'k') {
			$('.eCop_otherClass').show();
			$('#c_esubvalueother' + i).show();
		} else if(i == cCope && styleC == "none") {
			$('#c_esubvalueother' + i).show();
			$('.eCop_otherClass' + i).hide();
		}
		styleC = $(".eCop_otherClass").css("display");
		if(i == eCope && styleC != "none") {
			if(st == 'k') {
				$('#c_esubvalueother' + i).show();
				$('.eCop_otherClass' + i).show();
			} else {
				$('#c_esubvalueother' + i).hide();
				$('.eCop_otherClass' + i).show();
			}
		}
	}
	if(styleC == "none") {
		for(var i = 1; i <= eCope; i++) {
			$('#c_esubvalueother' + i).show();
			$('.eCop_otherClass' + i).hide();
		}
	}
}


function statusChange(Shape, position, Shape_status) {
	//26-01-1994
// 	if(Shape_status == 1 || Shape_status == 0) {
// 		$('.diagnosisClass' + Shape + position).hide();
// 		$('.diagnosisClass' + Shape).hide();
// 		$('.addbtClass' + Shape + position).hide();
// 		$('.addbtClass' + Shape).hide();
// 	} else {
// 		$('.diagnosisClass' + Shape + position).show();
// 		$('.diagnosisClass' + Shape).show();
// 		$('.addbtClass' + Shape + position).show();
// 		$('.addbtClass' + Shape).show();
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
	
	
	$.post('getShapevalueUrl?' + key + "=" + value, {
		Shape_status: Shape_status
	}, function(j) {
		var options = '<option value="' + "0" + '">' + "--Select--" + '</option>';
		var length = j.length;
		if(length != 0) {
			for(var i = 0; i < length; i++) {
				options += '<option value="' + j[i][1] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			 if(Shape == 1) {
				    if(Shape_status == 1){
				    	$("select#sShape_value" + position).html(options);
						$("select#sShape_value" + position).val(1);
					}else{
				        $("select#sShape_value" + position).html(options);
					}
			}
			 else if(Shape == 2) {
				    if(Shape_status == 1){
				        $("select#hShape_value" + position).html(options);
						$("select#hShape_value" + position).val(1);
					}else{
				        $("select#hShape_value" + position).html(options);
					}
			} else if(Shape == 3) {
				   if(Shape_status == 1){
					     $("select#aShape_value" + position).html(options);
						$("select#aShape_value" + position).val(1);
					}else{
				        $("select#aShape_value" + position).html(options);
					}
			} else if(Shape == 4) {
				   if(Shape_status == 1){
					   $("select#pShape_value" + position).html(options);
						$("select#pShape_value" + position).val(1);
					}else{
				        $("select#pShape_value" + position).html(options);
					}
			} else if(Shape == 5) {
				  if(Shape_status == 1){
					  $("select#eShape_value" + position).html(options);
						$("select#eShape_value" + position).val(1);
					}else{
				        $("select#eShape_value" + position).html(options);
					}
			}
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});

}

function AutoCompleteDiagnosis(ele) {
	var code = ele.value;
	var susNoAuto = $("#" + ele.id);
	susNoAuto.autocomplete({
		source: function(request, response) {
			$.ajax({
				type: 'POST',
				url: "getMNHDistinctICDList?" + key + "=" + value,
				data: {
					a: code,
					b: "ALL"
				},
				success: function(data) {
					var susval = [];
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					for(var i = 0; i < data.length; i++) {
						susval.push(dec(enc, data[i]));
					}
					response(susval);
				}
			});
		},
		minLength: 1,
		autoFill: true,
		change: function(event, ui) {
			if(ui.item) {
				return true;
			} else {
				//alert("Please Enter Approved Diagnosis ");
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select: function(event, ui) {}
	});
}
sShape = 1;

function sShape_add_fn(q) {
	if($('#sShape_add' + q).length) {
		$("#sShape_add" + q).hide();
		$("#sShape_remove" + q).hide();
	}
	sShape = sShape + 1;
	$("input#sShape_count").val(sShape);
	$("table#s_madtable").append('<tr id="tr_sShape' + sShape + '">' 
			+ '<td  style="width: 2%;">' + sShape + '</td>' + '<td>' 
			+ '<select name="s_status' + sShape + '" id="s_status' + sShape + '" ' + '	class="form-control"  onchange="statusChange(1,' + sShape + ',this.options[this.selectedIndex].value)">'
		
		+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' 
		+ '  </td>' + '   <td style="">'
		+ '<select name="sShape_value' + sShape + '" id="sShape_value' + sShape + '" ' + '		class="form-control">' + '		</select>	</td>'
		
		+ '<td style="""> ' + ' <input type="text" name="s_from_date' + sShape + '" id="s_from_date' + sShape + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '<td style="""> ' + ' <input type="text" name="s_to_date' + sShape + '" id="s_to_date' + sShape + '" maxlength="10" disabled onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '  <td style="visibility:hidden; "  class="diagnosisClass1' + sShape + '">' + ' <input type="text" name="_diagnosis_code1' + sShape + '" id="_diagnosis_code1' + sShape + '" class="form-control-sm form-control" autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" maxlength="255" >	' + '   </td>'
		
		+ ' <td style="display:none;"><input type="text" id="sShape_ch_id' + sShape + '" name="sShape_ch_id' + sShape + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add' + sShape + '" onclick="sShape_add_fn(' + sShape + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove' + sShape + '" onclick="sShaperemove_fn(' + sShape + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	datepicketDate('s_from_date' + sShape);
	datepicketDate('s_to_date' + sShape);
	$("#s_to_date" + sShape).datepicker("option", "maxDate", null);
	statusChange(1, sShape, $("#s_status" + sShape).val());
// 	if(q != 0) {
// 		var preShape = sShape - 1;
// 		$("#sShape_value" + preShape + " > option").clone().appendTo("#sShape_value" + sShape);
// 		$("#s_status" + sShape).val($("#s_status" + preShape).val());
// 		$("#sShape_value" + sShape).val($("#sShape_value" + preShape).val());
// 		statusChange(1, sShape, $("#s_status" + preShape).val());
// 		$("#s_status" + sShape + " option[value='1']").remove();
// 		$("#s_status" + preShape + " option[value='1']").remove();
// 		$("#s_status" + preShape + " option[value='0']").remove();
// 	}
	
}

function sShaperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_sShape" + R).remove();
		R = R - 1;
		$("#sShape_add" + R).show();
		$("#sShape_remove" + R).show();
		$("input#sShape_count").val(R);
		sShape = sShape - 1;
// 		if(sShape == 1) {
// 			var s_val = $("#s_status" + sShape).val();
// 			var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 			var lis2 = $("#s_status" + sShape).html();
// 			$('#s_status' + sShape).empty().append('<option value="0">--Select--</option>' + lis1 + lis2);
// 			$("#s_status" + sShape).val(s_val);
// 		}
	}
}
hShape = 1;

function hShape_add_fn(q) {
	if($('#hShape_add' + q).length) {
		$("#hShape_add" + q).hide();
		$("#hShape_remove" + q).hide();
	}
	hShape = hShape + 1;
	$("input#hShape_count").val(hShape);
	$("table#h_madtable").append('<tr id="tr_hShape' + hShape + '">' 
			+ '<td  style="width: 2%;">' + hShape + '</td>' + '<td>' + '<select name="h_status' + hShape + '" id="h_status' + hShape + '" ' + '	class="form-control"  onchange="statusChange(2,' + hShape + ',this.options[this.selectedIndex].value)">'
		
		+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' 
		+ '   <td style="">' + '<select name="hShape_value' + hShape + '" id="hShape_value' + hShape + '" ' + '		class="form-control">' + '		</select>	</td>'
		+ '<td style="""> ' + ' <input type="text" name="h_from_date' + hShape + '" id="h_from_date' + hShape + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' 
		+ '</td>' + '<td style="""> ' + ' <input type="text" name="h_to_date' + hShape + '" id="h_to_date' + hShape + '" maxlength="10" disabled onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
		+ '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		+ '  <td style="visibility:hidden; "  class="diagnosisClass2' + hShape + '">' + ' <input type="text" name="_diagnosis_code2' + hShape + '" id="_diagnosis_code2' + hShape + '" class="form-control" autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" maxlength="255" >	' 
		+ '   </td>'
		+ ' <td style="display:none;"><input type="text" id="hShape_ch_id' + hShape + '" name="hShape_ch_id' + hShape + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add' + hShape + '" onclick="hShape_add_fn(' + hShape + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "hShape_remove' + hShape + '" onclick="hShaperemove_fn(' + hShape + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	datepicketDate('h_from_date' + hShape);
	datepicketDate('h_to_date' + hShape);
	$("#h_to_date" + hShape).datepicker("option", "maxDate", null);
	statusChange(2, hShape, $("#h_status" + hShape).val());
// 	if(q != 0) {
// 		var preShape = hShape - 1;
// 		$("#hShape_value" + preShape + " > option").clone().appendTo("#hShape_value" + hShape);
// 		$("#h_status" + hShape).val($("#h_status" + preShape).val());
// 		$("#hShape_value" + hShape).val($("#hShape_value" + preShape).val());
// 		statusChange(2, hShape, $("#h_status" + preShape).val());
// 		$("#h_status" + hShape + " option[value='1']").remove();
// 		$("#h_status" + preShape + " option[value='1']").remove();
// 		$("#h_status" + preShape + " option[value='0']").remove();
// 	}

}

function hShaperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_hShape" + R).remove();
		R = R - 1;
		$("#hShape_add" + R).show();
		$("#hShape_remove" + R).show();
		$("input#hShape_count").val(R);
		hShape = hShape - 1;
// 		if(hShape == 1) {
// 			var s_val = $("#h_status" + hShape).val();
// 			var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 			var lis2 = $("#h_status" + hShape).html();
// 			$('#h_status' + hShape).empty().append('<option value="0">--Select--</option>' + lis1 + lis2);
// 			$("#h_status" + hShape).val(s_val);
// 		}
	}
}
aShape = 1;

function aShape_add_fn(q) {
	if($('#aShape_add' + q).length) {
		$("#aShape_add" + q).hide();
		$("#aShape_remove" + q).hide();
	}
	aShape = aShape + 1;
	$("input#aShape_count").val(aShape);
	$("table#a_madtable").append('<tr id="tr_aShape' + aShape + '">' 
			+ '<td  style="width: 2%;">' + aShape + '</td>' 
			+ '<td>' + '<select name="a_status' + aShape + '" id="a_status' + aShape + '" ' + '	class="form-control"  onchange="statusChange(3,' + aShape + ',this.options[this.selectedIndex].value)">'
		
		+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' 
		+ '		</select>' + '  </td>' 
		+ '   <td style="">' + '<select name="aShape_value' + aShape + '" id="aShape_value' + aShape + '" ' + '		class="form-control">' + '		</select>	</td>'
		
		+ '<td style="""> ' + ' <input type="text" name="a_from_date' + aShape + '" id="a_from_date' + aShape + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '<td style="""> ' + ' <input type="text" name="a_to_date' + aShape + '" id="a_to_date' + aShape + '" maxlength="10" disabled onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
		+ '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '  <td style="visibility:hidden; "  class="diagnosisClass3' + aShape + '">' + ' <input type="text" name="_diagnosis_code3' + aShape + '" id="_diagnosis_code3' + aShape + '" class="form-control-sm form-control" autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);" maxlength="255" >	' + '   </td>'
		
		+ ' <td style="display:none;"><input type="text" id="aShape_ch_id' + aShape + '" name="aShape_ch_id' + aShape + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add' + aShape + '" onclick="aShape_add_fn(' + aShape + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "aShape_remove' + aShape + '" onclick="aShaperemove_fn(' + aShape + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	datepicketDate('a_from_date' + aShape);
	datepicketDate('a_to_date' + aShape);
	$("#a_to_date" + aShape).datepicker("option", "maxDate", null);
	statusChange(3, aShape, $("#a_status" + aShape).val());
// 	if(q != 0) {
// 		var preShape = aShape - 1;
// 		$("#aShape_value" + preShape + " > option").clone().appendTo("#aShape_value" + aShape);
// 		$("#a_status" + aShape).val($("#a_status" + preShape).val());
// 		$("#aShape_value" + aShape).val($("#aShape_value" + preShape).val());
// 		statusChange(3, aShape, $("#a_status" + preShape).val());
// 		$("#a_status" + aShape + " option[value='1']").remove();
// 		$("#a_status" + preShape + " option[value='1']").remove();
// 		$("#a_status" + preShape + " option[value='0']").remove();
// 	}
	
}

function aShaperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_aShape" + R).remove();
		R = R - 1;
		$("#aShape_add" + R).show();
		$("#aShape_remove" + R).show();
		$("input#aShape_count").val(R);
		aShape = aShape - 1;
// 		if(aShape == 1) {
// 			var s_val = $("#a_status" + aShape).val();
// 			var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 			var lis2 = $("#a_status" + aShape).html();
// 			$('#a_status' + aShape).empty().append('<option value="0">--Select--</option>' + lis1 + lis2);
// 			$("#a_status" + aShape).val(s_val);
// 		}
	}
}
pShape = 1;

function pShape_add_fn(q) {
	if($('#pShape_add' + q).length) {
		$("#pShape_add" + q).hide();
		$("#pShape_remove" + q).hide();
	}
	pShape = pShape + 1;
	$("input#pShape_count").val(pShape);
	$("table#p_madtable").append('<tr id="tr_pShape' + pShape + '">' 
			+ '<td  style="width: 2%;">' + pShape + '</td>'
			+ '<td>' + '<select name="p_status' + pShape + '" id="p_status' + pShape + '" ' + '	class="form-control"  onchange="statusChange(4,' + pShape + ',this.options[this.selectedIndex].value)">'
		
		+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' 
		+ '   <td style="">' + '<select name="pShape_value' + pShape + '" id="pShape_value' + pShape + '" ' + '		class="form-control">' + '		</select>	</td>'
		+ '<td style="""> ' + ' <input type="text" name="p_from_date' + pShape + '" id="p_from_date' + pShape + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
		+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '<td style="""> ' + ' <input type="text" name="p_to_date' + pShape + '" id="p_to_date' + pShape + '" maxlength="10" disabled onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '  <td style="visibility:hidden; "  class="diagnosisClass4' + pShape + '">' + ' <input type="text" name="_diagnosis_code4' + pShape + '" id="_diagnosis_code4' + pShape + '" class="form-control-sm form-control" autocomplete="off" maxlength="255" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>'
		
		+ ' <td style="display:none;"><input type="text" id="pShape_ch_id' + pShape + '" name="pShape_ch_id' + pShape + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add' + pShape + '" onclick="pShape_add_fn(' + pShape + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pShape_remove' + pShape + '" onclick="pShaperemove_fn(' + pShape + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	datepicketDate('p_from_date' + pShape);
	datepicketDate('p_to_date' + pShape);
	$("#p_to_date" + pShape).datepicker("option", "maxDate", null);
	statusChange(4, pShape, $("#p_status" + pShape).val());
// 	if(q != 0) {
// 		var preShape = pShape - 1;
// 		$("#pShape_value" + preShape + " > option").clone().appendTo("#pShape_value" + pShape);
// 		$("#p_status" + pShape).val($("#p_status" + preShape).val());
// 		$("#pShape_value" + pShape).val($("#pShape_value" + preShape).val());
// 		statusChange(4, pShape, $("#p_status" + preShape).val());
// 		$("#p_status" + pShape + " option[value='1']").remove();
// 		$("#p_status" + preShape + " option[value='1']").remove();
// 		$("#p_status" + preShape + " option[value='0']").remove();
// 	}
	
}

function pShaperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_pShape" + R).remove();
		R = R - 1;
		$("#pShape_add" + R).show();
		$("#pShape_remove" + R).show();
		$("input#pShape_count").val(R);
		pShape = pShape - 1;
// 		if(pShape == 1) {
// 			var s_val = $("#p_status" + pShape).val();
// 			var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 			var lis2 = $("#p_status" + pShape).html();
// 			$('#p_status' + pShape).empty().append('<option value="0">--Select--</option>' + lis1 + lis2);
// 			$("#p_status" + pShape).val(s_val);
// 		}
	}
}
eShape = 1;

function eShape_add_fn(q) {
	if($('#eShape_add' + q).length) {
		$("#eShape_add" + q).hide();
		$("#eShape_remove" + q).hide();
	}
	eShape = eShape + 1;
	$("input#eShape_count").val(eShape);
	$("table#e_madtable").append('<tr id="tr_eShape' + eShape + '">' + '<td  style="width: 2%;">' + eShape + '</td>' 
	+ '<td>' + '<select name="e_status' + eShape + '" id="e_status' + eShape + '" ' + '	class="form-control"  onchange="statusChange(5,' + eShape + ',this.options[this.selectedIndex].value)">'
		
		+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' 
		+ '   <td style="">' + '<select name="eShape_value' + eShape + '" id="eShape_value' + eShape + '" ' + '		class="form-control">' + '		</select>	</td>'
		
		+ '<td style="""> ' + ' <input type="text" name="e_from_date' + eShape + '" id="e_from_date' + eShape + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
		+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		+ '<td style="""> ' + ' <input type="text" name="e_to_date' + eShape + '" id="e_to_date' + eShape + '" maxlength="10" disabled onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
		+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '  <td style="visibility:hidden; "  class="diagnosisClass5' + eShape + '">' + ' <input type="text" name="_diagnosis_code5' + eShape + '" id="_diagnosis_code5' + eShape + '" class="form-control" autocomplete="off" ' 
		+ '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>'
		+ ' <td style="display:none;"><input type="text" id="eShape_ch_id' + eShape + '" name="eShape_ch_id' + eShape + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >'
        + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add' + eShape + '" onclick="eShape_add_fn(' + eShape + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eShape_remove' + eShape + '" onclick="eShaperemove_fn(' + eShape + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	datepicketDate('e_from_date' + eShape);
	datepicketDate('e_to_date' + eShape);
	$("#e_to_date" + eShape).datepicker("option", "maxDate", null);
	statusChange(5, eShape, $("#e_status" + eShape).val());
// 	if(q != 0) {
// 		var preShape = eShape - 1;
// 		$("#eShape_value" + preShape + " > option").clone().appendTo("#eShape_value" + eShape);
// 		$("#e_status" + eShape).val($("#e_status" + preShape).val());
// 		$("#eShape_value" + eShape).val($("#eShape_value" + preShape).val());
// 		statusChange(5, eShape, $("#e_status" + preShape).val());
// 		$("#e_status" + eShape + " option[value='1']").remove();
// 		$("#e_status" + preShape + " option[value='1']").remove();
// 		$("#e_status" + preShape + " option[value='0']").remove();
// 	}

}

function eShaperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_eShape" + R).remove();
		R = R - 1;
		$("#eShape_add" + R).show();
		$("#eShape_remove" + R).show();
		$("input#eShape_count").val(R);
		eShape = eShape - 1;
// 		if(eShape == 1) {
// 			var s_val = $("#e_status" + eShape).val();
// 			var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 			var lis2 = $("#e_status" + eShape).html();
// 			$('#e_status' + eShape).empty().append('<option value="0">--Select--</option>' + lis1 + lis2);
// 			$("#e_status" + eShape).val(s_val);
// 		}
	}
}
cCope = 1;

function cCope_add_fn(q) {
	if($('#cCope_add' + q).length) {
		$("#cCope_add" + q).hide();
		$("#cCope_remove" + q).hide();
	}
	cCope = cCope + 1;
	$("input#cCope_count").val(cCope);
	$("table#c_cmadtable").append('<tr id="tr_cCope' + cCope + '">' + '<td  style="width: 2%;">' + cCope + '</td>' 
	+ '<td style="width: 70%;">' + '<select name="c_cvalue' + cCope + '" id="c_cvalue' + cCope + '" ' + '	class="form-control" onchange="onCCopeChange(this,' + cCope + ')">'
		
		+ '	<c:forEach var="item" items="${getcCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>'
		+ '<td style="display:none;" class="cCopClass' + cCope + '" >' + '<input type="text" name="c_cother' + cCope + '" id="c_cother' + cCope + '" class="form-control" autocomplete="off" >	' + ' </td>'
		+ ' <td style="display:none;"><input type="text" id="cCope_ch_id' + cCope + '" name="cCope_ch_id' + cCope + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' 
		+ '<td style="width: 28%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add' + cCope + '" onclick="cCope_add_fn(' + cCope + ');" ><i class="fa fa-plus"></i></a>' 
		+ '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "cCope_remove' + cCope + '" onclick="cCoperemove_fn(' + cCope + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	var thisT = document.getElementById('c_cvalue' + cCope)
	onCCopeChange(thisT, cCope);
	$("#c_cvalue" + cCope + " option[value='0']").remove();
	$("#c_cvalue" + cCope + " option[value='1']").remove();
	$("#c_cvalue" + (cCope - 1) + " option[value='0']").remove();
	$("#c_cvalue" + (cCope - 1) + " option[value='1']").remove();
}

function cCoperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_cCope" + R).remove();
		R = R - 1;
		$("#cCope_add" + R).show();
		$("#cCope_remove" + R).show();
		$("input#cCope_count").val(R);
		cCope = cCope - 1;
		if(cCope == 1) {
			var s_val = $("#c_cvalue" + cCope).val();
			var lis1 = '<option value="${getcCopeList[0][1]}" name="${getcCopeList[0][0]}">${getcCopeList[0][0]}</option>'
			var lis2 = '<option value="${getcCopeList[1][1]}" name="${getcCopeList[1][0]}">${getcCopeList[1][0]}</option>'
			var lis3 = $("#c_cvalue" + cCope).html();
			$('#c_cvalue' + cCope).empty().append("" + lis1 + lis2 + lis3);
			$("#c_cvalue" + cCope).val(s_val);
		}
		var thisT = document.getElementById('c_cvalue' + cCope)
		onCCopeChange(thisT, cCope);
	}
}
oCope = 1;

function oCope_add_fn(q) {
	if($('#oCope_add' + q).length) {
		$("#oCope_add" + q).hide();
		$("#oCope_remove" + q).hide();
	}
	oCope = oCope + 1;
	$("input#oCope_count").val(oCope);
	$("table#c_omadtable").append('<tr id="tr_oCope' + oCope + '">' + '<td  style="width: 2%;">' + oCope + '</td>' 
	+ '<td>' + '<select name="c_ovalue' + oCope + '" id="c_ovalue' + oCope + '" ' + '	class="form-control"  >'
		
		+ '	<c:forEach var="item" items="${getoCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>'
		
		
		
		
		+ ' <td style="display:none;"><input type="text" id="oCope_ch_id' + oCope + '" name="oCope_ch_id' + oCope + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add' + oCope + '" onclick="oCope_add_fn(' + oCope + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "oCope_remove' + oCope + '" onclick="oCoperemove_fn(' + oCope + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	$("#c_ovalue" + oCope + " option[value='0']").remove();
	$("#c_ovalue" + (oCope - 1) + " option[value='0']").remove();
}

function oCoperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_oCope" + R).remove();
		R = R - 1;
		$("#oCope_add" + R).show();
		$("#oCope_remove" + R).show();
		$("input#oCope_count").val(R);
		oCope = oCope - 1;
		if(oCope == 1) {
			var s_val = $("#c_ovalue" + oCope).val();
			var lis1 = '<option value="${getoCopeList[0][1]}" name="${getoCopeList[0][0]}">${getoCopeList[0][0]}</option>'
			var lis2 = $("#c_ovalue" + oCope).html();
			$('#c_ovalue' + oCope).empty().append("" + lis1 + lis2);
			$("#c_ovalue" + oCope).val(s_val);
		}
	}
}
pCope = 1;

function pCope_add_fn(q) {
	if($('#pCope_add' + q).length) {
		$("#pCope_add" + q).hide();
		$("#pCope_remove" + q).hide();
	}
	pCope = pCope + 1;
	$("input#pCope_count").val(pCope);
	$("table#c_pmadtable").append('<tr id="tr_pCope' + pCope + '">' + '<td  style="width: 2%;">' + pCope + '</td>' 
	+ '<td>' + '<select name="c_pvalue' + pCope + '" id="c_pvalue' + pCope + '" ' + '	class="form-control"  >'
		
		+ '	<c:forEach var="item" items="${getpCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>'
		
		
		
		
		+ ' <td style="display:none;"><input type="text" id="pCope_ch_id' + pCope + '" name="pCope_ch_id' + pCope + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add' + pCope + '" onclick="pCope_add_fn(' + pCope + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pCope_remove' + pCope + '" onclick="pCoperemove_fn(' + pCope + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	$("#c_pvalue" + pCope + " option[value='0']").remove();
	$("#c_pvalue" + pCope + " option[value='1']").remove();
	$("#c_pvalue" + (pCope - 1) + " option[value='0']").remove();
	$("#c_pvalue" + (pCope - 1) + " option[value='1']").remove();
}

function pCoperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_pCope" + R).remove();
		R = R - 1;
		$("#pCope_add" + R).show();
		$("#pCope_remove" + R).show();
		$("input#pCope_count").val(R);
		pCope = pCope - 1;
		if(pCope == 1) {
			var s_val = $("#c_pvalue" + pCope).val();
			var lis1 = '<option value="${getpCopeList[0][1]}" name="${getpCopeList[0][0]}">${getpCopeList[0][0]}</option>'
			var lis2 = '<option value="${getpCopeList[1][1]}" name="${getpCopeList[1][0]}">${getpCopeList[1][0]}</option>'
			var lis3 = $("#c_pvalue" + pCope).html();
			$('#c_pvalue' + pCope).empty().append("" + lis1 + lis2 + lis3);
			$("#c_pvalue" + pCope).val(s_val);
		}
	}
}
eCope = 1;

function eCope_add_fn(q) {
	if($('#eCope_add' + q).length) {
		$("#eCope_add" + q).hide();
		$("#eCope_remove" + q).hide();
	}
	eCope = eCope + 1;
	$("input#eCope_count").val(eCope);
	$("table#c_emadtable").append('<tr id="tr_eCope' + eCope + '">' + '<td  style="width: 2%;">' + eCope + '</td>' 
	+ '<td>' + '<select name="c_evalue' + eCope + '" id="c_evalue' + eCope + '" ' + '	class="form-control" onchange="onECopeChange(this,' + eCope + ')" >'
		
		+ '	<c:forEach var="item" items="${geteCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' 
		+ '  </td>' + '<td style="display:none;" class="eCopClass' + eCope + '">' + '<select name="c_esubvalue' + eCope + '" id="c_esubvalue' + eCope + '" onchange="onECopeSubChange(this,' + eCope + ')"' 
		+ '			class="form-control" >' + '			<option value="0">--Select--</option>' + '			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">' 
		+ '				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' 
		+ '			</c:forEach></select>   </td>' 
		+ '<td style="display:none;" class="eCop_otherClass' + eCope + '" >' + '<input type="text" name="c_esubvalueother' + eCope + '" id="c_esubvalueother' + eCope + '" class="form-control" autocomplete="off" maxlength="50"  >' + '	   </td>'
		
		
		
		
		+ ' <td style="display:none;"><input type="text" id="eCope_ch_id' + eCope + '" name="eCope_ch_id' + eCope + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" >' + '   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add' + eCope + '" onclick="eCope_add_fn(' + eCope + ');" ><i class="fa fa-plus"></i></a>' + '    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eCope_remove' + eCope + '" onclick="eCoperemove_fn(' + eCope + ');"><i class="fa fa-trash"></i></a> ' + '</td>' + '</tr>');
	$("#c_evalue" + eCope + " option[value='0']").remove();
	$("#c_evalue" + (eCope - 1) + " option[value='0']").remove();
	var thisT = document.getElementById('c_evalue' + eCope)
	onECopeChange(thisT, eCope);
}

function eCoperemove_fn(R) {
	var r = confirm("Are You Sure! You Want To Delete This Row");
	if(r) {
		$("tr#tr_eCope" + R).remove();
		R = R - 1;
		$("#eCope_add" + R).show();
		$("#eCope_remove" + R).show();
		$("input#eCope_count").val(R);
		eCope = eCope - 1;
		if(eCope == 1) {
			var s_val = $("#c_evalue" + eCope).val();
			var lis1 = '<option value="${geteCopeList[0][1]}" name="${geteCopeList[0][0]}">${geteCopeList[0][0]}</option>'
			var lis2 = $("#c_evalue" + eCope).html();
			$('#c_evalue' + eCope).empty().append("" + lis1 + lis2);
			$("#c_evalue" + eCope).val(s_val);
		}
		var thisT = document.getElementById('c_evalue' + eCope)
		onECopeChange(thisT, eCope);
	}
}



function getsShapeDetails() {
	var p_id = $('#census_id').val();
	var comm_id = $('#comm_id').val();
	var Shape = 'S'
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();

	
		$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
			p_id: p_id,
			Shape: Shape,
			comm_id :comm_id,
			meddtlfillin3008:meddtlfillin3008
		}, function(j) {
			var v = j.length;
			if(v != 0) {
				$('#s_madtable').show();
				$('#s_madtbody').empty();
				for(i; i < v; i++) {
					x = i + 1;
					var fd = "DD/MM/YYYY";
					var td = "DD/MM/YYYY";
					if(j[i][2] != null && j[i][2] != "") {
						fd = ParseDateColumncommission(j[i][2]);
					}
					if(j[i][3] != null && j[i][3] != "") {
						td = ParseDateColumncommission(j[i][3]);
					}
					$("table#s_madtable").append('<tr id="tr_sShape' + x + '">' + '<td class="sShape_srno" style="width: 2%;">' + x + '</td>' 
					+ '<td>' + '<select name="s_status' + x + '" id="s_status' + x + '" ' 
					+ '	class="form-control"  onchange="statusChange(1,' + x + ',this.options[this.selectedIndex].value)">'
					+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' 
					+ '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' 
					+ '	</c:forEach>' + '		</select>' + '  </td>' 
					+ '   <td style="">' + '<select name="sShape_value' + x + '" id="sShape_value' + x + '" ' 
					+ '		class="form-control" onchange="onShapeValueChange(this,\'s\')">' + '		</select>	</td>'
					+ '<td style=""> ' + ' <input type="text" name="s_from_date' + x + '" id="s_from_date' + x 
					+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
					+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
					+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' 
					+ fd + '">' + '</td>'
					+ '<td style=""> ' + ' <input type="text" name="s_to_date' + x + '" id="s_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
					+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
					+ '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '">' 
					+ '</td>' + '  <td style="visibility:hidden; "  class="diagnosisClass1' + x + '">' + ' <input type="text" name="_diagnosis_code1' + x + '" id="_diagnosis_code1' + x + '" value="' + j[i][4] + '" class="form-control" maxlength="255"  autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>' + ' <td style="display:none;"><input type="text" id="sShape_ch_id' + x + '" name="sShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="addbtClass1' + x + '">' + '   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add' + x + '" onclick="sShape_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				
					if(meddtlfillin3008 ==1){
						$('#s_status' + x).prop('disabled', true);
			            $('#sShape_value' + x).prop('disabled', true);
			            $('#s_from_date' + x).prop('disabled', true);
			            $('#s_to_date' + x).prop('disabled', true);
			            $('#_diagnosis_code1' + x).prop('disabled', true);
					}
					else{
						datepicketDate('s_from_date' + x);
						datepicketDate('s_to_date' + x);
					}
				
					$("#s_to_date" + x).datepicker("option", "maxDate", null);
					$("#s_status" + x).val(j[i][0]);
					$.ajaxSetup({
						async: false
					});
					statusChange(1, x, j[i][0]);
					$("#sShape_value" + x).val(j[i][1]);
//	 				if(x > 1) {
//	 					$("#s_status" + x + " option[value='1']").remove();
//	 					$("#s_status" + (x - 1) + " option[value='1']").remove();
//	 				} else {
						var thisT = document.getElementById('sShape_value' + x);
						onShapeValueChange(thisT, 's');
//	 				}
				}
				sShape = v;
				$("input#sShape_count").val(v);
				$("input#sShapeOld_count").val(v);
				if(meddtlfillin3008 ==1){
					$("#sShape_add" + v).hide();
				}else{
					$("#sShape_add" + v).show();
				}
				
				
				
				$('#mad_classification_count').val(j[i - 1][8]);
				if(j[i - 1][11] != null && j[i - 1][11] != "" && j[i - 1][12] != null && j[i - 1][12] != "" && j[i - 1][13] != null && j[i - 1][13] != "") {
					$("#check_1bx").prop("checked", true);
					oncheck_1bx();
					fd = ParseDateColumncommission(j[i - 1][11]);
					td = ParseDateColumncommission(j[i - 1][12]);
					$("#1bx_from_date").val(fd);
					$("#1bx_to_date").val(td);
					$("#1bx_diagnosis_code").val(j[i - 1][13]);
				}
				
				
			}
		});
		
	
	
}

function gethShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'H';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#h_madtable').show();
			$('#h_madtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var fd = "DD/MM/YYYY";
				var td = "DD/MM/YYYY";
				if(j[i][2] != null && j[i][2] != "") {
					fd = ParseDateColumncommission(j[i][2]);
// 					td = ParseDateColumncommission(j[i][3]);
				}
				if(j[i][3] != null && j[i][3] != "") {
					td = ParseDateColumncommission(j[i][3]);
				}
				$("table#h_madtable").append('<tr id="tr_hShape' + x + '">' 
						+ '<td style="width: 2%;">' + x + '</td>' + '<td>'
						+ '<select name="h_status' + x + '" id="h_status' + x + '" ' + '	class="form-control"  onchange="statusChange(2,' + x + ',this.options[this.selectedIndex].value)">' 
						+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
						+ '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' 
						+ '		</select>' + '  </td>' 
						+ '   <td style="">' + '<select name="hShape_value' + x + '" id="hShape_value' + x + '" ' + '		class="form-control" onchange="onShapeValueChange(this,\'h\')">' + '		</select>	</td>'
					+ '<td style=""> ' + ' <input type="text" name="h_from_date' + x + '" id="h_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '">' + '</td>' + '<td style=""> ' + ' <input type="text" name="h_to_date' + x + '" id="h_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '">' + '</td>'
					+ '  <td style="visibility:hidden; "  class="diagnosisClass2' + x + '">' + ' <input type="text" name="_diagnosis_code2' + x + '" id="_diagnosis_code2' + x + '" value="' + j[i][4] + '" class="form-control" maxlength="255"  autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>' + ' <td style="display:none;"><input type="text" id="hShape_ch_id' + x + '" name="hShape_ch_id' + x + '" value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="addbtClass2' + x + '">' + '   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add' + x + '" onclick="hShape_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				
				if(meddtlfillin3008==1){
					     $('#h_status' + x).prop('disabled', true);
					    $('#hShape_value' + x).prop('disabled', true);
					    $('#h_from_date' + x).prop('disabled', true);
					    $('#h_to_date' + x).prop('disabled', true);
					    $('#_diagnosis_code2' + x).prop('disabled', true);
				}
				else{
					datepicketDate('h_from_date' + x);
					datepicketDate('h_to_date' + x);	
				}
			
				$("#h_to_date" + x).datepicker("option", "maxDate", null);
				$("#h_status" + x).val(j[i][0]);
				$.ajaxSetup({
					async: false
				});
				statusChange(2, x, j[i][0]);
				$("#hShape_value" + x).val(j[i][1]);
// 				if(x > 1) {
// 					$("#h_status" + x + " option[value='1']").remove();
// 					$("#h_status" + (x - 1) + " option[value='1']").remove();
// 				} else {
					var thisT = document.getElementById('hShape_value' + x);
					onShapeValueChange(thisT, 'h');
// 				}
			}
			hShape = v;
			$("input#hShape_count").val(v);
			$("input#hShapeOld_count").val(v);
			if(meddtlfillin3008==1){
				$("#hShape_add" + v).hide();
			}else{
				$("#hShape_add" + v).show();
			}
			
			
		}
	});
}

function getaShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'A';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			classification = j[0][13];
			$('#a_madtable').show();
			$('#a_madtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var fd = "DD/MM/YYYY";
				var td = "DD/MM/YYYY";
				if(j[i][2] != null && j[i][2] != "") {
					fd = ParseDateColumncommission(j[i][2]);
// 					td = ParseDateColumncommission(j[i][3]);
				}
				if(j[i][3] != null && j[i][3] != "") {
					td = ParseDateColumncommission(j[i][3]);
				}
				$("table#a_madtable").append('<tr id="tr_aShape' + x + '">' + '<td style="width: 2%;">' + x + '</td>' 
				+ '<td>' + '<select name="a_status' + x + '" id="a_status' + x + '" ' 
				+ '	class="form-control"  onchange="statusChange(3,' + x + ',this.options[this.selectedIndex].value)">'
				+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' 
				+ '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' 
				+ '	</c:forEach>' + '		</select>' + '  </td>'
				+ '   <td style="">' + '<select name="aShape_value' + x + '" id="aShape_value' + x + '" ' 
				+ '		class="form-control" onchange="onShapeValueChange(this,\'a\')">' + '		</select>	</td>'
					+ '<td style=""> ' + ' <input type="text" name="a_from_date' + x + '" id="a_from_date' + 
					x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
					+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
					+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '">' + '</td>' 
					+ '<td style=""> ' + ' <input type="text" name="a_to_date' + x + '" id="a_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
					+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '">' + '</td>' 
					+ '  <td style="visibility:hidden; "  class="diagnosisClass3' + x + '">' 
					+ ' <input type="text" name="_diagnosis_code3' + x + '" id="_diagnosis_code3' + x + '" value="' + j[i][4] + '" class="form-control" maxlength="255"  autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>' + ' <td style="display:none;"><input type="text" id="aShape_ch_id' + x + '" name="aShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="addbtClass3' + x + '" >' + '   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add' + x + '" onclick="aShape_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				
				if(meddtlfillin3008 == 1){
					  $('#a_status' + x).prop('disabled', true);
					    $('#aShape_value' + x).prop('disabled', true);
					    $('#a_from_date' + x).prop('disabled', true);
					    $('#a_to_date' + x).prop('disabled', true);
					    $('#_diagnosis_code3' + x).prop('disabled', true);
					   
				}else{
					datepicketDate('a_from_date' + x);
					datepicketDate('a_to_date' + x);
				}				
				
				$("#a_to_date" + x).datepicker("option", "maxDate", null);
				$("#a_status" + x).val(j[i][0]);
				$.ajaxSetup({
					async: false
				});
				statusChange(3, x, j[i][0]);
				$("#aShape_value" + x).val(j[i][1]);
// 				if(x > 1) {
// 					$("#a_status" + x + " option[value='1']").remove();
// 					$("#a_status" + (x - 1) + " option[value='1']").remove();
// 				} else {
					var thisT = document.getElementById('aShape_value' + x);
					onShapeValueChange(thisT, 'a');
// 				}
			}
			aShape = v;
			$("input#aShape_count").val(v);
			$("input#aShapeOld_count").val(v);
			if(meddtlfillin3008 == 1){
				$("#aShape_add" + v).hide();
			}else{
				$("#aShape_add" + v).show();
			}
			
			
		}
	});
}

function getpShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'P';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#p_madtable').show();
			$('#p_madtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var fd = "DD/MM/YYYY";
				var td = "DD/MM/YYYY";
				if(j[i][2] != null && j[i][2] != "") {
					fd = ParseDateColumncommission(j[i][2]);
// 					td = ParseDateColumncommission(j[i][3]);
				}
				if(j[i][3] != null && j[i][3] != "") {
					td = ParseDateColumncommission(j[i][3]);
				}
				$("table#p_madtable").append('<tr id="tr_pShape' + x + '">' + '<td style="width: 2%;">' + x + '</td>' 
				+ '<td>' + '<select name="p_status' + x + '" id="p_status' + x + '" ' + '	class="form-control"  onchange="statusChange(4,' + x + ',this.options[this.selectedIndex].value)">' 
				+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' 
				+ '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' 
				+ '		</select>' + '  </td>' 
				+ '   <td style="">' + '<select name="pShape_value' + x + '" id="pShape_value' + x + '" ' + 'class="form-control" onchange="onShapeValueChange(this,\'p\')">' + '		</select>	</td>'
				+ '<td style=""> ' + ' <input type="text" name="p_from_date' + x + '" id="p_from_date' + x 
				+ '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
				+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '">' + '</td>' 
				+ '<td style=""> ' 
				+ ' <input type="text" name="p_to_date' + x + '" id="p_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
				+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
				+ '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td 
				+ '">' + '</td>' 
				+ '  <td style="visibility:hidden; "  class="diagnosisClass4' + x + '">' + ' <input type="text" name="_diagnosis_code4' + x + '" id="_diagnosis_code4' + x + '" value="' + j[i][4] 
				+ '" class="form-control" maxlength="255" autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>' + ' <td style="display:none;"><input type="text" id="pShape_ch_id' + x + '" name="pShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="addbtClass4' + x + '">' + '   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add' + x + '" onclick="pShape_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				
				if(meddtlfillin3008 == 1){
					 $('#p_status' + x).prop('disabled', true);
					    $('#pShape_value' + x).prop('disabled', true);
					    $('#p_from_date' + x).prop('disabled', true);
					    $('#p_to_date' + x).prop('disabled', true);
					    $('#_diagnosis_code4' + x).prop('disabled', true);
				}else{
					datepicketDate('p_from_date' + x);
					datepicketDate('p_to_date' + x);
					
				}
			
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
// 				if(x > 1) {
// 					$("#p_status" + x + " option[value='1']").remove();
// 					$("#p_status" + (x - 1) + " option[value='1']").remove();
// 				} else {
					var thisT = document.getElementById('pShape_value' + x);
					onShapeValueChange(thisT, 'p');
// 				}
			}
			pShape = v;
			$("input#pShape_count").val(v);
			$("input#pShapeOld_count").val(v);
			if(meddtlfillin3008 == 1){
				$("#pShape_add" + v).hide();
			}else{
				$("#pShape_add" + v).show();
			}
			
			
		}
	});
}

function geteShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'E';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#e_madtable').show();
			$('#e_madtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var fd = "DD/MM/YYYY";
				var td = "DD/MM/YYYY";
				if(j[i][2] != null && j[i][2] != "") {
					fd = ParseDateColumncommission(j[i][2]);
// 					td = ParseDateColumncommission(j[i][3]);
				}
				if(j[i][3] != null && j[i][3] != "") {
					td = ParseDateColumncommission(j[i][3]);
				}
				$("table#e_madtable").append('<tr id="tr_eShape' + x + '">' + '<td style="width: 2%;">' + x + '</td>' + '<td>'
				+ '<select name="e_status' + x + '" id="e_status' + x + '" ' + '	class="form-control"  onchange="statusChange(5,' + x + ',this.options[this.selectedIndex].value)">' 
				+ '	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>' + '	</c:forEach>' 
				+ '		</select>' 
				+ '  </td>' 
				+ '   <td style="">' 
				+ '<select name="eShape_value' + x + '" id="eShape_value' + x + '" ' + '		class="form-control" onchange="onShapeValueChange(this,\'e\')">' 
				+ '		</select>	</td>'
				+ '<td style=""> ' + ' <input type="text" name="e_from_date' + x + '" id="e_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
				+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
				+ '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + fd + '">' + '</td>' 
				+ '<td style=""> ' + ' <input type="text" name="e_to_date' + x + '" id="e_to_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' 
				+ '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' 
				+ '	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + td + '">' + '</td>' 
				+ '  <td style="visibility:hidden; "  class="diagnosisClass5' + x + '">' + ' <input type="text" name="_diagnosis_code5' + x + '" id="_diagnosis_code5' + x + '" value="' + j[i][4] + '" class="form-control" maxlength="255"  autocomplete="off" ' + '  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	' + '   </td>' + ' <td style="display:none;"><input type="text" id="eShape_ch_id' + x + '" name="eShape_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="addbtClass5' + x + '">' + '   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add' + x + '" onclick="eShape_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				
				if(meddtlfillin3008==1){
					$('#e_status' + x).prop('disabled', true);
				    $('#eShape_value' + x).prop('disabled', true);
				    $('#e_from_date' + x).prop('disabled', true);
				    $('#e_to_date' + x).prop('disabled', true);
				    $('#_diagnosis_code5' + x).prop('disabled', true);
				}else{
					datepicketDate('e_from_date' + x);
					datepicketDate('e_to_date' + x);
				}
			
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
// 				if(x > 1) {
// 					$("#e_status" + x + " option[value='1']").remove();
// 					$("#e_status" + (x - 1) + " option[value='1']").remove();
// 				} else {
					var thisT = document.getElementById('eShape_value' + x);
					onShapeValueChange(thisT, 'e');
// 				}
			}
			eShape = v;
			$("input#eShape_count").val(v);
			$("input#eShapeOld_count").val(v);
			if(meddtlfillin3008==1){
				$("#eShape_add" + v).hide();
			}else{
				$("#eShape_add" + v).show();
			}
			
			
		}
	});
}

function getcCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_C';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#c_cmadtable').show();
			$('#c_cmadtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				cCope = x;
				$("table#c_cmadtable").append('<tr id="tr_cCope' + x + '">' + '<td  style="width: 2%;">' + x + '</td>' 
				+ '<td style="width: 70%;">' + '<select name="c_cvalue' + x + '" id="c_cvalue' + x + '" ' + '	class="form-control" onchange="onCCopeChange(this,' + x + '); onCopeChangebt(this,1,' + x + ');">' + '	<c:forEach var="item" items="${getcCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' 
				+ '<td style="display:none;width: 28%;" class="cCopClass' + x + '" >' + '<input type="text" name="c_cother' + x + '" id="c_cother' + x + '" value="' + j[i][10] + '" class="form-control" autocomplete="off" >	' + ' </td>' + ' <td style="display:none;"><input type="text" id="cCope_ch_id' + x + '" name="cCope_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="CopaddbtClass11" >' + '   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add' + x + '" onclick="cCope_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				$("#c_cvalue" + x).val(j[i][1]);
				var thisT = document.getElementById('c_cvalue' + x);
				onCCopeChange(thisT, x);
				
				if(meddtlfillin3008 == 1){
					  $('#c_cvalue' + x).prop('disabled', true);
					    $('#c_cother' + x).prop('disabled', true);
				}
				if(x == 1) {
					onCopeChangebt(thisT, 1, x);
				}
				if(x > 1) {
					$("#c_cvalue" + x + " option[value='0']").remove();
					$("#c_cvalue" + x + " option[value='1']").remove();
					$("#c_cvalue" + (x - 1) + " option[value='0']").remove();
					$("#c_cvalue" + (x - 1) + " option[value='1']").remove();
				}
			}
			
			
			cCope = v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			if(meddtlfillin3008 == 1){
				$("#cCope_add" + v).hide();
			}else{
				$("#cCope_add" + v).show();	
			}
			
		}
	});
}

function getoCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_O';
	var comm_id = $('#comm_id').val();
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	var i = 0;
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#c_omadtable').show();
			$('#c_omadtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				oCope = x;
				$("table#c_omadtable").append('<tr id="tr_oCope' + x + '">' + '<td  style="width: 2%;">' + x + '</td>' + '<td>' + '<select name="c_ovalue' + x + '" id="c_ovalue' + x + '" ' + '	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,' + x + ');">' + '	<c:forEach var="item" items="${getoCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' + ' <td style="display:none;"><input type="text" id="oCope_ch_id' + x + '" name="oCope_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="CopaddbtClass21" >' + '   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add' + x + '" onclick="oCope_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				$.ajaxSetup({
					async: false
				});
				$("#c_ovalue" + x).val(j[i][1]);
				var thisT = document.getElementById('c_ovalue' + x)
				if(meddtlfillin3008 ==1){
				    $('#c_ovalue' + x).prop('disabled', true);
				}
				if(x == 1) {
					onCopeChangebt(thisT, 2, x);
				}
				if(x > 1) {
					$("#c_ovalue" + x + " option[value='0']").remove();
					$("#c_ovalue" + (x - 1) + " option[value='0']").remove();
				}
			}
			oCope = v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			if(meddtlfillin3008 ==1){
				$("#oCope_add" + v).hide();
			}else{
				$("#oCope_add" + v).show();	
			}
			
		}
	});
}

function getpCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_P';
	var comm_id = $('#comm_id').val();
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	var i = 0;
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#c_pmadtable').show();
			$('#c_pmadtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				pCope = x;
				$("table#c_pmadtable").append('<tr id="tr_pCope' + x + '">' + '<td  style="width: 2%;">' + x + '</td>' + '<td>' + '<select name="c_pvalue' + x + '" id="c_pvalue' + x + '" ' + '	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,' + x + ');">' + '	<c:forEach var="item" items="${getpCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' + ' <td style="display:none;"><input type="text" id="pCope_ch_id' + x + '" name="pCope_ch_id' + x + '"  value="' + j[i][5] + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="CopaddbtClass31">' + '   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add' + x + '" onclick="pCope_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				$.ajaxSetup({
					async: false
				});
				$("#c_pvalue" + x).val(j[i][1]);
				var thisT = document.getElementById('c_pvalue' + x)
				if(meddtlfillin3008==1){
					 $('#c_pvalue' + x).prop('disabled', true);
				}
				if(x == 1) {
					onCopeChangebt(thisT, 3, x);
				}
				if(x > 1) {
					$("#c_pvalue" + x + " option[value='0']").remove();
					$("#c_pvalue" + x + " option[value='1']").remove();
					$("#c_pvalue" + (x - 1) + " option[value='0']").remove();
					$("#c_pvalue" + (x - 1) + " option[value='1']").remove();
				}
			}
			pCope = v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			if(meddtlfillin3008==1){
				$("#pCope_add" + v).hide();
			}else{
				$("#pCope_add" + v).show();	
			}
			
		}
	});
}

function geteCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_E';
	var comm_id = $('#comm_id').val();
	var i = 0;
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	$.post('medical_cat_ModifyGetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape,
		comm_id:comm_id,
		meddtlfillin3008:meddtlfillin3008
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#c_emadtable').show();
			$('#c_emadtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				eCope = x;
				$("table#c_emadtable").append('<tr id="tr_eCope' + x + '">' + '<td  style="width: 2%;">' + x + '</td>' + '<td>' + '<select name="c_evalue' + x + '" id="c_evalue' + x + '" ' + '	class="form-control-sm form-control"  onchange="onECopeChange(this,' + x + '); onCopeChangebt(this,4,' + x + ');">' + '	<c:forEach var="item" items="${geteCopeList}" varStatus="num">' + '	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '	</c:forEach>' + '		</select>' + '  </td>' + '<td style="display:none;" class="eCopClass' + x + '">' + '<select name="c_esubvalue' + x + '" id="c_esubvalue' + x + '" onchange="onECopeSubChange(this,' + x + ')"' + '			class="form-control-sm form-control" >' + '			<option value="0">--Select--</option>' + '			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">' + '				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>' + '			</c:forEach></select>   </td>' + '<td style="display:none;" class="eCop_otherClass' + x + '" >' + '	 <input type="text" name="c_esubvalueother' + x + '" id="c_esubvalueother' + x + '" value="' + j[i][10] + '" class="form-control-sm form-control" autocomplete="off" >' + '	   </td>' + ' <td style="display:none;"><input type="text" id="eCope_ch_id' + x + '" name="eCope_ch_id' + x + '" value="' + j[i][5] + '"  class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;" class="CopaddbtClass41">' + '   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add' + x + '" onclick="eCope_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '</td>' + '</tr>');
				$.ajaxSetup({
					async: false
				});
				$("#c_evalue" + x).val(j[i][1]);
				$("#c_esubvalue" + x).val(j[i][9]);
				var thisT = document.getElementById('c_evalue' + x)
				if(meddtlfillin3008 == 1){
				    $('#c_evalue' + x).prop('disabled', true);
				    $('#c_esubvalue' + x).prop('disabled', true);
				    $('#c_esubvalueother' + x).prop('disabled', true);
				}
				onECopeChange(thisT, x);
				if(x == 1) {
					onCopeChangebt(thisT, 4, x);
				}
				if(x > 1) {
					$("#c_evalue" + x + " option[value='0']").remove();
					$("#c_evalue" + (x - 1) + " option[value='0']").remove();
				}
			}
			eCope = v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			if(meddtlfillin3008 == 1){
				$("#eCope_add" + v).hide();
			}else{
				$("#eCope_add" + v).show();	
			}
			
		}
	});
}



/* var input = document.getElementById("first_name");
input.addEventListener("keyup", function () {
	AvoidSpace(event);
});
input.addEventListener("keyup", function () {
	specialcharecter(this);
}); */
$(document).ready(function() {
	debugger
	if('${personnel_no}' != "") {
		$('#btn1').hide();
		$('#censussearshUrl').show();
		$("#personnel_no").val('${personnel_no}');
		personal_number();
	}
	//siblingCB(1);
	//spouseCB(1);
	
	$("a").click(function(){
		addMaxLengthToInput(auth_length);

	});
	//26-01-1994
	if('${mlabel}' != "") {
		$('#personnel_no').attr('readonly', false);
		$("#medical_details_bt").click();
		$("#medical_details_bt").addClass("active");
	}
});

function calculate_age(dob, marrage_date) {


	var from_d = dob.value;
	from_d = from_d.replaceAll("/", "-");
	var from_d1 = from_d.substring(6, 10);
	var from_d2 = from_d.substring(3, 5);
	var from_d3 = from_d.substring(0, 2);
	var frm_d = from_d3 + "-" + from_d2 + "-" + from_d1;
	var today = marrage_date.value;
	today = today.replaceAll("/", "-");
	var to_d1 = today.substring(6, 10);
	var to_d2 = today.substring(3, 5);
	var to_d3 = today.substring(0, 2);
	var to_d0 = to_d3 + "-" + to_d2 + "-" + to_d1;
	if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3) {
		var year = to_d1 - from_d1
		var month = to_d2 - from_d2
	}
	if(to_d2 > from_d2 && to_d3 < from_d3) {
		var year = to_d1 - from_d1
		var month = to_d2 - from_d2 - 1
	}
	if(from_d2 > to_d2) {
		var year = to_d1 - from_d1 - 1
		var month1 = from_d2 - to_d2
		var month = 12 - month1
	}
	if(from_d2 == to_d2 && from_d3 > to_d3) {
		var year = to_d1 - from_d1 - 1
		var days = from_d3 - to_d3
	}
	if(from_d2 == to_d2 && to_d3 > from_d3) {
		var year = to_d1 - from_d1
		var days = to_d3 - from_d3
	}
	if(from_d2 == to_d2 && to_d3 == from_d3) {
		var year = to_d1 - from_d1
		var days = 0
	}
	
	if(from_d2 > to_d2 && from_d3 > to_d3) {
		var m = from_d2 - to_d2
		var n = m * 30
		var m1 = from_d3 - to_d3
		var m2 = n + m1
		var days = m2
	}
	if(from_d2 > to_d2 && to_d3 > from_d3) {
		var m = from_d2 - to_d2
		var n = m * 30
		var m1 = to_d3 - from_d3
		var m2 = n + m1
		var days = m2
	}
	if(to_d2 > from_d2 && to_d3 > from_d3) {
		var m = to_d2 - from_d2
		var n = m * 30
		var m1 = to_d3 - from_d3
		var m2 = n + m1
		var days = m2
	}
	if(to_d2 > from_d2 && from_d3 > to_d3) {
		var m = to_d2 - from_d2
		var n = m * 30
		var m1 = from_d3 - to_d3
		var m2 = n + m1
		var days = m2
	}
	
	
	if(month == undefined) {
		month = 0;
	}
	if(year < 18) {
		alert("Age of Spouse And Offr Should be above 18");
		
		$("#" + marrage_date.id).focus();
		return false;
	}
	return true;
}
jQuery(function($) { 
	

	datepicketDate('sep_from_date1');
	datepicketDate('issue_dt');
	datepicketDate('father_dob');
	datepicketDate('mother_dob');
	datepicketDate('sib_date_of_birth1');
	datepicketDate('marriage_date_of_birth1');
	datepicketDate('marriage_date1');
	datepicketDate('divorce_date_of_birth1');
	datepicketDate('divorce_marriage_date1');
	datepicketDate('divorce_date1');
	datepicketDate('from_dt1');
	datepicketDate('to_dt1');
	datepicketDate('date_form1');
	datepicketDate('date_to1');
	datepicketDate('s_from_date1');
	datepicketDate('s_to_date1');
	datepicketDate('h_from_date1');
	datepicketDate('h_to_date1');
	datepicketDate('a_from_date1');
	datepicketDate('a_to_date1');
	datepicketDate('p_from_date1');
	datepicketDate('p_to_date1');
	datepicketDate('e_from_date1');
	datepicketDate('e_to_date1');
	datepicketDate('1bx_from_date');
	datepicketDate('1bx_to_date');
	$("#1bx_to_date").datepicker("option", "maxDate", null);
	$("#s_to_date1").datepicker("option", "maxDate", null);
	$("#h_to_date1").datepicker("option", "maxDate", null);
	$("#a_to_date1").datepicker("option", "maxDate", null);
	$("#p_to_date1").datepicker("option", "maxDate", null);
	$("#e_to_date1").datepicker("option", "maxDate", null);
});

function lengthadhar() {
	document.getElementById("adhar_number1").maxLength = "4";
	document.getElementById("adhar_number1").minLength = "4";
	document.getElementById("adhar_number2").maxLength = "4";
	document.getElementById("adhar_number2").minLength = "4";
	document.getElementById("adhar_number3").maxLength = "4";
	document.getElementById("_number3").minLength = "4";
}

function movetoNext(current, nextFieldID) {
	if(current.value.length >= current.maxLength) {
		document.getElementById(nextFieldID).focus();
	}
}

function ServiceOtherFn(divId, perId, obj) {
	if(obj.options[obj.selectedIndex].text.toUpperCase() == 'OTHER' || obj.options[obj.selectedIndex].text.toUpperCase() == 'OTHERS') {
		$("#" + divId).show();
		$("#" + perId).hide();
	} else {
		$("#" + divId).hide();
		$("#" + perId).show();
	}
}


function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText.toUpperCase()==other || thisText.toUpperCase()=='OTHER'){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}




function returnSearch(){
	$("#personnel_no1").val('${personnel_no_return}') ;	
	$("#status1").val('${status_return}') ;		
	$("#rank1").val('${rank_return}') ;		
	$("#unit_name1").val('${unit_name_return}') ;	
	$("#unit_sus_no1").val('${unit_sus_no_return}') ;
	document.getElementById('searchForm').submit();
}


function AllCoperemove_fn() {
	var r = confirm("Are You Sure! You Want To Reset All Medical Details");
	if(r) {
		
		var sShapeOld_count = $("input#sShapeOld_count").val();
		var hShapeOld_count = $("input#hShapeOld_count").val();
		var aShapeOld_count = $("input#aShapeOld_count").val();
		var pShapeOld_count = $("input#pShapeOld_count").val();
		var eShapeOld_count = $("input#eShapeOld_count").val();
		
		
		var cCopeOld_count = $("input#cCopeOld_count").val();
		var oCopeOld_count = $("input#oCopeOld_count").val();
		var pCopeOld_count = $("input#pCopeOld_count").val();
		var eCopeOld_count = $("input#eCopeOld_count").val();
		var id="";
		
		
		for (var i = 1; i <= parseInt(sShapeOld_count); i++) {
			if (i==1) {
				id = $("input#sShape_ch_id" + i).val();
			}
			else{
				id += ","+$("input#sShape_ch_id" + i).val();
			}
		}
		
		for (var i = 1; i <= parseInt(hShapeOld_count); i++) {
			id += ","+$("input#hShape_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(aShapeOld_count); i++) {
			
			id += ","+$("input#aShape_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(pShapeOld_count); i++) {
			id += ","+$("input#pShape_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(eShapeOld_count); i++) {
			id += ","+$("input#eShape_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(cCopeOld_count); i++) {
				id += ","+$("input#cCope_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(oCopeOld_count); i++) {
			id += ","+$("input#oCope_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(pCopeOld_count); i++) {
			
			id += ","+$("input#pCope_ch_id" + i).val();
		}
		
		for (var i = 1; i <= parseInt(eCopeOld_count); i++) {
			id += ","+$("input#eCope_ch_id" + i).val();
		}
		
		
		
		
		$("#personnel_no9").val($("#personnel_no").val()) ;
		$("#id9").val(id);
		document.getElementById('View1Form').submit();
		
	
	}
}
	 

	</script>


	<c:url value="AllCopeRemove" var="ViewUrl" />
	<form:form action="${ViewUrl}" method="post" id="View1Form"
		name="View1Form">
		<input type="hidden" name="personnel_no9" id="personnel_no9" value="0">
		<input type="hidden" name="id9" id="id9" value="0">
		<input type="hidden" name="mlabel9" id="mlabel9" value="mlabel">
	</form:form>