<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="mctreqagencyActionForm" id="mctreqagencyActionForm" action="mctagencyAction" method="POST" commandName="tms_request_agency_masterCmd"> 
	<div class="animated fadeIn">
		
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>DEPOT / UNIT / EST</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-8">
	              					<div class="row form-group">
	            					    <div class="col col-md-6">
	                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>Depot / Unit / Est</label>
	                					</div> 
	                				  <div class="col-12 col-md-6">
										<textarea id="req_agency" name="req_agency" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"  oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('req_agency');" maxlength="100"></textarea>
									   </div>                	
	              					</div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-8">
	              					<div class="row form-group">
	            					    <div class="col col-md-6">
	                  						  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	                					</div> 
	                					<div class="col-12 col-md-6">
	                  						<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control autocomplete" autocomplete="off" oninput="this.value = this.value.toUpperCase()" onkeyup="checkSpace('sus_no');" >
	                					</div>
	              					</div>
	              				</div>
	              			</div>
            			</div>
					<div class="card-footer" align="center" class="form-control">						
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save"  onclick="return validate();">
	              		<input type="reset" class="btn btn-success btn-sm" value="Clear">	              		
		            </div> 
	        	</div>
			</div>
		</div>

</form:form>

<script>
      $(document).ready(function() {      
    	    	   		
    		 $("#req_agency").keypress(function(){
     			var unit_name = this.value;
     				 var susNoAuto=$("#req_agency");
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
     			 $(this).val(ui.item.value);     				      	
     			 var target_unit_name = ui.item.value;
     		
    			$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
    				target_unit_name:$(this).val()
    			}).done(function(j) {
    				  var length = j.length-1;
			        	var enc = j[length].substring(0,16);
			        	$("#sus_no").val(dec(enc,j[0]));	
    			}).fail(function(xhr, textStatus, errorThrown) {
    			});
     				       
     				      }     
     				});
     			
     		});
      	  	
    			$("input#sus_no").keyup(function(){
          			var sus_no = this.value;
          			var unitNameAuto=$("#sus_no");
          			unitNameAuto.autocomplete({
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
          			        	  $("#req_agency").val("");
          			        	  unitNameAuto.val("");	        	  
          			        	  unitNameAuto.focus();
          			        	  return false;	             
          			          }   	         
          			      }, 
          			      select: function( event, ui ) {
            			      	var sus_no = ui.item.value;            			      	
            				
            					$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
            						sus_no:sus_no
            					}).done(function(j) {
            						var length = j.length-1;
            			        	var enc = j[length].substring(0,16);
            			        	$("#req_agency").val(dec(enc,j[0]));	
            					}).fail(function(xhr, textStatus, errorThrown) {
            					});            			      	
            			     }
          			});
          		});
    	});
</script>

<script>
function validate() {
	
	if($("input#req_agency").val() == ""){
		alert("Please Enter Depot/Unit/Est or SUS No.");
		$("input#req_agency").focus();
		return false;
	}
	if($("#sus_no").val() == ""){
		alert("Please Enter Depot/Unit/Est or SUS No.");
		$("#sus_no").focus();
		return false;
	}
	return true;
}
</script>
 

