<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Increment Decrement General Notes For Weapon</title></head>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
});
</script>
<body>
<script>
$(document).ready(function() {
	
	 $('#condition').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });	

	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	locfor1();
	incredecre1();
	
	$("select#scenario").val($("input#scenario_hid").val());
	
	var x =document.getElementById("we_pe_no").value;
	getArmByWePeNo(x);
	
	var y = document.getElementById("item_seq_no").value;
	getbaseauth_type(y);

	   var loc_code = document.getElementById("formation_code").value;
	  
		$.post("editgetFormationlist?"+key+"="+value, {loc_code:loc_code}).done(function(j) {
			$("#formation").val(j); 
		}).fail(function(xhr, textStatus, errorThrown) { }); 
		
		$.ajaxSetup({
		    async: false
		});
		var unit_code = document.getElementById("unit_code").value;

		$.post("editgetUnitlist?"+key+"="+value, {unit_code:unit_code}).done(function(j) {
			$("#unit").val(j); 
			}).fail(function(xhr, textStatus, errorThrown) { }); 

	   $.ajaxSetup({
 	  	    async: false
 	  	}); 
	   var location_code = document.getElementById("location_code").value;

	   $.post("getLocationByName?"+key+"="+value, {code:location_code}).done(function(j) {
			$("#location").val(j); 
	}).fail(function(xhr, textStatus, errorThrown) { }); 


		$.ajaxSetup({
		    async: false
		});
		
	    var wepetext14=$("#item_type");      
	
		  wepetext14.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
	        	type: 'POST',
	 	        url: "getitemtype?"+key+"="+value,
		        data: {item_type:document.getElementById('item_type').value},
		          success: function( data ) {
		            //response( data );
		            if(data.length > 1){
		        	  var susval = [];
		        	  var length = data.length-1;
	  	        		 var enc = data[length].columnName1.substring(0,16);
	                      for(var i = 0;i<data.length-1;i++){
	                       susval.push(dec(enc,data[i].columnName));
		        	  	}
			        	var dataCountry1 = susval.join("|");

		            var myResponse = []; 
		            var autoTextVal=wepetext14.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
						  myResponse.push(e);
						  
						}
					});      	          
					response( myResponse ); 
					}
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Nomenclature");
		        	  wepetext14.val("");
		        	  document.getElementById("base_auth").value=0.00;
		  				$("input[type=radio][id=ct-radio2]").attr('disabled', true);
						document.getElementById("item_seq_no").value="";
		        	 
		        	  wepetext14.focus();
		        	  return false;	             
		          }   	         
		      }, 
		       select: function( event, ui ) {
		        	$(this).val(ui.item.value); 
		        	$.post("getitemcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {	
	            		document.getElementById("item_seq_no").value=j;	
		        	  }).fail(function(xhr, textStatus, errorThrown) { }); 
		        	
		        	 $.ajaxSetup({
		     	  	    async: false
		     	  	}); 
		        	getbaseauth($(this).val());	        	
		        } 	     
		    });
		  
		  $.ajaxSetup({
		  	    async: false
		  	}); 
			 var wepetext2=$("#item_seq_no");
			
			  wepetext2.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
		 	        url: "getItemCodeList?"+key+"="+value,
			        data: {item_code:document.getElementById('item_seq_no').value},
			          success: function( data ) {
			            //response( data );
			            if(data.length > 1){
			        	  var susval = [];
			        	  var length = data.length-1;
		  	        		 var enc = data[length].columnName1.substring(0,16);
		                      for(var i = 0;i<data.length-1;i++){
		                       susval.push(dec(enc,data[i].columnName));
			        	  	}
				        	var dataCountry1 = susval.join("|");

			            var myResponse = []; 
			            var autoTextVal=wepetext2.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							//if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							  
							}
						});      	          
						response( myResponse ); 
						}
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) { 	            	
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Item Code");
			        	  wepetext2.val("");
			        	  document.getElementById("item_type").value="";  
			        	  document.getElementById("base_auth").value=0.00;
			  				$("input[type=radio][id=ct-radio2]").attr('disabled', true);
			        	  wepetext2.focus();
			        	  return false;	             
			          }   	         
			      }, 
			        select: function( event, ui ) {
			        	$(this).val(ui.item.value);    	        	
			        	$.post("getitemnamefromcode?"+key+"="+value, {val : ui.item.value}).done(function(j) {	
		            		document.getElementById("item_type").value=j;	
			        	  }).fail(function(xhr, textStatus, errorThrown) { });  
			        	
			        	 $.ajaxSetup({
			     	  	    async: false
			     	  	}); 
			        	getbaseauth(document.getElementById("item_type").value);	 
			        }  	     
			    });
	  
			  document.getElementById("base_auth").value=parseFloat(document.getElementById("base_auth").value);
			  document.getElementById("amt_inc_dec").value=parseFloat(document.getElementById("amt_inc_dec").value);			  
 
}); 
</script>
<script>
function isNumberKey(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
			return false;
		} else {
			if (evt.target.value.search(/\./) > -1 && charCode == 46) {
				return false;
			}
		}
		return true;
	}
	
function incredecre1(){
	var str = document.getElementById("amt_inc_dec_hid").value;
	var res = str.split("",1);
	
	   if(res == '-'){
		  document.getElementById("ct-radio12").checked = true;
		  document.getElementById("amt_inc_dec").value = str.substr(1);
	 }  
	   else{
		   document.getElementById("ct-radio1").checked = true;
		   document.getElementById("amt_inc_dec").value=str;
	   }
	} 


function getbaseauth(val){ 
	
		$.post("getitemcode?"+key+"="+value, {val : val}).done(function(j) {	
		 if(j!=null) 
		    	  {  
				document.getElementById("item_seq_no").value=j;
				var item=document.getElementById("item_seq_no").value;
		  			 var we_pe=document.getElementById("we_pe_no").value;		  			 
		  			$.post("getAutoWepen?"+key+"="+value, {we_pe:we_pe, item_code:item}).done(function(j) {	
		  				document.getElementById("base_auth").value=0.00;
	    				document.getElementById("base_autho_hidden").value = 0.00;
		  				if(j != ""){	 
		  					if(j[0].auth_weapon == '0.00' || j[0].auth_weapon == '0' || j[0].auth_weapon == '' || j[0].auth_weapon == undefined)
				    	          { 
				    				
		    					 	document.getElementById("base_auth").value=0.00;
				    				document.getElementById("base_autho_hidden").value = 0.00;
				    	            $("input[type=radio][id=ct-radio12]").attr('disabled', true);
				    	          }
				    			else
				    				{
				    				document.getElementById("base_auth").value=j[0].auth_weapon;
		    						document.getElementById("base_autho_hidden").value = j[0].auth_weapon;
		    						 $("input[type=radio][id=ct-radio12]").attr('disabled', false);
				    				}
		  				}
		  			 }).fail(function(xhr, textStatus, errorThrown) { })
		    	  }
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  } 
	  
	function selectradiobase()
	{
		var r =  $('input:radio[name=ct-radio1]:checked').val();	
		var base = document.getElementById("base_auth").value;
		 if(r == undefined)
		 {		 
			 alert("Please Select Increment/Decrement");
				return false;
		 }
		return true;
		}
	  
  
function getbaseauth_type(val){ 
	
	$.post("getitemtype_code?"+key+"="+value, {val : val}).done(function(j) {	
		  document.getElementById("item_type").value=j	;
		  getbaseauth(document.getElementById("item_type").value);
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  
</script>


<form:form action="in_deEditAction" commandName="in_deEditCMD" method="post"  class="form-horizontal"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"> <h5>Edit INCREMENT/DECREMENT General Notes FOR WEAPON</h5></div>
	          		<div class="card-body card-block cue_text">
	          		     <div class="col-md-12">
								 <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					    <input type="hidden" id="id" name="id" class="form-control" value="${in_deEditCMD.id}">
	                  						<input id="we_pe_no" name="we_pe_no"   class="form-control" autocomplete="off" onchange="getArmByWePeNo(this.value);" readonly="true"  value="${in_deEditCMD.we_pe_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	                					</div>
	              					</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="table_title1" name="" class="form-control" autocomplete="off" readonly="readonly">
	                					</div>
	                					</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">
	              				 <div class="col-md-6">
	              				    <div class="row form-group">
									        <div class="col col-md-6">
	                  							<label class=" form-control-label">Choose Scenario <strong style="color: red;">*</strong></label>
	                						</div>
	                						<div class="col-6 col-md-6">
	                						 <input type="hidden" id="scenario_hid" name="scenario_hid" class="form-control"  value="${in_deEditCMD.scenario}" readonly="readonly">
	                 							<select name="scenario" id="scenario" class="form-control" >
								                    <option value="">--Select--</option>
								                    <option value="Location">Location</option>
									                <option value="Formation">Formation</option>
									                <option value="Unit">Unit</option>
									                <option value="Others">Others</option>
								               	</select>
	                						</div>
				  				    </div>
				  				  </div>
				  				<div class="col-md-6">
	              				<div class="row form-group" id="getloc" style="display:none;">
                					<div class="col col-md-6" >
                  						<label class=" form-control-label">Location <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-6 col-md-6">
                  							<input type="hidden" id="location_code" name="location" value="${in_deEditCMD.location}" >
	                						<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					
                					</div>
              					</div>  
              					<div class="row form-group" id="getform" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Formation <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
									<input type="hidden" id="formation_code" name="formation"  class="form-control" value="${in_deEditCMD.formation}" >
									<input type="text" id="formation" name="formation_name" maxlength="8" class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
									</div>                				
              					</div> 
              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit"  class="form-control" value="${in_deEditCMD.scenario_unit}">
	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                				</div>
              					</div>  
              					</div>
              			  </div>
              				<div class="col-md-12"><span class="line"></span></div>		
	                           <div class="col-md-12">
								  <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nomenclature <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                				       <input class="form-control" id="item_type"  autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"> 
				      					</div>
	                					</div>
	              					</div>
	              				<div class="col-md-6">
	              			 	<div class="row form-group"> 
	                					<div class="col col-md-6">
	                  						<label  class=" form-control-label" >Item Code <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input id="item_seq_no" name="item_seq_no" maxlength="8" class="form-control" autocomplete="off" value="${in_deEditCMD.item_seq_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                  					</div>
	  								</div> 
	  						</div>
	              		</div>
	              		  <div class="col-md-12">
	  					  	   <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Base Authorisation </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="base_auth" name="" readonly="readonly" class="form-control">
	                  						 <input type="hidden" id="base_autho_hidden" name="base_autho_hidden">
	                					</div>
	                					</div>
	              					</div>
	              		 </div>
	              		 <div class="col-md-12">
	              			   <div class="col-md-6">
	                               <div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label for="text-input" class=" form-control-label">Increment/Decrement <strong style="color: red;">*</strong></label>
	                					</div>
	                				    	<div class="col-12 col-md-6">
							                  <div class="form-check-inline form-check">
							                       <label for="ct-radio1" class="form-check-label ">
							                           <input type="radio" id="ct-radio1" name="ct-radio1" value="Increase" class="form-check-input">Increment
							                       </label>&nbsp;&nbsp;&nbsp;
							                       <label for="ct-radio12" class="form-check-label ">
							                            <input type="radio" id="ct-radio12" name="ct-radio1" value="Decrease" class="form-check-input">Decrement
							                       </label>&nbsp;&nbsp;&nbsp;
							                   </div>
	                						</div>
	              						</div>
	                             </div>
                               	<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Amount of Increment/ Decrement <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                					<input type="hidden" id="amt_inc_dec_hid" name="amt_inc_dec_hid" placeholder="" class="form-control" value="${in_deEditCMD.amt_inc_dec}">
                  						<input  id="amt_inc_dec" name="amt_inc_dec" onkeypress="return isNumberKey(event, this);" maxlength="8"
                  						 onkeyup="selectradiobase();" class="form-control" onchange="return checkAmt_inc_decLength();" >
                					</div>
                					</div>
              					</div>					
	  						</div>		
	  						<div class="col-md-12">
	  								<div class="col-md-6">
	  								<div class="row form-group">
											  <div class="col col-md-6">
											      <label class=" form-control-label">General Notes Condition <strong style="color: red;">*</strong></label>
											  </div>
											  <div class="col-12 col-md-6">
											       <textarea class="form-control" id="condition" name="condition" maxlength="255">${in_deEditCMD.condition}</textarea>
											  </div>
										</div>
										</div>	
										<div class="col-md-6">
	  								       <div class="row form-group">
											  <div class="col col-md-6">
											      <label class=" form-control-label">Remarks</label>
											  </div>
											  <div class="col-12 col-md-6">
											 <textarea class="form-control char-counter1" id="remarks" name="remarks" maxlength="255">${in_deEditCMD.remarks}</textarea>
											  </div>
										  </div>
										</div>
							</div>
					</div>
							<div class="card-footer" align="center">
			              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidation()">
								<a href="search_in_de" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
				            </div> 
	        	</div>
			</div>
	</div>
</form:form>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

<c:url value="updateIncrementUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form> 
	
<script>

function checkAmt_inc_decLength() {
	var amt_inc_dec= $("input#amt_inc_dec").val();
	var r =  $('input:radio[name=ct-radio1]:checked').val();	
	var lenval=0;
	 if(r == "Increase")
		 lenval = 8;
	 else if(r == "Decrease")
		 lenval = 9;
	 
	 if(amt_inc_dec.length > lenval) {
		 alert("Please Enter Valid Digit");
			$("input#amt_inc_dec").val("");	
		 return false;
	 }
	 
	 else {
		 if(amt_inc_dec != "" && amt_inc_dec.includes(".")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split(".");			
			 	if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2 )
				{
			 		alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			 	
			 }
			else if(amt_inc_dec != "" && amt_inc_dec.includes(".") && amt_inc_dec.includes("-")) {
				var amt_inc_dec1 = amt_inc_dec.toString().split("-")[1].split(".");	
				if(amt_inc_dec1[0].length > 5 || amt_inc_dec1[1].length > 2) 
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
				}
			}
			else {
				if(auth_weapon.length > 5)
				{
					alert("Please Enter Valid Digit");
					$("input#amt_inc_dec").val("");
			 		return false;
					
				}
			}
		 	return true;
		}	 
}

function setfun()
{
	if($("#amt_inc_dec").val() == ""){
		$("#amt_inc_dec").val(0);
    }
}

$("#scenario").change(function(){
  	$("#Location").val("");
  	$("#Formation").val("");
  	$("#Unit").val("");
  	
  	if($(this).val()=="Location")
	   {    
		   $("label#labelcon").text("Location");
	       $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	       $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
	   }
	   else if($(this).val()=="Formation"){
		   $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#location_code").val("");
	   }
	   else if($(this).val()=="Unit"){
		   $("label#labelcon").text("Unit");
		   $("div#getunit").show();
		   $("div#getform").hide();
		   $("div#getloc").hide();
		   $("#location").val("");
		   $("#formation").val("");
		   $("#formation_code").val("");
		   $("#location_code").val("");
	   }
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("#location").val("");
		   $("#formation").val("");
		   $("#unit").val("");
		   $("#unit_code").val("");
		   $("#formation_code").val("");
		   $("#location_code").val("");
		   
	    }
	});
	
$(function() {
	 var wepetext1=$("#formation");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
	            url: "getFormationUrl?"+key+"="+value, 
	        data: {formation : document.getElementById('formation').value},
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
	    	        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext1.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					  
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) { 
	    		return true;    	            
	          } 
	    	 else {
	        	  alert("Please Enter Approved Formation");
	        	  wepetext1.val("");
				 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	         select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var unit_name=ui.item.value;
	        	 $.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
	        			document.getElementById("formation_code").value=j[0];
	        	 }).fail(function(xhr, textStatus, errorThrown) { });    	        	
	        }  	      
	    });
     
   });    
   
$(function() {
	 var wepetext1=$("#unit");
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        	type: 'POST',
	  	      url: "getUnitUrl?"+key+"="+value,
	        data: {unit : document.getElementById('unit').value},
	        success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	var susval = [];
	        	var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                 for(var i = 0;i<data.length-1;i++){
                  susval.push(dec(enc,data[i].columnName));
       	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext1.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					  
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	          
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) { 
	    		return true;    	            
	          } 
	    	 else {
	        	  alert("Please Enter Approved Unit");
	        	  wepetext1.val("");
				 wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	         select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var unit_name=ui.item.value;
	        	$.post("getUnitNameFromSusNo?"+key+"="+value, {unit_name : unit_name}).done(function(j) {
        			document.getElementById("unit_code").value=j[0];
        	 }).fail(function(xhr, textStatus, errorThrown) { });	        	
	        }  	      
	    });   
  });  
   
$(function() {	
	  var wepetext1=$("#location");	  
	 
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getLocation?"+key+"="+value,  
	        data: {code_value : document.getElementById('location').value},
	          success: function( data ) {
	            //response( data );
	            if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext1.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					  
					}
				});      	          
				response( myResponse ); 
				}
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Location");
	        	  wepetext1.val("");
	        	  document.getElementById("location").value="";	
	        	  document.getElementById("location").focus();	
	        	  document.getElementById("location_code").value="";
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	        	$(this).val(ui.item.value);    	        	
	        	var code_value=ui.item.value;
	        	$.post("getLocationByCode?"+key+"="+value, {code_value : code_value}).done(function(j) {
        			document.getElementById("location_code").value=j[0];
        	  }).fail(function(xhr, textStatus, errorThrown) { });	        	
	        } 	      
	    });
	
});
</script>
<script>
 	 function isvalidation(){
 		 setfun(); 
  	   if($("input#we_pe_no").val()==""){
  			alert("Please Enter Type of we_pe_no")
  			$("input#we_pe_no").focus();
  			return false;
  		}
  	   
  	   if($("select#scenario").val()==""){
 		alert("Please Select Scenario");
 		$("select#scenario").focus();
 		return false;
 	     } 
  	   
 	    var f = document.getElementById("scenario").value;
        if(f == "Formation"){
               if($("input#formation").val() == ""){
                        alert("Please Enter Formation");
                               $("input#formation").focus();
                               return false;
                   }
        }
       if(f == "Location"){
               if($("input#location").val() == ""){
                       alert("Please Enter Location");
                          $("input#location").focus();
                           return false;
                     } 
       }
       if(f == "Unit"){
               if($("input#unit").val() == ""){
                       alert("Please Enter Unit");
                              $("input#unit").focus();
                              return false;
                      } 
      }
 	var r =  $('input:radio[name=ct-radio1]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	  
	  if($("input#item_type").val()==""){
			alert("Please Enter Nomenclature ")
			$("input#item_type").focus();
			return false;
		}
	 	if($("input#item_seq_no").val()==""){
			alert("Please Enter Item Code")
			$("input#item_seq_no").focus();
			return false;
		}
	 	
	if($("input#amt_inc_dec").val()=="" || $("input#amt_inc_dec").val()=="0"){
		alert("Please Enter Amt Incr/Decr")
		$("input#amt_inc_dec").focus();
		return false;
	}
	  var amt;
	  var base;
 if($("input#amt_inc_dec").val()!="")
	  {		 
  amt = $("input#amt_inc_dec").val();
    var r_s =  $('input:radio[name=ct-radio1]:checked').val();	
	 base = document.getElementById("base_auth").value;	
	 
		 if(r_s == "Decrease")
			 {
			 var amt = $("input#amt_inc_dec").val().split("-");
			 var base1 = parseFloat(base);
	 		 var amt1 = parseFloat(amt);
			 if (base1 < amt1){
				 alert("Please Check Amount of Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
			 }
			 else
				 return true;
			 }
		 
		 if($('input:radio[name=ct-radio1]:checked').val() == "Increase")
		 {
			if ($("#amt_inc_dec").val().includes("-"))
			 {
				 alert("Please Remove Dec Value of Amt Inc/Dec");
				 $("#amt_inc_dec").focus();
				 return false;
			 }
		 }
		
	  }
	
	if($("textarea#condition").val()==""){
		alert("Please Enter Condition")
		$("textarea#condition").focus();
		return false;
	}
  	return true;
  }
</script>
<script>    
function locfor1(){
	
	$("#Location").val("");
	$("#Formation").val("");
	$("#Unit").val("");
	var loc = document.getElementById("scenario_hid").value;
	   if(loc == "Location")
	   { 
		   $("label#labelcon").text("Location");
		   $("div#getloc").show();
	       $("div#getform").hide();
	       $("div#getunit").hide();
	   	   $("input#formation").val("");
	   	   $("input#unit").val("");
	       
	   }
	    else if(loc=="Formation"){
	    	
	     $("label#labelcon").text("Formation");
		   $("div#getform").show();
		   $("div#getloc").hide();
		   $("div#getunit").hide();
		   $("input#location").val("");
	   	   $("input#unit").val("");
	   } 
	    else if(loc=="Unit"){
	    	
		     $("label#labelcon").text("Unit");
			   $("div#getunit").show();
			   $("div#getform").hide();
			   $("div#getloc").hide();
			   $("#location").val("");
			   $("#formation").val("");
		   } 
	   else
	   {
		   $("label#labelcon").text("Others");
		   $("div#getloc").hide();
		   $("div#getform").hide();
		   $("div#getunit").hide();
		   $("input#formation").val("");
	   	   $("input#unit").val("");
	   	   $("#location").val("");
	    }
}

function getArmByWePeNo(val)
{ 
	  if(val != "" && val != null)
	  {  
			$.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) { 
			if(j!=null)
			document.getElementById("table_title1").value=j[0].table_title;
		  }).fail(function(xhr, textStatus, errorThrown) { });
	  }
	  else
	  {
		  document.getElementById("table_title1").value="";
	  } 
}
 </script>			

