<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>

<style>
.sticky {
	position: fixed;
	top: 0;
	z-index: 1000;
	padding-right: 35px;
}

.sticky+.subcontent {
	padding-top: 330px;
}
</style>

<div class="container-fluid" align="center">
	<form id="Comm_form">
		<div class="animated fadeIn">
			<div class="card" align="center">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title" id="insp_div5">
								<b>APPROVE UPDATE CENSUS DETAILS JCO/OR</b>
							</h4>
							<p>
								<strong>(*Note: Incase of single entry being rejected
									or 'All' entries being rejected, the Approver has to still
									click on Approve button to send data entry back to DDO.) </strong>
							</p>
						</div>
						<div class="col-md-12"
							style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Army No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="army_no" name="army_no"
											class="form-control-sm form-control autocomplete"
											autocomplete="off"> <input type="hidden" id="status"
											name="status" value="0"
											class="form-control-sm form-control autocomplete"
											autocomplete="off">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div id="div_update_data" style="display: none;" class="subcontent">
	<div id="maindetailsdiv">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="app_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="app_final"
								onclick="divN(this)"><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br>



							<div class="row">

								<div class="col-md-12">
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> Name</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="cadet_name"></label>
												<label class=" form-control-label" id="dob"
													style="display: none;"></label> <input type="hidden"
													class=" form-control-label" id="dob_date"> <input
													type="hidden" class=" form-control-label" id="enroll_date">
												<label class=" form-control-label" id="marital_status_p"
													style="display: none;"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Rank</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_rank"></label> <input
													type="hidden" id="hd_p_rank" name="hd_p_rank"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> Appointment</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_app"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of
													Appointment</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_app_dt"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">

									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of TOS</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_tos"></label> <input
													type="hidden" class=" form-control-label" id="tos_date">
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> SUS No</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="app_sus_no"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Unit Name</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="app_unit_name"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> Command</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_cmd"></label>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">

									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong> Serving Status</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_serving_status"></label>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- START GENDER -->
	<c:if test="${ChangeOfgender != 0}">
		<form id="form_gender">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title yellow" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="a_final"
									onclick="divN(this)"><b>Gender</b></a>
							</h4>
						</div>
						<div id="collapse1p" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="g_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="g_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Gender
													</label>
												</div>
												<div class="col-md-8">
													<label id="genderlbl"></label> <input type="hidden"
														id="g_id" name="g_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off">
													<div style="display: none;">
														<select name="gender" id="gender"
															class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getGenderList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Gender?')){addRemarkModel('Gender_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Gender -->

	<!-- START Birth-->
	<c:if test="${ChangeOfdob != 0}">
		<form id="form_Birth">
			<div class="card">
				<div class="panel-group" id="accordion21">
					<div class="panel panel-default" id="b_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title lightgreen" id="b_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionbb" href="#" id="b_final"
									onclick="divN(this)"><b>DATE of BIRTH</b></a>
							</h4>
						</div>
						<div id="collapse1x" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="b_authority"></label> <input type="hidden"
														id="birth_id" name="birth_id" value="0"
														class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="b_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Date of Birth
													</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_birth"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Birth?')){addRemarkModel('DOB_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Birth -->

	<!-- START ADDRESS -->
	<c:if test="${ChangeOfAddressBirth != 0}">
		<form id="form_addr_details_form">
			<div class="card">
				<div class="panel-group" id="accordion9">
					<div class="panel panel-default" id="c_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title blue" id="c_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion9" href="#" id="c_final"
									onclick="divN(this)"><b>Address of Birth</b></a>
							</h4>
						</div>
						<div id="collapse1c" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="addr_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="addr_date_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong> Place of Birth</label>
												</div>
												<div class="col-md-8">
													<label id="place_of_birth"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Country</label>
												</div>
												<div class="col-md-8">
													<label id="Countrylbl"></label>
													<div style="display: none;">
														<select name="country_of_birth" id="country_of_birth"
															class="form-control-sm form-control"
															onchange="fn_otherShowHide(this,'other_id','country_other')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getMedCountryName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6" id="other_id">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Country Other </label>
												</div>
												<div class="col-md-8">
													<label id="country_other"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong> State</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="addr_ch_id" name="addr_ch_id"
														value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="statelbl"></label>
													<div style="display: none;">
														<select name="state_of_birth" id="state_of_birth"
															class="form-control-sm form-control"
															onchange="fn_otherShowHide(this,'St_other_id','state_other')">
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
									</div>
									<div class="col-md-12">
										<div class="col-md-6" id="St_other_id">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> State Other </label>
												</div>
												<div class="col-md-8">
													<label id="state_other"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong> District</label>
												</div>
												<div class="col-md-8">
													<label id="Districtlbl"></label>
													<div style="display: none;">
														<select name="district_of_birth" id="district_of_birth"
															class="form-control-sm form-control"
															onchange="fn_otherShowHide(this,'Dis_other_id','district_other')">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getMedDistrictName}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6" id="Dis_other_id">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> District Other
													</label>
												</div>
												<div class="col-md-8">
													<label id="district_other"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Address of Birth?')){addRemarkModel('ADD_Birth_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END ADDRESS -->

	<!-- START Nationality -->
	<c:if test="${ChangeOfNationality != 0}">
		<form id="form_nationality">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="d_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title green" id="d_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="d_final"
									onclick="divN(this)"><b>Nationality</b></a>
							</h4>
						</div>
						<div id="collapse1d" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="n_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="n_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Nationality
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="n_id" name="n_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="nationalitylbl"></label>
													<div style="display: none;">
														<select name="nationality" id="nationality"
															class="form-control-sm form-control">
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
										<div class="col-md-6" id="Nati_other_id">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Nationality
														Other </label>
												</div>
												<div class="col-md-8">
													<label id="nationality_other"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Nationality?')){addRemarkModel('Nationality_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Nationality -->

	<!-- START Mother Tongue -->
	<c:if test="${ChangeOfMotherTongue != 0}">
		<form id="form_Mother_Tongue">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="e_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title lightred" id="e_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="e_final"
									onclick="divN(this)"><b>Mother Tongue</b></a>
							</h4>
						</div>
						<div id="collapse1e" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="m_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="m_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Mother Tongue
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="m_id" name="m_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="mother_toungelbl"></label>
													<div style="display: none;">
														<select name="mother_tounge" id="mother_tounge"
															class="form-control-sm form-control">
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
										<div class="col-md-6" id="MotherTou_other_id">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Mother Tongue
														Other </label>
												</div>
												<div class="col-md-8">
													<label id="mother_tongue_other"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Mother Tongue?')){addRemarkModel('Mother_Ton_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Mother Tongue -->

	<!-- START Blood Group -->
	<c:if test="${ChangeOfBloodGroup != 0}">
		<form id="form_Blood_Group">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="f_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="f_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="f_final"
									onclick="divN(this)"><b>Blood Group</b></a>
							</h4>
						</div>
						<div id="collapse1f" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="bg_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="bg_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Blood Group
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="bg_id" name="bg_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="blood_grouplbl"></label>
													<div style="display: none;">
														<select name="blood_group" id="blood_group"
															class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getBloodList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Blood Group?')){addRemarkModel('Blood_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Blood Group -->

	<!-- START Height -->
	<c:if test="${ChangeOfHeight != 0}">
		<form id="form_Height">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="g_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title blue" id="g_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="g_final"
									onclick="divN(this)"><b>Height</b></a>
							</h4>
						</div>
						<div id="collapse1g" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="h_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="h_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;"></strong>Height
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="h_id" name="h_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="heightlbl"></label>
													<div style="display: none;">
														<select name="height" id="height"
															class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getHeight}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Height?')){addRemarkModel('Height_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Height -->

	<!-- START Aadhar No -->
	<c:if test="${ChangeOfAadhar != 0}">
		<form id="form_aadhar_no">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="h_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title lightgreen" id="h_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="h_final"
									onclick="divN(this)"><b>Aadhar No</b></a>
							</h4>
						</div>
						<div id="collapse1h" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="aa_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="aa_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong> Aadhar No</label>
												</div>
												<div class="col-md-2">
													<input type="hidden" id="aa_id" name="aa_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="aadhar_no"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Aadhar No Details?')){addRemarkModel('Aadhar_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END Aadhar No -->

	<!-- START Pan No -->
	<c:if test="${ChangeOfPanNo != 0}">
		<form id="form_pan_no">
			<div class="card">
				<div class="panel-group" id="accordionaa">
					<div class="panel panel-default" id="i_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title blue" id="i_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordionaa" href="#" id="i_final"
									onclick="divN(this)"><b>Pan No</b></a>
							</h4>
						</div>
						<div id="collapse1i" class="panel-collapse collapse">
							<div class="card-body card-block">
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="pan_id" name="pan_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <label id="pan_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="pan_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong> Pan No</label>
												</div>
												<div class="col-md-8">
													<label id="pan_no"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject PAN No Details?')){addRemarkModel('Pan_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</div>
</form>
</c:if>
<!-- END Pan No -->

<!-- START Class Enrolment -->
<c:if test="${ChangeOfClassEnroll != 0}">
	<form id="form_class_enrolment">
		<div class="card">
			<div class="panel-group" id="accordionaa">
				<div class="panel panel-default" id="j_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="j_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordionaa" href="#" id="j_final"
								onclick="divN(this)"><b>Class for Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="ce_authority"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="ce_date_of_authority"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong
													style="color: red;"></strong>Class for Enrollment
												</label>
											</div>
											<div class="col-md-8">
												<label id="class_enrolllbl"></label> <input type="hidden"
													id="ce_id" name="ce_id" value="0"
													class="form-control-sm form-control autocomplete"
													autocomplete="off">
												<div style="display: none;">
													<select name="class_enroll" id="class_enroll"
														class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getClass_enrollList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="gorkha_domicile"
										style="display: none;">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"> </strong>Domicile</label>
											</div>
											<div class="col-md-8">
												<label id="domicilelbl"></label> <select name="domicile"
													id="domicile" class="form-control" style="display: none;">
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
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm"
									value="Reject"
									onclick="if (confirm('Are You Sure You Want to Reject Class For Enroll Details?')){addRemarkModel('Enroll_Class_Reject',0)}else{return false;}">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END Class Enrollment -->


<!--START Date of Enrollment-->
<c:if test="${ChangeOfDtofEnrolment != 0}">
	<form id="form_dt_of_enrollment">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="l_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="l_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordionbb" href="#" id="l_final"
								onclick="divN(this)"><b> Date of Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1l" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="doe_authority"></label> <input type="hidden"
													id="doe_id" name="doe_id" value="0" class="form-control"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="doe_date_of_authority"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong
													style="color: red;"></strong> Date of Enrollment
												</label>
											</div>
											<div class="col-md-8">
												<label id="date_of_enrollment"></label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm"
									value="Reject"
									onclick="if (confirm('Are You Sure You Want to Reject Date of Enrollment Details?')){addRemarkModel('Dt_OF_Enrol_Reject',0)}else{return false;}">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!--END  Date of Enrollment-->

<!--START Date of Attestation-->
<c:if test="${ChangeOfDtofAttestation != 0}">
	<form id="form_dt_of_Attestation">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="m_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="m_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordionbb" href="#" id="m_final"
								onclick="divN(this)"><b> Date of Attestation</b></a>
						</h4>
					</div>
					<div id="collapse1m" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="doa_authority"></label> <input type="hidden"
													id="doa_id" name="doa_id" value="0" class="form-control"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="doa_date_of_authority"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong
													style="color: red;"></strong> Date of Attestation
												</label>
											</div>
											<div class="col-md-8">
												<label id="date_of_attestation"></label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm"
									value="Reject"
									onclick="if (confirm('Are You Sure You Want to Reject Date of Attestation Details?')){addRemarkModel('Dt_Of_Attes_Reject',0)}else{return false;}">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!--END Date of Attestation-->

<!--START Family details-->
<c:if test="${ChangeOfFamily != 0}">
	<form id="form_family_details">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="n_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="n_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordionbb" href="#" id="n_final"
								onclick="divN(this)"><b> Family Details</b></a>
						</h4>
					</div>
					<div id="collapse1n" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="fd_authority"></label> <input type="hidden"
													id="fd_id" name="fd_id" value="0" class="form-control"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="fd_date_of_authority"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Father's Name</label>
											</div>
											<div class="col-md-8">
												<label id="father_name"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Father's Date of Birth</label>
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
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Father's Place of Birth</label>
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
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Father In Service/Ex-Service</label>
											</div>
											<div class="col-md-8" style="display: none;">
												<input type="radio" id="father_proff_radio1"
													name="father_proff_radio" value="yes"
													onchange="father_proffFn();">&nbsp <label for="yes">Yes</label>&nbsp
												<input type="radio" id="father_proff_radio2"
													name="father_proff_radio" value="no"
													onchange="father_proffFn();" checked="checked">&nbsp
												<label for="no">No</label><br>
											</div>
											<div class="col-md-8">
												<label id="ilfather_proff_radio"></label>
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
														style="color: red;"></strong>Father's Services</label>
												</div>
												<div class="col-md-8">
													<label id="father_servicelbl"></label>
													<div style="display: none;">
														<select name="father_service" id="father_service"
															class="form-control-sm form-control" onchange="ServiceOtherFn('father_otherDiv','father_personalDiv',this)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getExservicemenList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="father_otherDiv"
											style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Other</label>
												</div>
												<div class="col-md-8">
													<label id="father_other"></label>
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
													<label id="father_personal_no"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="father_proffDiv">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Father's Profession</label>
											</div>
											<div class="col-md-8">
												<label id="father_professionlbl"></label>
												<div style="display: none;">
													<select name="father_profession" id="father_profession"
														class="form-control-sm form-control">
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
									<div class="col-md-6" id="father_proffotherDiv" style="display: none">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Father's
													Profession Other </label>
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
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Mother's Name</label>
											</div>
											<div class="col-md-8">
												<label id="mother_name"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Mother's Date of Birth</label>
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
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Mother's Place of Birth</label>
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
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Mother In Service/Ex-Service</label>
											</div>
											<div class="col-md-8" style="display: none;">
												<input type="radio" id="mother_proff_radio1"
													name="mother_proff_radio" value="yes"
													onchange="mother_proffFn();">&nbsp <label for="yes">Yes</label>&nbsp
												<input type="radio" id="mother_proff_radio2"
													name="mother_proff_radio" value="no"
													onchange="mother_proffFn();" checked="checked">&nbsp
												<label for="no">No</label><br>
											</div>
											<div class="col-md-8">
												<label id="ilmother_proff_radio"></label>
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
														style="color: red;"></strong>Mother's Services</label>
												</div>
												<div class="col-md-8">
													<label id="mother_servicelbl"></label>
													<div style="display: none;">
														<select name="mother_service" id="mother_service"
															class="form-control-sm form-control"   onchange="ServiceOtherFn('mother_otherDiv','mother_personalDiv',this)">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getExservicemenList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="mother_otherDiv"
											style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"></strong>Other</label>
												</div>
												<div class="col-md-8">
													<label id="mother_other"></label>
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
													<label id="mother_personal_no"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6" id="mother_proffDiv">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Mother's Profession</label>
											</div>
											<div class="col-md-8">
												<label id="mother_professionlbl"></label>
												<div style="display: none;">
													<select name="mother_profession" id="mother_profession"
														class="form-control-sm form-control">
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
									<div class="col-md-6" id="mother_otherproffDiv" style="display: none">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Mother's
													Profession Other </label>
											</div>
											<div class="col-md-8">
												<label id="mother_proffother"></label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm"
									value="Reject"
									onclick="if (confirm('Are You Sure You Want to Reject Family Details?')){addRemarkModel('Family_Reject',0)}else{return false;}">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!--END Family Details-->

<!--START Details of Sibilings-->
<c:if test="${ChangeOfSibling != 0}">
	<form id="form_sibilings">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="o_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="o_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordionbb" href="#" id="o_final"
								onclick="divN(this)"><b>Details of Sibling</b></a>
						</h4>
					</div>
					<div id="collapse1o" class="panel-collapse collapse">
						<div class="card-body card-block">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Authority</label>
											</div>
											<div class="col-md-8">
												<label id="ds_authority"></label> <input type="hidden"
													id="ds_id" name="ds_id" value="0" class="form-control"
													autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;"></strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<label id="ds_date_of_authority"></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<table id="family_table" class="table-bordered "
										style="margin-top: 10px; width: 100%;">
										<thead style="color: white; text-align: center;">
											<tr>
												<th style="width: 2%;">Sr No</th>
												<th>Name</th>
												<th>Date of Birth</th>
												<th>Relationship</th>
												<th>Aadhar No</th>
												<th>PAN No</th>
												<th>Services</th>
												<th>Personal No.</th>
												<th>Other Service</th>
											</tr>
										</thead>
										<tbody data-spy="scroll" id="family_sibtbody"
											style="min-height: 46px; max-height: 650px; text-align: center;">
											<tr id="tr_sibling1">
												<td class="sib_srno" style="width: 2%;">1</td>
											</tr>
										</tbody>
									</table>
									<input type="hidden" id="siblings_count" name="siblings_count"
										class="required form-control" autocomplete="off" value="1" />
									<input type="hidden" id="siblingsOld_count"
										name="siblingsOld_count" class="form-control" maxlength="2"
										autocomplete="off" value="0">
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								
							<input type="button" class="btn btn-primary btn-sm"
									value="Reject"
									onclick="if (confirm('Are You Sure You Want to Reject Siblings Details?')){addRemarkModel('Sibilings_Reject',0)}else{return false;}">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!--END Details of Sibilings-->

<form:form name="Approve_Update_Census_Data"
	id="Approve_Update_Census_Data"
	action="Approve_Update_Census_DataAction_JCO" method="post"
	class="form-horizontal" commandName="Approve_Update_Census_DataCMD_JCO">
	<div class="animated fadeIn">
		<div class="card">
			<input type="hidden" id="jco_id" name="jco_id" value="0"
				class="form-control autocomplete" autocomplete="off">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="col-md-12" align="center">
						<p>
							<input type="checkbox" id="myCheck" onclick="myFunction();" /> I
							have checked all the tabs and certify that all the information is
							correct.
						</p>
					</div>
					<div class="col-md-12" id="submit_a" align="center">
						<input type="submit" class="btn btn-primary btn-sm"
							value="Approve"
							onclick="if (confirm('Are You Sure You Want to Approve Update Census Details?')){return true;}else{return false;}">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>


<script type="text/javascript">
function myFunction() {
	  var checkBox = document.getElementById("myCheck");
	  if (checkBox.checked == true){
		  $("#submit_a").show();
	  } else {
		  $("#submit_a").hide();
	  }
}

function divN(obj){
	var id = obj.id;
	var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		$('.droparrow').each(function(i, obj) {
			 $("#"+obj.id).attr("class", "droparrow collapsed");
			 });
		if (hasC) {	
		$("#"+id).addClass( " collapsed");		 
		}  else {				
			$("#"+sib_id).addClass("show");	
			$("#"+id).removeClass("collapsed");
	    }
		
		if (obj.id == "a_final") {
			if(!hasC){
			}
		}
		
		
		else if (obj.id == "b_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "e_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "f_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "g_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "k_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "l_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "m_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "n_final") {
			if(!hasC){
			}
		}
		else if (obj.id == "o_final") {
			if(!hasC){
			}
		}
		}
</script>
<script>

$(document).ready(function() {
	
 	$('#jco_id').val('${id}');
  	$('#army_no').val('${army_no2}');
  	 $("input#army_no").attr('readonly', true);
  	personal_number();
  	$("#submit_a").hide();
  	 
	<c:if test="${ChangeOfdob != 0}"> 
	getdob();
	</c:if>
	<c:if test="${ChangeOfgender != 0}"> 
	get_Gender();
	</c:if>
	<c:if test="${ChangeOfAddressBirth != 0}"> 
	get_Address_OF_Birth();
	</c:if>
	<c:if test="${ChangeOfNationality != 0}"> 
	get_Nationality();
	</c:if>
	<c:if test="${ChangeOfMotherTongue != 0}"> 
	get_Mother_Tongue();
	</c:if>
	<c:if test="${ChangeOfBloodGroup != 0}"> 
	get_Blood_Group();
	</c:if>
	<c:if test="${ChangeOfHeight != 0}"> 
	get_height();
	</c:if>
	<c:if test="${ChangeOfAadhar != 0}"> 
	get_aadhar_no();
	</c:if>
	<c:if test="${ChangeOfPanNo != 0}"> 
	get_Pan_No();
	</c:if>
	<c:if test="${ClassEnroll != 0}"> 
	get_enrollClass();
	</c:if>
	<c:if test="${ChangeOfDtofEnrolment != 0}"> 
	get_Dt_Of_Enrolment();
	</c:if>
	<c:if test="${ChangeOfDtofAttestation != 0}"> 
	get_Dt_Of_Attestation();
	</c:if>
	<c:if test="${ChangeOfFamily != 0}"> 
	get_Family_Detalis();
	</c:if>
	<c:if test="${ChangeOfSibling != 0}"> 
	get_Sibling_Detalis();
	</c:if>
	
});

function personal_number()
{
	if($("input#personnel_no").val()==""){
		alert("Please select Army No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	var army_no = $("input#army_no").val();
	 $.post('GetArmyNoData?' + key + "=" + value,{ army_no : army_no },function(j) {
		 if(j!=""){
	    	$("#jco_id").val(j[0][0]);
	    	var jco_id =j[0][0]; 
	    	$.post('GetJcoUpdateCensusDataApprove?' + key + "=" + value,{ jco_id : jco_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			
	    			  $('#div_cda_acnt_no').show(); 
	    			  curr_marital_status=k[0].marital_status;  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			  
	    			   $("#cadet_name").text(k[0].full_name);
	    			 	if(k[0].date_of_birth==null){
	    		    		$("#dob").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#dob").text(convertDateFormate(k[0].date_of_birth));  
	    		    		 $("#dob_date").val(ParseDateColumncommission(k[0].date_of_birth));
	    		    	}
	    			 	$("#enroll_date").val(ParseDateColumncommission(k[0].enroll_dt));
	    			    $("#marital_status_p").text(k[0].marital_status);
	    		    	$("#p_rank").text(k[0].rank);
	    		    	if(k[0].rank=='RECTS')
	    		    		$("#attestationDiv").show();
	    		    	else
	    		    		$("#attestationDiv").hide();
	    		    	$("#hd_p_rank").val(k[0].rank);
	    		    	$("#p_app").text(k[0].appointment);
	    		    	if(k[0].appointment==null){
	    		    		$("#p_app").text("");
	    		    	}
	    		    	else{
	    		    		$("#p_app").text(k[0].appointment);
	    		    	}
	    		    	if(k[0].date_appointment==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0].date_appointment));  
	    		    	}
	    		    	if(k[0].dt_of_tos==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0].dt_of_tos));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0].dt_of_tos));
	    		    	$("#app_sus_no").text(k[0].unit_sus_no);
	    		    	$("#p_id_no").text(k[0].id_card_no);
	    		    	$("#p_religion").text(k[0].religion_name);
	    		    	$("#app_parent_arm").text(k[0].parent_arm);
	    		    	$("#p_cmd").text(k[0].command);
	    		    	$('#inter_arm_record_office_suslb').text(k[0].record_office_sus );
	    		    	$('#inter_arm_record_office_unitlb').text(k[0].record_office );
	    		    	$('#inter_arm_old_arm_service').text(k[0].parent_arm );
	    		    	
	    		    	$("#old_trade").text(k[0].trade); 
	    		    	$("#old_class").text(k[0].class_pay);
	    		    	$("#old_group").text(k[0].pay_group);
	    		    	$("#old_dt_seniority").text(convertDateFormate(k[0].date_of_seniority));

	    			    
	    				
	    		    	if(k[0].parent_arm == "GORKHA" || k[0].parent_arm == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	if(k[0].regiment!=0){
	    		    		$('#inter_arm_regt_val').val(k[0].regiment);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    		$('#inter_arm_old_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    				
	    		    	var sus_no =k[0].unit_sus_no;
	    		    	getunit(sus_no);
	    		    	function getunit(val) {	
	    		            $.post("getTargetUnitNameList?"+key+"="+value, {
	    		            	sus_no : sus_no
	    		        }, function(j) {
	    		                
	    		                

	    		        }).done(function(j) {
	    		    				   var length = j.length-1;
	    		    	                   var enc = j[length].substring(0,16); 
	    		    	                   
	    		    	                   $("#app_unit_name").text(dec(enc,j[0])); 
	    		        }).fail(function(xhr, textStatus, errorThrown) {
	    		        });
	    		    } 
	    		 }
	   });
	 		$.ajaxSetup({
					async : false
				});
	 		
	    	 $.post('GetServingStatusJCO?' + key + "=" + value,{ jco_id : jco_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		 }
	   });
		 }
});
	 		
	 $("input#army_no").attr('readonly', true);
}


$("input#army_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#army_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved_JCO?"+key+"="+value,
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
		        	  document.getElementById("army_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      } 	     
		}); 			
});
</script>

<script>


//****************************Start Gender********************************//
function Gender_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var g_id = $("#g_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getgenderJCOReject?' + key + "=" + value, {jco_id:jco_id,g_id:g_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_gender').hide();
		 }
	 });
} 
	
function get_Gender(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_GenderData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#g_authority').text(j[0].authority);
				$('#g_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#gender').val(j[0].gender);
				$('#genderlbl').text($( "#gender option:selected" ).text());
				$("#g_id").val(j[0].id);
			}
	  });
} 

//*****************************End Gender********************************//

//*****************************Start DOB********************************//
function DOB_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var birth_id = $("#birth_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getBirthJCOReject?' + key + "=" + value, {jco_id:jco_id,birth_id:birth_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_Birth').hide();
		 }
	 });
} 

function getdob(){
	 var jco_id = '${id}';
	 $.post('getupdate_census_dobData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
		 if(j!=""){
			 $('#b_authority').text(j[0].authority);
			 $('#b_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
			 $('#date_of_birth').text(convertDateFormate(j[0].date_of_birth));
			 $('#birth_id').val(j[0].id);
		 }
	 });
 }
//*****************************End DOB********************************//

//******************************Start Address of Birth********************************//
function ADD_Birth_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var addr_ch_id = $("#addr_ch_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getAdd_BirthJCOReject?' + key + "=" + value, {jco_id:jco_id,addr_ch_id:addr_ch_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_addr_details_form').hide();
		 }
	 });
}

function get_Address_OF_Birth(){
 	 var jco_id=$('#jco_id').val(); 
 	  $.post('getupdate_census_ADDBirData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
 			var v=j.length;
 			if(j!=""){
 				$('#addr_authority').text(j[0].authority);
 				$('#addr_date_authority').text(convertDateFormate(j[0].date_of_authority));
 				$('#place_of_birth').text(j[0].place_of_birth);
 				$('#country_of_birth').val(j[0].country_of_birth);
				$('#Countrylbl').text($( "#country_of_birth option:selected" ).text());
				$('#state_of_birth').val(j[0].state_of_birth);
				$('#statelbl').text($( "#state_of_birth option:selected" ).text());
				$('#district_of_birth').val(j[0].district_of_birth);
				$('#Districtlbl').text($( "#district_of_birth option:selected" ).text());
				
				var text = $("#country_of_birth option:selected").text();
				if(text == "OTHERS"){
					$('#country_other').text(j[0].country_other);
					$("#other_id").show();
				}
				else{
					$("#other_id").hide();
				}
				
				var text1 = $("#state_of_birth option:selected").text();
				if(text1 == "OTHERS"){
					$('#state_other').text(j[0].state_other);
					$("#St_other_id").show();
				}
				else{
					$("#St_other_id").hide();
				}
				
				var text2 = $("#district_of_birth option:selected").text();
				if(text2 == "OTHERS"){
					$('#district_other').text(j[0].district_other);
					$("#Dis_other_id").show();
				}
				else{
					$("#Dis_other_id").hide();
				}
				
 				$("#addr_ch_id").val(j[0].id);
 			}
 	  });
}
//*****************************End Address Of Birth********************************//

//*****************************Start Nationality********************************//
function Nationality_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var n_id = $("#n_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getNationality_JCOReject?' + key + "=" + value, {jco_id:jco_id,n_id:n_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_nationality').hide();
		 }
	 });
}
function get_Nationality(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_NatinalityData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#n_authority').text(j[0].authority);
				$('#n_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#nationality').val(j[0].nationality);
				$('#nationalitylbl').text($( "#nationality option:selected" ).text());
				
				var text = $("#nationality option:selected").text();
				if(text == "OTHERS"){
					$('#nationality_other').text(j[0].nationality_other);
					$("#Nati_other_id").show();
				}
				else{
					$("#Nati_other_id").hide();
				}
				
				$("#n_id").val(j[0].id);
			}
	  });
}
//*****************************End Nationality********************************//

//*****************************Start Mother Tongue********************************//

function Mother_Ton_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var m_id = $("#m_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getMother_Tongue_JCOReject?' + key + "=" + value, {jco_id:jco_id,m_id:m_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_Mother_Tongue').hide();
		 }
	 });
}
function get_Mother_Tongue(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_Mother_TongueData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#m_authority').text(j[0].authority);
				$('#m_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#mother_tounge').val(j[0].mother_tounge);
				$('#mother_toungelbl').text($( "#mother_tounge option:selected" ).text());
				
				var text = $("#mother_tounge option:selected").text();
				if(text == "OTHERS"){
					$('#mother_tongue_other').text(j[0].mother_tongue_other);
					$("#MotherTou_other_id").show();
				}
				else{
					$("#MotherTou_other_id").hide();
				}
				
				
				$("#m_id").val(j[0].id);
			}
	  });
}
	
//*****************************End Mother Tongue********************************//

//*****************************Start Blood Group********************************//
function Blood_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var bg_id = $("#bg_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getBlood_JCOReject?' + key + "=" + value, {jco_id:jco_id,bg_id:bg_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_Blood_Group').hide();
		 }
	 });
}
function get_Blood_Group(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_Blood_GroupData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#bg_authority').text(j[0].authority);
				$('#bg_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#blood_group').val(j[0].blood_group);
				$('#blood_grouplbl').text($( "#blood_group option:selected" ).text());
				$("#bg_id").val(j[0].id);
			}
	  });
}

//*****************************End Blood Group********************************//

//*****************************Start Height********************************//
function Height_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var h_id = $("#h_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getHeight_JCOReject?' + key + "=" + value, {jco_id:jco_id,h_id:h_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_Height').hide();
		 }
	 });
}
function get_height(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_HeightData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#h_authority').text(j[0].authority);
				$('#h_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#height').val(j[0].height);
				$('#heightlbl').text($( "#height option:selected" ).text());
				$("#h_id").val(j[0].id);
			}
	  });
}
//*****************************End Height********************************//

//*****************************Start Aadhar No********************************//
function Aadhar_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var aa_id = $("#aa_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getAadhar_No_JCOReject?' + key + "=" + value, {jco_id:jco_id,aa_id:aa_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_aadhar_no').hide();
		 }
	 });
}
function get_aadhar_no(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_AadharData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#aa_authority').text(j[0].authority);
				$('#aa_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				var aadhar = j[0].aadhar_no +"";
				$('#aadhar_no').text(aadhar.substring(0, 12));
				$("#aa_id").val(j[0].id);
			}
	  });
}
//*****************************End Addhar No********************************//

//*****************************Start Pan No********************************//
function Pan_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var pan_id = $("#pan_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getPan_No_JCOReject?' + key + "=" + value, {jco_id:jco_id,pan_id:pan_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_pan_no').hide();
		 }
	 });
}
function get_Pan_No(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_PanNoData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#pan_authority').text(j[0].authority);
				$('#pan_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#pan_no').text(j[0].pan_no);
				$("#pan_id").val(j[0].id);
			}
	  });
}

//*****************************End Pan No********************************//

//*****************************Start Class Of Enrollment********************************//
function Enroll_Class_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var ce_id = $("#ce_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getEnroll_Class_JCOReject?' + key + "=" + value, {jco_id:jco_id,ce_id:ce_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_class_enrolment').hide();
		 }
	 });
}
function get_enrollClass(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_EnrollClassData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#ce_authority').text(j[0].authority);
				$('#ce_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#class_enroll').val(j[0].class_enroll);
				$('#class_enrolllbl').text($( "#class_enroll option:selected" ).text());
				fn_domicile();
				$('#domicile').val(j[0].domicile);
				$('#domicilelbl').text($( "#domicile option:selected" ).text());
				
				
				$("#ce_id").val(j[0].id);
			}
	  });
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
//*****************************End Class Of Enrolment********************************//

//*****************************Satrt Date Of Enrolment********************************//
function Dt_OF_Enrol_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var doe_id = $("#doe_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getDate_OF_Enrol_JCOReject?' + key + "=" + value, {jco_id:jco_id,doe_id:doe_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_dt_of_enrollment').hide();
		 }
	 });
}

function get_Dt_Of_Enrolment(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_Dt_Of_EnrolmentData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#doe_authority').text(j[0].authority);
				$('#doe_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#date_of_enrollment').text(convertDateFormate(j[0].date_of_enrollment));
				$("#doe_id").val(j[0].id);
			}
	  });
}

//*****************************End Date Of Enrolment********************************//

//*****************************Start Date Of Attestion********************************//
function Dt_Of_Attes_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var doa_id = $("#doa_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getDt_Of_Attes_JCOReject?' + key + "=" + value, {jco_id:jco_id,doa_id:doa_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_dt_of_Attestation').hide();
		 }
	 });
}

function get_Dt_Of_Attestation(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_Dt_Of_AttesationData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#doa_authority').text(j[0].authority);
				$('#doa_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#date_of_attestation').text(convertDateFormate(j[0].date_of_attestation));
				$("#doa_id").val(j[0].id);
			}
	  });
}

//*****************************End Date Of Attestion********************************//

//****************************Start Family Details********************************//
function Family_Reject(){
	 
	 var jco_id = $("#jco_id").val(); 
	 var fd_id = $("#fd_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getFamily_JCOReject?' + key + "=" + value, {jco_id:jco_id,fd_id:fd_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_family_details').hide();
		 }
	 });
}

function get_Family_Detalis(){
	 var jco_id=$('#jco_id').val(); 
	  $.post('getupdate_census_FamilyData2?' + key + "=" + value, {jco_id:jco_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#fd_authority').text(j[0].authority);
				$('#fd_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
				$('#father_name').text(j[0].father_name);
				$('#father_dob').text(convertDateFormate(j[0].father_dob));
				$('#father_place_birth').text(j[0].father_place_birth);
				
				
				if(j[0].father_profession == "0"){
					$('#father_proff_radio1').prop('checked', true);
					$('#ilfather_proff_radio').text("Yes")
					$('#father_inserviceDiv').show();
					$("#father_proffDiv").hide();
				}
				else{
					$('#father_proff_radio2').prop('checked', true);
					$('#ilfather_proff_radio').text("No")
					$('#father_inserviceDiv').hide();
					$("#father_proffDiv").show();
				}
				
				$('#father_profession').val(j[0].father_profession);
				$('#father_professionlbl').text($( "#father_profession option:selected" ).text());
				$('#father_service').val(j[0].father_service);
				$('#father_servicelbl').text($( "#father_service option:selected" ).text());
				$('#father_personal_no').text(j[0].father_personal_no);
				$('#mother_name').text(j[0].mother_name);
				$('#mother_dob').text(convertDateFormate(j[0].mother_dob));
				$('#mother_place_birth').text(j[0].mother_place_birth);
				$('#mother_profession').val(j[0].mother_profession);
				$('#mother_professionlbl').text($( "#mother_profession option:selected" ).text());
				
				if(j[0].mother_profession == "0"){
					$('#mother_proff_radio1').prop('checked', true);
					$('#ilmother_proff_radio').text("Yes")
					$('#mother_inserviceDiv').show();
					$("#mother_proffDiv").hide();
				
				}
				else{
					$('#mother_proff_radio2').prop('checked', true);
					$('#ilmother_proff_radio').text("No")
					$('#mother_inserviceDiv').hide();
					$("#mother_proffDiv").show();
				}
				
				$('#mother_service').val(j[0].mother_service);
				$('#mother_servicelbl').text($( "#mother_service option:selected" ).text());
				$('#mother_personal_no').text(j[0].mother_personal_no);
				
 				var text4 = $("#father_service option:selected").text();
				if(text4 == "OTHER"){
					
					$("#father_other").text(j[0].father_other);
					$("#father_otherDiv").show();
					$("#father_personalDiv").hide();
				}
				else{
					$("#father_otherDiv").hide();
					$("#father_personalDiv").show();
				} 
				
				var text5 = $("#mother_service option:selected").text();
				
				if(text5 == "OTHER"){
					$("#mother_other").text(j[0].mother_other);
					$("#mother_otherDiv").show();
					$("#mother_personalDiv").hide();
				}
				else{
					$("#mother_otherDiv").hide();
					$("#mother_personalDiv").show();
				} 
				ServiceOtherFn('father_otherDiv','father_personalDiv',document.getElementById('father_service'));
				ServiceOtherFn('mother_otherDiv','mother_personalDiv',document.getElementById('mother_service'));
			
				var text6 = $("#father_profession option:selected").text();
				if(text6 == "OTHERS"){
					$("#father_proffother").text(j[0].father_proffother);
					$("#father_proffotherDiv").show();
				}
				else{
					$("#father_proffotherDiv").hide();
				}
				var text8 = $("#mother_profession option:selected").text();
				if(text8 == "OTHERS"){
					$("#mother_proffother").text(j[0].mother_proffother);
					$("#mother_otherproffDiv").show();
				}
				else{
					$("#mother_otherproffDiv").hide();
				}
				
				$("#fd_id").val(j[0].id);
			}
	  });
}
//*****************************End Family Details********************************//

//*****************************Start Sibling********************************//
function Sibilings_Reject(){
	var jco_id = $("#jco_id").val(); 
	 var ds_id = $("#ds_id").val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('getSibling_JCOReject?' + key + "=" + value, {jco_id:jco_id,ds_id:ds_id,reject_remarks:reject_remarks}, function(j){
		 if(j!=""){
			 alert(j);
			 $('#form_sibilings').hide();
		 }
	 });
}
function get_Sibling_Detalis(){
	var jco_id = $('#jco_id').val();
	var i = 0;
	$.post('getupdate_census_SiblingData2?' + key + "=" + value,{jco_id:jco_id},function(j) {
		var v = j.length;
		if (v != 0) {
			$('#family_sibtbody').empty();
			for (i; i < v; i++) {
				x = i + 1;
				var dob = convertDateFormate(j[i].date_of_birth);
				
				$("table#family_table").append('<tr id="tr_sibling'+x+'">'
						+ '<td class="sib_srno">'
						+ x
						+ '</td>'
						+ '<td style="width: 10%;"><label id="sib_name_lb'+x+'">'+j[i].name+'</label> '
						+ '<td style="width: 10%;"> <label id="sib_date_of_birth_lb'+x+'">'+dob+'</label></td>'
						+ '<td style="width: 10%;"><label id="sib_relationship_lb'+x+'"></label> <select style="display:none" name="sib_relationship'+x+'" id="sib_relationship'+x+'" class="form-control"   >'
						+ '<option value="0">--Select--</option>'
						+ '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">'
						+ '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>'
						+ '</c:forEach></select></td>'
						+ '<td style="width: 10%;"><label id="aadhar_no_lb'+x+'">'+j[i].aadhar_no+'</label></td>'
						+ '<td style="width: 10%;"> <label id="pan_no_lb'+x+'">'+setInvalidToNull(j[i].pan_no)+'</label></td>'
						
						+ '<td style="display:none;"><input type="text" id="sib_ch_id'+x+'" name="sib_ch_id'+x+'"   value="'+j[i].id+'"  class="form-control autocomplete" autocomplete="off" ></td>'
						+ '<td style="width: 5%;"> ' + '	<label class=" form-control-label" id="sibling_servicelbl' + x + '"> <select name="sibling_service' + x + '" id="sibling_service' + x + '"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
						+ '<td style="width: 10%;"> ' + '	<label class=" form-control-label"  id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	>' +  + '</label> ' + '	</td>'
						+ '<td style="width: 10%;"> ' + '	<label class=" form-control-label"  id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	>' + + '</label> ' + '	</td>'
						+'</tr>');
				
				  		$('#sib_relationship' + x).val(j[i].relationship);
						$('#sib_relationship_lb' + x).text($('#sib_relationship'+ x+' option:selected').text());
						if(j[i].sibling_service == 0){
						$('#sibling_service' + x).val("");
						}else{
						$('#sibling_service' + x).val(j[i].sibling_service);
						
						}
						$('#sibling_personal_no' + x).text(j[i].sibling_personal_no);
						$('#other_sibling_ser' + x).text(setInvalidToNull(j[i].other_sibling_ser));
						$('#sibling_servicelbl' + x).text($("#sibling_service" + x +" option:selected").text());
						$('#ds_authority').text(j[0].authority);
						$('#ds_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
						$("#ds_id").val(j[0].id);
							
				 
		}

	}
	  });
}

//*****************************End Sibling********************************//

function rank_intake_jco() 
{
	var a = $("select#rank option:selected").val();

	if(a == "17") {
		$("#intake").show();
		
	}
	else
		{
		$("#intake").hide();
		}
	
}

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

</script>
<script>
  
jQuery(function($){
	 datepicketDate('g_date_of_authority');  
	 datepicketDate('b_date_of_authority'); 
	 datepicketDate('date_of_birth');  
	 datepicketDate('addr_date_authority'); 
	 datepicketDate('n_date_of_authority'); 
	 datepicketDate('m_date_of_authority'); 
	 datepicketDate('bg_date_of_authority'); 
	 datepicketDate('h_date_of_authority');
	 datepicketDate('aa_date_of_authority');
	 datepicketDate('pan_date_of_authority'); 
	 datepicketDate('ce_date_of_authority');
	 datepicketDate('rank_date_of_authority');
	 datepicketDate('doe_date_of_authority');
	 datepicketDate('enroll_dt');
	 datepicketDate('doa_date_of_authority');
	 datepicketDate('date_of_attestation');
	 datepicketDate('fd_date_of_authority');
	 datepicketDate('father_dob');
	 datepicketDate('mother_dob');
	 datepicketDate('ds_date_of_authority');
	 datepicketDate('sib_date_of_birth1');
	 });
</script>

