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
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
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
			<div class="container-fluid" align="center" >
				<div class="card">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default" id="insp_div1">
							<div class="panel-heading">
								<!-- 					<div class="panel-heading"> -->
								<!-- 						<h4 class="panel-title main_title" id="insp_div5"> -->
								<!-- 							<a class="droparrow collapsed" data-toggle="collapse" -->
								<!-- 								data-parent="#accordion" href="#collapse1in" id="insp_final" -->
								<!-- 								style="color: blue;"><b>Census Details</b></a> -->
								<!-- 						</h4> -->
								<!-- 					</div> -->
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
												<input type="text" id="personnel_no" name="personnel_no" onkeydown="return AvoidSpace(event)" 
																			onkeypress="return onlyAlphaNumeric(event);"  maxlength="9" min="7"  class="form-control autocomplete" autocomplete="off">

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

	
<div class="tab" id="tab_id" style="display: none;">
		<button class="tablinks " id="personal_btn" onclick="openCity(event, '1')">Personal
			Details</button>
		<button class="tablinks" id="add_btn_re" onclick="openCity(event, 'addr_details')">Address
			& Domicile Details</button>
		<button class="tablinks"  id="family_btn" onclick="openCity(event, 'family_details')">Family
			Details</button>
		<button class="tablinks" id="foreign_btn"
			onclick="openCity(event, 'Foreign_Countries')">Foreign
			Countries Visited</button>
		<button class="tablinks"  id="pre_cadet_btn" onclick="openCity(event, 'Pre-Cadet')">Pre-Cadet/NCC
			Details</button>

		<button class="tablinks"  id="qualification_btn"onclick="openCity(event, 'Qualifications')">Qualification
		</button>
		<button class="tablinks"  id="language_btn" onclick="openCity(event, 'language_details')">Language</button>
		<button class="tablinks" id="medical_details_bt" onclick="openCity(event, 'medical_details')">Medical
			Details</button>
	</div>



	<div id="1" class="tabcontent" style="display: none;">

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
											<input type="hidden" id="census_id" name="census_id"value="0" class="form-control autocomplete"
												autocomplete="off"> 
											<input type="hidden"id="comm_id" name="comm_id"class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="med_id" name="med_id" value="0" class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="i_id" name="i_id" value="0" class="form-control autocomplete" autocomplete="off">
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
												maxlength="50" onkeypress="return onlyAlphabets(event);" style="left: 80px;width: 80%;"
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
											<input type="text" id="middle_name" name="middle_name"  class="form-control autocomplete" autocomplete="off"
											maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" style="width: 80%;">
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
												class="form-control autocomplete" autocomplete="off" style="width: 80%;"
												maxlength="50" onkeypress="return onlyAlphabets(event);"
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
												onchange="fn_country_birth();"
												class="form-control">
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
												onchange="fn_state_birth();"
												class="form-control">
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
								<div class="col-md-6" id = "country_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY Of Birth Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="country_birth_other" name="country_birth_other" 
					                	onkeypress="return onlyAlphabets(event);" 
					                	class="form-control autocomplete" maxlength="50"  autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "state_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE Of Birth Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="state_birth_other" name="state_birth_other"
					                	 onkeypress="return onlyAlphabets(event);" 
					                	class="form-control autocomplete" maxlength="50"  autocomplete="off" >
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
											<select name="district_birth" id="district_birth" onchange="fn_district_birth();"
												class="form-control">
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
											     onkeypress="return onlyAlphabets(event);" 
											     maxlength="50" class="form-control autocomplete" autocomplete="off"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>

							</div>
							
							     <div class="col-md-12">
								<div class="col-md-6" id = "district_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT of Birth Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="district_birth_other" name="district_birth_other" 
					                		
					                	 onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	  maxlength="50" autocomplete="off" >
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
								<div class="col-md-6" id = "nationality_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Nationality Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nationality_other" name="nationality_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					<div class="col-md-6" id = "mother_tongue_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Mother Tongue Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="mother_tongue_other" name="mother_tongue_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	 autocomplete="off" maxlength="50" >
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
											<select name="religion" id="religion"
												class="form-control" onchange="fn_religion();">
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
												<select name="ncc_type" id="ncc_type"
													class="form-control">
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
								<div class="col-md-6" id = "religion_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Religion Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="religion_other" name="religion_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"><!-- * --> </strong> Aadhaar No</label>
										</div>



										<div class="col-md-2">
											<input type="text" id="adhar_number1" name="adhar_number1"
												class="form-control autocomplete" autocomplete="off"
												 maxlength="4" 
												onkeydown="return AvoidSpace(event)"  onkeypress="return isNumber(event)"
												onkeyup="movetoNext(this, 'adhar_number2'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number2" name="adhar_number2"
												class="form-control autocomplete" autocomplete="off"
												 maxlength="4"
												onkeydown="return AvoidSpace(event)" onkeypress="return isNumber(event)"
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
												style="color: red;"><!-- * --> </strong> Pan No</label>
										</div>
										<div class="col-md-8">

											<input type="text" id="pan_no" name="pan_no"
												class="form-control autocomplete" autocomplete="off"
												onchange=" isPAN(this); " 	onkeyup="return specialcharecter(this)"
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
												class="form-control"
												onchange="getInst_btn(this);">
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
												class="form-control"
												onchange="getInst_trainngBtn(this);">
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

								<!-- 							<div class="col-md-2"> -->
								<!-- 								<div class="row form-group"> -->
								<!-- 									<div class="col-md-4"> -->
								<!-- 										<label class=" form-control-label"> Platoon</label> -->
								<!-- 									</div> -->
								<!-- 									<div class="col-md-8"> -->
								<!-- 										<select name="platoon_id" id="platoon_id" -->
								<!-- 											class="form-control-sm form-control"> -->
								<!-- 											<option value="0">--Select--</option> -->
								<!-- 										</select> -->

								<!-- 									</div> -->
								<!-- 								</div> -->
								<!-- 							</div> -->
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
											<select name="height" id="height"
												class="form-control">
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
												<td><input type="text" id="medicine1" name="medicine1" maxlength="100" 
													class="form-control autocomplete" autocomplete="off">
												</td>

												<td style="display: none;"><input type="text"
													id="allergic_ch_id1" name="allergic_ch_id1" value="0" maxlength="100" 
													class="form-control autocomplete" autocomplete="off"></td>
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

							<div class="" align="center">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Identity
										Card Details</h4></label>

							</div>
							<br>

							<div id="identity_div">

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> ID Card no</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="id_card_no" name="id_card_no"
													onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
														onkeydown="return AvoidSpace(event)" 
													class="form-control autocomplete" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> Issuing Date</label>
											</div>
											<div class="col-md-8">
												<!-- 											<input type="date" id="issue_dt" name="issue_dt" -->
												<!-- 												class="form-control autocomplete" autocomplete="off" -->
												<%-- 												maxlength="10" max="${date_without_time}"> --%>

												<input type="text" name="issue_dt" id="issue_dt"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control" style="width: 85%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">

											</div>
										</div>
									</div>
								</div>


								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> Issuing Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="issue_authority"
													name="issue_authority" class="form-control autocomplete"
													autocomplete="off" maxlength="10"> <input
													type="hidden" id="issue_authority_sus"
													name="issue_authority_sus"
													class="form-control autocomplete" autocomplete="off"
													maxlength="10">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> Visible Identification
													Marks</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="id_marks" name="id_marks"
													onkeypress="return onlyAlphaNumeric(event);"  
													class="form-control autocomplete" autocomplete="off"
													onkeypress="return onlyAlphabets(event);" maxlength="50">
											</div>
										</div>
									</div>

								</div>
							<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> Hair Colour</label>
											</div>
											<div class="col-md-8">
												<select name="hair_colour" id="hair_colour"
													class="form-control-sm form-control" onchange="fn_hair_colour();">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getHair_ColourList}"
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
													style="color: red;"><!-- * --> </strong> Eye Colour</label>
											</div>
											<div class="col-md-8">
												<select name="eye_colour" id="eye_colour"
													class="form-control-sm form-control" onchange="fn_eye_colour();">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getEye_ColourList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>


								</div>
								<div class="col-md-12">
								<div class="col-md-6" id = "hair_colour_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Hair Colour Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="hair_colour_other" name="hair_colour_other"
					                	 class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" 
					                	  autocomplete="off" maxlength="50"  >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "eye_colour_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Eye Colour Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="eye_colour_other" name="eye_colour_other" 
					                	class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					
	              					</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"><!-- * --> </strong> Photograph </label>
											</div>
											<div class="col-md-8">
												<input type="file" id="identity_image" name="identity_image"
													accept="image/*"
													onchange="document.getElementById('identity_image_preview').src = window.URL.createObjectURL(this.files[0])">
												<label class=" form-control-label" style="color: red;"><strong
													style="color: red;">Note:</strong> Dress No. 4 : Dress Gen
													Duty Summer.' - PASSPORT SIZE </label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<img id="identity_image_preview" alt="Officer Image"
												src="js/img/No_Image.jpg" width="100" height="100" />
											<!-- 										src='healthEduConvertpath?healtheduid="+rs.getString("id")+"' -->
										</div>
									</div>


								</div>


								<!-- <div class="col-md-12">
	              			              									
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Border Area</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="radio" id="border_area_yes" name="border_area" value="yes">
										  <label for="male">Yes</label>
										  <input type="radio" id="border_area_no" name="border_area" value="no">
										  <label for="female">No</label><br>                 
						                </div>
						            </div>
	              				</div> 
	              			</div> -->

							</div>
						</form>
					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="button" id ="save_per" class="btn btn-primary btn-sm" value="Save And Next"
							onclick="return Validation();">
					</div>
				</div>
			</div>
		</div>

	</div>


	<div id="addr_details" class="tabcontent" style="display: none;">
		<form id="addr_details_form">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header">
							<h5>Address & Domicile Details</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
						<div class="card-body card-block">
							<br>
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
												onchange="fn_org_domicile_Country();"
												class="form-control">
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
												onchange="fn_org_domicile_state();"
												class="form-control">
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
								<div class="col-md-6" id = "org_domicile_country_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="org_domicile_Country_other" name="org_domicile_Country_other" 
					                	 onkeypress="return onlyAlphabets(event);"
					                	class="form-control autocomplete" autocomplete="off" maxlength="50"  >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "org_domicile_state_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="org_domicile_state_other" name="org_domicile_state_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	  autocomplete="off" maxlength="50" >
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
												onchange="fn_org_domicile_district();"
												class="form-control">
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
											<select name="org_domicile_tehsil" id="org_domicile_tehsil" onchange="fn_org_domicile_tehsil();"
												class="form-control">
												<option value="0">--Select--</option>
												
											</select>
										</div>
									</div>
								</div>
							</div>
							
								<div class="col-md-12">
								<div class="col-md-6" id = "org_domicile_district_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="org_domicile_district_other" name="org_domicile_district_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "org_domicile_tehsil_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="org_domicile_tehsil_other" name="org_domicile_tehsil_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<div class="col-md-12">
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Presently Domicile of</h4></label>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="pre_domicile_Country" id="pre_domicile_Country"
												onchange="fn_pre_domicile_Country()"
												class="form-control">
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
											<select name="pre_domicile_state" id="pre_domicile_state"
												onchange="fn_pre_domicile_state();"
												class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "pre_domicile_Country_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_Country_other" name="pre_domicile_Country_other"
					                	 onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pre_domicile_state_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_state_other" name="pre_domicile_state_other"
					                	 onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	   autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="pre_domicile_district"
												id="pre_domicile_district"
												onchange="fn_pre_domicile_district();"
												class="form-control">
												<option value="0">--Select--</option>
											<%-- 	<c:forEach var="item" items="${getMedDistrictName}"
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
											<select name="pre_domicile_tehsil" id="pre_domicile_tehsil" onchange="fn_pre_domicile_tehsil();"
												class="form-control">
												<option value="0">--Select--</option>
											
									
												
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "pre_domicile_district_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_district_other" name="pre_domicile_district_other"
					                	  onkeypress="return onlyAlphabets(event);" 
					                	class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pre_domicile_tehsil_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pre_domicile_tehsil_other" name="pre_domicile_tehsil_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	  autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<label class=" form-control-label" style="margin-left: 10px;"><h4>
									Permanent Home Address</h4></label>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_Country"
												id="per_home_addr_Country"
												onchange="fn_per_home_addr_Country();"
												class="form-control">
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
											<select name="per_home_addr_state" id="per_home_addr_state"
												onchange="fn_per_home_addr_state();"
												class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "per_home_addr_Country_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_Country_other" name="per_home_addr_Country_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete"
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_state_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_state_other" name="per_home_addr_state_other"
					                	 onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_district"
												id="per_home_addr_district"
												onchange="fn_per_home_addr_district();"
												class="form-control">
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
												style="color: red;">* </strong> TEHSIL</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_tehsil" id="per_home_addr_tehsil" onchange="fn_per_home_addr_tehsil();"
												class="form-control">
												<option value="0">--Select--</option>
												
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "per_home_addr_district_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_district_other" name="per_home_addr_district_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "per_home_addr_tehsil_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="per_home_addr_tehsil_other" name="per_home_addr_tehsil_other"
					                	 onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	  autocomplete="off" maxlength="50"  >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Village/Town/City</label>
										</div>
										<div class="col-md-8">
											<%-- <select name="per_home_addr_village" id="per_home_addr_village" class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>	 --%>
											<input type="text" id="per_home_addr_village" name="per_home_addr_village" 
											onkeypress="return onlyAlphabets(event);" 
												oninput="this.value = this.value.toUpperCase()" maxlength="50" 
												class="form-control autocomplete" autocomplete="off">

										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Pin</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="per_home_addr_pin" onkeypress="return isNumber(event)"
												name="per_home_addr_pin" class="form-control autocomplete"
												autocomplete="off" maxlength="6" onkeydown="return AvoidSpace(event)" 
												>
										</div>
									</div>
								</div>
							</div>


							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Nearest Railway Station</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="per_home_addr_rail"
												name="per_home_addr_rail" class="form-control autocomplete"
												autocomplete="off" maxlength="100" 
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-6">
						     <label for="lbl_per_home_addr_rural">
							 <input type="radio" id="per_home_addr_rural" name="per_home_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="lbl_per_home_addr_urban" >
							 <input type="radio" id="per_home_addr_urban" name="per_home_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="lbl_per_home_addr_semi_urban">
							 <input type="radio" id="per_home_addr_semi_urban" name="per_home_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						                <div class="popup" onclick="myFunction()">
												<i class="fa fa-question-circle"
													style="font-size: 28px; color: black"></i> <span
													class="popuptext" id="myPopup">Urban :An urban area
													is the region surrounding a city. Rural :Rural areas are
													the opposite of urban areas, Rural areas Rural areas often
													called the country. Semi Urban : Semi Urban are large
													residential areas that surround main cities. </span>
											</div>
						            </div>
	              				</div> 	
	              				
								
							</div>
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Border Area</label>
										</div>
										<div class="col-md-8">
											<input type="radio" id="border_area"
												name="permanent_border_area" value="yes"> <label
												for="male">Yes</label> <input type="radio" id="border_area1"
												name="permanent_border_area" value="no"> <label
												for="female">No</label><br>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12"></div>

							<div class="col-md-12">
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Present Address</h4></label>
							</div>
							<div class="col-md-12" style="font-size: 15px;">
								<input type="checkbox" id="check_address" name="check_address"
									onclick="copy_address()"> <label for="text-input"
									class=" form-control-label"
									style="color: #dd1a3e; margin-left: 10px;"> Same as
									Permanent Address </label>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="pers_addr_Country" id="pers_addr_Country"
												onchange="fn_pers_addr_Country();"
												class="form-control">
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
											<select name="pers_addr_state" id="pers_addr_state"
												onchange="fn_pers_addr_state();"
												class="form-control">
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
										</div>
									</div>
								</div>
							</div>
							
									<div class="col-md-12">
								<div class="col-md-6" id = "pers_addr_Country_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_Country_other" name="pers_addr_Country_other"
					                	 maxlength="50"  class="form-control autocomplete" 
					                	 onkeypress="return onlyAlphabets(event);" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_state_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_state_other" name="pers_addr_state_other"
					                	  maxlength="50"  
					                	class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="pers_addr_district" id="pers_addr_district"
												onchange="fn_pers_addr_district();"
												class="form-control">
												<option value="0">--Select--</option>
											<%-- 	<c:forEach var="item" items="${getMedDistrictName}"
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
											<select name="pers_addr_tehsil" id="pers_addr_tehsil" onchange="fn_pers_addr_tehsil();"
												class="form-control">
												<option value="0">--Select--</option>
												
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "pers_addr_district_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_district_other" name="pers_addr_district_other"
					                	 maxlength="50"  class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" 
					                	 autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "pers_addr_tehsil_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pers_addr_tehsil_other" name="pers_addr_tehsil_other" maxlength="50"  
					                	class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Village/Town/City</label>
										</div>
										<div class="col-md-8">
											<%--  <select name="pers_addr_village" id="pers_addr_village" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getVillageList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> --%>
											<input type="text" id="pers_addr_village"
													onkeypress="return onlyAlphabets(event);" maxlength="50" 
												name="pers_addr_village" class="form-control autocomplete"  
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Pin</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="pers_addr_pin" name="pers_addr_pin" onkeypress="return isNumber(event)"
												class="form-control autocomplete" autocomplete="off" onkeydown="return AvoidSpace(event)" 
												 maxlength="6">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Nearest Railway Station</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="pers_addr_rail" name="pers_addr_rail" maxlength="100" 
												class="form-control autocomplete" autocomplete="off"
													onkeypress="return onlyAlphaNumeric(event);" 
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-6">
						     <label for="pers_addr_rural">
							 <input type="radio" id="pers_addr_rural" name="pers_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="pers_addr_urban" >
							 <input type="radio" id="pers_addr_urban" name="pers_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="pers_addr_semi_urban">
							 <input type="radio" id="pers_addr_semi_urban" name="pers_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
						            </div>
	              				</div>
							</div>



							<div id="spouse_addressMaindiv" style="display: none">
								<div class="col-md-12" style="font-size: 15px;">
									<input type="checkbox" id="check_spouse_address"
										name="check_spouse_address" onclick="spouse_addressFn()">
									<label for="text-input" class=" form-control-label"
										style="color: #dd1a3e; margin-left: 10px;"> Is Family
										Staying SF ACCN </label>
								</div>
								<div id="spouse_addressInnerdiv" style="display: none">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> COUNTRY</label>
												</div>
												<div class="col-md-8">
													<select name="spouse_addr_Country" id="spouse_addr_Country"
														onchange="fn_spouse_addr_Country();"
														class="form-control">
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
													<select name="spouse_addr_state" id="spouse_addr_state"
														onchange="fn_spouse_addr_state();"
														class="form-control">
														<option value="0">--Select--</option>
														<%-- <c:forEach var="item" items="${getMedStateName}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach> --%>
													</select>
												</div>
											</div>
										</div>
									</div>
                     <div class="col-md-12">
								<div class="col-md-6" id = "spouse_addr_Country_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_country_other" name="spouse_addr_country_other" 
					                	class="form-control autocomplete" onkeypress="return onlyAlphabets(event);"
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_state_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_state_other" name="spouse_addr_state_other" 
					                	class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> DISTRICT</label>
												</div>
												<div class="col-md-8">
													<select name="spouse_addr_district"
														id="spouse_addr_district"
														onchange="fn_spouse_addr_district();"
														class="form-control">
														<option value="0">--Select--</option>
													<%-- 	<c:forEach var="item" items="${getMedDistrictName}"
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
													<select name="spouse_addr_tehsil" id="spouse_addr_tehsil"
														class="form-control" onchange="fn_spouse_addr_tehsil();">
														<option value="0">--Select--</option>
														
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
								<div class="col-md-6" id = "spouse_addr_district_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_district_other" name="spouse_addr_district_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50"  >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "spouse_addr_tehsil_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="spouse_addr_tehsil_other" name="spouse_addr_tehsil_other" 
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" 
					                	 autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Village/Town/City</label>
												</div>
												<div class="col-md-8">
													<%--  <select name="pers_addr_village" id="pers_addr_village" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getVillageList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select> --%>
													<input type="text" id="spouse_addr_village"
														onkeypress="return onlyAlphabets(event);"
														name="spouse_addr_village" maxlength="50" 
														class="form-control autocomplete" autocomplete="off"
														oninput="this.value = this.value.toUpperCase()">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Pin</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="spouse_addr_pin" onkeydown="return AvoidSpace(event)" 
														name="spouse_addr_pin" class="form-control autocomplete"
														autocomplete="off" onkeypress="return isNumber(event)"
														maxlength="6">
												</div>
											</div>
										</div>
									</div>


									<div class="col-md-12">

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Nearest Railway Station</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="spouse_addr_rail"
														name="spouse_addr_rail" class="form-control autocomplete"
														autocomplete="off" maxlength="100"   
														onkeypress="return onlyAlphaNumeric(event);"
														oninput="this.value = this.value.toUpperCase()">
												</div>
											</div>
										</div>
										<div class="col-md-6">
	              					<div class="row form-group">
						             <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Is  Rural /Urban/Semi Urban</label>
						                </div>
						                <div class="col-md-6">
						     <label for="spouse_addr_rural">
							 <input type="radio" id="spouse_addr_rural" name="spouse_addr_rural_urban" value="rural" > Rural</label> 
					    	 <label for="spouse_addr_urban" >
							 <input type="radio" id="spouse_addr_urban" name="spouse_addr_rural_urban" value="urban" > Urban</label> 
					    	 <label for="spouse_addr_semi_urban">
							 <input type="radio" id="spouse_addr_semi_urban" name="spouse_addr_rural_urban" value="semi_urban" >Semi Urban</label> 
						                </div>
<!-- 						                 <div class="popup" onclick="myFunction()"> -->
<!-- 												<i class="fa fa-question-circle" -->
<!-- 													style="font-size: 28px; color: black"></i> <span -->
<!-- 													class="popuptext" id="myPopup">Urban :An urban area -->
<!-- 													is the region surrounding a city. Rural :Rural areas are -->
<!-- 													the opposite of urban areas, Rural areas Rural areas often -->
<!-- 													called the country. Semi Urban : Semi Urban are large -->
<!-- 													residential areas that surround main cities. </span> -->
<!-- 											</div> -->
						            </div>
	              				</div>
									</div>
									<div class="col-md-12">
								<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ SUS No</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_sus_no" name="Stn_HQ_sus_no" class="form-control autocomplete" 
					                	onkeydown="return AvoidSpace(event)" autocomplete="off"   maxlength="8">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Stn HQ Name</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="Stn_HQ_unit_name" name="Stn_HQ_unit_name" class="form-control autocomplete" autocomplete="off" maxlength="100"  >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
								</div>
							</div>
							<div class="col-md-12">
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										NOK</h4></label>
							</div>
							<div class="col-md-12" style="font-size: 15px;">
								<input type="checkbox" id="check_address1" name="check_address1"
									onclick="copy_address1()"> <label for="text-input"
									class=" form-control-label"
									style="color: #dd1a3e; margin-left: 10px;"> Same as
									Permanent Address </label>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> NOK Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="nok_name" name="nok_name"
												class="form-control autocomplete" autocomplete="off"  
												onkeypress="return onlyAlphabets(event);" maxlength="50" 
												oninput="this.value = this.value.toUpperCase()">
												 <input
												type="hidden" id="nok_ch_id" name="nok_ch_id" value="0"
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Relation</label>
										</div>
										<div class="col-md-8">
											<select name="nok_relation" id="nok_relation"
												class="form-control" onchange="fn_otherShowHide(this,'nok_relation_otherDiv','nok_relation_other')">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRelationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>

										</div>
									</div>
								</div>
							</div>
							
								<div class="col-md-12" id="nok_relation_otherDiv" style="display:none">
								<div class="col-md-6">
									
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Other Relation</label>
										</div>
										<div class="col-md-8">
										<input type="text" id="nok_relation_other" name="nok_relation_other"  
										onkeypress="return onlyAlphabets(event);"  class="form-control autocomplete" autocomplete="off" maxlength="50" >

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="nok_country" id="nok_country"
												onchange="fn_nok_Country();"
												class="form-control">
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
											<select name="nok_state" id="nok_state"
												onchange="fn_nok_state();"
												class="form-control">
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
								<div class="col-md-6" id = "nok_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="ctry_other" name="ctry_other" onkeypress="return onlyAlphabets(event);" 
					                	 class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_st" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="st_other" name="st_other" onkeypress="return onlyAlphabets(event);"
					                	 class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="nok_district" id="nok_district"
												onchange="fn_nok_district();"
												class="form-control">
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
												style="color: red;">* </strong> TEHSIL</label>
										</div>
										<div class="col-md-8">
											<select name="nok_tehsil" id="nok_tehsil"
												class="form-control" onchange="fn_nok_tehsil()">
												<option value="0">--Select--</option>
												
											</select>

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6" id = "nok_other_dist" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="dist_other" name="dist_other" onkeypress="return onlyAlphabets(event);" 
					                	 class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "nok_other_te" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nok_tehsil_other" name="nok_tehsil_other" onkeypress="return onlyAlphabets(event);" 
					                	 class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Village/Town/City</label>
										</div>
										<div class="col-md-8">
											<%-- <select name="nok_village" id="nok_village" class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getVillageList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> --%>
											<input type="text" id="nok_village" name="nok_village"
													onkeypress="return onlyAlphabets(event);"  
												oninput="this.value = this.value.toUpperCase()" maxlength="50" 
												class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Pin</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="nok_pin" name="nok_pin" onkeydown="return AvoidSpace(event)" 
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return isNumber(event)" maxlength="6">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Nearest Railway Station</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="nok_near_railway_station"
												name="nok_near_railway_station" maxlength="100" 
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Is Rural/Urban/Semi Urban</label>
										</div>
										<div class="col-md-6">
											<label for="nok_rural"> <input type="radio"
												id="nok_rural" name="nok_rural_urban" value="rural">
												Rural
											</label> <label for="nok_urban"> <input type="radio"
												id="nok_urban" name="nok_rural_urban" value="urban">
												Urban
											</label> <label for="nok_semi_urban"> <input type="radio"
												id="nok_semi_urban" name="nok_rural_urban"
												value="semi_urban">Semi Urban
											</label>
									</div>


<!-- 											<div class="popup" onclick="myFunction()"> -->
<!-- 												<i class="fa fa-question-circle" -->
<!-- 													style="font-size: 28px; color: black"></i> <span -->
<!-- 													class="popuptext" id="myPopup">Urban :An urban area -->
<!-- 													is the region surrounding a city. Rural :Rural areas are -->
<!-- 													the opposite of urban areas, Rural areas Rural areas often -->
<!-- 													called the country. Semi Urban : Semi Urban are large -->
<!-- 													residential areas that surround main cities. </span> -->
<!-- 											</div> -->




										
									</div>
								</div>
								
							</div>
							<div class="col-md-12">
								
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> NOK's Mobile No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="nok_mobile_no" name="nok_mobile_no" onkeydown="return AvoidSpace(event)" 
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return isNumber(event)" maxlength="10">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center" class="form-control">
							<input type="button" class="btn btn-primary btn-sm" value="Save"
								onclick="save_address_details();" id ="save_add" >
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>



	<div id="Pre-Cadet" class="tabcontent" style="display: none;">
		<form id="pre_cadet_details_form">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header">
							<h5> Pre-Cadet Details/NCC Experience</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>

						<div class="card-body card-block">
							<br>

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
											<input type="text" id="designation1" name="designation1" maxlength="50" 
											onkeypress="return onlyAlphabets(event);" 
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
											<input type="text" id="emp_name1" name="emp_name1" maxlength="100" 
												class="form-control autocomplete" autocomplete="off"
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
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Competency</label>
										</div>
										<div class="col-md-8">
										<select name="competency" id="competency"
											class="form-control" onchange="fn_otherShowHide(this,'divcompetency_other','competency_other')"
											>
											<option value="0" >--Select--</option>
											<c:forEach var="item" items="${getSpecializationList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										</div>
									</div>
								</div>
								<div class="col-md-6" id="divcompetency_other" style="display:none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Competency Other</label>
										</div>
										<div class="col-md-8">
										<input type="text" id="competency_other" name="competency_other"  onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off" maxlength="50"  >
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
											<input type="text" id="army_no1" name="army_no1" maxlength="10" 
												class="form-control autocomplete" autocomplete="off"
												onkeydown="return AvoidSpace(event)" onkeypress="return onlyAlphaNumeric(event);">
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
												onkeypress="return AvoidSpace(event);" maxlength="100" 
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
											<!-- 											<input type="date" id="date_form1" name="date_form1" -->
											<%-- 												onchange="calcDate()" max="${date_without_time}" --%>
											<!-- 												class="form-control autocomplete" autocomplete="off"> -->

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
											<!-- 											<input type="date" id="date_to1" name="date_to1" -->
											<%-- 												onchange="calcDate()" max="${date_without_time}" --%>
											<!-- 												class="form-control autocomplete" autocomplete="off"> -->


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
											<input type="text" id="total_rank1" name="total_rank1" maxlength="50" 
												class="form-control autocomplete" autocomplete="off"
												disabled="disabled">
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
						</div>
						<div class="card-footer" align="center" class="form-control">
							<input type="button" class="btn btn-primary btn-sm" value="Save" id ="save_pre_cadet"
								onclick="pre_cadet_save();">
						</div>
					</div>
				</div>
			</div>
		</form>

	</div>




	<div id="Qualifications" class="tabcontent" style="display: none;">
		<div class="card" style="margin-top: 20px;">
			<div class="card-header">
				<h5>Qualifications</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;"></span>
				</h6>
			</div>

			<div class="card_body" style="padding: 10px">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Qualification Type</label>
							</div>
							<div class="col-md-8">
								<select name="quali_type" id="quali_type" class=" form-control"
									onchange="fn_qualification_typeChange(this,'quali','quali_degree','specialization')">
								<option value="0" >--Select--</option>
									<c:forEach var="item" items="${getQualificationTypeList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>

								</select> <input type="hidden" id="qualification_ch_id"
									name="qualification_ch_id" value="0"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>

					</div>

					<div class="col-md-6" id="academyQuali">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Examination Passed</label>
							</div>
							<div class="col-md-8">
								<select name="quali" id="quali" class=" form-control"
									onchange="fn_ExaminationTypeChange(this,'quali_degree','specialization');fn_other_exam();">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>

					</div>
				</div>
                    <div class="col-md-12">
						<div class="col-md-6" id ="other_div_exam" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Examination Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="exam_other" name="exam_other" maxlength="50"  
					                	onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" >
					                	 </div>
						            </div>
	              				</div>	   
                        </div>
				<div class="col-md-12">
				

					<div class="col-md-6" id="courseNamediv" >
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Degree Acquired</label>
							</div>
							<div class="col-md-8">
								<select name="quali_degree" id="quali_degree"
									class=" form-control" onchange="fn_otherShowHide(this,'other_div_qualiDeg','quali_deg_other')">

									<option value="0">--Select--</option>
									
									
								</select>
							</div>
						</div>

					</div>
					<div class="col-md-6" id="specializationDiv" >
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Specialization</label>
							</div>
							<div class="col-md-8">
								<select name="specialization" id="specialization"
									class="form-control" onchange="fn_otherShowHide(this,'other_div_qualiSpec','quali_spec_other')">
									<option value="0">--Select--</option>
								<c:forEach var="item" items="${getSpecializationList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
								</select>
							</div>
						</div>

					</div>

				</div>
				 <div class="col-md-12">
						<div class="col-md-6" id ="other_div_qualiDeg" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Degree Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="quali_deg_other" name="quali_deg_other" onkeypress="return onlyAlphabets(event);" 
					                		class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>	   
	              				
	              				<div class="col-md-6" id ="other_div_qualiSpec" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Specialization Other  </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="quali_spec_other" name="quali_spec_other" onkeypress="return onlyAlphabets(event);"
					                		class="form-control autocomplete" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
                        </div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Year of Passing</label>
							</div>
							<div class="col-md-8">

								<input type="text" id="yrOfPass" name="yrOfPass" 	onkeypress = "return isNumber(event)" onkeydown="return AvoidSpace(event)"
									class="form-control autocomplete" autocomplete="off"
									maxlength="4" />
							</div>
						</div>

					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Div/Grade</label>
							</div>
							<div class="col-md-8">
								<select name="div_class" id="div_class"
									class=" form-control"  onchange="fn_other_class();">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getDivclass}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
				</div>
				
					<div class="col-md-12" id ="other_div_class" style="display: none;" >
				       <div class="col-md-6" >
	              					<div class="row form-group">
						               <div class="col-md-4" align="left">
						                    <label class=" form-control-label"><strong
									style="color: red;">* </strong>Div/Grade Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="class_other" name="class_other" class="form-control autocomplete"
					                		onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              		</div>	   
					
				</div>		

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Subjects</label>
							</div>
							<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showCheckboxes()">
										<select name="sub_quali" id="sub_quali"
											class=" form-control">
											<option>Select Subjects</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="quali_sub_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Subjects">
										</div>
										<div>
											<c:forEach var="item" items="${getSubject}" varStatus="num">
												<label for="one" class="quali_subjectlist"> <input
													type="checkbox" name="multisub" value="${item[0]}" />
													${item[1]}
												</label>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>Institute & Place</label>
							</div>
							<div class="col-md-8">
								<div class="row">
									<input type="text" id="institute_place" name="institute_place"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" maxlength="100" 
										oninput="this.value = this.value.toUpperCase()">
								</div>

							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-4" align="left">
								<label class=" form-control-label"><strong
									style="color: red;"></strong>Selected Subject</label>
							</div>
							<div class="col-md-8">
								<div class="row">

									<div id="quali_sub_list"
										style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

									</div>

								</div>

							</div>
						</div>





					</div>

				</div>
				
				<!--  bisag 180822 v1 (Added as per miso sir guidance) -->
					<div class="col-md-12">
			<div class="col-md-6">
						<div class="row form-group">
							
									<strong style="color: red;">Note:-<span style="text-decoration:underline">PROFESSIONAL COURSES-</span> THOSE COURSE WHICH PROVIDES YOU WITH PRACTICAL SKILL, MAKING YOU JOB READY AFTER COMPLETION OF COURSE.</strong>
							
			<strong style="color: red;">Note:-<span style="text-decoration:underline">ACADEMIC COURSES-</span> GIVES YOU A STRONG ACADEMIC FOUNDATION ON A PARTICULAR SUBJECT BUT NOT NECESSARIALY MAKING YOU JOB READY.</strong>					
								
							</div>

						</div>
						
							<div class="col-md-6">
							</div>
					</div>





			</div>
			<div class="card-footer" align="center" class="form-control">
				<input type="button" class="btn btn-primary btn-sm" value="Save"
					onclick="qualification_save_fn();" id ="save_qualification">
			</div>
		</div>



		<table id="qualificationtable"
			class="table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th style="text-align: center; width: 10%;">Ser No</th>
					<th style="text-align: center;">Qualification Type</th>
					<th style="text-align: center;">Examination Passed</th>	
							
					<th style="text-align: center;">Degree Acquired</th>
					
					<th style="text-align: center;">Specialization</th>
					
					<th style="text-align: center;">Year of Passing</th>
					<th style="text-align: center;">Subjects</th>
					<th style="text-align: center;">Div/Grade</th>
					
					<th style="text-align: center;">Institute & Place</th>
					
			        
					<th class="hide-action" style="text-align: center;">Action</th>
				</tr>
			</thead>
			<tbody id="qualificationtbody">

			</tbody>
		</table>
	</div>
</div>

</div>


<div id="language_details" class="tabcontent" style="display: none;">
	<div class="card" style="margin-top: 20px;">
		<div class="card-header" style="text-align: center">
			<h5>Indian Language</h5>
			<h6 class="enter_by">
				<span style="font-size: 12px; color: red;"></span>
			</h6>
		</div>
		<div class="card_body">
			<!-- <div class="card-header"><h5>Indian Language</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div> -->
									<table id="language_table" class="table-bordered " style="margin-top:10px;width: -webkit-fill-available;">

				<thead style="color: white; text-align: center;">
					<tr>
						<th style="width: 2%;">Sr No</th>
						<th style="width: 10%;">Language</th>
						<th style="width: 10%;">Other Language</th>
						<th style="width: 10%;">Language Standard</th>
								<th style="width: 10%;">Other Language Standard</th>
						<th class="hide-action" style="width: 10%;">Action</th>
					</tr>
				</thead>
				<tbody data-spy="scroll" id="langtbody"
					style="min-height: 46px; max-height: 650px; text-align: center;">
					<tr id="tr_lang1">
						<td class="lang_srno" style="width: 2%;">1</td>

						<td style="width: 10%;"><select id="language1" name="language1" onchange="onLanguage(1)" class="form-control autocomplete">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getIndianLanguageList}"
									varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
								</select>
						</td>
						<td style="width: 10%;">
							 <input type="text" id="other_language1" name="other_language1" class="form-control autocomplete"  
							 	 maxlength="50"  readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						</td>

						<td style="width: 10%;">
						 <select  id="lang_std1" name="lang_std1" class="form-control autocomplete" onchange="onLanguage_std(1)" >
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getLanguageSTDList}"
									varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
						</select></td>
						<td style="width: 10%;">
			 <input type="text" id="other_lang_std1" name="other_lang_std1" class="form-control autocomplete"  
			 	 onkeypress="return onlyAlphabets(event);" readonly autocomplete="off" maxlength="50" >
						</td>
						<td style="display: none;"><input type="text"
							id="lang_ch_id1" name="lang_ch_id1" value="0"
							class="form-control autocomplete" autocomplete="off"></td>

						<td class="hide-action" style="width: 10%;">
							<a  class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="language_save1" onclick="language_save_fn(1);"><i
								class="fa fa-save"></i></a> <a style="display: none;"
							class="btn btn-success btn-sm" value="ADD" title="ADD"
							id="language_add1" onclick="language_add_fn(1);"><i
								class="fa fa-plus"></i></a> <a style="display: none;"
							class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
							id="language_remove1" onclick="language_remove_fn(1);"><i
								class="fa fa-trash"></i></a></td>
					</tr>
				</tbody>
			</table>

			<div class="card-header" style="text-align: center">
				<h5>Foreign Language</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;"></span>
				</h6>
			</div>

			<table id="foregin_language_table" class="table-bordered "
				style="margin-top: 10px;">

				<thead style="color: white; text-align: center;">
					<tr>
						<th style="width: 2%;">Sr No</th>
						<th style="width: 10%;">Language</th>
						<th style="width: 10%;">Other Language</th>
						<th style="width: 10%;">Language Standard</th>
						<th style="width: 10%;">Other Language Standard</th>
						<th style="width: 10%;">Language Proficiency</th>
						<th style="width: 10%;">Other Language Proficiency</th>
						<th style="width: 10%;">Passing Year</th>
						<!-- <th style="width: 10%;">Mother Tongue</th> -->
						<th style="width: 10%;" class="hide-action">Action</th>
					</tr>
				</thead>
				<tbody data-spy="scroll" id="flangtbody"
					style="min-height: 46px; max-height: 650px; text-align: center;">
					<tr id="tr_flang1">
						<td class="flang_srno" style="width: 2%;">1</td>

						<td style="width: 10%;">
										 <select  id="flanguage1" name="flanguage1" class="form-control autocomplete" onchange="onF_Language(1)" >
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getForiegnLangugeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td>
										<td style="width: 10%;">
			                      		 <input type="text" id="f_other_language1" name="f_other_language1" maxlength="50"  class="form-control"  
			                      		 	 readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						         		</td>
										<td style="width: 10%;">
										  <select  id="flang_std1" name="flang_std1" class="form-control autocomplete"  onchange="onF_Language_std(1)">
										   <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
											</select>
										</td>
										<td style="width: 10%;">
			                         <input type="text" id="f_other_lang_std1" name="f_other_lang_std1" maxlength="50"  class="form-control autocomplete" 
			                         	 onkeypress="return onlyAlphabets(event);" readonly autocomplete="off" >
						       </td>
										<td style="width: 10%;">
										   <select  id="lang_prof1" name="lang_prof1" class="form-control autocomplete"  onchange="onF_Language_prof(1)">
										  <option value="0">--Select--</option>
											<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										 </select>
										</td>
										<td style="width: 10%;">
			                         <input type="text" id="f_other_prof1" name="f_other_prof1" class="form-control autocomplete" 
			                         	 maxlength="50"  readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						                  </td>
						<td style="width: 10%;"><input type="text" id="exam_pass1"
							name="exam_pass1" class="form-control-sm form-control autocomplete"
							onkeypress="return isNumber(event);"  autocomplete="off" maxlength="4" ></td>

						<td style="display: none;"><input type="text"
							id="flang_ch_id1" name="flang_ch_id1" value="0"
							class="form-control autocomplete" autocomplete="off"></td>

						<td class="hide-action" style="width: 10%;"><a
							class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
							id="foregin_language_save1"
							onclick="foregin_language_save_fn(1);"><i class="fa fa-save"></i></a>
							<a style="display: none;" class="btn btn-success btn-sm"
							value="ADD" title="ADD" id="foregin_language_add1"
							onclick="foregin_language_add_fn(1);"><i class="fa fa-plus"></i></a>
							<a style="display: none;" class="btn btn-danger btn-sm"
							value="REMOVE" title="REMOVE" id="foregin_language_remove1"
							onclick="foregin_language_remove_fn(1);"><i
								class="fa fa-trash"></i></a></td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>


</div>

<div id="Foreign_Countries" class="tabcontent" style="display: none;">
	<div class="card" style="margin-top: 20px;">
		<div class="card-header" style="text-align: center;">
			<h5> Foreign Countries Visited</h5>
			<h6 class="enter_by">
				<span style="font-size: 12px; color: red;"></span>
			</h6>
		</div>
		<div class="card_body">
			<table id="country_visit" class="table-bordered "
				style="margin-top: 10px;">

				<thead style="color: white; text-align: center;">
					<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Country Visited</th> 
										<th style="width: 10%;">Country Visited Other</th>
										<th style="width: 10%;">From</th>
										<th style="width: 10%;">To</th>
										<th style="width: 10%;">Duration</th>
										<th style="width: 10%;">Purpose of Visit</th>
										<th style="width: 10%;">Purpose of Visit Other</th>
										<th style="width: 10%;">Action</th>
					</tr>
				</thead>
				<tbody data-spy="scroll" id="foregin_Country_tbody"
					style="min-height: 46px; max-height: 650px; text-align: center;">
					<tr id="tr_foregin_country1">
						<td class="fcon_srno" style="width: 2%;">1</td>

						<td style="width: 10%;"><select name="country1" id="country1" onchange="onContryVisited(1)"
							class="form-control">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getForiegnCountryList}"
									varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
						</select></td>
						<td style="width: 10%;">
							  <input type="text" id="foregin_Country_ot1" name="foregin_Country_ot1" 	 
							  class="form-control autocomplete"  readonly autocomplete="off" onkeypress="return onlyAlphabets(event);" maxlength="50">
						</td>


						<td style="width: 10%;">
							<!-- 						<input type="date" id="from_dt1" --> <!-- 							name="from_dt1" class="form-control autocomplete" -->
							<!-- 							autocomplete="off" onchange="calcDate22(1)" --> <%-- 							max="${date_without_time}"> --%>

							<input type="text" name="from_dt1" id="from_dt1" maxlength="10"
							onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
							style="width: 85%; display: inline;"
							onfocus="this.style.color='#000000'"
							onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
							onkeyup="clickclear(this, 'DD/MM/YYYY')"
							onchange="clickrecall(this,'DD/MM/YYYY');calcDate22(1);validateDate_FutureDate(this.value,this);"
							aria-required="true" autocomplete="off"
							style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">


						</td>

						<td style="width: 5%;">
							<!-- 						<input type="date" id="to_dt1" --> <!-- 							name="to_dt1" class="form-control autocomplete" -->
							<!-- 							autocomplete="off" onchange="calcDate22(1)" --> <%-- 							max="${date_without_time}"> --%>



							<input type="text" name="to_dt1" id="to_dt1" maxlength="10"
							onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
							style="width: 85%; display: inline;"
							onfocus="this.style.color='#000000'"
							onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
							onkeyup="clickclear(this, 'DD/MM/YYYY')"
							onchange="clickrecall(this,'DD/MM/YYYY');calcDate22(1);validateDate_FutureDate(this.value,this); "
							aria-required="true" autocomplete="off"
							style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

						</td>


						<td style="width: 10%;"><input type="text" id="period1"
							name="period1" class="form-control autocomplete" maxlength="50" 
							autocomplete="off" readonly="readonly"></td>


						<td style="width: 5%;">
							<!-- <input type="text" id="purpose_visit1" name="purpose_visit1" class="form-control autocomplete" autocomplete="off" >  -->
							<select name="purpose_visit1" id="purpose_visit1" onchange="onPurposeVisited(1)"
							class="form-control">
								<option value="0">--Select--</option>
								<c:forEach var="item" items="${getForeign_country}"
									varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
						</select>
						</td>
						
						<td style="width: 10%;">
							<input type="text" id="purpose_visit_ot1" name="purpose_visit_ot1" class="form-control autocomplete" 
								 maxlength="50"  readonly autocomplete="off" onkeypress="return onlyAlphabets(event);">
						</td>

						<td style="display: none;"><input type="text"
							id="foregin_country_ch_id1" name="foregin_country_ch_id1"
							value="0" class="form-control autocomplete" autocomplete="off"></td>
						<td class="hide-action" style="width: 10%;"><a
							class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
							id="country_save1" onclick="country_save_fn(1);"><i
								class="fa fa-save"></i></a> <a style="display: none;"
							class="btn btn-success btn-sm" value="ADD" title="ADD"
							id="country_add1" onclick="country_add_fn(1);"><i
								class="fa fa-plus"></i></a> <a style="display: none;"
							class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
							id="country_remove1" onclick="country_remove_fn(1);"><i
								class="fa fa-trash"></i></a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>


</div>
<div id="family_details" class="tabcontent" style="display: none;">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Family Details</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;"></span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<label class=" form-control-label" style="margin-right: 100px;"><h4>Father's
								Details</h4></label>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Father's Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="father_name" name="father_name" class="form-control autocomplete" autocomplete="off"
									maxlength="50" 	onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Father's Date of Birth</label>
								</div>
								<div class="col-md-8">
									<!-- 									<input type="date" id="father_dob" name="father_dob" -->
									<!-- 										class="form-control autocomplete" autocomplete="off"> -->
									<input type="text" name="father_dob" id="father_dob"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">


								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Father's Aadhaar No.</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="father_adhar_number"
										name="father_adhar_number" class="form-control autocomplete"
										maxlength="14" onkeypress="return isAadhar(this,event); " 	onkeydown="return AvoidSpace(event)"
										autocomplete="off">


								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>Father's PAN No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="father_pan_no" name="father_pan_no"
										class="form-control autocomplete" autocomplete="off"
										onchange=" isPAN(this); " 	onkeydown="return AvoidSpace(event)"
										oninput="this.value = this.value.toUpperCase()" maxlength="10">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Father's Place of Birth</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="father_place" name="father_place"
										class="form-control autocomplete" autocomplete="off" 	
										onkeypress="return onlyAlphabets(event);" maxlength="50" 
										oninput="this.value = this.value.toUpperCase()">


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
											style="color: red;"> </strong>Father In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="father_proff_radio1"
											name="father_proff_radio" value="yes"
											onchange="father_proffFn();">&nbsp <label
											for="yes">Yes</label>&nbsp 
											<input type="radio"
											id="father_proff_radio2" name="father_proff_radio" value="no"
											onchange="father_proffFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


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
											<label class=" form-control-label"><strong
												style="color: red;"> </strong>Father's Services</label>
										</div>
										<div class="col-md-8">
											<select name="father_service" id="father_service"
												class="form-control" onchange="ServiceOtherFn('father_otherDiv','father_personalDiv',this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>


										</div>
									</div>
								</div>
								<div class="col-md-6" id="father_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8"> 
											<input type="text" id="father_other" name="father_other" onkeydown="return AvoidSpace(event)"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);" maxlength="50" 
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
								<div class="col-md-6" id="father_personalDiv">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong>Father's Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="father_personal_no"
												name="father_personal_no" class="form-control autocomplete"
												autocomplete="off" maxlength="9" min="7" 	onkeydown="return AvoidSpace(event)"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>

							</div>
							<div class="col-md-6" id="father_proffDiv">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"> </strong>Father's Profession</label>
									</div>
									<div class="col-md-8">
										<!--   <input type="text" id="father_profession" name="father_profession" class="form-control autocomplete" autocomplete="off"  >  -->

										<select name="father_profession" id="father_profession"
											class="form-control" onchange="fn_otherShowHide(this,'father_proffotherDiv','father_proffother')">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getProfession}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="father_proffotherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="father_proffother" name="father_proffother"
												class="form-control autocomplete" autocomplete="off" maxlength="50" 
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" 	onkeydown="return AvoidSpace(event)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
							
						</div>



						<div class="col-md-12">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Mother's
									Details</h4></label>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother's Name</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="mother_name" name="mother_name"
											class="form-control autocomplete" autocomplete="off"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()">


									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"> </strong>Mother's Date of Birth</label>
									</div>
									<div class="col-md-8">
										<!-- 									<input type="date" id="mother_dob" name="mother_dob" -->
										<!-- 										class="form-control autocomplete" autocomplete="off"> -->

										<input type="text" name="mother_dob" id="mother_dob"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);">


									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"> </strong>Mother's Aadhaar No.</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="mother_adhar_number" 	onkeydown="return AvoidSpace(event)"
											name="mother_adhar_number" class="form-control autocomplete"
											maxlength="14" onkeypress="return isAadhar(this,event); "
											autocomplete="off">


									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"> </strong>Mother's PAN No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="mother_pan_no" name="mother_pan_no"
											class="form-control autocomplete" autocomplete="off"
											onchange=" isPAN(this); " 	onkeydown="return AvoidSpace(event)"
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
											style="color: red;"> </strong>Mother's Place of Birth</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="mother_place" name="mother_place"
											class="form-control autocomplete" autocomplete="off" 	
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
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
											style="color: red;"> </strong>Mother In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="mother_proff_radio1"
											name="mother_proff_radio" value="yes"
											onchange="mother_proffFn();">&nbsp <label
											for="yes">Yes</label>&nbsp <input type="radio"
											id="mother_proff_radio2" name="mother_proff_radio" value="no"
											onchange="mother_proffFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


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
											<label class=" form-control-label"><strong
												style="color: red;"> </strong>Mother's Services</label>
										</div>
										<div class="col-md-8">
											<select name="mother_service" id="mother_service"
												class="form-control "
												onchange="ServiceOtherFn('mother_otherDiv','mother_personalDiv',this)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>


										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_otherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_other" name="mother_other" onkeydown="return AvoidSpace(event)"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);" maxlength="50" 
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_personalDiv">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong>Mother's Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_personal_no"
												name="mother_personal_no" class="form-control autocomplete"
												autocomplete="off"
												maxlength="9" min="7" onkeydown="return AvoidSpace(event)"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="mother_proffDiv">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;"> </strong>Mother's Profession</label>
									</div>
									<div class="col-md-8">
										<select name="mother_profession" id="mother_profession"
											class="form-control " onchange="fn_otherShowHide(this,'mother_otherproffDiv','mother_proffother')">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getProfession}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="mother_otherproffDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_proffother" name="mother_proffother"
												class="form-control autocomplete" autocomplete="off" maxlength="50" 
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" 	onkeydown="return AvoidSpace(event)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
						</div>


						<div class="col-md-4">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details of Siblings</h4></label>
						</div>
						<br>
						<div class="col-md-12 watermarked" style="display: block;">
							<table id="family_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Name</th>
										<th>Date of Birth</th>
										<th>Relationship</th>
										<th>Aadhaar No</th>
										<th>PAN No</th>
										<th>Service/Ex-Service</th>
										<th>Services</th>
										<th>Personal No.</th>
										<th>Other Service</th>
										<th class="hide-action">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_sibtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno">1</td>
										<td><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>
										<td>
											<input
											type="text" name="sib_date_of_birth1" id="sib_date_of_birth1"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

										</td>
										<td><select name="sib_relationship1"
											id="sib_relationship1" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFamily_siblings}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select></td>

										<td><input type="text" 	onkeydown="return AvoidSpace(event)"
											id="sib_adhar_number1" name="sib_adhar_number1"
											class="form-control autocomplete" maxlength="14"
											onkeypress="return isAadhar(this,event); " autocomplete="off"></td>

										<td><input type="text" 	onkeydown="return AvoidSpace(event)"
											id="sib_pan_no1" name="sib_pan_no1"
											class="form-control autocomplete" autocomplete="off"
											onchange=" isPAN(this); "
											oninput="this.value = this.value.toUpperCase()"
											maxlength="10"></td>

										<td style="display: none;"><input type="text"
											id="sib_ch_id1" name="sib_ch_id1" value="0"
											class="form-control autocomplete" autocomplete="off"></td>
										<td>
										<input type="checkbox" id="ser-ex1" name="ser-ex1" value="Yes" onclick="siblingCB(1);">
										</td>
										<td><select name="sibling_service1" id="sibling_service1" onchange = "otherfunction(1)"
												class="form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="sibling_personal_no1"
												name="sibling_personal_no1" class="form-control autocomplete"
												autocomplete="off"
												maxlength="9" min="7" onkeydown="return AvoidSpace(event)"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_sibling_ser1"
												name="other_sibling_ser1" class="form-control autocomplete"
												autocomplete="off" maxlength="50" 	
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td class="hide-action"><a
											class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
											id="family_save1" onclick="family_save_fn(1);"><i
												class="fa fa-save"></i></a> <a style="display: none;"
											class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="family_add1" onclick="family_add_fn(1);"><i
												class="fa fa-plus"></i></a> <a style="display: none;"
											class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
											id="family_remove1" onclick="family_remove_fn(1);"><i
												class="fa fa-trash"></i></a></td>
									</tr>
								</tbody>
							</table>
						</div>

						<div id="fill_marraige_div" style="display: none">
							<div class="col-md-6" id=""
								style="margin-top: 10px; width: 100%;">
								<label class=" form-control-label" style="margin-right: 100px;"><h4>Details	of Spouse</h4></label>

							</div>
							<br>
							<div class="col-md-12 watermarked" style="display: block;">

								<table id="married_table" class="table-bordered"
									style="margin-top: 10px; width: 100%;">

									<thead style="color: white; text-align: center;">
										<tr>
											<th>Sr No</th>
											<th >Name</th>
											<th >Date of Birth</th>
											<th >Place of Birth</th>
											<th >Nationality</th>
											<th >Other Nationality</th>
											<th >Date of Marriage</th>
											<th>Aadhaar No</th>
											<th >PAN No</th>
											<th>Service/Ex-Service</th>
											<th>Services</th>
											<th>Personal No.</th>
											<th>Other Service</th>	
											<th style="width: 20%" id="th_spouseprof">Spouse Profession</th>										
											<th class="sepratedSpouseClass">From Date</th>

											<th class="hide-action">Action</th>
										</tr>
									</thead>
									<tbody data-spy="scroll" id="family_marrtbody"
										style="min-height: 46px; max-height: 650px; text-align: center;">
										<tr id="tr_marriage1">
											<td class="marr_srno">1</td>
											<td><input type="text"
												id="maiden_name1" name="maiden_name1"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphabets(event);" maxlength="50" 
												oninput="this.value = this.value.toUpperCase()"></td>
											<td >
												<input type="text" name="marriage_date_of_birth1"
												id="marriage_date_of_birth1" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

											</td>
											<td >
												 <input type="text"
												id="marriage_place_of_birth1"
												name="marriage_place_of_birth1" 
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphabets(event);" maxlength="50" 
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td ><select
												name="marriage_nationality1" id="marriage_nationality1" onchange="marriageNationChange(1)"
												class="form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
											</select></td>
											
											<td ><input type="text" id="marriage_othernationality1" name="marriage_othernationality1" maxlength="50" 
												class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"
													oninput="this.value = this.value.toUpperCase()" readonly="readonly">
												</td>
											
											<td >
												<input	type="text" name="marriage_date1" id="marriage_date1"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">


											</td>
											<td ><input type="text"
												id="spouse_adhar_number1" name="spouse_adhar_number1"
												class="form-control autocomplete" maxlength="14"
												onkeypress="return isAadhar(this,event); " 	onkeydown="return AvoidSpace(event)"
												autocomplete="off"></td>


											<td><input type="text" id="spouse_pan_number1"
												name="spouse_pan_number1" class="form-control autocomplete"
												autocomplete="off" onchange=" isPAN(this); " 	onkeydown="return AvoidSpace(event)"
												oninput="this.value = this.value.toUpperCase()"
												maxlength="10"></td>

											<td style="display: none;"><input type="text"
												id="marr_ch_id1" name="marr_ch_id1" value="0"
												class="form-control autocomplete" autocomplete="off"></td>
										<td>
										<input type="checkbox" id="spo-ex1" name="spo-ex1" value="Yes" onclick="spouseCB(1);">
										</td>
										<td><select name="spouse_service1" id="spouse_service1" onchange="otherfunction(1)"
												class="form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getExservicemenList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td><input type="text" id="spouse_personal_no1"
												name="spouse_personal_no1" class="form-control autocomplete"
												autocomplete="off"
												maxlength="9" min="7" onkeydown="return AvoidSpace(event)"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_spouse_ser1"
												name="other_spouse_ser1" class="form-control autocomplete"
												autocomplete="off" maxlength="50" 	onkeydown="return AvoidSpace(event)"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td style="width: 20%" id="spouse_proffDiv"><select name="spouse_profession1" id="spouse_profession1"
												class="form-control"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getProfession}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select></td>
											<td class="sepratedSpouseClass">
												<input	 type="text" name="sep_from_date1" id="sep_from_date1"
												maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">


											</td>
											
											<td class="hide-action"><a
												class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
												id="married_save1" onclick="married_save_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
												id="married_remove1" onclick="married_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</tbody>
								</table>
							</div>



						<div class="col-md-12" id="spouse_quali_radioDiv"
							style="margin-top: 10px; width: 100%;">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Any
									Qualification Details of Spouse</h4><strong
								style="color: red;">Note:-Only Highest qualification should be fill.</strong></label> &nbsp<input type="radio"
								id="spouse_quali_radio1" name="spouse_quali_radio" value="yes"
								onchange="spouse_quali_radioFn();">&nbsp <label
								for="yes">Yes</label>&nbsp <input type="radio"
								id="spouse_quali_radio2" name="spouse_quali_radio" value="no"
								onchange="spouse_quali_radioFn();" checked="checked">&nbsp
							<label for="no">No</label><br>

						</div>

							<div id="spouse_Qualifications"
								style="margin-top: 20px; display: none">

								<div class="card" style="margin-top: 20px;">
									<br>
									<div class="card_body" style="padding: 10px">
										<div class="col-md-12">

											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Qualification Type</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali_type" id="spouse_quali_type"
															class=" form-control"
															onchange="fn_qualification_typeChange(this,'spouse_quali','quali_degree_spouse','spouse_specialization')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getQualificationTypeList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>

														</select> <input type="hidden" id="spouse_qualification_ch_id"
															name="spouse_qualification_ch_id" value="0"
															class="form-control autocomplete" autocomplete="off">
													</div>
												</div>

											</div>
											<div class="col-md-6" id="academyQuali">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Examination Passed</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_quali" id="spouse_quali"
															class=" form-control"
															onchange="fn_ExaminationTypeChange(this,'quali_degree_spouse','spouse_specialization');fn_otherShowHide(this,'other_div_examSpouse','exam_otherSpouse')">
															<option value="0">--Select--</option>

														</select>
													</div>
												</div>

											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6" id="other_div_examSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Examination Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="exam_otherSpouse" onkeypress="return onlyAlphabets(event);"
															name="exam_otherSpouse" class="form-control autocomplete"
															autocomplete="off" maxlength="50"  	onkeydown="return AvoidSpace(event)">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Degree Acquired</label>
													</div>
													<div class="col-md-8">
														<select name="quali_degree_spouse" id="quali_degree_spouse"
															class=" form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiDegSpouse','quali_deg_otherSpouse')">

															<option value="0">--Select--</option>

														</select>
													</div>
												</div>

											</div>
											<div class="col-md-6" id="specializationDiv">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Specialization</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_specialization"
															id="spouse_specialization" class="form-control"
															onchange="fn_otherShowHide(this,'other_div_qualiSpecSpouse','quali_spec_otherSpouse')">
															<option value="0">--Select--</option>
												<c:forEach var="item" items="${getSpecializationList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
														</select>
													</div>
												</div>

											</div>


										</div>
										<div class="col-md-12">
											<div class="col-md-6" id="other_div_qualiDegSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Degree Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_deg_otherSpouse" onkeypress="return onlyAlphabets(event);"
															name="quali_deg_otherSpuse" maxlength="50" 	onkeydown="return AvoidSpace(event)"
															class="form-control autocomplete" autocomplete="off">
													</div>
												</div>
											</div>

											<div class="col-md-6" id="other_div_qualiSpecSpouse"
												style="display: none;">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Specialization Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="quali_spec_otherSpouse" onkeypress="return onlyAlphabets(event);"
															name="quali_spec_otherSpouse" maxlength="50" 	onkeydown="return AvoidSpace(event)"
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
															style="color: red;">* </strong>Year of Passing</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_yrOfPass"	onkeydown="return AvoidSpace(event)"
															name="spouse_yrOfPass" class="form-control autocomplete"
															autocomplete="off" onkeypress="return isNumber(event)"
															maxlength="4" />
													</div>
												</div>

											</div>

											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_div_class" id="spouse_div_class"
															class="form-control " onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getDivclass}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>

											</div>


										</div>

										<div class="col-md-12" id="other_div_classSpouse"
											style="display: none;">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4" align="left">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Div/Grade Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="class_otherSpouse" name="class_otherSpouse" 
															 onkeypress="return onlyAlphaNumeric(event);"
															class="form-control autocomplete" autocomplete="off" maxlength="50" >
													</div>
												</div>
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Subjects</label>
													</div>
													<div class="col-md-8">
														<div class="multiselect">

															<div class="selectBox" onclick="showCheckboxesSpouse()">
																<select name="spouse_sub_quali" id="spouse_sub_quali"
																	class="form-control">
																	<option>Select Subjects</option>
																</select>
																<div class="overSelect"></div>
															</div>
															<div id="spouse_checkboxes" class="checkboxes" style="max-height: 200px; overflow: auto;">
																<input type="text" id="spouse_searchSubject"
																	name="spouse_searchSubject"
																	placeholder="Search Subjects" maxlength="50" 
																	class="form-control autocomplete" autocomplete="off">
																<c:forEach var="item" items="${getSubject}"
																	varStatus="num">
																	<label for="one" class="spouse_subjectlist"> <input
																		type="checkbox" name="spouse_multisub"
																		value="${item[0]}" /> ${item[1]}
																	</label>
																</c:forEach>
															</div>

														</div>
													</div>

												</div>
											</div>
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong>Institute & Place</label>
													</div>
													<div class="col-md-8">

														<input type="text" id="spouse_institute_place"
															name="spouse_institute_place" maxlength="100" 
															class="form-control autocomplete" autocomplete="off"
															onkeypress="return onlyAlphabets(event);"
															oninput="this.value = this.value.toUpperCase()">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;"></strong>Selected Subject</label>
													</div>
													<div class="col-md-8">
														<div class="row">

															<div id="spouse_quali_sub_list"
																style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">

															</div>

														</div>

													</div>
												</div>

											</div>


										</div>





									</div>
									<div class="" align="right" class="form-control">
										<input type="button" class="btn btn-primary btn-sm"
											value="Save" style="margin-right: 30px"
											onclick="spouse_qualification_save_fn();">
									</div>
								</div>
							</div>
						</div>
					</div>



					<div id="fill_divorce_div" style="display: none">
						<div class="col-md-6">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details
									of Divorce/Widow/Widower</h4></label> <br> <span
								style="font-size: 15px; color: red;">(*Note: Please fill
								up the details if your current or previous status is/was
								Divorced/Widow/Widower)</span>

						</div>
						<div class="col-md-12" style="display: block;">
							<table id="divorce_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th>Marital Event</th>
										<th>Name</th>
										<th >Date of Birth</th>
										<th >Place of Birth</th>
										<th>Nationality</th>
										<th >Other Nationality</th>
										<th >Date of Marriage</th>
										<th >Aadhaar No</th>
										<th>PAN No</th>
										<th >Date of Divorce/Widow/Widower</th>
										<th class="hide-action" >Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_divotbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_divorce1">
										<td class="divosr_no" style="width: 2%;">1</td>
										<td><select name="marital_event1"
											id="marital_event1"
											class="form-control " >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_statusList}"
													varStatus="num">
													<c:if test="${item[0]==9 || item[0]==11 || item[0]==12}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:if>
												</c:forEach>
										</select></td>
										<td><input type="text" id="spouse_name1" maxlength="50" 
											name="spouse_name1" class="form-control autocomplete"
											autocomplete="off" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"></td>
										<td>
											<!-- 										<input type="date" id="divorce_date_of_birth1" -->
											<!-- 											name="divorce_date_of_birth1" --> <!-- 											class="form-control autocomplete" autocomplete="off" -->
											<%-- 											max="${date_without_time}"> --%> <input
											type="text" name="divorce_date_of_birth1"
											id="divorce_date_of_birth1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">


										</td>
										<td><input type="text" id="divorce_place_of_birth1"
											name="divorce_place_of_birth1" maxlength="50" 
											class="form-control autocomplete" autocomplete="off"
											onkeypress="return onlyAlphabets(event);" 	
											oninput="this.value = this.value.toUpperCase()"></td>

										<td><select name="divorce_nationality1"
											id="divorce_nationality1"
											class="form-control " onchange="divorceNationChange(1)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNationalityList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select></td>
										<td ><input type="text" id="divorce_othernationality1" name="divorce_othernationality1" maxlength="50" 
												class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" 
												oninput="this.value = this.value.toUpperCase()" readonly="readonly" >
										</td>

										<td>
											<!-- 										<input type="date" id="divorce_marriage_date1" -->
											<!-- 											name="divorce_marriage_date1" --> <!-- 											class="form-control autocomplete" autocomplete="off" -->
											<%-- 											max="${date_without_time}"> --%> <input
											type="text" name="divorce_marriage_date1"
											id="divorce_marriage_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

										</td>
										<td><input type="text" id="divorce_spouse_adhar_number1"
											name="divorce_spouse_adhar_number1" 	onkeydown="return AvoidSpace(event)"
											onkeypress="return isAadhar(this,event);"
											class="form-control autocomplete" maxlength="14"
											autocomplete="off"></td>


										<td><input type="text" id="divorce_spouse_pan_number1"
											name="divorce_spouse_pan_number1" 	onkeydown="return AvoidSpace(event)"
											class="form-control autocomplete" autocomplete="off"
											onchange=" isPAN(this); "
											oninput="this.value = this.value.toUpperCase()"
											maxlength="10"></td>

										<td><input type="text" name="divorce_date1"
											id="divorce_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style="display: none;"><input type="text"
											id="divo_ch_id1" name="divo_ch_id1" value="0"
											class="form-control autocomplete" autocomplete="off"></td>

										<td class="hide-action"><a class="btn btn-primary btn-sm"
											value="SAVE" title="SAVE" id="divorce_save1"
											onclick="divorce_save_fn(1);"><i class="fa fa-save"></i></a>
											<a style="display: none;" class="btn btn-success btn-sm"
											value="ADD" title="ADD" id="divorce_add1"
											onclick="divorce_add_fn(1);"><i class="fa fa-plus"></i></a> <a
											style="display: none;" class="btn btn-danger btn-sm"
											value="REMOVE" title="REMOVE" id="divorce_remove1"
											onclick="divorce_remove_fn(1);"><i class="fa fa-trash"></i></a></td>
									</tr>
								</tbody>
							</table>

						</div>
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<input type="button" class="btn btn-primary btn-sm" value="Save" id ="save_family_details_btn"
						onclick="save_family_details();"> 
				</div>

			</div>
		</div>
	</div>
</div>




<div id="medical_details" class="tabcontent" style="display: none;">
	<!-- START MEDICAL -->
	<form id="madical_category_form">

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
				<div style="    width: -webkit-fill-available; text-align: -webkit-center;">
					<strong style="font-size: x-large;text-align: center;">For Any Modification pls Click Here </strong>
							<a class="btn btn-danger btn-sm" value="DELETE" title="Reset All Medical Details " id="cCope_remove6" onclick="AllCoperemove_fn();"><i style="font-size: x-large;" class="fa fa-trash"></i></a>
							</div>
							</br>
							</br>
							</br>
					<input type="hidden" id="mad_classification_count"
						name="mad_classification_count" value="NIL">
					<div class="col-md-12">
						<div class="row form-group">
							<div class="col-md-12" style="font-size: 15px; margin: 10px;">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX"> <label
									for="text-input" class=" form-control-label"
									style="margin-left: 10px;"> <strong> SHAPE 1BX
								</strong>
								</label>
							</div>
						</div>
						<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"
							value="0" class="form-control autocomplete" autocomplete="off">
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
									<input type="text" name="1bx_diagnosis_code" maxlength="255" 
										id="1bx_diagnosis_code" class="form-control"
										autocomplete="off" placeholder="Search..."
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
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style=""><input type="text" name="s_to_date1"
											id="s_to_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
										<td style="display: none;" class="diagnosisClass11"><input
											type="text" name="_diagnosis_code11" id="_diagnosis_code11"
											class="form-control" autocomplete="off"
											placeholder="Search..." maxlength="255" 
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

										<td style="width: 10%; display: none;" class="addbtClass11">
											<a class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="sShape_add1" onclick="sShape_add_fn(1);"><i
												class="fa fa-plus"></i></a>
										</td>
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
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style=""><input type="text" name="h_to_date1"
											id="h_to_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
										<td style="display: none;" class="diagnosisClass21"><input
											type="text" name="_diagnosis_code21" id="_diagnosis_code21"
											class="form-control" autocomplete="off"
											placeholder="Search..." maxlength="255" 
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
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style=""><input type="text" name="a_to_date1"
											id="a_to_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
										<td style="display: none;" class="diagnosisClass31"><input
											type="text" name="_diagnosis_code31" id="_diagnosis_code31"
											class="form-control" autocomplete="off"
											placeholder="Search..." maxlength="255" 
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
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style=""><input type="text" name="p_to_date1"
											id="p_to_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
										<td style="display: none;" class="diagnosisClass41"><input
											type="text" name="_diagnosis_code41" id="_diagnosis_code41"
											class="form-control" autocomplete="off"
											placeholder="Search..." maxlength="255" 
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
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>

										<td style=""><input type="text" name="e_to_date1"
											id="e_to_date1" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
										<td style="display: none;" class="diagnosisClass51"><input
											type="text" name="_diagnosis_code51" id="_diagnosis_code51"
											class="form-control" autocomplete="off"
											placeholder="Search..." maxlength="255" 
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
										<th style="width: 70%;">Value & Description</th>
										<th style="width: 14%;display: none;" class="cCopClass">Other</th>
										<th style="width: 14%;display: none;" class="CopaddbtClass1">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="c_cmadtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_cCope1">
										<td style="width: 2%;">1</td>
										<td style="width: 70%;"><select name="c_cvalue1" id="c_cvalue1"
											onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
											class="form-control">
												<c:forEach var="item" items="${getcCopeList}"
													varStatus="num">
													<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
												</c:forEach>
										</select></td>

										<td style="width: 14%;display: none;" class="cCopClass1"><input
											type="text" name="c_cother1" id="c_cother1"
											class="form-control" autocomplete="off">
										</td>

										<td style="display: none;"><input type="text"
											id="cCope_ch_id1" name="cCope_ch_id1" value="0"
											class="form-control autocomplete" autocomplete="off"></td>

										<td style="width: 14%; display: none;" class="CopaddbtClass11">
											<a class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="cCope_add1" onclick="cCope_add_fn(1);"><i
												class="fa fa-plus"></i></a>
										</td>
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
										<th style="width: 10%; display: none;" class="CopaddbtClass2">Action</th>
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

										<td style="width: 10%; display: none;" class="CopaddbtClass21">
											<a class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="oCope_add1" onclick="oCope_add_fn(1);"><i
												class="fa fa-plus"></i></a>
										</td>
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
										<th style="width: 10%; display: none;" class="CopaddbtClass3">Action</th>
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

										<td style="width: 10%; display: none;" class="CopaddbtClass31">
											<a class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="pCope_add1" onclick="pCope_add_fn(1);"><i
												class="fa fa-plus"></i></a>
										</td>
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
										<th style="width: 10%;display: none;" class="eCop_otherClass">Other</th>
										<th style="width: 10%; display: none;" class="CopaddbtClass4">Action</th>
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
											onchange="onECopeSubChange(this,1)"
											class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getesubCopeList}"
													varStatus="num">
													<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
												</c:forEach>
										</select></td>
										<td style="display: none;" class="eCop_otherClass1"><input
											type="text" name="c_esubvalueother1" id="c_esubvalueother1" maxlength="50" 
											class="form-control" autocomplete="off">
										</td>

										<!-- 						                                <td style="" > -->
										<!-- 														 <input type="text" name="c_edescription1" id="c_edescription1" class="form-control-sm form-control" autocomplete="off"  -->
										<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
										<!-- 														   </td> -->


										<td style="display: none;"><input type="text"
											id="eCope_ch_id1" name="eCope_ch_id1" value="0"
											class="form-control autocomplete" autocomplete="off"></td>

										<td style="width: 10%; display: none;" class="CopaddbtClass41">
											<a class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="eCope_add1" onclick="eCope_add_fn(1);"><i
												class="fa fa-plus"></i></a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="card-footer" align="center" class="form-control">
					<input type="button" class="btn btn-primary btn-sm" value="Save" id ="save_medical_cat"
						onclick="return medical_cat_save();">
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
	<!-- END MEDICAL -->
</div>

<div id="submit_data" class="card-footer" align="center"
	class="form-control" style="display: none;">
	<input type="button" class="btn btn-primary btn-sm" id="bt_sub"
		value="Submit For Approval" onclick="Submit_Approval();"><a
		href="search_censusUrl" id="censussearshUrllink" style="display: none;"
		class="btn btn-success btn-sm">Back</a> <button
		 id="censussearshUrl" style="display: none;" onclick="returnSearch()"
		class="btn btn-success btn-sm">Back</button>
</div>
<c:url value="GetSearch_Census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value=""/>				
		<input type="hidden" name="rank1" id="rank1"/>		
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		
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
		opendiv();
		$("#cadet_name").text(j[0][5]);
		comm_id = j[0][0];
		comm_date=ParseDateColumncommission(j[0][11])
	
		// 	    	 var d = new Date(j[0][4]);
		// 			 var date= d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear();
		off_dob = ParseDateColumncommission(j[0][4]);
		$("#dob").text(convertDateFormate(j[0][4]));
		$("#dob_date").val(off_dob);
		setMaxDate('father_dob', off_dob);
		// 	    	$("#father_dob").attr("max",ParseDateColumn(j[0][4]));
		// 			$("#mother_dob").attr("max",ParseDateColumn(j[0][4]));
		setMaxDate('mother_dob', off_dob);
		
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
		$.post('GetCensusData?' + key + "=" + value, {
			comm_id: comm_id
		}, function(k) {
			if(k.length > 0) {
				// 	    			 id,comm_id,first_name,middle_name,last_name,place_birth,district_birth,state_birth,country_birth,"
				// 						+ "nationality,religion,marital_status,blood_group,height,adhar_number,border_area,"
				// 						+ "father_name, father_dob,father_place_birth,father_profession,mother_name,"
				// 						+ "mother_dob,mother_place_birth,mother_profession,mother_tongue,ncc_type,institute 
				$.ajaxSetup({
					async: false
				});
				
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
				$('#father_name').val(k[0].father_name);
				if(k[0].father_dob != null)
				{
					$('#father_dob').val(ParseDateColumncommission(k[0].father_dob));
				}
				
				
				$('#father_place').val(k[0].father_place_birth);
				$('#father_profession').val(k[0].father_profession);
				
				if(k[0].father_profession==103){
					$('#father_proffother').val(k[0].father_proffother);
				}
				$('#mother_name').val(k[0].mother_name);
				
				if(k[0].mother_dob != null)
				{
					$('#mother_dob').val(ParseDateColumncommission(k[0].mother_dob));
				}
				
				$('#mother_place').val(k[0].mother_place_birth);
				$('#mother_profession').val(k[0].mother_profession);
				if(k[0].mother_profession==103){
					$('#mother_proffother').val(k[0].mother_proffother);
				}
				$('#mother_tongue').val(k[0].mother_tongue);
				fn_mother_tongue();
				$('#mother_tongue_other').val(k[0].mother_tongue_other);
				$('#ncc_type').val(k[0].ncc_type);
				$('#father_adhar_number').val(k[0].father_adhar_number);
				$('#father_pan_no').val(k[0].father_pancard_number);
				$('#mother_adhar_number').val(k[0].mother_adhar_number);
				$('#mother_pan_no').val(k[0].mother_pancard_number);
				
				if(k[0].father_profession==0){
					$("input[name='father_proff_radio'][value='yes']").prop('checked', true);
					
					if(k[0].father_service!=null)
						$('#father_service').val(k[0].father_service);
					if(k[0].father_other!=null)
						$('#father_other').val(k[0].father_other);
					if(k[0].father_personal_no!=null)
						$('#father_personal_no').val(k[0].father_personal_no);
				}else{
					
					if(k[0].father_other!=null)
						$('#father_proffother').val(k[0].father_other);
					
				}
				if(k[0].mother_profession==0){
					$("input[name='mother_proff_radio'][value='yes']").prop('checked', true);
					
					if(k[0].mother_service!=null)
						$('#mother_service').val(k[0].mother_service);
					if(k[0].mother_other!=null)
						$('#mother_other').val(k[0].mother_other);
					if(k[0].mother_personal_no!=null)
						$('#mother_personal_no').val(k[0].mother_personal_no);
				}else{
					if(k[0].mother_other!=null)
						$('#mother_proffother').val(k[0].mother_other);
				}
				
				
				
				isAadhar(document.getElementById('father_adhar_number'))
				isAadhar(document.getElementById('mother_adhar_number'))
				father_proffFn();
				mother_proffFn();
				ServiceOtherFn('father_otherDiv','father_personalDiv',document.getElementById('father_service'));
				ServiceOtherFn('mother_otherDiv','mother_personalDiv',document.getElementById('mother_service'));
				
				fn_otherShowHide(document.getElementById('father_profession'),'father_proffotherDiv','father_proffother');
				fn_otherShowHide(document.getElementById('mother_profession'),'mother_otherproffDiv','mother_proffother');
				
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
				// 	    			  $('#platoon_id').val(k[0].platoon_id);
				
				$("#org_domicile_Country").val(k[0].org_country);
				fn_org_domicile_Country_ready();
				$("#org_domicile_state").val(k[0].org_state);
				fn_org_domicile_state();
				$("#org_domicile_district").val(k[0].org_district);
				fn_org_domicile_district();
				$("#org_domicile_tehsil").val(k[0].org_tehsil);
				fn_org_domicile_tehsil();
				$("#tab_id").show();
				var comm_id = $('#comm_id').val();
				
				$("#org_domicile_Country_other").val(k[0].org_domicile_country_other);
				$("#org_domicile_state_other").val(k[0].org_domicile_state_other);
				$("#org_domicile_district_other").val(k[0].org_domicile_district_other);
				$("#org_domicile_tehsil_other").val(k[0].org_domicile_tehsil_other);
		
				getallergicDetails();
				//////
				var comm_id = $('#comm_id').val();
				var i_id = $('#i_id').val();
				// alert("get -----"+comm_id)
				$.post('GetIdentityData?' + key + "=" + value, {
					comm_id: comm_id,
					i_id: i_id
				}, function(i) {
					if(i.length > 0) {
						// 		 	   			$("input[name='identity_radio'][value='yes']").prop('checked', true);
						// 						$("#identity_div").show();
						$('#i_id').val(i[0].id);
						$('#hair_colour').val(i[0].hair_colour);
						fn_hair_colour();
						$('#hair_colour_other').val(i[0].hair_other);
						$('#eye_colour').val(i[0].eye_colour);
						fn_eye_colour();
						$('#eye_colour_other').val(i[0].eye_other);
						$('#id_card_no').val(i[0].id_card_no);
						$('#issue_dt').val(ParseDateColumncommission(i[0].issue_dt));
						setMinDate('issue_dt', comm_date);
						$('#id_marks').val(i[0].id_marks);
						$('#identity_image_preview').attr("src", "censusIdentityConvertpath?i_id=" + i[0].id + " ");
						$('#issue_authority_sus').val(i[0].issue_authority);
						fn_getUnitnamefromSus(i[0].issue_authority, document.getElementById("issue_authority"));
					}
				});
			} else {
				
				$("#submit_data").hide();
				
			}
			opendiv();
			var census_id = $('#census_id').val()
			  if(parseInt(census_id) > 0) {
		        $("#tab_id").show();
		        $("#submit_data").show();
			} else {
		 	   
			} 
		});
	});
	 
	$("input#personnel_no").attr('readonly', true);
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
	// 		var ir = $('input:radio[name=identity_radio]:checked').val();
	// 		if(ir == "yes"){	
// 	if($("input#height").val().trim() == "") {
// 		alert("Please Enter Height");
// 		$("input#height").focus();
// 		return false;
// 	}
	// 		}
	// 		var ar = $('input:radio[name=identity_radio]:checked').val();
	// 		if(ar == "yes"){
	//		}
	/* 		var ab = $('input:radio[name=identity_radio]:checked').val();
    
	 if(ab == "yes"){
	 var a = $('input:radio[name=border_area]:checked').val();
    
	 if(a == undefined){	
    
	 alert("Please select Border Area");
	 return false;
	 }
	 } */
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
	
	var i_id = $('#i_id').val();
	
	var file = document.getElementById("identity_image");

	if (
    id_card_no.trim() != "" ||
    issue_dt.trim() != "" ||
    issue_authority.trim() != "" ||
    id_marks.trim() != "" ||
    (hair_colour != "" && hair_colour != "0") ||
    (eye_colour != "" && eye_colour != "0") ||
    (i_id != "0" && file.files.length != 0)
) {

		if(id_card_no.trim()=="") {
		alert("Please Enter Id Card No");
		$('#id_card_no').focus();
		return false
	}
	if(issue_dt == "DD/MM/YYYY") {
		alert("Please Enter Issue Date");
		$('#issue_dt').focus();
		return false;
	}
	if(issue_dt.trim()=="") {
		alert("Please Enter Issue Date");
		$('#issue_dt').focus();
		return false;
	}
	
	if(getformatedDate(issue_dt) < getformatedDate(comm_date)) {
		alert("Enter Issue Date Should be  Equal To Or Greater Then Date of Commission");
		$("input#issue_dt").focus();
		return false;
	}
	
	var currentdate = new Date();
	if(getformatedDate(issue_dt) > currentdate) {
		alert("Enter Valid Issue Date");
		$("input#issue_dt").focus();
		return false;
	}
	
	
	if($('#issue_authority').val().trim() == "") {
		alert("Please Enter Issue Authority");
		$('#issue_authority').focus();
		return false;
	}
	if(issue_authority.trim()=="") {
		alert("Please Enter Valid Issue Authority");
		$('#issue_authority_sus').focus();
		return false;
	}
	if(id_marks.trim()=="") {
		alert("Please Enter Visible Identity Marks");
		$('#id_marks').focus();
		return false;
	}
	if(hair_colour == "0") {
		alert("Please Select Hair Color");
		$('#hair_colour').focus();
		return false;
	}
var text15 = $("#hair_colour option:selected").text();
	
	if(text15.toUpperCase() == "OTHERS" && $("input#hair_colour_other").val().trim() == ""){
		alert("Please Enter Hair Colour Other");
		$("input#hair_colour_other").focus();
		return false;
	}
	if(eye_colour == "0") {
		alert("Please Select Eye Color");
		$('#eye_colour').focus();
		return false;
	}
var text16 = $("#eye_colour option:selected").text();
	
	if(text16.toUpperCase() == "OTHERS" && $("input#eye_colour_other").val().trim() == ""){
		alert("Please Enter Eye Colour Other");
		$("input#eye_colour_other").focus();
		return false;
	}
// 	var i_id = $('#i_id').val();
// 	var file = document.getElementById("identity_image");
	if(i_id == "0" && file.files.length == 0) {
		alert("Please Upload Photograph");
		return false
	}
	
}

	$('#save_per').prop('disabled', true);
	// $("#WaitLoader").show();
	// 	aaaa	 var formvalues=$("#personalDetailsForm").serialize();
	var form_data = new FormData(document.getElementById("personalDetailsForm"));
	$.ajax({
		url: 'Personnel_Detailaction?_csrf=' + value,
		type: "POST",
		data: form_data,
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false
	}).done(function(data) {
		if(data.error == null) {
			
			
			
			alert("Data Save/Updated Successfully");
		//	$("#WaitLoader").hide();
			$('#save_per').prop('disabled', false);
			$("#tab_id").show();
			openCity(event, 'addr_details');
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[1].classList.add("active");
			$('#cadet_name').text(first_name + " " + middle_name + " " + last_name);
			$('#census_id').val(data.census_id);
			curr_marital_status=$('#marital_status').val();
			$("#i_id").val(data.i_id);
			getallergicDetails();
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
$(document).ready(function() {
	/* 	var ad = $('#adhar_number').val(); 
	
	 if( ad !=null){
	
	 $("input[name='identity_radio'][value='yes']").prop('checked', true);
    
	 }
	 else{
	 $("input[name='identity_radio1'][value='no']").prop('checked', true);
	 }  */
});
// ////////////////////////////////start  pre_cadet//////////////////////////////////////////////////
function pre_cadet_save() {
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
	
	
	
	$('#save_pre_cadet').prop('disabled', true);
	
	
	var formvalues = $("#pre_cadet_details_form").serialize();
	var p_id = $("#census_id").val();
	var com_id = $("#comm_id").val();
	formvalues += "&p_id=" + p_id + "&com_id=" + com_id;
	if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "6" || $("#cadet_status1").val() == "7" || $("#cadet_status1").val() == "8" || $("#cadet_status1").val() == "9") {
		var total_rank = $("#total_rank1").val();
		formvalues += "&total_rank1=" + total_rank;
	}
	$.post('pre_cadet_action?' + key + "=" + value, formvalues, function(data) {
		if(data.error == null) {
			$('#pre_ch_id1').val(data.pre_ch_id);
			$("#ncc_ch_id1").val(data.ncc_ch_id);
			alert("Data Save/Updated Successfully");
			openCity(event, 'Qualifications');
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[5].classList.add("active");
			$('#save_pre_cadet').prop('disabled', false);
		} else {
			alert(data.error)
			$('#save_pre_cadet').prop('disabled', false);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
		$('#save_pre_cadet').prop('disabled', false);
	});
}

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
	$.post('getPreCreditData?' + key + "=" + value, {
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
					url: "PSG_CADET_getTargetUnitsNameActiveList?" + key + "=" + value,
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
	$.post('getNCCData?' + key + "=" + value, {
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

fcon = 1;
fcon_srno = 1;

function country_add_fn(q) {
	if($('#country_add' + q).length) {
		$("#country_add" + q).hide();
	}
	fcon = q + 1;
	if(q != 0) fcon_srno += 1;
	$("table#country_visit").append('<tr id="tr_foregin_country' + fcon + '">' + '<td class="fcon_srno">' + fcon_srno + '</td>' + '<td style="width: 10%;"><select name="country' + fcon + '" id="country' + fcon + '" onchange="onContryVisited('+fcon+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
	+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+fcon+'" name="foregin_Country_ot'+fcon+'" onkeypress="return onlyAlphabets(event);" 	 readonly  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
	+ '<td style="width: 10%;"> ' + ' <input type="text" name="from_dt' + fcon + '" id="from_dt' + fcon + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22(' + fcon + ');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td style="width: 10%;"> ' + ' <input type="text" name="to_dt' + fcon + '" id="to_dt' + fcon + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22(' + fcon + ');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td style="width: 10%;"> <input type="text" id="period' + fcon + '" name="period' + fcon + '" class="form-control autocomplete" autocomplete="off" readonly="readonly"></td>'
	+ '<td style="width: 10%;"><select name="purpose_visit' + fcon + '" id="purpose_visit' + fcon + '" onchange="onPurposeVisited('+fcon+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>' 
	+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+fcon+'" onchange="onPurposeVisited('+fcon+')" name="purpose_visit_ot'+fcon+'" onkeypress="return onlyAlphabets(event);" 	onkeydown="return AvoidSpace(event)"readonly  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
	+ '<td style="display:none;"><input type="text" id="foregin_country_ch_id' + fcon + '" name="foregin_country_ch_id' + fcon + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save' + fcon + '" onclick="country_save_fn(' + fcon + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add' + fcon + '" onclick="country_add_fn(' + fcon + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove' + fcon + '" onclick="country_remove_fn(' + fcon + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('from_dt' + fcon);
	datepicketDate('to_dt' + fcon);
	setMinDate('from_dt' + fcon, off_dob);
	setMinDate('to_dt' + fcon, off_dob);
}

function country_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var foregin_country_ch_id = $('#foregin_country_ch_id' + R).val();
		$.post('foregin_country_delete_action?' + key + "=" + value, {
			foregin_country_ch_id: foregin_country_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_foregin_country" + R).remove();
				if(R == fcon) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#country_add' + i).length) {
							$("#country_add" + i).show();
							temp = false;
							fcon = i;
							break;
						}
					}
					if(temp) {
						country_add_fn(0);
					}
				}
				$('.fcon_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					fcon_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function country_save_fn(ps) {
	var country = $('#country' + ps).val();
	var other_ct=$('#foregin_Country_ot'+ps).val();
	var period = $('#period' + ps).val();
	var from_dt = $('#from_dt' + ps).val();
	var to_dt = $('#to_dt' + ps).val();
	var purpose_visit = $('#purpose_visit' + ps).val();
	var other_purpose_visit=$('#purpose_visit_ot'+ps).val();
	var foregin_country_ch_id = $('#foregin_country_ch_id' + ps).val();
	var f_id = $('#census_id').val();
	var comm_id = $('#comm_id').val();
	if(country == "0") {
		alert("Please select Country Visited");
		$("select#country" + ps).focus();
		return false;
	}
	
	var country_sel = $("#country"+ps+" option:selected").text();
	if (country_sel == "Other" && other_ct.trim()=="") {
		alert("Please Enter Other Country");
		$('#foregin_Country_ot'+ps).focus();
		return false;
	}
	
	if(from_dt.trim()=="" || from_dt == "DD/MM/YYYY") {
		alert("Please Enter From Date");
		$("input#from_dt" + ps).focus();
		return false;
	}
	if(getformatedDate($("input#dob_date").val()) >= getformatedDate(from_dt)) {
		alert("From Date should be Greater than Date of Birth");
		$("input#from_dt" + ps).focus();
		return false;
	}
	if(to_dt.trim()=="" || to_dt == "DD/MM/YYYY") {
		alert("Please Enter To Date");
		$("input#to_dt" + ps).focus();
		return false;
	}
	var currentdate = new Date();
	if(getformatedDate(to_dt) > currentdate) {
		alert("Enter Valid To Date");
		$("input#to_dt" + ps).focus();
		return false;
	}
	if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
		alert("Invalid Date Range");
		$("input#to_dt" + ps).focus();
		return false;
	}
	if(period.trim()=="") {
		alert("Please Enter Valid From And To Date");
		$("input#period" + ps).focus();
		return false;
	}
	if(purpose_visit.trim()=="0") {
		alert("Please Enter Purpose To Visit");
		$("#purpose_visit" + ps).focus();
		return false;
	}
	
	var pup_sel = $("#purpose_visit"+ps+" option:selected").text();
	if (pup_sel.toUpperCase() == "OTHER" && other_purpose_visit.trim()=="") {
		alert("Please Enter Other Purpose Visit");
		$('#purpose_visit_ot'+ps).focus();
		return false;
	}
	
	$('#country_save' + ps).prop('disabled',true);
	$.post('foregin_country_action?' + key + "=" + value, {
		country: country,
		other_ct:other_ct,
		period: period,
		from_dt: from_dt,
		to_dt: to_dt,
		purpose_visit: purpose_visit,
		other_purpose_visit:other_purpose_visit,
		foregin_country_ch_id: foregin_country_ch_id,
		f_id: f_id,
		comm_id: comm_id
	}, function(data) {
		if(parseInt(data) > 0) {
			$("#foregin_country_ch_id" + ps).val(data);
			$("#country_add" + ps).show();
			$("#country_remove" + ps).show();
			alert("Data Saved Successfully")
		} else alert(data)
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getForeign_CountriesDetails() {
	var f_id = $('#census_id').val();
	var i = 0;
	$.post('getForeginCountryData?' + key + "=" + value, {
		f_id: f_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#foregin_Country_tbody').empty();
			for(i; i < v; i++) {
				f = i + 1;
		
				$("table#country_visit").append('<tr id="tr_foregin_country' + f + '">' + '<td class="fcon_srno">' + f + '</td>' + '<td style="width: 10%;"><select name="country' + f + '" id="country' + f + '" onchange="onContryVisited('+f+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
				+'<td style="width: 10%;"> <input type="text" id="foregin_Country_ot'+f+'" name="foregin_Country_ot'+f+'" 	 onkeypress="return onlyAlphabets(event);" value="' + j[i].other_country + '"  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
				+ '<td style="width: 10%;"> ' + ' <input type="text" name="from_dt' + f + '" id="from_dt' + f + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22(' + f + ');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].from_dt) + '">' + '</td>' + '<td style="width: 10%;"> ' + ' <input type="text" name="to_dt' + f + '" id="to_dt' + f + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');calcDate22(' + f + ');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].to_dt) + '">' + '</td>' + '<td style="width: 10%;"> <input type="text" id="period' + f + '" name="period' + f + '" value="' + j[i].period + '"  readonly="readonly" class="form-control autocomplete" autocomplete="off" ></td>'
				+ '<td style="width: 10%;"><select name="purpose_visit' + f + '" id="purpose_visit' + f + '" onchange="onPurposeVisited('+f+')" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>' 
				+'<td style="width: 10%;"> <input type="text" id="purpose_visit_ot'+f+'" name="purpose_visit_ot'+f+'" 	 onkeypress="return onlyAlphabets(event);" value="' + j[i].other_purpose_visit + '"  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
				+ '<td style="display:none;"><input type="text" id="foregin_country_ch_id' + f + '" name="foregin_country_ch_id' + f + '"  value="' + j[i].id + '" value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "country_save' + f + '" onclick="country_save_fn(' + f + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "country_add' + f + '" onclick="country_add_fn(' + f + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "country_remove' + f + '" onclick="country_remove_fn(' + f + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$("#country" + f).val(j[i].country)
				  onContryVisited(f);
				$("#purpose_visit" + f).val(j[i].purpose_visit)
				datepicketDate('from_dt' + f);
				datepicketDate('to_dt' + f);
				setMinDate('from_dt' + f, off_dob);
				setMinDate('to_dt' + f, off_dob);
				 onPurposeVisited(f);
			}
			fcon = v;
			fcon_srno = v;
			$("#country_add" + fcon).show();
		}
	});
}


function onPurposeVisited(pup){
	var purpose_sel = $("#purpose_visit"+pup+" option:selected").text();
	if(purpose_sel.toUpperCase() != "OTHER"){
		$('#purpose_visit_ot'+pup).val("");
		$('#purpose_visit_ot'+pup).attr('readonly', true);
	}
	else
		$('#purpose_visit_ot'+pup).attr('readonly', false);
}
function onContryVisited(ind){
	var country_sel = $("#country"+ind+" option:selected").text();
	if(country_sel != "Other"){
		$('#foregin_Country_ot'+ind).val("");
		$('#foregin_Country_ot'+ind).attr('readonly', true);
	}
	else
		$('#foregin_Country_ot'+ind).attr('readonly', false);
}



sib = 1;
sib_srno = 1;

function family_add_fn(q) {
	if($('#family_add' + q).length) {
		$("#family_add" + q).hide();
	}
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' 
			+ '<td> <input type="sib_name' + sib + '" id="sib_name' + sib + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control" maxlength="50" autocomplete= "off" maxlength="50">'
		+ '<td>'
		
		+ ' <input type="text" name="sib_date_of_birth' + sib + '" id="sib_date_of_birth' + sib + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td> <select name="sib_relationship' + sib + '" id="sib_relationship' + sib + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	id="sib_adhar_number' + sib + '" name="sib_adhar_number' + sib + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '		onkeydown="return AvoidSpace(event)" onkeypress="return isAadhar(this,event); " autocomplete="off"></td> ' + '<td> ' + '	<input type="text" id="sib_pan_no' + sib + '" name="sib_pan_no' + sib + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + ' 	onkeydown="return AvoidSpace(event)"	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' 

		+ '<td><input type="checkbox" ' + '	id="ser-ex' + sib + '" name="ser-ex' + sib + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + sib + ');"></td> '
		+ '<td> <select name="sibling_service' + sib + '" id="sibling_service' + sib + '" class="form-control"  onchange = "otherfunction(' + sib + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	maxlength="9" min="7"  id="sibling_personal_no' + sib + '" name="sibling_personal_no' + sib + '" ' + '	class="form-control" autocomplete="off" ></td> '
		+ '<td><input type="text" ' + '	id="other_sibling_ser' + sib + '" name="other_sibling_ser' + sib + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" 	 autocomplete="off" maxlength="50" ></td>'
		+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="family_save_fn(' + sib + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + sib + '" onclick="family_add_fn(' + sib + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + sib + '" onclick="family_remove_fn(' + sib + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('sib_date_of_birth' + sib);
	siblingCB(sib);
	otherfunction(sib);
}

function family_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var sib_ch_id = $('#sib_ch_id' + R).val();
		$.post('family_sibling_delete_action?' + key + "=" + value, {
			sib_ch_id: sib_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_sibling" + R).remove();
				if(R == sib) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#family_add' + i).length) {
							$("#family_add" + i).show();
							temp = false;
							sib = i;
							break;
						}
					}
					if(temp) {
						family_add_fn(0);
					}
				}
				$('.sib_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					sib_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function family_save_fn(ps) {
	
	var sib_name = $('#sib_name' + ps).val();
	var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
	var sib_relationship = $('#sib_relationship' + ps).val();
	var sib_ch_id = $('#sib_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	var com_id = $("#comm_id").val();
	var sib_ser = $("select#sibling_service"+ps).val();
	var sib_pers_no = $("#sibling_personal_no"+ps).val();
	if($('#ser-ex' + ps).is(":checked")){
		var ser_ex = "Yes";
	}else{
		var ser_ex = "";
	}
	var other_sib_ser =  $("#other_sibling_ser"+ps).val();
	
	if(sib_name.trim() == "") {
		alert("Please Enter Sibling Name");
		$("input#sib_name" + ps).focus();
		return false;
	}
	if(sib_date_of_birth == "DD/MM/YYYY") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_date_of_birth.trim() == "") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_relationship == "0") {
		alert("Please Select Sibling Relationship");
		$("select#sib_relationship" + ps).focus();
		return false;
	}
	if($('#sib_adhar_number' + ps).val().trim() == "" || $('#sib_adhar_number' + ps).val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#sib_adhar_number"+ps).focus();
		return false;
	}
	var sibling = $( "#sibling_service"+ps+" option:selected" ).text();
	if($('#ser-ex' + ps).is(":checked")){
		if(sib_ser == 0){
			alert("Please Select Sibling Service");
			$("select#sibling_service"+ps).focus();
			return false;
		}
		if(sib_ser == 1){
			if(sib_pers_no.trim() == ""){
				alert("Please Enter Sibling Personal No");
				$("#sibling_personal_no"+ps).focus();
				return false;
			}
		}
		if(sib_pers_no.trim()!=''){
			if(sib_pers_no.trim().length < 5 || sib_pers_no.trim().length > 15){
			alert("Please Enter Valid Sibling Personal No");
			$("#sibling_personal_no").focus();
			return false;
			}
		}
			
		if (sibling.toUpperCase() == "OTHER"){
			if($('#other_sibling_ser' + ps).val().trim() == ""){
				alert("Please Enter Other Sibling Service");
			$('#other_sibling_ser' + ps).focus();
			return false;
			}
			}
	}
	else{
		sibling="";	
	}
	var adhar_number = $('#sib_adhar_number' + ps).val().split('-').join('');
	var pan_no = $('#sib_pan_no' + ps).val();
	var currentdate = new Date();
	if(getformatedDate(sib_date_of_birth) > currentdate) {
		alert("Enter Valid Date of Birth Date");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	$('#family_save' + ps).prop('disabled',true);
	
	$.post('family_sibling_action?' + key + "=" + value, {
		sib_name: sib_name,
		sib_date_of_birth: sib_date_of_birth,
		adhar_number: adhar_number,
		pan_no: pan_no,
		sib_relationship: sib_relationship,
		sib_ch_id: sib_ch_id,
		p_id: p_id,
		com_id: com_id,
		sib_ser:sib_ser,
		sib_pers_no:sib_pers_no,
		ser_ex:ser_ex,
		other_sib_ser:other_sib_ser,sibling:sibling
	}, function(data) {
		if(data.error == null) {
			if(data.sibId != null) {
				$('#sib_ch_id' + ps).val(data.sibId);
				$("#family_add" + ps).show();
				$("#family_remove" + ps).show();
				alert(data.saved);
				
			} else {
				alert(data.updated);
				
			}
		} else alert(data.error);
		$('#family_save' + ps).prop('disabled',false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$('#family_save' + ps).prop('disabled',false);
	});
}

function getfamily_siblingDetails() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getfamily_siblingData?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#family_sibtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				var pan = "";
				if(j[i].pan_no != null) {
					pan = j[i].pan_no;
				}
				$("table#family_table").append('<tr id="tr_sibling' + x + '">' + '<td class="sib_srno">' + x + '</td>'
						+ '<td> <input type="sib_name' + x + '" id="sib_name' + x + '"  value="' + j[i].name + '"  onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control-sm form-control" autocomplete="off" maxlength="50"  >' + '<td> '
					
					+ ' <input type="text" name="sib_date_of_birth' + x + '" id="sib_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' + '<td> <select name="sib_relationship' + x + '" id="sib_relationship' + x + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	id="sib_adhar_number' + x + '" name="sib_adhar_number' + x + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off" value="' + j[i].aadhar_no + '" ></td> ' + '<td> ' + '	<input type="text" id="sib_pan_no' + x + '" name="sib_pan_no' + x + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" value="' + pan + '" > ' + '	</td> ' + '<td style="display:none;"><input type="text" id="sib_ch_id' + x + '" name="sib_ch_id' + x + '"   value="' + j[i].id + '"  class="form-control autocomplete" autocomplete="off" ></td>'
					+ '<td><input type="checkbox" ' + '	id="ser-ex' + x + '" name="ser-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + x + ');"></td> '
					+ '<td> <select name="sibling_service' + x + '" id="sibling_service' + x + '" class="form-control" onchange = "otherfunction(' + x + ')"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '  maxlength="9" min="7" onkeydown="return AvoidSpace(event)"	id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].sibling_personal_no + '" ></td> '
					+ '<td><input type="text" ' + '	id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" 	 autocomplete="off" maxlength="50" ></td>'
					+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + x + '" onclick="family_save_fn(' + x + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + x + '" onclick="family_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + x + '" onclick="family_remove_fn(' + x + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$('#sib_relationship' + x).val(j[i].relationship);
				$('#sibling_service' + x).val(j[i].sibling_service);
				$('#other_sibling_ser' + x).val(j[i].other_sibling_ser);
				if(j[i].if_sibling_ser =="Yes"){
					$("input[type=checkbox][name='ser-ex"+x+"']").prop("checked",true);

				}
				siblingCB(x);
				
				otherfunction(x);
				datepicketDate('sib_date_of_birth' + x);
				isAadhar(document.getElementById('sib_adhar_number' + x))
					
			}
			sib = v;
			sib_srno = v;
			$('#family_add' + sib).show();
		}
	});
}
///married
marr = 1;
marr_srno = 1;

function married_add_fn(q) {
	if($('#married_add' + q).length) {
		$("#married_add" + q).hide();
	}
	marr = q + 1;
	if(q != 0) {
		marr_srno += 1;
	}
	
	var marriage_othernationality='marriage_othernationality'+marr;
	$("table#married_table").append('<tr id="tr_marriage' + marr + '">' + '<td class="marr_srno">' + marr_srno + '</td>' 
			+ '<td> <input type="text" id="maiden_name' + marr + '" name="maiden_name' + marr + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
			+ '<td> ' + ' <input type="text" name="marriage_date_of_birth' + marr + '" id="marriage_date_of_birth' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		
		+ '<td> <input type="text" id="marriage_place_of_birth' + marr + '" name="marriage_place_of_birth' + marr + '" class="form-control autocomplete" autocomplete="off" maxlength="50" 	></td>' 
		+ '<td>' + ' <select id="marriage_nationality' + marr + '" name="marriage_nationality' + marr + '" class="form-control"   onchange="marriageNationChange('+marr+')">' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select> </td>'
		+ '<td><input type="text" id="marriage_othernationality' + marr + '" name="marriage_othernationality' + marr + '" ' 
		+ '	maxlength="50"  class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" 	 oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
		+ '	</td>'
		 + '<td> ' 
		+ ' <input type="text" name="marriage_date' + marr + '" id="marriage_date' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		
		+ '<td> <input type="text" id="spouse_adhar_number' + marr + '" name="spouse_adhar_number' + marr + '" 	onkeydown="return AvoidSpace(event)" onkeypress="return isAadhar(this,event);"   maxlength="14" class="form-control autocomplete" autocomplete="off"  ></td>'
		+ '<td> ' + '	<input type="text" id="spouse_pan_number' + marr + '" name="spouse_pan_number' + marr + '" ' + ' 	onkeydown="return AvoidSpace(event)"	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> '
		+ '<td style="display:none;"><input type="text" id="marr_ch_id' + marr + '" name="marr_ch_id' + marr + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
		+ '<td><input type="checkbox" ' + '	id="spo-ex' + marr + '" name="spo-ex' + marr + '" ' + '	value="Yes"  ' + '	onclick="spouseCB(' + marr + ');"></td> '
		+ '<td> <select name="spouse_service' + marr + '" id="spouse_service' + marr + '" class="form-control"  onchange="otherfunction(' + marr + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' 
		+ '<td style="width: 15%;"><input type="text" ' + '	maxlength="9" min="7" 	onkeydown="return AvoidSpace(event)" id="spouse_personal_no' + marr + '" name="spouse_personal_no' + marr + '" ' + '	class="form-control" autocomplete="off" ></td> '
		+ '<td><input type="text" ' + '	id="other_spouse_ser' + marr + '" onkeypress="return onlyAlphaNumeric(event);" 	 name="other_spouse_ser' + marr + '" ' + '	class="form-control" autocomplete="off" maxlength="50" ></td>'
		+'<td> <select name="spouse_profession' + marr + '" id="spouse_profession' + marr + '" class="form-control" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getProfession}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' 
		+ '<td class="sepratedSpouseClass"> ' 
			+ ' <input type="text" name="sep_from_date' + marr + '" id="sep_from_date' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "married_save' + marr + '" onclick="(' + marr + ');" ><i class="fa fa-save"></i></a>'
		+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "married_add'+marr+'" onclick="married_add_fn('+marr+');" ><i class="fa fa-plus"></i></a>'
		+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "married_remove' + marr + '" onclick="married_remove_fn(' + marr + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('marriage_date_of_birth' + marr);
	datepicketDate('marriage_date' + marr);
	datepicketDate('sep_from_date' + marr);
	marriageNationChange(marr);
	spouseCB(marr);
	otherfunction(marr);
}

function married_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var marr_ch_id = $('#marr_ch_id1').val();
		$.post('family_marriage_delete_action?' + key + "=" + value, {
			marr_ch_id: marr_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_marriage" + R).remove();
				
				married_add_fn(0);
				$('#spouse_quali_type').val('0')
				$('#spouse_yrOfPass').val("");
				$('#spouse_div_class').val('0');
				$('#spouse_institute_place').val('');
				$('#spouse_qualification_ch_id').val('0');
				$("input[type=checkbox][name='spouse_multisub']").prop("checked", false);
				$("#spouse_sub_quali option:first").text("Select Subjects");
				$('#spouse_quali').val('0');
				$('#exam_otherSpouse').val('');
				$('#quali_deg_otherSpouse').val('');
				$('#quali_spec_otherSpouse').val('');
				$('#class_otherSpouse').val('');
				$('#quali_degree_spouse').val('0');
				$('#spouse_quali_sub_list').empty();
				$('#spouse_specialization').val('0');
				$("#spouse_quali_radio2").prop("checked", true);
				$("#spouse_Qualifications").hide();
				var typethisT = document.getElementById('spouse_quali_type');
				fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
				
				alert("Data Deleted Successfully");
				getmarital_status();
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function married_save_fn(ps) {
	var spo_ser = $("select#spouse_service"+ps).val();
	var spo_pers_no = $("#spouse_personal_no"+ps).val();
	if($('#spo-ex' + ps).is(":checked")){
		var spo_ex = "Yes";
	}else{
		var spo_ex = "";
	}
	
	
	var marital_status = curr_marital_status;
	var maiden_name = $('#maiden_name' + ps).val();
	var marriage_date_of_birth = $('#marriage_date_of_birth' + ps).val();
	var marriage_place_of_birth = $('#marriage_place_of_birth' + ps).val();
	var marriage_nationality = $('#marriage_nationality' + ps).val();
	var marriage_date = $('#marriage_date' + ps).val();
	var sep_from_date = $('#sep_from_date' + ps).val();
	var adhar_number = $('#spouse_adhar_number' + ps).val().split('-').join('');
	var marr_ch_id = $('#marr_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	var com_id = $("#comm_id").val();
	var spouse_pan_number = $("#spouse_pan_number"+ps).val();
	var marriage_othernationality = $("#marriage_othernationality"+ps).val();
	var other_spo_ser = $("#other_spouse_ser"+ps).val();
	var spouse_profession = $("#spouse_profession"+ps).val();

	

	if($("input#maiden_name"+ps).val().trim() == "") {
		alert("Please Enter Name");
		$("input#maiden_name"+ps).focus();
		return false;
	}
	if($("input#marriage_date_of_birth"+ps).val().trim() == "" || $("input#marriage_date_of_birth"+ps).val().trim() == "DD/MM/YYYY") {
		alert("Please Enter  Date of Birth");
		$("input#marriage_date_of_birth"+ps).focus();
		return false;
	}
	if($("input#marriage_place_of_birth"+ps).val().trim() == "") {
		alert("Please Enter Place of Birth");
		$("input#marriage_place_of_birth"+ps).focus();
		return false;
	}
	if($("select#marriage_nationality"+ps).val() == "0") {
		alert("Please Enter Nationality");
		$("select#marriage_nationality"+ps).focus();
		return false;
	}
	var marriage_nationalityOther = $("#marriage_nationality"+ps+" option:selected").text();
	if(marriage_nationalityOther.toUpperCase() == "OTHERS"){
		
		if($("#marriage_othernationality" + ps).val().trim() == "") {
			alert("Please Enter Nationality");
			$("#marriage_othernationality" + ps).focus();
			return false;
		}
	}
	if($("input#marriage_date"+ps).val().trim() == "" || $("input#marriage_date"+ps).val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Marriage Date");
		$("input#marriage_date"+ps).focus();
		return false;
	}
	if(getformatedDate(marriage_date_of_birth) >= getformatedDate(marriage_date)) {
		alert("Marriage Date should be Greater than Date of Birth");
		$("input#marriage_date" + ps).focus();
		return false;
	}
	if(calculate_age(document.getElementById('marriage_date_of_birth' + ps), document.getElementById('marriage_date' + ps)) && calculate_age(document.getElementById('dob_date'), document.getElementById('marriage_date' + ps))) {} else {
		return false;
	}
	if($("input#spouse_adhar_number"+ps).val().trim() == "" || $("input#spouse_adhar_number"+ps).val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#spouse_adhar_number"+ps).focus();
		return false;
	}
	var spouse = $( "#spouse_service"+ps+" option:selected" ).text();
	if($('#spo-ex' + ps).is(":checked")){
		if(spo_ser == 0){
			alert("Please Select Spouse Service");
			$("select#spouse_service"+ps).focus();
			return false;
		}
		if(spo_ser == 1){
			if(spo_pers_no.trim()==""){
				alert("Please Enter Spouse Personal No");
				$("#spouse_personal_no"+ps).focus();
				return false;
			}
		}
		
		if(spo_pers_no.trim()!=''){
			if(spo_pers_no.trim().length < 5 || spo_pers_no.trim().length > 15){
			alert("Please Enter Valid Spouse Personal No");
			$("#spouse_personal_no").focus();
			return false;
			}
		}
		
		if (spouse.toUpperCase() == "OTHER"){
			if($('#other_spouse_ser' + ps).val().trim() == ""){
				alert("Please Enter Other Spouse Service");
			$('#other_spouse_ser' + ps).focus();
			return false;
			}
			}
	}
	else{
		spouse="";
		if($("select#spouse_profession"+ps).val() == "0") {
			alert("Please Select Spouse Profession");
			$("select#spouse_profession"+ps).focus();
			return false;
	}
	}
	if(marital_status=='13'){
		if($("input#sep_from_date"+ps).val().trim() == "" || $("input#sep_from_date"+ps).val().trim() == "DD/MM/YYYY") {
			alert("Please Enter From Date");
			$("input#sep_from_date"+ps).focus();
			return false;
		}
		if(getformatedDate(marriage_date) >= getformatedDate(sep_from_date)) {
			alert("From Date should be Greater than Marriage Date");
			$("input#sep_from_date" + ps).focus();
			return false;
		}
	}
	$("#married_save"+ps).prop('disabled',true);
	


	$.post('family_marriage_action?' + key + "=" + value, {
		maiden_name: maiden_name,
		sep_from_date:sep_from_date,
		marriage_date_of_birth: marriage_date_of_birth,
		marriage_place_of_birth: marriage_place_of_birth,
		marriage_nationality: marriage_nationality,
		marriage_date: marriage_date,
		adhar_number: adhar_number,
		marr_ch_id: marr_ch_id,
		p_id: p_id,
		marital_status: marital_status,
		com_id: com_id,
		marriage_othernationality:marriage_othernationality,
		spouse_pan_number: spouse_pan_number,
		spo_ser:spo_ser,
		spo_pers_no:spo_pers_no,
		spo_ex:spo_ex,
		other_spo_ser:other_spo_ser,spouse_profession : spouse_profession,spouse:spouse
	}, function(data) {
		if(parseInt(data) > 0) {
			$('#marr_ch_id' + ps).val(data);
			$("#married_remove" + ps).show();
			alert("Data Saved Successfully")
		} else alert(data);
		$("#married_save"+ps).prop('disabled',false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$("#married_save"+ps).prop('disabled',false);
	});
}

function getfamily_marriageDetails() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getfamily_marriageData?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			
			$('#married_table').show();
			$('#family_marrtbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				
				
				var pan_card = "";
				if(j[i].pan_card != null) pan_card = j[i].pan_card;
				var other_nation=""
					if(j[i].other_nationality != null) other_nation = j[i].other_nationality;
				
				$("table#married_table").append('<tr id="tr_marriage' + x + '">' 
						+ '<td class="marr_srno">' + x + '</td>' 
						+ '<td> <input type="text" id="maiden_name' + x + '" name="maiden_name' + x + '" onkeypress="return onlyAlphabets(event);"  value="' + j[i].maiden_name + '" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>' 
						+ '<td style="width: 10%;"> ' + ' <input type="text" name="marriage_date_of_birth' + x + '" id="marriage_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' 
						+ '<td> <input type="text" id="marriage_place_of_birth' + x + '" name="marriage_place_of_birth' + x + '" 	 onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  value="' + j[i].place_of_birth + '" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>' 
						+ '<td> ' + ' <select id="marriage_nationality' + x + '" name="marriage_nationality' + x + '" onchange="marriageNationChange('+x+')" class="form-control"   >' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select></td>' 
						+ '<td><input type="text" id="marriage_othernationality' + x + '" name="marriage_othernationality' + x + '"  value="'+other_nation+'"'
						+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"  oninput="this.value = this.value.toUpperCase()" readonly="readonly" maxlength="50" >'
						+ '	</td>'
				+ '<td> <input type="text" name="marriage_date' + x + '" id="marriage_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].marriage_date) + '">' + '</td>'
					
					+ '<td> <input type="text" id="spouse_adhar_number' + x + '" name="spouse_adhar_number' + x + '"  onkeypress="return isAadhar(this,event);" 	onkeydown="return AvoidSpace(event)" maxlength="14"  value="' + j[i].adhar_number + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td> ' + '	<input type="text" id="spouse_pan_number' + x + '" name="spouse_pan_number' + x + '" ' + '	class="form-control autocomplete" 	onkeydown="return AvoidSpace(event)" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  value="' + pan_card + '"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="marr_ch_id' + x + '" name="marr_ch_id' + x + '"  value="' + j[i].id + '"class="form-control autocomplete" autocomplete="off" ></td>' 
					+ '<td><input type="checkbox" ' + '	id="spo-ex' + x + '" name="spo-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="spouseCB(' + x + ');"></td> '
					+ '<td> <select name="spouse_service' + x + '" id="spouse_service' + x + '" class="form-control"  onchange="otherfunction(' + x + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + ' maxlength="9" min="7"  onkeydown="return AvoidSpace(event)" id="spouse_personal_no' + x + '" name="spouse_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].spouse_personal_no + '" ></td> '
					+ '<td><input type="text" ' + '	id="other_spouse_ser' + x + '" name="other_spouse_ser' + x + '" ' + '	onkeypress="return onlyAlphaNumeric(event);" 	 class="form-control" autocomplete="off" maxlength="50" ></td>'
					+'<td id="spouse_proffDiv" > <select name="spouse_profession' + x + '" id="spouse_profession' + x + '" class="form-control" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getProfession}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
					+ '<td class="sepratedSpouseClass"><input type="text" name="sep_from_date' + x + '" id="sep_from_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].separated_form_dt) + '" >' + '</td>'
					
					+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "married_save' + x + '" onclick="married_save_fn(' + x + ');" ><i class="fa fa-save"></i></a>'
					+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "married_add'+x+'" onclick="married_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
					+ '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "married_remove' + x + '" onclick="married_remove_fn(' + x + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$("#marriage_nationality" + x).val(j[i].nationality);
				$('#spouse_service' + x).val(j[i].spouse_service);
				$('#other_spouse_ser' + x).val(j[i].other_spouse_ser);
				$('#spouse_profession' + x).val(j[i].spouse_profession);
				if(j[i].if_spouse_ser =="Yes"){
					$("input[type=checkbox][name='spo-ex"+x+"']").prop("checked",true);

				}
				isAadhar(document.getElementById('spouse_adhar_number' + x))
				datepicketDate('marriage_date_of_birth' + x);
				datepicketDate('marriage_date' + x);
				datepicketDate('sep_from_date' + x);
				
				if(j[i].marital_status == "13") {
					$(".sepratedSpouseClass").show();
				}
				else{
					$(".sepratedSpouseClass").hide();
				}
				
				marriageNationChange(x);
				spouseCB(x);
				
				otherfunction(x);
			}
			$("#fill_marraige_div").show();
		}
		marr = v;
		marr_srno = v;
	});
}

divo = 1;
divosr_no = 1;

function divorce_add_fn(q) {
	
	if($('#divorce_add' + q).length) 
	{
		$("#divorce_add" + q).hide();
	}
	if(q != 0) {
		divosr_no += 1;
	}
	divo = q + 1;
	$("table#divorce_table").append('<tr id="tr_divorce' + divo + '">' + '<td class="divosr_no">' + divosr_no + '</td>' 
			+'<td><select name="marital_event' + divo + '" '
			+' id="marital_event' + divo + '" '
			+' 	class="form-control" >'
			+'		<option value="0">--Select--</option>'
			+'	<c:forEach var="item" items="${getMarital_statusList}"	varStatus="num">'
			+'		<c:if test="${item[0]==9 || item[0]==11 || item[0]==12}">'
			+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+'	</c:if>'
			+'	</c:forEach>'
			+'</select></td>'
		+	'<td > <input type="text" id="spouse_name' + divo + '" name="spouse_name' + divo + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
		
		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date_of_birth' + divo + '" id="divorce_date_of_birth' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		+ '<td ><input type="text" id="divorce_place_of_birth' + divo + '" name="divorce_place_of_birth' + divo + '" 	 onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" maxlength="50" >' + ' </td><td >'
		+ '<select name="divorce_nationality' + divo + '" id="divorce_nationality' + divo + '" class="form-control"  onchange="divorceNationChange('+divo+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
		+ '<td style="width: 13%;"><input type="text" id="divorce_othernationality' + divo + '" name="divorce_othernationality' + divo + '" '
		+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" 	 oninput="this.value = this.value.toUpperCase()" readonly="readonly" maxlength="50" >'
		+ '	</td>'
		
		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_marriage_date' + divo + '" id="divorce_marriage_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ ' <td ><input type="text" id="divorce_spouse_adhar_number' + divo + '" name="divorce_spouse_adhar_number' + divo + '" 	onkeydown="return AvoidSpace(event)" onkeypress="return isAadhar(this,event);" class="form-control autocomplete" maxlength="14" autocomplete="off" >' + ' </td>'
		
		+ '<td style="width: 10%;"> ' + '	<input type="text" id="divorce_spouse_pan_number' + divo + '" name="divorce_spouse_pan_number' + divo + '" ' + ' 	onkeydown="return AvoidSpace(event)"	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" > ' + '	</td> ' 
		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date' + divo + '" id="divorce_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' 
		+ '<td style="display:none;"><input type="text" id="divo_ch_id' + divo + '" name="divo_ch_id' + divo + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
		+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "divorce_save' + divo + '" onclick="divorce_save_fn(' + divo + ');" ><i class="fa fa-save"></i></a>'
		+ '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "divorce_add' + divo + '" onclick="divorce_add_fn(' + divo + ');" ><i class="fa fa-plus"></i></a>'
		+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "divorce_remove' + divo + '" onclick="divorce_remove_fn(' + divo + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('divorce_date_of_birth' + divo);
	datepicketDate('divorce_marriage_date' + divo);
	datepicketDate('divorce_date' + divo);
	divorceNationChange(divo);
}

function divorce_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var marr_ch_id = $('#divo_ch_id' + R).val();
		$.post('family_marriage_delete_action?' + key + "=" + value, {
			marr_ch_id: marr_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_divorce" + R).remove();
				if(R == divo) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#divorce_add' + i).length) {
							$("#divorce_add" + i).show();
							temp = false;
							divo = i;
							break;
						}
					}
					if(temp) {
						divorce_add_fn(0);
					}
				}
				$('.divosr_no').each(function(i, obj) {
					obj.innerHTML = i + 1;
					divosr_no = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function divorce_save_fn(ps) {
	var marital_status = $("select#marital_status option:selected").val();
	var marital_event = $("#marital_event"+ps).val();
	var spouse_name = $('#spouse_name' + ps).val();
	var marriage_date_of_birth = $('#divorce_date_of_birth' + ps).val();
	var marriage_place_of_birth = $('#divorce_place_of_birth' + ps).val();
	var marriage_nationality = $('#divorce_nationality' + ps).val();
	var divorce_marriage_date = $('#divorce_marriage_date' + ps).val();
	var divorce_date = $('#divorce_date' + ps).val();
	var adhar_number = $('#divorce_spouse_adhar_number' + ps).val().split('-').join('');
	var divorce_spouse_pan_number = $('#divorce_spouse_pan_number' + ps).val();
	var divo_ch_id = $('#divo_ch_id' + ps).val();
	var divorce_othernationality = $('#divorce_othernationality' + ps).val();
	var p_id = $('#census_id').val();
	var com_id = $("#comm_id").val();
	
	if($("select#marital_event" + ps).val() == "0") {
		alert("Please Select Marital Event");
		$("select#marital_event" + ps).focus();
		return false;
	}
	if($("input#spouse_name" + ps).val().trim() == "") {
		alert("Please Enter Name");
		$("input#spouse_name" + ps).focus();
		return false;
	}
	if($("input#divorce_date_of_birth" + ps).val().trim() == "" || $("input#divorce_date_of_birth" + ps).val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Date of Birth");
		$("input#divorce_date_of_birth" + ps).focus();
		return false;
	}
	if($("input#divorce_place_of_birth" + ps).val().trim() == "") {
		alert("Please Enter  Place of Birth");
		$("input#divorce_place_of_birth" + ps).focus();
		return false;
	}
	if($("select#divorce_nationality" + ps).val() == "0") {
		alert("Please Enter Nationality");
		$("select#divorce_nationality" + ps).focus();
		return false;
	}
	var divorce_nationalityOther = $("#divorce_nationality"+ps+" option:selected").text();
	if(divorce_nationalityOther.toUpperCase() == "OTHERS"){
		
		if($("#divorce_othernationality" + ps).val().trim() == "") {
			alert("Please Enter Nationality");
			$("#divorce_othernationality" + ps).focus();
			return false;
		}
	}
	if($("input#divorce_marriage_date" + ps).val().trim() == "" || $("input#divorce_marriage_date" + ps).val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Marriage Date");
		$("input#divorce_marriage_date" + ps).focus();
		return false;
	}
	if(getformatedDate(marriage_date_of_birth) >= getformatedDate(divorce_marriage_date)) {
		alert("Marriage Date should be Greater than Date of Birth");
		$("input#divorce_marriage_date" + ps).focus();
		return false;
	}
	if(calculate_age(document.getElementById('divorce_date_of_birth' + ps), document.getElementById('divorce_marriage_date' + ps)) && calculate_age(document.getElementById('dob_date'), document.getElementById('divorce_marriage_date' + ps))) {} else {
		return false;
	}
	
	if($("input#divorce_spouse_adhar_number" + ps).val().trim() == "" || $("input#divorce_spouse_adhar_number" + ps).val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#divorce_spouse_adhar_number" + ps).focus();
		return false;
	}
	if($("input#divorce_date" + ps).val().trim() == "" || $("input#divorce_date" + ps).val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Date of Divorce/Widow/Widower");
		$("input#divorce_date" + ps).focus();
		return false;
	}
	
	

	if(marital_status == '8') {
		
		var current_marriage_date = $("#marriage_date1").val();
		if(current_marriage_date.trim()=="") {
			alert("Please First Fill Up Current Marriage Details");
			return false;
		} else {
			
			if(getformatedDate(divorce_marriage_date) >= getformatedDate(current_marriage_date)) {
				alert("Marriage Date should be Lesser than Current Marriage Date");
				$("input#divorce_marriage_date" + ps).focus();
				return false;
			}
			if(getformatedDate(divorce_date) >= getformatedDate(current_marriage_date)) {
				alert("Divorce/Widow/Widower Date should be Lesser than Current Marriage Date");
				$("input#divorce_date" + ps).focus();
				return false;
			}
		}
		marital_status='9';
	}
	if(getformatedDate(divorce_marriage_date) >= getformatedDate(divorce_date)) {
		alert("Divorce/Widow/Widower Date should be Greater than Marriage Date");
		$("input#divorce_date" + ps).focus();
		return false;
	}
	
	$("#divorce_save" + ps).prop('disabled',true);
	$.post('family_divorce_action?' + key + "=" + value, {
		
		spouse_name: spouse_name,
		marital_event:marital_event,
		divorce_marriage_date: divorce_marriage_date,
		marriage_date_of_birth: marriage_date_of_birth,
		marriage_place_of_birth: marriage_place_of_birth,
		marriage_nationality: marriage_nationality,
		adhar_number: adhar_number,
		marital_status: marital_status,
		divorce_spouse_pan_number: divorce_spouse_pan_number,
		divorce_date: divorce_date,
		divo_ch_id: divo_ch_id,
		p_id: p_id,
		divorce_othernationality:divorce_othernationality,
		com_id: com_id
	}, function(data) {
		if(parseInt(data) > 0) {
			$('#divo_ch_id' + ps).val(data);
			$("#divorce_add" + ps).show();
			$("#divorce_remove" + ps).show();
			alert("Data Saved Successfully")
		} else alert(data);
		$("#divorce_save" + ps).prop('disabled',false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$("#divorce_save" + ps).prop('disabled',false);
	});
}

function getfamily_divoreceDetails() {
	var p_id = $('#census_id').val();
	var divorce = 1;
	var i = 0;
	$.post('getfamily_marriageData?' + key + "=" + value, {
		p_id: p_id,
		divorce: divorce
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$("input[name='divorce'][value='yes']").prop('checked', true);
			$('#divorce_table').show();
			$('#family_divotbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
				
				
				
				var pan_card = "";
				if(j[i].pan_card != null) pan_card = j[i].pan_card;
				
				var other_nation=""
					if(j[i].other_nationality != null) other_nation = j[i].other_nationality;
				
	$("table#divorce_table").append('<tr id="tr_divorce' + x + '">' + '<td class="divosr_no">' + x + '</td>' 
						
						+'<td><select name="marital_event' + x + '" '
						+' id="marital_event' + x + '" '
						+' 	class="form-control" >'
						+'		<option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getMarital_statusList}" varStatus="num">'
						+'		<c:if test="${item[0]==9 || item[0]==11 || item[0]==12}">'
						+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:if>'
						+'	</c:forEach>'
						+'</select></td>'
						
				+ '<td > <input type="text" id="spouse_name' + x + '" name="spouse_name' + x + '" value="' + j[i].maiden_name + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
					
					+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date_of_birth' + x + '" id="divorce_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' 
					+ '<td style="width: 15%;">' + '<input type="text" id="divorce_place_of_birth' + x + '" name="divorce_place_of_birth' + x + '" value="' + j[i].place_of_birth + '" onkeypress="return onlyAlphabets(event);" 	 oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" maxlength="50" >' 
					+ ' </td><td>' 
					+ '<select name="divorce_nationality' + x + '" id="divorce_nationality' + x + '" class="form-control"  onchange="divorceNationChange('+x+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
					+ '<td style="width: 13%;"><input type="text" id="divorce_othernationality' + x + '" name="divorce_othernationality' + x + '" value="'+other_nation+'"'
					+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" 	 readonly="readonly" maxlength="50" >'
					+ '	</td>'
					
					+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_marriage_date' + x + '" id="divorce_marriage_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].marriage_date) + '">' + '</td>'
					+ ' <td ><input type="text" id="divorce_spouse_adhar_number' + x + '" name="divorce_spouse_adhar_number' + x + '"  onkeypress="return isAadhar(this,event);" 	onkeydown="return AvoidSpace(event)"  value="' + j[i].adhar_number + '" class="form-control autocomplete" maxlength="14" autocomplete="off" >'
					
					+ '<td style="width: 10%;"> ' + '	<input type="text" id="divorce_spouse_pan_number' + x + '" name="divorce_spouse_pan_number' + x + '" ' + '	class="form-control autocomplete" 	onkeydown="return AvoidSpace(event)" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  value="' + pan_card + '"> ' + '	</td> ' 
					+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date' + x + '" id="divorce_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].divorce_date) + '">' + '</td>' 
					+ '<td style="display:none;"><input type="text" id="divo_ch_id' + x + '" name="divo_ch_id' + x + '"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" ><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "divorce_save' + x + '" onclick="divorce_save_fn(' + x + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "divorce_add' + x + '" onclick="divorce_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' 
					+ '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "divorce_remove' + x + '" onclick="divorce_remove_fn(' + x + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$("#divorce_nationality" + x).val(j[i].nationality);
				$("#marital_event" + x).val(j[i].marital_status);
				isAadhar(document.getElementById('divorce_spouse_adhar_number' + x))
				datepicketDate('divorce_date_of_birth' + x);
				datepicketDate('divorce_marriage_date' + x);
				datepicketDate('divorce_date' + x);
				divorceNationChange(x);
			}
			divo = v;
			divosr_no = v;
			$('#divorce_add' + divo).show();
			$("#fill_divorce_div").show();
		}
	});
}

function save_family_details() {
	
	var m_proff = $("#mother_profession option:selected").text();
	var f_proff = $("#father_profession option:selected").text();
	
	if($("input#father_name").val().trim() == "") {
		alert("Please Enter Father's Name ");
		$("input#father_name").focus();
		return false;
	}
	var fservice_radio = $("input[name='father_proff_radio']:checked").val();
	/* if($("input#father_dob").val() == "DD/MM/YYYY") {
		alert("Please Enter Father DOB");
		$("input#father_dob").focus();
		return false;
	} 
	if($("input#father_dob").val().trim() == "") {
		alert("Please Enter Father DOB");
		$("input#father_dob").focus();
		return false;
	}
	var father_dob = $("#father_dob").val();
	if(getformatedDate(father_dob) > getformatedDate(off_dob)) {
		alert("Please Enter Valid Father DOB");
		$("input#father_dob").focus();
		return false;
	}
	
	if($("input#father_adhar_number").val().trim() == "" || $("input#father_adhar_number").val().length < 14) {
		alert("Please Enter Father Aadhaar No");
		$("input#father_adhar_number").focus();
		return false;
	}
	if($("input#father_place").val().trim() == "") {
		alert("Please Enter Father's Place of Birth ");
		$("input#father_place").focus();
		return false;
	}
	
	if(fservice_radio == "yes") {
		if($("#father_service").val()=="0"){
			alert("Please Select Father Service");
			$("#father_service").focus();
			return false;
		}
		//26-01-1994
		if($("#father_service option:selected").text().toUpperCase() == 'OTHER' || $("#father_service option:selected").text().toUpperCase() == 'OTHERS') {
// 		if($("#father_service").val()=="4"){
			if($("#father_other").val().trim()==''){
				alert("Please Enter Other Service");
				$("#father_other").focus();
				return false;
			}
		}
		if($("#father_service").val()=="1"){
			if($("#father_personal_no").val().trim()==''){
				alert("Please Enter Father Personal No");
				$("#father_personal_no").focus();
				return false;
			}
		}
		if($("#father_personal_no").val()!=''){
			if($("#father_personal_no").val().length < 7 || $("#father_personal_no").val().length > 9){
			alert("Please Enter Valid Father Personal No");
			$("#father_personal_no").focus();
			return false;
			}
		}
	}
	else{
		if($("#father_profession").val() == "0") {
			alert("Please Enter Father's Profession ");
			$("#father_profession").focus();
			return false;
		}
		
		if(f_proff == other){
			if($("#father_proffother").val().trim()==""){
				alert("Please Enter Father's Other Profession ");
				$("#father_proffother").focus();
				return false;
			}
		}
		
		
	}
	*/
	
	if($("input#mother_name").val().trim() == "") {
		alert("Please Enter Mother's Name ");
		$("input#mother_name").focus();
		return false;
	}
	var mservice_radio = $("input[name='mother_proff_radio']:checked").val();

	/*
	if($("input#mother_dob").val() == "DD/MM/YYYY") {
		alert("Please Enter Mother's DOB");
		$("input#mother_dob").focus();
		return false;
	}
	if($("input#mother_dob").val().trim() == "") {
		alert("Please Enter Mother's DOB  ");
		$("input#mother_dob").focus();
		return false;
	}
	var mother_dob = $("#mother_dob").val();
	if(getformatedDate(mother_dob) > getformatedDate(off_dob)) {
		alert("Please Enter Valid Mother DOB");
		$("input#mother_dob").focus();
		return false;
	}
	
	
	if($("input#mother_adhar_number").val().trim() == "" || $("input#mother_adhar_number").val().length < 14) {
		alert("Please Enter Mother Aadhaar No");
		$("input#mother_adhar_number").focus();
		return false;
	}
	
	if($("input#mother_place").val().trim() == "") {
		alert("Please Enter Mother's Place of Birth ");
		$("input#mother_place").focus();
		return false;
	}
	
		if(mservice_radio == "yes") {
		if($("#mother_service").val()=="0"){
			alert("Please Select Mother Service");
			$("#mother_service").focus();
			return false;
		}
		//26-01-1994
		if($("#mother_service option:selected").text().toUpperCase() == 'OTHER' || $("#mother_service option:selected").text().toUpperCase() == 'OTHERS') {
// 		if($("#mother_service").val()=="4"){
			if($("#mother_other").val().trim()==""){
				alert("Please Enter Other Service");
				$("#mother_other").focus();
				return false;
			}
		}
		if($("#mother_service").val()=="1"){
			if($("#mother_personal_no").val().trim()==""){
				alert("Please Enter Mother Personal No");
				$("#mother_personal_no").focus();
				return false;
			}
		}
		if($("#mother_personal_no").val().trim()!=''){
			if($("#mother_personal_no").val().length < 7 || $("#mother_personal_no").val().length > 9){
			alert("Please Enter Valid Mother Personal No");
			$("#mother_personal_no").focus();
			return false;
			}
		}
	}
	else{
		if($("#mother_profession").val() == "0") {
			alert("Please Enter Mother's Profession ");
			$("#mother_profession").focus();
			return false;
		}

		if(m_proff == other){
			if($("#mother_proffother").val().trim()==""){
				alert("Please Enter Mother's Other Profession ");
				$("#mother_proffother").focus();
				return false;
			}
		}
		
	}
	
	if(m_proff.toUpperCase() != other && m_proff.toUpperCase() != 'OTHER')
		m_proff='';
	if(f_proff.toUpperCase() != other && f_proff.toUpperCase() != 'OTHER')
		f_proff='';
	 	var a = $('input:radio[name=spouse_radio]:checked').val();
	     
	    if(a == undefined){	
	        
	        alert("Please select Details of Spouse ");
	        return false;
	        }
	    var b = $('input:radio[name=divorce]:checked').val();
	     
	    if(b == undefined){	
	        
	        alert("Please select Details of Devorce ");
	        return false;
	        } */
	        
	        
	        $('#save_family_details_btn').prop('disabled', true);
	var father_name = $("#father_name").val();
	var father_dob = $("#father_dob").val();
	var father_place_birth = $("#father_place").val();
	var father_profession = $("#father_profession").val();
	var mother_name = $("#mother_name").val();
	var mother_dob = $("#mother_dob").val();
	var mother_place_birth = $("#mother_place").val();
	var mother_profession = $("#mother_profession").val();
	var father_name = $("#father_name").val();
	var father_dob = $("#father_dob").val();
	var father_place_birth = $("#father_place").val();
	var father_profession = $("#father_profession").val();
	var father_pan_no = $("#father_pan_no").val();
	var mother_pan_no = $("#mother_pan_no").val();
	
	var father_service = $("#father_service").val();
	var mother_service = $("#mother_service").val();
	var father_other = $("#father_other").val();
	var mother_other = $("#mother_other").val();
	
	// bisag v2 03072023(Observation)
	var father_proffother = $("#father_proffother").val();
	var mother_proffother = $("#mother_proffother").val();
	
// 	var f_otherproff = $("#father_proffother").val();
// 	var m_otherproff = $("#mother_proffother").val();
	
	var father_personal_no = $("#father_personal_no").val();
	var mother_personal_no = $("#mother_personal_no").val();
	var ismotherInservice = mservice_radio;
	var isfatherInservice = fservice_radio;
	
	var father_adhar_number = $('#father_adhar_number').val().split('-').join('');
	var mother_adhar_number = $('#mother_adhar_number').val().split('-').join('');
	var p_id = $('#census_id').val();
	$.post('saveFamilyDetailsAction?' + key + "=" + value, {
		isfatherInservice:isfatherInservice,
		ismotherInservice:ismotherInservice,
		mother_personal_no:mother_personal_no,
		father_personal_no:father_personal_no,
		mother_other:mother_other,
		father_other:father_other,
		mother_service:mother_service,
		father_service:father_service,
		father_name: father_name,
		father_dob: father_dob,
		father_adhar_number: father_adhar_number,
		father_pan_no: father_pan_no,
		father_place_birth: father_place_birth,
		father_profession: father_profession,
		mother_name: mother_name,
		mother_adhar_number: mother_adhar_number,
		mother_pan_no: mother_pan_no,
		mother_dob: mother_dob,
		mother_place_birth: mother_place_birth,
		mother_profession: mother_profession,
		p_id: p_id,
// 		f_otherproff:f_otherproff,
// 		m_otherproff:m_otherproff,
		father_proffother:father_proffother,
		mother_proffother:mother_proffother,
		m_proff:m_proff,
		f_proff:f_proff
		
	}, function(j) {
		
			alert(j);
			openCity(event, 'Foreign_Countries');
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[3].classList.add("active");
			  $('#save_family_details_btn').prop('disabled', false);
	});
}

function siblingCB(a) {
	if ($('#ser-ex'+a).is(":checked"))
	{
		$('#sibling_service' + a).attr('readonly', false);
		$('#sibling_personal_no' + a).attr('readonly', false);
	}
	else{

		$('#sibling_service' + a).attr('readonly', true);
		$('#sibling_personal_no' + a).attr('readonly', true);
		$('#other_sibling_ser' + a).attr('readonly', true);
		$('select#sibling_service' + a).val(0);
		$('#sibling_personal_no' + a).val("");
		$('#other_sibling_ser' + a).val("");
	}
}


lang = 1;
flang = 1;
lang_srno = 1;
flang_srno = 1;

function spouseCB(a) {
	if ($('#spo-ex'+a).is(":checked"))
	{
		
		$("#spouse_proffDiv").hide();
		$("#th_spouseprof").hide();
		$('#spouse_service' + a).attr('readonly', false);
		$('#spouse_personal_no' + a).attr('readonly', false);
	}
	else{
	
		
		$("#spouse_proffDiv").show();
		$("#th_spouseprof").show();
		$('#spouse_service' + a).attr('readonly', true);
		$('#spouse_personal_no' + a).attr('readonly', true);
		$('#other_spouse_ser' + a).attr('readonly', true);
		$('#spouse_service' + a).val(0);
		$('#spouse_personal_no' + a).val("");
		$('#other_spouse_ser' + a).val("");
	}
	
}

function otherfunction(k){
	var spouse = $( "#spouse_service"+k+" option:selected" ).text();
	var sibling = $( "#sibling_service"+k+" option:selected" ).text();
	
	if (spouse.toUpperCase() == "OTHER"){
		$('#other_spouse_ser' + k).attr('readonly', false);
		$('#spouse_personal_no' + k).attr('readonly', true);
		$('#spouse_personal_no' + k).val('');
	}
	if (spouse.toUpperCase() != "OTHER" && spouse != "--Select--"){
		$('#other_spouse_ser' + k).attr('readonly', true);
		$('#spouse_personal_no' + k).attr('readonly', false);
		$('#other_spouse_ser' + k).val('');
	}
	if(sibling.toUpperCase() == "OTHER"){
		$('#other_sibling_ser' + k).attr('readonly', false);	
		$('#sibling_personal_no' + k).attr('readonly', true);
		$('#sibling_personal_no' + k).val('');
	}
	if(sibling.toUpperCase() != "OTHER" && sibling != "--Select--"){
		$('#other_sibling_ser' + k).attr('readonly', true);
		$('#sibling_personal_no' + k).attr('readonly', false);
		$('#other_sibling_ser' + k).val('');
	}
}

function language_add_fn(q) {
	if($('#language_add' + q).length) {
		$("#language_add" + q).hide();
	}
	lang = q + 1;
	if(q != 0) lang_srno += 1;
	$("table#language_table").append('<tr id="tr_lang' + lang + '">' 
			+ '<td class="lang_srno">' + lang_srno + '</td>' 
			+ '<td style="width: 10%;">' 
			+ '<select  id="language' + lang + '" name="language' + lang + '"  onchange="onLanguage('+lang+')" class="form-control autocomplete"  >' 
			+ ' <option value="0">--Select--</option>' 
			+ '<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">' 
			+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+ '</c:forEach>' + '</select>' 
			+ '</td>' 
			
			+'<td style="width: 10%;"> <input type="text" id="other_language'+lang+'" name="other_language'+lang+'" onkeypress="return onlyAlphabets(event);"	   readonly  class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>'
			
			+ '<td style="width: 10%;"> '
			+'<select name="lang_std'+lang+'" id="lang_std'+lang+'" onchange="onLanguage_std('+lang+')" class="form-control" >'
			+' <option value="0">--Select--</option>'
			+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
			+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+'	</c:forEach>'
			+'</select>'
			+'</td> '
			+'<td style="width: 10%;">'
			+'<input type="text" id="other_lang_std'+lang+'" name="other_lang_std'+lang+'" onkeypress="return onlyAlphabets(event);" readonly	  class="form-control autocomplete" autocomplete="off"  maxlength="50" >'
			+'</td>'
 	
			+ '<td style="display:none;">'
			+'<input type="text" id="lang_ch_id' + lang + '" name="lang_ch_id' + lang + '"  value="0" class="form-control autocomplete" autocomplete="off" >'
			+'</td>' 
			+ '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save' + lang + '" onclick="language_save_fn(' + lang + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add' + lang + '" onclick="language_add_fn(' + lang + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove' + lang + '" onclick="language_remove_fn(' + lang + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
}

function language_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var lang_ch_id = $('#lang_ch_id' + R).val();
		$.post('language_delete_action?' + key + "=" + value, {
			lang_ch_id: lang_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_lang" + R).remove();
				if(R == lang) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#language_add' + i).length) {
							$("#language_add" + i).show();
							temp = false;
							lang = i;
							break;
						}
					}
					if(temp) {
						language_add_fn(0);
					}
				}
				$('.lang_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					lang_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function onLanguage(ind){
	var language = $("#language"+ind+" option:selected").text();
	if(language.toUpperCase() != "OTHERS"){
		$('#other_language'+ind).val("");
		$('#other_language'+ind).attr('readonly', true);
	}
	else
		$('#other_language'+ind).attr('readonly', false);
}
function onLanguage_std(indl){
	var country_sel = $("#lang_std"+indl+" option:selected").text();
	if(country_sel.toUpperCase() != "OTHERS"){
		$('#other_lang_std'+indl).val("");
		$('#other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#other_lang_std'+indl).attr('readonly', false);
}


function language_save_fn(ps) {
	var language = $('#language' + ps).val();
	var other_language = $('#other_language' + ps).val();
	var lang_std = $('#lang_std' + ps).val();
	var other_lang_std = $('#other_lang_std' + ps).val();
	var lang_ch_id = $('#lang_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	if(language == "0") {
		alert("Please Select Language");
		$("select#language" + ps).focus();
		return false;
	}
	var language_text = $("#language" + ps+" option:selected").text();
	if(language_text == other){
		if($("#other_language"+ps).val().trim()==""){
			alert("Please Enter Other Language ");
			$("#other_language"+ps).focus();
			return false;
		}
	}
	
	if(lang_std == "0") {
		alert("Please Select Language Standard");
		$("select#lang_std" + ps).focus();
		return false;
	}
	
	var languagestd_text = $("#lang_std" + ps+" option:selected").text();
	if(languagestd_text == other){
		if($("#other_lang_std"+ps).val().trim()==""){
			alert("Please Enter Other Language Standard");
			$("#other_lang_std"+ps).focus();
			return false;
		}
	}
	
	var com_id = $("#comm_id").val();
	$("#language_save"+ps).prop('disabled',true);
	$.post('language_detail_action?' + key + "=" + value, {
		language: language,
		other_language :other_language,
		lang_std: lang_std,
		other_lang_std:other_lang_std,
		lang_ch_id: lang_ch_id,
		p_id: p_id,
		com_id: com_id
	}, function(data) {
		if(parseInt(data) > 0) {
			$('#lang_ch_id' + ps).val(data);
			$("#language_add" + ps).show();
			$("#language_remove" + ps).show();
			alert("Data Saved Successfully")
		} else alert(data);
		$("#language_save"+ps).prop('disabled',false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$("#language_save"+ps).prop('disabled',false);
	});
}

function get_language_details() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getlanguagedetailsData?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			xl = 0;
			xf = 0;
			for(i; i < v; i++) {
				if(j[i].language != "0") {
					xl = xl + 1;
					if(xl == 1) {
						$('#langtbody').empty();
					}
					$("table#language_table").append('<tr id="tr_lang' + xl + '">' 
							+ '<td class="lang_srno">' + xl 
							+ '</td>' 
							+ '<td style="width: 10%;"> '
							+ '<select  id="language' + xl + '" name="language' + xl + '" value="' + j[i].language + '" onchange="onLanguage('+xl+')"  class="form-control autocomplete"  >'
							+ ' <option value="0">--Select--</option>' 
							+ '<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">' 
							+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
							+ '</c:forEach>' 
							+ '</select>' 
							+ '</td>'
							
							+'<td style="width: 10%;">' 
							+'<input type="text" id="other_language'+xl+'" name="other_language'+xl+'" 	 onkeypress="return onlyAlphabets(event);" value="' + j[i].other_language + '"  class="form-control autocomplete" autocomplete="off" maxlength="50" >'
							+'</td>'
							
							+ '<td style="width: 10%;"> ' 
							+ '<select name="lang_std' + xl + '" id="lang_std' + xl + '"  onchange="onLanguage_std('+xl+')" class="form-control" >' 
							+ ' <option value="0">--Select--</option>' 
							+ '	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">' 
							+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
							+ '	</c:forEach>' 
							+ '</select> '
							+'</td> ' 
							+'<td style="width: 10%;"> <input type="text" id="other_lang_std'+xl+'" name="other_lang_std'+xl+'" value="'+j[i].other_lang_std+'" class="form-control autocomplete" onkeypress="return onlyAlphabets(event);" autocomplete="off" 	 maxlength="50" ></td>'
							+ '<td style="display:none;"><input type="text" id="lang_ch_id' + xl + '" name="lang_ch_id' + xl + '"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "language_save' + xl + '" onclick="language_save_fn(' + xl + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "language_add' + xl + '" onclick="language_add_fn(' + xl + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "language_remove' + xl + '" onclick="language_remove_fn(' + xl + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
					$('#lang_std' + xl).val(j[i].lang_std);
					$('#language' + xl).val(j[i].language);
					onLanguage(xl);
					 onLanguage_std(xl);
				} else {
					xf = xf + 1;
					if(xf == 1) {
						$('#flangtbody').empty();
					}
					$("table#foregin_language_table").append('<tr id="tr_flang' + xf + '">'
							+ '<td class="flang_srno">' + xf + '</td>' 
							+ '<td style="width: 10%;"> '
							+'<select  id="flanguage' + xf + '" name="flanguage' + xf + '" value="' + j[i].foreign_language + '" class="form-control autocomplete"  onchange="onF_Language('+xf+')">' 
							+ '  <option value="0">--Select--</option>'
							+ '	<c:forEach var="item" items="${getForeignLanguageList}" varStatus="num">' 
							+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
							+ '	</c:forEach>' + '	</select></td>' 
							
							+'<td style="width: 10%;">'
							+'<input type="text" id="f_other_language'+xf+'" name="f_other_language'+xf+'" value="'+j[i].f_other_language+'" readonly	class="form-control autocomplete" autocomplete="off"  maxlength="50"  onkeypress="return onlyAlphabets(event);"></td>'
							
							
							
							+ '<td style="width: 10%;"> '
							+ '<select name="flang_std' + xf + '" id="flang_std' + xf + '" class="form-control" onchange="onF_Language_std('+xf+')" >' 
							+ ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">' 
							+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' 
							+ '</select> </td> ' 
							
							+'<td style="width: 10%;">'
							+'<input type="text" id="f_other_lang_std'+xf+'" name="f_other_lang_std'+xf+'" readonly onkeypress="return onlyAlphabets(event);" 	 value="'+j[i].f_other_lang_std+'"   class="form-control autocomplete" autocomplete="off"  maxlength="50" ></td>'

							
							+ '<td style="width: 10%;">'
							+'<select name="lang_prof' + xf + '" id="lang_prof' + xf + '" class="form-control"  onchange="onF_Language_prof('+xf+')"  >' 
							+ ' <option value="0">--Select--</option>' 
							+ '	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">' 
							+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
							+ '	</c:forEach>' 
							+ ' </select></td> ' 
							
							+'<td style="width: 10%;">'
							+'<input type="text" id="f_other_prof'+xf+'" name="f_other_prof'+xf+'" value="'+j[i].f_other_prof+'" onkeypress="return onlyAlphabets(event);"	 class="form-control autocomplete" autocomplete="off" maxlength="50"  ></td>'
							
							
							+ '<td style="width: 10%;"> <input type="text" id="exam_pass' + xf + '" onkeypress="return isNumber(event);"   name="exam_pass' + xf + '" value="' + j[i].f_exam_pass + '"   class="form-control autocomplete" autocomplete="off" maxlength="4" ></td>'
						+ '<td style="display:none;"><input type="text" id="flang_ch_id' + xf + '" name="flang_ch_id' + xf + '"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save' + xf + '" onclick="foregin_language_save_fn(' + xf + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add' + xf + '" onclick="foregin_language_add_fn(' + xf + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove' + xf + '" onclick="foregin_language_remove_fn(' + xf + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
					$('#flang_std' + xf).val(j[i].lang_std);
					$('#lang_prof' + xf).val(j[i].f_lang_prof);
					$('#flanguage' + xf).val(j[i].foreign_language);
					 onF_Language(xf);
					 onF_Language_std(xf);
					 onF_Language_prof(xf);
		
				}
			}
			if(xl != 0) {
				lang = xl;
				lang_srno = xl;
				$("#language_add" + xl).show();
			}
			if(xf != 0) flang = xf;
			flang_srno = xf;
			$("#foregin_language_add" + xf).show();
		}
	});
}

function onF_Language(ind){
	var country_sel = $("#flanguage"+ind+" option:selected").text();
	if(country_sel.toUpperCase() != "OTHERS"){
		$('#f_other_language'+ind).val("");
		$('#f_other_language'+ind).attr('readonly', true);
	}
	else
		$('#f_other_language'+ind).attr('readonly', false);
}
function onF_Language_std(indl){
	var country_sel = $("#flang_std"+indl+" option:selected").text();
	if(country_sel.toUpperCase() != "OTHERS"){
		$('#f_other_lang_std'+indl).val("");
		$('#f_other_lang_std'+indl).attr('readonly', true);
	}
	else
		$('#f_other_lang_std'+indl).attr('readonly', false);
}
function onF_Language_prof(indl){

	var country_sel = $("#lang_prof"+indl+" option:selected").text();
	if(country_sel.toUpperCase() != "OTHERS"){
		$('#f_other_prof'+indl).val("");
		$('#f_other_prof'+indl).attr('readonly', true);
	}
	else
		$('#f_other_prof'+indl).attr('readonly', false);
}



function foregin_language_add_fn(q) {
	if($('#foregin_language_add' + q).length) {
		$("#foregin_language_add" + q).hide();
	}
	flang = q + 1;
	if(q != 0) flang_srno += 1;
	$("table#foregin_language_table").append('<tr id="tr_flang' + flang + '">' 
			+ '<td class="flang_srno">' + flang_srno + '</td>' 
			+ '<td style="width: 10%;"> <select id="flanguage' + flang + '" name="flanguage' + flang + '" class="form-control autocomplete" onchange="onF_Language('+flang+')" >' 
			+ '   <option value="0">--Select--</option>' 
			+ '	<c:forEach var="item" items="${getForeignLanguageList}" varStatus="num">' 
			+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+ '	</c:forEach>' + '	</select></td>' 
			
			+'<td style="width: 10%;">'
			+'<input type="text" id="f_other_language'+flang+'" name="f_other_language'+flang+'" class="form-control autocomplete" autocomplete="off" maxlength="50" readonly onkeypress="return onlyAlphabets(event);"></td>'
			
			+ '<td style="width: 10%;"> ' 
			+ '<select name="flang_std' + flang + '" id="flang_std' + flang + '" class="form-control"  onchange="onF_Language_std('+flang+')">' 
			+ ' <option value="0">--Select--</option>' 
			+ '	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
			+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+ '	</c:forEach>'
			+ '</select>' 
			+ '</td> '
			
			+'<td style="width: 10%;">'
			+'<input type="text" id="f_other_lang_std'+flang+'" name="f_other_lang_std'+flang+'" maxlength="50"  onkeypress="return onlyAlphabets(event);" 	readonly class="form-control autocomplete" autocomplete="off"  ></td>'

			
			+ '<td style="width: 10%;"> '
			+'<select name="lang_prof' + flang + '" id="lang_prof' + flang + '" class="form-control"  onchange="onF_Language_prof('+flang+')" >' 
			+ ' <option value="0">--Select--</option>' 
			+ '	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">' 
			+ '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
			+ '	</c:forEach>' + ' </select>'
			+'</td> ' 
			
			+'<td style="width: 10%;">'
			+'<input type="text" id="f_other_prof'+flang+'" name="f_other_prof'+flang+'" class="form-control autocomplete" maxlength="50" readonly	onkeypress="return onlyAlphabets(event);"  autocomplete="off"  ></td>'
			
			
			+ '<td style="width: 10%;"> '
			+'<input type="text" id="exam_pass' + flang + '" onkeypress="return isNumber(event)"  name="exam_pass' + flang + '" 	onkeydown="return AvoidSpace(event)" class="form-control autocomplete" autocomplete="off" maxlength="4">'
			+'</td>' 
			+ '<td style="display:none;">'
			+'<input type="text" id="flang_ch_id' + flang + '" name="flang_ch_id' + flang + '"  value="0" class="form-control autocomplete" autocomplete="off" >'
			+'</td>' 
			+ '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "foregin_language_save' + flang + '" onclick="foregin_language_save_fn(' + flang + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "foregin_language_add' + flang + '" onclick="foregin_language_add_fn(' + flang + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "foregin_language_remove' + flang + '" onclick="foregin_language_remove_fn(' + flang + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
}

function foregin_language_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var lang_ch_id = $('#flang_ch_id' + R).val();
		$.post('language_delete_action?' + key + "=" + value, {
			lang_ch_id: lang_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_flang" + R).remove();
				if(R == flang) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#foregin_language_add' + i).length) {
							$("#foregin_language_add" + i).show();
							temp = false;
							flang = i;
							break;
						}
					}
					if(temp) {
						foregin_language_add_fn(0);
					}
				}
				$('.flang_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					flang_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function foregin_language_save_fn(ps) {
	var language = $('#flanguage' + ps).val();
	var lang_prof = $('#lang_prof' + ps).val();
	var exam_pass = $('#exam_pass' + ps).val();
	var lang_ch_id = $('#flang_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	var lang_std = $('#flang_std' + ps).val();
	
	var f_other_prof=$('#f_other_prof'+ps).val();
	var f_other_lang_std=$('#f_other_lang_std'+ps).val();
	
	var f_other_language=$('#f_other_language'+ps).val();
	 
	 if (language == "0") {
			alert("Please select Language");
			$("select#flanguage"+ps).focus();
			return false;
		}
		 var country_sel = $("#flanguage"+ps+" option:selected").text();
		
		if (country_sel.toUpperCase() == "OTHERS" && f_other_language.trim()=="") {
			alert("Please Enter Other Language");
			$('#f_other_language'+ps).focus();
			return false;
		} 
	 if (lang_std == "0") {
			alert("Please Select Language Standard");
			$("select#flang_std"+ps).focus();
			return false;
		}	
	
	 var flang_std = $("#flang_std"+ps+" option:selected").text();
		if (flang_std.toUpperCase() == "OTHERS" && f_other_lang_std.trim()=="") {
			alert("Please Enter Other Language Standard");
			$('#f_other_lang_std'+ps).focus();
			return false;
		} 
	 if (lang_prof == "0") {
			alert("Please Select Language Proficiency");
			$("select#lang_prof"+ps).focus();
			return false;
		}
	 var country_selp = $("#lang_prof"+ps+" option:selected").text();
		if (country_selp.toUpperCase() == "OTHERS" && f_other_prof.trim()=="") {
			alert("Please Enter Other Language Proficiency");
			$('#f_other_prof'+ps).focus();
			return false;
		} 
	if(exam_pass.trim()=="") {
		alert("Please Enter  Exam Passed Year");
		$("input#exam_pass" + ps).focus();
		return false;
	}
	var dateofBirthyear = off_dob.split('/')[2];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var com_id = $("#comm_id").val();
	$('#foregin_language_save'+ps).prop('disabled',true);
	$.post('foreginlanguage_detail_action?' + key + "=" + value, {
		language: language,
		lang_prof: lang_prof,
		exam_pass: exam_pass,
		lang_std: lang_std,
		lang_ch_id: lang_ch_id,
		f_other_language:f_other_language,
		  f_other_lang_std:f_other_lang_std,
		  f_other_prof:f_other_prof,
		p_id: p_id,
		com_id: com_id,
		dateofBirthyear: dateofBirthyear
	}, function(data) {
		if(parseInt(data) > 0) {
			$('#flang_ch_id' + ps).val(data);
			$("#foregin_language_add" + ps).show();
			$("#foregin_language_remove" + ps).show();
			alert("Data Saved Successfully")
		} else alert(data);
		$('#foregin_language_save'+ps).prop('disabled',false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$('#foregin_language_save'+ps).prop('disabled',false);
	});
}

qua = 1;
qua_srno = 1;

function qualification_add_fn(q) {
	if($('#qualification_add' + q).length) {
		$("#qualification_add" + q).hide();
	}
	qua = q + 1;
	if(q != 0) qua_srno += 1;
	$("table#qualification_table").append('<tr id="tr_qualification' + qua + '">'
			+ '<td class="qua_srno">' + qua_srno + '</td>' 
			+ '<td style="width: 10%;"><select name="type' + qua + '" id="type' + qua + '" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getQualificationTypeList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>'
			+ '<td style="width: 10%;"><input type="text" id="examination_pass' + qua + '" name="examination_pass' + qua + '" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>' 
			+ '<td style="width: 10%;"><input type="text" id="passing_year' + qua + '" name="passing_year' + qua + '" maxlength="4" class="form-control autocomplete" autocomplete="off"  ></td>'
			+ '<td style="width: 10%;"><input type="text" id="div_class' + qua + '" name="div_class' + qua + '" class="form-control autocomplete" autocomplete="off" maxlength="100" ></td>' 
			+ '<td style="width: 10%;"><input type="text" id="subject' + qua + '" name="subject' + qua + '" class="form-control autocomplete" autocomplete="off" maxlength="50" ></td>' 
			+ '<td style="width: 10%;"><input type="text" id="institute' + qua + '" name="institute' + qua + '" class="form-control autocomplete" autocomplete="off" maxlength="100" ></td>'
		/* +'<td style="width: 10%;"><select name="institute'+qua+'" id="institute'+qua+'" class="form-control-sm form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getInstituteNameList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'		 */
		+ '<td style="display:none;"><input type="text" id="qualification_ch_id' + qua + '" name="qualification_ch_id' + qua + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "qualification_save' + qua + '" onclick="qualification_save_fn(' + qua + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "qualification_add' + qua + '" onclick="qualification_add_fn(' + qua + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "qualification_remove' + qua + '" onclick="qualification_remove_fn(' + qua + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
}

function qualification_remove_fn(R) {
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var qualification_ch_id = $('#qualification_ch_id' + R).val();
		$.post('qualification_delete_action?' + key + "=" + value, {
			qualification_ch_id: qualification_ch_id
		}, function(data) {
			if(data == '1') {
				$("tr#tr_qualification" + R).remove();
				if(R == qua) {
					R = R - 1;
					var temp = true;
					for(i = R; i >= 1; i--) {
						if($('#qualification_add' + i).length) {
							$("#qualification_add" + i).show();
							temp = false;
							qua = i;
							break;
						}
					}
					if(temp) {
						qualification_add_fn(0);
					}
				}
				$('.qua_srno').each(function(i, obj) {
					obj.innerHTML = i + 1;
					qua_srno = i + 1;
				});
				alert("Data Deleted Successfully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function getQualifications() {
	var q_id = $('#census_id').val();
	var i = 0;
	$.post('getQualificationData?' + key + "=" + value, {
		q_id: q_id
	}, function(j) {
		var v = j.length;
		$('#qualificationtbody').empty();
		if(v != 0) {
			
			for(i; i < v; i++) {
				qu = i + 1;
				var examother="--";
				var classother="--";
				var deg_other="--";
				var spec_other="--";
				var exampass = "--";				
				var deg_name = "--";
				var specialization = "--";
				if(j[i].exp_name != null) exampass = j[i].exp_name;
			
				if(j[i].d_name != null) deg_name = j[i].d_name;
				if(j[i].spce_name != null) specialization = j[i].spce_name;
				
				if(j[i].exam_other!=null){
					examother=j[i].exam_other;
					exampass=j[i].exam_other;
				}
		  	    
		  	  if(j[i].degree_other!=null){
		  		deg_name=j[i].degree_other;
		  		deg_other=j[i].degree_other;
		  	  }
		  	   if(j[i].specialization_other!=null)
		  		   {
		  		 specialization=j[i].specialization_other;
		  		spec_other=j[i].specialization_other;
		  		   }
		  	    

				var divclass = $('#div_class option[value="' + j[i].div_class + '"]').text();
				 if(j[i].class_other!=null){
			  	    	classother=j[i].class_other;
			  	    	divclass=j[i].class_other;
				 }
				var subjectslist = j[i].subject.split(',');
				var subjects = "";
				for(k = 0; k < subjectslist.length; k++) {
					if(k != 0) subjects += ",";
					subjects += $("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").parent().text();
				}
				var Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Qualification ?') ){editQualificationData(" + j[i].id + "," + j[i].type + ",'" +  j[i].examination_pass + "'," + j[i].passing_year + ",'" + j[i].degree + "','" + j[i].specialization + "'," + j[i].div_class + ",'" + j[i].subject + "','" + j[i].institute + "','" + examother + "','" + classother + "','" + deg_other + "','" + spec_other + "')}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				var Delete = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Qualification ?') ){deleteQualificationData(" + j[i].id + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";
				var action = f + f1;
				$("table#qualificationtable").append('<tr>' 
						+ '<td style="font-size: 15px;text-align: center ;width: 10%;">' + qu + '</td> ' 
						+ '<td style="font-size: 15px;text-align: center">' + j[i].label + '</td>	' 
						+ '<td style="font-size: 15px;text-align: center">' + exampass + '</td> ' 
				 + '<td style="font-size: 15px;text-align: center">' + deg_name + '</td>'
				 +   '<td style="font-size: 15px;text-align: center">' + specialization + '</td>' +
				'<td style="font-size: 15px;text-align: center">' + j[i].passing_year + '</td>' 
				+ '<td style="font-size: 15px;text-align: center">' + subjects + '</td>' 
				+ '<td style="font-size: 15px;text-align: center">' + divclass + '</td>'  
				+ '<td style="font-size: 15px;text-align: center">' + j[i].institute + '</td>' 
				 + '<td class="hide-action" style="font-size: 15px; text-align: center;">' + action + '</td>' + '</tr>');
			}
		} else {
			$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px; text-align: center; color: red;" colspan="10">Data Not Available</td>' + '</tr>');
		}
	});
}

function editQualificationData(id, type, exampass, passing_year, degree,  specialization, div_class, subject, institute,examother,classother,deg_other,spec_other) {
	console.log(exampass+" "+degree+" "+specialization);
	$('#yrOfPass').val(passing_year);
	$('#div_class').val(div_class);
	$('#institute_place').val(institute);
	$('#qualification_ch_id').val(id);
	$("input[type=checkbox][name='multisub']").prop("checked", false);
	var subjectslist = subject.split(',');
	for(k = 0; k < subjectslist.length; k++) {
		$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
		$("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
	}
	$('#quali_sub_list').empty();
	$("input[type='checkbox'][name='multisub']").each(function() {
		if(this.checked) {
			$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
		}
	});
	$('#quali_type').val(type);
	var typethisT = document.getElementById('quali_type');
//	fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
	if(exampass != '--') {
		
		$('#quali').val(exampass);
		var qualithisT = document.getElementById('quali');
		fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
	}
	
	 if(examother!='--')
	  	$('#exam_other').val(examother);
	 if(classother!='--')
		 $('#class_other').val(classother);
	 if(deg_other!='--')
		 $('#quali_deg_other').val(deg_other);
	
	 
	
	  
	
	if(degree != '--') {
		$('#quali_degree').val(degree);
		
	}
	if(specialization != '--') $('#specialization').val(specialization);
	 if(spec_other!='--')
		 $('#quali_spec_other').val(spec_other);
	 fn_other_exam();
	 fn_other_class();
	 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
	 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
}

function deleteQualificationData(id) {
	var qualification_ch_id = id;
	$.post('qualification_delete_action?' + key + "=" + value, {
		qualification_ch_id: qualification_ch_id
	}, function(data) {
		if(data == '1') {
			getQualifications();
			alert("Data Deleted Successfully");
		} else {
			alert("Data not Deleted ");
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}



function fn_qualification_typeChange(obj,set_id,sec_id,th_id) {
	var q_type = obj.value;
	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(q_type!='0'){
		$.post('GetExaminatonPassed?' + key + "=" + value, {
			q_type: q_type
			
		}, function(j) {
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
		});
	}
	
	var qualithisT = document.getElementById(set_id);
	fn_ExaminationTypeChange(qualithisT,sec_id,th_id);
	
	
	
}

function fn_ExaminationTypeChange(obj,set_id,sec_id) {
	var e_pass =  obj.value;

	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	if(e_pass!='0'){
		$.post('GetExaminatonPassedDegree?' + key + "=" + value, {
			e_pass: e_pass
			
		}, function(j) {
			
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
			
		});
	}
	

	 fn_other_exam();	
	 fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
	 fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
	 
	 fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
}



function fn_setSpecialization(obj,set_id) {
	var degree =  obj.value;

	var options = '<option value="0" >--Select--</option>';
	$("select#"+set_id).html(options);
	
	if(degree!='0'){
		$.post('GetDegreeSpecialization?' + key + "=" + value, {
			degree: degree
			
		}, function(j) {
			
			if(j.length > 0) {
			
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				$("select#"+set_id).html(options);
			}
			
		});
	}
	
	
	
	
}


function qualification_save_fn(qs) {
	var dateofBirthyear = off_dob.split('/')[2];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var type = $('#quali_type').val();
	var examination_pass = $('#quali').val();
	var exam_other_qua=$('#exam_other').val();
  	var degree_other=$('#quali_deg_other').val();
	var spec_other=$('#quali_spec_other').val();
	var class_other_qua=$('#class_other').val();
	
	var passing_year = $('#yrOfPass').val();
	var degree = $('#quali_degree').val();
	var specialization = $('#specialization').val();
	var div_class = $('#div_class').val();
	var institute = $('#institute_place').val();
	var qualification_ch_id = $('#qualification_ch_id').val();
	var q_id = $('#census_id').val();
	var com_id = $('#comm_id').val();
	var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var subject = subjectvar.join(",");
	

		if(type == null || type == "0") {
			alert("Please Select Qualification Type");
			$("#quali_type").focus();
			return false;
		}
	
		if(examination_pass == null || examination_pass == "0") {
			alert("Please Select Examination Pass");
			$("#quali").focus();
			return false;
		}
	
		if($("#quali option:selected").text()==other) {
	     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
	     		alert( "Please Enter Examination Other ");
				
				$("input#exam_other").focus();
				return false;
			   }
	      }
		
		if(degree == null || degree == "0") {
			alert("Please Select The Degree Acquired");
			$("#quali_degree").focus();
			return false;
		}
		  if($("#quali_degree option:selected").text()==other) {
		      	 if(degree_other == null || degree_other.trim()==""){ 	       
		      		alert( "Please Enter Degree Other ");
		 			$("input#quali_deg_other").focus();
		 			return false;
		 		   }
		       }
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("#specialization").focus();
			return false;
		}
		
	

	
	
      
    

			if($("#specialization option:selected").text()=="Others") {
			                if(spec_other == null || spec_other.trim()==""){          
			        
			                          alert( "Please Enter Specialization Other ");
			                          $("input#quali_spec_other").focus();
			                          return false;
			                     }
			        }

      
      
	if(passing_year.trim()=="") {
		alert("Please Enter Year of Passing");
		$("input#yrOfPass").focus();
		return false;
	}
	if(passing_year.trim() != "") {
		if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
			alert("Please Enter Valid Year of Passing");
			$("input#yrOfPass").focus();
			return false;
		}
	}
	if(div_class == "0") {
		alert("Please Select Div/Grade");
		$("#div_class").focus();
		return false;
	}
	if(div_class=="4") {
    	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
     
			alert( "Please Enter Div/Grade Other ");
			$("input#class_other").focus();
			return false;
		   }
     }
	if(subject.trim()=="") {
		alert("Please Select The Subject");
		$("select#sub_quali").focus();
		return false;
	}
	if(institute.trim()=="") {
		alert("Please Enter Institute & place");
		$("#institute_place").focus();
		return false;
	}
	$('#save_qualification').prop('disabled', true);

	$.post('qualification_action?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,
		q_id: q_id,
		degree: degree,
		specialization: specialization,
		com_id: com_id,exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,
		degree_other:degree_other,spec_other:spec_other,
		dateofBirthyear: dateofBirthyear
	}, function(data) {
		if(parseInt(data) > 0) {
			alert("Data Saved Successfully")
			openCity(event, 'language_details');
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[6].classList.add("active");
			$('#save_qualification').prop('disabled', false);

		} else alert(data)
		$('#quali').val('0');		
		$('#yrOfPass').val('');
		$('#quali_course_name').val('0');
		$('#specialization').val('0');
		$('#div_class').val('0');
		$('#institute_place').val('');
		$('#qualification_ch_id').val('0');
		$("input[type=checkbox][name='multisub']").prop("checked", false);
		$('#quali').prop('disabled', false);
		$('#stream12').prop('disabled', false);
		$('#quali_type').prop('disabled', false);
		$("#sub_quali option:first").text('Select Subjects');
		$("#quali_sub_list").empty();
		var typethisT = document.getElementById('quali_type');
		fn_qualification_typeChange(typethisT,'quali','quali_degree','specialization');
		var qualithisT = document.getElementById('quali');
		fn_ExaminationTypeChange(qualithisT,'quali_degree','specialization');
	
		fn_other_class();
		getQualifications();
		$('#save_qualification').prop('disabled', false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
		$('#save_qualification').prop('disabled', false);
	});
}

function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text.toUpperCase() == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
  		$("#exam_other").val("");
  	}
  }


function fn_other_class() {
	var text = $("#div_class option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
	
		$("#other_div_class").show();
		
	}
	else
	{
		$("#other_div_class").hide();
		$("#class_other").val("");
		
	}
}






















function save_address_details() {
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
	if($("select#pre_domicile_Country").val() == "0") {
		alert("Please Select Present Domicile Country");
		$("select#pre_domicile_Country").focus();
		return false;
	}
	var text5 = $("#pre_domicile_Country option:selected").text();
	if(text5.toUpperCase() == "OTHERS" && $("input#pre_domicile_Country_other").val().trim() == ""){
		alert("Please Enter Present Domicile Country Other");
		$("input#pre_domicile_Country_other").focus();
		return false;
	}
	if($("select#pre_domicile_state").val() == "0") {
		alert("Please Select Present Domicile State");
		$("select#pre_domicile_state").focus();
		return false;
	}
	var text6 = $("#pre_domicile_state option:selected").text();
	if(text6.toUpperCase() == "OTHERS" && $("input#pre_domicile_state_other").val().trim() == ""){
		alert("Please Enter Present Domicile State Other");
		$("input#pre_domicile_state_other").focus();
		return false;
	}
	if($("select#pre_domicile_district").val() == "0") {
		alert("Please Select Present Domicile District");
		$("select#pre_domicile_district").focus();
		return false;
	}
	var text7 = $("#pre_domicile_district option:selected").text();
	if(text7.toUpperCase() == "OTHERS" && $("input#pre_domicile_district_other").val().trim() == ""){
		alert("Please Enter Present Domicile District Other");
		$("input#pre_domicile_district_other").focus();
		return false;
	}
	if($("select#pre_domicile_tehsil").val() == "0") {
		alert("Please Select Present Domicile Tehsil");
		$("select#pre_domicile_tehsil").focus();
		return false;
	}
	var text8 = $("#pre_domicile_tehsil option:selected").text();
	if(text8.toUpperCase() == "OTHERS" && $("input#pre_domicile_tehsil_other").val().trim() == ""){
		alert("Please Enter Present Domicile Tehsil Other");
		$("input#pre_domicile_tehsil_other").focus();
		return false;
	}
	if($("select#per_home_addr_Country").val() == "0") {
		alert("Please Select Permanent Home Address Country");
		$("select#per_home_addr_Country").focus();
		return false;
	}
	var text9 = $("#per_home_addr_Country option:selected").text();
	if(text9.toUpperCase() == "OTHERS" && $("input#per_home_addr_Country_other").val().trim() == ""){
		alert("Please Enter Permanent Home Address Country Other");
		$("input#per_home_addr_Country_other").focus();
		return false;
	}
	if($("select#per_home_addr_state").val() == "0") {
		alert("Please Select Permanent Home Address state");
		$("select#per_home_addr_state").focus();
		return false;
	}
	var text10 = $("#per_home_addr_state option:selected").text();
	if(text10.toUpperCase() == "OTHERS" && $("input#per_home_addr_state_other").val().trim() == ""){
		alert("Please Enter Permanent Home Address State Other");
		$("input#per_home_addr_state_other").focus();
		return false;
	}
	if($("select#per_home_addr_district").val() == "0") {
		alert("Please Select Permanent Home Address District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	var text11 = $("#per_home_addr_district option:selected").text();
	if(text11.toUpperCase() == "OTHERS" && $("input#per_home_addr_district_other").val().trim() == ""){
		alert("Please Enter Permanent Home Address District Other");
		$("input#per_home_addr_district_other").focus();
		return false;
	}
	if($("select#per_home_addr_tehsil").val() == "0") {
		alert("Please Select Permanent Home Address Tehsil");
		$("select#per_home_addr_tehsil").focus();
		return false;
	}
	var text12 = $("#per_home_addr_tehsil option:selected").text();
	if(text12.toUpperCase() == "OTHERS" && $("input#per_home_addr_tehsil_other").val().trim() == ""){
		alert("Please Enter Permanent Home Address Tehsil Other");
		$("input#per_home_addr_tehsil_other").focus();
		return false;
	}
	if($("input#per_home_addr_village").val().trim() == "") {
		alert("Please Enter Permanent Home Address Village/Town/City");
		$("#per_home_addr_village").focus();
		return false;
	}
	if($("input#per_home_addr_pin").val().trim() == "" || $("input#per_home_addr_pin").val().length < 6) {
		alert("Please Enter Valid Permanent Home Address Pin");
		$("input#per_home_addr_pin").focus();
		return false;
	}
	if($("input#per_home_addr_rail").val().trim() == "") {
		alert("Please Enter Permanent Home Address Nearest Railway Station");
		$("input#per_home_addr_rail").focus();
		return false;
	}
	var ph = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
	if(ph == undefined) {
		alert("Please Select Is Permanent Home Address Rural /Urban/Semi Urban");
		return false;
	}

	
	var a = $('input:radio[name=permanent_border_area]:checked').val();
	if(a == undefined) {
		alert("Please Select Permanent Home Address Border Area");
		return false;
	}
	if($("select#pers_addr_Country").val() == "0") {
		alert("Please Select Present Address Country");
		$("select#pers_addr_Country").focus();
		return false;
	}
	
	var textp9 = $("#pers_addr_Country option:selected").text();
	if(textp9.toUpperCase() == "OTHERS" && $("input#pers_addr_Country_other").val().trim() == ""){
		alert("Please Enter Present Address Country Other");
		$("input#pers_addr_Country_other").focus();
		return false;
	}
	
	if($("select#pers_addr_state").val() == "0") {
		alert("Please Select Present Address State");
		$("select#pers_addr_state").focus();
		return false;
	}
	var textp10 = $("#pers_addr_state option:selected").text();
	if(textp10.toUpperCase() == "OTHERS" && $("input#pers_addr_state_other").val().trim() == ""){
		alert("Please Enter Present Address State Other");
		$("input#pers_addr_state_other").focus();
		return false;
	}
	if($("select#pers_addr_district").val() == "0") {
		alert("Please Select Present Address District");
		$("select#pers_addr_district").focus();
		return false;
	}
	var textp11 = $("#pers_addr_district option:selected").text();
	if(textp11.toUpperCase() == "OTHERS" && $("input#pers_addr_district_other").val().trim() == ""){
		alert("Please Enter Present Address District Other");
		$("input#pers_addr_district_other").focus();
		return false;
	}
	
	if($("select#pers_addr_tehsil").val() == "0") {
		alert("Please Select Present Address Tehsil");
		$("select#pers_addr_tehsil").focus();
		return false;
	}
	
	var textp12 = $("#pers_addr_tehsil option:selected").text();
	if(textp12.toUpperCase() == "OTHERS" && $("input#pers_addr_tehsil_other").val().trim() == ""){
		alert("Please Enter Present Address Tehsil Other");
		$("input#pers_addr_tehsil_other").focus();
		return false;
	}
	
	if($("input#pers_addr_village").val().trim() == "") {
		alert("Please Enter Present Address Village/Town/City");
		$("#pers_addr_village").focus();
		return false;
	}
	if($("input#pers_addr_pin").val().trim() == "" || $("input#pers_addr_pin").val().length < 6) {
		alert("Please Enter Present Address valid Pin");
		$("input#pers_addr_pin").focus();
		return false;
	}
	if($("input#pers_addr_rail").val().trim() == "") {
		alert("Please Enter Present Address Nearest Railway Station");
		$("input#pers_addr_rail").focus();
		return false;
	}
	var prh = $('input:radio[name=pers_addr_rural_urban]:checked').val();
	if(prh == undefined) {
		alert("Please select Present Address Is  Rural /Urban/Semi Urban");
		return false;
	}
	
	if($("#check_spouse_address").is(':checked')) {
		if($("select#spouse_addr_Country").val() == "0") {
			alert("Please Select SF ACCN Country");
			$("select#spouse_addr_Country").focus();
			return false;
		}
var sp12 = $("#spouse_addr_Country option:selected").text();
		
		if(sp12.toUpperCase() == "OTHERS" && $("input#spouse_addr_country_other").val().trim() == ""){
			alert("Please Enter SF ACCN Country Other");
			$("input#spouse_addr_country_other").focus();
			return false;
		}
		
		if($("select#spouse_addr_state").val() == "0") {
			alert("Please Select SF ACCN State");
			$("select#spouse_addr_state").focus();
			return false;
		}
       var sp13 = $("#spouse_addr_state option:selected").text();
		
		if(sp13.toUpperCase() == "OTHERS" && $("input#spouse_addr_state_other").val().trim() == ""){
			alert("Please Enter SF ACCN State Other");
			$("input#spouse_addr_state_other").focus();
			return false;
		}
		if($("select#spouse_addr_district").val() == "0") {
			alert("Please Select SF ACCN District");
			$("select#spouse_addr_district").focus();
			return false;
		}
     var sp14 = $("#spouse_addr_district option:selected").text();
		
		if(sp14.toUpperCase() == "OTHERS" && $("input#spouse_addr_district_other").val().trim() == ""){
			alert("Please Enter SF ACCN District Other");
			$("input#spouse_addr_district_other").focus();
			return false;
		}
		if($("select#spouse_addr_tehsil").val() == "0") {
			alert("Please Select SF ACCN Tehsil");
			$("select#spouse_addr_tehsil").focus();
			return false;
		}
     var sp15 = $("#spouse_addr_tehsil option:selected").text();
		
		if(sp15.toUpperCase() == "OTHERS" && $("input#spouse_addr_tehsil_other").val().trim() == ""){
			alert("Please Enter SF ACCN Tehsil Other");
			$("input#spouse_addr_tehsil").focus();
			return false;
		}
		if($("input#spouse_addr_village").val().trim() == "") {
			alert("Please Enter SF ACCN Village/Town/City");
			$("#spouse_addr_village").focus();
			return false;
		}
		if($("input#spouse_addr_pin").val().trim() == "" || $("input#spouse_addr_pin").val().length < 6) {
			alert("Please Enter SF ACCN valid Pin");
			$("input#spouse_addr_pin").focus();
			return false;
		}
		if($("input#spouse_addr_rail").val().trim() == "") {
			alert("Please Enter SF ACCN Nearest Railway Station");
			$("input#spouse_addr_rail").focus();
			return false;
		}
		var spouserus = $('input:radio[name=spouse_addr_rural_urban]:checked').val();
		if(spouserus == undefined) {
			alert("Please select SF ACCN Address Is  Rural /Urban/Semi Urban");
			return false;
		}
		
		if ($("input#Stn_HQ_sus_no").val().trim()=="") {
			alert("Please Enter SF ACCN Address Stn HQ SUS No");
			$("input#Stn_HQ_sus_no").focus();
			return false;
		}
	}
	if($("input#nok_name").val().trim() == "") {
		alert("Please Enter NOK Name");
		$("input#nok_name").focus();
		return false;
	}
	if($("select#nok_relation").val() == "0") {
		alert("Please Select Nok Relation");
		$("select#nok_relation").focus();
		return false;
	}
	
	  var nok_relation_otherBool = $("#nok_relation option:selected").text();
		
		if(nok_relation_otherBool.toUpperCase() == "OTHERS" && $("input#nok_relation_other").val().trim() == ""){
			alert("Please Enter Relation Other");
			$("input#nok_relation_other").focus();
			return false;
		}
		
	if($("select#nok_country").val() == "0") {
		alert("Please Select  Nok Country");
		$("select#nok_country").focus();
		return false;
	}
	var text11n = $("#nok_country option:selected").text();
	
	if(text11n.toUpperCase() == "OTHERS" && $("input#ctry_other").val().trim() == ""){
		alert("Please Enter NOK  Country Other");
		$("input#ctry_other").focus();
		return false;
	}
	if ($("select#nok_state").val() == "0") {
		alert("Please Select  Nok State");
		$("select#nok_state").focus();
		return false;
	}
  var text21n = $("#nok_state option:selected").text();
	
	if(text21n== "OTHERS" && $("input#st_other").val().trim() == ""){
		alert("Please Enter NOK State Other");
		$("input#st_other").focus();
		return false;
	}
	if ($("select#nok_district").val() == "0") {
		alert("Please Select Nok District");
		$("select#nok_district").focus();
		return false;
	}
	 var text31n = $("#nok_district option:selected").text();
		
		if(text31n.toUpperCase() == "OTHERS" && $("input#dist_other").val().trim() == ""){
			alert("Please Enter NOK District Other");
			$("input#dist_other").focus();
			return false;
		}
	if ($("select#nok_tehsil").val() == "0" ) {
		alert("Please Select NOK Tehsil");
		$("select#nok_tehsil").focus();
		return false;
	}
	var text51n = $("#nok_tehsil option:selected").text();
	
	if(text51n.toUpperCase() == "OTHERS" && $("input#nok_tehsil_other").val().trim() == ""){
		alert("Please Enter NOK Tehsil Other");
		$("input#nok_tehsil_other").focus();
		return false;
	}
	if($("input#nok_village").val().trim() == "") {
		alert("Please Enter  Nok Village/Town/City");
		$("#nok_village").focus();
		return false;
	}
	if($("input#nok_pin").val().trim() == "" || $("input#nok_pin").val().length < 6) {
		alert("Please Enter valid  Nok Pin");
		$("input#nok_pin").focus();
		return false;
	}
	if($("input#nok_near_railway_station").val().trim() == "") {
		alert("Please Enter  Nok Nearest Railway Station");
		$("input#nok_near_railway_station").focus();
		return false;
	}
	var n = $('input:radio[name=nok_rural_urban]:checked').val();
	if(n == undefined) {
		alert("Please select NOK Is  Rural /Urban/Semi Urban");
		return false;
	}
	if($("input#nok_mobile_no").val().trim() == "" || $("input#nok_mobile_no").val().length < 10) {
		alert("Please Enter Valid  NOK's Mobile No");
		$("input#nok_mobile_no").focus();
		return false;
	}

	var formvalues = $("#addr_details_form").serialize();
	var p_id = $("#census_id").val();
	var addr_ch_id = $("#addr_ch_id").val();
	
	var nok_ch_id = $("#nok_ch_id").val();
	var comm_id = $("#comm_id").val();
	var marital_status = $('#marital_status').val();
	$('#save_add').prop('disabled', true);
	formvalues += "&p_id=" + p_id + "&addr_ch_id=" + addr_ch_id + "&nok_ch_id=" + nok_ch_id + "&comm_id=" + comm_id + "&marital_status=" + marital_status + "&nok_relation_otherBool=" + nok_relation_otherBool;
	
	formvalues += "&org_co=" + text1 + "&org_so=" + text2 + "&org_do=" + text3 + "&org_to=" + text4;
	formvalues += "&pd_co=" + text5 + "&pd_so=" + text6 + "&pd_do=" + text7 + "&pd_to=" + text8;
	formvalues += "&per_co=" + text9 + "&per_so=" + text10 + "&per_do=" + text11 + "&per_to=" + text12;
	formvalues += "&pre_co=" + textp9 + "&pre_so=" + textp10 + "&pre_do=" + textp11 + "&pre_to=" + textp12;
	formvalues += "&sp_co=" + sp12 + "&sp_so=" + sp13 + "&sp_do=" + sp14 + "&sp_to=" + sp14;
	formvalues += "&nok_co=" + text11n + "&nok_so=" + text21n + "&nok_do=" + text31n + "&nok_to=" + text51n;
	
	$.post('address_details_action?' + key + "=" + value, formvalues, function(data) {
		if(data.error == null) {
			$('#addr_ch_id').val(data.addr_ch_id);
			$("#nok_ch_id").val(data.nok_ch_id);
			alert("Data Save/Updated Successfully");
			openCity(event, 'family_details');
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[2].classList.add("active");
			$('#save_add').prop('disabled', false);
			
		} else {
			alert(data.error);
			$('#save_add').prop('disabled', false);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		$('#save_add').prop('disabled', false);
	});
}

function get_address_details() {
	var p_id1 = $("#census_id").val();
	
	$.post('address_details_GetData_census?' + key + "=" + value, {
		p_id1: p_id1
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$.ajaxSetup({
				async: false
			});
			$("#pre_domicile_Country").val(j[0].pre_country);
			fn_pre_domicile_Country_ready();
			$("#pre_domicile_state").val(j[0].pre_state);
			fn_pre_domicile_state();
			$("#pre_domicile_district").val(j[0].pre_district);
			fn_pre_domicile_district();
			$("#pre_domicile_tehsil").val(j[0].pre_tesil);
			fn_pre_domicile_tehsil();
			$("#per_home_addr_Country").val(j[0].permanent_country);
			fn_per_home_addr_Country_ready();
			$("#per_home_addr_state").val(j[0].permanent_state);
			fn_per_home_addr_state();
			$("#per_home_addr_district").val(j[0].permanent_district);
			fn_per_home_addr_district();
			$("#per_home_addr_tehsil").val(j[0].permanent_tehsil);
			fn_per_home_addr_tehsil();
			var br = j[0].permanent_border_area;
			if(br == "yes") {
				$("#border_area").prop("checked", true);
			}
			if(br == "no") {
				$("#border_area1").prop("checked", true);
			}
			$("#per_home_addr_village").val(j[0].permanent_village);
			$("#pers_addr_Country").val(j[0].present_country);
			fn_pers_addr_Country_ready();
			$("#pers_addr_state").val(j[0].present_state);
			fn_pers_addr_state();
			$("#pers_addr_district").val(j[0].present_district);
			fn_pers_addr_district();
			$("#pers_addr_tehsil").val(j[0].present_tehsil);
			fn_pers_addr_tehsil();
			$("#pers_addr_village").val(j[0].present_village);
			$("#per_home_addr_pin").val(j[0].permanent_pin_code);
			$("#per_home_addr_rail").val(j[0].permanent_near_railway_station);
			$("#pers_addr_pin").val(j[0].present_pin_code);
			$("#pers_addr_rail").val(j[0].present_near_railway_station);
			
			
			
			$("#pre_domicile_Country_other").val(j[0].pre_country_other);
// 			$("#pre_domicile_state_other").val(j[0].pre_domicile_state_other);
			$("#pre_domicile_district_other").val(j[0].pre_domicile_district_other);
			$("#pre_domicile_tehsil_other").val(j[0].pre_domicile_tesil_other);
			$("#per_home_addr_Country_other").val(j[0].per_home_addr_country_other);
			$("#per_home_addr_state_other").val(j[0].per_home_addr_state_other);
			$("#per_home_addr_district_other").val(j[0].per_home_addr_district_other);
			$("#per_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
			$("#pers_addr_Country_other").val(j[0].pers_addr_country_other);
			$("#pers_addr_state_other").val(j[0].pers_addr_state_other);
			$("#pers_addr_district_other").val(j[0].pers_addr_district_other);
			$("#pers_addr_tehsil_other").val(j[0].pers_addr_tehsil_other);
			 $("input[name=pers_addr_rural_urban][value=" + j[0].present_rural_urban_semi + "]").prop('checked', true);
			 $("input[name=per_home_addr_rural_urban][value=" + j[0].permanent_rural_urban_semi + "]").prop('checked', true);
			
			if($('#marital_status').val() == '8') {
				if(j[0].spouse_country != null && j[0].spouse_country != 0) {
					$("#spouse_addr_Country").val(j[0].spouse_country);
					fn_spouse_addr_Country_ready();
					$("#spouse_addr_state").val(j[0].spouse_state);
					fn_spouse_addr_state();
					$("#spouse_addr_district").val(j[0].spouse_district);
					fn_spouse_addr_district();
					$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
					//26-01-1994
// 					fn_spouse_addr_tehsil();
					$("#spouse_addr_pin").val(j[0].spouse_pin_code);
					$("#spouse_addr_rail").val(j[0].spouse_near_railway_station);
					$("#spouse_addr_village").val(j[0].spouse_village);
// 					$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
					$('#check_spouse_address').prop('checked', true);
					spouse_addressFn();
					$("#spouse_addr_country_other").val(j[0].spouse_addr_country_other);
					$("#spouse_addr_state_other").val(j[0].spouse_addr_state_other);
					$("#spouse_addr_district_other").val(j[0].spouse_addr_district_other);
					$("#spouse_addr_tehsil_other").val(j[0].spouse_addr_tehsil_other);
					 $("input[name=spouse_addr_rural_urban][value=" + j[0].spouse_rural_urban_semi + "]").prop('checked', true);
					 $("#Stn_HQ_sus_no").val(j[0].stn_hq_sus_no);
						var sus_no = j[0].stn_hq_sus_no;			      	
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
			}
			$("#addr_ch_id").val(j[0].id);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	$.post('Nok_details_GetData?' + key + "=" + value, {
		p_id1: p_id1
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			
			$("#nok_village").val(j[0].nok_village);
			$("#nok_country").val(j[0].nok_country);
			fn_nok_Country_ready();
			$("#nok_state").val(j[0].nok_state);
			fn_nok_state();
			$("#nok_district").val(j[0].nok_district);
			fn_nok_district();
			
			$("#nok_tehsil").val(j[0].nok_tehsil);
			$("#nok_pin").val(j[0].nok_pin);
			$("#nok_near_railway_station").val(j[0].nok_near_railway_station);
			$("#nok_name").val(j[0].nok_name);
			$("#nok_relation").val(j[0].nok_relation);
			$("#nok_relation_other").val(j[0].relation_other);
			$("#nok_mobile_no").val(j[0].nok_mobile_no);
			$("#nok_ch_id").val(j[0].id);
			var nok = j[0].nok_rural_urban_semi;
			if(nok == "rural") {
				$("#nok_rural").prop("checked", true);
			}
			if(nok == "urban") {
				$("#nok_urban").prop("checked", true);
			}
			if(nok == "semi_urban") {
				$("#nok_semi_urban").prop("checked", true);
			}
			
			 var text6 = $("#nok_country option:selected").text();
				if(text6.toUpperCase() == "OTHERS"){
					$("#ctry_other").val(j[0].ctry_other);
					$("#nok_other_id").show();
				}
				else{
					$("#nok_other_id").hide();
				}
				
				var text7 = $("#nok_state option:selected").text();
				if(text7.toUpperCase() == "OTHERS"){
					$('#st_other').val(j[0].st_other);
					$("#nok_other_st").show();
				}
				else{
					$("#nok_other_st").hide();
				}
				var text8 = $("#nok_district option:selected").text();
				if(text8.toUpperCase() == "OTHERS"){
					$("#dist_other").val(j[0].dist_other);
					$("#nok_other_dist").show();
				}
				else{
					$("#nok_other_dist").hide();
				}
				var text9 = $("#nok_tehsil option:selected").text();
				if(text9.toUpperCase() == "OTHERS"){
					$("#nok_tehsil_other").val(j[0].tehsil_other);
					$("#nok_other_te").show();
				}
				else{
					$("#nok_other_te").hide();
				}
				
				fn_otherShowHide(document.getElementById('nok_relation'),'nok_relation_otherDiv','nok_relation_other')
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	var marital_status = $('#marital_status').val()
	if(marital_status != 8) {
		$("#nok_relation option[value='24']").hide();
		$("#spouse_addressMaindiv").hide();
	} else {
		$("#nok_relation option[value='24']").show();
		$("#spouse_addressMaindiv").show();
	}
}
/* function divorce_radio(km){
 var a = $('input:radio[name=divorce'+km+']:checked').val();
 if(a == "yes"){	
 $("#divorce_table").show();
 $("th#th_divorce").show(); 
 $("td#td_divorce").show();  
 }
 else{	
 $("#divorce_table").hide();
 $("th#th_divorce").hide(); 
 $("td#td_divorce").hide(); 
 }
 } */
/* function Spouse_radio(){
 var a = $('input:radio[name=spouse_radio]:checked').val();
 if(a.toLowerCase() == "yes"){	
 $("#married_table").show();
 }
 else{	
 $("#married_table").hide();
 }
 } */
function Submit_Approval() {
	 //document.getElementById("bt_sub").disabled = true;
	 $("#bt_sub").prop('disabled',true);
	var p_id = $("#census_id").val();
	$.post('Submit_Approval_Data?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		
		// $("#bt_sub").prop('disabled',true);
		if(j == 1) {
			
		 alert("Submit Successfully");
		 //$("#bt_sub").prop('disabled',true);
			var href = $('#censussearshUrllink').attr('href');
			window.location.href = href;

		} else {
			alert(j);
			
			 $("#bt_sub").prop('disabled',false);
		}
		// $("#bt_sub").prop('disabled', false);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch");
		 $("#bt_sub").prop('disabled',false);
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

 
function copy_address() {
	
	if($("#check_address").prop('checked') == true) {
		
		$("#pers_addr_village").val($("#per_home_addr_village").val());
		$('#pers_addr_tehsil').empty()
		$("#per_home_addr_tehsil option").clone().appendTo("#pers_addr_tehsil");
		$('#pers_addr_district').empty()
		$("#per_home_addr_district option").clone().appendTo("#pers_addr_district");
		$('#pers_addr_state').empty()
		$("#per_home_addr_state  option").clone().appendTo("#pers_addr_state");
		$("#pers_addr_pin").val($("#per_home_addr_pin").val());
		$("#pers_addr_rail").val($("#per_home_addr_rail").val());
		$("#pers_addr_Country").val($("#per_home_addr_Country").val());	
		$("#pers_addr_state").val($("#per_home_addr_state").val());	
		$("#pers_addr_district").val($("#per_home_addr_district").val());
		$("#pers_addr_tehsil").val($("#per_home_addr_tehsil").val());
		 $("input[name=pers_addr_rural_urban]").prop('checked', false);
		 var value= $('input:radio[name=per_home_addr_rural_urban]:checked').val()
		            $("input[name=pers_addr_rural_urban][value=" + value + "]").prop('checked', true);
		               
		
		
		
		 var con_other = $("#per_home_addr_Country option:selected").text();
		 var state_other = $("#pers_addr_state option:selected").text();
		 var dis_other = $("#pers_addr_district option:selected").text();
		 var teh_other = $("#per_home_addr_tehsil option:selected").text();
		
		if(con_other==other){
            $("#pers_addr_Country_other").val($("#per_home_addr_Country_other").val());
            $("#pers_addr_Country_other_id").show();
		}
		else{
			   $("#pers_addr_Country_other").val('');
	            $("#pers_addr_Country_other_id").hide();
		}
		if(state_other==other){
			$("#pers_addr_state_other").val($("#per_home_addr_state_other").val());
			 $("#pers_addr_state_other_id").show();
		}
		else{
			   $("#pers_addr_state_other").val('');
	            $("#pers_addr_state_other_id").hide();
		}
		if(dis_other==other){
			$("#pers_addr_district_other").val($("#per_home_addr_district_other").val());
			 $("#pers_addr_district_other_id").show();
			
		}
		else{
			   $("#pers_addr_district_other").val('');
	            $("#pers_addr_district_other_id").hide();
		}
		if(teh_other==other){
			$("#pers_addr_tehsil_other").val($("#per_home_addr_tehsil_other").val());
			 $("#pers_addr_tehsil_other_id").show();
		}
		else{
			   $("#pers_addr_tehsil_other").val('');
	            $("#pers_addr_tehsil_other_id").hide();
		}
	
	} else {
		$("#pers_addr_village").val("");
		$("#pers_addr_tehsil").val("0");
		$("#pers_addr_district").val("0");
		$("#pers_addr_state").val("0");
		$("#pers_addr_pin").val("");
		$("#pers_addr_rail").val("");
		$("#pers_addr_Country").val("0");
		 $("#pers_addr_tehsil_other").val('');
		 $("#pers_addr_district_other").val('');
		  $("#pers_addr_state_other").val('');
		  $("#pers_addr_Country_other").val('');
		//26-01-1994
		  $('input:radio[name="pers_addr_rural_urban"]').prop('checked', false);
		  
		
	}
}

function copy_address1() {
	if($("#check_address1").prop('checked') == true) {		
		$('#nok_tehsil').empty()
		$("#per_home_addr_tehsil  option").clone().appendTo("#nok_tehsil");
		$('#nok_district').empty()
		$("#per_home_addr_district option").clone().appendTo("#nok_district");
		$('#nok_state').empty()
		$("#per_home_addr_state  option").clone().appendTo("#nok_state");
		$("#nok_village").val($("#per_home_addr_village").val());
		$("#nok_pin").val($("#per_home_addr_pin").val());
		$("#nok_near_railway_station").val($("#per_home_addr_rail").val());
		$("#nok_country").val($("#per_home_addr_Country").val());		
		$("#nok_state").val($("#per_home_addr_state").val());
		$("#nok_district").val($("#per_home_addr_district").val());
		$("#nok_tehsil").val($("#per_home_addr_tehsil").val());
		 $("input[name=nok_rural_urban]").prop('checked', false);
		var value= $('input:radio[name=per_home_addr_rural_urban]:checked').val()
		 $("input[name=nok_rural_urban][value=" + value + "]").prop('checked', true);
	               
		 var con_other = $("#per_home_addr_Country option:selected").text();
		 var state_other = $("#pers_addr_state option:selected").text();
		 var dis_other = $("#pers_addr_district option:selected").text();
		 var teh_other = $("#per_home_addr_tehsil option:selected").text();
		
		if(con_other==other){
            $("#ctry_other").val($("#per_home_addr_Country_other").val());
            $("#nok_other_id").show();
		}
		else{
			   $("#ctry_other").val('');
	            $("#nok_other_id").hide();
		}
		if(state_other==other){
			$("#st_other").val($("#per_home_addr_state_other").val());
			 $("#nok_other_st").show();
		}
		else{
			   $("#st_other").val('');
	            $("#nok_other_st").hide();
		}
		if(dis_other==other){
			$("#dist_other").val($("#per_home_addr_district_other").val());
			 $("#nok_other_dist").show();
			
		}
		else{
			   $("#dist_other").val('');
	            $("#nok_other_dist").hide();
		}
		if(teh_other==other){
			$("#nok_tehsil_other").val($("#per_home_addr_tehsil_other").val());
			 $("#nok_other_te").show();
		}
		else{
			   $("#nok_tehsil_other").val('');
	            $("#nok_other_te").hide();
		}
	} else {
		
		$("#nok_village").val("");
		$("#nok_tehsil").val("0");
		$("#nok_district").val("0");
		$("#nok_state").val("0");
		$("#nok_pin").val("");
		$("#nok_near_railway_station").val("");
		$("#nok_country").val("0");
		 $("#nok_tehsil_other").val('');
		 $("#dist_other").val('');
		 $("#st_other").val('');
		  $("#ctry_other").val('');
		  //26-01-1994
		  $('input:radio[name="nok_rural_urban"]').prop('checked', false);
	}
}

function fn_per_home_addr_state() {
	
	 var text = $("#per_home_addr_state option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#per_home_addr_state_other_id").show();
		}
		else{
			$("#per_home_addr_state_other_id").hide();
			$("#per_home_addr_state_other").val("");
		}
	
	var state_id = $('select#per_home_addr_state').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#per_home_addr_district").html(options);
		fn_per_home_addr_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pers_addr_state() {
	
	 var text = $("#pers_addr_state option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pers_addr_state_other_id").show();
		}
		else{
			$("#pers_addr_state_other_id").hide();
			$("#pers_addr_state_other").val("");
		}
	
	var state_id = $('select#pers_addr_state').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pers_addr_district").html(options);
		fn_pers_addr_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_spouse_addr_state() {
	var text = $("#spouse_addr_state option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#spouse_addr_state_hid").show();
		}
		else{
			$("#spouse_addr_state_hid").hide();
			$("#spouse_addr_state_other").val("");
		}
		var state_id = $('select#spouse_addr_state').val();
		$.post("getDistrictListFormState1?" + key + "=" + value, {
			state_id: state_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#spouse_addr_district").html(options);
			fn_spouse_addr_district();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}

function fn_nok_state() {
	
	var text = $("#nok_state option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
		$("#nok_other_st").show();
	}
	else{
		$("#nok_other_st").hide();
		$("#st_other").val("");
	}
	var state_id = $('select#nok_state').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#nok_district").html(options);
		fn_nok_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

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

function fn_pre_domicile_state() {
	 var text = $("#pre_domicile_state option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pre_domicile_state_other_id").show();
		}
		else{
			$("#pre_domicile_state_other_id").hide();
			$("#pre_domicile_state_other").val("");
		}
	
	var state_id = $('select#pre_domicile_state').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pre_domicile_district").html(options);
		fn_pre_domicile_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}


function fn_per_home_addr_Country() {
	
	 var text = $("#per_home_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#per_home_addr_Country_other_id").show();
		}
		else{
			$("#per_home_addr_Country_other_id").hide();
			$("#per_home_addr_Country_other").val("");
		}
	
	var contry_id = $('select#per_home_addr_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#per_home_addr_state").html(options);
		fn_per_home_addr_state();
		fn_per_home_addr_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_per_home_addr_Country_ready() {
	
	 var text = $("#per_home_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#per_home_addr_Country_other_id").show();
		}
		else{
			$("#per_home_addr_Country_other_id").hide();
			$("#per_home_addr_Country_other").val("");
		}
	
	var contry_id = $('select#per_home_addr_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#per_home_addr_state").html(options);
	
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pers_addr_Country() {
	 var text = $("#pers_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pers_addr_Country_other_id").show();
		}
		else{
			$("#pers_addr_Country_other_id").hide();
			$("#pers_addr_Country_other").val("");
		}
	
	var contry_id = $('select#pers_addr_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pers_addr_state").html(options);
		fn_pers_addr_state();
// 		fn_pers_addr_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pers_addr_Country_ready() {
	 var text = $("#pers_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pers_addr_Country_other_id").show();
		}
		else{
			$("#pers_addr_Country_other_id").hide();
			$("#pers_addr_Country_other").val("");
		}
	
	var contry_id = $('select#pers_addr_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pers_addr_state").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_spouse_addr_Country_ready() {
	var text = $("#spouse_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#spouse_addr_Country_hid").show();
		}
		else{
			$("#spouse_addr_Country_hid").hide();
			$("#spouse_addr_country_other").val("");
		}
		
		var contry_id = $('select#spouse_addr_Country').val();
		$.post("getStateListFormcon1?" + key + "=" + value, {
			contry_id: contry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#spouse_addr_state").html(options);
			
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
function fn_spouse_addr_Country() {
	var text = $("#spouse_addr_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#spouse_addr_Country_hid").show();
		}
		else{
			$("#spouse_addr_Country_hid").hide();
			$("#spouse_addr_country_other").val("");
		}
		
		var contry_id = $('select#spouse_addr_Country').val();
		$.post("getStateListFormcon1?" + key + "=" + value, {
			contry_id: contry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#spouse_addr_state").html(options);
			fn_spouse_addr_state();
// 			fn_spouse_addr_district();
// 			fn_spouse_addr_tehsil();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}

function fn_nok_Country_ready() {
	
	var text = $("#nok_country option:selected").text();

	if(text.toUpperCase() == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
		$("#ctry_other").val("");
	}	
	var contry_id = $('select#nok_country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#nok_state").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();

	if(text.toUpperCase() == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
		$("#ctry_other").val("");
	}	
	var contry_id = $('select#nok_country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#nok_state").html(options);
		fn_nok_state();
// 		fn_nok_district();
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


function fn_pre_domicile_Country_ready() {
	 var text = $("#pre_domicile_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pre_domicile_Country_other_id").show();
		}
		else{
			$("#pre_domicile_Country_other_id").hide();
			$("#pre_domicile_Country_other").val("");
		}

	
	var contry_id = $('select#pre_domicile_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pre_domicile_state").html(options);
		
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pre_domicile_Country() {
	 var text = $("#pre_domicile_Country option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pre_domicile_Country_other_id").show();
		}
		else{
			$("#pre_domicile_Country_other_id").hide();
			$("#pre_domicile_Country_other").val("");
		}

	
	var contry_id = $('select#pre_domicile_Country').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pre_domicile_state").html(options);
		fn_pre_domicile_state();
// 		fn_pre_domicile_district();
// 		fn_pre_domicile_tehsil();
	}).fail(function(xhr, textStatus, errorThrown) {});
}


function fn_per_home_addr_district() {
	
	 var text = $("#per_home_addr_district option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#per_home_addr_district_other_id").show();
		}
		else{
			$("#per_home_addr_district_other_id").hide();
			$("#per_home_addr_district_other").val("");
		}
	
	var Dist_id = $('select#per_home_addr_district').val();
	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
		Dist_id: Dist_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#per_home_addr_tehsil").html(options);
		fn_per_home_addr_tehsil();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_per_home_addr_tehsil() {
	 var text = $("#per_home_addr_tehsil option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#per_home_addr_tehsil_other_id").show();
		}
		else{
			$("#per_home_addr_tehsil_other_id").hide();
			$("#per_home_addr_tehsil_other").val("");
		}
	
}

function fn_pers_addr_district() {
	 var text = $("#pers_addr_district option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pers_addr_district_other_id").show();
		}
		else{
			$("#pers_addr_district_other_id").hide();
			$("#pers_addr_district_other").val("");
		}
	
	var Dist_id = $('select#pers_addr_district').val();
	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
		Dist_id: Dist_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pers_addr_tehsil").html(options);
		fn_pers_addr_tehsil();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pers_addr_tehsil() {
	 var text = $("#pers_addr_tehsil option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pers_addr_tehsil_other_id").show();
		}
		else{
			$("#pers_addr_tehsil_other_id").hide();
			$("#pers_addr_tehsil_other").val("");
		}
}

function fn_spouse_addr_district() {
	var text = $("#spouse_addr_district option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#spouse_addr_district_hid").show();
		}
		else{
			$("#spouse_addr_district_hid").hide();
			$("#spouse_addr_district_other").val("");
		}
		var Dist_id = $('select#spouse_addr_district').val();
		$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
			Dist_id: Dist_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#spouse_addr_tehsil").html(options);
			fn_spouse_addr_tehsil();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
	function fn_spouse_addr_tehsil() {
		var text = $("#spouse_addr_tehsil option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#spouse_addr_tehsil_hid").show();
			}
			else{
				$("#spouse_addr_tehsil_hid").hide();
				$("#spouse_addr_tehsil_other").val("");
			}
	}


function fn_nok_district() {
	
	
	var text = $("#nok_district option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#nok_other_dist").show();
	}
	else{
		$("#nok_other_dist").hide();
		$("#dist_other").val("");
	}
	var Dist_id = $('select#nok_district').val();
	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
		Dist_id: Dist_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#nok_tehsil").html(options);
		fn_nok_tehsil();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_nok_tehsil() {
	
	var text = $("#nok_tehsil option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#nok_other_te").show();
	}
	else{
		$("#nok_other_te").hide();
		$("#nok_tehsil_other").val('');
	}
}	

function fn_pre_domicile_district() {
	
	 var text = $("#pre_domicile_district option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#pre_domicile_district_other_id").show();
		}
		else{
			$("#pre_domicile_district_other_id").hide();
			$("#pre_domicile_district_other").val("");
		}
	
	
	var Dist_id = $('select#pre_domicile_district').val();
	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
		Dist_id: Dist_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#pre_domicile_tehsil").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pre_domicile_tehsil() {
	var text = $("#pre_domicile_tehsil option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		$("#pre_domicile_tehsil_other_id").show();
	}
	else{
		$("#pre_domicile_tehsil_other_id").hide();
		$("#pre_domicile_tehsil_other").val("");
	}
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
 function fn_hair_colour() {
	 var text = $("#hair_colour option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#hair_colour_other_hid").show();
		}
		else{
			$("#hair_colour_other_hid").hide();
			$("#hair_colour_other").val('');
		}
 	}
 function fn_eye_colour() {
	 var text = $("#eye_colour option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			$("#eye_colour_other_hid").show();
		}
		else{
			$("#eye_colour_other_hid").hide();
			$("#eye_colour_other").val('');
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
	$.post('getallergicData?' + key + "=" + value, {
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

function father_proffFn() {
	var a = $('input:radio[name=father_proff_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#father_inserviceDiv").show();
		$("#father_proffDiv").hide();
		$("#father_profession").val('0');
	
		
	} else {
		$("#father_service").val('0');
		$("#father_proffDiv").show();
		$("#father_inserviceDiv").hide();
	}
	ServiceOtherFn('father_otherDiv','father_personalDiv',document.getElementById('father_service'));

	fn_otherShowHide(document.getElementById('father_profession'),'father_proffotherDiv','father_proffother');
	
}

function mother_proffFn() {
	var a = $('input:radio[name=mother_proff_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#mother_inserviceDiv").show();
		$("#mother_proffDiv").hide();
		$("#mother_profession").val('0');
	} else {
		$("#mother_proffDiv").show();
		$("#mother_inserviceDiv").hide();
		$("#mother_service").val('0');
	}
	ServiceOtherFn('mother_otherDiv','mother_personalDiv',document.getElementById('mother_service'));
	fn_otherShowHide(document.getElementById('mother_profession'),'mother_otherproffDiv','mother_proffother');
}


function showCheckboxesSpouse() {
	
	$("#spouse_checkboxes").toggle();
	$("#spouse_searchSubject").val('');
	$('.spouse_subjectlist').each(function() {
		$(this).show()
	})
}

function spouse_quali_radioFn() {
	var a = $('input:radio[name=spouse_quali_radio]:checked').val();
	if(a.toLowerCase() == "yes") {
		$("#spouse_Qualifications").show();
	} else {
		$("#spouse_Qualifications").hide();
	}
}

function spouse_qualification_save_fn() {
	spouse_id = $("#marr_ch_id1").val();
	if(spouse_id == '0' || spouse_id == '') {
		alert("Please First Save The Spouse Details");
		return false;
	}
	var dateofBirthyear = $("#marriage_date_of_birth1").val().split('/')[0];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var type = $('#spouse_quali_type').val();
	var examination_pass = $('#spouse_quali').val();
	var exam_other_qua=$('#exam_otherSpouse').val();
  	var degree_other=$('#quali_deg_otherSpouse').val();
	var spec_other=$('#quali_spec_otherSpouse').val();
	var class_other_qua=$('#class_otherSpouse').val();
	var passing_year = $('#spouse_yrOfPass').val();
	var degree = $('#quali_degree_spouse').val();
	var specialization = $('#spouse_specialization').val();
	var div_class = $('#spouse_div_class').val();
	var institute = $('#spouse_institute_place').val();
	var qualification_ch_id = $('#spouse_qualification_ch_id').val();
	var q_id = $('#census_id').val();
	var com_id = $('#comm_id').val();
	var subjectvar = $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var subject = subjectvar.join(",");


		
		if(type == null || type == "0") {
			alert("Please Select Qualification Type");
			$("#spouse_quali_type").focus();
			return false;
		}
	
		if(examination_pass == null || examination_pass == "0") {
			alert("Please Select Examination Pass");
			$("#spouse_quali").focus();
			return false;
		}
	
		  if($("#spouse_quali option:selected").text()==other) {
		     	 if(exam_other_qua == null || exam_other_qua.trim()==""){ 	 
		     		alert( "Please Enter Examination Other ");
					
					$("input#exam_otherSpouse").focus();
					return false;
				   }
		      }
		  
		if(degree == null || degree == "0") {
			alert("Please Select The Degree Acquired");
			$("#quali_degree_spouse").focus();
			return false;
		}
		if($("#quali_degree_spouse option:selected").text()==other) {
	      	 if(degree_other == null || degree_other.trim()==""){ 	       
	      		alert( "Please Enter Degree Other ");
	 			$("input#quali_deg_otherSpouse").focus();
	 			return false;
	 		   }
	       }
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("#spouse_specialization").focus();
			return false;
		}
		
	

	
	
    
      
      if($("#spouse_specialization option:selected").text()==other) {
       	 if(spec_other == null || spec_other.trim()==""){ 	 
        
  			alert( "Please Enter Specialization Other ");
  			$("input#quali_spec_otherSpouse").focus();
  			return false;
  		   }
        }
      
	
	if(passing_year.trim()=="") {
		alert("Please Enter Year of Passing");
		$("input#spouse_yrOfPass").focus();
		return false;
	}
	if(passing_year.trim() != "") {
		if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
			alert("Please Enter Valid Year of Passing");
			$("input#spouse_yrOfPass").focus();
			return false;
		}
	}
	if(div_class == "0") {
		alert("Please Select The Div/Grade");
		$("#spouse_div_class").focus();
		return false;
	}
	if(div_class=="4") {
   	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
    
			alert( "Please Enter Div/Grade Other ");
			$("input#class_otherSpouse").focus();
			return false;
		   }
    }
	if(subject.trim()=="") {
		alert("Please Select The Subject");
		$("select#spouse_sub_quali").focus();
		return false;
	}
	if(institute.trim()=="") {
		alert("Please Enter The Institute & place");
		$("#spouse_institute_place").focus();
		return false;
	}
	$.post('spouse_qualification_action?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,
		q_id: q_id,		
		degree: degree,
		specialization: specialization,
		com_id: com_id,
		dateofBirthyear: dateofBirthyear,
		exam_other_qua:exam_other_qua,class_other_qua:class_other_qua,		
		degree_other:degree_other,spec_other:spec_other,
		spouse_id: spouse_id
	}, function(data) {
		if(parseInt(data) > 0) {
			alert("Data Saved Successfully")
			$('#spouse_qualification_ch_id').val(data);
		} else alert(data)
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getSpouseQualificationData() {
	var q_id = $('#census_id').val();
	var i = 0;
	$.post('getSpouseQualificationData?' + key + "=" + value, {
		q_id: q_id
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#spouse_yrOfPass').val(j[0].passing_year);
			$('#spouse_div_class').val(j[0].div_class);
			$('#spouse_institute_place').val(j[0].institute);
			$('#spouse_qualification_ch_id').val(j[0].id);
			$("input[type=checkbox][name='spouse_multisub']").prop("checked", false);
			var subjectslist = j[0].subject.split(',');
			for(k = 0; k < subjectslist.length; k++) {
				$("input[type=checkbox][name='spouse_multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
				$("#spouse_sub_quali option:first").text('Subjects(' + subjectslist.length + ')');
			}
			$('#spouse_quali_sub_list').empty()
			$("input[type='checkbox'][name='spouse_multisub']").each(function() {
				if(this.checked) {
					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
				}
			});
			$("#spouse_checkboxes").show();
			$('#spouse_quali_type').val(j[0].type);
			var typethisT = document.getElementById('spouse_quali_type');
			fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
			
			if(j[0].examination_pass != null) {
				$('#spouse_quali').val(j[0].examination_pass);
				var qualithisT = document.getElementById('spouse_quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
			}
			
			if(j[0].degree != null) {
				$('#quali_degree_spouse').val(j[0].degree);
				
			}
			if(j[0].specialization != null) {
			$('#spouse_specialization').val(j[0].specialization);
			 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
			}
			
			
			
			
		
			
			
			var examother="";
			var classother="";
			var deg_other="";
			var spec_other="";
			
			if(j[i].exam_other!=null)
	  			examother=j[i].exam_other;
	  	    if(j[i].class_other!=null)
	  	    	classother=j[i].class_other;
	  	    
	  	  if(j[i].degree_other!=null)
	  		deg_other=j[i].degree_other;
	  	   if(j[i].specialization_other!=null)
	  	    	spec_other=j[i].specialization_other;
	  	    
	  	
	 	  	$('#exam_otherSpouse').val(examother);
	 	
	 		 $('#class_otherSpouse').val(classother);
	 	 
	 		 $('#quali_deg_otherSpouse').val(deg_other);
	 	
	 		 $('#quali_spec_otherSpouse').val(spec_other);
	 		 
	 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');

	 	 
			$("#spouse_quali_radio1").prop("checked", true);
			$("#spouse_Qualifications").show();
		}
	});
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

function medical_cat_save() {

	var count_classi = 0;
	
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
	
$('#save_medical_cat').prop('disabled', true);

var formvalues=$("#madical_category_form").serialize();
var comm_date=$("#comm_date").val();
var census_id=$("#census_id").val();	
var comm_id=$('#comm_id').val();	
/* Date 1bx_from_date = $("input#1bx_from_date").val();
Date 1bx_to_date = $("#1bx_to_date").val(); */
formvalues += "&census_id=" + census_id + "&comm_id=" + comm_id;

if(getformatedDate($("input#1bx_from_date").val()) > getformatedDate($("#1bx_to_date").val())) {
	alert("To Date should be Greater than Or Equal to From Date");
	return false;
}

	$.post('census_medical_categoryAction?' + key + "=" + value, formvalues, function(data) {
		if(data == '1') {
			alert('Data Save/Update Successfully');
			$("#submit_data").show();
			
			getsShapeDetails();
			gethShapeDetails();
			getaShapeDetails();
			getpShapeDetails();
			geteShapeDetails();
			getcCopeDetails();
			getoCopeDetails();
			getpCopeDetails();
			geteCopeDetails();
			$('#save_medical_cat').prop('disabled', false);
		} else {
			alert(data);
			
			$('#save_medical_cat').prop('disabled', false);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
		$('#save_medical_cat').prop('disabled', false);
	});
}


	


		



			






function getsShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'S'
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
				datepicketDate('s_from_date' + x);
				datepicketDate('s_to_date' + x);
				$("#s_to_date" + x).datepicker("option", "maxDate", null);
				$("#s_status" + x).val(j[i][0]);
				$.ajaxSetup({
					async: false
				});
				statusChange(1, x, j[i][0]);
				$("#sShape_value" + x).val(j[i][1]);
// 				if(x > 1) {
// 					$("#s_status" + x + " option[value='1']").remove();
// 					$("#s_status" + (x - 1) + " option[value='1']").remove();
// 				} else {
					var thisT = document.getElementById('sShape_value' + x);
					onShapeValueChange(thisT, 's');
// 				}
			}
			sShape = v;
			$("input#sShape_count").val(v);
			$("input#sShapeOld_count").val(v);
			$("#sShape_add" + v).show();
			
			
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
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
				datepicketDate('h_from_date' + x);
				datepicketDate('h_to_date' + x);
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
			$("#hShape_add" + v).show();
			
		}
	});
}

function getaShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'A';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
				datepicketDate('a_from_date' + x);
				datepicketDate('a_to_date' + x);
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
			$("#aShape_add" + v).show();
			
		}
	});
}

function getpShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'P';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#pShape_add" + v).show();
			
		}
	});
}

function geteShapeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'E';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#eShape_add" + v).show();
			
		}
	});
}

function getcCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_C';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#cCope_add" + v).show();
		}
	});
}

function getoCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_O';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#oCope_add" + v).show();
		}
	});
}

function getpCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_P';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#pCope_add" + v).show();
		}
	});
}

function geteCopeDetails() {
	var p_id = $('#census_id').val();
	var Shape = 'C_E';
	var i = 0;
	$.post('madical_cat_GetData?' + key + "=" + value, {
		p_id: p_id,
		Shape: Shape
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
			$("#eCope_add" + v).show();
		}
	});
}

$("input[type='checkbox'][name='multisub']").click(function() {
	
	var num = 0;
	$('#quali_sub_list').empty()
	$("input[type='checkbox'][name='multisub']").each(function() {
		if(this.checked) {
			$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
			num = num + 1;
		}
	});
	if(num != 0) $("#sub_quali option:first").text('Subjects(' + num + ')');
	else $("#sub_quali option:first").text("Select Subjects");
});

// $("#quali_sub_search").keyup(function() {
// 	var re = new RegExp($(this).val(), "i")
// 	$('.quali_subjectlist').each(function() {
// 		var text = $(this).text(),
// 			matches = !!text.match(re);
// 		$(this).toggle(matches)
// 	})
// });
//////modified on 21 june 2022 AS PER MISO PSG REQUIREMENT
$("#quali_sub_search").keyup(function() {
	var re = new RegExp($(this).val(), "i")
	var search = $(this).val();
	var searchL = search.length;
	$('.quali_subjectlist').each(function() {
		var text = $(this).text(),
			matches = !!text.match(re);
		if (text.trim().substring(0,searchL).toUpperCase() == search.trim().toUpperCase()) {
			$(this).toggle(true);
		}
		else{
			$(this).toggle(false);
		}
	})
});
$("input[type='checkbox'][name='spouse_multisub']").click(function() {
	
	var num = 0;
	$('#spouse_quali_sub_list').empty()
	$("input[type='checkbox'][name='spouse_multisub']").each(function() {
		if(this.checked) {
			$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
			num = num + 1;
		}
	});
	if(num != 0) $("#spouse_sub_quali option:first").text('Subjects(' + num + ')');
	else $("#spouse_sub_quali option:first").text("Select Subjects");
});
$("#spouse_searchSubject").keyup(function() {
	var re = new RegExp($(this).val(), "i")
	$('.spouse_subjectlist').each(function() {
		var text = $(this).text(),
			matches = !!text.match(re);
		$(this).toggle(matches)
	})
});

function removeSubFn(value) {
	$("input[type='checkbox'][name='multisub'][value='" + value + "']").attr('checked', false);
	var num = 0;
	$('#quali_sub_list').empty()
	$("input[type='checkbox'][name='multisub']").each(function() {
		if(this.checked) {
			$('#quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(" + this.value + ")'></i><span> <br>");
			num = num + 1;
		}
	});
	if(num != 0) $("#sub_quali option:first").text('Subjects(' + num + ')');
	else $("#sub_quali option:first").text("Select Subjects");
}

function removeSpouseSubFn(value) {
	$("input[type='checkbox'][name='spouse_multisub'][value='" + value + "']").attr('checked', false);
	var num = 0;
	$('#spouse_quali_sub_list').empty()
	$("input[type='checkbox'][name='spouse_multisub']").each(function() {
		if(this.checked) {
			$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSpouseSubFn(" + this.value + ")'></i><span> <br>");
			num = num + 1;
		}
	});
	if(num != 0) $("#spouse_sub_quali option:first").text('Subjects(' + num + ')');
	else $("#spouse_sub_quali option:first").text("Select Subjects");
}
/* var input = document.getElementById("first_name");
input.addEventListener("keyup", function () {
	AvoidSpace(event);
});
input.addEventListener("keyup", function () {
	specialcharecter(this);
}); */
$(document).ready(function() {
	
	if('${personnel_no}' != "") {
		$('#btn1').hide();
		$('#censussearshUrl').show();
		$("#personnel_no").val('${personnel_no}');
		personal_number();
	}
	siblingCB(1);
	spouseCB(1);
	
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

function marriageNationChange(index){
	var value=$("#marriage_nationality"+index+" option:selected").text();
	if(value==other){
		$('#marriage_othernationality'+index).prop("readonly",false)
	}
	else{
		$('#marriage_othernationality'+index).prop("readonly",true)
		$('#marriage_othernationality'+index).val("")
	}
}
function divorceNationChange(index){
	var value=$("#divorce_nationality"+index+" option:selected").text();
	if(value==other){
		$('#divorce_othernationality'+index).prop("readonly",false)
	}
	else{
		$('#divorce_othernationality'+index).prop("readonly",true)
		$('#divorce_othernationality'+index).val("")
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

function opendiv(){
	
	var comm_id=$("#comm_id").val();
	
	$.post('check_perasonel_no?' + key + "=" + value, {
		comm_id:comm_id
	}, function(j) {
		
	
		if(j!=null&&j!="")
			{
 		$("#"+j[0]).click();
 		if(j[0]=="personal_btn")
 			{
 			tablinks = document.getElementsByClassName("tablinks");
 			tablinks[0].classList.add("active");
 			}
 		if(j[0]=="add_btn_re")
			{
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[1].classList.add("active");
			}
 		if(j[0]=="family_btn")
			{
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[2].classList.add("active");
			}
 		if(j[0]=="pre_cadet_btn")
			{
			tablinks = document.getElementsByClassName("tablinks");
			tablinks[4].classList.add("active");
			}
 		if(j[0]=="qualification_btn")
		{
		tablinks = document.getElementsByClassName("tablinks");
		tablinks[5].classList.add("active");
		}
 		if(j[0]=="language_btn")
		{
		tablinks = document.getElementsByClassName("tablinks");
		tablinks[6].classList.add("active");
		}
 		if(j[0]=="medical_details_bt")
		{
		tablinks = document.getElementsByClassName("tablinks");
		tablinks[7].classList.add("active");
		}
			}
		else
		{
			$("#personal_btn").addClass("active");
 					$("#personal_btn").click();
 					tablinks = document.getElementsByClassName("tablinks");
 					tablinks[0].classList.add("active");		           		          
		}
	});
	
	
}	

	</script>
	
	
	<c:url value="AllCopeRemove" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="View1Form" name="View1Form" >
	   <input type="hidden" name="personnel_no9" id="personnel_no9" value="0">  
	    <input type="hidden" name="id9" id="id9" value="0">  
	    <input type="hidden" name="mlabel9" id="mlabel9" value="mlabel">  
</form:form>
	
	
	