<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<div class="container-fluid" align="center">
	<form:form name="Edit_BattleCA" id="Edit_BattleCA" action="Edit_BattleCA_Action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="Edit_BattleCACMD">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header"><h5>UPDATE BATTLE CASUALTY</h5><h6 class="enter_by">
							<span style="font-size: 12px; color: red;">(To be entered by Unit)</span></h6>
						</div>
						<div class="card-body card-block">			
							<div class="col-md-12" >	
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" readonly="readonly"
						                   maxlength="9" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)" >  
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Casualty </label>
						                </div>
						                <div class="col-md-8">
						                  
											<input type="text" name="date_of_casualty" id="date_of_casualty" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 93%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
							</div>
							<div class="col-md-12">	  
							<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  	<label class=" form-control-label"><strong style="color: red;">* </strong>Name </label>    
						               </div>				                
						                <div class="col-md-8">
						                 	<label id="name" name="name"> </label>
						                </div>
						            </div>
	              				</div>	
							<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  	<label class=" form-control-label"><strong style="color: red;">* </strong>Rank </label>    
						                </div>
						                <div class="col-md-8">
						                 	<label id="rank" name="rank"> </label>
						                </div>
						            </div>
	              				</div>	               					            						              					              				
	              			</div>
							<div class="col-md-12" >	
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>DOB </label>    
						                </div>
						                <div class="col-md-8">
						                 	<label id="dob" name="dob"> </label>
						                </div>
						            </div>
	              				</div>									
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>Unit </label>    
						                </div>
						                <div class="col-md-8">
						                 	<label id="unit" name="unit"> </label>
						                </div>
						            </div>
	              				</div>	            					              				
	              			</div>
	              			<div class="col-md-12" >
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> <strong style="color: red;"></strong> Authority  </label>
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Date of Authority </label>
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
								<div class="col-md-12" >
	              															
								 <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Approved Casualty Status by MP5(d) </label>
						                </div>
						                <div class="col-md-8">
						             <!--  <input type="hidden" id="parent_armLb" name="parent_armLb" class="form-control autocomplete" autocomplete="off" >  -->
							                 <select name="status_by_mp" id="status_by_mp" class="form-control-sm form-control"    >
												<option value="0">--Select--</option>		
												<option value="1">BC</option>		
												<option value="2">PC</option>												
											</select>
						                </div>
						            </div>
						           </div>
							</div>
							</div>	
						<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                   
						<input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">				
						<div class="card-footer" align="center" class="form-control">
							<input type="button" class="btn btn-primary btn-sm" value="Update" onclick="return validate_insertSeniority_save();">
						 <a href="Search_BattleCAUrl" class="btn btn-info btn-sm" >Back</a>  
						</div>
					</div>
				</div>
		   </div>
	</form:form>
	
	<c:url value="Search_BattleCAUrl" var="Search_BattleCAUrl" />
<form:form action="${Search_BattleCAUrl}" method="get" id="redirectForm" name="redirectForm" modelAttribute="id1">
</form:form>
</div>
<script>
$(document).ready(function() {
	$("#personnel_no").val('${personnel_no5}');
	$("#authority").val('${Edit_battleCMD.authority}');
	
	var date_of_authority =ParseDateColumncommission('${Edit_battleCMD.date_of_authority}');		
	$('#date_of_authority').val(date_of_authority);	
	
	var date_of_casualty =ParseDateColumncommission('${Edit_battleCMD.date_of_casualty}');		
	$('#date_of_casualty').val(date_of_casualty);	
	
	$("#id").val('${Edit_battleCMD.id}');
	$("#status_by_mp").val('${Edit_battleCMD.status_by_mp}');
	personal_number();
});

jQuery(function($){ //on document.ready  
	datepicketDate('date_of_authority');
	datepicketDate('date_of_casualty');
	
});
	
function personal_number()
{
	
	if($("input#personnel_no").val().trim() == ""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	
	var personnel_no = $("input#personnel_no").val().trim();
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	    	$("#comm_id").val(j[0][0]);

	   	 var comm_id =j[0][0]; 
	   	 $.post('GetCensusData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	   		 if(k.length > 0){
	   			  $('#census_id').val(k[0].id);  
	   		 }	 
		   }); 	 
	   	 
	 	$.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id},function(k) {
			
		  	$("#name").text(k[0][2]);
		 	$("#rank").text(k[0][3]);
		  	$("#dob").text(k[0][11].substring(0,10));
		  	var sus_no =k[0][7];

			
    		
			if(sus_no!=null){
				getunit(sus_no);
			
		    	function getunit(val) {	
		            $.post("getTargetUnitNameList?"+key+"="+value, {
		            	sus_no : sus_no
		        }, function(j) {
		                //var json = JSON.parse(data);
		                //...

		        }).done(function(j) {
		    				   var length = j.length-1;
		    	                   var enc = j[length].substring(0,16); 
		    	                   //alert("unit---" + dec(enc,j[0]));
		    	                   $("#unit").text(dec(enc,j[0])); 
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
		    } 
		    
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
			    	url: "getpersonnel_noList_BA_PY_CA?"+key+"="+value,
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

function redirect(){
	
	document.getElementById('redirectForm').submit();
	
}

function validate_insertSeniority_save(){
	if ($("input#personnel_no").val().trim() == "") {
		alert("Please Enter Personnel Number.");
		$("input#personnel_no").focus();
		return false;
	}  
	
	if ($("input#date_of_casualty").val().trim() == "" || $("input#date_of_casualty").val().trim() == "DD/MM/YYYY") {
		alert("Please Select Date of Casualty ");
		$("input#date_of_casualty").focus();
		return false;
	 } 
	
	if ($("input#authority").val().trim() == "") {
		alert("Please Enter Authority ");
		$("input#authority").focus();
		return false;
	}
	 if ($("input#date_of_authority").val().trim() == "" || $("input#date_of_authority").val().trim() == "DD/MM/YYYY") {
		alert("Please Select Date of Authority ");
		$("input#date_of_authority").focus();
		return false;
	} 
    
	
	 var personnel_no=$('#personnel_no').val().trim();
		var authority=$('#authority').val().trim();
		var Sen_id=$('#id').val().trim();
		var census_id=$('#census_id').val().trim();
		var comm_id=$('#comm_id').val().trim();
		var status_by_mp=$('#status_by_mp').val().trim();
		
		var date_of_authority=$('#date_of_authority').val().trim();
		var casualty=$('#date_of_casualty').val().trim();
		
		   $.post('battle_casualtyAction?' + key + "=" + value, {personnel_no:personnel_no,authority:authority,census_id:census_id,comm_id:comm_id,status_by_mp:status_by_mp,date_of_authority:date_of_authority,
			   casualty:casualty,Sen_id:Sen_id}, function(data){
		      
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
	   redirect();
}

</script>	
		

