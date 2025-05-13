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
<div id="div_extention" class="tabcontent" style="display: block;">
	<form:form id="Edit_extention" action="Edit_extention_Action?${_csrf.parameterName}=${_csrf.token}" method="post" commandName="Edit_extention_CMD">
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    	   <div class="card">
	    		<div class="card-header"><h5>UPDATE EXTENTION</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by Unit)</span></h6></div>
	          		<div class="card-body card-block">
				<br> 
				 <div class="col-md-12">	              					
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						                <div class="col-md-8">
						                    <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  onchange="return personal_number();" > 						                   
						               		<input type="hidden" id="comm_id" name="comm_id" value="${comm_id}" class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="census_id" name="census_id" value="${census_id}"  class="form-control autocomplete" autocomplete="off">
											<input type="hidden" id="r_id" name="r_id" value="${r_id}"  class="form-control autocomplete" autocomplete="off" value="0">
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
	        			            				
	              			</div>		              								
						
							<div class="card-footer" align="center" class="form-control">
								<a href="extentionUrl" class="btn btn-success btn-sm" >Clear</a> 
								<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="Extention_save_fn();"> 	
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
	
	getExtentionData();
	personal_number();
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
	
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
	 /*  function formateDate(value){
		var date = new Date(value);
		var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
		return formattedDate;
	}   */
	function getExtentionData()
	{
		 var comm_id1 = $('#comm_id').val(); 
		 var census_id1 = $('#census_id').val();  
		// alert( comm_id1 +"------" +census_id1)
		 if(comm_id1 !="" && census_id1 !="")
			 {
			
			 $.post('getExtentionData?' + key + "=" + value,{ comm_id : comm_id1,ch_id:census_id1  },function(h) {
		 	    	
	    		 if(h.length != 0)
	    		 {	    			 
	    			
	    			 
	    			 $('#r_id').val(h[0].id); 
	    			var from_dt=new Date(h[0].from_dt).toISOString().split('T')[0];
	    			 /* var from_dt = formateDate(j[i].from_dt); */
	    			 
	    			 $('#from_dt').val(from_dt); 
	    			 var to_dt=new Date(h[0].to_dt).toISOString().split('T')[0];
	    			 $('#to_dt').val(to_dt); 
	    			  
	    		 }
	   });	
		}  
		}

	function Extention_save_fn()
	{
		 if($("input#personnel_no").val()==""){
				alert("Please select Personal No");
				$("input#personnel_no").focus();
				return false;
			}
			/* if($("input#auth_no").val()==""){
				alert("Please Enter Authority No");
				$("input#auth_no").focus();
				return false;
			}
			if ($("input#auth_dt").val() == "" || $("#auth_dt").val() == "DD/MM/YYYY") {
				alert("Please Select  Date of Authority ");
				$("input#auth_dt").focus();
				return false;
			} */
			if ($("input#from_dt").val() == "" || $("#from_dt").val() == "DD/MM/YYYY") {
				alert("Please Select  From Date ");
				$("input#from_dt").focus();
				return false;
			} 
			
			var from_dt = $("#from_dt").val();
			 var to_dt = $("#to_dt").val();
			 var date_of_tos1 = ParseDateColumncommission($("#date_of_tos").text());
			
			  if(getformatedDate(date_of_tos1) > getformatedDate(from_dt)) {
					alert(" From Date can not be less than Date of TOS");
					$("#from_dt").focus();
					return false;
				}
			   if ($("input#to_dt").val() == "" || $("#to_dt").val() == "DD/MM/YYYY") {
					alert("Please Select  To Date ");
					$("input#To_dt").focus();
					return false;
				}
				 if(getformatedDate(date_of_tos1) > getformatedDate(to_dt)) {
						alert("To Date can not be less than Date of TOS");
						$("#to_dt").focus();
						return false;
					}
				
				 if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
						alert("To Date can not be less than From Date");
						$("#to_dt").focus();
						return false;
					}
				 
				 return true;
	}
	
	</script>
