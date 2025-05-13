<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form id="accidental_vehicle" name="accidental_vehicle" action="accidental_vehicleAction" method='POST' class="form-horizontal" commandName="accidental_vehicleCMD"> 
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h3>ACCIDENTAL VEHICLE REPORT</h3></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">
                                           <div class="col-md-6">
                                                      <div class="row form-group">
	                						<div class="col-md-4">
	                  							<label class=" form-control-label"><strong style="color: red;">*</strong>BA No</label>
	                						</div>
	   
	                					<div class="col-12 col-md-8">
	              							<input type="text" id="ba_no" name="ba_no" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="10" />
	                  					</div>
	              					</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
                						<div class=" col-md-4">
                						    <label class=" form-control-label"><strong style="color: red;">*</strong>Auth </label>
                						 </div>
                						<div class=" col-md-8">
                                           <input type="text" id="authority" name="authority" placeholder="" class="form-control autocomplete" autocomplete="off"/> 
                					    </div>
	                				</div> 
                            	</div>
							</div>
	            		</div> 
						<div class="form-control card-footer" align="center">
							<input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return validate();">
							<a href="accidental_vehicle" class="btn btn-success btn-sm" >Clear</a> 
			            </div> 	
					</div>
				</div>
			</div>
		</div>	 
</form:form>
<script>
function validate() {
	if($("#ba_no").val() == ""){
		alert("Please Enter the Ba No.");
		return false;
	}
	return true;
}
</script>
<script>
	
	$(function() {
        $("#ba_no").keypress(function(){
				var ba_no = this.value;
			 	var susNoAuto=$("#ba_no");
			  	susNoAuto.autocomplete({
			  	source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			        	url: "getBaNoFromMVCRprtl?"+key+"="+value,
			        	data: {ba_no:ba_no},
			        	success: function( data ) {
			        	
	        			 var unitval = [];
		        	     var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
			        	}
			        	for(var i = 0;i<data.length;i++){
			        		unitval.push(dec(enc,data[i]));
			        	}
			        	response( unitval ); 
			        	}
			    	});
			    },
			    minLength: 1,
			    autoFill: true,
			  	change: function(event, ui) {
			    	if (ui.item) {   	        	  
			          return true;    	            
			        } else {
			        	alert("Please Enter BA No.");
			        	document.getElementById("ba_no").value="";
			        	susNoAuto.val("");	        	  
			        	susNoAuto.focus();
			        	return false;	             
			    	}   	         
			    }, 
			    select: function( event, ui ) {
			      	var ReName = ui.item.value;
					
				} 	     
			});
		});
		
	});
	
</script>
