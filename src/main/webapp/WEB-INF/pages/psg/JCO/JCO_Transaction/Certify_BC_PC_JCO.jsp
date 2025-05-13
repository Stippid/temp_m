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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


<div class="container-fluid" align="center">
	<form:form name="certify_BCPC_JCO" id="certify_BCPC_JCO" action="certify_BCPC_JCOAction?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="certify_BCPC_JCOCMD">
			<div class="animated fadeIn">
				<div class="container-fluid" align="center">
					<div class="card">
						<div class="card-header"><h5> ENTER DETAILS OF INDLS DECLARED AS BATTLE CASUALTY JCO/OR</h5><h6 class="enter_by">
							<span style="font-size: 12px; color: red;">(To be entered by MISO)</span></h6>
						</div>
						<div class="card-body card-block">			
							<div class="col-md-12" >	
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Army No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
						                   maxlength="12" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)"> 
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
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>Name </label>    </div>
						                
						                <div class="col-md-8">
						                 <label id="name" name="name"> </label>
						                </div>
						            </div>
	              				</div>	
							<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>Rank </label>    </div>
						                
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
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>DOB </label>    </div>
						                
						                <div class="col-md-8">
						                 <label id="dob" name="dob"> </label>
						                </div>
						            </div>
	              				</div>									
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong style="color: red;">* </strong>Unit </label>    </div>
						                
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
						                    <label class=" form-control-label"> <strong style="color: red;">* </strong> Authority  </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="authority" name="authority" class="form-control autocomplete" maxlength="100" autocomplete="off" 
				                     onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>											
								<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Date of Authority </label>
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
						</div>		
						<input type="hidden" id="status" name="status" class="form-control autocomplete" autocomplete="off"  >	
						<input type="hidden" id="jco_id" name="jco_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                   
						<div class="card-footer" align="center" class="form-control">
							<a href="Certify_BC_PC_Url_JCO" class="btn btn-success btn-sm">Clear</a> 
							<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="return battle_casualty_save();">
						</div>
					</div>
				</div>
		   
	</form:form>
</div>
</div>
<c:url value="Search_Certify_BC_PC_JCO" var="Search_BattleCAUrl" />
<form:form action="${Search_BattleCAUrl}" method="get" id="redirectForm" name="redirectForm" modelAttribute="id1">
</form:form>

<script>

function redirect(){
	
	document.getElementById('redirectForm').submit();
	
}


$(document).ready(function() {
	
	
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
	
	var army_no = $("input#personnel_no").val();
//		alert("hello---" + personnel_no);
	$.post('GetArmyNoData?' + key + "=" + value,{army_no : army_no},function(j) {
						if (j != "") {
							$("#jco_id").val(j[0][0]);
							var jco_id = j[0][0];
							$.post('GetCensusDataApproveJCO?' + key+ "=" + value,{jco_id : jco_id},function(k) {
								if (k.length > 0) {
									$("#name").text(k[0].full_name);
									$("#rank").text(k[0].rank);
// 									$("#hd_p_rank").val(k[0].rank);
									var dob = ParseDateColumncommission(k[0].date_of_birth);
									$("#dob").text(dob);
									
									$.ajaxSetup({
										async : false
									});
									
									var sus_no = k[0].unit_sus_no;
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												$("#unit").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
									$.ajaxSetup({
										async : false
									});
									 
							}
						});
							
						}
					});
	
	 }
$("input#personnel_no").keypress(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noList_Certify_BCPC?"+key+"="+value,
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


function battle_casualty_save(){

	if ($("input#personnel_no").val().trim() == "") {
		alert("Please Enter Personnel Number.");
		$("input#personnel_no").focus();
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
	 var casualty = $("input#date_of_casualty").val().trim();
    if (casualty == "" || casualty == "DD/MM/YYYY") {
		alert("Please Select Date of Casualty");
		$("input#date_of_casualty").focus();
		return false;
	 } 
    
    
	var personnel_no=$('#personnel_no').val();
	var authority=$('#authority').val();
	var Sen_id=$('#id').val();
	var jco_id=$('#jco_id').val();
	var date_of_authority=$('#date_of_authority').val();
	
	   $.post('certify_BCPC_JCOAction?' + key + "=" + value, {personnel_no:personnel_no,authority:authority,jco_id:jco_id,date_of_authority:date_of_authority,casualty:casualty,
		   Sen_id:Sen_id}, function(data){
		      
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
	   //alert(Sen_id)
	   if(Sen_id > 0)
	   redirect();
}

</script>	
		

