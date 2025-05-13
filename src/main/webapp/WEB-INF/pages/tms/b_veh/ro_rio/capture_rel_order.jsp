<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form id="capture_rel_order" name="capture_rel_order"   method='POST' commandName="capture_rel_orderCMD"> <!-- action="capture_rel_orderAction?${_csrf.parameterName}=${_csrf.token}" -->
	<div class="animated fadeIn">
		<div>
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5>CREATE RELEASE ORDER<br></h5><span style="font-size: 12px;color:red">(To be entered by MGO EM/IM)</span></div>
					<div class="card-header"><strong>Basic Details </strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
						<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">*</strong>Type of Vehicle </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="type_of_vehicle" id="type_of_vehicle"
											class="form-control-sm form-control" readonly = "readonly">
											<option value="0">--Select--</option>
											<option value="A">A Vehicle</option>
											<option value="B">B Vehicle</option>
											<option value="C">C Vehicle</option>
										</select>
										 <input type="hidden" id="type_of_vehicle_hidden" name="type_of_vehicle_hidden">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Type of RO 
										</label>
									</div>
									<!-- priti 25-08-2020 -->
									<div class="col-md-8">
										<select name="type_of_ro" id="type_of_ro" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											<option value="1" name="S">RO</option>
											<option value="3" name="L">Loan RO</option>
											<option value="4" name="N">NRU RO</option>
											<option value="5" name="P">Conditional RO</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>RO No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="ro_no" name="ro_no" class="form-control form-control-sm autocomplete" value="" autocomplete="off" maxlength="50">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div id ="last_updt" class="col-md-4">
								<label style="color: green;font-size: 12px;">MVCR Last Uptd </label>
								</div>
								<div class="col-md-8">
									<span id="mvcr_last_update" style="color: green;font-size: 12px;"></span>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No 
										</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
									</div>
									<div class="col-md-8">
										<textarea id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Comd SUS No</label>
									</div>
									<div class="col-12 col-md-8">
									
										<input type="text" id="command_sus_no" name="command_sus_no" maxlength="8" placeholder="" readonly="readonly" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Comd Name </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea id="comd_name" name="comd_name" readonly="readonly" maxlength="100" class="form-control autocomplete" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Corp SUS No</label>
									</div>
									<div class="col-12 col-md-8">
									
										<input type="text" id="corp_sus_no" name="corp_sus_no" maxlength="8" placeholder="" readonly="readonly" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Corp Name </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea id="corp_name" name="corp_name" readonly="readonly" maxlength="100" class="form-control autocomplete" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Depot SUS No 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="depot_sus_no" name="depot_sus_no" maxlength="8" class="form-control autocomplete" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Depot Name </label>
									</div>
									<div class="col-12 col-md-8">
									   <textarea id="depot_name" name="depot_name" maxlength="100" class="form-control autocomplete" autocomplete="off"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"> NRS </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="nrs" name="nrs" readonly="readonly" class="form-control" autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Type of Issue </label>
									</div>
									
									<!-- priti 25-08-2020 -->
									<div class="col-12 col-md-8">
										<select name="type_of_issue" id="type_of_issue" class="form-control-sm form-control">
											<option value="">--Select--</option>	
											<c:forEach var="item" items="${getMvcrpartCissuetypeList}" varStatus="num" >
												<c:choose>
												    <c:when test="${item[0] =='3'}">
												       <option value="${item[0]}" style="color:red">${item[1]}</option>
												    </c:when>    
												    <c:when test="${item[0] =='5'}">
												       <option value="${item[0]}" selected="selected">${item[1]}</option>
												    </c:when>  
												    <c:otherwise>
												       <option value="${item[0]}" >${item[1]}</option>
												    </c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Issue Stock
										</label>
									</div>
									<div class="col col-md-8">
										<div class="col-md-1">
											<input type="radio" id="inline-radio1" name="issue_stock" value="free_stock" class=" form-control form-check-input" >
										</div>
										<div class="col-md-5">
											<label class=" form-control-label"> Free Stock</label>
										</div>
										<div class="col-md-1">
											<input type="radio" id="inline-radio2" name="issue_stock" value="ex_wwr" class="form-control form-check-input" >
										</div>
										<div class="col-md-5">
											<label class=" form-control-label"> &emsp;Ex WWR</label>
										</div> 
									</div>
								</div>
							</div>
						<!-- 	<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Type of Vehicle
										</label>
									</div>
									<div class="col-12 col-md-8">
										<select name="type_of_vehicle" id="type_of_vehicle" class="form-control-sm form-control">
											<option value="0">--Select--</option>
											 <option value="A">A Vehicle</option>
											<option value="B">B Vehicle</option>
											 <option value="C">C Vehicle</option>
										</select>
									</div>
								</div>
							</div> -->
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Issue Date
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="issue_dateLb" name="issue_dateLb" class="form-control" readonly="readonly">
										<input type="hidden" id="issue_date" name="issue_date" class="form-control" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Under SD Relief </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sdR" name="sdR" readonly="readonly" class="form-control" autocomplete="off" maxlength="100">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" id="get_other_issue_type" style="display: none;">
									<div class="col col-md-4">
										<label class=" form-control-label">Other Issue Type </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="other_issue_type" name="other_issue_type" placeholder="" class="form-control" autocomplete="off" maxlength="50">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header" id="get_loan" style="border: 1px solid rgba(0, 0, 0, .125); display: none;">
							<strong>Loan </strong>
					</div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" id="get_loan_auth" style="display: none;">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Loan Authority </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="loan_auth" name="loan_auth" placeholder="" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" id="get_loan_duration" style="display: none;">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Loan Duration (years) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="number" id="loan_duration" name="loan_duration" placeholder="" onkeypress="return validateNumber(event,this);" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
					</div>
				<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);">
					<strong>Vehicle Issue Details </strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>MCT</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="mct" name="mct" placeholder="Enter MCT No" class="form-control autocomplete" autocomplete="off" onkeyup="return checkVehicleType();" maxlength="10">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Nomenclature </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="std_nomclature" name="std_nomclature" class="form-control autocomplete" autocomplete="off" onkeyup="return checkVehicleType();" maxlength="100">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">UE</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="ue" name="ue" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">UH</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="uh" name="uh" placeholder="" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">PRF UE</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="prf_ue" name="prf_ue" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">PRF UH</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="prf_uh" name="prf_uh"  class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">In Lieu MCT No.</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="inliuemct" name="inliuemct" class="form-control autocomplete" autocomplete="off" maxlength="10">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> Nomenclature </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="std_nomclature_inlieu" name="std_nomclature_inlieu" placeholder="" class="form-control" autocomplete="off" maxlength="100">
								</div>
							</div>

						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Qty</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="quantity" name="quantity" onkeypress="return validateNumber(event,this);" class="form-control" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Accounting</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="accounting" id="accounting" class="form-control-sm form-control">
										<option value="peace">Peace</option>
										<option value="war">War</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Issue Precedence</label>
								</div>
								<!-- priti 25-08-2020 -->
								<div class="col-12 col-md-8">
									<select name="issue_precedence" id="issue_precedence" class="form-control-sm form-control">
										<option value="normal">Normal</option>
										<!-- <option value="routine">Routine</option> -->
										<option value="priority">Priority</option>
									<!-- 	<option value="op_immidiates">OP Immidiates</option>
										<option value="flush">Flush</option> -->
										<option value="emergency">Emergency</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> <strong	style="color: red;">*</strong>Remarks</label>
								</div>
								<div class="col-12 col-md-8">
									<textarea class="form-control" id="remarks" name="remarks" maxlength="255"></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
				</div>
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<input type="button" class="btn btn-primary btn-sm" value="Save" id="save_per" onclick="return validate();"> 
				</div>
				<input type="hidden" id="count" name="count"> 
				<div class="card-body card-block" style="overflow: auto;" id="bveh">
					<div class="col-md-12">  
						<table class="table no-margin table-striped table-hover table-bordered" id="add_reports">
							<thead>
								<tr>
									<th style="text-align: center;width: 10%;">Ser No</th>
									<th style="display: none;">Type of RO</th>
									<th style="text-align: center;width: 10%;"">RO NO</th>
									<th style="text-align: center;width: 10%;"">SUS No</th>
									<th style="text-align: center;width: 30%;"">Unit Name</th>
									<th style="display: none;">Command SUS No</th>
									<th style="display: none;">Command Name</th>
									<th style="display: none;">Depot SUS No</th>
									<th style="display: none;">Depot Name</th>
									<th style="display: none;">NRS</th>
									<th style="display: none;">Type of Issue</th>
									<th style="display: none;">Issue Stock</th>
									<th style="display: none;">Type Of Vehicle</th>
									<th style="display: none;">Issue Date</th>
									<th style="display: none;">Loan Auth</th>
									<th style="display: none;">Loan Duration</th>
									<th style="display: none;">MCT</th>
									<th style="text-align: center;width: 30%;"">Nomenclature</th>
									<th style="display: none;">UE</th>
									<th style="display: none;">UH</th>
									<th style="display: none;">PRF UE</th>
									<th style="display: none;">PRF UH</th>
									<th style="display: none;">Inlieu MCT</th>
									<th style="display: none;">Inlieu Nomenclature</th>
									<th style="text-align: center;width: 10%;"">Qty</th>
									<th style="display: none;">Accounting</th>
									<th style="display: none;">Issue Pre</th>
									<th style="display: none;">Remark</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer" align="center">
						<!-- <a href="capture_rel_order" class="btn btn-primary btn-sm">Refresh</a> -->
						<%      String vehicleType = ""; 
                               String jsVehicleType = request.getParameter("type_of_vehicle");
    
                      if (jsVehicleType != null && !jsVehicleType.isEmpty()) { vehicleType = jsVehicleType; }
                                         String href = "";
                         if (vehicleType.equals("A")) {href = "acapture_rel_ord";}
                         else if (vehicleType.equals("B")) {href = "bcapture_rel_ord";}
                         else if (vehicleType.equals("C")) {href = "ccapture_rel_ord";} %>
                         <a href="<%= href %>" class="btn btn-primary btn-sm">Refresh</a>

					</div> 
			</div>
		</div>
	</div>
</div>
</form:form>

<script>
$(document).ready(function() {
	$("#type_of_vehicle").val('${veh}');
	
var veh_type = 	$("#type_of_vehicle").val();	
if (veh_type == 'A') {
    var labelElement = document.getElementById("last_updt").querySelector("label");
    if (labelElement) {
        labelElement.textContent = "FMCR Last Uptd";
    }
}
if (veh_type == 'C') {
    var labelElement = document.getElementById("last_updt").querySelector("label");
    if (labelElement) {
        labelElement.textContent = "EMAR Last Uptd";
    }
}

	
});
$(function() {
	$("#type_of_issue").change(function(){
		var type_of_issue = this.value;
		if(type_of_issue =='3'){
			$("#type_of_issue option[value=3]").css('color', 'red');
			document.getElementById("type_of_issue").style.borderColor = "red";
            document.getElementById("type_of_issue").style.borderWidth = "2px";
         
            
            
		}else{
			document.getElementById("type_of_issue").style.borderColor = "#e9ecef";
			document.getElementById("type_of_issue").style.borderWidth = "2px";
		}
	});
	

	
	var d = new Date();
    $("#issue_date").val(d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate());
    $("#issue_dateLb").val(d.getDate()+'-'+(d.getMonth()+1)+'-'+d.getFullYear());
	
	// Source SUS No

		$("#sus_no").keyup(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
			        	  if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			     }, 
			     select: function( event, ui ) {
		    	 	var sus_no = ui.item.value;
		    	
			            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
			        		var enc = data[length].substring(0,16);
			        		$("#unit_name").val(dec(enc,data[0]));	
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			          
			        
			            $.post("getComdSusNoFromSus?"+key+"="+value,{sus_no:ui.item.value}, function(data1) {
			            }).done(function(data1) {
			            	var length = data1.length-1;
				        	var enc = "";
				        	if(data1.length != 0){
				        	  enc = data1[length].substring(0,16);
					        }
				        	$("#command_sus_no").val(dec(enc,data1[0]));
				      
					        
					        $.post("getUnitNameFromSus?"+key+"="+value,{sus_no:dec(enc,data1[0])}, function(data1) {
				            }).done(function(data1) {
				            	var length = data1.length-1;
						        var enc = data1[length].substring(0,16);
						        $("#comd_name").val(dec(enc,data1[0]));
						      
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
					        
					        
				        	 $.post("getCorpSusNoFromSus?"+key+"="+value,{sus_no:ui.item.value}, function(data2) {
					            }).done(function(data2) {
					            	var length = data2.length-1;
						        	var enc = "";
						        	if(data2.length != 0){
						        	  enc = data2[length].substring(0,16);
							        }
						        	$("#corp_sus_no").val(dec(enc,data2[0]));
						        	
				   	
						        	 
				            $.post("getUnitNameFromSus?"+key+"="+value,{sus_no:dec(enc,data2[0])}, function(data2) {
				            }).done(function(data2) {
				            	var length = data2.length-1;
						        var enc = data2[length].substring(0,16);
						        
						        $("#corp_name").val(dec(enc,data2[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
		  			     
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
  			     
			        
			            $.post("getNRSFromSus?"+key+"="+value,{sus_no:ui.item.value}, function(data3) {
			            }).done(function(data3) {
			         		
			               	  var length = data3.length-1;
					          var enc = data3[length].substring(0,16);
					          $("#nrs").val(dec(enc,data3[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			            
			            $.post("getsdReliefFromSus?" + key + "=" + value, { sus_no:sus_no }, function(sdReliefData) {
			                }).done(function(sdReliefData) {
			                	console.log(sdReliefData.length);
			                	if (sdReliefData.length > 1){
			                    var sdReliefLength = sdReliefData.length - 1;
			                    var encSd = sdReliefData[sdReliefLength].substring(0, 16);
			                    var yes_no = dec(encSd, sdReliefData[0]);
			                    	$("#sdR").val(yes_no);
			                	}else {
			                		$("#sdR").val("NO");
			                	}
			                }).fail(function(xhr, textStatus, errorThrown) {
			                    console.error("Failed to get SD Relief:", errorThrown);
			                });
			            
			            $.post("mvcr_last_updateDate?"+key+"="+value,{sus_no:sus_no}, function(data) {
			            }).done(function(data) {
			         		
			            	var d = new Date(data[0]);
						    var date=d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear();
						    $("span#mvcr_last_update").text(date);	
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
				} 	     
			});	
		});
	// End
	// Source Unit Name
	  $("#unit_name").keyup(function(){
	
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
 				        	 if(data.length != 0){
 				        		var enc = data[length].substring(0,16);
 				        	}
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
 				    	 var target_unit_name = ui.item.value;
 				    
 				            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
 				            }).done(function(data) {
 				       		   
 				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#sus_no").val(dec(enc,data[0]));
					        	
					      
		 				            $.post("mvcr_last_updateDate?"+key+"="+value,{sus_no:dec(enc,data[0])}, function(data) {
		 				            }).done(function(data) {
		 				            	var d = new Date(data[0]);
			  						    var date=d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear();
			  						  $("span#mvcr_last_update").text(date);	
		 				            }).fail(function(xhr, textStatus, errorThrown) {
		 				            });
		 				            
		 				            
		 				       
			 				            $.post("getComdSusNoFromSus?"+key+"="+value,{sus_no:dec(enc,data[0])}, function(data1) {
			 				            }).done(function(data1) {
			 				            	var length = data1.length-1;
					 			        	var enc = data1[length].substring(0,16);
					 			        	$("#command_sus_no").val(dec(enc,data1[0]));		 	           			
					 			   
				 				            
					 			        	  $.post("getUnitNameFromSus?"+key+"="+value,{sus_no:dec(enc,data1[0])}, function(data1) {
									            }).done(function(data1) {
									            	var length = data1.length-1;
											        var enc = data1[length].substring(0,16);
											        $("#comd_name").val(dec(enc,data1[0]));
											      
									            }).fail(function(xhr, textStatus, errorThrown) {
									            });
					 			        	
			 				            }).fail(function(xhr, textStatus, errorThrown) {
			 				            });
			 				            
			 				            
			 				           $.post("getCorpSusNoFromSus?"+key+"="+value,{sus_no:dec(enc,data[0])}, function(data1) {
			 				            }).done(function(data1) {
			 				            	var length = data1.length-1;
					 			        	var enc = data1[length].substring(0,16);
					 			        	$("#corp_sus_no").val(dec(enc,data1[0]));		 	           			
					 			   
				 				            
					 			        	  $.post("getUnitNameFromSus?"+key+"="+value,{sus_no:dec(enc,data1[0])}, function(data1) {
									            }).done(function(data1) {
									            	var length = data1.length-1;
											        var enc = data1[length].substring(0,16);
											        $("#corp_name").val(dec(enc,data1[0]));
											      
									            }).fail(function(xhr, textStatus, errorThrown) {
									            });
					 			        	
			 				            }).fail(function(xhr, textStatus, errorThrown) {
			 				            });
			 				        
				 				            $.post("getNRSFromSus?"+key+"="+value,{sus_no:dec(enc,data[0])}, function(data3) {
				 				            }).done(function(data3) {
				 				            	 var length = data3.length-1;
						  				          var enc = data3[length].substring(0,16);
						  				          $("#nrs").val(dec(enc,data3[0]));
				 				            }).fail(function(xhr, textStatus, errorThrown) {
				 				            });
				 				            
				 				           // Call the getSDReliefFromSus API to fetch sd_relief
				 			                $.post("getsdReliefFromSus?" + key + "=" + value, { sus_no:dec(enc,data[0]) }, function(sdReliefData) {
				 			                }).done(function(sdReliefData) {
				 			                	console.log(sdReliefData.length);
				 			                	if (sdReliefData.length > 1){
				 			                    var sdReliefLength = sdReliefData.length - 1;
				 			                    var encSd = sdReliefData[sdReliefLength].substring(0, 16);
				 			                    var yes_no = dec(encSd, sdReliefData[0]);
				 			                    	$("#sdR").val(yes_no);
				 			                	}else {
				 			                		$("#sdR").val("NO");
				 			                	}
				 			                }).fail(function(xhr, textStatus, errorThrown) {
				 			                    console.error("Failed to get SD Relief:", errorThrown);
				 			                });
				 				            
 				            }).fail(function(xhr, textStatus, errorThrown) {
 				            });
 				      } 	     
 				}); 			
 		});
});
</script>

<script>
$(function() {
	try{
   		if(window.location.href.includes("msg="))
   		{
   			var url = window.location.href.split("?msg")[0];
   			window.location = url;
   		}
   	}
   	catch (e) {
   	}
});
</script>

<script>
$(function() {
		// Source SUS No
	
		$("#depot_sus_no").keypress(function(){
			var sus_no = this.value;
			var susNoAuto=$("#depot_sus_no");
			susNoAuto.autocomplete({
			 	source: function( request, response ) {
			       	$.ajax({
			       		type: 'POST',
				    	url: "getDepotSUSNoDetailList?"+key+"="+value,
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
			        	alert("Please Enter Approved Depot SUS No.");
			        	document.getElementById("depot_name").value="";
			        	susNoAuto.val("");	        	  
			        	susNoAuto.focus();
			        	return false;	             
					}   	         
				}, 
				select: function( event, ui ) {
			    	var sus_no = ui.item.value;
			    
				            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#depot_name").val(dec(enc,data[0]));	
			  	   	        	  	   	        		
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
				} 	     
			});	
		});
	
	// Source Unit Name
			$("#depot_name").keypress(function(){
			var unit_name = this.value;
			var susNoAuto=$("#depot_name");
			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getDepotNameDetailList?"+key+"="+value,
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
			        	  document.getElementById("depot_sus_no").value="";
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
						        	$("#depot_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
		});
});
</script>


<script>
 function getPrfUE_UH(mct){
		var sus_no = $("#sus_no").val();
	 	if(sus_no == ""){
			alert("Please Select Sus No you will get UE/UH.");
			$("#sus_no").focus();
		} 

	$.post("getUEFromMCT?"+key+"="+value,{mct : mct,sus_no:sus_no}, function(data) { }).done(function(data) {
		if(data.length == 0){
			$("#ue").val(0);
		}else{
			var length = data.length-1;
	  		var enc = data[length].substring(0,16);
	  		$("#ue").val(dec(enc,data[0]));
		}
	}).fail(function(xhr, textStatus, errorThrown) {});

	$.post("getUHFromMCT?"+key+"="+value, { mct : mct, sus_no : sus_no}, function(data1) { }).done(function(data1) {
       	var length = data1.length-1;
        var enc = data1[length].substring(0,16);
        	
        if(dec(enc,data1[0]) == ""){
        	$("#uh").val(0);
        }else{
        	$("#uh").val(dec(enc,data1[0]));
        }
    }).fail(function(xhr, textStatus, errorThrown) {});

	$.post("getPrfUEFromMCT?"+key+"="+value, {mct : mct, sus_no : sus_no}, function(data2) { }).done(function(data2) {
           var length = data2.length-1;
	    	var enc = data2[length].substring(0,16);
	    	if(dec(enc,data2[0]) == ""){
	    		$("#prf_ue").val(0);
	    	}else{
	    		$("#prf_ue").val(dec(enc,data2[0]));
	    	}
       	 	    
    }).fail(function(xhr, textStatus, errorThrown) {
    });

        $.post("getPrfUHFromMCT?"+key+"="+value, {mct : mct, sus_no : sus_no}, function(data3) {

    }).done(function(data3) {
           
        	var length = data3.length-1;
	    	var enc = data3[length].substring(0,16);
	    	
	    	if(dec(enc,data3[0]) == ""){
	    		$("#prf_uh").val(0);
	    	}else{
	    		$("#prf_uh").val(dec(enc,data3[0]));
	    	}    
    }).fail(function(xhr, textStatus, errorThrown) {
    });
 }
</script>

<script>	

$('#type_of_vehicle').change(function() {
    $("#mct").val("");
    $("#std_nomclature").val("");
    $("#ue").val("");
    $("#uh").val("");
    $("#prf_ue").val("");
    $("#prf_uh").val("");
    $("#inliuemct").val("");
    $("#std_nomclature_inlieu").val("");
 });

$("#type_of_issue").change(function(){      
   if($(this).val()=="other")
   {    
	   $("label#labelcon").text("Other Issue Type");
       $("div#get_other_issue_type").show();
   }
   else
   {    
	  $("div#get_other_issue_type").hide();
   }
});

$("#type_of_ro").change(function(){
	
	var d = new Date();
	var year = d.getFullYear();
	var type_of_ro = $("#type_of_ro").find('option:selected').attr("name");  
	if(type_of_ro != undefined){
		var roNo = year+"-"+type_of_ro+"-";
		$.post("generateRONo?"+key+"="+value,{roNo:roNo}, function(j) {
			$("#ro_no").val(j[0]);
		});
	}else{
		$("#ro_no").val("");
	}
	
	if($(this).val()=="3")
	{    
		$("label#labelcon").text("Loan Authority");
		$("div#get_loan_auth").show();
       	$("label#labelcon").text("Loan Duration (years)");
       	$("div#get_loan_duration").show();
       	$("label#labelcon").text("Loan");
      	$("div#get_loan").show();      
	}
	else
	{    
		$("div#get_loan_auth").hide();
		$("div#get_loan_duration").hide();
		$("div#get_loan").hide();
   	}
});
</script>

<!-- //////////////////////////////////// Validation code ///////////////////////////////////////////////////////////-->
<script>

var freestock = 0;
function getFreeStock(mct,depot_sus_no){
	var type_of_vehicle = $("#type_of_vehicle").val();	
	$.post("getFreeStockBANo_and_ro_rio_pending_qty?"+key+"="+value, {mct:mct,d_sus_no:depot_sus_no,type_of_vehicle:type_of_vehicle}).done(function(k) {
		freestock = k;
	}).fail(function(xhr, textStatus, errorThrown){});
}



var max_fields = 100; //maximum input boxes allowed
var x = 0; //initlal text box count

var mctArry ="";

function validate(){
	//if(!confirm("free stock is consist of "+freestock+" from "+$("#quantity").val()+" Qty \n Do you want proceed further?")){
	if(!confirm(freestock+" Vehs available in free stock \n Do you want proceed further?")){
		return false;
	}
	if($("Select#type_of_ro").val() == "0"){
		alert("Please Select the Type of Release Order.");
		$("Select#type_of_ro").focus();
		return false;
	}
	if($("Select#ro_no").val() == ""){
		alert("Please Select the Release Order NO.");
		$("Select#ro_no").focus();
		return false;
	}
	if($("#sus_no").val() == ""){
		alert("Please Enter Unit SUS No.");
		$("#sus_no").focus();
		return false;
    }
	if($("#unit_name").val() == ""){
		alert("Please Enter Unit Name");
		$("#unit_name").focus();
		return false;
    }
	if($("#depot_sus_no").val() == ""){
		alert("Please Enter Depot SUS No.");
		$("#depot_sus_no").focus();
		return false;
    }	
	if($("#depot_name").val() == ""){
		alert("Please Enter Depot Name.");
		$("#depot_name").focus();
		return false;
    }
	if($("Select#type_of_issue").val() == ""){
		alert("Please Select Type of Issue.");
		$("Select#type_of_issue").focus();
		return false;
    }
	var a = $("input[name='issue_stock']:checked").val();
	if ($('input[name="issue_stock"]:checked').length == 0) {
		alert('Please Select Issue Stock. ');
		return false;
	}	
	if($("#type_of_ro").val() == "3"){
		if($("#loan_auth").val() == ""){
			alert("Please Enter Loan Authority.");
			$("#loan_auth").focus();
			return false;
	    }
		if($("#loan_duration").val() == ""){
			alert("Please Enter Loan Duration.");
			$("#loan_duration").focus();
			return false;
	    }
	} 
	if($("Select#type_of_vehicle").val() == "0"){
		alert("Please Select Type of Vehicle.");
		$("Select#type_of_vehicle").focus();
		return false;
    }
	if($("#mct").val() == ""){
		alert("Please Enter MCT No.");
		$("#mct").focus();
		return false;
    }
	if($("#type_of_ro").val() == "3"){
		if($("#loan_auth").val() == ""){
			alert("Please Enter Loan Authority.");
			$("#loan_auth").focus();
			return false;
	    }
		if($("#loan_duration").val() == ""){
			alert("Please Enter Loan Duration.");
			$("#loan_duration").focus();
			return false;
	    }
	}
	/* for(var i=1;i<=x;i++){
		var mctArry = $("#mct"+i).val();
		var mctNo = $("#mct").val();
		if(mctArry == mctNo){
			alert("Mct Already Exist !");
			$("#mct").focus();
			return false;
		}	
	} */
	if($("#quantity").val() <= 0){
		alert("Please Enter Qty.");
		$("#quantity").focus();
		return false;
    }
	
	if($("#quantity").val() > freestock){
		alert("RO ("+$("#quantity").val()+") Qty exceed from free stock Qty ("+freestock+")");
		$("#quantity").focus();
		return false;
    }
	if($("#issue_precedence").val() == ""){
		alert("Please Select Issue Precedence.");
		$("#issue_precedence").focus();
		return false;
    }
	if($("#accounting").val() == ""){
		alert("Please Select Accounting.");
		$("#accounting").focus();
		return false;
    }
	if($("#remarks").val() == ""){
		alert("Please Enter Remarks.");
		$("#remarks").focus();
		return false;
    }
	$('#save_per').prop('disabled', true);
	
	
	if($("#type_of_ro").val() != "0"){
		var type_of_ro = $("#type_of_ro").val();
		var ro_no = $("#ro_no").val();
		var sus_no = $("#sus_no").val();
		var unit_name = $("#unit_name").val();
		var command_sus_no  = $("#command_sus_no").val();
		var comd_name = $("#comd_name").val();
		var depot_sus_no = $("#depot_sus_no").val();
		var depot_name = $("#depot_name").val();
		var nrs = $("#nrs").val();
		var sdR = $("#sdR").val();
		var type_of_issue = $("#type_of_issue").val();
		var issue_stock = $("input[name='issue_stock']:checked").val();
		var type_of_vehicle = $("#type_of_vehicle").val();
		var issue_date = $("#issue_date").val();
		var loan_auth = $("#loan_auth").val();
		var loan_duration = $("#loan_duration").val();
		var mct = $("#mct").val();
		var std_nomclature = $("#std_nomclature").val();
		var ue = $("#ue").val();
		var uh = $("#uh").val();
		var prf_ue = $("#prf_ue").val();
		var prf_uh = $("#prf_uh").val();
		var inliuemct = $("#inliuemct").val();
		var std_nomclature_inlieu = $("#std_nomclature_inlieu").val();
		var quantity = $("#quantity").val();
		var accounting = $("#accounting").val();
		var issue_precedence = $("#issue_precedence").val();
		var remarks = $("#remarks").val();
		var corps_sus_no  = $("#corp_sus_no").val();
		if (x < max_fields) {
			if (ro_no != "" && sus_no != "" && mct != "") {
				var form = jQuery('#capture_rel_order')[0];
				var data = new FormData(form);
				jQuery.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : "capture_rel_orderAction?"+key+"="+value,  //capture_rel_orderAction //unit_upload_doc_mcr 
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					timeout : 600000,
					success : function(data) {
						alert(data);
						if(data == "Release Order Created."){
							$("#quantity").val("0");
							$("#type_of_ro").val("0");
							$("#ro_no").val("");
							$("#mct").val("");
							$("#std_nomclature").val("");
							$('#save_per').prop('disabled', false);
							
							x++;
							$("table#add_reports").append('<tr id="'+x+'"><td style="text-align:center;width:10%;">'+ x+ '</td>'
								+ '<td style="text-align:center;width:10%;">'+ro_no+'</td>'
								+ '<td style="text-align:center;width:10%;">'+sus_no+'</td>'
								+ '<td style="text-align:center;width:30%;">'+unit_name+'</td>'
								+ '<td style="text-align:center;width:30%;">'+std_nomclature+'</td>'
								+ '<td style="text-align:center;width:10%;">'+quantity+'</td>');
							return true;
						}
					}
				});
			}else {
				alert("Please Enter Manadatory Fields");
				$('#save_per').prop('disabled', false);
			}
		} else {
			alert("Add More Details of RO Limit is 100");
			$('#save_per').prop('disabled', false);
		}
	}
	return false;
}	

function validateSubmitButton(){
	/* if(confirm('Are you sure you want to submit') == true ){
		window.location.href='capture_rel_order';
	}else{
		return false;
	} */
		
	//alert("quantity");
	/* if($("Select#type_of_ro").val() == "0"){
		alert("Please Select the Type of Release Order.");
		$("Select#type_of_ro").focus();
		return false;
	}
	if($("Select#ro_no").val() == ""){
		alert("Please Select the Release Order NO.");
		$("Select#ro_no").focus();
		return false;
	}
	if($("#sus_no").val() == ""){
		alert("Please Enter Unit SUS No.");
		$("#sus_no").focus();
		return false;
    }
	if($("#unit_name").val() == ""){
		alert("Please Enter Unit Name");
		$("#unit_name").focus();
		return false;
    }
	if($("#depot_sus_no").val() == ""){
		alert("Please Enter Depot SUS No.");
		$("#depot_sus_no").focus();
		return false;
    }	
	if($("#depot_name").val() == ""){
		alert("Please Enter Depot Name.");
		$("#depot_name").focus();
		return false;
    }
	if($("Select#type_of_issue").val() == ""){
		alert("Please Select Type of Issue.");
		$("Select#type_of_issue").focus();
		return false;
    }
	var a = $("input[name='issue_stock']:checked").val();
	if ($('input[name="issue_stock"]:checked').length == 0) {
		alert('Please Select Issue Stock. ');
		return false;
	}
	if($("#type_of_ro").val() == "3"){
		if($("#loan_auth").val() == ""){
			alert("Please Enter Loan Authority.");
			$("#loan_auth").focus();
			return false;
	    }
		if($("#loan_duration").val() == ""){
			alert("Please Enter Loan Duration.");
			$("#loan_duration").focus();
			return false;
	    }
	} 
	if($("Select#type_of_vehicle").val() == "0"){
		alert("Please Select Type of Vehicle.");
		$("Select#type_of_vehicle").focus();
		return false;
    }
	return true; */
}

</script>

<script>	
/////////////////////==== Only 0-9 numeric value
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

function checkVehicleType(){
	var type_of_vehicle = $("#type_of_vehicle").val();
	if(type_of_vehicle == ""){
		alert("Please Select Type Of Vehicle");
		$("#mct").val("");
		$("#type_of_vehicle").focus();
		return false;	
	}
	return true;
}



</script>
<script>
	var issue_stock;
		$(function() {
			$('input[name="issue_stock"]').on('click', function() {
				
				if ($(this).val() == 'free_stock') {
					
					
					$(function() {

						$("input#mct").keyup(function(){
							var mct = this.value;
						debugger;
							var mct_numberAuto=$("#mct");
				
							var type_of_vehicle = $("#type_of_vehicle").val();
							var issue_stock = $("input[name='issue_stock']:checked").val();
							var depot_sus_no = $("#depot_sus_no").val();
							
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle");
								$("#type_of_vehicle").focus();
								$("#std_nomclature").val("");
					        	mct_numberAuto.val("");
								return false;
							}
							mct_numberAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getMctNoDetailListfree?"+key+"="+value,
							        data: {mct:mct ,type_of_vehicle:type_of_vehicle,issue_stock:issue_stock,depot_sus_no:depot_sus_no},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	alert("Please Enter Valid MCT No.");
							        	$("#std_nomclature").val("");
							        	mct_numberAuto.val("");	        	  
							        	mct_numberAuto.focus();
							        	return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui ) {
							      	var mct = ui.item.value;
							     
							        $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : mct}, function(data) {
						            }).done(function(data) {

							      		var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#std_nomclature").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });							    
							      	getPrfUE_UH(mct);							      	
							      	getFreeStock(mct,$("#depot_sus_no").val());
								}
							});
						});
						 $("input#std_nomclature").keyup(function(){
								var mctNomen = this.value;
								var type_of_vehicle = $("#type_of_vehicle").val();
								var mct_nomenAuto=$("#std_nomclature");
								var issue_stock = $("input[name='issue_stock']:checked").val();
								var depot_sus_no = $("#depot_sus_no").val();
								
								if(type_of_vehicle == "0"){
									alert("please select  Type of Vehicle");
									$("#type_of_vehicle").focus();
									$("#mct").val("");
						        	mct_nomenAuto.val("");	  
									return false;
								}
								mct_nomenAuto.autocomplete({
								      source: function( request, response ) {
								        $.ajax({
								        	
								        	type: 'POST',
									    	url: "getStdNomenclatureListFromVeh_catfree_stock?"+key+"="+value,
								        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle,issue_stock:issue_stock,depot_sus_no:depot_sus_no},
								          success: function( data ) {
								        	  var susval = [];
								        	  var length = data.length-1;
								        	  if(data.length != 0){
									        		var enc = data[length].substring(0,16);
									        	}
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
								        	  alert("Please Enter Valid  Nomenclature.");
								        	  $("#mct").val("");
								        	  mct_nomenAuto.val("");	        	  
								        	  mct_nomenAuto.focus();
								        	  return false;	             
								    	}   	         
								    }, 
								  	select: function( event, ui ) {
								      	var nomencatre = ui.item.value;
								  
								        
								        $.post("getStdNomenclaturetoMctNoList?"+key+"="+value, {std_nomclature : nomencatre}, function(data) {
							            }).done(function(data) {
							            	var length = data.length-1;
								        	var enc = data[length].substring(0,16);
								        	$("#mct").val(dec(enc,data[0]));
								        	var mct= $("#mct").val();								        
											getPrfUE_UH(mct);										      	
									      	getFreeStock(mct,$("#depot_sus_no").val());
							            }).fail(function(xhr, textStatus, errorThrown) {
							            });
									}
								});
							});
						$("input#inliuemct").keyup(function(){
							var mct = this.value;
							var mct_numberAuto=$("#inliuemct");
							var type_of_vehicle = $("#type_of_vehicle").val();
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle");
								$("#type_of_vehicle").focus();
								$("#std_nomclature_inlieu").val("");
					        	mct_numberAuto.val("");
								return false;
							}
							mct_numberAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getMctNoDetailList?"+key+"="+value,
							        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	alert("Please Enter Valid MCT No.");
							        	$("#std_nomclature_inlieu").val("");
							        	mct_numberAuto.val("");	        	  
							        	mct_numberAuto.focus();
							        	return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui){
							      	var mct = ui.item.value;
							        $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : mct}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#std_nomclature_inlieu").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
							        
							        
							    }
							});
						});
						$("input#std_nomclature_inlieu").keyup(function(){
							var mctNomen = this.value;
							var type_of_vehicle = $("#type_of_vehicle").val();
							var mct_nomenAuto=$("#std_nomclature_inlieu");
							
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle.");
								$("#type_of_vehicle").focus();
								$("#inliuemct").val("");
					        	mct_nomenAuto.val("");	  
								return false;
							}
							mct_nomenAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
							        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	  alert("Please Enter Valid  Nomenclature.");
							        	  $("#inliuemct").val("");
							        	  mct_nomenAuto.val("");	        	  
							        	  mct_nomenAuto.focus();
							        	  return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui ) {
							      	var nomencatre = ui.item.value;
							        $.post("getStdNomenclaturetoMctNoList?"+key+"="+value,{std_nomclature : nomencatre}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#inliuemct").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
								}
							});
						});
						
					});
				} else {
					
					$(function() {

						$("input#mct").keyup(function(){
							var mct = this.value;
							var mct_numberAuto=$("#mct");
							var type_of_vehicle = $("#type_of_vehicle").val();
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle");
								$("#type_of_vehicle").focus();
								$("#std_nomclature").val("");
					        	mct_numberAuto.val("");
								return false;
							}
							mct_numberAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getMctNoDetailList?"+key+"="+value,
							        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	alert("Please Enter Valid MCT No.");
							        	$("#std_nomclature").val("");
							        	mct_numberAuto.val("");	        	  
							        	mct_numberAuto.focus();
							        	return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui ) {
							      	var mct = ui.item.value;
							     
							        $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : mct}, function(data) {
						            }).done(function(data) {

							      		var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#std_nomclature").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
							      	getPrfUE_UH(mct);							      	
							      	getFreeStock(mct,$("#depot_sus_no").val());
								}
							});
						});
						 $("input#std_nomclature").keyup(function(){
								var mctNomen = this.value;
								var type_of_vehicle = $("#type_of_vehicle").val();
								var mct_nomenAuto=$("#std_nomclature");
								var issue_stock = $("input[name='issue_stock']:checked").val();
								var depot_sus_no = $("#depot_sus_no").val();
								
								if(type_of_vehicle == "0"){
									alert("please select  Type of Vehicle");
									$("#type_of_vehicle").focus();
									$("#mct").val("");
						        	mct_nomenAuto.val("");	  
									return false;
								}
								mct_nomenAuto.autocomplete({
								      source: function( request, response ) {
								        $.ajax({
								        	
								        	type: 'POST',
									    	url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
								        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
								          success: function( data ) {
								        	  var susval = [];
								        	  var length = data.length-1;
								        	  if(data.length != 0){
									        		var enc = data[length].substring(0,16);
									        	}
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
								        	  alert("Please Enter Valid  Nomenclature.");
								        	  $("#mct").val("");
								        	  mct_nomenAuto.val("");	        	  
								        	  mct_nomenAuto.focus();
								        	  return false;	             
								    	}   	         
								    }, 
								  	select: function( event, ui ) {
								      	var nomencatre = ui.item.value;
								  
								        
								        $.post("getStdNomenclaturetoMctNoList?"+key+"="+value, {std_nomclature : nomencatre}, function(data) {
							            }).done(function(data) {
							            	var length = data.length-1;
								        	var enc = data[length].substring(0,16);
								        	$("#mct").val(dec(enc,data[0]));
											getPrfUE_UH(j);
							            }).fail(function(xhr, textStatus, errorThrown) {
							            });
									}
								});
							});
						$("input#inliuemct").keyup(function(){
							var mct = this.value;
							var mct_numberAuto=$("#inliuemct");
							var type_of_vehicle = $("#type_of_vehicle").val();
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle");
								$("#type_of_vehicle").focus();
								$("#std_nomclature_inlieu").val("");
					        	mct_numberAuto.val("");
								return false;
							}
							mct_numberAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getMctNoDetailList?"+key+"="+value,
							        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	alert("Please Enter Valid MCT No.");
							        	$("#std_nomclature_inlieu").val("");
							        	mct_numberAuto.val("");	        	  
							        	mct_numberAuto.focus();
							        	return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui){
							      	var mct = ui.item.value;
							        $.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct : mct}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#std_nomclature_inlieu").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
							        
							        
							    }
							});
						});
						$("input#std_nomclature_inlieu").keyup(function(){
							var mctNomen = this.value;
							var type_of_vehicle = $("#type_of_vehicle").val();
							var mct_nomenAuto=$("#std_nomclature_inlieu");
							
							if(type_of_vehicle == "0"){
								alert("please select  Type of Vehicle.");
								$("#type_of_vehicle").focus();
								$("#inliuemct").val("");
					        	mct_nomenAuto.val("");	  
								return false;
							}
							mct_nomenAuto.autocomplete({
							      source: function( request, response ) {
							        $.ajax({
							        	type: 'POST',
								    	url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
							        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
							          success: function( data ) {
							        	  var susval = [];
							        	  var length = data.length-1;
							        	  if(data.length != 0){
								        		var enc = data[length].substring(0,16);
								        	}
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
							        	  alert("Please Enter Valid  Nomenclature.");
							        	  $("#inliuemct").val("");
							        	  mct_nomenAuto.val("");	        	  
							        	  mct_nomenAuto.focus();
							        	  return false;	             
							    	}   	         
							    }, 
							  	select: function( event, ui ) {
							      	var nomencatre = ui.item.value;
							        $.post("getStdNomenclaturetoMctNoList?"+key+"="+value,{std_nomclature : nomencatre}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#inliuemct").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
								}
							});
						});
						
					});
					
				
				}
			});
		});
	</script>


<!-- <script>
  window.addEventListener('DOMContentLoaded', function() {
    var dropdown = document.getElementById('type_of_vehicle');
    dropdown.disabled = true;

    var hiddenInput = document.getElementById('type_of_vehicle_hidden');
    hiddenInput.value = dropdown.value;
  });
</script> -->




