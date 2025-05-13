<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<form:form action="sho_strength_inputAction" id="sho_strength_input" method="post" class="form-horizontal" commandName="sho_strength_inputCMD">

      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SHO/FHO STRENGTH</h5>
		             <h6><span style="font-size: 12px; color: red">(To be entered by SHO/FHO)</span></h6>
		      </div> 
		      <div class="card-body card-block">
				<div class="row">
				
					
				<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
							 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label></div>
						<div class="col-md-9">
							<input type="text" id="unit_name1" name ="unit_name1" class="form-control-sm form-control" maxlength="100"
	               			  placeholder="Search..." autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						</div>
						</div>
						</div>
						
						<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
						</div>
						<div class="col-md-8">
							<input type="hidden" id="id_hid" name="id_hid">
	             			  <input type="text" id="sus_no1" name="sus_no1" class="form-control-sm form-control" maxlength="8"
	             			  placeholder="Search..." autocomplete="off" title="Type SUS No or Part of SUS No to Search"/>
	               		 </div>
						</div>
						</div>
			</div>
					

	
	             		 
<div class="card-header-inner" id="aa"> <strong>LODGER FORMATION</strong></div>

		<div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							 <label for="text-input" class=" form-control-label">OFFICER</label>
						</div>
						<div class="col-md-8">
							<input type="text" id=off_lodg_for name ="off_lodg_for" class="form-control-sm form-control" placeholder="0"  maxlength="10"
	               			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label">CADET</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="cadet_lodg_for" name="cadet_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">MNS</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="mns_lodg_for" name ="mns_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	               			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label">JCOs</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="jco_lodg_for" name="jco_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
			</div>				 
		      	    
		   <div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							 <label class=" form-control-label">OR</label>
						</div>
						<div class="col-md-8">
							 <input type="text" id="or_lodg_for" name="or_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							  <label class=" form-control-label">RECT</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="rect_lodg_for" name="rect_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label">CIV(E)</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="cive_lodg_for" name="cive_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							  <label class=" form-control-label">CIV(NE)</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="civne_lodg_for" name="civne_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
	               		 </div>
						</div>
						</div>
			</div>   	    
		      	    
		      	    
		      	    
		      	   <div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
						  <label class=" form-control-label">OTHERS</label>
						</div>
						<div class="col-md-8">
							 <input type="text" id="other_lodg_for" name="other_lodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							   <label class=" form-control-label">TOTAL</label>
						</div>
						<div class="col-md-8">
							  <input type="text" id="total_lodger" name="total_lodger" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  readonly="readonly" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
			</div>    
		      	    
  		         
  			 
  					<div class="card-header-inner" id="aa"> <strong>NON LODGER FORMATION</strong></div>
  			<div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							 <label for="text-input" class=" form-control-label">OFFICER </label>
						</div>
						<div class="col-md-8">
							  <input type="text" id="off_nonlodg_for" name ="off_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	               			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label">CADET</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="cadet_nonlodg_for" name="cadet_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							  <label for="text-input" class=" form-control-label">MNS</label>
						</div>
						<div class="col-md-8">
							 <input type="text" id="mns_nonlodg_for" name ="mns_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	               			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							   <label class=" form-control-label">JCOs</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="jco_nonlodg_for" name="jco_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
			</div>   
  			
  			
  			
  			<div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							  <label class=" form-control-label">OR</label>
						</div>
						<div class="col-md-8">
							   <input type="text" id="or_nonlodg_for" name="or_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label">RECT</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="rect_nonlodg_for" name="rect_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							   <label class=" form-control-label">CIV(E)</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="cive_nonlodg_for" name="cive_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							  <label class=" form-control-label">CIV(NE)</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="civne_nonlodg_for" name="civne_nonlodg_for" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
	               		 </div>
						</div>
						</div>
			</div>  
  			
  			
  			<div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
							 <label class=" form-control-label">OTHERS</label>
						</div>
						<div class="col-md-8">
							   <input type="text" id="other_nonlodg_for" name="other_nonlodg_for" class="form-control-sm form-control" placeholder="0" 
	             			  onkeypress="return isNumberPointKey(event)" onkeyup="totalcal2();" autocomplete="off">
						</div>
						</div>
						</div>
						
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
						<label class=" form-control-label">TOTAL</label>
						</div>
						<div class="col-md-8">
							 <input type="text" id="total_nonlodger" name="total_nonlodger" class="form-control-sm form-control" placeholder="0" maxlength="10"
	             			  readonly="readonly" autocomplete="off">   
	               		 </div>
						</div>
						</div>			
			</div>  
  					
  					</div> 
  					</div> 
  						
		     
             
             <div class="form-control card-footer" align="center">
			    <a	href="mnh_input_SHOstrengthDetails" type="reset" value="Clear"  class="btn btn-primary btn-sm"  onclick="btn_clc();" > Clear </a> 
		       <input type="submit" class="btn btn-success btn-sm" value="Save" id="Saveid" onclick="validate();" >
		       <input type="submit" id="updateid" class="btn btn-success btn-sm" value="update"  onclick="validate();" style="display: none"> 
           </div>
			
              
          </div>
      </div>

</form:form>



<script>
function btn_clc(){
	location.reload(true);
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

function totalcal1(){
	var a1 = $("#off_lodg_for").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#cadet_lodg_for").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#mns_lodg_for").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#jco_lodg_for").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#or_lodg_for").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	var a6 = $("#rect_lodg_for").val();
	if(a6 == "" || a6 == null){
		a6 = 0;
	}
	var a7 = $("#cive_lodg_for").val();
	if(a7 == "" || a7 == null){
		a7 = 0;
	}
	var a8 = $("#civne_lodg_for").val();
	if(a8 == "" || a8 == null){
		a8 = 0;
	}
	var a9 = $("#other_lodg_for").val();
	if(a9 == "" || a9 == null){
		a9 = 0;
	}
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5)+parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a9));
	$("#total_lodger").val(t1);
}

function totalcal2(){
	var a1 = $("#off_nonlodg_for").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#cadet_nonlodg_for").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#mns_nonlodg_for").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#jco_nonlodg_for").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#or_nonlodg_for").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	var a6 = $("#rect_nonlodg_for").val();
	if(a6 == "" || a6 == null){
		a6 = 0;
	}
	var a7 = $("#cive_nonlodg_for").val();
	if(a7 == "" || a7 == null){
		a7 = 0;
	}
	var a8 = $("#civne_nonlodg_for").val();
	if(a8 == "" || a8 == null){
		a8 = 0;
	}
	var a9 = $("#other_nonlodg_for").val();
	if(a9 == "" || a9 == null){
		a9 = 0;
	}
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5)+parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a9));
	$("#total_nonlodger").val(t1);
}

function defaultval(){
	if($("#off_lodg_for").val() == "") {
		$("#off_lodg_for").val('0');
	}
	if($("#cadet_lodg_for").val() == "") {
		$("#cadet_lodg_for").val('0');
	}
	if($("#mns_lodg_for").val() == "") {
		$("#mns_lodg_for").val('0');
	}
	if($("#jco_lodg_for").val() == "") {
		$("#jco_lodg_for").val('0');
	}
	if($("#or_lodg_for").val() == "") {
		$("#or_lodg_for").val('0');
	}
	if($("#rect_lodg_for").val() == "") {
		$("#rect_lodg_for").val('0');
	}
	if($("#cive_lodg_for").val() == "") {
		$("#cive_lodg_for").val('0');
	}
	if($("#civne_lodg_for").val() == "") {
		$("#civne_lodg_for").val('0');
	}
	if($("#other_lodg_for").val() == "") {
		$("#other_lodg_for").val('0');
	}
	if($("#total_lodger").val() == "") {
		$("#total_lodger").val('0');
	}
	
	if($("#off_nonlodg_for").val() == "") {
		$("#off_nonlodg_for").val('0');
	}
	if($("#cadet_nonlodg_for").val() == "") {
		$("#cadet_nonlodg_for").val('0');
	}
	if($("#mns_nonlodg_for").val() == "") {
		$("#mns_nonlodg_for").val('0');
	}
	if($("#jco_nonlodg_for").val() == "") {
		$("#jco_nonlodg_for").val('0');
	}
	if($("#or_nonlodg_for").val() == "") {
		$("#or_nonlodg_for").val('0');
	}
	if($("#rect_nonlodg_for").val() == "") {
		$("#rect_nonlodg_for").val('0');
	}
	if($("#cive_nonlodg_for").val() == "") {
		$("#cive_nonlodg_for").val('0');
	}
	if($("#civne_nonlodg_for").val() == "") {
		$("#civne_nonlodg_for").val('0');
	}
	if($("#other_nonlodg_for").val() == "") {
		$("#other_nonlodg_for").val('0');
	}
	if($("#total_nonlodger").val() == "") {
		$("#total_nonlodger").val('0');
	}	
		
}

function validate(){
	if($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	else if($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}else{
		var q1 = $("#sus_no1").val();
		var q2 = $("#id_hid").val();
		
		if(q2 != ""){
			$("#sus_no1").attr('disabled',false);
			$("#unit_name1").attr('disabled',false);
			defaultval();
			$("#sho_strength_input").submit();
		}
		else{
			defaultval();
			$("#sho_strength_input").submit();
		}
	}			
}	



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

<script>
$(document).ready(function() {
	
	if('${size}' != 0){
		$("#Saveid").hide();
		$("#updateid").show();
		
		$("#id_hid").val('${list.id}');
		$("#sus_no1").attr('disabled',true);
		$("#sus_no1").val('${list.sus_no}');
		$("#unit_name1").attr('disabled',true);
		$.post("getMNHUnitNameBySUSNo?"+key+"="+value, {y:'${list.sus_no}'},function(j) {
			var enc = j[j.length - 1].substring(0, 16);
			var a = dec(enc, j[0]);
			$("#unit_name1").val(a);
		});
		if('${list.off_lodg_for}' != 0){
			$("#off_lodg_for").val('${list.off_lodg_for}');
		}
		if('${list.cadet_lodg_for}' != 0){
			$("#cadet_lodg_for").val('${list.cadet_lodg_for}');
		}
		if('${list.mns_lodg_for}' != 0){
			$("#mns_lodg_for").val('${list.mns_lodg_for}');
		}
		if('${list.jco_lodg_for}' != 0){
			$("#jco_lodg_for").val('${list.jco_lodg_for}');
		}
		if('${list.or_lodg_for}' != 0){
			$("#or_lodg_for").val('${list.or_lodg_for}');
		}
		if('${list.rect_lodg_for}' != 0){
			$("#rect_lodg_for").val('${list.rect_lodg_for}');
		}
		if('${list.cive_lodg_for}' != 0){
			$("#cive_lodg_for").val('${list.cive_lodg_for}');
		}
		if('${list.civne_lodg_for}' != 0){
			$("#civne_lodg_for").val('${list.civne_lodg_for}');
		}
		if('${list.other_lodg_for}' != 0){
			$("#other_lodg_for").val('${list.other_lodg_for}');
		}
		if('${list.total_lodger}' != 0){
			$("#total_lodger").val('${list.total_lodger}');
		}
		
		if('${list.off_nonlodg_for}' != 0){
			$("#off_nonlodg_for").val('${list.off_nonlodg_for}');
		}
		if('${list.cadet_nonlodg_for}' != 0){
			$("#cadet_nonlodg_for").val('${list.cadet_nonlodg_for}');
		}
		if('${list.mns_nonlodg_for}' != 0){
			$("#mns_nonlodg_for").val('${list.mns_nonlodg_for}');
		}
		if('${list.jco_nonlodg_for}' != 0){
			$("#jco_nonlodg_for").val('${list.jco_nonlodg_for}');
		}
		if('${list.or_nonlodg_for}' != 0){
			$("#or_nonlodg_for").val('${list.or_nonlodg_for}');
		}
		if('${list.rect_nonlodg_for}' != 0){
			$("#rect_nonlodg_for").val('${list.rect_nonlodg_for}');
		}
		if('${list.cive_nonlodg_for}' != 0){
			$("#cive_nonlodg_for").val('${list.cive_nonlodg_for}');
		}
		if('${list.civne_nonlodg_for}' != 0){
			$("#civne_nonlodg_for").val('${list.civne_nonlodg_for}');
		}
		if('${list.other_nonlodg_for}' != 0){
			$("#other_nonlodg_for").val('${list.other_nonlodg_for}');
		}
		if('${list.total_nonlodger}' != 0){
			$("#total_nonlodger").val('${list.total_nonlodger}');
		}
	}
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});
</script>