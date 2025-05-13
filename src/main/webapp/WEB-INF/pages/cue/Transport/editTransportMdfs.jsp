<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
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
<form:form  action="editTransportMdfsAction" method='POST' commandName="edittransportmdfsCmd">
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"> <h5>Edit AUTHORISATION of TRANSPORT UNDER MODIFICATION</h5></div> 
	    			<div class="card-body card-block cue_text">
	    			  <div class="col-md-12">
	    					<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MISO WE/PE No <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input type="hidden" id="id" name="id" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off" value="${edittransportmdfsCmd.id}">
                  						<input type="text" id="we_pe_no" name="we_pe_no" class="form-control autocomplete" value="${edittransportmdfsCmd.we_pe_no}" readonly="readonly"/>
                					</div>
              					</div>
              				</div>
              				<div class="col-md-6">	
		    					<div class="row form-group">
		                			<div class="col col-md-6">
		                  				<label class=" form-control-label">Table Title</label>
		                			</div>
		                			<div class="col-12 col-md-6">
		                  				<input type="text" id="table_title" name="table_title" class="form-control autocomplete" autocomplete="off" value="${edittransportmdfsCmd.table_title}" readonly="readonly"/>
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
						  <input type="hidden" id="scenario_hid" name="scenario_hid" class="form-control"  value="${edittransportmdfsCmd.scenario}" >
						              <select name="scenario" id="scenario" class=" form-control" >
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
	                  						<label class=" form-control-label">Location <strong style="color: red;">*</strong> </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="location_code" name="location" value="${edittransportmdfsCmd.location}" >
	                						<input type="text" id="location" name="location_name" maxlength="5" class="form-control" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
	                					</div>
	              					</div>  
	              					<div class="row form-group" id="getform" style="display:none;" >
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Formation <strong style="color: red;">*</strong> </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input type="hidden" id="formation_code" name="formation" value="${edittransportmdfsCmd.formation}" >
	                						<input type="text" id="formation" name="formation_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off">
	                					</div>
	              					</div> 
	              					<div class="row form-group" id="getunit" style="display:none;" >
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Unit <strong style="color: red;">*</strong> </label>
                					</div>
                					<div class="col-12 col-md-6">
	                					<input type="hidden" id="unit_code" name="scenario_unit"  class="form-control" value="${edittransportmdfsCmd.scenario_unit}">
	                					<input type="text" id="unit" name="scenario_unit_name" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control" autocomplete="off">
	                				</div>
              					</div>  
	              				</div>
              			</div>  
	    	     	<div class="col-md-12"><span class="line"></span></div>
	    	         	<div class="col-md-12">
	            			<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Modification <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="modification" name="modification" maxlength="15" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" value="${edittransportmdfsCmd.modification}">
                					</div>
              					</div>
              				</div>
              			</div>
              			<div class="col-md-12">
              			   <div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">MCT No <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="mct_no" name="mct_no" maxlength="4" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" autocomplete="off" value="${edittransportmdfsCmd.mct_no}" readonly="readonly"/>
                					</div>
              					</div>
              				</div>
              				<div class="col-md-6">
	            				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Std. Nomenclature </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="text" id="std_nomclature" name="std_nomclature" readonly="readonly" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" class="form-control autocomplete" value="${edittransportmdfsCmd.std_nomclature}" autocomplete="off">
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
                  						<input type="text" value=0  id="auth_amt" name="auth_amt" onkeypress="return isNumber0_9Only(event);"  readonly="readonly" placeholder="" class="form-control validate">
                					</div>
              					</div>
              				</div>
              				  <div class="col-md-6">
              					<div class="row form-group">
									<div class="col col-md-6">
										<label for="text-input" class="form-control-label">Increment/Decrement <strong style="color: red;">*</strong></label>
									</div>
									<div class="col col-md-6">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
											<input type="hidden" id="inc_dec" name="" class="form-control">
												<input type="radio" id="inline-radio1" name="inc_dec" value="I" class="form-check-input">Increment
											</label>&nbsp;&nbsp;&nbsp;
											<label for="inline-radio2" class="form-check-label ">
												<input type="radio" id="inline-radio2" name="inc_dec" value="D" class="form-check-input">Decrement
											</label>
										</div>
									</div>
				  				</div>
				  			  </div>
				  			 </div>
				  			 <div class="col-md-12">
				  			   <div class="col-md-6">
				  				<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Amount of Increment/ Decrement <strong style="color: red;">*</strong></label>
                					</div>
                					<div class="col-12 col-md-6">
                						<input type="hidden" id="amt_inc_dec_hid" name="amt_inc_dec_hid" placeholder="" class="form-control"  value="${edittransportmdfsCmd.amt_inc_dec}"> 
				                		<input  id="amt_inc_dec" name="amt_inc_dec" class="form-control" onkeypress="return validateNumber(event, this)"  maxlength="5">
                					</div>
              					</div>
              					</div>
              				<div class="col-md-6">
              					<div class="row form-group">
                					<div class="col col-md-6">
                  						<label class=" form-control-label">Remarks </label>
                					</div>
                					<div class="col-12 col-md-6">
                  						<input type="hidden" id="remarks_hid" name="remarks_hid" class="form-control char-counter1"  value="${edittransportmdfsCmd.remarks}">
                					    <textarea class="form-control char-counter1" id="remarks" name="remarks" maxlength="255"></textarea> 
                					</div>
              					</div>
	            			</div>
	            	  </div>
	            </div>
    		    <div  class="card-footer" align="center" >
					<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();setfun();" > 
					<a href="search_transport_mdfs" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
				</div>
	    		</div>
	    	</div>
	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

<c:url value="UpdateMdfs" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script>

function setfun()
{
	if($("#amt_inc_dec").val() == ""){
		$("#amt_inc_dec").val(0);
		
    }
}
$("#scenario").change(function(){
  	$("#location").val("");
  	$("#formation").val("");
  	$("#unit").val("");
  	
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
				  document.getElementById("formation_code").value="";
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
				  document.getElementById("unit_code").value="";
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
$(function() {
	
	 var wepetext1=$("#modification");
	
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTypeofModiList?"+key+"="+value,
	        data: {label:document.getElementById('modification').value},
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
	        	  alert("Please Enter Approved Modification");
	        	  wepetext1.val("");
	        	  wepetext1.focus();
	        	  return false;	             
	          }   	         
	      },     
	    });
	      
    	   try{
    			if(window.location.href.includes("&msg="))
    			{
    				var url = window.location.href.split("?updateid=")[0];
    				var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
    				//alert(id);
    				//window.location = url;
    				
    				 document.getElementById('updateid').value=id;
    		 		 document.getElementById('updateForm').submit();
    			}
    			
    		}
    		catch (e) {
    			// TODO: handle exception
    		}
      });
</script>
 
<script>
function isvalidData(){	
	  if($("input#amt_inc_dec").val()== 0){
			alert("Please Enter amt_inc_dec");
			$("input#amt_inc_dec").focus();
			return false;
		}	
	 return true;	
}
</script>

<script>
$(function() {
                
        $('#mct_no').change(function() {
           	var mct_no = this.value;     
          	var we_pe_no = $("input#we_pe_no").val();
           	if(mct_no != '' && we_pe_no != '')
   				{
           		$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct_no}).done(function(j) {
           		 if(j == ''){
   	           			document.getElementById("amt_inc_dec").disabled = true;
       		    		$("input:radio[name=inc_dec]").attr("disabled",true);
       		    		
       		    	}
   	           		else
   	           		{  	
   	           	   		$("#auth_amt").val(j);
   	           		    	
   		           	   	if(j == 0){
   	           		       	document.getElementById("inline-radio2").disabled = true;
   	           		     	document.getElementById("amt_inc_dec").disabled = true;
   	           		       	
   	           		    }
   	           		    else{
   	           		    	document.getElementById("inline-radio1").disabled = false;
   	           		    	document.getElementById("inline-radio2").disabled = false;
   	           		     	document.getElementById("amt_inc_dec").disabled = false;
   	           		    }
   	           		 	           		           		    		
       		    	}   	
                 }).fail(function(xhr, textStatus, errorThrown) { });
   				}
        });         
      });       
</script>

<script>
function incredecre(){
		var str = document.getElementById("amt_inc_dec_hid").value;
		var res = str.split("",1);
		
		if(res == '-'){
		
			$("#inline-radio2").prop("checked", true);
	 		$("#inline-radio2").attr('checked', 'checked');
		
		  document.getElementById("amt_inc_dec_hid").value = str.substr(0);
		  document.getElementById("amt_inc_dec").value=str.substr(1, str.length);
		}  
		else{
		    $("#inline-radio1").prop("checked", true);
		$("#inline-radio1").attr('checked', 'checked');
		   document.getElementById("amt_inc_dec").value=str;
	   }
	}  
	
	
$(document).ready(function(){
	
	 $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });

	$.ajaxSetup({
	    async: false
	});
	var scenario_code = $("input#scenario_hid").val();

$("select#scenario").val(scenario_code);
 
if(scenario_code =="Location")
{    
	   $("label#labelcon").text("Location");
    $("div#getloc").show();
    $("div#getform").hide();
    $("div#getunit").hide();
}
else if(scenario_code =="Formation")
{
	   $("label#labelcon").text("Formation");
	   $("div#getform").show();
	   $("div#getloc").hide();
	   $("div#getunit").hide();
}
else if(scenario_code =="Unit")
{
	   $("label#labelcon").text("Unit");
	   $("div#getunit").show();
	   $("div#getloc").hide();
	   $("div#getform").hide();
}
else
{
	   $("label#labelcon").text("Others");
	   $("div#getloc").hide();
	   $("div#getform").hide();
	   $("div#getunit").hide();
 } 

$.ajaxSetup({
    async: false
});
	incredecre();
	
	
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
		        }).fail(function(xhr, textStatus, errorThrown) { })

		$.ajaxSetup({
		    async: false
		});
	$("textarea#remarks").val($("input#remarks_hid").val());
	
	var mct_no = $("input#mct_no").val();	
	
	 $.post("getMctMaintoStdNomenclatureList?"+key+"="+value, {mct_no:mct_no}).done(function(j) {
		 for ( var i = 0; i < j.length; i++) {           			
	   	 		$("input#std_nomclature").val(j);		
	   	 	}
	        }).fail(function(xhr, textStatus, errorThrown) { }); 
	
 	var we_pe_no = $("input#we_pe_no").val();
 	
 	$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no,mct_no:mct_no}).done(function(j) {
    	for ( var i = 0; i < j.length; i++) {           			
   	 		$("input#auth_amt").val(j);		
   	 		
   	 	if(j == ''){
   			document.getElementById("amt_inc_dec").disabled = true;
	    		$("input:radio[name=inc_dec]").attr("disabled",true);
	    		
	    	}
   		else
   		{  	
   	   		$("#auth_amt").val(j);
   		    	
   		    if(j == 0){
   		       	document.getElementById("inline-radio2").disabled = true;
   		    }
   		 	           		           		    		
	    	}   
   	 	}
	        }).fail(function(xhr, textStatus, errorThrown) { }); 
 
  	var we_pe_no = $("input#we_pe_no").val();
 	
 	
 	
 	$.post("getWEPENoDetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
 		for ( var i = 0; i < j.length; i++) {           			
   	 		$("input#table_title").val(j);		
   	 	}
	}).fail(function(xhr, textStatus, errorThrown) { });
 	
 	
 	var table_title = $("input#table_title").val();
  	
 	$.post("getBaseAuthAmountDetailsList?"+key+"="+value, {we_pe_no:we_pe_no}).done(function(j) {
		 for ( var i = 0; i < j.length; i++) {           			
	   	 		$("input#table_title").val(j);		
	   	 	}
	}).fail(function(xhr, textStatus, errorThrown) { });
  	
	document.getElementById("auth_amt").value=parseFloat(document.getElementById("auth_amt").value);
	 document.getElementById("amt_inc_dec").value=parseFloat(document.getElementById("amt_inc_dec").value);
  
});
</script>


<script>
function validate(){
	if($("#we_pe_no").val() == ""){
		alert("Please Enter the We/PE No");
		$("#we_pe_no").focus();
		return false;
	}

	if($("#scenario").val() == ""){
		alert("Please Enter the Scenario");
		$("#scenario").focus();
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
	if($("#modification").val() == ""){
		alert("Please Enter the Modification.");
		$("#modification").focus();
		return false;
	}
	if($("#mct_no").val() == ""){
		alert("Please Select MCT No");
		$("#mct_no").focus();
		return false;
    }

	  if($("input#amt_inc_dec").val()=="" || $("input#amt_inc_dec").val()=="0"){
			alert("Please Enter Amount of Increment/Decrement");
			$("input#amt_inc_dec").focus();
			return false;
		}
	  
	  var base;
	  var amt;
	  if($("input#amt_inc_dec").val()!="" && $("input#amt_inc_dec").val()!='0')
		{		
		  amt = $("input#amt_inc_dec").val();
		  var r_s =  $('input:radio[name=inc_dec]:checked').val();	
		 base = document.getElementById("auth_amt").value;	
		 base = parseFloat(base);
		 amt = parseFloat(amt);
		 if(r_s == "I")
		 {
			  
			if ($("input#amt_inc_dec").val().includes("-"))
			 {
				
				 alert("Please Remove Dec Value of Amt Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
			 }
		 }
		 
		 if(r_s == "D")
			 {
			 
			 if (base < amt)
				 {
				 alert("Please Check Amount of Inc/Dec");
				 $("input#amt_inc_dec").focus();
				 return false;
				 }
			 else
				 return true;
			 }
			 
			 
		  }
	  if($("input#amt_inc_dec").val()!="" && $("input#amt_inc_dec").val()!='0')
	  {		 
		
		if($('input:radio[name=inc_dec]:checked').val() == "D")
		 {
			 if ($("#auth_amt").val() < $("#amt_inc_dec").val())
			 {
				 alert("Please Check Amount of Inc/Dec");
				 return false;
			 }
		 }
	  }
	  
	  return true;
	  
}
</script>

<script>
function isNumber0_9Only(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if( ! ( charCode >= 48 && charCode <= 57 ) && charCode != 8 ){
		 return false;
	}
    return true;
}

function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}

function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}
</script>