<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="copy_wet_pet_fet" id="copy_wet_pet_fet" action="copy_wet_pet_fetAction" method='POST' commandName="copy_wet_pet_fetCmd">
	          		
	 	<div class="container" align="center">
        	<div class="card">
          		<div class="card-header"><h5><b>Copy WET/PET/FET DATA</b><br><span style="font-size: 10px;font-size:15px;color:red">(To be entered by etrc/miso)</span></h5></div>        		
          		
          		<div class="card-body card-block cue_text">
          		<div class="col-md-12">
          		<div class="col-md-6">
          		<div class="row form-group"> 
            			 <div class="col-12 col-md-6">
            			 <label for="text-input" class=" form-control-label">Type of Document <strong style="color: red;">*</strong></label>
				          </div>
				           <div class="col-12 col-md-6">      	 <div class="form-check-inline form-check" > 
		                                <label for="inline-radio1" class="form-check-label" >
		                                  <input type="radio" id="wet_pet1" name="wet_pet" value="WET" class="form-check-input">WET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" class="form-check-input">PET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-radio2" class="form-check-label ">
		                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" class="form-check-input">FET
		                                </label>&nbsp;&nbsp;&nbsp;
		                                      
		                             </div>
				                </div>
				                </div>
				                </div>
           			</div>
            		<div class="col-md-12">	              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">WET/PET/FET No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="wet_pet_no1" name="wet_pet_no1"  class="form-control" maxlength="100" autocomplete="off" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
 							</div> 
  						</div>
  						
  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Unit Type</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="unit_type1" name="unit_type1" maxlength="100" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>	 							
	  					</div>
  					</div>
  					
  					<div class="col-md-12">	              					
              			<div class="col-md-6">
              			 	<div class="row form-group"> 
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Copy WET/PET/FET No <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="wet_pet_no" name="wet_pet_no" maxlength="100" class="form-control"  autocomplete="off">
								</div>
 							</div> 
  						</div>
  						
  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Copy Unit Type <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="unit_type" name="unit_type" class="form-control" maxlength="100" autocomplete="off" >
								</div>
							</div>	 							
	  					</div>  					
  						
  					</div>  	
  					
  					<div class="col-md-12">	
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Remarks</label>
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<textarea id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"></textarea>
									</div>	 							
		  						</div>
		  						</div>	
	   					</div>	
  									
  						
  				</div>
  				 
  				<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
             			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidation();" >
            	</div> 						
 			</div>
		 </div>
</form:form>	

 
<script>

$(document).ready(function() {
	
	if('${wet_pet01}' != "")
	{
		$("input[name=wet_pet][value="+'${wet_pet01}'+"]").prop('checked', true);
		$("#wet_pet_no1").val('${wet_pet_no01}');
		$("#unit_type1").val('${unit_type01}');
	}
	
	$.ajaxSetup({
	    async: false
	});
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	   	if(r != undefined)
	    	getWePeNoByType(r);	
	
	getarmvalue();
	
	$('#wet_pet_no').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	   
	   $('#unit_type').keyup(function() {
  	        this.value = this.value.toUpperCase();
  	    });
	   $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	   
	try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		}
	}
	catch (e) {
		// TODO: handle exception
	}
  
});	

 function getarmvalue(){
	
	$('input[type=radio][name=wet_pet]').change(function() {		
		document.getElementById("wet_pet_no1").value="";
		document.getElementById("unit_type1").value="";
		var radio_doc = $(this).val();
		getWePeNoByType(radio_doc);
	});
	
}
 
 function getWePeNoByType(radio_doc)
 {
	 if(radio_doc != ""){
	 var wepetext=$("#wet_pet_no1");
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
 	        url: "getWetPetFetByNo?"+key+"="+value,
	        data: {val : radio_doc,wet_pet_no : document.getElementById('wet_pet_no1').value},
	          success: function( data ) {
	            //response( data );
	           // var dataCountry1 = data.join("|");
	           if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
	        		 var enc = data[length].columnName1.substring(0,16);
                   for(var i = 0;i<data.length-1;i++){
                    susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	var dataCountry1 = susval.join("|");
	            var myResponse = []; 
	            var autoTextVal=wepetext.val();
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
	        	alert("Please Enter Approved WET/PET No");
	        	wepetext.val("");	
	        	  
	        	$("#unit_type1").val("");
	 			
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
        select: function( event, ui ) {
       	$(this).val(ui.item.value);    	        	
       	getWetPetFetByUnit_type($(this).val());	        	
        } 	     
	    });
	 }
	 else
		 alert("Please Select Document Type");
	
 }
 
 function getWetPetFetByUnit_type(val)
 {
	 document.getElementById("unit_type1").value="";
	
	  if(val != "" && val != null)
	  {
		$.post("getWetPetFetByUnit_type?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!= null)
				document.getElementById("unit_type1").value=j[0].unit_type;		
			else
				document.getElementById("unit_type1").value="";
	 	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
	  else
	  {
		  alert("Please enter WET/PET/FET No");
		  document.getElementById("unit_type1").value="";
		  }
 }
 
 
 function isvalidation(){
	 
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	 {		 
		 alert("Please Select WET/PET/FET Radio");
			return false;
	 }
	 	 
	  if($("input#amt_inc_dec").val()==""){
			alert("Please Enter Amount of Increment/Decrement");
			$("input#amt_inc_dec").focus();
			return false;
		}
	   
	 if($("input#wet_pet_no1").val()=="" ){
			alert("Please Enter WET/PET/FET  ")
			$("input#wet_pet_no1").focus();
			return false;
		}
	   
	   if($("input#wet_pet_no").val()=="" ){
			alert("Please Enter Copy WET/PET/FET  ")
			return false;
		}
	   
	   if($("input#unit_type").val()=="" ){
			alert("Please Enter Unit Type")
			return false;
		}
	   
	 return true;  
 }
	</script> 