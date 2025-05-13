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
<link rel="stylesheet" href="js/select/select2.min.css">
<script src="js/select/select2.min.js"></script>

<style>
.select2.narrow {
	width: 100%;
	position: relative;
	padding: .375rem .75rem;
	font-size: 1rem;
	line-height: 1.5;
	color: #343a40;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.wrap.select2-selection--single {
	height: 100%;
}

.select2-container .wrap.select2-selection--single .select2-selection__rendered
	{
	word-wrap: break-word;
	text-overflow: inherit;
	white-space: normal;
}

.select2-dropdown.select2-dropdown--below {
	top: 10px;
}

span.select2.select2-container.select2-container--default {
	width: inherit !important;
}


.card-header {
	margin-bottom: 0;
	background-color: #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 12px;
}


/* Full screen modal styles */
        #fullScreenModal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.9);
            align-items: center;
            justify-content: center;
        }

        #fullScreenModal img {
            max-width: 90%;
            max-height: 90%;
            object-fit: contain;
        }

        #closeFullScreen {
            position: absolute;
            top: 15px;
            right: 35px;
            color: white;
            font-size: 40px;
            font-weight: bold;
            cursor: pointer;
        }

        /* Image hover effect */
        .image-container {
            position: relative;
            display: inline-block;
        }

        .hover-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            display: none;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        .image-container:hover .hover-overlay {
            display: flex;
        }


</style>

<form:form name="" id="civilian_reg"
	action="AppEditcivilianRegularAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	commandName="AppEditReg_details_cmd" 
enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Update Regular Employee</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Authority </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="authority" name="authority"
										onkeyup="return specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">

								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Authority </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dt_of_authority" id="dt_of_authority"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
										autocomplete="off" style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Unit Sus No. </label>
								</div>
								<div class="col-md-8">
									<label id="unit_sus_no_hid" style="display: none"></label> <input
										type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										style="display: none;" maxlength="50">
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Unit Posted To </label>
								</div>
								<div class="col-md-8">
									<label id="unit_name_l" style="display: none"></label> <input
										type="text" id="unit_posted_to" name="unit_posted_to"
										style="display: none;" class="form-control autocomplete"
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
										style="color: red;"> *</strong> Employee No. </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="employee_no" name="employee_no"
										oninput="this.value = this.value.toUpperCase()"
										onkeypress="return AvoidSpace(event)"  onchange="emp_no_already_exist();"
										class="form-control autocomplete" autocomplete="off"
										onkeyup="return specialcharecter(this)" maxlength="50">

								</div>
									<input type="hidden" id="employee_no_old" name="employee_no_old" class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
				
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										style="color: red;">*</strong> Mobile No</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="mobile_no" name="mobile_no"
										class="form-control" maxlength="10" min="0"
										 placeholder="Enter 10-digit mobile number" 
										oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
								</div>
							</div>
						</div>
						</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>First Name</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" id="status" name="status"
										class="form-control autocomplete" value="${Details.status}"
										autocomplete="off"> <input type="hidden" id="id"
										name="id" class="form-control autocomplete"
										value="${Details.id}" autocomplete="off"> <input
										type="hidden" id="main_id" name="main_id"
										class="form-control autocomplete" value="${Details.main_id}"
										autocomplete="off"> <input type="text" id="first_name"
										name="first_name" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										class="form-control autocomplete" autocomplete="off"
										value="${Details.first_name}" maxlength="50">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Middle Name</label>
								</div>
								<div class="col-md-7">
									<input type="text" id="middle_name" name="middle_name"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										class="form-control autocomplete" autocomplete="off"
										value="${Details.middle_name}" maxlength="50">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Last Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="last_name" name="last_name"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										class="form-control autocomplete" autocomplete="off"
										value="${Details.last_name}" maxlength="50">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Date of Birth</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="dob" id="dob" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');dobValidate(this);"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Gender</label>
								</div>
								<div class="col-md-8">
									<select name="gender" id="gender"
										class="form-control-sm form-control"
										onchange="gender_other1();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getGenderList}" varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.gender}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select> <input id="gender_hid" name="gender_hid" type="hidden" />
								</div>
							</div>
						</div>

					</div>


					<div class="col-md-12" id="gender_other_div" style="display: none;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Gender Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="gender_other" name="gender_other"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
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
										style="color: red;"> *</strong>Classification of Services</label>
								</div>
								<div class="col-md-8">
									<div id="divContainer" class="form-check-inline form-check"></div>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Group</label>
								</div>
								<div class="col-md-8">
									<div id="div_group" class="form-check-inline form-check"></div>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Cadre</label>
								</div>
								<div class="col-md-8">
									<select name="cadre" id="cadre"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCadre}" varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.cadre}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Highest Qualification</label>
								</div>
								<div class="col-md-8">
									<select name="high_qual" id="high_qual"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCivHigh_Qualification}" varStatus="num">
											<option value="${item[0]}"
											<c:if test="${item[0] eq Details.qualification}">selected="selected"</c:if>>${item[1]}</option>
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
										style="color: red;"> *</strong>Category </label>
								</div>
								<div class="col-md-8">
									<select name="category_belongs" id="category"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCategoryList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.category_belongs}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Service Status </label>
								</div>
								<div class="col-md-8">
									<div id="div_service_status"
										class="form-check-inline form-check"></div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6" id="div_classification_of_trade"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Classification of Trade</label>
								</div>
								<div class="col-md-8">
									<select name="classification_trade"
										id="classification_of_trade"
										class="form-control-sm form-control"
										onchange="classification_trade_other();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getClassificationOfTradesList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.classification_trade}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
								<input type="hidden" name="classification_of_trade_hid"
									id="classification_of_trade_hid" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Type</label>
								</div>
								<div class="col-md-8">
									<div id="div_type" class="form-check-inline form-check"></div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6" id="classification_of_trade_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong>Classification of Trade Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="classification_of_trade_other"
										name="classification_of_trade_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Whether Ex-Serviceman</label>
								</div>
								<div class="col-md-8">
									<label for="wes_yes">Yes</label> <input class=" " type="radio"
										onchange="onWes(this.value);" id="wes_yes" value="yes"
										name="wes"> &nbsp;&nbsp &nbsp;&nbsp<label for="wes_no">No</label> <input
										class=" " type="radio" onchange="onWes(this.value);"
										id="wes_no" value="no" name="wes">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="display: none;" id="div_wes">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Service's Name</label>
								</div>
								<div class="col-md-8">
									<select name="whether_ex_serviceman" id="whether_ex_serviceman"
										class="form-control-sm form-control"
										onchange="ServiceOtherFn();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getExservicemenList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.whether_ex_serviceman}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div id="other_service_div" class="col-md-6"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Other</label><strong
										style="color: red;"> *</strong>
								</div>
								<div class="col-md-8">
									<input type="text" id="service_other" name="service_other"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" maxlength="50">
								</div>
							</div>
						</div>
					</div>

					<input type="hidden" name="whether_ex_serviceman_hid"
						id="whether_ex_serviceman_hid" />


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Whether Person With Disability</label>
								</div>
								<div class="col-md-4">
									<label for="wpwd_yes">Yes</label> <input class=" " type="radio"
										onchange="onWpwd(this.value);" id="wpwd_yes" value="yes"
										name="wpwd"> &nbsp;&nbsp &nbsp;&nbsp<label for="wpwd_no">No</label> <input
										class=" " type="radio" onchange="onWpwd(this.value);"
										id="wpwd_no" value="no" name="wpwd">
								</div>
							</div>
						</div>

						<div class="col-md-6" id="div_wpwd" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Disability</label><strong
										style="color: red;">* </strong>
								</div>
								<div class="col-md-8">
									<select name="whether_person_disability"
										id="whether_person_disability"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getPersonWithDisabilityList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.whether_person_disability}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>

					<!-- 	              			change -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> <strong
										style="color: red;"> </strong>Post in which Initially
										Appointed
									</label>
								</div>
								<div class="col-md-8">
									<select name="post_initialy_appointed" id="initialy_appointed"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.post_initialy_appointed}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Date of Joining in Govt Service </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="joining_date_gov_service"
										id="dt_of_join_govt_service" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');SetMin();"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">

								</div>
							</div>
						</div>
					</div>
					<!-- 	              		change -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Designation</label>
								</div>
								<div class="col-md-8">

									<select name="designation" id="designation"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getDesignationList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.designation}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Date of Designation</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="designation_date"
										id="dt_of_designation" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');SetMin();"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">

						<!-- 	              				change -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Pay Level</label>
								</div>
								<div class="col-md-8">
									<select name="pay_level" id="pay_level"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getPayLevelList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.pay_level}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Date of TOS</label>
								</div>
								<div class="col-md-8">
<!-- 									<input type="text" name="date_of_tos" id="date_of_tos" -->
<!-- 										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 										class="form-control" style="width: 93%; display: inline;" -->
<!-- 										onfocus="this.style.color='#000000'" -->
<!-- 										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" -->
<!-- 										onkeyup="clickclear(this, 'DD/MM/YYYY')" -->
<!-- 										onchange="clickrecall(this,'DD/MM/YYYY');SetMin();" -->
<!-- 										aria-required="true" autocomplete="off" -->
<!-- 										style="color: rgb(0, 0, 0);"> -->
									<input type="text" name="date_of_tos" id="date_of_tos"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');SetMin();"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);" readonly>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Father's Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="father_name"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										name="father_name" value="${Details.father_name}"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>


						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Mother's Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="mother_name"
										onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"
										name="mother_name" value="${Details.mother_name}"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="">
						<div class="col-md-6">

							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Religion </label>
								</div>
								<div class="col-md-8">
									<select name="religion" id="religion"
										class="form-control-sm form-control" onchange="rel_other();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getReligionList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.religion}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="religion_hid" id="religion_hid" />
								</div>
							</div>

						</div>


						<div class="col-md-6">

							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Mother Tongue </label>
								</div>
								<div class="col-md-8">
									<select name="mother_tongue" id="monther_tongue"
										class="form-control-sm form-control"
										onchange="mot_tongue_other();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMothertoungeList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.mother_tongue}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="mother_tongue_hid"
										id="mother_tongue_hid" />
								</div>
							</div>

						</div>


					</div>
					<div class="col-md-12">
						<div class="col-md-6" id="religion_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Religion Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="religion_other" name="religion_other"
										maxlength="50" class="form-control autocomplete"
										autocomplete="off" onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
						<div class="col-md-6" id="mother_tongue_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Mother Tongue Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="mother_tongue_other"
										name="mother_tongue_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12" style="">
						<div class="col-md-6">

							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Aadhaar No. </label>
								</div>
								<div class="col-md-8">
									<div class="row form-group">
										<input class="form-control" autocomplete="off"
											style="width: 18%;" id="pers_aadhar_no11"
											name="pers_aadhar_no11" value="${pers_aadhar_no11_val}"
											type="text" maxlength="4"
											onkeyup="movetoNext(this, 'pers_aadhar_no2'),lengthadhar()"
											onkeypress="return isNumber(event)"> <input
											class="form-control" autocomplete="off"
											style="width: 18%; margin-left: 15px" id="pers_aadhar_no2"
											name="pers_aadhar_no2" value="${pers_aadhar_no2_val}"
											type="text" maxlength="4"
											onkeyup="movetoNext(this, 'pers_aadhar_no3'),lengthadhar()"
											onkeypress="return isNumber(event)"> <input
											class="form-control" autocomplete="off"
											style="width: 18%; margin-left: 15px" id="pers_aadhar_no3"
											name="pers_aadhar_no3" value="${pers_aadhar_no3_val}"
											type="text" maxlength="4" onkeyup="lengthadhar()"
											onkeypress="return isNumber(event)"> <input
											type="hidden" id="aadhar_card" name="aadhar_card"
											value="${Details.aadhar_card}"
											class="form-control autocomplete" autocomplete="off"
											maxlength="50">
									</div>
								</div>
							</div>

						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong> PAN No.</label>
								</div>
								<div class="col-md-8">

									<input type="text" id="pan_no" name="pan_no"
										value="${Details.pan_no}" class="form-control autocomplete"
										autocomplete="off" onchange=" isPAN(this); "
										oninput="this.value = this.value.toUpperCase()" maxlength="10">
								</div>
							</div>
						</div>


					</div>

					<div class="card-header-inner" id="aa"
						style="margin-left: 20px; margin-bottom: 20px;">
						<strong>ORGINAL DOMICILE</strong>
					</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Country</label>
								</div>
								<div class="col-md-8">
									<select name="country_original" id="country_original"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_Country();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCountryList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.country_original}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="country_original_hid"
										id="country_original_hid" />
								</div>

							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">

								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> State </label>
								</div>
								<div class="col-md-8">
									<select name="original_state" id="original_state"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_state();">
										<option value="0">--Select--</option>
									</select> <input type="hidden" name="original_state_hid"
										id="original_state_hid" />
								</div>


							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District</label>
								</div>
								<div class="col-md-8">
									<select name="district_original" id="original_district"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_district();">
										<option value="0">--Select--</option>
									</select>
								</div>

								<input type="hidden" name="district_original_hid"
									id="district_original_hid" />
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-4" id="origin_country_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="original_country_other"
										name="original_country_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
						<div class="col-md-4" id="origin_state_other_div"
							style="display: none;">
							<div class="row form-group">

								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> State Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="original_state_other"
										name="original_state_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
						<div class="col-md-4" id="origin_dist_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="original_district_other"
										name="original_district_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil</label>
								</div>
								<div class="col-md-8">
									<select name="original_tehsil" id="original_tehsil"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_tesil();">
										<option value="0">--Select--</option>
									</select>
								</div>
								<input type="hidden" name="original_tehsil_hid"
									id="original_tehsil_hid" />
							</div>
						</div>
						<div class="col-md-4" id="original_tehsil_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="original_tehsil_other"
										name="original_tehshil_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
					</div>

					<div class="card-header-inner" id="aa"
						style="margin-left: 20px; margin-bottom: 20px;">
						<strong>PRESENT DOMICILE</strong>
					</div>

					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Country </label>
								</div>
								<div class="col-md-8">
									<select name="country_present" id="country1"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_Country_present();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCountryList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.country_present}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="country1_hid" id="country1_hid" />
								</div>

							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> State </label>
								</div>
								<div class="col-md-8">
									<select name="state_present" id="present_state"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_state_present();">
										<option value="0">--Select--</option>

									</select>
								</div>
								<input type="hidden" name="present_state_hid"
									id="present_state_hid" />

							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District </label>
								</div>
								<div class="col-md-8">
									<select name="district_present" id="present_district"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_district_present();">
										<option value="0">--Select--</option>

									</select>
								</div>
								<input type="hidden" name="present_district_hid"
									id="present_district_hid" />

							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4" id="present_country_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Country Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_country_other"
										name="present_country_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
						<div class="col-md-4" id="present_state_other_div"
							style="display: none;">
							<div class="row form-group">

								<div class="col-md-4">
									<label class=" form-control-label"> State Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_state_other"
										name="present_state_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
						<div class="col-md-4" id="present_dist_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> District Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_district_other"
										name="present_district_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil</label>
								</div>
								<div class="col-md-8">
									<select name="tehsil_present" id="present_tehsil"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_tesil_present();">
										<option value="0">--Select--</option>

									</select> <input type="hidden" id="tehsil_present_hid"
										name="tehsil_present_hid" />
								</div>
							</div>
						</div>
						<div class="col-md-4" id="present_tehsil_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Tehsil Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_tehsil_other"
										name="present_tehsil_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Nationality </label>
								</div>
								<div class="col-md-8">
									<select name="nationality" id="nationality"
										class="form-control-sm form-control"
										onchange="national_other();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getNationalityList}"
											varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.nationality}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
								<input type="hidden" name="nationality_hid" id="nationality_hid" />
							</div>
						</div>
						<div class="col-md-4" id="nationality_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Nationality Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="nationality_other"
										name="nationality_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
        <div class="col-md-6">
            <div class="row form-group">
                <div class="col-md-4">
                    <label class="form-control-label">
                        <strong style="color: red;"></strong> Photograph
                    </label>
                </div>
                <div class="col-md-8">
                    <input type="file" 
                           id="identity_image" 
                           name="identity_image" 
                           accept="image/*" 
                            onchange="previewImage(this)">
                    <label class="form-control-label" style="color: red;">
                        <strong style="color: red;">Note:</strong> (Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed.)
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="row form-group">
                <div class="image-container">
                    <img id="identity_image_preview" 
                         alt="Officer Image" 
                         src="js/img/No_Image.jpg" 
                         width="100" 
                         height="100" 
                         onclick="openFullScreen()"
                    />
                    <div class="hover-overlay" onclick="openFullScreen()">
                        Click to View
                    </div>
                </div>
               
                <input type="text" id="pre_identity_id" name="pre_identity_id" value="0" style="display: none">
            </div>
        </div>
    </div>
       <div id="fullScreenModal">
        <span id="closeFullScreen" onclick="closeFullScreen()">&times;</span>
        <img id="fullScreenImage">
    </div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Non Effective </label>
								</div>
								<div class="col-md-8">
									<label for="eff_yes">Yes</label> <input class=" " type="radio"
										onchange="oneff1(this.value);" id="eff_yes" value="yes"
										name="eff">&nbsp;&nbsp&nbsp;&nbsp <label for="eff_no">No</label> <input
										class=" " type="radio" onchange="oneff1(this.value);"
										id="eff_no" value="no" name="eff" checked>

								</div>

							</div>
						</div>
					</div>


					<div class="col-md-12" style="display: none;" id="div_eff">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Non Effective</label>
								</div>
								<div class="col-md-8">
									<select name="non_effective" id="non_effective"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getRegNonEff}" varStatus="num">
											<option value="${item[0]}"
												<c:if test="${item[0] eq Details.non_effective}">selected="selected"</c:if>>${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Non Effective Date </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="date_non_effective"
										id="non_effective_date" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 93%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');SetMin();"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">

								</div>
							</div>
							<input type="hidden" name="app_edit_hd_r" id="app_edit_hd_r">
						</div>

					</div>

				</div>

				<div class="card-footer" align="center" class="form-control">
					<input type="submit" class="btn btn-primary btn-sm" value="Update"
						onclick="return validate();">
					<a href="Search_civilian_regular" class="btn btn-info btn-sm">Back</a>
				</div>
			</div>
		</div>
	</div>
</form:form>


<script>

  function lengthadhar() {
		document.getElementById("pers_aadhar_no11").maxLength = "4";
		document.getElementById("pers_aadhar_no11").minLength = "4";
		document.getElementById("pers_aadhar_no2").maxLength = "4";
		document.getElementById("pers_aadhar_no2").minLength = "4";
		document.getElementById("pers_aadhar_no3").maxLength = "4";
		document.getElementById("pers_aadhar_no3").minLength = "4";	  
	} 
		
	function movetoNext(current, nextFieldID) {  
		if (current.value.length >= current.maxLength) {  
			document.getElementById(nextFieldID).focus();  
		}  
	}
  
  function onWes(val) {
		if(val == "yes" )
	    {
			 $("div#div_wes").show();
			 ServiceOtherFn();
		} 
		else 
		{
			 $("div#div_wes").hide();
		}
	}
  
  function onWpwd(val) {
		if(val == "yes" )
	    {
			 $("div#div_wpwd").show();
		} 
		else 
		{
			 $("div#div_wpwd").hide();
		}
	}
  
  function ServiceOtherFn() {
		
		var sx = $("#whether_ex_serviceman option:selected").text();
		 $("#whether_ex_serviceman_hid").val(sx); 
		if(sx == "OTHER"){
			$("#other_service_div").show();
		}
		else{
			$("#other_service_div").hide();
		}
	}
  
  
  function oneff1(val) {
		if(val == "yes" )
	    {
			 $("div#div_eff").show();
		} 
		else 
		{
			 $("div#div_eff").hide();
		}
	}
	
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
		        	  document.getElementById("unit_sus_no").value="";
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
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	// unit name
	 $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
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
				        	  document.getElementById("unit_posted_to").value="";
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

	 jQuery(function($){ //on document.ready  

		 datepicketDate('dt_of_authority');
		 datepicketDate('dt_of_join_govt_service');
		 datepicketDate('dt_of_designation');
		 datepicketDate('non_effective_date');
// 		 datepicketDate('date_of_tos');
		 datepicketDate('dob');
	});
	 
	 
	 $(document).ready(function() {
		 
		  var r = ('${roleAccess}');
			 $("#app_edit_hd_r").val(r);
			
			
		
			 var r =('${roleAccess}');
				if( r == "Unit"){
					
					 $("#unit_sus_no_hid").show();
					 $("#unit_name_l").show();
					 
				}
				else  if(r == "MISO"){
					
					 $("input#unit_sus_no").show();		 
					 $("input#unit_posted_to").show();
					
				}
		 
		 $.post("getClassificationOfServiceList?"+key+"="+value,function(j) {
				$("#divContainer").empty();
		 		for ( var i = 0; i < j.length ;i++) {
		 		  var classification_services = '${Details.classification_services}';
		 			var a = j[i][0];
		 		 	if(a == classification_services){
		 				$("#divContainer").append ( "<label>" + j[i][1] + " </label>&nbsp;&nbsp<input type='radio' name='classification_services' onchange='ongaz(this.value);' id='classification_services' value='" + j[i][0] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
		 			} else{ 
		 				$("#divContainer").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='classification_services' onchange='ongaz(this.value);' id='classification_services' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
		 			} 
		 		} 
		 		ongaz('${Details.classification_services}');
		 	});
			
			
			$.post("getServiceStatusList?"+key+"="+value,function(j) {
				$("#div_service_status").empty();
		 		for ( var i = 0; i < j.length ;i++) { 
		 			  var service_status = '${Details.service_status}';
			 			var as = j[i][0];

			 		 	if(as == service_status){
			 		 		$("#div_service_status").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='service_status' id='service_status' value='" + j[i][0] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
			 			} else{ 
		 				$("#div_service_status").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='service_status' id='service_status' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
		 		} 
			 		 	}
		 	});
			
			
			/* $.post("GetCiv_R_GroupList?"+key+"="+value,function(j) {
				$("#div_group").empty();
		 		for ( var i = 0; i < j.length ;i++) { 
		 			
		 			  var civ_group = '${Details.civ_group}';
			 			var g = j[i][0];

			 		 	if(g == civ_group){
			 		 		$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][0] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
			 			} else{ 
		 				$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
		 		} 
			 		 	}
		 	}); */
			
			$.post("getCivilianTypeList?"+key+"="+value,function(j) {
				$("#div_type").empty();
		 		for ( var i = 0; i < j.length ;i++) { 
		 			  var civ_type = '${Details.civ_type}';
			 			var t = j[i][0];

			 		 	if(t == civ_type){
			 		 		$("#div_type").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='type' id='type' value='" + j[i][0] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
			 			} else{ 
		 				$("#div_type").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='type' id='type' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp " );
		 		} 
		 		}
		 	});
		 
		 
		 var $select2 = $('.select2').select2({
			    containerCssClass: "wrap"
			}); 
		 
		  gender_other1();
		  classification_trade_other();
		  rel_other();
		  mot_tongue_other();
		  national_other();
		 
		/*   if ('${Details.original_country_other}' != null && '${Details.original_country_other}' !="")
			 {
			 $("#origin_country_other_div").show();
			 }
		 else
			 {
			 $("#origin_country_other_div").hide();
			 }
		  
		  if ('${Details.original_state_other}' != null && '${Details.original_state_other}' !="")
			 {
			 $("#origin_state_other_div").show();
			 }
		 else
			 {
			 $("#origin_state_other_div").hide();
			 }
		  
		  if ('${Details.original_district_other}' != null && '${Details.original_district_other}' !="")
			 {
			 $("#origin_dist_other_div").show();
			 }
		 else
			 {
			 $("#origin_dist_other_div").hide();
			 }
		  
		  if ('${Details.original_tehshil_other}' != null && '${Details.original_tehshil_other}' !="")
			 {
			 $("#original_tehsil_other_div").show();
			 }
		 else
			 {
			 $("#original_tehsil_other_div").hide();
			 }
		  
		  if ('${Details.present_country_other}' != null && '${Details.present_country_other}' !="")
			 {
			 $("#present_country_other_div").show();
			 }
		 else
			 {
			 $("#present_country_other_div").hide();
			 }
		  
		  
		  if ('${Details.present_state_other}' != null && '${Details.present_state_other}' !="")
			 {
			 $("#present_state_other_div").show();
			 }
		 else
			 {
			 $("#present_state_other_div").hide();
			 }

		  if ('${Details.present_district_other}' != null && '${Details.present_district_other}' !="")
			 {
			 $("#present_dist_other_div").show();
			 }
		 else
			 {
			 $("#present_dist_other_div").hide();
			 }
		  
		  
		  if ('${Details.present_tehshil_other}' != null && '${Details.present_tehshil_other}' !="")
			 {
			 $("#present_tehsil_other_div").show();
			 }
		 else
			 {
			 $("#present_tehsil_other_div").hide();
			 }
		  
		  if ('${Details.nationality_other}' != null && '${Details.nationality_other}' !="")
			 {
			 $("#nationality_other_div").show();
			 }
		 else
			 {
			 $("#nationality_other_div").hide();
			 } */
		  if('${Details.non_effective}' != 0 && '${Details.date_non_effective}' != ""){
			  oneff1('yes');
				 $("#eff_yes").prop("checked", true);
			 }else{
				 oneff1('no');
				 $("#eff_no").prop("checked", true);
			 }
		  
		  
		 
		 $("#authority").val('${Details.authority}');
		 $("#dt_of_authority").val(ParseDateColumncommission('${Details.dt_of_authority}'));
		 $("#employee_no").val('${Details.employee_no}');
		 $("#employee_no_old").val('${Details.employee_no}');
		
		 $("#unit_sus_no").val('${Details.sus_no}');
		 $("#unit_sus_no_hid").text('${Details.sus_no}');
	
		 var sus_no = '${Details.sus_no}';
			if(sus_no!=""){
	    		getunit(sus_no);
	    	}
	    	
	    	function getunit(val) {	
	            $.post("getTargetUnitNameList?"+key+"="+value, {
	            	sus_no : sus_no
	        }, function(j) {
	                
	        }).done(function(j) {
	    				   var length = j.length-1;
	    	                   var enc = j[length].substring(0,16); 
	    	                   $("#unit_posted_to").val(dec(enc,j[0])); 
	    	                   $("#unit_name_l").text(dec(enc,j[0])); 
	        }).fail(function(xhr, textStatus, errorThrown) {
	        });
	    }
		 
		 $("#dob").val(ParseDateColumncommission('${Details.dob}'));  
		 $("#dt_of_join_govt_service").val(ParseDateColumncommission('${Details.joining_date_gov_service}'));
		 $("#non_effective_date").val(ParseDateColumncommission('${Details.date_non_effective}'));
		 $("#gender_other").val('${Details.gender_other}');
		 $("#classification_of_trade_other").val('${Details.classification_trade_other}');
		 $("#religion_other").val('${Details.religion_other}');
		 $("#mother_tongue_other").val('${Details.mother_tongue_other}');
		
		 $("#dt_of_designation").val(ParseDateColumncommission('${Details.designation_date}'));
		 $("#date_of_tos").val(ParseDateColumncommission('${Details.date_of_tos}')); 
		 
		
		 
			var adhar = '${Details.aadhar_card}';
			var arrTo=adhar.substring(0, 4);
			var arrTo1=adhar.substring(4, 8);
			var arrTo2=adhar.substring(8);

			$("#pers_aadhar_no11").val(arrTo);
			$("#pers_aadhar_no2").val(arrTo1);
			$("#pers_aadhar_no3").val(arrTo2); 
		 
		 
		 if('${Details.classification_services}' == "1")
		 	$("#cos_gaz").prop("checked", true);
		 else if('${Details.classification_services}' == "2")
				$("#cos_non_gaz").prop("checked", true);
		 
		 if('${Details.civ_group}' == "A")
			 	$("#group_a").prop("checked", true);
			 else if('${Details.civ_group}' == "B")
					$("#group_b").prop("checked", true);
			 else if('${Details.civ_group}' == "C")
					$("#group_c").prop("checked", true);
			 
		 if('${Details.service_status}' == "1")
			 	$("#service_temporary").prop("checked", true);
			 else if('${Details.service_status}' == "2")
					$("#service_permanent").prop("checked", true);
			 
		 if('${Details.civ_type}' == "1")
			 	$("#type_industrial").prop("checked", true);
			 else if('${Details.civ_type}' == "2")
					$("#type_non_industrial").prop("checked", true);
			 
		 
		 if('${Details.whether_ex_serviceman}' != ""){
			 
			 onWes('yes');
			 $("#wes_yes").prop("checked", true);
		 }else{
			 onWes('no');
			 $("#wes_no").prop("checked", true);
		 }
		 
		 if('${Details.whether_person_disability}' != ""){
			 onWpwd('yes');
			 $("#wpwd_yes").prop("checked", true);
		 }else{
			 onWpwd('no');
			 $("#wpwd_no").prop("checked", true);
		 }
		 
		 
		 
		 
		 
		 $.ajaxSetup({
			    async: false
			});
		 
		 
		 $("#country_original").val('${Details.country_original}');
		 fn_pre_domicile_Country_ready();
		 
		 $("#original_state").val('${Details.state_original}');
		 fn_pre_domicile_state();
		 
		 $("#original_district").val('${Details.district_original}');
	     fn_pre_domicile_district();
		 
		 $("#original_tehsil").val('${Details.tehsil_origin}');
		 fn_pre_domicile_tesil();
		 
		 
		 $("#country1").val('${Details.country_present}'); 
		    fn_pre_domicile_Country_present();
			
			$("#present_state").val('${Details.state_present}'); 
			fn_pre_domicile_state_present();
			
			$("#present_district").val('${Details.district_present}'); 
			fn_pre_domicile_district_present();
			
			$("#present_tehsil").val('${Details.tehsil_present}'); 
			fn_pre_domicile_tesil_present(); 
			
			
			
			 $("#original_country_other").val('${Details.original_country_other}');
			 $("#original_state_other").val('${Details.original_state_other}');
			 $("#original_district_other").val('${Details.original_district_other}');
			 $("#original_tehsil_other").val('${Details.original_tehshil_other}');
			 
			 $("#present_country_other").val('${Details.present_country_other}');
			 $("#present_state_other").val('${Details.present_state_other}');
			 $("#present_district_other").val('${Details.present_district_other}');
			 $("#present_tehsil_other").val('${Details.present_tehshil_other}');
			 $("#nationality_other").val('${Details.nationality_other}');
			 $("#service_other").val('${Details.service_other}');
			 
			 $("#mobile_no").val('${Details.mobile_no}');
				var id = '${Details.id}'		
					$('#identity_image_preview').attr("src", "regularCivIdentityConvertpath?i_id="+id+" ");
		
		 var r =('${roleAccess}');
			if( r == "Unit"){
				
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
			}
			else  if(r == "MISO"){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_posted_to").show();
			}
		});
	 
	 function dobValidate(b){

		  var today=new Date();
		  var nowyear=today.getFullYear();
		  var nowmonth=today.getMonth()+1;
		  var nowday=today.getDate();
		     var from_d=$("#"+b.id).val();
		     from_d = from_d.replace("/", "-");
		 
		  
			 
		          
		     var birthyear=from_d.substring(6,10);
		     var birthmonth=from_d.substring(3,5);
		     var birthday=from_d.substring(0,2);
		     var frm_d = birthday+"-"+birthmonth+"-"+birthyear; 
		  var age=nowyear-birthyear;
		  var age_month=birthmonth-nowmonth;
		  var age_day=nowday-birthday;
		  if(age<18){
			  alert("please enter age above 18 Years.");
			  $("#"+b.id).val("");

			  return false;

			  
		  }
		  if(age==18){
			  if(nowmonth<birthmonth){
				  
				  alert("please enter age above 18 Years.");
				  $("#"+b.id).val("");

				  return false;

			  }
			  if(nowmonth==birthmonth){
				  if(nowday<birthday){
					  alert("please enter age above 18 Years.");
					  $("#"+b.id).val("");

					  return false;

				  }
			  }
		  }
		  
		  if(age>60){
			  alert("please enter age below 60 Years.");
			  $("#"+b.id).val("");

			  return false;

			  
		  }
		  if(age==60){
			  if(nowmonth>birthmonth){
				  
				  alert("please enter age below 60 Years.");
				  $("#"+b.id).val("");

				  return false;
				 
			  }
			  if(nowmonth==birthmonth){
				  if(nowday>birthday){
					  alert("please enter age below 60 Years.");
					  $("#"+b.id).val("");

					  return false;

				  }
			  }
		  }
		  
		 
		  
	  }
	 
	 function SetMin(){
			if ($("input#dob").val() != "") {
				var dob_dt = $("input#dob").val();
				setMinDate('dt_of_join_govt_service', dob_dt);
				setMinDate('dt_of_designation', dob_dt);
				setMinDate('non_effective_date', dob_dt);
				setMinDate('date_of_tos', dob_dt);
			}
		}
	 
	 
	 function gender_other1() {
			var gender = $("#gender option:selected").text();
			 $("#gender_hid").val(gender); 
			if(gender == "OTHER"){
				$("#gender_other_div").show();
			}
			else{
				$("#gender_other_div").hide();
				 $("#gender_hid").val(""); 
			}
		}
	 
	 function classification_trade_other() {
		
			var classification_of_trade = $("#classification_of_trade option:selected").text();
			 $("#classification_of_trade_hid").val(classification_of_trade);
			if(classification_of_trade == "OTHERS"){
				$("#classification_of_trade_other_div").show();
			}
			else{
				$("#classification_of_trade_other_div").hide();
				 $("#classification_of_trade_hid").val("");
			}
		}
	 function rel_other() {
		
			var religion = $("#religion option:selected").text();
		$("#religion_hid").val(religion);
		
			if(religion == "OTHERS"){
				$("#religion_other_div").show();
			
			}
			else{
				$("#religion_other_div").hide();
				$("#religion_hid").val("");
			}
		}
	 function mot_tongue_other() {
		 var monther_tongue = $("#monther_tongue option:selected").text();
		 $("#mother_tongue_hid").val(monther_tongue);
		 
			if(monther_tongue == "OTHERS"){
				$("#mother_tongue_other_div").show();
			}
			else{
				$("#mother_tongue_other_div").hide();
				$("#monther_tongue_hid").val("");
			}
		}
	 function national_other() {
		 var nationality = $("#nationality option:selected").text();
		 $("#nationality_hid").val(nationality);
			if(nationality == "OTHERS"){
			    $("#nationality_other_div").show();
			}
			else{
				$("#nationality_other_div").hide();
				 $("#nationality_hid").val("");
			}
		}
	 function fn_pre_domicile_Country() {
			var text = $("#country_original option:selected").text();
			
			 $("#country_original_hid").val(text);
			if(text == "OTHERS"){
				$("#origin_country_other_div").show();
			}
			else{
				$("#origin_country_other_div").hide();
				$("#original_country_other").val("");
				$("#country_original_hid").val("");
			}
			
			var obj=$("#country_original");
			//FnTehsilDisableEnable(obj,'pre_domicile_tesil','add_other_te','pre_domicile_tesil_other');
			
			 var contry_id = $('select#country_original').val();

			 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#original_state").html(options);
			 				fn_pre_domicile_state();
			 				fn_pre_domicile_district();
			 				fn_pre_domicile_tesil(); 
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 
	 function fn_pre_domicile_Country_ready() {
			var text = $("#country_original option:selected").text();
			
			 $("#country_original_hid").val(text);
			if(text == "OTHERS"){
				$("#origin_country_other_div").show();
			}
			else{
				$("#origin_country_other_div").hide();
				$("#original_country_other").val("");
				$("#country_original_hid").val("");
			}
			
			var obj=$("#country_original");
			//FnTehsilDisableEnable(obj,'pre_domicile_tesil','add_other_te','pre_domicile_tesil_other');
			
			 var contry_id = $('select#country_original').val();

			 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#original_state").html(options);
			 			
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_state() {
			var text = $("#original_state option:selected").text();
		
			 $("#original_state_hid").val(text);
			if(text == "OTHERS"){
			
				$("#origin_state_other_div").show();
			}
			else{
				$("#origin_state_other_div").hide();
				$("#original_state_other").val("");
				$("#original_state_hid").val("");
		}
			 var state_id =$('select#original_state').val();

			 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
			 		 				 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#original_district").html(options);
			 				fn_pre_domicile_district();
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_district() {
			
			var text = $("#original_district option:selected").text();
			
			 $("#district_original_hid").val(text);
			 
			
			if(text == "OTHERS"){
				
				$("#origin_dist_other_div").show();
			}
			else{
				$("#origin_dist_other_div").hide();
				$("#original_district_other").val("");
				$("#district_original_hid").val("");
		}
			 var Dist_id = $('select#original_district').val();

			 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#original_tehsil").html(options);

			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_tesil() {
			
			var text = $("#original_tehsil option:selected").text();
			$("#original_tehsil_hid").val(text);
			
			if(text == "OTHERS"){
				$("#original_tehsil_other_div").show();
			}
			else{
				$("#original_tehsil_other_div").hide();
				$("#original_tehsil_other").val("");
				$("#original_tehsil_hid").val("");
		}
		}
	 ///for present address
	 
	  function fn_pre_domicile_Country_present() {
			var text = $("#country1 option:selected").text();
			$("#country1_hid").val(text);
			if(text == "OTHERS"){
				$("#present_country_other_div").show();
			}
			else{
				$("#present_country_other_div").hide();
				$("#present_country_other").val("");
				$("#country1_hid").val("");
			}
			
			var obj=$("#country1");
			//FnTehsilDisableEnable(obj,'pre_domicile_tesil','add_other_te','pre_domicile_tesil_other');
			
			 var contry_id = $('select#country1').val();

			 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#present_state").html(options);
			 				fn_pre_domicile_state_present();
			 				fn_pre_domicile_district_present();
			 				fn_pre_domicile_tesil_present(); 
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_state_present() {
			var text = $("#present_state option:selected").text();
			$("#present_state_hid").val(text);
			if(text == "OTHERS"){
							$("#present_state_other_div").show();
			}
			else{
				$("#present_state_other_div").hide();
				$("#present_state_other").val("");
				$("#present_state_hid").val("");
		}
			 var state_id =$('select#present_state').val();

			 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#present_district").html(options);
			 				fn_pre_domicile_district_present();
			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_district_present() {
			
			var text = $("#present_district option:selected").text();
			$("#present_district_hid").val(text);
			if(text == "OTHERS"){
				//alert("district other")
				$("#present_dist_other_div").show();
			}
			else{
				$("#present_dist_other_div").hide();
				$("#present_district_other").val("");
				$("#present_district_hid").val("");
		}
			 var Dist_id = $('select#present_district').val();

			 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#present_tehsil").html(options);
			 				fn_pre_domicile_tesil_present();

			 			}).fail(function(xhr, textStatus, errorThrown) {
			 	});
		}
	 function fn_pre_domicile_tesil_present() {
			
			var text = $("#present_tehsil option:selected").text();
			$("#present_tehsil_hid").val(text);
			if(text == "OTHERS"){
				$("#present_tehsil_other_div").show();
			}
			else{
				$("#present_tehsil_other_div").hide();
				$("#present_tehsil_other").val("");
				$("#present_tehsil_hid").val("");
		}
		}
	 ///end
	 function validate() {
		 
		 if ($("#authority").val().trim() == "") {
				alert("Please Enter Authority");
				$("#authority").focus();
				return false;
			}
		 
			if ($("#dt_of_authority").val() == "DD/MM/YYYY" || $("#dt_of_authority").val().trim()=="") {
				alert("Please Select Date of Authority");
				$("#dt_of_authority").focus();
				return false;
			} 
		 
		 var hd_r = $("#app_edit_hd_r").val();
		 if (hd_r == "MISO") {
			 
			 if ($("#unit_sus_no").val().trim() == "") {
					alert("Please Enter Unit SUS No");
					$("#unit_sus_no").focus();
					return false;
				}
			 if ($("#unit_posted_to").val().trim() == "") {
					alert("Please Enter Unit Posted To");
					$("#unit_posted_to").focus();
					return false;
				}
		}
			
		 if ($("#employee_no").val().trim() == "") {
				alert("Please Enter Employee No.");
				$("#employee_no").focus();
				return false;
			}
		 
		 
		 var mobileNo = $("#mobile_no").val().trim();
		   if (mobileNo == "") {
             alert("Please Enter Mobile No.");
             $("#mobile_no").focus();
             return false;
         }  else if (mobileNo.length < 10) {
             alert('Please enter a 10-digit mobile number');
             $("#mobile_no").focus();
             return false;
         } else if (!/^\d+$/.test(mobileNo)) {
             alert('Mobile number should contain only digits');
             $("#mobile_no").focus();
             return false;
         }
 
		 if ($("#first_name").val().trim() == "") {
				alert("Please Enter First Name");
				$("#first_name").focus();
				return false;
			} 
		 
		 if ($("#dob").val() == "DD/MM/YYYY" || $("#dob").val().trim()=="") {
				alert("Please Select Date of Birth");
				$("#dob").focus();
				return false;
			} 
		
			if ($("#gender").val() == "0") {
				alert("Please Select Gender");
				$("#gender").focus();
				return false;
			}
			
			if ($("#gender").val() != "0") {
				var gender = $("#gender option:selected").text();
				 
				if(gender == "OTHER"){
					if ($("#gender_other").val() == "") {
						alert("Please Enter Gender Other");
						$("#gender_other").focus();
						return false;
					} 
			}
			}
			
			if ($("input[name='classification_services']:checked").val() == undefined) {
				alert("Please Select Classification of Services");
				$("#classification_services").focus();
				return false;
			}
			

			   if ($("input[name='group']:checked").val() == undefined) {
					alert("Please Select Group");
					$("#group").focus();
					return false;
				}
			
				
				if ($("#category").val() == "0") {
					alert("Please Select Category");
					$("#category").focus();
					return false;
				} 
				
				if ($("input[name='service_status']:checked").val() == undefined) {
					alert("Please Select Service Status");
					$("#service_status").focus();
					return false;
				}
				if ($("input[name='classification_services']:checked").val() == 2) {
				if ($("#classification_of_trade").val() == "0") {
					alert("Please Select Classification of Trade");
					$("#classification_of_trade").focus();
					return false;
				}
				}
				
				if ($("#classification_of_trade").val() != "0") {
					var classification_of_trade = $("#classification_of_trade option:selected").text();
					 
					if(classification_of_trade == "OTHERS"){
						if ($("#classification_of_trade_other").val().trim() == "") {
							alert("Please Enter The  Classification of Trade");
							$("#classification_of_trade_other").focus();
							return false;
						} 
					}
				}
				
				if ($("input[name='type']:checked").val() == undefined) {
					alert("Please Select Type");
					$("#type").focus();
					return false;
				}
				
				
				var wes_yes = $("input[name='wes']:checked").val();
			    if(wes_yes=="yes"){
                    
                    if($("select#whether_ex_serviceman").val()== 0)
                            {
                               alert("Please Select Whether Ex-Serviceman");
                                    $("select#whether_ex_serviceman").focus();
                                    return false;
                            }
                    else{
                    	var sx = $("#whether_ex_serviceman option:selected").text();
               		 $("#whether_ex_serviceman_hid").val(sx); 
               		if(sx == "OTHER"){
               			
               			if($("#service_other").val()== "")
                        {
                           alert("Please Enter Service Name Other");
                                $("#service_other").focus();
                                return false;
                        }
               		}
                    	 
                    }
			    }
				var wpwd_yes = $("input[name='wpwd']:checked").val();
			    if(wpwd_yes=="yes"){
                    
                    if($("select#whether_person_disability").val()== 0)
                            {
                               alert("Please Select Whether Person With Disability");
                                    $("select#whether_person_disability").focus();
                                    return false;
                            }
			    }
			    
				if ($("#dt_of_join_govt_service").val() == "DD/MM/YYYY" || $("#dt_of_join_govt_service").val().trim()=="") {
					alert("Please Select Date of Joining Govt Service");
					$("#dt_of_join_govt_service").focus();
					return false;
				} 
				
				if(getformatedDate($("input#dob").val()) > getformatedDate($("#dt_of_join_govt_service").val())) {
					alert("Date of Joining  should Be Greater Than Date of Birth");
					return false;
			  }
				
				if ($("#dt_of_designation").val() == "DD/MM/YYYY" || $("#dt_of_designation").val().trim()=="") {
					alert("Please Select Date of Designation");
					$("#dt_of_designation").focus();
					return false;
				} 
				
				if(getformatedDate($("input#dt_of_join_govt_service").val()) > getformatedDate($("#dt_of_designation").val())) {
					alert("Date of Designation  should Be Greater Than Date of Joining Govt Service");
					return false;
			  	}
				
				if ($("#pay_level").val() == "0") {
					alert("Please Select Pay Level");
					$("#pay_level").focus();
					return false;
				} 
				
				if ($("#date_of_tos").val() == "DD/MM/YYYY" || $("#date_of_tos").val().trim()=="") {
					alert("Please Select Date of TOS");
					$("#date_of_tos").focus();
					return false;
				} 
				
				if(getformatedDate($("input#dt_of_join_govt_service").val()) > getformatedDate($("#date_of_tos").val())) {
					alert("Date of TOS  should Be Greater Than Date of Joining");
					$("#date_of_tos").focus();
					return false;
			  	}
				
				if ($("#religion").val() != "0") {
					var religion = $("#religion option:selected").text();
					 
					if(religion == "OTHERS"){
						if ($("#religion_other").val().trim() == "") {
							alert("Please Enter The Religion Other");
							$("#religion_other").focus();
							return false;
						} 
					}
				}
				
				if ($("#monther_tongue").val() != "0") {
					var monther_tongue = $("#monther_tongue option:selected").text();
					 
					if(monther_tongue == "OTHERS"){
						if ($("#mother_tongue_other").val().trim() == "") {
							alert("Please Enter Mother Tongue Other");
							$("#mother_tongue_other").focus();
							return false;
						} 
					}
				}
				
				if ($("#country_original").val() == "0") {
					alert("Please Select Original Country");
					$("#country_original").focus();
					return false;
				}
				
				if ($("#original_state").val() == "0") {
					alert("Please Select Original State");
					$("#original_state").focus();
					return false;
				} 
				//original Domicile
				if ($("#country_original").val() != "0") {
					var original_country = $("#country_original option:selected").text();
					 
					if(original_country == "OTHERS"){
						if ($("#original_country_other").val().trim() == "") {
							alert("Please Enter The Original Domicile of Country Other");
							$("#original_country_other").focus();
							return false;
						} 
					}
				}
				
				if ($("#original_state").val() != "0") {
					var original_state = $("#original_state option:selected").text();
					 
					if(original_state == "OTHERS"){
						if ($("#original_state_other").val().trim() == "") {
							alert("Please Enter The Original Domicile of State Other");
							$("#original_state_other").focus();
							return false;
						} 
					}
				}
				if ($("#original_district").val() != "0") {
					var original_district = $("#original_district option:selected").text();
					 
					if(original_district == "OTHERS"){
						if ($("#original_district_other").val().trim() == "") {
							alert("Please Enter The Original Domicile of District Other");
							$("#original_district_other").focus();
							return false;
						} 
					}
				}
				if ($("#original_tehsil").val() != "0") {
					var original_tehsil = $("#original_tehsil option:selected").text();
					 
					if(original_tehsil == "OTHERS"){
						if ($("#original_tehsil_other").val().trim() == "") {
							alert("Please Enter The Original Domicile of Tehsil Other");
							$("#original_tehsil_other").focus();
							return false;
						} 
					}
				}
				//present domisile
				if ($("#country1").val() == "0") {
					alert("Please Select Present Country");
					$("#country1").focus();
					return false;
				} 
				
				if ($("#country1").val() != "0") {
					var country1 = $("#country1 option:selected").text();
					 
					if(country1 == "OTHERS"){
						if ($("#present_country_other").val().trim() == "") {
							alert("Please Enter The Present Domicile of Country Other");
							$("#present_country_other").focus();
							return false;
						} 
					}
				}
				if ($("#present_state").val() == "0") {
					alert("Please Select Present State");
					$("#present_state").focus();
					return false;
				}
				if ($("#present_state").val() != "0") {
					var present_state = $("#present_state option:selected").text();
					 
					if(present_state == "OTHERS"){
						if ($("#present_state_other").val().trim() == "") {
							alert("Please Enter The Present Domicile of State Other");
							$("#present_state_other").focus();
							return false;
						} 
					}
				}
				if ($("#present_district").val() != "0") {
					var present_district = $("#present_district option:selected").text();
					 
					if(present_district == "OTHERS"){
						if ($("#present_district_other").val().trim() == "") {
							alert("Please Enter The Present Domicile of District Other");
							$("#present_district_other").focus();
							return false;
						} 
					}
				}
				if ($("#present_tehsil").val() != "0") {
					var present_tehsil = $("#present_tehsil option:selected").text();
					 
					if(present_tehsil == "OTHERS"){
						if ($("#present_tehsil_other").val().trim() == "") {
							alert("Please Enter The Present Domicile of Tehsil Other");
							$("#present_tehsil_other").focus();
							return false;
						} 
					}
				}
				
				if ($("#nationality").val() != "0") {
					var nationality = $("#nationality option:selected").text();
					 
					if(nationality == "OTHERS"){
						if ($("#nationality_other").val() == "") {
							alert("Please Enter Nationality Other");
							$("#nationality_other").focus();
							return false;
						} 
					}
				}
				if ($("#classification_of_trade").val() != "0") {
					var classification_of_trade = $("#classification_of_trade option:selected").text();
					 
					if(classification_of_trade == "OTHERS"){
						if ($("#classification_of_trade_other").val() == "") {
							alert("Please Enter Classification of Trade Other");
							$("#classification_of_trade_other").focus();
							return false;
						} 
					}
				}
				
				var eff_yes = $("input[name='eff']:checked").val();
                
                if(eff_yes=="yes"){
                        
                           if($("select#non_effective").val()=="0")
                                   {
                                      alert("Please Select Non Effective");
                                           $("select#non_effective").focus();
                                           return false;
                                   }
                           if ($("input#non_effective_date").val() == "DD/MM/YYYY" || $("input#non_effective_date").val().trim()=="") {
                                       alert("Please Select Date of Non Effective");
                                       $("input#non_effective_date").focus();
                                       return false;
                               } 

						if(getformatedDate($("input#dob").val()) > getformatedDate($("#non_effective_date").val())) {
       						alert("Date of Non Effective should Be Greater Than Date of Birth ");
       						return false;
       			  	 	}
                         
                        if(getformatedDate($("input#dt_of_join_govt_service").val()) > getformatedDate($("#non_effective_date").val())) {
        					alert("Date of Non Effective should Be Greater Than Date of Joining Govt Service ");
        					return false;
        			     }
                        if(getformatedDate($("input#dt_of_designation").val()) > getformatedDate($("#non_effective_date").val())) {
        					alert("Date of Non Effective should Be Greater Than Date of Designation");
        					return false;
        			  	}
                } 
		 return true;
	}
  

  </script>

<script>
 
function ongaz(val){	
	  if(val == 2 )
	    {
			 $("div#div_classification_of_trade").show();			 	
			
				$.post("GetCiv_R_GroupList?"+key+"="+value,function(j) { 
					$("#div_group").empty();
			 		for ( var i = 1; i < j.length ;i++) { 
			 			
			 			  var civ_group = '${Details.civ_group}';
				 			var g = j[i][1];

				 		 	if(g == civ_group){
				 		 		$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][1] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
				 			} else{ 
			 				$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][1] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
			 		} 
				 		 	}
			 	}); 
		} 
		else 
		{
			 $("div#div_classification_of_trade").hide();		
				$.post("GetCiv_R_GroupList?"+key+"="+value,function(j) { 
					$("#div_group").empty();
			 		for ( var i = 0; i < j.length-1 ;i++) { 
			 			
			 			  var civ_group = '${Details.civ_group}';
				 			var g = j[i][1];

				 		 	if(g == civ_group){
				 		 		$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][0] + "' checked /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
				 			} else{ 
			 				$("#div_group").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='group' id='group' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp  " );
			 		} 
				 		 	}
			 	}); 
		}
}
 function AvoidSpace(event) {
	    var k = event ? event.which : window.event.keyCode;
	    if (k == 32) return false;
	}
 
 
 function openFullScreen() {
        const modal = document.getElementById('fullScreenModal');
        const fullScreenImg = document.getElementById('fullScreenImage');
        const previewImg = document.getElementById('identity_image_preview');         
    
        if (previewImg.src && previewImg.src.indexOf('No_Image.jpg') === -1) {
            fullScreenImg.src = previewImg.src;
            modal.style.display = 'flex';
        }
    }

    function closeFullScreen() {
        const modal = document.getElementById('fullScreenModal');
        modal.style.display = 'none';
    }
   
    window.onclick = function(event) {
        const modal = document.getElementById('fullScreenModal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
    
    function previewImage(input) {
        
        var maxSizeInBytes = 2 * 1024 * 1024; 
        var file = input.files[0];
        
        if (file) {      
            if (file.size > maxSizeInBytes) {
                alert('File is too large. Maximum file size is 2MB.');
                input.value = ''; 
                return;
            }
          
            var validTypes = ['image/jpeg', 'image/png', 'image/jpg'];
            if (!validTypes.includes(file.type)) {
                alert('Invalid file type. Only JPG, JPEG, and PNG are allowed.');
                input.value = ''; 
                return;
            }

        
            var preview = document.getElementById('identity_image_preview');
            preview.src = window.URL.createObjectURL(file);
      
           
        }
    }

    function emp_no_already_exist()
	 {
		
		 var emp_no = $("input#employee_no").val();	
		 var emp_old = $("#employee_no_old").val();		
		 
		 $.ajaxSetup({
         async : false
 	   });
		 
		 if (emp_no.trim().toUpperCase() !== emp_old.trim().toUpperCase()){			 
		 var result_data = true;
		 $.post("getEmpNoAlreadyExist?"+key+"="+value, {emp_no : emp_no}).done(function(j) {
	
			 	if(j == false){
					alert("Employee Number already Exist.")					
					$("input#employee_no").val("");	
					 $("input#employee_no").focus();
					result_data = false;
				} 
				
			}); 
		 return result_data;
		 }
	 }

 </script>



