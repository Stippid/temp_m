<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css">


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>



<form:form name="Report_Serving" id="Report_Serving"
	action="Report_Serving_Action" method="post" class="form-horizontal"
	commandName="Report_Serving_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 style="text-transform: capitalize">UNIT STR RETURNS</h5>
					<h6 class="enter_by"></h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No: </label>
								</div>
								<div class="col-md-8">
									<label id="unit_sus_no_hid"
										style="display: none; text-transform: uppercase;"><b>${roleSusNo}</b></label>
									<input type="text" id="unit_sus_no" name="unit_sus_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)" style="display: none">
									<input type="hidden" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name: </label>

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
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Month </label>

								</div>
								<div class="col-md-8">
									<select id="month" name="month" class="required form-control">
										<option value="0">--Select--</option>
										<option value="1">January</option>
										<option value="2">February</option>
										<option value="3">March</option>
										<option value="4">April</option>
										<option value="5">May</option>
										<option value="6">June</option>
										<option value="7">July</option>
										<option value="8">August</option>
										<option value="9">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">

									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Year </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year" name="year"
										class="form-control autocomplete" maxlength="4"
										onkeypress="return isNumber(event)"
										onclick="return AvoidSpace(event)" autocomplete="off"
										onkeyup="return specialcharecter(this)">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Present Return No</label>

								</div>
								<div class="col-md-8">
									<input type="text" id="present_return_no"
										name="present_return_no" class="form-control autocomplete"
										autocomplete="off" maxlength="10"
										onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Date of Present Return </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="present_return_dt"
										id="present_return_dt" maxlength="10"
										onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
										style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
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
									<label class=" form-control-label"> Last Return No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="last_return_no" name="last_return_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="15" onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong> Date of Last Return </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="last_return_dt" id="last_return_dt"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 85%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
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
									<label class=" form-control-label"> Command </label>
								</div>
								<div class="col-md-8">
									<label><b>${unit_name}</b></label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Establishment No </label>
								</div>
								<div class="col-md-8">
									      <input type="hidden" id="offf_auth_count" name="offf_auth_count">
										  <input type="hidden" id="offf_posted_count" name="offf_posted_count"> 
										  <input	type="hidden" id="offfdefi_count" name="offfdefi_count">
										  <input type="hidden" id="offf_sur_count" name="offf_sur_count">
										  
										       <input type="hidden" id="offf_mns_auth_count" name="offf_mns_auth_count">
										  <input type="hidden" id="offf_mns_posted_count" name="offf_mns_posted_count"> 
										  <input	type="hidden" id="offf_mnsdefi_count" name="offf_mnsdefi_count">
										  <input type="hidden" id="offf_mns_sur_count" name="offf_mns_sur_count">
										  
										   <input type="hidden" id="jcos_auth_count" name="jcos_auth_count"> <input
										type="hidden" id="jcos_posted_count" name="jcos_posted_count"> <input type="hidden" id="jcodefi_count" name="jcodefi_count"> <input type="hidden" id="jco_sur_count" name="jco_sur_count"> <input type="hidden" id="or_auth_count" name="or_auth_count"> <input type="hidden" id="or_posted_count"
										name="or_posted_count"> <input type="hidden"
										id="ordefi_count" name="ordefi_count"> <input
										type="hidden" id="or_sur_count" name="or_sur_count"> <input
										type="hidden" id="auth_civ_count" name="auth_civ_count">
									<input type="hidden" id="civ_posted_count"
										name="civ_posted_count"> <input type="hidden"
										id="civdefi_count" name="civdefi_count"> <input
										type="hidden" id="civ_sur_count" name="civ_sur_count">
									<input type="text" id="we_pe_no" name="we_pe_no"
										class="form-control autocomplete" readonly="readonly"
										autocomplete="off" maxlength="15"
										onkeyup="return specialcharecter(this)"
										onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
					</div>


				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Report_3009Url" class="btn btn-success btn-sm">Clear</a>
					<input type="button" class="btn btn-info btn-sm"
						onclick="Search();" value="Search">
				</div>

			</div>
		</div>

	</div>
	<div class="datatablediv">
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>SECTION I : AUTH STR (As per WE/PE)</h5>

			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getauthServingTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th style="text-align: center;" id="appt">Arms /Services</th>
							<th style="text-align: center;" id="rankT">Offrs</th>
							<th style="text-align: center;" id="name">MNS Offrs</th>
							<th style="text-align: center;" id="personnel_no">JCOs</th>
							<th style="text-align: center;" id="cda_acc_no">OR</th>
							<th style="text-align: center;" id="med_cat">Remarks</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>


		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getauthcivTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>

							<th colspan="4" style="text-align: center;" id="parent_arm">
								Civilian Employee</th>
							<th rowspan="3" style="text-align: center;" id="med_cat">
								Remarks</th>
						</tr>
						<tr>
							<th colspan="2" style="text-align: center;">Gazetted</th>
							<th colspan="2" style="text-align: center;">Non Gasetted</th>
						</tr>
						<tr>
							<th style="text-align: center;">GP 'A'</th>


							<th style="text-align: center;">GP 'B'</th>
							<th style="text-align: center;">GP 'B'</th>
							<th style="text-align: center;">GP 'C'</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>


		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>SECTION II : POSTED STR</h5>

			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getpostedServingTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2" style="text-align: center;" id="appt">Arms
								/Services</th>
							<th colspan="6" style="text-align: center;" id="rankT">
								Offrs</th>
							<th colspan="6" style="text-align: center;" id="name">MNS
								Offrs</th>
							<th colspan="4" style="text-align: center;" id="personnel_no">
								JCOs</th>
							<th colspan="4" style="text-align: center;" id="cda_acc_no">
								OR</th>
						</tr>
						<tr>
							<th style="text-align: center;">Brig and above</th>
							<th style="text-align: center;">Col</th>
							<th style="text-align: center;">Col [Ts]</th>
							<th style="text-align: center;">ltcol</th>
							<th style="text-align: center;">Maj</th>
							<th style="text-align: center;">Capt/Lt</th>

							<th style="text-align: center;">Brig and above</th>
							<th style="text-align: center;">Col</th>
							<th style="text-align: center;">Lt Col [S]</th>
							<th style="text-align: center;">Lt Col [TS]</th>
							<th style="text-align: center;">Maj</th>
							<th style="text-align: center;">Capt/Lt</th>

							<th style="text-align: center;">Sub Maj</th>
							<th style="text-align: center;">Sub</th>
							<th style="text-align: center;">Nb Sub</th>
							<th style="text-align: center;">WARRANT OFFICER</th>


							<th style="text-align: center;">Hav</th>
							<th style="text-align: center;">NK</th>
							<th style="text-align: center;">LNK/Sep</th>
							<th style="text-align: center;">RECTS</th>





						</tr>



					</thead>

				</table>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getpostcivTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>

							<th colspan="4" style="text-align: center;" id="parent_arm">
								Civilian Employee</th>
							<th rowspan="3" style="text-align: center;" id="med_cat">
								Remarks</th>
						</tr>
						<tr>
							<th colspan="2" style="text-align: center;">Gazetted</th>
							<th colspan="2" style="text-align: center;">Non Gasetted</th>
						</tr>
						<tr>
							<th style="text-align: center;">GP 'A'</th>
							<th style="text-align: center;">GP 'B'</th>
							<th style="text-align: center;">GP 'B'</th>
							<th style="text-align: center;">GP 'C'</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>SECTION III : SUMMARY</h5>

			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getsummuryTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<th colspan="" style="text-align: center;" id=""></th>
						<th colspan="2" style="text-align: center;" id="">Auth</th>
						<th colspan="" style="text-align: center;" id=""></th>
						<th colspan="" style="text-align: center;" id=""></th>
						<th colspan="" style="text-align: center;" id=""></th>
						<th colspan="" style="text-align: center;" id=""></th>
						<tr>
							<th style="text-align: center;" id="appt">Cat</th>
							<th style="text-align: center;" id="rankT">Auth as per WE/PE</th>
							<th style="text-align: center;" id="rankT">Over and Above (Pl type here, if any)</th>
							<th style="text-align: center;" id="name">Posted</th>
							<th style="text-align: center;" id="personnel_no">Sur</th>
							<th style="text-align: center;" id="cda_acc_no">Defi</th>
							<th style="text-align: center;" id="cda_acc_no">Auth Letter (Over and Above)</th>
						</tr>

					</thead>
					<tbody>
						<tr>
							<td style="font-size: 15px; text-align: center;">Offr's</td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_auth_09">0</td>
							<td style="font-size: 15px; text-align: center;"><input
								type="number" id="officerauth" name="officerauth"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="updateValues(this)" step="1" min="0"></td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_posted_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="offf_sur_09">0</td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_defi_09">0</td>

							<td style="font-size: 15px; text-align: center;"><input
								type="text" id="officerauthno" name="officerauthno"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="return specialcharecter(this)"
								></td>
						</tr>
							<tr>
							<td style="font-size: 15px; text-align: center;">Offr's MNS</td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_mns_auth_09">0</td>
							<td style="font-size: 15px; text-align: center;"><input
								type="number" id="officermnsauth" name="officermnsauth"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="updateValues(this)" step="1" min="0"></td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_mns_posted_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="offf_mns_sur_09">0</td>
							<td style="font-size: 15px; text-align: center;"
								id="offf_mns_defi_09">0</td>

							<td style="font-size: 15px; text-align: center;"><input
								type="text" id="officermnsauthno" name="officermnsauthno"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="return specialcharecter(this)"
								onkeypress="return AvoidSpace(event)"></td>
						</tr>
						<tr>
							<td style="font-size: 15px; text-align: center;">JCOs</td>
							<td style="font-size: 15px; text-align: center;" id="jco_auth_09">0</td>
							<td style="font-size: 15px; text-align: center;"><input
								type="number" id="jcoauth" name="jcoauth"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="updateValues(this)" step="1" min="0"></td>


							<td style="font-size: 15px; text-align: center;"
								id="jco_posted_09">0</td>

							<td style="font-size: 15px; text-align: center;" id="jco_sur_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="jco_defi_09">0</td>

							<td style="font-size: 15px; text-align: center;"><input
								type="text" id="jcoauthno" name="jcoauthno"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="return specialcharecter(this)"
								onkeypress="return AvoidSpace(event)"></td>

						</tr>
						<tr>
							<td style="font-size: 15px; text-align: center;">OR</td>
							<td style="font-size: 15px; text-align: center;" id="or_auth_09">0</td>
							<td style="font-size: 15px; text-align: center;"><input
								type="number" id="orauth" name="orauth"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="updateValues(this)" step="1" min="0"></td>
							<td style="font-size: 15px; text-align: center;"
								id="or_posted_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="or_sur_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="or_defi_09">0</td>

							<td style="font-size: 15px; text-align: center;"><input
								type="text" id="orauthno" name="orauthno"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="return specialcharecter(this)"
								onkeypress="return AvoidSpace(event)"></td>
						</tr>
						<tr>
							<td style="font-size: 15px; text-align: center;">Civ</td>
							<td style="font-size: 15px; text-align: center;" id="civ_auth_09">0</td>
							<td style="font-size: 15px; text-align: center;"><input
								type="number" id="civauth" name="civauth"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="updateValues(this)" step="1" min="0"></td>


							<td style="font-size: 15px; text-align: center;"
								id="civ_posted_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="civ_sur_09">0</td>
							<td style="font-size: 15px; text-align: center;" id="civ_defi_09">0</td>

							<td style="font-size: 15px; text-align: center;"><input
								type="text" id="civauthno" name="civauthno"
								class="form-control autocomplete" autocomplete="off"
								onkeyup="return specialcharecter(this)"
								onkeypress="return AvoidSpace(event)"></td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>SECTION IV : RANK AND TRADE WISE (JCOs/OR)</h5>

			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getrankandtradeTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2" style="text-align: center;" id="appt">Arms
								/Services</th>
							<th rowspan="2" style="text-align: center;" id="rankT">
								Trade</th>
							<th colspan="4" style="text-align: center;" id="name">JCOs</th>
							<th colspan="4" style="text-align: center;" id="cda_acc_no">
								OR</th>

							<th rowspan="2" style="text-align: center;" id="med_cat">
								Total</th>
						</tr>
						<tr>
							<th style="text-align: center;" id="date_of_appointment">
								Sub Maj</th>
							<th style="text-align: center;" id="date_of_appointment">
								Sub</th>
							<th style="text-align: center;" id="date_of_appointment">Nb
								Sub</th>
							<th style="text-align: center;" id="date_of_appointment">
								WARRANT OFFICER</th>
							<th style="text-align: center;" id="date_of_appointment">
								Hav</th>
							<th style="text-align: center;" id="date_of_appointment">NK
							</th>
							<th style="text-align: center;" id="date_of_appointment">
								LNK/Sep</th>
							<th style="text-align: center;" id="date_of_appointment">
								RECTS</th>


						</tr>
					</thead>

				</table>

			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>SECTION V : RELIGIOUS DENOMINATION</h5>

			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getreligiousTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2" style="text-align: center;" id="rankT">
								Religion</th>
							<th rowspan="2" style="text-align: center;" id="appt">Arms
								/Services</th>
							<th colspan="2" style="text-align: center;" id="name">
								Posted Str incl ERE pers</th>
							<th colspan="2" style="text-align: center;" id="cda_acc_no">
								Religious Teacher</th>
						</tr>
						<tr>

							<th style="text-align: center;" id="name">JCOs</th>
							<th style="text-align: center;" id="cda_acc_no">OR</th>
							<th style="text-align: center;" id="date_of_seniority">Auth
							</th>
							<th style="text-align: center;" id="date_of_appointment">
								Held</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>

	<div class="col-md-12" id="obr">
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-2">
					<label class=" form-control-label"> Remarks </label>
				</div>
				<div class="col-md-6">
					<textarea id="remarks" name="remarks"
						class="form-control autocomplete" autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		
		
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-2">
					<label class=" form-control-label"> Distribution List  </label>
				</div>
				<div class="col-md-6">
					<textarea id="distlist" name="distlist"
						class="form-control autocomplete" autocomplete="off"></textarea>
				</div>
			</div>
		</div>

	</div>

	<div class="card-footer" align="center" class="form-control"
		id="Savebtn">
		<c:if test="${roleType == 'DEO' || roleType == 'ALL'}">
			<input type="button" class="btn btn-primary btn-sm"
				value="Submit For Approval" id="saveb" onclick="Report_Save_fn();">
		</c:if>
	</div>
	
	<input type="hidden" id="monthhidden" name="monthhidden"  > 
    <input type="hidden" id="yearhidden" name="yearhidden">  
</form:form>
<c:url value="GetSearch_Report_Serving3009" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no2">

	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0">

</form:form>
<script>
	var reloadtable = false;
	jQuery(function($) { //on document.ready  

		datepicketDate('last_return_dt');
		datepicketDate('present_return_dt');

	});
	$(document).ready(function() {


		$("#WaitLoader").show();
		var r = ('${roleAccess}');

		
		 if('${month1}' != 0) {
			    $("#month").val('${month1}');
			    $("#monthhidden").val('${month1}');
			} else {
			    var currentDate = new Date();				    
			    var month = currentDate.getMonth(); 
			    $("#month").val(month);
			}

			if('${year1}' != '') {
			    $("#year").val('${year1}');
			    $("#yearhidden").val('${year1}');
			} else {
			    var currentDate = new Date();
			    var month = currentDate.getMonth();
			    var year = currentDate.getFullYear();			    
			 
			    if(month === 0) {
			        year = year - 1;
			    }
			    $("#year").val(year);
			}


		
		if (r == "Unit") {
			$("#unit_sus_no_hid").show();
			$("#unit_name_l").show();
			$("#month").attr("readonly", "readonly");
			$("#year").prop("readonly", true);
		} else if (r == "MISO") {

			$("input#unit_sus_no").show();
			$("input#unit_name").show();
		}

		if ('${roleSusNo}' != "") {
			$('#roleSusNo').val('${roleSusNo}');
		}

		if ('${unit_sus_no2}' != "") {
			$("#unit_sus_no").val('${unit_sus_no2}');

			var sus_no = '${unit_sus_no2}';
			$.post("getTargetUnitNameList?" + key + "=" + value, {
				sus_no : sus_no
			}, function(j) {
			}).done(function(j) {
				var length = j.length - 1;
				var enc = j[length].substring(0, 16);
				$("#unit_name").val(dec(enc, j[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});

			$("#unit_sus_no").attr("disabled", true);
			$("#unit_name").attr("disabled", true);
		}

		if ('${size}' != "" || '${size}' != 0) {
			$("div#getSearch_Letter").show();
			$("div#Note").show();

			var t1 = new Date();
			var Newmonth1 = t1.getMonth() + 1;
			var Newyear1 = t1.getFullYear();
			var mon1 = $("#month").val();
			var yr1 = $("#year").val();

			/*  
			if(mon1 == Newmonth1 && yr1 == Newyear1){
				 $("div#Savebtn").show();		
			}else{
				$("div#Savebtn").hide();	
			}  */
		} else if ('${roleType}' == "DEO" || '${roleType}' == "ALL") {
			$("div#getSearch_Letter").show();
		} else {
			$("div#getSearch_Letter").hide();
			$("div#Savebtn").hide();
			$("div#obr").hide();
		}

		mockjax1('getauthServingTable');
		table = dataTable11('getauthServingTable');

		mockjax1('getauthcivTable');
		table2 = dataTable11('getauthcivTable');

		mockjax1('getpostedServingTable');
		table3 = dataTable11('getpostedServingTable');

		mockjax1('getpostcivTable');
		table4 = dataTable11('getpostcivTable');

		mockjax1('getrankandtradeTable');
		table5 = dataTable11('getrankandtradeTable');

		mockjax1('getreligiousTable');
		table6 = dataTable11('getreligiousTable');

		$('#btn-reload').on('click', function() {

			table.ajax.reload();
			table2.ajax.reload();
			table3.ajax.reload();
			table4.ajax.reload();
			table5.ajax.reload();
			table6.ajax.reload();

		});

	});
</script>



<script type="text/javascript">
	////////////////////data table////////////

	function data(tableName) {

		jsondata = [];

		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];
		var unit_sus_no = $("#unit_sus_no").val();
		var last_return_dt = $("#last_return_dt").val();
		var cont_comd = $("#cont_comd").val();
		var month = $('#month').val();
		var year = $('#year').val();

		var totalAuth = 0;
		var totalHeld = 0;
		var sum_held = 0;
		var sum_auth = 0;

		///////////serving/////
		if (tableName == "getauthServingTable") {

			$.post("getauthdetails3009Count?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getauthdetails3009?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {

				var offf_auth = 0;
				var jcos_auth = 0;
				var or_auth = 0;
				var offf_mns=0;

				var officer = 0;
				var jco = 0;
				var or = 0;
				var officer_mns=0;

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					offf_auth = offf_auth + j[i].offr;
					jcos_auth = jcos_auth + j[i].jco;
					or_auth = or_auth + j[i].ors;
					offf_mns = offf_mns + j[i].mns_off;

					jsondata.push([ j[i].arm_desc, j[i].offr, j[i].mns_off, j[i].jco,
							j[i].ors, " " ]);
					officer += j[i].offr;
					jco += j[i].jco;
					or += j[i].ors;
					officer_mns += j[i].mns_off

				}

				jsondata.push([ "TOTAL".bold(), officer, officer_mns, jco, or, "" ]);

				document.getElementById("offf_auth_09").innerHTML = offf_auth;
				document.getElementById("offf_mns_auth_09").innerHTML = officer_mns;
				document.getElementById("jco_auth_09").innerHTML = jcos_auth;
				document.getElementById("or_auth_09").innerHTML = or_auth;

			});
		}

		if (tableName == "getauthcivTable") {

			$.post("getauthcivTableCount?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getauthcivTable3009?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				var civ_auth = 0;
				var civ = 0;

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					civ_auth = civ_auth + j[i].gp_a_gaz + j[i].gp_b_gaz
							+ j[i].gp_b_non_gaz + j[i].gp_c_non_gaz;

					jsondata.push([ j[i].gp_a_gaz, j[i].gp_b_gaz,
							j[i].gp_b_non_gaz, j[i].gp_c_non_gaz, " " ]);
				}
				document.getElementById("civ_auth_09").innerHTML = civ_auth;
			});
		}

		if (tableName == "getpostcivTable") {

			$.post("getpostcivTableCount?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$
					.post(
							"getpostcivTable3009?" + key + "=" + value,
							{
								startPage : startPage,
								pageLength : pageLength,
								Search : Search,
								orderColunm : orderColunm,
								orderType : orderType,
								month : month,
								year : year,
								unit_sus_no : unit_sus_no
							},
							function(j) {
								var civ_posted = 0;

								for (var i = 0; i < j.length; i++) {
									var sr = i + 1;

									civ_posted = civ_posted + j[i].gp_a_gaz
											+ j[i].gp_b_gaz + j[i].gp_b_non_gaz
											+ j[i].gp_c_non_gaz;

									jsondata.push([ j[i].gp_a_gaz,
											j[i].gp_b_gaz, j[i].gp_b_non_gaz,
											j[i].gp_c_non_gaz, " " ]);
								}

								document.getElementById("civ_posted_09").innerHTML = civ_posted;

							});
		}

		if (tableName == "getpostedServingTable") {

			$.post("getpostedServing3009Count?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$
					.post(
							"getpostedServing3009?" + key + "=" + value,
							{
								startPage : startPage,
								pageLength : pageLength,
								Search : Search,
								orderColunm : orderColunm,
								orderType : orderType,
								month : month,
								year : year,
								unit_sus_no : unit_sus_no
							},
							function(j) {
								console.log(j[0])

								var offf_posted = 0;
								var jcos_posted = 0;
								var or_posted = 0;
								var offf_posted_mns = 0;

								var brig = 0;
								var r_col = 0;
								var r_col_ts = 0;
								var r_ltcol = 0;
								var r_maj = 0;
								var r_capt_lt = 0;
								var brig_mns = 0;
								var r_col_mns = 0;							
								var r_ltcol_s_mns = 0;
								var r_ltcol_ts_mns = 0;
								var r_maj_mns = 0;
								var r_capt_lt_mns = 0;
								var r_sub_maj = 0;
								var r_sub = 0;
								var r_nb_sub = 0;
								var r_warrant_off = 0;
								var r_hav = 0;
								var r_nk = 0;
								var r_sep = 0;
								var r_rect = 0;

								for (var i = 0; i < j.length; i++) {
									var sr = i + 1;

									offf_posted = offf_posted + j[i].brig_above
											+ j[i].col + j[i].col_ts
											+ j[i].ltcol + j[i].maj
											+ j[i].capt_lt;
									
									offf_posted_mns = offf_posted_mns + j[i].brig_above_mns
									+ j[i].col_mns + j[i].ltcol_s_mns
									+ j[i].ltcol_ts_mns + j[i].maj_mns
									+ j[i].capt_lt_mns;
									
									jcos_posted = jcos_posted + j[i].sub_maj
											+ j[i].sub + j[i].nb_sub
											+ j[i].warrant_off;
									or_posted = or_posted + j[i].hav + j[i].nk
											+ j[i].sep + j[i].rect;

									brig += j[i].brig_above;
									r_col += j[i].col;
									r_col_ts += j[i].col_ts;
									r_ltcol += j[i].ltcol;
									r_maj += j[i].maj;
									r_capt_lt += j[i].capt_lt;
									
									brig_mns += j[i].brig_above_mns;
									r_col_mns += j[i].col_mns;									
									r_ltcol_s_mns += j[i].ltcol_s_mns;
									r_ltcol_ts_mns += j[i].ltcol_ts_mns;
									r_maj_mns += j[i].maj_mns;
									r_capt_lt_mns += j[i].capt_lt_mns;
									
									r_sub_maj += j[i].sub_maj;
									r_sub += j[i].sub;
									r_nb_sub += j[i].nb_sub;
									r_warrant_off += j[i].warrant_off;
									r_hav += j[i].hav;
									r_nk += j[i].nk;
									r_sep += j[i].sep;
									r_rect += j[i].rect;

									jsondata.push([ j[i].arm_desc,
											j[i].brig_above, j[i].col,
											j[i].col_ts, j[i].ltcol, j[i].maj,
											j[i].capt_lt, j[i].brig_above_mns,j[i].col_mns,j[i].ltcol_s_mns,j[i].ltcol_ts_mns,
											j[i].maj_mns,j[i].capt_lt_mns, j[i].sub_maj, j[i].sub,
											j[i].nb_sub, j[i].warrant_off,
											j[i].hav, j[i].nk, j[i].sep,
											j[i].rect ]);

								}

								jsondata.push([ "TOTAL".bold(), brig, r_col,
										r_col_ts, r_ltcol, r_maj, r_capt_lt,
										brig_mns, r_col_mns, r_ltcol_s_mns, r_ltcol_ts_mns, r_maj_mns,r_capt_lt_mns, r_sub_maj,
										r_sub, r_nb_sub, r_warrant_off, r_hav,
										r_nk, r_sep, r_rect ]);

								document.getElementById("offf_posted_09").innerHTML = offf_posted;
								document.getElementById("offf_mns_posted_09").innerHTML = offf_posted_mns;
								document.getElementById("jco_posted_09").innerHTML = jcos_posted;
								document.getElementById("or_posted_09").innerHTML = or_posted;

							});
		}

		if (tableName == "getrankandtradeTable") {

			$.post("gettradeServing3009Count?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("gettradeServing3009?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				console.log(j[0])
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ j[i].arm_desc, j[i].trade, j[i].sub_maj,
							j[i].sub, j[i].nb_sub, j[i].warrnt_off, j[i].hav,
							j[i].nk, j[i].sep, j[i].rects, j[i].total ]);

				}
			});
		}

		if (tableName == "getreligiousTable") {

			$.post("getreligious3009Count?" + key + "=" + value, {
				Search : Search,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getreligious3009?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				month : month,
				year : year,
				unit_sus_no : unit_sus_no
			}, function(j) {
				console.log(j[0])
				var jco = 0;
				var or = 0;
				var au = 0;
				var held = 0;
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ j[i].religion_name, j[i].arm_desc,
							j[i].jcos, j[i].ors, j[i].auth,
							j[i].held_religious_teacher ]);

					jco += j[i].jcos;
					or += j[i].ors;
					au += j[i].auth;
					held += j[i].held_religious_teacher;

				}

				jsondata.push([ "", "TOTAL".bold(), jco, or, au, held ]);
				$("#WaitLoader").hide();
			});

		}

		var held_off = document.getElementById("offf_posted_09").innerHTML;
		var auth_off = document.getElementById("offf_auth_09").innerHTML;
		

		$("#offf_auth_count").val(auth_off);
		$("#offf_posted_count").val(held_off);
		
		$("#offf_mns_auth_count").val(auth_off_mns);
		$("#offf_mns_posted_count").val(held_off_mns);

		var off_sur = held_off - auth_off;
		//var offdefi = 0;
		if (off_sur >= 0) {
			document.getElementById("offf_defi_09").innerHTML = 0;
			document.getElementById("offf_sur_09").innerHTML = off_sur;
			$("#offf_sur_count").val(off_sur);
			$("#offfdefi_count").val("0");
		} else {
			document.getElementById("offf_defi_09").innerHTML = off_sur;
			document.getElementById("offf_sur_09").innerHTML = 0;
			$("#offfdefi_count").val(off_sur);
			$("#offf_sur_count").val("0");
		}
		
		
		var held_off_mns=document.getElementById("offf_mns_posted_09").innerHTML;
		var auth_off_mns = document.getElementById("offf_mns_auth_09").innerHTML;
		
		$("#offf_mns_auth_count").val(auth_off_mns);
		$("#offf_mns_posted_count").val(held_off_mns);
		
		var off_mns_sur = held_off_mns - auth_off_mns;
		
		if (off_mns_sur >= 0) {
			document.getElementById("offf_mns_defi_09").innerHTML = 0;
			document.getElementById("offf_mns_sur_09").innerHTML = off_mns_sur;
			$("#offf_mns_sur_count").val(off_mns_sur);
			$("#offf_mnsdefi_count").val("0");
		} else {
			document.getElementById("offf_mns_defi_09").innerHTML = off_mns_sur;
			document.getElementById("offf_mns_sur_09").innerHTML = 0;
			$("#offf_mnsdefi_count").val(off_mns_sur);
			$("#offf_mns_sur_count").val("0");
		}

		var held_jco = document.getElementById("jco_posted_09").innerHTML;
		var auth_jco = document.getElementById("jco_auth_09").innerHTML;

		$("#jcos_posted_count").val(held_jco);
		$("#jcos_auth_count").val(auth_jco);

		var jco_sur = held_jco - auth_jco;

		if (jco_sur >= 0) {
			document.getElementById("jco_defi_09").innerHTML = 0;
			document.getElementById("jco_sur_09").innerHTML = jco_sur;
			$("#jco_sur_count").val(jco_sur);
			$("#jcodefi_count").val("0");
		} else {
			document.getElementById("jco_defi_09").innerHTML = jco_sur;
			document.getElementById("jco_sur_09").innerHTML = 0;
			$("#jcodefi_count").val(jco_sur);
			$("#jco_sur_count").val("0");
		}

		var held_or = document.getElementById("or_posted_09").innerHTML;
		var auth_or = document.getElementById("or_auth_09").innerHTML;

		$("#or_auth_count").val(auth_or);
		$("#or_posted_count").val(held_or);

		var or_sur = held_or - auth_or;
		if (or_sur >= 0) {
			document.getElementById("or_defi_09").innerHTML = 0;
			document.getElementById("or_sur_09").innerHTML = or_sur;
			$("#or_sur_count").val(or_sur);
			$("#ordefi_count").val("0");
		} else {
			document.getElementById("or_defi_09").innerHTML = or_sur;
			document.getElementById("or_sur_09").innerHTML = 0;
			$("#ordefi_count").val(or_sur);
			$("#or_sur_count").val("0");

		}

		var held_civ = document.getElementById("civ_posted_09").innerHTML;
		var auth_civ = document.getElementById("civ_auth_09").innerHTML;

		$("#auth_civ_count").val(auth_civ);
		$("#civ_posted_count").val(held_civ);

		var civ_sur = held_civ - auth_civ;
		if (civ_sur >= 0) {
			document.getElementById("civ_defi_09").innerHTML = 0;
			document.getElementById("civ_sur_09").innerHTML = civ_sur;
			$("#civ_sur_count").val(civ_sur);
			$("#civdefi_count").val("0");
		} else {
			document.getElementById("civ_defi_09").innerHTML = civ_sur;
			document.getElementById("civ_sur_09").innerHTML = 0;
			$("#civdefi_count").val(civ_sur);
			$("#civ_sur_count").val("0");

		}

	}

	$("#unit_sus_no")
			.keyup(
					function() {
						if (reloadtable) {
							reloadtable = false;
							table.ajax.reload();
							table2.ajax.reload();
							table3.ajax.reload();
							table4.ajax.reload();
							table5.ajax.reload();
							table6.ajax.reload();
						}
						var sus_no = this.value;
						var susNoAuto = $("#unit_sus_no");

						susNoAuto
								.autocomplete({
									source : function(request, response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetSUSNoList?"
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
													.getElementById("unit_name").value = "";
											susNoAuto.val("");
											susNoAuto.focus();
											return false;
										}
									},
									select : function(event, ui) {
										var sus_no = ui.item.value;
										$.post(
												"getTargetUnitNameList?" + key
														+ "=" + value, {
													sus_no : sus_no
												}, function(j) {
												}).done(
												function(j) {
													var length = j.length - 1;
													var enc = j[length]
															.substring(0, 16);
													$("#unit_name").val(
															dec(enc, j[0]));
												}).fail(
												function(xhr, textStatus,
														errorThrown) {
												});
									}
								});
					});

	// unit name
	$("input#unit_name").keypress(function() {
		if (reloadtable) {
			reloadtable = false;
			table.ajax.reload();
			table2.ajax.reload();
			table3.ajax.reload();
			table4.ajax.reload();
			table5.ajax.reload();
			table6.ajax.reload();
		}
		var unit_name = this.value;
		var susNoAuto = $("#unit_name");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getTargetUnitsNameActiveList?" + key + "=" + value,
					data : {
						unit_name : unit_name
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}

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
					alert("Please Enter Approved Unit Name.");
					document.getElementById("unit_name").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {
				var unit_name = ui.item.value;

				$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
					target_unit_name : unit_name
				}, function(data) {
				}).done(function(data) {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#unit_sus_no").val(dec(enc, data[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		});
	});
	function Search() {

		$("#WaitLoader").show();

		var t = new Date();

		var r = ('${roleAccess}');
		if (r == "MISO") {
			if ($('#unit_sus_no').val().trim() == "") {
				alert("Please Enter Unit Sus No");
				$('#unit_sus_no').focus();
				$("#WaitLoader").hide();
				return false;
			}
		}

		$("#unit_sus_no2").val($("#unit_sus_no").val());

		//	document.getElementById('searchForm').submit();
		table.ajax.reload();
		table2.ajax.reload();
		table3.ajax.reload();
		table4.ajax.reload();
		table5.ajax.reload();
		table6.ajax.reload();
		$("#WaitLoader").hide();
		$('#saveb').prop('disabled', false);
		reloadtable = true;

	}
	var submitButton = document.getElementById("saveb");
	function Report_Save_fn() {

		var r = ('${roleAccess}');		
		if (r != "Unit"){
			if ($("input#unit_sus_no").val() == "") {
				alert("Please Enter Sus No ");
				$("input#unit_sus_no").focus();
				return false;
			}
		}

		

	 	

		if ($("select#month").val() == "0") {
			alert("Please Enter month ");
			$("select#month").focus();
			return false;
		}
		if ($("input#year").val() == "") {
			alert("Please Enter year ");
			$("input#year").focus();
			return false;
		}
		if ($("input#present_return_no").val() == "") {
			alert("Please Enter present return no ");
			$("input#present_return_no").focus();
			return false;
		}
		if ($("input#present_return_dt").val() == ""
				|| $("input#present_return_dt").val() == "DD/MM/YYYY") {
			alert("Please Enter present return dt ");
			$("input#gmpresent_return_dtail").focus();
			return false;
		}

		
	
	if ($("input#officerauth").val() != "") {
			if ($("input#officerauthno").val() == "") {
				alert("Please Enter Offr's Auth no ");
				$("input#officerauthno").focus();
				return false;
			}
		}

		if ($("input#officermnsauth").val() != "") {
			if ($("input#officermnsauthno").val() == "") {
				alert("Please Enter Offr's MNS Auth no ");
				$("input#officermnsauthno").focus();
				return false;
			}
		}

		if ($("input#jcoauth").val() != "") {
			if ($("input#jcoauthno").val() == "") {
				alert("Please Enter JCO'S Auth no ");
				$("input#jcoauthno").focus();
				return false;
			}
		}

		if ($("input#orauth").val() != "") {
			if ($("input#orauthno").val() == "") {
				alert("Please Enter OR Auth no ");
				$("input#orauthno").focus();
				return false;
			}
		}
		if ($("input#civauth").val() != "") {
			if ($("input#civauthno").val() == "") {
				alert("Please Enter CIV Auth no ");
				$("input#civauthno").focus();
				return false;
			}
		}
		
		 var selectedMonth=$('#month').val();		
		 var selectedYear=$('#year').val();
		
		 var currentMonth=$('#monthhidden').val();		
		 var currentYear=$('#yearhidden').val();	
		 

		 if (selectedMonth != currentMonth  ) {
		 alert(" 1. Data cannot be approved. \n 2. Unit can only approve IAFF 3009 for previous month.");
		    $("select#month").focus();
		  return false;
		 }
		 
		 if (selectedYear != currentYear) {
			  alert("Only the current year is allowed.");
			   $("input#year").focus();
			  return false;
		 }
		//disableButton();

		$('#saveb').prop('disabled', true);
		debugger;
		var month = $('#month').val();
		var year = $('#year').val();
		var present_return_no = $("#present_return_no").val();
		var present_return_dt = $("#present_return_dt").val();
		var last_return_no = $("#last_return_no").val();
		var last_return_dt = $("#last_return_dt").val();

		var unit_sus_no = $("#unit_sus_no").val();

		var offf_auth_count = $("#offf_auth_count").val();
		var offf_posted_count = $("#offf_posted_count").val();
		var offf_defi_count = $("#offfdefi_count").val();
		var offf_sur_count = $("#offf_sur_count").val();
		var officerauth = $("#officerauth").val();
		var officerauthno = $("#officerauthno").val();
		
		var offf_mns_auth_count = $("#offf_mns_auth_count").val();
		var offf_mns_posted_count = $("#offf_mns_posted_count").val();
		var offf_mns_defi_count = $("#offf_mnsdefi_count").val();
		var offf_mns_sur_count = $("#offf_mns_sur_count").val();
		var officermnsauth = $("#officermnsauth").val();
		var officermnsauthno = $("#officermnsauthno").val();
		

		var jcos_auth_count = $("#jcos_auth_count").val();
		var jcos_posted_count = $("#jcos_posted_count").val();
		var jcodefi_count = $("#jcodefi_count").val();
		var jco_sur_count = $("#jco_sur_count").val();
		var jcoauth = $("#jcoauth").val();
		var jcoauthno = $("#jcoauthno").val();

		var or_auth_count = $("#or_auth_count").val();
		var or_posted_count = $("#or_posted_count").val();
		var ordefi_count = $("#ordefi_count").val();
		var or_sur_count = $("#or_sur_count").val();
		var orauth = $("#orauth").val();
		var orauthno = $("#orauthno").val();

		var auth_civ_count = $("#auth_civ_count").val();
		var civ_posted_count = $("#civ_posted_count").val();
		var civdefi_count = $("#civdefi_count").val();
		var civ_sur_count = $("#civ_sur_count").val();
		var civauth = $("#civauth").val();
		var civauthno = $("#civauthno").val();
		var remarks = $("#remarks").val();
		var distlist = $("#distlist").val();

		$.post('Insert_3009?' + key + "=" + value, {
			month : month,
			year : year,
			present_return_no : present_return_no,
			present_return_dt : present_return_dt,
			last_return_no : last_return_no,
			last_return_dt : last_return_dt,
			unit_sus_no : unit_sus_no,
			offf_auth_count : offf_auth_count,
			offf_posted_count : offf_posted_count,
			offf_defi_count : offf_defi_count,
			offf_sur_count : offf_sur_count,
			jcos_auth_count : jcos_auth_count,
			jcos_posted_count : jcos_posted_count,
			jcodefi_count : jcodefi_count,
			jco_sur_count : jco_sur_count,
			or_auth_count : or_auth_count,
			or_posted_count : or_posted_count,
			ordefi_count : ordefi_count,
			or_sur_count : or_sur_count,
			auth_civ_count : auth_civ_count,
			civ_posted_count : civ_posted_count,
			civdefi_count : civdefi_count,
			civ_sur_count : civ_sur_count,
			officerauth : officerauth,
			officerauthno : officerauthno,
			jcoauth : jcoauth,
			jcoauthno : jcoauthno,
			orauth : orauth,
			orauthno : orauthno,
			civauth : civauth,
			civauthno : civauthno,
			remarks : remarks,
			distlist : distlist,
			offf_mns_auth_count : offf_mns_auth_count,
			offf_mns_posted_count : offf_mns_posted_count,
			offf_mns_defi_count : offf_mns_defi_count,
			offf_mns_sur_count : offf_mns_sur_count,
			officermnsauth : officermnsauth,
			officermnsauthno : officermnsauthno

		}, function(data) {

			if (data == null) {
				alert("Data Save/Updated Successfully");
				window.location.href = "Search_Report_3009Url";
			} else {
				alert(data)
				//$('#saveb').prop('disabled', false);
				window.location.href = "Search_Report_3009Url";

			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function updateValues(inputField) {

		debugger;

		var categoryId = inputField.parentNode.parentNode.cells[0].textContent;
		if (categoryId === "Offr's") {
			categoryId = "offf";
		} else if (categoryId === "Offr's MNS") {
			categoryId = "offf_mns";
		} else if (categoryId === "JCOs") {
			categoryId = "jco";
		} else if (categoryId === "OR") {
			categoryId = "or";
		} else if (categoryId === "Civ") {
			categoryId = "civ";
		}

		var authValue = parseFloat(inputField.value) || 0;
		var authAsPerWEPE = parseFloat(document.getElementById(categoryId
				+ "_auth_09").textContent) || 0;

		var sumValue = authValue + authAsPerWEPE;
		var postedValue = parseFloat(document.getElementById(categoryId
				+ "_posted_09").textContent) || 0;

		var subValue = postedValue - sumValue;

		if (subValue >= 0) {
			document.getElementById(categoryId + "_sur_09").textContent = subValue;
			document.getElementById(categoryId + "_defi_09").textContent = "0";

			document.getElementById(categoryId + "_sur_count").value = subValue;
			document.getElementById(categoryId + "defi_count").value = "0";

		} else {
			document.getElementById(categoryId + "_sur_09").textContent = "0";
			document.getElementById(categoryId + "_defi_09").textContent = subValue;
			document.getElementById(categoryId + "_sur_count").value = "0";
			document.getElementById(categoryId + "defi_count").value = subValue;
		}
	}
</script>