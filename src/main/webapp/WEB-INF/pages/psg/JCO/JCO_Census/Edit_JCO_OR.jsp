
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
	
	<form:form name="jco_Personnel_Detail" id="jco_Personnel_Detail" action="Edit_JCOsAction?${_csrf.parameterName}=${_csrf.token}" method="post"
			class="form-horizontal" commandName="Edit_JCOsCMD">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header"><h5>ENTER DATA JCOs,OR and RECTS</h5><h6 class="enter_by">
										<span style="font-size: 12px; color: red;">(To be entered by Unit)</span></h6>
						</div>
		<div class="card-body card-block">
			<div class="card-header-inner" id="aa"
								style="margin-left: 20px; margin-top: 10px;">
								<strong><h4>Personal Details </h4></strong>
							</div>	
								<div class="col-md-12">	
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Category</label>
										</div>
										<div class="col-md-8" style="margin-left:-13px;">
											<select name="category" id="category"  class="form-control" onchange="category_type();fn_category_rank();" readonly="readonly">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCategory_jCOList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>																	
													
							</div>			
					<div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;margin-left:10px;">* </strong>Army No</label>
								</div>
								
						 	<div class="col-md-10">
								    <div class="row form-group">
								    <input type="hidden" id="jco_id" name="jco_id"  class="form-control autocomplete" autocomplete="off">
								    <div class="col-md-4" style="margin-left:-15px;">
									<select id="army_no1" name="army_no1" class="form-control" readonly="readonly">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getArmyType}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
									 
									</div>
									<div class="col-md-4">
									<input type="text" id="army_no2" name="army_no2" onchange="army_length(),Army_no_already_exist()"  onkeyup="onChangeArmyNo(this);" onkeypress="return isNumber(event)" class="form-control-sm form-control" autocomplete="off" placeholder="Enter No..." maxlength="9" readonly="readonly">
								</div>
								<div class="col-md-4">
									<input type="text" id="army_no3" name="army_no3" class="form-control-sm form-control" autocomplete="off"  maxlength="10" readonly="readonly">
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
											<input type="text" id="first_name" name="first_name" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()" maxlength="50" style="left: 80px;width: 80%;">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Middle Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="middle_name" name="middle_name" class="form-control autocomplete" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()" autocomplete="off" maxlength="50" style="width: 80%;">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Last Name</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="last_name" name="last_name" class="form-control autocomplete" onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()" autocomplete="off" maxlength="50" style="width: 80%;">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
												style="color: red;">* </strong> Gender</label>
								</div>
								<div class="col-md-8">
								 <select name="gender" id="gender"
										class="form-control" onchange="fn_gender();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getGenderList}" varStatus="num">
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
												style="color: red;">* </strong>Rank</label>
										</div>
										<div class="col-md-8">
								 	<select name="rank" id="rank1"
										class="form-control" onchange="rank_intake_jco();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getRankjcoList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
										</div>
									</div>
								</div>
								
							</div>
							<div class="col-md-12">
		
								<div class="col-md-6" id = "gender_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Gender Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="gender_other" name="gender_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              					
	              					</div>
	              					
	              				</div>
							<div class="col-md-12" id="intake" style="display:none;">		
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Rank Intake</label>
										</div>
										<div class="col-md-8">
								 	<select name="rank_intake" id="rank_intake"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getRank_intakeList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
												style="color: red;">* </strong>Date of Birth</label>
										</div>
										<div class="col-md-8">
											<!-- <label class=" form-control-label" id="dob"></label> -->
										
										      <input type="text" name="date_of_birth" id="date_of_birth" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"  
											onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" > 
										
									
										</div>
									</div>
								</div>
							  <div class="col-md-6" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Place of Birth</label>
										</div>
										<div class="col-md-8">
					
											<input type="text" id="place_of_birth" name="place_of_birth"
												class="form-control autocomplete" onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												oninput="this.value = this.value.toUpperCase()" autocomplete="off">
										</div>
									</div>
							 </div>								
						</div>
									<div class="col-md-12" style="display: none;">	
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> COUNTRY of Birth</label>
										</div>
										<div class="col-md-8">
											<input type="hidden" id="country_of_birth_hid" name="country_of_birth_hid"  class="form-control autocomplete" autocomplete="off">
                                       <input type="hidden" id="state_of_birth_hid" name="state_of_birth_hid"  class="form-control autocomplete" autocomplete="off">
                                        <input type="hidden" id="district_of_birth_hid" name="district_of_birth_hid"  class="form-control autocomplete" autocomplete="off">
									    <input type="hidden" id="nationality_hid" name="nationality_hid"  class="form-control autocomplete" autocomplete="off">
											 <input type="hidden" id="mother_tongue_hid" name="mother_tongue_hid"  class="form-control autocomplete" autocomplete="off">	
										 	 	 <input type="hidden" id="religion_hid" name="religion_hid"  class="form-control autocomplete" autocomplete="off">	
											 <input type="hidden" id="gender_hid" name="gender_hid"  class="form-control autocomplete" autocomplete="off">	
											
											<select name="country_of_birth" id="country_of_birth"  onchange="fn_country_birth();"class="form-control">
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
											<select name="state_of_birth" id="state_of_birth" 	onchange="fn_state_birth();" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
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
					                	<input type="text" id="country_other" name="country_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "state_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">STATE of Birth Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="state_other" name="state_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              					</div>
								<div class="col-md-12" style="display: none;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT of Birth</label>
										</div>
										<div class="col-md-8">
											<select name="district_of_birth" id="district_of_birth" class="form-control" onchange="fn_district_birth();" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedDistrictName}"
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
												style="color: red;">* </strong> Nationality</label>
										</div>
										<div class="col-md-8">
											<select name="nationality" id="nationality" class="form-control" onchange="fn_nationality();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNationalityList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>								
							</div>
							
							<div class="col-md-12" style="display: none;">
							
								<div class="col-md-6" id = "district_birth_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">DISTRICT of Birth Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="district_other" name="district_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
								<div class="col-md-6" id = "nationality_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Nationality Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="nationality_other" name="nationality_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
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
											<select name="religion" id="religion" class="form-control" onchange="fn_religion();">
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
												style="color: red;">* </strong> Mother Tongue</label>
										</div>
										<div class="col-md-8">
											<select name="mother_tongue" id="mother_tongue" class="form-control" onchange="fn_mother_tongue();">
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
							
								<div class="col-md-6" id = "religion_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Religion Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="religion_other" name="religion_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              					
	              					</div>
	              						<div class="col-md-6" id = "mother_tongue_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Mother Tongue Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="mother_tongue_other" name="mother_tongue_other" onkeypress="return onlyAlphabets(event);" class="form-control autocomplete" autocomplete="off" maxlength="50">
					                	 </div>
						            </div>
	              				</div>
	              				</div>
							<div class="col-md-12" >
								<div class="col-md-6" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Marital Status</label>
										</div>
										<div class="col-md-8">
											<select name="marital_status" id="marital_status" class="form-control" onchange="return validate_marital_event();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMarital_statusList}"
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
												style="color: red;">* </strong> Blood Group</label>
										</div>
										<div class="col-md-8">
											<select name="blood_group" id="blood_group" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getBloodList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[0]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								</div>
							<div class="col-md-12" style="display: none;">							
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong> Height (cm)</label>
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
								
								 <div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Aadhaar No</label>
										</div>
										
								 		<div class="col-md-2">
											<input type="text" id="adhar_number1" name="adhar_number1"
												class="form-control autocomplete" autocomplete="off" maxlength="4"  onkeypress="return isNumber(event)" onkeyup="movetoNext(this, 'adhar_number2'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number2" name="adhar_number2"
												class="form-control autocomplete" autocomplete="off" maxlength="4" onkeypress="return isNumber(event)" onkeyup="movetoNext(this, 'adhar_number3'),lengthadhar()">
										</div>
										<div class="col-md-2">
											<input type="text" id="adhar_number3" name="adhar_number3"
												class="form-control autocomplete" autocomplete="off" maxlength="4"  onkeypress="return isNumber(event)" onkeyup="lengthadhar()">
										</div> 
									</div>
								</div> 
							</div>
							<div class="col-md-12" style="display: none;">	
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Pan No</label>
									</div>
									<div class="col-md-8">

											<input type="text" id="pan_no" name="pan_no"
												class="form-control autocomplete" autocomplete="off"
												onchange=" isPAN(this); "
												oninput="this.value = this.value.toUpperCase()"
												maxlength="10">
									</div>
								</div>
							</div>
							</div>
							
							<div class="col-md-12" style="display: none;">	
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Class For Enrollment</label>
										</div>
										<div class="col-md-8">
											<select name="class_enroll" id="class_enroll"  class="form-control" onchange="fn_domicile();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getClass_enrollList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
								<div class="col-md-6" id="gorkha_domicile" style="display:none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Domicile</label>
										</div>
										<div class="col-md-8">
								 	<select name="domicile" id="domicile"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getclass_domicileList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
										</div>
									</div>
								</div>																									
							</div>
	                   <div class="card-header-inner" id="aa"
								style="margin-left: 20px; margin-bottom: 20px;">
								<strong><h4>Service Details </h4></strong>
							</div>
							<div class="col-md-12">	              					
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Class (Pay)</label>
						                </div>
						                <div class="col-md-8">
						                 
						                	    <select name="class_pay" id="class_pay" class="form-control"    >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getClass_payList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Pay Group</label>
						                </div>
						                <div class="col-md-8">
						                 
						                	    <select name="pay_group" id="pay_group" class="form-control"    >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getPaygroupList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Record Office (SUS No)</label>
						                </div>
						                <div class="col-md-8">
						 
												<input type="text" id="record_office_sus" name="record_office_sus"  class="form-control-sm form-control" autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Record Office</label>
						                </div>
						                <div class="col-md-8">
						               
											<input type="text" id="record_office" name="record_office"  class="form-control-sm form-control" autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				
	              				</div>
	              			
							<div class="col-md-12">	
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Enrollment</label>
						                </div>
						                <div class="col-md-8">
						                 
						                   <input type="text" name="enroll_dt" id="enroll_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  >
						                </div>
						            </div>
	              				</div>              					
	              					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Attestation</label>
						                </div>
						                <div class="col-md-8">
						                 
						                 <input type="text" name="date_of_attestation" id="date_of_attestation" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			
	              				
	              				</div>
							<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Seniority</label>
						                </div>
						                <div class="col-md-8">
						                 <!--   <input type="date" id="date_seniority" name="date_seniority" class="form-control autocomplete" autocomplete="off" >  -->
						                   <input type="text" name="date_of_seniority" id="date_of_seniority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Trade</label>
										</div>
										<div class="col-md-8">
										 
										<select name="trade" id="trade" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTradeJcoList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of TOS</label>
						                </div>
						                <div class="col-md-8">
						                 <input type="text" name="date_of_tos" id="date_of_tos" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Rank</label>
						                </div>
						                <div class="col-md-8">
						                 
						                   <input type="text" name="date_of_rank" id="date_of_rank" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div> 
	              				</div>
								<div class="col-md-12">	
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Arm/Service</label>
										</div>
										<div class="col-md-8">
										 <!--  <input type="text" id="arm_service" name="arm_service" class="form-control autocomplete" autocomplete="off"  > 	 -->
										    <select name="arm_service" id="arm_service" class="form-control-sm form-control"   onchange="chgarm(this,'regiment');"  >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
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
												style="color: red;">* </strong>Regiment(INF)</label>
										</div>
										<div class="col-md-8">
				
										     <select name="regiment" id="regiment" class="form-control-sm form-control"   disabled="disabled">
												<option value="0">--Select--</option>
												
											</select>
										</div>
									</div>
								</div>	
									</div>	
									
									<div class="col-md-12">
									<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
												</div>
												<div class="col-md-8">
												<!-- 	<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50">				 -->
														
									<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none">				
										    <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label> 	
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Unit SUS No </label>
												</div>
												<div class="col-md-8">
											
											 <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   style="display: none"> 
												
												</div>
											</div>
										</div>
								</div>
											
                         <div class="card-header-inner" id="aa"
								style="margin-left: 20px; margin-bottom: 20px;">
								<strong><h4>Family Details </h4></strong>
							</div>
							
							<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Father's Name</label>
									</div>
									<div class="col-md-8">
										 <input type="text" id="father_name" name="father_name"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()" maxlength="50">
                              	</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Father's Date of Birth</label>
									</div>
									<div class="col-md-8">
									<!-- 	<input type="date" id="father_dob" name="father_dob" class="form-control autocomplete" autocomplete="off"> -->
									  <input type="text" name="father_dob" id="father_dob" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display: none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Father's Place of Birth</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="father_place_birth" name="father_place_birth"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()" maxlength="50">
									</div>
								</div>
							</div>
						
						</div>
						
						<div class="col-md-12" style="display: none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Father In Service/Ex-Service</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="father_proff_radio1"
											name="father_proff_radio" value="yes"
											onchange="father_proffFn();">&nbsp <label
											for="yes">Yes</label>&nbsp <input type="radio"
											id="father_proff_radio2" name="father_proff_radio" value="no"
											onchange="father_proffFn();" checked="checked">&nbsp
										<label for="no">No</label><br>


									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12" style="display: none;">
							<div id="father_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Father's Services</label>
										</div>
										<div class="col-md-8">
											<select name="father_service" id="father_service"
												class="form-control-sm form-control"
												onchange="ServiceOtherFn('father_otherDiv','father_personalDiv',this)">
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
											<input type="text" id="father_other" name="father_other"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphabets(event);"
												maxlength="50">


										</div>
									</div>
								</div>
								<div class="col-md-6" id="father_personalDiv" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong>Father's Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="father_personal_no"
												name="father_personal_no" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>

							</div>
							<div class="col-md-6" id="father_proffDiv" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Father's Profession</label>
									</div>
									<div class="col-md-8">
										<!--   <input type="text" id="father_profession" name="father_profession" class="form-control autocomplete" autocomplete="off"  >  -->
                                             <!-- onchange="fn_father_other();" -->
										<select name="father_profession" id="father_profession"
											class="form-control"  onchange="fn_otherShowHide(this,'father_proffotherDiv','father_proffother')">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getProfession}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
							<div class="col-md-12" style="display: none;">
							<div class="col-md-6" id="father_proffotherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="father_proffother" name="father_proffother"
												class="form-control autocomplete" autocomplete="off" 
												onkeypress="return onlyAlphabets(event);"
												maxlength="50">


										</div>
									</div>
								</div>
							
								</div>
						<div class="col-md-12" >
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother's Name</label>
									</div>
									<div class="col-md-8">
									<input type="text" id="mother_name" name="mother_name"
                                           class="form-control autocomplete" autocomplete="off"
                                           onkeypress="return onlyAlphabets(event);"
                                           oninput="this.value = this.value.toUpperCase()" maxlength="50">
									</div>
								</div>
							</div>
							<div class="col-md-6" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother's Date of Birth</label>
									</div>
									<div class="col-md-8">
										<!-- <input type="date" id="mother_dob" name="mother_dob" class="form-control autocomplete" autocomplete="off"> -->
									         <input type="text" name="mother_dob" id="mother_dob" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="display: none;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother's Place of Birth</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="mother_place_birth" name="mother_place_birth"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()" maxlength="50">
									</div>
								</div>
							</div>
						</div>
								<div class="col-md-12" style="display: none;"> 
						


							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother In Service/Ex-Service</label>
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

						<div class="col-md-12" style="display: none;">
							<div id="mother_inserviceDiv" class="col-md-12"
								style="display: none">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Mother's Services</label>
										</div>
										<div class="col-md-8">
											<select name="mother_service" id="mother_service"
												class="form-control-sm form-control"
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
											<input type="text" id="mother_other" name="mother_other"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphabets(event);"
												maxlength="50">


										</div>
									</div>
								</div>
								<div class="col-md-6" id="mother_personalDiv" style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"></strong>Mother's Personal No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_personal_no"
												name="mother_personal_no" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">


										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-6" id="mother_proffDiv" style="display: none;">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Mother's Profession</label>
									</div>
									<div class="col-md-8">
										<!-- <input type="text" id="mother_profession" name="mother_profession" class="form-control autocomplete" autocomplete="off"  >  -->
									<!-- 	onchange="fn_mother_other();" -->
										
										<select name="mother_profession" id="mother_profession"
											class="form-control"  onchange="fn_otherShowHide(this,'mother_proffotherDiv','mother_proffother')">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getProfession}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
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
					<!-- 			<div id="divhideformed"
											style="width: -webkit-fill-available; text-align: -webkit-center; display:block">
											<strong style="font-size: x-large; text-align: center;">For
												Any Modification pls Click Here </strong> <a
												class="btn btn-danger btn-sm" value="DELETE"
												title="Reset All Medical Details " id="cCope_remove6"
												onclick="AllCoperemove_fn();"><i
												style="font-size: x-large;" class="fa fa-trash"></i></a>
										</div> -->
										</br> </br> </br> <input type="hidden" id="mad_classification_count"
											name="mad_classification_count" value="NIL">
							
							
								<div class="col-md-12">
									<div class="row form-group">
										<div class="col-md-12" style="font-size: 15px; margin: 10px;">
											<input type="checkbox" id="check_1bx" name="check_1bx"
												onclick="oncheck_1bx()" value="1BX"> <label
												for="text-input" class=" form-control-label"
												style="margin-left: 10px;"><strong> SHAPE
													1BX </strong></label>
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
												<input type="text" name="1bx_diagnosis_code"
													id="1bx_diagnosis_code"
													class="form-control-sm form-control" autocomplete="off"
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
														class="form-control-sm form-control"
														onchange="statusChange(1,1,this.options[this.selectedIndex].value)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getShapeStatusList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style=""><select name="sShape_value1"
														id="sShape_value1" class="form-control-sm form-control"
														onchange="onShapeValueChange(this,'s')">
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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

													</td>
													<td style="display: none;" class="diagnosisClass11">
														<input type="text" name="_diagnosis_code11"
														id="_diagnosis_code11"
														class="form-control-sm form-control" autocomplete="off"
														placeholder="Search..."
														onkeypress="AutoCompleteDiagnosis(this);">
													</td>

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
														class="form-control-sm form-control"
														onchange="statusChange(2,1,this.options[this.selectedIndex].value)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getShapeStatusList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style=""><select name="hShape_value1"
														id="hShape_value1" class="form-control-sm form-control"
														onchange="onShapeValueChange(this,'h')">
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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

													</td>
													<td style="display: none;" class="diagnosisClass21"><input
														type="text" name="_diagnosis_code21"
														id="_diagnosis_code21"
														class="form-control-sm form-control" autocomplete="off"
														placeholder="Search..."
														onkeypress="AutoCompleteDiagnosis(this);"></td>

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
														class="form-control-sm form-control"
														onchange="statusChange(3,1,this.options[this.selectedIndex].value)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getShapeStatusList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style=""><select name="aShape_value1"
														id="aShape_value1" class="form-control-sm form-control"
														onchange="onShapeValueChange(this,'a')">
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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

													</td>
													<td style="display: none;" class="diagnosisClass31"><input
														type="text" name="_diagnosis_code31"
														id="_diagnosis_code31"
														class="form-control-sm form-control" autocomplete="off"
														placeholder="Search..."
														onkeypress="AutoCompleteDiagnosis(this);"></td>

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
														class="form-control-sm form-control"
														onchange="statusChange(4,1,this.options[this.selectedIndex].value)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getShapeStatusList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style=""><select name="pShape_value1"
														id="pShape_value1" class="form-control-sm form-control"
														onchange="onShapeValueChange(this,'p')">
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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

													</td>
													<td style="display: none;" class="diagnosisClass41"><input
														type="text" name="_diagnosis_code41"
														id="_diagnosis_code41"
														class="form-control-sm form-control" autocomplete="off"
														placeholder="Search..."
														onkeypress="AutoCompleteDiagnosis(this);"></td>

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
														class="form-control-sm form-control"
														onchange="statusChange(5,1,this.options[this.selectedIndex].value)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getShapeStatusList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[1]}">${item[0]}</option>
															</c:forEach>
													</select></td>
													<td style=""><select name="eShape_value1"
														id="eShape_value1" class="form-control-sm form-control"
														onchange="onShapeValueChange(this,'e')">
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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

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
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

													</td>
													<td style="display: none;" class="diagnosisClass51"><input
														type="text" name="_diagnosis_code51"
														id="_diagnosis_code51"
														class="form-control-sm form-control" autocomplete="off"
														placeholder="Search..."
														onkeypress="AutoCompleteDiagnosis(this);"></td>

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
													<th style="">Value & Description</th>
													<th style="display: none;" class="cCopClass">Other</th>
													<!-- 													<th >Description</th>																					 -->
													<th style="width: 10%; display: none;"
														class="CopaddbtClass1">Action</th>
												</tr>
											</thead>
											<tbody data-spy="scroll" id="c_cmadtbody"
												style="min-height: 46px; max-height: 650px; text-align: center;">
												<tr id="tr_cCope1">
													<td style="width: 2%;">1</td>
													<td style=""><select name="c_cvalue1" id="c_cvalue1"
														onchange="onCCopeChange(this,1); onCopeChangebt(this,1,1);"
														class="form-control-sm form-control">
															<!-- 																<option value="0">--Select--</option>																 -->
															<c:forEach var="item" items="${getcCopeList}"
																varStatus="num">
																<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
															</c:forEach>
													</select></td>

													<td style="display: none;" class="cCopClass1"><input
														type="text" name="c_cother1" id="c_cother1"
														class="form-control-sm form-control" autocomplete="off">
													</td>



													<td style="display: none;"><input type="text"
														id="cCope_ch_id1" name="cCope_ch_id1" value="0"
														class="form-control autocomplete" autocomplete="off"></td>

													<td style="width: 10%; display: none;"
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
					
			<!-- manav -->
		
		<input type="hidden" id="sShape_count" name="sShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="sShapeOld_count" name="sShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="hShape_count" name="hShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="hShapeOld_count" name="hShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="aShape_count" name="aShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="aShapeOld_count" name="aShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="pShape_count" name="pShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pShapeOld_count" name="pShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="eShape_count" name="eShape_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eShapeOld_count" name="eShapeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
				
				<input type="hidden" id="cCope_count" name="cCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="cCopeOld_count" name="cCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="oCope_count" name="oCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="oCopeOld_count" name="oCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="pCope_count" name="pCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="pCopeOld_count" name="pCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
					<input type="hidden" id="eCope_count" name="eCope_count"
				class="required form-control" autocomplete="off"  value="1"/> 
			<input type="hidden" id="eCopeOld_count" name="eCopeOld_count"
				class="form-control" maxlength="2" autocomplete="off" value="0">
		

						
					<!-- //===========================End medical dtl =======================================// -->
						
						
						
						
						
						<div class="col-md-12" style="display: none;">
						<div class="col-md-6" id="mother_proffotherDiv" style="display: none">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">*</strong>Other</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="mother_proffother" name="mother_proffother"
												class="form-control autocomplete" autocomplete="off"
												onkeypress="return onlyAlphabets(event);"
												 maxlength="50">


										</div>
									</div>
								</div>
								</div>
					<div class="col-md-4" style="display: none;">
							<label class=" form-control-label" style="margin-right: 100px;"><h4>Details of Siblings </h4></label></div>
						<br>
						<div class="col-md-12" style="display: none;">
							<table id="family_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Name</th>										
										<th style="width: 10%;">Date of Birth</th>
										<th style="width: 10%;">Relationship</th>
										<th style="width: 10%;">Aadhar No</th>
										<th style="width: 10%;">PAN No</th>
										<th style="width: 10%;">Service/Ex-Service</th>
										<th style="width: 10%;">Services</th>
										<th style="width: 10%;">Personal No.</th>
										<th style="width: 10%;">Other Service</th>
										<th style="width: 10%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_sibtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno" style="width: 2%;">1</td>
										<td style="width: 20%;"><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete" autocomplete="off"
											onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()">
										</td>										
										
										<td style="width: 10%;">
											
											<input type="text" name="sib_date_of_birth1"
												id="sib_date_of_birth1" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 85%; display: inline;"
												onfocus="this.style.color='#000000'"
												onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
												onkeyup="clickclear(this, 'DD/MM/YYYY')"
												onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
												aria-required="true" autocomplete="off"
												style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

										</td>
										<td style="width: 10%;"><select name="sib_relationship1" id="sib_relationship1" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach></select>
										</td>
										<td style="width: 10%;"><input type="text"
											id="sib_adhar_number1" name="sib_adhar_number1"
											class="form-control autocomplete" maxlength="14"
											onkeypress="return isAadhar(this,event); " autocomplete="off"></td>

										<td style="width: 10%;"><input type="text"
											id="sib_pan_no1" name="sib_pan_no1"
											class="form-control autocomplete" autocomplete="off"
											onchange=" isPAN(this); "
											oninput="this.value = this.value.toUpperCase()"
											maxlength="10"></td>
										<td style="display: none;"><input type="text"
											id="sib_ch_id1" name="sib_ch_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>
										<td>
										<input type="checkbox" id="ser-ex1" name="ser-ex1" value="Yes" onclick="siblingCB(1);">
										</td>
										<td><select name="sibling_service1" id="sibling_service1" onchange = "otherfunction(1)"
												class="form-control-sm form-control"
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
												maxlength="15"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_sibling_ser1"
												name="other_sibling_ser1" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
										<td style="width: 10%;">
<!-- 										<a class="btn btn-primary btn-sm" value="SAVE" title="SAVE" id="family_save1"
											onclick="family_save_fn(1);"><i class="fa fa-save"></i></a> -->
											
											<a  class="btn btn-success btn-sm" value="ADD" title="ADD" id="family_add1"
											onclick="family_add_fn(1);"><i class="fa fa-plus"></i></a> 
											
											<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="family_remove1"
											onclick="family_remove_fn(1);"><i class="fa fa-trash"></i></a>
										</td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" id="siblings_count" name="siblings_count"
									class="required form-control" autocomplete="off" value="1" /> <input
									type="hidden" id="siblingsOld_count" name="siblingsOld_count"
									class="form-control" maxlength="2" autocomplete="off" value="0">
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
											<th>Aadhar No</th>
											<th >PAN No</th>
											<th>Service/Ex-Service</th>
											<th>Services</th>
											<th>Personal No.</th>
											<th>Other Service</th>
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
												onkeypress="return onlyAlphabets(event);"
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
												onkeypress="return onlyAlphabets(event);"
												oninput="this.value = this.value.toUpperCase()" maxlength="50">
											</td>
											<td ><select
												name="marriage_nationality1" id="marriage_nationality1" onchange="marriageNationChange(1)"
												class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getNationalityList}"
														varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
											</select></td>
											
											<td ><input type="text" id="marriage_othernationality1" name="marriage_othernationality1"
												class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">
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
												onkeypress="return isAadhar(this,event); "
												autocomplete="off"></td>


											<td><input type="text" id="spouse_pan_number1"
												name="spouse_pan_number1" class="form-control autocomplete"
												autocomplete="off" onchange=" isPAN(this); "
												oninput="this.value = this.value.toUpperCase()"
												maxlength="10"></td>

											<td style="display: none;"><input type="text"
												id="marr_ch_id1" name="marr_ch_id1" value="0"
												class="form-control autocomplete" autocomplete="off"></td>
										<td>
										<input type="checkbox" id="spo-ex1" name="spo-ex1" value="Yes" onclick="spouseCB(1);">
										</td>
										<td><select name="spouse_service1" id="spouse_service1" onchange="otherfunction(1)"
												class="form-control-sm form-control"
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
												maxlength="15"
												onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
												oninput="this.value = this.value.toUpperCase()">
											</td>
											<td><input type="text" id="other_spouse_ser1"
												name="other_spouse_ser1" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return onlyAlphaNumeric(event);"
												oninput="this.value = this.value.toUpperCase()">
											</td>
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
											<!-- <td class="hide-action"><a
												class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
												id="married_save1" onclick="married_save_fn(1);"><i
													class="fa fa-save"></i></a> <a style="display: none;"
												class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
												id="married_remove1" onclick="married_remove_fn(1);"><i
													class="fa fa-trash"></i></a></td> -->
													<td class="hide-action">
<!-- 													<a style="" class="btn btn-success btn-sm" value="ADD" title="ADD" id="married_add1" onclick="married_add_fn(1);"><i class="fa fa-plus"></i></a> -->
<!-- 													 <a class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="married_remove1" onclick="married_remove_fn(1);"><i -->
<!-- 													class="fa fa-trash"></i></a> -->
													</td>
										</tr>
									</tbody>
								</table>
								<input type="hidden" id="spouse_count" name="spouse_count"
									class="required form-control" autocomplete="off" value="1" /> <input
									type="hidden" id="spouseOld_count" name="spouseOld_count"
									class="form-control" maxlength="2" autocomplete="off" value="0">
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
															autocomplete="off">
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
															name="quali_deg_otherSpuse"
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
															name="quali_spec_otherSpouse"
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

														<input type="text" id="spouse_yrOfPass"
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
															style="color: red;">* </strong>Div/Grade/PCT(%)</label>
													</div>
													<div class="col-md-8">
														<select name="spouse_div_class" id="spouse_div_class"
															class="form-control-sm form-control" onchange="fn_otherShowHide(this,'other_div_classSpouse','class_otherSpouse')">
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
															style="color: red;">* </strong>Div/Grade/PCT(%) Other </label>
													</div>
													<div class="col-md-8">
														<input type="text" id="class_otherSpouse" name="class_otherSpouse" onkeypress="return onlyAlphaNumeric(event);"
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
															style="color: red;">* </strong>Subjects</label>
													</div>
													<div class="col-md-8">
														<div class="multiselect">

															<div class="selectBox" onclick="showCheckboxesSpouse()">
																<select name="spouse_sub_quali" id="spouse_sub_quali"
																	class="form-control-sm form-control">
																	<option>Select Subjects</option>
																</select>
																<div class="overSelect"></div>
															</div>
															<input type ="hidden" id="subject_sp" name="subject_sp">
															<div id="spouse_checkboxes" class="checkboxes"
																style="max-height: 200px; overflow: auto;">
																<input type="text" id="spouse_searchSubject"
																	name="spouse_searchSubject"
																	placeholder="Search Subjects"
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
															name="spouse_institute_place"
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
									<!-- <div class="" align="right" class="form-control">
										<input type="button" class="btn btn-primary btn-sm"
											value="Save" style="margin-right: 30px"
											onclick="spouse_qualification_save_fn();">
									</div> -->
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
										<th >Aadhar No</th>
										<th>PAN No</th>
										<th >Date of Divorce/Widow/Widower</th>
										<th class="hide-action" style="width: 15%;">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_divotbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_divorce1">
										<td class="divosr_no" style="width: 2%;">1</td>
										<td style="width: 10%;"><select name="marital_event1"
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
										<td><input type="text" id="spouse_name1"
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
											name="divorce_place_of_birth1"
											class="form-control autocomplete" autocomplete="off"
											onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()" maxlength="50"></td>

										<td><select name="divorce_nationality1"
											id="divorce_nationality1"
											class="form-control-sm form-control" onchange="divorceNationChange(1)">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNationalityList}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
										</select></td>
										<td ><input type="text" id="divorce_othernationality1" name="divorce_othernationality1"
												class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">
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
											name="divorce_spouse_adhar_number1"
											onkeypress="return isAadhar(this,event);"
											class="form-control autocomplete" maxlength="14"
											autocomplete="off"></td>


										<td><input type="text" id="divorce_spouse_pan_number1"
											name="divorce_spouse_pan_number1"
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

										<td class="hide-action">

											<a class="btn btn-success btn-sm"
											value="ADD" title="ADD" id="divorce_add1"
											onclick="divorce_add_fn(1);"><i class="fa fa-plus"></i></a> 
<!-- 											<a class="btn btn-danger btn-sm" -->
<!-- 											value="REMOVE" title="REMOVE" id="divorce_remove1" -->
<!-- 											onclick="divorce_remove_fn(1);"><i class="fa fa-trash"></i></a> -->
											</td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" id="divorce_count" name="divorce_count"
									class="required form-control" autocomplete="off" value="1" /> <input
									type="hidden" id="divorceOld_count" name="divorceOld_count"
									class="form-control" maxlength="2" autocomplete="off" value="0">
						</div>
					</div>

						</div>
					
						<div class="card-footer" align="center" class="form-control">
							
                           <a href="search_jco_url" class="btn btn-danger btn-sm">Cancel</a> 
							<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validation();" ><!--onclick="return Validation();"  -->
						</div>
				       </div>
					</div>
					</div>
				</form:form>
			</div>
			
<script>
//260194
function SetMin(){
	if ($("input#date_of_birth").val() != "") {
			
			var birth_dt = $("input#date_of_birth").val();
			setMinDate('enroll_dt', birth_dt);
			setMinDate('date_of_seniority', birth_dt);
			setMinDate('date_of_tos', birth_dt);
			setMinDate('date_of_attestation', birth_dt);
			
			setMaxDate('father_dob', birth_dt);
			setMaxDate('mother_dob', birth_dt);
		}
		if ($("input#enroll_dt").val() != "") {
			
			var comm_dt = $("input#enroll_dt").val();
			setMinDate('date_of_attestation', comm_dt);
			setMinDate('date_of_tos', comm_dt);
			setMinDate('date_of_seniority', comm_dt);
			setMinDate('date_of_rank', comm_dt);

			
		}
  }

function Validation()
{
	
	$("#marital_status").val(10);
	
	var dt_of_birth = $("#date_of_birth").val();
	dt_of_birth1 =dt_of_birth.substring(6,10);
    
	var seniority = $("#date_of_seniority").val();
	seniority1 =seniority.substring(6,10);
	
	var en_dt  = $("#enroll_dt").val();
	enroll_dt1 =en_dt.substring(6,10);
	
	var fa_dob  = $("#father_dob").val();
	father_dob1 =fa_dob.substring(6,10);
	
	var mo_dob  = $("#mother_dob").val();
	mother_dob1 =mo_dob.substring(6,10);
	
	
	var dt_of_tos = $("#date_of_tos").val();
	dt_of_tos1 =dt_of_tos.substring(6,10);
	
	
	  if ($("select#category").val() == "0") {
			alert("Please Select Category ");
			$("select#category").focus();
			return false;
		}
	  var ar = $("#category option:selected").val();
	   
		if(ar == "JCO") {
			 if ($("select#army_no1").val() == "0") {
			      alert("Please Enter Army No ");
			      $("select#army_no1").focus();
					return false;
				}
		 } 

		   if ($("input#army_no2").val() == "") {
				alert("Please Enter Army No ");
				$("input#army_no2").focus();
			return false;
			}  
		   var arm21=$("input#army_no2").val();
			 if (arm21.length < 1 || arm21.length > 9) {
				alert("Please Enter Valid Army No.");
				$("input#army_no2").focus();
				return false;
			 } 
			 if($("input#first_name").val().trim() == "") {
					alert("Please Enter  First Name ");
					$("input#first_name").focus();
					return false;
			}
			if ($("select#gender").val() == "0") {
				alert("Please Select Gender ");
				$("select#gender").focus();
				return false;
			}
	         var gen = $("#gender option:selected").text();
			
			if(gen.toUpperCase() == "OTHER" && $("input#gender_other").val().trim() == ""){
				alert("Please Enter Gender Other");
				$("input#gender_other").focus();
				return false;
			}
			if ($("select#rank1").val() == "0") {
				alert("Please Select Rank ");
				$("select#rank1").focus();
				return false;
			}
		  var rnk = $("#rank1 option:selected").val();
			
			if(rnk == "17"){
				if($("select#rank_intake").val()== "0") {
				alert("Please Select Rank Intake");
				$("select#rank_intake").focus();
				return false;
			  }
			}
			if($("input#date_of_birth").val().trim() == "" || $("input#date_of_birth").val().trim() == "DD/MM/YYYY"){
				alert("Please Select Date of Birth ");
				$("input#date_of_birth").focus();
				return false;
			}
		     /* if($("input#place_of_birth").val().trim() == "") {
				alert("Please Enter Place of Birth ");
				$("input#place_of_birth").focus();
				return false;
			}
			if ($("select#country_of_birth").val() == "0") {
				alert("Please Select Country of Birth");
				$("select#country_of_birth").focus();
				return false;
			}
			var text9 = $("#country_of_birth option:selected").text();
			
			if(text9.toUpperCase() == "OTHERS" && $("input#country_other").val().trim() == ""){
				alert("Please Enter Country Birth Other");
				$("input#country_other").focus();
				return false;
			}
			if ($("select#state_of_birth").val() == "0") {
				alert("Please Select State of Birth ");
				$("select#state_of_birth").focus();
				return false;
			}
			var text10 = $("#state_of_birth option:selected").text();
			
			if(text10.toUpperCase() == "OTHERS" && $("input#state_other").val().trim() == ""){
				alert("Please Enter State Birth Other");
				$("input#state_other").focus();
				return false;
			}
			if ($("select#district_of_birth").val() == "0") {
				alert("Please Select District of Birth ");
				$("select#district_of_birth").focus();
				return false;
			}
			var text11 = $("#district_of_birth option:selected").text();
			
			if(text11.toUpperCase() == "OTHERS" && $("input#district_other").val().trim() == ""){
				alert("Please Enter District Birth Other");
				$("input#district_other").focus();
				return false;
			}
			
			if ($("select#nationality").val() == "0") {
				alert("Please Select Nationality");
				$("select#nationality").focus();
				return false;
			}
			var text12 = $("#nationality option:selected").text();
			
			if(text12.toUpperCase() == "OTHERS" && $("input#nationality_other").val().trim() == ""){
				alert("Please Enter Nationality Other");
				$("input#nationality_other").focus();
				return false;
			} */
			if ($("select#religion").val() == "0") {
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
			if ($("select#mother_tongue").val() == "0") {
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
			if ($("select#marital_status").val() == "0") {
				alert("Please Select Marital Status");
				$("select#marital_status").focus();
				return false;
			}
			if ($("select#blood_group").val() == "0") {
				alert("Please Select Blood Group");
				$("select#blood_group").focus();
				return false;
			}
			
			/* if ($("select#height").val() == "0") {
				alert("Please Select Height");
				$("select#height").focus();
				return false;
	 		} */
			  var aadhar1 = $("input#adhar_number1").val();
			   var aadhar2 = $("input#adhar_number2").val();
			   var aadhar3 = $("input#adhar_number3").val();
				 
					/* if(aadhar1.trim()=="" || aadhar1.length < 4) {
						alert("Please Enter Aadhar Number");
						$("input#adhar_number1").focus();
						return false;
					}
					if(aadhar2.trim()=="" || aadhar2.length < 4) {
						alert("Please Enter Aadhar Number");
						$("input#adhar_number2").focus();
						return false;
					}
					if(aadhar3.trim()=="" || aadhar3.length < 4) {
						alert("Please Enter Aadhar Number");
						$("input#adhar_number3").focus();
						return false;
					}
					var pan_no=$('#pan_no').val();		
					if(pan_no.trim()=="") {
					
						alert("Please Enter PAN Number");
						$('#pan_no').focus();
						return false
					}
					
					 if ($("select#class_enroll").val() == "0") {
							alert("Please Select Class for Enrollment");
							$("select#class_enroll").focus();
							return false;
					 }
					  if($("#class_enroll").val() == "10"){
							if ($("select#domicile").val() == "0") {
								alert("Please Select Domicile ");
								$("select#domicile").focus();
								return false;
						}
					  }  */
					 if ($("select#class_pay").val() == "0") {
							alert("Please Select Class Pay");
							$("select#class_pay").focus();
							return false;
					 }
						 
					 if ($("select#pay_group").val() == "0") {
							alert("Please Select Pay Group");
							$("select#pay_group").focus();
							return false;
					 }
					 if ($("input#record_office_sus").val() == "") {
							alert("Please Enter Record Office SUS No");
							$("input#record_office_sus").focus();
							return false;
					 }
					  var rk = $("#rank1 option:selected").text();
					   
						if(rk != "RECTS") {
								 if ($("input#date_of_attestation").val() == "" || $("#date_of_attestation").val() == "DD/MM/YYYY") {
								alert("Please Select Date of Attestation");
								$("input#date_of_attestation").focus();
								return false;
					         } 
						 } 
			
						
					 if ($("input#enroll_dt").val() == "" || $("#enroll_dt").val() == "DD/MM/YYYY") {
						alert("Please Select Date of Enrollment");
						$("input#enroll_dt").focus();
						return false;
				 }
					 
					
				if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('enroll_dt'))>=16) {
					    	 
					    } else {
					    	 alert("Enrollment  Age Should Be 16 Or Above ");
								$("#enroll_dt").focus();
					 		return false;
					 }

				
				   if ($("input#date_of_seniority").val() == "" || $("#date_of_seniority").val() == "DD/MM/YYYY") {
					alert("Please Select Date of Seniority");
					$("input#date_of_seniority").focus();
					return false;
				     }
			   

		      if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('date_of_seniority'))>=16) {
			    	 
			     } else {
			    	 alert("Seniority Age Should Be 16 Or Above ");
						$("#date_of_seniority").focus();
			 		return false;
			 	}

				if ($("select#trade").val() == "0") {
					alert("Please Select Trade ");
					$("select#trade").focus();
					return false;
				}
				
			    if ($("input#date_of_tos").val() == "" || $("#date_of_tos").val() == "DD/MM/YYYY") {
						alert("Please Select Date of TOS");
						$("input#date_of_tos").focus();
						return false;
				 }
			    
			    if ($("input#date_of_rank").val() == "" || $("#date_of_rank").val() == "DD/MM/YYYY") {
					alert("Please Select Date of Rank");
					$("input#date_of_rank").focus();
					return false;
			 }
				if ($("select#arm_service").val() == "0") {
					alert("Please Select Arm/Service ");
					$("select#arm_service").focus();
					return false;
				}
				  if($("#arm_service").val() == "0800" || $("select#arm_service").val() == "0700"){
						if ($("select#regiment").val() == "0") {
							alert("Please Select Regiment ");
							$("select#regiment").focus();
							return false;
					}
				  }
				  if($("input#unit_sus_no").val().trim() == "") {
						alert("Please Enter Unit SUS No ");
						$("input#unit_sus_no").focus();
						return false;
					}
				  if($("input#unit_name").val().trim() == "") {
						alert("Please Enter Unit Name ");
						$("input#unit_name").focus();
						return false;
					}
					if($("input#father_name").val().trim() == "") {
						alert("Please Enter Father's Name ");
						$("input#father_name").focus();
						return false;
					}
					
					  if($("input#mother_name").val().trim() == "") {
							alert("Please Enter Mother's Name");
							$("input#mother_name").focus();
							return false;
						   }
					  
					  
					  /* ============================= medical validation ======================== */
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
						/*  ============================= End medical validation ======================== */
										 medical_cat_validation();
				
				  
				  	/*	if ($("input#father_place_birth").val() == "") {
				  			alert("Please Enter Father's Place of Birth");
				  			$("input#father_place_birth").focus();
				  			return false;
				  		}

						var fservice_radio = $("input[name='father_proff_radio']:checked").val();
						if(fservice_radio == "yes") {
							if($("#father_service").val()=="0"){
								alert("Please Select Father Service");
								$("#father_service").focus();
								return false;
							}
							if($("#father_service").val()=="4"){
								if($("#father_other").val()==""){
									alert("Please Enter Other Service");
									$("#father_other").focus();
									return false;
								}
							}
							if($("#father_service").val()=="1" || $("#father_service").val()=="2" || $("#father_service").val()=="3"){
								if($("#father_personal_no").val().trim() ==""){
									alert("Please Enter Father Personal No");
									$("#father_personal_no").focus();
									return false;
								}
							} 
												
						}
						else{
							if($("#father_profession").val() == "0") {
								alert("Please Select Father's Profession ");
								$("#father_profession").focus();
								return false;
							}
							
					 		var f_proff = $("#father_profession  option:selected").text();
							if(f_proff == "OTHERS"){
								if($("#father_proffother").val().trim()==""){
									alert("Please Enter Father's Other Profession ");
									$("#father_proffother").focus();
									return false;
								}
							} 
						}
			     
	
		   if ($("input#mother_dob").val() == "" || $("#mother_dob").val() == "DD/MM/YYYY") {
				alert("Please Select Mother's Date of Birth");
				$("input#mother_dob").focus();
				return false;
			}
			
			if ($("input#mother_place_birth").val().trim() == "") {
				alert("Please Enter Mother's Place of Birth");
				$("input#mother_place_birth").focus();
				return false;
			}
			
			var mservice_radio = $("input[name='mother_proff_radio']:checked").val();
			if(mservice_radio == "yes") {
				if($("#mother_service").val()=="0"){
					alert("Please Select Mother Service");
					$("#mother_service").focus();
					return false;
				}
				if($("#mother_service").val()=="4"){
					if($("#mother_other").val()==""){
						alert("Please Enter Other Service");
						$("#mother_other").focus();
						return false;
					}
				}
			
				if($("#mother_service").val()=="1" || $("#mother_service").val()=="2" || $("#mother_service").val()=="3"){
					
					if($("#mother_personal_no").val().trim() ==""){
						alert("Please Enter Mother Personal No");
						$("#mother_personal_no").focus();
						return false;
					}
				
			}
			}
			else{
				if($("#mother_profession").val() == "0") {
					alert("Please Select Mother's Profession ");
					$("#mother_profession").focus();
					return false;
				}
				var m_proff = $("#mother_profession option:selected").text();
				if(m_proff == "OTHERS"){
					if($("#mother_proffother").val().trim() ==""){
						alert("Please Enter Mother's Other Profession ");
						$("#mother_proffother").focus();
						return false;
					}
				}
			}  */
				
		////////////////////khushbu
/* 
			for(var ps = 1;ps<=$("#siblings_count").val();ps++){
				
				if($("#siblings_count").val()==1){
					if($("#sib_name"+ps).val().trim()!=""){
						if($("#sib_date_of_birth"+ps).val().trim()=="" || $("input#sib_date_of_birth"+ps).val().trim() == "DD/MM/YYYY"){
							alert("Please Select Date of Birth");
							$("input#sib_date_of_birth"+ps).focus();
							return false;
						}
						if($("select#sib_relationship"+ps).val().trim()=="0"){
							alert("Please Select Relationship");
							$("select#sib_relationship"+ps).focus();
							return false;
						}
						if($("input#sib_adhar_number"+ps).val().trim() == "" || $("input#sib_adhar_number"+ps).val().length < 14) {
							alert("Please Enter Aadhar No");
							$("input#sib_adhar_number"+ps).focus();
							return false;
						}
					}
					else{
						$("#siblings_count").val(0);
					}
				}
				else {
					if($("#sib_name"+ps).val().trim()==""){
						alert("Please Enter Sibling Name");
						$("input#sib_name"+ps).focus();
						return false;
					}
					if($("#sib_name"+ps).val().trim()!=""){
						if($("#sib_date_of_birth"+ps).val().trim()=="" || $("input#sib_date_of_birth"+ps).val().trim() == "DD/MM/YYYY"){
							alert("Please Select Date of Birth");
							$("input#sib_date_of_birth"+ps).focus();
							return false;
						}
						if($("select#sib_relationship"+ps).val().trim()=="0"){
							alert("Please Select Relationship");
							$("select#sib_relationship"+ps).focus();
							return false;
						}
						if($("input#sib_adhar_number"+ps).val().trim() == "" || $("input#sib_adhar_number"+ps).val().length < 14) {
							alert("Please Enter Aadhar No");
							$("input#sib_adhar_number"+ps).focus();
							return false;
						}
					}
				
				}
			}
		
			if(!validate_marital_event()){
				return false;
			}
		
			if($("#marital_status").val() == "8" || $("#marital_status").val() == "13"){
				 for(var ps = 1;ps<=$("#spouse_count").val();ps++){
					var marital_status = $("select#marital_status option:selected").val();
						var maiden_name = $('#maiden_name' + ps).val();
						var marriage_date_of_birth = $('#marriage_date_of_birth' + ps).val();
						var marriage_place_of_birth = $('#marriage_place_of_birth' + ps).val();
						var marriage_nationality = $('#marriage_nationality' + ps).val();
						var marriage_date = $('#marriage_date' + ps).val();
						var adhar_number = $('#spouse_adhar_number' + ps).val().split('-').join('');
						var spouse_pan_number = $("#spouse_pan_number"+ps).val();
						var marriage_othernationality = $("#marriage_othernationality" +ps).val();
						var other_spouse_ser = $("#other_spouse_ser"+ps).val();
						var spo_ser = $("select#spouse_service"+ps).val();
						var spo_pers_no = $("#spouse_personal_no"+ps).val();
						
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
                       if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('marriage_date' + ps))>=18) {
	                        
	                    } else {
	                             alert("Age of Spouse And JCO/OR Should be above 18");
	                              $("#marriage_date"+ps).focus();
	                                 return false;
	                     }
					       if(calculate_agediff(document.getElementById('marriage_date_of_birth'+ps), document.getElementById('marriage_date' + ps))>=18) {
                               
                           } else {
                                    alert("Spouse Age Should Be Above 18 ");
                                     $("#marriage_date_of_birth"+ps).focus();
                                        return false;
                            } 
						if($("input#spouse_adhar_number"+ps).val().trim() == "" || $("input#spouse_adhar_number"+ps).val().length < 14) {
							alert("Please Enter Aadhar No");
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
						
			if($("#marital_status").val() == "13"){
							
							if($("input#sep_from_date"+ps).val().trim() == "") {
								alert("Please Enter Date of Separation");
								$("input#sep_from_date"+ps).focus();
								return false;
							}
							
							if(getformatedDate(marriage_date) >= getformatedDate($("input#sep_from_date"+ps).val())) {
								alert("Date of Separation Date should be Greater than Date Of Marriage");
								$("input#sep_from_date" + ps).focus();
								return false;
							}
						}
				}
			}
			else{
				$("#spouse_count").val('0');
			}
			
			////qualification
			var ab = $('input:radio[name=spouse_quali_radio]:checked').val();
			if(ab.toLowerCase() == "yes") {
				
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

		var subjectvar = $('input[name="spouse_multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
			var subject = subjectvar.join(",");
			$('#subject_sp').val(subject);
			
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
		
			  if($("#spouse_quali option:selected").text()=="other") {
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
			if($("#quali_degree_spouse option:selected").text()=="other") {
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
			
		
	      if($("#spouse_specialization option:selected").text()=="other") {
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
			alert("Please Select The Div/Grade/PCT(%)");
			$("#spouse_div_class").focus();
			return false;
		}
		if(div_class=="4") {
	   	 if(class_other_qua == null || class_other_qua.trim()==""){ 	 
	    
				alert( "Please Enter Div/Grade/PCT(%) Other ");
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
	 }
		
		/////divorce
			if($("#marital_status").val() != "10" && $("#marital_status").val() != "0"){
				var bool=true;
		  for(var ps = 1;ps<=$("#divorce_count").val();ps++){
			
			  var spouse_name = $('#spouse_name' + ps).val();
				var marriage_date_of_birth = $('#divorce_date_of_birth' + ps).val();
				var marriage_place_of_birth = $('#divorce_place_of_birth' + ps).val();
				var marriage_nationality = $('#divorce_nationality' + ps).val();
				var divorce_marriage_date = $('#divorce_marriage_date' + ps).val();
				var divorce_date = $('#divorce_date' + ps).val();
				var adhar_number = $('#divorce_spouse_adhar_number' + ps).val().split('-').join('');
				var divorce_spouse_pan_number = $('#divorce_spouse_pan_number' + ps).val();
				var divorce_othernationality = $('#divorce_othernationality' + ps).val();
				
		if($("#marital_status").val() == "9" || $("#marital_status").val() == "11" || $("#marital_status").val() == "12"){
					
					if($("select#marital_event" + ps).val() == "0") {
						alert("Please Select Marital Event");
						$("select#marital_event" + ps).focus();
						return false;
					}
					
				
				if($("#marital_status").val()==$("select#marital_event" + ps).val()){
					bool=false;
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
							alert("Please Enter Other Nationality");
							$("#divorce_othernationality" + ps).focus();
							return false;
						}
					}
					if($("input#divorce_marriage_date" + ps).val().trim() == "" || $("input#divorce_marriage_date" + ps).val().trim() == "DD/MM/YYYY") {
						alert("Please Enter Marriage Date");
						$("input#divorce_marriage_date" + ps).focus();
						return false;
					}
					/* if(calculate_age_jco(document.getElementById('divorce_date_of_birth' + ps), document.getElementById('divorce_marriage_date' + ps)) && calculate_age(document.getElementById('date_of_birth'), document.getElementById('divorce_marriage_date' + ps))) {} else {
						return false;
					} 
					if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('divorce_marriage_date' + ps))>=18) {
                        
                    } else {
                             alert("Age of Spouse And JCO/OR Should be above 18");
                              $("#divorce_marriage_date"+ps).focus();
                                 return false;
                     }
					 if(calculate_agediff(document.getElementById('divorce_date_of_birth'+ps), document.getElementById('divorce_marriage_date' + ps))>=18) {
                         
                     } else {
                              alert("Spouse Age Should Be Above 18 ");
                               $("#divorce_date_of_birth"+ps).focus();
                                  return false;
                      } 
					if(getformatedDate(marriage_date_of_birth) >= getformatedDate(divorce_marriage_date)) {
						alert("Marriage Date should be Greater than Date of Birth");
						$("input#divorce_marriage_date" + ps).focus();
						return false;
					}
			
					
					if($("input#divorce_spouse_adhar_number" + ps).val().trim() == "" || $("input#divorce_spouse_adhar_number" + ps).val().length < 14) {
						alert("Please Enter Aadhar No");
						$("input#divorce_spouse_adhar_number" + ps).focus();
						return false;
					}
					if($("input#divorce_spouse_pan_number" + ps).val().trim() == "") {
						alert("Please Enter PAN No");
						$("input#divorce_spouse_pan_number" + ps).focus();
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
					}
					 if(getformatedDate(divorce_marriage_date) >= getformatedDate(divorce_date)) {
						alert("Divorce/Widow/Widower Date should be Greater than Marriage Date");
						$("input#divorce_date" + ps).focus();
						return false;
					}
				}else{
						if(spouse_name != "")
						{			
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
									alert("Please Enter Other Nationality");
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
						
							if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('divorce_marriage_date' + ps))>=18) {
		                        
		                    } else {
		                             alert("Age of Spouse And JCO/OR Should be above 18");
		                              $("#divorce_marriage_date"+ps).focus();
		                                 return false;
		                     }
							 if(calculate_agediff(document.getElementById('divorce_date_of_birth'+ps), document.getElementById('divorce_marriage_date' + ps))>=18) {
		                         
		                     } else {
		                              alert("Spouse Age Should Be Above 18 ");
		                               $("#divorce_date_of_birth"+ps).focus();
		                                  return false;
		                      } 
					
							if($("input#divorce_spouse_adhar_number" + ps).val().trim() == "" || $("input#divorce_spouse_adhar_number" + ps).val().length < 14) {
								alert("Please Enter Aadhar No");
								$("input#divorce_spouse_adhar_number" + ps).focus();
								return false;
							}
							if($("input#divorce_spouse_pan_number" + ps).val().trim() == "") {
								alert("Please Enter PAN No");
								$("input#divorce_spouse_pan_number" + ps).focus();
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
							}
							 if(getformatedDate(divorce_marriage_date) >= getformatedDate(divorce_date)) {
								alert("Divorce/Widow/Widower Date should be Greater than Marriage Date");
								$("input#divorce_date" + ps).focus();
								return false;
							}  
						}
					else{
						  $("#divorce_count").val('0')
					  }	
			}
		 }
		  
		  if($("#marital_status").val() == "9" || $("#marital_status").val() == "11" || $("#marital_status").val() == "12"){
			  
			  if(bool){
				  alert("Please Add "+$("#marital_status option:selected").text()+" Deatils")
				  return false;
			  }
		  }
			}	else{
				  $("#divorce_count").val('0')
			  }	
			$("#category").prop("disabled",false);
			$("#army_no1").prop("disabled",false);
			$("#army_no2").prop("disabled",false);
			$("#army_no3").prop("disabled",false); */
}

jQuery(function($){ //on document.ready  
	datepicketDate('sep_from_date1');
	 datepicketDate('date_of_seniority');
	 datepicketDate('date_of_birth');
	 datepicketDate('date_of_attestation');
	 datepicketDate('enroll_dt');
	 datepicketDate('father_dob');
	 datepicketDate('mother_dob');
	 //datepicketDate('sib_date_of_birth1');
	 datepicketDate('date_of_tos');
	 datepicketDate('marriage_date_of_birth1');
	 datepicketDate('marriage_date1');
	 datepicketDate('divorce_marriage_date1');
	 datepicketDate('divorce_date1');
	 datepicketDate('divorce_date_of_birth1');
	 datepicketDate('date_of_rank');
	 
	 //medical date
	 datepicketDate('madical_date_of_authority');	 
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
		 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);	
		 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);	 
		 //end medical date

});
/* function getformatedDate(dateString){
	var dateParts = dateString.split("/");
	return new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
} */
///////////////////////////////////////////////////////////////////////////
		
		
$(document).ready(function() {
	
	 $.ajaxSetup({
         async : false
 	});
	 
	$("#jco_id").val('${Edit_JCOsCMD.id}');
	
	 
		if('${Edit_JCOsCMD.country_other}' !=null && '${Edit_JCOsCMD.country_other}' != ""){
			$("#country_birth_other_hid").show();
		}
		else
			{
			$("#country_birth_other_hid").hide();
			}
		if('${Edit_JCOsCMD.state_other}' !=null && '${Edit_JCOsCMD.state_other}' != ""){
			$("#state_birth_other_hid").show();
		}
		else
			{
			$("#state_birth_other_hid").hide();
			}
		if('${Edit_JCOsCMD.district_other}' !=null && '${Edit_JCOsCMD.district_other}' != ""){
			$("#district_birth_other_hid").show();
		}
		else
			{
			$("#district_birth_other_hid").hide();
			}
	$("#category").val('${Edit_JCOsCMD.category}');
	
	$("#first_name").val('${Edit_JCOsCMD.first_name}');
	$("#middle_name").val('${Edit_JCOsCMD.middle_name}');
	$("#last_name").val('${Edit_JCOsCMD.last_name}');
	$("#gender").val('${Edit_JCOsCMD.gender}');	
   var cat=  '${Edit_JCOsCMD.category}';
  
   if (cat =="OR")
	   {
   var l = '${Edit_JCOsCMD.army_no}'.length;
 
   $("#army_no2").val(
			'${Edit_JCOsCMD.army_no}'
					.substring(l-10, l-1));
	$("#army_no3").val(
			'${Edit_JCOsCMD.army_no}'
					.substring(l,l-1));
	   }
else{
	 
	   
	   var l = '${Edit_JCOsCMD.army_no}'.length;
	   if(l == 4){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 3));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(3, 4));
	    }
	   if(l == 5){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 4));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(4, 5));
	    }
	   if(l == 6){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 5));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(5, 6));
	    }
	   if(l == 7){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 6));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(6, 9));
	    }
	   if(l == 8){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 7));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(7, 8));
	    }
	   if(l == 9){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 8));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(8, 9));
	    }
	   if(l == 10){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 9));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(9, 10));
	   }
	   if(l == 11){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 10));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(10, 11));
	   }
	   if(l == 12){
		   $("#army_no1").val('${Edit_JCOsCMD.army_no}'.substring(0, 2));
			$("#army_no2").val('${Edit_JCOsCMD.army_no}'.substring(2, 11));
			$("#army_no3").val('${Edit_JCOsCMD.army_no}'.substring(11, 12));
	   }
	   }

	
/*    if('${Edit_JCOsCMD.date_of_birth}' == null || '${Edit_JCOsCMD.date_of_birth}' == ""){
	   alert('${Edit_JCOsCMD.date_of_birth}');
	   $("#date_of_birth").val('DD/MM/YYYY');
   }
   else
	   {
	   $("#date_of_birth").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_birth}'));
	   } */
   $("#date_of_birth").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_birth}'));
	$("#place_of_birth").val('${Edit_JCOsCMD.place_of_birth}');
	$("#country_of_birth").val('${Edit_JCOsCMD.country_of_birth}');
	// Changes BISAG Delhi
	fn_country_birth();
	$("#state_of_birth").val('${Edit_JCOsCMD.state_of_birth}');
	fn_state_birth();
	$("#district_of_birth").val('${Edit_JCOsCMD.district_of_birth}');
	fn_district_birth();
	// Changes End
	$("#nationality").val('${Edit_JCOsCMD.nationality}');
	$("#religion").val('${Edit_JCOsCMD.religion}');
	$("#mother_tongue").val('${Edit_JCOsCMD.mother_tongue}');
	$("#marital_status").val('${Edit_JCOsCMD.marital_status}');
	$("#blood_group").val('${Edit_JCOsCMD.blood_group}');
	$("#height").val('${Edit_JCOsCMD.height}');
	$("#adhar_number1").val('${Edit_JCOsCMD.aadhar_no}'.substring(0, 4));
	$("#adhar_number2").val('${Edit_JCOsCMD.aadhar_no}'.substring(4, 8));
	$("#adhar_number3").val('${Edit_JCOsCMD.aadhar_no}'.substring(8, 12));
	$("#pan_no").val('${Edit_JCOsCMD.pan_no}');
	$("#class_enroll").val('${Edit_JCOsCMD.class_enroll}');
	$("#class_pay").val('${Edit_JCOsCMD.class_pay}');
	
	$("#pay_group").val('${Edit_JCOsCMD.pay_group}');
	$("#record_office_sus").val('${Edit_JCOsCMD.record_office_sus}');
	$("#record_office").val('${Edit_JCOsCMD.record_office}');
	$("#date_of_attestation").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_attestation}'));
	$("#enroll_dt").val(ParseDateColumncommission('${Edit_JCOsCMD.enroll_dt}'));
	$("#date_of_rank").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_rank}'));

	$("#date_of_seniority").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_seniority}'));
	$("#trade").val('${Edit_JCOsCMD.trade}');
	$("#date_of_tos").val(ParseDateColumncommission('${Edit_JCOsCMD.date_of_tos}'));
	SetMin();
	$("#arm_service").val('${Edit_JCOsCMD.arm_service}');
	chgarm(document.getElementById("arm_service"),'regiment');
	
	$("#regiment").val('${Edit_JCOsCMD.regiment}');
	
			$("#unit_sus_no").val('${Edit_JCOsCMD.unit_sus_no}');
	
 	var sus_no ='${Edit_JCOsCMD.unit_sus_no}';
	getunit(sus_no);
	
	function getunit(val) {	
        $.post("getTargetUnitNameList?"+key+"="+value, {
        	sus_no : sus_no
    }, function(j) {
            
            

    }).done(function(j) {
            for (var i = 0; i < j.length; i++) {
				   var length = j.length-1;
	                   var enc = j[length].substring(0,16); 
	                   $("#unit_name").val(dec(enc,j[0])); 
			}
    }).fail(function(xhr, textStatus, errorThrown) {
    });
}  
	father_proffFn();
	mother_proffFn();
	ServiceOtherFn('father_otherDiv','father_personalDiv',document.getElementById('father_service'));
	ServiceOtherFn('mother_otherDiv','mother_personalDiv',document.getElementById('mother_service'));
	fn_otherShowHide(document.getElementById('father_profession'),'father_proffotherDiv','father_proffother');
	fn_otherShowHide(document.getElementById('mother_profession'),'mother_proffotherDiv','mother_proffother');
	$("#father_name").val('${Edit_JCOsCMD.father_name}');
	$("#father_dob").val(ParseDateColumncommission('${Edit_JCOsCMD.father_dob}'));
	$("#father_place_birth").val('${Edit_JCOsCMD.father_place_birth}');
	$("#father_service").val('${Edit_JCOsCMD.father_service}');
	$("#father_other").val('${Edit_JCOsCMD.father_other}');
	$("#father_personal_no").val('${Edit_JCOsCMD.father_personal_no}');
	$("#father_profession").val('${Edit_JCOsCMD.father_profession}');
	$("#mother_name").val('${Edit_JCOsCMD.mother_name}');
	$("#mother_dob").val(ParseDateColumncommission('${Edit_JCOsCMD.mother_dob}'));
	$("#mother_place_birth").val('${Edit_JCOsCMD.mother_place_birth}');
	$("#mother_service").val('${Edit_JCOsCMD.mother_service}');
	$("#mother_other").val('${Edit_JCOsCMD.mother_other}');
	$("#mother_personal_no").val('${Edit_JCOsCMD.mother_personal_no}');
	$("#mother_profession").val('${Edit_JCOsCMD.mother_profession}'); 
	
	$("#country_other").val('${Edit_JCOsCMD.country_other}'); 
	$("#state_other").val('${Edit_JCOsCMD.state_other}'); 
	$("#district_other").val('${Edit_JCOsCMD.district_other}'); 
	$("#nationality_other").val('${Edit_JCOsCMD.nationality_other}'); 
	$("#mother_tongue_other").val('${Edit_JCOsCMD.mother_tongue_other}'); 
	$("#religion_other").val('${Edit_JCOsCMD.religion_other}'); 
	$("#gender_other").val('${Edit_JCOsCMD.gender_other}'); 
	siblingCB(1);
	spouseCB(1);
	//fn_country_birth();
	//fn_state_birth();
	//fn_district_birth();
	fn_nationality();
	fn_mother_tongue();
	fn_religion();
	fn_gender();
	rank_intake_jco();
	getmarital_status_jco() ;
	fn_domicile();
	fn_category_rank();

		if('${Edit_JCOsCMD.rank_intake}' !=null && '${Edit_JCOsCMD.rank_intake}' != "0"){
			$("#intake").show();
		}
		else
			{
			$("#intake").hide();
			$("#rank_intake").val("0");
			}
	$("#rank_intake").val('${Edit_JCOsCMD.rank_intake}');
	
	 
// 	var a = ('${Edit_JCOsCMD.marital_status}');
	
// 	if(a == "9" || a == "12" || a == "11" || a == "13") {
// 		$("#fill_marraige_div").hide();
// 		$("#fill_divorce_div").show();
// 	}
// 	if(a == "8") {
// 		$("#fill_marraige_div").show();
// 		$("#fill_divorce_div").show();
// 	}
// 	if(a == "10" || a == "0") {
// 		$("#fill_marraige_div").hide();
// 		$("#fill_divorce_div").hide();
// 	}

	
	if('${Edit_JCOsCMD.father_profession}' != "0" && '${Edit_JCOsCMD.father_profession}' != ""){			

		
		$("#father_proff_radio2").prop("checked", true);
		$("#father_inserviceDiv").hide();
		$("#father_proffDiv").show();
		
		
		}
	else{		
		
		$("#father_proff_radio1").prop("checked", true);
		$("#father_inserviceDiv").show();
		$("#father_proffDiv").hide();
		
		if('${Edit_JCOsCMD.father_other}' !=null && '${Edit_JCOsCMD.father_other}' != ""){									
			$("#father_inserviceDiv").show();
			$("#father_proffDiv").hide();
			$("#father_otherDiv").show();
			$("#father_personalDiv").hide();
			}
		
		}
			
		var father_proff=$("#father_profession option:selected").text().toUpperCase();
		
		if(father_proff=="OTHER" || father_proff=="OTHERS"){
		    $("#father_proffotherDiv").show();
		}
	
		var mother_proff=$("#mother_profession option:selected").text().toUpperCase();
		if(mother_proff=="OTHER" || mother_proff=="OTHERS"){
			$("#mother_proffotherDiv").show();
		  }
		
		if('${Edit_JCOsCMD.mother_profession}' != "0" && '${Edit_JCOsCMD.mother_profession}' != ""){			

			
			$("#mother_proff_radio2").prop("checked", true);
			$("#mother_inserviceDiv").hide();
			$("#mother_proffDiv").show();
			
			
			}
		else{		
			
			$("#mother_proff_radio1").prop("checked", true);
			$("#mother_inserviceDiv").show();
			$("#mother_proffDiv").hide();
			
			if(  '${Edit_JCOsCMD.mother_other}' !=null && '${Edit_JCOsCMD.mother_other}' != ""){									
				$("#mother_inserviceDiv").show();
				$("#mother_proffDiv").hide();
				$("#mother_otherDiv").show();
				$("#mother_personalDiv").hide();
				}
			
			}
	$("#father_proffother").val('${Edit_JCOsCMD.father_proffother}'); 
	$("#mother_proffother").val('${Edit_JCOsCMD.mother_proffother}');
	
	var text = ('${Edit_JCOsCMD.class_enroll}');
	if(text == "10"){
		$("#gorkha_domicile").show();
	}
	else{
		 $("#gorkha_domicile").hide();
		  $("#domicile").val("0");
	}
	$("#domicile").val('${Edit_JCOsCMD.domicile}');
	
 
	    getfamily_siblingDetails();
		getfamily_marriageDetails();
		getfamily_divorceDetails();
		getspouseQualification();
		
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	///////////////////
   var sus_no = '${roleSusNo}';
  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
  		var l=data.length-1;
  		var enc = data[l].substring(0,16);    	   	 
  	 	$("#unit_name").text(dec(enc,data[0]));
  		}).fail(function(xhr, textStatus, errorThrown) {
	  }); 
  	
  	
  	
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
</script>
		<script>
	
		// Unit SUS No

		$("#unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var susNoAuto=$("#unit_sus_no");

			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
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
			        	  alert("Please Enter Approved Unit SUS No.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
				var unit_name = this.value;
					 var susNoAuto=$("#unit_name");
					  susNoAuto.autocomplete({
					      source: function( request, response ) {
					        $.ajax({
					        	type: 'POST',
						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
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
					        	  alert("Please Enter Approved Unit Name.");
					        	  document.getElementById("unit_name").value="";
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
							        	$("#unit_sus_no").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
					}); 			
			});
		 $("#record_office").keyup(
					function(){
						
						var record_office = this.value;
						var susNoAuto = $("#record_office");
						susNoAuto.autocomplete({
									source : function(request, response) {
										$.ajax({
													type : 'POST',
													url : "getRecordoffice_UnitNameList?"
															+ key + "=" + value,
													data : {
														record_office : record_office
													},
													success : function(data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval.push(dec(
																	enc,
																	data[i]));
														}
														var dataCountry1 = susval
																.join("|");
														var myResponse = [];
														var autoTextVal = susNoAuto
																.val();
														$
																.each(
																		dataCountry1
																				.toString()
																				.split(
																						"|"),
																		function(
																				i,
																				e) {
																			var newE = e
																					.substring(
																							0,
																							autoTextVal.length);
																			if (e
																					.toLowerCase()
																					.includes(
																							autoTextVal
																									.toLowerCase())) {
																				myResponse
																						.push(e);
																			}
																		});
														response(myResponse);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit Name");
											document
													.getElementById("record_office_sus").value = "";
											susNoAuto.val("");
											susNoAuto.focus();
											return false;
										}
									},
									select : function(event, ui) {
										var record_office = ui.item.value;
										$.post("getRecordofficeSUSNoFromUnitName?" + key
														+ "=" + value, {
													record_office : record_office
												}, function(j) {

												}).done(
												function(j) {
													var length = j.length - 1;
													var enc = j[length]
															.substring(0, 16);
													$("#record_office_sus").val(
															dec(enc, j[0]));
													$("#record_office").attr('readonly', true);

												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});
		</script>

			
<script>
/////////////////////family siblings////////////////////////
	
  aller=1;
  function family_add_fn(q){
	 
  
  	if(q!=0){
  	$("#family_add"+aller).hide();
  	$("#family_remove"+aller).hide();
  	}
  	aller=q+1;
 
 
  	 $("input#siblings_count").val(aller);
  	

  	$("table#family_table").append('<tr align="center" id="tr_sibling'+aller+'"><td style="width: 2%;">'+aller+'</td>'
  	+'<td><input type="text" id="sib_name'+aller+'" name="sib_name'+aller+'" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">  </td>'
  //	+'<td style="width: 10%;"> <input type="date" id="sib_date_of_birth'+aller+'" name="sib_date_of_birth'+aller+'" class="form-control autocomplete" autocomplete="off" ></td>'	
	+ '<td style="width: 10%;"> <input type="text" name="sib_date_of_birth' + aller + '" id="sib_date_of_birth' + aller + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
  +'<td style="width: 10%;"> <select name="sib_relationship'+aller+'" id="sib_relationship'+aller+'" class="form-control-sm form-control"   >'
	+'<option value="0">--Select--</option>'
	+'<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">'
	+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
	+'</c:forEach></select></td>'
	+ '<td style="width: 15%;"><input type="text" ' + '	id="sib_adhar_number' + aller + '" name="sib_adhar_number' + aller + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off"  ></td> ' 
	+ '<td style="width: 15%;"> <input type="text" id="sib_pan_no' + aller + '" name="sib_pan_no' + aller + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  > ' + '	</td> ' 
	
	+ '<td><input type="checkbox" ' + '	id="ser-ex' + aller + '" name="ser-ex' + aller + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + aller + ');"></td> '
	+ '<td> <select name="sibling_service' + aller + '" id="sibling_service' + aller + '" class="form-control-sm form-control"  onchange = "otherfunction(' + aller + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	 maxlength="15" id="sibling_personal_no' + aller + '" name="sibling_personal_no' + aller + '" ' + '	class="form-control" autocomplete="off" ></td> '
	+ '<td><input type="text" ' + '	id="other_sibling_ser' + aller + '" name="other_sibling_ser' + aller + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'
  	
	+'<td style="display: none;"><input type="text" id="sib_ch_id'+aller+'" name="sib_ch_id'+aller+'" value="0" class="form-control autocomplete" autocomplete="off"></td>'
  	+'<td style="width: 10%;"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add'+aller+'" onclick="family_add_fn('+aller+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove'+aller+'" onclick="family_remove_fn('+aller+');"><i class="fa fa-trash"></i></a></td>' 
      +'</tr>');
  	
  	datepicketDate('sib_date_of_birth' + aller);
	siblingCB(aller);
	otherfunction(aller);
	isAadhar(document.getElementById('sib_adhar_number' + aller))
  		 if(aller==1){
  			 $("#family_remove"+aller).hide();
  		 }	

  }
  marr=1;
  function married_add_fn(q) {

		
		if(q != 0) {
		 	$("#married_add"+marr).hide();
		  	$("#married_remove"+marr).hide();
		}
		   marr = q + 1;
	 	 $("input#spouse_count").val(marr);
		//var marriage_othernationality='marriage_othernationality'+marr;
		$("table#married_table").append('<tr align="center" id="tr_marriage' + marr + '"><td style="width: 2%;">'+marr+'</td>'
             + '<td> <input type="text" id="maiden_name' + marr + '" name="maiden_name' + marr + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" ></td>' + '<td> ' + ' <input type="text" name="marriage_date_of_birth' + marr + '" id="marriage_date_of_birth' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
			
			+ '<td> <input type="text" id="marriage_place_of_birth' + marr + '" name="marriage_place_of_birth' + marr + '" class="form-control autocomplete" autocomplete="off" maxlength="50"></td>' + '<td>' + ' <select id="marriage_nationality' + marr + '" name="marriage_nationality' + marr + '" class="form-control-sm form-control"   onchange="marriageNationChange('+marr+')">' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select> </td>'
			+ '<td><input type="text" id="marriage_othernationality' + marr + '" name="marriage_othernationality' + marr + '" '
			+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly" maxlength="50">'
			+ '	</td>'
			 + '<td> ' 
			+ ' <input type="text" name="marriage_date' + marr + '" id="marriage_date' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
			
			+ '<td> <input type="text" id="spouse_adhar_number' + marr + '" name="spouse_adhar_number' + marr + '"  onkeypress="return isAadhar(this,event);"   maxlength="14" class="form-control autocomplete" autocomplete="off"  ></td>' + '<td> ' + '	<input type="text" id="spouse_pan_number' + marr + '" name="spouse_pan_number' + marr + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="marr_ch_id' + marr + '" name="marr_ch_id' + marr + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+ '<td><input type="checkbox" ' + '	id="spo-ex' + marr + '" name="spo-ex' + marr + '" ' + '	value="Yes"  ' + '	onclick="spouseCB(' + marr + ');"></td> '
			+ '<td> <select name="spouse_service' + marr + '" id="spouse_service' + marr + '" class="form-control-sm form-control"  onchange="otherfunction(' + marr + ')"  >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td style="width: 15%;"><input type="text" ' + '	maxlength="15" id="spouse_personal_no' + marr + '" name="spouse_personal_no' + marr + '" ' + '	class="form-control" autocomplete="off" ></td> '
			+ '<td><input type="text" ' + '	id="other_spouse_ser' + marr + '" onkeypress="return onlyAlphaNumeric(event);" name="other_spouse_ser' + marr + '" ' + '	class="form-control" autocomplete="off" ></td>'
			
			 + '<td class="sepratedSpouseClass"> ' 
				+ ' <input type="text" name="sep_from_date' + marr + '" id="sep_from_date' + marr + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
		
			+'<td style="display:none;"><input type="text" id="marr_ch_id' + marr + '" name="marr_ch_id' + marr + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+ '<td class="hide-action"><a style="display:none;" class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "married_save' + marr + '" onclick="married_save_fn(' + marr + ');" ><i class="fa fa-save"></i></a>'
// 			+'<a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "married_add'+marr+'" onclick="married_add_fn('+marr+');" ><i class="fa fa-plus"></i></a>'
// 			+ '<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "married_remove' + marr + '" onclick="married_remove_fn(' + marr + ');"><i class="fa fa-trash"></i></a>'
			+ '</td></tr>');
		datepicketDate('marriage_date_of_birth' + marr);
		datepicketDate('marriage_date' + marr);
		datepicketDate('sep_from_date' + marr);
		marriageNationChange(marr);
		spouseCB(marr);
		otherfunction(marr);
		
		 if(marr==1){
				 $("#married_remove"+marr).hide();
			 }	
		 
		 if($("#marital_status").val()=='Separated'){
			 $(".sepratedSpouseClass").show();	
		 }else{
			 $(".sepratedSpouseClass").hide();	
		 }

		//$("input#spouse_count").val(v);
		//$("input#spouseOld_count").val(v);
		
	}


  divo = 1;


  function divorce_add_fn(q) {
  	
 
  	if(q != 0) {
	 	$("#divorce_add"+q).hide();
	  	$("#divorce_remove"+q).hide();
	}
  	divo = q + 1;
  	 $("input#divorce_count").val(divo);
  	 
  	$("table#divorce_table").append('<tr id="tr_divorce' + divo + '"><td style="width: 2%;">'+divo+'</td>'
  			+'<td style="width: 10%;"><select name="marital_event' + divo + '" '
			+' id="marital_event' + divo + '" '
			+' 	class="form-control" >'
			+'		<option value="0">--Select--</option>'
			+'	<c:forEach var="item" items="${getMarital_statusList}"	varStatus="num">'
			+'		<c:if test="${item[0]==9 || item[0]==11 || item[0]==12}">'
			+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
			+'	</c:if>'
			+'	</c:forEach>'
			+'</select></td>'
  	+ '<td > <input type="text" id="spouse_name' + divo + '" name="spouse_name' + divo + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" ></td>'
  		
  		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date_of_birth' + divo + '" id="divorce_date_of_birth' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td >' + '<input type="text" id="divorce_place_of_birth' + divo + '" name="divorce_place_of_birth' + divo + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  class="form-control autocomplete" autocomplete="off" maxlength="50">' + ' </td><td >'
  		+ '<select name="divorce_nationality' + divo + '" id="divorce_nationality' + divo + '" class="form-control-sm form-control"  onchange="divorceNationChange('+divo+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
  		+ '<td style="width: 13%;"><input type="text" id="divorce_othernationality' + divo + '" name="divorce_othernationality' + divo + '" '
  		+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
  		+ '	</td>'
  		
  		+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_marriage_date' + divo + '" id="divorce_marriage_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + ' <td ><input type="text" id="divorce_spouse_adhar_number' + divo + '" name="divorce_spouse_adhar_number' + divo + '"  onkeypress="return isAadhar(this,event);" class="form-control autocomplete" maxlength="14" autocomplete="off" >' + ' </td>'
  		
  		+ '<td style="width: 10%;"> ' + '	<input type="text" id="divorce_spouse_pan_number' + divo + '" name="divorce_spouse_pan_number' + divo + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" > ' + '	</td> ' + '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date' + divo + '" id="divorce_date' + divo + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td style="display:none;"><input type="text" id="divo_ch_id' + divo + '" name="divo_ch_id' + divo + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action"> <a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "divorce_add' + divo + '" onclick="divorce_add_fn(' + divo + ');" ><i class="fa fa-plus"></i></a>' + '<a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "divorce_remove' + divo + '" onclick="divorce_remove_fn(' + divo + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
  	datepicketDate('divorce_date_of_birth' + divo);
  	datepicketDate('divorce_marriage_date' + divo);
  	datepicketDate('divorce_date' + divo);
  	//divorceNationChange(divo);
  	 if(divo==1){
		 $("#divorce_remove"+divo).hide();
	 }	

  }
  function siblingCB(a) {
	 
		if ($('#ser-ex'+a).is(":checked"))
		{
			var sx = $("#sibling_service"+a+" option:selected" ).text();
			
			$('#sibling_service' + a).attr('readonly', false);
			if(sx="OTHER"){
				$('#sibling_personal_no' + a).attr('readonly', true);
			}
			else{
				$('#sibling_personal_no' + a).attr('readonly', false);
			}
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

  function family_remove_fn(R){
  	 
  	 var rc = confirm("Are You Sure! You Want To Delete");
  		if(rc) {
  			var sib_ch_id = $('#sib_ch_id' + R).val();
  			if(sib_ch_id == 0){
  				 $("tr#tr_sibling"+R).remove();	
  				 aller=aller-1
  				 $("input#siblings_count").val(aller);
  				 $("#family_add"+aller).show();
  				 if(aller!=1){
  				 $("#family_remove"+aller).show();
  				 }
  			}else{
  			$.post('family_sibling_delete_action_jco?' + key + "=" + value, {
  				sib_ch_id: sib_ch_id
  			}, function(data) {
  				if(data == '1') {
  					$("tr#tr_sibling" + R).remove();
  					if(R == aller) {
  						R = R - 1;
  						var temp = true;
  						for(i = R; i >= 1; i--) {
  							if($('#family_add' + i).length) {
  								$("#family_add" + i).show();
  								temp = false;
  								aller = i;
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
  				
  					
  					getfamily_siblingDetails();
  					alert("Data Deleted Successfully");
  				} else {
  					alert("Data not Deleted ");
  				}
  			}).fail(function(xhr, textStatus, errorThrown) {
  				alert("fail to fetch")
  			});
  		 }
  		}	
  }

  function married_remove_fn(R) {
	 	
		 var rc = confirm("Are You Sure! You Want To Delete");
			if(rc) {
				var marr_ch_id = $('#marr_ch_id'+R).val();
				//var marr_ch_id = $('#marr_ch_id1').val();
				if(marr_ch_id == 0){				
					$("tr#tr_marriage"+marr).remove();	
					 marr=R-1
					 $("input#spouse_count").val(marr);
					 $("#married_add"+marr).show();
					 if(marr!=1){
					 $("#married_remove"+marr).show();
					 }
					 married_add_fn(0);
				}else{
				$.post('family_marriage_delete_action_jco?' + key + "=" + value, {
					marr_ch_id: marr_ch_id
				}, function(data) {
					if(data == '1') {
						$("tr#tr_marriage" + R).remove();
					
						//married_add_fn(0);
						
						$('#spouse_quali_type').val('0');
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
						//fn_qualification_typeChange(typethisT,'spouse_quali','quali_degree_spouse','spouse_specialization');
						getfamily_marriageDetails();
						alert("Data Deleted Successfully");
						if(R ==1){
							married_add_fn(0);
							
						}
					var	a=$("#marital_status").val();
						if(a == "9" || a == "12" || a == "11" || a == "10") {
							$("#fill_marraige_div").hide();							
						}
					} else {
						alert("Data not Deleted ");
					}
				}).fail(function(xhr, textStatus, errorThrown) {
					alert("fail to fetch")
				});
				}
			}	
	}


function getfamily_marriageDetails() {
	
	var p_id1 = '${Edit_JCOsCMD.id}';
	
	var i = 0;
	$.post('getfamily_marriageData_jco?' + key + "=" + value, {
		p_id1: p_id1
	}, function(j) {
	
		var v = j.length;
		if(v != 0) {
			
			//$('#married_table').show();
			$('#family_marrtbody').empty();
			for(i; i < v; i++) {
				y = i + 1;
	
				var pan_card = "";
				if(j[i].pan_card != null) pan_card = j[i].pan_card;
				var other_nation=""
					if(j[i].other_nationality != null) other_nation = j[i].other_nationality;
				
				$("table#married_table").append('<tr id="tr_marriage' + y + '"><td style="width: 2%;">'+y+'</td>' 
						+ '<td> <input type="text" id="maiden_name' + y + '" name="maiden_name' + y + '" onkeypress="return onlyAlphabets(event);"  value="' + j[i].maiden_name + '" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" ></td>' + '<td style="width: 10%;"> ' + ' <input type="text" name="marriage_date_of_birth' + y + '" id="marriage_date_of_birth' + y + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' + '<td> <input type="text" id="marriage_place_of_birth' + y + '" name="marriage_place_of_birth' + y + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"  value="' + j[i].place_of_birth + '" class="form-control autocomplete" autocomplete="off" maxlength="50"></td>' + '<td> ' + ' <select id="marriage_nationality' + y + '" name="marriage_nationality' + y + '" onchange="marriageNationChange('+y+')" class="form-control-sm form-control"   >' + ' <option value="0">--Select--</option>' + '	<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '	</c:forEach>' + '</select></td>' 
						+ '<td><input type="text" id="marriage_othernationality' + y + '" name="marriage_othernationality' + y + '"  value="'+other_nation+'"'
						+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
						+ '	</td>'
				+ '<td> '
					+ ' <input type="text" name="marriage_date' + y + '" id="marriage_date' + y + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].marriage_date) + '">' + '</td>'
					
					+ '<td> <input type="text" id="spouse_adhar_number' + y + '" name="spouse_adhar_number' + y + '"  onkeypress="return isAadhar(this,event);"  maxlength="14"  value="' + j[i].adhar_number + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td> ' + '	<input type="text" id="spouse_pan_number' + y + '" name="spouse_pan_number' + y + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  value="' + pan_card + '"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="marr_ch_id' + y + '" name="marr_ch_id' + y + '"  value="' + j[i].id + '"class="form-control autocomplete" autocomplete="off" ></td>' 
					+ '<td><input type="checkbox" ' + '	id="spo-ex' + y + '" name="spo-ex' + y + '" ' + '	value="Yes"  ' + '	onclick="spouseCB(' + y + ');"></td> '
					+ '<td> <select name="spouse_service' + y + '" id="spouse_service' + y + '" class="form-control-sm form-control"  onchange="otherfunction(' + y + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + ' maxlength="15"	id="spouse_personal_no' + y + '" name="spouse_personal_no' + y + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].spouse_personal_no + '" ></td> '
					+ '<td><input type="text" ' + '	id="other_spouse_ser' + y + '" name="other_spouse_ser' + y + '" ' + '	onkeypress="return onlyAlphaNumeric(event);" class="form-control" autocomplete="off" ></td>'
					 + '<td class="sepratedSpouseClass"> ' 
						+ ' <input type="text" name="sep_from_date' + y + '" id="sep_from_date' + y + '" value="' + ParseDateColumncommission(j[i].separated_from_dt) + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
				
					+ '<td class="hide-action"><input type="hidden" id="marr_ch_id' + y + '" name="marr_ch_id' + y + '"  value="' + j[i].id + '"class="form-control autocomplete" autocomplete="off" >'
// 					+'<a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "married_add'+ y +'" onclick="married_add_fn('+ y +');" ><i class="fa fa-plus"></i></a>'
					+ '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "married_remove' + y + '" onclick="married_remove_fn(' + y + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
				$("#marriage_nationality" + y).val(j[i].nationality);
				
				$('#spouse_service' + y).val(j[i].spouse_service);
				$('#other_spouse_ser' + y).val(j[i].other_spouse_ser);
				if(j[i].if_spouse_ser =="Yes"){
					$("input[type=checkbox][name='spo-ex"+y+"']").prop("checked",true);

				}
				isAadhar(document.getElementById('spouse_adhar_number' + y))
				datepicketDate('marriage_date_of_birth' + y);
				datepicketDate('marriage_date' + y);
				datepicketDate('sep_from_date' + y);
				
				marriageNationChange(y);
				spouseCB(y);
				otherfunction(y);
			}
			  marr = v;
			
			$("input#spouse_count").val(v);
			$("input#spouseOld_count").val(v);
			$('#married_add'+ marr).show();
			getmarital_status_jco() ;
		}
		
		else{
// 			  married_add_fn(0);
			$("input#spouse_count").val('1');
			$("input#spouseOld_count").val('0');
		}
	
	});
}
////////////////
 
 function getfamily_divorceDetails() {
	var p_id1 = $('#jco_id').val();
	
	var i = 0;
	$.post('getfamily_divorceData_jco?' + key + "=" + value, {
		p_id1: p_id1
	}, function(j) {
	//getfamily_marriageData_jco
		var v = j.length;
		if(v != 0) {
			$('#family_divotbody').empty();
			for(i; i < v; i++) {
				x = i + 1;
	
				var pan_card = "";
				if(j[i].pan_card != null) pan_card = j[i].pan_card;
				var other_nation=""
					if(j[i].other_nationality != null) other_nation = j[i].other_nationality;
				
				$("table#divorce_table").append('<tr id="tr_divorce' + x + '"><td style="width: 2%;">'+x+'  </td>'
				
						+'<td style="width: 10%;"><select name="marital_event' + x + '" '
						+' id="marital_event' + x + '" '
						+' 	class="form-control" >'
						+'		<option value="0">--Select--</option>'
						+'	<c:forEach var="item" items="${getMarital_statusList}"	varStatus="num">'
						+'		<c:if test="${item[0]==9 || item[0]==11 || item[0]==12}">'
						+'		<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+'	</c:if>'
						+'	</c:forEach>'
						+'</select></td>'
						
						  + '<td > <input type="text" id="spouse_name' + x + '" name="spouse_name' + x + '" value="' + j[i].maiden_name + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" ></td>'
							+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date_of_birth' + x + '" id="divorce_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' + '<td style="width: 15%;">' + '<input type="text" id="divorce_place_of_birth' + x + '" name="divorce_place_of_birth' + x + '" value="' + j[i].place_of_birth + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" maxlength="50">' 
							+ ' </td><td>' 
							+ '<select name="divorce_nationality' + x + '" id="divorce_nationality' + x + '" class="form-control-sm form-control"  onchange="divorceNationChange('+x+')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getNationalityList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select> </td>'
							+ '<td style="width: 13%;"><input type="text" id="divorce_othernationality' + x + '" name="divorce_othernationality' + x + '" value="'+other_nation+'"'
							+ '	class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" readonly="readonly">'
							+ '	</td>'
							+ '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_marriage_date' + x + '" id="divorce_marriage_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].marriage_date) + '">' + '</td>' + ' <td ><input type="text" id="divorce_spouse_adhar_number' + x + '" name="divorce_spouse_adhar_number' + x + '"  onkeypress="return isAadhar(this,event);"  value="' + j[i].adhar_number + '" class="form-control autocomplete" maxlength="14" autocomplete="off" >'
							+ '<td style="width: 10%;"> ' + '	<input type="text" id="divorce_spouse_pan_number' + x + '" name="divorce_spouse_pan_number' + x + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  value="' + pan_card + '"> ' + '	</td> ' + '<td style="width: 10%;"> ' + ' <input type="text" name="divorce_date' + x + '" id="divorce_date' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].divorce_date) + '">' + '</td>' + '<td style="display:none;"><input type="text" id="divo_ch_id' + x + '" name="divo_ch_id' + x + '"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>' + '<td class="hide-action" ><a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "divorce_add' + x + '" onclick="divorce_add_fn(' + x + ');" ><i class="fa fa-plus"></i></a>' + '<a  class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "divorce_remove' + x + '" onclick="divorce_remove_fn(' + x + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
						$("#divorce_nationality" + x).val(j[i].nationality);
						$("#marital_event" + x).val(j[i].marital_status);
						isAadhar(document.getElementById('divorce_spouse_adhar_number' + x));
						datepicketDate('divorce_date_of_birth' + x);
						datepicketDate('divorce_marriage_date' + x);
						datepicketDate('divorce_date' + x);
						divorceNationChange(x);

		
			}
			  divo = v;
			
			$("input#divorce_count").val(v);
			$("input#divorceOld_count").val(v);
			$('#divorce_add'+ divo).show();
		}
		
		else{
			//divorce_add_fn(0);
			$("input#divorce_count").val('1');
			$("input#divorceOld_count").val('0');
		}
	
	});
} 

 function getfamily_siblingDetails(){
 	
 	var p_id=$('#jco_id').val();	
 	 var i=0;
 	  $.post('getjco_siblingData?' + key + "=" + value, {p_id:p_id }, function(j){
 			var v=j.length;
 			$("#family_sibtbody").empty();
 		if(v!=0){
 			
 			for(i;i<v;i++){			
 				x=i+1;	
 				
 			$("table#family_table").append('<tr align="center" id="tr_sibling'+x+'"><td style="width: 2%;">'+x+'</td>'
 				  	+'<td><input type="text" id="sib_name'+x+'" name="sib_name'+x+'" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()">  </td>'
 				  	+ '<td style="width: 10%;"> <input type="text" name="sib_date_of_birth' + x + '" id="sib_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY"></td>'
 				  	 +'<td style="width: 10%;"> <select name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control-sm form-control"   >'
 				  	+'<option value="0">--Select--</option>'
 				  	+'<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">'
 				  	+'<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
 				  	+'</c:forEach></select></td>'
 				  	+ '<td style="width: 15%;"><input type="text" ' + '	id="sib_adhar_number' + x + '" name="sib_adhar_number' + x + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off"  ></td> ' 
 				  	+ '<td style="width: 15%;"> <input type="text" id="sib_pan_no' + x + '" name="sib_pan_no' + x + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"  > ' + '	</td> ' 
 				  	+ '<td><input type="checkbox" ' + '	id="ser-ex' + x + '" name="ser-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + x + ');"></td> '
 				  	+ '<td> <select name="sibling_service' + x + '" id="sibling_service' + x + '" class="form-control-sm form-control"  onchange = "otherfunction(' + x + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	 maxlength="15" id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" ></td> '
 				  	
 				  	+ '<td><input type="text" ' + '	id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'
 				  	+'<td style="display: none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'" class="form-control autocomplete" autocomplete="off"></td>'
 				    	+'<td style="width: 10%;"><a style="display: none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add'+x+'" onclick="family_add_fn('+x+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove'+x+'" onclick="family_remove_fn('+x+');"><i class="fa fa-trash"></i></a></td>' 
 				        +'</tr>');		
 			
 					
 			$('#sib_name'+x).val( j[i].name );	
 			
 			$('#sib_date_of_birth' + x).val(ParseDateColumncommission(j[i].date_of_birth));
 			datepicketDate('sib_date_of_birth' + x);
 			//datepicketDate('sib_date_of_birth' + x).val(j[i].date_of_birth );
 			$('#sib_relationship' + x).val(j[i].relationship);
 			$('#sib_adhar_number' + x).val(j[i].aadhar_no);
 			$('#sib_pan_no' + x).val(j[i].pan_no);
 			$('#sibling_service' + x).val(j[i].sibling_service);
 			$('#other_sibling_ser' + x).val(j[i].other_sibling_ser);
 			$('#sibling_personal_no' + x).val(j[i].sibling_personal_no);
 			$('#sib_ch_id'+x).val(j[i].id);		 
 			
 			if(j[i].if_sibling_ser =="Yes"){
 				$("input[type=checkbox][name='ser-ex"+x+"']").prop("checked",true);

 			}
 			siblingCB(x);

 			otherfunction(x);
 			isAadhar(document.getElementById('sib_adhar_number' + x));
 		}
 			aller=v;
 			$("input#siblings_count").val(v);
 			$("input#siblingsOld_count").val(v);
 			$("#family_add"+v).show(); 

 			}
 		else{
 			family_add_fn(0);
 			$("input#siblings_count").val('1');
 			$("input#siblingsOld_count").val('0');
 		}		
 	  }); 
 }

///////qualification
function getspouseQualification(){
	 $.ajaxSetup({
         async : false
 	});
	var p_id=$('#jco_id').val();	

	  $.post('getspouse_qualiData_jco?' + key + "=" + value, {p_id:p_id }, function(j){
			
			var v = j.length;
			
			if(v != 0) {
				
				if($('#spouse_yrOfPass').val(j[0].passing_year) != "")
				{
				
					$("#spouse_quali_radio1").prop("checked", true);
					$("#spouse_Qualifications").show();
				}

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
				
		/* 		if(j[0].examination_pass != null) {
					$('#spouse_quali').val(j[0].examination_pass);
					var qualithisT = document.getElementById('spouse_quali');
					fn_ExaminationTypeChange(qualithisT,'quali_degree_spouse','spouse_specialization');
				}
				
				if(j[0].degree != null) {
					$('#quali_degree_spouse').val(j[0].degree);
					
				} */
			/* 	if(j[0].specialization != null) {
				$('#spouse_specialization').val(j[0].specialization);
				 fn_otherShowHide(document.getElementById('spouse_specialization'),'other_div_qualiSpecSpouse','quali_spec_otherSpouse');
				}
				 */
			
				var examother="";
				var classother="";
				var deg_other="";
				var spec_other="";
				
				
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
		  	    
		  	
				$('#exam_otherSpouse').val(j[0].exam_other);
			 	
		 	  	$('#class_otherSpouse').val(j[0].class_other);
		 	 
		 		 $('#quali_deg_otherSpouse').val(j[0].degree_other);
		 	
		 		 $('#quali_spec_otherSpouse').val(j[0].specialization_other);

		 		 fn_otherShowHide(document.getElementById('spouse_div_class'),'other_div_classSpouse','class_otherSpouse');

		 	 
				/* $("#spouse_quali_radio1").prop("checked", true);
				$("#spouse_Qualifications").show(); */
			}
		
	  }); 
	  
}
</script>	
<script>

var adhar = '${pers_aadhar_no77}';
var arrTo=adhar.substring(0, 4);
var arrTo1=adhar.substring(4, 8);
var arrTo2=adhar.substring(8);

$("#pers_aadhar_no11").val(arrTo);
$("#pers_aadhar_no2").val(arrTo1);
$("#pers_aadhar_no3").val(arrTo2); 
/* $('#country_of_birth').val(k[0].country_of_birth);  
fn_country_of_birth();
$('#state_of_birth').val(k[0].state_of_birth); 
fn_state_of_birth();
$('#district_of_birth').val(k[0].district_of_birth);  */


function lengthadhar() {
	document.getElementById("adhar_number1").maxLength = "4";
	document.getElementById("adhar_number1").minLength = "4";
	document.getElementById("adhar_number2").maxLength = "4";
	document.getElementById("adhar_number2").minLength = "4";
	document.getElementById("adhar_number3").maxLength = "4";
	document.getElementById("adhar_number3").minLength = "4";	  
} 
	
function movetoNext(current, nextFieldID) {  
	if (current.value.length >= current.maxLength) {  
		document.getElementById(nextFieldID).focus();  
	}  
}

//PERSONAL PAGE
function fn_country_birth() {
	 var text = $("#country_of_birth option:selected").text();
	 $("#country_of_birth_hid").val(text);
		if(text.toUpperCase() == "OTHERS"){
			$("#country_birth_other_hid").show();
		}
		else{
			$("#country_birth_other_hid").hide();
			$("#country_other").val("");
		} 
		var contry_id = $('select#country_of_birth').val();
		$.post("getStateListFormcon1?" + key + "=" + value, {
			contry_id: contry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#state_of_birth").html(options);
			fn_state_birth();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}

	function fn_state_birth() {
	 var text = $("#state_of_birth option:selected").text();
	 $("#state_of_birth_hid").val(text);
		if(text.toUpperCase() == "OTHERS"){
			$("#state_birth_other_hid").show();
		}
		else{
			$("#state_birth_other_hid").hide();
			$("#state_other").val("");
		} 
		var state_id = $('select#state_of_birth').val();
		$.post("getDistrictListFormState1?" + key + "=" + value, {
			state_id: state_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#district_of_birth").html(options);
			fn_district_birth();
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
	 function fn_district_birth() {
		 var text = $("#district_of_birth option:selected").text();
		 $("#district_of_birth_hid").val(text);
			if(text.toUpperCase() == "OTHERS"){
				$("#district_birth_other_hid").show();
			}
			else{
				$("#district_birth_other_hid").hide();
				
				$("#district_other").val("");
			} 
			
	 	}
	 
	 function fn_nationality() {
		 var text = $("#nationality option:selected").text();
		$("#nationality_hid").val(text);
			if(text.toUpperCase() == "OTHERS"){
				$("#nationality_other_hid").show();
			}
			else{
				$("#nationality_other_hid").hide();
				$("#nationality_other").val("");
			}
	 		 
	 	}
	 function fn_mother_tongue() {
		 var text = $("#mother_tongue option:selected").text();
		 $("#mother_tongue_hid").val(text);
			if(text.toUpperCase() == "OTHERS"){
				$("#mother_tongue_other_hid").show();
			}
			else{
				$("#mother_tongue_other_hid").hide();
				$("#mother_tongue_other").val("");
			}
	 		 
	 	}
	 function fn_religion() {
		 var text = $("#religion option:selected").text();
		 $("#religion_hid").val(text);
			if(text.toUpperCase() == "OTHERS"){
				$("#religion_other_hid").show();
			}
			else{
				$("#religion_other_hid").hide();
				$("#religion_other").val("");
			}
	 		 
	 	}
	 
	 function fn_gender() {
		 var text = $("#gender option:selected").text();
		 $("#gender_hid").val(text);
			if(text.toUpperCase() == "OTHER"){
				$("#gender_other_hid").show();
			}
			else{
				$("#gender_other_hid").hide();
				$("#gender_other").val("");
			}
	 		 
	 	}
	 function fn_domicile()
	 {
	 	var a = $("#class_enroll option:selected").val();
	 	if(a == "10") {

	 		  $("#gorkha_domicile").show();

	 	} 
	 	else {
	 		  $("#gorkha_domicile").hide();
	 		  $("#domicile").val("0");
	 	
	 	}
	 }


</script>

<script>

function onChangeArmyNo(obj)
{
	var data = obj.value;
	
	if(data.length == 1){
		var suffix="";
 	    var summation =+2*parseInt(data[0]);
	}
	if(data.length == 2){
		var suffix="";
 	    var summation =+3*parseInt(data[0])+2*parseInt(data[1]);
	}
	if(data.length == 3){
		var suffix="";
 	    var summation =+4*parseInt(data[0])+3*parseInt(data[1])+2*parseInt(data[2]);
	}
	if(data.length == 4){
		var suffix="";
 	    var summation =+5*parseInt(data[0])+4*parseInt(data[1])+3*parseInt(data[2])+2*parseInt(data[3]);
	}
	if(data.length == 5){
		var suffix="";
 	    var summation =+6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	}
	if(data.length == 6){
		var suffix="";
 	    var summation =+7*parseInt(data[0])+6*parseInt(data[1])+5*parseInt(data[2])+4*parseInt(data[3])+3*parseInt(data[4])+2*parseInt(data[5]);
	}
	 
	if(data.length == 7){
		var suffix="";
 	    var summation =+8*parseInt(data[0])+7*parseInt(data[1])+6*parseInt(data[2])+5*parseInt(data[3])+4*parseInt(data[4])+3*parseInt(data[5])+2*parseInt(data[6]);
	}
	if(data.length == 8){
		var suffix="";
 	    var summation =+9*parseInt(data[0])+8*parseInt(data[1])+7*parseInt(data[2])+6*parseInt(data[3])+5*parseInt(data[4])+4*parseInt(data[5])+3*parseInt(data[6])+2*parseInt(data[7]);
		
		}
	
	if(data.length == 9){
		var suffix="";
 	    var summation =10*parseInt(data[0])+9*parseInt(data[1])+8*parseInt(data[2])+7*parseInt(data[3])+6*parseInt(data[4])+5*parseInt(data[5])+4*parseInt(data[6])+3*parseInt(data[7])+2*parseInt(data[8]);
		
		}
	
 	 summation = parseInt( parseInt(summation) % 11);
	
		if(summation == 0)
		{
			suffix="A";
		}
		if(summation == 1)
		{
			suffix="F";
		}
		if(summation == 2)
		{
			suffix="H";
		}
		if(summation == 3)
		{
			suffix="K";
		}
		if(summation == 4)
		{
			suffix="L";
		}
		if(summation == 5)
		{
			suffix="M";
		}
		if(summation == 6)
		{
			suffix="N";
		}
		if(summation == 7)
		{
			suffix="P";
		}
		if(summation == 8)
		{
			suffix="W";
		}
		if(summation == 9)
		{
			suffix="X";
		}
		if(summation == 10)
		{
			suffix="Y";
		}
		 $.ajaxSetup({
            async : false
    	});
		
		$("#army_no3").val(suffix);
		
		 $.ajaxSetup({
            async : false
   	 });
		Army_no_already_exist();
	}
	 

	  
  
  function Army_no_already_exist()
	 {
		 var cat = $("#category option:selected").val();
		 var army_no1 = $("select#army_no1").val();
		 var army_no2 = $("input#army_no2").val();
		 var army_no3 = $("input#army_no3").val();
	
		 if(cat == "OR" ){
			 $("input#army_no1").val("");
			 var army_no = army_no2 + army_no3;
		  }
		   if(cat == "JCO" ){
		   var army_no = army_no1 + army_no2 + army_no3;
		 }
		 $.ajaxSetup({
       async : false
	   });
		 var result_data = true;
		 $.post("getArmyNoAlreadyExist?"+key+"="+value, {army_no : army_no}).done(function(j) {
	
			 	if(j == false){
					alert("Army Number already Exist.")
					$("select#army_no1").val("");
					$("input#army_no2").val("");
					$("input#army_no3").val("");
					result_data = false;
				} 
				
			}); 
		 return result_data;
	 }
	 
	 
  function father_proffFn() {
		var a = $('input:radio[name=father_proff_radio]:checked').val();
		if(a.toLowerCase() == "yes") {
			$("#father_inserviceDiv").show();
			$("#father_proffDiv").hide();
			$("#father_profession").val("0");
			$("#father_proffotherDiv").hide();
			$("#father_proffother").val("");
		} else {
			$("#father_proffDiv").show();
			$("#father_inserviceDiv").hide();
			$("#father_service").val("0");
			$("#father_other").val("");
			$("#father_personal_no").val("");
			
		}
		
	}

	function mother_proffFn() {
		var a = $('input:radio[name=mother_proff_radio]:checked').val();
		if(a.toLowerCase() == "yes") {
			$("#mother_inserviceDiv").show();
			$("#mother_proffDiv").hide();
			$("#mother_profession").val("0");
			$("#mother_proffotherDiv").hide();
			$("#mother_proffother").val("");
		} else {
			$("#mother_proffDiv").show();
			$("#mother_inserviceDiv").hide();
			$("#mother_service").val("0");
			$("#mother_other").val("");
			$("#mother_personal_no").val("");	
			
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
	</script>
<script>
$("#record_office_sus")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#record_office_sus");
      
			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
									.ajax({
										type : 'POST',
										url : "getRecordofficeSUSNoList?"
												+ key + "=" + value,
										data : {
											sus_no : sus_no
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No.");
								document
										.getElementById("record_office").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getRecordofficeUnitNameList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#record_office").val(
												dec(enc, j[0]));
										$("#record_office").attr('readonly', true);
									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});


</script>
<script>
function getmarital_status_jco() 
{ 
	var a = $("select#marital_status option:selected").val();
	if(a == "9" || a == "12" || a == "11" || a == "13") {
		$("#fill_marraige_div").hide();
		$("#fill_divorce_div").show();
	}
	if(a == "8") {
		$("#fill_marraige_div").show();
		$("#fill_divorce_div").show();
		$("#spouse_quali_radioDiv").show();
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
</script>
<script>

function spouse_quali_radioFn() {
		var a = $('input:radio[name=spouse_quali_radio]:checked').val();
		if(a.toLowerCase() == "yes") {
			$("#spouse_Qualifications").show();
			
		} else {
			$("#spouse_Qualifications").hide();
		}
	}
	
marr = 1;
//marr_srno = 1;


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
	

	 //fn_other_exam();	
	// fn_otherShowHide(document.getElementById('quali_degree'),'other_div_qualiDeg','quali_deg_other');
	// fn_otherShowHide(document.getElementById('specialization'),'other_div_qualiSpec','quali_spec_other');
	 
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

function fn_otherShowHide(obj,Divother_id,other_id){
	var thisText=$("#"+obj.id+" option:selected").text();
	if(thisText.toUpperCase()=='OTHERS' || thisText.toUpperCase()=='OTHER'){
		$('#'+Divother_id).show();
	}
	else{
		$('#'+Divother_id).hide();
		$('#'+other_id).val('');
	}
}



function showCheckboxesSpouse() {
	
	$("#spouse_checkboxes").toggle();
	$("#spouse_searchSubject").val('');
	$('.spouse_subjectlist').each(function() {
		$(this).show()
	})
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



function divorce_remove_fn(R) {
	
	var rc = confirm("Are You Sure! You Want To Delete");
	if(rc) {
		var marr_ch_id = $('#divo_ch_id' + R).val();
		
		if(marr_ch_id == 0){
			 $("tr#tr_divorce"+R).remove();	
			 aller=R-1
			 $("input#divorce_count").val(aller);
			 $("#divorce_add"+aller).show();
			 if(aller!=1){
			 $("#divorce_remove"+aller).show();
			 }	else{
				 $("#divorce_add"+aller).show();
			 } 
		}else{
		$.post('family_marriage_delete_action_jco?' + key + "=" + value, {
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
						if($("#marital_status").val() == "10" ) {
							$("#fill_divorce_div").hide();
						}
					}
				}
				$('.divosr_no').each(function(i, obj) {
					obj.innerHTML = i + 1;
					divosr_no = i + 1;
				});
				getfamily_divorceDetails();
				alert("Data Deleted Successfully");
				
				
			} else {
				alert("Data not Deleted ");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
		
	}
	
}


</script>
<script>
function calculate_age(obj){    
	
	
    var from_d=$("#"+obj.id).val();
	 
	 var from_d=$("#"+obj.id).val();
    from_d = from_d.replaceAll("/", "-");
 
	 
    var from_d1=from_d.substring(6,10);
    var from_d2=from_d.substring(3,5);
    var from_d3=from_d.substring(0,2);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
  
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
   

    
    if (month == undefined)
     {
            month = 0;
     }
   
    if(year < 16)
    {
   	 alert("Please enter age above 16");
   	 $("#"+obj.id).val("");
    }
}

divo = 1;



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
function marriageNationChange(index){
	var value=$("#marriage_nationality"+index+" option:selected").text();
	if(value== "OTHERS"){
		$('#marriage_othernationality'+index).prop("readonly",false)
	}
	else{
		$('#marriage_othernationality'+index).prop("readonly",true)
		$('#marriage_othernationality'+index).val("");
	}
}

function spouseCB(a) {
	if ($('#spo-ex'+a).is(":checked"))
	{
		$('#spouse_service' + a).attr('readonly', false);
		$('#spouse_personal_no' + a).attr('readonly', false);
	}
	else{

		$('#spouse_service' + a).attr('readonly', true);
		$('#spouse_personal_no' + a).attr('readonly', true);
		$('#other_spouse_ser' + a).attr('readonly', true);
		$('#spouse_service' + a).val(0);
		$('#spouse_personal_no' + a).val("");
		$('#other_spouse_ser' + a).val("");
	}
	
}
function divorceNationChange(index){
	var value=$("#divorce_nationality"+index+" option:selected").text();
	
	if(value == "OTHERS"){
		
		$('#divorce_othernationality'+index).prop("readonly",false)
	}
	else{
		$('#divorce_othernationality'+index).prop("readonly",true)
		$('#divorce_othernationality'+index).val("")
	}
}
function rank_intake_jco() 
{
	var a = $("select#rank1 option:selected").text();

	if(a == "RECTS") {
		$("#intake").show();
		
	}
	else
		{
		$("#intake").hide();
		$("#rank_intake").val("0");
		}
	
}
 function fn_category_rank() {

	
	var category= '${Edit_JCOsCMD.category}';
          
	 		 	$.post("getCategoryrankList?"+key+"="+value, {category:category}).done(function(j) {
	 		
	 		 
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#rank1").html(options);
	 				$("#rank1").val('${Edit_JCOsCMD.rank}');
	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
} 
 
 
 function validate_marital_event(){
	var pre_m_status= '${Edit_JCOsCMD.marital_status}';
	 var m_status=$("#marital_status").val();
	var mrg_id=$("#marr_ch_id1").val();
	var dww_id=$("#divo_ch_id1").val();
	
	 if(m_status==10){
		 if(mrg_id!='0' && mrg_id!=''){
			 alert("Please First Delete Mrriage Data");
			 return false;
			 }
		if(dww_id!='0' && dww_id!=''){
			 alert("Please First Delete Divorced/Widow/Widower Data");
			 return false;
			 }
	 }
	 else if(m_status==8 || m_status==13){
		 if(pre_m_status==8 && m_status==13){
			 if(mrg_id!='0' && mrg_id!=''){
				 alert("Please First Delete Mrriage Data");
				 return false;
				 }
		 }
	 }
	 else if(m_status==9 || m_status==11 || m_status==12){
		 if(mrg_id!='0' && mrg_id!=''){
			 alert("Please First Delete Mrriage/Separated Data");
			 return false;
			 }
	 }
	 getmarital_status_jco();
	 return true;
 }

 

</script>


<!--  //************************************* Start  MEDICAL  js*****************************// -->
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
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+sShape+'" onclick="sShape_add_fn('+sShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove'+sShape+'" onclick="sShaperemove_fn('+sShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
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

function sShaperemove_fn(R){
	var r = confirm("Are You Sure! You Want To Delete This Row");
	 if(r){				   
					 $("tr#tr_sShape"+R).remove(); 
						 R = R-1;
					 $("#sShape_add"+R).show();
					 $("#sShape_remove"+R).show();	
					 $("input#sShape_count").val(R);
					 sShape=sShape-1;
// 						 if(sShape == 1){
// 							var s_val = $("#s_status"+sShape).val();
// 							var lis1 = '<option value="${getShapeStatusList[0][1]}" name="${getShapeStatusList[0][1]}">${getShapeStatusList[0][0]}</option>'
// 							 var lis2 = $("#s_status"+sShape).html();
// 							 $('#s_status'+sShape).empty().append('<option value="0">--Select--</option>'+lis1+lis2);
// 							 $("#s_status"+sShape).val(s_val);
// 							}
			
	 }

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
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+aShape+'" onclick="aShape_add_fn('+aShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "aShape_remove'+aShape+'" onclick="aShaperemove_fn('+aShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
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
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+pShape+'" onclick="pShape_add_fn('+pShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pShape_remove'+pShape+'" onclick="pShaperemove_fn('+pShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
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
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+eShape+'" onclick="eShape_add_fn('+eShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eShape_remove'+eShape+'" onclick="eShaperemove_fn('+eShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
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
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+cCope+'" onclick="cCope_add_fn('+cCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "cCope_remove'+cCope+'" onclick="cCoperemove_fn('+cCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
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
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+oCope+'" onclick="oCope_add_fn('+oCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "oCope_remove'+oCope+'" onclick="oCoperemove_fn('+oCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
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
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+pCope+'" onclick="pCope_add_fn('+pCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "pCope_remove'+pCope+'" onclick="pCoperemove_fn('+pCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
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
	 	+'<td style="width: 10%;" >'
	 	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+eCope+'" onclick="eCope_add_fn('+eCope+');" ><i class="fa fa-plus"></i></a>'
	 	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "eCope_remove'+eCope+'" onclick="eCoperemove_fn('+eCope+');"><i class="fa fa-trash"></i></a> '
	 	+'</td>'
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
	 
	 
	 


function getsShapeDetails(){
	
	  
	 var p_id=$('#jco_id').val();
	 var Shape='S'
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
			
		if(v!=0){	
			 
			$('#s_madtable').show(); 
			$('#s_madtbody').empty(); 
			for(i;i<v;i++){			
				x=i+1;		
				 var fd="DD/MM/YYYY";
				 var td="DD/MM/YYYY";
				 
				 if(j[i][2]!=null && j[i][2]!=""){
					 fd=ParseDateColumncommission(j[i][2]);
// 				  	 td=ParseDateColumncommission(j[i][3]);
				 }
				 if(j[i][3] != null && j[i][3] != "") {
						td = ParseDateColumncommission(j[i][3]);
					}
				 if (j[i][0]==1) {
					 td="";
					 }
					$("table#s_madtable").append('<tr id="tr_sShape'+x+'">'
						+'<td class="sShape_srno" style="width: 2%;">'+x+'</td>'
						+'<td>'
						
						+'<select name="s_status'+x+'" id="s_status'+x+'" '
						+'	class="form-control-sm form-control"  onchange="statusChange(1,'+x+',this.options[this.selectedIndex].value)">'
						+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
						+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
						+'	</c:forEach>'
						+'		</select>'
						+'  </td>'
						+'   <td style="">'
						+'<select name="sShape_value'+x+'" id="sShape_value'+x+'" '
						+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'s\')">'
						+'		</select>	</td>'

						
						+'<td style=""> '
						+' <input type="text" name="s_from_date'+x+'" id="s_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
						+'</td>'
						+'<td style=""> '
						+' <input type="text" name="s_to_date'+x+'" id="s_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
						+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
						+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
						+'</td>'
						
						+'  <td style="visibility:hidden; "  class="diagnosisClass1'+x+'">'
						+' <input type="text" name="_diagnosis_code1'+x+'" id="_diagnosis_code1'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
						+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
						+'   </td>'

						+' <td style="display:none;"><input type="text" id="sShape_ch_id'+x+'" name="sShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						+'<td style="width: 10%;" class="addbtClass1'+x+'">'
						+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+x+'" onclick="sShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						+'</td>'
						

						+'</tr>');
					
					 datepicketDate('s_from_date'+x);
					 datepicketDate('s_to_date'+x);
					 $( "#s_to_date"+x ).datepicker( "option", "maxDate", null);
					 
					$("#s_status"+x).val(j[i][0]);
						$.ajaxSetup({
  							async : false
  						});

						statusChange(1,x,j[i][0]);
						
						$("#sShape_value"+x).val(j[i][1]); 
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
			$("#madical_authority").val(j[i-1][6]); 
			$("#madical_date_of_authority").val(ParseDateColumncommission(j[i-1][7])); 
			$('#mad_classification_count').val(j[i-1][8]);
			
			if(j[i-1][11]!=null && j[i-1][11]!="" && j[i-1][12]!=null && j[i-1][12]!="" && j[i-1][13]!=null && j[i-1][13]!=""){
				$("#check_1bx").prop("checked", true);
				oncheck_1bx();
				 fd=ParseDateColumncommission(j[i-1][11]);
			  	 td=ParseDateColumncommission(j[i-1][12]);
				$("#1bx_from_date").val(fd);
				$("#1bx_to_date").val(td);
				$("#1bx_diagnosis_code").val(j[i-1][13]);
			}

			
			setDiagnosis();
			}
		
		
			
	  });
}


function gethShapeDetails(){
	
	  
	var p_id=$('#jco_id').val();
	 var Shape='H';
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#h_madtable').show(); 
				$('#h_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#h_madtable").append('<tr id="tr_hShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							
							+'<select name="h_status'+x+'" id="h_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(2,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="hShape_value'+x+'" id="hShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'h\')">'
							+'		</select>	</td>'

							
							
							+'<td style=""> '
							+' <input type="text" name="h_from_date'+x+'" id="h_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="h_to_date'+x+'" id="h_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							+'  <td style="visibility:hidden; "  class="diagnosisClass2'+x+'">'
							+' <input type="text" name="_diagnosis_code2'+x+'" id="_diagnosis_code2'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'

							+' <td style="display:none;"><input type="text" id="hShape_ch_id'+x+'" name="hShape_ch_id'+x+'" value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass2'+x+'">'
							+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "hShape_add'+x+'" onclick="hShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('h_from_date'+x);
						 datepicketDate('h_to_date'+x);
						 $( "#h_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#h_status"+x).val(j[i][0]);
						$.ajaxSetup({
 							async : false
 						});
						
						statusChange(2,x,j[i][0]);
						

						$("#hShape_value"+x).val(j[i][1]); 
// 						if(x>1){
// 							$("#h_status"+x+" option[value='1']").remove();
// 							$("#h_status"+(x-1)+" option[value='1']").remove();
							
// 						}
// 						else {
							var thisT = document.getElementById('hShape_value'+x);
							onShapeValueChange(thisT,'h');
// 						}
						 
						
						
					
		}
			hShape=v;
			$("input#hShape_count").val(v);
			$("input#hShapeOld_count").val(v);
			$("#hShape_add"+v).show();
			
			setDiagnosis();
			}
			
	  });
}



function getaShapeDetails(){
	
	  
	var p_id=$('#jco_id').val();
	 var Shape='A';
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			classification=j[0][13];
			$('#a_madtable').show(); 
				$('#a_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#a_madtable").append('<tr id="tr_aShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="a_status'+x+'" id="a_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(3,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="aShape_value'+x+'" id="aShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'a\')">'
							+'		</select>	</td>'

							+'<td style=""> '
							+' <input type="text" name="a_from_date'+x+'" id="a_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="a_to_date'+x+'" id="a_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass3'+x+'">'
							+' <input type="text" name="_diagnosis_code3'+x+'" id="_diagnosis_code3'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="aShape_ch_id'+x+'" name="aShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass3'+x+'" >'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "aShape_add'+x+'" onclick="aShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('a_from_date'+x);
						 datepicketDate('a_to_date'+x);
						 $( "#a_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#a_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(3,x,j[i][0]);
						
						$("#aShape_value"+x).val(j[i][1]); 
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
			
			setDiagnosis();
			}
			
	  });
}



function getpShapeDetails(){
	
	  
	var p_id=$('#jco_id').val();
	 var Shape='P';
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			
		if(v!=0){	
			$('#p_madtable').show(); 
				$('#p_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#p_madtable").append('<tr id="tr_pShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="p_status'+x+'" id="p_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(4,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="pShape_value'+x+'" id="pShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'p\')">'
							+'		</select>	</td>'
	
							+'<td style=""> '
							+' <input type="text" name="p_from_date'+x+'" id="p_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="p_to_date'+x+'" id="p_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'

							+'  <td style="visibility:hidden; "  class="diagnosisClass4'+x+'">'
							+' <input type="text" name="_diagnosis_code4'+x+'" id="_diagnosis_code4'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="pShape_ch_id'+x+'" name="pShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass4'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pShape_add'+x+'" onclick="pShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('p_from_date'+x);
						 datepicketDate('p_to_date'+x);
						 $( "#p_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#p_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(4,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#pShape_value"+x).val(j[i][1]); 
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
			setDiagnosis();
			}
			
	  });
}


function geteShapeDetails(){
	
	  
	var p_id=$('#jco_id').val();
	 var Shape='E';
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
			var v=j.length;
			 
		if(v!=0){	
			$('#e_madtable').show(); 
				$('#e_madtbody').empty(); 
				for(i;i<v;i++){			
					x=i+1;		
	
					 var fd="DD/MM/YYYY";
					 var td="DD/MM/YYYY";
					 
					 if(j[i][2]!=null && j[i][2]!=""){
						 fd=ParseDateColumncommission(j[i][2]);
// 					  	 td=ParseDateColumncommission(j[i][3]);
					 }
					 if(j[i][3] != null && j[i][3] != "") {
							td = ParseDateColumncommission(j[i][3]);
						}
					 if (j[i][0]==1) {
						 td="";
						 }
					
						$("table#e_madtable").append('<tr id="tr_eShape'+x+'">'
							+'<td style="width: 2%;">'+x+'</td>'
							+'<td>'
							+'<select name="e_status'+x+'" id="e_status'+x+'" '
							+'	class="form-control-sm form-control"  onchange="statusChange(5,'+x+',this.options[this.selectedIndex].value)">'
							+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
							+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
							+'	</c:forEach>'
							+'		</select>'
							+'  </td>'
							+'   <td style="">'
							+'<select name="eShape_value'+x+'" id="eShape_value'+x+'" '
							+'		class="form-control-sm form-control" onchange="onShapeValueChange(this,\'e\')">'
							+'		</select>	</td>'



							+'<td style=""> '
							+' <input type="text" name="e_from_date'+x+'" id="e_from_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+fd+'">'
							+'</td>'
							+'<td style=""> '
							+' <input type="text" name="e_to_date'+x+'" id="e_to_date'+x+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" '
							+'	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
							+'	onchange="clickrecall(this,\'DD/MM/YYYY\')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="'+td+'">'
							+'</td>'
							
							+'  <td style="visibility:hidden; "  class="diagnosisClass5'+x+'">'
							+' <input type="text" name="_diagnosis_code5'+x+'" id="_diagnosis_code5'+x+'" value="'+j[i][4]+'" class="form-control-sm form-control" autocomplete="off" '
							+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
							+'   </td>'
							+' <td style="display:none;"><input type="text" id="eShape_ch_id'+x+'" name="eShape_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
							+'<td style="width: 10%;" class="addbtClass5'+x+'">'
							+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eShape_add'+x+'" onclick="eShape_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
							+'</td>'
							+'</tr>');
						
						datepicketDate('e_from_date'+x);
						 datepicketDate('e_to_date'+x);
						 $( "#e_to_date"+x ).datepicker( "option", "maxDate", null);

						 
						$("#e_status"+x).val(j[i][0]);
						$.ajaxSetup({
							async : false
						});

						statusChange(5,x,j[i][0]);
						$.ajaxSetup({
							async : false
						});

						$("#eShape_value"+x).val(j[i][1]); 
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
			setDiagnosis();
			}
			
	  });
}



function getcCopeDetails(){
	
	  
	var p_id=$('#jco_id').val();
	 var Shape='C_C';
	 var i=0;
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
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
						 	+'	class="form-control-sm form-control" onchange="onCCopeChange(this,'+x+'); onCopeChangebt(this,1,'+x+');">'
						 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'
						 	+'<td style="display:none;" class="cCopClass'+x+'" >'
							+'<input type="text" name="c_cother'+x+'" id="c_cother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
							+' </td>'
						 	+' <td style="display:none;"><input type="text" id="cCope_ch_id'+x+'" name="cCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass11" >'
						 	+'   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "cCope_add'+x+'" onclick="cCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
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
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
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
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,2,'+x+');">'
						 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	
						 	+' <td style="display:none;"><input type="text" id="oCope_ch_id'+x+'" name="oCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass21" >'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "oCope_add'+x+'" onclick="oCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
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
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
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
						 	+'	class="form-control-sm form-control"  onchange="onCopeChangebt(this,3,'+x+');">'
						 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+' <td style="display:none;"><input type="text" id="pCope_ch_id'+x+'" name="pCope_ch_id'+x+'"  value="'+j[i][5]+'" class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass31">'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "pCope_add'+x+'" onclick="pCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
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
	  $.post('madical_cat_GetData_jco?' + key + "=" + value, {p_id:p_id,Shape:Shape }, function(j){
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
						 	+'	class="form-control-sm form-control"  onchange="onECopeChange(this,'+x+'); onCopeChangebt(this,4,'+x+');">'
						 	+'	<c:forEach var="item" items="${geteCopeList}" varStatus="num">'
						 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'	</c:forEach>'
						 	+'		</select>'
						 	+'  </td>'	 	
						 	+'<td style="display:none;" class="eCopClass'+x+'">'
						 	+'<select name="c_esubvalue'+x+'" id="c_esubvalue'+x+'" onchange="onECopeSubChange(this,'+x+')"'
						 	+'			class="form-control-sm form-control" >'
						 	+'			<option value="0">--Select--</option>'																
						 	+'			<c:forEach var="item" items="${getesubCopeList}" varStatus="num">'
						 	+'				<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
						 	+'			</c:forEach></select>   </td>'
						 	+'<td style="display:none;" class="eCop_otherClass'+x+'" >'
						 	+'	 <input type="text" name="c_esubvalueother'+x+'" id="c_esubvalueother'+x+'" value="'+j[i][10]+'" class="form-control-sm form-control" autocomplete="off" >'						                              
						 	+'	   </td>'
						 	+' <td style="display:none;"><input type="text" id="eCope_ch_id'+x+'" name="eCope_ch_id'+x+'" value="'+j[i][5]+'"  class="form-control autocomplete" autocomplete="off" ></td>'   
						 	+'<td style="width: 10%;" class="CopaddbtClass41">'
						 	+'   <a  style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "eCope_add'+x+'" onclick="eCope_add_fn('+x+');" ><i class="fa fa-plus"></i></a>'
						 	+'</td>'
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