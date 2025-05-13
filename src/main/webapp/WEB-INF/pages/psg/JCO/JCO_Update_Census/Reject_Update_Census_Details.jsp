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
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

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
<form  id="Personnel_no_form" >
 		<div class="animated fadeIn">
	    	<div class="" align="center">
		<div class="card">	
			<div class="panel-group" id="accordion">
				<div class="panel panel-default" id="insp_div1">
					<div class="panel-heading">
					<div class="panel-heading">
						<h4 class="panel-title main_title" id="insp_div5">
						<b>REJECTED DATA OF UPDATED JCO/OR</b>
						</h4>
						<p> <strong>(*Note: Make sure you have either updated or deleted the data entry, else the same data entry will be shown in pending with Approver.) </strong></p>
					</div>
	              					<div class="col-md-12" style="padding-top: 20px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Army No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
							               		<input type="hidden" id="jco_id" name="jco_id" class="form-control autocomplete" autocomplete="off"  > 	
							                    <input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
											
											
										 <input type="hidden" id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="rankV" name="rankV" class="form-control autocomplete"  > 
						                 <input type=hidden id="jco_idV" name="jco_idV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  >
											
											</div>
										</div>
									</div>

								</div> 
						
					 	</div>	
					 	<div class="card-footer" align="right" class="form-control" id="back_bt_div" >
							<input type="button"   class="btn btn-primary btn-sm" onclick="Cancel();" value="Back">   
			            </div> 			
					</div>
				</div>
		</div>
	</div>
	</div>
</form>
<div id="maindetailsdiv">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="app_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="app_final" onclick="divN(this)"><b>RECORD</b></a>
						</h4>
					</div>
					<div id="collapse1app" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Name</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="cadet_name"></label>
												<label class=" form-control-label" id="dob" style="display: none;"></label> 
												<input type="hidden" class=" form-control-label" id="dob_date">
												<input type="hidden" class=" form-control" id="comm_date">
												<label class=" form-control-label" id="marital_status_p" style="display: none;"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Rank</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_rank"></label> 
												<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control-sm form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Appointment</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_app"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Date of Appointment</label>
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
												<label class=" form-control-label" id="p_tos"></label>
												<input type="hidden" class=" form-control-label" id="tos_date">
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> SUS No</label>
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
												<label class=" form-control-label"><strong style="color: red;"></strong> Command</label>
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
												<label class=" form-control-label"> Arm/Service</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="app_parent_arm"></label>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Serving Status</label>
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
<c:if test="${ChangeOfGender != 0}">
<form id="form_gender">
	<div class="card">
		<div class="panel-group" id="accordionaa">
			<div class="panel panel-default" id="a_div1">
				<div class="panel-heading">
					<h4 class="panel-title main_title yellow" id="a_div5">
						<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="a_final" onclick="divN(this)"><b>Gender</b></a>
					</h4>
				</div>
				<div id="collapse1p" class="panel-collapse collapse">
					<div class="card-body card-block">
						<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('gender');" />
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="g_authority" name="g_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="g_date_of_authority"
												id="g_date_of_authority" maxlength="10"
												onclick="clickclear(this, 'DD/MM/YYYY')"
												class="form-control-sm form-control"
												style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Gender
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="g_id" name="g_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="gender"
														id="gender" class="form-control-sm form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getGenderList}"
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
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="g_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
						<div class="card-footer" align="center" class="form-control">
		              		<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Gender_save_fn();">
			            <input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_gender_remove();">
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
<c:if test="${ChangeOfDOB != 0}">
	<form id="form_Birth">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="b_final" onclick="divN(this)"><b>DATE OF BIRTH</b></a>
						</h4>
					</div>
					<div id="collapse1x" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('date_of_birth');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="b_authority" name="authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
												<input type="hidden" id="birth_id" name="birth_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="b_date_of_authority"
													id="b_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
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
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Birth </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="date_of_birth" id="date_of_birth"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);">
											</div>
										</div>
									</div>
									<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="b_reject_remarks"></label>
												</div>
											</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_DOB_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_DOB_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion9" href="#" id="c_final" onclick="divN(this)"><b>Address of Birth</b></a>
						</h4>
					</div>
					<div id="collapse1c" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('address_of_birth');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="addr_authority"
														id="addr_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
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
													<input type="text" name="addr_date_authority"
														id="addr_date_authority" maxlength="10"
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
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Place of Birth</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="place_of_birth"
														name="place_of_birth"
														onkeypress="return onlyAlphabets(event);"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="50">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Country</label>
												</div>
												<div class="col-md-8">
													<select name="country_of_birth" id="country_of_birth"
														class="form-control"
														onchange="fn_otherShowHide(this,'con_other_id','country_other')">
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

									<div class="col-md-12">
										<div class="col-md-6" id="con_other_id" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Country Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="country_other" name="country_other"
														class="form-control autocomplete" autocomplete="off"
														onkeypress="return onlyAlphabets(event);">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> State</label>
												</div>
												<div class="col-md-8">
													<select name="state_of_birth" id="state_of_birth"
														class="form-control"
														onchange="fn_otherShowHide(this,'sta_other_st','state_other')">
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
										<div class="col-md-6" id="sta_other_st" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">State Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="state_other" name="state_other"
														class="form-control autocomplete" autocomplete="off"
														onkeypress="return onlyAlphabets(event);"> <input
														type="hidden" id="addr_ch_id" name="addr_ch_id"
														class="form-control autocomplete" autocomplete="off"
														value="0">
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> District</label>
												</div>
												<div class="col-md-8">
													<select name="district_of_birth" id="district_of_birth"
														class="form-control"
														onchange="fn_otherShowHide(this,'other_dist','district_other')">
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
									<div class="col-md-12">
										<div class="col-md-6" id="other_dist" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">District Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="district_other"
														name="district_other" class="form-control autocomplete"
														autocomplete="off"
														onkeypress="return onlyAlphabets(event);">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="ab_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Add_Birth_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_Add_Birth_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="d_final" onclick="divN(this)"><b>Nationality</b></a>
						</h4>
					</div>
					<div id="collapse1d" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('nationality');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="n_authority" name="n_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="n_date_of_authority"
														id="n_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Nationality
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="n_id" name="n_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="nationality"
														id="nationality" class="form-control"
														onchange="fn_otherShowHide(this,'NA_other_nati','nationality_other')">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getNationalityList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="NA_other_nati"
											style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Nationality
														Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="nationality_other"
														name="nationality_other" class="form-control autocomplete"
														autocomplete="off"
														onkeypress="return onlyAlphabets(event);">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="n_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Nationality_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_Nationality_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="e_final" onclick="divN(this)"><b>Mother Tongue</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('mother_tounge');" />
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="m_authority" name="m_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="m_date_of_authority"
													id="m_date_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Mother Tongue
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="m_id" name="m_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="mother_tounge"
														id="mother_tounge" class="form-control"
														onchange="fn_otherShowHide(this,'motherton_other','mother_tongue_other')">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getMothertoungeList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="motherton_other"
											style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Mother Tongue
														Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_tongue_other"
														name="mother_tongue_other"
														class="form-control autocomplete" autocomplete="off"
														onkeypress="return onlyAlphabets(event);">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="mt_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Mother_Tongue_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_Mother_Tongue_remove();">
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
								<br> <input type="button" class="btn btn-primary btn-sm"
									value="View History" onclick="Pop_Up_History('blood_group');" />
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="bg_authority" name="bg_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="bg_date_of_authority"
														id="bg_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Blood Group
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="bg_id" name="bg_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="blood_group"
														id="blood_group" class="form-control">
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
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="bg_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Update" onclick="validate_Blood_save_fn();"> <input
										type="button" class="btn btn-danger btn-sm" value="Delete"
										onclick="validate_Blood_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="g_final" onclick="divN(this)"><b>Height</b></a>
						</h4>
					</div>
					<div id="collapse1g" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('height');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="h_authority" name="h_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="h_date_of_authority"
														id="h_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Height
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="h_id" name="h_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="height"
														id="height" class="form-control">
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
														<label class=" form-control-label"> Reject Remarks</label>
													</div>
													<div class="col-md-8">
														<label class=" form-control-label" id="h_reject_remarks"></label>
													</div>
												</div>
											</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Height_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_Height_remove();">
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
								<br> <input type="button" class="btn btn-primary btn-sm"
									value="View History" onclick="Pop_Up_History('aadhar_no');" />
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="aa_authority" name="aa_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="aa_date_of_authority"
														id="aa_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
														style="color: red;">* </strong> Aadhar No</label>
												</div>
												<div class="col-md-2">
													<input type="hidden" id="aa_id" name="aa_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <input type="text"
														id="adhar_number1" name="adhar_number1"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="4"
														onkeypress="return isNumber(event)"
														onkeyup="movetoNext(this, 'adhar_number2'),lengthadhar()">
												</div>
												<div class="col-md-2">
													<input type="text" id="adhar_number2" name="adhar_number2"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="4"
														onkeypress="return isNumber(event)"
														onkeyup="movetoNext(this, 'adhar_number3'),lengthadhar()">
												</div>
												<div class="col-md-2">
													<input type="text" id="adhar_number3" name="adhar_number3"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="4"
														onkeypress="return isNumber(event)"
														onkeyup="lengthadhar()">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="ad_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Update" onclick="validate_add_No_save_fn();"> <input
										type="button" class="btn btn-danger btn-sm" value="Delete"
										onclick="validate_add_No_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="i_final" onclick="divN(this)"><b>Pan No</b></a>
						</h4>
					</div>
					<div id="collapse1i" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('pan_no');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="pan_id" name="pan_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <input type="text"
														id="pan_authority" name="pan_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="pan_date_of_authority"
														id="pan_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
														style="color: red;">* </strong> Pan No</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="pan_no" name="pan_no"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" onchange=" isPAN(this); "
														oninput="this.value = this.value.toUpperCase()"
														maxlength="10">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="p_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_pan_No_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_pan_No_remove();">
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionaa" href="#" id="j_final" onclick="divN(this)"><b>Class For Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1j" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('class_enroll');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="ce_authority" name="ce_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="ce_date_of_authority"
														id="ce_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong>Class for Enrollment
													</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="ce_id" name="ce_id" value="0"
														class="form-control-sm form-control autocomplete"
														autocomplete="off"> <select name="class_enroll"
														id="class_enroll" class="form-control"
														onchange="fn_domicile();">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getClass_enrollList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="gorkha_domicile"
											style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Domicile</label>
												</div>
												<div class="col-md-8">
													<select name="domicile" id="domicile" class="form-control">
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
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="ce_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_Class_Enro_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_Class_Enro_remove();">
			            	</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END Class Enrolment -->


<!--START Date of Enrollment-->
<c:if test="${ChangeOfDtofEnrolment != 0}">
	<form id="form_dt_of_enrollment">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="l_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="l_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="l_final" onclick="divN(this)"><b> Date of Enrollment</b></a>
						</h4>
					</div>
					<div id="collapse1l" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('dt_of_enrollment');" />
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="doe_authority" name="doe_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)"> <input
														type="hidden" id="doe_id" name="doe_id" value="0"
														class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="doe_date_of_authority"
														id="doe_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Date of Enrollment
													</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="enroll_dt" id="enroll_dt"
														maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="ed_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_date_of_Enro_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_date_of_Enro_remove();">
			            										           		<input type="hidden" id="min_date_enroll" name="min_date_enroll" class="form-control autocomplete"  > 
			            		
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
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordionbb" href="#" id="m_final" onclick="divN(this)"><b>  Date of Attestation</b></a>
						</h4>
					</div>
					<div id="collapse1m" class="panel-collapse collapse">
						<div class="card-body card-block">
							<br> <input type="button" class="btn btn-primary btn-sm" value="View History" onclick="Pop_Up_History('dt_of_attest');" />
							<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="doa_authority" name="doa_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)"> <input
														type="hidden" id="doa_id" name="doa_id" value="0"
														class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="doa_date_of_authority"
														id="doa_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Date of Attestation
													</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="date_of_attestation"
														id="date_of_attestation" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="att_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
							<div class="card-footer" align="center" class="form-control">
		              			<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="validate_date_of_Atte_save_fn();">
			            		<input type="button" class="btn btn-danger btn-sm" value="Delete" onclick="validate_date_of_Atte_remove();">
			            	</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!--END   Date of Attestation-->
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
								<br> <input type="button" class="btn btn-primary btn-sm"
									value="View History"
									onclick="Pop_Up_History('family_details');" />
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="fd_authority" name="fd_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)"> <input
														type="hidden" id="fd_id" name="fd_id" value="0"
														class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="fd_date_of_authority"
														id="fd_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
														style="color: red;">* </strong>Father's Name</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="father_name" name="father_name"
														class="form-control-sm form-control autocomplete"
														autocomplete="off">

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Father's Date of Birth</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="father_dob" id="father_dob"
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
														style="color: red;">* </strong>Father's Place of Birth</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="father_place_birth"
														name="father_place_birth"
														class="form-control-sm form-control autocomplete"
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
														style="color: red;">* </strong>Father In
														Service/Ex-Service</label>
												</div>
												<div class="col-md-8">
													<input type="radio" id="father_proff_radio1"
														name="father_proff_radio" value="yes"
														onchange="father_proffFn();">&nbsp <label
														for="yes">Yes</label>&nbsp <input type="radio"
														id="father_proff_radio2" name="father_proff_radio"
														value="no" onchange="father_proffFn();" checked="checked">&nbsp
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
											<div class="col-md-6" id="father_otherDiv"
												style="display: none">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">*</strong>Other</label>
													</div>
													<div class="col-md-8">
														<input type="text" id="father_other" name="father_other"
															class="form-control-sm form-control autocomplete"
															autocomplete="off"
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
															name="father_personal_no"
															class="form-control-sm form-control autocomplete"
															autocomplete="off"
															onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
															oninput="this.value = this.value.toUpperCase()"
															maxlength="15">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="father_proffDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Father's Profession</label>
												</div>
												<div class="col-md-8">
													<select name="father_profession" id="father_profession"
														class="form-control" onchange="fn_father_other();">
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
									<div class="col-md-12">
										<div class="col-md-6" id="father_proffotherDiv"
											style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">*</strong>Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="father_proffother"
														name="father_proffother" class="form-control autocomplete"
														autocomplete="off"
														oninput="this.value = this.value.toUpperCase()"
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
														style="color: red;">* </strong>Mother's Name</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_name" name="mother_name"
														class="form-control-sm form-control autocomplete"
														autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Mother's Date of Birth</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="mother_dob" id="mother_dob"
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
														style="color: red;">* </strong>Mother's Place of Birth</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_place_birth"
														name="mother_place_birth"
														class="form-control-sm form-control autocomplete"
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
														style="color: red;">* </strong>Mother In
														Service/Ex-Service</label>
												</div>
												<div class="col-md-8">
													<input type="radio" id="mother_proff_radio1"
														name="mother_proff_radio" value="yes"
														onchange="mother_proffFn();">&nbsp <label
														for="yes">Yes</label>&nbsp <input type="radio"
														id="mother_proff_radio2" name="mother_proff_radio"
														value="no" onchange="mother_proffFn();" checked="checked">&nbsp
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
											<div class="col-md-6" id="mother_otherDiv"
												style="display: none">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">*</strong>Other</label>
													</div>
													<div class="col-md-8">
														<input type="text" id="mother_other" name="mother_other"
															class="form-control-sm form-control autocomplete"
															autocomplete="off"
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
															name="mother_personal_no"
															class="form-control-sm form-control autocomplete"
															autocomplete="off"
															onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
															oninput="this.value = this.value.toUpperCase()"
															maxlength="15">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="mother_proffDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Mother's Profession</label>
												</div>
												<div class="col-md-8">
													<select name="mother_profession" id="mother_profession"
														class="form-control" onchange="fn_mother_other();">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getProfession}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="mother_otherproffDiv"
											style="display: none">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">*</strong>Other</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="mother_proffother"
														name="mother_proffother" class="form-control autocomplete"
														autocomplete="off"
														oninput="this.value = this.value.toUpperCase()"
														maxlength="50">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="f_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<input type="button" class="btn btn-primary btn-sm"
										value="Update" onclick="validate_Family_save_fn();"> <input
										type="button" class="btn btn-danger btn-sm" value="Delete"
										onclick="validate_family_remove();">
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
								<br> <input type="button" class="btn btn-primary btn-sm"
									value="View History"
									onclick="Pop_Up_History('sibling_details');" />
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="ds_authority" name="ds_authority"
														class="form-control-sm form-control autocomplete"
														autocomplete="off" maxlength="100"
														onkeyup="return specialcharecter(this)"> <input
														type="hidden" id="ds_id" name="ds_id" value="0"
														class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="ds_date_of_authority"
														id="ds_date_of_authority" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control-sm form-control"
														style="width: 93%; display: inline;"
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
													<label class=" form-control-label"> Reject Remarks</label>
												</div>
												<div class="col-md-8">
													<label class=" form-control-label" id="sb_reject_remarks"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12 watermarked" style="display: block;">
										<table id="family_table" class="table-bordered "
											style="margin-top: 10px; width: 100%;">

											<thead style="color: white; text-align: center;">
												<tr>
													<th>Sr No</th>
													<th>Name</th>
													<th>Date of Birth</th>
													<th>Relationship</th>
													<th>Aadhar No</th>
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
													<td><input type="text" id="sib_name1" name="sib_name1"
														class="form-control autocomplete"
														onkeypress="return onlyAlphabets(event);"
														oninput="this.value = this.value.toUpperCase()"
														autocomplete="off"></td>
													<td><input type="text" name="sib_date_of_birth1"
														id="sib_date_of_birth1" maxlength="10"
														onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control" style="width: 85%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);" value="DD/MM/YYYY"></td>
													<td><select name="sib_relationship1"
														id="sib_relationship1"
														class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getFamily_siblings}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
													</select></td>

													<td><input type="text" id="sib_adhar_number1"
														name="sib_adhar_number1" class="form-control autocomplete"
														maxlength="14" onkeypress="return isAadhar(this,event); "
														autocomplete="off"></td>

													<td><input type="text" id="sib_pan_no1"
														name="sib_pan_no1" class="form-control autocomplete"
														autocomplete="off" onchange=" isPAN(this); "
														oninput="this.value = this.value.toUpperCase()"
														maxlength="10"></td>

													<td style="display: none;"><input type="text"
														id="sib_ch_id1" name="sib_ch_id1" value="0"
														class="form-control autocomplete" autocomplete="off"></td>
													<td><input type="checkbox" id="ser-ex1" name="ser-ex1"
														value="Yes" onclick="siblingCB(1);"></td>
													<td><select name="sibling_service1"
														id="sibling_service1" onchange="otherfunction(1)"
														class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getExservicemenList}"
																varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
													</select></td>
													<td><input type="text" id="sibling_personal_no1"
														name="sibling_personal_no1"
														class="form-control autocomplete" autocomplete="off"
														maxlength="15"
														onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
														oninput="this.value = this.value.toUpperCase()"></td>
													<td><input type="text" id="other_sibling_ser1"
														name="other_sibling_ser1"
														class="form-control autocomplete" autocomplete="off"
														onkeypress="return onlyAlphaNumeric(event);"
														oninput="this.value = this.value.toUpperCase()"></td>
													<td class="hide-action"><a
														class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
														id="family_save1" onclick="family_save_fn(1);"><i
															class="fa fa-save"></i></a> <a style="display: none;"
														class="btn btn-success btn-sm" value="ADD" title="ADD"
														id="family_add1" onclick="family_add_fn(1);"><i
															class="fa fa-plus"></i></a> <a style="display: none;"
														class="btn btn-danger btn-sm" value="REMOVE"
														title="REMOVE" id="family_remove1"
														onclick="family_remove_fn(1);"><i class="fa fa-trash"></i></a></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
<!--END Details of Sibilings-->
	<div class="card-footer" align="center" class="form-control">
							 <input id="btnsaveAll" type="button" class="btn btn-primary btn-sm" value="Submit" onclick="SubmitAll();">		              
			            </div>
</div>

<c:url value="GetS_C_LData_JCO" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="army_no1">
		<input type="hidden" name="army_no1" id="army_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="icstatus" id="icstatus" value="0" />
		
	</form:form> 
	
	
<script>

$(document).ready(function() {
	$("#personnel_noV").val('${personnel_no7}');
		$("#statusV").val('${status7}');
		$("#rankV").val('${rank7}');
		$("#jco_idV").val('${jco_id7}');
		$("#unit_nameV").val('${unit_name7}');
		$("#unit_sus_noV").val('${unit_sus_no7}');
		 	
		
		$('#event').val('${event}');
	 	$('#jco_id').val('${jco_id}');
	 	$('#census_id').val('${census_id}');
	  	$('#personnel_no').val('${personnel_no2}');
	  	 $("input#personnel_no").attr('readonly', true);

	  	 personal_number();
	  	  $("#submit_a").hide();
});
	  	  
</script>


<script type="text/javascript">

var app_status=3;
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
				get_Gender();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_Birth();
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
				get_Birth_Address();
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				get_nationality();
			}
		}
		else if (obj.id == "e_final") {
			if(!hasC){
				get_Mother_Tongue();
			}
		}
		else if (obj.id == "f_final") {
			if(!hasC){
				get_Blood_Group();
			}
		}
		else if (obj.id == "g_final") {
			if(!hasC){
				get_height();
			}
		}
		else if (obj.id == "h_final") {
			if(!hasC){
				get_aadhar_no();
			}
		}
		else if (obj.id == "i_final") {
			if(!hasC){
				get_pan();
			}
		}
		else if (obj.id == "j_final") {
			if(!hasC){
				get_class_enrollment();
			}
		}
// 		else if (obj.id == "k_final") {
// 			if(!hasC){
// 				get_rank();
// 			}
// 		}
		else if (obj.id == "l_final") {
			if(!hasC){
				get_EnrollDate();
			}
		}
		else if (obj.id == "m_final") {
			if(!hasC){
				get_AttestationDate();
			}
		}
		else if (obj.id == "n_final") {
			if(!hasC){
				get_family_details();
			}
		}
		else if (obj.id == "o_final") {
			if(!hasC){
				getfamily_siblingDetails();
				colAdj("family_table");
			}
		}
		
		}
</script>

<script>
//************************************* START GENDER*****************************//
function get_Gender(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getGenderData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#g_id').val(j[i].id);
				 $("#g_authority").val(j[i].authority);
				 $("#g_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#gender").val(j[i].gender);
				 $("#g_reject_remarks").text(j[i].reject_remarks);
				 
			}
	  });
}	


function validate_gender_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#g_id').val();
     $.post('Gender_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                          $('#g_id').val(0);
         				 $("#g_authority").val('');
         				 $("#g_date_of_authority").val('');
         				 $("#gender").val('');        				
         				$('#form_gender').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Gender_save_fn(){
	 if ($("input#g_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#g_authority").focus();
	 		return false;
		}
	 if ($("input#g_date_of_authority").val().trim() == "") {
			alert("Please Enter Date of Authority");
			$("input#g_date_of_authority").focus();
	 		return false;
		}
	 if ($("select#gender").val() == "0") {
			alert("Please Select Gender");
			$("select#gender").focus();
	 		return false;
		}
	 	
	var jco_id=$('#jco_id').val();
	var gender=$('#gender').val();
	var g_id=$('#g_id').val();
	var formdata=$('#form_gender').serialize();
	
	formdata+="&jco_id="+jco_id+"&gender="+gender+"&g_id="+g_id+"&app_status="+app_status;		
	   $.post('Census_Gender_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#g_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END GENDER*****************************//


//************************************* START DOB*****************************//
function get_Birth(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getdobData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#birth_id').val(j[i].id);
				 $("#b_authority").val(j[i].authority);
				 $("#b_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#date_of_birth").val(ParseDateColumncommission(j[i].date_of_birth));
				 $("#b_reject_remarks").text(j[i].reject_remarks);
			}
	  });
}	


function validate_DOB_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#birth_id').val();
     $.post('DOB_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                          $('#birth_id').val(0);
         				 $("#b_authority").val('');
         				 $("#b_date_of_authority").val('');
         				 $("#date_of_birth").val('');
         				$('#form_Birth').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_DOB_save_fn(){
	 if ($("input#b_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#b_authority").focus();
	 		return false;
		}
	 if ($("input#b_date_of_authority").val().trim() == "") {
			alert("Please Enter Date of Authority");
			$("input#b_date_of_authority").focus();
	 		return false;
		}
	 if($("input#date_of_birth").val().trim() == "" || $("input#date_of_birth").val().trim() == "DD/MM/YYYY"){
			alert("Please Select Date of Birth ");
			$("input#date_of_birth").focus();
			return false;
		}
	 	
	var jco_id=$('#jco_id').val();
	var date_of_birth=$('#date_of_birth').val();
	var birth_id=$('#birth_id').val();
	var formdata=$('#form_Birth').serialize();
	
	formdata+="&jco_id="+jco_id+"&date_of_birth="+date_of_birth+"&birth_id="+birth_id+"&app_status="+app_status;		
	   $.post('Birth_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#birth_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END DOB*****************************//


//************************************* START Address Of Birth*****************************//
function get_Birth_Address(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getAddBirData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#addr_ch_id').val(j[i].id);
				 $("#addr_authority").val(j[i].authority);
				 $("#addr_date_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#place_of_birth").val(j[i].place_of_birth);
				 $("#country_of_birth").val(j[i].country_of_birth);
				 $("#state_of_birth").val(j[i].state_of_birth);
				 $("#district_of_birth").val(j[i].district_of_birth);
				 $("#ab_reject_remarks").text(j[i].reject_remarks);
				 
				 var text6 = $("#country_of_birth option:selected").text();
					if(text6 == "OTHERS"){
						$("#country_other").val(j[0].country_other);
						$("#con_other_id").show();
					}
					else{
						$("#con_other_id").hide();
					}
					
					var text7 = $("#state_of_birth option:selected").text();
					if(text7 == "OTHERS"){
						$('#state_other').val(j[0].state_other);
						$("#sta_other_st").show();
					}
					else{
						$("#sta_other_st").hide();
					}
					
					var text8 = $("#district_of_birth option:selected").text();
					if(text8 == "OTHERS"){
						$("#district_other").val(j[0].district_other);
						$("#other_dist").show();
					}
					else{
						$("#other_dist").hide();
					}
				
			}
	  });
}	


function validate_Add_Birth_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#addr_ch_id').val();
     $.post('ADD_Birth_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#addr_ch_id').val(0);
         				 $("#addr_authority").val('');
         				 $("#addr_date_authority").val('');
         				 $("#place_of_birth").val('');
         				 $("#country_of_birth").val('');
         				 $("#state_of_birth").val('');
         				 $("#district_of_birth").val('');
         				 
         				 $("#country_other").val('');
        				 $("#con_other_id").hide();
        			
        				 $('#state_other').val('');
        				 $("#sta_other_st").hide();
        		
        				 $("#district_other").val('');
        				 $("#other_dist").hide();
        				 $('#form_addr_details_form').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Add_Birth_save_fn(){
	 if ($("input#addr_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#addr_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#addr_date_authority").val().trim() == "") {
	 		alert("Please Enter Date of Authority");
	 		$("input#addr_date_authority").focus();
	 		return false;
	 	} 
	 	 if($("input#place_of_birth").val().trim() == "") {
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
	var jco_id=$('#jco_id').val();
	var place_of_birth=$('#place_of_birth').val();
	var country_of_birth=$('#country_of_birth').val();
	var state_of_birth=$('#state_of_birth').val();
	var district_of_birth=$('#district_of_birth').val();
	var country_other=$('#country_other').val();
	var state_other=$('#state_other').val();
	var district_other=$('#district_other').val();
	var addr_ch_id=$('#addr_ch_id').val();
	var formdata=$('#form_addr_details_form').serialize();
	
	formdata+="&jco_id="+jco_id+"&place_of_birth="+place_of_birth+"&country_of_birth="+country_of_birth+"&state_of_birth="+state_of_birth+"&district_of_birth="+district_of_birth
	+"&country_other="+country_other+"&state_other="+state_other+"&district_other="+district_other+"&addr_ch_id="+addr_ch_id+"&app_status="+app_status;		
	   $.post('Address_Birth_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#addr_ch_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Address Of Birth*****************************//


//************************************* START Nationality*****************************//
function get_nationality(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getNationalityData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#n_id').val(j[i].id);
				 $("#n_authority").val(j[i].authority);
				 $("#n_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $('#nationality').val(j[i].nationality);
				 $('#n_reject_remarks').text(j[i].reject_remarks);
	 				
	 				var text6 = $("#nationality option:selected").text();
					if(text6 == "OTHERS"){
						$("#nationality_other").val(j[0].nationality_other);
						$("#NA_other_nati").show();
					}
					else{
						$("#NA_other_nati").hide();
					}
			}
	  });
}	


function validate_Nationality_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#n_id').val();
     $.post('Nationality_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                         alert("Data Deleted Successfully");
                         $('#n_id').val(0);
         				 $("#n_authority").val('');
         				 $("#n_date_of_authority").val('');
         				 $("#nationality").val('');   
         				 $("#nationality_other").val('');
       					 $("#NA_other_nati").hide();
         				 $('#form_nationality').hide();
                      	 }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Nationality_save_fn(){

	if ($("input#n_authority").val().trim() == "") {
 		alert("Please Enter Authority");
 		$("input#n_authority").focus();
 		return false;
 	}  
 	if ($("input#n_date_of_authority").val().trim() == "") {
 		alert("Please Enter Date of Authority");
 		$("input#n_date_of_authority").focus();
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
	}
	var jco_id=$('#jco_id').val();
	var nationality=$('#nationality').val();
	var nationality_other=$('#nationality_other').val();
	var n_id=$('#n_id').val();
	var formdata=$('#form_nationality').serialize();
	
	formdata+="&jco_id="+jco_id+"&nationality="+nationality+"&n_id="+n_id+"&nationality_other="+nationality_other+"&app_status="+app_status;		
	   $.post('Nationality_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#n_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Nationality*****************************//


//************************************* START Mother Tongue*****************************//
function get_Mother_Tongue(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getMother_TongueData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#m_authority').val(j[i].authority);
 				$('#m_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#mother_tounge').val(j[i].mother_tounge);
 				$('#mt_reject_remarks').text(j[i].reject_remarks);
 				
 				var text7 = $("#mother_tounge option:selected").text();
				if(text7 == "OTHERS"){
					$('#mother_tongue_other').val(j[i].mother_tongue_other);
					$("#motherton_other").show();
				}
				else{
					$("#motherton_other").hide();
				}
				
 				$("#m_id").val(j[i].id);
			}
	  });
}	


function validate_Mother_Tongue_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#m_id').val();
     $.post('Mother_Tongue_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                         alert("Data Deleted Successfully");
                         $('#m_id').val(0);
         				 $("#m_authority").val('');
         				 $("#m_date_of_authority").val('');
         				 $("#mother_tounge").val('');   
         				 $("#mother_tongue_other").val('');
       					 $("#motherton_other").hide();
         				 $('#form_Mother_Tongue').hide();
                      	 }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Mother_Tongue_save_fn(){

	if ($("input#m_authority").val().trim() == "") {
 		alert("Please Enter Authority");
 		$("input#m_authority").focus();
 		return false;
 	}  
 	if ($("input#m_date_of_authority").val().trim() == "") {
 		alert("Please Enter Date of Authority");
 		$("input#m_date_of_authority").focus();
 		return false;
 	} 
 	if ($("select#mother_tounge").val() == "0") {
		alert("Please Select Mother Tongue");
		$("select#mother_tounge").focus();
		return false;
	}
	var text13 = $("#mother_tongue option:selected").text();
	
	if(text13.toUpperCase() == "OTHERS" && $("input#mother_tongue_other").val().trim() == ""){
		alert("Please Enter Mother Tongue Other");
		$("input#mother_tongue_other").focus();
		return false;
	}
	var jco_id=$('#jco_id').val();
	var mother_tounge=$('#mother_tounge').val();
	var mother_tongue_other=$('#mother_tongue_other').val();
	var m_id=$('#m_id').val();
	var formdata=$('#form_Mother_Tongue').serialize();
	
	formdata+="&jco_id="+jco_id+"&mother_tounge="+mother_tounge+"&m_id="+m_id+"&mother_tongue_other="+mother_tongue_other+"&app_status="+app_status;		
	   $.post('Mother_Tongue_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#m_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Mother Tongue*****************************//


//************************************* START Blood Group*****************************//
function get_Blood_Group(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getBlood_GroupData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#bg_authority').val(j[i].authority);
 				$('#bg_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#blood_group').val(j[i].blood_group);
 				$("#bg_id").val(j[i].id);
 				$('#bg_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_Blood_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#bg_id').val();
     $.post('Blood_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                          $('#bg_id').val(0);
         				 $("#bg_authority").val('');
         				 $("#bg_date_of_authority").val('');
         				 $("#blood_group").val('');        				
         				$('#form_Blood_Group').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Blood_save_fn(){
	 
	 if ($("input#bg_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#bg_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#bg_date_of_authority").val().trim() == "") {
	 		alert("Please Enter Date of Authority");
	 		$("input#bg_date_of_authority").focus();
	 		return false;
	 	} 
	 	if ($("select#blood_group").val() == "0") {
	 		alert("Please Select Blood Group");
	 		$("select#blood_group").focus();
	 		return false;
	 	} 
	var jco_id=$('#jco_id').val();
	var blood_group=$('#blood_group').val();
	var bg_id=$('#bg_id').val();
	var formdata=$('#form_Blood_Group').serialize();
	
	formdata+="&jco_id="+jco_id+"&blood_group="+blood_group+"&bg_id="+bg_id+"&app_status="+app_status;		
	   $.post('Blood_Group_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#bg_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Blood Group*****************************//

//************************************* START Height*****************************//
function get_height(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getHeightData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#h_authority').val(j[i].authority);
 				$('#h_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#height').val(j[i].height);
 				$("#h_id").val(j[i].id);
 				$('#h_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_Height_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#h_id').val();
     $.post('Height_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#h_id').val(0);
         				 $("#h_authority").val('');
         				 $("#h_date_of_authority").val('');
         				 $("#height").val('');        				
         				 $('#form_Height').hide();
                       }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Height_save_fn(){
	 
	 if ($("input#h_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#h_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#h_date_of_authority").val().trim() == "") {
	 		alert("Please Enter Date of Authority");
	 		$("input#h_date_of_authority").focus();
	 		return false;
	 	} 
	 	if ($("select#height").val() == "0") {
	 		alert("Please Select Height");
	 		$("select#height").focus();
	 		return false;
	 	} 
	var jco_id=$('#jco_id').val();
	var height=$('#height').val();
	var h_id=$('#h_id').val();
	var formdata=$('#form_Height').serialize();
	
	formdata+="&jco_id="+jco_id+"&height="+height+"&h_id="+h_id+"&app_status="+app_status;		
	   $.post('Height_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#h_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Height*****************************//


//************************************* START Aadhar No*****************************//
function get_aadhar_no(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getAadharData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#aa_authority').val(j[i].authority);
 				$('#aa_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				var aadhar = j[i].aadhar_no +"";
 				
 				$('#adhar_number1').val(aadhar.substring(0, 4));
 				$('#adhar_number2').val(aadhar.substring(4, 8));
 				$('#adhar_number3').val(aadhar.substring(8, 12));
 				$("#aa_id").val(j[i].id);
 				$('#ad_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_add_No_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#aa_id').val();
     $.post('Add_No_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#aa_id').val(0);
         				 $("#aa_authority").val('');
         				 $("#aa_date_of_authority").val('');
         				var aadhar = j[i].aadhar_no +"";
         				
         				$('#adhar_number1').val('');
         				$('#adhar_number2').val('');
         				$('#adhar_number3').val('');    				
         				 $('#form_aadhar_no').hide();
                       }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_add_No_save_fn(){
	 
	if ($("input#aa_authority").val().trim() == "") {
 		alert("Please Enter Authority");
 		$("input#aa_authority").focus();
 		return false;
 	}  
 	if ($("input#aa_date_of_authority").val().trim() == "") {
 		alert("Please Enter Date of Authority");
 		$("input#aa_date_of_authority").focus();
 		return false;
 	} 
 	var aadhar1 = $("input#adhar_number1").val();
    var aadhar2 = $("input#adhar_number2").val();
    var aadhar3 = $("input#adhar_number3").val();
	 
		if(aadhar1.trim()=="" || aadhar1.length < 4) {
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
	var jco_id=$('#jco_id').val();
	var adhar_number1=$('#adhar_number1').val();
	var adhar_number2=$('#adhar_number2').val();
	var adhar_number3=$('#adhar_number3').val();
	var aa_id=$('#aa_id').val();
	var formdata=$('#form_aadhar_no').serialize();
	
	formdata+="&jco_id="+jco_id+"&adhar_number1="+adhar_number1+"&adhar_number2="+adhar_number2+"&adhar_number3="+adhar_number3+"&aa_id="+aa_id+"&app_status="+app_status;		
	   $.post('Aadhar_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#aa_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Aadhar No*****************************//


//************************************* START Pan No*****************************//
function get_pan(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getPanNoData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#pan_authority').val(j[i].authority);
 				$('#pan_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#pan_no').val(j[i].pan_no);
 				$("#pan_id").val(j[i].id);
 				$('#p_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_pan_No_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#pan_id').val();
     $.post('Pan_No_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#pan_id').val(0);
         				 $("#pan_authority").val('');
         				 $("#pan_date_of_authority").val('');
         				 $("#pan_no").val('');        				
         				 $('#form_pan_no').hide();
                       }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_pan_No_save_fn(){
	 
	 if ($("input#pan_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#pan_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#pan_date_of_authority").val().trim() == "") {
	 		alert("Please Enter Date of Authority");
	 		$("input#pan_date_of_authority").focus();
	 		return false;
	 	} 
	 	var pan_no=$('#pan_no').val();		
		if(pan_no.trim()=="") {
		
			alert("Please Enter PAN Number");
			$('#pan_no').focus();
			return false
		}
	var jco_id=$('#jco_id').val();
	var pan_no=$('#pan_no').val();
	var pan_id=$('#pan_id').val();
	var formdata=$('#form_pan_no').serialize();
	
	formdata+="&jco_id="+jco_id+"&pan_no="+pan_no+"&pan_id="+pan_id+"&app_status="+app_status;		
	   $.post('Pan_no_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#pan_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Pan No*****************************//


//************************************* START Class Of Enrolment*****************************//
function get_class_enrollment(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getEnrollClassData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#ce_authority').val(j[i].authority);
 				$('#ce_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#class_enroll').val(j[i].class_enroll);
 				fn_domicile();
 				$('#domicile').val(j[i].domicile);
 				$("#ce_id").val(j[i].id);
 				$('#ce_reject_remarks').text(j[i].reject_remarks);
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

function validate_Class_Enro_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#ce_id').val();
     $.post('Class_Enro_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#ce_id').val(0);
         				 $("#ce_authority").val('');
         				 $("#ce_date_of_authority").val('');
         				 $("#class_enroll").val('');        				
         				 $('#form_class_enrolment').hide();
                       }else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Class_Enro_save_fn(){
	 
	 if ($("input#ce_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#ce_authority").focus();
	 		return false;
		}
	  if ($("input#ce_date_of_authority").val().trim() == "") {
			alert("Please Enter Date of Authority");
			$("input#ce_date_of_authority").focus();
	 		return false;
		}
	  if ($("select#class_enroll").val().trim() == "0") {
			alert("Please Select Class For Enrolment");
			$("select#class_enroll").focus();
	 		return false;
		}
	  if($("#class_enroll").val() == "10"){
			if ($("select#domicile").val() == "0") {
				alert("Please Select Domicile ");
				$("select#domicile").focus();
				return false;
		}
	  } 
	    
	var jco_id=$('#jco_id').val();
	var class_enroll=$('#class_enroll').val();
	var ce_id=$('#ce_id').val();
	var formdata=$('#form_class_enrolment').serialize();
	
	formdata+="&jco_id="+jco_id+"&class_enroll="+class_enroll+"&ce_id="+ce_id+"&app_status="+app_status;		
	   $.post('Enroll_Class_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#ce_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Class Of Enrolment*****************************//


//************************************* START Date Of Enrolment*****************************//
function get_EnrollDate(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getDt_of_EnrolmentData_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#doe_authority').val(j[i].authority);
 				$('#doe_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#enroll_dt').val(ParseDateColumncommission(j[i].date_of_enrollment));
 				$("#doe_id").val(j[i].id);
 				$('#ed_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_date_of_Enro_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#doe_id').val();
     $.post('Dt_Of_Enroll_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                          $('#doe_id').val(0);
         				 $("#doe_authority").val('');
         				 $("#doe_date_of_authority").val('');
         				 $("#enroll_dt").val('');
         				$('#form_dt_of_enrollment').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_date_of_Enro_save_fn(){
	
	if ($("input#doe_authority").val().trim() == "") {
 		alert("Please Enter Authority");
 		$("input#doe_authority").focus();
 		return false;
 	}  
 	if ($("input#doe_date_of_authority").val().trim() == "") {
 		alert("Please Enter Date of Authority");
 		$("input#doe_date_of_authority").focus();
 		return false;
 	} 
//  	if ($("input#enroll_dt").val().trim() == "" || $("#enroll_dt").val() == "DD/MM/YYYY") {
// 		alert("Please Select Date of Enrollment");
// 		$("input#enroll_dt").focus();
// 		return false;
//  }
 	
// 	if ($("input#enroll_dt").val() == "" || $("#enroll_dt").val() == "DD/MM/YYYY") {
//		alert("Please Select Date of Enrollment");
//		$("input#enroll_dt").focus();
//		return false;
 //}else if ($("input#enroll_dt").val() == "" || $("#enroll_dt").val() == "DD/MM/YYYY" || new Date($("#enroll_dt").val()) < new Date($("#min_date_enroll").val())){
//	 alert("Please Select Date of Enrollment Grater than Date of Rank and Date of TOS");
//		$("input#enroll_dt").focus();
//		return false;
 //}
	var jco_id=$('#jco_id').val();
	var enroll_dt=$('#enroll_dt').val();
	var doe_id=$('#doe_id').val();
	var formdata=$('#form_dt_of_enrollment').serialize();
	
	formdata+="&jco_id="+jco_id+"&enroll_dt="+enroll_dt+"&doe_id="+doe_id+"&app_status="+app_status;		
	   $.post('Enroll_Date_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#doe_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Date Of Enrolment*****************************//


//************************************* START Date Of Attestion*****************************//
function get_AttestationDate(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getDt_of_Attestation_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#doa_authority').val(j[i].authority);
 				$('#doa_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#date_of_attestation').val(ParseDateColumncommission(j[i].date_of_attestation));
 				$("#doa_id").val(j[i].id);
 				$('#att_reject_remarks').text(j[i].reject_remarks);
			}
	  });
}	


function validate_date_of_Atte_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#doa_id').val();
     $.post('Dt_Of_Attes_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                          $('#doa_id').val(0);
         				 $("#doa_authority").val('');
         				 $("#doa_date_of_authority").val('');
         				 $("#date_of_attestation").val('');
         				$('#form_dt_of_Attestation').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_date_of_Atte_save_fn(){
	
	 if ($("input#doa_authority").val().trim() == "") {
	 		alert("Please Enter Authority");
	 		$("input#doa_authority").focus();
	 		return false;
	 	}  
	 	if ($("input#doa_date_of_authority").val().trim() == "") {
	 		alert("Please Enter Date of Authority");
	 		$("input#doa_date_of_authority").focus();
	 		return false;
	 	} 
	 	if ($("input#date_of_attestation").val().trim() == "" || $("#date_of_attestation").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Attestation");
			$("input#date_of_attestation").focus();
			return false;
         }
	var jco_id=$('#jco_id').val();
	var date_of_attestation=$('#date_of_attestation').val();
	var doa_id=$('#doa_id').val();
	var formdata=$('#form_dt_of_Attestation').serialize();
	
	formdata+="&jco_id="+jco_id+"&date_of_attestation="+date_of_attestation+"&doa_id="+doa_id+"&app_status="+app_status;		
	   $.post('Attestation_Date_Action_JCO?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	
	        	 $('#doa_id').val(data);
	        	  alert("Data Saved Successfully")
	          }
	          else
	        	  alert(data);
	    		          
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************END Date Of Attestion*****************************//


//************************************* START Family Details*****************************//
function get_family_details(){
	
	 var jco_id = '${jco_id}';
	 var i=0;
	  $.post('getFamily_JCO3?' + key + "=" + value, {jco_id:jco_id }, function(j){
			
			if(j!=0){
				$('#fd_authority').val(j[i].authority);
 				$('#fd_date_of_authority').val(ParseDateColumncommission(j[i].date_of_authority));
 				$('#father_name').val(j[i].father_name);
 				$('#father_dob').val(ParseDateColumncommission(j[i].father_dob));
 				$('#father_place_birth').val(j[i].father_place_birth);
 				$('#father_proff_radio').val(j[i].father_proff_radio);
 				$('#father_profession').val(j[i].father_profession);
 				$('#father_service').val(j[i].father_service);
 				$('#father_personal_no').val(j[i].father_personal_no);
 				$('#mother_name').val(j[i].mother_name);
 				$('#mother_dob').val(ParseDateColumncommission(j[i].mother_dob));
 				$('#mother_place_birth').val(j[i].mother_place_birth);
 				$('#mother_profession').val(j[i].mother_profession);
 				$('#mother_service').val(j[i].mother_service);
 				$('#mother_proff_radio').val(j[i].mother_proff_radio);
 				$('#mother_personal_no').val(j[i].mother_personal_no);
 				$('#f_reject_remarks').text(j[i].reject_remarks);
 				var text6 = $("#father_profession option:selected").text();
				if(text6 == "OTHERS"){
					$("#father_proffother").val(j[i].father_proffother);
					$("#father_proffotherDiv").show();
				}
				else{
					$("#father_proffotherDiv").hide();
				}
				
				var text8 = $("#mother_profession option:selected").text();
				if(text8 == "OTHERS"){
					$("#mother_proffother").val(j[i].mother_proffother);
					$("#mother_otherproffDiv").show();
				}
				else{
					$("#mother_otherproffDiv").hide();
				}
 				$("#fd_id").val(j[i].id);
			}
	  });
}	


function validate_family_remove(){
	   var rc = confirm("Are You Sure! You Want To Delete");
    if(rc){
    var id=$('#fd_id').val();
     $.post('Family_JCOdelete_action?' + key + "=" + value, {id:id}, function(data){ 
                      if(data=='1'){
                          alert("Data Deleted Successfully");
                         $('#fd_id').val(0);
         				 $("#fd_authority").val('');
         				 $("#fd_date_of_authority").val('');
         				 $("#father_name").val('');
         				 $("#father_dob").val('');
         				 $("#father_place_birth").val('');
         				 $("#father_proff_radio").val('');
         				 $("#father_profession").val('');
         				 $("#father_service").val('');
         				 $("#father_personal_no").val('');
         				 $("#mother_name").val('');
         				 $("#mother_dob").val('');
         				 $("#mother_place_birth").val('');
         				 $("#mother_profession").val('');
         				 $("#mather_proff_radio").val('');
         				 $("#mother_service").val('');
         				 $("#mother_personal_no").val('');
         				 $("#father_proffother").val('');
       					 $("#father_proffotherDiv").hide();
       					 $("#mother_proffother").val('');
       					 $("#mother_otherproffDiv").hide();
         				 $('#form_family_details').hide();
                      }
                       else{
                           alert("Data not Deleted ");
                        }
                      
    }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
     });
   }	
	}
	
function validate_Family_save_fn(){
	
	 var dt_of_birth = $("#date_of_birth").val();
	 dt_of_birth1 =dt_of_birth.substring(6,10);
    

	
	var fa_dob  = $("#father_dob").val();
	father_dob1 =fa_dob.substring(6,10);
	
	var mo_dob  = $("#mother_dob").val();
	mother_dob1 =mo_dob.substring(6,10);
	
  
 if ($("input#fd_authority").val().trim() == "") {
 		alert("Please Enter Authority");
 		$("input#fd_authority").focus();
 		return false;
 	}  
 	if ($("input#fd_date_of_authority").val().trim() == "" || $("input#fd_date_of_authority").val().trim() == "DD/MM/YYYY") {
 		alert("Please Enter Date of Authority");
 		$("input#fd_date_of_authority").focus();
 		return false;
 	}
  
 	if($("input#father_name").val().trim() == "") {
		alert("Please Enter Father's Name ");
		$("input#father_name").focus();
		return false;
	}

      if ($("input#father_dob").val() == "" || $("#father_dob").val() == "DD/MM/YYYY") {
		alert("Please Select Father's Date of Birth");
		$("input#father_dob").focus();
		return false;
	}
       
  		if ($("input#father_place_birth").val() == "") {
  			alert("Please Enter Father's Place of Birth");
  			$("input#father_place_birth").focus();
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
				if($("#father_other").val()==""){
					alert("please Enter Other Service");
					$("#father_other").focus();
					return false;
				}
			}
			if($("#father_service option:selected").text() == "OTHER"){
				if($("#father_other").val().trim() ==""){
					alert("please Enter Father Other");
					$("#father_other").focus();
					return false;
				}
			
		}
			if($("#father_service option:selected").text() != "OTHER"){
				if($("#father_personal_no").val().trim() ==""){
					alert("please Enter Father Personal No");
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
		if($("input#mother_name").val().trim() == "") {
			alert("Please Enter Mother's Name");
			$("input#mother_name").focus();
			return false;
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

		if($("#mother_service option:selected").text() == "OTHER"){
			if($("#mother_other").val().trim() ==""){
				alert("please Enter Mother Other");
				$("#mother_other").focus();
				return false;
			}

		}
		if($("#mother_service option:selected").text() != "OTHER"){
			if($("#mother_personal_no").val().trim() ==""){
				alert("please Enter Mother Personal No");
				$("#mother_personal_no").focus();
				return false;
			}
		
		}

	}else{
	if($("#mother_profession").val() == "0") {
		alert("Please Select Mother's Profession ");
		$("#mother_profession").focus();
		return false;
	}
	var m_proff = $("#mother_profession option:selected").text();
		if(m_proff == "OTHERS"){
			if($("#mother_proffother").val().trim() ==""){
				alert("Please Enter Mother's Profession Other ");
				$("#mother_proffother").focus();
				return false;
			}
		}
	} 

		var jco_id = $('#jco_id').val();
		var father_name = $('#father_name').val();
		var father_dob = $('#father_dob').val();
		var father_place_birth = $('#father_place_birth').val();
		var father_proff_radio = $('#father_proff_radio').val();
		var father_profession = $('#father_profession').val();
		var father_service = $('#father_service').val();
		var father_personal_no = $('#father_personal_no').val();
		var mother_name = $('#mother_name').val();
		var mother_dob = $('#mother_dob').val();
		var mother_place_birth = $('#mother_place_birth').val();
		var mother_profession = $('#mother_profession').val();
		var mather_proff_radio = $('#mather_proff_radio').val();
		var mother_service = $('#mother_service').val();
		var mother_personal_no = $('#mother_personal_no').val();
		var father_proffother = $('#father_proffother').val();
		var mother_proffother = $('#mother_proffother').val();
		var fd_id = $('#fd_id').val();

		var formdata = $('#form_family_details').serialize();

		formdata += "&jco_id=" + jco_id + "&father_name=" + father_name
				+ "&father_dob=" + father_dob + "&father_place_birth="
				+ father_place_birth + "&father_proff_radio="
				+ father_proff_radio + "&father_profession="
				+ father_profession + "&father_profession=" + father_profession
				+ "&father_service=" + father_service + "&father_personal_no="
				+ father_personal_no + "&mother_name=" + mother_name
				+ "&mother_dob=" + mother_dob + "&mother_place_birth="
				+ mother_place_birth + "&mother_profession="
				+ mother_profession + "&mather_proff_radio="
				+ mather_proff_radio + "&mother_service=" + mother_service
				+ "&mother_personal_no=" + mother_personal_no
				+ "&father_proffother=" + father_proffother
				+ "&mother_proffother=" + mother_proffother + "&fd_id=" + fd_id
				+ "&app_status=" + app_status;

		$.post('Family_Details_Action_JCO?' + key + "=" + value, formdata,
				function(data) {
					if (data == "update")
						alert("Data Updated Successfully");
					else if (parseInt(data) > 0) {

						$('#fd_id').val(data);
						alert("Data Saved Successfully")
					} else
						alert(data);

				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	//*************************************END Family Details*****************************//

	//************************************* START Details Of Sibling*****************************//
sib = 1;
	sib_srno = 1;

	function family_add_fn(q) {
		if($('#family_add' + q).length) {
			$("#family_add" + q).hide();
		}
		if(q != 0) sib_srno += 1;
		sib = q + 1;
		$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' + '<td> <input type="sib_name' + sib + '" id="sib_name' + sib + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control-sm form-control"   >'
			
			
			+ '<td>'
			
			+ ' <input type="text" name="sib_date_of_birth' + sib + '" id="sib_date_of_birth' + sib + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>'
			+ '<td> <select name="sib_relationship' + sib + '" id="sib_relationship' + sib + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>'
			+ '<td><input type="text" ' + '	id="sib_adhar_number' + sib + '" name="sib_adhar_number' + sib + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off"></td> ' 
			+ '<td> ' + '	<input type="text" id="sib_pan_no' + sib + '" name="sib_pan_no' + sib + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' 
			+ '<td style="display:none;"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' 

			+ '<td><input type="checkbox" ' + '	id="ser-ex' + sib + '" name="ser-ex' + sib + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + sib + ');"></td> '
			+ '<td> <select name="sibling_service' + sib + '" id="sibling_service' + sib + '" class="form-control-sm form-control"  onchange = "otherfunction(' + sib + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' 
			+ '<td><input type="text" ' + '	 maxlength="15" id="sibling_personal_no' + sib + '" name="sibling_personal_no' + sib + '" ' + '	class="form-control" autocomplete="off" ></td> '
			+ '<td><input type="text" ' + '	id="other_sibling_ser' + sib + '" name="other_sibling_ser' + sib + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'
			+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="family_save_fn(' + sib + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + sib + '" onclick="family_add_fn(' + sib + ');" ><i class="fa fa-plus"></i></a>' 
			+ '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + sib + '" onclick="family_remove_fn(' + sib + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
		datepicketDate('sib_date_of_birth' + sib);
		siblingCB(sib);
		otherfunction(sib);
	}
	
	
	aller = 1;
	function getfamily_siblingDetails() {
		var jco_id = $('#jco_id').val();
		var i = 0;
		$.post('getSibling_JCO3?' + key + "=" + value, {
			jco_id: jco_id
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
					$("table#family_table").append('<tr id="tr_sibling' + x + '">' + '<td class="sib_srno">' + x + '</td>' + '<td> <input type="sib_name' + x + '" id="sib_name' + x + '"  value="' + j[i].name + '"  onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control-sm form-control"   >' + '<td> '
						
						+ ' <input type="text" name="sib_date_of_birth' + x + '" id="sib_date_of_birth' + x + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="' + ParseDateColumncommission(j[i].date_of_birth) + '">' + '</td>' 
						+ '<td> <select name="sib_relationship' + x + '" id="sib_relationship' + x + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	id="sib_adhar_number' + x + '" name="sib_adhar_number' + x + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '	onkeypress="return isAadhar(this,event); " autocomplete="off" value="' + j[i].aadhar_no + '" ></td> ' 
						+ '<td> ' + '	<input type="text" id="sib_pan_no' + x + '" name="sib_pan_no' + x + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + '	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10" value="' + pan + '" > ' + '	</td> ' 
						+ '<td style="display:none;"><input type="text" id="sib_ch_id' + x + '" name="sib_ch_id' + x + '"   value="' + j[i].id + '"  class="form-control autocomplete" autocomplete="off" ></td>'
						+ '<td><input type="checkbox" ' + '	id="ser-ex' + x + '" name="ser-ex' + x + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + x + ');"></td> '
						+ '<td> <select name="sibling_service' + x + '" id="sibling_service' + x + '" class="form-control-sm form-control" onchange = "otherfunction(' + x + ')"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '  maxlength="15"	id="sibling_personal_no' + x + '" name="sibling_personal_no' + x + '" ' + '	class="form-control" autocomplete="off" value="' + j[i].sibling_personal_no + '" ></td> '
						+ '<td><input type="text" ' + '	id="other_sibling_ser' + x + '" name="other_sibling_ser' + x + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" autocomplete="off" ></td>'
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
					$('#ds_authority').val(j[0].authority);
					$('#ds_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
					$('#sb_reject_remarks').text(j[i].reject_remarks);
						
				}
				sib = v;
				sib_srno = v;
				$('#family_add' + sib).show();
			}
		});
	}

	function siblingCB(a) {
		if ($('#ser-ex' + a).is(":checked")) {
			$('#sibling_service' + a).attr('readonly', false);
			$('#sibling_personal_no' + a).attr('readonly', false);
		} else {

			$('#sibling_service' + a).attr('readonly', true);
			$('#sibling_personal_no' + a).attr('readonly', true);
			$('#other_sibling_ser' + a).attr('readonly', true);
			$('select#sibling_service' + a).val(0);
			$('#sibling_personal_no' + a).val("");
			$('#other_sibling_ser' + a).val("");
		}
	}
	function otherfunction(k) {
		var spouse = $("#spouse_service" + k + " option:selected").text();
		var sibling = $("#sibling_service" + k + " option:selected").text();

		if (spouse.toUpperCase() == "OTHER") {
			$('#other_spouse_ser' + k).attr('readonly', false);
			$('#spouse_personal_no' + k).attr('readonly', true);
			$('#spouse_personal_no' + k).val('');
		}
		if (spouse.toUpperCase() != "OTHER" && spouse != "--Select--") {
			$('#other_spouse_ser' + k).attr('readonly', true);
			$('#spouse_personal_no' + k).attr('readonly', false);
			$('#other_spouse_ser' + k).val('');
		}
		if (sibling.toUpperCase() == "OTHER") {
			$('#other_sibling_ser' + k).attr('readonly', false);
			$('#sibling_personal_no' + k).attr('readonly', true);
			$('#sibling_personal_no' + k).val('');
		}
		if (sibling.toUpperCase() != "OTHER" && sibling != "--Select--") {
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
	  			$.post('Sibling_JCOdelete_action?' + key + "=" + value, {
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

 function family_save_fn(ps) {
		
		var sib_name = $('#sib_name' + ps).val();
		var sib_date_of_birth = $('#sib_date_of_birth' + ps).val();
		var sib_relationship = $('#sib_relationship' + ps).val();
		var sib_ch_id = $('#sib_ch_id' + ps).val();
		var ds_authority = $('#ds_authority').val();
		var date_of_authority = $('#ds_date_of_authority').val();
		
		var jco_id = $('#jco_id').val();
		//var com_id = $("#comm_id").val();
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
			alert("Please Enter Aadhar No");
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
		$.post('Sibling_Action_JCO?' + key + "=" + value, {
			sib_name: sib_name,
			sib_date_of_birth: sib_date_of_birth,
			adhar_number: adhar_number,
			pan_no: pan_no,
			sib_relationship: sib_relationship,
			sib_ch_id: sib_ch_id,
			jco_id: jco_id,
			ds_authority:ds_authority,
			date_of_authority:date_of_authority,
			app_status:app_status,
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
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
	//*************************************END Details Of Sibling*****************************//
</script>

<script>

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
	 var army_no = '${personnel_no2}';
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
	    		    		 SetMin();
	    		    	}
	    			 	
	    			 	$("#enroll_date").val(ParseDateColumncommission(k[0].enroll_dt));
	    			 	var e_date = ParseDateColumncommission(k[0].enroll_dt);
	    			 	if (e_date != "") {
							setMinDate('date_of_attestation', e_date);
							setMinDate('date_of_tos', e_date);
						}
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

	    		    	var dtTos = new Date(k[0].dt_of_tos2);
	    		    	var dtrank = new Date(k[0].date_of_rank);

	    		    	if(dtrank > dtTos){
		    		    	setMaxDate('enroll_dt', k[0].dt_of_tos2);
		    		    	$("#min_date_enroll").val(k[0].dt_of_tos2);
	    		    	}else{
		    		    	setMaxDate('enroll_dt', k[0].date_of_rank);
		    		    	$("#min_date_enroll").val(k[0].date_of_rank);

	    		    	}
	    				
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


function SubmitAll(){
	var jco_id=$('#jco_id').val();
	var event ='${event}';
	 $.post('Submit_Reject_Upadte_Census_JCODataUrl?' + key + "=" + value,{ jco_id : jco_id,event:event },function(k) {
	
			alert("Submitted Successfully.");
			Cancel();
});
	
}

function Cancel(){
 	$("#army_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#jco_id").val($("#jco_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	$("#icstatus").val($("#activestatus").val()) ;
	
	document.getElementById('searchForm').submit();
}

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
	
	
 function SetMin(){
		
		if ($("input#dob_date").val() != "") {
				
				var birth_dt = $("input#dob_date").val();
				
				setMinDate('enroll_dt', birth_dt);
				setMinDate('date_of_seniority', birth_dt);
				setMinDate('date_of_tos', birth_dt);
				setMinDate('date_of_attestation', birth_dt);
				setMaxDate('father_dob', birth_dt);
				setMaxDate('mother_dob', birth_dt);
			}

	  }
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

