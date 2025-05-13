<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form name="addition_of_ba_no" id="addition_of_ba_no"
	action="addition_of_ba_noAction" method="post" class="form-horizontal"
	commandName="addition_of_ba_noCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h3>Addition Of BA No<br></h3></div>
					<div class="card-header"><strong>Basic Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>BA No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="ba_no" name="ba_no" placeholder=""
											class="form-control" oninput="isalphanumeric();"
											maxlength="9" /> <input type="hidden" id="parent_request_id"
											name="parent_request_id" placeholder="" class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
									<div class="row form-group">
										<div class=" col-md-4">
											<label class=" form-control-label">Type of Vehicle </label>
										</div>
										<div class="col-md-8">
											<div class="form-check-inline form-check">
												<label for="inline-radio" class="form-check-label ">
													<input type="radio" id="type_of_veh" name="type_of_veh"
													value="A" class="form-check-input" disabled="disabled">A Vehicle
												</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio"
													class="form-check-label "> <input type="radio"
													id="type_of_veh" name="type_of_veh" value="B"
													class="form-check-input" disabled="disabled">B Vehicle
												</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio"
													class="form-check-label "> <input type="radio"
													id="type_of_veh" name="type_of_veh" value="C"
													class="form-check-input" disabled="disabled">C Vehicle
												</label>
											</div>
										</div>
									</div>
								</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Chassis No </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="chesis_no" name="chesis_no" placeholder="" class="form-control" readonly="readonly" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Engine No </label>
									</div>
									<div class=" col-md-8">
										<input type="text" id="engine_no" name="engine_no" placeholder="" class="form-control" readonly="readonly" autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
							<div class="col-md-12">
								<div class="col-md-6">
								<div class="row form-group" >
		 								    <div class="col col-md-4">
		                                          <label class=" form-control-label">MCT </label>
		                                    </div>
		                                    <div class="col-12 col-md-8">
		    						               <input type="text" id="mct_no" name="mct_no" placeholder="" readonly="readonly" class="form-control autocomplete" autocomplete="off">
		  					                </div>
		                           </div>
		                          </div>
								<div class="col-md-6">
								<div class="row form-group" >
		 								    <div class="col col-md-4">
		                                          <label class=" form-control-label"> Nomenclature </label>
		                                    </div>
		                                    <div class="col-12 col-md-8">
		    						               <input type="text" id="std_nomclature" name="std_nomclature" placeholder="" readonly="readonly" class="form-control autocomplete" autocomplete="off">
		  					                </div>
		                               </div>
		                          </div>
		                     </div>
		                     <div class="col-md-12">
								<div class="col-md-6">
								<div class="row form-group" >
		 								    <div class="col col-md-4">
		                                          <label class=" form-control-label">Old EM/BA No </label>
		                                    </div>
		                                    <div class="col-12 col-md-8">
		    						               <input type="text" id="old_ba_no" name="old_ba_no" placeholder="" readonly="readonly" class="form-control autocomplete" autocomplete="off">
		  					                </div>
		                           </div>
		                          </div>
								
		                     </div>
					</div>

				 		<div class="col-md-6 col-md-offset-1">	  
					              <div class="row form-group">
		 								    <div class="col col-md-6">
		                                         <input type="button" class="btn btn-success" onclick="return existingBANo()" id="checkBano" value="Check BA No"> 
		                                    </div>
		                           </div> 	
				</div>
				<div class="form-control card-footer" align="center">
					<a href="addition_of_ba_no" class="btn btn-success btn-sm" >Clear</a> 
					<input id="submit_id" type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="return validate();" style="display: none";>
					
				</div>
			</div>
		</div>
	</div>
</div>
</form:form>

<script>
	function validate() {
		if ($("#ba_no").val() == "") {
			alert("Please Enter the BA No.");
			return false;
		}
		
		if ($('input[name="type_of_veh"]:checked').length == 0) {
	        alert("Please Select Type Of Vehicle.");
	        return false;
		}  
				
		if ($("#chesis_no").val() == "") {
			alert("Please Enter the Chesis_no No.");
			return false;
		}
		
		if ($("#engine_no").val() == "") {
			alert("Please Enter the Engine No.");
			return false;
		}
		
		if ($("#mct_no").val() == "") {
			alert("Please Enter the MCT No.");
			return false;
		}
		
		if ($("#std_nomclature").val() == "") {
			alert("Please Enter the Std Nomclature.");
			return false;
		}
	}

	function isalphanumeric() {
		var ba_no = document.getElementById("ba_no").value;
		var n_ba_no = ba_no.replace(/[^a-z0-9]/gi, '').toUpperCase();
		document.getElementById("ba_no").value = n_ba_no;
	}

	function existingBANo() {
		var ba_no = document.getElementById("ba_no").value;
		var dt_year = ba_no.substring(0, 2);
		var veh_code = ba_no.substring(2, 3);
		var serialNo = ba_no.substring(3, 9);

		if (ba_no.length == 9) {
			$.ajax({
						type : 'POST',
						url : "getBaNoGenerate?"+key+"="+value,
						data : {
							ba_no : ba_no,
							serialNo : serialNo,
							veh_code : veh_code,
							dt_year : dt_year
						},
						success : function(response) {
							if (response != "") {
								if (response != "Already Exist BA No") {
									$("#ba_no").val(response);
									$('#ba_no').attr('readonly', true);
									 $('input[name=type_of_veh]').attr(
											"disabled", false);
									$('input[name=type_of_request]').attr(
											"disabled", false); 
									document.getElementById('checkBano').disabled = true;
									$('#chesis_no').attr('readonly', false);
									$('#engine_no').attr('readonly', false);
									$('#mct_no').attr('readonly', false);
									$('#submit_id').show();
									$('#std_nomclature').attr('readonly', false);
									$('#old_ba_no').attr('readonly', false);
								}
								else {
									alert(response);
								}
							} 
						}
					});
		} else {
			alert("BA No Length Criteria Not Match.");
		}

	}


$(function() {

		$("input#mct_no").keyup(function(){
			var mct = this.value;
			var mct_numberAuto=$("#mct_no");
			  var type_of_vehicle = $("input[name='type_of_veh']:checked").val();
		
			mct_numberAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getMctNoDetailList?"+key+"="+value,
			        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
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
			        	alert("Please Enter Valid MCT No.");
			        	$("#std_nomclature").val("");
			        	mct_numberAuto.val("");	        	  
			        	mct_numberAuto.focus();
			        	return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var mct = ui.item.value;
			      	$.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct:mct}).done(function(j) {				
			      		var length = j.length-1;
      		        	var enc = j[length].substring(0,16);
      		        	$("#std_nomclature").val(dec(enc,j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		
		$("input#std_nomclature").keyup(function(){
			var mctNomen = this.value;
			 var type_of_vehicle = $("input[name='type_of_veh']:checked").val();
			var mct_nomenAuto=$("#std_nomclature");
			
			if(type_of_vehicle == "0"){
				alert("please select  Type of Vehicle.");
				$("#std_nomclature").focus();
				$("#mct_no").val("");
		    	mct_nomenAuto.val("");	  
				return false;
			}
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
			        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
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
			        	  alert("Please Enter Valid  Nomenclature.");
			        	  $("#mct").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var nomencatre = ui.item.value;
			      	$.post("getStdNomenclaturetoMctNoList?"+key+"="+value, {std_nomclature:nomencatre}).done(function(data) {				
				      		var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#mct_no").val(dec(enc,data[0]));
			        	
			        	$.post("getMctNotoStdNomenclatureList?"+key+"="+value,{mct:dec(enc,data[0])}).done(function(j) {				
			        		var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#std_nomclature").val(dec(enc,j[0]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});
			        	
					}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			});
		});
		
		
	});
	
</script>

