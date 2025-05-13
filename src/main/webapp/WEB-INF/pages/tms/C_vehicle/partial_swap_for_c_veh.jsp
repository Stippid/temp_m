<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="partial_swap_for_c_veh" id="partial_swap_for_c_veh" action="partial_swap_for_c_vehAction" method="post" class="form-horizontal" commandName="partial_swap_for_c_vehCMD">
<div class="animated fadeIn">
	<div class="">
		<div class="container" align="center">
			<div class="card">
			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>Partial Swap FOR C VEHICLE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
			
					<div class="card-body card-block">
						<div class="col-md-12">
	     					<div class="col-md-4">
	     							<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Source SUS No </label>		
               			        	<input type="text" id="source_sus_no" name="source_sus_no" maxlength="8" placeholder="Source SUS No" class="form-control autocomplete" autocomplete="off" ><!-- oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('source_sus_no');" > -->
								</div>	
	     					</div>
	      				    <div class="col-md-4">
	      				  	     <div class="col col-md-0" >
									<label class=" form-control-label"><strong style="color: red;">* </strong>Auth No </label>		
               			        	<input type="text" id="authority_no" name="authority_no" maxlength="50" placeholder="Auth No" class="form-control autocomplete" autocomplete="off">
								</div>	
	      				    </div>
	      				   <div class="col-md-4">
	      					<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Target SUS No </label>		
               			        	<input type="text" id="target_sus_no" name="target_sus_no" maxlength="8" placeholder="Target SUS No " class="form-control autocomplete" autocomplete="off">
								</div>	
	      				   </div>
    				   </div>
						
						<div class="col-md-12">
	                       <div class="col-md-4">
	                       			<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Source Unit Name </label>	
									<textarea id="source_unit_name" name="source_unit_name" maxlength="10" placeholder="Source Unit Name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>	
								</div>	
	                       </div>
	                      <div class="col-md-4">
	                      			
	                      </div>
	                      <div class="col-md-4">
	                      		<div class="col col-md-0">
									<label class=" form-control-label"> <strong style="color: red;">* </strong>Target Unit Name</label>	
									<textarea id="target_unit_name" name="target_unit_name" maxlength="10" placeholder="Target Unit Name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>		
								</div>	
	                      </div>
                        </div>
						
								
						 <div class="col-md-12" style="margin-top: 20px;">
                        	 <div class="col-md-4">
	                      		<div class="col col-md-0" >
									<select id="lsbox" name="lsbox" class="form-control" size="15" style="width: 100%;"  multiple></select>
								</div>
                        	</div>
                        	<div class="col-md-4">
	                       		<div class="col col-md-0" style="text-align : center;" >
	                       			<button type="button" id="btnRight" class="btn btn-success btn-sm" class="form-control" >  ADD  </button><br><br>				   
					            	<button type="button"  id="btnLeft" class="btn btn-danger btn-sm" class="form-control">  REMOVE  </button> 	
					            </div>
	                       </div>
	                       <div class="col-md-4">
	                      		<div class="col col-md-0" >
	                      			<select id="ba_no" name="ba_no" size="15" class="form-control" style="width: 100%;" multiple></select>
	                      		</div>
	                      	</div>
                        </div>
						
										
				</div>	
					<input type="hidden" id="app" name="app" />
					<input type="hidden" id="app1" name="app" />
							
				<div class="card-footer" align="center" id="btnhide" style="display: block;">			
					<a href="partial_swap_for_c_veh" class="btn btn-success btn-sm" >Clear</a>  
		            <input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return validate();">
		           
			    </div> 				
			    
			</div>
		</div>
	</div>
</div>
</form:form>
<script>
function susStatusCheck(e)
{
	var sus = e;
	if($("source_sus_no").value != '')
	{
		$.post("getSUSStatus?"+key+"="+value, {sus : sus}).done(function(j) {	
			if (j > 0)
				{
					alert("Unit Status - InActive.");				
				}
			else
				{
					alert("Unit Status - Active.")
				}
		});	
	}
}
function unitNameStatusCheck(e)
{
	var unitName = e;
	if($("source_unit_name").value != '')
	{
		
	 	   	$.post("getUnitNameStatus?"+key+"="+value, {unitName:unitName}, function(j) {				

			}).done(function(j) {
				if (j > 0)
					{
						alert("Selected Unit is Currently Inactive.");				
					}
				else
					{
						alert("Selected Unit is Currently Active.")
					}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
	}
}
</script>
<script>
$('#btnRight').click(function(e) {
    var selectedOpts = $('#lsbox option:selected');
    if (selectedOpts.length == 0) {
    	alert("Please Select Source or Target.");
        e.preventDefault();
    }
    $('#ba_no').append($(selectedOpts).clone());
    $(selectedOpts).remove();
    e.preventDefault();
});
$('#btnLeft').click(function(e) {
    var selectedOpts = $('#ba_no option:selected');
    if (selectedOpts.length == 0) {
    	alert("Please Select Source or Target.");
        e.preventDefault();
    }
   	$('#lsbox').append($(selectedOpts).clone());
    $(selectedOpts).remove();
    e.preventDefault();
});
</script>
<script>
$(function() {

	$("input#source_sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#source_sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getSourceSUSNoList1?"+key+"="+value,
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("source_sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var sus_no = ui.item.value;
			    	
				            $.post("getSourceUnitNameList?"+key+"="+value,{source_sus_no:sus_no}, function(data) {
				            }).done(function(data) {
				            	var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					        	$("#source_unit_name").val(dec(enc,data[0]));
		  	   	        		susStatusCheck(ui.item.value);
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
  			      
			    	 	var list = document.getElementById("lsbox");
			    	 	
			            $.post("getPartialEMnoList?"+key+"="+value,{source_sus_no : sus_no}, function(j) {
			            }).done(function(j) {
			            	var options;
		  					for ( var i = 0; i < j.length; i++) {
		  						options += '<option value="'+ j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   			
		  					}	
		  					$("select#lsbox").html(options); 
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			      } 	     
			});	
			  
			  $("#ba_no").empty();
	});
	

     $("#source_unit_name").keypress(function(){
  			var unit_name = this.value;
  				 var susNoAuto=$("#source_unit_name");
  				  susNoAuto.autocomplete({
  				      source: function( request, response ) {
  				        $.ajax({
  				      	type: 'POST',
				    	url: "getSourceUNITNAME?"+key+"="+value,
  				        data: {unit_name:unit_name},
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
  				        	  alert("Please Enter Approved Unit Name.");
  				        	  document.getElementById("source_unit_name").value="";
  				        	  susNoAuto.val("");	        	  
  				        	  susNoAuto.focus();
  				        	  return false;	             
  				          }   	         
  				      }, 
  				      select: function( event, ui ) {
  				      	var source_unit_name = ui.item.value;
  				  
			        $.post("getAllUnitDetailsListPartialSwap?"+key+"="+value,{unitName:ui.item.value}, function(j) {
		            }).done(function(j) {
		            	 if(j != "")
			    		 {
				    		 var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#source_sus_no").val(dec(enc,j[0]));
				        	source_unit_name = dec(enc,j[0]);
			    		 }
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
	  				    var list = document.getElementById("lsbox");
	  				
				        $.post("getPartialBANoListFromUnitname?"+key+"="+value,{unit_name : source_unit_name}, function(j) {
			            }).done(function(j) {
			            	var options;
		  					for ( var i = 0; i < j.length; i++) {
		  						options += '<option value="'+ j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   			
		  					}	
		  					$("select#lsbox").html(options); 
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
  				      } 	     
  				}); 			
  		});
    

});
</script>
<script>
$(function() {

	$("input#target_sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#target_sus_no");
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("target_sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var sus_no = ui.item.value;
			    
				        $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
			            }).done(function(j) {
			            	var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#target_unit_name").val(dec(enc,j[0]));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
  			     } 	     
			});	
	});
	
   
	
    $("#target_unit_name").keypress(function(){
 			var unit_name = this.value;
 				 var susNoAuto=$("#target_unit_name");
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
 				        	  alert("Please Enter Approved Unit Name.");
 				        	  document.getElementById("target_unit_name").value="";
 				        	  susNoAuto.val("");	        	  
 				        	  susNoAuto.focus();
 				        	  return false;	             
 				          }   	         
 				      }, 
 				      select: function( event, ui ) {
 				    	 var target_unit_name = ui.item.value;
 				    
 				       $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(j) {
			            }).done(function(j) {
			            	 var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#target_sus_no").val(dec(enc,j[0]));
					    	 
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
 				      } 	     
 				}); 			
 		});
    
    
});
</script>
<script>
function validate(){
	document.getElementById("ba_no").multiple = true;
	var x = document.getElementById("ba_no").length;
	var ba = document.getElementById("ba_no");

	for(var i =0;i<x;i++){
		if(i == 0)
		{
			var str1 = ba.options[i].value;
		}
		else
		{
			var str1 = str1 + ',' + ba.options[i].value;	 
		}
	    document.getElementById("app").value = str1; 
	}

	if($("#source_sus_no").val() == ""){
		alert("Please Enter the Source SUS No.");
		$("#source_sus_no").focus();
		return false;
	}
	if($("#target_sus_no").val() == ""){
		alert("Please Enter the Target SUS No.");
		$("#target_sus_no").focus();
		return false;
	}
	if($("#authority_no").val() == ""){
		alert("Please Enter the Authority No.");
		$("#authority_no").focus();
		return false;
	}
	if($("#target_unit_name").val() == ""){
		alert("Please Select Proper SUS NO.");
		$("#target_sus_no").focus();
		return false;
	}
	
	if($("#app").val() == ""){
		alert("Please select EM NO from Source Unit.");
		$("#ba_no").focus();
		return false;
	}

	if($("#target_sus_no").val() == $("#source_sus_no").val())
    {
    		alert("Source and Target SUS No should not be same.");
    		return false
  		
    }
	localStorage.setItem("source_sus_no", document.getElementById("source_sus_no").value);
	var e = document.getElementById("SelectLm");
	var strUser = e.options[e.selectedIndex].value;
	return true;
}
function clear(){   
	document.getElementById("partial_swap").reset();
}
</script>