<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit WE Link with WET</title></head>

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

<script type="text/javascript">
function getarmvalue(val){
	 
	 var val=document.getElementById("wet_pet_no").value;
	 
	if(val != "" && val != null)
	 	  {
		$.post("getsuperseededvalue2?"+key+"="+value, {val : val}).done(function(j) {
	 			if(j!="" && j!=null){
	 			 document.getElementById("table_title_WETPET").value=j[0].unit_type;
	 			$('input[name=wet_pet]').prop('checked', true);
	 			}
	 			else
	 			{
	 				document.getElementById("table_title_WETPET").value="";
		 			$('input[name=wet_pet]').prop('checked', false);
	 			}
		}).fail(function(xhr, textStatus, errorThrown) { }); 	
	 	  }
}

 </script>

<body>

<form:form id="EditWE_link_with_WET_A" name="EditWE_link_with_WET_A" action="EditWE_link_with_WET_Action" method="post"  class="form-horizontal" commandName="EditWE_link">
	<div class="animated fadeIn">
		 	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit UPLOAD WE/PE TO UPLOAD WET/PET/FET</h5> </div>
	          			<div class="card-body card-block cue_text">
	          			<div class="col-md-12">
	            			<div class="col-md-7">
	            					<div class="row form-group">
						                	<div class="col col-md-6">
						                		<label for="text-input" class=" form-control-label">WE/PE Document</label>
						                	</div>
						                
											  <div class="col col-md-6">
							                              <div class="form-check-inline form-check">
							                                <label for="inline-radio1" class="form-check-label ">
							                                 <input type="hidden" id="id" name="id" value="${EditWE_link.id}" class="form-control">
							                                <input  type="hidden" id="we_pe_radio" value="${EditWE_link.we_pe}" class="form-control" >
							                                  <input type="radio" id="we_pe1" name="we_pe" value="WE" class="form-check-input" disabled="disabled">WE
							                                 
							                                  
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio2" class="form-check-label ">
							                                  <input type="radio" id="we_pe2" name="we_pe" value="PE" class="form-check-input" disabled="disabled">PE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="we_pe3" name="we_pe" value="FE" class="form-check-input" disabled="disabled">FE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio3" class="form-check-label ">
							                                <input type="radio" id="we_pe4" name="we_pe" value="GSL" class="form-check-input" disabled="disabled">GSL
							                                </label>&nbsp;&nbsp;&nbsp;
							                                </div>
							                                </div>
							                                </div>
		        				</div>
		        				</div>
		        				<div class="col-md-12">
								<div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                					
	                  						<label class=" form-control-label">WE/PE Document No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                						<input  type="hidden" id="id" name="id" class="form-control">
	                						   <input type="hidden" id="id" name="id" value="${EditWE_link.id}" class="form-control" >
	                  					     <input type="text" id="we_pe_no" name="we_pe_no" class="form-control" value="${EditWE_link.we_pe_no}" readonly="readonly" >
	                					</div>
	              					</div>
	              				
							   </div>
							   
							<div class="col-md-5">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  					     <input type="text" id="table_title" name="table_title" class="form-control" readonly="readonly" value="${EditWE_link.table_title}">
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
							                                <label for="inline-radio1" class="form-check-label ">
							                              
							                             <input type="hidden" id="radio" name="radio" class="form-control">
							                             <input type="radio" id="wet_pet1" name="wet_pet" value="WET" class="form-check-input">WET
							                               </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio2" class="form-check-label ">
							                                  <input type="radio" id="wet_pet2" name="wet_pet" value="PET" class="form-check-input">PET
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="wet_pet3" name="wet_pet" value="FET" class="form-check-input">FET
							                                </label>&nbsp;&nbsp;&nbsp;
							                              </div>
							                            </div>
												</div>
	            				</div>
	              				</div>
	              				<div class="col-md-12">	
	              			 <div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">WET/PET/FET Document No<strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  					     <input type="text" id="wet_pet_no" name="wet_pet_no"  maxlength="50"  class="form-control"
	                  					      value="${EditWE_link.wet_pet_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search" autocomplete="off">
	                					</div>
	              					</div>
	              					</div>		
	              					
	              					
	              			<div class="col-md-5">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Table Title</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  					     <input type="text" id="table_title_WETPET" name="table_title_WETPET" class="form-control" readonly="readonly">
	                					</div>
	              					</div>
	              				
							   </div>
							   </div>
							   <div class="col-md-12">
							   <div class="col-md-7">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Should be visible to Unit or not? <strong style="color: red;">*</strong></label>
	                					</div>
	                					<div class="col col-md-4">
				                              	<div class="form-check-inline form-check">
				                                	<label for="inline-radio1" class="form-check-label ">
				                                	<input type="hidden" id="id" name="id" value="${EditWE_link.id}" class="form-control">
				                                	 <input  type="hidden" id="unit_visible_hi" value="${EditWE_link.unit_visible}" class="form-control" >
				                                  		<input type="radio" id="unit_visible1" name="unit_visible" value="Yes" maxlength="3"  class="form-check-input">Yes
				                                	</label>&nbsp;&nbsp;&nbsp;
				                                	<label for="inline-radio2" class="form-check-label ">
				                                		<input type="radio" id="unit_visible2" name="unit_visible" value="No" maxlength="3" class="form-check-input">No
				                                	</label>
				                              	</div>
				                            </div>
	              					</div>
							   </div>
							   
							   </div>
	              				</div>			
	              		</div>
	              		
	              		<div class="card-footer" align="center">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="">
	              		<a href="search_we_link_wet" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 
	              		
	              		</div>
	              		</div>
	              	
	              		</form:form>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="update_WE_linkwith_WET_Url" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
<script >

$(document).ready(function() {
 var we_pe_hi = $("input#we_pe_radio").val();
	 
	 if(we_pe_hi == "WE"){		  
		  document.getElementById("we_pe1").checked = true;		
		  $("#wet_pet2,#wet_pet3").attr("disabled", true);
	 } 
	 else if(we_pe_hi == "PE"){		  
		  document.getElementById("we_pe2").checked = true;	
		  $("#wet_pet1,#wet_pet3").attr("disabled", true);
	 } 
	 else if(we_pe_hi == "FE"){		  
		  document.getElementById("we_pe3").checked = true;	
		  $("#wet_pet1,#wet_pet2").attr("disabled", true);
	 } 
	 else if(we_pe_hi == "GSL"){		  
		  document.getElementById("we_pe4").checked = true;		 
	 } 

$.ajaxSetup({
    async: false
});
    
	var val=document.getElementById("wet_pet_no").value;

	if(val != "" && val != null)
	  {
		$.post("getsuperseededvalue2?"+key+"="+value, {val : val}).done(function(j) {
			var wet_pet_hi=j[0].wet_pet;
			 if(wet_pet_hi == "WET"){		  
				  document.getElementById("wet_pet1").checked = true;		
			 } 
			 else if(wet_pet_hi == "PET"){		  
				  document.getElementById("wet_pet2").checked = true;		
			 } 
			 else if(wet_pet_hi == "FET"){		  
				  document.getElementById("wet_pet3").checked = true;		
			 }  
			 
			document.getElementById("table_title_WETPET").value=j[0].unit_type;
			getWetpetNo($('input[name=wet_pet]').val());			 
			
		}).fail(function(xhr, textStatus, errorThrown) { }); 
	  }	
	
	var unit=document.getElementById("unit_visible_hi").value;
	if(unit != "" && unit != null)
	  {
		 if(unit == "Yes"){		  
			  document.getElementById("unit_visible1").checked = true;		
		 } 
		 else if(unit == "No"){		  
			  document.getElementById("unit_visible2").checked = true;		
		 }
	  }
	
	  try{
			if(window.location.href.includes("&msg="))
			{
				var url = window.location.href.split("?updateid=")[0];
				var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
				 document.getElementById('updateid').value=id;
		 		 document.getElementById('updateForm').submit();
			}
		}
		catch (e) {
			// TODO: handle exception
		}
});
	
	function getWetpetNo(val)
	 {
		  if(val !="" || val != null)
		  {
			  var wepetext=$("#wet_pet_no");
			 
			  wepetext.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			  	    url: "getWetPetFetNo?"+key+"="+value,
	   		        data: {val:val,wet_pet_no:document.getElementById('wet_pet_no').value},
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
			        	  
			        	document.getElementById("table_title_WETPET").value="";
			        	wepetext.focus();
			        	return false;	             
			          }   	         
			      }, 
		       select: function( event, ui ) {
		      	$(this).val(ui.item.value);    	        	
		      	getarmvalue2($(this).val());	        	
		       } 	     
			    });
		  }
	 }
	
function getarmvalue2(val){
	
	if(val != "" && val != null)
	  {
		$.post("getsuperseededvalue2?"+key+"="+value, {val : val}).done(function(j) {
			if(j!="" && j!= null)
				document.getElementById("table_title_WETPET").value=j[0].unit_type;
			else
				document.getElementById("table_title_WETPET").value="";
		  }).fail(function(xhr, textStatus, errorThrown) { }); 	
	  }
}

</script>

<script>
function isCueValid(){
	 var r =  $('input:radio[name=wet_pet]:checked').val();	
	  if(r == undefined)
	  {		 
		    alert("Please select Document");
		    $('input:radio[name=wet_pet]:checked').focus();
			return false;
	  }
	  return true;
	  }
	  
</script>             		
</body>
	              		
</html>
