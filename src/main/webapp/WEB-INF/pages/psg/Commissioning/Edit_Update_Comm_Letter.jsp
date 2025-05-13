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
.sticky + .subcontent {
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
								<b>UPDATE COMMISSIONING DETAILS </b>
							</h4>
						</div>
						<div class="col-md-12"
							style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="status" name="status" value="0" class="form-control autocomplete" autocomplete="off">
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
												<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
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
									<div class="col-md-12">
									<div class="col-md-3" style="display: none;" id="reg_div">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> Regiment</label>
											</div>
											<div class="col-md-8">
												<label id="p_regt" class=" form-control-label"></label> 
													<select name="regt" id="inter_arm_regt_val" class="form-control" style="display: none;">
														<option value="0">--Select--</option>
													<c:forEach var="item" items="${getRegtList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
													</select>
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
	</div>
	<!-- Start PERSONNEL  -->
<c:if test="${ChangeOfpr != 0}">
	<form id="form_perssonel">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="a_final" onclick="divN(this)"><b>PERSONNEL NO</b></a>
						</h4>
					</div>
					<div id="collapse1a" class="panel-collapse collapse">
						<div class="card-body card-block"><br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="p_authority" name="p_authority" class="form-control-sm form-control autocomplete" maxlength="100" autocomplete="off" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="p_r_id" name="p_r_id" value="0" class="form-control-sm form-control" />
												<input type="text" name="p_date_of_authority" id="p_date_of_authority" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')" 
													class="form-control-sm form-control" style="width: 93%; display: inline;"
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
								<div class="col-md-12 form-group">
									<div class="col-md-2">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong> Personal No</label>
									</div>
									<div class="col-md-10">
										<div class="row form-group">
											<div class="col-md-4">
												<select id="persnl_no1" name="persnl_no1"
													class="form-control-sm form-control">
													<option value="-1">--Select--</option>
													<c:forEach var="item" items="${getPersonalType}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-4">
												<input type="text" id="persnl_no2" name="persnl_no2" onkeyup="onChangePerNo(this);" class="form-control-sm form-control" autocomplete="off" placeholder="Enter No..." maxlength="5">
											</div>
											<div class="col-md-4">
												<input type="text" id="persnl_no3" name="persnl_no3" class="form-control-sm form-control" autocomplete="off" maxlength="10" readonly="readonly">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_personal_no();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END PERSONNEL  -->

	<!-- START CADET -->
<c:if test="${ChangeOfcadet != 0}">
	<form id="form_cadet">
		<div class="card">
			<div class="panel-group" id="accordion6">
				<div class="panel panel-default" id="e_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="e_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion6" href="#" id="e_final" onclick="divN(this)"><b>CADET NO</b></a>
						</h4>
					</div>
					<div id="collapse1e" class="panel-collapse collapse">
						<div class="card-body card-block"><br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="c_authority" name="c_authority" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)"> 
												<input type="hidden" id="c_id" name="c_id" value="0" class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="c_date_of_authority"
													id="c_date_of_authority" maxlength="10"
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
												<label class=" form-control-label"> Cadet No </label>
											</div>
											<div class="col-md-8">
												<input type="text" id="cadet_no" name="cadet_no" class="form-control-sm form-control autocomplete" autocomplete="off" onkeyup="return specialcharecterCadet(this);" onkeypress="return AvoidSpace(event)">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_cadet();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END CADET -->
	<!-- START COURSE -->
<c:if test="${ChangeOfcourse != 0}">
	<form id="form_course">
		<div class="card">
			<div class="panel-group" id="accordion15">
				<div class="panel panel-default" id="k_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightgreen" id="k_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion15" href="#" id="k_final" onclick="divN(this)"><b>COURSE NO/COURSE</b></a>
						</h4>
					</div>
					<div id="collapse1k" class="panel-collapse collapse">
						<!-- <div class="card-body card-block"> -->
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="co_authority" name="co_authority"class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="co_date_of_authority"
													id="co_date_of_authority" maxlength="10"
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
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Course No </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="co_id" name="co_id" value="0" class="form-control autocomplete" autocomplete="off">
												<input type="text" id="batch_no" name="batch_no" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return isNumber0_9Only(event)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Course Com</label>
											</div>
											<div class="col-md-8">
												<select name="course" id="course" class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getCourseNameList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Course();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END Course  -->
	<!-- START GENDER -->
<c:if test="${ChangeOfgender != 0}">
	<form id="form_gender">
		<div class="card">
			<div class="panel-group" id="accordion42">
				<div class="panel panel-default" id="p_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title yellow" id="p_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion42" href="#" id="p_final" onclick="divN(this)"><b>Gender</b></a>
						</h4>
					</div>
					<div id="collapse1p" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
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
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Gender</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="g_id" name="g_id" value="0" class="form-control autocomplete" autocomplete="off">
												<select name="gender" id="gender" class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getGenderList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Gender();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END Gender -->

	<!-- START COMMISSION -->
<c:if test="${ChangeOfcommision != 0}">
	<form id="form_COMMISSION">
		<div class="card">
			<div class="panel-group" id="accordion45">
				<div class="panel panel-default" id="r_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightred" id="r_div5">
							<a class="droparrow collapsed" data-toggle="collapse"data-parent="#accordion45" href="#" id="r_final" onclick="divN(this)"> <b>COMMISSION</b></a>
						</h4>
					</div>
					<div id="collapse1r" class="panel-collapse collapse">
						<div class="card-body card-block">	<br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="com_authority" name="com_authority"class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100"onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="com_date_of_authority"
													id="com_date_of_authority" maxlength="10"
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
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Type of Commission Granted</label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="com_id" name="com_id" value="0" class="form-control autocomplete" autocomplete="off">
												<select name="type_of_comm_granted" id="type_of_comm_granted" class="form-control-sm form-control">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getTypeOfCommissionList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Commission </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="date_of_commission"
													id="date_of_commission" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
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
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Commission();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END COMMISSION-->

	<!-- 	START Birth-->
<c:if test="${ChangeOfdob != 0}">
	<form id="form_Birth">
		<div class="card">
			<div class="panel-group" id="accordion21">
				<div class="panel panel-default" id="x_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title blue" id="x_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion21" href="#" id="x_final" onclick="divN(this)"><b>DATE OF BIRTH</b></a>
						</h4>
					</div>
					<div id="collapse1x" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="b_authority" name="authority" class="form-control-sm form-control autocomplete"autocomplete="off" maxlength="100"onkeyup="return specialcharecter(this)"> 
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
												<label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Birth</label>
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
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Birth();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- 	END Birth -->
	<!-- START REGIMENT -->
<c:if test="${ChangeOfarmservice != 0}">
	<form id="form_regiment">
		<div class="card">
			<div class="panel-group" id="accordion17">
				<div class="panel panel-default" id="aa_div16">
					<div class="panel-heading">
						<h4 class="panel-title main_title green" id="aa_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion17" href="#" id="aa_final" onclick="divN(this)"><b>ARM/SERVICE</b></a>
						</h4>
					</div>
					<div id="collapse1aa" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="arm_authority" name="authority" onkeyup="return specialcharecter(this)"class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="100"> 
												<input type="hidden" id="reg_id" name="reg_id" value="0" class="form-control" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Date of Authority</label>
											</div>
											<div class="col-md-8">
												<input type="text" name="arm_date_of_authority"
													id="arm_date_of_authority" maxlength="10"
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
												<label class=" form-control-label"><strong style="color: red;">* </strong> Parent Arm/Service </label>
											</div>
											<div class="col-md-8">
												<label id="parent_arm_val" class=" form-control-label"></label>
												<select name="parent_arm" id="parent_arm" class="form-control-sm form-control" onchange="chgarm(this,'regiment');">
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
												<label class=" form-control-label"> Regt</label>
											</div>
											<div class="col-md-8">
												<select name="regiment" id="regiment" class="form-control-sm form-control" disabled="disabled">
													<option value="0">--Select--</option>
													
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Regiment();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
	<!-- END REGIMENT -->
	<!-- Start UNIT  -->
<c:if test="${ChangeOfunit != 0}">
	<form id="form_unit">
		<div class="card">
			<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="b_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="b_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="b_final" onclick="divN(this)"><b>UNIT</b></a>
						</h4>
					</div>
					<div id="collapse1b" class="panel-collapse collapse">
						<div class="card-body card-block"> <br>
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Authority </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="unit_id" name="unit_id" value="0" class="form-control autocomplete" autocomplete="off" maxlength="100">
												<input type="text" id="u_authority" name="authority" class="form-control-sm form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="date_of_authority" id="u_date_of_authority" 
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
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
												<label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
											</div>
											<div class="col-md-8">
												<input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control-sm form-control autocomplete" autocomplete="off" maxlength="50">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Posted To </label>
											</div>
											<div class="col-md-8">
												<input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control-sm form-control autocomplete" autocomplete="off">
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
												<input type="text" name="date_of_tos" id="date_of_tos"
													maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
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
							</div>
							<div class="card-footer" align="center" class="form-control">
							<input type="hidden" id="tenure_dt" name="tenure_dt" value="" class="form-control autocomplete" autocomplete="off">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_Unit();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END UNIT  -->
<!-- START rank -->
<c:if test="${ChangeOfrank != 0}">
	<form id="form_rnk">
		<div class="card">
			<div class="panel-group" id="accordion71">
				<div class="panel panel-default" id="r1_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title lightblue" id="r1_div5">
							<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion71" href="#" id="r1_final" onclick="divN(this)"><b>Change Rank</b></a>
						</h4>
					</div>
					<div id="collapse1rnk" class="panel-collapse collapse">
						<div class="card-body card-block"><br>
							<div class="row">
							<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Authority </label>
											</div>
											<div class="col-md-8">
												<input type="hidden" id="rank_id" name="rank_id" value="0" maxlength="100" class="form-control autocomplete" autocomplete="off">
												<input type="text" id="r_authority" name="r_authority" class="form-control-sm form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)" maxlength="100">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Authority </label>
											</div>
											<div class="col-md-8">
												<input type="text" name="rdate_of_authority" 
													id="rdate_of_authority" maxlength="10"
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
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Rank  </label>
						                </div>
						                <div class="col-md-8">
									        <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
						                </div>
						            </div>
	              				</div>
	              					
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Date of Rank </label>
						                </div>
						                <div class="col-md-8">
						                 	<input type="text" name="dt_rk" 
													id="dt_rk" maxlength="10"
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
							</div>
							<div class="card-footer" align="center" class="form-control">
								<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_rank();">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>
<!-- END Rank  -->	 

</div>
<script type="text/javascript">
$(document).ready(function() {
 	$('#comm_id').val('${id}');
  	$('#personnel_no').val('${personnel_no2}');
  	 $("input#personnel_no").attr('readonly', true);
  	personal_number();
  	$("#submit_a").hide();
  	 
 	<c:if test="${ChangeOfpr != 0}">
 	getpersonnelNo();
	</c:if>
	
	<c:if test="${ChangeOfcadet != 0}"> 
	getcadet();
	</c:if>
	
	<c:if test="${ChangeOfcourse != 0}">
	getcourseno();
	</c:if>
	
	<c:if test="${ChangeOfgender != 0}"> 
	getgender();
	</c:if>
	
	<c:if test="${ChangeOfcommision != 0}"> 
	getcommison();
	</c:if>
	
	<c:if test="${ChangeOfdob != 0}"> 
	getdob();
	</c:if>
	
	<c:if test="${ChangeOfarmservice != 0}"> 
	getarmservice();
	</c:if>
	
	<c:if test="${ChangeOfunit != 0}"> 
	getunit();
	
	
	</c:if>

	
});

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
				get_PersonalNo();
			}
		}	
		else if (obj.id == "e_final") {
			if(!hasC){
				get_CadetNo();
			}
		}
		else if (obj.id == "k_final") {
			if(!hasC){
				get_Course();
			}
		}
		else if (obj.id == "p_final") {
			if(!hasC){
				get_Gender();
			}
		}
		else if (obj.id == "r_final") {
			if(!hasC){
				get_Commission();
			}
		}	
		else if (obj.id == "x_final") {
			if(!hasC){
				get_Birth();
			}
		}
		else if (obj.id == "aa_final") {
			if(!hasC){
				get_Regiment();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_Unit();
			}
		}
		 else if (obj.id == "r1_final") {
				if(!hasC){
					get_rank();
				}
			}
		
		}
function SetMin(){
	if ($("input#comm_date").val() != "") {
		var comm_dt = $("input#comm_date").val();
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
	if ($("input#date_of_commission").val() != "") {
		var comm_dt = ParseDateColumncommission($("input#date_of_commission").val());
		setMinDate('date_of_seniority', comm_dt);
		setMinDate('date_of_tos', comm_dt);
		setMinDate('date_of_rank', comm_dt);
	}
}
		
</script>


<script>

/* Personal No */

function personal_number()
{
	
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	else{
		$("#div_update_data").show();
	}
	 var personnel_no = $("input#personnel_no").val();
	 $.post('GetPersonnelNoDataForComm?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
			
	    	$("#comm_id").val(j[0][0]);
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCommissionDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			  
	    			  
	    			  
	    			  $('#marital_event').val('0');
	    			  $('#marriage_div').show();
	    			   $("#cadet_name").text(k[0][1]);
	    			 
	    			 	$("#comm_date").val(ParseDateColumncommission(k[0][9]));
	    			 	SetMin();
	    		    	$("#p_rank").text(k[0][2]);
	    		    	$("#hd_p_rank").val(k[0][2]);
	    		    	if(k[0][3]==null){
	    		    		$("#p_app").text('');
	    		    	}else{
	    		    		$("#p_app").text(k[0][3]);
	    		    	}
	    		    	if(k[0][4]==null){
	    		    		$("#p_app_dt").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_app_dt").text(convertDateFormate(k[0][4]));  
	    		    	}
	    		    	if(k[0][5]==null){
	    		    		$("#p_tos").text("");    
	    		    	}
	    		    	else{
	    		    		 $("#p_tos").text(convertDateFormate(k[0][5]));  
	    		    	}
	    		    	$("#tos_date").val(ParseDateColumncommission(k[0][5]));
	    		    	$("#app_sus_no").text(k[0][6]);
	    		    	
	    		    
	    		    	$("#app_parent_arm").text(k[0][7]);
	    		    	$("#p_cmd").text(k[0][12]);
	    		    	 if(k[0][6] == "GORKHA" || k[0][6] == "INFANTRY"){
	    		 			$("#reg_div").show();
	    		 		}
	    		 	  else
	    		 		  {
	    		 		 $("#reg_div").hide();
	    		 		  }
	    		    	if(k[0][14]!=0){
	    		    		$('#inter_arm_regt_val').val(k[0][14]);
	    		    		$('#p_regt').text($( "#inter_arm_regt_val option:selected" ).text());
	    		    	}
	    		    	else{
	    		    		$('#p_regt').text("");
	    		    	}
	    		    	var sus_no =k[0][6];
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
	 		$.post('GetServingStatus?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    		    	$("#p_serving_status").text(k[0][2]);
	    		 }
	   });
	 		$.post('GETtenure_date?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		   		 if(k.length > 0){
			   			$('#tenure_dt').val(k[0][1]);
			   			setMaxDate('date_of_tos', k[0][1]);
		   		 }	 
		   		 
		       });
		 }
	    	
	   });

	 $("input#personnel_no").attr('readonly', true);
}
$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListFORComm?"+key+"="+value,
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
</script>
<script type="text/javascript">
<!-- //-->
function getpersonnelNo(){
	 var comm_id = '${id}';
	 $.post('getPersonalNOData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#p_authority').val(j[0].authority);
					 $('#p_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#persnl_no1').val(j[0].new_personal_no);
					 $('#p_r_id').val(j[0].id);
						
		 }
	 });
	} 
function getcadet(){
	 var comm_id = '${id}';
	 $.post('getcadetData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#c_authority').val(j[0].authority);
					 $('#c_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#cadet_no').val(j[0].cadet_no);
					 $('#c_id').val(j[0].id);
		 }
	 });
	}
function getcourseno(){
	 var comm_id = '${id}';
	 $.post('getCourseData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#co_authority').val(j[0].authority);
					 $('#co_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#batch_no').val(j[0].batch_no);
					 $('#course').val(j[0].course);
					 $('#course_val').val($( "#course option:selected" ).text());
					 $('#co_id').val(j[0].id);
		 }
	 });
	}
function getgender(){
	 var comm_id = '${id}';
	 $.post('getGenderData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#g_authority').val(j[0].authority);
					 $('#g_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
					 $('#gender').val(j[0].gender);
					 $('#genderlbl').val($( "#gender option:selected" ).text());
					 $('#g_id').val(j[0].id);
		 }
	 });
	}
function getcommison(){
	 var comm_id = '${id}';
	 $.post('getcommisonData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#com_authority').val(j[0].authority);
			 $('#com_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#type_of_comm_granted').val(j[0].type_of_comm_granted);
			 $('#type_of_comm_grantedlbl').val($( "#type_of_comm_granted option:selected" ).text());
			 $('#date_of_commission').val(convertDateFormate(j[0].date_of_commission));
			 $('#com_id').val(j[0].id);
		 }
	 });
  } 
function getdob(){
	 var comm_id = '${id}';
	 $.post('getdobData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#b_authority').val(j[0].authority);
			 $('#b_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#date_of_birth').val(convertDateFormate(j[0].date_of_birth));
			 $('#birth_id').val(j[0].id);
		 }
	 });
 }
function getarmservice(){
	 var comm_id = '${id}';
	 $.post('getarmsirviceData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#arm_authority').val(j[0].authority);
			 $('#arm_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#parent_arm').val(j[0].parent_arm);
			 $('#parent_armlbl').val($( "#parent_arm option:selected" ).text());
			 $('#regiment').val(j[0].regiment)
			 $('#regimentlbl').val($( "#regiment option:selected" ).text());
			 $('#reg_id').val(j[0].id);
		 }
	 });
 } 
function getunit(){
	 var comm_id = '${id}';
	 $.post('getunitData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#u_authority').val(j[0].authority);
			 $('#u_date_of_authority').val(convertDateFormate(j[0].date_of_authority));
			 $('#unit_sus_no').val(j[0].unit_sus_no);
			 $('#unit_posted_to').val(j[0].unit_post_to);
			 $('#date_of_tos').val(convertDateFormate(j[0].date_of_tos));
			 $('#unit_id').val(j[0].id);
		 }
	 });
}
</script>

<script>
function validate_personal_no(){

	if ($("select#persnl_no1").val() == "0") {
		alert("Please Select Personal No");
		$("select#persnl_no1").focus();
		return false;
			}
   if ($("input#persnl_no2").val() == "") {
		alert("Please Enter Personal No");
		$("input#persnl_no2").focus();
		return false;
	}
	    var formvalues=$("#form_perssonel").serialize();
		var p_r_id=$("#p_r_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&p_r_id="+p_r_id+"&comm_id="+comm_id;
		 $.post('Personal_no_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#p_r_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_PersonalNo(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_PersonalNo1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#p_authority').val(j[0].authority);
				var l = j[0].new_personal_no.length;
				$('#p_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$("#persnl_no1").val(j[0].new_personal_no.substring(0,l-6));
				$("#persnl_no2").val(j[0].new_personal_no.substring(l-6, l-1));
				$("#persnl_no3").val(j[0].new_personal_no.substring(l,l-1));
				
			}
		
	  });
}  
</script>
<script>
/* ----------- Start cadet no ---------------*/
function validate_cadet(){
	 if ($("input#cadet_no").val() == "") {
			alert("Please Enter Cadet No");
			$("input#cadet_no").focus();
			return false;
	  }
	    var formvalues=$("#form_cadet").serialize();
		var c_id=$("#c_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&c_id="+c_id+"&comm_id="+comm_id;
		 $.post('Cadet_no_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#c_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_CadetNo(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_CadetNo1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#c_authority').val(j[0].authority);
				$('#c_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$("#cadet_no").val(j[0].cadet_no);
			}
	  });
} 
/* ----------- Start Course ---------------*/
function validate_Course(){
	 if ($("input#batch_no").val() == "") {
			alert("Please Enter Course No");
			$("input#batch_no").focus();
			return false;
				}
	if ($("select#course").val() == "0") {
		alert("Please Select Course");
		$("select#course").focus();
		return false;
	} 
	    var formvalues=$("#form_course").serialize();
		var co_id=$("#co_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&co_id="+co_id+"&comm_id="+comm_id;
		 $.post('Course_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#co_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Course(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_CourseNo1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#co_authority').val(j[0].authority);
				$('#co_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#batch_no').val(j[0].batch_no);
				$('#course').val(j[0].course);
			}
	  });
} 
/* ----------- Start Gender ---------------*/
function validate_Gender(){
	 if ($("select#gender").val() == "0") {
			alert("Please Select Gender");
			$("select#gender").focus();
	 		return false;
		}
	    var formvalues=$("#form_gender").serialize();
		var g_id=$("#g_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&g_id="+g_id+"&comm_id="+comm_id;
		 $.post('Comm_Gender_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#g_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Gender(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Gender1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#g_authority').val(j[0].authority);
				$('#g_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#gender').val(j[0].gender);
			}
	  });
} 
/* ----------- Start Commission ---------------*/
function validate_Commission(){
	if ($("select#type_of_comm_granted").val() == "0") {
		alert("Please Select Type of Commission Granted");
		$("select#type_of_comm_granted").focus();
 		return false;
	}
	if ($("input#date_of_commission").val() == "") {
		alert("Please Enter Date of Commission");
		$("input#date_of_commission").focus();
		return false;
	}
	    var formvalues=$("#form_COMMISSION").serialize();
		var com_id=$("#com_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&com_id="+com_id+"&comm_id="+comm_id;
		 $.post('COMMISSION_Action?' + key + "=" + value,formvalues, function(data){
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#com_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Commission(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Commission1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#com_authority').val(j[0].authority);
				$('#com_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#type_of_comm_granted').val(j[0].type_of_comm_granted);
				$('#date_of_commission').val(ParseDateColumncommission(j[0].date_of_commission));
			}
	  });
}
/* ----------- Start Birth ---------------*/
function validate_Birth(){
	if ($("#date_of_birth").val() == "") {
		alert("Please Select Date of Birth");
		$("#date_of_birth").focus();
		return false;
	}  
	    var formvalues=$("#form_Birth").serialize();
		var birth_id=$("#birth_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&birth_id="+birth_id+"&comm_id="+comm_id;
		 $.post('Birth_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#birth_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Birth(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Birth1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#b_authority').val(j[0].authority);
				$('#b_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
			}
	  });
}
/* ----------- Start Regiment ---------------*/
function validate_Regiment(){
	 if ($("select#parent_arm").val() == "0") {
		 
			alert("Please Select  Parent Arm/Service ");
			$("select#parent_arm").focus();
			return false;
		}
     var sp14 = $("#parent_arm option:selected").text();
	
     if(($("#parent_arm").val() == "0700" || $("#parent_arm").val() == "0800") && $("select#regiment").val() == "0"){
		alert("Please Select Regiment");
		$("select#regiment").focus();
		return false;
	}
	    var formvalues=$("#form_regiment").serialize();
		var reg_id=$("#reg_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&reg_id="+reg_id+"&comm_id="+comm_id;
		 $.post('Regiment_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#reg_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Regiment(){
	
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Regiment?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				 $.ajaxSetup({
		             async : false
		    	 });
				
				$('#arm_authority').val(j[0].authority);
				$('#arm_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#parent_arm').val(j[0].parent_arm_service);
				chgarm(document.getElementById("parent_arm"),'regiment');
				$('#regiment').val(j[0].regt);
				
			}
	  });
	 
}
//   function chgarm(){
	
// 	var text = $("#parent_arm option:selected").text();
		
// 		if(text == "INFANTRY"){
// 			$("#regiment").attr('disabled',false);
// 		}
// 		else{
// 			 $("#regiment").attr('disabled',true);
// 			  $("#regiment").val("0");
// 		}
//   }
  </script>
  
  <script>
/* ----------- Start Unit ---------------*/
function validate_Unit(){
	if ($("input#unit_sus_no").val() == "") {
		alert("Please Enter Unit Sus No");
		$("input#unit_sus_no").focus();
		return false;
	}
    if ($("input#unit_posted_to").val() == "") {
		alert("Please Enter Unit Posted To");
		$("input#unit_posted_to").focus();
		return false;
	}
	 if ($("#date_of_tos").val() == "") {
			alert("Please Enter Date of TOS");
			$("#date_of_tos").focus();
	 		return false;
		}
	    var formvalues=$("#form_unit").serialize();
		var unit_id=$("#unit_id").val();	
		var comm_id=$("#comm_id").val();	
		formvalues+="&unit_id="+unit_id+"&comm_id="+comm_id;
		 $.post('Unit_Action?' + key + "=" + value,formvalues, function(data){
			      
		          if(data=="update")
		        	  alert("Data Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#unit_id").val(data);	
		        	  alert("Data Saved SuccesFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Unit(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Unit1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#u_authority').val(j[0].authority);
				$('#u_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#unit_sus_no').val(j[0].unit_sus_no);
				$('#unit_posted_to').val(j[0].unit_post_to);
				$('#date_of_tos').val(ParseDateColumncommission(j[0].date_of_tos));
			}
	  });
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
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	
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
	    
	     if(year < 17)
	     {
	    	 alert("Please enter age above 17");
	    	 $("#"+obj.id).val("");
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
	 		 $.ajaxSetup({
                 async : false
         	});
	 		
	 		$("#persnl_no3").val(suffix);
	 		
	 		 $.ajaxSetup({
                 async : false
        	 });
	 		Personal_no_already_exist();
	 	}
	 	
	 	
	 }
	

	 function Personal_no_already_exist()
	 {
		
		 var persnl_no1 = $("select#persnl_no1").val();
		 if(persnl_no1 == "-1")
		 {
			 alert("Please Select Personal Number Prefix.")
			 return false;
		 }
		 var persnl_no2 = $("input#persnl_no2").val();
		 if(persnl_no2.length != 5 )
		 {
			 alert("Please Enter Personal Number.")
			 return false;
		 }
		 var persnl_no3 = $("input#persnl_no3").val();
		 if(persnl_no3.length != 1 )
		 {
			 alert("Please Enter Personal Number.")
			 return false;
		 }
		 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
		 
		 $.post("getPersonnelNoAlreadyExistInCommission?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
			
			 	if(j == true){
					alert("Personal Number already Approved.")
					$("select#persnl_no1").val("-1");
					$("input#persnl_no2").val("");
					$("input#persnl_no3").val("0");
				} 
				
			}); 
	 }
	 
	
	 
	 function date_of_com() {
		  $( "#date_of_commission" ).datepicker({  maxDate: new Date() });
		  alert("Future date are not allowed ");
	 }
	 
	 function isNumber0_9Only(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
				return false;
			}else{
				if(charCode == 46){
					return false;
				}
			}
			return true;
		}
 </script>
<script>
  

jQuery(function($){
	

	 datepicketDate('p_date_of_authority');  
	 datepicketDate('c_date_of_authority'); 
	 datepicketDate('co_date_of_authority');  
	 datepicketDate('g_date_of_authority'); 
	 
	 datepicketDate('com_date_of_authority'); 
	 datepicketDate('date_of_commission'); 
	 
	 datepicketDate('b_date_of_authority'); 
	 datepicketDate('date_of_birth');
	 
	 datepicketDate('arm_date_of_authority');
	 
	 datepicketDate('u_date_of_authority'); 
	 datepicketDate('date_of_tos');
	 
	 datepicketDate('rdate_of_authority'); 
	 datepicketDate('dt_rk');
	 });
	 
	 
function specialcharecterCadet(a)
{
    var iChars = "@#^&*$,.:;%!+_-[]";   
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}
  
function validate_rank(){
	
	
	 if ($("#rdate_of_authority").val().trim()=="" || $("#rdate_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#rdate_of_authority").focus();
			return false;
		} 
	 
	 if ($("select#rank").val()== "0" || $("select#rank").val()== "") {
			alert("Please Enter Rank");
			$("select#rank").focus();
	 		return false;
		}
	 if ($("#dt_rk").val() == "") {
			alert("Please Enter Date of Rank");
			$("#dt_rk").focus();
	 		return false;
		}
    if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#dt_rk").val())) {
        alert("Date of Rank should be greater Or equal to Commissioning Date");
        $("#dt_rk").focus();
        return false;
}
	 
	 
	 
	 var formvalues=$("#form_rnk").serialize();
	 var dt_commission  = $("#comm_date").val();
		var comm_id=$("#comm_id").val();	

		formvalues+="&comm_id="+comm_id+"&dt_commission="+dt_commission;;
		  
	
		 $.post('RANK_Action?' + key + "=" + value,formvalues, function(data){
		          if(data=="update")
		        	  alert("Rank Updated Successfully");
		          else if(parseInt(data)>0){
		        	  $("#com_id").val(data);	
		        	  alert("Rank Saved SuccessFully")
		          }
		          else
		        	  alert(data)
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		}); 
	 
}
function get_rank(){

	 var comm_id = '${id}';
	 $.post('get_rank1?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#r_authority').val(j[0].authority);
					 $('#rdate_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
					 $('#rank').val(j[0].rank);
					
					 $('#dt_rk').val(ParseDateColumncommission(j[0].date_of_rank));
					 $('#rank_id').val(j[0].id);
		 }
	 });
	}	 
</script>