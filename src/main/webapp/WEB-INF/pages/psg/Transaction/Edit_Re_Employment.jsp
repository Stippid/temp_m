<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<div id="div_re_employment" class="tabcontent" style="display: block;">
	<form:form id="Edit_form_div_re_employment" action="Edit_re_employment_Action?${_csrf.parameterName}=${_csrf.token}" method="post" commandName="Edit_re_employment_CMD">
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    	   <div class="card">
	    		<div class="card-header"><h5> RECALLED FROM RESERVE/RE-EMPLOYMENT</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by Unit)</span></h6></div>
	          		<div class="card-body card-block">
				<br> 
				 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Recalled From Reserve/Re-Employment </label>
						                </div>
						                <div class="col-md-8">
						                 	<form:select id="re_emp_select" path="re_emp_select" class="form-control" >
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${getRe_EmploymentTypeList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
						                   <!-- <input type="text" id="cause_of_release" name="cause_of_release" class="form-control autocomplete" autocomplete="off" >  -->
						                </div>
						            </div>
	              				</div>	
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  onchange="return personal_number();" > 						                   
						               							                   
						          
						                </div>
						            </div>
	              				</div>
	              				</div>
				
				<div class="col-md-12" >	              					
	              				

							
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Name </label>
											</div>
											<div class="col-md-8">
												<label id="name" name="name"> </label>
											</div>
										</div>
									</div>
								
								
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"> Rank </label>
											</div>
											<div class="col-md-8">
												<label id="rank" name="rank"> </label>
											</div>
										</div>
									</div>
									
									</div>
									
									<div class="col-md-12">
				  	<label class=" form-control-label"  style="margin-left:10px;"><h4>AUTHORITY</h4></label>
				 </div>
				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Authority No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" autocomplete="off" > 
						                   
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="auth_dt" name="auth_dt" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	
	              			<br>
	              			</div>
	              		
				<div class="col-md-12">
				  	<label class=" form-control-label"  style="margin-left:10px;"><h4>EMPLOYMENT</h4></label>
				 </div>
				 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Granted (From Date) </label>
						                </div>
						                <div class="col-md-8">
						                 	<input type="Date" id="granted_fr_dt" name="granted_fr_dt" class="form-control autocomplete" autocomplete="off">
						                   <!-- <input type="text" id="cause_of_release" name="cause_of_release" class="form-control autocomplete" autocomplete="off" >  -->
						                </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Date of TOS </label>
								</div>
								<div class="col-md-8">
									<input type="Date" id="date_of_tos" name="date_of_tos" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
	              				
	              				
	              				</div>
				 
				 <div class="col-md-12">	 
					 
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Unit SUS No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off">
									<input type="hidden" id="comm_id" name="comm_id" value="${comm_id}" class="form-control autocomplete" autocomplete="off">
									<input type="hidden" id="census_id" name="census_id" value="${census_id}"  class="form-control autocomplete" autocomplete="off">
										<input type="hidden" id="r_id" name="r_id" value="${r_id}"  class="form-control autocomplete" autocomplete="off" value="0">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Posted No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						 
						
					</div>	
					
					<div class="col-md-12">	 
					  
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Appt </label>
								</div>
								<div class="col-md-8">
									
									<form:select id="appt" path="appt" class="form-control" >
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${getTypeofAppointementList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Date of Appt</label>
								</div>
								<div class="col-md-8">
									<input type="Date" id="date_of_appt" name="date_of_appt" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						
						
					</div>	
					<div class="col-md-12" id="xlist" style="display: none" >
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> X List SUS No </label>
								</div>
								<div class="col-md-8">
									 <input type="text"   id="x_sus_no" name="x_sus_no" value=""
										class="form-control autocomplete" autocomplete="off">   
					  			 
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> X List Loc </label>
								</div>
								<div class="col-md-8">
									 <textarea    id="x_list_loc" name="x_list_loc" value=""
										class="form-control autocomplete" autocomplete="off">   </textarea>
								</div>
							</div>
						</div>
					</div>
					
					
					
				 
		 						
	              			<div class="col-md-12">
				  	<label class=" form-control-label"  style="margin-left:10px;"><h4>EXTENTION</h4></label>
				 </div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> From Date </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="from_dt" name="from_dt" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> To Date </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="to_dt" name="to_dt" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	
	              				<br>
	              				</div>
	              			<%-- <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Course of Release From Re Employment  </label>
						                </div>
						                <div class="col-md-8">
						                 	<select name="cause_of_release" id="cause_of_release" class="form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getNon_EffectiveList}" varStatus="num">
												<option value="${item[0]}" >${item[1]}</option>
												</c:forEach>
											</select>
						                   <!-- <input type="text" id="cause_of_release" name="cause_of_release" class="form-control autocomplete" autocomplete="off" >  -->
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Date of Release From Re Employment  </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="date" id="dt_of_release" name="dt_of_release" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	  
	              				</div>
	              			 --%>	
	              								            				
	              			</div>		              								
						
							<div class="card-footer" align="center" class="form-control">
								<a href="Re_EmploymentUrl" class="btn btn-success btn-sm" >Clear</a> 
								<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="Re_employment_save_fn();"> 	
			            	</div> 			
						</div>
					</div>
				</div>
		</form:form>
	</div>
	<script>
	$(document).ready(function() {

	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
	if ('${personnel_no}' != "") {
		$('#personnel_no').val('${personnel_no}');
	}
	/* if ('${rank}' != "") {
		$('#rank').val('${rank}');
	}	 */
	
	getRe_EmployeementData();
	personal_number();
	
	
});
	
	function personal_number()
	{
		if($("input#personnel_no").val()==""){
			alert("Please select Personal No");
			$("input#personnel_no").focus();
			return false;
		}
		
		
		var personnel_no = $("input#personnel_no").val();
		//alert(personnel_no+ "---function")
		 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
		   	    	
		    	$("#comm_id").val(j[0][0]);	    	    				    	
		    	var comm_id =j[0][0]; 
		    	var name = j[0][5];
		    	var rank = j[0][6];
		    	$("#rank").text(rank);
  			  $("#name").text(name);
		    	//alert("comm_id------" + comm_id  );
		    	 /* $.post('GetCensusData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		 	    	
		    		 if(k.length > 0)
		    		 {	    			 
		    			  $('#census_id').val(k[0][0]); 
		    			
		    			alert(k[0][0] + "--census_id")
		    			
		    			  $("#rank").text(rank);
		    			  $("#name").text(name);
		    			  getRe_EmployeementData();  
		    		 }
		    		 
		   }); */
	  });
		 $("input#personnel_no").attr('readonly', true);
		 
		
	}
	
	function getRe_EmployeementData()
	{
		 var comm_id1 = $('#comm_id').val(); 
		 var census_id1 = $('#census_id').val();  
		// alert( comm_id1 +"------" +census_id1)
		 if(comm_id1 !="" && census_id1 !="")
			 {
			
			 $.post('getRe_EmployeementData?' + key + "=" + value,{ comm_id : comm_id1,ch_id:census_id1  },function(h) {
		 	    	
	    		 if(h.length != 0)
	    		 {	    			 
	    			//alert(h[0].unit_sus_no);
	    			 $('#unit_sus_no').val(h[0].unit_sus_no);    	
	    			 $('#unit_posted_to').val(h[0].unit_posted_to); 
	    			 $('#r_id').val(h[0].id); 
	    			 $('#appt').val(h[0].appt); 
	    			 $('#re_emp_select').val(h[0].re_emp_select); 
	    			
	    			 $('#x_sus_no').val(h[0].x_sus_no); 
	    			 $('#x_list_loc').val(h[0].x_list_loc); 
	    			 var date_of_appt=new Date(h[0].date_of_appt).toISOString().split('T')[0];	
	    			 $('#date_of_appt').val(date_of_appt); 
	    			 var date_of_tos=new Date(h[0].date_of_tos).toISOString().split('T')[0];	
	    			 $('#date_of_tos').val(date_of_tos); 
	    			 $('#authority').val(h[0].authority); 
	    			 var auth_dt=new Date(h[0].auth_dt).toISOString().split('T')[0];
	    			 $('#auth_dt').val(auth_dt); 
	    			 var from_dt=new Date(h[0].from_dt).toISOString().split('T')[0];
	    			 $('#from_dt').val(from_dt); 
	    			 var to_dt=new Date(h[0].to_dt).toISOString().split('T')[0];
	    			 $('#to_dt').val(to_dt); 
	    			 
	    			 var granted_fr_dt=new Date(h[0].granted_fr_dt).toISOString().split('T')[0];	
	    			 $('#granted_fr_dt').val(granted_fr_dt); 
	    			 $('#re_emp_select').val(h[0].re_emp_select);  
	    			 
	    			 
	    		 }
	   });	
		}  
		}
	
	function Re_employment_save_fn()
	{
		 if($("input#personnel_no").val()==""){
			alert("Please select Personal No");
			$("input#personnel_no").focus();
			return false;
		} 
		 
		 if($("#date_of_tos").val()== "DD/MM/YYYY"){
			 alert("Please Enter TOS Date");
			 $("input#date_of_tos").focus();
			 return false;
		 }
		 return true;
	}

	
	
	</script>
