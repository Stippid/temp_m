<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
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
<form:form action="edit_link_wet_weAction" method="post" class="form-horizontal" commandName="edit_link_wet_weCMD"> 
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header" ><h5>Edit LINK WE/PE TO WET/PET/FET</h5></div>
	          			<div class="card-body card-block cue_text">
	            			<div class="col-md-12">
								<div class="col-md-7">
		              				<div class="row form-group">
							             <div class="col col-md-6">
							                	<label for="text-input" class=" form-control-label">WE/PE Document </label>
							             </div>
										 <div class="col col-md-6">
						                        <div class="form-check-inline form-check">
						                              <label for="inline-radio1" class="form-check-label ">
						                                  <input type="radio" id="we_pe1" name="WE" value="WE" class="form-check-input" disabled="disabled">WE
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio2" class="form-check-label ">
						                                  <input type="radio" id="we_pe1" name="PE" value="PE" class="form-check-input" disabled="disabled">PE
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio3" class="form-check-label ">
						                                  <input type="radio" id="we_pe1" name="FE" value="FE" class="form-check-input" disabled="disabled">FE
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio4" class="form-check-label ">
						                                  <input type="radio" id="we_pe1" name="GSL" value="GSL" class="form-check-input" disabled="disabled">GSL
						                               </label>
						                         </div>
						                 </div>
										</div>
									 </div>
									</div>
									<div class="col-md-12">
									   <div class="col-md-7">
										<div class="row form-group">
	                                             <div class="col col-md-6">
	                                                     <label class=" form-control-label">WE/PE Document No </label>
	                                             </div>
	                                             <div class="col-12 col-md-5">
	                                                     <input type="hidden" id="id" name="id" class="form-control" value="${edit_link_wet_weCMD.id}">
		                  						         <input type="text" id="we_pe_no" name="we_pe_no"  class="form-control autocomplete" autocomplete="off" value="${edit_link_wet_weCMD.we_pe_no}" disabled="disabled" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
		                					     </div>
	                                    </div>
								     </div>
								  <div class="col-md-5">
								    <div class="row form-group">
	                                             <div class="col col-md-5">
	                                                     <label class=" form-control-label">Table Title </label>
	                                             </div>
	                                             <div class="col-12 col-md-7">
		                  						         <input type="text" id="table_title" name="table_title" placeholder="" class="form-control" value="${edit_link_wet_weCMD.table_title}" disabled="disabled">
		                					     </div>
	                                </div>
								</div>
	            		      </div>
	            		      <div class="col-md-12">
		            		  <div class="col-md-7">
		            		        <div class="row form-group">
							             <div class="col col-md-6">
							                	<label for="text-input" class=" form-control-label">WET/PET/FET Document <strong style="color: red;">*</strong></label>
							             </div>
										 <div class="col col-md-6">
						                        <div class="form-check-inline form-check">
						                              <label for="inline-radio5" class="form-check-label ">
						                                  <input type="radio" id="we_pe2" name="WET" value="WET" class="form-check-input">WET
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio6" class="form-check-label ">
						                                  <input type="radio" id="we_pe2" name="PET" value="PET" class="form-check-input">PET
						                               </label>&nbsp;&nbsp;&nbsp;
						                               <label for="inline-radio7" class="form-check-label ">
						                                  <input type="radio" id="we_pe2" name="FET" value="FET" class="form-check-input">FET
						                               </label>
						                         </div>
						                 </div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
							  <div class="col-md-7">
									<div class="row form-group">
	                                             <div class="col col-md-6">
	                                                     <label class=" form-control-label">WET/PET/FET Document No <strong style="color: red;">*</strong></label>
	                                             </div>
	                                             <div class="col-12 col-md-5">
		                  						         <input type="text" id="wet_pet_no" name="wet_pet_no"  maxlength="50" class="form-control autocomplete" autocomplete="off" value="${edit_link_wet_weCMD.wet_pet_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
		                					     </div>
	                                </div>
		            		  </div>
		            		  <div class="col-md-5">
		            		     <div class="row form-group">
	                                             <div class="col col-md-5" >
	                                                     <label class=" form-control-label">Table Title </label>
	                                             </div>
	                                             <div class="col-12 col-md-7">
		                  						         <input type="text" id="unit_type" name="unit_type" placeholder="" class="form-control" disabled="disabled">
		                					     </div>
	                             </div>
		            		  </div>
	            		</div>
	            	</div>
					 <div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">
					<a href="search_link_wet_to_we" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 
	        	</div>
			</div>
	</div>
</form:form>
 

 
<script>
function validate(){
  
	 if($("#we_pe_no").val() == ""){
		alert("Please Enter WE/PE No");
		$("#we_pe_no").focus();
		return false;
	 } 
   
	 if($("#wet_pet_no").val() == ""){
		  alert("Please Enter WET/PET No");
		  $("#wet_pet_no").focus();
		  return false;
	 } 
	return true;
}
</script>

<script>

$('#wet_pet_no').change(function() {
    var wet_pet_no = this.value;
    $('#wet_pet_no').change(function() {
        var wet_pet_no = this.value;
        $.post("getWetpetNoDetailsList?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
       		for ( var i = 0; i < j.length; i++) {
       	 		$("#unit_type").val(j[i].unit_type);
       		}
        }).fail(function(xhr, textStatus, errorThrown) { }); 	
    }); 
}); 

$("input[type='radio']").click(function(){
	var we_pe1 = $("input[name='we_pe1']:checked").val();
	
	
	if(this.value == 'WET'){
		var wet_pet = this.value;
		$('input[name=PET],[name=FET]').prop('checked',false);
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		getWetPetNoByType(wet_pet);		
	}	
	else if(this.value == 'PET'){
		var wet_pet = this.value;
		$('input[name=WET],[name=FET]').prop('checked',false);
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		getWetPetNoByType(wet_pet);		
	}
	else if(this.value == 'FET'){
		var wet_pet = this.value;
		$('input[name=WET],[name=PET]').prop('checked',false);
		document.getElementById("wet_pet_no").value = "";
		document.getElementById("unit_type").value = "";
		
		getWetPetNoByType(wet_pet);
	}	

});	 

function getWetPetNoByType(wet_pet)
{
	 if(wet_pet != ""){
	 var wepetext=$("#wet_pet_no");
	
	  wepetext.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	  	    url: "getWetPetFetNo?"+key+"="+value,
	        data: {val : wet_pet,wet_pet_no:document.getElementById('wet_pet_no').value},
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
	        	
	        	wepetext.focus();
	        	return false;	             
	          }   	         
	      }, 
	    });
	 }
	 else
		 alert("Please select Document");
	
}
</script>

<script>
var wet_pet_no = '${edit_link_wet_weCMD.wet_pet_no}';
var wet_pet;
var b;

$(document).ready(function() {
	 
	if('${edit_link_wet_weCMD.we_pe}' == "WE"){
		$('input[name=PET],[name=FET],[name=PE],[name=FE],[name=GSL]').attr("disabled", true);
		$('input[name=WE],[name=WET]').prop('checked',true);  	
		wet_pet = 'WET';
	
		getWetPetNoByType(wet_pet);
	}else if('${edit_link_wet_weCMD.we_pe}' == "PE"){
		$('input[name=WET],[name=FET],[name=WE],[name=FE],[name=GSL]').attr("disabled", true);
		$('input[name=PE],[name=PET]').prop('checked',true); 
		wet_pet = 'PET';
		
		getWetPetNoByType(wet_pet);
	}else if('${edit_link_wet_weCMD.we_pe}' == "FE"){
		$('input[name=WET],[name=PET],[name=WE],[name=PE],[name=GSL]').attr("disabled", true);
		$('input[name=FE],[name=FET]').prop('checked',true);  
		wet_pet = 'FET';
		
		getWetPetNoByType(wet_pet);
	}else if('${edit_link_wet_weCMD.we_pe}' == "GSL"){
		$('[name=WE],[name=PE],[name=FE]').attr("disabled", true);
		$('input[name=GSL]').prop('checked',true);
		 $.post("getWetPetFetListValue?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
			for(var i = 0;i < j.length;i++){
				$('input[name='+j[i]+']').prop('checked',true);
				wet_pet = j[i];
				getWetPetNoByType(wet_pet);			
		    }	
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	}
	 $.ajaxSetup({
		    async: false
		});
	
	 $.post("getWetpetNoDetailsList?"+key+"="+value, {wet_pet_no:wet_pet_no}).done(function(j) {
			for ( var i = 0; i < j.length; i++) {
				$("#unit_type").val(j[i].unit_type);
		    }
		 }).fail(function(xhr, textStatus, errorThrown) { });
	
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
       
</script>
