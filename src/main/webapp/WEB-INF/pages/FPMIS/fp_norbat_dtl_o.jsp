<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
var roleid="${roleid}";
var roleSusNo="${roleSusNo}";
var role = "${role}";
</script>
<body onload="setMenu();">
<form:form name="CreateUnitProfileFP" id="CreateUnitProfileFP" action="createUnitProfileActionFP?${_csrf.parameterName}=${_csrf.token}" class="fp-form" method='POST' commandName="createUnitProfileFPCMD">

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					<div class="card-header mms-card-header" style="padding:20px;">
			             <h5> <span id="lbladd"></span>CREATE BRANCHES FOR OWN HQs</h5>
					     <h6><span style="font-size: 14px;color:yellow;">(This Screen will be used to create branches within HQ and Units directly under HQ)</span></h6>
			             
			        </div> 
							<div class="card-body card-block ncard-body-bg">
								<div class="col-md-12">
	            					<div class="col-md-6">
	            						<div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
											</div>
											<div class="col-12 col-md-8">			
												<input type="text" id="unit_name" name="unit_name" maxlength="100" size="50" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Add Branch / Unit Name" class="form-control autocomplete char-counter1" autocomplete="off" title="Enter Unit Name.">
											</div>
										</div>
	            					</div>
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Unique No (Auto Genr)</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" class="form-control autocomplete" autocomplete="off" readonly title="Generate Sus No.">
											</div>
										</div>
	            					</div>
	            				</div>
	            				
	            				<div class="col-md-12">
	            				<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> HQ Type</label>
											</div>
											<div class="col-12 col-md-8">
												<select name="hq_type" id="hq_type"  class="form-control-sm form-control" title="Select HQ Type.">
												<option value="">-- Select HQ Level --</option>
												<c:if test="${roleid =='358'}">
				                               				<option value="-1">Top Level</option>
				                               				<option value="0">Dte Br</option>
				                               				<option value="0">Dte </option>
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br </option>
				                               				<option value="5">Unit</option>
				                               		</c:if>
				                               		<c:if test="${roleid =='357'}">
				                               				<option value="0">Dte Br</option>
				                               				<option value="0">Dte</option>
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br </option>
				                               				<option value="5">Unit</option>
				                               		</c:if>
										<c:if test="${roleid =='356'}">
				                               				<option value="0">Dte Br</option>
				                               				<option value="0">Dte</option>
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br </option>
				                               				<option value="5">Unit</option>
				                               		</c:if>

													<c:if test="${roleid =='336'}">
				                               				<option value="-1">Top Level</option>
				                               				<option value="0">Dte Br</option>
				                               				<option value="0">Dte</option>
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
				                               		</c:if>
				                               		<c:if test="${roleid =='310'}">
				                               				<option value="-1">Top Level</option>
				                               				<option value="0">Dte Br</option>
				                               				<option value="0">Dte </option>
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
				                               		</c:if>
													<c:if test="${roleid =='5'}">
															<option value="1">Command Br</option>
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='6'}">
				                               				<option value="2">Corps Br</option>
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='7'}">
				                               				<option value="3">Div Br</option>
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='8'}">
				                               				<option value="4">Bde Br</option>
				                               				<option value="5">Unit</option>
													</c:if>
		                  						</select>
											</div>
										</div>
	            					</div>
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Under Formation</label>
											</div>
											<div class="col-md-8">
											<input type="hidden" id="form_code_control1" name="form_code_control1" class="form-control" autocomplete="off">
											<input type="hidden" id="fmn_sus_no1" name="fmn_sus_no1" class="form-control" autocomplete="off">
												<select name="form_code_control" id="form_code_control" data-placeholder="Select the Formation..." class="form-control-sm form-control" readonly="readonly" title="Select Under Formation.">
													<option value="-1">--Select--</option>
												</select>
											</div>
										</div>
	            					</div>
	            					
						       </div>
						       <div class="col-md-12 from-group">
							       <div class="col-md-6">
			            				<div class="row form-group">
								                	<div class="col col-md-4">
								                  		<label class=" form-control-label"><strong style="color: red;">*</strong> Status</label> <br>
								                  	</div>
								                	<div class="col-12 col-md-8">
								                		<select name="status_sus_no" id="status_sus_no"  class="form-control-sm form-control" title="Select Status.">
						                               		<option value="Active">Active</option>
						                               		<option value="Inactive">Inactive</option>
				                  						</select>
								                	</div>
								       </div>
							       </div>
							        <div class="col-md-6">
							        	<input type="button" style="text-align:center;float:center;" value="Generate Unique No" onclick="return getSusIncr();" title="Generate Sus No.">
							        </div>
						       </div>
						       
				        		<div class="col-md-12 from-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label">Remarks</label>
								</div>
								<div class="col-md-10">
									<textarea rows="2" cols="250" class="form-control char-counter" placeholder="Enter Your Remarks..." id="remarks" name="remarks" maxlength="255" style="resize:none;" title="Enter Remarks."></textarea>
									</div>
								</div>
	              	</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-primary btn-sm nGlow" value="Clear" title="Click to Clear Data.">   
	              		<input type="submit" id="btnsave" class="btn btn-success btn-sm nGlow" value="Save" onclick="return validate();" title="Click to Save Data.">
	              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>
<script>
function validate(){
	if($("#sus_no").val() == ""){
		alert("Please Generate SUS No.");
		$("#sus_no").focus();
		return false;
    }	
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name.");
		$("#unit_name").focus();
		return false;
    }
	if($("#hq_type").val() == ""){
		alert("Please Select Hq Type.");
		$("#hq_type").focus();
		return false;
    }
	if($("#form_code_control").val() == "-1"){
		alert("Please Select Under Formation.");
		$("#form_code_control").focus();
		return false;
    }
}

function getFormation() {
	var hq_type = $("#hq_type").val();
	var hq_type1 = $("#hq_type :selected").text();
	if (hq_type1.indexOf("Brdsafas") < 0) {
		var form_code_control = $("#form_code_control1").val();
		$("#form_code_control").attr('readonly',false);
		if (hq_type =='5') {
			$.post("getAllFormationListAuto?"+key+"="+value, {a1 : roleSusNo},function(j) {
				var options = "";
				var a = [];
				var enc = j[j.length - 1].substring(0, 16);
				for (var i = 0; i < j.length; i++) {
					a[i] = dec(enc, j[i]);
				}
				var data = a[0].split(",");
				var datap;
				  for (var i = 0; i < data.length - 1; i++) {
					datap = data[i].split(":");
					options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
				}  
				$("#sus_no").val("");
				$("#form_code_control").html(options);
			});			
		} else {
			$.post("getAllFormationList?"+key+"="+value, {a1 : hq_type, a2 : form_code_control},function(j) {
				var options = '';
				var a = [];
				var enc = j[j.length - 1].substring(0, 16);
				for (var i = 0; i < j.length; i++) {
					a[i] = dec(enc, j[i]);
				}
				var data = a[0].split(",");
				var datap;
				
				for (var i = 0; i < data.length - 1; i++) {
					datap = data[i].split(":");
					options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
				}
				$("#sus_no").val("");
				$("#form_code_control").html(options);
			});
		}
	}
}

function getFormationAuto() {
	$("#form_code_control").attr('readonly',false);
	$.post("getAllFormationListAuto?"+key+"="+value, {a1 : roleSusNo},function(j) {
		var options = "";
		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0].split(",");
		var datap;
		  for (var i = 0; i < data.length - 1; i++) {
			datap = data[i].split(":");
			options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
			document.getElementById("form_code_control1").value=datap[1];
			document.getElementById("fmn_sus_no1").value=datap[2];			
		}  
		$("#sus_no").val("");
		$("#form_code_control").html(options);
	});
}

function getSusIncr() {
	if($("#unit_name").val() == ""){
		alert("Please some Unit Name");
		$("#unit_name").focus();
		return false;
    }
	if($("#hq_type").val() == ""){
		alert("Please Select Hq Type");
		$("#hq_type").focus();
		return false;
    }
	if($("#form_code_control").val() == "-1"){
		alert("Please Select Formation");
		$("#form_code_control").focus();
		return false;
    }	
	var sus_no = $("#sus_no").val();
	var a2="";
	var hq_type = $("#hq_type").val();
	var hq_type1 = $("#hq_type :selected").text();
	a2=$("#fmn_sus_no1").val();
	if (hq_type1.indexOf("Br") > 0) {
		a2=$("#form_code_control :selected").attr("name");
	} else {
		if (hq_type =='0') {
			a2="CXNX";
		} else {
			a2="DXNX";
		}
	}
	$.post("getSusIncrList?"+key+"="+value, {a1 : sus_no,a2:a2},function(j) {
		var options = 0;
		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0];
		if (data=='E') {
			alert("Maximum Br Generation Exceeded.");
			document.getElementById("sus_no").value="";
			$("#sus_no").attr("readonly","readonly");			
		} else {
			var datap;
			data = parseInt(data);
			if(data>=100000){
				datap='C'
			}else if(data>=10000 && data<100000){
				datap='C0'
			}else if(data>=10000 && data<10000){
				datap='C00'
			}else if(data>=100 && data<1000){
				datap='C000'
			}else if(data>=10 && data<100){
				datap='C0000'
			}else if(data<10){
				datap='C00000'
			} 
			options = datap+data;
			document.getElementById("sus_no").value=a[0];
			$("#sus_no").attr("readonly","readonly");
		}
	});
}
</script>
<script>
      $(document).ready(function() {      
    	  getFormationAuto();
    	  
    	  $(".char-counter").charCounter();
    	  
    	  $('#hq_type').change(function(){    			
    			if($("#hq_type").val() != ""){
    				$("#sus_no").val("");
    				getFormation();
    			}
    	  });
    	  
    	  $('#form_code_control').select2();
    	  
    	  $('#form_code_control').change(function(){    			
  				$("#sus_no").val("");
  		  });    	  
    	  $("#unit_name").keypress(function(){
  			var unit_name = this.value;
  				$("#sus_no").val("");
  				 var susNoAuto=$("#unit_name");
  				  susNoAuto.autocomplete({
  				      source: function( request, response ) {
  				        $.ajax({
  				        type: 'POST',
  				        url: "getUnitsNameActiveList?"+key+"="+value,
  				        data: {unit_name:unit_name},
  				          success: function( data ) {
  				        	 var susval = [];
  				        	  var length = data.length-1;
  				        	var enc = "";
  				        	if(data.length != 0){
  				        		enc = data[length].substring(0,16);
  				        	}
  					        	for(var i = 0;i<data.length;i++){
  					        		susval.push(dec(enc,data[i]));
  					        	}
  					        	var dataCountry1 = susval.join("|");
  				            var myResponse = []; 
  				            var autoTextVal=susNoAuto.val();

  							$.each(dataCountry1.toString().split("|"), function(i,e){
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
  				        	//getSusIncr();
 				        	 $("#sus_no").attr('readonly',true);
 				        	  return true;	
  				          }   	         
  				      } 
  				      /* select: function( event, ui ) {
  				      	$(this).val(ui.item.value);
  				        getOrbatDetailsFromUnitName($(this).val());
  				      } */ 	     
  				});
  			
  		});
    	  
    		$("input#sus_no").keyup(function(){
    			var sus_no = this.value;
    			var unitNameAuto=$("#sus_no");
    			unitNameAuto.autocomplete({
    			      source: function( request, response ) {
    			        $.ajax({
    			        type: 'POST',
    			        url: "getSusNoActiveList?"+key+"="+value,
    			        data: {sus_no:sus_no},
    			          success: function( data ) {
    			        	  var susval = [];
    			        	  var length = data.length-1;
    			        	  var enc = "";
					        	if(data.length != 0){
					        		enc = data[length].substring(0,16);
					        	}
    				        	for(var i = 0;i<data.length;i++){
    				        		susval.push(dec(enc,data[i]));
    				        	}
    				        	var dataCountry1 = susval.join("|");
    			            var myResponse = []; 
    			            var autoTextVal=unitNameAuto.val();
    						$.each(dataCountry1.toString().split("|"), function(i,e){
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
    			        	  return true;
    			          }   	         
    			      }/* , 
    			      select: function( event, ui ) {
    			      	var sus_no = ui.item.value;
    			    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
    			       		var length = j.length-1;
    						var enc = j[length].substring(0,16);
    				   		$("#unit_name").val(dec(enc,j[0]));
    				   		getOrbatDetailsFromUnitName(dec(enc,j[0]))
    			       	});
    			     } */
    			});
    		});
    	});
</script>
