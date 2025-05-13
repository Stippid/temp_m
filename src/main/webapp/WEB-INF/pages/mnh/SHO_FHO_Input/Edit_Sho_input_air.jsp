<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
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

<form:form name="Sho_input_air_edit" id="Sho_input_air_edit" action="Sho_input_air_editAction" method='POST' commandName="Sho_input_air_editCMD">

        <div class="card">
              <div class="card-header">
		           <h5 align="center">EDIT EPIDEMIOLOGICAL INVESTIGATION REPORT(AIRBORNE)</h5>
				<h6>
					<span style="font-size: 12px; margin-left:700px; color: red">(To be entered by SHO/FHO)</span>
				</h6>
			  </div>
			
			<div class="card-body card-block">
				<div class="row">
				 <div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>SHO/FHO Name</label>
							 </div>
						<div class="col-md-9">
							<input type="text" id="unit_name1" name="unit_name1" class="form-control-sm form-control" placeholder="Search..." maxlength="100"
						autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						</div>
						</div>
						</div>
						
						<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-8">
						<input type="hidden" name="eir_id" id="eir_id"/>
						<input type="hidden" name="id" id="id"/>
	             			  <input type="text" id="sus_no1" name="sus_no" class="form-control-sm form-control" 
	             			  maxlength="8" placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
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
						<select name="disease" id="disease" class="form-control-sm form-control" onchange="Clearvalue()" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_6}" varStatus="num">
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
							<input type="text" id="case_no" name="case_no" class="form-control-sm form-control" placeholder="Enter..." 
						autocomplete="off">	
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date</label>
						</div>
						<div class="col-md-8">
	             			  <input type="date" id="datee" name="datee" class="form-control-sm form-control" placeholder="Search..." 
						autocomplete="off" min="1899-01-01" max="${date}" >
						</div>
						</div>
						</div>
			</div>
				
				
				
		<div class="card-header-inner" id="aa" > <strong>Personnel Particulars of Individual</strong></div> 
	     	<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Service</label>
							 </div>
						<div class="col-md-8">
							
							<select id="service" name="service" class="form-control-sm form-control">
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
	             			<select id="relationship" name="relationship" class="form-control-sm form-control">
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_7}" varStatus="num">
							<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>
						</div>
						</div>
						</div>
			</div>


					<%-- <div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Personnel No</label>
								</div>
								
								<div class="col-md-10">
								    <div class="row form-group">
								    <div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_1}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
									</div>
									<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2"
										class="form-control-sm form-control" autocomplete="off"
										placeholder="Enter No..." maxlength="10">
								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_2}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
									</div>
								</div>
					</div> --%>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong> Personnel No</label>
							</div>
							<div class="col-md-8">
							<div class="col-md-3">
								<select id="persnl_no1" name="persnl_no1"
									class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCodePERSNL_PRE}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6">
								<input type="text" id="persnl_no2" name="persnl_no2" onkeypress="return isNumber0_9Only(event)"  class="form-control-sm form-control" autocomplete="off"
									placeholder="Enter No..." maxlength="10">
							</div>
							<div class="col-md-3">
							<select id="persnl_no3" name="persnl_no3" class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCodePERSNL_SUF}" varStatus="num">
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
									oninput="this.value = this.value.toUpperCase()" placeholder="Enter Personnel Name..." maxlength="100" value="${Sho_input_air_editCMD.personal_name}">
							</div>
						</div>
					</div>
				</div>

                  <!--  <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">* </strong> Personnel
										Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personal_name" name="personal_name"
										class="form-control-sm form-control" autocomplete="off"
										oninput="this.value = this.value.toUpperCase()"
										placeholder="Enter Personnel Name..." maxlength="100">
								</div>
							</div>
						</div>
					</div>	
					 -->


					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Category</label>
							 </div>
						<div class="col-md-8">
							 <select name="category" id="category" class="form-control-sm form-control" readonly>
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
	             			<select name="eir_rank.id" id="rank" data-placeholder="Select the Rank..." class="form-control-sm form-control" onchange="rank_change(this);">
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
							<input type="date" id="date_of_birth1" name="date_of_birth1" autocomplete="off"  class="form-control-sm form-control"
								onchange="calculate_age(this);"  maxlength="3" min="1899-01-01" max="${date}">
						</div>
						</div>
						</div>
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
            <label for="text-input" class=" form-control-label">Aadhar Card No.</label>
						</div>
							
						<div class="col-md-8">
							<form:input type="text" id="adhar_card_no1" path="adhar_card_no" class="form-control-sm form-control" 
						maxlength="14" min="12" onkeypress="return isNumber0_9Only(event)" placeholder=" Please Enter 12 Digit Aadhar card No..." autocomplete="off"/>

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
	             			<input type="text" id="personnel_unit" name="personnel_unit" autocomplete="off" class="form-control-sm form-control" oninput="this.value = this.value.toUpperCase()"
							  placeholder="Enter Unit...">
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
							 <input type="text" id="present_address" name="present_address" autocomplete="off" class="form-control-sm form-control"
						 	placeholder="Enter Present Address(Residential)...">
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Contact Number(Mobile)</label>
						</div>
						<div class="col-md-8">
	             			<input type="text" id="contact_no" name="contact_no" autocomplete="off" class="form-control-sm form-control" onkeypress="return isNumber0_9Only(event)"
						  	maxlength="10" placeholder="Enter Contact Number...">
						</div>
						</div>
						</div>
			</div>
<%-- 

              <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	 <label for="text-input" class=" form-control-label">Type of First Symptom</label>
							 </div>
						<div class="col-md-8">
							 <input type="text" id="type_of_symptom" name="type_of_symptom" autocomplete="off" class="form-control-sm form-control"
							 placeholder="Enter Type of First Symptom...">
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Date of Onset of Symptom</label>
						</div>
						<div class="col-md-8">
							<input type="date" id="date_onset_symp" name="date_onset_symp" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}">
						</div>
						</div>
						</div>
			   </div> --%>

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

                <div class="col-md-12" >
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4" id="med1">
						 	 <label for="text-input" class=" form-control-label">Date of Reporting</label>
							 </div>
					
							 <div class="col-md-4" id="MIroom1" style="display:none;">
							    <label for="text-input" class=" form-control-label">Date of Reporting</label>
						     </div>
						<div class="col-md-8" id="med2">
							<input type="date" id="date_repo_med_centre" name="date_repo_med_centre" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}">
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
								autocomplete="off" class="form-control char-counter1"
								placeholder="Enter Brief History..."></textarea>
						</div>
					</div> 
					
                <div class="col-md-12" style="display:none;" id="hospitalization">

						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	 <label for="text-input" class=" form-control-label">Date of Hospitalization</label>
							 </div>
						<div class="col-md-8">
							<input type="date" id="date_hospitalization" name="date_hospitalization" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}">
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Period of Infectivity</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="period_infectivity" name="period_infectivity" placeholder="Enter period_infectivity..." autocomplete="off" class="form-control-sm form-control">
						</div>
						</div>
						</div>
						
</div>

<!-- <div class="card-header-inner" id="aa"> <strong>Incubation Period (IP)</strong></div> -->

	<%-- <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						 	 <label for="text-input" class=" form-control-label">Start Date</label>
							 </div>
						<div class="col-md-8">
						<input type="date" id="ip_start_date" name="ip_start_date" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}"
						  >
						</div>
						</div>
						</div>
						
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
								<label for="text-input" class=" form-control-label">End Date</label>
						</div>
						<div class="col-md-8">
							<input type="hidden" id="dif_val_hid" name="dif_val_hid" class="form-control-sm form-control">
							 <input type="date" id="ip_end_date" name="ip_end_date" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}"
							> 
						</div>
						</div>
					</div>
		</div> --%>
				
					  <!--   <div class="col-md-12 form-group" id="divPrint" style="display: none;">
							<span id="ip"></span>
							    <table id="myTable" class="table no-margin table-striped  table-hover  table-bordered report_print ">
										<thead style="width: 100%;">
										   <tr>
										   <th colspan="11" style="font-size: 14px; margin-top:5px;text-align:center;">HISTORY OF STAY/TRAVEL DURING IP</th>
										   </tr>
										   <tr>
										   <th colspan="11" style="font-size: 14px; margin-top:5px;text-align:center;">INDIVIDUALS STAY(LOCATION) DURING IP</th>
										   </tr>
										</thead>
										<thead>
										<tr>
										<th>Date</th>
										<th>Time</th>
										<th>Loc</th>
										<th>Time</th>
										<th>Loc</th>
										<th>Time</th>
										<th>Loc</th>
										<th>Time</th>
										<th>Loc</th>
										<th>Time</th>
										<th>Loc</th>
										</tr>
										</thead>
									</table>
							</div>
				       
		
			    <div class="col-md-12 row form-group" style="display: none;" id="divPrint4">
					<div class="card-body" >
						<div id="divShow" style="display: block;">
							<div class="watermarked"  id="divwatermark">
									<div id="cont"></div>
									<table id="myTable4" class="table no-margin table-striped  table-hover  table-bordered report_print ">
										<thead>
										<tr>
										  <th colspan="2"  style="font-size: 14px; text-align:center;" >LIST OF CLOSE CONTACTS</th>
										</tr>
										</thead>
										<thead>
										<tr>
										  <th>Date</th>
										  <th>Time</th>
										</tr>
										</thead>
									  </table>
								</div>
							</div>
						</div>
					  
				   </div>
				   
				   <div class="col-md-12 row form-group" style="display: none;" id="divPrint3">
				   	   <div class="card-body" >
						  <div id="divShow" style="display: block;">
							<div class="watermarked" id="divwatermark">
							   <div id="cont"></div>
									<table id="myTable3" class="table no-margin table-striped  table-hover  table-bordered report_print">
										<thead>
											<tr>
												<th style="font-size: 14px; text-align:center;" colspan="3">LIST OF CLOSE CONTACTS AS PER ABOVE INVESTIGATION</th>
											</tr>
										</thead>
										<thead>
										<tr>
											
											<th>Name of close contact</th>
											<th>Date of Exposure</th>
											<th>Remarks</th>
										</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
				</div>
 	 -->
 	 			<div class="col-md-12">
							<table id="h_travel_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">
            <thead style="width: 100%;">
							<tr>
								<th colspan="11"
									style="font-size: 14px; margin-top: 5px; text-align: center;">HISTORY OF RECENT TRAVEL</th>
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
										<td style="width: 20%;">
											<input type="date" id="h_datee1" name="h_datee1"
									class="form-control-sm form-control"  autocomplete="off" min="1899-01-01" max="${date}">

										</td>
										<td style="width: 20%;"><input type="time" id="h_time1" name="h_time1"
									class="form-control-sm form-control"  autocomplete="off" >
										</td>										
										
										
										<td style="width: 20%;"><input type="text" id="h_loc1" name="h_loc1"
									class="form-control-sm form-control"  autocomplete="off">
										</td>
										
										
										
									
											
											
										<td style="width: 10%;">

											<a  class="btn btn-success btn-sm" value="ADD" title="ADD" id="h_travel_add1"
											onclick="h_travel_add_fn(1);"><i class="fa fa-plus"></i></a> 
											
											<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="h_travel_remove1"
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
									style="font-size: 14px; margin-top: 5px; text-align: center;">LIST OF CLOSE CONTACTS</th>
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
										<td style="width: 10%;">
											<input type="date" id="h_datee_expo1" name="h_datee_expo1"
									class="form-control-sm form-control"  autocomplete="off" min="1899-01-01" max="${date}">

										</td>
										<td style="width: 10%;"><input type="text" id="h_name1" name="h_name1"
									class="form-control-sm form-control"  autocomplete="off" >
										</td>										
										
										
										<td style="width: 20%;"><input type="textarea" id="h_remark1" name="h_remark1"
									class="form-control-sm form-control"  autocomplete="off">
										</td>
										
										
										
									
											
											
										<td style="width: 10%;">

											<a  class="btn btn-success btn-sm" value="ADD" title="ADD" id="h_list_add1"
											onclick="h_list_add_fn(1);"><i class="fa fa-plus"></i></a> 
											
											<a style="display: none;" class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE" id="h_list_remove1"
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
 			<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
						 	<label for="text-input" class=" form-control-label">Probable Source of Infection</label>
							 </div>
						<div class="col-md-9">
						<input type="text" id="prob_source_insp" name="prob_source_insp" autocomplete="off" class="form-control-sm form-control"
					placeholder="Enter Probable Source of Infection">
						</div>
						</div>
						</div>
</div>
 			
 			
 					<div class="col-md-12 row form-group" id="env">
					<div class="card-body" >
						<div id="divShow" style="display: block;">
							<div class="watermarked" data-watermark="" id="divwatermark">
								
								<table class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead>
										<tr>
										   <th style="font-size: 14px;  text-align:center;" colspan="3">ENVIRONMENTAL INSPECTION</th>
										</tr>
										<tr>
											<th>Loc1</th> 
											<th>Loc2</th>
											<th>Loc3</th>
										</tr>
									</thead>
									<thead>
									   <tr>						
										 <td> <input type="text" id="loc1" name="en_loc1" placeholder="Enter Location1..." class="form-control" autocomplete="off"> </td>
										 <td> <input type="text" id="loc2" name="en_loc2" placeholder="Enter Location2..." class="form-control" autocomplete="off"> </td>
										 <td> <input type="text" id="loc3" name="en_loc3" placeholder="Enter Location3..." class="form-control" autocomplete="off"> </td>
									  </tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${list}" varStatus="num">
									  <tr>
										<th style="font-size: 14px; text-align: center;">${num.index+1}</th>
										<th id="thAction1"style="font-size: 14px; text-align: center;" width="10%">${item.id}</th>
									  </tr>
									</c:forEach>
									</tbody>
								</table>
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
								<%-- <select id="final_epid_diag" name="final_epid_diag" class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${FinalEpiddiag}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select> --%>
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
					
				
				<div class="card-header-inner" id="aa"> <strong>Action Undertaken</strong></div>
					
		      <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						   <label for="text-input" class=" form-control-label">For the Individuals</label>
						</div>
					    <div class="col-md-8">
					    <input type="text" id="action_for_patient" name="action_for_patient" placeholder="For the Individuals..." class="form-control" autocomplete="off">
							<%-- <select id="action_for_patient" name="action_for_patient" class="form-control-sm form-control">
							<option value="-1">--Select--</option>
								 <c:forEach var="item" items="${Actionunder}" varStatus="num">
									 <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								 </c:forEach>
						    </select> --%>
						 </div>
						  </div>
						  </div>	 
					
				   </div>
				<!-- 
				
					  <div class="col-md-12 row form-group" style=" display: none;" id="close">
				      	<div class="col-md-12">
							<div class="card-body" >
								<div id="divShow" style="display: block;">
									<div class="watermarked" data-watermark="" id="divwatermark">
									<span id="ip"></span>
									<div id="cont"></div>
										<table id="myTable5" class="table table-striped  table-hover  table-bordered">
										  <thead style="background-color: #9c27b0; color: white;">
										    <tr>
											  <th style="font-size: 14px; text-align: center;"colspan="4">For Close Contact</th>
										    </tr>
										   </thead>
										   <thead style="background-color: #9c27b0; color: white;">
										   <tr>
											 <th style="font-size: 14px; text-align: center;">Ser No.</th> 
											 <th style="font-size: 14px; text-align: center;">Name Of Close Contact</th>
											 <th style="font-size: 14px; text-align: center;">Date Of Exposure</th>
											 <th style="font-size: 14px; text-align: center;">Period Of Segregation</th>
										  </tr>
										 </tbody>
									   </table>
								    </div>
							    </div>
						     </div>
					      </div>
                       </div> -->
                       
                   <div class="col-md-12" style=" display: none;"  id="segroom">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Action in the Patient’s Room</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="act_patient_room" name="act_patient_room" autocomplete="off" 
							placeholder="Enter Action in the Patient’s Room..." class="form-control-sm form-control">
						</div>
						</div>
						</div>
						
					  <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Action in the Segregation Room</label>
						</div>
					    <div class="col-md-8">
						    <input type="text" id="act_segration_room" name="act_segration_room" autocomplete="off" 
						    placeholder="Enter Action in the Segregation Room..." class="form-control-sm form-control">
						</div>
					</div>
					</div>
					</div>
					
				      <div class="col-md-12" style="display: none;"  id="certiinfe">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
						  <label for="text-input" class=" form-control-label">Date of Certification of Infection Free</label>
					   </div>
					   <div class="col-md-8">
						 <input type="date" id="date_certi_infec_free" name="date_certi_infec_free" autocomplete="off" class="form-control-sm form-control" min="1899-01-01" max="${date}">
					  </div>
				   </div> 
				   </div>
				   </div>
			<div class="col-md-12 form-group">
							<div class="col-md-2">
								<label for="text-input" class=" form-control-label">Recommendation for the indl:barrack/married accn</label>
							</div>
							<div class="col-md-10">
								<textarea rows="2" cols="250" class="form-control char-counter1"
									placeholder="Enter Recommendation for the Individual..." id="recommendation_indl" name="recommendation_indl"></textarea>
							</div>
						</div>
					<div class="col-md-12 form-group">
							<div class="col-md-2">
								<label for="text-input" class=" form-control-label">Recommendation for the Unit</label>
							</div>
							<div class="col-md-10">
								<textarea rows="2" cols="250" class="form-control char-counter1"
									placeholder="Enter Recommendation for the Unit.." id="recommendation_unit" name="recommendation_unit"></textarea>
							</div>
						</div>
						
				</div>	
		</div>	
			 <div class="form-control card-footer" align="center">			  
             <a href="Search_mnh_inputs_air_get" class="btn btn-danger btn-sm" id="btn_cancl" onClick="javascript:window.close('','_parent','');">  Cancel </a>
	         <input type="submit" id="btn_update" class="btn btn-success btn-sm" value="Update" onclick="return isvalidData();"/> 
	         
         </div>
           </div>
	



</form:form>
<c:url value="getTblDeleteAir" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="${Sho_input_air_editCMD.id}"/> 
</form:form> 
<script>
$(document).ready(function() {
	
	$("#service").val("ARMY");
	chgCategory();
	getRank();
	
	$('#persnl_no1').change(function(){
		chgCategory();
		
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
	
	$('#persnl_no3').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});
	
	$('#category').change(function(){
		if($("#service").val() != "-1" && $("#category").val() != "-1"){
			getRank();
		}
	});

var dis='${Sho_input_air_editCMD.disease}'		
		if(dis == "6"){			
			$("#MIroom1").show();
			$("#med1").hide();
			$("#med2").show();
			$("#hospitalization").show();
			$("#close").show();
			$("#segroom").show();
			$("#certiinfe").show();
			$("#env").hide();
			tableRow5();
			} 

	
	$("#id").val('${Sho_input_air_editCMD.id}');
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	var year = d.getFullYear() +'-';
	$("#case_no").val(year);
	$().getCurDt(datee);
	

	
 
    
	
	if('${Sho_input_air_editCMD.category}' == "OR" && '${Sho_input_air_editCMD.service}' == "ARMY"){
		$("#persnl_no2").val('${Sho_input_air_editCMD.persnl_no}'.substring(0,l-1));
		$("#persnl_no3").val('${Sho_input_air_editCMD.persnl_no}'.substring(l-1,l));
	}else if('${Sho_input_air_editCMD.service}' == "AIRFORCE" || '${Sho_input_air_editCMD.service}' == "NAVY"){
		$("#persnl_no2").val('${Sho_input_air_editCMD.persnl_no}'.substring(0,l-1));
		$("#persnl_no3").val('${Sho_input_air_editCMD.persnl_no}'.substring(l-1,l));
	}
	else if('${Sho_input_air_editCMD.service}' == "ARMY" && '${Sho_input_air_editCMD.category}' != "OR"){
		$("#persnl_no1").val('${Sho_input_air_editCMD.persnl_no}'.substring(0,2));
		$("#persnl_no2").val('${Sho_input_air_editCMD.persnl_no}'.substring(2,l-1));
		$("#persnl_no3").val('${Sho_input_air_editCMD.persnl_no}'.substring(l-1,l));
	}else if('${Sho_input_air_editCMD.service}' == "OTHERS"){
		$("#persnl_no1").val('-1');
		$("#persnl_no2").val('');
		$("#persnl_no3").val('-1');
	}
	
	if('${Sho_input_air_editCMD.service}' == "NAVY" || '${Sho_input_air_editCMD.service}' == "AIRFORCE"){
		$("#persnl_no1").attr('disabled',true);
		$("#category").attr('readonly',false);
	}
	if('${Sho_input_air_editCMD.service}' == "OTHERS"){
		$("#persnl_no1").attr('disabled',true);
		$("#persnl_no2").attr('disabled',true);
		$("#persnl_no3").attr('disabled',true);
		$("#category").attr('readonly',true);
		$("#rank").attr('readonly',true);
		
		$("#category option[value='OFFICER']").hide();
		$("#category option[value='MNS']").hide();
		$("#category option[value='JCO']").hide();
	}
	if('${Sho_input_air_editCMD.service}' == "ARMY"){
		$("#category").attr('readonly',true);
	}
	
	if('${Sho_input_air_editCMD.service}' != "OTHERS"){
		$("#category").val('${Sho_input_air_editCMD.category}');
		getRank();
	}

	$('#service').change(function(){
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
	});
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("&msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
	
	
		
	 $("#id").val('${Sho_input_air_editCMD.id}');
	 	
	 	$("#unit_name1").attr('readonly',true);
	 	$("#sus_no1").attr('readonly',true);
	 	
	 	$("#sus_no1").val('${Sho_input_air_editCMD.sus_no}');
	 	$.post("getMNHUnitNameBySUSNo?"+key+"="+value, {y:'${Sho_input_air_editCMD.sus_no}'},function(j) {
	 		var enc = j[j.length - 1].substring(0, 16);
	 		var a = dec(enc, j[0]);
	 		$("#unit_name1").val(a);
	 	});
	 	$("#disease").attr('readonly',true);
	 	$("#disease").val('${Sho_input_air_editCMD.disease}');
	 	
	 	$("#case_no").val('${Sho_input_air_editCMD.case_no}');
	 	$("#service").val('${Sho_input_air_editCMD.service}');
	 	$("#relationship").val('${Sho_input_air_editCMD.relationship}');
	 	var l = '${Sho_input_air_editCMD.persnl_no}'.length;
	 	$("#persnl_no1").val('${Sho_input_air_editCMD.persnl_no}'.substring(0,2));
	 	$("#persnl_no2").val('${Sho_input_air_editCMD.persnl_no}'.substring(2,l-1));
	 	$("#persnl_no3").val('${Sho_input_air_editCMD.persnl_no}'.substring(l-1,l));
	 	
	 	if('${Sho_input_air_editCMD.service}' != "OTHERS"){
	 		$("#category").val('${Sho_input_air_editCMD.category}');
	 		getRank();
	 	}

	 	$("#personnel_unit").val('${Sho_input_air_editCMD.personnel_unit}');
	 	$("#present_address").val('${Sho_input_air_editCMD.present_address}');
	 	$("#contact_no").val('${Sho_input_air_editCMD.contact_no}');
	 	$("#type_of_symptom").val('${Sho_input_air_editCMD.type_of_symptom}');
	 	$("#history").val('${Sho_input_air_editCMD.history}');
	 	$("#prob_source_insp").val('${Sho_input_air_editCMD.prob_source_insp}');
	 	$("#loc1").val('${Sho_input_air_editCMD.en_loc1}');
	 	$("#loc2").val('${Sho_input_air_editCMD.en_loc2}');
	 	$("#loc3").val('${Sho_input_air_editCMD.en_loc3}');
	 	
	 	$("#final_epid_diag").val('${Sho_input_air_editCMD.final_epid_diag}');
	 	$("#final_epid_diagnosis").val('${Sho_input_air_editCMD.final_epid_diagnosis}');
	 	$("#action_for_patient").val('${Sho_input_air_editCMD.action_for_patient}');
	 	$("#action_by_smc").val('${Sho_input_air_editCMD.action_by_sho}');
	 	$("#action_by_sh").val('${Sho_input_air_editCMD.action_by_sho}');
	 	$("#action_by_cont").val('${Sho_input_air_editCMD.action_by_sho}');
	 	$("#recommendation_indl").val('${Sho_input_air_editCMD.recommendation_indl}');
	 	$("#recommendation_unit").val('${Sho_input_air_editCMD.recommendation_unit}');
	 	
	 	
	 	
	      var d1 = new Date('${Sho_input_air_editCMD.date_onset_symp}');
	 	var d2 = new Date('${Sho_input_air_editCMD.date_repo_med_centre}');
	 	
	 	

	 	var c_d1 = d1.getFullYear()+"-"+("0" + (d1.getMonth()+1)).slice(-2)+"-"+("0" + d1.getDate()).slice(-2);	
	 	$("#date_onset_symp").val(c_d1);
	 	var c_d2 = d2.getFullYear()+"-"+("0" + (d2.getMonth()+1)).slice(-2)+"-"+("0" + d2.getDate()).slice(-2); 
	 	$("#date_repo_med_centre").val(c_d2);
	 	
	 	
	
	
	var d = new Date();
 var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
 
 

 $("#datee").change(function(){
		if($("#datee").val() > c_d){
			$("#datee").focus();
			alert("Can't select the Future Date");
			$("#datee").val('');
			return false;
		}
	});

 $("#date_repo_med_centre").change(function(){
		 if($("#date_repo_med_centre").val() > c_d){
			$("#date_repo_med_centre").focus();
			alert("Can't select the Future Date");
			$("#date_repo_med_centre").val('');
			return false;
		}
	});
 var date_of_birth = '${Sho_input_air_editCMD.date_of_birth}';
	date_of_birth= date_of_birth.substring(0,10);
	
	$("#date_of_birth1").val(date_of_birth);
	calculate_age_edit('${Sho_input_air_editCMD.date_of_birth}');
 
	gettravelDetails();
	getlistDetails();
	
});
////
 var d = new Date();
 var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);

 $("#datee").change(function(){
		if($("#datee").val() > c_d){
			$("#datee").focus();
			alert("Can't select the Future Date");
			$("#datee").val('');
			return false;
		}
	});

 $("#date_repo_med_centre").change(function(){
		 if($("#date_repo_med_centre").val() > c_d){
			$("#date_repo_med_centre").focus();
			alert("Can't select the Future Date");
			$("#date_repo_med_centre").val('');
			return false;
		}
	});












</script>

<script>
function isvalidData(){

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
	if($("#service").val() == "NAVY" && $("#service").val() == "AIRFORCE"){
		if ($("#persnl_no2").val() == "" && $("#persnl_no3").val() == "-1") {
			alert("Please Enter the Personnel No");
			$("#persnl_no2").focus();
			$("#persnl_no3").focus();
	 		return false;
		}
	}
		 var adhar_no = ($("#adhar_card_no1").val());
 		 
	 		if (!adhar_no == "" && adhar_no != "0" && adhar_no != null  && adhar_no.length < 12  )
	 		{
	 		alert("Adhaar No Should Contain Maximum 12 Characters");
	 		return false;
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
	if ($("#category").val() == "-1" && $("#service").val() != "OTHERS") {
		alert("Please Select the Category");
		$("#category").focus();
		return false;
	}
	 if ($("#rank").val() == "-1" && $("#service").val() != "OTHERS") {
		alert("Please Select the Rank");
		$("#rank").focus();
		return false;
	}
	 if ($("#relationship").val() == "-1" ) {
			alert("Please Select the Relationship");
			$("#relationship").focus();
			return false;
		}
	 return true;
	 }

function btn_clc(){
	location.reload(true);
}

///////////////////////////////

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
	
function validate(){
	var age = +document.getElementById('age').value;
	if ( !( age >= 17 ) ){
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


$("#diagnosis").keyup(function(){
	var d1 = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis,{a:d1,b:"ALL"},'','');
});	

$("#relationship").keyup(function(){
	var d1 = this.value;
	$().Autocomplete2('GET','getMedrelationList',relationship,{a:d1,b:"ALL"},'','');
});	
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
	$("#rank").attr('readonly',false);
	$.post("getMNHRank?"+key+"="+value, {a1 : category,a2 : service},function(j) {
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
			options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[0] + '</option>';
		}
		$("#rank").html(options);
		var q = '${Sho_input_air_editCMD.eir_rank.id}';
		if(q != ""){
			$("#rank").val(q);
		}
	});
}
</script>

<script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 250
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();
</script>

  <script>
/////////////////////edit list 08-10-2021////////////////////////
	
  aller=1;
 
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
  

		
function getlistDetails() {
			
    var p_id = '${Sho_input_air_editCMD.id}';
    var i = 0;
	$.post('getmnh_listData?' + key + "=" + value,{p_id : p_id },
	  function(j) {
					var v = j.length;
					$("#h_list_tbody").empty();
						if (v != 0) {
                         for (i; i < v; i++) {
							x = i + 1;
                         $("table#h_list_table")
			.append('<tr align="center" id="tr_h_list'+x+'"><td style="width: 2%;">'
							+ x
							+ '</td>'
							+ '<td><input type="date" id="h_datee_expo'+x+'" name="h_datee_expo'+x+'" class="form-control autocomplete" autocomplete="off" >  </td>'
							+ '<td > <input type="text" name="h_name' + x + '" id="h_name' + x + '" class="form-control autocomplete"  autocomplete="off" ></td>'
							+ '<td><input type="text" ' + '	id="h_remark' + x + '" name="h_remark' + x + '" ' + '	class="form-control"  autocomplete="off" ></td>'
							+ '<td style="display: none;"><input type="text" id="list_id'+x+'" name="list_id'+x+'" class="form-control autocomplete" autocomplete="off"></td>'
							+ '<td style="width: 10%;"><a style="display: none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "h_list_add'
							+ x
							+ '" onclick="h_list_add_fn('
							+ x
							+ ');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "h_list_remove'
							+ x
							+ '" onclick="h_list_remove_fn('
							+ x
							+ ');"><i class="fa fa-trash"></i></a></td>'
							+ '</tr>');

						$('#h_datee_expo' + x).val(formateDate(j[i].exposure_date));
						$('#h_name' + x).val(j[i].name_of_close_contact);
						$('#h_remark' + x).val(j[i].remarks);

						$('#list_id' + x).val(j[i].id);

					}
                         aller = v;
									$("input#h_list_count").val(v);
									$("input#h_list_Old_count").val(v);
									$("#h_list_add" + v).show();

								} else {
									h_list_add_fn(0);
									$("input#h_list_count").val('1');
									$("input#h_list_Old_count").val('0');
								}
							});
		}
		function h_list_remove_fn(R){
		  	 
		  	 var rc = confirm("Are You Sure! You Want To Delete");
		  		if(rc) {
		  			var list_id = $('#list_id' + R).val();
		  			if(list_id == 0){
		  				 $("tr#tr_h_list"+R).remove();	
		  				 aller=aller-1
		  				 $("input#h_list_count").val(aller);
		  				 $("#h_list_add"+aller).show();
		  				 if(aller!=1){
		  				 $("#h_list_remove"+aller).show();
		  				 }
		  			}else{
		  			$.post('h_list_delete_action_jco?' + key + "=" + value, {
		  				list_id: list_id
		  			}, function(data) {
		  				if(data == '1') {
		  					$("tr#tr_h_list" + R).remove();
		  					if(R == aller) {
		  						R = R - 1;
		  						var temp = true;
		  						for(i = R; i >= 1; i--) {
		  							if($('#h_list_add' + i).length) {
		  								$("#h_list_add" + i).show();
		  								temp = false;
		  								aller = i;
		  								break;
		  							}
		  						}
		  						if(temp) {
		  							h_list_add_fn(0);
		  						}
		  						
		  					}
		  					$('.h_list_srno').each(function(i, obj) {
		  						obj.innerHTML = i + 1;
		  						h_list_srno = i + 1;
		  					});
		  				
		  					
		  					getlistDetails();
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
	</script> 

<script>
////////////////////Edit Travel of history////////////////////////

travel=1;
function h_travel_add_fn(q){

	if(q!=0){
	  	$("#h_travel_add"+travel).hide();
	  	$("#h_travel_remove"+travel).hide();
	  	}
	  	travel=q+1;
	 
	 
	  	 $("input#h_travel_count").val(travel);
	  	

	  	$("table#h_travel_table").append('<tr align="center" id="tr_h_travel'+travel+'"><td style="width: 2%;">'+travel+'</td>'
	  	+'<td><input type="date" id="h_datee'+travel+'" name="h_datee'+travel+'" class="form-control autocomplete" autocomplete="off">  </td>'
	+ '<td > <input type="time" name="h_time' + travel + '" id="h_time' + travel + '" class="form-control autocomplete" >' + '</td>'
	  +'<td > <input type="text" name="h_loc'+travel+'" id="h_loc'+travel+'" class="form-control-sm form-control"   >'
	  +'<td style="display: none;"><input type="text" id="travel_id'+travel+'" name="travel_id'+travel+'" value="0" class="form-control autocomplete" autocomplete="off"></td>'
	  +'<td ><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "h_travel_add'+travel+'" onclick="h_travel_add_fn('+travel+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "h_travel_remove'+travel+'" onclick="h_travel_remove_fn('+travel+');"><i class="fa fa-trash"></i></a></td>' 
	      +'</tr>');
	
	
		 if(travel==1){
			 $("#h_travel_remove"+aller).hide();
		 }	

}

 function gettravelDetails(){
 
 	var p_id='${Sho_input_air_editCMD.id}';	
 	
 	 var i=0;
 	  $.post('getmnh_historyData?' + key + "=" + value, {p_id:p_id }, function(j){
 			var v=j.length;
 			$("#h_travel_tbody").empty();
 		if(v!=0){
 			
 			for(i;i<v;i++){			
 				x=i+1;	
 				
 			$("table#h_travel_table").append('<tr align="center" id="tr_h_travel'+x+'"><td style="width: 2%;">'+x+'</td>'
 				  	+'<td><input type="date" id="h_datee'+x+'" name="h_datee'+x+'" class="form-control autocomplete" autocomplete="off" >  </td>'
 				  	+ '<td > <input type="time" name="h_time' + x + '" id="h_time' + x + '" maxlength="10"  class="form-control autocomplete"  autocomplete="off" ></td>'
 				    + '<td><input type="text" ' + '	id="h_loc' + x + '" name="h_loc' + x + '" ' + '	class="form-control"  autocomplete="off" ></td>'
 				  	+'<td style="display: none;"><input type="text" id="travel_id'+x+'" name="travel_id'+x+'" class="form-control autocomplete" autocomplete="off"></td>'
 				    	+'<td style="width: 10%;"><a style="display: none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "h_travel_add'+x+'" onclick="h_travel_add_fn('+x+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "h_travel_remove'+x+'" onclick="h_travel_remove_fn('+x+');"><i class="fa fa-trash"></i></a></td>' 
 				        +'</tr>');		
 			
 		
 		
 			$('#h_datee'+x).val(formateDate(j[i].history_date));	
 			$('#h_time' + x).val(j[i].history_time);
 			$('#h_loc' + x).val(j[i].history_loc);
 			
 			$('#travel_id'+x).val(j[i].id);		 
 			
 			
 			
 		}
 			travel=v;
 			$("input#h_travel_count").val(v);
 			$("input#h_travel_Old_count").val(v);
 			$("#h_travel_add"+v).show(); 

 			}
 		else{
 			h_travel_add_fn(0);
 			$("input#h_travel_count").val('1');
 			$("input#h_travel_Old_count").val('0');
 		}		
 	  }); 
 }
function h_travel_remove_fn(R){
	  	 
	  	 var rc = confirm("Are You Sure! You Want To Delete");
	  		if(rc) {
	  			var travel_id = $('#travel_id' + R).val();
	  			if(travel_id == 0){
	  				 $("tr#tr_h_travel"+R).remove();	
	  				 aller=aller-1
	  				 $("input#h_travel_count").val(aller);
	  				 $("#h_travel_add"+aller).show();
	  				 if(aller!=1){
	  				 $("#h_travel_remove"+aller).show();
	  				 }
	  			}else{
	  			$.post('h_travel_delete_action_jco?' + key + "=" + value, {
	  				travel_id: travel_id
	  			}, function(data) {
	  				if(data == '1') {
	  					$("tr#tr_h_travel" + R).remove();
	  					if(R == aller) {
	  						R = R - 1;
	  						var temp = true;
	  						for(i = R; i >= 1; i--) {
	  							if($('#h_travel_add' + i).length) {
	  								$("#h_travel_add" + i).show();
	  								temp = false;
	  								aller = i;
	  								break;
	  							}
	  						}
	  						if(temp) {
	  							h_travel_add_fn(0);
	  						}
	  						
	  					}
	  					$('.h_travel__srno').each(function(i, obj) {
	  						obj.innerHTML = i + 1;
	  						h_travel__srno = i + 1;
	  					});
	  				
	  					
	  					gettravelDetails();
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
function formateDate(value){
    var date = new Date(value);
    var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
    return formattedDate;
}


function rank_change(obj){

    $("#hid_rank").val(obj.value);
}



	  </script>
 