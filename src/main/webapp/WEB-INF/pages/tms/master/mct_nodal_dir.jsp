<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="mctnodalActionForm" id="mctnodalActionForm" action="mctnodalAction" method="POST" commandName="tms_nodal_dir_masterCmd"> 
	<div class="animated fadeIn">
		
	    	<div class="container" align="center">
	    		<div class="card" >
	    			<!-- <div class="card-header" ><h5>MCT NODEL DTE / DEPOT<br><span style="font-size: 10px;font-size:12px;color:red">(To be entered by MISO)</span></h5></div> -->
	    			<div class="card-header"><h5>NODAL DTE / DEPOT</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	          				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	             				        <div class="col-md-4">
		                  				  <label class=" form-control-label"><strong style="color: red;">* </strong>Nodal Dte/Depot</label>
		                				</div> 
		                				<div class="col-md-8">
		                					<form:select id="type_of_agncy" path="type_of_agncy" class="form-control">
		                						<option value="0">--Select--</option>
		                						<option value="Depot">Depot</option>
		                						<option value="Nodal_dte">Nodal Dte</option>
		                					</form:select>
		                  				</div>
		                  				<div class="col-md-4">
		                  					<form:errors path="type_of_agncy" id="type_of_agncy" cssClass="error"></form:errors>
		                  				</div>
		              				</div>	
	              				</div>
	              					<div class="col-md-6">
	              					<div class="row form-group">
							              <div class="col-md-4">
							                  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of  Vehicle</label>
							             </div>
							             <div class="col-md-8">
							                  <select name="type_of_veh" id="type_of_veh" class=" form-control">
							                          <option selected value="">--Select--</option>
												      <option value="A">A Vehicle</option>
										              <option value="B">B Vehicle</option>
										              <option value="C">C Vehicle</option>
											  </select>
							             </div>
						            </div>
	              				</div>
	              			</div>
	          			
	          				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	             				        <div class="col-md-4">
		                  				  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
		                				</div> 
		                				<div class="col-md-8">
		                  					<form:input id="sus_no" path="sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="8"/>
		                				</div>
		                				<div class="col-md-4">
		                					<form:errors path="sus_no" id="sus_no" cssClass="error"></form:errors>
		                				</div>
		              				</div>
	              				</div>
	              			</div>
	              		<%-- 	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	             				        <div class="col-md-4">
		                  				  <label class=" form-control-label"><strong style="color: red;">* </strong>Nodal Dte</label>
		                				</div> 
		                				<div class="col-md-8">
		                  					<form:input  id="nodal_dir" path="nodal_dir" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100"/>
		                  					
		                				</div>
		                				<div class="col-md-4">
		                					<form:errors path="nodal_dir" id="nodal_dir" cssClass="error"></form:errors>
		                				</div>
		              				</div>
	              				</div>
	              			</div> --%>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group" id="depot_Code_DiV" style="display: none;">
	             				        <div class="col-md-4">
		                  				  <label class=" form-control-label"><strong style="color: red;">* </strong>Depot Code</label>
		                				</div> 
		                				<div class="col-md-8">
		                					<form:input  id="depot_code" path="depot_code" maxlength="1" oninput="this.value = this.value.toUpperCase()" onkeypress="return onlyAlphabets(event);" class="form-control" autocomplete="off" />
		                				</div>
		                  			</div>
	              				</div>
	              			</div>
						</div>
					<div class="card-footer" align="center" class="form-control">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()">
	              		<input type="reset" class="btn btn-success btn-sm" value="Clear">   
		            </div> 
	        	</div>
			</div>
		
	</div>
</form:form>

<script type="text/javascript">

$(function() {
  
		$("#sus_no").keypress(function(){	  		
			var sus_no = this.value;
				 var susNoAuto=$("#sus_no");
				  susNoAuto.autocomplete({
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
				        	  document.getElementById("nodal_dir").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
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
					        	$("#nodal_dir").val(dec(enc,j[0]));	
							}).fail(function(xhr, textStatus, errorThrown) {
							});	      	
				      } 	     
				});	
			});
  
	$('#type_of_agncy').change(function() {
		var type_of_agncy = this.value;
		if(type_of_agncy == "Depot"){
			$("#depot_Code_DiV").show();
			$("#depot_code").val("");
			$("#sus_no").val("");
			$("#nodal_dir").val("");
			 $('#nodal_dir').attr('readonly', true);
		}else{
			$("#depot_Code_DiV").hide();
			$("#depot_code").val("");
			$("#sus_no").val("");
			$("#nodal_dir").val("");
			$('#nodal_dir').attr('readonly', false);
			$('#nodal_dir').attr('oninput', 'this.value = this.value.toUpperCase()');
			$('#nodal_dir').attr('onkeyup', "checkSpace('nodal_dir');");
		}
	});

  
});
</script>

<script>
function validate() {
	if($("select#type_of_agncy").val() == "0"){
		alert("Please Select Nodal Dte/Depot.");
		$("select#type_of_agncy").focus();
		return false;
	}
	if(type_of_veh.selectedIndex == 0){
		alert("Please Select the Type of Vehicle.");
		$("#type_of_veh").focus();
		return false;
	} 
	if($("#sus_no").val() == ""){
		alert("Please Enter SUS No.");
		$("#sus_no").focus();
		return false;
	} 
 	if($("input#nodal_dir").val() == ""){
		alert("Please Enter MCT Nodal Dte/Depot or Unit Name.");
		$("input#nodal_dir").focus();
		return false;
	}
	if($("#type_of_agncy").val() == "Depot" && $("#depot_code").val() == ""){
		alert("Please Enter Depot Code.");
		$("#type_of_agncy").focus();
		return false;
	}  
	return true;
}
function onlyAlphabets(e, t) {
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
}
</script>


