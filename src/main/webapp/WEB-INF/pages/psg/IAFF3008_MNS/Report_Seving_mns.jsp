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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!--  <script src="js/common/commonmethod.js" type="text/javascript"></script>  -->

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


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
<style>
.instruction {
	color: red;
}
</style>

<form:form name="Report_Serving" id="Report_Serving"
	action="Report_Serving_Action" method="post" class="form-horizontal"
	commandName="Report_Serving_CMD">
	<div class="animated fadeIn">
		<div class="container-xl">
			<div class="col-lg-2 col-md-12 col-sm-12 col-12 instruction">
			
			<input type="hidden" id="monthhidden" name="monthhidden"  > 
			<input type="hidden" id="yearhidden" name="yearhidden"  > 
			
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-12">
				<div class="card">
					<div class="card-header" align="center">
						<h5>Offr's Str Return MNS</h5>
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
										<label class=" form-control-label"> Unit Name </label>
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
										<label class=" form-control-label"> Present Return No
										</label>
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
											style="color: red;">* </strong> Date of Last Return </label>
									</div>
									<div class="col-md-8">
										<input type="text" name="last_return_dt" id="last_return_dt"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
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
											autocomplete="off" maxlength="15"
											onkeyup="return specialcharecter(this)"
											onkeypress="return AvoidSpace(event)">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 card-footer" align="center"
						class="form-control">
						<a href="Report_SevingUrl_mns" class="btn btn-success btn-sm">Clear</a>
						<input type="button" class="btn btn-info btn-sm"
							onclick="Search();" value="Search">

					</div>
				</div>
			</div>
		</div>
		<div class="datatablediv">
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SERVING</h5>
					<h6>List of Appts is being maint by MISO/CUE&Orbat.
						Clarifications if any, may be sought directly from MISO/CUE &
						Orbat</h6>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getServingTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th style="text-align: center;" id="appt">Ser No</th>
								<th style="text-align: center;" id="appt">Update</th>
								<th style="text-align: center;" id="appt"><strong
									style="color: red;">* </strong>Appt</th>
								<th style="text-align: center;" id="rankT">Rank</th>
								<th style="text-align: center;" id="name">Name</th>
								<th style="text-align: center;" id="personnel_no">Personal
									No</th>
								<th style="text-align: center;" id="cda_acc_no">CDA A/C No
								</th>
								<th style="text-align: center;" id="med_cat">Medical Cat</th>
								<th style="text-align: center;" id="dt_of_tos">Date of TOS
								</th>
								<th style="text-align: center;" id="date_of_birth">Date of
									Birth</th>
								<th style="text-align: center;" id="date_of_commission">
									Date of Comm</th>
								<th style="text-align: center;" id="date_of_seniority">
									Date of SEN</th>
								<th style="text-align: center;" id="date_of_appointment">DATE
									OF PRESENT RANK</th>
								<th style="text-align: center;" id="date_of_appointment">TNAI
									NO</th>
								<th style="text-align: center;" id="date_of_appointment">MARRIED/SINGLE/SEPARATED
								</th>
								<th style="text-align: center;" id="date_of_appointment">DOM
								</th>
							
							</tr>
						</thead>

					</table>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>SUPERNUMERARY STR</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getSupernumTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th style="text-align: center;" id="appt">Ser No</th>
								<th style="text-align: center;" id="appt"><strong
									style="color: red;">* </strong> Appt</th>
								<th style="text-align: center;" id="rankT">Rank</th>
								<th style="text-align: center;" id="name">Name</th>
								<th style="text-align: center;" id="personnel_no">Personal
									No</th>
								<th style="text-align: center;" id="cda_acc_no">CDA A/C No
								</th>
								<th style="text-align: center;" id="med_cat">Medical Cat</th>
								<th style="text-align: center;" id="dt_of_tos">Date of TOS
								</th>
								<th style="text-align: center;" id="date_of_birth">Date of
									Birth</th>
								<th style="text-align: center;" id="date_of_commission">
									Date of Comm</th>
								<th style="text-align: center;" id="date_of_seniority">
									Date of SEN</th>
								<th style="text-align: center;" id="date_of_appointment">DATE
									OF PRESENT RANK</th>
								<th style="text-align: center;" id="date_of_appointment">TNAI
									NO</th>
								<th style="text-align: center;" id="date_of_appointment">MARRIED/SINGLE/SEPARATED
								</th>
								<th style="text-align: center;" id="date_of_appointment">DOM
								</th>
							


							</tr>
						</thead>

					</table>
				</div>
			</div>


			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>DESERTER</h5>
				</div>
			</div>

			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getDeserterTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th style="text-align: center;" id="appt">Ser No</th>
								<th style="text-align: center;" id="appt"><strong
									style="color: red;">* </strong> Appt</th>
								<th style="text-align: center;" id="rankT">Rank</th>
								<th style="text-align: center;" id="name">Name</th>
								<th style="text-align: center;" id="personnel_no">Personal
									No</th>
								<th style="text-align: center;" id="cda_acc_no">CDA A/C No
								</th>
								<th style="text-align: center;" id="med_cat">Medical Cat</th>
								<th style="text-align: center;" id="dt_of_tos">Date of TOS
								</th>
								<th style="text-align: center;" id="date_of_birth">Date of
									Birth</th>
								<th style="text-align: center;" id="date_of_commission">
									Date of Comm</th>
								<th style="text-align: center;" id="date_of_seniority">
									Date of SEN</th>
								<th style="text-align: center;" id="dt_desertion">Date of
									Desertion</th>
								<th style="text-align: center;" id="date_of_appointment">DATE
									OF PRESENT RANK</th>
								<th style="text-align: center;" id="date_of_appointment">TNAI
									NO</th>
								<th style="text-align: center;" id="date_of_appointment">MARRIED/SINGLE/SEPARATED
								</th>
								<th style="text-align: center;" id="date_of_appointment">DOM
								</th>
							


							</tr>
						</thead>

					</table>
				</div>
			</div>



			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="card-header">
					<h5>HELD STR AS PER IAFF-3008</h5>
				</div>
			</div>
			<div class="col-md-12" id="getSearch_Letter" style="display: block;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<table id="getHeldTable"
						class="table no-margin table-striped  table-hover  table-bordered ">
						<thead>
							<tr>
								<th style="text-align: center;" id="rankT">Ser No</th>
								<th style="text-align: center;" id="rankT">Rank</th>
								<th style="text-align: center;" id="total_held">Total Held
								</th>
							</tr>
						</thead>

					</table>
				</div>
			</div>




			
				<div class="col-md-12">
					<div class="row form-group">
						<div class="col-md-4">
							<label class="form-control-label"> Nursing Offr TOS since
								submission of last report :</label>
						</div>
						<div class="col-md-8">
							<textarea id="remarksfornursingoffrstos"
								name="remarksfornursingoffrstos"
								class="form-control autocomplete" maxlength="250"
								autocomplete="off"></textarea>
						</div>
					</div>
				</div>

			
			<div class="col-md-12">
				<div class="row form-group">
					<div class="col-md-4">
						<label class="form-control-label"> Nursing Offr SOS since
							submission of last report : </label>
					</div>
					<div class="col-md-8">
						<textarea id="remarksfornursingoffrssos"
							name="remarksfornursingoffrssos"
							class="form-control autocomplete" maxlength="250"
							autocomplete="off"></textarea>
					</div>
				</div>
			</div>

		</div>
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-4">
					<label class="form-control-label"> Nursing Offrs under
						order of posting out : </label>
				</div>
				<div class="col-md-8">
					<textarea id="remarksfornursingoffrspostout"
						name="remarksfornursingoffrspostout"
						class="form-control autocomplete" maxlength="250"
						autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-4">
					<label class="form-control-label"> Nursing Offrs under
						order of posting in : </label>
				</div>
				<div class="col-md-8">
					<textarea id="remarksfornursingoffrspostin"
						name="remarksfornursingoffrspostin"
						class="form-control autocomplete" maxlength="250"
						autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-4">
					<label class="form-control-label"> Nursing Offrs under
						order of Retirement / Release/SSC Release:</label>
				</div>
				<div class="col-md-8">
					<textarea id="remarksfornursingoffrsunderretire"
						name="remarksfornursingoffrsunderretire"
						class="form-control autocomplete" maxlength="250"
						autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-4">
					<label class="form-control-label">Remarks: </label>
				</div>
				<div class="col-md-8">
					<textarea id="remarks" name="remarks"
						class="form-control autocomplete" maxlength="250"
						autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		<div class="col-md-12">
			<div class="row form-group">
				<div class="col-md-4">
					<label class="form-control-label">Distr: </label>
				</div>
				<div class="col-md-8">
					<textarea id="distr" name="distr" class="form-control autocomplete"
						maxlength="250" autocomplete="off"></textarea>
				</div>
			</div>
		</div>
	</div>


	</div>
	<div class="col-md-12 card-footer" align="center" class="form-control"
		id="Savebtn">
		<c:if test="${roleType == 'ALL'}">
			<input type="button" class="btn btn-primary btn-sm"
				value="Submit For Approval" onclick="Report_Save_fn();">
		</c:if>
		<c:if test="${roleType == 'DEO'}">
			<input type="button" class="btn btn-primary btn-sm"
				value="Submit For Approval" onclick="Report_Save_fn();">
		</c:if>
	</div>
	</div>
</form:form>

<c:url value="GetSearch_Report_Serving" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="unit_sus_no2">
	<input type="hidden" name="month1" id="month1" value="0">
	<input type="hidden" name="year1" id="year1" value="0">
	<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0">
	<input type="hidden" name="present_return_no1" id="present_return_no1">
	<input type="hidden" name="present_return_dt1" id="present_return_dt1">
</form:form>


<c:url value="change_in_rank_3008" var="changeinrankUrl" />
<form:form action="${changeinrankUrl}" method="post"
	id="changeinrankForm" name="changeinrankForm" modelAttribute="id"
	target="result">
	<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
</form:form>

<c:url value="change_in_perosonnel_no_3008"
	var="changeinpersonnelnnoUrl" />
<form:form action="${changeinpersonnelnnoUrl}" method="post"
	id="changeinpersonnelnnoForm" name="changeinpersonnelnnoForm"
	modelAttribute="id" target="result">
	<input type="hidden" name="comm_id2" id="comm_id2" value="0" />
</form:form>

<c:url value="Update_officier_3008_mns" var="update_officier_3008_Url" />
<form:form action="${update_officier_3008_Url}" method="post"
	id="update_officier_3008_Form" name="update_officier_3008_Form"
	modelAttribute="id" target="result">
	<input type="hidden" name="comm_id3" id="comm_id3" value="0" />
</form:form>

<c:url value="form_census_3008" var="form_census_3008_Url" />
<form:form action="${form_census_3008_Url}" method="post"
	id="form_census_3008_Form" name="form_census_3008_Form"
	modelAttribute="id" target="result">
	<input type="hidden" name="comm_id4" id="comm_id4" value="0" />
</form:form>



<c:url value="add_offrs_3008" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="get"
	id="form_add_officer3008_Form" name="form_add_officer3008_Form"
	modelAttribute="cont_comd4" target="result">
</form:form>
<c:url value="Posting_OutUrl_3008" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="get"
	id="Posting_OutUrl_3008_Form" name="Posting_OutUrl_3008_Form"
	modelAttribute="cont_comd5" target="result">
</form:form>

<script>

		function Search(){	
	
			$("#WaitLoader").show();
			
			var currentDate = new Date();
			 var Newmonth, Newyear;			
			 if (currentDate.getMonth() == 0) {  
				 Newmonth = 12;  
				 Newyear = currentDate.getFullYear() - 1; 
			 } else {
				 Newmonth = currentDate.getMonth(); 
				 Newyear = currentDate.getFullYear();
			 }
			 var mon = $("#month").val();
			 var yr = $("#year").val();
			 
			if(mon == Newmonth){
				 $("#month").val(Newmonth); 	
				 $("div#Savebtn").show();	
					$("div#obr").show();
			}else{
				$("div#Savebtn").hide();
				$("div#obr").hide();
			}		
			if(yr == Newyear){
				 $("#year").val(Newyear); 	
				 $("div#Savebtn").show();
					$("div#obr").show();
			}else{
				$("div#Savebtn").hide();	
				$("div#obr").hide();
			}  
			var r =('${roleAccess}');	
			if(r == "MISO" || r == "DGMS"){
				if($('#unit_sus_no').val().trim() == "") {
					alert("Please Enter Unit Sus No");
					$('#unit_sus_no').focus();
					$("#WaitLoader").hide();
					return false;
				}
			}
		$("#month1").val($("#month").val()) ;	
		$("#year1").val($("#year").val()) ;	
		$("#unit_sus_no2").val($("#unit_sus_no").val()) ;
		
		$("#present_return_no1").val($("#present_return_no").val()) ;	
		$("#present_return_dt1").val($("#present_return_dt").val()) ;	
// 		document.getElementById('searchForm').submit();
		table.ajax.reload();
    	table2.ajax.reload();
    	table3.ajax.reload();
    	table4.ajax.reload();
    	    	
    	$("#WaitLoader").hide();
	}
		
		
		$(document).ready(function() {
			
			$("#WaitLoader").show();
// 			colAdj("getSearch_Letter");
			
			var r =('${roleAccess}');
			
			if( r == "Unit"){
				 $("#unit_sus_no_hid").show();
				 $("#unit_name_l").show();
				 $("#month").attr("readonly", "readonly");
					$("#year").prop("readonly", true);
			}
			else  if(r == "MISO" || r == 'DGMS'){
				
				 $("input#unit_sus_no").show();		 
				 $("input#unit_name").show();
			}
			
			$("#printbtn").hide();
			
			if('${roleSusNo}' != ""){
				$('#roleSusNo').val('${roleSusNo}');
			}	
			if('${roleType}' == "DEO"){	
				$("#month").attr("disabled", false);
				$("#year").attr("disabled", false);
				$("#present_return_no").attr("disabled", false);
				$("#present_return_dt").attr("disabled", false);
				$("#last_return_no").attr("disabled", false);
				$("#last_return_dt").attr("disabled", false);
			} 
			
			   //-----Chandani
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
			 
			 if('${unit_sus_no2}' != ""){
				 $("#unit_sus_no").val('${unit_sus_no2}');		
				 
				 var sus_no = '${unit_sus_no2}';			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no }, function(j) {}).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				 
				 $("#unit_sus_no").attr("disabled", true);
				 $("#unit_name").attr("disabled", true);
			 }	
			 
			 if('${month1}' != 0){
				 $("#month").val('${month1}');
				 $("#monthhidden").val('${month1}');
			 }
			 else{
				 var currentDate = new Date();		
					var lastDayOfMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();	
					var currentDay = currentDate.getDate();		
					if (currentDay == lastDayOfMonth) {	 
						var month = currentDate.getMonth() + 1;
						$("#month").val(month);
					} else {
						var month = currentDate.getMonth();
						$("#month").val(month);
					}		   
			 } 
			 
			   if('${year1}' != ''){		
				    $("#year").val('${year1}');
				    $("#yearhidden").val('${year1}');
			   }
			    else{
					 var d = new Date();		  
					 var year = d.getFullYear();
					 $("#year").val(year);   
			     }  
			     if('${present_return_no1}' != ""){
					 $("#present_return_no").val('${present_return_no1}');		
				 }
			     if('${present_return_dt1}' != "" || '${present_return_dt1}' != "DD/MM/YYYY"){
					 $("#present_return_dt").val('${present_return_dt1}');		
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
			 else if('${roleType}' == "DEO" || '${roleType}' == "ALL" ){	
				 $("div#getSearch_Letter").show();	
			 }
			 else{		 
				  $("div#getSearch_Letter").hide();	
				  $("div#Savebtn").hide();	
				  $("div#obr").hide();
			 }
			
				$.post('getEstablishment_No?' + key + "=" + value, {}, function(data){
					if(data.length > 0){
						var we_pe = data[0][0];
						$("#we_pe_no").val(we_pe);
					}
				});
				
				
				mockjax1('getServingTable');
				table = dataTable11('getServingTable');
				mockjax1('getSupernumTable');
				table2 = dataTable11('getSupernumTable');				
				mockjax1('getDeserterTable');
				table3 = dataTable11('getDeserterTable');				
				mockjax1('getHeldTable');
				table4 = dataTable11('getHeldTable');
				
				$('#btn-reload').on('click', function(){
					
			    	table.ajax.reload();
			    	table2.ajax.reload();
			    	table3.ajax.reload();
			    	table4.ajax.reload();
			    	
			    	
			    });

			 	 		
		});
		
		function Report_Save_fn(){
			
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
			 if ($("input#present_return_dt").val() == "" || $("input#present_return_dt").val() == "DD/MM/YYYY") {
					alert("Please Enter present return dt ");
					$("input#gmpresent_return_dtail").focus();
					return false;
				} 
			/* 	if ($("textarea#observation").val() == "") {
					alert("Please Enter Observation");
					$("textarea#observation").focus();
					return false;
				} */
				
				var selectedMonth=$('#month').val();
				 var selectedYear=$('#year').val();	
				 var currentMonth=$('#monthhidden').val();		
				 var currentYear=$('#yearhidden').val();

				 if ( selectedMonth != currentMonth  ) {
				 alert(" 1. Data cannot be approved. \n 2. Unit can only approve IAFF 3008 for previous month.");
				    $("select#month").focus();
				  return false;
				 }
				 if (selectedYear != currentYear) {
					  alert("Only the current year is allowed.");
					   $("input#year").focus();
					  return false;
				 }
			
				
				var nullFieldsList = [];
				$('#getServingTable tbody tr').each(function() {
				    var $row = $(this);
				    var personnel_no = $row.find('td:eq(5)').text(); 
				    var nullFields = [];
				    
				    $row.find('td').each(function(index) {
				        // Skip columns 1 (Update), date of DOM, and MARRIED/SINGLE/SEPARATED
				        // These are at indices 15 and 14 respectively in your table
				        if (index > 1 && index !== 15 && $(this).text().trim() === '') { 
				            nullFields.push($('#getServingTable thead th').eq(index).text().trim());
				        }
				    });
				    
				    if (nullFields.length > 0) {
				        nullFieldsList.push({
				            personnel_no: personnel_no,
				            null_fields: nullFields
				        });
				    }
				});

				if (nullFieldsList.length > 0) {
				    alert("ERROR : \n 1. Fields such as Appts, CDA Ac No, Med Cat & Marital status are yet to be filled for Offrs. \n 2. Pl fill in all detls before approving / submiting. \n 3. IAFF 3008 MNS cannot have blank data.");
				    return false; 
				} else {
			var month = $('#month').val();
			var year = $('#year').val();	
			var present_return_no=$("#present_return_no").val();
			var present_return_dt=$("#present_return_dt").val();
			var last_return_no=$("#last_return_no").val();
			var last_return_dt=$("#last_return_dt").val();
			//var observation=$("#observation").val();
			var remarksfornursingoffrstos=$("#remarksfornursingoffrstos").val();
			var remarksfornursingoffrssos=$("#remarksfornursingoffrssos").val();
			var remarksfornursingoffrspostout=$("#remarksfornursingoffrspostout").val();
			var remarksfornursingoffrspostin=$("#remarksfornursingoffrspostin").val();
			var remarksfornursingoffrsunderretire=$("#remarksfornursingoffrsunderretire").val();
			var remarks=$("#remarks").val();
			var remarksdistr=$("#distr").val();
			var unit_sus_no=$("#unit_sus_no").val();
		
		 	
			$.post('Insert_3008_mns?' + key + "=" + value, {month:month,year:year,
																present_return_no:present_return_no,
																present_return_dt:present_return_dt,
																last_return_no:last_return_no,
																last_return_dt:last_return_dt,
																//observation:observation,
																remarksfornursingoffrstos:remarksfornursingoffrstos,
																remarksfornursingoffrssos:remarksfornursingoffrssos,
																remarksfornursingoffrspostout:remarksfornursingoffrspostout,
																remarksfornursingoffrspostin:remarksfornursingoffrspostin,
																remarksfornursingoffrsunderretire:remarksfornursingoffrsunderretire,
																remarksdistr:remarksdistr,
																remarks:remarks,
																unit_sus_no:unit_sus_no}, function(data){
																	
					 if(data ==null){
		     	         alert("Data Save/Updated Successfully");	
			          }
			          else{
			        	  alert(data)
			        	  }       	
			 	  }).fail(function(xhr, textStatus, errorThrown) 
			 	  	{		 	 		
			  	});
				    }
			
			} 
		
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





<script type="text/javascript">
////////////////////data table////////////

function data(tableName){
	jsondata = [];

	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
	
	var month = $('#month').val();
	var year = $('#year').val();	
	var present_return_no=$("#present_return_no").val();
	var present_return_dt=$("#present_return_dt").val();
	var last_return_no=$("#last_return_no").val();
	var last_return_dt=$("#last_return_dt").val();
	var observation=$("#observation").val();
	var unit_sus_no=$("#unit_sus_no").val();
	
	
	var totalAuth = 0;
	var totalHeld = 0;
	var sum_held=0;
	var sum_auth=0;

	///////////serving/////
	if (tableName=="getServingTable") {
	
		
		$.post("getServing3008Count_mns?"+key+"="+value,{Search:Search,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
			FilteredRecords = j;
		});
		$.post("getServing3008_mns?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				
				jsondata.push([sr,j[i].action,j[i].appt,j[i].rank,j[i].name,j[i].personnel_no,j[i].cda_acc_no,
				j[i].med_cat,j[i].dt_of_tos,j[i].date_of_birth,j[i].date_of_commission,
					j[i].date_of_seniority,j[i].dt_of_rk,j[i].tnai_no,j[i].marital_status_description,j[i].dom
				]);
				
			}
		});
	}
	
		///////////serving/////
	else if (tableName=="getSupernumTable") {
	
		$.post("getSupernum3008Count_mns?"+key+"="+value,{Search:Search,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
			FilteredRecords = j;
		});
		
		$.post("getSupernum3008_mns?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
				
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				jsondata.push([sr,j[i].appt,j[i].rank,j[i].name,j[i].personnel_no,j[i].cda_acc_no,
					j[i].med_cat,j[i].dt_of_tos,j[i].date_of_birth,j[i].date_of_commission,
						j[i].date_of_seniority,j[i].dt_of_rk,j[i].tnai_no,j[i].marital_status_description,j[i].dom
					]);
				
			}
		});
	}
	

	
	/////////// Deserter /////
	else if (tableName=="getDeserterTable") {
		
		$.post("getDeserter3008Count_mns?"+key+"="+value,{Search:Search,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
			FilteredRecords = j;
		});
		
		$.post("getDeserter3008_mns?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
				
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				jsondata.push([sr,j[i].appt,j[i].rank,j[i].name,j[i].personnel_no,j[i].cda_acc_no,
					j[i].med_cat,j[i].dt_of_tos,j[i].date_of_birth,j[i].date_of_commission,
					j[i].date_of_seniority,j[i].dt_desertion,j[i].dt_of_rk,j[i].tnai_no,j[i].marital_status_description,j[i].dom
				]);
				
			}
		});
	}	


	
/////////// Held /////
	else if (tableName=="getHeldTable") {
		$("#WaitLoader").show();

			
		$.post("getHeld3008Count_mns?"+key+"="+value,{Search:Search,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
			FilteredRecords = j;
		});
		
		$.post("getHeld3008_mns?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,month:month,year:year,unit_sus_no:unit_sus_no},function(j) {
				
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				jsondata.push([sr,j[i].rank,j[i].total_held
				]);

				var totalHeld1= j[i].total_held;
				
				if(totalHeld1 == null ) {
					totalHeld =0;
				}
				else
				{
					totalHeld = parseInt(totalHeld1);
				}						

				sum_held += parseInt(totalHeld);		
				}	
			var sum_autht = $("#totalAuth").text();
			var sur = parseInt(sum_held) - parseInt(sum_autht);
			var defi=0;
			if(sur >= 0){
				defi = sur;
				sur = 0;
			}else{
				sur = sur;
				defi = 0;			
			}
			$('#totalHeld').text(sum_held);
			$('#defi').text(Math.abs(sur));
			$('#sur').text(defi);
			$("#WaitLoader").hide();
				
		});
	}
	
}

var popupWindow = null
function openadd_offrs_3008(url) {
	
	popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");		
	document.getElementById('form_add_officer3008_Form').submit();


}

function openpostinout(url) {
	popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
	document.getElementById('Posting_OutUrl_3008_Form').submit();
}
/* function openPopup(url) {
    window.open(url, "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
} */
var count =0;
var formSubmitted = false;
var formSubmitted = false;
function openPopup(comm_id, type) {
    $('#comm_id3').val(comm_id);  
    if (type == "Update_officier_3008_mns") {

    	if(popupWindow != null && !popupWindow.closed){			
			popupWindow.close();
    	
        popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

  document.getElementById('update_officier_3008_Form').submit();   

    	}
    	
    	else{
    		
    		 popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

    		  document.getElementById('update_officier_3008_Form').submit();   
    		  
    		  
    		
    	}
    	
    	    	
    	
        var checkPopupClosed = setInterval(function() {   
            if (popupWindow.closed) { 
                clearInterval(checkPopupClosed); 
                if (!formSubmitted) {
                	table.ajax.reload();
                   
                }
            }
        }, 500);
        
        
        
    }
}

window.onbeforeunload = function () {
	  
    if (popupWindow && !popupWindow.closed) {
        formSubmitted = true;
    }
};


</script>

