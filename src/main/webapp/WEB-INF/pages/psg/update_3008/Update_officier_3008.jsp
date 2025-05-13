<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
HttpSession sess = request.getSession(false);
if (sess.getAttribute("userId") == null) {
	response.sendRedirect("~/login");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
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
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!-- <script src="js/common/commonmethod.js" type="text/javascript"></script> -->

<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>


</head>
<body>

	<div class="container-fluid" align="center">

		<div class="animated fadeIn">
			<div class="" align="center">
				<div class="card">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default" id="insp_div1">

							<div class="panel-heading">
								<h4 class="panel-title main_title" id="insp_div5">
									<b>UPDATE DATA OF OFFR 3008 
								</h4>
							</div>
							<div class="col-md-12"
								style="padding-top: 5px; padding-left: 250px;">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> <strong
												style="color: red;">* </strong>Personal No
											</label>
										</div>
										<div class="col-md-8">
			
											<input type="text" id="personnel_no" name="personnel_no"
												class="form-control autocomplete " autocomplete="off"
												readonly> <input type="hidden" id="comm_id"
												name="comm_id" class="form-control autocomplete"
												autocomplete="off"> <input type="hidden"
												id="census_id" name="census_id" value="0"
												class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="status" name="status" value="0"
												class="form-control autocomplete" autocomplete="off">
												<input type="hidden" id="roleAccess" name="roleAccess"
												value="0" class="form-control" />
										</div>
									</div>
								</div>
							</div>
							<!-- janki -->
							<input type="hidden" id="personnel_noV" name="personnel_noV"
								class="form-control autocomplete"> <input type="hidden"
								id="statusV" name="statusV" class="form-control autocomplete">
							<input type="hidden" id="rankV" name="rankV"
								class="form-control autocomplete"> <input type=hidden
								id="comm_idV" name="comm_idV" class="form-control autocomplete">
							<input type="hidden" id="unit_nameV" name="unit_nameV"
								class="form-control autocomplete"> <input type="hidden"
								id="unit_sus_noV" name="unit_sus_noV"
								class="form-control autocomplete"> <input type="hidden"
								id="cr_byV" name="cr_byV" class="form-control autocomplete">
							<input type="hidden" id="cr_dateV" name="cr_dateV"
								class="form-control autocomplete">

							<div class="card-footer" align="right" class="form-control"
								id="back_bt" style="display: none;">
								<a href="Search_Report_3008Url" class="btn btn-info btn-sm">Back</a>
							</div>



						</div>
					</div>
				</div>
				<!--  change in appointment start -->
				<form id="form_change_of_appointment">
					<div class="card">
						<div class="panel-group" id="accordion41">
							<div class="panel panel-default" id="c_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title red" id="c_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion41" href="#" id="c_final"
											onclick="divN(this)"><b>CHANGE IN APPOINTMENT</b></a>
									</h4>
								</div>
								<div id="collapse1c" class="panel-collapse collapse">
									<div class="card-body card-block">

										<input type="button" name="save"
											class="btn btn-primary btn-sm" value="View History"
											onclick="Pop_Up_History('Appointment');" />

										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label for="Authority"> <strong
																style="color: red;">* </strong>Authority
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="appt_authority"
																name="appt_authority" class="form-control"
																autocomplete="off" maxlength="100"
																onkeyup="return specialcharecter(this)"> <input
																type="hidden" id="appoint_id" name="appoint_id"
																value="0" class="form-control" autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label for="Date Autho"><strong
																style="color: red;">* </strong> Date of Authority </label>
														</div>
														<div class="col-md-8">
															<input type="text" name="appt_date_of_authority"
																id="appt_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
																style="color: red;">* </strong>Appointment </label>
														</div>
														<div class="col-md-8">
															<select id="appointment" name="appointment"
																class="form-control" onchange="appointment_att();">
																<option value="0">--Select--</option>
																<c:forEach var="item"
																	items="${getTypeofAppointementList}" varStatus="num">
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
																style="color: red;">* </strong> Date of Appointment </label>
														</div>
														<div class="col-md-8">
															<input type="text" name="date_of_appointment"
																id="date_of_appointment" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
											<div class="card-body" style="display: none;" id="Note">
												<p align="left" style="color: #ff0730fc; font-weight: bold;">Note
													: Please Enter X-List SUS No Or X-List Unit Name Or X-List
													Location.</p>
											</div>
											<div class="col-md-12" style="display: none;" id="att_dapu">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> X-List SUS No</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="parent_sus_no"
																name="parent_sus_no" class="form-control autocomplete"
																autocomplete="off" maxlength="8"
																onkeypress="return AvoidSpace(event)"
																onkeyup="return specialcharecter(this)">

														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> X-List Unit
																Name</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="parent_unit" name="parent_unit"
																class="form-control autocomplete" autocomplete="off"
																maxlength="50" onkeyup="return specialcharecter(this)">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12" style="display: none;" id="att_dapu1">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> X-List
																Location</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="x_list_loc" name="x_list_loc"
																class="form-control autocomplete" autocomplete="off"
																maxlength="50" onkeyup="return specialcharecter(this)">
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="card-footer" align="center" class="form-control">
											<input type="button" name="save"
												class="btn btn-primary btn-sm" value="Save/Approve"
												onclick=" validate_appointment_save();" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!--  change in appointment end -->

				<!--  change in rank start -->

				<div class="card">
					<div class="panel-group" id="accordion4">
						<div class="panel panel-default" id="a_div1">
							<div class="panel-heading">
								<h4 class="panel-title main_title red" id="a_div5">
									<a class="droparrow collapsed" data-toggle="collapse"
										data-parent="#accordion4" href="#" id="a_final"
										onclick="divN(this)"><b>CHANGE IN RANK</b></a>
								</h4>
							</div>
							<div id="collapse1a" class="panel-collapse collapse">
								<div class="card-body card-block">
									<br> <input type="button" class="btn btn-primary btn-sm"
										value="View History" onclick="Pop_Up_History('rank');" />
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"> <strong
															style="color: red;">* </strong>Authority
														</label>
													</div>
													<div class="col-md-8">
														<input type="text" id="r_authority" name="authority"
															class="form-control autocomplete" autocomplete="off"
															onkeyup="return specialcharecter(this)" maxlength="100">
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
														<input type="hidden" id="ch_r_id" name="ch_r_id" value="0"
															class="form-control" /> <input type="hidden"
															id="comm_id" name="comm_id" value="0"
															class="form-control" /> <input type="text"
															name="r_date_of_authority" id="r_date_of_authority"
															maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
															class="form-control" style="width: 83%; display: inline;"
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
															style="color: red;">* </strong> Rank Type
														</label>
													</div>
													<div class="col-md-8">
														<select name="rank_type" id="rank_type"
															class="form-control">
															<option value="0">--Select--</option>
															<c:forEach var="item" items="${getOFFTypeofRankList}"
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
														<label class=" form-control-label"> <strong
															style="color: red;">* </strong>Rank
														</label>
													</div>
													<div class="col-md-8">
														<select name="rank" id="rank" class="form-control">
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
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"> <strong
															style="color: red;">* </strong> Date of Rank
														</label>
													</div>
													<div class="col-md-8">
														<input type="text" name="date_of_rank" id="date_of_rank"
															maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
															class="form-control" style="width: 83%; display: inline;"
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
										<input type="button" class="btn btn-primary btn-sm"
											value="Save/Approve" onclick="validate_ChngofRank_save();">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--  change in rank end -->

				<!--  change in name start -->
				<form id="form_change_of_name">
					<div class="card">
						<div class="panel-group" id="accordion5">
							<div class="panel panel-default" id="b_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title red" id="b_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion5" href="#" id="b_final"
											onclick="divN(this)"><b>CHANGE IN NAME</b></a>
									</h4>
								</div>
								<div id="collapse1b" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" name="save"
											class="btn btn-primary btn-sm" value="View History"
											onclick="Pop_Up_History('Name');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Authority
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="na_authority" name="authority"
																class="form-control autocomplete" autocomplete="off"
																maxlength="100" onkeyup="return specialcharecter(this)">
															<input type="hidden" id="name_id" name="name_id"
																value="0" class="form-control autocomplete"
																autocomplete="off">
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
															<input type="text" name="date_of_authority"
																id="na_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
																style="color: red;">* </strong>Name
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="name" name="name"
																class="form-control autocomplete" autocomplete="off"
																maxlength="50" onkeypress="return onlyAlphabets(event);"
																oninput="this.value = this.value.toUpperCase()">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Date of Change in Name
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="change_in_name_date"
																id="change_in_name_date" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
											<input type="button" class="btn btn-primary btn-sm"
												value="Save/Approve" onclick="validate_Change_of_name_save_fn();">

										</div>
									</div>
									<!-- 	  <div class="card-footer" align="left" class="form-control">
								<p><u><b>Note:</b></u> Name should be as per Commission Offr Information. </p> 	
			            	</div>  -->
								</div>
							</div>
						</div>
					</div>
				</form>
				<!--  change in name end -->

				<!-- START CHANGE OF COMMISSION DETAIL -->
				<form id="forminspupdate">
					<div class="card">
						<div class="panel-group" id="accordion7">
							<div class="panel panel-default" id="y_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title red" id="y_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion7" href="#" id="y_final"
											onclick="divN(this)"><b>SSC TO PERMT COMMISSION</b></a>
									</h4>
								</div>
								<div id="collapse1y" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" class="btn btn-primary btn-sm"
											value="View History"
											onclick="Pop_Up_History('permt_commission');" />

										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Authority</label>
														</div>
														<div class="col-md-8">
															<input type="hidden" id="coc_ch_id" name="coc_ch_id"
																value="0" class="form-control autocomplete"
																autocomplete="off"> <input type="text"
																id="coc_authority" name="authority"
																class="form-control autocomplete" autocomplete="off"
																maxlength="100" onkeyup="return specialcharecter(this)">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;"> *</strong> Date of Authority</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="date_of_authority"
																id="coc_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
													<label for="text-input" class=" form-control-label"><strong
														style="color: red;"> *</strong>New Personal No</label>
												</div>
												<div class="col-md-10">
													<div class="row form-group">
														<div class="col-md-4">
															<select id="persnl_no1" name="persnl_no1"
																class="form-control">
																<option value="-1">--Select--</option>
																<c:forEach var="item" items="${getPersonalType}"
																	varStatus="num">
																	<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
																</c:forEach>
															</select>
														</div>
														<div class="col-md-4">
															<input type="text" id="persnl_no2" name="persnl_no2"
																onkeyup="onChangePerNo(this);" class="form-control"
																autocomplete="off" placeholder="Enter No..."
																maxlength="5" onkeypress="return isNumber0_9Only(event)">
														</div>
														<div class="col-md-4">
															<input type="text" id="persnl_no3" name="persnl_no3"
																class="form-control" autocomplete="off" maxlength="10"
																readonly="readonly">
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;"> </strong> Previous Commission
																(Type)</label>
														</div>

														<input type="hidden" id="coc_old_previous_commission"
															name="coc_old_previous_commission"
															class="form-control autocomplete" autocomplete="off">
														<input type="hidden" id="coc_old_personal_no"
															name="coc_old_personal_no"
															class="form-control autocomplete" autocomplete="off">

														<div class="col-md-8" id="coc_previous_commission">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;"> *</strong> Type of Commission
																Granted</label>
														</div>
														<div class="col-md-8">
															<select name="type_of_commission_granted"
																id="type_of_commission_granted" class="form-control">
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getTypeOfCommissionList}"
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
																style="color: red;">* </strong> Date of Permanent
																Commission</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="date_of_permanent_commission"
																id="coc_date_of_permanent_commission" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Date of Seniority</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="date_of_seniority"
																id="coc_date_of_seniority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
																style="color: red;">* </strong>Date From Which Change in
																Seniority is Effective</label>
														</div>
														<div class="col-md-8">
															<input type="text" name="eff_date_of_seniority"
																id="eff_coc_date_of_seniority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
										<div id="coc_bt_div">
											<div class="card-footer" align="center" class="form-control">
												<input id="btnsave" type="button"
													class="btn btn-primary btn-sm" value="Save/Approve"
													onclick="validate_insertSeniorityAction_save()">
											</div>
										</div>
										<div class="card-footer" align="left" class="form-control">
											<!--  <p style="color: red;"><u><b>Note:</b></u> Previous Type of commission SSC then Submit for approval show. </p>  	 -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END CHANGE OF COMMISSION DETAIL -->
				<!-- START cda accountno -->
				<form id="form_cda_accountno">
					<div class="card">
						<div class="panel-group" id="accordion15">
							<div class="panel panel-default" id="k_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title red" id="k_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion15" href="#" id="k_final"
											onclick="divN(this)"><b>CHANGE IN CDA ACCOUNT NO</b></a>
									</h4>
								</div>
								<div id="collapse1k" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" class="btn btn-primary btn-sm"
											value="View History" onclick="Pop_Up_History('CDA');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Authority</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="co_authority" name="co_authority"
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
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Name </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="cadet_name"></label>
															<input type="hidden" id="cname" name="cname"
																class="form-control autocomplete" autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> CDA Account No</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="cda_acc_no" name="cda_acc_no"
																class="form-control autocomplete" maxlength="20"
																autocomplete="off"
																onkeyup="return specialcharecterCDA(this)"
																onkeypress="return AvoidSpace(event)"> <input
																type="hidden" id="co_id" name="co_id" value="0"
																class="form-control" autocomplete="off">

														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Rank </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="p_rank"></label> <input
																type="hidden" id="hd_p_rank" name="hd_p_rank"
																class="form-control autocomplete" autocomplete="off">
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
															<label class=" form-control-label" id="app_unit_name"></label>
															<input type="hidden" id="un" name="un"
																class="form-control autocomplete" autocomplete="off">
														</div>
													</div>
												</div>

											</div>

										</div>
										<div class="card-footer" align="center" class="form-control">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save/Approve" onclick="validate_cda_account_no();">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END Course  -->

				<!-- 	START INTER ARM -->
				<form id="forminspupdate">
					<div class="card">
						<div class="panel-group" id="accordion21">
							<div class="panel panel-default" id="x_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="x_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion21" href="#" id="x_final"
											onclick="divN(this)"><b>INTER ARM/SERVICE TRANSFER</b></a>
									</h4>
								</div>
								<div id="collapse1x" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" class="btn btn-primary btn-sm"
											value="View History"
											onclick="Pop_Up_History('inter_arm_service_transfer');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Authority</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="inter_arm_authority"
																name="authority" class="form-control autocomplete"
																autocomplete="off" maxlength="100"
																onkeyup="return specialcharecter(this)"> <input
																type="hidden" id="p_id" name="p_id"
																class="form-control autocomplete" value="0"
																autocomplete="off" maxlength="50">
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
															<input type="text" name="date_of_authority"
																id="inter_arm_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
																style="color: red;"> </strong> From Old Arm/Service </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label"
																id="inter_arm_old_arm_service"></label> <select
																name="old_arm_service"
																id="inter_arm_old_arm_service_val" class="form-control"
																style="display: none;">
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getParentArmList}"
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
																style="color: red;"> </strong> Old Regt (INF)</label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label"
																id="inter_arm_old_regt"></label> <select name="old_regt"
																id="inter_arm_old_regt_val" class="form-control"
																style="display: none;">
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getRegtList}"
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
																style="color: red;">*</strong> To Present Arm/Service</label>
														</div>
														<div class="col-md-8">
															<select name="parent_arm_service"
																id="inter_arm_parent_arm_service" class="form-control"
																onchange="chgarm(this,'inter_arm_regt');">
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getParentArmList}"
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
																style="color: red;"> *</strong> New Regt (INF) </label>
														</div>
														<div class="col-md-8">
															<select name="regt" id="inter_arm_regt"
																class="form-control" disabled="disabled">
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
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> With Effect From </label>
														</div>
														<div class="col-md-8">
															<input type="text" name="with_effect_from"
																id="inter_arm_with_effect_from" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
											<input type="button" class="btn btn-primary btn-sm"
												value="Save/Approve"
												onclick="validate_inter_arm_transfer_save();">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- 	END INTER ARM -->

				<!-- START MEDICAL -->
				<form id="madical_category_form">
					<div class="card">
						<div class="panel-group" id="accordion32">
							<div class="panel panel-default" id="s_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="s_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion32" href="#" id="s_final"
											onclick="divN(this)"><b>CHANGE IN MEDICAL</b></a>
									</h4>
								</div>
								<div id="collapse1s" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" class="btn btn-primary btn-sm"
											value="View History"
											onclick="Pop_Up_History('Change_Medicle');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-6">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Authority</label>
														</div>
														<div class="col-md-6">
															<!-- 					 <input type="hidden" id="med_cat_ch_id" name="med_cat_ch_id" value="0" class="form-control autocomplete" autocomplete="off" > 							                      -->

															<input type="text" id="madical_authority" maxlength="100"
																name="madical_authority"
																class="form-control autocomplete"
																onkeyup="return specialcharecter(this)"
																autocomplete="off">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-6">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Date of Authority</label>
														</div>
														<div class="col-md-6">
															<!-- 							<input type="date" id="madical_date_of_authority" -->
															<%-- 								name="madical_date_of_authority" max="${date}" min='1899-01-01' --%>
															<!-- 								class="form-control autocomplete" autocomplete="off"> -->

															<input type="text" name="madical_date_of_authority"
																id="madical_date_of_authority" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">


														</div>
													</div>
												</div>
											</div>
											<input type="hidden" id="mad_classification_count"
												name="mad_classification_count" value="NIL">
											<div class="col-md-12">
												<div class="row form-group">
													<div class="col-md-12"
														style="font-size: 15px; margin: 10px;">
														<input type="checkbox" id="check_1bx" name="check_1bx"
															onclick="oncheck_1bx()" value="1BX"> <label
															for="text-input" class=" form-control-label"
															style="margin-left: 10px;"><strong>
																SHAPE 1BX </strong></label>
													</div>
												</div>
												<input type="hidden" id="shape_1bx_id" name="shape_1bx_id"
													value="0" class="form-control autocomplete"
													autocomplete="off">
											</div>
											<div class="col-md-12" id="shape1bxdiv"
												style="display: none;">

												<div class="col-md-4">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>From Date</label>
														</div>
														<div class="col-md-8">
															<%-- 						<input type="date" id="1bx_from_date" name="1bx_from_date" max="${date_without_time}" class="form-control autocomplete" autocomplete="off"> --%>

															<input type="text" name="1bx_from_date"
																id="1bx_from_date" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 80%; display: inline;"
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
																class="form-control"
																style="width: 80%; display: inline;"
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
														<div class="col-md-5">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Diagnosis</label>
														</div>
														<div class="col-md-7">
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
													<strong style="color: red;">* </strong><b>S -
														Psychological & Congnitive</b>
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
																<td style=""><select name="s_status1"
																	id="s_status1" class="form-control-sm form-control"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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

																<td style="width: 10%; display: none;"
																	class="addbtClass11"><a
																	class="btn btn-success btn-sm" value="ADD" title="ADD"
																	id="sShape_add1" onclick="sShape_add_fn(1);"><i
																		class="fa fa-plus"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>

												<br />

												<div class="card-header-inner"
													style="text-align: center; margin-bottom: 20px;">
													<strong style="color: red;">* </strong><b>H - Hearing</b>


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
																<td style=""><select name="h_status1"
																	id="h_status1" class="form-control-sm form-control"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

																</td>
																<td style="display: none;" class="diagnosisClass21">
																	<input type="text" name="_diagnosis_code21"
																	id="_diagnosis_code21"
																	class="form-control-sm form-control" autocomplete="off"
																	placeholder="Search..."
																	onkeypress="AutoCompleteDiagnosis(this);">
																</td>
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

																<td style="width: 10%; display: none;"
																	class="addbtClass21"><a
																	class="btn btn-success btn-sm" value="ADD" title="ADD"
																	id="hShape_add1" onclick="hShape_add_fn(1);"><i
																		class="fa fa-plus"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>


												<br />
												<div class="card-header-inner"
													style="text-align: center; margin-bottom: 20px;">

													<strong style="color: red;">* </strong><b>A -
														Appendages</b>
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
																<td style=""><select name="a_status1"
																	id="a_status1" class="form-control-sm form-control"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

																</td>
																<td style="display: none;" class="diagnosisClass31">
																	<input type="text" name="_diagnosis_code31"
																	id="_diagnosis_code31"
																	class="form-control-sm form-control" autocomplete="off"
																	placeholder="Search..."
																	onkeypress="AutoCompleteDiagnosis(this);">
																</td>
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

																<td style="width: 10%; display: none;"
																	class="addbtClass31"><a
																	class="btn btn-success btn-sm" value="ADD" title="ADD"
																	id="aShape_add1" onclick="aShape_add_fn(1);"><i
																		class="fa fa-plus"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>


												<br />
												<div class="card-header-inner"
													style="text-align: center; margin-bottom: 20px;">
													<strong style="color: red;">* </strong><b>P - Physical
														Capacity</b>
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
																<td style=""><select name="p_status1"
																	id="p_status1" class="form-control-sm form-control"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

																</td>
																<td style="display: none;" class="diagnosisClass41">
																	<input type="text" name="_diagnosis_code41"
																	id="_diagnosis_code41"
																	class="form-control-sm form-control" autocomplete="off"
																	placeholder="Search..."
																	onkeypress="AutoCompleteDiagnosis(this);">
																</td>
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

																<td style="width: 10%; display: none;"
																	class="addbtClass41"><a
																	class="btn btn-success btn-sm" value="ADD" title="ADD"
																	id="pShape_add1" onclick="pShape_add_fn(1);"><i
																		class="fa fa-plus"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>

												<br />

												<div class="card-header-inner"
													style="text-align: center; margin-bottom: 20px;">
													<strong style="color: red;">* </strong><b>E - Eye Sight</b>

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
																<td style=""><select name="e_status1"
																	id="e_status1" class="form-control-sm form-control"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
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
																	class="form-control"
																	style="width: 85%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">

																</td>
																<td style="display: none;" class="diagnosisClass51">
																	<input type="text" name="_diagnosis_code51"
																	id="_diagnosis_code51"
																	class="form-control-sm form-control" autocomplete="off"
																	placeholder="Search..."
																	onkeypress="AutoCompleteDiagnosis(this);">
																</td>
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

																<td style="width: 10%; display: none;"
																	class="addbtClass51"><a
																	class="btn btn-success btn-sm" value="ADD" title="ADD"
																	id="eShape_add1" onclick="eShape_add_fn(1);"><i
																		class="fa fa-plus"></i></a></td>
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
																<td style=""><select name="c_cvalue1"
																	id="c_cvalue1"
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

																<!-- 						                                <td style="" > -->
																<!-- 														 <input type="text" name="c_cdescription1" id="c_cdescription1" class="form-control-sm form-control" autocomplete="off"  -->
																<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
																<!-- 														   </td> -->


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
																<td style=""><select name="c_ovalue1"
																	id="c_ovalue1" onchange="onCopeChangebt(this,2,1);"
																	class="form-control-sm form-control">
																		<!-- 																<option value="0">--Select--</option>																 -->
																		<c:forEach var="item" items="${getoCopeList}"
																			varStatus="num">
																			<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																		</c:forEach>
																</select></td>



																<!-- 						                                <td style="" > -->
																<!-- 														 <input type="text" name="c_odescription1" id="c_odescription1" class="form-control-sm form-control" autocomplete="off"  -->
																<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
																<!-- 														   </td> -->


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
																<td style=""><select name="c_pvalue1"
																	id="c_pvalue1" onchange="onCopeChangebt(this,3,1);"
																	class="form-control-sm form-control">
																		<!-- 																<option value="0">--Select--</option>																 -->
																		<c:forEach var="item" items="${getpCopeList}"
																			varStatus="num">
																			<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
																		</c:forEach>
																</select></td>



																<!-- 						                                <td style="" > -->
																<!-- 														 <input type="text" name="c_pdescription1" id="c_pdescription1" class="form-control-sm form-control" autocomplete="off"  -->
																<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
																<!-- 														   </td> -->


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
																<td style=""><select name="c_evalue1"
																	id="c_evalue1"
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

																<!-- 						                                <td style="" > -->
																<!-- 														 <input type="text" name="c_edescription1" id="c_edescription1" class="form-control-sm form-control" autocomplete="off"  -->
																<!-- 														  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">						                               -->
																<!-- 														   </td> -->


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
										<div class="card-footer" align="center" class="form-control">
											<input type="button" class="btn btn-primary btn-sm"
												value="Save/Approve" onclick="return medical_cat_save();">
										</div>
									</div>
								</div>
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
				<!-- 	End Medical -->

				
		<!-- 	START Birth-->
				<form id="form_Birth">
					<div class="card">
						<div class="panel-group" id="accordion22">
							<div class="panel panel-default" id="d_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="d_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion22" href="#" id="d_final"
											onclick="divN(this)"><b>DATE OF BIRTH</b></a>
									</h4>
								</div>
								<div id="collapse1d" class="panel-collapse collapse">
									<div id="hidedivdob">
										<div class="card-body card-block">
											<br> <input type="button" class="btn btn-primary btn-sm"
												value="View History"
												onclick="Pop_Up_History('date_of_birth');" />

											<div class="row">
												<div class="col-md-12">
													<div class="col-md-6">
														<div class="row form-group">
															<div class="col-md-4">
																<label class=" form-control-label"><strong
																	style="color: red;">* </strong>Authority</label>
															</div>
															<div class="col-md-8">
																<input type="text" id="b_authority" name="authority"
																	class="form-control-sm form-control autocomplete"
																	autocomplete="off" maxlength="100"
																	onkeyup="return specialcharecter(this)"> <input
																	type="hidden" id="birth_id" name="birth_id" value="0"
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
																<label class=" form-control-label"> <strong
																	style="color: red;">* </strong>Date of Birth
																</label>
															</div>
															<div class="col-md-8">
																<input type="text" name="date_of_birth"
																	id="date_of_birth" maxlength="10"
																	onclick="clickclear(this, 'DD/MM/YYYY')"
																	class="form-control-sm form-control"
																	style="width: 93%; display: inline;"
																	onfocus="this.style.color='#000000'"
																	onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																	onkeyup="clickclear(this, 'DD/MM/YYYY')"
																	onchange="clickrecall(this,'DD/MM/YYYY');validateAge(this);"
																	aria-required="true" autocomplete="off"
																	style="color: rgb(0, 0, 0);">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="card-footer" align="center" class="form-control">
												<input type="button" class="btn btn-primary btn-sm"
													value="Save/Approve" onclick="validate_Birth();">
											</div>
										</div>

									</div>
								
										<div class="col-md-12" id="hidedivdobunit">
											<div class="row form-group">
												<div class="col-md-2">
													<label class="form-control-label"> NOTE :</label>
												</div>
												<div>

													<label style="color: red;">Pl contact MISO at 39861
														or raise ticket to updt this fd along with relevant docus</label>
												</div>
											</div>						

									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- 	END Birth -->

				<!-- START COMMISSION -->
				<form id="form_COMMISSION">
					<div class="card">
						<div class="panel-group" id="accordion45">
							<div class="panel panel-default" id="r_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="r_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion45" href="#" id="r_final"
											onclick="divN(this)"> <b>COMMISSION</b></a>
									</h4>
								</div>
								<div id="collapse1r" class="panel-collapse collapse">
								
									<div class="card-body card-block" id="hidecommdiv">
										<br> <input type="button" class="btn btn-primary btn-sm"
											value="View History" onclick="Pop_Up_History('commission');" />
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong>Authority</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="com_authority"
																name="com_authority"
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
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Type of Commission
																Granted
															</label>
														</div>
														<div class="col-md-8">
															<input type="hidden" id="com_id" name="com_id" value="0"
																class="form-control autocomplete" autocomplete="off">
															<select name="type_of_comm_granted"
																id="type_of_comm_granted"
																class="form-control-sm form-control">
																<option value="0">--Select--</option>
																<c:forEach var="item" items="${getTypeOfCommissionList}"
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
																style="color: red;">* </strong> Date of Commission </label>
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
											<input type="button" class="btn btn-primary btn-sm"
												value="Save/Approve" onclick="validate_Commission();">
										</div>
									</div>
										<div class="col-md-12" id="hidecommdivunit">
											<div class="row form-group">
												<div class="col-md-2">
													<label class="form-control-label"> NOTE :</label>
												</div>
												<div>

													<label style="color: red;">Pl contact MISO at 39861
														or raise ticket to updt this fd along with relevant docus</label>
												</div>
											</div>						

									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END COMMISSION-->

				<!-- START date change in sen -->
				<form id="forminspupdate">
					<div class="card">
						<div class="panel-group" id="accordion8">
							<div class="panel panel-default" id="dd_div1">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="dd_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion8" href="#" id="dd_final"
											onclick="divN(this)"><b>CHANGE IN DATE OF SENIORITY</b></a>
									</h4>
								</div>
								<div id="collapse1dd" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br> <input type="button" name="save"
											class="btn btn-primary btn-sm" value="View History"
											onclick="Pop_Up_History('Seniority');" />
										<div class="row">

											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> <strong
																style="color: red;">* </strong>Authority
															</label>
														</div>
														<div class="col-md-8">
															<input type="text" id="sen_authority"
																name="sen_authority" class="form-control autocomplete"
																autocomplete="off"
																onkeyup="return specialcharecter(this)" maxlength="100">
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
															<input type="hidden" id="Sen_id" name="Sen_id" value="0"
																class="form-control" /> <input type="hidden"
																id="comm_id" name="comm_id" value="0"
																class="form-control" /> <input type="text"
																name="sen_date_of_authority" id="sen_date_of_authority"
																maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 83%; display: inline;"
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
															<label class=" form-control-label"> Rank </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="sen_rank"></label>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> Name </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="sen_name"></label>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label">Unit SUS No </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="app_sus_no"></label>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"> Unit Name </label>
														</div>
														<div class="col-md-8">
															<label class=" form-control-label" id="sen_unit_name"></label>
														</div>
													</div>
												</div>

											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;"></strong>Old Date of Seniority </label>
														</div>
														<div class="col-md-8">
															<label id="old_seniority"></label>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="col-md-4">
														<label class=" form-control-label"><strong
															style="color: red;">* </strong> New Date of Seniority </label>
													</div>
													<div class="col-md-8">
														<input type="text" name="date_of_seniority"
															id="date_of_seniority" maxlength="10"
															onclick="clickclear(this, 'DD/MM/YYYY')"
															class="form-control" style="width: 93%; display: inline;"
															onfocus="this.style.color='#000000'"
															onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
															onkeyup="clickclear(this, 'DD/MM/YYYY')"
															onchange="clickrecall(this,'DD/MM/YYYY');"
															aria-required="true" autocomplete="off"
															style="color: rgb(0, 0, 0);">
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Reason </label>
														</div>
														<div class="col-md-8">
															<input type="text" id="reason" name="reason"
																class="form-control-sm form-control autocomplete"
																maxlength="100" autocomplete="off"
																onkeyup="return specialcharecter(this)">
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<label class=" form-control-label"><strong
																style="color: red;">* </strong> Date From Which Change
																in Seniority is Effective </label>
														</div>

														<div class="col-md-8">
															<input type="text" name="date_of_applicability"
																id="date_of_applicability" maxlength="10"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control-sm form-control"
																style="width: 93%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" max="${maxDate}">
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="card-footer" align="center" class="form-control">
											<input id="btnsave" type="button"
												class="btn btn-primary btn-sm" value="Save/Approve"
												onclick="validate_insertSeniority_save();">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END sen -->

				<!-- START date change in tos -->
				<form id="formtosdateupdate">
					<div class="card">
						<div class="panel-group" id="accordion88">
							<div class="panel panel-default" id="td_div5">
								<div class="panel-heading">
									<h4 class="panel-title main_title blue" id="td_div5">
										<a class="droparrow collapsed" data-toggle="collapse"
											data-parent="#accordion88" href="#" id="td_final"
											onclick="divN(this)"><b>CHANGE IN DATE OF TOS </b></a>
									</h4>
								</div>
								<div id="collapse1t" class="panel-collapse collapse">
									<div class="card-body card-block">
										<br>
										<div class="row">

											<!-- 					<div class="col-md-12">
				
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<strong	style="color: red;">* </strong><label class=" form-control-label"> Name </label>
							</div>
							<div class="col-md-8">						
								 <label id="tos_name"></label> 
							</div>
						</div>
					</div>
				 	<div class="col-md-6">
							<div class="row form-group">
								 <div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> Rank </label>
								</div>
								<div class="col-md-8">
									<label id="rank"></label>
									<input type="hidden" id="tos_rank1" name="tos_rank1" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
				</div> -->

											<!-- 	<div class="col-md-12">
					  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong	style="color: red;">* </strong><label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">
									<label id="tos_from_sus_no" name="tos_from_sus_no"></label> 
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
								<strong	style="color: red;">* </strong>	<label class=" form-control-label"> From Unit Name </label>
 								</div>
 								<div class="col-md-8"> 
									 <label id="tos_from_unit_name"></label> 									 
 								</div> 
							</div>
						</div>
					</div>			 -->
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<strong style="color: red;">* </strong><label
																class=" form-control-label"> SUS No </label>
														</div>
														<div class="col-md-8">
															<input type="hidden" id="dt_tos_pre" name="dt_tos_pre"
																value="" class="form-control autocomplete"
																autocomplete="off" /> <input type="hidden" id="tos_id"
																name="tos_id" value="" class="form-control autocomplete"
																autocomplete="off" /> <input type="hidden"
																id="tos_preid" name="tos_preid" value=""
																class="form-control autocomplete" autocomplete="off" />
															<label id="tos_to_sus_no"></label>
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<strong style="color: red;">* </strong> <label
																class=" form-control-label"> Unit Name</label>
														</div>
														<div class="col-md-8">
															<label id="tos_to_unit_name"></label>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-12">
												<div class="col-md-6">
													<div class="row form-group">
														<div class="col-md-4">
															<strong style="color: red;">* </strong><label
																class=" form-control-label"> Date of TOS </label>
														</div>
														<div class="col-md-8">
															<input type="text" name="tos_dt_of_tos"
																id="tos_dt_of_tos" maxlength="10" value="DD/MM/YYYY"
																onclick="clickclear(this, 'DD/MM/YYYY')"
																class="form-control"
																style="width: 85%; display: inline;"
																onfocus="this.style.color='#000000'"
																onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
																onkeyup="clickclear(this, 'DD/MM/YYYY')"
																onchange="clickrecall(this,'DD/MM/YYYY')"
																aria-required="true" autocomplete="off"
																style="color: rgb(0, 0, 0);" />
														</div>
													</div>
												</div>
												<div class="col-md-6" id="">
													<div class="row form-group">
														<div class="col-md-4">
															<strong style="color: red;"> </strong><label
																class=" form-control-label">Unit Description </label>
														</div>
														<div class="col-md-8">
															<label id="unit_description"> </label>
														</div>
													</div>
												</div>
											</div>
										</div>

										<div class="card-footer" align="center" class="form-control">
											<input id="btnsave" type="button"
												class="btn btn-primary btn-sm" value="Save/Approve"
												onclick="validate_tos_date_for_edit();">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!-- END tos -->

			</div>
		</div>




		<!-- POPUP History -->
		<c:url value="Preview_PersonnelNo_Url" var="Preview_PersonnelNo" />
		<form:form action="${Preview_PersonnelNo}" method="post"
			id="Preview_PersonnelNo_Form" name="Preview_PersonnelNo_Form"
			modelAttribute="id" target="result">
			<input type="hidden" name="personnel_no1" id="personnel_no1"
				value="0" />
		</form:form>

		<c:url value="Change_Of_RankUrl" var="Change_Of_RankUrl" />
		<form:form action="${Change_Of_RankUrl}" method="post"
			id="Change_Rank_Form" name="Change_Rank_Form" target="result">
			<input type="hidden" name="rank_comm_id" id="rank_comm_id" value="0" />
			<input type="hidden" name="rank_census_id" id="rank_census_id"
				value="0" />
		</form:form>

		<!-- /////////// Change_Name_Form //////////////// -->

		<c:url value="Change_In_NameUrl" var="Change_In_NameUrl" />
		<form:form action="${Change_In_NameUrl}" method="post"
			id="Change_Name_Form" name="Change_Name_Form" modelAttribute="id"
			target="result">
			<input type="hidden" name="name_comm_id" id="name_comm_id" value="0" />
			<input type="hidden" name="name_census_id" id="name_census_id"
				value="0" />
		</form:form>


		<!-- ///////////////////////CHANGE IN APPOINTMENT///////////// -->
		<c:url value="Change_In_Appointment_3008Url"
			var="Change_In_Appointment_3008Url" />
		<form:form action="${Change_In_Appointment_3008Url}" method="post"
			id="Change_Appointment_Form" name="Change_Appointment_Form"
			modelAttribute="id" target="result">
			<input type="hidden" name="appointment_comm_id"
				id="appointment_comm_id" value="0" />
			<input type="hidden" name="appointment_census_id"
				id="appointment_census_id" value="0" />
		</form:form>




		<!-- ///////////////////////CHANGE IN MEDICLE DETAILS///////////// -->
		<c:url value="Change_In_MedicleUrl" var="Change_In_MedicleUrl" />
		<form:form action="${Change_In_MedicleUrl}" method="post"
			id="Change_In_Medicle_Form" name="Change_In_Medicle_Form"
			target="result">
			<input type="hidden" name="cim_comm_id" id="cim_comm_id" value="0" />
			<input type="hidden" name="cim_census_id" id="cim_census_id"
				value="0" />
		</form:form>

		<!-- ///////////  Inter_Arm_Service_Transfer_Form //////////////// -->

		<c:url value="Inter_Arm_Service_TransferUrl"
			var="Inter_Arm_Service_TransferUrl" />
		<form:form action="${Inter_Arm_Service_TransferUrl}" method="post"
			id="Inter_Arm_Service_Transfer_Form"
			name="Inter_Arm_Service_Transfer_Form" modelAttribute="id"
			target="result">
			<input type="hidden" name="as_comm_id" id="as_comm_id" value="0" />
			<input type="hidden" name="as_census_id" id="as_census_id" value="0" />
		</form:form>

		<!-- ///////////  Ssc_To_Permit_Commission_Form //////////////// -->

		<c:url value="Ssc_To_Permit_CommissionUrl"
			var="Ssc_To_Permit_CommissionUrl" />
		<form:form action="${Ssc_To_Permit_CommissionUrl}" method="post"
			id="Ssc_To_Permit_Commission_Form"
			name="Ssc_To_Permit_Commission_Form" modelAttribute="id"
			target="result">
			<input type="hidden" name="ssc_comm_id" id="ssc_comm_id" value="0" />
			<input type="hidden" name="ssc_census_id" id="ssc_census_id"
				value="0" />
		</form:form>

		<!-- ///////////  change in date //////////////// -->
		<c:url value="Preview_DateofBirth_Url" var="Preview_CadetNo" />
		<form:form action="${Preview_CadetNo}" method="post"
			id="Preview_DateofBirth_Form" name="Preview_DateofBirth_Form"
			modelAttribute="id" target="result">
			<input type="hidden" name="comm_id5" id="comm_id5" value="0" />
			<input type="hidden" name="personnel_no5" id="personnel_no5"
				value="0" />
		</form:form>



		<input type="hidden" id="comm_date" name="comm_date"
			value="'${comm_date }'" class="form-control" /> <input type="hidden"
			class=" form-control-label" id="tos_date">
		<!-- cda account no -->
		<c:url value="Popup_CDAUrl" var="Popup_CDAURL" />
		<form:form action="${Popup_CDAURL}" method="post" id="popup_cda"
			name="popup_cda" modelAttribute="id" target="result">
			<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
		</form:form>

		<c:url value="Popup_SeniorityUrl_3008" var="Popup_SeniorityURL" />
		<form:form action="${Popup_SeniorityURL}" method="post"
			id="popup_seniority" name="popup_seniority" modelAttribute="id"
			target="result">
			<input type="hidden" name="comm_id11" id="comm_id11" value="0" />
		</form:form>

		<c:url value="Preview_Commission_Url" var="Preview_CadetNo" />
		<form:form action="${Preview_CadetNo}" method="post"
			id="Preview_Commission_Form" name="Preview_Commission_Form"
			modelAttribute="id" target="result">
			<input type="hidden" name="comm_id4" id="comm_id4" value="0" />
			<input type="hidden" name="personnel_no4" id="personnel_no4"
				value="0" />
		</form:form>


	</div>
</body>
</html>
<script>
$(document).ready(function() {
	jQuery(function($){ 	
		datepicketDate('r_date_of_authority');
		datepicketDate('date_of_rank');
		 datepicketDate('p_date_of_authority');  		
		 datepicketDate('appt_date_of_authority');  		
		 datepicketDate('na_date_of_authority');  		
		 datepicketDate('date_of_appointment');  		
		 datepicketDate('inter_arm_date_of_authority');  		
		 datepicketDate('inter_arm_with_effect_from');  		
		 datepicketDate('coc_date_of_authority');  		
		 datepicketDate('eff_coc_date_of_seniority');  		
		 datepicketDate('coc_date_of_seniority');  		
		 datepicketDate('coc_date_of_permanent_commission'); 
		 
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
		 datepicketDate('change_in_name_date');
		 datepicketDate('change_in_rel_date');		 
		 datepicketDate('1bx_from_date');
		 datepicketDate('1bx_to_date');
		 
		 datepicketDate('com_date_of_authority'); 
		 datepicketDate('date_of_commission'); 
		 
		 
		 datepicketDate('b_date_of_authority'); 
		 datepicketDate('date_of_birth');
		 datepicketDate('c_date_of_authority'); 
		 datepicketDate('co_date_of_authority'); 
		 datepicketDate('sen_date_of_authority');
		 datepicketDate('date_of_seniority');
		 datepicketDate('date_of_applicability');
		 datepicketDate('tos_dt_of_tos');
		 
		 
		// datepicketDate('date_of_birth1');//kajal
		 $( "#1bx_to_date" ).datepicker( "option", "maxDate", null);		
		 $( "#s_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#h_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#a_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#p_to_date1" ).datepicker( "option", "maxDate", null);
		 $( "#e_to_date1" ).datepicker( "option", "maxDate", null);
		 // medical ends
	});
	$("#comm_id").val('${comm_id}');
	$("#roleAccess").val('${roleAccess}');
	$("#census_id").val('${census_id}');
	$("#comm_date").val('${comm_date}');
	$("#personnel_no").val('${personnel_no}');
	$("#tos_date").val('${tos_date}');
	$("select#inter_arm_old_arm_service").val('${parent_arm}');
	$("select#inter_arm_old_regt").val('${regiment}');
	 var personnel_no = $("input#personnel_no").val();
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		 if(j!=""){
	    	$("#comm_id").val(j[0][0]);
	    	//alert("hii--" + j[0][8]);
	    	$('#inter_arm_old_arm_service_val').val(j[0][8]);
			$('#inter_arm_old_arm_service').text($( "#inter_arm_old_arm_service_val option:selected" ).text());
	    	if(j[0][9] != 0){
	    		$('#inter_arm_old_regt_val').val(j[0][9]);
				$('#inter_arm_old_regt').text($( "#inter_arm_old_regt_val option:selected" ).text());
	    	}
	    	else {
	    		$('#inter_arm_old_regt').text("");
	    	}
		 }
		 });
	

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
				getChangeOfRank();
			}
		}
		else if (obj.id == "b_final") {
			if(!hasC){
				get_change_of_name();
			}
		}
		else if (obj.id == "c_final") {
			if(!hasC){
				get_Appointment();
			}
		}
		else if (obj.id == "d_final") {
			if(!hasC){
				get_Birth();
			}
		}	
		else if (obj.id == "r_final") {
			if(!hasC){
				get_Commission();
			}
		}
	
		else if (obj.id == "k_final") {
			if(!hasC){
				get_cda_account_no();
				getpersonaldata();
			}
		}
		else if (obj.id == "dd_final") {
			if(!hasC){				
				getpersonaldata();
				get_seniortiy();
			}
		}
		else if (obj.id == "td_final") {
			if(!hasC){
				debugger;
				getpersonaldataforchangetos();
			}
		}
		
		
		
		else if (obj.id == "s_final") {
			if(!hasC){
				getsShapeDetails();
				gethShapeDetails();
				getaShapeDetails();
				getpShapeDetails();
				geteShapeDetails();
				getcCopeDetails();
				getoCopeDetails();
				getpCopeDetails();
				geteCopeDetails();
				
				
			}
		}
	
	
		else if (obj.id == "x_final") {
			if(!hasC){
				getInterArm();
				
			}
		}
		else if (obj.id == "y_final") {
			if(!hasC){
				getcoc();
				
			}
		}	
	
		addMaxLengthToInput(auth_length);
}

var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 



function closeWindow() {
	window.close();
	 window.opener.location.reload(); 
	}
 
function Pop_Up_History(a) {
	var x = screen.width/2 - 1100/2;
   var y = screen.height/2 - 900/2;
 	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	var comm_id = $("#comm_id").val();
	var census_id = $("#census_id").val();
	if(a == "personnel_no"){
		
		$('#personnel_no1').val(comm_id);
		document.getElementById('Preview_PersonnelNo_Form').submit();
	}	
if(a == "rank"){
		
		$('#rank_comm_id').val(comm_id);
		document.getElementById('Change_Rank_Form').submit();
	}	
if(a == "Name"){
	$('#name_comm_id').val(comm_id);
// 	$('#name_census_id').val(census_id);
	document.getElementById('Change_Name_Form').submit();
}
if(a == "Appointment"){
	$('#appointment_comm_id').val(comm_id);
// 	$('#appointment_census_id').val(census_id);
	document.getElementById('Change_Appointment_Form').submit();
}
if(a == "Change_Medicle")
	{
		$("#cim_comm_id").val(comm_id);
		$("#cim_census_id").val(census_id);
		document.getElementById('Change_In_Medicle_Form').submit();
	}
if(a == "inter_arm_service_transfer"){
	$("#as_comm_id").val(comm_id);
	$("#as_census_id").val(census_id);
	$("#Inter_Arm_Service_Transfer_Form").submit();
}
if(a == "permt_commission"){
	$("#ssc_comm_id").val(comm_id);
	$("#ssc_census_id").val(census_id);
	$("#Ssc_To_Permit_Commission_Form").submit();
}

if(a == "date_of_birth"){
	$("#comm_id5").val(comm_id);
	$("#personnel_no5").val($("#personnel_no").val());
	document.getElementById('Preview_DateofBirth_Form').submit();
}
if(a == "CDA"){
	$('#comm_id1').val(comm_id);
	document.getElementById('popup_cda').submit();
}
if(a == "commission"){
	$("#comm_id4").val(comm_id);
	$("#personnel_no4").val($("#personnel_no").val())
	document.getElementById('Preview_Commission_Form').submit();
}
if(a == "Seniority"){
	debugger;
	$('#comm_id11').val(comm_id);	
	document.getElementById('popup_seniority').submit();
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
		//Personal_no_already_exist();
	}
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


//************************************* START CHANGE OF RANK DETAIL*****************************//
function validate_ChngofRank_save() {
	
    if ($("#r_authority").val().trim() == "") {
        alert("Please Enter Authority.");
        $("#r_authority").focus();
        return false;
    }
    if ($("#r_date_of_authority").val() == "DD/MM/YYYY" || $("#r_date_of_authority").val().trim() == "") {
        alert("Please Enter Date of Authority.");
        $("#r_date_of_authority").focus();
        return false;
    }

     if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#r_date_of_authority").val())) {
        alert("Authority Date should be Greater than Commission Date");
        $("#r_date_of_authority").focus();
        return false;
    }

    if ($("#rank_type").val() == "0") {
        alert("Please Select Rank Type.");
        $("#rank_type").focus();
        return false;
    }
    if ($("#rank").val() == "0") {
        alert("Please Select Rank.");
        $("#rank").focus();
        return false;
    }
    if ($("#date_of_rank").val() == "DD/MM/YYYY" || $("#date_of_rank").val().trim() == "") {
        alert("Please Enter Date of Rank.");
        $("#date_of_rank").focus();
        return false;
    }

    if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#date_of_rank").val())) {
        alert("Date of Rank should be greater Or equal to Commissioning Date");
        $("#date_of_rank").focus();
        return false;
    } 

    var authority = $('#r_authority').val();
    var date_of_authority = $('#r_date_of_authority').val();
    var rank_type = $('#rank_type').val();
    var rank = $('#rank').val();
    var comm_date = $('#comm_date').val();
    var date_of_rank = $('#date_of_rank').val();
    var ch_r_id = $('#ch_r_id').val();
    var census_id_cr = $('#census_id').val();
    var comm_id_cr = $('#comm_id').val();
    
    if(!confirm("Are You Sure! You Want To Approve This Data?")){
		return false;
	}

    $.post('Change_of_Rank_action_3008?' + key + "=" + value, {
        authority: authority,
        date_of_authority: date_of_authority,
        rank_type: rank_type,
        rank: rank,
        date_of_rank: date_of_rank,
        comm_date: comm_date,
        census_id_cr: census_id_cr,
        comm_id_cr: comm_id_cr,
        ch_r_id: ch_r_id
    }, function(data) {

        if (data == "update")
        	{       	
        	 alert("Data approved successfully");
        	 window.location.reload();
        	}
           
        else if (parseInt(data) > 0) {
            $('#ch_r_id').val(data);
            alert("Data approved successfully");
            window.location.reload();
        } else
        	{        	
            alert(data);            
        	}
  
    }).fail(function(xhr, textStatus, errorThrown) {
        alert("fail to fetch");
    });
}
function getChangeOfRank(){
// 	var ch_id=$('#census_id').val(); 
	var comm_id=$('#comm_id').val();
	var i=0;
	 $.post('getChangeOfRankData_3008?' + key + "=" + value, {comm_id:comm_id}, function(j){
		 if(j!=""){
			 $('#ch_r_id').val(j[0].id);
				$('#r_authority').val(j[0].authority);
				var dtA =ParseDateColumncommission(j[0].date_of_authority);		
				$('#r_date_of_authority').val(dtA);	
				$('#rank_type').val( j[0].rank_type );		
				 $('#rank').val( j[0].rank );	
				 $('#census_id').val( j[0].census_id); 
				var dtR =ParseDateColumncommission(j[0].date_of_rank);
				$('#date_of_rank').val(dtR);	
		 }
	 });
	} 
	
//************************************* END CHANGE OF RANK DETAIL*****************************//


//************************************* START CHANGE OF NAME DETAIL*****************************//
function get_change_of_name(){
	debugger;
//  var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val();
	 var i=0;
	  $.post('change_of_name_GetData_3008?' + key + "=" + value, {comm_id:comm_id }, function(j){
			var v=j.length;
			if(v!=0){
				$('#name_id').val(j[i].id);
				 $("#na_authority").val(j[i].authority);
				 $("#na_date_of_authority").val(ParseDateColumncommission(j[i].date_of_authority));
				 $("#name").val(j[i].name);
				 $("#change_in_name_date").val(ParseDateColumncommission(j[i].change_in_name_date));      
			}
	  });
}	

function validate_Change_of_name_save_fn(){
	
	 if ($("input#na_authority").val().trim()=="") {
			alert("Please Enter Authority");
			$("input#na_authority").focus();
			return false;
		}
	 	 if ($("input#na_date_of_authority").val() == "DD/MM/YYYY" || $("input#na_date_of_authority").val().trim()=="") {
			alert("Please Enter Date of Authority ");
			$("input#na_date_of_authority").focus();
			return false;
		} 
	 	if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#na_date_of_authority").val())) {
			alert("Authority Date should be Greater than Commission Date");
			return false;
	  }
	 	if ($("input#name").val().trim()=="") {
			alert("Please Enter Name ");
			$("input#name").focus();
			return false;
		}

	 	if ($("input#change_in_name_date").val() == "DD/MM/YYYY" || $("input#change_in_name_date").val().trim()=="") {
	 				alert("Please Select Date of Change in Name ");
	 				$("input#change_in_name_date").focus();
	 				return false;
	 			} 
	 	
	 			
	var comm_id=$('#comm_id').val();
	var census_id=$('#census_id').val(); 
	var name=$('#name').val();
	var name_id=$('#name_id').val();
	var comm_date=$('#comm_date').val();
	var change_in_name_date=$('#change_in_name_date').val();
	var formdata=$('#form_change_of_name').serialize();
	if(!confirm("Are You Sure! You Want To Approve This Data")){
		return false;
	}
	formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&name_id="+name_id;	
	   $.post('change_of_name_3008_action?' + key + "=" + value, formdata, function(data){		      
	          if(data=="update"){
	        	  alert("Data approved successfully");
		          window.location.reload(); 
	          }	        
	          else if(parseInt(data)>0){	        	
	        	 $('#name_id').val(data);
	        	  alert("Data approved successfully")
	        	  window.location.reload();
	          }
	          else{
	        	  alert(data);
	          }    
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});	
}
//*************************************end Change of name*****************************//



//***************** START CHANGE OF APPOINTEMENT DETAILS************************//

function get_Appointment(){
// 	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('get_Appointment_3008?' + key + "=" + value, { comm_id:comm_id}, function(j){
			var v=j.length;
			if(v!=0){

				$('#appoint_id').val(j[i].id);
				 $("#appt_authority").val(j[i].appt_authority);
				 $("#appt_date_of_authority").val(ParseDateColumncommission(j[i].appt_date_of_authority));
				 $("#appointment").val(j[i].appointment);
				 $("#date_of_appointment").val(ParseDateColumncommission(j[i].date_of_appointment));
// 				 appNamechng();
// 				 $("#x_sus_no").val(j[i].x_list_sus_no);
// 				 $("#x_list_loc").val(j[i].x_list_loc);
				 $("#parent_sus_no").val(j[i].parent_sus_no);
				 $("#parent_unit").val(j[i].parent_unit);
				 $("#x_list_loc").val(j[i].x_list_loc);
				 if (j[i].appointment == "9562" || j[i].appointment == "9565") {
						$("#att_dapu").show();
						$("#att_dapu1").show();
					} else {
						$("#att_dapu").hide();
						$("#att_dapu1").hide();
					}
				 
			}
	  });
}	
function appointment_att() {

	if ($("#appointment").val() == "9562" || $("#appointment").val() == "9565") {
		$("#att_dapu").show();
		$("#att_dapu1").show();
		 $("div#Note").show();
	} else {
		$("#att_dapu").hide();
		$("#att_dapu1").hide();
		$("div#Note").hide();
	}
}

function validate_appointment_save(){
	debugger;
		var tos_date=$('#tos_date').val();
		var comm_id=$('#comm_id').val();
		var comm_date=$('#comm_date').val();
		var census_id=$('#census_id').val();
		var status=$('#status').val();
		var appoint_id=$('#appoint_id').val();
		var parent_sus_no =$('#parent_sus_no').val();
		var parent_unit=$('#parent_unit').val();
		var x_list_loc=$('#x_list_loc').val();
		
		
		
	  if ($("input#appt_authority").val().trim()=="") {
			alert("Please Enter Authority.");
			$("input#appt_authority").focus();
			return false;
		}
		  if ($("input#appt_date_of_authority").val() == "DD/MM/YYYY" || $("input#appt_date_of_authority").val().trim()=="") {
			alert("Please Enter Date Of Authority. ");
			$("input#appt_date_of_authority").focus();
			return false;
		} 
		  if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#appt_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				return false;
		  }
	  if ($("select#appointment").val() == "0") {
			alert("Please Select Appointment. ");
			$("select#appointment").focus();
			return false;
		} 
	   if ($("input#date_of_appointment").val() == "DD/MM/YYYY" || $("input#date_of_appointment").val().trim()=="") {
			alert("Please Enter Date Of Appointment. ");
			$("input#date_of_appointment").focus();
			return false;
		}  
	   if($("input#tos_date").val()!="" && getformatedDate($("input#tos_date").val()) > getformatedDate($("#date_of_appointment").val())) {
			alert("Date of Appointment Date should be Greater than TOS Date");
			return false;
	   }

	 if($('#appointment').val() == "9562" || $('#appointment').val() == "9565" )
	  {
			   if (($("#parent_sus_no").val().trim()== "") && ($("#parent_unit").val().trim()== "") && ($("#x_list_loc").val().trim()== "")) {
					alert("Please Enter X-List SUS No Or  X-List Unit Name Or  X-List Location.");
					$("input#parent_sus_no").focus();
					return false;
				}
	  }
		var formdata=$('#form_change_of_appointment').serialize();
		formdata+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date+"&tos_date="+tos_date;	
		
		if(!confirm("Are You Sure! You Want To Approve This Data")){
			return false;
		}
		
		   $.post('change_appointment_3008_Action?' + key + "=" + value, formdata, function(data){		      
			   if(data=="update"){
				   alert("Data approved successfully");
				   window.location.reload();
			   }
			   else if(parseInt(data)>0){
		        	 $('#appoint_id').val(data);
		        	  alert("Data approved successfully");
		        	  window.location.reload();
		          }
		          else
		        	  alert(data)
		        	  window.location.reload();
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});	
	}

//************************************* END APPOINTMENT*****************************//


//************************************* START CHANGE OF MEDICAL DETAIL*****************************//
var classification='1';
$("#mad_classification").change(function(){
	
	classification=this.value;
	setDiagnosis();
	
});

function onShapeValueChange(e,label){
	//26-01-1994
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
// 	if(classification=='1'){
// 		$('.diagnosisClass1').hide();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
		
// 	}
// 	if(classification=='2'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').hide();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='3'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').hide();
// 		$('.diagnosisClass4').hide();
// 		$('.diagnosisClass5').hide();
// 		$('.diagnosisClass6').hide();
// 	}
// 	else if(classification=='4'){
// 		$('.diagnosisClass1').show();
// 		$('.diagnosisClass2').show();
// 		$('.diagnosisClass3').show();
// 		$('.diagnosisClass4').show();
// 		$('.diagnosisClass5').show();
// 		$('.diagnosisClass6').show();
// 	}
}

function statusChange(Shape,position,Shape_status){
	
// 	if(classification=='1' && (Shape_status==2  || Shape_status==3)){
// 		 $("select#s_status"+position).val('1');
// 		 alert("Please First Change Medical Classification ");	
// 	}
// 	else{
// 	
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
// 	var shapeVal = 0;
// 	var stlab;
// 	if(Shape==1){
// 		shapeVal = sShape;
// 		stlab = "s_status";
// 	 }
// 	 else  if(Shape==2){
// 		 shapeVal = hShape;
// 		 stlab = "h_status";
// 	 }
// 	else  if(Shape==3){
// 		shapeVal = aShape;
// 		stlab = "a_status";
// 	}
// 	else  if(Shape==4){
// 		shapeVal = pShape;
// 		stlab = "p_status";
// 	}
// 	else  if(Shape==5){
// 		shapeVal = eShape;
// 		stlab = "e_status";
// 	}
	
// 	for(var i = 1; i<=shapeVal; i++){
		 
// 		var	st = $("#"+stlab+i).val();
// 		 var styleC = $(".diagnosisClass"+Shape).css("display");
// 		 var styleCval = $(".diagnosisClass"+Shape+i).css("display");
		 
// 		 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape).show();
// 			 //if(styleCval == "none"){
// 				 $('#_diagnosis_code'+Shape+i).show();
// 			 //}
// 		 }
// 		 else if(i == shapeVal && styleC == "none"  ){
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 $('.diagnosisClass'+Shape+i).hide();
// 		 }
		 
// 		 if(i == shapeVal && styleC != "none"  ){
// 			 if(st == 2 || st == 3){
// 			 $('.diagnosisClass'+Shape+i).show();
// 			 $('#_diagnosis_code'+Shape+i).show();
// 			 }
// 			 else {
// 				 $('.diagnosisClass'+Shape+i).show();
// 				 $('#_diagnosis_code'+Shape+i).hide();
// 			}
// 		 }
		 
	 
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
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="sShape_value'+sShape+'" id="sShape_value'+sShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="s_from_date'+sShape+'" name="s_from_date'+sShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="s_to_date'+sShape+'" name="s_to_date'+sShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
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
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="s_diagnosis_code2'+sShape+'" id="s_diagnosis_code2'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="s_diagnosis_code3'+sShape+'" id="s_diagnosis_code3'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="s_diagnosis_code4'+sShape+'" id="s_diagnosis_code4'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="s_diagnosis_code5'+sShape+'" id="s_diagnosis_code5'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="s_diagnosis_code6'+sShape+'" id="s_diagnosis_code6'+sShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
	+' <td style="display:none;"><input type="text" id="sShape_ch_id'+sShape+'" name="sShape_ch_id'+sShape+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'   
	+'<td style="width: 10%;" >'
	+'   <a  class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "sShape_add'+sShape+'" onclick="sShape_add_fn('+sShape+');" ><i class="fa fa-plus"></i></a>'
	+'    <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "sShape_remove'+sShape+'" onclick="sShaperemove_fn('+sShape+');"><i class="fa fa-trash"></i></a> '
	+'</td>'
	+'</tr>');
	
	 
	 datepicketDate('s_from_date'+sShape);
	 datepicketDate('s_to_date'+sShape);
	 $( "#s_to_date"+sShape ).datepicker( "option", "maxDate", null);
	 statusChange(1, sShape, $("#s_status" + sShape).val());
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
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="hShape_value'+hShape+'" id="hShape_value'+hShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="h_from_date'+hShape+'" name="h_from_date'+hShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="h_to_date'+hShape+'" name="h_to_date'+hShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
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
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="h_diagnosis_code2'+hShape+'" id="h_diagnosis_code2'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="h_diagnosis_code3'+hShape+'" id="h_diagnosis_code3'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="h_diagnosis_code4'+hShape+'" id="h_diagnosis_code4'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="h_diagnosis_code5'+hShape+'" id="h_diagnosis_code5'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="h_diagnosis_code6'+hShape+'" id="h_diagnosis_code6'+hShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
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
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="aShape_value'+aShape+'" id="aShape_value'+aShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="a_from_date'+aShape+'" name="a_from_date'+aShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="a_to_date'+aShape+'" name="a_to_date'+aShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
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
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="a_diagnosis_code2'+aShape+'" id="a_diagnosis_code2'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="a_diagnosis_code3'+aShape+'" id="a_diagnosis_code3'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="a_diagnosis_code4'+aShape+'" id="a_diagnosis_code4'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="a_diagnosis_code5'+aShape+'" id="a_diagnosis_code5'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="a_diagnosis_code6'+aShape+'" id="a_diagnosis_code6'+aShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
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
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="pShape_value'+pShape+'" id="pShape_value'+pShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
// 	+'	<td style="">'
// 	+'		<input type="date" id="p_from_date'+pShape+'" name="p_from_date'+pShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="p_to_date'+pShape+'" name="p_to_date'+pShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
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
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="p_diagnosis_code2'+pShape+'" id="p_diagnosis_code2'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="p_diagnosis_code3'+pShape+'" id="p_diagnosis_code3'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="p_diagnosis_code4'+pShape+'" id="p_diagnosis_code4'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="p_diagnosis_code5'+pShape+'" id="p_diagnosis_code5'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="p_diagnosis_code6'+pShape+'" id="p_diagnosis_code6'+pShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
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
// 	+'		<option value="0">--Select--</option>'
	+'	<c:forEach var="item" items="${getShapeStatusList}" varStatus="num">'
	+'	<option value="${item[1]}" name="${item[1]}">${item[0]}</option>'
	+'	</c:forEach>'
	+'		</select>'
	+'  </td>'
	+'   <td style="">'
	+'<select name="eShape_value'+eShape+'" id="eShape_value'+eShape+'" '
	+'		class="form-control-sm form-control">'
	+'		</select>	</td>'
	
// 	+'	<td style="">'
// 	+'		<input type="date" id="e_from_date'+eShape+'" name="e_from_date'+eShape+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
// 	+'		 </td>'	   
// 	+'<td style="">'
// 	+'		<input type="date" id="e_to_date'+eShape+'" name="e_to_date'+eShape+'" class="form-control autocomplete" autocomplete="off">		'				                             
// 	+'   </td>'
	
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
// 	+'    <td style=" display: none" class="diagnosisClass2">'
// 	+'<input type="text" name="e_diagnosis_code2'+eShape+'" id="e_diagnosis_code2'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+' </td>'
// 	+'   <td style=" display: none" class="diagnosisClass3">'
// 	+' <input type="text" name="e_diagnosis_code3'+eShape+'" id="e_diagnosis_code3'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">		'				                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass4">'
// 	+' <input type="text" name="e_diagnosis_code4'+eShape+'" id="e_diagnosis_code4'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+' placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'  </td>'
// 	+'    <td style=" display: none" class="diagnosisClass5">'
// 	+' <input type="text" name="e_diagnosis_code5'+eShape+'" id="e_diagnosis_code5'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">				'		                              
// 	+'  </td>'
// 	+'   <td style=" display: none" class="diagnosisClass6">'
// 	+' <input type="text" name="e_diagnosis_code6'+eShape+'" id="e_diagnosis_code6'+eShape+'" class="form-control-sm form-control" autocomplete="off" '
// 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	+'   </td>'
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
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getcCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	+'<td style="display:none;" class="cCopClass'+cCope+'" >'
		+'<input type="text" name="c_cother'+cCope+'" id="c_cother'+cCope+'" class="form-control-sm form-control" autocomplete="off" >	'					                              
		+' </td>'
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_cdescription'+cCope+'" id="c_cdescription'+cCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
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
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getoCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_odescription'+oCope+'" id="c_odescription'+oCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
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
// 	 	+'		<option value="0">--Select--</option>'
	 	+'	<c:forEach var="item" items="${getpCopeList}" varStatus="num">'
	 	+'	<option value="${item[1]}" name="${item[0]}">${item[0]}</option>'
	 	+'	</c:forEach>'
	 	+'		</select>'
	 	+'  </td>'
	 	
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_pdescription'+pCope+'" id="c_pdescription'+pCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
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
// 	 	+'		<option value="0">--Select--</option>'
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
// 	 	+'  <td style="" >'
// 	 	+' <input type="text" name="c_edescription'+eCope+'" id="c_edescription'+eCope+'" class="form-control-sm form-control" autocomplete="off" '
// 	 	+'  placeholder="Search..." onkeypress="AutoCompleteDiagnosis(this);">	'					                              
// 	 	+'   </td>'
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
	 
	 
	 function medical_cat_save(){
		 var count_classi = 0;
		 
		 if($("#madical_authority").val().trim()==""){
				alert("Please Enter Authority");
				$("#madical_authority").focus();				
				return false;
			}
		 
		 if($("input#madical_date_of_authority").val().trim()=="" || $("#madical_date_of_authority").val().trim()=="DD/MM/YYYY"){
				alert("Please Enter Date of Authority");
				$("#madical_date_of_authority").focus();
				return false;
			}
		

		 	var comm_date=$("#comm_date").val();
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#madical_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				$("#madical_date_of_authority").focus();
				return false;
		    }
		 
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
					
			// 		if($("#s_status"+j).val() != "1"){			
			// 			if($("#_diagnosis_code1"+j).val().trim()==""){
			// 				alert("Please Enter S-Shape Diagnosis "+j+" Row");
			// 				$("#_diagnosis_code1"+j).focus();				
			// 				return false;
			// 			}
			// 		}
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
			
		    var formvalues=$("#madical_category_form").serialize();
		
			var census_id=$("#census_id").val();	
			var comm_id=$('#comm_id').val();			
			formvalues+="&census_id="+census_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
			

			if(!confirm("Are You Sure! You Want To Approve This Data")){
				return false;
			}
			
		   $.post('medical_category_3008_Action?' + key + "=" + value,formvalues, function(data){
			      
		      
		             	 
		        	 if(data=='1'){
		        		 alert('Data approved successfully');
		        	
// 		        		 get1BXShapeDetails();
		        	  getsShapeDetails();
		        	  gethShapeDetails();
		        	  getaShapeDetails();
		        	  getpShapeDetails();
		        	  geteShapeDetails();
		        	  getcCopeDetails();
		        	  getoCopeDetails();
		        	  getpCopeDetails();
		        	  geteCopeDetails();
		        	  window.location.reload();
		        	 }
		        	 else{
		        		 alert(data);
		        	 }
		         
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
	}

	 function getsShapeDetails(){	
	 	 var p_id=$('#census_id').val(); 	
	 	 var Shape='S'
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
//	  				  	 td=ParseDateColumncommission(j[i][3]);
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
//	 							+'	<td style="">'
//	 							+'		<input type="date" id="s_from_date'+x+'" name="s_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	 							+'		 </td>'	   
//	 							+'<td style="">'
//	 							+'		<input type="date" id="s_to_date'+x+'" name="s_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	 							+'   </td>'
	 						
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
//	  						if(x>1){
//	  							$("#s_status"+x+" option[value='1']").remove();
//	  							$("#s_status"+(x-1)+" option[value='1']").remove();
	 							
//	  						}
//	  						else {
	 							var thisT = document.getElementById('sShape_value'+x);
	 							onShapeValueChange(thisT,'s');
//	  						}
	 						 
	 						
	 						
	 					
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
//	   			$('#mad_classification').attr('readonly', true);
	 			
	 			setDiagnosis();
	 			}
	 		
	 		
	 			
	 	  });
	 }


	 function gethShapeDetails(){
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='H';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
//	  					  	 td=ParseDateColumncommission(j[i][3]);
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
//	  							+'	<td style="">'
//	  							+'		<input type="date" id="h_from_date'+x+'" name="h_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	  							+'		 </td>'	   
//	  							+'<td style="">'
//	  							+'		<input type="date" id="h_to_date'+x+'" name="h_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	  							+'   </td>'
	 							
	 							
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
//	  						if(x>1){
//	  							$("#h_status"+x+" option[value='1']").remove();
//	  							$("#h_status"+(x-1)+" option[value='1']").remove();
	 							
//	  						}
//	  						else {
	 							var thisT = document.getElementById('hShape_value'+x);
	 							onShapeValueChange(thisT,'h');
//	  						}
	 						 
	 						
	 						
	 					
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='A';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
//	  					  	 td=ParseDateColumncommission(j[i][3]);
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
//	  							+'	<td style="">'
//	  							+'		<input type="date" id="a_from_date'+x+'" name="a_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	  							+'		 </td>'	   
//	  							+'<td style="">'
//	  							+'		<input type="date" id="a_to_date'+x+'" name="a_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	  							+'   </td>'
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
//	  						if(x>1){
//	  							$("#a_status"+x+" option[value='1']").remove();
//	  							$("#a_status"+(x-1)+" option[value='1']").remove();
	 							
//	  						}
//	  						else {
	 							var thisT = document.getElementById('aShape_value'+x);
	 							onShapeValueChange(thisT,'a');
//	  						}
	 						 
	 						
	 						
	 					
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='P';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
//	  					  	 td=ParseDateColumncommission(j[i][3]);
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
//	  							+'	<td style="">'
//	  							+'		<input type="date" id="p_from_date'+x+'" name="p_from_date'+x+'" value="'+fd+'" max="${date_without_time}" class="form-control autocomplete" autocomplete="off">	'					                             
//	  							+'		 </td>'	   
//	  							+'<td style="">'
//	  							+'		<input type="date" id="p_to_date'+x+'" name="p_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	  							+'   </td>'		
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
//	  						if(x>1){
//	  							$("#p_status"+x+" option[value='1']").remove();
//	  							$("#p_status"+(x-1)+" option[value='1']").remove();
	 							
//	  						}
//	  						else {
	 							var thisT = document.getElementById('pShape_value'+x);
	 							onShapeValueChange(thisT,'p');
//	  						}
	 						 
	 						
	 						
	 					
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='E';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
//	  					  	 td=ParseDateColumncommission(j[i][3]);
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
//	  							+'	<td style="">'
//	  							+'		<input type="date" id="e_from_date'+x+'" name="e_from_date'+x+'" max="${date_without_time}" value="'+fd+'" class="form-control autocomplete" autocomplete="off">	'					                             
//	  							+'		 </td>'	   
//	  							+'<td style="">'
//	  							+'		<input type="date" id="e_to_date'+x+'" name="e_to_date'+x+'" value="'+td+'" class="form-control autocomplete" autocomplete="off">		'				                             
//	  							+'   </td>'


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
//	  						if(x>1){
//	  							$("#e_status"+x+" option[value='1']").remove();
//	  							$("#e_status"+(x-1)+" option[value='1']").remove();
	 							
//	  						}
//	  						else {
	 							var thisT = document.getElementById('eShape_value'+x);
	 							onShapeValueChange(thisT,'e');
//	  						}
	 						 
	 						
	 						
	 					
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='C_C';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='C_O';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='C_P';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008 }, function(j){
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
	 	
	 	 var p_id=$('#census_id').val(); 
	 	
	 	 var Shape='C_E';
	 	 var i=0;
	 	 var comm_id = $('#comm_id').val();	
	      var meddtlfillin3008 ='0';
	 	  $.post('madical_cat_GetData?' + key + "=" + value, {p_id:p_id,Shape:Shape,comm_id:comm_id,meddtlfillin3008:meddtlfillin3008}, function(j){
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


//*****************START INTER ARM  DETAILS************************//

function validate_inter_arm_transfer_save(){


	if ($("input#inter_arm_authority").val().trim()=="") {
	    alert("Please Enter Authority");
		$("input#inter_arm_authority").focus();
		return false;
	} 
		 if ($("input#inter_arm_date_of_authority").val().trim()=="" || $("input#inter_arm_date_of_authority").val() == "DD/MM/YYYY") {
		 alert("Please Enter Date of Authority");
			$("input#inter_arm_date_of_authority").focus();
			return false;
		} 
		 var test = $("#comm_date").val();
		
			if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#inter_arm_date_of_authority").val())) {
				alert("Authority Date should be Greater than Commission Date");
				$("input#inter_arm_date_of_authority").focus();
				return false;
		  }
// 		if ($("input#old_arm_service").val().trim()=="") {
// 			 alert("Please Enter Old Arm Services");
// 				$("input#old_arm_service").focus();
// 				return false;
// 			} 
// 		if ($("input#old_regt").val().trim()=="") {
// 			 alert("Please Enter Old Regt");
// 				$("input#old_regt").focus();
// 				return false;
// 			} 
	 	 if ($("select#inter_arm_parent_arm_service").val() == "0") {
		 alert("Please Select To Present Arm/Services");
			$("select#inter_arm_parent_arm_service").focus();
			return false;
		} 
	 	 
// 	 	if ($("select#regt").val() == "0") {
// 			 alert("Please Select Regt");
// 				$("select#regt").focus();
// 				return false;
// 			} 
// 	 	if ($("input#with_effect_from").val().trim()=="") {
// 			 alert("Please Select Date");
// 				$("input#with_effect_from").focus();
// 				return false;
// 			} 
   var inter_arm_regt=$('#inter_arm_regt').val();
 	var inter_arm_parent_arm_serviceV = $("#inter_arm_parent_arm_service"+" option:selected").text();
 	
 	if(($("#inter_arm_parent_arm_service").val() == "0700" || $("#inter_arm_parent_arm_service").val() == "0800") && inter_arm_regt == "0") {
		alert("Please Select Inter Arm Regt");
		$('#inter_arm_regt').focus();
		return false;
	}
 	if ($("input#inter_arm_with_effect_from").val().trim()=="" || $("input#inter_arm_with_effect_from").val() == "DD/MM/YYYY") {
		 alert("Please Enter With Effect From");
			$("input#inter_arm_with_effect_from").focus();
			return false;
		} 
 	 if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#inter_arm_with_effect_from").val())) {
			alert("With Effect From Date should be Greater than Commission Date");
			$("#inter_arm_with_effect_from").focus();
			return false;
	  }
 

 	var authority1=$('#inter_arm_authority').val();
 	var date_of_authority1=$('#inter_arm_date_of_authority').val();
 	var old_arm_service1=$('#inter_arm_old_arm_service').val();
	var old_regt1=$('#inter_arm_old_regt').val();
 	var parent_arm_service1=$('#inter_arm_parent_arm_service').val();
 	var with_effect_from1=$('#inter_arm_with_effect_from').val();
 	var regt1=$('#inter_arm_regt').val();
 	var pre_ch_id=$('#p_id').val();
 	var census_id=$('#census_id').val();
 	var comm_id=$('#comm_id').val();
    var comm_date=$('#comm_date').val();
 	
	if(!confirm("Are You Sure! You Want To Approve This Data")){
		return false;
	}
	
 
	   $.post('Inter_arm__3008_action?' + key + "=" + value, {authority1:authority1, date_of_authority1:date_of_authority1,
		   old_arm_service1:old_arm_service1,old_regt1:old_regt1,parent_arm_service1:parent_arm_service1,
		   with_effect_from1:with_effect_from1,regt1:regt1,pre_ch_id:pre_ch_id,census_id:census_id,comm_id:comm_id,comm_date:comm_date}, function(data){
		      
	          if(data=="update")
	        	  {
	        	  alert("Data approved successfully");
	        	  window.location.reload();
	        	  }
	        	
	          else if(parseInt(data)>0){
				$('#p_id').val(data);
	        	  alert("Data approved successfully")
	        	  window.location.reload();
	          }
	          else
	        	  {
	        	  alert(data);
	        	 
	        	  }

	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
 	  		});
}

function getInterArm(){

	 var p_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getInterArm?' + key + "=" + value, {p_id:p_id,comm_id:comm_id }, function(j){
			var v=j.length;
			debugger;
			if(v!=0){
				$('#pre_cadeytbody').empty(); 
		for(i;i<v;i++){
			x=i+1;
 			var fd=ParseDateColumncommission(j[i].date_of_authority);				
			var td=ParseDateColumncommission(j[i].with_effect_from);
			$('#inter_arm_parent_arm_service').val(j[i].parent_arm_service);	
			chgarm(document.getElementById("inter_arm_parent_arm_service"),'inter_arm_regt');
			$('#inter_arm_authority').val(j[i].authority);
			$('#inter_arm_date_of_authority').val(fd);
			$('#inter_arm_old_arm_service').val(j[i].old_arm_service);
			$('#inter_arm_old_regt').val(j[i].old_regt);
			$('#inter_arm_with_effect_from').val(td);
 			$('#inter_arm_regt').val(j[i].regt);
			$('#p_id').val(j[i].id);
		}
	}
});
}
//*****************END INTER ARM  DETAILS************************//


//***************** START CHANGE OF COMMISSION DETAILS************************//
function validate_insertSeniorityAction_save(){
	debugger;
    var authority=$('#coc_authority').val();
    var date_of_authority=$('#coc_date_of_authority').val();
    var persnl_no1=$('#persnl_no1').val();
    var persnl_no2=$('#persnl_no2').val();
    var persnl_no3=$('#persnl_no3').val();
    var date_of_permanent_commission=$('#coc_date_of_permanent_commission').val();
    var previous_commission=$('#coc_old_previous_commission').val();
    var old_personal_no=$('#coc_old_personal_no').val();
    var type_of_commission_granted=$('#type_of_commission_granted').val();
    var date_of_seniority=$('#coc_date_of_seniority').val();
    var eff_date_of_seniority=$('#eff_coc_date_of_seniority').val();
    var coc_ch_id=$('#coc_ch_id').val();
    var cen_id=$('#census_id').val();
    var comm_id=$('#comm_id').val();
    var comm_date=$('#comm_date').val();
    
	
    
     if ($("input#coc_authority").val().trim()=="") {
              alert("Please Enter Authority");
                     $("input#coc_authority").focus();
                     return false;
             } 
              if ($("input#coc_date_of_authority").val().trim()=="" || $("input#coc_date_of_authority").val() == "DD/MM/YYYY") {
              alert("Please Enter Date of Authority");
                     $("input#coc_date_of_authority").focus();
                     return false;
             } 
             if(getformatedDate($("input#comm_date").val()) > getformatedDate($("#coc_date_of_authority").val())) {
                     alert("Authority Date should be Greater than Commission Date");
                     $("input#coc_date_of_authority").focus();
                     return false;
       }
       
             if ($("select#persnl_no1").val() == "-1") {
                     alert("Please Enter New Personal No");
                            $("#persnl_no1").focus();
                            return false;
                    }
             if ($("input#persnl_no2").val().trim()=="") {
                     alert("Please Enter New Personal No");
                            $("input#persnl_no2").focus();
                            return false;
                    }
             
             var result=Personal_no_already_exist(coc_ch_id);
         	if(result == false)
         	{
         		$("select#persnl_no1").focus();
         		return false;
         		
         	} 
              if ($("select#type_of_commission_granted").val() == "0") {
                       alert("Please Select Type of Commission Granted");
                              $("select#type_of_commission_granted").focus();
                              return false;
                     } 
             if ($("input#coc_date_of_permanent_commission").val().trim()=="" || $("input#coc_date_of_permanent_commission").val() == "DD/MM/YYYY") {
                      alert("Please Enter Permanent Commission Date");
                            $("input#coc_date_of_permanent_commission").focus();
                             return false;
                    } 
             if(getformatedDate($("input#comm_date").val()) > getformatedDate($("input#coc_date_of_permanent_commission").val())) {
       			alert("Date of Permanent Commission should be Greater than Commission Date");
       			$("#coc_date_of_permanent_commission").focus();
       			return false;
       	  } 
     
     if ($("input#coc_date_of_seniority").val().trim()=="" || $("input#coc_date_of_seniority").val() == "DD/MM/YYYY") {
                     alert("Please Enter Date Of Seniority");
                             $("input#coc_date_of_seniority").focus();
                            return false;
             } 
                     if ($("input#eff_coc_date_of_seniority").val().trim()=="" || $("input#eff_coc_date_of_seniority").val() == "DD/MM/YYYY") {
                             alert("Please Enter Date From Which Change in Seniority is Effective");
                                     $("input#eff_coc_date_of_seniority").focus();
                                    return false;
                     } 

                     if(!confirm("Are You Sure! You Want To Approve This Data")){
                 		return false;
                 	}
                     
                     $.post('coc_3008action?' + key + "=" + value, {authority:authority,date_of_authority:date_of_authority,persnl_no1:persnl_no1,persnl_no2:persnl_no2,persnl_no3:persnl_no3,
                             date_of_permanent_commission:date_of_permanent_commission,previous_commission:previous_commission,type_of_commission_granted:type_of_commission_granted,
                             date_of_seniority:date_of_seniority,coc_ch_id:coc_ch_id,cen_id:cen_id,comm_id:comm_id,eff_date_of_seniority:eff_date_of_seniority,comm_date:comm_date,old_personal_no:old_personal_no}, function(data){
                                
                                    if(data=="update"){
                                     alert("Data approved successfully");
                                     window.close();}
              else if(parseInt(data)>0){
                     $('#coc_ch_id').val(data);
                      alert("Data approved successfully")
                      window.close();
              }
              else{
                      alert(data);                     
                      }
                               }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
                      });
}
function getcoc(){
	 var n_id=$('#census_id').val(); 
	 var comm_id=$('#comm_id').val(); 
	 var i=0;
	  $.post('getcocData?' + key + "=" + value, {n_id:n_id,comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				var authDt=ParseDateColumncommission(j[0].date_of_authority);				
				var commDt=ParseDateColumncommission(j[0].date_of_permanent_commission);
				var senDt=ParseDateColumncommission(j[0].date_of_seniority);
				$('#coc_authority').val(j[0].authority);
				$('#coc_date_of_authority').val(authDt);
				var pr_no=j[0].new_personal_no.split(/\d/)[0];
				$("#persnl_no1").val(pr_no);
				$("#persnl_no2").val(j[0].new_personal_no.substring(pr_no.length, pr_no.length+5));
				$("#persnl_no3").val(j[0].new_personal_no.substr(-1));
				$('#coc_date_of_permanent_commission').val(commDt);
				
				//$('#coc_previous_commission').val(j[0].previous_commission);
				$('#type_of_commission_granted').val(j[0].type_of_commission_granted);
				$('#eff_coc_date_of_seniority').val(ParseDateColumncommission(j[0].eff_date_of_seniority));
				$('#coc_ch_id').val(j[0].id);
				$('#census_id').val(j[0].census_id);
				$('#comm_id').val(j[0].comm_id);
			}
		
	  });
	  
	  //function getcocDataStatus1() {
		
		  $.post('getcocDataStatus1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			  if(j!=""){
				 
				  var str = j[0][0];
				  var res = str.substring(0, 3).toUpperCase();
				  $('#coc_previous_commission').text(j[0][0]);
				  $('#coc_date_of_seniority').val(ParseDateColumncommission(j[0][1]));
				  $('#coc_old_personal_no').val(j[0][2]);
				  $('#coc_old_previous_commission').val(j[0][3]);
				 
			  }
		  });
	//}
	 
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

function Personal_no_already_exist(id)
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
	 
	 
	 var data_result=true; 
	
     $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no,id:id}).done(function(j) {
		 	if(j == false){
				alert("Personal No already Exist.")
				$("select#persnl_no1").val("-1");
				$("input#persnl_no2").val("");
				$("input#persnl_no3").val("");
				data_result =  false;
			} 
		}); 
	 
	return data_result;
}
//***************** END CHANGE OF COMMISSION DETAILS************************//

//***************** START CHANGE OF CDA ACCOUNT DETAILS************************//
function validate_cda_account_no(){	
	 if ($("#co_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("#co_authority").focus();
	 		return false;
		}
	 if ($("#co_date_of_authority").val().trim()=="" || $("#co_date_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#co_date_of_authority").focus();
			return false;
		} 
	 
	 if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#co_date_of_authority").val())) {
	        alert("Authority Date should be Greater than Commission Date");
	        $("#co_date_of_authority").focus();
	        return false;
	    }
	
	if ($("input#cda_acc_no").val() == "") {
			alert("Please Enter CDA Account Number.");
			$("input#cda_acc_no").focus();
			return false;
	}  	  
	   if(!confirm("Are You Sure! You Want To Approve This Data")){
    		return false;
    	}
 
	    var formvalues=$("#form_cda_accountno").serialize();
		var co_id=$("#co_id").val();	
		var comm_id=$("#comm_id").val();
		 var comm_date=$('#comm_date').val();
			
		formvalues+="&co_id="+co_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
		 $.post('Change_of_cda_action_3008?' + key + "=" + value,formvalues, function(data){
		          if(data=="update"){
		        	  alert("Data Approved Successfully");
			          window.location.reload();  
		          }		        	
		          else if(parseInt(data)>0){
		        	  $("#co_id").val(data);	
		        	  alert("Data Approved Successfully")
		        	  window.location.reload();
		          }
		          else{
		        	  alert(data) 
		          }
		        	 
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_cda_account_no(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_cda_account_no?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#cda_acc_no').val(j[0].cda_acc_no);	
				$("#co_id").val(j[0].id);
			}
	  });
} 

function getpersonaldata(){	
    	 var comm_id = $("#comm_id").val();
			$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
				if (k.length > 0) {
					$('#census_id').val(k[0][1]);
					$("#cadet_name").text(k[0][2]);
					$("#sen_name").text(k[0][2]);
					$("#p_rank").text(k[0][3]);	
					$("#sen_rank").text(k[0][3]);
					$("#hd_p_rank").val(k[0][3]);
					$("#app_sus_no").text(k[0][7]);
					
					$.ajaxSetup({
						async : false
					});	
				
					var sus_no = k[0][7];
					getunit(sus_no);
					function getunit(val) {
			                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
								var length = j.length - 1;
								var enc = j[length].substring(0,16);
								//alert("unit---" + dec(enc,j[0]));
								$("#app_unit_name").text(dec(enc,j[0]));
								$("#sen_unit_name").text(dec(enc,j[0]));
							}).fail(function(xhr,textStatus,errorThrown) {
					        });
					}
					$.ajaxSetup({
						async : false
					});
			}
		});
			
		 	$.post('getoldseniorityDate?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		   		 if(k.length > 0){
		   			   
			   			$('#old_seniority').text((k[0][0]));
			   			
			   		}	 
		       });
		}
		
//***************** END CHANGE OF CDA ACCOUNT DETAILS************************//

//***************** START CHANGE OF BIRTH DATE DETAILS************************//
function validate_Birth(){
	 if ($("#b_authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("#b_authority").focus();
	 		return false;
		}
	 if ($("#b_date_of_authority").val().trim()=="" || $("#b_date_of_authority").val() == "DD/MM/YYYY" ) {
			alert("Please Enter Date of Authority");
			$("input#b_date_of_authority").focus();
			return false;
		} 
	 if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#b_date_of_authority").val())) {
	        alert("Authority Date should be Greater than Commission Date");
	        $("#b_date_of_authority").focus();
	        return false;
	    }
	 
	if ($("#date_of_birth").val() == "") {
		alert("Please Select Date of Birth");
		$("#date_of_birth").focus();
		return false;
	}  
	   if(!confirm("Are You Sure! You Want To Approve This Data")){
    		return false;
    	}
	    var formvalues=$("#form_Birth").serialize();
		var birth_id=$("#birth_id").val();	
		var comm_id=$("#comm_id").val();	
		 var comm_date=$('#comm_date').val();
		formvalues+="&birth_id="+birth_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
		 $.post('Birth_Action_3008?' + key + "=" + value,formvalues, function(data){
		          if(data=="update"){
		        	  alert("Data Approved Successfully");
			          window.location.reload();  
		          }		        	  
		          else if(parseInt(data)>0){
		        	  $("#birth_id").val(data);	
		        	  alert("Data Approved Successfully");
		        	  window.location.reload();
		          }
		          else{
		        	  alert(data);  
		          }
		        	  
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}
function get_Birth(){
	if ($("#roleAccess").val() == "Unit"){
		document.getElementById("hidedivdob").style.display = "none";
		document.getElementById("hidedivdobunit").style.display = "block";

	}else{
		document.getElementById("hidedivdob").style.display = "block";
		document.getElementById("hidedivdobunit").style.display = "none";
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Birth1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#b_authority').val(j[0].authority);
				$('#b_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#date_of_birth').val(ParseDateColumncommission(j[0].date_of_birth));
				$("#birth_id").val(j[0].id);
			}
	  });
	}
}

//***************** END CHANGE OF BIRTH DATE DETAILS************************//	


//***************** START CHANGE OF COMMISSION DETAILS************************//	
function validate_Commission(){
	
	if ($("input#com_authority").val().trim() == "") {
		alert("Please Enter Authority");
		$("input#com_authority").focus();
 		return false;
	}
	
	if ($("input#com_date_of_authority").val().trim() == "" || $("input#com_date_of_authority").val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Date of Authority");
		$("input#com_date_of_authority").focus();
		return false;
	}
	
	 if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#com_date_of_authority").val())) {
	        alert("Authority Date should be Greater than Commission Date");
	        $("#com_date_of_authority").focus();
	        return false;
	    }
	
	if ($("select#type_of_comm_granted").val() == "0") {
		alert("Please Select Type of Commission Granted");
		$("select#type_of_comm_granted").focus();
 		return false;
	}
	if ($("input#date_of_commission").val().trim() == "" || $("input#date_of_commission").val().trim() == "DD/MM/YYYY") {
		alert("Please Enter Date of Commission");
		$("input#date_of_commission").focus();
		return false;
	}
	
	if ($("input#date_of_commission").val().trim() != "" || $("input#date_of_commission").val().trim() != "DD/MM/YYYY") {
		if(confirm("Are You Sure You Want To Update Commissioning Date? It Will Affect Your Date of Rank of Commissioning Time.")){
		}
		else{
			return false;
		}
		
	}
	
	   if(!confirm("Are You Sure! You Want To Approve This Data")){
    		return false;
    	}
	    var formvalues=$("#form_COMMISSION").serialize();
		var com_id=$("#com_id").val();	
		var comm_id=$("#comm_id").val();
		 var comm_date=$('#comm_date').val();
		formvalues+="&com_id="+com_id+"&comm_id="+comm_id+"&comm_date="+comm_date;
		 $.post('COMMISSION_Action_3008?' + key + "=" + value,formvalues, function(data){
		          if(data=="update"){
		        	  alert("Data Approved Successfully");
			             window.location.reload();  
		          }		        	  
		          else if(parseInt(data)>0){
		        	  $("#com_id").val(data);	
		        	  alert("Data Approved Successfully")
		        	  window.location.reload();
		          }
		          else{
		        	  alert(data)  
		          }
		        	  
		 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		  		});
}

function get_Commission(){
	if ($("#roleAccess").val() == "Unit"){
		document.getElementById("hidecommdiv").style.display = "none";
		document.getElementById("hidecommdivunit").style.display = "block";

	}else{
		document.getElementById("hidecommdiv").style.display = "block";
		document.getElementById("hidecommdivunit").style.display = "none";
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_Commission1?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#com_authority').val(j[0].authority);
				$('#com_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#type_of_comm_granted').val(j[0].type_of_comm_granted);
				$('#date_of_commission').val(ParseDateColumncommission(j[0].date_of_commission));
				$("#com_id").val(j[0].id);
			}
	  });
	}
}


//***************** END CHANGE OF COMMISSION DETAILS************************//	


//***************** START CHANGE OF SENIORITY DETAILS************************//	
function validate_insertSeniority_save(){

	if ($("input#sen_authority").val() == "") {
		alert("Please Enter Authority ");
		$("input#sen_authority").focus();
		return false;
	}
	 if ($("input#sen_date_of_authority").val() == "" || $("input#sen_date_of_authority").val() == "DD/MM/YYYY") {
		alert("Please Select Date of Authority ");
		$("input#sen_date_of_authority").focus();
		return false;
	} 
	 if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#sen_date_of_authority").val())) {
	        alert("Authority Date should be Greater than Commission Date");
	        $("#sen_date_of_authority").focus();
	        return false;
	    }
    if ($("input#date_of_seniority").val() == "" || $("input#date_of_seniority").val() == "DD/MM/YYYY") {
		alert("Please Select New Date of Seniority ");
		$("input#date_of_seniority").focus();
		return false;
	 } 
    if ($("input#reason").val() == "") {
		alert("Please Enter Reason ");
		$("input#reason").focus();
		return false;
	}
	if ($("input#date_of_applicability").val() == "" || $("input#date_of_applicability").val() == "DD/MM/YYYY") {
		alert("Please Select Effective Date of Seniority");
		$("input#date_of_applicability").focus();
		return false;
	 } 

	   if(!confirm("Are You Sure! You Want To Approve This Data")){
    		return false;
    	}
 
	
		var authority=$('#sen_authority').val();
		var Sen_id=$('#Sen_id').val();
		var census_id=$('#census_id').val();
		var comm_id=$('#comm_id').val();
		var date_of_authority=$('#sen_date_of_authority').val();
		var date_of_seniority=$('#date_of_seniority').val();
		var date_of_applicability=$('#date_of_applicability').val();
		var reason=$('#reason').val();
		 var comm_date=$('#comm_date').val();
	
 
	   $.post('Seniority_Details_Action_3008?' + key + "=" + value, {authority:authority,census_id:census_id,comm_id:comm_id,date_of_authority:date_of_authority,
		   date_of_seniority:date_of_seniority,Sen_id:Sen_id,date_of_applicability:date_of_applicability,reason:reason,comm_date:comm_date}, function(data){
		         
	          if(data=="update")
	        	  {
	        	  alert("Data approved successfully");
	        	  window.location.reload();
	        	  }
	        	
	          else if(parseInt(data)>0){
				$('#Sen_id').val(data);
	        	  alert("Data approved successfully")
	        	  window.location.reload();
	          }
	          else
	        	  {
	        	  alert(data);
	        	  
	        	  }

	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
 	  		});
}

function get_seniortiy(){
	 var comm_id=$('#comm_id').val(); 
	  $.post('get_seniortiy?' + key + "=" + value, {comm_id:comm_id}, function(j){
			var v=j.length;
			if(j!=""){
				$('#sen_authority').val(j[0].authority);
				$('#sen_date_of_authority').val(ParseDateColumncommission(j[0].date_of_authority));
				$('#date_of_seniority').val(ParseDateColumncommission(j[0].date_of_seniority));
				$("#reason").val(j[0].reason);
				$('#date_of_applicability').val(ParseDateColumncommission(j[0].applicablity_date));
				
			}
	  });
}
//***************** END CHANGE OF SENIORITY DETAILS************************//

//***************** START CHANGE OF TOS DATE DETAILS************************//
function validate_tos_date_for_edit() {
	 debugger;
 	
	if ($("input#tos_from_sus_no").val() == "") {
		alert("Please Select From SUS No");
		$("input#tos_from_sus_no").focus();
		return false;
	}		
	if($("#tos_dt_of_tos").val()== "DD/MM/YYYY"){
		alert("Please Select Date of TOS");
		$("input#tos_dt_of_tos").focus();
		return false;
	 }
	if ($("input#tos_dt_of_tos").val() == "") {
		alert("Please Select Date of TOS");
		$("input#tos_dt_of_tos").focus();
		return false;
	}

	   if(!confirm("Are You Sure! You Want To Approve This Data")){
    		return false;
    	}
		 if (getformatedDate($("input#comm_date").val()) > getformatedDate($("#tos_dt_of_tos").val())) {
		        alert("TOS Should Be Greater Or Equal To Date of Commission");
		        $("#tos_dt_of_tos").focus();
		        return false;
		    }		 
	
			var comm_id=$('#comm_id').val();
			var date_of_tos=$('#tos_dt_of_tos').val();	
	        var dt_tos_pre=$('#dt_tos_pre').val();	
	        var id=$('#tos_id').val();
	        var preid=$('#tos_preid').val(); 

			   $.post('update_tos_date_Action_3008?' + key + "=" + value, {comm_id:comm_id,date_of_tos:date_of_tos,dt_tos_pre:dt_tos_pre,id:id,preid:preid}, function(data){
				         
			          if(data=="update")
			        	  {
			        	  alert("Data approved successfully");
			        	  window.location.reload();
			        	 
			        	  }
			        	
			          else if(parseInt(data)>0){
						$('#Sen_id').val(data);
			        	  alert("Data approved successfully")
			        	  window.location.reload();
			        	 
			          }
			          else
			        	  {
			        	  alert(data);
			        	  
			        	  }

			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch");
		 	  		});
}

function getpersonaldataforchangetos(){
	
    	 var comm_id = $("#comm_id").val();
			$.post('GetpostinoutDataForTos?' + key+ "=" + value,{comm_id : comm_id},function(k) {
				if (k.length > 0) {
					debugger;
					$('#tos_id').val(k[0][0]);					
					$('#tos_name').val(k[0][2]);				
					$("#tos_rank1").text(k[0][3]);
					$("#tos_from_sus_no").text(k[0][8]);
					$("#tos_to_sus_no").text(k[0][5]);	
					$("#tos_to_unit_name").text(k[0][6]);
					$("#tos_dt_of_tos").val(ParseDateColumncommission(k[0][7]));
					
					
					
					$.ajaxSetup({
						async : false
					});					
				
					var sus_no = k[0][8];
					getunit(sus_no);
					function getunit(val) {
			                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
								var length = j.length - 1;
								var enc = j[length].substring(0,16);
								//alert("unit---" + dec(enc,j[0]));
								$("#tos_from_unit_name").text(dec(enc,j[0]));								
							}).fail(function(xhr,textStatus,errorThrown) {
					        });
					}
					$.ajaxSetup({
						async : false
					});							
								
								var comm_id = $("#comm_id").val();
								$.post('GetpostinoutPrevioustDateofTos?' + key + '=' + value, { comm_id: comm_id }, function (t) {
								    if (t.length > 0) {
								        debugger;
								        $("#dt_tos_pre").val(ParseDateColumncommission(t[0][1]));
								        $("#tos_preid").val(t[0][0]);								       
								    }
								});
								
								$.ajax({
								    async: false, 
								    
								});

			}
		});
			
		 	
		}
	
//***************** END CHANGE OF TOS DATE DETAILS************************//

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

function specialcharecterCDA(a)
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

function validateAge(input) {
    var currentDate = new Date();
    var inputDate = new Date(input.value);    
    var ageDifference = currentDate.getFullYear() - inputDate.getFullYear();   
    if (ageDifference < 18) {
        alert("Please enter age above 17.");     
        input.value = '';       
        input.focus();
    }
}


function validateDate(inputDate, inputElement) {
    var currentDate = new Date();
    var parts = inputDate.split('/');
    var selectedDate = new Date(parts[2], parts[1] - 1, parts[0]);
    
    if (selectedDate > currentDate) {
        alert("Future dates are not allowed.");
        inputElement.value = "DD/MM/YYYY";
        inputElement.style.color = "#000000";
    }
}



</script>
