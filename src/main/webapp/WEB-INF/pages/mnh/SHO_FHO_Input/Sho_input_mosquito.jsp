<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<style>
.cue_text input, 
textarea {

text-transform: initial;
}
</style>


<form:form name="mnh_inputs_mosquito" id="mnh_inputs_mosquito"
	action="mnh_inputs_mosquitoAction" method='POST'
	commandName="mnh_inputs_mosquitoCMD">

	<div class="card">
		<div class="card-header">
			<h5 align="center">EPIDEMIOLOGICAL INVESTIGATION REPORT(MOSQUITO/VECTOR BORNE)</h5>
			<h6>
				<span style="font-size: 12px; margin-left: 700px; color: red">(To be entered by SHO/FHO)</span>
			</h6>
		</div>

		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong>SHO/FHO Name</label>
							</div>
							<div class="col-md-9">
								<input type="hidden" id="emp_id" name="emp_id"	class="form-control-sm form-control" placeholder="Search...">
								<input type="text" id="unit_name1" name="unit_name1" class="form-control-sm form-control" placeholder="Search..."
									maxlength="100" autocomplete="off"	title="Type Unit Name or Part of Unit Name to Search">
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong style="color: red;">*</strong>SUS No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="sus_no1" name="sus_no" class="form-control-sm form-control" maxlength="8"
									placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Disease</label>
							</div>
							<div class="col-md-9">
								<select name="disease" id="disease" class="form-control-sm form-control" onchange="Clearvalue()">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_5}" varStatus="num">
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
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Case No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="case_no" name="case_no" class="form-control-sm form-control" placeholder="Enter..." autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="datee" name="datee"
									class="form-control-sm form-control" placeholder="Search..." autocomplete="off" min="1899-01-01" max="${date}">
							</div>
						</div>
					</div>
				</div>
				<div class="card-header-inner" id="aa">
					<strong>Personnel Particulars of Individual</strong>
				</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong> Personnel No</label>
							</div>
							<div class="col-md-8">
							<div class="col-md-3">
								<select id="persnl_no1" name="persnl_no1"
									class="form-control-sm form-control"  onchange="personal_number()">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_1}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6">
								<input type="text" id="persnl_no2" name="persnl_no2" onkeypress="return isNumber0_9Only(event)" class="form-control-sm form-control" autocomplete="off"
									placeholder="Enter No..." maxlength="10" onchange="personal_number()">
							</div>
							<div class="col-md-3">
							<select id="persnl_no3" name="persnl_no3" class="form-control-sm form-control" onchange="personal_number()">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_2}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong> Personnel Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="personal_name" name="personal_name" class="form-control-sm form-control" autocomplete="off"
									oninput="this.value = this.value.toUpperCase()" placeholder="Enter Personnel Name..." maxlength="100">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Service</label>
							</div>
							<div class="col-md-8">
								<select id="service" name="service"
									class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_3}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Relation</label>
							</div>
							<div class="col-md-8">
								<select id="relationship" name="relationship"
									class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_7}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
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
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Category</label>
							</div>
							<div class="col-md-8">
								<select name="category" id="category"
									class="form-control-sm form-control" readonly="true">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_4}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Rank</label>
							</div>
							<div class="col-md-8">
								<select name="eir_rank.id" id="rank" data-placeholder="Select the Rank..." class="form-control-sm form-control">
									<option value="-1">--Select--</option>
								</select>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Date of Birth</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="date_of_birth1" name="date_of_birth1" autocomplete="off" class="form-control-sm form-control"
									onchange="calculate_age(this);" maxlength="3" min="1899-01-01" max="${date}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Aadhar Card No.</label>
							</div>

							<div class="col-md-8">
								<input type="text" id="adhar_card_no" name="adhar_card_no" class="form-control-sm form-control" maxlength="12"
									onkeypress="return isNumber0_9Only(event)" placeholder=" Please Enter 12 Digit Aadhar card No..."
									autocomplete="off">

							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Age (Years)</label>
							</div>

							<div class="col-md-4">
								<input id="age_year" name="age_year" readonly class="form-control-sm form-control" maxlength="3"
									onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Year...">
							</div>
							<div class="col-md-4">
								<input id="age_month" name="age_month" readonly class="form-control-sm form-control" maxlength="2"
									onkeypress="return isNumberPointKey(event);" autocomplete="off" placeholder="Enter Month...">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Unit</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="personnel_unit" name="personnel_unit" autocomplete="off" class="form-control-sm form-control"
									oninput="this.value = this.value.toUpperCase()" placeholder="Enter Unit...">
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Present Address(Residential)</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="present_address" name="present_address"
									autocomplete="off" class="form-control-sm form-control" placeholder="Enter Present Address(Residential)...">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Contact Number(Mobile)</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="contact_no" name="contact_no" autocomplete="off" class="form-control-sm form-control"
									onkeypress="return isNumber0_9Only(event)" maxlength="10" placeholder="Enter Contact Number...">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Onset of Symptoms</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="date_onset_symp" name="date_onset_symp"
									autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Symptoms</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="type_of_symptom" name="type_of_symptom"
									autocomplete="off" class="form-control-sm form-control" placeholder="Enter Type of First Symptom...">
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Date of Reporting</label>
							</div>
							<div class="col-md-8">
								<input type="date" id="date_repo_med_centre" name="date_repo_med_centre" autocomplete="off"
									class="form-control-sm form-control" min="1899-01-01" max="${date}">
							</div>
						</div>
					</div>
					  

						<div class="col-md-6" style="display:none" id="div_past">
						<div class="row form-group">
						<div class="col-md-4">
						 	 <label for="text-input" class=" form-control-label">Past History Of MALARIA/DENGUE</label>
							 </div>
						<div class="col-md-8">
							<select name="history_Of_past" id="history_Of_past"
									class="form-control-sm form-control" >
									<option value="-1">--Select--</option>
									<option value="0">Yes</option>
									<option value="1">No</option>
								</select>
						</div>
						</div>
						</div>
						
						
						

				</div>

				<div class="col-md-12 form-group">
					<div class="col-md-2">
						<label for="text-input" class=" form-control-label">Brief History</label>
					</div>
					<div class="col-md-10">
						<textarea rows="2" cols="250" id="history" name="history"
							autocomplete="off" class="form-control char-counter1" placeholder="Enter Brief History..."></textarea>
					</div>
				</div>
				<div class="col-md-12">
					<table id="h_travel_table" class="table-bordered "
						style="margin-top: 10px; width: 100%;">
						<thead style="width: 100%;">
							<tr>
								<th colspan="11"
									style="font-size: 14px; margin-top: 5px; text-align: center;">HISTORY
									OF RECENT TRAVEL</th>
							</tr>


						</thead>
						<thead style="color: white; text-align: center;">
							<tr>
								<th style="width: 2%;">Sr No</th>
								<th>Date</th>
								<th>Time</th>
								<th>Loc</th>
								<th style="width: 10%;">Action</th>
							</tr>
						</thead>
						<tbody data-spy="scroll" id="h_travel_tbody"
							style="min-height: 46px; max-height: 650px; text-align: center;">
							<tr id="tr_h_travel1">
								<td class="h_travel__srno" style="width: 2%;">1</td>
								<td style="width: 20%;"><input type="date" id="h_datee1"
									name="h_datee1" class="form-control-sm form-control"
									autocomplete="off" min="1899-01-01" max="${date}"></td>
								<td style="width: 20%;"><input type="time" id="h_time1"
									name="h_time1" class="form-control-sm form-control"
									autocomplete="off"></td>


								<td style="width: 20%;"><input type="text" id="h_loc1"
									name="h_loc1" class="form-control-sm form-control"
									autocomplete="off"></td>


                        <td style="display: none;">
	                     <input type="text" id="travel_id1" name="travel_id1" value="0" class="form-control autocomplete" autocomplete="off">
										</td>



								<td style="width: 10%;"><a class="btn btn-success btn-sm"
									value="ADD" title="ADD" id="h_travel_add1"
									onclick="h_travel_add_fn(1);"><i class="fa fa-plus"></i></a> <a
									style="display: none;" class="btn btn-danger btn-sm"
									value="REMOVE" title="REMOVE" id="h_travel_remove1"
									onclick="h_travel_remove_fn(1);"><i class="fa fa-trash"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" id="h_travel_count" name="h_travel_count"
						class="required form-control" autocomplete="off" value="1" /> <input
						type="hidden" id="h_travel_Old_count" name="h_travel_Old_count"
						class="form-control" maxlength="2" autocomplete="off" value="0">
				</div>

				<div class="col-md-12">
					<table id="h_list_table" class="table-bordered "
						style="margin-top: 10px; width: 100%;">
						<thead style="width: 100%;">
							<tr>
								<th colspan="11"
									style="font-size: 14px; margin-top: 5px; text-align: center;">LIST
									OF CLOSE CONTACTS</th>
							</tr>


						</thead>
						<thead style="color: white; text-align: center;">
							<tr>
								<th style="width: 2%;">Sr No</th>
								<th>Date of Exposure</th>
								<th>Name of close contact</th>
								<th>Remarks</th>
								<th style="width: 10%;">Action</th>
							</tr>
						</thead>
						<tbody data-spy="scroll" id="h_list_tbody"
							style="min-height: 46px; max-height: 650px; text-align: center;">
							<tr id="tr_h_list_1">
								<td class="h_list_srno" style="width: 2%;">1</td>
								<td style="width: 10%;"><input type="date"
									id="h_datee_expo1" name="h_datee_expo1"
									class="form-control-sm form-control" autocomplete="off"
									min="1899-01-01" max="${date}"></td>
								<td style="width: 10%;"><input type="text" id="h_name1"
									name="h_name1" class="form-control-sm form-control"
									autocomplete="off"></td>
                           <td style="width: 20%;"><input type="textarea"
									id="h_remark1" name="h_remark1"
									class="form-control-sm form-control" autocomplete="off">
								</td>
                             <td style="width: 10%;"><a class="btn btn-success btn-sm"
									value="ADD" title="ADD" id="h_list_add1"
									onclick="h_list_add_fn(1);"><i class="fa fa-plus"></i></a> <a
									style="display: none;" class="btn btn-danger btn-sm"
									value="REMOVE" title="REMOVE" id="h_list_remove1"
									onclick="h_list_remove_fn(1);"><i class="fa fa-trash"></i></a>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" id="h_list_count" name="h_list_count"
						class="required form-control" autocomplete="off" value="1" /> <input
						type="hidden" id="h_list_Old_count" name="h_list_Old_count"
						class="form-control" maxlength="2" autocomplete="off" value="0">
				</div>
				

				
				<div class="col-md-12" style="margin-top:20px;">
					<div class="col-md-8">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label">Probable Source of Infection</label>
							</div>
							<div class="col-md-9">
								<input type="text" id="prob_source_insp" name="prob_source_insp"
									autocomplete="off" class="form-control-sm form-control" placeholder="Enter Probable Source of Infection...">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 row form-group" id="env">
					<div class="card-body">
						<div id="divShow" style="display: block;">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<table
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead>
										<tr>
											<th style="font-size: 14px; text-align: center;" colspan="3">ENVIRONMENTAL INSPECTION</th>
										</tr>
										<tr>
											<th>Loc1</th>
											<th>Loc2</th>
											<th>Loc3</th>
										</tr>
									</thead>
									<thead>
										<tr>
											<td><input type="text" id="loc1" name="en_loc1" placeholder="Enter Location1..." class="form-control" autocomplete="off"></td>
											<td><input type="text" id="loc2" name="en_loc2" placeholder="Enter Location2..." class="form-control" autocomplete="off"></td>
											<td><input type="text" id="loc3" name="en_loc3" placeholder="Enter Location3..." class="form-control" autocomplete="off"></td>
										</tr>
									</thead>
									<tbody>
									<%--  <c:forEach var="item" items="${list}" varStatus="num">
											<tr>
												<th style="font-size: 14px; text-align: center;">${num.index+1}</th>
												<th id="thAction1"
													style="font-size: 14px; text-align: center;" width="10%">${item.}</th>
											</tr>
										</c:forEach> --%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
   <div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Vector Breeding Found</label>
							</div>
							<div class="col-md-8">
								<select name="breeding_found" id="breeding_found"
									class="form-control-sm form-control" >
									<option value="-1">--Select--</option>
									<option value="0">Yes</option>
									<option value="1">No</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Follow Up Done</label>
							</div>
							<div class="col-md-8">
							<select name="follow_up_down" id="follow_up_down"
									class="form-control-sm form-control" >
									<option value="-1">--Select--</option>
									<option value="0">Yes</option>
									<option value="1">No</option>
								</select>
								
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<label for="text-input" class=" form-control-label">Final Epidemiological Diagnosis</label>
					</div>

					<div class="col-md-10">
						<div class="row form-group">
							<div class="col-md-6">
							<input type="text" id="final_epid_diag" name="final_epid_diag" placeholder="Enter Epidemiological Diagnosis..." class="form-control" autocomplete="off">
							</div>

							<div class="col-md-6">
								<select id="final_epid_diagnosis" name="final_epid_diagnosis" class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${Epiddiag}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>

						</div>

					</div>
				</div>

				<div class="card-header-inner" id="aa">
					<strong>Action Undertaken</strong>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">For the Individuals</label>
							</div>
							<div class="col-md-8">
							<input type="text" id="action_for_patient" name="action_for_patient" placeholder="For the Individuals..." class="form-control" autocomplete="off">
							
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Action By SHO/FHO</label>
							</div>
							<div class="col-md-8">
							<div id = "action_by_sho1" class="form-control-label" >
								</div>
							
								
							</div>
						</div>
					</div>
					
					
				</div>
				<div class="col-md-12" style="margin-left:700px;">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label" id="d11" style="display: none;">Details</label>
								</div>
								<div class="col-md-8">
									<textarea class="form-control-sm form-control" name="other_action_by_sho" maxlength="250"
										id="other_action_by_sho" style="display: none;" autocomplete="off" ></textarea>
								
									
								</div>
							</div>
						</div>
                     
						
					</div>
						<div class="col-md-12 form-group">
							<div class="col-md-2">
								<label for="text-input" class=" form-control-label">Recommendation for the indl: Barrack/Married accn</label>
							</div>
							<div class="col-md-10">
								<textarea rows="2" cols="250" class="form-control-sm form-control"
									placeholder="Enter Recommendation for the Individual.." id="recommendation_indl" name="recommendation_indl"></textarea>
							</div>
						</div>
				<div class="col-md-12 form-group">
					<div class="col-md-2">
						<label for="text-input" class=" form-control-label">Recommendation for the Unit</label>
					</div>
					<div class="col-md-10">
						<textarea rows="2" cols="250" class="form-control-sm form-control" placeholder="Enter Recommendation for the Unit.."
							id="recommendation_unit" name="recommendation_unit"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-control card-footer" align="center">
			<a href="mnh_input_mosquito" type="reset" class="btn btn-primary btn-sm" onclick="btn_clc();"> Clear </a> 
			<input type="submit" class="btn btn-success btn-sm" id="btn_save" value="Save" onclick="return isvalidData();">
		</div>
	</div>
</form:form>

<script>
	$(document) .ready(	function() {

						var d = new Date();
						var c_d = d.getFullYear() + "-"
								+ ("0" + (d.getMonth() + 1)).slice(-2) + "-"
								+ ("0" + d.getDate()).slice(-2);
						var year = d.getFullYear() + '-';
						$("#case_no").val(year);
						$().getCurDt(datee);

						$("#ip_start_date")
								.change(
										function() {
											var a = this.value;
											var b = $("#ip_end_date").val();

											if (a != "" && b != "") {
												if (a > c_d) {
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													$("#divPrint").hide();
													alert("Can't select the Future Start Date");
													return false;
												} else if (b > c_d) {
													$("#ip_end_date").focus();
													$("#ip_end_date").val('');
													$("#divPrint").hide();
													alert("Can't select the Future End Date");
													return false;
												} else if (a > b) {
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													$("#divPrint").hide();
													alert("Start Date Must be less than End Date Require");
													return false;
												} else {
													tb0(a, b, "#ip_start_date");
												}
											} else {
												if (a > c_d) {
													alert("Can't select the Future Start Date");
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													return false;
												}
												if (b > c_d) {
													alert("Can't select the Future End Date");
													$("#ip_end_date").focus();
													$("#ip_end_date").val('');
													return false;
												}
											}

										});

						$("#ip_end_date").change(function() {
											var b = this.value;
											var a = $("#ip_start_date").val();

											if (a != "" && b != "") {
												if (a > c_d) {
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													$("#divPrint").hide();
													alert("Can't select the Future Start Date");
													return false;
												} else if (b > c_d) {
													$("#ip_end_date").focus();
													$("#ip_end_date").val('');
													$("#divPrint").hide();
													alert("Can't select the Future End Date");
													return false;
												} else if (a > b) {
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													$("#divPrint").hide();
													alert("Start Date Must be less than End Date Require");
													return false;
												} else {
													tb0(a, b, '#ip_end_date');
												}
											} else {
												if (a > c_d) {
													alert("Can't select the Future Start Date");
													$("#ip_start_date").focus();
													$("#ip_start_date").val('');
													return false;
												}
												if (b > c_d) {
													alert("Can't select the Future End Date");
													$("#ip_end_date").focus();
													$("#ip_end_date").val('');
													return false;
												}
											}
										});

						if ('${list.category}' == "OR"
								&& '${list.service}' == "ARMY") {
							$("#persnl_no2").val(
									'${list.persnl_no}'.substring(0, l - 1));
							$("#persnl_no3").val(
									'${list.persnl_no}'.substring(l - 1, l));
						} else if ('${list.service}' == "AIRFORCE"
								|| '${list.service}' == "NAVY") {
							$("#persnl_no2").val(
									'${list.persnl_no}'.substring(0, l - 1));
							$("#persnl_no3").val(
									'${list.persnl_no}'.substring(l - 1, l));
						} else if ('${list.service}' == "ARMY"
								&& '${list.category}' != "OR") {
							$("#persnl_no1").val(
									'${list.persnl_no}'.substring(0, 2));
							$("#persnl_no2").val(
									'${list.persnl_no}'.substring(2, l - 1));
							$("#persnl_no3").val(
									'${list.persnl_no}'.substring(l - 1, l));
						} else if ('${list.service}' == "OTHERS") {
							$("#persnl_no1").val('-1');
							$("#persnl_no2").val('');
							$("#persnl_no3").val('-1');
						}

						if ('${list.service}' == "NAVY"
								|| '${list.service}' == "AIRFORCE") {
							$("#persnl_no1").attr('disabled', true);
							$("#category").attr('disabled', false);
						}
						if ('${list.service}' == "OTHERS") {
							$("#persnl_no1").attr('disabled', true);
							$("#persnl_no2").attr('disabled', true);
							$("#persnl_no3").attr('disabled', true);
							$("#category").attr('disabled', true);
							$("#rank").attr('disabled', true);

							$("#category option[value='OFFICER']").hide();
							$("#category option[value='MNS']").hide();
							$("#category option[value='JCO']").hide();
						}
						if ('${list.service}' == "ARMY") {
							$("#category").attr('disabled', true);
						}

						if ('${list.service}' != "OTHERS") {
							$("#category").val('${list.category}');
							getRank();
						}
						$("#service").val("ARMY");
						chgCategory();
						getRank();

						$('#persnl_no1').change(function() {
									chgCategory();
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#persnl_no3').change(function() {
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});
						$('#category').change(function() {
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#service').change(function() {
									chgCategory();
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#persnl_no1').change(function() {
									chgCategory();

									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});

						$('#category').change(function() {
									if ($("#service").val() != "-1"
											&& $("#category").val() != "-1") {
										getRank();
									}
								});
						
						
					/* 	$('#service').change(function(){
							chgCategory();
							
							if($("#service").val() != "-1" && $("#category").val() != "-1"){
								getRank();
							}
						});
						
						$('#persnl_no1').change(function(){
							chgCategory();
							
							if($("#service").val() != "-1" && $("#category").val() != "-1"){
								getRank();
							}
						});
						
						$('#category').change(function(){
							if($("#service").val() != "-1" && $("#category").val() != "-1"){
								getRank();
							}
						}); */

						try {
							if (window.location.href.includes("msg=")) {
								var url = window.location.href.split("?msg")[0];
								window.location = url;
							}
						} catch (e) {
							// TODO: handle exception
						}

					});

	var d = new Date();
	var c_d = d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2)
			+ "-" + ("0" + d.getDate()).slice(-2);

	$("#datee").change(function() {
		if ($("#datee").val() > c_d) {
			$("#datee").focus();
			alert("Can't select the Future Date");
			$("#datee").val('');
			return false;
		}
	});

	$("#date_repo_med_centre").change(function() {
		if ($("#date_repo_med_centre").val() > c_d) {
			$("#date_repo_med_centre").focus();
			alert("Can't select the Future Date");
			$("#date_repo_med_centre").val('');
			return false;
		}
	});

	function Clearvalue() {
		 var text15 = $("#disease option:selected").text();
			
			if(text15.toUpperCase() == "MALARIA" || text15.toUpperCase() == "DENGUE" ){
				
				$("#div_past").show();
			} 
			else{
				
				$("#div_past").hide();
			}
	}



</script>

<script>
	function isvalidData() {

		if ($("#sus_no1").val() == "") {
			alert("Please Enter the SUS No");
			$("#sus_no1").focus();
			return false;
		}
		if ($("#disease").val() == "-1") {
			alert("Please Select the Disease");
			$("#disease").focus();
			return false;
		}
		var year = d.getFullYear() + '-';
		if ($("#case_no").val() == year) {
			alert("Please Enter the Case No");
			$("#case_no").focus();
			return false;
		}
	
		if ($("#service").val() == "NAVY" && $("#service").val() == "AIRFORCE") {
			if ($("#persnl_no2").val() == "" && $("#persnl_no3").val() == "-1") {
				alert("Please Enter the Personnel No");
				$("#persnl_no2").focus();
				$("#persnl_no3").focus();
				return false;
			}
		}
		if($("#service").val() == "ARMY"){
			if ($("#persnl_no1").val() == "-1" && $("#persnl_no2").val() == "" && $("#persnl_no3").val() == "-1") {
				alert("Please Enter the Personnel No");
				$("#persnl_no2").focus();
				$("#persnl_no3").focus();
				$("#persnl_no1").focus();
		 		return false;
			}
		}
		if ($("#persnl_no2").val() == "" && $("#service").val() != "OTHERS") {
			alert("Please Enter the Personnel No");
			$("#persnl_no2").focus();
			return false;
		}
		if ($("#persnl_no3").val() == "-1" && $("#service").val() != "OTHERS") {
			alert("Please Select the Personnel No");
			$("#persnl_no3").focus();
			return false;
		}
		if ($("#personal_name").val() == " ") {
			alert("Please Enter  the Personal Name");
			$("#personal_name").focus();
			return false;
		}
		if ($("#category").val() == "-1" && $("#service").val() != "OTHERS") {
			alert("Please Select the Category");
			$("#category").focus();
			return false;
		}
		
		if ($("#relationship").val() == "-1") {
			alert("Please Select the Relationship");
			$("#relationship").focus();
			return false;
		}
		if ($("#rank").val() == "-1" && $("#service").val() != "OTHERS") {
			alert("Please Select the Rank");
			$("#rank").focus();
			return false;
		}
		var adhar_no = ($("#adhar_card_no").val());

		if (!adhar_no == "" && adhar_no != "0" && adhar_no != null
				&& adhar_no.length < 12) {
			alert("Adhaar No Should Contain Maximum 12 Digit");
			return false;
		}
		
		
		var b = $('input:checkbox[name=action_by_sho]:checked').val();
	    		    if(b == undefined){	
	        		        alert("Please Check Action By SHO/FHO");
		        return false;
	        }  
		
		return true;
	}

	function btn_clc() {
		location.reload(true);
	}

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)) {
			return false;
		} else {
			if (charCode == 46) {
				return false;
			}
		}
		return true;
	}

	function validate() {
		var age = +document.getElementById('age').value;
		if (!(age >= 17)) {
			alert("The age must be greater than 17");
			return false;
		}
		return true;
	}

	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";

	jQuery(function() {
		// Source SUS No

		jQuery("#sus_no1").keypress(function(){
			var sus_no = this.value;
				 var susNoAuto=jQuery("#sus_no1");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetSUSNoList?"+key+"="+value,
				        data: {sus_no:sus_no},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
				        	  alert("Please Enter Approved SUS No.");
				        	  document.getElementById("sus_no1").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	  var unit_sus_no = ui.item.value;
				    	 	
	  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
	  	 			 		if(j == ""){
	  	 			      	 	alert("Please Enter Approved Unit SUS No.");
	  			        	  	document.getElementById("unit_name1").value="";
	  			        	  	susNoAuto.val("");
	  			        	  	susNoAuto.focus();
	  	 			      	}else{
		    	 	   	        	var length = j.length-1;
		    	    				var enc = j[length].substring(0,16);
		    	    				$("#unit_name1").val(dec(enc,j[0]));	
		    	 	   	        		
	  	 	   	        	}
	  	 				}).fail(function(xhr, textStatus, errorThrown) {
	  	 				});
				      } 	     
				});	
		});
		// End
		
		// Source Unit Name

	    jQuery("#unit_name1").keypress(function(){
	 			var unit_name = this.value;
					 var susNoAuto=jQuery("#unit_name1");
					  susNoAuto.autocomplete({
					      source: function( request, response ) {
					        jQuery.ajax({
					        type: 'POST',
					        url: "getTargetUnitsNameActiveList?"+key+"="+value,
					        data: {unit_name:unit_name},
					          success: function( data ) {
					        	  var susval = [];
					        	  var length = data.length-1;
					        	  if(data.length != 0){
						        		var enc = data[length].substring(0,16);
						        	}
						        	for(var i = 0;i<data.length;i++){
						        		susval.push(dec(enc,data[i]));
						        	}
						        	var dataCountry1 = susval.join("|");
					            var myResponse = []; 
					            var autoTextVal=susNoAuto.val();
								jQuery.each(dataCountry1.toString().split("|"), function(i,e){
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
					        	  alert("Please Enter Approved Unit Name.");
					        	  document.getElementById("unit_name1").value="";
					        	  susNoAuto.val("");	        	  
					        	  susNoAuto.focus();
					        	  return false;	             
							}   	         
						}, 
						select: function( event, ui ) {
							 var target_unit_name = ui.item.value;
							
									 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
									 		 var length = j.length-1;
					 				         var enc = j[length].substring(0,16);
					 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
										}).fail(function(xhr, textStatus, errorThrown) {
								});
						} 	     
					}); 			
	 			});

	});


	$("#diagnosis").keyup(function() {
		var d1 = this.value;
		$().Autocomplete2('GET', 'getMNHDistinctICDList', diagnosis, {
			a : d1,
			b : "ALL"
		}, '', '');
	});

	$("#relationship").keyup(function() {
		var d1 = this.value;
		$().Autocomplete2('GET', 'getMedrelationList', relationship, {
			a : d1,
			b : "ALL"
		}, '', '');
	});
//pki
$.post("getaction_bySystem?"+key+"="+value,function(j) {
			$("#action_by_sho1").empty();
	 		for ( var i = 0; i < j.length ;i++) {
	 			
	 				$("#action_by_sho1").append ( "<input type='checkbox' name='action_by_sho' onchange='funcheckbox(this)' id='action_by_sho"+j[i][0] +"' value='" + j[i][0] + "'  />&nbsp;" + j[i][1] + " <br/> " );
	 		} 
	 	});
	
function funcheckbox(ele){
	var checkboxes = ele.value;
	var b = $("#action_by_sho5").val();

	if (b == checkboxes && b != "" ) {
    	if($('input:checkbox[id=action_by_sho5]').is(':checked') == true){
	    	$("#d11").show();
	    	$("#other_action_by_sho").show();
    	}
    	else{
        	$("#d11").hide();
        	$("#other_action_by_sho").hide();
        	$("#other_action_by_sho").val("");
        }
    }

}
</script>
<script>
function chgCategory(){
	if($("#service").val() == "NAVY" || $("#service").val() == "AIRFORCE"){
		$("#persnl_no1").attr('readonly',true);
		$("#persnl_no2").attr('readonly',false);
		$("#persnl_no3").attr('readonly',false);
		$("#category").attr('readonly',false);
		
		$("#category option[value='OFFICER']").show();
		$("#category option[value='MNS']").show();
		$("#category option[value='JCO']").show();
		
		$("#persnl_no1").val('-1');
		$("#category").val('-1')
	}	
	
	if($("#service").val() == "OTHERS"){
		$("#persnl_no1").attr('readonly',true);
		$("#persnl_no2").attr('readonly',true);
		$("#persnl_no3").attr('readonly',true);
		$("#category").attr('readonly',true);
		
		$("#category option[value='OFFICER']").hide();
		$("#category option[value='MNS']").hide();
		$("#category option[value='JCO']").hide();
		
		$("#persnl_no1").val('-1');
		$("#persnl_no2").val('');
		$("#persnl_no3").val('-1');
		$("#category").val('-1')
	}	
	
	if($("#service").val() == "ARMY"){
		$("#persnl_no1").attr('readonly',false);
		$("#persnl_no2").attr('readonly',false);
		$("#persnl_no3").attr('readonly',false);
		$("#category").attr('readonly',true);
		
		$("#category option[value='OFFICER']").show();
		$("#category option[value='MNS']").show();
		$("#category option[value='JCO']").show();
		
		$("#persnl_no2").val('');
		$("#persnl_no3").val('-1');
		
		if($("#persnl_no1").val() == "-1"){
			$("#category").val('OR');
			$("#hid_category").val('OR');
		}
		else if($("#persnl_no1").val() == "TJ" || $("#persnl_no1").val() == "JC"){
			$("#category").val('JCO');
			$("#hid_category").val('JCO');
		}
        else if($("#persnl_no1").val() == "NR" || $("#persnl_no1").val() == "NS" || 
				$("#persnl_no1").val() == "NL" || $("#persnl_no1").val() == "PN"){
        	$("#category").val('MNS');
        	$("#hid_category").val('MNS');
		}
        else if($("#persnl_no1").val() != "NR" || $("#persnl_no1").val() != "NS" || 
				$("#persnl_no1").val() != "NL" || $("#persnl_no1").val() != "PN" || $("#persnl_no1").val() != "TJ" || 
				$("#persnl_no1").val() != "JC"){
        	$("#category").val('OFFICER');
        	$("#hid_category").val('OFFICER');
        	
		}	
	}
}

	function getRank() {
		var category = $("#category").val();
		var service = $("#service").val();
		$("#rank").attr('disabled', false);
		$.post("getMNHRank?" + key + "=" + value,{a1 : category,a2 : service},function(j) {
							var options = '<option value="-1">--Select--</option>';
							var a = [];
							var enc = j[j.length - 1].substring(0, 16);
							for (var i = 0; i < j.length; i++) {
								a[i] = dec(enc, j[i]);
							}
							var data = a[0].split(",");
							var datap;

							for (var i = 0; i < data.length - 1; i++) {
								datap = data[i].split(":");
								options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'
										+ datap[0] + '</option>';
							}
							$("#rank").html(options);
							var q = '${list.rank}';
							if (q != "") {
								$("#rank").val(q);
							}
						});
	}
</script>

<script>
	(function($) {
		"use strict";
		$.fn.charCounter = function(options) {
			if (typeof String.prototype.format == "undefined") {
				String.prototype.format = function() {
					var content = this;
					for (var i = 0; i < arguments.length; i++) {
						var replacement = '{' + i + '}';
						content = content.replace(replacement, arguments[i]);
					}
					return content;
				};
			}
			var options = $.extend({
				backgroundColor : "#FFFFFF",
				position : {
					right : 10,
					top : 10
				},
				font : {
					size : 10,
					color : "#a59c8c"
				},
				limit : 250
			}, options);
			return this.each(function() {
				var el = $(this), wrapper = $("<div/>").addClass(
						'focus-textarea').css({
					"position" : "relative",
					"display" : "inline-block"
				}), label = $("<span/>").css({
					"zIndex" : 999,
					"backgroundColor" : options.backgroundColor,
					"position" : "absolute",
					"font-size" : options.font.size,
					"color" : options.font.color
				}).css(options.position);
				if (options.limit > 0) {
					label
							.text("{0}/{1}".format(el.val().length,
									options.limit));
					el.prop("maxlength", options.limit);
				} else {
					label.text(el.val().length);
				}
				el.wrap(wrapper);
				el.before(label);
				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
						'keydown', updateLabel);
				function updateLabel(e) {
					if (options.limit > 0) {
						label.text("{0}/{1}".format($(this).val().length,
								options.limit));
					} else {
						label.text($(this).val().length);
					}
				}
			});
		}
	})(jQuery);
	$(".char-counter1").charCounter();
</script>
<script>
/////////////////////new changes 08-10-2021////////////////////////
	
  aller=1;
  travel=1;
  function h_list_add_fn(q){
	 
  
  	if(q!=0){
  	$("#h_list_add"+aller).hide();
  	$("#h_list_remove"+aller).hide();
  	}
  	aller=q+1;
 
 
  	 $("input#h_list_count").val(aller);
  	

  	$("table#h_list_table").append('<tr align="center" id="tr_h_list'+aller+'"><td style="width: 2%;">'+aller+'</td>'
  	+'<td><input type="date" id="h_datee_expo'+aller+'" name="h_datee_expo'+aller+'" class="form-control autocomplete" autocomplete="off">  </td>'
+ '<td> <input type="text" name="h_name' + aller + '" id="h_name' + aller + '" class="form-control autocomplete" autocomplete="off">' + '</td>'
  +'<td> <input type="textarea" name="h_remark'+aller+'" id="h_remark'+aller+'" class="form-control-sm form-control"  autocomplete="off" >'
	+'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "h_list_add'+aller+'" onclick="h_list_add_fn('+aller+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "h_list_remove'+aller+'" onclick="h_list_remove_fn('+aller+');"><i class="fa fa-trash"></i></a></td>' 
      +'</tr>');
  	
  	
	
	
  		 if(aller==1){
  			 $("#h_list_remove"+aller).hide();
  		 }	

  }
  
 
  function h_travel_add_fn(q){
		 
	  
	  	if(q!=0){
	  	$("#h_travel_add"+travel).hide();
	  	$("#h_travel_remove"+travel).hide();
	  	}
	  	travel=q+1;
	 
	 
	  	 $("input#h_travel_count").val(travel);
	  	

	  	$("table#h_travel_table").append('<tr align="center" id="tr_h_travel'+travel+'"><td style="width: 2%;">'+travel+'</td>'
	  	+'<td><input type="date" id="h_datee'+travel+'" name="h_datee'+travel+'" class="form-control autocomplete" autocomplete="off">  </td>'
	+ '<td style="width: 10%;"> <input type="time" name="h_time' + travel + '" id="h_time' + travel + '" autocomplete="off" class="form-control autocomplete" >' + '</td>'
	  +'<td style="width: 10%;"> <input type="text" name="h_loc'+travel+'" id="h_loc'+travel+'" autocomplete="off" class="form-control-sm form-control"   >'
	  +'<td style="display: none;"><input type="text" id="travel_id'+travel+'" name="travel_id'+travel+'" value="0" class="form-control autocomplete" autocomplete="off"></td>'
	  +'<td style="width: 10%;"><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "h_travel_add'+travel+'" onclick="h_travel_add_fn('+travel+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "h_travel_remove'+travel+'" onclick="h_travel_remove_fn('+travel+');"><i class="fa fa-trash"></i></a></td>' 
	      +'</tr>');
	  	
	  	
		
		
	  		 if(travel==1){
	  			 $("#h_travel_remove"+travel).hide();
	  		 }	

	  }
	  
	  function h_travel_remove_fn(R){
			
			 $("tr#tr_h_travel"+R).remove();	
			 travel=travel-1
			 $("input#h_travel_count").val(travel);
			 $("#h_travel_add"+travel).show();
			 if(travel!=1){
			 $("#h_travel_remove"+travel).show();
			 }	
		}
	  function h_list_remove_fn(R){
			
			 $("tr#tr_h_list"+R).remove();	
			 aller=aller-1
			 $("input#h_list_count").val(aller);
			 $("#h_list_add"+aller).show();
			 if(aller!=1){
			 $("#h_list_remove"+aller).show();
			 }	
		}
	  function personal_number()
	  {
	  	var a1 = $("select#persnl_no1").val();
	  	var a2 =  $("input#persnl_no2").val();
	  	var a3 = $("select#persnl_no3").val();
	  	var personnel_no = a1 +a2+a3;
	  	 if(a1 != '-1' && a2 != '' && a3 != '-1')
	        {
	      
	  	 $.ajaxSetup({
	           async : false
	   	   });

	  		 $.post("GetPersonnelNoDatamnh?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
	  	      
	  	         v =j.length;
	  	        if(v==1){
	  	        	$("#category").val("OFFICER"); 
	  	        	$("#service").val("ARMY");  
	  	        	$("#adhar_card_no").val(j[0].adhar_number);	
	  	     		$("#date_of_birth1").val(j[0].date_of_birth);	     		
	                  $("#personal_name").val(j[0].name);
	  	    		//$("#sex").val(j[0].gender_name);
	  	    		$("#daily_rank").val(j[0].description);
	  	    		
	  	    		//$("#appointment").val(j[0].appointment);	
	  	    		$("#persnl_unit").val(j[0].unit_name);	
	  	    		$("#contact_no").val(j[0].mobile_no);	
	  	    		
	  	    
                     $("#category").attr('readonly',true);
                      $("#personal_name").attr('readonly',true);
                      $("#date_of_birth1").attr('readonly',true);
	                 var from_d=j[0].date_of_birth;
	  	     		
	  	     	    var from_d1=from_d.substring(0,4);
	  	     	    var from_d2=from_d.substring(7,5);
	  	     	    var from_d3=from_d.substring(10,8);
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
	  	     	    //days
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
	  	     	    $("#age_year").val(year);
	  	     	    
	  	     	    if (month == undefined)
	  	     	            {
	  	     	            month = 0;
	  	     	            }
	  	     	    document.getElementById('age_month').value = month;
	  	     	    
	  	            }
	    	      else
		          {
		          $("#adhar_card_no").val("");        
		          $("#date_of_birth1").val("");                                  
		           $("#personal_name").val("");   
		          $("#persnl_unit").val("");
		          $("#contact_no").val("0");
		          $("#age_year").val("0");
		          $("#age_month").val("0");
		          
		         $("#personal_name").attr('readonly',false);
		          $("#date_of_birth1").attr('readonly',false);
		          $("#category").attr('readonly',false);
		          $("#service").attr('readonly',false);
		          }
		  	      getRank();
		  		 }); 
	  	       
	  		 
	  		
	  	}
	  	 
 	      else
          {
          $("#adhar_card_no").val("");        
          $("#date_of_birth1").val("");                                  
           $("#personal_name").val("");   
          $("#persnl_unit").val("");
          $("#contact_no").val("0");
          $("#age_year").val("0");
          $("#age_month").val("0");
          
         $("#personal_name").attr('readonly',false);
          $("#date_of_birth1").attr('readonly',false);
          $("#category").attr('readonly',false);
          $("#service").attr('readonly',false);
          }
  	      getRank();
  		
	  }
</script>