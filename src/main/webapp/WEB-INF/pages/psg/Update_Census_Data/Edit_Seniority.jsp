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

<div class="container-fluid" align="center">
	<form:form name="Edit_Seniority_Details" id="Edit_Seniority_Details" action="Edit_Seniority_Action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Edit_SeniorityCMD">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header"><h5>UPDATE SENIORITY</h5><h6 class="enter_by">
							<span style="font-size: 12px; color: red;">(To be entered by MISO)</span></h6>
						</div>
						<div class="card-body card-block">			
							<div class="col-md-12" >	
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" readonly autocomplete="off" 
						                   maxlength="9" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>	
	              			   
							</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">   Rank </label>
									</div>
									<div class="col-md-8">
										<label class=" form-control-label" id="p_rank"></label> 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Name </label>
									</div>
									<div class="col-md-8">
										<label class=" form-control-label" id="cadet_name"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
	                          <div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Unit SUS No </label>
									</div>
									<div class="col-md-8">
										<label class=" form-control-label" id="app_sus_no"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Unit Name </label>
									</div>
									<div class="col-md-8">
										<label class=" form-control-label" id="app_unit_name"></label>
									</div>
								</div>
							</div>	
						</div>
						<div class="col-md-12" >										
              				<div class="col-md-6">
									<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;"></strong>Old Date of Seniority </label>
					                </div>
					                <div class="col-md-8">
					               		<label id = "old_seniority"></label>
					                </div>
					            </div>
						  	</div>
							<div class="col-md-6">
				               <div class="col-md-4">
				                    <label class=" form-control-label"><strong style="color: red;">* </strong> New Date of Seniority </label>
				                </div>
				                <div class="col-md-8">			                  
									<input type="text" name="date_of_seniority" id="date_of_seniority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
									onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
									onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
				                </div>
				            </div>
						</div>
						<div class="col-md-12">	              					
              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> Reason  </label>
					                </div>
					                <div class="col-md-8">
					                   <input type="text" id="reason" name="reason" class="form-control-sm form-control autocomplete" autocomplete="off" 
					                    maxlength="100" onkeyup="return specialcharecter(this)"> 
					                </div>
					            </div>
              				</div>	            				
	              			<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">* </strong> Date From Which Change in Seniority is Effective </label>    </div>	                
					                <div class="col-md-8">
					                  <input type="text" name="date_of_applicability" id="date_of_applicability" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control-sm form-control" style="width: 93%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"
										max="${maxDate}" >
					                </div>
					            </div>
              				</div>	   	     				
              			</div>
							<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control-sm form-control autocomplete" autocomplete="off" 
						                   maxlength="100" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> * </strong> Date of Authority </label>
						                </div>
						                <div class="col-md-8">
						                    <input type="hidden" id="id" name="id" value="0" class="form-control-sm form-control" />  
						                  <input type="text" name="date_of_authority" id="date_of_authority" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control-sm form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
						</div>		
						<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                   
						<input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">				
						<div class="card-footer" align="center" class="form-control">
							<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="return validate_insertSeniority_save();">
						 <a href="Search_SeniorityUrl" class="btn btn-info btn-sm" >Back</a>  
						</div>
					</div>
				</div>
		   </div>
	</form:form>
</div>
<script>
$(document).ready(function() {
	$("#personnel_no").val('${personnel_no5}');
	$("#authority").val('${Edit_SeniorityCMD.authority}');
	$("#reason").val('${Edit_SeniorityCMD.reason}');
	var date_of_authority =ParseDateColumncommission('${Edit_SeniorityCMD.date_of_authority}');		
	$('#date_of_authority').val(date_of_authority);	
	
	var date_of_seniority =ParseDateColumncommission('${Edit_SeniorityCMD.date_of_seniority}');		
	$('#date_of_seniority').val(date_of_seniority);	
	
	var applicablity_date =ParseDateColumncommission('${Edit_SeniorityCMD.applicablity_date}');		
	$('#date_of_applicability').val(applicablity_date);	
	
	$("#id").val('${Edit_SeniorityCMD.id}');
	
	personal_number();
});

jQuery(function($){ //on document.ready  
	datepicketDate('date_of_authority');
	datepicketDate('date_of_seniority');
	datepicketDate('date_of_applicability');
	
});

function personal_number()
{
	
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	
	var personnel_no = $("input#personnel_no").val();
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	    	$("#comm_id").val(j[0][0]);

	   	 var comm_id =j[0][0]; 
	   	 $.post('GetCensusData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	   		 if(k.length > 0){
	   			  $('#census_id').val(k[0].id);  
	   		 }	 
		   }); 	 
	   	 $.post('getoldseniorityDate1?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	   		 if(k.length > 0){
	   			    $('#id').val(k[0][0]); 
		   			$('#authority').val(k[0][1]); 
		   			$('#date_of_authority').val(ParseDateColumncommission(k[0][2])); 
		   			$('#date_of_seniority').val(ParseDateColumncommission(k[0][3])); 
		   			$('#old_seniority').text(k[0][4]); 
	   		 }	  
	  });
  });
	 
} 
function personal_number() {

	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	
	 var personnel_no = $("input#personnel_no").val();
		
		 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
			 if(j!=""){
							$("#comm_id").val(j[0][0]);
							
							$.ajaxSetup({
								async : false
							});
							var comm_id = j[0][0];
							$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
								if (k.length > 0) {
									$('#census_id').val(k[0][1]);
								
									curr_marital_status = k[0][13];
									$("#cadet_name").text(k[0][2]);
									$("#p_rank").text(k[0][3]);
									$("#hd_p_rank").val(k[0][3]);
									$("#app_sus_no").text(k[0][7]);
									
									$.ajaxSetup({
										async : false
									});
									var sus_no = k[0][7];
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												//alert("unit---" + dec(enc,j[0]));
												$("#app_unit_name").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
							}
						});

						}
			 
			   	$.post('getoldseniorityDate?' + key + "=" + value,{ comm_id : comm_id },function(k) {
			   		 if(k.length > 0){
			   			   
				   			$('#old_seniority').text((k[0][0]));
				   			
				   		}	 
			       });
					});
		 
		
}
$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
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
		    		 personal_number();
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  
		      } 	     
		}); 	
});


function validate_insertSeniority_save(){

	count();
	
	if ($("input#personnel_no").val() == "") {
		alert("Please Enter Personnel Number.");
		$("input#personnel_no").focus();
		return false;
	}  
    if ($("input#date_of_seniority").val() == "" || $("input#date_of_seniority").val() == "DD/MM/YYYY") {
		alert("Please Select New Date of Seniority ");
		$("input#date_of_seniority").focus();
		return false;
	 } 
    if ($("input#reason").val() == "") {
		alert("Please Enter Reason ");
		$("input#reason").focus();
		return false;
	}
	if ($("input#date_of_applicability").val() == "" || $("input#date_of_applicability").val() == "DD/MM/YYYY") {
		alert("Please Select Effective Date of Seniority");
		$("input#date_of_applicability").focus();
		return false;
	 } 
	if ($("input#authority").val() == "") {
		alert("Please Enter Authority ");
		$("input#authority").focus();
		return false;
	}
	 if ($("input#date_of_authority").val() == "" || $("input#date_of_authority").val() == "DD/MM/YYYY") {
		alert("Please Select Date of Authority ");
		$("input#date_of_authority").focus();
		return false;
	} 
   
	var personnel_no=$('#personnel_no').val();
	var authority=$('#authority').val();
	var Sen_id=$('#id').val();
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	var date_of_authority=$('#date_of_authority').val();
	var date_of_seniority=$('#date_of_seniority').val();
	var date_of_applicability=$('#date_of_applicability').val();
	var reason=$('#reason').val();
	
	   $.post('Seniority_Details_Action?' + key + "=" + value, {personnel_no:personnel_no,authority:authority,census_id:census_id,comm_id:comm_id,date_of_authority:date_of_authority,
		   date_of_seniority:date_of_seniority,Sen_id:Sen_id,date_of_applicability:date_of_applicability,reason:reason}, function(data){
		      
	          if(data=="update")
	        	  alert("Data Updated Successfully");
	          else if(parseInt(data)>0){
	        	 $('#id').val(data);
	        	  alert("Data Saved SuccesFully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}

function count(){
	
	var comm_id=$('#comm_id').val();
	$.post('getDateofseniorityAlreadyExistCount?' + key + "=" + value, {comm_id:comm_id}, function(j){
		if(j > 0){
			alert("Date of Seniority Record is in Pending for Approval in Change of Commissioning Header.");
			return false;
		}
	 });
}

</script>	
		

