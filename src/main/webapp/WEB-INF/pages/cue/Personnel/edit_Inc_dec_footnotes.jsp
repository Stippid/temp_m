<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<form:form name="Edit_Inc_dec_footnotes"  action="Edit_Inc_dec_footnotesAction" method='POST' commandName="edit_Inc_dec_footnotesActionCMD">
	          		
	 	<div class="container" align="center">
        	<div class="card">
        		<div class="card-header"> <h5> Edit Increase/Decrease general notes for Personnel</h5></div>   
          		<div class="card-body card-block cue_text">
            		<div class="col-md-12">	              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No</label>
               					</div>
               					<div class="col-12 col-md-6">
               					<input  type="hidden" name="id" value="${edit_Inc_dec_footnotesActionCMD.id}">
                 					<input  id="we_pe_no" name="we_pe_no" maxlength="100" class="form-control" onchange="getarmvalue(this.value);" autocomplete="off" value="${edit_Inc_dec_footnotesActionCMD.we_pe_no}" readonly="readonly" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  					</div>
  					<div class="col-md-12">
 						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Table Title</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="tableTitle" class="form-control" autocomplete="off" readonly="readonly" >
								</div>
							</div>	 							
	  					</div>
	  					<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">User Arm</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input type="text" id="user_arm_hi" readonly="readonly" class="form-control">
                 					<input type="hidden" id="user_arm" name="" readonly="readonly" class="form-control">
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
					  <div class="col-12 col-md-6">
					  <input type="hidden" id="scenario_hid" name="scenario_hid" class="form-control"  value="${edit_Inc_dec_footnotesActionCMD.scenario}" >
					              <select name="scenario" id="scenario" class=" form-control " >
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
                  						<label class=" form-control-label">Location </label>
                					</div>
                					<div class="col-12 col-md-6">
                  					<input type="hidden" id="location_code" name="location" value="${edit_Inc_dec_footnotesActionCMD.location}" >
	                						<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
              					</div>  
              					<div class="row form-group" id="getform" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Formation </label>
                					</div>
                					<div class="col-12 col-md-6">
									<input type="hidden" id="formation_code" name="formation"  class="form-control" value="${edit_Inc_dec_footnotesActionCMD.formation}" >
									<input type="text" id="formation" name="formation_name" maxlength="8" class="form-control autocomplete" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
									</div>
              					</div>  
              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit" class="form-control" value="${edit_Inc_dec_footnotesActionCMD.scenario_unit}">
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
				                  	<label class=" form-control-label">Category of Personnel</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                <input type="hidden" id=category_of_personnel1 name="category_of_personnel" value="${edit_Inc_dec_footnotesActionCMD.category_of_personnel}" >
				                	<select  id="category_of_personnel" class="form-control" disabled="disabled"   ></select>
				                </div>
				            </div>							
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Parent Arm</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                <input  id="parent_arm"  placeholder="" class="form-control" readonly="readonly" >
				                  <select  id="parent_arm1"  class="form-control" style="display: none;" disabled="disabled" ></select>
				                <input type="hidden" name="arm_code" id="arm_code" maxlength="4" value="${edit_Inc_dec_footnotesActionCMD.arm_code}" >
				                
			                </div>
				            </div>	  								
  						</div>
  					</div>
  					<div class="col-md-12">
	  					<div class="col-md-6">
	  							<div class="row form-group">
					                <div class="col col-md-6">
					                  	<label class=" form-control-label">Category</label> <strong style="color: red;">*</strong>
					                </div>
					                <div class="col-12 col-md-6">
					              	<input type="hidden" id=rank_cat1 name="rank_cat" value="${edit_Inc_dec_footnotesActionCMD.rank_cat}" >
					                <select  id="rank_cat" class="form-control" disabled="disabled">
					                 <option value="">--Select--</option>
                 						<c:forEach var="item" items="${getTypeofRankcategory}" varStatus="num" >
           									<option value="${item[0]}" <c:if test="${item[0] eq edit_Inc_dec_footnotesActionCMD.rank_cat}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>
					                </select>
					                </div>
					            </div>	  								
	  						</div>
	  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                <input type="hidden" id="id" name="id" class="form-control" value="${edit_Inc_dec_footnotesActionCMD.id}">
				                  	<label class=" form-control-label">Common Appt/Trade</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
					                <input type="hidden" id="appt_trade_code" name="appt_trade"  value="${edit_Inc_dec_footnotesActionCMD.appt_trade }">
					                <input  id="appt_trade" name="appt_trade_name" maxlength="8" class="form-control" autocomplete="off" readonly="readonly"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
				                </div>
				            </div>	  								
  						</div>
  					</div>   
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Rank</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                	<input id="rank" name=""  class="form-control"  readonly="readonly"  >
				              <input type="hidden" id="rank_hide" name="rank" class="form-control"  value="${edit_Inc_dec_footnotesActionCMD.rank}"  >
				                 </div>
				            </div>						
  						</div>
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Base Authorisation</label> 
				                </div>
				                <div class="col-12 col-md-6">
				                <input  id="base_autho" name="base_autho" class="form-control" readonly="readonly" maxlength="4">
				                </div>
				            </div>	  								
  						</div>
  					</div> 
  					<div class="col-md-12">	
  						<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Increment/Decrement</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                	 <div class="form-check-inline form-check">
		                                <label for="radio_status2" class="form-check-label ">
		                                  <input type="radio" id="radio_status2" name="radio_status" value="Increment" class="form-check-input">Increment
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="radio_status1" class="form-check-label ">
		                                  <input type="radio" id="radio_status1" name="radio_status" value="Decrement" class="form-check-input">Decrement
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
				                	<input type="hidden" id="amt_inc_dec_hid" name="amt_inc_dec_hid" class="form-control" value="${edit_Inc_dec_footnotesActionCMD.amt_inc_dec}">
				                <input  id="amt_inc_dec1" name="amt_inc_dec1" maxlength="5" class="form-control"  onkeypress="return isNumberKey(event, this);" onkeyup="selectradiobase();" maxlength="5" autocomplete="off" >
				                </div>
				            </div>	  								
  						</div>
  					</div>
  				<div class="col-md-12">
  				<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">General Notes Condition</label> <strong style="color: red;">*</strong>
				                </div>
				                <div class="col-12 col-md-6">
				                <textarea  id="condition" name="condition" class="form-control" maxlength="255">${edit_Inc_dec_footnotesActionCMD.condition}</textarea>
				                </div>
				            </div>	
  						</div>
  				</div>
  			</div> 
  				<div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();">
	              		<a href="Search_Inc_dec_footnotes" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		        </div>		
  			</div>
		 </div>
</form:form>

<c:if test="${not empty msg}">
	<input type="text" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

	<c:url value="updateIncDecFootnoteUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
<script>
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
       	$.post("getUnitNameFromSusNo?"+key+"="+value,{unit_name : unit_name}).done(function(j) {
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
	    	 
	        	$.post("getUnitNameFromSusNo?"+key+"="+value,{unit_name : unit_name}).done(function(j) {
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
      	  document.getElementById("location_code").value="";
      	   document.getElementById("location").value="";	
      	  document.getElementById("location").focus();
      	  
      	  
      	  wepetext1.focus();
      	  return false;	             
        }   	         
    }, 
    select: function( event, ui ) {
      	$(this).val(ui.item.value);    	        	
      	var code_value=ui.item.value;
     
      	$.post("getLocationByCode?"+key+"="+value,{code_value : code_value}).done(function(j) {
      		document.getElementById("location_code").value=j[0];
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
      	 
      }       
  });
});
      
  
function locfor1(){
	$("#location").val("");
	$("#formation").val("");
	$("#unit").val("");
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
</script>

<script>
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
</script>

	
<script>
$(document).ready(function() {
	$('#amt_inc_dec1').change(function(){
		  if ($(this).val() >= 100){
		    alert("Authorisation of manpower entered is more than 100");
		  }
		});
	
	getapptradename();
	incredecre1();
	
	var rank_hide = $("#rank_hide").val();
	var rank_cat1 = $("#rank_cat1").val();
	var category_of_personnel1 = $("#category_of_personnel1").val();
 
	  $.post("getTypeofRankcategoryList?"+key+"="+value).done(function(j) { 
		 var length = j.length-1;
			var enc = j[length].columnName1.substring(0,16);
			var newVal="";
				if(document.getElementById("rank_cat1").value != "" || document.getElementById("rank_cat1").value != null)
				{
				newVal=document.getElementById("rank_cat1").value;
				}
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length-1; i++) {
					if(dec(enc,j[i].columnCode) == newVal){
						options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" selected="selected ">'+ dec(enc,j[i].columnName)+ '</option>';
					}
					else{
					options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
					}
				}	
				
				$("select#rank_cat").html(options); 
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	

$.post("getPersonCatList?"+key+"="+value).done(function(j) {
	var length = j.length-1;
	var enc = j[length].columnName1.substring(0,16);
var options = '<option value="' + " " + '">'+ "--Select--" + '</option>';
for ( var i = 0; i < j.length-1; i++) {		
	if(category_of_personnel1 == dec(enc,j[i].columnCode)){
		$("#parent_arm").val( $("#user_arm_hi").val());
		options += '<option value="'+dec(enc,j[i].columnCode)+'"  name="'  + dec(enc,j[i].columnName)+ '" selected=selected  >'+ dec(enc,j[i].columnName)+ '</option>';
	}	
	else
		{
		options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '">'+ dec(enc,j[i].columnName)+ '</option>';}
		}
$("select#category_of_personnel").html(options);  
 }).fail(function(xhr, textStatus, errorThrown) { }); 	
	



var pm = document.getElementById("arm_code").value;
$.post("editParentArmList?"+key+"="+value,{pm:pm}).done(function(j) {
	$("#parent_arm").val(j);  
 }).fail(function(xhr, textStatus, errorThrown) { }); 	


$('select#category_of_personnel').change(function() {
	var code = $(this).find('option:selected').attr("name");  
	if(code == "Regimental")	        		
	{
		$("#parent_arm").val( $("#user_arm_hi").val());
		document.getElementById("parent_arm").disabled = true; 		
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
		$("#arm_code").val($("#user_arm").val());
	}
	else if(code == "ERE")
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = false; 
		$("input#parent_arm").hide();
		$("select#parent_arm1").show();
		parentArm();        		
	}
	else
	{
		$("#parent_arm").val("");
		document.getElementById("parent_arm").disabled = true; 
		$("select#parent_arm1").val("0");
		$("select#parent_arm1").hide();		
		$("input#parent_arm").show();
	}
	
	
});


	$('select#parent_arm1').change(function() {	
		$("#arm_code").val($("#parent_arm1").val());
	});
	
	
	var rnk = document.getElementById("rank_hide").value;
	$.post("editTypeofRankList_enti_wepe?"+key+"="+value,{rnk:rnk}).done(function(j) {
			$("#rank").val(j[0][0]);  
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
	

	
	$.ajaxSetup({
	    async: false
	});
	locfor1();
	$("select#scenario").val($("input#scenario_hid").val());
	
	getarmvalue(document.getElementById("we_pe_no").value);
	
	 $.ajaxSetup({
	    async: false
	});
	base_auth();
	
	$('#condition').keyup(function() {
	    this.value = this.value.toUpperCase();
	});
	
	


	 var loc_code = document.getElementById("formation_code").value;

		$.post("editgetFormationlist?"+key+"="+value,{loc_code:loc_code}).done(function(j) {
			$("#formation").val(j); 
			 }).fail(function(xhr, textStatus, errorThrown) { });  
		

	var unit_code = document.getElementById("unit_code").value;

	$.post("editgetUnitlist?"+key+"="+value,{unit_code:unit_code}).done(function(j) {
		$("#unit").val(j); 
		 }).fail(function(xhr, textStatus, errorThrown) { });  


	$.ajaxSetup({
	    async: false
	});

	var location_code = document.getElementById("location_code").value;
	$.post("getLocationByName?"+key+"="+value,{code:location_code}).done(function(j) {
		$("#location").val(j);
		 }).fail(function(xhr, textStatus, errorThrown) { }); 


	$.ajaxSetup({
	    async: false
	});
	try{
		if(window.location.href.includes("&msg="))
		{
			var url = window.location.href.split("?updateid=")[0];
			var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
			//window.location = url;
			
			 document.getElementById('updateid').value=id;
	 		 document.getElementById('updateForm').submit();
		}
		
	}
	catch (e) {
		// TODO: handle exception
	}


});

function incredecre1(){
	var str = document.getElementById("amt_inc_dec_hid").value;
	var res = str.split("",1);
	
	   if(res == '-'){
		  
		   document.getElementById("radio_status1").checked = true;
		  document.getElementById("amt_inc_dec1").value = str.substr(1);
	 }  
	   else{
		   document.getElementById("radio_status2").checked = true;
		   document.getElementById("amt_inc_dec1").value=str;
	   }
	} 
function selectradiobase()
{
	var r =  $('input:radio[name=radio_status]:checked').val();	
	var base = document.getElementById("base_autho").value;
	 if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	return true;
	}
$('input#appt_trade').change(function() {
		var a = $(this).val();
		var b = $("select#rank_cat").val();
		$.post("getappt_trade_codelist1?"+key+"="+value,{a : a,b:b}).done(function(j) {
			document.getElementById("appt_trade_code").value=j[0];
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	
});

 function base_auth(){
	
	var r_c = document.getElementById("rank_cat1").value;
	var c_p = document.getElementById("category_of_personnel1").value;
	var r = document.getElementById("rank_hide").value;
	var arm = document.getElementById("arm_code").value;
	var a_t = document.getElementById("appt_trade_code").value;
	var we_pe = document.getElementById("we_pe_no").value;
 
	 
	 $.post("getwepe_pers_detBaselist?"+key+"="+value,{ r_c : r_c , c_p : c_p , r : r , arm : arm , a_t : a_t ,we_pe : we_pe}).done(function(j) {
		 if(j == "" || j == null)				
				document.getElementById("base_autho").value=0.00;
			else
				document.getElementById("base_autho").value=j;
			
			if(j == '0.00' || j == '0' ||j == "")
			{ 			
				$("input[type=radio][id=radio_status1]").attr('disabled', true);
			}
			else				
				$("input[type=radio][id=radio_status1]").attr('disabled', false);
		 }).fail(function(xhr, textStatus, errorThrown) { }); 

} 



 function parentArm()
 {
 	var arm_code = $("#arm_code").val();
 	 var u_a = document.getElementById("user_arm").value;
 	
 		
 	
 		$.post("getArmNameListCue?"+key+"="+value,{u : u_a}).done(function(j) {
 			var length = j.length-1;
 	 		var enc = j[length].columnName1.substring(0,16);
 	 		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
 	 		for ( var i = 0; i < j.length-1; i++) {
 	 			if(arm_code == dec(enc,j[i].columnCode))
 	 				options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" selected="selected">'+ dec(enc,j[i].columnName)+ '</option>';
 	 			else
 	 				options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
 	 		}	
 	 		$("select#parent_arm1").html(options); 
		 }).fail(function(xhr, textStatus, errorThrown) { }); 	
 		
 		
 	}
	
function getapptradename()
{
	var a=document.getElementById("appt_trade_code").value;
	
	 $.post("getappt_trade_code?"+key+"="+value,{a : a}).done(function(j) {	
			document.getElementById("appt_trade").value=j[0];
			
		}).fail(function(xhr, textStatus, errorThrown) { });
}



function getarmvalue(val){
	
	  if(val != "" && val != null)
	  {
		  
		  $.post("getDetailsByWePeCondiNo?"+key+"="+value,{val : val}).done(function(j) {
			  document.getElementById("user_arm").value=j[0].arm;	
				document.getElementById("tableTitle").value=j[0].table_title;		
				ex();
			 }).fail(function(xhr, textStatus, errorThrown) { });  
		  
	  }
	  else
	  {
		  document.getElementById("user_arm").value="";	
		  document.getElementById("tableTitle").value="";
	  }

}
function ex(){
	
	var parent_arm_name = document.getElementById("user_arm").value;		
	$.post("getArmNameByCode?"+key+"="+value,{parent_arm_name:parent_arm_name}).done(function(j) {
		$("#user_arm_hi").val(j); 
		regi_arm();
		 }).fail(function(xhr, textStatus, errorThrown) { });  

}


var arm = document.getElementById("arm_code").value;

function regi_arm(){
	var regi = document.getElementById("category_of_personnel1").value;
	if(regi == "2"){
		document.getElementById('parent_arm').style.display = 'block';
		document.getElementById('parent_arm1').style.display = 'none';
		document.getElementById("parent_arm").value = document.getElementById("user_arm_hi").value;
	}
	if(regi == "1"){
		document.getElementById('parent_arm1').style.display = 'block';
		document.getElementById('parent_arm').style.display = 'none';
		parentArm();
	}
	
}

function isvalidData(){
	 if($("input#we_pe_no").val()==""){
			alert("Please Enter WE/PE No");
			$("input#we_pe_no").focus();
			return false;
		}
	 if($("select#scenario").val()=="select" ||$("select#scenario").val()=="0" || $("select#scenario").val()==null || $("select#scenario").val()=="null"){
			alert("Please Select Scenario");
			$("select#scenario").focus();
			return false;
		} 
	 
	 var f = document.getElementById("scenario").value;
	 
	 if(f == "Formation"){
		 if($("input#formation").val()=="" ){
			 alert("Please Enter Formation");
				$("input#formation").focus();
				return false;
			}
	 }
		  if(f == "Location"){
			 if($("input#location").val()=="" ){
				 alert("Please Enter Location");
					$("input#location").focus();
					return false;
				} 
			
		}
		  if(f == "Unit"){
				 if($("input#unit").val()=="" ){
					 alert("Please Enter Unit");
						$("input#unit").focus();
						return false;
					} 
			}
		
	  if($("select#cat_per").val()=="0" || $("select#cat_per").val()==" " ){
			alert("Please Select Cat of Pers");
			$("select#cat_per").focus();
			return false;
		}
	  if($("select#rank_cat").val()==" "  ){
			alert("Please Select rank_cat");
			$("select#rank_cat").focus();
			return false;
		}
	  if($("input#appt_trade").val()==""){
			alert("Please Enter Appt/Trade");
			$("input#appt_trade").focus();
			return false;
		}
	  if($("select#rank").val()==""){
			alert("Please select Rank");
			$("select#rank").focus();
			return false;
		}
	  
	 
	var r =  $('input:radio[name=radio_status]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select Increment/Decrement");
			return false;
	 }
	 	 
	  if($("input#amt_inc_dec1").val()=="" || $("input#amt_inc_dec1").val()=="0"){
			alert("Please Enter Amount of Increment/Decrement");
			$("input#amt_inc_dec1").val("")
			$("input#amt_inc_dec1").focus();
			return false;
			
		}

	  var amt ;
		 var base;
		
		  if($("input#amt_inc_dec1").val()!="")
		  {		 
			
			   amt = $("input#amt_inc_dec1").val();
			  var r_s =  $('input:radio[name=radio_status]:checked').val();	
				 base = document.getElementById("base_autho").value;	
								
			        if(r_s == "Decrement")
				    {
			        	base = parseFloat(base);
					       amt = parseFloat(amt);
				 if (base < amt){
					 alert("Please Check Amount of Inc/Dec");
					 $("input#amt_inc_dec1").focus();
					 return false;
				 }
				 else
					 {
					 return true; 
					 }
					 
				 }
			      
			        if($('input:radio[name=radio_status]:checked').val() == "Increment")
					 {
						if ($("#amt_inc_dec1").val().includes("-"))
						 {
							 alert("Please Remove Dec Value of Amt Inc/Dec");
							 $("#amt_inc_dec1").focus();
							 return false;
						 }
					 }
		  }
	 
	  if($("textarea#condition").val()==""){
			alert("Please Enter Condition");
			$("textarea#condition").focus();
			return false;
		}
	 return true;
	
}

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
	
</script>