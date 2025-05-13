<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<style>
  .centered-row {
       height: 40px;
  }
</style>


<%-- <form:form name="Report_Serving" id="Report_Serving" action="Report_Serving_Action" method="post" class="form-horizontal" commandName="Report_Serving_CMD"> --%>
<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>APPROVE IAFF - 3009 MONTHLY UNIT STR REPORT</h5>
				<h6 class="enter_by"></h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> SUS No </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" style="display: none"><b>${roleSusNo}</b></label>
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
								<label class=" form-control-label"> Unit Name </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" onkeyup="return specialcharecter(this)"
									style="display: none"> <label id="unit_name_l"
									style="display: none"><b>${unit_name}</b></label>
									<input type="hidden" id="status" name="status" class="form-control">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Month </label>
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
								<label class=" form-control-label"> Year </label>
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
								<label class=" form-control-label"> Present Return No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="present_return_no"
									name="present_return_no" class="form-control autocomplete"
									autocomplete="off" maxlength="15"
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

								<input type="text" id="we_pe_no" name="we_pe_no"
									class="form-control autocomplete" readonly="readonly"
									autocomplete="off" maxlength="100"
									onkeyup="return specialcharecter(this)"
									onkeypress="return AvoidSpace(event)">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="card-header">
			<h5>SECTION I : AUTH STR</h5>

		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getauthServingTable"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>

					<tr class="centered-row">
						<th style="text-align: center; vertical-align: middle;" id="appt">Arms /Services</th>
						<th style="text-align: center; vertical-align: middle;" id="rankT">Offrs</th>
						<th style="text-align: center; vertical-align: middle;" id="name">MNS Offrs</th>
						<th style="text-align: center; vertical-align: middle;" id="personnel_no">JCOs</th>
						<th style="text-align: center; vertical-align: middle;" id="cda_acc_no">OR</th>
						<th style="text-align: center; vertical-align: middle;" id="med_cat">Remarks</th>
					</tr>


				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item1" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; ">${item1[0]}</td>
							<td style="font-size: 15px; text-align: center;">${item1[1]}</td>
							<td style="font-size: 15px; text-align: center;">${item1[2]}</td>
							<td style="font-size: 15px; text-align: center;">${item1[3]}</td>
							<td style="font-size: 15px; text-align: center;">${item1[4]}</td>
							<td style="font-size: 15px; text-align: center;">${item1[5]}</td>


						</tr>

					</c:forEach>
					 <tr>
                    <td style="font-size: 15px; text-align: left;"><strong>TOTAL</strong></td>
                      <td style="font-size: 15px; text-align: center;"><strong>${offrstotal}</strong></td>
                       <td style="font-size: 15px; text-align: center;"><strong>${mnsoffrstotal}</strong></td>
                        <td style="font-size: 15px; text-align: center;"><strong>${jcostotal}</strong></td>
                       <td style="font-size: 15px; text-align: center;"><strong>${ortotal}</strong></td>
        </tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getauthcivTable"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<tr class="centered-row">

						<th colspan="4" style="text-align: center;" id="parent_arm">
							Civilian Employee</th>
						<th rowspan="3" style="text-align: center;" id="med_cat">
							Remarks</th>
					</tr>
					<tr >
						<th colspan="2" style="text-align:  center;">Gazetted</th>
						<th colspan="2" style="text-align: center;">Non Gasetted</th>
					</tr>
					<tr>
						<th style="text-align: center;">GP 'A'</th>


						<th style="text-align: center;">GP 'B'</th>
						<th style="text-align: center;">GP 'B'</th>
						<th style="text-align: center;">GP 'C'</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list9.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item7" items="${list9}" varStatus="num">
						<tr>
							<td style="font-size: 15px;">${item7[0]}</td>
							<td style="font-size: 15px;">${item7[1]}</td>
							<td style="font-size: 15px;">${item7[2]}</td>
							<td style="font-size: 15px;">${item7[3]}</td>
							<td style="font-size: 15px;">${item7[4]}</td>

						</tr>

					</c:forEach>
				</tbody>

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
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr class="centered-row">
						<th rowspan="2" style="text-align: center; width: 300px; vertical-align: middle;" id="appt">Arms
							/Services</th>
						<th colspan="6" style="text-align: center;" id="rankT">Offrs
						</th>
						<th colspan="6" style="text-align: center;" id="name">MNS
							Offrs</th>
						<th colspan="4" style="text-align: center;" id="personnel_no">
							JCOs</th>
						<th colspan="4" style="text-align: center;" id="cda_acc_no">
							OR</th>
					</tr>
					<tr class="centered-row">
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
				<tbody>
					<c:if test="${list2.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item2" items="${list2}" varStatus="num">
						<tr>

							<td style="font-size: 15px;">${item2[0]}</td>
							<td style="font-size: 15px;">${item2[1]}</td>
							<td style="font-size: 15px;">${item2[2]}</td>
							<td style="font-size: 15px;">${item2[3]}</td>
							<td style="font-size: 15px;">${item2[4]}</td>
							<td style="font-size: 15px;">${item2[5]}</td>						
							<td style="font-size: 15px;">${item2[6]}</td>
							<td style="font-size: 15px;">${item2[7]}</td>
							<td style="font-size: 15px;">${item2[8]}</td>
							<td style="font-size: 15px;">${item2[9]}</td>
							<td style="font-size: 15px;">${item2[10]}</td>
							<td style="font-size: 15px;">${item2[11]}</td>
							<td style="font-size: 15px;">${item2[12]}</td>
							<td style="font-size: 15px;">${item2[13]}</td>
							<td style="font-size: 15px;">${item2[14]}</td>
							<td style="font-size: 15px;">${item2[15]}</td>
							<td style="font-size: 15px;">${item2[16]}</td>
							<td style="font-size: 15px;">${item2[17]}</td>
							<td style="font-size: 15px;">${item2[18]}</td>
							<td style="font-size: 15px;">${item2[19]}</td>
							<td style="font-size: 15px;">${item2[20]}</td>


						</tr>

					</c:forEach>
					<tr>
                    <td style="font-size: 15px; text-align: left;"><strong>TOTAL</strong></td>
                     <td style="font-size: 15px; text-align: left;"><strong>${brig_and_above_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${col_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${col_ts_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${lt_col_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${maj_offrs}</strong></td>						
						<td style="font-size: 15px; text-align: left;"><strong>${capt_lt_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${brig_and_above_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${col_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${lt_col_s_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${lt_col_ts_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${maj_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${capt_lt_mns_offrs}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${sub_major_jco}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${sub_jco}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${nb_sub_jco}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${warrant_officer_jco}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${hav_or}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${nk_or}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${lnk_sep_or}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${rects_or}</strong></td>
        </tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-12" id="getSearch_Letter" style="display: block;">
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span>
			<table id="getpostcivTable"
				class="table no-margin table-striped  table-hover  table-bordered ">
				<thead>
					<tr class="centered-row">

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
				<tbody>
					<c:if test="${list8.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item8" items="${list8}" varStatus="num">
						<tr>
							<td style="font-size: 15px;">${item8[0]}</td>
							<td style="font-size: 15px;">${item8[1]}</td>
							<td style="font-size: 15px;">${item8[2]}</td>
							<td style="font-size: 15px;">${item8[3]}</td>
							<td style="font-size: 15px;">${item8[4]}</td>

						</tr>

					</c:forEach>
				</tbody>

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
					<c:if test="${list7.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item7" items="${list7}" varStatus="num">
						<tr>
						<td style="font-size: 15px; text-align: center;">Offr's</td>
						<td style="font-size: 15px; text-align: center;" id="offf_auth_09">${item7[0]}</td>
						<td style="font-size: 15px; text-align: center;" id="officerauth">${item7[1]}</td>
						<td style="font-size: 15px; text-align: center;"id="offf_posted_09">${item7[2]}</td>
						<td style="font-size: 15px; text-align: center;" id="offf_defi_09">${item7[3]}</td>
						<td style="font-size: 15px; text-align: center;" id="offf_sur_09">${item7[4]}</td>
						<td style="font-size: 15px; text-align: center;" id="officerauthno">${item7[5]}</td>

					</tr>
							<tr>
						<td style="font-size: 15px; text-align: center;">Offr's MNS</td>
						<td style="font-size: 15px; text-align: center;" id="offf_mns_auth_09">${item7[6]}</td>
						<td style="font-size: 15px; text-align: center;" id="officer_mnsauth">${item7[7]}</td>
						<td style="font-size: 15px; text-align: center;"id="offf_mns_posted_09">${item7[8]}</td>
						<td style="font-size: 15px; text-align: center;" id="offf_mns_defi_09">${item7[9]}</td>
						<td style="font-size: 15px; text-align: center;" id="offf_mns_sur_09">${item7[10]}</td>
						<td style="font-size: 15px; text-align: center;" id="officer_mnsauthno">${item7[11]}</td>

					</tr>
					
					<tr>
						<td style="font-size: 15px; text-align: center;">JCOs</td>
						<td style="font-size: 15px; text-align: center;" id="jcos_auth_09">${item7[12]}</td>
						<td style="font-size: 15px; text-align: center;" id="jcoauth">${item7[13]}</td>
						<td style="font-size: 15px; text-align: center;"id="jcos_posted_09">${item7[14]}</td>
						<td style="font-size: 15px; text-align: center;" id="jco_defi_09">${item7[15]}</td>
						<td style="font-size: 15px; text-align: center;" id="jco_sur_09">${item7[16]}</td>
						<td style="font-size: 15px; text-align: center;" id="jcoauthno">${item7[17]}</td>

					</tr>
					<tr>
						<td style="font-size: 15px; text-align: center;">OR</td>
						<td style="font-size: 15px; text-align: center;" id="or_auth_09">${item7[18]}</td>
						<td style="font-size: 15px; text-align: center;" id="orauth">${item7[19]}</td>
						<td style="font-size: 15px; text-align: center;" id="or_posted_09">${item7[20]}</td>
						<td style="font-size: 15px; text-align: center;" id="or_defi_09">${item7[21]}</td>
						<td style="font-size: 15px; text-align: center;" id="or_sur_09">${item7[22]}</td>						
<td style="font-size: 15px; text-align: center;" id="orauthno">${item7[23]}</td>
						

					</tr>
					<tr>
						<td style="font-size: 15px; text-align: center;">Civ</td>
						<td style="font-size: 15px; text-align: center;" id="civ_auth_09">${item7[24]}</td>
						<td style="font-size: 15px; text-align: center;" id="civauth">${item7[25]}</td>
						<td style="font-size: 15px; text-align: center;" id="civ_posted_09">${item7[26]}</td>
						<td style="font-size: 15px; text-align: center;" id="civ_defi_09">${item7[27]}</td>
						<td style="font-size: 15px; text-align: center;" id="civ_sur_09">${item7[28]}</td>
						<td style="font-size: 15px; text-align: center;" id="civauthno">${item7[29]}</td>
						

					</tr>
					</c:forEach>
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
			<table id="getSearch_Letter "
				class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead>
					<tr class="centered-row">
					<tr>
						<th rowspan="2" style="text-align: center; vertical-align: middle; width: 500px;" id="appt">Arms/Services</th>

						<th rowspan="2" style="text-align: center; vertical-align: middle; width:400px;" id="rankT">Trade
						</th>
						<th colspan="4" style="text-align: center; vertical-align: middle;" id="name">JCOs</th>
						<th colspan="4" style="text-align: center; vertical-align: middle;" id="cda_acc_no">
							OR</th>
						<!--  <th colspan="2"  style="text-align: center;" id="parent_arm"> Civ Employed in lieu of Combatant </th> -->
						<th rowspan="2" style="text-align: center; vertical-align: middle;" id="med_cat">
							Total</th>
					</tr>
					<tr>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">Sub
							Maj</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">Sub
						</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">Nb
							Sub</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">
							WARRANT OFFICER</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">Hav
						</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">NK</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">
							LNK/Sep</th>
						<th style="text-align: center; vertical-align: middle;" id="date_of_appointment">
							RECTS</th>
						<!-- <th style="text-align: center;" id="date_of_seniority"> Gazetted </th>
			                         <th style="text-align: center;" id="date_of_appointment"> Non Gasetted </th> -->

					</tr>

					
				</thead>
				<tbody>
					<c:if test="${list4.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item4" items="${list4}" varStatus="num">
						<tr>

							<td style="font-size: 15px;">${item4[0]}</td>
							<td style="font-size: 15px;">${item4[1]}</td>
							<td style="font-size: 15px;">${item4[2]}</td>
							<td style="font-size: 15px;">${item4[3]}</td>
							<td style="font-size: 15px;">${item4[4]}</td>
							<td style="font-size: 15px;">${item4[5]}</td>
							<td style="font-size: 15px;">${item4[6]}</td>
							<td style="font-size: 15px;">${item4[7]}</td>
							<td style="font-size: 15px;">${item4[8]}</td>
							<td style="font-size: 15px;">${item4[9]}</td>
							<td style="font-size: 15px;">${item4[10]}</td>


						</tr>

					</c:forEach>
				</tbody>
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
					<tr class="centered-row">
						<th rowspan="2" style="text-align: center; vertical-align: middle;" id="rankT">
							Arms/Services</th>
						<th rowspan="2" style="text-align: center; vertical-align: middle;" id="appt">Religion</th>
						<th colspan="2" style="text-align: center; vertical-align: middle;" id="name">Posted
							Str incl ERE pers</th>
						<th colspan="2" style="text-align: center; vertical-align: middle;" id="cda_acc_no">
							Religious Teacher</th>
					</tr>
					<tr>

						<th style="text-align: center;" id="name">JCOs</th>
						<th style="text-align: center;" id="cda_acc_no">OR</th>
						<th style="text-align: center;" id="date_of_seniority">Auth</th>
						<th style="text-align: center;" id="date_of_appointment">
							Held</th>

					</tr>
				</thead>
				<tbody>
					<c:if test="${list6.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="13">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item6" items="${list6}" varStatus="num">
						<tr>

							<td style="font-size: 15px;">${item6[0]}</td>
							<td style="font-size: 15px;">${item6[1]}</td>
							<td style="font-size: 15px;">${item6[2]}</td>
							<td style="font-size: 15px;">${item6[3]}</td>
							<td style="font-size: 15px;">${item6[4]}</td>
							<td style="font-size: 15px;">${item6[5]}</td>
							
							<%-- <td style="font-size: 15px;">${item6[6]}</td> --%>


						</tr>

					</c:forEach>
					<tr>
					<td style="font-size: 15px; text-align: left;"><strong> </strong></td>
                     <td style="font-size: 15px; text-align: left;"><strong>TOTAL</strong></td>
                     <td style="font-size: 15px; text-align: left;"><strong>${jcos_posted_str_incl_ere_pers}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${or_posted_str_incl_ere_pers}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${auth_religious_teacher}</strong></td>
						<td style="font-size: 15px; text-align: left;"><strong>${held_religious_teacher}</strong></td>						
        </tr>
					
				</tbody>
			</table>
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
						class="form-control autocomplete" autocomplete="off"}></textarea>
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
	<input type="hidden" id="version" name="version" class="form-control">
	<input type="hidden" id="pagename" name="pagename" class="form-control">
	<input type="hidden" id="unit_sus_no1" name="unit_sus_no1" class="form-control">
	<input type="hidden" id="hd_bde_sus5" name="hd_bde_sus5" class="form-control">
	<input type="hidden" id="hd_div_sus5" name="hd_div_sus5" class="form-control">
	<input type="hidden" id="hd_corp_sus5" name="hd_corp_sus5" class="form-control">
	<input type="hidden" id="hd_cmd_sus5" name="hd_cmd_sus5" class="form-control">
	
	<div class="card-footer" align="center" class="form-control"
		 style="display: block;">
		<input type="button" id="clbtnid" class="btn btn-danger btn-sm" onclick="closeButtonClicked()" value="Close">
		 <c:if test="${roleType == 'APP' || roleType == 'DEO'}">		
			<input type="submit" id="Savebtn" class="btn btn-primary btn-sm" value="Approve"
				onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return Report_Approve_fn();}else{return false;}">

		</c:if>
		<c:if test="${roleType == 'ALL'}">			
			<input type="submit"  id="Savebtn" class="btn btn-primary btn-sm" value="Approve"
				onclick="if (confirm('Are You Sure You Want to Approve Officer Data?')){return Report_Approve_fn();}else{return false;}">

	</c:if>
	</div>
	
			 
			
	


	<%-- </form:form> --%>

	<c:url value="Approve_3009" var="editUrl" />
	<form:form action="${editUrl}" method="post" id="updateForm"
		name="updateForm">
		<input type="hidden" name="month1" id="month1">
		<input type="hidden" name="year1" id="year1">
		<input type="hidden" name="version1" id="version1">
		<input type="hidden" name="unit_sus_no5" id="unit_sus_no5">
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1">
		<input type="hidden" name="status1" id="status1">
	</form:form>


<c:url value="GetSearch_Report_3009" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no1">	
	 	<input type="hidden" name="month1" id="month2" value="0">
	 	<input type="hidden" name="year1" id="year2" >			
	 	<input type="hidden" name="status1" id="status2" value="0">	
	 	<input type="hidden" name="unit_sus_no1" id="unit_sus_no2" >
	 	<%--  <c:if test="${roleAccess == MISO}">
	  	 
	  	 </c:if> --%>
	</form:form> 
	
	<c:url value="GetI3009_Serving" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchFormreport"
	name="searchForm" modelAttribute="unit_sus_no2">
	<input type="hidden" name="month1" id="month3" value="0">
	<input type="hidden" name="year1" id="year3" value="0">
	<input type="hidden" name="unit_sus_no2" id="unit_sus_no3" value="0">
	
	<input type="hidden" name="hd_cmd_sus1" id="hd_cmd_sus3" value="0">
	<input type="hidden" name="hd_corp_sus1" id="hd_corp_sus3" value="0">
	<input type="hidden" name="hd_div_sus1" id="hd_div_sus3" value="0">
	<input type="hidden" name="hd_bde_sus1" id="hd_bde_sus3" value="0">
	<input type="hidden" name="line_dte1" id="line_dte3"  value="">

</form:form>

	<script>

		
		function Report_Approve_fn(){
			$("#month1").val($("#month").val()) ;	
			$("#year1").val($("#year").val()) ;	
			$("#version1").val($("#version").val()) ;	
			$("#unit_sus_no5").val($("#unit_sus_no").val());
			$("#unit_sus_no1").val($("#unit_sus_no1").val());
			$("#status1").val($("#status").val());
			document.getElementById('updateForm').submit();
		}
		
		
		function closeButtonClicked(){		
		var pagename = $("#pagename").val();
			if(pagename == "Search_Report_3009_Url"){
			$("#month2").val($("#month").val()) ;	
			$("#year2").val($("#year").val()) ;		;	
			$("#unit_sus_no2").val($("#unit_sus_no1").val());
			$("#status2").val($("#status").val());		
			document.getElementById('searchForm').submit();
			}
			else if (pagename == "IAFF_3009_Query"){
             
				$("#month3").val($("#month").val()) ;	
				$("#year3").val($("#year").val()) ;	
				$("#unit_sus_no3").val($("#unit_sus_no1").val());	
				$("#hd_cmd_sus3").val($("#hd_cmd_sus5").val());	
				$("#hd_corp_sus3").val($("#hd_corp_sus5").val());	
				$("#hd_div_sus3").val($("#hd_div_sus5").val());	
				$("#hd_bde_sus3").val($("#hd_bde_sus5").val());	
				$("#line_dte3").val($("#line_dte5").val()); 
					
				document.getElementById('searchFormreport').submit();
			}
		}
		
		
		
		$(document).ready(function() {
			
			var r =('${roleAccess}');	
			
			$("#pagename").val('${pagename}');
			$("#hd_div_sus5").val('${hd_div_sus5}');
			$("#hd_bde_sus5").val('${hd_bde_sus5}');
			$("#hd_div_sus5").val('${hd_div_sus5}');
			$("#hd_corp_sus5").val('${hd_corp_sus5}');
			$("#hd_cmd_sus5").val('${hd_cmd_sus5}');
			$("#status").val('${status}');
			$("#unit_sus_no1").val('${unit_sus_no1}');
		 	$("#line_dte5").val('${line_dte5}');
			
			var test = $("#unit_sus_no1").val();
			
			
			
			
			
			
			if( r == "Unit"){
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
			}
			else  if(r == "MISO" || r == "Formation" || r == "Line_dte"){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_name").show();
			}
			
			$("#printbtn").hide();
			
			if('${roleSusNo}' != ""){
				$('#roleSusNo').val('${roleSusNo}');
			}
			
			
			if('${status}' == '1'){	
				$("#Savebtn").hide();
//	 			//	document.getElementById("Savebtn").style.display = "none";
			}
			
			
		//if('${roleType}' == "APP" || '${roleType}' == "ALL" || '${roleType}' == "DEO"){	
			$('#year').val('${year5}');
			$('#month').val('${month5}');
			$('#unit_sus_no').val('${unit_sus_no5}');
			$("#present_return_no").val('${present_return_no5}');
			$("#present_return_dt").val(ParseDateColumncommission('${present_return_dt5}'));
			$("#remarks").val('${remarks5}');
			$("#distlist").val('${distlist5}');
				
			 	$("#month").attr("disabled", true);
				$("#year").attr("disabled", true);
				$("#present_return_no").attr("disabled", true);
				$("#present_return_dt").attr("disabled", true);
				$("#last_return_no").attr("disabled", true);
				$("#last_return_dt").attr("disabled", true); 
				$("#remarks").attr("disabled", true);
				$("#distlist").attr("disabled", true);
				
				   
			     if('${last_return_no1}' != ""){
					 $("#last_return_no").val('${last_return_no1}');	
					 $("#last_return_no").attr("disabled", true);
				 }
			     if('${last_return_dt1}' != "" || '${last_return_dt1}' != "DD/MM/YYYY"){
					 $("#last_return_dt").val(ParseDateColumncommission('${last_return_dt1}'));		
					 $("#last_return_dt").attr("disabled", true);
				 }
			     if('${we_pe_no1}' != "" ){
					 $("#we_pe_no").val('${we_pe_no1}');		
					 $("#we_pe_no").attr("disabled", true);
				 }
			     
			//}
		 	
			 if('${version5}' != "" || '${version5}' != "0" || '${version5}' != 0){
					$('#version').val('${version5}');
			 }
		
			
			 if('${unit_sus_no5}' != ""){
				 $("#unit_sus_no").val('${unit_sus_no5}');		
				 
				 var sus_no = '${unit_sus_no5}';			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				 
				 $("#unit_sus_no").attr("disabled", true);
				 $("#unit_name").attr("disabled", true);
			 }	
			 
			 
			 
			 if('${month5}' != "" || '${month5}' != 0){
					$('#month').val('${month5}');
			 }else{
				 var d = new Date();
				    var month = d.getMonth() + 1;
				    $("#month").val(month); 		   
			 } 
			 
			    if('${year5}' != "" || '${year5}' != 0){
					$('#year').val('${year5}');
				}
			    else{
					 var d = new Date();		  
					 var year = d.getFullYear();
					 $("#year").val(year);   
			     }  
			
			 if('${size}'!= "" || '${size}'!= 0 ){
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
			}
			 else{		 
				  $("div#getSearch_Letter").hide();	
				  $("div#Savebtn").hide();	
			 }
			
				$.post('getEstablishment_No?' + key + "=" + value, {}, function(data){
					if(data.length > 0){
						var we_pe = data[0][0];
						$("#we_pe_no").val(we_pe);
					}
				});

			 	 		
		});
		
		
		</script>
	<script>
		function validateNumber(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
				return false;
			} else {
				if (charCode == 46) {
					return false;
				}
			}
			return true;
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
					 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
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
		
		
		 jQuery(function($){ //on document.ready  
			 datepicketDate('present_return_dt');
			 datepicketDate('last_return_dt');
			});
		</script>