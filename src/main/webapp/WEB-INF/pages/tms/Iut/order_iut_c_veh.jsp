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
<form:form name="Iutveh_c" id="Iutveh_c" action="IutAction_c?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="IutActionCMD_c">
<div class="animated fadeIn">
	<div>
		<div class="container" align="center">
			<div class="card">
			<div class="card-header"><h5>ORDER IUT C VEH<br></h5></div>			
					<div class="card-body card-block">
						<div class="col-md-12">
	     					<div class="col-md-4">
	     							<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Source SUS No</label>		
               			        	<input type="text" id="source_sus_no" name="source_sus_no" maxlength="8" placeholder="Source SUS No" class="form-control autocomplete" autocomplete="off" ><!-- oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('source_sus_no');" > -->
								</div>	
	     					</div>
	      				    <div class="col-md-4">
	      				  	     <div class="col col-md-0" >
									<label class=" form-control-label"><strong style="color: red;">*</strong> Auth(Enter Appt & Fmn)</label>		
               			        	<input type="text" id="authority_no" name="authority_no" placeholder="Auth No" class="form-control autocomplete" autocomplete="off" maxlength="50">
								</div>	
	      				    </div>
	      				   <div class="col-md-4">
	      					<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Target SUS No</label>		
               			        	<input type="text" id="target_sus_no" name="target_sus_no" maxlength="8" placeholder="Target SUS No " class="form-control autocomplete" autocomplete="off">
								</div>	
	      				   </div>
    				   </div>						
						<div class="col-md-12">
	                       <div class="col-md-4">
	                       			<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Source Unit Name</label>
									<textarea id="source_unit_name" name="source_unit_name" placeholder="Source Unit Name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100"></textarea>		
								</div>	
	                       </div>
	                     
	                      		<div class="col-md-4">
	      				  	     <div class="col col-md-0" >
									<label class=" form-control-label"><strong style="color: red;">*</strong>Username</label>		
               			        	<input type="text" id="username" name="username" value="${roleloginName}" readonly="readonly" class="form-control autocomplete" autocomplete="off" maxlength="50">
								</div>	
	                      </div>
	                       <%--   <div class="col-md-2">
	      				  	     <div class="col col-md-0" >
									<label class=" form-control-label"><strong style="color: red;">*</strong>UserID</label>		
               			        	<input type="text" id="userid" name="userid" readonly="readonly" value="${userId}" class="form-control autocomplete" autocomplete="off" maxlength="50">
								</div>	
	                      </div> --%>
	                      
	                      	
	                     
	                      <div class="col-md-4">
	                      		<div class="col col-md-0">
									<label class=" form-control-label" ><strong style="color: red;">*</strong> Target Unit Name</label>	
									<textarea id="target_unit_name" name="target_unit_name"  placeholder="Target Unit Name" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100"></textarea>	
								</div>	
	                      </div>
                        </div>
                        
                        <div class="col-md-12">
	                       <div class="col-md-4">
	                       			<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">*</strong> VEH CAT</label>
									<select id="type_veh"  class="form-control-sm form-control" name= "type_veh"  style="width: 100%">
											<option value="C">C Vehicles</option>
										</select>		
								</div>	
	                       </div>
	                 
                        </div>
                        
                          <div class="col-md-12">
	               
	                       <div class="col-md-4">
	                       			<div class="col col-md-0">
									<label class=" form-control-label"><strong style="color: red;">*</strong> MCT MAIN ID</label>
									<select id="mct_main" name="mct_main"  class="form-control-sm form-control" style="width: 100%" onchange="getbanoList(this.value)">
										</select>	
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
					<a href="order_iut" class="btn btn-success btn-sm" >Clear</a>   
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
						getMCTMainList();
						
					}	
				}).fail(function(xhr, textStatus, errorThrown) {
			});			 			
	}
}
function unitNameStatusCheck(e)
{
	var unitName = e;
	if($("source_unit_name").value != '')
	{
	
			 	$.post("getUnitNameStatus?"+key+"="+value, {unitName : unitName}).done(function(j) {				
			 		if (j > 0)
					{
						alert("Selected Unit is currently Inactive.");				
					}
				 else
					{
					 alert("getmct");
						alert("Selected Unit is currently Active1.")
						getMCTMainList();
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
	// Source SUS No

	/* $("input#source_sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#source_sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getSusNoActiveList?"+key+"="+value,
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
			        	  alert("Invalid Input. \nPlease Enter Approved SUS No.");
			 			  document.getElementById("source_sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var source_sus_no = ui.item.value;
			    
			    			 	$.post("getSourceUnitNameList?"+key+"="+value, {source_sus_no:source_sus_no}).done(function(j) {				
			    			 		var length = j.length-1;
						        	var enc = j[length].substring(0,16);
						        	$("#source_unit_name").val(dec(enc,j[0]));
			  	   	        		susStatusCheck(ui.item.value);	
			    				}).fail(function(xhr, textStatus, errorThrown) {
			    				});			      	
  			      	var list = document.getElementById("lsbox");
		  	   	     
		  	   			 					
	  				
			      } 	     
			});	
			  
			  $("#ba_no").empty();
	});
	// End   
    // Source Unit Name
 
     $("#source_unit_name").keypress(function(){
  			var unit_name = this.value;
  				 var susNoAuto=$("#source_unit_name");
  				  susNoAuto.autocomplete({
  				      source: function( request, response ) {
  				        $.ajax({
  				        type: 'POST',
  				        url: "getUnitsNameActiveList?"+key+"="+value,
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
  				        	alert("Invalid Input. \nPlease Enter Approved Unit Name.");
  				        	  document.getElementById("source_unit_name").value="";
  				        	  susNoAuto.val("");	        	  
  				        	  susNoAuto.focus();
  				        	  return false;	             
  				          }   	         
  				      }, 
  				      select: function( event, ui ) {
  				     	var source_unit_name = ui.item.value;
  				      
		  	   			 	$.post("getAllUnitDetailsListPartialSwap?"+key+"="+value, {unitName:ui.item.value}).done(function(j) {				
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
	  			
	  				 		 $.post("getPartialBANoListfromUnit?"+key+"="+value, {unit_name : source_unit_name}).done(function(j) {				
		  				 		 	if(j != "")
					  				{
					  					var options;
		 			  					for ( var i = 0; i < j.length; i++) {
		 			  						options += '<option value="'+ j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   			
		 			  					}	
		 			  					$("select#lsbox").html(options);  
					  				}	
	  				 			}).fail(function(xhr, textStatus, errorThrown) {
	  				 			});		  				
		  				unitNameStatusCheck(ui.item.value);  				    	  				
  				      } 	     
  				}); 			
  		});  */  
  		
  		$("#source_sus_no").keyup(function(){
  			
            var sus_no = this.value;
			var susNoAuto=$("#source_sus_no");
			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getSUSNoList_Active_or_Inactive_it?"+key+"="+value,
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
			        	  document.getElementById("source_sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#source_unit_name").val(dec(enc,j[0]));   
		             susStatusCheck(ui.item.value);	   
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				var list = document.getElementById("lsbox");
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
  				        url: "UnitsNameList_active_or_inactive?"+key+"="+value,
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
  				        	alert("Invalid Input. \nPlease Enter Approved Unit Name.");
  				        	  document.getElementById("source_unit_name").value="";
  				        	  susNoAuto.val("");	        	  
  				        	  susNoAuto.focus();
  				        	  return false;	             
  				          }   	         
  				      }, 
  				      select: function( event, ui ) {
  				     	var source_unit_name = ui.item.value;
  				      
		  	   			 	$.post("getAllUnitDetailsListPartialSwap?"+key+"="+value, {unitName:ui.item.value}).done(function(j) {				
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
	  			
	  				 		/*  $.post("getPartialBANoListfromUnit?"+key+"="+value, {unit_name : source_unit_name}).done(function(j) {				
		  				 		 	if(j != "")
					  				{
					  					var options;
		 			  					for ( var i = 0; i < j.length; i++) {
		 			  						options += '<option value="'+ j[i]+'" name="' + j[i]+ '" >'+ j[i] + '</option>';   			
		 			  					}	
		 			  					$("select#lsbox").html(options);  
					  				}	
	  				 			}).fail(function(xhr, textStatus, errorThrown) {
	  				 			});	 */	  				
		  				unitNameStatusCheck(ui.item.value);  				    	  				
  				      } 	     
  				}); 			
  		});  
    try{
    	if(window.location.href.includes("?"))
		{
			var url = window.location.href.split("?")[0];
			window.location = url;
		}
	}
	catch (e) {
	} 
});
</script>
<script>
$(function() {	
	// Target SUS No

	$("input#target_sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=$("#target_sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
  				    url: "getSusNoActiveList?"+key+"="+value,
					data : {sus_no:sus_no},
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
			        	  alert("Invalid Input. \nPlease Enter Approved SUS No.");
			        	  document.getElementById("target_sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	 var sus_no = ui.item.value;
			    	 
			    			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			    			 		var length = j.length-1;
			    		        	var enc = j[length].substring(0,16);
			    		        	$("#target_unit_name").val(dec(enc,j[0]));	
			    				}).fail(function(xhr, textStatus, errorThrown) {
			    			});  			      
  			     } 	     
			});	
	});
	// End    
 // Target Unit Name

    $("#target_unit_name").keypress(function(){
 			var unit_name = this.value;
 				 var susNoAuto=$("#target_unit_name");
 				  susNoAuto.autocomplete({
 				      source: function( request, response ) {
 				        $.ajax({
 				        type: 'POST',
 				        url: "getUnitsNameActiveList?"+key+"="+value,
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
 				        	 alert("Invalid Input. \n Please Enter Approved Unit Name.");
 				        	  document.getElementById("target_unit_name").value="";
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
		alert("Please Enter the Source SUS No or Unit Name.");
		$("#source_sus_no").focus();
		return false;
	}
	if($("#target_sus_no").val() == ""){
		alert("Please Enter the Target SUS No or Unit Name.");
		$("#target_sus_no").focus();
		return false;
	}
	if($("#authority_no").val() == ""){
		alert("Enter Auth No.");
		$("#authority_no").focus();
		return false;
	}
	if($("#target_unit_name").val() == ""){
		alert("Please Select Proper SUS NO.");
		$("#target_sus_no").focus();
		return false;
	}
	
	if($("#type_veh").val() == "0"){
		alert("Please Select VEH CAT");
		$("#type_veh").focus();
		return false;
	}
	
	if($("#mct_main").val() == "0"){
		alert("Please Select MCT Main");
		$("#mct_main").focus();
		return false;
	}
	
	if($("#app").val() == ""){
		alert("Please Select BA No from Source Unit.");
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

function getMCTMainList()
{	
	var val = "C";
	var source_sus_no = $("#source_sus_no").val();
	var options = '<option value="0">'+ "--Select--" + '</option>';
	 if(val !="")
	{ 
	    $.post("getMCtMain_Id?"+key+"="+value,{type_of_veh : val,sus_no:source_sus_no}).done(function(j) {
	    	
	    	for ( var i = 0; i < j.length; i++) {
				if(j[i].mct_main_id == '${mct_main}'){
					
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
				}else{
					
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
				}
			}	
			$("select#mct_main").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	 }
	else {
		$("select#mct_main").html(options);
	} 
}


$(document).ready(function() {	
/* 	var val = "C";
	var source_sus_no = $("#source_sus_no").val();
	var options = '<option value="0">'+ "--Select--" + '</option>';
	 if(val !="")
	{ 
	    $.post("getMCtMain_Id?"+key+"="+value,{type_of_veh : val,sus_no:source_sus_no}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				if(j[i].mct_main_id == '${mct_main}'){
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" selected=selected>'+ j[i].mct_nomen+ '</option>';
				}else{
					options += '<option value="'+j[i].mct_main_id+'" name="' + j[i].mct_nomen+ '" >'+ j[i].mct_nomen+ '</option>';
				}
			}	
			$("select#mct_main").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	 }
	else {
		$("select#mct_main").html(options);
	}  */
});




function getbanoList(va) {
	var source_sus_no = $("#source_sus_no").val();
	var type_of_veh = $("select#type_veh").val();
	var mct_main = va;
	
	
	 $.post("getIUTBANoList?"+key+"="+value, {source_sus_no : source_sus_no,type_of_veh : type_of_veh,mct_main : mct_main}).done(function(j) {				
		 console.log("123");
		 var options;
		for ( var i = 0; i < j.length; i++) {
			console.log(j[i].ba_no);
			options += '<option value="'+ j[i].ba_no+'" name="' + j[i].ba_no+ '" >'+ j[i].ba_no+ '</option>';   			
		}	
		$("select#lsbox").html(options); 	
		}).fail(function(xhr, textStatus, errorThrown) {
		});	
	
}

</script>