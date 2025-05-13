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




<form:form action="morning_bed_stateDAAction" id="morning_bed_stateDA" method="POST" class="form-horizontal" commandName="morning_bed_stateDACMD">
   
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		            <h5>DAILY MORNING BED STATE</h5>
		            <h6><span style="font-size: 12px;color:red">(To be entered by Medical Unit)</span></h6>
		      </div> 
		      
		    <div class="card-body card-block">
				<div class="row">
				
					
				<div class="col-md-12">
						<div class="col-md-8">
						<div class="row form-group">
						<div class="col-md-3">
               <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Hospital Name</label>
	               		 </div>
	               		<div class="col-md-9">

	               		      		
	               			  <input type="text" id="unit_name1" name ="unit_name1"  value="${unit_name}" class="form-control-sm form-control" placeholder="Search..." 
	               			  autocomplete="off" title="Type Unit Name or Part of Unit Name to Search">
						</div>
						</div>
						</div>
						 
						<div class="col-md-4">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="sus_no1" name="sus_no1"  value="${sus_no}" class="form-control-sm form-control" placeholder="Search..." 
	             			  autocomplete="off" title="Type SUS No or Part of SUS No to Search" maxlength="8"/>
	               		 </div>  
  					</div> 
  					</div>
						</div>
						
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Auth Beds</label>
	               		 </div>
	               		<div class="col-md-8">
	               			  <input type="text" id="auth_beds" name="auth_beds" class="form-control-sm form-control"  autocomplete="off" maxlength="10" readonly > 
						 </div>	
						 </div>
						 </div>
						 <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date</label>
	               		 </div>
	               		<div class="col-md-8">
	               			  <input type="date" id="dat1" name="dat1" class="form-control-sm form-control"  autocomplete="off" min="1899-01-01" max="${date}"> 
						 </div>
						</div>
						</div>
			</div>
			
			
</div> 
</div>		
  					<!-- Add Category Start Here -->
  				
  					<div class="col-md-2" style="padding-top: 5px;">
	                 <div class="card-header-inner" id="a1"> <strong>Serving</strong></div>
				   	</div>			
				   <div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
	                 		   <label for="text-input" class=" form-control-label">Officer</label>
	               		 </div>
		               <div class="col-md-8">  
						       <input type="text" id="off_army" name="off_army" class="form-control-sm form-control" autocomplete="off" 
						       onkeypress="return isNumberPointKey(event);" maxlength="10" value="0"  onkeyup="totalcal();">
						 </div>
						</div>
						</div>

  						 <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Officer Family</label>
                		 </div>
                	     <div class="col-md-8">
					           <input type="text" id="off_fam_army" name="off_fam_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
					      </div>
						</div>
						</div>
  					
  						 <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">JCO/OR</label>
                		 </div>
                	      <div class="col-md-8">
					           <input type="text" id="jco_or_army" name="jco_or_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
					      </div>
						</div>
						
					      <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">JCO/OR Family</label>
                		 </div>
					     <div class="col-md-8">
					           <input type="text" id="jco_or_fam_army" name="jco_or_fam_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
  				    </div>
  				    </div>
  				    </div>
  				    <div class="col-md-2" style="padding-top: 5px;">
	                 <div class="card-header-inner" id="a1"><strong>Ex-Servicemen</strong></div>
				    </div>
				    	<div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
	                 		   <label for="text-input" class=" form-control-label">Officer</label>
	               		 </div>
		               	<div class="col-md-8">  
						       <input type="text" id="ex_off_army" name="ex_off_army" class="form-control-sm form-control" autocomplete="off" 
						       onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
						 </div>
						 </div>
						 </div>

  						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Officer Family</label>
                		 </div>
                	     <div class="col-md-8">
					           <input type="text" id="ex_off_fam_army" name="ex_off_fam_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10"value="0" onkeyup="totalcal();">
					     </div>
					      </div>
					       </div>
  					
  						  <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">JCO/OR</label>
                		 </div>
                	   <div class="col-md-8">
					           <input type="text" id="ex_jco_or_army" name="ex_jco_or_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);"maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
					      
					      </div>
					       </div>
					       
					     <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">JCO/OR Family</label>
                		 </div>
					    <div class="col-md-8">
					           <input type="text" id="ex_jco_or_fam_army" name="ex_jco_or_fam_army" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);"maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
  				    </div>
  				  
					      </div>
					       </div>
  					
  					<!-- Add Category Other Start Here -->
  				  <div  class="card-header-inner" id="a1"> <strong>Others</strong></div>
	                 		    
				   <div class="col-md-12">
						<div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
	                 		   <label for="text-input" class=" form-control-label">Para Mil Pers</label>
	               		 </div>
		               	 <div class="col-md-8">  
						       <input type="text" id="para_mil_pers" name="para_mil_pers" class="form-control-sm form-control" autocomplete="off" 
						       onkeypress="return isNumberPointKey(event);"maxlength="10"value="0" onkeyup="totalcal();">
						 </div>
                      </div>
                      </div>
  						 <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Para Mil Family</label>
                		 </div>
                	     <div class="col-md-8">
					           <input type="text" id="para_family" name="para_family" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);"maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
  					    </div>
  					    </div>
  						  <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Other</label>
                		 </div>
                	     <div class="col-md-8">
					           <input type="text" id="other_ne" name="other_ne" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
					     </div>
					     </div>
					   <div class="col-md-3">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Other Family</label>
                		 </div>
					   <div class="col-md-8">
					           <input type="text" id="other_family" name="other_family" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10" value="0" onkeyup="totalcal();">
					     </div>
  				    </div>
  				    </div>
  				     </div>
  					<!-- Add Category Other End Here -->
  					
  					<!-- Cadet and Agniveer -->
  					
  					
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		   <label for="text-input" class=" form-control-label">Cadet</label>
	               		 </div>
		               	 <div class="col-md-8">  
						       <input type="text" id="cadet" name="cadet" class="form-control-sm form-control" autocomplete="off" 
						       onkeypress="return isNumberPointKey(event);"maxlength="10" onkeyup="totalcal();" value="0"> 
						 </div>
                      </div>
                      </div>
  						  <div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
                  			   <label for="text-input" class=" form-control-label">Rect(Agniveer)</label>
                		 </div>
                	     <div class="col-md-8">
					           <input type="text" id="rect_agniveer" name="rect_agniveer" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" maxlength="10"  onkeyup="totalcal();" value="0">
					     </div>
					     </div>
					     </div>
					  
  				     </div>
  					
  					
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Bed Laid Out</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="beds_laid_out" name="beds_laid_out" class="form-control-sm form-control" 
	             			  autocomplete="off" placeholder="Enter Bed Laid out..."  maxlength="10" onkeypress="return isNumberPointKey(event)" onkeyup="totalcal1();"
	             			  readonly> 
	               		 </div>  	 
						  </div>  	
						   </div>  	
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Total No of Patients</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  
	             			  <input type="text" id="total_no_of_patients" name="total_no_of_patients" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);"maxlength="10" value="0" readonly>
	               		 </div>  
  					</div> 
  					</div> 
  					</div> 
  					
  					<div class="col-md-12">
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	               			  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>BED Occ(%) as per Laid out BEDS</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="text" id="bed_occ_per_as_per_laid_out_bed" name="bed_occ_per_as_per_laid_out_bed" 
	             			  class="form-control-sm form-control" maxlength="16" autocomplete="off" readonly> 
	               		 </div>  
	               		 </div>  						
	               		</div>  
						<div class="col-md-6">
						<div class="row form-group">
						<div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>BED Occ(%) as per Auth BEDS</label>
	               		 </div>
	               		 <div class="col-md-8">
	               			  <input type="text" id="bed_occ_per_as_per_auth_bed" name="bed_occ_per_as_per_auth_bed" 
	               			  class="form-control-sm form-control"  maxlength="16" autocomplete="off"  onkeyup="totalcal2();"readonly> 
						 </div>	 
  					</div> 
		      </div>
		   </div>  
		    	<div class="card-footer" align="center">
			
			<input type="hidden" id="btn_hid" name="btn_hid" class="form-control-sm form-control" value ="0">
			<input type="hidden" id="btn_hid_exit" name="btn_hid_exit" class="form-control-sm form-control" value ="0"> 
                
				 <a href="mnh_bed_statusDA" class="btn btn-info btn-sm" id="btn_clc" >Clear</a>					     

		           <input type="submit" id="btn_save" class="btn btn-primary btn-sm" value="Save & Exit" onclick="return isvalidData();"/>       
		             <input type="submit" class="btn btn-success btn-sm" value="NIL Report"  onclick="return isvalidData_nil();"/> 		         	         
           </div>
          </div>
      </div>     

</form:form>

 

<!-- for Functions -->
<script>
function btn_clc(){
	location.reload(true);
}


function isvalidData_nil(){
	
	
	

	
	if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	else if ($("#dat1").val() == "") {
		alert("Please Enter Date");
		$("#dat1").focus();
		return false;
	}
 
	else{
		var a1 = $("#off_army").val();
		var a2 = $("#off_fam_army").val();
		var a3 = $("#jco_or_army").val();
		var a4 = $("#jco_or_fam_army").val();
		var a5 = $("#ex_off_army").val();
		var a6 = $("#ex_off_fam_army").val();
		var a7 = $("#ex_jco_or_army").val();
		var a8 = $("#ex_jco_or_fam_army").val();
		var a25 = $("#para_mil_pers").val();
		var a26 = $("#para_family").val();
		var a27 = $("#other_ne").val();
		var a28 = $("#other_family").val();
		var a29 = $("#cadet").val();
		var a30 = $("#rect_agniveer").val();
		var a11 = $("#total_no_of_patients").val();
		var a12= $("#bed_occ_per_as_per_laid_out_bed").val();
		
		var dt_adm = $("#dat1").val();
		
		var bed_occ_per_as_per_auth_bed1 = $("#bed_occ_per_as_per_auth_bed").val();
		
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

function totalcal(){
	var a1 = $("#off_army").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#off_fam_army").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#jco_or_army").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#jco_or_fam_army").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#ex_off_army").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	var a6 = $("#ex_off_fam_army").val();
	if(a6 == "" || a6 == null){
		a6 = 0;
	}
	var a7 = $("#ex_jco_or_army").val();
	if(a7 == "" || a7 == null){
		a7 = 0;
	}
	var a8 = $("#ex_jco_or_fam_army").val();
	if(a8 == "" || a8 == null){
		a8 = 0;
	}
	var a25 = $("#para_mil_pers").val();
	if(a25 == "" || a25 == null){
		a25 = 0;
	}
	var a26 = $("#para_family").val();
	if(a26 == "" || a26 == null){
		a26 = 0;
	}
	var a27 = $("#other_ne").val();
	if(a27 == "" || a27 == null){
		a27 = 0;
	}
	var a28 = $("#other_family").val();
	if(a28 == "" || a28 == null){
		a28 = 0;
	}
	var a29 = $("#cadet").val();
	if(a29 == "" || a29 == null){
		a29 = 0;
	}
	
	var a30 = $("#rect_agniveer").val();
	if(a28 == "" || a30 == null){
		a30 = 0;
	}
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5)+
			parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a25)+parseInt(a26)+parseInt(a27)+
			parseInt(a28)+parseInt(a29)+parseInt(a30));
	$("#total_no_of_patients").val(t1);
	
	//Calculations
	var a11 = $("#total_no_of_patients").val();
	if(a11 == "" || a11 == null){
		a11 = 0;
	}
	var a12 = $("#auth_beds").val();
	if(a12 == "" || a12 == null){
		a12 = 0;
	}
	var a22 = $("#beds_laid_out").val();
	if(a22 == "" || a22 == null){
		a22 = 0;
	}
	var t14 = ((parseInt(a11)/parseInt(a22)) * 100);
	$("#bed_occ_per_as_per_laid_out_bed").val(t14.toFixed(2));
	
	var t11 = ((parseInt(a11)/parseInt(a12)) * 100);
	$("#bed_occ_per_as_per_auth_bed").val(t11.toFixed(2));
}


function totalcal1(){
	var a1 = $("#total_no_of_patients").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#beds_laid_out").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	
	var t1 = ((parseInt(a1)/parseInt(a2)) * 100);
	$("#bed_occ_per_as_per_laid_out_bed").val(t1.toFixed(2));
}

function totalcal2(){
	var a1 = $("#total_no_of_patients").val();
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#auth_beds").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	
	var t1 = ((parseInt(a1)/parseInt(a2)) * 100);
	$("#bed_occ_per_as_per_auth_bed").val(t1.toFixed(2));
}

function isvalidData(){
	
	
var sr = $("#btn_hid").val("1");
	
	
	//var rel = $("#relation").val();
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
	if ($("#unit_name1").val() == "") {
		alert("Please Enter the Unit Name");
		$("#unit_name1").focus();
		return false;
	}
	else if ($("#sus_no1").val() == "") {
		alert("Please Enter the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	else if ($("#auth_beds").val() == "") {
		alert("Please Enter Auth Beds");
		$("#auth_beds").focus();
		return false;
	}
	else if ($("#dat1").val() == "") {
		alert("Please Select the Date");
		$("#dat1").focus();
		return false;
	}
	else if($("#dat1").val() > c_d){
		$("#dat1").focus();
		alert("Can't select the Future Date");
		return false;
	}
	else if ($("#beds_laid_out").val() == "") {
		alert("Please Enter Bed Laid out");
		$("#beds_laid_out").focus();
		return false;
	}
	else if ($("#total_no_of_patients").val() == "") {
		alert("Please Enter Total No of Patients");
		$("#total_no_of_patients").focus();
		return false;
	}
	else if ($("#bed_occ_per_as_per_laid_out_bed").val() == "") {
		alert("Please Enter Bed occ. as per Laid out Beds");
		$("#bed_occ_per_as_per_laid_out_bed").focus();
		return false;
	}
	else if ($("#bed_occ_per_as_per_auth_bed").val() == "") {
		alert("Please Enter Bed occ. as per Auth Beds");
		$("#bed_occ_per_as_per_auth_bed").focus();
		return false;
	}
	else{
		var unit = $("#unit_name1").val();
		var dt_adm = $("#dat1").val();
		
	
		
		var bed_occ_per_as_per_auth_bed1 = $("#bed_occ_per_as_per_auth_bed").val();
	}

	
}

function editData(id){
	$("#id1").val(id);
	$("#editForm").submit();
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
			    	 	var unit_name = "";
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
	    	    				unit_name=dec(enc,j[0]);
	    	 	   	        		
  	 	   	        	}
  	 				}
  	 			 ).fail(function(xhr, textStatus, errorThrown) {
	 				});
  	 			 	
  	 				$.post("getMedAuthBedsTotal?"+key+"="+value, {enc:"1",y:unit_sus_no}).done(function(j) {
  	 					if(j.length > 0)
  	 					{
							 var length = j.length-1;
					         var enc = j[length].substring(0,16);
					         jQuery("#auth_beds").val(dec(enc,j[0]));
  	 					}
  	 				   
					}).fail(function(xhr, textStatus, errorThrown) {
						
					});
  	 			 	
  	 				$.post("getMedBedslaid_outTotal?"+key+"="+value, {enc:"1",y:unit_sus_no}).done(function(j) {				
  	 					if(j.length > 0)
  	 					{ 
	  	 					var length = j.length-1;
					        var enc = j[length].substring(0,16);
					        jQuery("#beds_laid_out").val(dec(enc,j[0]));
					        totalcal1();
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
						 var sus_no="";
							 $.ajaxSetup({
									async : false
								}); 
							 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
							 		 var length = j.length-1;
			 				         var enc = j[length].substring(0,16);
			 				         jQuery("#sus_no1").val(dec(enc,j[0]));	
			 				        sus_no =dec(enc,j[0]);
								}).fail(function(xhr, textStatus, errorThrown) {});
							 	
							 	 $.ajaxSetup({
										async : false
									}); 
							 	$.post("getMedAuthBedsTotal?"+key+"="+value, {enc:"1",y:sus_no}).done(function(j) {				
							 		if(j.length > 0)
			  	 					{ 
							 		 var length = j.length-1;
			 				         var enc = j[length].substring(0,16);
			 				         jQuery("#auth_beds").val(dec(enc,j[0]));	
			  	 					}
			 				      
								}).fail(function(xhr, textStatus, errorThrown) {
									
								});
							 	
							 	$.post("getMedBedslaid_outTotal?"+key+"="+value, {enc:"1",y:sus_no}).done(function(j) {				
							 		if(j.length > 0)
			  	 					{ 
							 		    var length = j.length-1;
							            var enc = j[length].substring(0,16);
								        jQuery("#beds_laid_out").val(dec(enc,j[0]));
								        totalcal1();
			  	 					}
								}).fail(function(xhr, textStatus, errorThrown) {
									
								});
							 	
							 	
							 	
					} 	     
				}); 			
 			});

});

</script>

<!-- for On Load Methods -->
<script>
$(document).ready(function() {
	
	
	if('${sus_no}' != ""){
		$("#beds_laid_out").attr('readonly',false);
    	$("#total_no_of_patients").attr('readonly',true);
    	
    	$.post("getMedAuthBedsTotal?"+key+"="+value, {enc:"1",y:'${sus_no}'}).done(function(j) {				
	 		 var length = j.length-1;
	         var enc = j[length].substring(0,16);
	         jQuery("#auth_beds").val(dec(enc,j[0]));	
		}).fail(function(xhr, textStatus, errorThrown) {
			
		});
    	
	}
	

	
	
	$("#unit_name1").val('${unit_name}');
	$.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:'${unit_name}'}, function(j) {			
		 var length = j.length-1;	
         var enc = j[length].substring(0,16);
        jQuery("#sus_no1").val(dec(enc,j[0]));
        $.post("getMedAuthBedsTotal?"+key+"="+value, {enc:"1",y:dec(enc,j[0])}).done(function(j) {				
	 		 var length = j.length-1;
	         var enc = j[length].substring(0,16);
	         jQuery("#auth_beds").val(dec(enc,j[0]));	
	         
	         
		}).fail(function(xhr, textStatus, errorThrown) {
			
		});
   	 $.post("getMedBedslaid_outTotal?"+key+"="+value, {enc:"1",y:dec(enc,j[0])}).done(function(j) {				
 		
			var length = j.length-1;
     var enc = j[length].substring(0,16);
     jQuery("#beds_laid_out").val(dec(enc,j[0]));
  
 //    totalcal1();
    }).fail(function(xhr, textStatus, errorThrown) {
	
   });
   	
	}).fail(function(xhr, textStatus, errorThrown) {
		
	});
	
	
	
	
	
	
	
	var para = '${r_1[0][1]}';
	if(para == "UNIT"){
		$("#sus_no1").val('${a_2}');
		$("#unit_name1").val('${a_3}');
		$("#auth_beds").val('${a_4[0]}');
		$("#beds_laid_out").attr('readonly',false);
    	$("#total_no_of_patients").attr('readonly',true);
	}
	
	if('${size}' != 0){
		$("#btn_update").show();
		$("#btn_save").hide();
		
		var d = new Date('${list.dt}');
		var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
		$("#id_hid").val('${list.id}');
		$("#sus_no1").val('${list.sus_no}');
		$("#sus_no1").attr('disabled',true);
		$.post("getMNHUnitNameBySUSNo?"+key+"="+value,{y:'${list.sus_no}'},function(j) {
			var enc = j[j.length - 1].substring(0, 16);
			var a = dec(enc, j[0]);
			$("#unit_name1").val(a);
		});
		$("#unit_name1").attr('disabled',true);
		$("#auth_beds").val('${list.auth_beds}');
		$("#beds_laid_out").val('${list.beds_laid_out}');
		$("#dat").val(c_d);
		$("#total_no_of_patients").val('${list.total_no_of_patients}');
		if('${list.sus_no}' != "" && '${list.auth_beds}'){
			$("#beds_laid_out").attr('readonly',true);
	    	$("#total_no_of_patients").attr('readonly',true);
		}
		$("#bed_occ_per_as_per_auth_bed").val('${list.bed_occ_per_as_per_auth_bed}');
		$("#bed_occ_per_as_per_laid_out_bed").val('${list.bed_occ_per_as_per_laid_out_bed}');
		
		//Added by Sandeep
		$("#off_army").val('${list.off_army}');
		$("#off_fam_army").val('${list.off_fam_army}');
		$("#jco_or_army").val('${list.jco_or_army}');
		$("#jco_or_fam_army").val('${list.jco_or_fam_army}');
		$("#ex_off_army").val('${list.ex_off_army}');
		$("#ex_off_fam_army").val('${list.ex_off_fam_army}');
		$("#ex_jco_or_army").val('${list.ex_jco_or_army}');
		$("#ex_jco_or_fam_army").val('${list.ex_jco_or_fam_army}');
		
		$("#para_mil_pers").val('${list.para_mil_pers}');
		$("#para_family").val('${list.para_family}');
		$("#other_ne").val('${list.other_ne}');
		$("#other_family").val('${list.other_family}');
	}
	
    $("#sus_no1").change(function(){
    	$("#beds_laid_out").attr('readonly',true);
    	$("#total_no_of_patients").attr('readonly',true);
    });
    
    $("#unit_name1").change(function(){
    	$("#beds_laid_out").attr('readonly',true);
    	$("#total_no_of_patients").attr('readonly',true);
    });
    
    	
});

</script>
