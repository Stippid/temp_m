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
  <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

<style>
.selectBox {
	position: relative;
}
label {
    word-break: break-word;
}
table{
width:100%;
}
select{
display: none;}



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
.hide_action{
display: none;
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
.tab {
	
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
								<!-- 					<div class="panel-heading"> -->
								<!-- 						<h4 class="panel-title main_title" id="insp_div5"> -->
								<!-- 							<a class="droparrow collapsed" data-toggle="collapse" -->
								<!-- 								data-parent="#accordion" href="#collapse1in" id="insp_final" -->
								<!-- 								style="color: blue;"><b>Census Details</b></a> -->
								<!-- 						</h4> -->
								<!-- 					</div> -->
								<div class="card-header">
									<h5> View Census Details of Offr</h5>
									<h6 class="enter_by">
										<span style="font-size: 12px; color: red;">(To be
											entered by unit )</span>
									</h6>
								</div>
								<div class="col-md-12"
									style="padding-top: 20px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no"
													class="form-control autocomplete" autocomplete="off">

											</div>
										</div>
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
												style="color: red;"> </strong> Name</label>
										</div>
										<div class="col-md-8">
											<label class=" form-control-label" id="cadet_name"></label>
												
													<input type="hidden" id="medDtlFillIn3008" name="medDtlFillIn3008"
												value="0" class="form-control autocomplete"
												autocomplete="off">
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
											
												<label class=" form-control-label" id="first_name"></label>
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
										
												
												
												<label class=" form-control-label" id="middle_name"></label>
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
								

						<label class=" form-control-label" id="last_name"></label>
												
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
											<label class=" form-control-label" id="dob"></label> 
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
											<select name="country_birth" id="country_birth" class="hide_action"
												onchange="fn_country_birth();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="country_birthlbl"></label> 
											
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
											<select name="state_birth" id="state_birth" class="hide_action"
												onchange="fn_state_birth();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="state_birthlbl"></label> 
										</div>
									</div>
								</div>

							</div>
							
							<div class="col-md-12">
								<div class="col-md-6" id = "country_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">COUNTRY of Birth Other </label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="country_birth_other" name="country_birth_other" class="form-control autocomplete" autocomplete="off" > -->
					                	<label class=" form-control-label" id="country_birth_other"></label> 
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "state_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE of Birth Other</label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="state_birth_other" name="state_birth_other" class="form-control autocomplete" autocomplete="off" > -->
					                	<label class=" form-control-label" id="state_birth_other"></label> 
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
											<select name="district_birth" id="district_birth" onchange="fn_district_birth();" class="hide_action"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedDistrictName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="district_birthlbl"></label> 
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
											
												
												<label class=" form-control-label" id="place_birth"></label> 
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
<!-- 					                	<input type="text" id="district_birth_other" name="district_birth_other" class="form-control autocomplete" autocomplete="off" > -->
					                	<label class=" form-control-label" id="district_birth_other"></label> 
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
											<select name="nationality" id="nationality" class="hide_action"
												class="form-control-sm form-control" onchange="fn_nationality();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNationalityList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="nationalitylbl"></label> 
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
											<select name="mother_tongue" id="mother_tongue" class="hide_action"
												class="form-control-sm form-control" onchange="fn_mother_tongue();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMothertoungeList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="mother_tonguelbl"></label> 
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
<!-- 					                	<input type="text" id="nationality_other" name="nationality_other" class="form-control autocomplete" autocomplete="off" > -->
					                		<label class=" form-control-label" id="nationality_other"></label> 
					                	 </div>
						            </div>
	              				</div>
	              					<div class="col-md-6" id = "mother_tongue_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Mother Tongue Other </label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="mother_tongue_other" name="mother_tongue_other" class="form-control autocomplete" autocomplete="off" > -->
					                	<label class=" form-control-label" id="mother_tongue_other"></label> 					                	
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
											<select name="religion" id="religion" class="hide_action"
												class="form-control-sm form-control" onchange="fn_religion();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getReligionList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="religionlbl"></label> 
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
												<select name="ncc_type" id="ncc_type" class="hide_action"
													class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getNCCTypeList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
												<label class=" form-control-label" id="ncc_typelbl"></label> 
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
<!-- 					                	<input type="text" id="religion_other" name="religion_other" class="form-control autocomplete" autocomplete="off" > -->
					                	<label class=" form-control-label" id="religion_other"></label> 
					                	 </div>
						            </div>
	              				</div>
	              					
	              					</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Aadhaar No</label>
										</div>



										<div class="col-md-2">
										
											<label class=" form-control-label" id="adhar_number1"></label> 	
												
										</div>
										<div class="col-md-2">
											<label class=" form-control-label" id="adhar_number2"></label> 	
										</div>
										<div class="col-md-2">
											<label class=" form-control-label" id="adhar_number3"></label> 	
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Pan No</label>
										</div>
										<div class="col-md-8">	
										<label class=" form-control-label" id="pan_no"></label> 	

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
											<select name="com_institute" id="com_institute" class="hide_action"
												class="form-control-sm form-control"
												onchange="getInst_btn(this);">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCommInstitute}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
											
										<label class=" form-control-label" id="com_institutelbl"></label> 
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
											<select name="training_institute" id="training_institute" class="hide_action"
												class="form-control-sm form-control"
												onchange="getInst_trainngBtn(this);">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTrainingInstitute}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
											
											<label class=" form-control-label" id="training_institutelbl"></label> 
											
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
											<select name="com_bn_id" id="com_bn_id" class="hide_action"
												class="form-control autocomplete"
												onchange="getInst_company(this);">
												<option value="0">--Select--</option>
										</select>
						<label class=" form-control-label" id="com_bn_idlbl"></label> 
										</div>

									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Comm Coy</label>
										</div>
										<div class="col-md-8">
											<select name="com_company_id" id="com_company_id" class="hide_action"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
											</select>
								<label class=" form-control-label" id="com_company_idlbl"></label> 
										</div>
									</div>
								</div>


								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Trg Bn</label>
										</div>
										<div class="col-md-8">
											<select name="training_bn_id" id="training_bn_id" class="hide_action"
												class="form-control autocomplete"
												onchange="getInst_trainngCompany(this);">
												<option value="0">--Select--</option>
										</select>
							<label class=" form-control-label" id="training_bn_idlbl"></label> 
										</div>

									</div>
								</div>
								<div class="col-md-3">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Trg Coy</label>
										</div>
										<div class="col-md-8">
											<select name="training_company_id" id="training_company_id" class="hide_action"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												</select>
											<label class=" form-control-label" id="training_company_idlbl"></label> 

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
											<select name="marital_status" id="marital_status" class="hide_action"
												class="form-control-sm form-control"
												onchange="getmarital_status()">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_statusList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="marital_statuslbl"></label> 
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
											<select name="blood_group" id="blood_group" class="hide_action"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getBloodList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="blood_grouplbl"></label> 
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
											<select name="height" id="height" class="hide_action"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getHeight}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
								<label class=" form-control-label" id="heightlbl"></label> 
										</div>
									</div>
								</div>
							</div>

		<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Father Name</label>
										</div>
										<div class="col-md-8">										
											<label class=" form-control-label" id="father_name"></label> 
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label">Mother Name</label>
										</div>
										<div class="col-md-8">											
								           <label class=" form-control-label" id="mother_name"></label> 
										</div>
									</div>
								</div>
							</div>
							<div>



								<div class="col-md-12" style="margin-top: 10px; width: 100%;">
									<label class=" form-control-label" style="margin-right: 100px;"><h4>Any
											Known Allergy</h4></label> &nbsp&nbsp<label class=" form-control-label" id="allergic_radio"><br>

								</div>
								<br>

								<div id="allergicToMedicineDiv" style="display: none;">



									<table id="allergic_table" class="table-bordered"
										style="margin-top: 10px; width: 100%;">

										<thead style="color: white; text-align: center;">
											<tr>
												<th style="width: 10%;">Sr No</th>
												<th>Allergy</th>
												

											</tr>
										</thead>
										<tbody data-spy="scroll" id="allergictbody"
											style="min-height: 46px; max-height: 650px; text-align: center;">
											<tr id="tr_allergic1">
												<td style="width: 2%;">1</td>
												<td>
													<label class=" form-control-label" id="medicine1"></label> 
												</td>

												
											

											</tr>
										</tbody>
									</table>



								</div>

							</div>						
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
											<select name="org_domicile_Country" id="org_domicile_Country" class="hide_action"
												onchange="fn_org_domicile_Country();"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCountryName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="org_domicile_Countrylbl"></label>
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
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</SELECT>
											<label class=" form-control-label" id="org_domicile_statelbl"></label>
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
					                	<label class=" form-control-label" id="org_domicile_Country_other"></label>
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "org_domicile_state_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> STATE OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	 <label class=" form-control-label" id="org_domicile_state_other"></label>
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
											<select name="org_domicile_district"
												id="org_domicile_district"
												onchange="fn_org_domicile_district();"
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedDistrictName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="org_domicile_districtlbl"></label>
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
												>
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCityName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="org_domicile_tehsillbl"></label>
										</div>
									</div>
								</div>
							</div>
							
								<div class="col-md-12">
								<div class="col-md-6" id = "org_domicile_district_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">  District Other</label>
						                </div>
						                <div class="col-md-8">
					                	<label class=" form-control-label" id="org_domicile_district_other"></label>
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "org_domicile_tehsil_other_id" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> TEHSIL OTHER</label>
						                </div>
						                <div class="col-md-8">
					                	<label class=" form-control-label" id="org_domicile_tehsil_other"></label>
					                	 </div>
						            </div>
	              				</div>
	              			</div>	
							
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
												 onchange="typeSelection()">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getPreCadetStatusList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
											<label class=" form-control-label" id="cadet_status1lbl"></label>
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-12">
								<br>
							</div>

							<div class="col-md-12" id="pre_cadetDegiDiv">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Designation</label>
										</div>
										<div class="col-md-8">									
												<label class=" form-control-label" id="designation1"></label>
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
								
													<label class=" form-control-label" id="emp_name1"></label>
										</div>
									</div>
								</div>
								
								


							</div>
								<div class="col-md-12" id="pre_cadetCompeDiv">
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Competency</label>
										</div>
										<div class="col-md-8">
										<select name="competency" id="competency"
											 onchange="fn_otherShowHide(this,'divcompetency_other','competency_other')"
											>
											<option value="0" >--Select--</option>
											<c:forEach var="item" items="${getSpecializationList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
										<label class=" form-control-label" id="competencylbl"></label>
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
					
												<label class=" form-control-label" id="competency_other"></label>
										</div>
									</div>
								</div>
								
								</div>
							<div class="col-md-12" id="pre_cadetRadioDiv">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>If Gazetted/Non Gazetted</label>
										</div>
										<div class="col-md-8">
<!-- 											<input type="radio" id="isyes1" name="isgazetted1" -->
<!-- 												value="gazetted"> <label for="Gazetted">Gazetted</label> -->
<!-- 											<input type="radio" id="isno1" name="isgazetted1" -->
<!-- 												value="non_gazetted"> <label for="No">Non -->
<!-- 												Gazetted</label> -->
												
												<label class=" form-control-label" id="isgazetted1"></label>
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
<!-- 											<input type="radio" id="iscivilyes1" name="isCivil1" -->
<!-- 												value="yes"> <label for="Yes">Yes</label> <input -->
<!-- 												type="radio" id="iscivilno1" name="isCivil1" value="no"> -->
<!-- 											<label for="No">No</label> -->
											<label class=" form-control-label" id="isCivil1"></label>
										</div>
									</div>
								</div>


							</div>
							<div class="col-md-12">
								<br>
							</div>
							<div id="pre_cadetArmyDiv">
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Service No</label>
										</div>
										<div class="col-md-8">
													<label class=" form-control-label" id="army_no1"></label>
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
												<label class=" form-control-label" id="unit_reg1"></label>

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
						<label class=" form-control-label" id="date_form1"></label>

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


										
												
												<label class=" form-control-label" id="date_to1"></label>

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
										
												<label class=" form-control-label" id="total_rank1"></label>
										</div>
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
<!-- 												<input type="radio" id="otu_div1" name="otu1" -->
<!-- 													value="otu_div"> <label for="Yes">OTU Div</label> <input -->
<!-- 													type="radio" id="jr_div1" name="otu1" value="jr_div"> -->
<!-- 												<label for="No">Jr Div</label> <input type="radio" -->
<!-- 													id="sr_div1" name="otu1" value="sr_div"> <label -->
<!-- 													for="No">Sr Div</label> <input type="radio" id="nill" -->
<!-- 													name="otu1" value="nil" checked="checked"> <label -->
<!-- 													for="nil">NIL</label> -->
													
													<label class=" form-control-label" id="otu1"></label>
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

					<input type="hidden" id="mad_classification_count" name="mad_classification_count" value="NIL">
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-12" style="font-size: 15px; margin:10px; ">
								<input type="checkbox" id="check_1bx" name="check_1bx"
									onclick="oncheck_1bx()" value="1BX" disabled="disabled"> <label for="text-input"
									class=" form-control-label" style="margin-left: 10px;"> SHAPE 1BX </label>
							</div>
					</div>
						<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"  value="0" class="form-control autocomplete" autocomplete="off" >
				</div>
					<div class="col-md-12" id="shape1bxdiv" style="display:none;">
				
				<div class="col-md-4">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>From Date</label>
						</div>
						<div class="col-md-8">
						<label  id="1bx_from_date" name="1bx_from_date" ></label>
						<input type="hidden" id="1bx_from_date" name="1bx_from_date" class="form-control autocomplete" autocomplete="off">

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
						<label  id="1bx_to_date" name="1bx_to_date" ></label>
							<input type="hidden" id="1bx_to_date" name="1bx_to_date" class="form-control autocomplete" autocomplete="off">
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
						<label  name="1bx_diagnosis_code" id="1bx_diagnosis_code" ></label>
							<input type="hidden" name="1bx_diagnosis_code" id="1bx_diagnosis_code" class="form-control-sm form-control" autocomplete="off"
								placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">
						</div>
					</div>
				</div>
			</div>
			
				<div class="watermarked"  id="shapediv" style="width: -webkit-fill-available;">				
							
			<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>S - Psychological & Congnitive</strong></div>
			<div class="">
	<table id="s_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass1">Diagnosis</th>	

											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="s_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					<br/>
				
		<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>H - Hearing</strong></div>
				<div>
	<table id="h_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass2">Diagnosis</th>		
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="h_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
				<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>A - Appendages</strong></div>		
					<div>
	<table id="a_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass3">Diagnosis</th>	
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="a_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical Capacity</strong></div>	
					<div>
	<table id="p_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none; " class="diagnosisClass4">Diagnosis</th>	
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="p_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					<br/>
					
	<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Eye Sight</strong></div>			
					<div>
	<table id="e_madtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Status LMC</th> 
													<th style="">Value</th> 
													<th style="">From Date</th> 													
													<th style="">To Date</th> 
													<th style="display:none;" class="diagnosisClass5">Diagnosis</th>		
											<!-- <th style="display:none;" class="addbtClass5">Action</th> -->
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="e_madtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 
										   </tbody> 
									</table>
					</div>
					
					
				<br/>
				
				<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>C - Climate and Terrain Restrictions</strong></div>
				<div style="width: -webkit-fill-available;">
	<table id="c_cmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="cCopClass">Other</th>  													
<!-- 													<th >Description</th>																					 -->
												<!-- 	<th style="width: 10%; display:none;" class="CopaddbtClass1">Action</th> -->
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_cmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					<br/>
					
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>O - Degree of Medical Observation Required</strong></div>
									<div style="width: -webkit-fill-available;">
	<table id="c_omadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													<!-- <th style="width: 10%; display:none;" class="CopaddbtClass2">Action</th> -->
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_omadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>
					
					
					<br/>
					<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>P - Physical Capability Limitations</strong></div>
										<div style="width: -webkit-fill-available;">
	<table id="c_pmadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th> 													
<!-- 													<th >Description</th>																					 -->
													
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_pmadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												
										   </tbody> 
									</table>
					</div>	
					

<br/>
<div class="card-header-inner" style="margin-left:20px;margin-bottom:20px;"> <strong>E - Exclusive Limitations</strong></div>
			<div style="width: -webkit-fill-available;">
	<table id="c_emadtable" class="table-bordered " style="margin-top:10px;width:100%;">
											
											<thead style=" color: white; text-align: center;">
												<tr>
													<th style="width: 2%;">Sr No</th>
													<th style="">Value & Description</th>
													<th style="display:none;" class="eCopClass">SubValue</th> 
													<th style="display:none;" class="eCop_otherClass">Other</th> 													
<!-- 													<th >Description</th>																					 -->
													
											   </tr>
											</thead>
										   <tbody data-spy="scroll" id="c_emadtbody" style="min-height:46px; max-height:650px; text-align: center;">
												 
										   </tbody> 
									</table>
					</div>
					</div>
					
							</div>

						</div>
					</div>
					
						</form>
					</div>
					
				</div>
			</div>
		</div>

	</div>


<div id="submit_data" class="card-footer" align="center"
	class="form-control" style="display: none;">
<!-- 	<input type="button" class="btn btn-primary btn-sm" -->
<!-- 		value="Submit For Approval" onclick="Submit_Approval();"> -->


<!--  <i  class="action_icons action_approve"></i> -->
<div id="approve_checkdiv" style="display:none">
 <p><input type="checkbox" class="btn btn-success btn-sm" id="approve_check" > I certify that all the data has been checked and verified by me.</p>
</div>
 <!-- <input type="button" class="btn btn-success btn-sm" value="Approve" onclick="Approved();"  id="approve_btn" style="display:none"> -->
  <input type="submit" class="btn btn-primary btn-sm" value="Approve" id="approve_btn" style="display:none" onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return Approved();}else{return false;}">
<!--  <input type="button" class="btn btn-danger btn-sm" id="reject_btn" onclick="Reject();" value="Reject" > -->
 
   <input type="button" class="btn btn-primary btn-sm" value="Reject" id="reject_btn" style="display:none" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Reject',0)}else{return false;}">

<a href="search_censusUrl" id="censussearshUrllink" style="display: none;"
		class="btn btn-success btn-sm">Back</a> <button
		 id="censussearshUrl"  onclick="returnSearch()"
		class="btn btn-info btn-sm">Back</button>
</div>

<c:url value="GetSearch_Census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value=""/>				
		<input type="hidden" name="rank1" id="rank1"/>		
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="IsMns" id="IsMns"  />
		
	</form:form> 

<%-- <c:url value="Approve_Census" var="approveUrl" /> --%>
<%-- <form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" > --%>
<!-- 	<input type="text" name="idA" id="idA" value="0"/> 	 -->
<%-- </form:form>	 --%>

<%-- <c:url value="Reject_Census" var="rejectUrl" /> --%>
<%-- <form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" > --%>
<!-- 	<input type="text" name="idR" id="idR" value="0"/>  -->
<%-- </form:form> --%>
<script>

function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
  
 
if(cityName=="Pre-Cadet"){
	  
	  getPreCreditDetails();
	  getNCC();
  }
else if(cityName=="addr_details"){	 

		get_address_details();
}
else if(cityName=="language_details"){	 
	get_language_details();
	colAdj("language_table");
	colAdj("foregin_language_table");
}
 
else if(cityName=="Foreign_Countries"){
	  getForeign_CountriesDetails();
	  colAdj("country_visit");
}
 
else if(cityName=="Qualifications"){
	  getQualifications();
	  colAdj("qualificationtable");
}

else if(cityName=="family_details"){
	 getmarital_status();
	 getfamily_siblingDetails();
	 getfamily_marriageDetails();
	 getSpouseQualificationData();
 	 getfamily_divoreceDetails();	 

 	 colAdj("family_table");
 	colAdj("married_table");
 	colAdj("divorce_table");
 	
}
else if(cityName=="medical_details"){ 
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


$(".hide-action").hide();




}
//*************************************MAIN Personel Number Onchange*****************************//
var comm_id = "";
var off_dob;
var comm_date;
function personal_number() {

	if($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	var census_id = $('#census_id').val()
	$("#submit_data").show();
	if(parseInt(census_id) > 0) {
		$("#tab_id").show();
	} else {
		$("#1").show();
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
	
	
		off_dob = ParseDateColumncommission(j[0][4]);
		$("#dob").text(convertDateFormate(j[0][4]));

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

	var comm_id =$("#comm_id").val();	
	$.post('GetCensusData?' + key + "=" + value, {
		comm_id: comm_id,app_status:app_status
	}, function(k) {
		if(k.length > 0) {
			
			$.ajaxSetup({
				async: false
			});
	
		
			$('#census_id').val(k[0].id);
			$('#first_name').text(k[0].first_name);
			$('#middle_name').text(k[0].middle_name);
			$('#last_name').text(k[0].last_name);
			$('#place_birth').text(k[0].place_birth);
			$('#country_birth').val(k[0].country_birth);
			$('#country_birthlbl').text($("#country_birth option:selected").text());
			fn_country_birth();
			$('#country_birth_other').text(k[0].country_birth_other);
			$('#state_birth').val(k[0].state_birth);
			$('#state_birthlbl').text($("#state_birth option:selected").text());
			fn_state_birth();
			$('#state_birth_other').text(k[0].state_birth_other);
			$('#district_birth').val(k[0].district_birth);
			$('#district_birthlbl').text($("#district_birth option:selected").text());
			fn_district_birth();
			$('#district_birth_other').text(k[0].district_birth_other);
			
			$('#nationality').val(k[0].nationality);
			$('#nationalitylbl').text($("#nationality option:selected").text());
			fn_nationality();
			$('#nationality_other').text(k[0].nationality_other);
			$('#religion').val(k[0].religion);
			$('#religionlbl').text($("#religion option:selected").text());
			fn_religion();
			$('#religion_other').text(k[0].religion_other);
			$('#marital_status').val(k[0].marital_status);
			$('#marital_statuslbl').text($("#marital_status option:selected").text());
			$('#blood_group').val(k[0].blood_group);
			$('#blood_grouplbl').text($("#blood_group option:selected").text());
			$('#height').val(k[0].height);
			$('#heightlbl').text($("#height option:selected").text());
			$('#adhar_number1').text(k[0].adhar_number.toString().substring(0, 4)+" "+k[0].adhar_number.toString().substring(4, 8)+" "+k[0].adhar_number.toString().substring(8, 12));


			$('#pan_no').text(k[0].pancard_number);
			if(k[0].border_area == "yes") {
				$("input[name='border_area'][value='yes']").prop('checked', true);
			} else {
				$("input[name='border_area'][value='no']").prop('checked', true);
			}
			$('#mother_tongue').val(k[0].mother_tongue);
			$('#mother_tonguelbl').text($("#mother_tongue option:selected").text());
			fn_mother_tongue();
			$('#mother_tongue_other').text(k[0].mother_tongue_other);
			$('#ncc_type').val(k[0].ncc_type);
			$('#ncc_typelbl').text($("#ncc_type option:selected").text());
			
			$('#father_name').text(k[0].father_name);
			$('#father_dob').text(convertDateFormate(k[0].father_dob));
			$('#father_place').text(k[0].father_place_birth);
			$('#father_profession').val(k[0].father_profession);
			if (k[0].father_profession==0) {
				$('#father_professionlbl').text("");
			}
			else
			$('#father_professionlbl').text($("#father_profession option:selected").text());
			$('#mother_name').text(k[0].mother_name);
			$('#mother_dob').text(convertDateFormate(k[0].mother_dob));
			$('#mother_place').text(k[0].mother_place_birth);
			$('#mother_profession').val(k[0].mother_profession);
			if (k[0].mother_profession==0) {
				$('#mother_professionlbl').text("");
			}
			else
			$('#mother_professionlbl').text($("#mother_profession option:selected").text());
		
			$('#father_adhar_number').text(k[0].father_adhar_number);
			$('#father_pan_no').text(k[0].father_pancard_number);
			$('#mother_adhar_number').text(k[0].mother_adhar_number);
			$('#mother_pan_no').text(k[0].mother_pancard_number);
			if(k[0].father_profession==103){
				$('#father_proffother').text(k[0].father_proffother);
			}
			if(k[0].mother_profession==103){
				$('#mother_proffother').text(k[0].mother_proffother);
			}



				/// bisag 180822 v1 (compulsion removed from profession)
				if(k[0].father_service!=null && k[0].father_service!="0"){
					$("#father_proff_radio").text('YES');
					$('#father_service').val(k[0].father_service);
					$('#father_servicelbl').text($("#father_service option:selected").text());
				}
				
				else  {
					$("#father_proff_radio").text('NO');
				}
				if(k[0].father_other!=null)
					$('#father_other').text(k[0].father_other);
				if(k[0].father_personal_no!=null)
					$('#father_personal_no').text(k[0].father_personal_no);
				
	
				
				if(k[0].mother_service!=null  && k[0].mother_service!="0"){
					$("#mother_proff_radio").text('YES');
					$('#mother_service').val(k[0].mother_service);
					$('#mother_servicelbl').text($("#mother_service option:selected").text());
				}
				else  {
					$("#mother_proff_radio").text('NO');
				}
				if(k[0].mother_other!=null)
					$('#mother_other').text(k[0].mother_other);
				if(k[0].mother_personal_no!=null)
					$('#mother_personal_no').text(k[0].mother_personal_no);
			 
				$('#com_institute').val(k[0].com_institute);
				$('#com_institutelbl').text($("#com_institute option:selected").text());

				var comm_id = $('#comm_id').val();
				var i_id = $('#i_id').val();
				
				
		
			} else {
				$("#submit_data").hide();
			}
		
	
			
			getInst_btn(document.getElementById('com_institute'));
			$('#com_bn_id').val(k[0].com_bn_id);
			$('#com_bn_idlbl').text($("#com_bn_id option:selected").text());
			getInst_company(document.getElementById('com_bn_id'));
			$('#com_company_id').val(k[0].com_company_id);
			$('#com_company_idlbl').text($("#com_company_id option:selected").text());
		
			if(k[0].training_institute != null) {
				$('#training_institute').val(k[0].training_institute);
				$('#training_institutelbl').text($("#training_institute option:selected").text());
				getInst_trainngBtn(document.getElementById('training_institute'));
				$('#training_bn_id').val(k[0].training_bn_id);
				$('#training_bn_idlbl').text($("#training_bn_id option:selected").text());
				getInst_trainngCompany(document.getElementById('training_bn_id'));
				$('#training_company_id').val(k[0].training_company_id);
				$('#training_company_idlbl').text($("#training_company_id option:selected").text());
			}
			if($('#training_institutelbl').text()=='--Select--'){
				$('#training_institutelbl').text('');
			
			}
			if($('#training_bn_idlbl').text()=='--Select--'){
			
				$('#training_bn_idlbl').text('');
				
			}
			if($('#training_company_idlbl').text()=='--Select--'){			
				$('#training_company_idlbl').text("");
			}
		
			if($('#com_bn_idlbl').text()=='--Select--'){			
				$('#com_bn_idlbl').text('');
			}
			if($('#com_company_idlbl').text()=='--Select--'){			
				$('#com_company_idlbl').text('');
			}
			$("#org_domicile_Country").val(k[0].org_country);
			 $('#org_domicile_Countrylbl').text($("#org_domicile_Country option:selected").text());
			fn_org_domicile_Country();
			$("#org_domicile_state").val(k[0].org_state);
			 $('#org_domicile_statelbl').text($("#org_domicile_state option:selected").text());
			fn_org_domicile_state();
			$("#org_domicile_district").val(k[0].org_district);
			 $('#org_domicile_districtlbl').text($("#org_domicile_district option:selected").text());
			fn_org_domicile_district();
			$("#org_domicile_tehsil").val(k[0].org_tehsil);
			 $('#org_domicile_tehsillbl').text($("#org_domicile_tehsil option:selected").text());
			fn_org_domicile_tehsil(); 			
			var comm_id = $('#comm_id').val();
			
			$("#org_domicile_Country_other").text(k[0].org_domicile_country_other);
			$("#org_domicile_state_other").text(k[0].org_domicile_state_other);
			$("#org_domicile_district_other").text(k[0].org_domicile_district_other);
			$("#org_domicile_tehsil_other").text(k[0].org_domicile_tehsil_other);			
			
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

//*************************************END Personel Detail*****************************//

//////////////////////////////////start  pre_cadet//////////////////////////////////////////////////

function getPreCreditDetails() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getPreCreditData?' + key + "=" + value, {
		p_id: p_id,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			
			
			$('#cadet_status1').val(j[0].pre_cadet_status);
			$('#cadet_status1lbl').text($("#cadet_status1 option:selected").text());
			if(j[0].pre_cadet_status == "1") {
				$('#pre_cadetDegiDiv').hide();
				$('#pre_cadetCompeDiv').hide();
				$('#pre_cadetArmyDiv').hide();
				$('#pre_cadetRadioDiv').hide();
			
			}
			if($("#cadet_status1").val() == "2" || $("#cadet_status1").val() == "3" || $("#cadet_status1").val() == "4") {
				$('#pre_cadetDegiDiv').show();
				$('#pre_cadetCompeDiv').hide();
				$('#pre_cadetArmyDiv').hide();
				$('#pre_cadetRadioDiv').hide();
				$("#designation1").text(j[0].designation);
				$("#emp_name1").text(j[0].employee_name);
				if(j[0].gazetted == "gazetted") {
					$("#isgazetted1").text("Gazetted");
				} else {
					$("#isgazetted1").text('Non Gazetted');
				}
				if(j[0].civil_service == "yes") {
					$("#isCivil1").text('YES');
				} else {
					$("#isCivil1").text('NO');
				}
				if($("#cadet_status1").val() == "2") {
					$('#pre_cadetCompeDiv').show();
					$("#competency").val(j[0].competency);
					$('#competencylbl').text($("#competency option:selected").text());
					if(j[0].competency_other!=null)
						$("#competency_other").text(j[0].competency_other);
				}
				else{
			
					$('#pre_cadetRadioDiv').show();
				}
			
			}
			if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "6" || $("#cadet_status1").val() == "7" || $("#cadet_status1").val() == "8" || $("#cadet_status1").val() == "9") {
				$('#pre_cadetDegiDiv').hide();
				$('#pre_cadetCompeDiv').hide();
				$('#pre_cadetArmyDiv').show();
				$('#pre_cadetRadioDiv').hide();
				$("#army_no1").text(j[0].army_no);
				$("#date_form1").text(convertDateFormate(j[0].from_date));
				$("#date_to1").text(convertDateFormate(j[0].to_date));
				$("#total_rank1").text(j[0].total_service);
				
				if($("#cadet_status1").val() == "5" || $("#cadet_status1").val() == "9") {
					$("#sus_no").val(j[0].unit_reg);
					fn_getUnitnamefromSus(j[0].unit_reg, document.getElementById("unit_reg1"));
				} else {
					$("#unit_reg1").text(j[0].unit_reg);
				}
			}
			$("#pre_ch_id1").val(j[0].id);
			
			fn_otherShowHide(document.getElementById('competency'),'divcompetency_other','competency_other');
		}
	});
}


function getNCC() {
	var n_id = $('#census_id').val();
	var i = 0;
	$.post('getNCCData?' + key + "=" + value, {
		n_id: n_id,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$("#ncc_ch_id1").val(j[0].id);
			if(j[0].otu == "otu_div") {
				$("#otu1").text('OUT Div');
				
			} else if(j[0].otu == "jr_div") {
				$("#otu1").text('Jr Div');
			} else if(j[0].otu == "sr_div") {
				$("#otu1").text('Sr Div');
			} else {
				$("#otu1").text('NIL');
			}
		}
	});
}
/////////country/////////////////////////////////


function getForeign_CountriesDetails() {
	var f_id = $('#census_id').val();
	var i = 0;
	$.post('getForeginCountryData?' + key + "=" + value, {
		f_id: f_id,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#foregin_Country_tbody').empty();
			for(i; i < v; i++) {
				f = i + 1;
		
			$("table#country_visit").append('<tr id="tr_foregin_country' + f + '">' 
				 + '<td class="fcon_srno">' + f + '</td>' 
				 +'<td style="width: 10%;"> <label id="country'+f+'"></label><select style="display:none;" name="countrySelect'+f+'" id="countrySelect'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForiegnCountryList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <label id="foregin_Country_ot'+f+'"></label></td>'
					+'<td style="width: 10%;"> <label id="from_dt'+f+'"></label></td>'
					+'<td style="width: 10%;"> <label id="to_dt'+f+'"></label></td>'
					+'<td style="width: 10%;"> <label id="period'+f+'"></label></td>'
					+'<td style="width: 10%;"> <label id="purpose_visit'+f+'"></label><select style="display:none" name="purpose_visitSelect'+f+'" id="purpose_visitSelect'+f+'" class="form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getForeign_country}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
					+'<td style="width: 10%;"> <label id="purpose_visit_ot'+f+'"></label></td>'
					+'<td style="display:none;"><input type="text" id="foregin_country_ch_id'+f+'" name="foregin_country_ch_id'+f+'"  value="' + j[i].id + '" value="0" class="form-control autocomplete" autocomplete="off" ></td>'
					+'</td></tr>');
	
		 $('#countrySelect'+f).val(j[i].country);
		  $('#country'+f).text($( "#countrySelect"+f+" option:selected" ).text());
		  $("#period"+f).text(j[i].period);
		  $("#from_dt"+f).text(convertDateFormate(j[i].from_dt));
		  $("#to_dt"+f).text(convertDateFormate(j[i].to_dt));
		  $('#purpose_visitSelect'+f).val(j[i].purpose_visit);
		  $('#purpose_visit'+f).text($( "#purpose_visitSelect"+f+" option:selected" ).text());
		  $('#foregin_Country_ot'+f).text(j[i].other_country);
		  $('#purpose_visit_ot'+f).text(j[i].other_purpose_visit);
		  
		
			}
			fcon = v;
			fcon_srno = v;
	
		}
	});
}



///////////////////////////END FOREGIN COUNTRY///////////////////////
////family
sib = 1;
sib_srno = 1;

function family_add_fn(q) {
	if($('#family_add' + q).length) {
		$("#family_add" + q).hide();
	}
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' + '<td style="width: 10%;"> <input type="sib_name' + sib + '" id="sib_name' + sib + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control-sm form-control"   >'
		
		+ '<td style="width: 10%;">'
		
		+ ' <input type="text" name="sib_date_of_birth' + sib + '" id="sib_date_of_birth' + sib + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td style="width: 10%;"> <select name="sib_relationship' + sib + '" id="sib_relationship' + sib + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '	id="sib_adhar_number' + sib + '" name="sib_adhar_number' + sib + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off"></td> ' + '<td style="width: 15%;"> ' + '	<input type="text" id="sib_pan_no' + sib + '" name="sib_pan_no' + sib + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="family_save_fn(' + sib + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + sib + '" onclick="family_add_fn(' + sib + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + sib + '" onclick="family_remove_fn(' + sib + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('sib_date_of_birth' + sib);
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
				alert("Data Deleted SuceessFully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function family_save_fn(ps) {
	// 	var sib_marital_status=$("#sib_marital_status"+ps).val()
	var sib_name = $('#sib_name' + ps).val();
	var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
	var sib_relationship = $('#sib_relationship' + ps).val();
	var sib_ch_id = $('#sib_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	var com_id = $("#comm_id").val();
	if(sib_name == "") {
		alert("Please Enter Sibling Name");
		$("input#sib_name" + ps).focus();
		return false;
	}
	if(sib_date_of_birth == "DD/MM/YYYY") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_date_of_birth == "") {
		alert("Please Enter Sibling Date of Birth");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	if(sib_relationship == "0") {
		alert("Please Select Sibling Relationship");
		$("select#sib_relationship" + ps).focus();
		return false;
	}
	if($('#sib_adhar_number' + ps).val() == "" || $('#sib_adhar_number' + ps).val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#spouse_adhar_number1").focus();
		return false;
	}
	var adhar_number = $('#sib_adhar_number' + ps).val().split('-').join('');
	var pan_no = $('#sib_pan_no' + ps).val();
	var currentdate = new Date();
	if(getformatedDate(sib_date_of_birth) > currentdate) {
		alert("Enter Valid Date of Birth Date");
		$("input#sib_date_of_birth" + ps).focus();
		return false;
	}
	$.post('family_sibling_action?' + key + "=" + value, {
		sib_name: sib_name,
		sib_date_of_birth: sib_date_of_birth,
		adhar_number: adhar_number,
		pan_no: pan_no,
		sib_relationship: sib_relationship,
		sib_ch_id: sib_ch_id,
		p_id: p_id,
		com_id: com_id
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
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getfamily_siblingDetails() {
	
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getfamily_siblingData?' + key + "=" + value, {
		p_id: p_id,app_status:app_status
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
				$("table#family_table").append(
						'<tr id="tr_sibling' + x + '">' + '<td class="sib_srno">' + x + '</td>' 
						+ '<td> <label >'+ j[i].name +'</label>' + '</td> '
					
					+ '<td><label >'+ convertDateFormate(j[i].date_of_birth)  +'</label>' + '</td>' 
					+ '<td> <label class=" form-control-label" id="sib_relationshiplbl' + x + '"> '
					+ '<select name="sib_relationship' + x + '" id="sib_relationship' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' 
					+ '<td><label class=" form-control-label"  ' + '	id="sib_adhar_number' + x + '" name="sib_adhar_number' + x + '" ' + '	>' + j[i].aadhar_no + ' </label></td> '
					+ '<td> ' + '	<label class=" form-control-label"  id="sib_pan_no' + x + '" name="sib_pan_no' + x + '" ' + '	>' + pan + '</label> ' + '	</td>'
					+ '<td> ' + '	<label class=" form-control-label" id="sibling_service' + x + '"> <select name="sib_service' + x + '" id="sib_service' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
					+ '<td> ' + '	<label class=" form-control-label"  id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	>' +  + '</label> ' + '	</td>'
					+ '<td> ' + '	<label class=" form-control-label"  id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	>' + + '</label> ' + '	</td></tr>');
				$('#sib_relationship' + x).val(j[i].relationship);
				if(j[i].sibling_service == 0){
					$('#sib_service' + x).val("");
				}else{
					$('#sib_service' + x).val(j[i].sibling_service);
				}
				$('#sibling_personal_no' + x).text(j[i].sibling_personal_no);
				$('#other_sibling_ser' + x).text(j[i].other_sibling_ser);
				$('#sibling_service' + x).text($("#sib_service" + x +" option:selected").text());
				$('#sib_relationshiplbl' + x).text($("#sib_relationship" + x +" option:selected").text());
					
			

					
			}
			sib = v;
			sib_srno = v;
			$('#family_add' + sib).show();
		}
	});
}

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
	$("table#married_table").append('<tr id="tr_marriage' + marr + '">' + '<td class="marr_srno">' + marr_srno + '</td>' + '<td style="width: 10%;"> <input type="text" id="maiden_name' + marr + '" name="maiden_name' + marr + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"> ' + ' <input type="text" name="marriage_date_of_birth' + marr + '" id="marriage_date_of_birth' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		
		+ '<td style="width: 10%;"> <input type="text" id="marriage_place_of_birth' + marr + '" name="marriage_place_of_birth' + marr + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;">' + ' <select id="marriage_nationality' + marr + '" name="marriage_nationality' + marr + '" class="form-control-sm form-control"   onchange="marriageNationChange('+marr+')">' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select> </td>'
		+ '<td style="width: 13%;"><input type="text" id="marriage_othernationality' + marr + '" name="marriage_othernationality' + marr + '" '
		+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
		+ '	</td>'
		 + '<td style="width: 10%;"> ' 
		+ ' <input type="text" name="marriage_date' + marr + '" id="marriage_date' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		
		+ '<td style="width: 10%;"> <input type="text" id="spouse_adhar_number' + marr + '" name="spouse_adhar_number' + marr + '"  onkeypress="return isAadhar(this,event);"   maxlength="14" class="form-control autocomplete" autocomplete="off"  ></td>' + '<td style="width: 10%;"> ' + '	<input type="text" id="spouse_pan_number' + marr + '" name="spouse_pan_number' + marr + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="marr_ch_id' + marr + '" name="marr_ch_id' + marr + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" style="width: 10%;"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "married_save' + marr + '" onclick="married_save_fn(' + marr + ');" ><i class="fa fa-save"></i></a>'
		
		+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "married_remove' + marr + '" onclick="married_remove_fn(' + marr + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('marriage_date_of_birth' + marr);
	datepicketDate('marriage_date' + marr);
	marriageNationChange(marr);
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
				
				alert("Data Deleted SuceessFully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function married_save_fn(ps) {
	if($("input#maiden_name1").val() == "") {
		alert("Please Enter  Name");
		$("input#maiden_name1").focus();
		return false;
	}
	if($("input#marriage_date_of_birth1").val() == "") {
		alert("Please Enter  Date of Birth");
		$("input#marriage_date_of_birth1").focus();
		return false;
	}
	if($("input#marriage_place_of_birth1").val() == "") {
		alert("Please Enter  Place of Birth");
		$("select#marriage_place_of_birth1").focus();
		return false;
	}
	if($("select#marriage_nationality1").val() == "0") {
		alert("Please Enter Nationality");
		$("select#marriage_nationality1").focus();
		return false;
	}
	if($("input#marriage_date1").val() == "") {
		alert("Please Enter Marriage Date");
		$("input#marriage_date1").focus();
		return false;
	}
	if($("input#spouse_adhar_number1").val() == "" || $("input#spouse_adhar_number1").val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#spouse_adhar_number1").focus();
		return false;
	}
	var marital_status = $("select#marital_status option:selected").val();
	var maiden_name = $('#maiden_name' + ps).val();
	var marriage_date_of_birth = $('#marriage_date_of_birth' + ps).val();
	var marriage_place_of_birth = $('#marriage_place_of_birth' + ps).val();
	var marriage_nationality = $('#marriage_nationality' + ps).val();
	var marriage_date = $('#marriage_date' + ps).val();
	var adhar_number = $('#spouse_adhar_number' + ps).val().split('-').join('');
	var marr_ch_id = $('#marr_ch_id' + ps).val();
	var p_id = $('#census_id').val();
	var com_id = $("#comm_id").val();
	var spouse_pan_number = $("#spouse_pan_number"+ps).val();
	var marriage_othernationality = $("#marriage_othernationality"+ps).val();
	
	if(getformatedDate(marriage_date_of_birth) >= getformatedDate(marriage_date)) {
		alert("Marriage Date should be Greater than Date of Birth");
		$("input#marriage_date" + ps).focus();
		return false;
	}
	if(calculate_age(document.getElementById('marriage_date_of_birth' + ps), document.getElementById('marriage_date' + ps)) && calculate_age(document.getElementById('dob_date'), document.getElementById('marriage_date' + ps))) {} else {
		return false;
	}
	$.post('family_marriage_action?' + key + "=" + value, {
		maiden_name: maiden_name,
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
		spouse_pan_number: spouse_pan_number
	}, function(data) {
		if(parseInt(data) > 0) {
			$('#marr_ch_id' + ps).val(data);
			$("#married_add" + ps).show();
			$("#married_remove" + ps).show();
			alert("Data Saved SuccesFully")
		} else alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
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
function getfamily_marriageDetails() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getfamily_marriageData?' + key + "=" + value, {
		p_id: p_id,app_status:app_status
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
				
				$("table#married_table").append('<tr id="tr_marriage' + x + '">' + '<td class="marr_srno">' + x + '</td>' + '<td> <label>' + j[i].maiden_name + '</label></td>' + '<td> ' + ' <label>' + convertDateFormate(j[i].date_of_birth) + '</label>' + '</td>' + '<td> <label>' + j[i].place_of_birth + '</label ></td>' + '<td> ' + '<label class=" form-control-label" id="marriage_nationalitylbl'+x+'"></label> <select id="marriage_nationality' + x + '" name="marriage_nationality' + x + '" onchange="marriageNationChange('+x+')"    >' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select></td>' 
						+ '<td><label>'+other_nation+'</label>'
						+ '	</td>'
				+ '<td> '
					+ ' <label>' + convertDateFormate(j[i].marriage_date) + '</label>' + '</td>'
					+ '<td> <label  id="spouse_adhar_number' + x + '" name="spouse_adhar_number' + x + '"  >' + j[i].adhar_number + '</label></td>' + '<td> ' + '	<label>' + pan_card + '</label> ' + '	</td> '
					+ '<td id="ad_th_service"> ' + '	<label class=" form-control-label" id="spouse_service' + x + '"> <select name="spo_service' + x + '" id="spo_service' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
					+ '<td id ="ad_th_persno"> ' + '	<label class=" form-control-label"  id="spouse_personal_no' + x + '" name="spouse_personal_no' + x + '" ' + '	>' +  + '</label> ' + '	</td>'
					+ '<td id="ad_th_otherser"> ' + '	<label class=" form-control-label"  id="other_spouse_ser' + x + '" name="other_spouse_ser' + x + '" ' + '	>' + + '</label> ' + '	</td>'
					+ '<td id ="ad_th_spouseproff"> ' + '<label class=" form-control-label" id="spouse_professionlbl'+x+'"></label> <select id="spouse_profession' + x + '" name="spouse_profession' + x + '"  >' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getProfession}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select></td>'
					+ '<td> '
					+ ' <label class="sepratedSpouseClass">' + convertDateFormate(j[i].separated_form_dt) + '</label>' + '</td>'
					
					+ '</tr>');
				$("#marriage_nationality" + x).val(j[i].nationality);
				 $('#marriage_nationalitylbl'+x).text($("#marriage_nationality"+x+" option:selected").text());

				 if(j[i].spouse_service == 0){
						$('#spo_service' + x).val("");
						$('#th_spouseproff').show();
						$('#ad_th_spouseproff').show();
						$('#th_service').hide();
						$('#ad_th_service').hide();
						$('#th_service').hide();
						$('#th_persno').hide();
						$('#ad_th_persno').hide();
						$('#th_otherser').hide();
						$('#ad_th_otherser').hide();
						
					}else{
						$('#spo_service' + x).val(j[i].spouse_service);
						$('#th_spouseproff').hide();
						$('#ad_th_spouseproff').hide();
						$('#th_service').show();
						$('#ad_th_service').show();
						$('#th_persno').show();
						$('#ad_th_persno').show();
						$('#th_otherser').show();
						$('#ad_th_otherser').show();
						$('#spouse_personal_no' + x).text(j[i].spouse_personal_no);
						$('#other_spouse_ser' + x).text(j[i].other_spouse_ser);
						$('#spouse_service' + x).text($("#spo_service" + x +" option:selected").text());
					}
					$("#spouse_profession" + x).val(j[i].spouse_profession);
					$('#spouse_professionlbl'+x).text($("#spouse_profession" + x +" option:selected").text());
				marriageNationChange(x);
				if(j[i].marital_status == "13") {
					$(".sepratedSpouseClass").show();
				}
				else{
					$(".sepratedSpouseClass").hide();
				}
				
			}
			$("#fill_marraige_div").show();
		}
		marr = v;
		marr_srno = v;
		$('#married_add' + marr).show();
	});
}
//divorce
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
	$("table#divorce_table").append('<tr id="tr_divorce' + divo + '">' + '<td class="divosr_no">' + divosr_no + '</td>' + '<td > <input type="text" id="spouse_name' + divo + '" name="spouse_name' + divo + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" ></td>'
		
		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date_of_birth' + divo + '" id="divorce_date_of_birth' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td >' + '<input type="text" id="divorce_place_of_birth' + divo + '" name="divorce_place_of_birth' + divo + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" >' + ' </td><td >'
		+ '<select name="divorce_nationality' + divo + '" id="divorce_nationality' + divo + '" class="form-control-sm form-control"  onchange="divorceNationChange('+divo+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
		+ '<td style="width: 13%;"><input type="text" id="divorce_othernationality' + divo + '" name="divorce_othernationality' + divo + '" '
		+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
		+ '	</td>'
		
		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_marriage_date' + divo + '" id="divorce_marriage_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + ' <td ><input type="text" id="divorce_spouse_adhar_number' + divo + '" name="divorce_spouse_adhar_number' + divo + '"  onkeypress="return isAadhar(this,event);" class="form-control autocomplete" maxlength="14" autocomplete="off" >' + ' </td>'
		
		+ '<td style="width: 10%;"> ' + '	<input type="text" id="divorce_spouse_pan_number' + divo + '" name="divorce_spouse_pan_number' + divo + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" > ' + '	</td> ' + '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date' + divo + '" id="divorce_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td style="display:none;"><input type="text" id="divo_ch_id' + divo + '" name="divo_ch_id' + divo + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "divorce_save' + divo + '" onclick="divorce_save_fn(' + divo + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "divorce_add' + divo + '" onclick="divorce_add_fn(' + divo + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "divorce_remove' + divo + '" onclick="divorce_remove_fn(' + divo + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
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
				alert("Data Deleted SuceessFully");
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
}

function divorce_save_fn(ps) {
	if($("input#spouse_name" + ps).val() == "") {
		alert("Please Enter Name");
		$("input#spouse_name" + ps).focus();
		return false;
	}
	if($("input#divorce_date_of_birth" + ps).val() == "") {
		alert("Please Enter Date of Birth");
		$("input#divorce_date_of_birth" + ps).focus();
		return false;
	}
	if($("input#divorce_place_of_birth" + ps).val() == "") {
		alert("Please Enter  Place of Birth");
		$("select#divorce_place_of_birth" + ps).focus();
		return false;
	}
	if($("select#divorce_nationality" + ps).val() == "0") {
		alert("Please Enter Nationality");
		$("select#divorce_nationality" + ps).focus();
		return false;
	}
	if($("input#divorce_marriage_date" + ps).val() == "") {
		alert("Please Enter Marriage Date");
		$("input#divorce_marriage_date" + ps).focus();
		return false;
	}
	if($("input#divorce_spouse_adhar_number" + ps).val() == "" || $("input#divorce_spouse_adhar_number" + ps).val().length < 14) {
		alert("Please Enter Aadhaar No");
		$("input#divorce_spouse_adhar_number" + ps).focus();
		return false;
	}
	if($("input#divorce_date" + ps).val() == "") {
		alert("Please Enter Date of Divorce/Widow/Widower");
		$("input#divorce_date" + ps).focus();
		return false;
	}
	var marital_status = $("select#marital_status option:selected").val();
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
	
	if(getformatedDate(marriage_date_of_birth) >= getformatedDate(divorce_marriage_date)) {
		alert("Marriage Date should be Greater than Date of Birth");
		$("input#divorce_marriage_date" + ps).focus();
		return false;
	}
	if(marital_status == '8') {
		var current_marriage_date = $("#marriage_date1").val();
		if(current_marriage_date == "") {
			alert("Please First Fill Up Current Marriage Details");
			return false;
		} else {
			alert(getformatedDate(current_marriage_date));
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
	}
	if(getformatedDate(divorce_marriage_date) >= getformatedDate(divorce_date)) {
		alert("Divorce/Widow/Widower Date should be Greater than Marriage Date");
		$("input#divorce_marriage_date" + ps).focus();
		return false;
	}
	if(calculate_age(document.getElementById('divorce_date_of_birth' + ps), document.getElementById('divorce_marriage_date' + ps)) && calculate_age(document.getElementById('dob_date'), document.getElementById('divorce_marriage_date' + ps))) {} else {
		return false;
	}
	$.post('family_divorce_action?' + key + "=" + value, {
		spouse_name: spouse_name,
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
			alert("Data Saved SuccesFully")
		} else alert(data);
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function getfamily_divoreceDetails() {
	var p_id = $('#census_id').val();
	var divorce = 1;
	var i = 0;
	$.post('getfamily_marriageData?' + key + "=" + value, {
		p_id: p_id,
		divorce: divorce,app_status:app_status
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
						
						+'<td><label class=" form-control-label" id="marital_eventlbl' + x + '"></label><select style="display:none" name="marital_event' + x + '" '
						+' id="marital_event' + x + '" '
						+' 	class="form-control-sm form-control" >'
						+'		<option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getMarital_statusList}" varStatus="num">'
						+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:forEach>'
						+'</select></td>'
						
					+ '<td > <label>' + j[i].maiden_name + '</label></td>'
					
					+ '<td> ' + ' <label>' + convertDateFormate(j[i].date_of_birth) + '</label>' + '</td>' + '<td>' + '<label>' + j[i].place_of_birth + '</label>' 
					+ ' </td><td>' 
					+ '<label class=" form-control-label" id="divorce_nationalitylbl' + x + '"></label><select name="divorce_nationality' + x + '" id="divorce_nationality' + x + '"   onchange="divorceNationChange('+x+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
					+ '<td><label>'+other_nation+'</label>'				
					+ '	</td>'
					+ '<td> ' + ' <label>' + convertDateFormate(j[i].marriage_date) + '</label>' + '</td>' + ' <td ><label  id="divorce_spouse_adhar_number' + x + '" name="divorce_spouse_adhar_number' + x + '" >' + j[i].adhar_number + '</label>'
					+ '<td> ' + '	<label>' + pan_card + '</label> ' 
					+ '	</td> ' + '<td> '
					+ ' <label>' + ParseDateColumncommission(j[i].divorce_date) + '</label>' + '</td>' 
					+ '</td></tr>');
				$("#divorce_nationality" + x).val(j[i].nationality);
				$('#divorce_nationalitylbl'+x).text($("#divorce_nationality"+x+" option:selected").text());

				$("#marital_event" + x).val(j[i].marital_status);
				$('#marital_eventlbl'+x).text($("#marital_event"+x+" option:selected").text());
				
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
	
	if($("input#father_name").val() == "") {
		alert("Please Enter Father's Name ");
		$("input#father_name").focus();
		return false;
	}
	if($("input#father_dob").val() == "DD/MM/YYYY") {
		alert("Please Enter Father DOB");
		$("input#father_dob").focus();
		return false;
	}
	if($("input#father_dob").val() == "") {
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
	if($("input#father_place").val() == "") {
		alert("Please Enter Father's Place of Birth ");
		$("input#father_place").focus();
		return false;
	}
	if($("input#father_adhar_number").val() == "" || $("input#father_adhar_number").val().length < 14) {
		alert("Please Enter Father Adhar  No");
		$("input#father_adhar_number").focus();
		return false;
	}
	var fservice_radio = $("input[name='father_proff_radio']:checked").val();
	if(fservice_radio == "yes") {
		if($("#father_service").val()=="0"){
			alert("please Select Father Service");
			$("#father_service").focus();
			return false;
		}
		if($("#father_service").val()=="4"){
			if($("#father_other").val()==''){
				alert("please Enter Other Service");
				$("#father_other").focus();
				return false;
			}
		}
		if($("#father_service").val()=="1"){
			if($("#father_personal_no").val()==''){
				alert("please Enter Father Personal No");
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
		var f_proff = $("#father_profession option:selected").text();
		if(f_proff == other){
			if($("#father_proffother").val()==""){
				alert("Please Enter Father's Other Profession ");
				$("#father_proffother").focus();
				return false;
			}
		}
		
	}
	
	
	if($("input#mother_name").val() == "") {
		alert("Please Enter Mother's Name ");
		$("input#mother_name").focus();
		return false;
	}
	if($("input#mother_dob").val() == "DD/MM/YYYY") {
		alert("Please Enter Mother's DOB");
		$("input#mother_dob").focus();
		return false;
	}
	if($("input#mother_dob").val() == "") {
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
	if($("input#mother_place").val() == "") {
		alert("Please Enter Mother's Place of Birth ");
		$("input#mother_place").focus();
		return false;
	}
	
	if($("input#mother_adhar_number").val() == "" || $("input#mother_adhar_number").val().length < 14) {
		alert("Please Enter Mother Adhar No");
		$("input#mother_adhar_number").focus();
		return false;
	}
	
	
	var mservice_radio = $("input[name='mother_proff_radio']:checked").val();
	if(mservice_radio == "yes") {
		if($("#mother_service").val()=="0"){
			alert("please Select Mother Service");
			$("#mother_service").focus();
			return false;
		}
		if($("#mother_service").val()=="4"){
			if($("#mother_other").val()==""){
				alert("please Enter Other Service");
				$("#mother_other").focus();
				return false;
			}
		}
		if($("#mother_service").val()=="1"){
			if($("#mother_personal_no").val()==""){
				alert("please Enter Mother Personal No");
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
			if($("#mother_proffother").val()==""){
				alert("Please Enter Mother's Other Profession ");
				$("#mother_proffother").focus();
				return false;
			}
		}
	}
	
	
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
	
	var f_otherproff = $("#father_proffother").val();
	var m_otherproff = $("#mother_proffother").val();
	
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
		f_otherproff:f_otherproff,
		m_otherproff:m_otherproff,
		m_proff:m_proff,
		f_proff:f_proff
		
	}, function(j) {
		if(j == 1) {
			alert("Data Saved Succesfully");
		} else {
			alert("Data Not Saved");
		}
	});
}
/////////////////////////////////////////END FAMILY///////////////////////////////////
//language


function get_language_details() {
	var p_id = $('#census_id').val();
	var i = 0;
	$.post('getlanguagedetailsData?' + key + "=" + value, {
		p_id: p_id,app_status:app_status
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
							+ '<td class="lang_srno">' + xl + '</td>' 
							+'<td> <label id="language'+xl+'"></label>'
							+'<select  id="language_val'+xl+'" name="language'+xl+'" value="'+j[i].language+'"  class="form-control"  style="display:none;">'
							  +' <option value="0">--Select--</option>'
							   +'<c:forEach var="item" items="${getIndianLanguageList}" varStatus="num">'
								+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
								+'</c:forEach>'
								+'</select>'
								+'</td>'
								+'<td> <label id="other_language'+xl+'"></label> '
							+'<td> <label id="lang_std'+xl+'"></label> '
							+'<select name="lang_std'+xl+'" id="lang_std_val'+xl+'" class="form-control" style="display:none;">'
							+' <option value="0">--Select--</option>'
							+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
							+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
							+'	</c:forEach>'
							+'</select> </td> '
							+'<td> <label id="other_lang_std'+xl+'"></label> '
							
							+'</tr>');
							 $('#lang_std_val'+xl).val(j[i].lang_std );
							 $('#lang_std'+xl).text($( "#lang_std_val"+xl+" option:selected" ).text());
							 $('#language_val'+xl).val(j[i].language );
							 $('#language'+xl).text($( "#language_val"+xl+" option:selected" ).text());
							 $('#other_language'+xl).text(j[i].other_language);
							  $('#other_lang_std'+xl).text(j[i].other_lang_std);
							
				} else {
					xf = xf + 1;
					if(xf == 1) {
						$('#flangtbody').empty();
					}
					$("table#foregin_language_table").append('<tr id="tr_flang' + xf + '">' 
							+ '<td class="flang_srno">' + xf + '</td>' 
							+'<td> <label id="flanguage'+xf+'"></label>'
							+'<select  id="flanguage_val'+xf+'" name="flanguage'+xf+'" class="form-control" style="display:none;" >'
							+'   <option value="0">--Select--</option>'
						    +'	<c:forEach var="item" items="${getForeignLanguageList}" varStatus="num">'
							+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
							+'	</c:forEach>'
							+'	</select></td>'
							+'<td> <label id="f_other_language'+xf+'"></label></td>'
							+'<td> <label id="flang_std'+xf+'"></label>'
							+'<select name="flang_std'+xf+'" id="flang_std_val'+xf+'" class="form-control" style="display:none;">'
							+' <option value="0">--Select--</option>'
							+'	<c:forEach var="item" items="${getLanguageSTDList}" varStatus="num">'
							+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' 
							+'	</c:forEach>'
							+'</select> </td> '
							+'<td> <label id="f_other_lang_std'+xf+'"></label></td>'
							+'<td> <label id="lang_prof'+xf+'"></label>'
							+'<select name="lang_prof'+xf+'" id="lang_prof_val'+xf+'" class="form-control"  style="display:none;" >'
							+' <option value="0">--Select--</option>'
							+'	<c:forEach var="item" items="${getLanguagePROOFList}" varStatus="num">'
							+'	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
							+'	</c:forEach>'
							+' </select></td> '
							+'<td> <label id="f_other_prof'+xf+'"></label></td>'
							+'<td> <label id="exam_pass'+xf+'"></label></td>'		
							+'<td style="display:none;"><input type="text" id="flang_ch_id'+xf+'" name="flang_ch_id'+xf+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'
							+'</tr>');
					$('#flang_std_val'+xf).val(j[i].lang_std );
					 $('#flang_std'+xf).text($( "#flang_std_val"+xf+" option:selected" ).text());
					 
					 $('#lang_prof_val'+xf).val(j[i].f_lang_prof );  
					 $('#lang_prof'+xf).text($( "#lang_prof_val"+xf+" option:selected" ).text());
					 
					 $('#flanguage_val'+xf).val(j[i].foreign_language );
					 $('#flanguage'+xf).text($( "#flanguage_val"+xf+" option:selected" ).text());
					 
					 $('#exam_pass'+xf).text(j[i].f_exam_pass );
					  $('#f_other_language'+xf).text(j[i].f_other_language);
					  $('#f_other_lang_std'+xf).text(j[i].f_other_lang_std);
					  $('#f_other_prof'+xf).text(j[i].f_other_prof);
				}
			}
			if(xl != 0) {
				lang = xl;
				lang_srno = xl;
				
			}
			if(xf != 0) flang = xf;
			flang_srno = xf;
		
		}
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
	$("table#qualification_table").append('<tr id="tr_qualification' + qua + '">' + '<td class="qua_srno">' + qua_srno + '</td>' + '<td style="width: 10%;"><select name="type' + qua + '" id="type' + qua + '" class="form-control-sm form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getQualificationTypeList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select>' + '<td style="width: 10%;"><input type="text" id="examination_pass' + qua + '" name="examination_pass' + qua + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"><input type="text" id="passing_year' + qua + '" name="passing_year' + qua + '" maxlength="4" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"><input type="text" id="div_class' + qua + '" name="div_class' + qua + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"><input type="text" id="subject' + qua + '" name="subject' + qua + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"><input type="text" id="institute' + qua + '" name="institute' + qua + '" class="form-control autocomplete" autocomplete="off" ></td>'
		
		+ '<td style="display:none;"><input type="text" id="qualification_ch_id' + qua + '" name="qualification_ch_id' + qua + '"  value="0" class="form-control autocomplete" autocomplete="off" ></td>' 
		+ '</tr>');
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
				alert("Data Deleted SuceessFully");
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
		q_id: q_id,app_status:app_status
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
			
				$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px;text-align: center ;">' + qu + '</td> ' + '<td style="font-size: 15px;text-align: center">' + j[i].label + '</td>	' + '<td style="font-size: 15px;text-align: center">' + exampass + '</td> ' 
				 + '<td style="font-size: 15px;text-align: center">' + deg_name + '</td>' +   '<td style="font-size: 15px;text-align: center">' + specialization + '</td>' +
				'<td style="font-size: 15px;text-align: center">' + j[i].passing_year + '</td>' + '<td style="font-size: 15px;text-align: center">' + subjects + '</td>' + '<td style="font-size: 15px;text-align: center">' 
				+ divclass + '</td>'  + '<td style="font-size: 15px;text-align: center">' + j[i].institute + '</td>' 
				 + '</tr>');
			}
		} else {
			$("table#qualificationtable").append('<tr>' + '<td style="font-size: 15px; text-align: center; color: red;" colspan="9">Data Not Available</td>' + '</tr>');
		}
	});
}
///////////////Start address details//////////////////////
function copy_address() {
	if($("#check_address").prop('checked') == true) {
		$("#pers_addr_village").val($("#per_home_addr_village").val());
		$("#pers_addr_tehsil").val($("#per_home_addr_tehsil").val());
		$("#pers_addr_district").val($("#per_home_addr_district").val());
		$("#pers_addr_state").val($("#per_home_addr_state").val());
		$("#pers_addr_pin").val($("#per_home_addr_pin").val());
		$("#pers_addr_rail").val($("#per_home_addr_rail").val());
	} else {
		$("#pers_addr_village").val("");
		$("#pers_addr_tehsil").val("");
		$("#pers_addr_district").val("");
		$("#pers_addr_state").val("");
		$("#pers_addr_pin").val("");
		$("#pers_addr_rail").val("");
	}
}

function save_address_details() {
	if($("select#org_domicile_Country").val() == "0") {
		alert("Please Select Original Domicile Country");
		$("select#org_domicile_Country").focus();
		return false;
	}
	if($("select#org_domicile_state").val() == "0") {
		alert("Please Select Original Domicile State");
		$("select#org_domicile_state").focus();
		return false;
	}
	if($("select#org_domicile_district").val() == "0") {
		alert("Please Select Original Domicile District");
		$("select#org_domicile_district").focus();
		return false;
	}
	if($("select#org_domicile_tehsil").val() == "0") {
		alert("Please Select Original Domicile Tehsil");
		$("select#org_domicile_tehsil").focus();
		return false;
	}
	if($("select#pre_domicile_Country").val() == "0") {
		alert("Please Select Present Domicile Country");
		$("select#pre_domicile_Country").focus();
		return false;
	}
	if($("select#pre_domicile_state").val() == "0") {
		alert("Please Select Present Domicile State");
		$("select#pre_domicile_state").focus();
		return false;
	}
	if($("select#pre_domicile_district").val() == "0") {
		alert("Please Select Present  Domicile District");
		$("select#pre_domicile_district").focus();
		return false;
	}
	if($("select#pre_domicile_tehsil").val() == "0") {
		alert("Please Select Present Domicile Tehsil");
		$("select#pre_domicile_tehsil").focus();
		return false;
	}
	if($("select#per_home_addr_Country").val() == "0") {
		alert("Please Select Permanent Country");
		$("select#per_home_addr_Country").focus();
		return false;
	}
	if($("select#per_home_addr_state").val() == "0") {
		alert("Please Select Permanent state");
		$("select#per_home_addr_state").focus();
		return false;
	}
	if($("select#per_home_addr_district").val() == "0") {
		alert("Please Select Permanent District");
		$("select#per_home_addr_district").focus();
		return false;
	}
	if($("select#per_home_addr_tehsil").val() == "0") {
		alert("Please Enter Permanent Tehsil");
		$("select#per_home_addr_tehsil").focus();
		return false;
	}
	if($("input#per_home_addr_village").val() == "") {
		alert("Please Select Permanent Village/Town/City");
		$("select#per_home_addr_village").focus();
		return false;
	}
	if($("input#per_home_addr_pin").val() == "" || $("input#per_home_addr_pin").val().length < 6) {
		alert("Please Enter Permanent valid Pin");
		$("input#per_home_addr_pin").focus();
		return false;
	}
	if($("input#per_home_addr_rail").val() == "") {
		alert("Please Enter Permanent Nearest Railway Station");
		$("input#per_home_addr_rail").focus();
		return false;
	}
	var n = $('input:radio[name=nok_rural_urban]:checked').val();
	if(n == undefined) {
		alert("Please select Is  Rural /Urban/Semi Urban");
		return false;
	}
	var a = $('input:radio[name=permanent_border_area]:checked').val();
	if(a == undefined) {
		alert("Please select Border Area");
		return false;
	}
	if($("select#pers_addr_Country").val() == "0") {
		alert("Please Select Present Country");
		$("select#pers_addr_Country").focus();
		return false;
	}
	if($("select#pers_addr_state").val() == "0") {
		alert("Please Select Present State");
		$("select#pers_addr_state").focus();
		return false;
	}
	if($("select#pers_addr_district").val() == "0") {
		alert("Please Select Present District");
		$("select#pers_addr_district").focus();
		return false;
	}
	if($("select#pers_addr_tehsil").val() == "0") {
		alert("Please Enter Present Tehsil");
		$("select#pers_addr_tehsil").focus();
		return false;
	}
	if($("input#pers_addr_village").val() == "") {
		alert("Please Select Present Village/Town/City");
		$("select#pers_addr_village").focus();
		return false;
	}
	if($("input#pers_addr_pin").val() == "" || $("input#pers_addr_pin").val().length < 6) {
		alert("Please Enter Present valid Pin");
		$("input#pers_addr_pin").focus();
		return false;
	}
	if($("input#pers_addr_rail").val() == "") {
		alert("Please Enter Present Nearest Railway Station");
		$("input#pers_addr_rail").focus();
		return false;
	}
	if($("#check_spouse_address").is(':checked')) {
		if($("select#spouse_addr_Country").val() == "0") {
			alert("Please Select SF ACCN Country");
			$("select#spouse_addr_Country").focus();
			return false;
		}
		if($("select#spouse_addr_state").val() == "0") {
			alert("Please Select SF ACCN State");
			$("select#spouse_addr_state").focus();
			return false;
		}
		if($("select#spouse_addr_district").val() == "0") {
			alert("Please Select SF ACCN District");
			$("select#spouse_addr_district").focus();
			return false;
		}
		if($("select#spouse_addr_tehsil").val() == "0") {
			alert("Please Enter SF ACCN Tehsil");
			$("select#spouse_addr_tehsil").focus();
			return false;
		}
		if($("input#spouse_addr_village").val() == "") {
			alert("Please Select SF ACCN Village/Town/City");
			$("select#spouse_addr_village").focus();
			return false;
		}
		if($("input#spouse_addr_pin").val() == "" || $("input#spouse_addr_pin").val().length < 6) {
			alert("Please Enter SF ACCN valid Pin");
			$("input#spouse_addr_pin").focus();
			return false;
		}
		if($("input#spouse_addr_rail").val() == "") {
			alert("Please Enter SF ACCN Nearest Railway Station");
			$("input#spouse_addr_rail").focus();
			return false;
		}
	}
	if($("input#nok_name").val() == "") {
		alert("Please Enter NOK Name");
		$("input#nok_name").focus();
		return false;
	}
	if($("select#nok_relation").val() == "0") {
		alert("Please Select Nok Relation");
		$("select#nok_relation").focus();
		return false;
	}
	if($("select#nok_country").val() == "0") {
		alert("Please Select  Nok Country");
		$("select#nok_country").focus();
		return false;
	}
	if($("select#nok_state").val() == "0") {
		alert("Please Select  Nok State");
		$("select#nok_state").focus();
		return false;
	}
	if($("select#nok_district").val() == "0") {
		alert("Please Select Nok District");
		$("select#nok_district").focus();
		return false;
	}
	if($("select#nok_tehsil").val() == "0") {
		alert("Please Enter  Nok Tehsil");
		$("select#nok_tehsil").focus();
		return false;
	}
	if($("input#nok_village").val() == "") {
		alert("Please Select  Nok Village/Town/City");
		$("select#nok_village").focus();
		return false;
	}
	if($("input#nok_pin").val() == "" || $("input#nok_pin").val().length < 6) {
		alert("Please Enter valid  Nok Pin");
		$("input#nok_pin").focus();
		return false;
	}
	if($("input#nok_near_railway_station").val() == "") {
		alert("Please Enter  Nok Nearest Railway Station");
		$("input#nok_near_railway_station").focus();
		return false;
	}
	if($("input#nok_mobile_no").val() == "" || $("input#nok_mobile_no").val().length < 10) {
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
	formvalues += "&p_id=" + p_id + "&addr_ch_id=" + addr_ch_id + "&nok_ch_id=" + nok_ch_id + "&comm_id=" + comm_id + "&marital_status=" + marital_status;
	$.post('address_details_action?' + key + "=" + value, formvalues, function(data) {
		if(data.error == null) {
			$('#addr_ch_id').val(data.addr_ch_id);
			$("#nok_ch_id").val(data.nok_ch_id);
			alert("Data Save/Updated Successfully");
		} else {
			alert(data.error)
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}

function get_address_details() {
	var p_id1 = $("#census_id").val();
	$.post('address_details_GetData_census?' + key + "=" + value, {
		p_id1: p_id1,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$.ajaxSetup({
				async: false
			});
			$("#pre_domicile_Country").val(j[0].pre_country);
			$('#pre_domicile_Countrylbl').text($("#pre_domicile_Country option:selected").text());
			fn_pre_domicile_Country();
			$("#pre_domicile_state").val(j[0].pre_state);
			$('#pre_domicile_statelbl').text($("#pre_domicile_state option:selected").text());
			fn_pre_domicile_state();
			$("#pre_domicile_district").val(j[0].pre_district);
			$('#pre_domicile_districtlbl').text($("#pre_domicile_district option:selected").text());
			fn_pre_domicile_district();
			$("#pre_domicile_tehsil").val(j[0].pre_tesil);
			$('#pre_domicile_tehsillbl').text($("#pre_domicile_tehsil option:selected").text());
			fn_pre_domicile_tehsil();
			$("#per_home_addr_Country").val(j[0].permanent_country);
			$('#per_home_addr_Countrylbl').text($("#per_home_addr_Country option:selected").text());
			fn_per_home_addr_Country();
			$("#per_home_addr_state").val(j[0].permanent_state);
			$('#per_home_addr_statelbl').text($("#per_home_addr_state option:selected").text());
			fn_per_home_addr_state();
			$("#per_home_addr_district").val(j[0].permanent_district);
			$('#per_home_addr_districtlbl').text($("#per_home_addr_district option:selected").text());
			fn_per_home_addr_district();
			$("#per_home_addr_tehsil").val(j[0].permanent_tehsil);
			$('#per_home_addr_tehsillbl').text($("#per_home_addr_tehsil option:selected").text());
			fn_per_home_addr_tehsil();
			var br = j[0].permanent_border_area;
			if(br == "yes") {
				$("#permanent_border_area").text("YES");
			}
			if(br == "no") {
				$("#permanent_border_area").text("NO");
			}
			$("#per_home_addr_village").text(j[0].permanent_village);
			$("#pers_addr_Country").val(j[0].present_country);
			$('#pers_addr_Countrylbl').text($("#pers_addr_Country option:selected").text());
			fn_pers_addr_Country();
			$("#pers_addr_state").val(j[0].present_state);
			$('#pers_addr_statelbl').text($("#pers_addr_state option:selected").text());
			fn_pers_addr_state();
			$("#pers_addr_district").val(j[0].present_district);
			$('#pers_addr_districtlbl').text($("#pers_addr_district option:selected").text());
			fn_pers_addr_district();
			$("#pers_addr_tehsil").val(j[0].present_tehsil);
			$('#pers_addr_tehsillbl').text($("#pers_addr_tehsil option:selected").text());
			fn_pers_addr_tehsil();
			$("#pers_addr_village").text(j[0].present_village);
			$("#per_home_addr_pin").text(j[0].permanent_pin_code);
			$("#per_home_addr_rail").text(j[0].permanent_near_railway_station);
			$("#pers_addr_pin").text(j[0].present_pin_code);
			$("#pers_addr_rail").text(j[0].present_near_railway_station);
			
			
			if(j[0].present_rural_urban_semi == "rural") {
				$("#pers_addr_rural_urban").text("Rural");
			}
			if(j[0].present_rural_urban_semi == "urban") {
				$("#pers_addr_rural_urban").text("Urban");
			}
			if(j[0].present_rural_urban_semi == "semi_urban") {
				$("#pers_addr_rural_urban").text("Semi Urban");
			}
			if(j[0].permanent_rural_urban_semi == "rural") {
				$("#per_home_addr_rural_urban").text("Rural");
			}
			if(j[0].permanent_rural_urban_semi == "urban") {
				$("#per_home_addr_rural_urban").text("Urban");
			}
			if(j[0].permanent_rural_urban_semi == "semi_urban") {
				$("#per_home_addr_rural_urban").text("Semi Urban");
			}
			
			$("#pre_domicile_Country_other").text(j[0].pre_country_other);
			$("#pre_domicile_state_other").text(j[0].pre_domicile_state_other);
			$("#pre_domicile_district_other").text(j[0].pre_domicile_district_other);
			$("#pre_domicile_tehsil_other").text(j[0].pre_domicile_tesil_other);
			$("#per_home_addr_Country_other").text(j[0].per_home_addr_country_other);
			$("#per_home_addr_state_other").text(j[0].per_home_addr_state_other);
			$("#per_home_addr_district_other").text(j[0].per_home_addr_district_other);
			$("#per_home_addr_tehsil_other").text(j[0].per_home_addr_tehsil_other);
			
			$("#pers_addr_Country_other").text(j[0].pers_addr_country_other);
			$("#pers_addr_state_other").text(j[0].pers_addr_state_other);
			$("#pers_addr_district_other").text(j[0].pers_addr_district_other);
			$("#pers_addr_tehsil_other").text(j[0].pers_addr_tehsil_other);
			
			if($('#marital_status').val() == '8') {
				if(j[0].spouse_country != null && j[0].spouse_country != 0) {
					$("#spouse_addr_Country").val(j[0].spouse_country);
					$('#spouse_addr_Countrylbl').text($("#spouse_addr_Country option:selected").text());
					fn_spouse_addr_Country();
					$("#spouse_addr_state").val(j[0].spouse_state);
					$('#spouse_addr_statelbl').text($("#spouse_addr_state option:selected").text());
					fn_spouse_addr_state();
					$("#spouse_addr_district").val(j[0].spouse_district);
					$('#spouse_addr_districtlbl').text($("#spouse_addr_district option:selected").text());
					fn_spouse_addr_district();
					$("#spouse_addr_tehsil").val(j[0].spouse_tehsil);
					$('#spouse_addr_tehsillbl').text($("#spouse_addr_tehsil option:selected").text());
					fn_spouse_addr_tehsil();
					$("#spouse_addr_pin").text(j[0].spouse_pin_code);
					$("#spouse_addr_rail").text(j[0].spouse_near_railway_station);
					$("#Stn_HQ_sus_no").text(j[0].stn_hq_sus_no);
					var sus_no = j[0].stn_hq_sus_no;			      	
					 $.post("getTargetUnitNameListStnHQ?"+key+"="+value, {
							 sus_no:sus_no
			         }, function(j) {
			                
			         }).done(function(j) {
			        	 var length = j.length-1;
			             var enc = j[length].substring(0,16);
			             $("#Stn_HQ_unit_name").text(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
		        	 });
					$("#spouse_addr_village").text(j[0].spouse_village);
					$("#spouse_addr_tehsil").text(j[0].spouse_tehsil);
					
					$("#spouse_addressMaindiv").show();
					$("#spouse_addressInnerdiv").show();
					$("#spouse_addr_country_other").text(j[0].spouse_addr_country_other);
					$("#spouse_addr_state_other").text(j[0].spouse_addr_state_other);
					$("#spouse_addr_district_other").text(j[0].spouse_addr_district_other);
					$("#spouse_addr_tehsil_other").text(j[0].spouse_addr_tehsil_other);
					
					if(j[0].spouse_rural_urban_semi == "rural") {
						$("#spouse_addr_rural_urban").text("Rural");
					}
					if(j[0].spouse_rural_urban_semi == "urban") {
						$("#spouse_addr_rural_urban").text("Urban");
					}
					if(j[0].spouse_rural_urban_semi == "semi_urban") {
						$("#spouse_addr_rural_urban").text("Semi Urban");
					}
				}
				else{
					$("#spouse_addressMaindiv").hide();
					$("#spouse_addressInnerdiv").hide();
				}
			}
			
			$("#addr_ch_id").val(j[0].id);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
	$.post('Nok_details_GetData?' + key + "=" + value, {
		p_id1: p_id1,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			
			$("#nok_village").text(j[0].nok_village);
			$("#nok_country").val(j[0].nok_country);
			$('#nok_countrylbl').text($("#nok_country option:selected").text());
			fn_nok_Country();
			$("#nok_state").val(j[0].nok_state);
			$('#nok_statelbl').text($("#nok_state option:selected").text());
			fn_nok_state();
			$("#nok_district").val(j[0].nok_district);
			$('#nok_districtlbl').text($("#nok_district option:selected").text());
			fn_nok_district();
			
			$("#nok_tehsil").val(j[0].nok_tehsil);
			$('#nok_tehsillbl').text($("#nok_tehsil option:selected").text());
			$("#nok_pin").text(j[0].nok_pin);
			$("#nok_near_railway_station").text(j[0].nok_near_railway_station);
			$("#nok_name").text(j[0].nok_name);
			$("#nok_relation").val(j[0].nok_relation);
			$('#nok_relationlbl').text($("#nok_relation option:selected").text());
			$("#nok_relation_other").text(j[0].relation_other);
			$("#nok_mobile_no").text(j[0].nok_mobile_no);
			$("#nok_ch_id").val(j[0].id);
			var nok = j[0].nok_rural_urban_semi;
			if(nok == "rural") {
				$("#nok_rural_urban").text("Rural");
			}
			if(nok == "urban") {
				$("#nok_rural_urban").text("Urban");
			}
			if(nok == "semi_urban") {
				$("#nok_rural_urban").text("Semi Urban");
			}
			
			 var text6 = $("#nok_country option:selected").text();
				if(text6 == "OTHERS"){
					$("#ctry_other").text(j[0].ctry_other);
					$("#nok_other_id").show();
				}
				else{
					$("#nok_other_id").hide();
				}
				
				var text7 = $("#nok_state option:selected").text();
				if(text7 == "OTHERS"){
					$('#st_other').text(j[0].st_other);
					$("#nok_other_st").show();
				}
				else{
					$("#nok_other_st").hide();
				}
				var text8 = $("#nok_district option:selected").text();
				if(text8 == "OTHERS"){
					$("#dist_other").text(j[0].dist_other);
					$("#nok_other_dist").show();
				}
				else{
					$("#nok_other_dist").hide();
				}
				var text9 = $("#nok_tehsil option:selected").text();
				if(text9 == "OTHERS"){
					$("#nok_tehsil_other").text(j[0].tehsil_other);
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
	
}

function Submit_Approval() {
	var p_id = $("#census_id").val();
	$.post('Submit_Approval_Data?' + key + "=" + value, {
		p_id: p_id
	}, function(j) {
		if(j == 1) {
			alert("Submit Successfully");
			
			var href = $('#censussearshUrllink').attr('href');
			window.location.href = href;
		} else {
			alert(j);
		}
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
}










function getmarital_status() {
	var a = $("select#marital_status option:selected").val();
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
}


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
		
	
	} else {
		$("#pers_addr_village").val("");
		$("#pers_addr_tehsil").val("0");
		$("#pers_addr_district").val("0");
		$("#pers_addr_state").val("0");
		$("#pers_addr_pin").val("");
		$("#pers_addr_rail").val("");
		$("#pers_addr_Country").val("0");
		
		
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
	} else {
		
		//for nok
		$("#nok_village").val("");
		$("#nok_tehsil").val("0");
		$("#nok_district").val("0");
		$("#nok_state").val("0");
		$("#nok_pin").val("");
		$("#nok_near_railway_station").val("");
		$("#nok_country").val("0");
	}
}
////start district 
function fn_per_home_addr_state() {
	
	 var text = $("#per_home_addr_state option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_state_other_id").show();
		}
		else{
			$("#per_home_addr_state_other_id").hide();
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
		
		if(text == "OTHERS"){
			$("#pers_addr_state_other_id").show();
		}
		else{
			$("#pers_addr_state_other_id").hide();
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
		
		if(text == "OTHERS"){
			$("#spouse_addr_state_hid").show();
		}
		else{
			$("#spouse_addr_state_hid").hide();
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
	if(text == "OTHERS"){
		$("#nok_other_st").show();
	}
	else{
		$("#nok_other_st").hide();
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
	
	if(text == "OTHERS"){
		$("#org_domicile_state_other_id").show();
	}
	else{
		$("#org_domicile_state_other_id").hide();
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
		
		if(text == "OTHERS"){
			$("#pre_domicile_state_other_id").show();
		}
		else{
			$("#pre_domicile_state_other_id").hide();
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
////End district 
////start state 
function fn_per_home_addr_Country() {
	
	 var text = $("#per_home_addr_Country option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_Country_other_id").show();
		}
		else{
			$("#per_home_addr_Country_other_id").hide();
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

function fn_pers_addr_Country() {
	 var text = $("#pers_addr_Country option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_Country_other_id").show();
		}
		else{
			$("#pers_addr_Country_other_id").hide();
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
		fn_pers_addr_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_spouse_addr_Country() {
	var text = $("#spouse_addr_Country option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_Country_hid").show();
		}
		else{
			$("#spouse_addr_Country_hid").hide();
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
			fn_spouse_addr_district();
			fn_spouse_addr_tehsil();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}

function fn_nok_Country() {
	
	var text = $("#nok_country option:selected").text();

	if(text == "OTHERS"){
		$("#nok_other_id").show();
	}
	else{
		$("#nok_other_id").hide();
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
		fn_nok_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_org_domicile_Country() {
	
	var text = $("#org_domicile_Country option:selected").text();
	
	if(text == "OTHERS"){
		$("#org_domicile_country_other_id").show();
	}
	else{
		$("#org_domicile_country_other_id").hide();
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

function fn_pre_domicile_Country() {
	 var text = $("#pre_domicile_Country option:selected").text();
		
		if(text == "OTHERS"){
			$("#pre_domicile_Country_other_id").show();
		}
		else{
			$("#pre_domicile_Country_other_id").hide();
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
		fn_pre_domicile_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}
////End state
//start tehsil
function fn_per_home_addr_district() {
	
	 var text = $("#per_home_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_district_other_id").show();
		}
		else{
			$("#per_home_addr_district_other_id").hide();
		}
	
	var Dist_id = $('select#per_home_addr_district').val();
// 	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
// 		Dist_id: Dist_id
// 	}).done(function(j) {
// 		var options = '<option   value="0">' + "--Select--" + '</option>';
// 		for(var i = 0; i < j.length; i++) {
// 			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
// 		}
// 		$("select#per_home_addr_tehsil").html(options);
// 	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_per_home_addr_tehsil() {
	 var text = $("#per_home_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#per_home_addr_tehsil_other_id").show();
		}
		else{
			$("#per_home_addr_tehsil_other_id").hide();
		}
	
}

function fn_pers_addr_district() {
	 var text = $("#pers_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_district_other_id").show();
		}
		else{
			$("#pers_addr_district_other_id").hide();
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
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pers_addr_tehsil() {
	 var text = $("#pers_addr_tehsil option:selected").text();
		
		if(text == "OTHERS"){
			$("#pers_addr_tehsil_other_id").show();
		}
		else{
			$("#pers_addr_tehsil_other_id").hide();
		}
}

function fn_spouse_addr_district() {
	var text = $("#spouse_addr_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#spouse_addr_district_hid").show();
		}
		else{
			$("#spouse_addr_district_hid").hide();
		}
		var Dist_id = $('select#spouse_addr_district').val();
		fn_spouse_addr_tehsil();
// 		$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
// 			Dist_id: Dist_id
// 		}).done(function(j) {
// 			var options = '<option   value="0">' + "--Select--" + '</option>';
// 			for(var i = 0; i < j.length; i++) {
// 				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
// 			}
// 			$("select#spouse_addr_tehsil").html(options);
// 			fn_spouse_addr_tehsil();
// 		}).fail(function(xhr, textStatus, errorThrown) {});
	}
	function fn_spouse_addr_tehsil() {
		var text = $("#spouse_addr_tehsil option:selected").text();
			
			if(text == "OTHERS"){
				$("#spouse_addr_tehsil_hid").show();
			}
			else{
				$("#spouse_addr_tehsil_hid").hide();
			}
	}


function fn_nok_district() {
	
	
	var text = $("#nok_district option:selected").text();
	
	if(text == "OTHERS"){
		$("#nok_other_dist").show();
	}
	else{
		$("#nok_other_dist").hide();
	}
	var Dist_id = $('select#nok_district').val();
	//26-01-1994
	fn_nok_tehsil();
// 	$.post("getTehsilListFormDistrict1?" + key + "=" + value, {
// 		Dist_id: Dist_id
// 	}).done(function(j) {
// 		var options = '<option   value="0">' + "--Select--" + '</option>';
// 		for(var i = 0; i < j.length; i++) {
// 			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
// 		}
// 		$("select#nok_tehsil").html(options);
// 		fn_nok_tehsil();
// 	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_nok_tehsil() {
	
	var text = $("#nok_tehsil option:selected").text();
	
	if(text == "OTHERS"){
		$("#nok_other_te").show();
	}
	else{
		$("#nok_other_te").hide();
	}
}	

function fn_pre_domicile_district() {
	
	 var text = $("#pre_domicile_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#pre_domicile_district_other_id").show();
		}
		else{
			$("#pre_domicile_district_other_id").hide();
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
	
	if(text == "OTHERS"){
		$("#pre_domicile_tehsil_other_id").show();
	}
	else{
		$("#pre_domicile_tehsil_other_id").hide();
	}
}

function fn_org_domicile_district() {
	
	 var text = $("#org_domicile_district option:selected").text();
		
		if(text == "OTHERS"){
			$("#org_domicile_district_other_id").show();
		}
		else{
			$("#org_domicile_district_other_id").hide();
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
		
		if(text == "OTHERS"){
			$("#org_domicile_tehsil_other_id").show();
		}
		else{
			$("#org_domicile_tehsil_other_id").hide();
		}
}


//PERSONAL PAGE
function fn_country_birth() {
var text = $("#country_birth option:selected").text();
	
	if(text == "OTHERS"){
		$("#country_birth_other_hid").show();
	}
	else{
		$("#country_birth_other_hid").hide();
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
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_state_birth() {
var text = $("#state_birth option:selected").text();
	
	if(text == "OTHERS"){
		$("#state_birth_other_hid").show();
	}
	else{
		$("#state_birth_other_hid").hide();
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
		
		if(text == "OTHERS"){
			$("#district_birth_other_hid").show();
		}
		else{
			$("#district_birth_other_hid").hide();
		}
 		 
 	}
 function fn_nationality() {
	 var text = $("#nationality option:selected").text();
		
		if(text == "OTHERS"){
			$("#nationality_other_hid").show();
		}
		else{
			$("#nationality_other_hid").hide();
		}
 		 
 	}
 function fn_mother_tongue() {
	 var text = $("#mother_tongue option:selected").text();
		
		if(text == "OTHERS"){
			$("#mother_tongue_other_hid").show();
		}
		else{
			$("#mother_tongue_other_hid").hide();
		}
 		 
 	}
 function fn_religion() {
	 var text = $("#religion option:selected").text();
		
		if(text == "OTHERS"){
			$("#religion_other_hid").show();
		}
		else{
			$("#religion_other_hid").hide();
		}
 		 
 	}
 function fn_hair_colour() {
	 var text = $("#hair_colour option:selected").text();
		
		if(text == "OTHERS"){
			$("#hair_colour_other_hid").show();
		}
		else{
			$("#hair_colour_other_hid").hide();
		}
 		 
 	}
 function fn_eye_colour() {
	 var text = $("#eye_colour option:selected").text();
		
		if(text == "OTHERS"){
			$("#eye_colour_other_hid").show();
		}
		else{
			$("#eye_colour_other_hid").hide();
		}
 		 
 	}

function calcDate22(f) {
	if($('#from_dt' + f).val() != "" && $('#from_dt' + f).val() != "DD/MM/YYYY" && $('#to_dt' + f).val() != "" && $('#to_dt' + f).val() != "DD/MM/YYYY") {
		if(getformatedDate($('#from_dt' + f).val()) > getformatedDate($('#to_dt' + f).val())) {
			alert("Invalid Date Range");
			$('#period' + f).val('');
			$("input#to_dt" + f).focus();
			return false;
		}
		var date_form = getformatedDate($('#from_dt' + f).val());
		var date_to = getformatedDate($('#to_dt' + f).val());
		var diff = Math.floor(date_to.getTime() - date_form.getTime());
		var day = 1000 * 60 * 60 * 24;
		var days = Math.floor(diff / day);
		
		
		var totalYears = Math.floor(days / 365);
		var totalMonths = Math.floor((days % 365) / 30);
		var remainingDays = Math.floor((days % 365) % 30);
		var message;
		if(totalYears == 0 && totalMonths == 0 && remainingDays == 0) {
			message = "0 years 0 months 1 days";
		} else {
			message = totalYears + " years "
			message += totalMonths + " months "
			message += remainingDays + " days"
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
	


	fn_otherShowHide(document.getElementById('spouse_quali'),'other_div_examSpouse','exam_otherSpouse');
	 fn_otherShowHide(document.getElementById('quali_degree_spouse'),'other_div_qualiDegSpouse','quali_deg_otherSpouse');	
	 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
	
}




function fn_other_exam() {
  	var text = $("#quali option:selected").text();
  	if(text == "OTHERS"){
  		$("#other_div_exam").show();
  	}
  	else{
  		$("#other_div_exam").hide();
  		$("#exam_other").val("");
  	}
  }


function fn_other_class() {
	var text = $("#div_class option:selected").text();
	if(text == "OTHERS"){
	
		$("#other_div_class").show();
	}
	else
	{
		$("#other_div_class").hide();
		$("#class_other").val("");
		
	}
}



function fn_getUnitnamefromSus(sus_no, e) {
	$.post("getTargetUnitNameList?" + key + "=" + value, {
		sus_no: sus_no
	}).done(function(data) {
		var l = data.length - 1;
		var enc = data[l].substring(0, 16);
		$("#"+e.id).text(dec(enc, data[0]));
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function qualification_save_fn(qs) {
	var dateofBirthyear = off_dob.split('/')[2];
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
	var type = $('#quali_type').val();
	var examination_pass = $('#quali').val();
	var stream = $("#stream12").val();
	var passing_year = $('#yrOfPass').val();
	var course_name = $('#quali_course_name').val();
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
	if(examination_pass == "0") {}
	if(type != "6") {
		if(examination_pass == null || examination_pass == "0") {
			alert("Please Enter Examination Pass");
			$("input#quali").focus();
			return false;
		}
		if(examination_pass == "12th") {
			if(stream == null || stream == "0") {
				alert("Please Select The Stream");
				$("input#stream12").focus();
				return false;
			}
			if(stream == "Science") {
				if(specialization == null || specialization == "0") {
					alert("Please Select The Specialization");
					$("input#specialization").focus();
					return false;
				}
			}
		}
		if(!examination_pass == "12th" && !examination_pass == "10th") {
			if(specialization == null || specialization == "0") {
				alert("Please Select The  Specialization");
				$("input#specialization").focus();
				return false;
			}
		}
	} else {
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("input#specialization").focus();
			return false;
		}
		if(course_name == null || course_name == "0") {
			alert("Please Select The Course Name");
			$("input#quali_course_name").focus();
			return false;
		}
	}
	if(passing_year == "") {
		alert("Please Enter yr of Passing");
		$("input#yrOfPass").focus();
		return false;
	}
	if(passing_year != "") {
		if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
			alert("Please Enter Valid Year of Passing");
			$("input#yrOfPass").focus();
			return false;
		}
	}
	if(div_class == "0") {
		alert("Please Enter Div/Grade");
		$("input#div_class").focus();
		return false;
	}
	if(subject == "") {
		alert("Please Enter Subject");
		$("input#sub_quali").focus();
		return false;
	}
	if(institute == "") {
		alert("Please select Institute of place");
		$("select#institute_place").focus();
		return false;
	}
	$.post('qualification_action?' + key + "=" + value, {
		type: type,
		examination_pass: examination_pass,
		passing_year: passing_year,
		div_class: div_class,
		subject: subject,
		institute: institute,
		qualification_ch_id: qualification_ch_id,
		q_id: q_id,
		stream: stream,
		course_name: course_name,
		specialization: specialization,
		com_id: com_id,
		dateofBirthyear: dateofBirthyear
	}, function(data) {
		if(parseInt(data) > 0) {
			alert("Data Saved SuccesFully")
		} else alert(data)
		$('#quali').val('0');
		$("#stream12").val('0');
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
		fn_qualification_typeChange();
		getQualifications();
	}).fail(function(xhr, textStatus, errorThrown) {
		alert("fail to fetch")
	});
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
	$("table#allergic_table").append('<tr align="center" id="tr_allergic' + aller + '"><td style="width: 2%;">' + aller + '</td>' + '<td><input type="text" id="medicine' + aller + '" name="medicine' + aller + '" class="form-control autocomplete" autocomplete="off" >  </td>' + '<td style="display: none;"><input type="text" id="allergic_ch_id' + aller + '" name="allergic_ch_id' + aller + '" value="0" class="form-control autocomplete" autocomplete="off"></td>' 
	+ '</tr>');
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
		comm_id: comm_id,app_status:app_status
	}, function(j) {
		var v = j.length;
		$("#allergictbody").empty();
		if(v != 0) {
			for(i; i < v; i++) {
				x = i + 1;
				$("table#allergic_table").append('<tr align="center" id="tr_allergic' + x + '"><td style="width: 2%;">' + x + '</td>' + '<td><label class=" form-control-label" id="medicine' + x + '" name="medicine' + x + '" >' + j[i].medicine + '</label>  </td>' + '</tr>');
			}
			aller = v;
			$("input#allergic_count").val(v);
			$("input#allergicOld_count").val(v);
			$("#allergic_id_add" + v).show();
			$("#allergic_radio").text('YES');
			$("#allergicToMedicineDiv").show();
		} else {

			$("#allergic_radio").text('NO');
			$("input#allergic_count").val('1');
			$("input#allergicOld_count").val('0');
		}
	});
}

function spouse_addressFn() {
	if($("#check_spouse_address").is(':checked')) $("#spouse_addressInnerdiv").show(); // checked
	else $("#spouse_addressInnerdiv").hide();
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
	var a = $('#father_proff_radio').text();
	if(a == "YES") {
		$("#father_inserviceDiv").show();
		$("#father_proffDiv").hide();
	} else {
		$("#father_proffDiv").show();
		$("#father_inserviceDiv").hide();
	}
}

function mother_proffFn() {
	var a = $('#mother_proff_radio').text();
	if(a == "YES") {
		$("#mother_inserviceDiv").show();
		$("#mother_proffDiv").hide();
	} else {
		$("#mother_proffDiv").show();
		$("#mother_inserviceDiv").hide();
	}
}
//spouse qualification
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
			$("input#spouse_quali_type").focus();
			return false;
		}
	
		if(examination_pass == null || examination_pass == "0") {
			alert("Please Select Examination Pass");
			$("input#spouse_quali").focus();
			return false;
		}
	
		
		if(degree == null || degree == "0") {
			alert("Please Select The Degree Acquired");
			$("input#quali_degree_spouse").focus();
			return false;
		}
		if(specialization == null || specialization == "0") {
			alert("Please Select The Specialization");
			$("input#spouse_specialization").focus();
			return false;
		}
		
	

	
	
      if($("#spouse_quali option:selected").text()==other) {
     	 if(exam_other_qua == null || exam_other_qua==""){ 	 
     		alert( "Please Enter Examination Other ");
			
			$("input#exam_otherSpouse").focus();
			return false;
		   }
      }
      if($("#quali_degree_spouse option:selected").text()==other) {
      	 if(degree_other == null || degree_other==""){ 	       
      		alert( "Please Enter Degree Other ");
 			$("input#quali_deg_otherSpouse").focus();
 			return false;
 		   }
       }
      if($("#spouse_specialization option:selected").text()==other) {
       	 if(spec_other == null || spec_other==""){ 	 
        
  			alert( "Please Enter Specialization Other ");
  			$("input#quali_spec_otherSpouse").focus();
  			return false;
  		   }
        }
      
	
	if(passing_year == "") {
		alert("Please Enter yr of Passing");
		$("input#spouse_yrOfPass").focus();
		return false;
	}
	if(passing_year != "") {
		if(parseInt(passing_year) <= parseInt(dateofBirthyear) || parseInt(passing_year) > parseInt(currentyear)) {
			alert("Please Enter Valid Year of Passing");
			$("input#spouse_yrOfPass").focus();
			return false;
		}
	}
	if(div_class == "0") {
		alert("Please Enter Div/Grade");
		$("input#spouse_div_class").focus();
		return false;
	}
	if(div_class=="4") {
   	 if(class_other_qua == null || class_other_qua==""){ 	 
    
			alert( "Please Enter Div/Grade Other ");
			$("input#class_otherSpouse").focus();
			return false;
		   }
    }
	if(subject == "") {
		alert("Please Enter Subject");
		$("input#spouse_sub_quali").focus();
		return false;
	}
	if(institute == "") {
		alert("Please select Institute of place");
		$("select#spouse_institute_place").focus();
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
			alert("Data Saved SuccesFully")
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
		q_id: q_id,app_status:app_status
	}, function(j) {
		var v = j.length;
		if(v != 0) {
			$('#spouse_yrOfPass').text(j[0].passing_year);
			$('#spouse_div_class').val(j[0].div_class);
			$('#spouse_div_classlbl').text($("#spouse_div_class option:selected").text());
			$('#spouse_institute_place').text(j[0].institute);
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
					$('#spouse_quali_sub_list').append("<span class='subspan'>" + this.parentElement.innerText + "<span> <br>");
				}
			});
			$("#spouse_checkboxes").show();
			$('#spouse_quali_type').val(j[0].type);
			$('#spouse_quali_typelbl').text($("#spouse_quali_type option:selected").text());
			var typethisT = document.getElementById('spouse_quali_type');
			fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
			
			if(j[0].examination_pass != null) {
				$('#spouse_quali').val(j[0].examination_pass);
				$('#spouse_qualilbl').text($("#spouse_quali option:selected").text());
				var qualithisT = document.getElementById('spouse_quali');
				fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
			}
			
			if(j[0].degree != null) {
				$('#quali_degree_spouse').val(j[0].degree);
				$('#quali_degree_spouselbl').text($("#quali_degree_spouse option:selected").text());
				var degreethisT = document.getElementById('quali_degree_spouse');

			}
			if(j[0].specialization != null) {
				$('#spouse_specialization').val(j[0].specialization);
				$('#spouse_specializationlbl').text($("#spouse_specialization option:selected").text());
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
	  	    
	  	
	 	  	$('#exam_otherSpouse').text(examother);
	 	
	 		 $('#class_otherSpouse').text(classother);
	 	 
	 		 $('#quali_deg_otherSpouse').text(deg_other);
	 	
	 		 $('#quali_spec_otherSpouse').text(spec_other);
	 		 
	 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');
	 	
	 	 
			$("#spouse_quali_radio").text("YES");
			$("#spouse_Qualifications").show();
		}
		else{
			$("#spouse_quali_radio").text("NO");
		}
	});
}

//************************************* START MEDICAL *****************************//
var classification = '1';
$("#mad_classification").change(function() {
	classification = this.value;
	
});



function onShapeValueChange(e,label){
// 	if(e.value=="1"){
// 		$("#"+label+"_from_date1").prop('readonly', true);
// 		$("#"+label+"_to_date1").prop('readonly', true);
// 	}
// 	else{
// 		$("#"+label+"_from_date1").prop('readonly', false);
// 		$("#"+label+"_to_date1").prop('readonly', false);
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
	
	 $.post('getShapevalueUrl?' + key + "=" + value, {Shape_status:Shape_status,app_status:app_status}, function(j){
		 
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
	 

}
sShape = 1;
function getsShapeDetails(){
	
	 var p_id=$('#census_id').val(); 	
	 var Shape='S'
	var comm_id = $('#comm_id').val();
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id :comm_id,
		  meddtlfillin3008:meddtlfillin3008 }, function(j){
			var v=j.length;
			
			
		if(v!=0){	
			 
			
			$('#s_madtable').show(); 
				$('#s_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
					 var fd="";
					 var td="";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
						$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
							+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
							+'<td><label id="s_status_lb'+x+'"></label>'
							
							+'<select name="s_status'+x+'" id="s_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="sShape_value_lb'+x+'"></label>'
							+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass1'+x+'">'
							+' <label name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'"  '
							+'  >'+j[i][4]+'<label>	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							

							+'</tr>');
						$("#s_status"+x).val(j[i][0]);
						$("#s_status_lb"+x).text($( "#s_status"+x+" option:selected" ).text());
						$.ajaxSetup({
  							async : false
  						});

						statusChange(1,x,j[i][0]);
						
						$("#sShape_value"+x).val(j[i][1]);
						$("#sShape_value_lb"+x).text($( "#sShape_value"+x+" option:selected" ).text());
// 						if(x>1){
// 							$("#s_status"+x+" option[value='1']").remove();
// 							$("#s_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('sShape_value'+x);
							onShapeValueChange(thisT,'s');
// 						}
						 
						
						
					
		}
			sShape=v;
			$("input#sShape_count").val(v);
			$("input#sShapeOld_count").val(v);
			$("#sShape_add"+v).show(); 
			$("#madical_authority").text(j[i-1][6]); 
			$("#madical_date_of_authority").text(convertDateFormate(j[i-1][7])); 
			$('#mad_classification_count').val(j[i-1][8]);
	
			if(j[i-1][11]!=null && j[i-1][11]!="" && j[i-1][12]!=null && j[i-1][12]!="" && j[i-1][13]!=null && j[i-1][13]!=""){
				$("#check_1bx").prop("checked", true);
				oncheck_1bx();
				 fd=convertDateFormate(j[i-1][11]);
			  	 td=convertDateFormate(j[i-1][12]);
				$("#1bx_from_date").text(fd);
				$("#1bx_to_date").text(td);
				$("#1bx_diagnosis_code").text(j[i-1][13]);
			}

			
			
			}
		
		
			
	  });
}

hShape = 1;
function gethShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='H';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008}, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#h_madtable').show(); 
				$('#h_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
					}
					
						$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="h_status_lb'+x+'"></label>'
							
							+'<select name="h_status'+x+'" id="h_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="hShape_value_lb'+x+'"></label>'
							+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
							+' <label name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							+'</tr>');
						$("#h_status"+x).val(j[i][0]);
						$("#h_status_lb"+x).text($( "#h_status"+x+" option:selected" ).text());
						$.ajaxSetup({
 							async : false
 						});
						
						statusChange(2,x,j[i][0]);
						

						$("#hShape_value"+x).val(j[i][1]); 
						$("#hShape_value_lb"+x).text($( "#hShape_value"+x+" option:selected" ).text());
						if(x>1){
							$("#h_status"+x+" option[value='1']").remove();
							$("#h_status"+(x-1)+" option[value='1']").remove();
							
						}
						else {
							var thisT = document.getElementById('hShape_value'+x);
							onShapeValueChange(thisT,'h');
						}
						 
						
						
					
		}
			hShape=v;
			$("input#hShape_count").val(v);
			$("input#hShapeOld_count").val(v);
			$("#hShape_add"+v).show();
			
			
			}
			
	  });
}


aShape = 1;
function getaShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='A';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,
		  comm_id:comm_id,
		  meddtlfillin3008:meddtlfillin3008}, function(j){
			var v=j.length;
			
		if(v!=0){	
			classification=j[0][13];
			$('#a_madtable').show(); 
				$('#a_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
					}
					
						$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="a_status_lb'+x+'"></label>'
							+'<select name="a_status'+x+'" id="a_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="aShape_value_lb'+x+'"></label>'
							+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
							+' <label name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						
							+'</tr>');
						$("#a_status"+x).val(j[i][0]);
						$("#a_status_lb"+x).text($( "#a_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(3,x,j[i][0]);
						
						$("#aShape_value"+x).val(j[i][1]); 
						$("#aShape_value_lb"+x).text($( "#aShape_value"+x+" option:selected" ).text());
// 						if(x>1){
// 							$("#a_status"+x+" option[value='1']").remove();
// 							$("#a_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('aShape_value'+x);
							onShapeValueChange(thisT,'a');
// 						}
						 
						
						
					
		}
			aShape=v;
			$("input#aShape_count").val(v);
			$("input#aShapeOld_count").val(v);
			$("#aShape_add"+v).show(); 		
			
			
			}
			
	  });
}


pShape = 1;
function getpShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='P';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#p_madtable').show(); 
				$('#p_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
					}
					
						$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td><label id="p_status_lb'+x+'"></label>'
							+'<select name="p_status'+x+'" id="p_status'+x+'" class="select_hide" '
							+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="pShape_value_lb'+x+'"></label>'
							+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" class="select_hide" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'												
							+'  <td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
							+' <label name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							
							+'</tr>');
						$("#p_status"+x).val(j[i][0]);
						$("#p_status_lb"+x).text($( "#p_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(4,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#pShape_value"+x).val(j[i][1]); 
						$("#pShape_value_lb"+x).text($( "#pShape_value"+x+" option:selected" ).text());
// 						if(x>1){
// 							$("#p_status"+x+" option[value='1']").remove();
// 							$("#p_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('pShape_value'+x);
							onShapeValueChange(thisT,'p');
// 						}
						 
						
						
					
		}
			pShape=v;
			$("input#pShape_count").val(v);
			$("input#pShapeOld_count").val(v);
			$("#pShape_add"+v).show(); 	
			
			}
			
	  });
}

eShape = 1;
function geteShapeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='E';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			$('#e_madtable').show(); 
				$('#e_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="";
					 var td="";
					 if(j[i][2]!=null && j[i][2]!=""){
					 	 fd=convertDateFormate(j[i][2]);
// 					  	 td=convertDateFormate(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = convertDateFormate(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
					}
					
						$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<label id="e_status_lb'+x+'"></label><select class="select_hide" name="e_status'+x+'" id="e_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style=""><label id="eShape_value_lb'+x+'"></label>'
							+'<select class="select_hide" name="eShape_value'+x+'" id="eShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')">'
							+'		</select>	</td>'
							+'	<td style="">'
							+'		<label >'+fd+'<label>	'					                             
							+'		 </td>'	   
							+'<td style="">'
							+'			<label >'+td+'<label>	'				                             
							+'   </td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
							+' <label name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'"  '
							+'  >'+j[i][4]+'<label>	'						                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						
							+'</tr>');
						$("#e_status"+x).val(j[i][0]);
						$("#e_status_lb"+x).text($( "#e_status"+x+" option:selected" ).text());
						$.ajaxSetup({
							async : false
						});

						statusChange(5,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#eShape_value"+x).val(j[i][1]); 
						$("#eShape_value_lb"+x).text($( "#eShape_value"+x+" option:selected" ).text());
// 						if(x>1){
// 							$("#e_status"+x+" option[value='1']").remove();
// 							$("#e_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('eShape_value'+x);
							onShapeValueChange(thisT,'e');
// 						}
						 
						
						
					
		}
			eShape=v;
			$("input#eShape_count").val(v);
			$("input#eShapeOld_count").val(v);
			$("#eShape_add"+v).show(); 
			
			}
			
	  });
}



function getcCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='C_C';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
						 	
						 	+'<label id="c_cvalue_lb'+x+'"></label><select class="select_hide" name="c_cvalue'+x+'" id="c_cvalue'+x+'" '
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<label name="c_cother'+x+'" id="c_cother'+x+'"   >'+j[i][10]+'</label>	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 
						 	+'</tr>');
					
						$("#c_cvalue"+x).val(j[i][1]); 
						$("#c_cvalue_lb"+x).text($( "#c_cvalue"+x+" option:selected" ).text());
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
			$(".select_hide").hide();
			cCope=v;
			$("input#cCope_count").val(v);
			$("input#cCopeOld_count").val(v);
			$("#cCope_add"+v).show(); 														
			}
			
	  });
}


function getoCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='C_O';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
						 	+'<label id="c_ovalue_lb'+x+'"><label><select class="select_hide" name="c_ovalue'+x+'" id="c_ovalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_ovalue"+x).val(j[i][1]); 
						$("#c_ovalue_lb"+x).text($( "#c_ovalue"+x+" option:selected" ).text());
						var thisT = document.getElementById('c_ovalue'+x)
						if(x==1){onCopeChangebt(thisT,2,x);}
						
						if(x>1){
						$("#c_ovalue"+x+" option[value='0']").remove();
						$("#c_ovalue"+(x-1)+" option[value='0']").remove();
						}
		
		}
			$(".select_hide").hide();
			oCope=v;
			$("input#oCope_count").val(v);
			$("input#oCopeOld_count").val(v);
			$("#oCope_add"+v).show(); 														
			}
			
	  });
}

function getpCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
	var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='C_P';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
						 	+'<label id="c_pvalue_lb'+x+'"></label><select class="select_hide" name="c_pvalue'+x+'" id="c_pvalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_pvalue"+x).val(j[i][1]); 
						$("#c_pvalue_lb"+x).text($( "#c_pvalue"+x+" option:selected" ).text());
						
						var thisT = document.getElementById('c_pvalue'+x)
						if(x==1){onCopeChangebt(thisT,3,x);}
						
						if(x>1){
						$("#c_pvalue"+x+" option[value='0']").remove();
						$("#c_pvalue"+x+" option[value='1']").remove();
						$("#c_pvalue"+(x-1)+" option[value='0']").remove();
						$("#c_pvalue"+(x-1)+" option[value='1']").remove();
						}
		
		}
				$(".select_hide").hide();
			pCope=v;
			$("input#pCope_count").val(v);
			$("input#pCopeOld_count").val(v);
			$("#pCope_add"+v).show(); 														
			}
			
	  });
}


function geteCopeDetails(){
	
	 var p_id=$('#census_id').val(); 
	 var comm_id = $('#comm_id').val();
		var meddtlfillin3008 = $('#medDtlFillIn3008').val();
	 var Shape='C_E';
	 var i=0;
	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,app_status:app_status,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
						 	+'<label id="c_evalue_lb'+x+'"></label>'
						 	+'<select style="display:none" name="c_evalue'+x+'" id="c_evalue'+x+'" '
						 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'<td style="display:none;" class="eCopClass'+x+'">'
						 	+'<label id="c_esubvalue_lb'+x+'"></label>'
						 	+'<select class="select_hide" style="display:none" name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')"'
						 	+'			class="form-control-sm form-control" >'
						 	+'			<option value="0">--Select--</option>'																
						 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
						 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'			</c:forEach></select>   </td>'
						 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
						 	+'	 <label name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'">'+j[i][10]+'</label>'						                              
						 	+'	   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" ></td>'   						 
						 	+'</tr>');
					$.ajaxSetup({
						async : false
					});
						$("#c_evalue"+x).val(j[i][1]); 
						$("#c_esubvalue"+x).val(j[i][9]);
						 $('#c_evalue_lb'+x).text($( "#c_evalue"+x+" option:selected" ).text());
						 if(j[i][9]!=null && j[i][9]!=0)
							$('#c_esubvalue_lb'+x).text($( "#c_esubvalue"+x+" option:selected" ).text());
						var thisT = document.getElementById('c_evalue'+x)
						onECopeChange(thisT,x);
						
						if(x==1){onCopeChangebt(thisT,4,x);}
						
						if(x>1){
						$("#c_evalue"+x+" option[value='0']").remove();
						$("#c_evalue"+(x-1)+" option[value='0']").remove();
						
						}
				
		}
				
				$(".select_hide").hide();
			eCope=v;
			$("input#eCope_count").val(v);
			$("input#eCopeOld_count").val(v);
			$("#eCope_add"+v).show(); 														
			}
			
	  });
}

//************************************* END MEDICAL *****************************//
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
$("#quali_sub_search").keyup(function() {
	var re = new RegExp($(this).val(), "i")
	$('.quali_subjectlist').each(function() {
		var text = $(this).text(),
			matches = !!text.match(re);
		$(this).toggle(matches)
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
		alert("Age Should be above 18");
		
		$("#" + marrage_date.id).focus();
		return false;
	}
	return true;
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


	</script>
<script>
var app_status = 0;
var other="OTHERS";
$( document ).ready(function() {
   if('${personnel_no}' != ""){	   
		
	   $('#btn1').hide();
	   app_status='${app_status}';

	  var  roleType='${roleType}';
	  if(app_status==0 && roleType=="APP" || roleType=="ALL"){

		  $("#approve_btn").hide();
          $("#reject_btn").hide();
		  $("#approve_checkdiv").show();
	  }
	   $("#personnel_no").val('${personnel_no}');
	   $.ajaxSetup({
				async : false
			});
	   personal_number();
	   




	   $("#approve_check").prop("disabled",false);
	 
	   
	   
	   
   }
   
   
   $('#approve_check').click(function(){
       if($(this).prop("checked") == true){
           $("#approve_btn").show();
           $("#reject_btn").show();
       }
       else if($(this).prop("checked") == false){
    	   $("#approve_btn").hide();
           $("#reject_btn").hide();
       }
   });
});

function Approved(){
	var census_id = $('#census_id').val()


	var checkBox = document.getElementById("approve_check");
	 if (checkBox.checked == true){	 
  $.post('Approve_Census?' + key + "=" + value, {census_id:census_id}, function(j){
	  alert(j);
	  
	 returnSearch();
  });
	 } else {
		    alert("Please Accept The Condition");
		    $("#approve_check").focus();
		    return false;
		  }
		
}
function Reject(){
	var census_id = $('#census_id').val()
	var reject_remarks=$('#reject_remarks').val().trim();
	
	
	 $.post('Reject_Census?' + key + "=" + value, {census_id:census_id,reject_remarks:reject_remarks}, function(j){
		  alert(j);
		  returnSearch();
	  });
} 

function returnSearch(){
	var personnelNo = $("#personnel_no").val();
	$("#personnel_no1").val('${personnel_no_return}') ;	
	$("#status1").val('${status_return}') ;		
	$("#rank1").val('${rank_return}') ;		
	$("#unit_name1").val('${unit_name_return}') ;	
	$("#unit_sus_no1").val('${unit_sus_no_return}') ;
	  if (personnelNo.substring(0, 2).toUpperCase() == 'NR' || 
		        personnelNo.substring(0, 2).toUpperCase() == 'NS') {
		        $("#IsMns").val('True');
		    } else {
		        $("#IsMns").val('False');
		    }
	
 	document.getElementById('searchForm').submit();
}
function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText==other){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}


</script>