 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script> 



<form:form name="prop_comm_letter" id="prop_comm_letter" action="prop_comm_letter_action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="prop_comm_letter_cmd"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>COMMISSIONING DETAILS MNS</h5> <h6 class="enter_by"><span style="font-size:18px;color:red;">All Fields Mandatory</span></h6><h6 class="enter_by"><!-- <span style="font-size:12px;color:red;">(To be filled by PCTA only)</span> --></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Authority</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" autocomplete="off" maxlength="100" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              				              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority Date</label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="id" name="id"
										class="form-control autocomplete" value="0" autocomplete="off">
										<input type="hidden" id="id1" name="id1"
										class="form-control autocomplete" value="0" autocomplete="off">
						                <input type="text" name="date_of_authority" id="date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12 form-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label"><strong
										style="color: red;">* </strong> Personal No</label>
								</div>
								
						 	<div class="col-md-10">
								    <div class="row form-group">
								    <div class="col-md-4">
									 <select id="persnl_no1" name="persnl_no1" class="form-control-sm form-control">
        <option value="-1">--Select--</option>   
        
                <option value="NR" name="NR">NR</option>
                                 <option value="NS" name="NS">NS</option>
    
        
  
    </select>
									</div>
									<div class="col-md-4">
									<input type="text" id="persnl_no2" name="persnl_no2" onkeyup="onChangePerNo(this);"  onkeypress="return isNumber0_9Only(event)" class="form-control-sm form-control" autocomplete="off" placeholder="Enter No..." maxlength="5">
								</div>
								<div class="col-md-4">
									<input type="text" id="persnl_no3" name="persnl_no3" class="form-control-sm form-control" autocomplete="off"  maxlength="10" readonly="readonly" >
								</div>
									</div>
								</div> 
					</div> 
	              			
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Cadet No</label>
						                </div>
						              <div class="col-md-8">
								                  <input type="text" id="cadet_no" name="cadet_no" class="form-control autocomplete" autocomplete="off" maxlength="7" onkeyup="return specialcharecterCadet(this);" onkeypress="return AvoidSpace(event)"> 
									</div>
						          </div>
	              				</div>
	              				<div class="col-md-6" id="medreg_no_div" style="display:none">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>TNAI NO</label>
						                </div>
						                <div class="col-md-8">
								                  <input type="text" id="tnai_no" name="tnai_no" class="form-control autocomplete" autocomplete="off" "> 
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
						                   <input type="text" id="batch_no" name="batch_no" class="form-control autocomplete" autocomplete="off" maxlength="30"  onkeypress="return isNumber0_9Only(event)"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Course Comm</label>
						                </div>
						                <div class="col-md-8">
								                  <!-- <input type="text" id="course" name="course" class="form-control autocomplete" autocomplete="off" > --> 
										  <select name="course" id="course" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												 <c:forEach var="item" items="${getCourseNameList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
												<!-- <option value="20" name="BSC.NURSING">BSC.NURSING</option> -->
											</select>
										</div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			 <div class="col-md-12">	            
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Name</label>
						                </div>
						                <div class="col-md-8">
								             <input type="text" id="name" name="name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);"
											oninput="this.value = this.value.toUpperCase()"> 
										</div>
						            </div>
	              				</div>
	              				
	              				 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Gender</label>
						                </div>
						                <div class="col-md-8">
								               
											 <select name="gender" id="gender" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getGenderList}" varStatus="num">
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
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Type of Commission Granted</label>
						                </div>
						                <div class="col-md-8">
								                
										    <select name="type_of_comm_granted" id="type_of_comm_granted" class="form-control-sm form-control"   >
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
						                <input type="text" name="date_of_commission" id="date_of_commission" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Seniority</label>
						                </div>
						                <div class="col-md-8">
						                   <!-- <input type="date" id="date_of_seniority" name="date_of_seniority" class="form-control autocomplete" autocomplete="off" > --> 
						                 <input type="text" name="date_of_seniority" id="date_of_seniority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              		
	              			</div>
	              			
	              			
	              			<div class="col-md-12">	              					
	              			
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Rank</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<%-- <c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Rank</label>
						                </div>
						                <div class="col-md-8">
						               
						                <input type="text" name="date_of_rank" id="date_of_rank" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			
	              			
	              		 <div class="col-md-12">	              					
	              			   <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong>Date of Birth </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_birth" id="date_of_birth" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"  
											onchange="clickrecall(this,'DD/MM/YYYY');calculate_age(this);SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
						                   <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Posted To </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
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
						                 <input type="text" name="date_of_tos" id="date_of_tos" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
						                     <select name="parent_arm" id="parent_arm" class="form-control-sm form-control"   onchange="chgarm(this,'regiment');" >
												<option value="0">--Select--</option>
											<%-- 	<c:forEach var="item" items="${getParentArmList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach> --%>
											</select>

						                </div>
						            </div>
	              				</div>
	              			 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Regiment</label>
						                </div>
						                <div class="col-md-8">
						              <input type="hidden" id="parent_armLb" name="parent_armLb" class="form-control autocomplete" autocomplete="off" > 
							                 <select name="regiment" id="regiment" class="form-control-sm form-control"   disabled="disabled"  >
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
						                    <label class=" form-control-label">Parent SUS </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="parent_sus_no" name="parent_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Parent Unit </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="parent_unit" name="parent_unit" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
						                </div>
						            </div>
	              				</div>
	              			</div>	
	              				
	              				
            			</div>

					<div class="card-footer" align="center" class="form-control">
								<a href="Prop_Comm_letter_new" class="btn btn-success btn-sm" >Clear</a> 
			              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick=" return validate();" ><!---->
				    </div> 		
	        	</div>
			</div>
	</div>
</form:form>

  <script>
  
  var roleAccess;
  $(document).ready(function() {
       roleAccess = '${roleAccess}';
      if (roleAccess === 'DGMS') {
          $('#medreg_no_div').show();
      }
      
      $('#persnl_no1').change(function() {
          var selectedValue = $(this).val();
          if (selectedValue === 'NR' || selectedValue === 'NS') {
              $('#medreg_no_div').show();
              rank_list("mns") 
              parent_arm("mns")
          } else {
              $('#medreg_no_div').hide();
              rank_list("miso") 
              parent_arm("miso")
          }
      });
  });
    
  
  function SetMin(){
	if ($("input#date_of_birth").val() != "") {
			
			var birth_dt = $("input#date_of_birth").val();
			setMinDate('date_of_commission', birth_dt);
			setMinDate('date_of_seniority', birth_dt);
			setMinDate('date_of_tos', birth_dt);
			//setMinDate('date_of_rank', birth_dt);
			
		}
		if ($("input#date_of_commission").val() != "") {
			var comm_dt = $("input#date_of_commission").val();
			
			setMinDate('date_of_tos', comm_dt);
			//setMinDate('date_of_rank', comm_dt);
			
		}
  }
  
  function validate() {
		 $.ajaxSetup({
          async : false
  	});
			 var dt_of_birth = $("#date_of_birth").val();
			
		 
		    var seniority = $("#date_of_seniority").val();
		    
		    
		    var Tos = $("#date_of_tos").val();
		   
		    
		    var dt_rank  = $("#date_of_rank").val();
		    
		    
		    var dt_commission  = $("#date_of_commission").val();
		   
		    patt2 = /^[a-zA-z]+([\s][a-zA-Z]+)*$/;
	  if ($("input#authority").val().trim() == "") {
			alert("Please Enter Authority");
			$("input#authority").focus();
			return false;
		 }
	  
	 
		
		 if ($("input#date_of_authority").val() == "" || $("#date_of_authority").val() == "DD/MM/YYYY") {
			alert("Please Select Authority Date");
			$("input#date_of_authority").focus();
			return false;
		 }
		
		 if ($("select#persnl_no1").val() == "-1" || $("select#persnl_no1").val() == "0") {
			alert("Please Select Personal No");
			$("select#persnl_no1").focus();
			return false;
		 }
		 if ($("input#persnl_no2").val().trim() == "") {
			alert("Please Enter Personal No");
			$("input#persnl_no2").focus();
			return false;
		 }
		 var prs2=$("input#persnl_no2").val();
		 if (prs2.length != 5) {
			alert("Please Enter Valid Personal No");
			$("input#persnl_no2").focus();
			return false;
		 }
		var result=Personal_no_already_exist();
		if(result == false)
		{
			$("select#persnl_no1").focus();
			return false;
			
		}
		
		
		
		 if ($("input#cadet_no").val().trim() == "") {
			alert("Please Enter Cadet No");
			$("input#cadet_no").focus();
			return false;
		 }
		 
         if ($("select#persnl_no1").val() == "NR" || $("select#persnl_no1").val() == "NS") {
			 
			 if ($("input#tnai_no").val().trim() == "") {
					alert("Please Enter TNAI No");
					$("input#tnai_no").focus();
					return false;
				 }
		 }
		 if ($("input#batch_no").val().trim() == "") {
			alert("Please Enter Course No");
			$("input#batch_no").focus();
			return false;
		 }
		if ($("select#course").val() == "0") {
			alert("Please Select Course");
			$("select#course").focus();
			return false;
		}
		if ($("input#name").val().trim() == "") {
			alert("Please Enter Name");
			$("input#name").focus();
			return false;
		}
		
		  result2 = patt2.test($("input#name").val());
		if(!result2){
			alert("Name Should Only Contain Alphabet And Only One Space B/W Words");
			$("input#name").focus();
			return false;
		}
	    if ($("select#gender").val() == "0") {
			alert("Please Select Gender");
			$("select#gender").focus();
	 		return false;
		}
		if ($("select#type_of_comm_granted").val() == "0") {
			alert("Please Select Type of Commission Granted");
			$("select#type_of_comm_granted").focus();
			return false;
		}
		if ($("input#date_of_commission").val() == "" || $("#date_of_commission").val() == "DD/MM/YYYY") {
			alert("Please Enter Date of Commission");
			$("input#date_of_commission").focus();
			return false;
		}
		
		 dt_commission1 =dt_commission.substring(6,10);
		 dt_commission2 =dt_commission.substring(3,5);
		 dt_commission3 =dt_commission.substring(0,2);
		    
		if ($("#date_of_seniority").val() == "" || $("#date_of_seniority").val() == "DD/MM/YYYY") {
			alert("Please Enter Date of Seniority");
			$("#date_of_seniority").focus();
	 		return false;
		}
		seniority1 =seniority.substring(6,10);
		//26-01-1994
		/* if(getformatedDate($("#date_of_seniority").val()) < getformatedDate($("input#date_of_commission").val())) {
			alert("Date of Seniority can be greater than Date of Commission or equal to.");
			$("#date_of_seniority").focus();
	 		return false;
		} */
	
		if ($("select#rank").val() == "0") {
			alert("Please Select Rank");
			$("select#rank").focus();
			return false;
		}
		if ($("#date_of_rank").val() == "" || $("#date_of_rank").val() == "DD/MM/YYYY") {
			alert("Please Select Date of Rank");
			$("#date_of_rank").focus();
			return false;
		}
		dt_rank1 =dt_rank.substring(6,10);
	    dt_rank2 =dt_rank.substring(3,5);
	    dt_rank3 =dt_rank.substring(0,2);
	
		/*  if (parseInt(dt_commission1) != parseInt(dt_rank1) || parseInt(dt_commission2) != parseInt(dt_rank2)||parseInt(dt_commission3) != parseInt(dt_rank3)) {
			alert(" Date of Rank should be equals to Date of Commission ");
			$("#date_of_rank").focus();
	 		return false;
		}  */
		
		
		 if(getformatedDate($("input#date_of_commission").val()) > getformatedDate($("#date_of_rank").val())) {
				alert("Date of Rank should be greater Or equal to Commissioning Date");
				$("#date_of_rank").focus();
				return false;
		  }
		 
		
		
	     if ($("#date_of_birth").val() == "" || $("#date_of_birth").val() == "DD/MM/YYYY") {
				alert("Please Enter Date of Birth");
				$("#date_of_birth").focus();
		 		return false;
		 }
	     dt_of_birth1 =dt_of_birth.substring(6,10);
	     if (dt_of_birth1 > dt_commission1) {
				alert(" Date of Commission can not be less than Date of Birth ");
				$("#date_of_commission").focus();
		 		return false;
			}
	     
		if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('date_of_commission'))>=16) {
			    	 
			     } else {
			    	 alert("Commission Age Should Be 16 Or Above ");
						$("#date_of_commission").focus();
			 		return false;
			 	}
	 	
	     
	     if (dt_of_birth1 > seniority1) {
				alert(" Date of Seniority can not be less than Date of Birth ");
				$("#date_of_seniority").focus();
		 		return false;
			}
	     if (dt_of_birth1 > dt_rank1) {
				alert(" Date of Rank can not be less than Date of Birth ");
				$("#date_of_rank").focus();
		 		return false;
			}
	     
	 	if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('date_of_rank'))>=16) {
	    	 
	     } else {
	    	 alert("Rank Age Should Be 16 Or Above ");
	    	 $("#date_of_rank").focus();
	 		return false;
	 	}
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
		 if ($("#date_of_tos").val() == "" || $("#date_of_tos").val() == "DD/MM/YYYY") {
			alert("Please Enter Date of TOS");
			$("#date_of_tos").focus();
	 		return false;
		 }
		 Tos1 =Tos.substring(6,10);
		 if (dt_of_birth1 > Tos1) {
				alert(" Date of TOS can not be less than Date of Birth ");
				$("#date_of_tos").focus();
		 		return false;
		}
		
		 if(getformatedDate($("#date_of_tos").val()) < getformatedDate($("input#date_of_commission").val())) {
				alert("Date of TOS can be greater than Date of Commission or equal to.");
				$("#date_of_tos").focus();
		 		return false;
			}
	
if(calculate_agediff(document.getElementById('date_of_birth'), document.getElementById('date_of_tos'))>=16) {
	    	 
	     } else {
	    	 alert("TOS Age Should Be 16 Or Above ");
	    	 $("#date_of_tos").focus();
	 		return false;
	 	}
	 	
	     if ($("select#parent_arm").val() == "0") {
				alert("Please Select  Parent Arm/Service ");
				$("select#parent_arm").focus();
				return false;
		 }
	 var sp14 = $("#parent_arm option:selected").text();
	 
	 $("#parent_armLb").val(sp14);
	 if(($("select#parent_arm").val() == "0700" || $("select#parent_arm").val() == "0800") && $("select#regiment").val() == "0"){
			alert("Please Select Regiment");
			$("select#regiment").focus();
			return false;
		} 
//		else if(sp14 != "INFANTRY" && $("select#regiment").val() != "0"){
//			alert("Please UnSelect Regiment");
//			$("select#regiment").focus();
//			return false;
//		}
		
		
			 return true;
	}
	
  
  
  
	 jQuery(function($){ 
		 datepicketDate('date_of_rank');
		 datepicketDate('date_of_commission');
		 datepicketDate('date_of_seniority');
		 datepicketDate('date_of_tos');
		 datepicketDate('date_of_birth');
		 datepicketDate('date_of_authority');            
		 
   });

	
	// Unit SUS No

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
	
	// unit name
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
	

		// Parent SUS No

			$("#parent_sus_no").keyup(function(){
				var sus_no = this.value;
				var susNoAuto=$("#parent_sus_no");

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
				        	  alert("Please Enter Approved Parent SUS No.");
				        	  document.getElementById("parent_unit").value="";
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
			             $("#parent_unit").val(dec(enc,j[0]));   
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
					} 	     
				});	
			});
			
			
			// Parent unit name
			 $("input#parent_unit").keypress(function(){
					var unit_name = this.value;
						 var susNoAuto=$("#parent_unit");
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
						        	  alert("Please Enter Approved Parent Unit.");
						        	  document.getElementById("parent_unit").value="";
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
								        	$("#parent_sus_no").val(dec(enc,data[0]));
							            }).fail(function(xhr, textStatus, errorThrown) {
							            });
						      } 	     
						}); 			
				});
				
				
	 
	 

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
	    

	     
	     if (month == undefined)
	      {
	             month = 0;
	      }
	    
	     if(year < 16)
	     {
	    	 alert("Please enter age above 16");
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
			 alert("Please Select Personal Number Prefix.");
			 $("select#persnl_no1").focus();
			 return false;
		 }
		 var persnl_no2 = $("input#persnl_no2").val();
		 if(persnl_no2.length != 5 )
		 {
			 alert("Please Enter Personal Number.");
			 $("input#persnl_no2").focus();
			 return false;
		 }
		 var persnl_no3 = $("input#persnl_no3").val();
		 if(persnl_no3.length != 1 )
		 {
			 alert("Please Enter Personal Number.")
			  $("input#persnl_no2").focus();
			 return false;
		 }
		 var personnel_no = persnl_no1 + persnl_no2 + persnl_no3;
		 
		 $.ajaxSetup({
             async : false
     	});
		 var data_result=true; 
		 var id = "0";
		 $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no,id:id}).done(function(j) {
			 	if(j == false){
					alert("Personal No already Exist.")
					$("select#persnl_no1").val("-1");
					$("input#persnl_no2").val("");
					$("input#persnl_no3").val("");
					data_result = false;
				} 
				
			}); 
		 
		 return data_result;
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
	 
	 function parent_arm(types){

		   var options = '<option value="0">' + "--Select--" + '</option>';
		   var type = types;

		   $.post("getparent_arm?" + key + "=" + value, { type: type })
		     .done(function(j) {
		       for (var i = 0; i < j.length; i++) {
		         options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][1] + '</option>';
		       }
		       $("#parent_arm").html(options);
		     })
		     .fail(function(xhr, textStatus, errorThrown) {
		      
		     });
       }
	 
	 function rank_list(types){

		   var options = '<option value="0">' + "--Select--" + '</option>';
		   var type = types;

		   $.post("getrank_list?" + key + "=" + value, { type: type })
		     .done(function(j) {
		       for (var i = 0; i < j.length; i++) {
		         options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][1] + '</option>';
		       }
		       $("#rank").html(options);
		     })
		     .fail(function(xhr, textStatus, errorThrown) {
		      
		     });
     }
	 
  </script>

  