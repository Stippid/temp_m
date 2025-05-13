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
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>


<form:form name="view_Search_Com_letter" id="view_Search_Com_letter" action="view_Search_Com_letterAction" method="post" class="form-horizontal" commandName="view_Search_Com_letterCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> VIEW COMMISSIONING DETAILS </h5> <h6 class="enter_by"><span style="font-size:18px;color:red;">All Fields Mandatory</span></h6><h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	              
	              				 <div class="col-md-12">	
	            				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>  Authority</label>
						                </div>
						                <div class="col-md-8">
						                <label id="authority" >${view_Search_Com_letterCMD.authority}</label>
						                 <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_posted_toV" name="unit_posted_toV" class="form-control autocomplete"  > 
						                 <input type=hidden id="parent_armV" name="parent_armV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="type_of_comm_grantedV" name="type_of_comm_grantedV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="date_of_commissionV" name="date_of_commissionV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="frm_dtV" name="frm_dtV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="to_dtV" name="to_dtV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="nameV" name="nameV" class="form-control autocomplete"  > 
						               
  						               
						                </div>
						            </div>
	              				</div>
	              				              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"></strong>  Authority Date</label>
						                </div>
						                <div class="col-md-8">
						                <!-- <input type="text" name="date_of_authority" id="date_of_authority" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" readonly/> -->
											<label id="date_of_authority" ></label>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		
	              			
	              		<div class="col-md-12 form-group">
								 
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Personal No</label>
						                </div>
						                <div class="col-md-8">
						                <label id="cadet_no" >${view_Search_Com_letterCMD.personnel_no}</label>
										</div>
						            </div>
	              				</div>
	              					<div class="col-md-6" id='divcadet_no' style="display: block;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Cadet No</label>
						                </div>
						                <div class="col-md-8">
						                <label id="cadet_no" >${view_Search_Com_letterCMD.cadet_no}</label>
										</div>
						            </div>
	              				</div>
	              				</div>
	              				<div class="col-md-12 form-group" id="divpnai_no" style="display: none;" >
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Cadet No</label>
						                </div>
						                <div class="col-md-8">
						                <label id="cadet_no" >${view_Search_Com_letterCMD.cadet_no}</label>
										</div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>TNAI No</label>
						                </div>
						                <div class="col-md-8">
						                <label id="tnai_no" >${view_Search_Com_letterCMD.tnai_no}</label>
										</div>
						            </div>
	              				</div>
					</div> 
					 
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Course No </label>
						                </div>
						                <div class="col-md-8">
						                <label id="batch_no">${view_Search_Com_letterCMD.batch_no}</label>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Course</label>
						                </div>
						                <div class="col-md-8">								                 
											
                                           <label class=" form-control-label" id="course"></label>
						                           <select name="course_val" id="course_val" class="form-control"  style="display: none" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getCourseNameList}" varStatus="num">
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Name</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="name">${view_Search_Com_letterCMD.name}</label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Gender</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="gender"></label>
						                           <select name="gender_val" id="gender_val" class="form-control"  style="display: none" >
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
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Type of Commission Granted</label>
						                </div>
						                <div class="col-md-8">
								                
								                <label class=" form-control-label" id="type_of_comm_granted"></label>
						                           <select name="type_of_comm_granted_val" id="type_of_comm_granted_val" class="form-control"  style="display: none" >
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Commission </label>
						                </div>
						                <div class="col-md-8">
						                <label id="date_of_commission"> </label>
						                </div>
						                
						                
						              
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Seniority</label>
						                </div>
						                <div class="col-md-8">
						                <label id="date_of_seniority"> </label>
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              				<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Birth </label>
						                </div>
						             
						                <div class="col-md-8">
						                 <label id="date_of_birth"> </label>
						                </div> 
						                
						                </div>
						            </div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>Rank</label>
						                </div>
						                <div class="col-md-8">
						              <%--  <label id="rank">${view_Search_Com_letterCMD.rank}</label> --%>
						                  <label class=" form-control-label" id="rank"></label>
						                           <select name="rank_val" id="rank_val" class="form-control"  style="display: none" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select>
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Rank</label>
						                </div>
						                <div class="col-md-8">
						                 <label id="date_of_rank"> </label>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			 
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Unit SUS No  </label>
						                </div>
						                <div class="col-md-8">
						                <label id="unit_sus_no"></label>
						                	<input type="hidden" id="id" name="id" class="form-control" autocomplete="off" value="0" >
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						                <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Unit Posted To </label>
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
						                
						                <label class=" form-control-label" id="date_of_tos"></label>
						                 <!-- <input type="text" name="date_of_tos" id="date_of_tos" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" > -->
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
						                 <label class=" form-control-label" id="parent_arm"></label>
						                           <select name="parent_arm_val" id="parent_arm_val" class="form-control"  style="display: none" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getParentArmList}" varStatus="num">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
											      </select>
						                
						                </div>
						            </div>
	              				</div>
	              			  <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong> Regiment</label>
						                </div>
						                <div class="col-md-8">
						        
						        
						          <label class=" form-control-label" id="regiment"></label>
						                           <select name="regiment_val" id="regiment_val" class="form-control"  style="display: none" >
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getRegtList}" varStatus="num">
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Parent SUS No  </label>
						                </div>
						                <div class="col-md-8">
						                <label id="parent_sus_no"></label>
						                	<input type="hidden" id="id" name="id" class="form-control" autocomplete="off" value="0">
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						                <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong>Parent Unit  </label>
						                </div>
						                <div class="col-md-8">
						                
						                  <label id="parent_unit"></label>
						                </div>
						            </div>
	              				</div>	              				
	              			</div> 
	              			<%-- 		<div class="col-md-12" id = "div_reject_remarks" style="display: none;">
	              			  <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"> </strong> Reject Remarks</label>
						                </div>
						                <div class="col-md-8">
						            <label>${view_Search_Com_letterCMD.reject_remarks}</label>
						                </div>
						            </div>
	              				</div>
	              			
	              				</div> --%>
	              			
	              	
	              			
	              			       			              				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">							
		              		
		              		<!-- <a href="Search_Commissioning_LetterUrl" value = "cancel" class="btn btn-danger btn-sm" >Cancel</a>   -->   
		              		<input type="button"  class="btn btn-info btn-sm"onclick="Cancel();" value="Back"> 
			            </div> 		
	        	</div>
			</div>
	</div>	
 	
</form:form>
		
<c:url value="GetSearch_Com_letter" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="status1">
	
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0"/>
		<input type="hidden" name="unit_posted_to2" id="unit_posted_to2" value="0"/>
		<input type="hidden" name="parent_arm1" id="parent_arm1" value="0"/>
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>
		<input type="hidden" name="name1" id="name1" value="0"/>
		<input type="hidden" name="type_of_comm_granted2" id="type_of_comm_granted2" value="0"/>
		<input type="hidden" name="date_of_commission1" id="date_of_commission1" value="0"/>
		<input type="hidden" name="frm_dt1" id="frm_dt1" value="0"/>
		<input type="hidden" name="to_dt1" id="to_dt1" value="0"/> 
		<input type="hidden" name="IsMns" id="IsMns" value="False"/> 
		
	</form:form> 
	<form:form action="${searchUrl}" method="post" id="searchFormMns" name="searchFormMns" modelAttribute="status1">
	
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value=""/>
		<input type="hidden" name="unit_posted_to2" id="unit_posted_to2" value=""/>
		<input type="hidden" name="parent_arm1" id="parent_arm1" value=""/>
		<input type="hidden" name="personnel_no1" id="personnel_no1" value=""/>
		<input type="hidden" name="name1" id="name1" value=""/>
		<input type="hidden" name="type_of_comm_granted2" id="type_of_comm_granted2" value=""/>
		<input type="hidden" name="date_of_commission1" id="date_of_commission1" value=""/>
		<input type="hidden" name="frm_dt1" id="frm_dt1" value=""/>
		<input type="hidden" name="to_dt1" id="to_dt1" value=""/> 
		<input type="hidden" name="IsMns" id="IsMns" value="True"/> 
		
	</form:form> 
	
		
		
				
<Script>


function Cancel(){

	var personnelprefix ='${view_Search_Com_letterCMD.personnel_no}';
	if(personnelprefix.substring(0, 2).toUpperCase() =="NR" || personnelprefix.substring(0, 2).toUpperCase() == "NS"){	
	  	
		$("#status1").val($("#statusV").val()) ;
		$("#unit_sus_no2").val($("#unit_sus_noV").val()) ;
		$("#unit_posted_to2").val($("#unit_posted_toV").val()) ;
		$("#parent_arm1").val($("#parent_armV").val()) ;
		$("#personnel_no1").val($("#personnel_noV").val()) ;
		$("#type_of_comm_granted2").val($("#type_of_comm_grantedV").val()) ;
		$("#date_of_commission1").val($("#date_of_commissionV").val()) ;
		$("#frm_dt1").val($("#frm_dtV").val()) ;
		$("#to_dt1").val($("#to_dtV").val()) ;
		$("#name1").val($("#nameV").val()) ;
				document.getElementById('searchFormMns').submit();
}else {
  	
	$("#status1").val($("#statusV").val()) ;
	$("#unit_sus_no2").val($("#unit_sus_noV").val()) ;
	$("#unit_posted_to2").val($("#unit_posted_toV").val()) ;
	$("#parent_arm1").val($("#parent_armV").val()) ;
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#type_of_comm_granted2").val($("#type_of_comm_grantedV").val()) ;
	$("#date_of_commission1").val($("#date_of_commissionV").val()) ;
	$("#frm_dt1").val($("#frm_dtV").val()) ;
	$("#to_dt1").val($("#to_dtV").val()) ;
	$("#name1").val($("#nameV").val()) ;	
	document.getElementById('searchForm').submit();
}
	
}

function chgarm(){
	  if($("#parent_arm").val() == "0700" || $("#parent_arm").val() == "0800"){
			
			$("#regiment").attr('readonly',false);
		}
	  else
		  {
		  $("#regiment").attr('readonly',true);
		  $("#regiment").val("0");
		  }
	  
}
function validate() {
	
	 var dt_of_birth = $("#date_of_birth").val();
	 dt_of_birth1 =dt_of_birth.substring(6,10);
	
	 
	    var seniority = $("#date_of_seniority").val();
	    seniority1 =seniority.substring(6,10);
	    
	    
	    var dt_rank  = $("#date_of_rank").val();
	    dt_rank1 =dt_rank.substring(6,10);
	    
	    var dt_commission  = $("#date_of_commission").val();
	    dt_commission1 =dt_commission.substring(6,10);
	
	
	if ($("input#authority").val() == "") {
			alert("Please Enter Authority");
			$("input#authority").focus();
			return false;
		}
		
		 if ($("input#date_of_authority").val() == "") {
			alert("Please Select Authority Date");
			$("input#date_of_authority").focus();
			return false;
		}
	
		/*if ($("input#personnel_no").val() == "") {
			alert("Please Enter Personnel No");
			$("input#personnel_no").focus();
			return false;
		}*/
		if ($("select#persnl_no1").val() == "0") {
				alert("Please Select Personal No");
				$("select#persnl_no1").focus();
				return false;
					}
		 if ($("input#persnl_no2").val() == "") {
				alert("Please Enter Personal No");
				$("input#persnl_no2").focus();
				return false;
			}
		 if ($("input#cadet_no").val() == "") {
				alert("Please Enter Cadet No");
				$("input#cadet_no").focus();
				return false;
					}
		 if ($("input#batch_no").val() == "") {
				alert("Please Enter Course No");
				$("input#batch_no").focus();
				return false;
					}
		if ($("select#course").val() == "0") {
			alert("Please Select Course");
			$("select#course").focus();
	 		return false;
		} 
		if ($("input#name").val() == "") {
			alert("Please Enter Name");
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
		if ($("input#date_of_commission").val() == "") {
			alert("Please Enter Date of Commission");
			$("input#date_of_commission").focus();
			return false;
		}	
		if (dt_of_birth1 > dt_commission1) {
			alert(" Date of Commission can not be less than Date of Birth ");
			$("#date_of_commission").focus();
	 		return false;
		}
		if ($("input#date_of_seniority").val() == "") {
			alert("Please Select Seniority Date");
			$("input#date_of_seniority").focus();
			return false;
		}	
		 if (dt_of_birth1 > seniority1) {
				alert(" Date of Seniority can not be less than Date of Birth ");
				$("#date_of_seniority").focus();
		 		return false;
			}
		
		if ($("select#rank").val() == "0") {
			alert("Please Select Rank");
			$("select#rank").focus();
			return false;
		}
		if ($("#date_of_rank").val() == "") {
			alert("Please Select Date of Rank");
			$("#date_of_rank").focus();
			return false;
		}
		if (dt_of_birth1 > dt_rank1) {
			alert(" Date of Rank can not be less than Date of Birth ");
			$("#date_of_rank").focus();
	 		return false;
		}
	 	if ($("#date_of_birth").val() == "") {
			alert("Please Select Date of Birth");
			$("#date_of_birth").focus();
			return false;
		}  
		if ($("select#parent_arm").val() == "0") {
			alert("Please Enter Parent Arm/Service");
			$("select#parent_arm").focus();
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
		 if($("#date_of_authority").val()== "dd/MM/yyyy"){
		 	$("#date_of_authority").val(null);
	 	 }

		 if($("#date_of_rank").val()== "dd/MM/yyyy"){
		 	$("#date_of_rank").val(null);
		 }
		 
		 return true;
	
}


$(document).ready(function() {
	
	var personnel_no ='${view_Search_Com_letterCMD.personnel_no}';
	if(personnel_no.substring(0, 2).toUpperCase() =="NR" || personnel_no.substring(0, 2).toUpperCase() == "NS"){
		   $('#divpnai_no').show();
		   $('#divcadet_no').hide();		   
	}

	
	/* if('${status5}' == 3){
		 $("#div_reject_remarks").show();
	 }
	 else{
		 $("#div_reject_remarks").hide();
	 } */
	 
	$("#statusV").val('${status5}');
	$("#unit_sus_noV").val('${unit_sus_no5}');
	$("#unit_posted_toV").val('${unit_posted_to5}');
	$("#parent_armV").val('${parent_arm5}');
	$("#personnel_noV").val('${personnel_no5}');
	$("#nameV").val('${name5}');
	$("#type_of_comm_grantedV").val('${type_of_comm_granted5}');
	$("#date_of_commissionV").val(ParseDateColumncommission('${date_of_commission5}'));
	$("#frm_dtV").val('${frm_dt5}');
	$("#to_dtV").val('${to_dt5}');
	
	  
	 $('#course_val').val( '${view_Search_Com_letterCMD.course}');	
	 $('#course').text($( "#course_val option:selected" ).text());
	 $('#rank_val').val( '${view_Search_Com_letterCMD.rank}');	
	 $('#rank').text($( "#rank_val option:selected" ).text());
	 $('#gender_val').val( '${view_Search_Com_letterCMD.gender}');	
	 $('#gender').text($( "#gender_val option:selected" ).text());
	 $('#type_of_comm_granted_val').val( '${view_Search_Com_letterCMD.type_of_comm_granted}');	
	 $('#type_of_comm_granted').text($( "#type_of_comm_granted_val option:selected" ).text());
	 $('#parent_arm_val').val( '${view_Search_Com_letterCMD.parent_arm}');	
	 $('#parent_arm').text($( "#parent_arm_val option:selected" ).text());
	 if('${view_Search_Com_letterCMD.regiment}' == 0){
		 $('#regiment').text("");
	 }
	 else{
		 $('#regiment_val').val( '${view_Search_Com_letterCMD.regiment}');	
		 $('#regiment').text($( "#regiment_val option:selected" ).text());
	 }
	 
	 
	 $("#unit_sus_no").text('${view_Search_Com_letterCMD.unit_sus_no}');	
	
	 $('#date_of_authority').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_authority}')); 
	 $('#date_of_commission').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_commission}')); 
	 $('#date_of_seniority').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_seniority}')); 
	 $('#date_of_birth').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_birth}')); 
	 $('#date_of_rank').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_rank}')); 
	 $('#date_of_tos').text(convertDateFormate('${view_Search_Com_letterCMD.date_of_tos}')); 		
	 $("#parent_sus_no").text('${view_Search_Com_letterCMD.parent_sus_no}');    
     $("#parent_unit").text('${view_Search_Com_letterCMD.parent_unit}');				
	 
	var sus_no ='${view_Search_Com_letterCMD.unit_sus_no}';
	getunit(sus_no);
	function getunit(val) {	
        $.post("getTargetUnitNameList?"+key+"="+value, {
        	sus_no : sus_no
    }, function(j) {
            
            

    }).done(function(j) {
            for (var i = 0; i < j.length; i++) {
				   var length = j.length-1;
	                   var enc = j[length].substring(0,16); 
	                   $("#unit_posted_to").text(dec(enc,j[0])); 
			}
    }).fail(function(xhr, textStatus, errorThrown) {
    });
} 
	
});


	 jQuery(function($){ 
		 
		 datepicketDate('date_of_rank');
		 datepicketDate('date_of_commission');
		 datepicketDate('date_of_seniority');
		 datepicketDate('date_of_birth');
		 datepicketDate('date_of_authority');
		 datepicketDate('date_of_birth');
		
   });

	
	

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
	
	

	 
	 /*age calculation form dob */

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
	    
	     if(year < 17)
	     {
	    	 alert("Please enter age above 17");
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
		 
		 
		 $.post("getPersonnelNoAlreadyExist?"+key+"="+value, {personnel_no : personnel_no}).done(function(j) {
			
			 	if(j == false){
					alert("Personal Number already Exist.")
					$("select#persnl_no1").val("-1");
					$("input#persnl_no2").val("");
					$("input#persnl_no3").val("0");
				} 
				
			}); 
	 }
	 
	 function onlyAlphabets(e, t) {
		    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
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
  </script>





