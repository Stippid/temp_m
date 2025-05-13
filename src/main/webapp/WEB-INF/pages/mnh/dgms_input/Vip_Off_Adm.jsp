<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="vip_off_adm" id="vip_off_adm" action="vip_off_admAction" method='POST' commandName="vip_off_admCmd">
	
	<div class="container" align="center">
			<div class="card">
			
			
				<div class="card-header">
		            
		           <h5>VIP(MAJ GEN & ABOVE) ADMISSIONS</h5>
		       
					 <h6><span style="font-size: 12px;color:red">(To be entered by Medical Unit)</span></h6>
				
		      	</div>
		      	<div class="card-body card-block">
				  <div class="row">
				   
					<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
                  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
						</div>
						<div class="col-md-9">
                <input type="text" id="unit_name1" name="unit_name1" value="${unit_name}" class="form-control-sm form-control" placeholder="Search..." 
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
                     
						   	
							<input type="text" id="sus_no" name="sus_no" class="form-control-sm form-control"  value="${sus_no}" placeholder="Search..." maxlength="8"
							autocomplete="off" title="Type SUS No or Part of SUS No to Search">
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
                         <select id="service" name="service" class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCode_SERVICE}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
						</div>
						</div>
						</div>
						</div>
				     <div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Personnel No</label>
								</div>
								
								<div class="col-md-10">								    <div class="col-md-4">
									<select id="persnl_no1" name="persnl_no1" class="form-control-sm form-control" onchange="personal_number1(this)">
								<option value="-1">--Select--</option>
								<c:forEach var="item" items="${getMedSystemCode_PERSNL_PRE}" varStatus="num">
									<option value="${item}" name="${item}">${item}</option>
								</c:forEach>
							</select>
									</div>
									<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2" class="form-control-sm form-control" autocomplete="off"
								placeholder="Enter No..." maxlength="10" onkeypress="return isNumber0_9Only(event)" onchange="personal_number1(this)">
								</div>
								<div class="col-md-4">
									<select id="persnl_no3" name="persnl_no3" class="form-control-sm form-control" onchange="personal_number1(this)">
								<option value="-1">--Select--</option>
								<c:forEach var="item" items="${getMedSystemCode_PERSNL_SUF}" varStatus="num">
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
							<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Personnel Name</label>
						</div>
						
						<div class="col-md-8">
							<input type="text" id="persnl_name" name="persnl_name" class="form-control-sm form-control" onkeypress="return onlyAlphabets(event);" autocomplete="off" 
							placeholder="Enter Personnel Name..." oninput="this.value = this.value.toUpperCase()" maxlength="100"/>
						</div>
						</div>
						</div>
				        <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							  <label for="text-input" class=" form-control-label">Appointment</label>
					     </div>
						 <div class="col-md-8">
							  <input type="text" id="appointment" name="appointment" class="form-control-sm form-control" autocomplete="off" 
							placeholder="Enter Appointment..." onkeypress="return onlyAlphaNumeric(event);" oninput="this.value = this.value.toUpperCase()" maxlength="50"/>
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
					
					    <select name="category" id="category" class="form-control-sm form-control" onchange="category_change(this);">
								 <option value="-1">--Select--</option>
								 <c:forEach var="item" items="${getMedSystemCode_CATEGORY}" varStatus="num">
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
					   
					         <select name="v_rank.id" id="rank" data-placeholder="Select the Rank..." class="form-control-sm form-control" onchange="rank_change(this);">
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
						     <label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>PSG Rank</label>
					    </div>
					   <div class="col-md-8">
					
					   <input type="text" id="daily_rank" name="daily_rank" class="form-control-sm form-control" 
					readonly autocomplete="off">
					   
					    </div>
						</div>
						</div>
					    
            
				   </div>
				    <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Gender</label>
								</div>
								<div class="col-md-8">
									<select name="sex" id="sex"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
										<option value="Other">Other</option>
									</select>
								</div>
							</div>
						</div>
             <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
            <label for="text-input" class=" form-control-label">Aadhar Card No.</label>
						</div>
							
						<div class="col-md-8">
							<input type="text" id="adhar_card_no" name="adhar_card_no" class="form-control-sm form-control" 
							 maxlength="12" onkeypress="return isNumber0_9Only(event)" placeholder=" Please Enter 12 Digit Aadhar card No..." autocomplete="off">

						</div>
				 </div>
				  </div>
						
					</div>
				    <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
                           <label class=" form-control-label"><strong style="color: red;">* </strong>Service Status</label>
						</div>
							
						<div class="col-md-8">
						<select id="type" name="type"  class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${getMedSystemCode_TYPE}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
                
						</div>
						</div>
						</div>

						  <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date of Reporting</label>
						</div>
							
						<div class="col-md-8">
								<input type="date" id="dt_report1" name="dt_report1" class="form-control-sm form-control"   min="1899-01-01" max="${date}" >
						</div>
				 </div>
				  </div>
				   </div>				  
				   
			
				 
				 	
				 
				 <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
								<label for="text-input" class=" form-control-label" style="margin-left: 11px;">DOB</label>
						  </div>
						  <div class="col-md-8">
								<input type="date" id="date_of_birth1" name="date_of_birth1" autocomplete="off" class="form-control-sm form-control" 
								  onchange="calculate_age_new(this);" maxlength="3" min="1899-01-01" max="${date}">
						  </div>
				          </div>
				          </div>
				           <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Unit</label>
						  </div>
						 <div class="col-md-8">
								<input type="text" id="persnl_unit" autocomplete="off" name="persnl_unit" class="form-control-sm form-control" 
								placeholder="Enter Unit..." onkeypress="return onlyAlphaNumeric(event);" oninput="this.value = this.value.toUpperCase()" onkeypress="return onlyAlphaNumeric(event);" maxlength="250"/>
						 </div>
				        </div>
				         </div>
				        
				          
				          </div>
				
				    <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
								<label for="text-input" class=" form-control-label" style="margin-left: 11px;">Age (Years)</label>
						  </div>
						  <div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-6">
                   		<input type="text" id="age_year" name="age_year" value="0" autocomplete="off" class="form-control-sm form-control" 
								maxlength="3" onkeypress="return isNumber0_9Only(event)" placeholder="Enter Age..." readonly>
						  </div>
						   <div class="col-md-6">
						    <input type="text" id="age_month" name="age_month" class="form-control-sm form-control" autocomplete="off" 
						     placeholder="Enter Month..." readonly>
						  </div>
						</div>
						</div>
						</div>
						</div>
                       <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Contact
										Number(Mobile)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="contact_no" name="contact_no1" 
										autocomplete="off" class="form-control-sm form-control"
										onkeypress="return isNumber0_9Only(event)" maxlength="10"
										placeholder=" Please Enter 10 Digit Mobile No...">
								</div>
							</div>
						</div>   




			  
				        </div>
				         
				<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	              <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date/Time of Admission</label>
					</div>
						<div class="col-md-8">
					<input type="datetime-local" id="dt_admission" name="dt_admission" class="form-control-sm form-control" min="1899-01-01" max="${date}">		
					</div>
					</div>	  
					</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong>Relation</label>
								</div>
								<div class="col-md-8">
									<select id="relation" name="relation"
										class="form-control-sm form-control">
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_5}" varStatus="num">
											<option value="${item}" name="${item}">${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div> 
                        
				    </div>
				    <!-- <div class="col-md-12 from-group">
						
						<div class="col-md-2">
						<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong> Diagnosis</label>
						  </div>
						  
						  	<div class="col-md-10" >
							
						   
									 <input type="text" id="diagnosis1" name="diagnosis1" class="form-control-sm form-control" 
									 placeholder="Search..." autocomplete="off">
							   <input type="hidden" id="diagnosis_id" name="v_icd_code.id" class="form-control-sm form-control" 
									 placeholder="Search..." autocomplete="off">
							  
							 
						
				       </div>
				        </div> 
				         -->
				          <div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Diagnosis ICD Code</label>
						</div>
						
						<div class="col-md-8">
						 <input type="text" name="diagnosis_code1d" onkeypress="return onlyAlphaNumeric(event);" id="diagnosis_code1d" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete="off" 
							  placeholder="Search...">
						</div>
						</div>
						</div>
				
				        <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
							  <label for="text-input" class=" form-control-label">Diagnosis ICD Cause</label>
					     </div>
						 <div class="col-md-8">
							 <input type="text" name="icd_cause_code1d" onkeypress="return onlyAlphaNumeric(event);" id="icd_cause_code1d" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete="off" 
							placeholder="Search..." readonly>			
						  </div>
						  </div>
						  </div>
				    </div>
				    <div class="col-md-12 from-group">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"> Remarks</label>
						</div>
						<div class="col-md-10">
							<textarea rows="2" cols="250" class="form-control char-counter1"
								placeholder="Enter Your Remarks..." onkeypress="return onlyAlphaNumeric(event);" id="remarks" name="remarks"
								maxlength="255"></textarea>
						</div>
					</div>
			</div>
			</div>
			
			<div class="card-footer" align="center"> 
			
			<input type="hidden" id="btn_hid" name="btn_hid" class="form-control-sm form-control" value ="0">
			<input type="hidden" id="nillFlag" name="nillFlag" class="form-control-sm form-control" value ="0">
			
              <input type="hidden" id="btn_hid_exit" name="btn_hid_exit" class="form-control-sm form-control" value ="0"> 
				 <a href="mnh_daily_disease" class="btn btn-primary btn-sm" id="btn_clc" >Clear</a>					     
		         <input type="submit" id="btn_save" class="btn btn-success btn-sm" value="Save & Add Record" onclick="return isvalidData();"/> 	
		       <!--     <a href="commonDashboard" class="btn btn-primary btn-sm" id="btn_clc" onclick="return isvalidData_exit();">Save & Exit</a> -->
		             <input type="submit" id="btn_exit" class="btn btn-primary btn-sm" value="Save & Exit" onclick="return isvalidData_exit();"/> 	         
		             <input type="submit"  id="btn_nil" class="btn btn-success btn-sm" value="NIL Report"  onclick="return isvalidData_nil();"/> 		         	         
           </div>
		</div>

</form:form>




<script>
$("input#persnl_unit").keypress(function(){
	var unit_name = this.value;
		 var susNoAuto=$("#persnl_unit");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getTargetUnitsNameActiveListnew?"+key+"="+value,
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
		          } 
 	         
		      }
		      	     
		}); 			
});

function btn_clc(){
	location.reload(true);
}
function isvalidData_nil(){
	
	if ($("#sus_no").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
		return false;
	}
	
	else{
		var p1 = $("#persnl_no1").val();
		var p2 = $("#persnl_no2").val();
		var p3 = $("#persnl_no3").val();
		var unit = $("#unit_name1").val();
		var persnl_name = $("#persnl_name").val();
		var dt = $("#dt_incident").val();
		var dt_adm = $("#dt_admission").val();
		var p = p1.concat(p2).concat(p3);
		var diag = $("#diagnosis1").val();
		$("#nillFlag").val('-2');
		
	
	}
}
function isvalidData(){
	var sr = $("#btn_hid").val("1");
	
	var rel = $("#relation").val();
	
	if ($("#sus_no").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no").focus();
		return false;
	}
	if ($("#service").val() == "-1") {
		alert("Please Select the Service");
		$("#service").focus();
 		return false;
	}	
	 var adhar_no = ($("#adhar_card_no").val());
		if (!adhar_no == "" && adhar_no != null  && (adhar_no.length != 12 && adhar_no.length != 14 ))
		{
		alert("Adhaar No Should Contain Maximum 12 Digit");
		return false;
		}
	
	if ($("#type").val() == "-1") {
		alert("Please Select the Type");
		$("#type").focus();
 		return false;
	}
	if ($("#dt_report1").val() == "-1") {
		alert("Please Select the Date of Report");
		$("#dt_report1").focus();
 		return false;
	}
	if($("#service").val() == "NAVY" && $("#service").val() == "AIRFORCE"){
		if ($("#persnl_no2").val() == "" && $("#persnl_no3").val() == "") {
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

	 if ($("#sex").val() == "-1") {
		alert("Please Select the Gender");
		$("#sex").focus();
 		return false;
	 }
	 if (rel == "-1") {
		alert("Please Select the Relation");
		$("#relation").focus();
		return false;
	}
		 
	if ($("#category").val() == "MNS" && $("#sex").val() != "Female") {
		alert("category is MNS then gender should be Female(F) only.");
		$("#sex").focus();
		return false;
	}

	if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF") && ($("#sex").val() != "Female")) {
	   alert("Gender should be Female Here");
		$("#sex").focus();
		return false;
			}
	if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF") && ($("#sex").val() != "Male")) {
		alert("Gender should be Male Here");
		$("#sex").focus();
		return false;
	}
	if ($("#persnl_name").val().trim() == "") {
		alert("Please Enter the Personnal Name");
		$("#persnl_name").focus();
		return false;
	}
	
	
	if ($("#dt_admission").val() == "" && $("#dt_admission").val() == "") {
		alert("Please Select the Date of Addmission");
		$("#dt_admission").focus();
 		return false;
	}
 	if ($("#diagnosis_code1d").val() == "") {
		alert("Please Enter the Diagnosis ICD Code");
		$("#diagnosis1").focus();
		return false;
	}  
	
	var b_1 = $("#diagnosis_code1d").val().split("-");
	var b_2 = b_1[0].substring(0,1).toUpperCase();
	var d_code = $("#diagnosis_code1d").val().split("-");
	var d_code_p = $("#diagnosis_code1d").val().substring(0,3).toUpperCase();
	  if ((b_2 == "S" || b_2 == "T" || b_2 == "X" || b_2 == "V" || b_2 == "W" || b_2 == "Y")  
		      && ($("#icd_cause_code1d").val() == "")) {
				alert("Please Enter ICD Cause");
				$("#icd_cause_code1d").focus();
				return false;
			} 
	
	
	
	else{
		var p1 = $("#persnl_no1").val();
		var p2 = $("#persnl_no2").val();
		var p3 = $("#persnl_no3").val();
		var unit = $("#unit_name1").val();
		var persnl_name = $("#persnl_name").val();
		var dt = $("#dt_incident").val();
		var dt_adm = $("#dt_admission").val();
		var p = p1.concat(p2).concat(p3);
		var diag = $("#diagnosis1").val();
		
	}
}

function isvalidData_exit(){
	var sr = $("#btn_hid_exit").val("1");
	
	
	var rel = $("#relation").val();

 
	if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	 if ($("#service").val() == "-1") {
		alert("Please Select the Service");
		$("#service").focus();
 		return false;
	}
	 var adhar_no = ($("#adhar_card_no1").val());
		if (!adhar_no == "" && adhar_no != null  && (adhar_no.length != 12 && adhar_no.length != 14 ))
		{
		alert("Adhaar No Should Contain Maximum 12 Digit");
		return false;
		}
	if ($("#type").val() == "-1") {
		alert("Please Select the Type");
		$("#type").focus();
 		return false;
	}
	 if ($("#dt_report1").val() == "-1") {
		alert("Please Select the Date of Report");
		$("#dt_report1").focus();
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
	 
	 if ($("#sex").val() == "-1") {
			alert("Please Select the Gender");
			$("#sex").focus();
	 		return false;
		}
	   if (rel == "-1") {
			alert("Please Select the Relation");
			$("#relation").focus();
	 		return false;
		}
	 
	 if ($("#category").val() == "MNS" && $("#sex").val() != "Female") {
			alert("category is MNS then gender should be Female(F) only.");
			$("#sex").focus();
			return false;
		}

	 if ((rel == "WIFEOF" || rel == "MOTHEROF" || rel == "DAUGHTEROF" || rel == "SISTEROF") && ($("#sex").val() != "Female")) {
			alert("Gender should be Female Here");
			$("#sex").focus();
			return false;
		}
		if ((rel == "BROTHEROF" || rel == "HUSBANDOF" || rel == "FATHEROF" || rel == "SONOF") && ($("#sex").val() != "Male")) {
			alert("Gender should be Male Here");
			$("#sex").focus();
			return false;
		}
	 
	if ($("#persnl_name").val().trim() == "") {
		alert("Please Enter the Personnal Name");
		$("#persnl_name").focus();
		return false;
	}
	
	 
	 
	 if ($("#dt_admission").val() == "") {
			alert("Please Select the Date of Admission");
			$("#dt_admission").focus();
	 		return false;
		}
	
	if ($("#diagnosis").val() == "-1" || $("#diagnosis").val() == "") {
		alert("Please Select or Enter the Diagnosis");
		$("#diagnosis").focus();
		return false;
	} 
	else{
		var p1 = $("#persnl_no1").val();
		var p2 = $("#persnl_no2").val();
		var p3 = $("#persnl_no3").val();
		var unit = $("#unit_name1").val();
		var persnl_name = $("#persnl_name").val();
		var dt = $("#dt_incident").val();
		var dt_adm = $("#dt_admission").val();
		var p = p1.concat(p2).concat(p3);
		var diag = $("#diagnosis").val();
		
	}
}

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
		
		//$("#persnl_no2").val('');
		//$("#persnl_no3").val('-1');
		
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
	$.post("getMNHRank?"+key+"="+value,{a1 : category,a2 : service},function(j) {
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
		var q = '${list.rank}';
		if(q != ""){
			$("#rank").val(q);
		}
	});
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




jQuery(function() {
	// Source SUS No

	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
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
			        	  document.getElementById("sus_no").value="";
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
				 				         jQuery("#sus_no").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});




	 $("#diagnosis1").keyup(function(){
		var d1 = this.value;
		$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis1,{a:d1,b:"ALL"},'getMNHDistinctICDList_ID',diagnosis_id,'','','');
	});	


$(document).ready(function () {
	$().getCurDt(dt_report1); 
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
	
	
	$("#unit_name1").val('${unit_name}');
	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:'${unit_name}'}, function(j) {			
		 var length = j.length-1;
		
         var enc = j[length].substring(0,16);
       
        jQuery("#sus_no").val(dec(enc,j[0]));
	});
	
	

});

function category_change(obj){

	$("#hid_category").val(obj.value);
}
function rank_change(obj){

	$("#hid_rank").val(obj.value);
}

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
</Script>
<script>

$("#diagnosis_code1d").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',diagnosis_code1d,{a:code,b:"ALL"},'','','','','');
});

$("#diagnosis_code1d").change(function(){
	var a = this.value.split("-");
	var b = a[0].substring(0,1).toUpperCase();

	if(b == "S" || b == "T" || b == "V" || b == "W" || b == "X" || b == "Y"){
		
		$("#icd_cause_code1d").attr('readonly',false);
		
		$("#icd_cause_code1d").keyup(function(){
			var code = this.value;
	
			$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1d,{a:code,b:"ALL2"},'','','','','');
		});
	}
	
	
	else{
		
		$("#icd_cause_code1d").attr('readonly',true);
		$("#icd_cause_code1d").val("");
	}
});

$("#icd_cause_code1d").keyup(function(){
	var code = this.value;
	$().Autocomplete2('GET','getMNHDistinctICDList',icd_cause_code1d,{a:code,b:"ALL2"},'','','','','');
});	



</script>

<script>
function personal_number(obj)
{
	var a1 = $("select#persnl_no1").val();
	var a2 =  $("input#persnl_no2").val();
	var a3 = $("select#persnl_no3").val();
	//var personnel_no = a1 +a2+a3;
    var personnel_no11 = a1 +a2+a3;
	var personnel_no = a1 +a2+obj.value;
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
                $("#persnl_name").val(j[0].name);
	    		$("#sex").val(j[0].gender_name);
	    		$("#daily_rank").val(j[0].description);
	    		
	    		$("#appointment").val(j[0].appointment);	
	    		$("#persnl_unit").val(j[0].unit_name);	
	    		if (j[0].mobile_no == null || j[0].mobile_no == "")
            	{
            	 $("#contact_no").val("0");        
            	}
            else
            	{
            	   $("#contact_no").val(j[0].mobile_no); 
            	}
                
	    		
	    		
	    		 $("#persnl_name").attr('readonly',true);
	    		 $("#date_of_birth1").attr('readonly',true);
	    		// $("#category").attr('disabled',true);
	    		// $("#service").attr('disabled',true);
	    		 
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
        	
        	//$("#category").val("-1"); 
        	//$("#service").val("-1");  
        	$("#adhar_card_no").val("");	
     		$("#date_of_birth1").val("");	     		
            $("#persnl_name").val("");
    		$("#sex").val("-1");
    		$("#daily_rank").val("");
    		
    		$("#appointment").val("");	
    		$("#persnl_unit").val("");
    		$("#contact_no").val("0");
    		$("#age_year").val("0");
    		$("#age_month").val("0");
        	
    		
    		 $("#persnl_name").attr('readonly',false);
    		 $("#date_of_birth1").attr('readonly',false);
    		 $("#category").attr('readonly',false);
    		 $("#service").attr('readonly',false);
        	}  
	       getRank();
		 }); 
		
	}
	
	
function personal_number1(obj)
{
        var a1 = $("select#persnl_no1").val();
        var a2 =  $("input#persnl_no2").val();
        var a3 = $("select#persnl_no3").val();
        //var personnel_no = a1 +a2+a3;
        if($("select#persnl_no1").val() != '')
        var personnel_no11 = a1 +a2+a3;
        var personnel_no = a1+a2+a3;
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
	                        $("#adhar_card_no1").val(j[0].adhar_number);        
                             $("#date_of_birth1").val(j[0].date_of_birth);                             
                             $("#persnl_name").val(j[0].name);
                            $("#sex").val(j[0].gender_name);
                            $("#daily_rank").val(j[0].description);
                            
                            $("#appointment").val(j[0].appointment);        
                            $("#persnl_unit").val(j[0].unit_name);  
                            if (j[0].mobile_no == null || j[0].mobile_no == "")
                            	{
                            	 $("#contact_no").val("0");        
                            	}
                            else
                            	{
                            	   $("#contact_no").val(j[0].mobile_no); 
                            	}
                                
                            
                            
                             $("#persnl_name").attr('readonly',true);
                             $("#date_of_birth1").attr('readonly',true);
                             $("#category").attr('readonly',true);
                             $("#sex").attr('readonly',true);
                            // $("#service").attr('disabled',true);
                             
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
                        $("#adhar_card_no1").val("");        
                        $("#date_of_birth1").val("");                                  
                         $("#persnl_name").val("");
                        $("#sex").val("-1");
                        $("#daily_rank").val("");
                        
                        $("#appointment").val("");        
                        $("#persnl_unit").val("");
                        $("#contact_no").val("0");
                        $("#age_year").val("0");
                        $("#age_month").val("0");
                        
                       $("#persnl_name").attr('readonly',false);
                        $("#date_of_birth1").attr('readonly',false);
                        $("#category").attr('readonly',false);
                        $("#service").attr('readonly',false);
                        $("#sex").attr('readonly',false);
                        }
                getRank();
               
                 }); 
               	
        }
        else
        {
        $("#adhar_card_no1").val("");        
        $("#date_of_birth1").val("");                                  
         $("#persnl_name").val("");
        $("#sex").val("-1");
        $("#daily_rank").val("");
        
        $("#appointment").val("");        
        $("#persnl_unit").val("");
        $("#contact_no").val("0");
        $("#age_year").val("0");
        $("#age_month").val("0");
        
       $("#persnl_name").attr('readonly',false);
        $("#date_of_birth1").attr('readonly',false);
        $("#category").attr('readonly',false);
        $("#service").attr('readonly',false);
        $("#sex").attr('readonly',false);
        }
getRank();
                
        }
	
	
function personal_number2(obj)
{
	var a1 = $("select#persnl_no1").val();
	var a2 =  $("input#persnl_no2").val();
	var a3 = $("select#persnl_no3").val();
	//var personnel_no = a1 +a2+a3;
    var personnel_no11 = a1 +a2+a3;
	var personnel_no = a1 +obj.value+a3;
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
                $("#persnl_name").val(j[0].name);
	    		$("#sex").val(j[0].gender_name);
	    		$("#daily_rank").val(j[0].description);
	    		
	    		$("#appointment").val(j[0].appointment);	
	    		$("#persnl_unit").val(j[0].unit_name);	
	    		if (j[0].mobile_no == null || j[0].mobile_no == "")
            	{
            	 $("#contact_no").val("0");        
            	}
            else
            	{
            	   $("#contact_no").val(j[0].mobile_no); 
            	}
                
	    		
	    		
	    		 $("#persnl_name").attr('readonly',true);
	    		 $("#date_of_birth1").attr('readonly',true);
	    		// $("#category").attr('disabled',true);
	    		// $("#service").attr('disabled',true);
	    		 
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
        	
        	//$("#category").val("-1"); 
        	//$("#service").val("-1");  
        	$("#adhar_card_no").val("");	
     		$("#date_of_birth1").val("");	     		
            $("#persnl_name").val("");
    		$("#sex").val("-1");
    		$("#daily_rank").val("");
    		
    		$("#appointment").val("");	
    		$("#persnl_unit").val("");
    		$("#contact_no").val("0");
    		$("#age_year").val("0");
    		$("#age_month").val("0");
        	
    		
    		 $("#persnl_name").attr('readonly',false);
    		 $("#date_of_birth1").attr('readonly',false);
    		 $("#category").attr('readonly',false);
    		 $("#service").attr('readonly',false);
        	}  
	       getRank();
		 }); 
		
	}
</script>