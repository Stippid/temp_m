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
<script src="js/Calender/jquery-2.2.3.min.js"></script>
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
<style>
.selectBox {
	position: relative;
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


<div class="container-fluid" align="center">
	<form id="Comm_form">
		<div class="animated fadeIn">
			<div class="card" align="center">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="insp_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title" id="insp_div5"> <b>APPROVE COMMISSIONING DETAILS </b></h4>
							<p> <strong style="color: red;">(*Note: Please Do Not Leave The Page Without Approving Otherwise, It Will be Shown In Pending State.) </strong></p>
						</div>
						<div class="col-md-12"
							style="padding-top: 5px; padding-left: 250px;">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;"> </strong>Personal No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="status" name="status" value="0"class="form-control autocomplete" autocomplete="off">
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
<div id="div_update_data" style="display: block;" class="subcontent">
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
								<!-- 	<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong style="color: red;"></strong> ID Card No</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_id_no"></label>
											</div>
										</div>
									</div> -->
								<!-- 	<div class="col-md-3">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Religion</label>
											</div>
											<div class="col-md-8">
												<label class=" form-control-label" id="p_religion"></label>
											</div>
										</div>
									</div> -->
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong> Authority </label>
												</div>
												<div class="col-md-8">
													<label id="p_authority"></label>
													<input type="hidden" id="prs_id" name="prs_id" value="0" class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority </label>
												</div>
												<div class="col-md-8">
													<label id="p_date_of_authority"></label> <input type="hidden" id="p_r_id" name="p_r_id" value="0" class="form-control-sm form-control" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12 form-group">
										<div class="col-md-2">
											<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong> Personal No</label>
										</div>
										<div class="col-md-10">
											<div class="row form-group">
												<div class="col-md-4">
													<label id="persnl_no1"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<!-- <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="getpersonnelNo_Reject();"> -->
									  <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('getpersonnelNo_Reject',0)}else{return false;}">
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong> Authority </label>
												</div>
												<div class="col-md-8">
													<label id="c_authority"></label> <input type="hidden" id="c_id" name="c_id" value="0" class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong> Date of Authority </label>
												</div>
												<div class="col-md-8">
													<label id="c_date_of_authority"></label>
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
													<label id="cadet_no"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<!-- <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Cadet_Reject();"> -->
									 <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Cadet_Reject',0)}else{return false;}">
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="co_authority"></label>
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
													<label id="co_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong>Course No </label>
												</div>
												<div class="col-md-8">
													<label id="batch_no"></label> <input type="hidden" id="co_id" name="co_id" value="0" class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Course</label>
												</div>
												<div class="col-md-8">
													<label id="course_val"></label> 
													<select name="course" id="course" class="form-control-sm form-control" style="display: none;">
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
									<!-- <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Course_Reject();"> -->
									 <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Course_Reject',0)}else{return false;}">
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="g_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Date of Authority</label>
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
													<label class=" form-control-label"> <strong style="color: red;"> </strong>Gender </label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="g_id" name="g_id" value="0" class="form-control autocomplete" autocomplete="off">
													<label id="genderlbl"></label>
													<div style="display: none;">
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
								</div>
								<div class="card-footer" align="center" class="form-control">
									<!-- <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Gender_Reject();"> -->
									 <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Gender_Reject',0)}else{return false;}">
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
								<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion45" href="#" id="r_final" onclick="divN(this)"> <b>COMMISSION</b></a>
							</h4>
						</div>
						<div id="collapse1rnk" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="com_authority"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="com_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Type of Commission Granted</label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="com_id" name="com_id" value="0" class="form-control autocomplete" autocomplete="off">
													<label id="type_of_comm_grantedlbl"></label>
													<div style="display: none;">
														<select name="type_of_comm_granted" id="type_of_comm_granted" class="form-control-sm form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getTypeOfCommissionList}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Date of Commission </label>
												</div>
												<div class="col-md-8">
													<label id="date_of_commission"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
								<!-- 	<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Commison_Reject();"> -->
									 <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Commison_Reject',0)}else{return false;}">
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Authority</label>
												</div>
												<div class="col-md-8">
													<label id="b_authority"></label> <input type="hidden" id="birth_id" name="birth_id" value="0" class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong>Date of Authority</label>
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
													<label class=" form-control-label"> <strong style="color: red;"> </strong>Date of Birth </label>
												</div>
												<div class="col-md-8">
													<label id="date_of_birth"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
								<!-- 	<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Birth_Reject();"> -->
									<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Birth_Reject',0)}else{return false;}">
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
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Authority</label>
												</div>
												<div class="col-md-8">
													<label id="arm_authority"></label> <input type="hidden" id="reg_id" name="reg_id" value="0" class="form-control" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority</label>
												</div>
												<div class="col-md-8">
													<label id="arm_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Parent Arm/Service </label>
												</div>
												<div class="col-md-8">
													<label id="parent_armlbl"></label>
													<div style="display: none;">
														<select name="parent_arm" id="parent_arm" class="form-control-sm form-control" onchange="chgarm();">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getParentArmList}" varStatus="num">
																<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> Regt</label>
												</div>
												<div class="col-md-8">
													<label id="regimentlbl"></label>
													<div style="display: none;">
														<select name="regiment" id="regiment" class="form-control-sm form-control" readonly="readonly">
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
								<div class="card-footer" align="center" class="form-control">
								<!-- 	<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Armservice_Reject();"> -->
									<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Armservice_Reject',0)}else{return false;}">
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
								<a class="droparrow collapsed" data-toggle="collapse" data-parent="#accordion4" href="#" id="b_final"onclick="divN(this)"><b>UNIT</b></a>
							</h4>
						</div>
						<div id="collapse1b" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong> Authority </label>
												</div>
												<div class="col-md-8">
													<label id="u_authority"></label> <input type="hidden" id="unit_id" name="unit_id" value="0" class="form-control autocomplete" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong style="color: red;"> </strong> Date of Authority </label>
												</div>
												<div class="col-md-8">
													<input type="hidden" id="u_id" name="u_id" value="0" class="form-control-sm form-control" /> 
													<label id="u_date_of_authority"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Unit SUS No </label>
												</div>
												<div class="col-md-8">
													<label id="unit_sus_no"></label>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Unit Posted To </label>
												</div>
												<div class="col-md-8">
													<label id="unit_posted_to"></label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;"> </strong> Date of TOS</label>
												</div>
												<div class="col-md-8">
													<label id="date_of_tos"></label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<!-- <input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="Unit_Reject();"> -->
										<input type="button" class="btn btn-primary btn-sm" value="Reject" onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Unit_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
	<!-- END UNIT  -->
		<!--  Start RANK-->
	<c:if test="${ChangeOfrank != 0}">
		<form id="form_rnk">
			<div class="card">
				<div class="panel-group" id="accordion6r">
					<div class="panel panel-default" id="r1_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title lightblue" id="r1_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion6r" href="#" id="r1_final"
									onclick="divN(this)"><b>Change Rank</b></a>
							</h4>
						</div>
						<div id="collapse1r" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Authority
													</label>
												</div>
												<div class="col-md-8">
													<label name="r_authority" id="r_authority"></label> <input
														type="hidden" id="rank_id" name="rank_id" value="0"
														maxlength="100" class="form-control autocomplete"
														autocomplete="off">
													<!-- <input type="text" id="r_authority" name="r_authority" class="form-control-sm form-control autocomplete" autocomplete="off" onkeyup="return specialcharecter(this)" maxlength="100">
											 -->
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Date of Authority
													</label>
												</div>
												<div class="col-md-8">
													<label name="rdate_of_authority" id="rdate_of_authority">
													</label>
													<!-- 	<input type="text" name="rdate_of_authority" 
													id="rdate_of_authority" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);"> -->
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Rank
													</label>
												</div>
												<div class="col-md-8">
													<label name="ranklab" id="ranklab"></label>
													<div style="display: none;"> <select
														name="rank" id="rank"
														class="form-control-sm form-control hidden">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
													</div>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"> <strong
														style="color: red;">* </strong> Date of Rank
													</label>
												</div>
												<div class="col-md-8">
													<label id="dt_rk"> </label>
													<!-- <input type="text" name="dt_rk" 
													id="dt_rk" maxlength="10"
													onclick="clickclear(this, 'DD/MM/YYYY')"
													class="form-control-sm form-control"
													style="width: 93%; display: inline;"
													onfocus="this.style.color='#000000'"
													onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
													onkeyup="clickclear(this, 'DD/MM/YYYY')"
													onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
													aria-required="true" autocomplete="off"
													style="color: rgb(0, 0, 0);"> -->
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-footer" align="center" class="form-control">
									<!-- 		<input type="button" class="btn btn-primary btn-sm" value="Submit for Approval" onclick="validate_rank();"> -->
									<input type="button" class="btn btn-primary btn-sm"
										value="Reject"
										onclick="if (confirm('Are You Sure You Want to Reject Officer Data?')){addRemarkModel('Rank_Reject',0)}else{return false;}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:if>
</div>
<form:form name="Approve_Update_Comm_Data" id="Approve_Update_Comm_Data" action="Approve_Update_Comm_DataAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Approve_Update_Comm_DataCMD">
	<div class="animated fadeIn">
		<div class="card">
			<input type="hidden" id="comm_id" name="comm_id" value="0" class="form-control autocomplete" autocomplete="off">
			<input type="hidden" id="personnelNo" name="personnelNo" value="0" class="form-control autocomplete" autocomplete="off">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="col-md-12" align="center">
						<p> <input type="checkbox" id="myCheck" onclick="myFunction();" /> I have checked all the tabs and certify that all the information is correct. </p>
					</div>
					<div class="col-md-12" id="submit_a" align="center">
						 <input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return true;}else{return false;}">
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
		else if (obj.id == "e_final") {
			if(!hasC){
				
			}
		}
		
		else if (obj.id == "k_final") {
			if(!hasC){
				
			}
		}
		else if (obj.id == "p_final") {
			if(!hasC){
				
				
			}
		}
		else if (obj.id == "r_final") {
			if(!hasC){
				
				
			}
		}	
		else if (obj.id == "x_final") {
			if(!hasC){
				
				
			}
		}
		else if (obj.id == "aa_final") {
			if(!hasC){
				
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				
			}
		}
		else if (obj.id == "r1_final") {
			if(!hasC){
				
			}
		}
		}
</script>
<script type="text/javascript">
<!-- Personal No //-->
$(document).ready(function() {
	
 	$('#comm_id').val('${id}');
  	$('#personnel_no').val('${personnel_no2}');
	$('#personnelNo').val('${personnel_no2}');
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
	<c:if test="${ChangeOfrank != 0}"> 
	getrank();
	</c:if>
	
});
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
	    			 
	    			 	$("#comm_date").val(ParseDateColumncommission(k[0][11]));
	    			 	
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
			        	for(var i = 0;i<data.length;i++){ susval.push(dec(enc,data[i]));}
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

<script>
function getpersonnelNo(){
	 var comm_id = '${id}';
	 $.post('getPersonalNOData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#p_authority').text(j[0].authority);
					 $('#p_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
					 $('#persnl_no1').text(j[0].new_personal_no);
					 $('#prs_id').val(j[0].id);
		 }
	 });
	} 
	
function getpersonnelNo_Reject(){
	 var comm_id = '${id}';
	 var prs_id = $('#prs_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectpersonnelNo?' + key + "=" + value, {comm_id:comm_id,prs_id:prs_id,reject_remarks:reject_remarks}, function(j){

		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_perssonel').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 }); 
  } 
function getcadet(){
	 var comm_id = '${id}';
	 $.post('getcadetData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#c_authority').text(j[0].authority);
					 $('#c_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
					 $('#cadet_no').text(j[0].cadet_no);
					 $('#c_id').val(j[0].id);
		 }
	 });
	}

function Cadet_Reject(){
	 var comm_id = '${id}';
	 var c_id = $('#c_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectcadNo?' + key + "=" + value, {comm_id:comm_id,c_id:c_id,reject_remarks:reject_remarks}, function(j){

		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_cadet').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	} 
	
function getcourseno(){
	 var comm_id = '${id}';
	 $.post('getCourseData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#co_authority').text(j[0].authority);
					 $('#co_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
					 $('#batch_no').text(j[0].batch_no);
					 $('#course').val(j[0].course);
					 $('#course_val').text($( "#course option:selected" ).text());
					 $('#co_id').val(j[0].id);
		 }
	 });
	}

function Course_Reject(){
	 var comm_id = '${id}';
	 var co_id = $('#co_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectCourse?' + key + "=" + value, {comm_id:comm_id,co_id:co_id,reject_remarks:reject_remarks}, function(j){
		 
		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_course').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	} 
function getgender(){
	 var comm_id = '${id}';
	 $.post('getGenderData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
					 $('#g_authority').text(j[0].authority);
					 $('#g_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
					 $('#gender').val(j[0].gender);
					 $('#genderlbl').text($( "#gender option:selected" ).text());
					 $('#g_id').val(j[0].id);
		 }
	 });
	}
function Gender_Reject(){
	 var comm_id = '${id}';
	 var g_id = $('#g_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectGENDER?' + key + "=" + value, {comm_id:comm_id,g_id:g_id,reject_remarks:reject_remarks}, function(j){
		 
		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_gender').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	}
function getcommison(){
	 var comm_id = '${id}';
	 $.post('getcommisonData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#com_authority').text(j[0].authority);
			 $('#com_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
			 $('#type_of_comm_granted').val(j[0].type_of_comm_granted);
			 $('#type_of_comm_grantedlbl').text($( "#type_of_comm_granted option:selected" ).text());
			 $('#date_of_commission').text(convertDateFormate(j[0].date_of_commission));
			 $('#com_id').val(j[0].id);
		 }
	 });
  } 
function Commison_Reject(){
	 var comm_id = '${id}';
	 var com_id = $('#com_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectCommission?' + key + "=" + value, {comm_id:comm_id,com_id:com_id,reject_remarks:reject_remarks}, function(j){
		 
		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_COMMISSION').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	}
function getdob(){
	 var comm_id = '${id}';
	 $.post('getdobData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#b_authority').text(j[0].authority);
			 $('#b_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
			 $('#date_of_birth').text(convertDateFormate(j[0].date_of_birth));
			 $('#birth_id').val(j[0].id);
		 }
	 });
 }
function Birth_Reject(){
	 var comm_id = '${id}';
	 var birth_id = $('#birth_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectBirth?' + key + "=" + value, {comm_id:comm_id,birth_id:birth_id,reject_remarks:reject_remarks}, function(j){

		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_Birth').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }	
	 });
	}
function getarmservice(){
	 var comm_id = '${id}';
	 $.post('getarmsirviceData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#arm_authority').text(j[0].authority);
			 $('#arm_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
			 $('#parent_arm').val(j[0].parent_arm_service);
			 $('#parent_armlbl').text($( "#parent_arm option:selected" ).text());
			 $('#regiment').val(j[0].regt)
			 $('#regimentlbl').text($( "#regiment option:selected" ).text());
			 $('#reg_id').val(j[0].id);
		 }
	 });
 } 
function Armservice_Reject(){
	 var comm_id = '${id}';
	 var reg_id = $('#reg_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('RejectArmService?' + key + "=" + value, {comm_id:comm_id,reg_id:reg_id,reject_remarks:reject_remarks}, function(j){
		 
		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_regiment').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }		
	 });
	}
function getunit(){
	 var comm_id = '${id}';
	 $.post('getunitData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#u_authority').text(j[0].authority);
			 $('#u_date_of_authority').text(convertDateFormate(j[0].date_of_authority));
			 $('#unit_sus_no').text(j[0].unit_sus_no);
			 $('#unit_posted_to').text(j[0].unit_post_to)
			 $('#date_of_tos').text(convertDateFormate(j[0].date_of_tos));
			 $('#u_id').val(j[0].id);
		 }
	 });
}
function Unit_Reject(){
	 var comm_id = '${id}';
	 var u_id = $('#u_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('Unitreject?' + key + "=" + value, {comm_id:comm_id,u_id:u_id,reject_remarks:reject_remarks}, function(j){
		 
		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_unit').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	}
////////
function Rank_Reject(){
	 var comm_id = '${id}';
	 var rank_id = $('#rank_id').val();
	 var reject_remarks = $("#reject_remarks").val();
	 $.post('Rejectrank?' + key + "=" + value, {comm_id:comm_id,rank_id :rank_id ,reject_remarks:reject_remarks}, function(j){

		 if(j == "1"){
			 alert("Data Rejected Successfully");
			 $('#form_rnk').hide();
		 }else {
			 alert("Data Not Rejected"); 
		 }
	 });
	} 
	
/////

function getrank(){

	 var comm_id = '${id}';
	 $.post('getraankData?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
		
			 $('#r_authority').text(j[0].authority);
			 $('#rdate_of_authority').text(convertDateFormate(j[0].date_of_authority));
	
			$('#rdate_of_authority').text(ParseDateColumncommission(j[0].date_of_authority));
			 $('#rank').val(j[0].rank)
			 $('#ranklab').text($( "#rank option:selected" ).text());
			$('#ranklab').text();
			 $('#dt_rk').text(ParseDateColumncommission(j[0].date_of_rank));
			 $('#rank_id').val(j[0].id);
					 	 
		 }
	 });
	}
</script>
