<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- bed_occ_ex_serv -->
<form:form action="bed_occ_serv_editAction" id="bed_occ_serv"  method="post" class="form-horizontal" commandName="bed_occ_serv_editCMD">		
<div class="container" align="center">
    <div class="card">
         <div class="card-header mnh-card-header">
		        <b> UPDATE BED OCCUPANCY</b>
		       <h6>
					<span style="font-size: 12px; color: red">(To be entered by Medical Unit)</span>
				</h6>
		 </div>
		 
		 <div class="card-body card-block">
		     <div class="col-md-12">
		          <div class="col-md-2" style="text-align: left;"> 
		               <label class=" form-control-label"><strong style="color: red;">* </strong>Service Status</label>
		          </div>
		          
		          <div class="col-md-10">
		                <select name="type" id="type"  class="form-control-sm form-control">
               			<option value="-1">--Select--</option>
							<c:forEach var="item" items="${service_type}" varStatus="num">
								<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
							</c:forEach>
					</select>
		          </div>
		      </div>
		 </div>
		 
		 <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;display: none;" id="a1">
		      <strong>BED OCCUPANCY SERVING</strong>
		 </div>
		 
		 <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;display: none;" id="b1">
		      <strong>BED OCCUPANCY EX-SERVICEMEN</strong>
		 </div>
		 <div class="card-body card-block" id="a2" style="display: none;">
		     
		            <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
	               		 </div>
	               		 <div class="col-md-6">	               		    		
	               			  <input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" 
	               			  placeholder="Search..." autocomplete="off" maxlength="100" title="Type Unit Name or Part of Unit Name to Search" >
						 </div>	 
						 
						 <div class="col-md-2" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		 </div>
	             		 <div class="col-md-2">
	             		 		
	             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" 
	             			  placeholder="Search..." autocomplete="off" maxlength="8" title="Type SUS No or Part of SUS No to Search" /><!--value="${bed_occ_servCmd.sus_no1}"  -->
	               		 </div>  
  					</div> 
  					
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
	               		 </div>
	               		 
	               		 <div class="col-md-4">
	               		      <input type="text" id="command" name="command" class="form-control-sm form-control" readonly>
	               		      <input type="hidden" id="command_sus" name="command_sus" class="form-control-sm form-control">
	               		 </div>
	               		 
	            		 <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			    <select name="quater" id="quater"  class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
									<c:forEach var="item" items="${getMedSystemCodeQuarter}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
					            </select>
	               		 </div>
  					</div>
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
	               		 </div>
	               		 <div class="col-md-4">
	               		      <input type="text" id="yr" name="yr" class="form-control-sm form-control" 
	               		      autocomplete="off" maxlength="4"   onchange="Checkyear(this)" onkeypress="return isNumberPointKey(event);">
	               		 </div>
  					</div>
  					
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label" >Officer</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Max</label>
	               		 </div>
	               		 
	               		 <div class="col-md-2">
	               		      <input type="text" id="ofcr_max" name="ofcr_max1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly>
	               		 </div>
	               		 
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Total</label>
	               		 </div>
	               		 
	               		 <div class="col-md-2">
	               		      <input type="text"  id="ofcr_tot" name="ofcr_tot1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly/>
	               		 </div>
	               		 
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Avg</label>
	               		 </div>
	               		 
	               		 <div class="col-md-2">
	               		      <input type="text" id="ofcr_avg" name="ofcr_avg1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" readonly>
	               		 </div>
  					</div>
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">JCO/OR</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Max</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="jco_max" name="jco_max1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Total</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" maxlength="10" id="jco_tot" name="jco_tot1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off"  onkeypress="return isNumberPointKey(event)" readonly/>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Avg</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="jco_avg" maxlength="10" name="jco_avg1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" readonly>
	               		 </div>
  					</div>
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Officer Family</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Max</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="ofcr_fmly_max" name="ofcr_fmly_max1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Total</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text"  id="ofcr_fmly_tot" name="ofcr_fmly_tot1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off"  maxlength="10" onkeypress="return isNumberPointKey(event)" readonly/>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Avg</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="ofcr_fmly_avg" name="ofcr_fmly_avg1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" readonly>
	               		 </div>
  					</div>
  					
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">JCO/OR Family</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Max</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="jco_fmly_max" name="jco_fmly_max1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Total</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text"  id="jco_fmly_tot" name="jco_fmly_tot1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)" readonly/>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Avg</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="jco_fmly_avg" name="jco_fmly_avg1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" readonly>
	               		 </div>
  					</div>
  					
  					 <div class="col-md-12" style="display: none;" id="b2">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Needy ESM When Not Drawing Pension</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Adm</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="need_exms_adm1" name="need_exms_adm1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)">
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Bed Days</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text"  id="need_exms_bed_days1" name="need_exms_bed_days1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)"/>
	               		 </div>
  					</div> 
  					
  					<div class="col-md-12" style="display: none;" id="b3">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">No of Families Refused After Utilising Comd Res</label>
	               		 </div>
	               		 <div class="col-md-1">
	               		      <label for="text-input" class=" form-control-label">Adm</label>
	               		 </div>
	               		 <div class="col-md-2">
	               		      <input type="text" id="no_of_fmly_ref_adm1" name="no_of_fmly_ref_adm1" class="form-control-sm form-control" placeholder="0"
	               		      autocomplete="off" maxlength="10" onkeypress="return isNumberPointKey(event)">
	               		 </div>
  					</div>
  					<div class="col-md-12">
  					     <div class="col-md-2" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Remarks</label>
	               		 </div>
	               		 <div class="col-md-10">
	               		<textarea   rows="2" cols="250" maxlength="255" class="form-control char-counter1"  placeholder="Enter Your Remarks..."  id="remarks" name="remarks"></textarea>
	               		 </div>
  					</div>     
		 </div>
		 
			 <input type="hidden" id="id" name="id" value="${bed_occ_serv_editCMD.id}" class="form-control" autocomplete="off">
				<div class="card-footer" align="center">
			     	<a href="mnh_input_search_bed_occupancy" class="btn btn-danger btn-sm" id="btn_cancl" >Cancel</a>
		            <input type="submit" id="btn_update" class="btn btn-success btn-sm" value="Update" onclick="return validate();" /> <!-- onclick="return validate();"  -->
		 		</div>		 
    </div>
</div>
</form:form>


<script>
function btn_clc(){
	location.reload(true);
}

function getQuat(){
	var qty = $("#quater").val();

	if(qty == "q1"){
		return 90;
	}
			
	else if(qty == "q2"){
		return 91;
	}
			
	else if(qty == "q3"){
		return 92;
	}
			
	else if(qty == "q4"){
		return 92;
	}
}

function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function getCommand(y){
	var FindWhat = "COMMAND";
	$.post("getMNHHirarNameBySUS?"+key+"="+value, {FindWhat:FindWhat,a:y},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#command").val(datap[1]);  
			$("#command_sus").val(datap[5]); 
		}	
	}); 
} 

function defVal(){
	var type = $("#type").val();
	var a1 = $("#ofcr_max").val();
	var a2 = $("#ofcr_tot").val();
	var a3 = $("#ofcr_avg").val();
	var a4 = $("#jco_max").val();
	var a5 = $("#jco_tot").val();
	var a6 = $("#jco_avg").val();
	var a7 = $("#ofcr_fmly_max").val();
	var a8 = $("#ofcr_fmly_tot").val();
	var a9 = $("#ofcr_fmly_avg").val();
	var a10 = $("#jco_fmly_max").val();
	var a11 = $("#jco_fmly_tot").val();
	var a12 = $("#jco_fmly_avg").val();
	var b1 = $("#need_exms_adm1").val();
	var b2 = $("#need_exms_bed_days1").val();
	var b3 = $("#no_of_fmly_ref_adm1").val();
	
	if(a1 == ""){
		$("#ofcr_max").val('0');
	}
	if(a2 == ""){
		$("#ofcr_tot").val('0');
	}
	if(a3 == ""){
		$("#ofcr_avg").val('0');
	}
	
	if(a4 == ""){
		$("#jco_max").val('0');
	}
	if(a5 == ""){
		$("#jco_tot").val('0');
	}
	if(a6 == ""){
		$("#jco_avg").val('0');
	}
	
	if(a7 == ""){
		$("#ofcr_fmly_max").val('0');
	}
	if(a8 == ""){
		$("#ofcr_fmly_tot").val('0');
	}
	if(a9 == ""){
		$("#ofcr_fmly_avg").val('0');
	}
	
	if(a10 == ""){
		$("#jco_fmly_max").val('0');
	}
	if(a11 == ""){
		$("#jco_fmly_tot").val('0');
	}
	if(a12 == ""){
		$("#jco_fmly_avg").val('0');
	}
	
	if(b1 == ""){
		$("#need_exms_adm1").val('0');
	}
	if(b2 == ""){
		$("#need_exms_bed_days1").val('0');
	}
	if(b3 == ""){
		$("#no_of_fmly_ref_adm1").val('0');
	}
}

function validate(){
	var d = new Date();
	var year = d.getFullYear();
	
	if($("#type").val() == "-1") {
		alert("Please Select the Type");
		$("#type").focus();
		return false;
	}
	else if($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	else if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	else if($("#command").val() == "") {
		alert("Command Name Should not be Null");
		$("#command").focus();
		return false;
	}
	else if($("#quater").val() == "-1") {
		alert("Please Select the Quarter");
		$("#quater").focus();
		return false;
	}
	if(quarter_validate($("#quater").val()) == 0){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	else if($("#yr").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#yr").focus();
		return false;
	}
	else if($("#yr").val() > year) {
		alert("Future Year cannot be allowed");
		$("#yr").focus();
		return false;
	}
	else if(parseInt($("#ofcr_max").val()) > parseInt($("#ofcr_tot").val())){
		alert("Please Enter The Proper Max Value");
		$("#ofcr_max").focus();
		return false;
	}	
	else if(parseInt($("#jco_max").val()) > parseInt($("#jco_tot").val())){
		alert("Please Enter The Proper Max Value");
		$("#jco_max").focus();
		return false;
	}	
	else if(parseInt($("#ofcr_fmly_max").val()) > parseInt($("#ofcr_fmly_tot").val())){
		alert("Please Enter The Proper Max Value");
		$("#ofcr_fmly_max").focus();
		return false;
	}	
	else if(parseInt($("#jco_fmly_max").val()) > parseInt($("#jco_fmly_tot").val())){
		alert("Please Enter The Proper Max Value");
		$("#jco_fmly_max").focus();
		return false;
	}else{
		var q1 = $("#sus_no1").val();
		var q2 = $("#id").val();
		var q3 = $("#quater").val();
		var q4 = $("#type").val();
		
		if(q2 != ""){
			$("#type").attr('readonly',false);
			$("#unit_name1").attr('readonly',false);
			$("#sus_no1").attr('readonly',false);
			$("#bed_occ_serv").submit();
		}
	}		
}

function clear_dt(){

	$("#command").val('');
	$("#quater").val('-1');
	$("#ofcr_max").val('');
	$("#ofcr_tot").val('');
	$("#ofcr_avg").val('');
	$("#jco_max").val('');
	$("#jco_tot").val('');
	$("#jco_avg").val('');
	$("#ofcr_fmly_max").val('');
	$("#ofcr_fmly_tot").val('');
	$("#ofcr_fmly_avg").val('');
	$("#jco_fmly_max").val('');
	$("#jco_fmly_tot").val('');
	$("#jco_fmly_avg").val('');
	$("#need_exms_adm1").val('');
	$("#need_exms_bed_days1").val('');
	$("#no_of_fmly_ref_adm1").val('');
	$("#remarks").val('');
}



$("#ofcr_tot").keyup(function(){
	$("#ofcr_avg").val('');
	var ofcr = this.value;
	var quat = getQuat();
	var avg = ofcr/quat;
	$("#ofcr_avg").val(avg.toFixed(2));
});

$("#jco_tot").keyup(function(){
	$("#jco_avg").val('');
    var ofcr = this.value;
	var quat = getQuat();
	var avg = ofcr/quat;
	$("#jco_avg").val(avg.toFixed(2));
});

$("#ofcr_fmly_tot").keyup(function(){
	$("#ofcr_fmly_avg").val('');
	var ofcr = this.value;
	var quat = getQuat();
	var avg = ofcr/quat;
	$("#ofcr_fmly_avg").val(avg.toFixed(2));
});

$("#jco_fmly_tot").keyup(function(){
	$("#jco_fmly_avg").val('');
	var ofcr = this.value;
	var quat = getQuat();
	var avg = ofcr/quat;
	$("#jco_fmly_avg").val(avg.toFixed(2));
});
</script>
<script>


var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";


jQuery(function() {
	// Source SUS No

	jQuery("#sus_no1").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no1");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
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
				        	var dataCountry1 = susval.join("|");
			            var myResponse = []; 
			            var autoTextVal=susNoAuto.val();
						jQuery.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
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
			        	  document.getElementById("sus_no1").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_sus_no = ui.item.value;
			    	 	
  	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
  	 			 		if(j == ""){
  	 			      	 	alert("Please Enter Approved Unit SUS No.");
  			        	  	document.getElementById("unit_name1").value="";
  			        	  	susNoAuto.val("");
  			        	  	susNoAuto.focus();
  	 			      	}else{
	    	 	   	        	var length = j.length-1;
	    	    				var enc = j[length].substring(0,16);
	    	    				$("#unit_name1").val(dec(enc,j[0]));	
	    	 	   	        		
  	 	   	        	}
  	 				}).fail(function(xhr, textStatus, errorThrown) {
  	 				});
			      } 	     
			});	
	});
	// End
	
	// Source Unit Name

    jQuery("#unit_name1").keypress(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name1");
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
					        	var dataCountry1 = susval.join("|");
				            var myResponse = []; 
				            var autoTextVal=susNoAuto.val();
							jQuery.each(dataCountry1.toString().split("|"), function(i,e){
								var newE = e.substring(0, autoTextVal.length);
								if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
								  myResponse.push(e);
								}
							});      	          
							response( myResponse ); 
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
				        	  document.getElementById("unit_name1").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;
						
								 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
								 		 var length = j.length-1;
				 				         var enc = j[length].substring(0,16);
				 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
									}).fail(function(xhr, textStatus, errorThrown) {
							});
					} 	     
				}); 			
 			});

});
</script>
<Script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 250
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter1").charCounter();
</Script>
<script>
$(document).ready(function() {

		$("#abc").val('${a_2}');		
		$("#id").val('${a_3}');
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
	
	if('${sus1}' != "" || '${unit1}' != "" || '${a_2}' != "" || '${a_3}' != ""){	
		$("#divPrint").show();
	}

	var d = new Date();
	var year = d.getFullYear();
	$("#yr").val(year);
	
	$("#type").change(function(){
		clear_dt();
		var a = this.value;
		if(a == "1"){
			$("#a1").show();
			$("#a2").show();
			$("#b1").hide();
			$("#b2").hide();
			$("#b3").hide();
		}
	
	    if(a == "2"){
	    	$("#a1").hide();
			$("#a2").show();
			$("#b1").show();
			$("#b2").show();
			$("#b3").show();
	    } 
	    
	    if(a == "-1"){
	    	$("#a1").hide();
			$("#a2").hide();
			$("#b1").hide();
			$("#b2").hide();
			$("#b3").hide();
	    } 
	});
	
	$("#quater").change(function(){
		$("#ofcr_max").attr('readonly',false);
		$("#ofcr_tot").attr('readonly',false);
		
		$("#jco_max").attr('readonly',false);
		$("#jco_tot").attr('readonly',false);
		
		$("#ofcr_fmly_max").attr('readonly',false);
		$("#ofcr_fmly_tot").attr('readonly',false);
		
		$("#jco_fmly_max").attr('readonly',false);
		$("#jco_fmly_tot").attr('readonly',false);
	});
	
	$('#unit_name1').change(function(){
		var y = this.value;
		
		$.post("getMNHSUSNoByUnitName?"+key+"="+value,{y:y},function(j) {
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);
		});
		
    }); 
	
	$('#sus_no1').change(function(){
		var y = this.value;
		getCommand(y);
    });  
	
	
		$("#id").val('${bed_occ_serv_editCMD.id}');
		
		$("#type").attr('readonly',true);
		var a = '${type2}';
		if(a == "1"){
			$("select#type").val('${type2}');
			$("#a1").show();
			$("#a2").show();
			$("#b1").hide();
			$("#b2").hide();
			$("#b3").hide();
		}
	
	    if(a == "2"){
	    	$("select#type").val('${type2}');
	    	$("#a1").hide();
			$("#a2").show();
			$("#b1").show();
			$("#b2").show();
			$("#b3").show();
	    } 
	    
	    if(a == "-1"){
	    	$("select#type").val('${type2}');
	    	$("#a1").hide();
			$("#a2").hide();
			$("#b1").hide();
			$("#b2").hide();
			$("#b3").hide();
	    }
	     
	    $("#sus_no1").val('${bed_occ_serv_editCMD.sus_no}');
		
	    getCommand('${bed_occ_serv_editCMD.sus_no}');

	    $("#sus_no1").attr('readonly',true);
	    $("#unit_name1").attr('readonly',true);
		$.post("getMNHUnitNameBySUSNo?"+key+"="+value,{y:'${bed_occ_serv_editCMD.sus_no}'},function(j) {
			var enc = j[j.length - 1].substring(0, 16);
			var a = dec(enc, j[0]);
			$("#unit_name1").val(a);
		});
		
		$("#quater").val('${bed_occ_serv_editCMD.qtr}');
		if('${bed_occ_serv_editCMD.qtr}' != ""){
			$("#ofcr_max").attr('readonly',false);
			$("#ofcr_tot").attr('readonly',false);
			
			$("#jco_max").attr('readonly',false);
			$("#jco_tot").attr('readonly',false);
			
			$("#ofcr_fmly_max").attr('readonly',false);
			$("#ofcr_fmly_tot").attr('readonly',false);
			
			$("#jco_fmly_max").attr('readonly',false);
			$("#jco_fmly_tot").attr('readonly',false);
		}
		$("#yr").val('${bed_occ_serv_editCMD.year}');
		
		$("#ofcr_max").val('${bed_occ_serv_editCMD.ofcr_max}');
		$("#ofcr_tot").val('${bed_occ_serv_editCMD.ofcr_tot}');
		$("#ofcr_avg").val('${bed_occ_serv_editCMD.ofcr_avg}');
		$("#jco_max").val('${bed_occ_serv_editCMD.jco_max}');
		$("#jco_tot").val('${bed_occ_serv_editCMD.jco_tot}');
		$("#jco_avg").val('${bed_occ_serv_editCMD.jco_avg}');
		$("#ofcr_fmly_max").val('${bed_occ_serv_editCMD.ofcr_fmly_max}');
		$("#ofcr_fmly_tot").val('${bed_occ_serv_editCMD.ofcr_fmly_tot}');
		$("#ofcr_fmly_avg").val('${bed_occ_serv_editCMD.ofcr_fmly_avg}');
		$("#jco_fmly_max").val('${bed_occ_serv_editCMD.jco_fmly_max}');
		$("#jco_fmly_tot").val('${bed_occ_serv_editCMD.jco_fmly_tot}');
		$("#jco_fmly_avg").val('${bed_occ_serv_editCMD.jco_fmly_avg}');

		
		$("#need_exms_adm1").val('${need_exms_adm}');
		$("#need_exms_bed_days1").val('${need_exms_bed_days}');
		$("#no_of_fmly_ref_adm1").val('${no_of_fmly_ref_adm}');
		$("#remarks").val('${bed_occ_serv_editCMD.remarks}');
	

});
</script>