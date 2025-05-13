<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>

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

<form:form name="civilian_non_reg" id="civilian_non_reg"
	action="non_regular_civilian_act?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	commandName="civilian_details_cmd" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Non Regular Employee</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Authority </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="authority" name="authority"
										onkeyup="return specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off"
										maxlength="100">

								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Date of Authority </label>
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
										style="color: red;"> *</strong> Unit Sus No. </label>
								</div>
								<div class="col-md-8">
									<label id="unit_sus_no_hid" style="display: none"><b>
											${roleSusNo} </b></label> <input type="text" id="unit_sus_no"
										name="unit_sus_no" class="form-control autocomplete"
										autocomplete="off" style="display: none;" maxlength="50">

								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> *</strong> Unit Posted To </label>
								</div>
								<div class="col-md-8">
									<label id="unit_name_l" style="display: none"><b>${unit_name}</b></label>
									<input type="text" id="unit_posted_to" name="unit_posted_to"
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
										style="color: red;">* </strong> Employee No. </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="employee_no" name="employee_no"
										oninput="this.value = this.value.toUpperCase()"
										class="form-control autocomplete" autocomplete="off"
										onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)" maxlength="50" onchange="emp_no_already_exist();">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										style="color: red;"> </strong> Mobile No</label>
								</div>
								<div class="col-md-8">
									<input type="number" id="mobile_no" name="mobile_no"
										class="form-control" maxlength="10" min="0"
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
										style="color: red;">* </strong> First Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="first_name" name="first_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()"> <input
										type="hidden" id="id" name="id"
										class="form-control autocomplete" value="0" autocomplete="off"
										maxlength="50">

								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-5">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Middle Name</label>
								</div>
								<div class="col-md-7">
									<input type="text" id="middle_name" name="middle_name"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50" onkeypress="return onlyAlphabets(event);"
										oninput="this.value = this.value.toUpperCase()">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Last Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="last_name" name="last_name"
										class="form-control autocomplete" autocomplete="off"
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Date of Birth</label>
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
										style="color: red;">* </strong> Gender</label>
								</div>
								<div class="col-md-8">
									<select name="gender" id="gender"
										class="form-control-sm form-control"
										onchange="gender_other1();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getGenderList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
										style="color: red;">* </strong>Category </label>
								</div>
								<div class="col-md-8">
									<select name="category_belongs" id="category"
										class="form-control-sm form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCategoryList}"
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
										style="color: red;">* </strong>Type</label>
								</div>
								<div class="col-md-8">
									<div id="div_type" class="form-check-inline form-check"></div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Pay Head</label>
								</div>
								<div class="col-md-8">
									<select name="pay_level" id="pay_head"
										class="form-control-sm form-control"
										onchange="pay_head_oth();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getPayHeadList}"
											varStatus="num">
											<option value="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="pay_head_hid" id="pay_head_hid" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Date of Joining </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="joining_date_gov_service"
										id="date_of_joining" maxlength="10"
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
						<div class="col-md-6" id="pay_head_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Pay Head Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="pay_head_other" name="pay_head_other"
										maxlength="50" class="form-control autocomplete"
										autocomplete="off" onkeypress="return onlyAlphabets(event);" />
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12">
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
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
										style="color: red;">*</strong> Religion Other</label>
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
										style="color: red;">*</strong> Mother Tongue Other</label>
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
					<div class="col-md-12">
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
											name="pers_aadhar_no11" value="" type="text" maxlength="4"
											onkeyup="movetoNext(this, 'pers_aadhar_no2'),lengthadhar()"
											onkeypress="return isNumber(event)"> <input
											class="form-control" autocomplete="off"
											style="width: 18%; margin-left: 15px" id="pers_aadhar_no2"
											name="pers_aadhar_no2" value="" type="text" maxlength="4"
											onkeyup="movetoNext(this, 'pers_aadhar_no3'),lengthadhar()"
											onkeypress="return isNumber(event)"> <input
											class="form-control" autocomplete="off"
											style="width: 18%; margin-left: 15px" id="pers_aadhar_no3"
											name="pers_aadhar_no3" value="" type="text" maxlength="4"
											onkeyup="lengthadhar()" onkeypress="return isNumber(event)">
										<%-- 	               			<input type="hidden" id="pers_aadhar_no_hd" name="pers_aadhar_no_hd" class="form-control" value="${pers_aadhar_no77}"></input>  --%>
										<input type="hidden" id="aadhar_card" name="aadhar_card"
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
										class="form-control autocomplete" autocomplete="off"
										onchange=" isPAN(this); "
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
										style="color: red;">* </strong> Country </label>
								</div>
								<div class="col-md-8">
									<select name="country_original" id="country"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_Country();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCountryList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<select name="state_original" id="original_state"
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
										style="color: red;"> </strong> District </label>
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
										style="color: red;">*</strong> Country Other</label>
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
										style="color: red;">*</strong> State Other </label>
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
										style="color: red;">*</strong> District Other </label>
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
										style="color: red;"></strong> Tehsil </label>
								</div>
								<div class="col-md-8">
									<select name="tehsil_origin" id="original_tehsil"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_tesil();">
										<option value="0">--Select--</option>
									</select> <input type="hidden" name="original_tehsil_hid"
										id="original_tehsil_hid" />
								</div>
							</div>
						</div>
						<div class="col-md-4" id="original_tehsil_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Tehsil Other </label>
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
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="country1_hid" id="country1_hid" />
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
									<select name="state_present" id="present_state"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_state_present();">
										<option value="0">--Select--</option>
									</select> <input type="hidden" name="present_state_hid"
										id="present_state_hid" />
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">District </label>
								</div>
								<div class="col-md-8">
									<select name="district_present" id="present_district"
										class="form-control-sm form-control"
										onchange="fn_pre_domicile_district_present();">
										<option value="0">--Select--</option>
									</select> <input type="hidden" name="present_district_hid"
										id="present_district_hid" />
								</div>


							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4" id="present_country_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Country Other</label>
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
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> State Other </label>
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
										style="color: red;">*</strong> District Other </label>
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
									<label class=" form-control-label">Tehsil </label>
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
						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Nationality </label>
								</div>
								<div class="col-md-8">
									<select name="nationality" id="nationality"
										class="form-control-sm form-control"
										onchange="national_other();">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getNationalityList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select> <input type="hidden" name="nationality_hid"
										id="nationality_hid" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4" id="present_tehsil_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Tehsil Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="present_tehsil_other"
										name="present_tehshil_other" maxlength="50"
										class="form-control autocomplete" autocomplete="off"
										onkeypress="return onlyAlphabets(event);" />
								</div>
							</div>
						</div>
						<div class="col-md-4" id="nationality_other_div"
							style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">*</strong> Nationality Other </label>
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
									<label class="form-control-label"> <strong
										style="color: red;"></strong> Photograph
									</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="identity_image" name="identity_image"
										accept="image/*" onchange="previewImage(this)"> <label
										class="form-control-label" style="color: red;"> <strong
										style="color: red;">Note:</strong> (Only *.jpg, *.jpeg and
										*.png file extensions and file size max 2MB are allowed.)
									</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="image-container">
									<img id="identity_image_preview" alt="Officer Image"
										src="js/img/No_Image.jpg" width="100" height="100"
										onclick="openFullScreen()" />
									<div class="hover-overlay" onclick="openFullScreen()">
										Click to View</div>
								</div>

								<input type="text" id="pre_identity_id" name="pre_identity_id"
									value="0" style="display: none">
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
									<label class=" form-control-label">Non Effective </label>
								</div>
								<div class="col-md-8">
									<label for="eff_yes">Yes</label> <input class=" " type="radio"
										onchange="oneff(this.value);" id="eff_yes" value="yes"
										name="eff">&nbsp;&nbsp&nbsp;&nbsp <label for="eff_no">No</label> <input
										class=" " type="radio" onchange="oneff(this.value);"
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
										<c:forEach var="item" items="${getNonRegNonEff}"
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
								<input type="hidden" name="n_hd_r" id="n_hd_r">
							</div>
						</div>
					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="non_regular_civilian_personnel_url"
						class="btn btn-success btn-sm">Clear</a> <input type="submit"
						class="btn btn-primary btn-sm" value="Save"
						onclick="return validate();">
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
 
  $('select#country').change(function() {
	 var contry_id = this.value;

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {		 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#original_state").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}); 

 $('select#original_state').change(function() {
 var state_id = this.value;

 		 	$.post("getDistrictListFormState1?"+key+"="+value, {state_id:state_id}).done(function(j) {
 		 				 	
 		 			 	
 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
 				for ( var i = 0; i < j.length; i++) {
 				
 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
 					
 				}
 				
 				$("select#original_district").html(options);

 			}).fail(function(xhr, textStatus, errorThrown) {
 	 });
});
$('select#original_district').change(function() {
	 var Dist_id = this.value;
       
	 		 	$.post("getTehsilListFormDistrict1?"+key+"="+value, {Dist_id:Dist_id}).done(function(j) {
	 		 				 	
	 		 		
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
	 					
	 				}
	 				
	 				$("select#original_tehsil").html(options);

	 			}).fail(function(xhr, textStatus, errorThrown) {
	 	});
}); 

$('select#country1').change(function() {
	 var contry_id = this.value;

	 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
	 		 				 	
	 		 			 	
	 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
	 				for ( var i = 0; i < j.length; i++) {
	 				
	 
	options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
														+ j[i][1] + '</option>';

											}

											$("select#present_state").html(
													options);

										}).fail(
										function(xhr, textStatus, errorThrown) {
										});
					});

	$('select#present_state')
			.change(
					function() {
						var state_id = this.value;

						$
								.post(
										"getDistrictListFormState1?" + key
												+ "=" + value, {
											state_id : state_id
										})
								.done(
										function(j) {

											var options = '<option   value="0">'
													+ "--Select--"
													+ '</option>';
											for (var i = 0; i < j.length; i++) {

												options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
														+ j[i][1] + '</option>';

											}

											$("select#present_district").html(
													options);

										}).fail(
										function(xhr, textStatus, errorThrown) {
										});
					});
	$('select#present_district')
			.change(
					function() {
						var teh_id = this.value;

						$
								.post(
										"getTehsilListFormDistrict1?" + key
												+ "=" + value, {
											Dist_id : teh_id
										})
								.done(
										function(j) {

											var options = '<option   value="0">'
													+ "--Select--"
													+ '</option>';
											for (var i = 0; i < j.length; i++) {

												options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
														+ j[i][1] + '</option>';

											}

											$("select#present_tehsil").html(
													options);

										}).fail(
										function(xhr, textStatus, errorThrown) {
										});
					});
 
 </script>


<script>
  
  var adhar = '${pers_aadhar_no77}';
  var arrTo=adhar.substring(0, 4);
  var arrTo1=adhar.substring(4, 8);
  var arrTo2=adhar.substring(8);

  $("#pers_aadhar_no11").val(arrTo);
  $("#pers_aadhar_no2").val(arrTo1);
  $("#pers_aadhar_no3").val(arrTo2); 

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
		
		var n_hd_r = $("#n_hd_r").val();
		 if (n_hd_r == "MISO") {
			 
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

		if ($("input#employee_no").val() == "") {
			alert("Please Enter Employee No");
			$("input#employee_no").focus();
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
		   
		   
		 var employee_no = $("#employee_no").val();
			
			
			var jqXHR =  $.ajax({
			        type: 'POST',
			        url: "getEmplNo?"+key+"="+value,
			        data: {employee_no:employee_no}, 
			       	success: function(response) {
			       			
				 	},
			        async: false
				});
				
				
				if(jqXHR.responseText == 'f'){
					alert("Employee No Is Already Exist");
					return false;
				}

	   
		 if ($("input#first_name").val() == "") {
				alert("Please Enter First Name");
				$("input#first_name").focus();
				return false;
			} 
		 
		 if ($("input#dob").val() == "DD/MM/YYYY" || $("input#dob").val().trim()=="") {
				alert("Please Select Date of Birth");
				$("input#dob").focus();
				return false;
			} 
		 
		 if ($("select#gender").val() == "0") {
				alert("Please Select Gender");
				$("select#gender").focus();
				return false;
			}
			
			if ($("select#gender").val() != "0") {
				var gender = $("#gender option:selected").text();
				 
				if(gender == "OTHER"){
					if ($("input#gender_other").val() == "") {
						alert("Please Enter Gender Other");
						$("input#gender_other").focus();
						return false;
					} 
			}
			}
				
				if ($("select#category").val() == "0") {
					alert("Please Select Category");
					$("select#category").focus();
					return false;
				} 
				
				if ($("input[name='civ_type']:checked").val() == undefined) {
					alert("Please Select Type");
					$("#civ_type").focus();
					return false;
				}
				
				if ($("select#pay_head").val() == "0") {
					alert("Please Select Pay Head");
					$("select#pay_head").focus();
					return false;
				} 
				
				if ($("#pay_head").val() != "0") {
					var pay_level = $("#pay_head option:selected").text();
					 
					if(pay_level == "OTHER"){
						if ($("#pay_head_other").val().trim() == "") {
							alert("Please Enter Pay Head Other");
							$("#pay_head_other").focus();
							return false;
						} 
				}
				}
				
				 if ($("input#date_of_joining").val() == "DD/MM/YYYY" || $("input#date_of_joining").val().trim()=="") {
						alert("Please Select Date of Joining");
						$("input#date_of_joining").focus();
						return false;
					} 
				 
				 if(getformatedDate($("input#dob").val()) > getformatedDate($("#date_of_joining").val())) {
						alert("Date of Joining should Be Greater Than Date of Birth");
						return false;
				  }
				 
				 if ($("#country").val() == "0") {
						alert("Please Select Original Country");
						$("#country").focus();
						return false;
					} 
				 if ($("select#country").val() != "0") {
						var text = $("#country option:selected").text();
						 
						if(text == "OTHERS"){
							if ($("input#original_country_other").val() == "") {
								alert("Please Enter Original Country Other");
								$("input#original_country_other").focus();
								return false;
							} 
					}
					}
				 
				 if ($("select#original_state").val() == "0") {
						alert("Please Select Original State");
						$("select#original_state").focus();
						return false;
					} 
					
					 if ($("select#original_state").val() != "0") {
							var text = $("#original_state option:selected").text();
							 
							if(text == "OTHERS"){
								if ($("input#original_state_other").val() == "") {
									alert("Please Enter Original State Other");
									$("input#original_state_other").focus();
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
				 
				 if ($("#country1").val() == "0") {
						alert("Please Select Present Country");
						$("#country1").focus();
						return false;
					} 
				 if ($("select#country1").val() != "0") {
						var text = $("#country1 option:selected").text();
						 
						if(text == "OTHERS"){
							if ($("input#present_country_other").val() == "") {
								alert("Please Enter Present Country Other");
								$("input#present_country_other").focus();
								return false;
							} 
					}
					}
				 if ($("#present_state").val() == "0") {
						alert("Please Select Present State");
						$("#present_state").focus();
						return false;
					} 
				 
				 if ($("select#present_state").val() != "0") {
						var text = $("#present_state option:selected").text();
						 
						if(text == "OTHERS"){
							if ($("input#present_state_other").val() == "") {
								alert("Please Enter Present State Other");
								$("input#present_state_other").focus();
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
							if ($("#nationality_other").val().trim() == "") {
								alert("Please Enter Nationality Other");
								$("#nationality_other").focus();
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
					    
					    if(getformatedDate($("input#date_of_joining").val()) > getformatedDate($("#non_effective_date").val())) {
        					alert("Date of Non Effective should Be Greater Than  Date of Joining Govt Service ");
        					return false;
        			  }
				 } 
				
		 return true;
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
	 
	 function onChangePerNo(obj)
	 {
	 	var data = obj.value;

	 	if(data.length == 5)
	 	{
	 		var suffix="";
	 		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	 		
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
	 		$("#persnl_no3").val(suffix);
	 		
	 	}
	 }
	 
	 
	 jQuery(function($){ //on document.ready  

		 datepicketDate('dt_of_authority');
		 datepicketDate('date_of_joining');
		/*  datepicketDate('dt_of_designation'); */
		 datepicketDate('non_effective_date');
		 datepicketDate('dob');
	});
	 
	 
	 function oneff(val) {
			if(val == "yes" )
		    {
				 $("div#div_eff").show();
			} 
			else 
			{
				 $("div#div_eff").hide();
			}
		}
	 
	 
	 $(document).ready(function() {
		 
		 var r = ('${roleAccess}');
		 $("#n_hd_r").val(r);
		 
		 
		 
		 $.post("getCivilianTypeList?"+key+"="+value,function(j) {
				$("#div_type").empty();
		 		for ( var i = 0; i < j.length ;i++) { 
		 				$("#div_type").append ( "<label>" + j[i][1] + "   </label>&nbsp;&nbsp<input type='radio' name='civ_type' id='civ_type' value='" + j[i][0] + "'  /> &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp &nbsp;&nbsp" );
		 		} 
		 	});
			
			var r =('${roleAccess}');
			if( r == "Unit"){
				
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
				 
			}
			else  if(r == "MISO"){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_posted_to").show();
				
			}
			
		   var sus_no = '${roleSusNo}';
		  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
		  		var l=data.length-1;
		  		var enc = data[l].substring(0,16);    	   	 
		  	 	$("#unit_posted_to").text(dec(enc,data[0]));
		  		}).fail(function(xhr, textStatus, errorThrown) {
			  }); 
		  	
		
			  
		});
	 
	 function SetMin(){
			if ($("input#dob").val() != "") {
				var dob_dt = $("input#dob").val();
				setMinDate('date_of_joining', dob_dt);
				setMinDate('non_effective_date', dob_dt);
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
				//$("#per_home_addr_country_other").val("");
			 	
			}
		}

	 function fn_pre_domicile_Country() {
			var text = $("#country option:selected").text();
			 $("#country_original_hid").val(text);
			if(text == "OTHERS"){
				$("#origin_country_other_div").show();
			}
			else{
				$("#origin_country_other_div").hide();
				$("#original_country_other").val("");
				 $("#country_original_hid").val("");
			}
			
		
			
			 var contry_id = $('select#country').val();

			 		 	$.post("getStateListFormcon1?"+key+"="+value, {contry_id:contry_id}).done(function(j) {
			 		 				 	
			 		 			 	
			 		 		var options = '<option   value="0">'+ "--Select--" + '</option>';
			 				for ( var i = 0; i < j.length; i++) {
			 				
			 						options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
			 					
			 				}
			 				
			 				$("select#original_state").html(options);
			 				fn_pre_domicile_state();

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
			 				fn_pre_domicile_tesil();
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
// 			 				fn_pre_domicile_district_present();
// 			 				fn_pre_domicile_tesil_present(); 
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
			$("#tehsil_present_hid").val(text);
			if(text == "OTHERS"){
				$("#present_tehsil_other_div").show();
			}
			else{
				$("#present_tehsil_other_div").hide();
				$("#present_tehsil_other").val("");
				$("#tehsil_present_hid").val("");
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
				 $("#mother_tongue_hid").val("");
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
	 function pay_head_oth() {
		 var pay_head = $("#pay_head option:selected").text();
		 $("#pay_head_hid").val(pay_head);
			if(pay_head == "OTHER"){
			    $("#pay_head_other_div").show();
			}
			else{
				$("#pay_head_other_div").hide();
				$("#pay_head_other").val("");
				$("#pay_head_hid").val("");
			}
		}
	 
	 function AvoidSpace(event) {
		    var k = event ? event.which : window.event.keyCode;
		    if (k == 32) return false;
		}

	 function emp_no_already_exist()
	 {
		
		 var emp_no = $("input#employee_no").val();	
		 
		 $.ajaxSetup({
          async : false
  	   });
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
  </script>


