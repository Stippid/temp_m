<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>


<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {

});
</script>

<form:form name="updateUnitName" id="updateUnitName" action="updateUnitNameAction" method='POST' commandName="updateUnitNameCMD">
		<div class="container" align="center">
        	<div class="card">
					<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"><h5><b>UPDATE UNIT NAME</b></h5> </div>
							<div class="card-body card-block">
	            				<!-- <form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> -->
	            				<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Search For</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                						<div class="row form-group">
													<div class="col-md-6">
														<input type="radio" id="Active" name="srch-radios" value="Active" class=""> <!-- onclick="return getStatus('Active');"  --> 
														Active Units
													</div>	
													<div class=" col-md-6">
														<input type="radio" id="Inactive" name="srch-radios" value="Inactive" class="" ><!-- onclick="return getStatus('Inactive');" -->
														<label class=" form-control-label">Inactive Units</label>
													</div>
												</div>                					
		                					</div>
		              					</div>
									</div>
								</div>
								<div id="divhide" style="display:none">
								<div class="col-md-12">
							<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">			
										<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 12px;" autocomplete="off" maxlength="100" placeholder="select Unit Name"></textarea>
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">SUS No</label>
						            </div>
						            <div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no"  class="form-control autocomplete" maxlength="8" autocomplete="off"  readonly>
									</div>
              					</div>
	          				</div>
	          			</div>
									
									<div class="col-md-12">	
									
									<div class="col-md-6">
										 <div class="row form-group">
							                <div class="col col-md-4">
							                  <label class=" form-control-label">New Unit Name</label>
							                </div>
							                <div class="col-12 col-md-8">
					<!-- 	ADD BY RAJ -->	    <input type="text" id="new_unit_name" name="new_unit_name" maxlength="100" placeholder="Enter Unit Name" style="font-family: 'FontAwesome',Arial;text-transform :uppercase" class="form-control" >
							                </div>
							              </div>
									</div>
								</div>
							</div>
									
					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="button" class="btn btn-success btn-sm" value="Clear" onclick="cancel()">   
<!-- 						<input type="button" class="btn btn-danger btn-sm" value="Cancel" onclick="cancel()"> -->
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isvalidData();"> 
	              	</div> 
		           
	        	</div>			
  		</div>

</form:form>


<script>



$(document).ready(function() {
    $('input[name="srch-radios"]').change(function() {
    	 var divHide = document.getElementById("divhide");
    	 divHide.style.display = "block";  
    	document.getElementById("unit_name").value="";
    	document.getElementById("sus_no").value="";
    	document.getElementById("new_unit_name").value="";
        var selectedStatus = $(this).val();
        
        //raj
         $('#new_unit_name').keyup(function() {
		        this.value = this.value.toUpperCase();
		    });
    

    var initialStatus = $('input[name="srch-radios"]:checked').val();

if (initialStatus == "Active"){

	jQuery("#unit_name").keyup(function(){
		
			var unit_name = this.value;
			 var susNoAuto=jQuery("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
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
			        	  document.getElementById("unit_name").value="";
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
					}   	         
				}, 
				select: function( event, ui ) {
					var target_unit_name = ui.item.value;
				     jQuery("#new_unit_name").val(target_unit_name);
				 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
				 		 var length = j.length-1;
					         var enc = j[length].substring(0,16);
					         jQuery("#sus_no").val(dec(enc,j[0]));	
					}).fail(function(xhr, textStatus, errorThrown) {
				});
			}
		}); 	
	});   
	 }else if(initialStatus == "Inactive"){
		 jQuery("#unit_name").keyup(function(){
		 var unit_name = this.value;
		 var susNoAuto=jQuery("#unit_name");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        jQuery.ajax({
		        type: 'POST',
		        url: "getUnitsNameInactiveList?"+key+"="+value,
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
		        	  document.getElementById("unit_name").value="";
		        	  document.getElementById("sus_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
				}   	         
			}, 
			select: function( event, ui ) {
				var target_unit_name = ui.item.value;
				 jQuery("#new_unit_name").val(target_unit_name);
			 	$.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
			 		 var length = j.length-1;
				         var enc = j[length].substring(0,16);
				         jQuery("#sus_no").val(dec(enc,j[0]));	
				}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	}); 

		});
	 }
});
});
function isAlphabeticsA_ZOnly(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(( charCode >= 48 && charCode <= 57 ) && ! charCode != 8 ){
		 return false;
	}
    return true;
}

function cancel(){
	window.location.reload();

}
function isvalidData()
{	debugger;

var initialStatus1 = $('input[name="srch-radios"]:checked').val();
if (initialStatus1 === undefined) {
	alert("please seclet status")
	return false;
}
	if($("#unit_name").val() == "")
	{
		alert("Please Enter Unit Name");
		$("input#month").focus();
		return false;
	}
	if($("input#new_unit_name").val() == "")
	{
		alert("Please Enter New Unit Name");
		$("input#year").focus();
		return false;
	}
	 
	return true; 
}


//add by raj

$(function(){
	$('#new_unit_name').keyup(function(){	
		yourInput= this.value.toUpperCase();
		re = /[`~!@#$%^&*_|+\-=?;:'",.<>\{\}\[\]\\]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar)
		{
			alert("Don't Enter Special Character");
			var no_spl_char = yourInput.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\]/gi, '');
			$(this).val(no_spl_char);
		}
	});
});

</script>