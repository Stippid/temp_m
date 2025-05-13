<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="tabcontent" style="display: block;">
	<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
					<h5>NON EFFECTIVE STATUS/ RE-EMPLOYMENT</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered
							by Unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<br>

					<div class="col-md-12" style="margin-top: 10px; width: 100%;">
						<label class=" form-control-label" style="margin-right: 100px;"><h4>Select
								Type</h4></label> &nbsp <input type="radio" id="nonEffectiveId"
							name="type_radio" value="nonEffective"
							onclick="toggleDivs('nonEffectiveDiv', 'reEmployementDiv')">
						&nbsp <label for="nonEffectiveId"><b>NON EFFECTIVE</b></label>

						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type="radio"
							id="reEmployementId" name="type_radio" value="reEmployement"
							onclick="toggleDivs('reEmployementDiv', 'nonEffectiveDiv')">
						&nbsp <label for="reEmployementId"><b>RE-EMPLOYMENT</b></label>

					</div>

					<div id="reEmployementDiv" style="display: none">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Personal No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no"
											class="form-control autocomplete" autocomplete="off"
											onchange="return personal_number();" maxlength="9"
											onkeyup="return specialcharecter(this)"
											onkeypress="return AvoidSpace(event)">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Name </label>
									</div>
									<div class="col-md-8">
										<label id="name" name="name"> </label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Rank </label>
									</div>
									<div class="col-md-8">
										<label id="rank" name="rank"> </label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<label class=" form-control-label" style="margin-left: 10px;"><h4>AUTHORITY</h4></label>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Authority No </label>
									</div>
									<div class="col-md-8">
										<input type="hidden" id="personnel_no_e1"
											name="personnel_no_e1" class="form-control autocomplete">
										<input type="hidden" id="statusAC" name="statusAC"
											class="form-control autocomplete"> <input
											type="hidden" id="unit_nameC" name="unit_nameC"
											class="form-control autocomplete"> <input
											type=hidden id="unit_sus_noC" name="unit_sus_noC"
											class="form-control autocomplete"> <input
											type="text" id="authority" name="authority"
											class="form-control autocomplete" maxlength="50"
											autocomplete="off" onkeyup="return specialcharecter(this)">
										<input type="hidden" id="cr_byC" name="cr_byC"
											class="form-control autocomplete"> <input
											type="hidden" id="cr_dateC" name="cr_dateC"
											class="form-control autocomplete">

									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Date of Authority
										</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="auth_dt" id="auth_dt" maxlength="10"
											onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
											style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY')"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);">
									</div>




								</div>
							</div>
						</div>
						<div class="col-md-12">
							<br>
						</div>
						<div class="col-md-12">
							<label class=" form-control-label" style="margin-left: 10px;"><h4>RE-EMPLOYMENT</h4></label>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4" id="Re_emp">
										<label class=" form-control-label"> <strong
											style="color: red;">* </strong>Granted From Date
										</label>
									</div>

									<div class="col-md-8">
										<input type="text" name="granted_fr_dt" id="granted_fr_dt"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY')"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Date of TOS </label>
									</div>
									<div class="col-md-8">
										<input type="text" name="date_of_tos" id="date_of_tos"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY')"
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
											style="color: red;">* </strong> Unit SUS No </label>
									</div>
									<div class="col-md-8">
										<label id="unit_sus_no_hid" style="display: none"><b>
												${roleSusNo} </b></label> <input type="text" id="unit_sus_no"
											name="unit_sus_no" class="form-control autocomplete"
											maxlength="8" onkeypress="return AvoidSpace(event)"
											onkeyup="return specialcharecter(this)" autocomplete="off"
											style="display: none"> <input type="hidden"
											id="census_id" name="census_id"
											class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="status_type" name="status_type"
											class="form-control autocomplete" autocomplete="off" value="${status_type}">
										<input type="hidden" id="r_id" name="r_id"
											class="form-control autocomplete" autocomplete="off"
											value="0"> <input type="hidden" id="tenure_date"
											name="tenure_date" class="form-control autocomplete"
											autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong> Unit Name </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="unit_name" name="unit_name"
											class="form-control autocomplete" autocomplete="off"
											maxlength="50" onkeyup="return specialcharecter(this)"
											style="display: none"> <label id="unit_name_l"
											style="display: none"><b>${unit_name}</b></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" id="xlist" style="display: none">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> X List SUS No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="x_sus_no" name="x_sus_no" value=""
											class="form-control autocomplete" maxlength="8"
											autocomplete="off" onkeypress="return AvoidSpace(event)"
											onkeyup="return specialcharecter(this)">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> X List Loc </label>
									</div>
									<div class="col-md-8">
										<textarea id="x_list_loc" name="x_list_loc" value=""
											class="form-control autocomplete" maxlength="250"
											onkeyup="return specialcharecter(this)" autocomplete="off">   </textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<br>
						</div>
					</div>
					<!-- ========================================non effective======================================== -->
					<form id="nonEffectiveDiv" style="display: none;">
						<div>
							<!-- manav -->
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Personal No</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="personnelNoForNonEffective"
												name="personnelNoForNonEffective"
												class="form-control autocomplete" autocomplete="off"
												onchange="return personal_numberNonEffective();" maxlength="9"
												onkeyup="return specialcharecter(this)"
												onkeypress="return AvoidSpace(event)">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Name </label>
									</div>
									<div class="col-md-8">
										<label id="name_ne" name="name_ne"> </label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Rank </label>
									</div>
									<div class="col-md-8">
										<label id="rank_ne" name="rank_ne"> </label>
									</div>
								</div>
							</div>
						</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> <strong
												style="color: red;">* </strong>Authority
											</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="non_ef_authority"
												name="non_ef_authority" value="" class="form-control"
												autocomplete="off" maxlength="100"
												onkeyup="return specialcharecter(this)"> <input
												type="hidden" id="nf_id" name="nf_id" value="0"
												class="form-control" autocomplete="off"> <input
												type="hidden" class=" form-control-label" id="comm_date" value="0">
											<input type="hidden" id="comm_id" name="comm_id"
												class="form-control autocomplete" autocomplete="off" value="0">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> <strong
												style="color: red;">* </strong>Date of Authority
											</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="date_of_authority_non_ef"
												id="date_of_authority_non_ef" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 93%; display: inline;"
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
											<label for="cause"> <strong style="color: red;">*
											</strong>Cause of Non Effective <span class="star_design"></span></label>
										</div>
										<div class="col-md-8">
											<select id="cause_of_non_effective"
												name="cause_of_non_effective" class="form-control">
												<!-- onchange="retire_add_fn(this);" -->
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getOFFR_Non_EffectiveList}"
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
											<label for="Date Non"><strong style="color: red;">*
											</strong> Date of Non Effective <span class="star_design"></span></label>
										</div>
										<div class="col-md-8">
											<input type="text" name="date_of_non_effective"
												id="date_of_non_effective" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control" style="width: 93%; display: inline;"
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
							<div class="col-md-12" style="font-size: 15px;">
								<input type="checkbox" id="check_per_address"
									name="check_per_address" onclick="per_address()"> <label
									for="text-input" class=" form-control-label"
									style="color: #dd1a3e; margin-left: 10px;"> Same as
									Permanent Address (If Census Filled) </label>
							</div>

							<div class="col-md-12">
								<label class=" form-control-label" style="margin-left: 10px;"><h4>
										Address After Service in Army (Permanent Address)</h4></label>
							</div>

							<!--  country-->
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>COUNTRY</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_country"
												id="perm_home_addr_country" class="form-control"
												onchange="fn_perm_home_addr_Country(),onPermHomeAddrCountry()">
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
											<select name="per_home_addr_state" id="perm_home_addr_state"
												class="form-control"
												onchange="fn_prem_domicile_state(),onPermHomeAddrState()">
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

							<!-- //country and STATE OTHERs -->
							<div class="col-md-12">
								<div class="col-md-6" id="Ot_perm_hm_addr_con_div"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>OTHER COUNTRY</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="perm_home_addr_country_other"
												name="per_home_addr_country_other"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="Ot_perm_hm_addr_state_div"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>OTHER STATE</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="perm_home_addr_state_other"
												name="per_home_addr_state_other"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);">
										</div>
									</div>
								</div>
							</div>


							<!-- DISTRICT -->
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> DISTRICT</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_district"
												id="perm_home_addr_district" class="form-control"
												onchange="fn_prem_domicile_district(),onPermHomeAddrDis()">
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
												style="color: red;">* </strong> TEHSIL</label>
										</div>
										<div class="col-md-8">
											<select name="per_home_addr_tehsil"
												id="perm_home_addr_tehsil" class="form-control"
												onchange="onPermHomeAddrTeh();">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedCityName}"
													varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>

							<!-- //DISTRICT and TEHSIL others -->
							<div class="col-md-12">
								<div class="col-md-6" id="Ot_perm_hm_addr_dis_div"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>OTHER DISTRICT</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="perm_home_addr_district_other"
												name="per_home_addr_district_other"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);">
										</div>
									</div>
								</div>
								<div class="col-md-6" id="Ot_perm_hm_addr_teh_div"
									style="display: none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>OTHER TEHSIL</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="perm_home_addr_tehsil_other"
												name="per_home_addr_tehsil_other"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50" onkeypress="return onlyAlphabets(event);">
										</div>
									</div>
								</div>
							</div>

							<!-- ------------------- -->

							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong> Village/Town/City</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="perm_home_addr_village"
												name="per_home_addr_village"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="50">
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
											<input type="text" id="perm_home_addr_pin"
												name="per_home_addr_pin" class="form-control autocomplete"
												autocomplete="off"
												onkeypress="return isNumber0_9Only(event)" maxlength="6">
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
											<input type="text" id="perm_home_addr_rail"
												name="per_home_addr_rail"
												onkeypress="return onlyAlphabets(event);"
												class="form-control autocomplete" autocomplete="off"
												maxlength="100"> <input type="hidden"
												id="non_addr_ch_id" name="non_addr_ch_id"
												class="form-control autocomplete" autocomplete="off"
												value="0"> <input type="hidden"
												id="addr_pending_ch_id" name="addr_pending_ch_id"
												class="form-control autocomplete" autocomplete="off"
												value="0">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Is Rural /Urban/Semi Urban</label>
										</div>
										<div class="col-md-8">
											<label for="lbl_per_home_addr_rural"> <input
												type="radio" id="perm_home_addr_rural"
												name="per_home_addr_rural_urban" value="rural">
												Rural
											</label> <label for="lbl_per_home_addr_urban"> <input
												type="radio" id="perm_home_addr_urban"
												name="per_home_addr_rural_urban" value="urban">
												Urban
											</label> <label for="lbl_per_home_addr_semi_urban"> <input
												type="radio" id="perm_home_addr_semi_urban"
												name="per_home_addr_rural_urban" value="semi_urban">Semi
												Urban
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Border Area</label>
										</div>
										<div class="col-md-8">
											<label for="border_area"> <input type="radio"
												id="per_border_area" name="border_area" value="yes">Yes
											</label> <label for="border_area1"> <input type="radio"
												id="per_border_area1" name="border_area" value="no">
												No
											</label>
										</div>
									</div>
								</div>
							</div>


						</div>
					</form>
					<!-- ============================================end not effective======================================== -->


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Re_EmploymentUrl" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-primary btn-sm" value="Save"
						onclick="save_fn();"> <input type="button" id="Cancle"
						class="btn btn-info btn-sm" onclick="Cancel();"
						style="display: none;" value="Back">
				</div>
			</div>
		</div>
	</div>
</div>



<c:url value="Search_Re_Employment" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="cr_date1" id="cr_date1" />
	<input type="hidden" name="cr_by1" id="cr_by1" />
</form:form>


<script>
$(function() {
	$("#from_dt").change(function() {
		//var selectedText = $(this).find("option:selected").text();
		var f_month = $(this).val();
		//$("#leave_to").attr("value", f_month);
		$("#to_dt").attr("min", f_month);

	});
});
$(function() {
	$("#to_dt").change(function() {
		//var selectedText = $(this).find("option:selected").text();
		var f_month = $(this).val();
				
		//$("#leave_to").attr("value", f_month);
		$("#from_dt").attr("max", f_month);

	});
});

function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_no_e1").val()) ;
	$("#status1").val($("#statusAC").val()) ;
	$("#unit_name1").val($("#unit_nameC").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noC").val()) ;
	$("#cr_by1").val($("#cr_byC").val()) ;
	$("#cr_date1").val($("#cr_dateC").val()) ;
	document.getElementById('searchForm').submit();
}

function checkRadioButtons() {
    var statusType = $('#status_type').val();
    console.log("Status type from hidden field",statusType)
    var nonEffectiveRadio = $('#nonEffectiveId');
    var reEmployementRadio = $('#reEmployementId');

    if (statusType && statusType.trim().toLowerCase() === 'non-effective') {
        nonEffectiveRadio.prop('checked', true);
         reEmployementRadio.prop('disabled', true);
         toggleDivs('nonEffectiveDiv', 'reEmployementDiv');
    } else if (statusType && statusType.trim().toLowerCase() === 're-employed') {
        reEmployementRadio.prop('checked', true);
         nonEffectiveRadio.prop('disabled', true);
         toggleDivs('reEmployementDiv', 'nonEffectiveDiv');
    }else { //Added else for invalid status type
        nonEffectiveRadio.prop('disabled', false);
         reEmployementRadio.prop('disabled', false);
    }
}

$(document).ready(function() {
	checkRadioButtons();
	$("#personnel_no_e1").val('${personnel_no6}');
	$("#unit_sus_noC").val('${unit_sus_no5}');
	$("#unit_name").val('${unit_name}');
	$("#unit_name_l").text('${unit_name}');
	$("#statusAC").val('${statusA5}');
	
	$("#cr_byC").val('${cr_by6}');
	$("#cr_dateC").val('${cr_date6}'); 	
	if('${statusA5}' =="0" || '${statusA5}' =="1"){		
		 $("#Cancle").show(); 
	}
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	
	
// 	   var sus_no = '${roleSusNo}';
// 	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
// 	  		var l=data.length-1;
// 	  		var enc = data[l].substring(0,16);    	   	 
// 	  	 	$("#unit_name").text(dec(enc,data[0]));
	  			
// 	  		}).fail(function(xhr, textStatus, errorThrown) {
// 	  });
	  
	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
	
	if ('${name}' != "") {
		$('#name').val('${name}');
	}
	if ('${rank}' != "") {
		$('#rank').val('${rank}');
	}	
	
	
	$("#reserve").hide();
	
	$('#personnel_no').val('${personnel_no}');
	$('#comm_id').val('${comm_id}');
	$('#personnelNoForNonEffective').val('${personnel_no}');
	if('${personnel_no}'!=""){
		personal_number();
		personal_numberNonEffective();
	}
	getRe_EmployeementData();
	getNon_EffectiveData();
});

jQuery(function($){ //on document.ready  
	 datepicketDate('auth_dt');
	 datepicketDate('granted_fr_dt');
	 datepicketDate('date_of_tos');
	});


//*************************************MAIN Personel Number Onchange*****************************//
function personal_number()
{
	
	/* if($("input#personnel_no").val()==""){
		alert("Please select Personnel No");
		$("input#personnel_no").focus();
		return false;
	}
	 */
	var personnel_no = $("input#personnel_no").val();
	 $.post('GetRecallnoNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	   	
		 if(j.length!=0){
			 
				var comm_id =j[0].id;
				
		    	$("#comm_id").val(j[0].id);	  
		    	$("#rank").text(j[0].description);	  
		    /* 	$("#appt").text(j[0].appt);	 */  
		    	$("#name").text(j[0].name);	  
		    	//$("#date_of_appt").text(j[0].date_of_appointment);	  
		    	/*  $("#date_of_appt").text(convertDateFormate(j[0].date_of_appointment)); */
		    	 $.post('GetRecallData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		 	    	
		    		 if(k.length > 0) {
		    			var census_id =k[0].id; 
		 	    	    $('#census_id').val(k[0].id); 
		    		
		    			getRe_EmployeementData();
		    			getNon_EffectiveData();
		    		 }
		    		 
		   });
		    	 $.post('GetTenureDATA?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		    		
		     	    	 if(k.length > 0){
		    		   			$('#tenure_date').val(ParseDateColumncommission(k[0][2]));
		    		   			
		    		   			SetMin();
		    	   		 }	 
		    		 
		    	});
		 }
	    	  	    				    	
	    
  });
	 //$("input#personnel_no").attr('readonly', true);
	 
	
}

function SetMin(){
	if ($("input#tenure_date").val() != "") {
		var tenure_dt = $("input#tenure_date").val();
		setMinDate('date_of_tos', tenure_dt);
	}
}

function getRe_EmployeementData()
{
	 var comm_id1 = $('#comm_id').val(); 
	 var census_id1 = $('#census_id').val();  
	// alert( comm_id1 +"------" +census_id1)
	 if(comm_id1 !="")
		 {
		
		 $.post('getRe_EmployeementData2?' + key + "=" + value,{ comm_id : comm_id1},function(h) {
	 	    	
    		 if(h.length != 0)
    		 {	    			 
    			
    			 $('#unit_sus_no').val(h[0].unit_sus_no);   
    			 $('#unit_name').val(h[0].unit_posted_to); 
    			 $('#r_id').val(h[0].id); 
    			 $('#date_of_tos').val(ParseDateColumncommission(h[0].date_of_tos)); 
    			 $('#authority').val(h[0].authority);
    			 $("#auth_dt").val(ParseDateColumncommission(h[0].auth_dt));
		
    			 var sus_no = h[0].unit_sus_no;	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
				         }, function(j) {
				                
				         }).done(function(j) {
				        	 var length = j.length-1;
				             var enc = j[length].substring(0,16);
				             $("#unit_name").val(dec(enc,j[0]));   
				                 
				         }).fail(function(xhr, textStatus, errorThrown) {
			         });
    			
    			 
    			// var granted_fr_dt=new Date(h[0].granted_fr_dt).toISOString().split('T')[0];	
    			 $('#granted_fr_dt').val(ParseDateColumncommission(h[0].granted_fr_dt)); 
    			 
    			 
    			 $('#re_emp_select').val(h[0].re_emp_select);  
    			 appNamechng();
    			 
    		 }
   });	
	}  
	}
	
	
function getNon_EffectiveData() {
	
	console.log(" in getNon_EffectiveData");
	 var comm_id1 = $('#comm_id').val();
	 console.log(" comm_id1 in getNon_EffectiveData" + comm_id1);
	 $.ajaxSetup({
		async : false
	});
	 getNon_effective();
		
	$.post('getComm?' + key + "=" + value,{comm_id : comm_id1},function(h) {
		console.log(" in getNon_EffectiveData length " + h.length);
   		if(h.length != 0) {
   			$('#comm_date').val(ParseDateColumncommission(h[0].date_of_commission));
   		}
  	});
		
		get_non_eff_address_details();
}
	
	
 function editData(id) {
	 debugger;
	 $("#r_id").val(id);
	 $("#nf_id").val(id);
	 $("#census_id").val(census_id);
	 $("#comm_id").val(comm_id); 
	 getRe_EmployeementData();
	 getNon_EffectiveData();
	 $("#personnel_no").val(personnel_no);
	 $("#personnelNoForNonEffective").val(personnel_no);
	 personal_number();
	 personal_numberNonEffective();
 }

$("input#personnel_no").keyup(function(){	
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListRe_Emp?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});
////unit sus no

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
	        	  document.getElementById("unit_name_l").value="";
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

$("#x_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#x_sus_no");

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
	        	  document.getElementById("x_sus_no").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			/* var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_posted_to").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         }); */
		} 	     
	});	
});


// unit name
 $("input#unit_name").keypress(function(){
		var unit_name = this.value;
		
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {"js/AES_ENC_DEC/AesUtil.js"
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
 function appNamechng(){
	 
		//var appname = $("#appt").val();
		var appname = $("#appt").find("option:selected").text();
			if(appname == "ON ATTACHMENT" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
					|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE"
					|| appname == "ON LEAVE PENDING RETIREMENT R/R" || appname == "ON STUDY LEAVE"){
				$("#xlist").show();
				}
			else{
				$("#xlist").hide();
				$("#x_sus_no").val("");
				$("#x_list_loc").val("");
			 
		}
	}
//*************************************END Personel Number Onchange*****************************//
//******************************Start ReEmployment***************************// 

function Re_employment_save_fn()
{
	 if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	 if($("#granted_fr_dt").val()== "DD/MM/YYYY"){
		 alert("Please Enter Granted From Date ");
		 $("input#granted_fr_dt").focus();
		 return false;
	 }
	 
	 if($("#date_of_tos").val()== "DD/MM/YYYY"){
		 alert("Please Enter TOS Date");
		 $("input#date_of_tos").focus();
		 return false;
	 }
	 
	var unit_sus_no = $('#unit_sus_no').val();  
	var unit_posted_to = $('#unit_name').val();
	/* var appt = $('#appt').val(); */
	
	var rank = $('#rank').val();
	
	var x_sus_no = $('#x_sus_no').val();
	var x_list_loc = $('#x_list_loc').val();
	/* var date_of_appt = $('#date_of_appt').val(); */
	var date_of_tos = $('#date_of_tos').val();
	var authority = $('#authority').val();  
	var auth_dt = $('#auth_dt').val();  
	var from_dt = $('#from_dt').val();  
	var to_dt = $('#to_dt').val();  
	var cause_of_release = $('#cause_of_release').val();  
	var dt_of_release = $('#dt_of_release').val();  
	var re_emp_select = $('#re_emp_select').val(); 
	var granted_fr_dt = $('#granted_fr_dt').val(); 
	
	var comm_id = $('#comm_id').val(); 
	var census_id = $('#census_id').val();
	var r_id = $('#r_id').val();
	
	var personnel_no = $('#personnel_no').val();
	
	
	
	$.post('Re_employment_action?' + key + "=" + value, {r_id:r_id,re_emp_select:re_emp_select,granted_fr_dt:granted_fr_dt,unit_sus_no:unit_sus_no,unit_posted_to:unit_posted_to,x_sus_no:x_sus_no,x_list_loc:x_list_loc,date_of_tos:date_of_tos,authority:authority,auth_dt:auth_dt,from_dt:from_dt,to_dt:to_dt,cause_of_release:cause_of_release,dt_of_release:dt_of_release,comm_id:comm_id,census_id:census_id,personnel_no:personnel_no}, function(data){
			 
        if(data=="update")
      	  alert("Data Updated Successfully");
        else if(parseInt(data)>0)
	       	  {	    	   
	        	alert("Data Saved Successfully");
	        	$("#tab_id").show();
	        	$("#r_id").val(data)
	       
	        	$('#census_id').val(data[0]);
	        	 location.reload();        	
	          }
        
	          else
	          {
	        	 
	        	  alert(data);
	        	  
	          }
       
	        	
	 	  	}).fail(function(xhr, textStatus, errorThrown) 
	 	  		{
	 	  					
	  		});
	
}
//*************************************end CDA ACCOUNT NO & CONTACT DETAIL*****************************//

function non_effect_save(){
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val();
	var nf_id=$('#nf_id').val();
	var comm_date=$('#comm_date').val(); 
	 
	var formdata=$('#nonEffectiveDiv').serialize();
	 
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;	
 
	if ($("input#personnelNoForNonEffective").val().trim()=="") {
		alert("Please Enter Personnel No");
		$("input#personnelNoForNonEffective").focus();
		return false;
	}
	
	if ($("input#non_ef_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#non_ef_authority").focus();
			return false;
		}
		 if ($("input#date_of_authority_non_ef").val().trim()=="" || $("input#date_of_authority_non_ef").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Authority ");
			$("input#date_of_authority_non_ef").focus();
			return false;
		} 
		 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_authority_non_ef").val())) {
				alert("Authority Date should be Greater than Commission Date");
				return false;
		  }

		if ($("select#cause_of_non_effective").val() == 0) {
			alert("Please Select Cause of Non Effective ");
			$("#cause_of_non_effective").focus();
			return false;
		}
		 if ($("input#date_of_non_effective").val().trim()=="" || $("input#date_of_non_effective").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Non Effective ");
			$("input#date_of_non_effective").focus();
			return false;
		} 

		if ($("select#perm_home_addr_country").val() == "0") {
			alert("Please select Country");
			$("select#perm_home_addr_country").focus();
			return false;
		}
		var text9 = $("#perm_home_addr_country option:selected").text();
		if(text9 == "OTHERS" && $("input#perm_home_addr_country_other").val().trim()==""){
			alert("Please Enter Country Other");
			$("input#perm_home_addr_country_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_state").val() == "0") {
			alert("Please Select State");
			$("select#perm_home_addr_state").focus();
			return false;
		}
		var text10 = $("#perm_home_addr_state option:selected").text();
		if(text10 == "OTHERS" && $("input#perm_home_addr_state_other").val().trim()==""){
			alert("Please Enter State Other");
			$("input#perm_home_addr_state_other").focus();
			return false;
		}
		
		if ($("select#perm_home_addr_district").val() == "0") {
			alert("Please Select  District");
			$("select#perm_home_addr_district").focus();
			return false;
		}
		var text11 = $("#perm_home_addr_district option:selected").text();
		if(text11 == "OTHERS" && $("input#perm_home_addr_district_other").val().trim()==""){
			alert("Please Enter District Other");
			$("input#perm_home_addr_district_other").focus();
			return false;
		}
		if ($("select#perm_home_addr_tehsil").val() == "0") {
			alert("Please Enter Tehsil");
			$("select#perm_home_addr_tehsil").focus();
			return false;
		}
		var text12 = $("#perm_home_addr_tehsil option:selected").text();
		if(text12 == "OTHERS" && $("input#perm_home_addr_tehsil_other").val().trim()==""){
			alert("Please Enter Tehsil Other");
			$("input#perm_home_addr_tehsil_other").focus();
			return false;
		}
		if ($("input#perm_home_addr_village").val().trim()=="") {
			alert("Please Enter Village/Town/City");
			$("input#perm_home_addr_village").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim()=="") {
			alert("Please Enter Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_pin").val().trim().length!=6) {
			alert("Please Enter Valid Pin");
			$("input#perm_home_addr_pin").focus();
			return false;
		}
		if ($("input#perm_home_addr_rail").val().trim()=="") {
			alert("Please Enter Nearest Railway Station");
			$("input#perm_home_addr_rail").focus();
			return false;
		}
		var a = $('input:radio[name=per_home_addr_rural_urban]:checked').val();
		if (a == undefined) {
			alert("Please select Is Rural /Urban/Semi Urban ");
			return false;
		}
		var b = $('input:radio[name=border_area]:checked').val();
		if (b == undefined) {
			alert("Please select Border Area ");
			return false;
		}

		$.post('non_effectiveAction?' + key + "=" + value, formdata, function(data) {
				if (data.error == undefined) {
               $('#nf_id').val(data.nf_id);
                $('#non_addr_ch_id').val(data.add_id);
                $("#addr_ch_id").val(data.add_id);
           if (data.msg != undefined) {
            alert("Data Saved/Update Successfully " + data.msg);
          } else {
             alert("Data Saved/Update Successfully");
         }    
    location.reload();
} else {
    alert(data.error);
}

				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	
// SAME AS PERMANENT ADDRESS
function per_address() {
	var comm_id = $("#comm_id").val();
	if ($("#check_per_address").prop('checked') == true) {
		//$("#perm_add_div").show();
		$.post('getPerAddressDataNonEffective?' + key + "=" + value, {		
			comm_id : comm_id
		}, function(j) {
			if (j != "") {
	 
				$("#perm_home_addr_country").val(j[0].permanent_country);
				fn_perm_home_addr_Country();
				$("#perm_home_addr_state").val(j[0].permanent_state);
				fn_prem_domicile_state();
				$("#perm_home_addr_district").val(j[0].permanent_district);
				fn_prem_domicile_district();
				$("#perm_home_addr_village").val(j[0].permanent_village);
				$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);
				$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
				$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
				
				if(j[0].per_home_addr_country_other!=null )
					$("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
					
				if(j[0].per_home_addr_state_other!=null)
					$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
				if(j[0].per_home_addr_district_other!=null)
					$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);	
				if(j[0].per_home_addr_tehsil_other!=null)
					$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
				var permanent = j[0].permanent_rural_urban_semi;
				if (permanent == "rural") {
					$("#perm_home_addr_rural").prop("checked", true);
				}
				if (permanent == "urban") {
					$("#perm_home_addr_urban").prop("checked", true);
				}
				if (permanent == "semi_urban") {
					$("#perm_home_addr_semi_urban").prop("checked", true);
				}
				var br = j[0].permanent_border_area;
				if (br == "yes") {
					$("#per_border_area").prop("checked", true);
				}
				if (br == "no") {
					$("#per_border_area1").prop("checked", true);
				}
			}
			onPermHomeAddrCountry();
			onPermHomeAddrState();
			onPermHomeAddrDis();
			onPermHomeAddrTeh();
			 
		});
	}
	else{
		$("#perm_home_addr_country").val(0);
		fn_perm_home_addr_Country();
		$("#perm_home_addr_state").val(0);
		fn_prem_domicile_state();
		$("#perm_home_addr_district").val(0);
		fn_prem_domicile_district();
		$("#perm_home_addr_village").val('');
		$("#perm_home_addr_tehsil").val(0);
		$("#perm_home_addr_pin").val('');
		$("#perm_home_addr_rail").val('');
		//26-01-1994
		$("#perm_home_addr_rural").prop("checked", false);
		$("#perm_home_addr_urban").prop("checked", false);
		$("#perm_home_addr_semi_urban").prop("checked", false);
		$("#per_border_area").prop("checked", false);
		$("#per_border_area1").prop("checked", false);
		
		onPermHomeAddrCountry();
		onPermHomeAddrState();
		onPermHomeAddrDis();
		onPermHomeAddrTeh();
	}

}


function fn_prem_domicile_state() {
	var state_id = $('select#perm_home_addr_state').val();

	$
			.post("getDistrictListFormState1?" + key + "=" + value, {
				state_id : state_id
			})
			.done(
					function(j) {

						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}

						$("select#perm_home_addr_district").html(options);
						fn_prem_domicile_district();
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function onPermHomeAddrCountry() {
	var perm_home_addr_country = $("#perm_home_addr_country option:selected").text();
	 
	if(perm_home_addr_country == "OTHERS"){
		$("#Ot_perm_hm_addr_con_div").show();
		
	}
	else{
		$("#Ot_perm_hm_addr_con_div").hide();
		$("#perm_home_addr_country_other").val("");
	 	
	}
}
function onPermHomeAddrState() {

	var perm_home_addr_state = $("#perm_home_addr_state option:selected").text();
	
	if(perm_home_addr_state == "OTHERS"){
		$("#Ot_perm_hm_addr_state_div").show();
		
	}
	else{
		$("#Ot_perm_hm_addr_state_div").hide();
		$("#perm_home_addr_state_other").val("");
	}
}	
function onPermHomeAddrDis() {
	var per_home_addr_district2 = $("#perm_home_addr_district option:selected").text();
	
	if(per_home_addr_district2 == "OTHERS"){
		
		$("#Ot_perm_hm_addr_dis_div").show();
	}
	else{
		$("#Ot_perm_hm_addr_dis_div").hide();
		$("#perm_home_addr_district_other").val("");
	 	
	}
	onPermHomeAddrTeh();
}	
function onPermHomeAddrTeh() {

	var perm_home_addr_tehsil = $("#perm_home_addr_tehsil option:selected").text();
	if(perm_home_addr_tehsil == "OTHERS"){
		$("#Ot_perm_hm_addr_teh_div").show();
		
	}
	else{
		$("#Ot_perm_hm_addr_teh_div").hide();
		$("#perm_home_addr_tehsil_other").val("");
	 	
	}
}

function fn_prem_domicile_district() {
	var Dist_id = $('select#perm_home_addr_district').val();

	$
			.post("getTehsilListFormDistrict1?" + key + "=" + value, {
				Dist_id : Dist_id
			})
			.done(
					function(j) {

						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}

						$("select#perm_home_addr_tehsil").html(options);

					}).fail(function(xhr, textStatus, errorThrown) {
			});
	onPermHomeAddrCountry();
	onPermHomeAddrState();
	onPermHomeAddrDis();
	onPermHomeAddrTeh();
}

function fn_perm_home_addr_Country() {
	var contry_id = $('select#perm_home_addr_country').val();

	$
			.post("getStateListFormcon1?" + key + "=" + value, {
				contry_id : contry_id
			})
			.done(
					function(j) {

						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {

							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'
									+ j[i][1] + '</option>';

						}
						$("select#perm_home_addr_state").html(options);
						fn_prem_domicile_state();
						
						onPermHomeAddrCountry();
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}

function toggleDivs(showDivId, hideDivId) { 
    document.getElementById(showDivId).style.display = 'block';
    document.getElementById(hideDivId).style.display = 'none';
}



jQuery(function($){
	 datepicketDate('date_of_authority_non_ef');
	 datepicketDate('date_of_non_effective');

});


function getNon_effective(){

	 var comm_id=$('#comm_id').val(); 	
	 var i=0;
	 var lengthV=0;
	 $.ajaxSetup({
        async : false
});
	  $.post('getNon_effectivedata?' + key + "=" + value, {comm_id:comm_id }, function(j){
			var v=j.length;
			lengthV=v;
			if(v!=0){
				$('#nf_id').val(j[i].id);
				$("#check_per_address").prop("checked", true);
				 $("#non_ef_authority").val(j[i].non_ef_authority);
				 $("#date_of_authority_non_ef").val(ParseDateColumncommission(j[i].date_of_authority_non_ef));
				 $("#cause_of_non_effective").val(j[i].cause_of_non_effective);
				 $("#date_of_non_effective").val(ParseDateColumncommission(j[i].date_of_non_effective));
			}
	  });

}


function get_non_eff_address_details(){
	var comm_id=$("#comm_id").val();
	
	if($('#nf_id').val()!="" && $('#nf_id').val()!="0"){
	$.post('addressDetailForNonEffective?' + key + "=" + value,{comm_id:comm_id }, function(j){
		var v=j.length;
		if(v!=0){
			$("#perm_home_addr_country").val(j[0].permanent_country);
			fn_perm_home_addr_Country();
			$("#perm_home_addr_state").val(j[0].permanent_state);
			fn_prem_domicile_state();
			$("#perm_home_addr_district").val(j[0].permanent_district);
			fn_prem_domicile_district();
			$("#perm_home_addr_village").val(j[0].permanent_village);
			$("#perm_home_addr_tehsil").val(j[0].permanent_tehsil);
			$("#perm_home_addr_pin").val(j[0].permanent_pin_code);
			$("#perm_home_addr_rail").val(j[0].permanent_near_railway_station);
			 var permanent= j[0].permanent_rural_urban_semi;
            if(permanent == "rural"){
                    $("#perm_home_addr_rural").prop("checked", true);
             } 
            if(permanent == "urban"){
                    $("#perm_home_addr_urban").prop("checked", true);
             }
            if(permanent == "semi_urban")
            {
                    $("#perm_home_addr_semi_urban").prop("checked", true);
             }  
            var br= j[0].permanent_border_area;
            if(br == "yes"){
                    $("#per_border_area").prop("checked", true);
             } 
            if(br == "no"){
                    $("#per_border_area1").prop("checked", true);
             } 
          
            $("#perm_home_addr_country_other").val(j[0].per_home_addr_country_other);
			$("#perm_home_addr_state_other").val(j[0].per_home_addr_state_other);
			$("#perm_home_addr_district_other").val(j[0].per_home_addr_district_other);
			$("#perm_home_addr_tehsil_other").val(j[0].per_home_addr_tehsil_other);
			
			onPermHomeAddrCountry();
				onPermHomeAddrState();
				onPermHomeAddrDis();
				onPermHomeAddrTeh();
			$("#non_addr_ch_id").val(j[0].id);
		}
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
	}
}


$("input#personnelNoForNonEffective").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnelNoForNonEffective");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListNonEffApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		    		 personal_numberNonEffective();
		    		 $("input#personnelNoForNonEffective").attr('readonly', true);
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  $("input#personnelNoForNonEffective").attr('readonly', false);
		        	  document.getElementById("personnelNoForNonEffective").value="";		        	 	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		      } 	     
		}); 			
});

function personal_numberNonEffective(){
	 var personnel_no = $("input#personnelNoForNonEffective").val();
	/*  $.ajaxSetup({
	        async : false
	}); */
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
			 console.log("Val of Com id In personal_numberNonEffective" + j[0][0]);
	    	$("#comm_id").val(j[0][0]);
	    	$("#name_ne").text(j[0][5]);
	    	$("#rank_ne").text(j[0][6]);
	    	$("#comm_date").val(ParseDateColumncommission(j[0][11]));
	 	}
	 });
		 var comm_id = $("input#comm_id").val();
	 $.post('GetCensusForNFData?' + key + "=" + value,{ comm_id : comm_id },function(j) {
		 if(j!=""){
			$('#census_id').val(j[0].id);
	 	}
	
  	});
	 getNon_effective();
	 get_non_eff_address_details();
	
}

function save_fn() {   
    var radioButtons = document.getElementsByName('type_radio');
    let selectedType = null;

    for (let radio of radioButtons) {
        if (radio.checked) {
            selectedType = radio.value;
            break;
        }
    }
    
    if (selectedType === 'nonEffective') {
    	non_effect_save();
    } else if (selectedType === 'reEmployement') {
    	Re_employment_save_fn();
    } else {
        alert('Please select a type first');
    }
}
</script>



