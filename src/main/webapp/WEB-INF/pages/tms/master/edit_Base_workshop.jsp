<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="#" id="#" action="edit_base_workshopAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="edit_base_workshopCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
				<div class="card-header"><h5>Base WorkShop<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
									 <input type="hidden" id="id" name="id" value="${edit_base_workshopCMD.id}"/>
										<input type="text" id="sus_no" name="sus_no"   maxlength="8" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name  </label>
									</div>							
									<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
									     </div>
									
									
								</div>
							</div>
						</div>
						</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
					
					<input type="submit"  class="btn btn-primary btn-sm" value="Update" onclick=" return validate();" >
					
				
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
</form:form>


<script>


$(function() {
	
	 	$("#unit_name").keypress(function(){
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
			        	  document.getElementById("sus_no").value="";
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
		    				$("#sus_no").val(dec(enc,data[0]));
					        
						 
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			      
				} 	     
			}); 			
		});
	
	$("input#sus_no").keypress(function(){
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
		        	  alert("Please Enter Approved SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		    
		        $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
		            }).done(function(data) {
		        		
		  			  	var length =  data.length-1;
			        	var enc =  data[length].substring(0,16);
			        	$("#unit_name").val(dec(enc, data[0]));	
				      
					 
					  
		            }).fail(function(xhr, textStatus, errorThrown) {
		            }); 	
 			     } 	     
		});	
	});	
});

function validate() {
	 $.ajaxSetup({
       async : false
	});
if ($("input#sus_no").val() == "") {
	alert("Please Enter Sus No");
	$("input#sus_no").focus();
	return false;
} 
if ($("input#unit_name").val() == "") {
	alert("Please Enter Unit Name");
	$("input#unit_name").focus();
	return false;
}

return true;
}
$(document).ready(function() {
 
	
		
	
	$("#sus_no").val('${edit_base_workshopCMD.sus_no}');
	$("#unit_name").val('${edit_base_workshopCMD.unit_name}');
	
	
	
});
	</script>